package com.blog.mapper;

import com.blog.entity.BlogType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
@Mapper
public interface BlogTypeMapper {

    public List<BlogType> findBlogTypeList();

    public BlogType getBlogTypeById(Integer typeId);
    
    public int updateBlogType(BlogType blogType);
    
    public List<BlogType> findBlogTypeComboList();


}
