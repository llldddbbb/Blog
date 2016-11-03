package com.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.BlogType;
import com.blog.service.BlogTypeService;
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
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
    @Resource
    private BlogTypeService blogTypeService;

    @RequestMapping("/list")
    public String list(HttpServletResponse response)throws Exception{
        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        JSONArray rows=JSON.parseArray(JSON.toJSONString(blogTypeList));
        JSONObject result=new JSONObject();
        result.put("rows", rows);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/findBlogTypeComboList")
    public String findBlogTypeComboList(HttpServletResponse response)throws Exception{
        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        JSONArray result= JSON.parseArray(JSON.toJSONString(blogTypeList));
        ResponseUtil.write(result, response);
        return null;
    }
}
