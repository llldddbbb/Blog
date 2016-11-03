package com.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.blog.entity.*;
import com.blog.service.*;
import com.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {
    @Resource
    private LinkService linkService;
    @Resource
    private BlogService blogService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogerService blogerService;
    @Resource
    private BlogTagService blogTagService;

    @RequestMapping("/refreshSystem")
    public String refreshSystem(HttpServletRequest request, HttpServletResponse response)throws Exception{
        ServletContext application=request.getServletContext();

        application.removeAttribute("linkList");
        List<Link> linkList=linkService.findLinkList();
        application.setAttribute("linkList",linkList);

        application.removeAttribute("hotBlogList");
        List<Blog> hotBlogList=blogService.findHotBlogList();
        application.setAttribute("hotBlogList",hotBlogList);

        application.removeAttribute("recommendBlogList");
        List<Blog> recommendBlogList=blogService.findRecommendBlogList();
        application.setAttribute("recommendBlogList",recommendBlogList);

        application.removeAttribute("technologyBlogList");
        List<Blog> technologyBlogList=blogService.findTechnologyBlogList();
        application.setAttribute("technologyBlogList",technologyBlogList);

        application.removeAttribute("blogTypeList");
        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        application.setAttribute("blogTypeList",blogTypeList);

        application.removeAttribute("blogTagList");
        List<BlogTag> blogTagList=blogTagService.findBlogTagList();
        application.setAttribute("blogTagList",blogTagList);

        application.removeAttribute("bloger");
        Bloger bloger=blogerService.getBloger();
        application.setAttribute("bloger",bloger);

        JSONObject result=new JSONObject();
        result.put("success", true);

        ResponseUtil.write(result, response);
        return null;
    }
}
