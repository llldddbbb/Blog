package com.blog;

import com.blog.entity.Blog;
import com.blog.entity.BlogAdvice;
import com.blog.entity.PageBean;
import com.blog.service.BlogAdviceService;
import com.blog.service.BlogService;
import com.blog.service.BlogerService;
import com.blog.util.PageUtil;
import com.blog.util.StringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class BlogApplication {

	@Resource
	private BlogService blogService;

	@Resource
	private BlogerService blogerService;

	@Resource
	private BlogAdviceService blogAdviceService;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@RequestMapping("/")
	public String goIndex(@RequestParam(value="page",required=false)String page, HttpServletRequest request){
	if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt("6"));
		Map<String,Object> map=new HashMap<>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		int totalNum=blogService.getBlogTotal(new HashMap<String,Object>());
		String pageCode= PageUtil.getPageCode("/?a=2", Integer.parseInt(page), totalNum, pageBean.getPageSize());
		List<Blog> blogList=blogService.findBlogList(map);
		for(Blog blog:blogList){
			Map<String,Object> m=new HashMap<>();
			m.put("blogId", blog.getId());
			blog.setContent(StringUtil.Html2Text(blog.getContent()));
		}
		Map<String,Object> blogAdviceMap=new HashMap<String,Object>();
		blogAdviceMap.put("start", 0);
		blogAdviceMap.put("pageSize", 3);
		List<BlogAdvice> newsestBlogAdviceList=blogAdviceService.findBlogAdviceList(blogAdviceMap);
		List<Blog> dateCountList=blogService.findDateCountList();
		List<Blog> hotBlogList=blogService.findHotBlogList();
		List<Blog> technologyBlogList=blogService.findTechnologyBlogList();
		HttpSession session=request.getSession();
		session.setAttribute("hotBlogList",hotBlogList);
		session.setAttribute("technologyBlogList",technologyBlogList);
		session.setAttribute("blogList",blogList);
		session.setAttribute("newsestBlogAdviceList",newsestBlogAdviceList);
		session.setAttribute("dateCountList",dateCountList);
		//session.setAttribute("blogListTemp", "foreground/blog/blogList");
		session.setAttribute("pageCode", pageCode);

		blogerService.updateWebClick();

		return "index";
	}

	@RequestMapping("/background")
	public String goLogin(){
		return "background/login";
	}



}
