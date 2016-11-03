package com.blog.mapper;

import com.blog.entity.Bloger;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by ldb on 2016/9/22.
 */
@Mapper
public interface BlogerMapper{

    public Bloger getBloger();
    
    public Bloger login(Map<String, Object> map);
    
    public int updatePassword(Map<String, Object> map);
    
    public int updateBlogerInfo(Bloger bloger);
    
    public int updateWebClick();

    public Bloger getByUserName(String userName);
}
