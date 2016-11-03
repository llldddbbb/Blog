package com.blog.serviceImpl;

import com.blog.mapper.BlogTypeMapper;
import com.blog.entity.BlogType;
import com.blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeMapper blogTypeMapper;


    @Override
    public List<BlogType> findBlogTypeList() {
        return blogTypeMapper.findBlogTypeList();
    }


	@Override
	public int updateBlogType(BlogType blogType) {
		return blogTypeMapper.updateBlogType(blogType);
	}


	@Override
	public List<BlogType> findBlogTypeComboList() {
		return blogTypeMapper.findBlogTypeComboList();
	}
	
	
}
