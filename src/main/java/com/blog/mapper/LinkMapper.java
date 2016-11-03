package com.blog.mapper;

import com.blog.entity.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
@Mapper
public interface LinkMapper {

    public List<Link> findLinkList();
    
    public int saveLink(Link link);
    
    public int updateLink(Link link);
    
    public int deleteLink(Integer id);
}
