package com.example.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 谢佳辉
 * @date 2021/1/23 4:16 下午
 */
@Configuration
public class ShiroConfig {
    //factoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         * anon:无需认证
         * authc:必须认证
         * user:必须拥有记住我才能有
         * perms:拥有某些资源才能访问
         * role:拥有某些角色才能访问
         */
        //认证
        Map<String,String>  filterMap = new LinkedHashMap<>();
        //filterMap.put("/evidence/*","authc");
        //filterMap.put("/cases/*","authc");
        //filterMap.put("/evidence/postevidence","perms[user:add]");
        //filterMap.put("/user/update","perms[user:update]");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login/ulogins");
        bean.setUnauthorizedUrl("/login/noauth");
        return bean;
    }
    //管理
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    //Realm
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
