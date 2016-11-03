package com.blog.mapper;

import com.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {


    public List<Blog> findRecommendBlogList() ;

    public List<Blog> findHotBlogList();

    public List<Blog> findTechnologyBlogList();

    public List<Blog> findBlogList(Map<String, Object> map);

    public List<Blog> findDateCountList();
    
    public Blog getBlogById(Integer id);
    
    public int updateBlogReadNum(Integer id);
    
    public int getBlogTotal(Map<String, Object> map);
    
    public int saveBlog(Blog blog);
    
    public int deleteBlog(Integer id);
    
    public int saveMore(Map<String, Object> map);
    
    public int updateBlog(Blog blog);
    
}
