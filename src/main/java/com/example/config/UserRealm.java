package com.example.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.LoginMapper;
import com.example.model.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 谢佳辉
 * @date 2021/1/23 4:20 下午
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    LoginMapper loginMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Login currentlogin =(Login)subject.getPrincipal();
        System.out.println(currentlogin.getToken());
        info.addStringPermission(currentlogin.getToken());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken)authenticationToken;
        Login login  = loginMapper.selectOne(new QueryWrapper<Login>().eq("Staff_id",userToken.getUsername()));
        if(login==null){
            return null;
        }

        return new SimpleAuthenticationInfo(login,login.getPassword(),"");
    }
}
