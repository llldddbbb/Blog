package com.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.Bloger;
import com.blog.service.BlogerService;
import com.blog.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldb on 2016/11/1.
 */
@Controller
@RequestMapping("/admin/bloger")
public class BlogerAdminController {

    @Resource
    private BlogerService blogerService;

    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,String id,HttpServletResponse response)throws Exception{
        int resultNum=0;
        Map<String,Object> map=new HashMap<>();
        map.put("password", newPassword);
        map.put("id", id);
        resultNum=blogerService.updatePassword(map);
        JSONObject result=new JSONObject();
        if(resultNum>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
//        HttpSession session=request.getSession();
//        session.invalidate();
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/templates/background/login.html";
    }

    @RequestMapping("/list")
    public String list(HttpServletResponse response)throws Exception{
        Bloger bloger=blogerService.getBloger();
        JSONArray rows = JSON.parseArray("["+JSON.toJSONString(bloger)+"]");
        JSONObject result=new JSONObject();
        result.put("rows", rows);
        ResponseUtil.write(result, response);
        return null;
    }

    @RequestMapping("/save")
    public String save(Bloger bloger,HttpServletResponse response)throws Exception{
        int resultNum=blogerService.updateBlogerInfo(bloger);
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
