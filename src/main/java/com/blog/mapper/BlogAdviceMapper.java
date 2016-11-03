package com.blog.mapper;

import com.blog.entity.BlogAdvice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ldb on 2016/9/22.
 */
@Mapper
public interface BlogAdviceMapper {


    public List<BlogAdvice> findBlogAdviceList(Map<String, Object> map) ;
    
    public int saveBlogAdvice(BlogAdvice blogAdvice);
    
    public int updateBlogAdviceReply(BlogAdvice blogAdvice);
    
    public int deleteBlogAdvice(Integer id);
    
    public int getBlogAdviceTotal();
    

}
