package com.blog.mapper;

import com.blog.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
@Mapper
public interface BlogTagMapper {

    public List<BlogTag> findBlogTagList();

    public BlogTag getBlogTagById(Integer id);
    
    public int saveBlogTag(BlogTag blogTag);
    
    public int updateBlogTag(BlogTag blogTag);
    
    public int deleteBlogTag(Integer id);
    
    public List<BlogTag> findBlogTagComboListByTypeId(Integer typeId);
}
