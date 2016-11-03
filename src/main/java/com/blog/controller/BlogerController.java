package com.blog.controller;

import com.blog.entity.Bloger;
import com.blog.service.BlogerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ldb on 2016/10/29.
 */
@Controller
@RequestMapping("/bloger")
public class BlogerController {

    @Resource
    private BlogerService blogerService;

    @RequestMapping("/login")
    public String login(@RequestParam(value="userName")String userName, @RequestParam(value="password")String password, HttpServletRequest request){
        HttpSession session=request.getSession();
        Bloger bloger=new Bloger(userName,password);
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(bloger.getUserName(), bloger.getPassword());
        try{
            subject.login(token);
            session.setAttribute("currentBloger",bloger);
            return "background/main";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("bloger", bloger);
            request.setAttribute("error", "用户名或密码错误");
            return "background/login";
        }

    }

}
