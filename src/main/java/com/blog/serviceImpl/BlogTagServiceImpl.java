package com.blog.serviceImpl;

import com.blog.entity.BlogTag;
import com.blog.mapper.BlogTagMapper;
import com.blog.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * Created by ldb on 2016/9/22.
 */
@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Override
    public List<BlogTag> findBlogTagList() {
        return blogTagMapper.findBlogTagList();
    }

	@Override
	public int saveBlogTag(BlogTag blogTag) {
		return blogTagMapper.saveBlogTag(blogTag);
	}

	@Override
	public int updateBlogTag(BlogTag blogTag) {
		return blogTagMapper.updateBlogTag(blogTag);
	}

	@Override
	public int deleteBlogTag(Integer id) {
		return blogTagMapper.deleteBlogTag(id);
	}

	@Override
	public List<BlogTag> findBlogTagComboListByTypeId(Integer typeId) {
		return blogTagMapper.findBlogTagComboListByTypeId(typeId);
	}
}
