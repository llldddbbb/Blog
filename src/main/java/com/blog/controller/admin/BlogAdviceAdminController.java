package com.blog.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.BlogAdvice;
import com.blog.entity.PageBean;
import com.blog.service.BlogAdviceService;
import com.blog.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/blogAdvice")
public class BlogAdviceAdminController {

    @Resource
    private BlogAdviceService blogAdviceService;

    @RequestMapping("/preAdvice")
    public String preAdvice(@RequestParam(value="page",required=false)String page, HttpServletRequest request){
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("advicePageSize")));
        Map<String,Object> map=new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        int totalNum=blogAdviceService.getBlogAdviceTotal();
        String pageCode= PageUtil.getPageCode("/Blog/blogAdvice/preAdvice.do?a=2", Integer.parseInt(page), totalNum, pageBean.getPageSize());
        List<BlogAdvice> blogAdviceList=blogAdviceService.findBlogAdviceList(map);
        request.setAttribute("blogAdviceList", blogAdviceList);
        request.setAttribute("pageCode", pageCode);
        return "foreground/blogAdvice/blogAdvice";
    }

    @RequestMapping("list")
    public String list(@RequestParam(value="page")String page,@RequestParam(value="rows")String rows,HttpServletResponse response)throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<BlogAdvice> blogAdviceList=blogAdviceService.findBlogAdviceList(map);
        JSONObject result=new JSONObject();
        JSONArray rowsDate=new JSONArray();
        for(BlogAdvice blogAdvice:blogAdviceList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", blogAdvice.getId());
            jsonObject.put("nickName", blogAdvice.getNickName());
            jsonObject.put("userIP", blogAdvice.getUserIP());
            jsonObject.put("content", blogAdvice.getContent());
            jsonObject.put("publishTime", DateUtil.formatDateToStr(blogAdvice.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
            jsonObject.put("reply", blogAdvice.getReply());
            rowsDate.add(jsonObject);
        }
        int total=blogAdviceService.getBlogAdviceTotal();
        result.put("total", total);
        result.put("rows", rowsDate);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/delete")
    public String delete(String ids,HttpServletResponse response)throws Exception{
        int resultNum=0;
        String[] idsStr=ids.split(",");
        for(String id:idsStr){
            resultNum+=blogAdviceService.deleteBlogAdvice(Integer.parseInt(id));
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

    @RequestMapping("/save")
    public String save(BlogAdvice blogAdvice,HttpServletResponse response,HttpServletRequest request)throws Exception{
        JSONObject result=new JSONObject();
        int resultNum=0;
        resultNum=blogAdviceService.updateBlogAdviceReply(blogAdvice);
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }
}
