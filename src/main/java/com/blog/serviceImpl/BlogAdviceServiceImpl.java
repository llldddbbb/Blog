package com.blog.serviceImpl;

import com.blog.entity.BlogAdvice;
import com.blog.mapper.BlogAdviceMapper;
import com.blog.service.BlogAdviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("blogAdviceService")
public class BlogAdviceServiceImpl implements BlogAdviceService{
	
	@Resource
	private BlogAdviceMapper blogAdviceMapper;
	
	@Override
	public List<BlogAdvice> findBlogAdviceList(Map<String,Object> map) {
		return blogAdviceMapper.findBlogAdviceList(map);
	}

	@Override
	public int saveBlogAdvice(BlogAdvice blogAdvice) {
		return blogAdviceMapper.saveBlogAdvice(blogAdvice);
	}

	@Override
	public int updateBlogAdviceReply(BlogAdvice blogAdvice) {
		return blogAdviceMapper.updateBlogAdviceReply(blogAdvice);
	}

	@Override
	public int deleteBlogAdvice(Integer id) {
		return blogAdviceMapper.deleteBlogAdvice(id);
	}

	@Override
	public int getBlogAdviceTotal() {
		return blogAdviceMapper.getBlogAdviceTotal();
	}


}
