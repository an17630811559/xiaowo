package com.awb.config.shiro;

import com.awb.config.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 14:56 2020/4/17
 */
@Configuration
public class ShiroConfig {


    /**
     * 在spring 工厂中创建自定义的Realm对象
     *
     * @return
     */
    @Bean
    public Realm getRealm() {
        UserRealm myRealm = new UserRealm();
        return myRealm;
    }

    /**
     * 在spring 工厂中创建SecurityManager对象
     *
     * @return
     */
    @Bean
    public SecurityManager getManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置安全管理器的域对象
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * Shiro过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 权限认证失败，则跳转到指定页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/index");
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/dist/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/sass/**", "anon");

        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/toIndex", "anon");

        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}
