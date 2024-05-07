package com.demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	// 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
			@Bean
			public Realm realm() {
				return new CustomRealm();
			}
	 
	 
	
//	@Bean
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		// 配置 SecurityManager，并注入 shiroRealm
//		securityManager.setRealm(realm());
//		// 配置 rememberMeCookie
//		// securityManager.setRememberMeManager(rememberMeManager());
//		// 配置 缓存管理类 cacheManager
//		// securityManager.setCacheManager(cacheManager());
//		// securityManager.setSessionManager(sessionManager());
//		return securityManager;
//	}

			/**
			 * DefaultAdvisorAutoProxyCreator 和 AuthorizationAttributeSourceAdvisor 用于开启
			 * shiro 注解的使用 如 @RequiresAuthentication， @RequiresUser， @RequiresPermissions 等
			 *
			 * @return DefaultAdvisorAutoProxyCreator
			 */
			@Bean
			@DependsOn({ "lifecycleBeanPostProcessor" })
			public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
				DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
				advisorAutoProxyCreator.setProxyTargetClass(true);
				return advisorAutoProxyCreator;
			}

			@Bean
			public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
				AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
				authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
				return authorizationAttributeSourceAdvisor;
			}
	/**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean // bean 的加载顺序很重要,realm 应该放在前面
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/jsonTest", "anon");
        chain.addPathDefinition("/index", "anon");
        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "user");
        return chain;
    }
    @Bean(name = "lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		// shiro 生命周期处理器
		return new LifecycleBeanPostProcessor();
	}

	

	/**
	 * 用于开启 Thymeleaf 中的 shiro 标签的使用
	 *
	 * @return ShiroDialect shiro 方言对象
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
