package com.blog.service;

import com.blog.entity.BlogTag;

import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
public interface BlogTagService {

    public List<BlogTag> findBlogTagList();
    
    public int saveBlogTag(BlogTag blogTag);
    
    public int updateBlogTag(BlogTag blogTag);
    
    public int deleteBlogTag(Integer id);
    
    public List<BlogTag> findBlogTagComboListByTypeId(Integer typeId);
}
