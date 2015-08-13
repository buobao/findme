package com.config;

import com.shiro.ShiroRealmImpl;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dqf on 2015/8/13.
 */
@Configuration
public class SecurityConfig {
    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean(name="ShiroRealmImpl")
    public ShiroRealmImpl getShiroRealm(){
        return new ShiroRealmImpl();
    }

    @Bean(name="shiroEhcacheManager")
    public EhCacheManager getEhCacheManager(){
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(getShiroRealm());
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        filterChainDefinitionMap.put("/com/**", "authc");
        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/public/**", "anon");
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}


/*
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(securityManager());
        factory.setLoginUrl("/ui/login");
        factory.setSuccessUrl("/ui/listView");
        factory.setUnauthorizedUrl("/ui/login");
        factory.setFilterChainDefinitions(
                        "/assets/scripts/**=anon\n"+
                        "/license/**=anon\n"+
                        "/manage/health/=anon\n"+
                        "/assets/static/*=authc\n"+
                        "/manage/metrics/**=authc\n"+
                        "/manage/beans/**=authc\n"+
                        "/manage/trace/**=authc\n"+
                        "/manage/mappings/**=authc\n"+
                        "/manage/dump/**=authc\n"+
                        "/manage/autoconfig/**=authc\n"+
                        "/manage/env/**=authc\n"+
                        "/manage/info/**=authc"
        );

        return factory;
    }

    @Bean
    public DefaultSecurityManager securityManager(){
        DefaultSecurityManager rc = new DefaultSecurityManager();
        rc.setRealm(realm());
        return rc;
    }

    @Bean
    public AuthorizingRealm realm(){
        AuthorizingRealm realm = new AuthorizingRealm() {
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthenticationException {
                return (AuthorizationInfo) new SimpleAuthenticationInfo("user", "password", "login");
            }

            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                return null;
            }
        };
        realm.setName("login");
        return realm;
    }






 */
