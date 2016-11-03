package com.blog.controller;


import com.blog.entity.Blog;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.service.BlogTagService;
import com.blog.service.BlogTypeService;
import com.blog.util.DateUtil;
import com.blog.util.PageUtil;
import com.blog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ldb on 2016/9/22.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    
    @Resource
    private BlogTypeService blogTypeService;
    
    
    @Resource
    private BlogTagService blogTagService;


    
    @RequestMapping("/show")
    public String show(@RequestParam(value="page",required=false)String page,@RequestParam(value="id")String id,HttpServletRequest request){
    	HttpSession session=request.getSession();
    	if(StringUtil.isEmpty(page)){
    		page="1";
    	}
    	Blog blog=blogService.getBlogById(Integer.parseInt(id));
    	blogService.updateBlogReadNum(Integer.parseInt(id));
    	request.setAttribute("blog", blog);
    	return "foreground/blog/blogShow";
    }


    @RequestMapping("/list")
    public String list(@RequestParam(required=false,value="typeId")String typeId,@RequestParam(required=false,value="tagId")String tagId,@RequestParam(required=false,value="publishTime")String publishTime,@RequestParam(value="page",required=false)String page, HttpServletRequest request) throws Exception{
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        String pageCode=null;
        int totalNum=0;
        PageBean pageBean=new PageBean(Integer.parseInt(page),6);
        Map<String,Object> map=new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());

        if(StringUtil.isNotEmpty(typeId)){
            map.put("typeId", typeId);
            totalNum=blogService.getBlogTotal(map);
            pageCode=PageUtil.getPageCode("blog/list.do?typeId="+typeId, Integer.parseInt(page), totalNum, pageBean.getPageSize());
        }
        if(StringUtil.isNotEmpty(tagId)){
            map.put("tagId", tagId);
            totalNum=blogService.getBlogTotal(map);
            pageCode=PageUtil.getPageCode("blog/list.do?tagId="+tagId, Integer.parseInt(page), totalNum, pageBean.getPageSize());
        }
        if(StringUtil.isNotEmpty(publishTime)){
            String publishTimeStr= DateUtil.formatStrToSQL(publishTime, "yyyy年MM月","yyyy-MM");
            map.put("publishTime", publishTimeStr);
            totalNum=blogService.getBlogTotal(map);
            pageCode= PageUtil.getPageCode("blog/list.do?publishTime="+publishTime, Integer.parseInt(page), totalNum, pageBean.getPageSize());
        }
        List<Blog> blogList=blogService.findBlogList(map);
        for(Blog b:blogList){
            Map<String,Object> m=new HashMap<>();
            m.put("blogId", b.getId());
            b.setContent(StringUtil.Html2Text(b.getContent()));
        }
        HttpSession session=request.getSession();
        session.setAttribute("blogList", blogList);
        session.removeAttribute("pageCode");
        session.setAttribute("pageCode", pageCode);
        return "index";
    }

}
