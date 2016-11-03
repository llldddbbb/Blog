package com.blog.controller;

import com.blog.entity.*;
import com.blog.service.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by ldb on 2016/10/30.
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

    private static ApplicationContext applicationContext;


    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application=servletContextEvent.getServletContext();
        BlogService blogService=(BlogService)applicationContext.getBean("blogService");
        BlogTypeService blogTypeService=(BlogTypeService)applicationContext.getBean("blogTypeService");
        BlogTagService blogTagService=(BlogTagService)applicationContext.getBean("blogTagService");
        BlogerService blogerService=(BlogerService)applicationContext.getBean("blogerService");
        LinkService linkService=(LinkService)applicationContext.getBean("linkService");


        List<Link> linkList=linkService.findLinkList();
        application.setAttribute("linkList",linkList);

        List<Blog> recommendBlogList=blogService.findRecommendBlogList();
        application.setAttribute("recommendBlogList",recommendBlogList);


        List<BlogType> blogTypeList=blogTypeService.findBlogTypeList();
        application.setAttribute("blogTypeList",blogTypeList);

        List<BlogTag> blogTagList=blogTagService.findBlogTagList();
        application.setAttribute("blogTagList",blogTagList);

        Bloger bloger=blogerService.getBloger();
        application.setAttribute("bloger",bloger);

    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
