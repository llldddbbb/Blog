package com.blog.realm;

import com.blog.entity.Bloger;
import com.blog.service.BlogerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by ldb on 2016/11/2.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private BlogerService blogerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=(String)token.getPrincipal();
        Bloger bloger=blogerService.getByUserName(userName);
        if(bloger!=null){
            SecurityUtils.getSubject().getSession().setAttribute("currentBloger", bloger);
            AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(bloger.getUserName(),bloger.getPassword(),"xx");
            return authcInfo;
        }else{
            return null;
        }
    }
}
