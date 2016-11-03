package com.blog.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.BlogTag;
import com.blog.entity.BlogType;
import com.blog.service.BlogTagService;
import com.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/blogTag")
public class BlogTagAdmin {

    @Resource
    private BlogTagService blogTagService;

    @RequestMapping("/list")
    public String list(HttpServletResponse response)throws Exception{
        List<BlogTag> blogTagList=blogTagService.findBlogTagList();
        JSONArray rows=new JSONArray();
        for(BlogTag blogTag:blogTagList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", blogTag.getId());
            jsonObject.put("tagName", blogTag.getTagName());
            jsonObject.put("typeName", blogTag.getBlogType().getTypeName());
            jsonObject.put("typeId", blogTag.getBlogType().getId());
            rows.add(jsonObject);
        }
        JSONObject result=new JSONObject();
        result.put("rows", rows);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/save")
    public String save(BlogTag blogTag,String typeId,HttpServletResponse response)throws Exception{
        BlogType blogType=new BlogType();
        blogType.setId(Integer.parseInt(typeId));
        blogTag.setBlogType(blogType);
        int resultNum=0;
        if(blogTag.getId()==0){
            resultNum=blogTagService.saveBlogTag(blogTag);
        }else{
            resultNum=blogTagService.updateBlogTag(blogTag);
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

    @RequestMapping("delete")
    public String delete(String id,HttpServletResponse response)throws Exception{
        int resultNum=blogTagService.deleteBlogTag(Integer.parseInt(id));
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/findBlogTagComboListByTypeId")
    public List<BlogTag> findBlogTagComboListByTypeId(String typeId, HttpServletResponse response)throws Exception{
        List<BlogTag> blogTagComboList=blogTagService.findBlogTagComboListByTypeId(Integer.parseInt(typeId));
        JSONArray jsonArray=new JSONArray();
        for(BlogTag blogTag:blogTagComboList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", blogTag.getId());
            jsonObject.put("tagName", blogTag.getTagName());
            jsonArray.add(jsonObject);
        }
        ResponseUtil.write(jsonArray,response);
        return null;
    }
}
