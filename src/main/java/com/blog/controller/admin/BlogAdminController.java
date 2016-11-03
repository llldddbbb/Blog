package com.blog.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.Blog;
import com.blog.entity.BlogType;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.util.DateUtil;
import com.blog.util.ResponseUtil;
import com.blog.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Resource
    private BlogService blogService;

    @Resource
    private BlogTypeService blogTypeService;


    @RequestMapping("/list")
    public String list_background(String page,String rows,@RequestParam(value="s_title",required=false)String s_title,HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String ,Object> map=new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        if(StringUtil.isNotEmpty(s_title)){
            map.put("title", StringUtil.formatSQLlike(s_title));
        }
        int total=blogService.getBlogTotal(map);
        List<Blog> blogList=blogService.findBlogList(map);
        JSONArray jsonArray=new JSONArray();
        for(Blog blog:blogList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", blog.getId());
            jsonObject.put("title", blog.getTitle());
            jsonObject.put("publishTime",DateUtil.formatDateToStr(blog.getPublishTime(), "yyyy-MM-dd HH:mm:ss") );
            jsonObject.put("typeName", blog.getBlogType().getTypeName());
            if(blog.getBlogTag()!=null){
                jsonObject.put("tagName", blog.getBlogTag().getTagName());
            }else{
                jsonObject.put("tagName", "");
            }
            jsonObject.put("isRecommend", blog.getIsRecommend());
            jsonArray.add(jsonObject);
        }
        JSONObject result=new JSONObject();
        result.put("total", total);
        result.put("rows", jsonArray);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/preSave")
    public String preSave(HttpServletRequest request,HttpServletResponse response)throws Exception{
        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        request.setAttribute("blogTypeList", blogTypeList);
        return "background/blog/blogWrite";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(value="id_background",required=false)String id_background,Blog blog,HttpServletResponse response)throws Exception{
        int resultNum=0;
        if("-1".equals(id_background)){
            blog.setCoverImageName("s1.jpg");
            resultNum=blogService.saveBlog(blog);
        }else{
            blog.setId(Integer.parseInt(id_background));
            resultNum=blogService.updateBlog(blog);
        }
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }


    @RequestMapping("/delete")
    public String delete(String id,HttpServletResponse response)throws Exception{
        int resultNum=blogService.deleteBlog(Integer.parseInt(id));
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }


    @RequestMapping("/preUpdate")
    public String preUpdate(String id,HttpServletRequest request)throws Exception{
        Blog blog=blogService.getBlogById(Integer.parseInt(id));
        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        request.setAttribute("blogTypeList", blogTypeList);
        request.setAttribute("blog_background", blog);
        if(blog.getBlogTag()!=null){
            request.setAttribute("selectTag",blog.getBlogTag().getId());
        }
        return "background/blog/blogWrite";
    }

    @RequestMapping("saveMore")
    public String saveMore(@RequestParam(value = "file") MultipartFile file,@RequestParam(value="id",required = false) String id, HttpServletRequest request,HttpServletResponse response)throws Exception{
        Map<String,Object> map=new HashMap<>();
        String fileName=request.getSession().getServletContext().getRealPath("/");
        String imageName=DateUtil.getCurrentDateStr();
        map.put("coverImageName",imageName+"."+file.getOriginalFilename().split("\\.")[1]);
        String filePath=fileName+"Blog\\coverImages\\"+imageName+"."+file.getOriginalFilename().split("\\.")[1];
        map.put("id", id);
        FileUtils.writeByteArrayToFile(new File(filePath),file.getBytes());
        int resultNum=0;
        resultNum=blogService.saveMore(map);
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }
}
