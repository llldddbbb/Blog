package com.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.blog.entity.BlogAdvice;
import com.blog.entity.PageBean;
import com.blog.service.BlogAdviceService;
import com.blog.util.PageUtil;
import com.blog.util.ResponseUtil;
import com.blog.util.StringUtil;
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
 * Created by ldb on 2016/9/22.
 */
@Controller
@RequestMapping("/blogAdvice")
public class BlogAdviceController {

	@Resource
	private BlogAdviceService blogAdviceService;

	@RequestMapping("/preAdvice")
	public String preAdvice(@RequestParam(value="page",required=false)String page,HttpServletRequest request){
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),15);
		Map<String,Object> map=new HashMap<>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		int totalNum=blogAdviceService.getBlogAdviceTotal();
		String pageCode=PageUtil.getPageCode("/blogAdvice/preAdvice.do?a=2", Integer.parseInt(page), totalNum, pageBean.getPageSize());
		List<BlogAdvice> blogAdviceList=blogAdviceService.findBlogAdviceList(map);
		request.setAttribute("blogAdviceList", blogAdviceList);
		request.setAttribute("pageCode", pageCode);
		return "foreground/blogAdvice/blogAdvice";
	}


	@RequestMapping("save")
	public String save(BlogAdvice blogAdvice,HttpServletResponse response,HttpServletRequest request)throws Exception{
		String userIP=request.getRemoteAddr();//获取IP
		int resultNum=0;
		JSONObject result=new JSONObject();
		if(StringUtil.isEmpty(blogAdvice.getNickName())||StringUtil.isEmpty(blogAdvice.getContent())){
			result.put("success", false);
			ResponseUtil.write(result, response);
			return null;
		}
		blogAdvice.setUserIP(userIP);
		resultNum=blogAdviceService.saveBlogAdvice(blogAdvice);
		if(resultNum>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(result, response);
		return null;
	}




}
