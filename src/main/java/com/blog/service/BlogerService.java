package com.blog.service;

import com.blog.entity.Bloger;

import java.util.Map;

/**
 * Created by ldb on 2016/9/22.
 */
public interface BlogerService {

    public Bloger getBloger();
    
    public Bloger login(Map<String, Object> map);
    
    public int updatePassword(Map<String, Object> map);
    
    public int updateBlogerInfo(Bloger bloger);
    
    public int updateWebClick();

    public Bloger getByUserName(String userName);
}
