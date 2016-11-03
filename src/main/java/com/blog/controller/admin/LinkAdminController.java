package com.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.Link;
import com.blog.service.LinkService;
import com.blog.util.ResponseUtil;
import com.blog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    @RequestMapping("/list")
    public String list(HttpServletResponse response)throws Exception{
        List<Link> linkList=linkService.findLinkList();
        String rowsStr= JSON.toJSONString(linkList);
        JSONArray rows=JSON.parseArray(rowsStr);
        JSONObject result=new JSONObject();
        result.put("rows", rows);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/save")
    public String save(Link link, @RequestParam(value="id",required=false)String id, HttpServletResponse response)throws Exception{
        int resultNum=0;
        if(StringUtil.isNotEmpty(id)){
            resultNum=linkService.updateLink(link);
        }else{
            resultNum=linkService.saveLink(link);
        }
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("error", "保存失败!");
        }
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/delete")
    public String delete(String ids,HttpServletResponse response)throws Exception{
        String[] idsArr=ids.split(",");
        int resultNum=0;
        for(String id:idsArr){
            resultNum+=linkService.deleteLink(Integer.parseInt(id));
        }
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("error", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }
}
