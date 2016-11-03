package com.blog.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.mapper.BlogMapper;
import com.blog.entity.Blog;
import com.blog.service.BlogService;

/**
 * Created by ldb on 2016/9/22.
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;


    @Override
    public List<Blog> findRecommendBlogList() {
        return blogMapper.findRecommendBlogList();
    }

    @Override
    public List<Blog> findHotBlogList() {
        return blogMapper.findHotBlogList();
    }

    @Override
    public List<Blog> findTechnologyBlogList() {
        return blogMapper.findTechnologyBlogList();
    }

    @Override
    public List<Blog> findBlogList(Map<String,Object> map) {
        return blogMapper.findBlogList(map);
    }

	@Override
	public List<Blog> findDateCountList() {
		return blogMapper.findDateCountList();
	}

	@Override
	public Blog getBlogById(Integer id) {
		return blogMapper.getBlogById(id);
	}

	@Override
	public int updateBlogReadNum(Integer id) {
		return blogMapper.updateBlogReadNum(id);
	}

	@Override
	public int getBlogTotal(Map<String,Object> map) {
		return blogMapper.getBlogTotal(map);
	}

	@Override
	public int saveBlog(Blog blog) {
		return blogMapper.saveBlog(blog);
	}

	@Override
	public int deleteBlog(Integer id) {
		return blogMapper.deleteBlog(id);
	}

	@Override
	public int saveMore(Map<String, Object> map) {
		return blogMapper.saveMore(map);
	}

	@Override
	public int updateBlog(Blog blog) {
		return blogMapper.updateBlog(blog);
	}

}
