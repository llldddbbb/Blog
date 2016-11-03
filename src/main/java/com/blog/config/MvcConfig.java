package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ldb on 2016/11/1.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/background/**")
                .addResourceLocations("classpath:/templates/background/");
        registry.addResourceHandler("/static/ueditor/**")
                .addResourceLocations("claspath:/static/ueditor/");
    }
}
