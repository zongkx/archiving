package com.demo.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	
	
	/**
	 * Shiro的Web过滤器Factory 命名:shiroFilter
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置 securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 登录的 url
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后跳转的 url
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权 url
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 设置免认证 url
//		 String[] anonUrls =
//		 StringUtils.splitByWholeSeparatorPreserveAllTokens(urls,",");
//		 for (String url : anonUrls) {
//			 filterChainDefinitionMap.put(url, "anon");
//		 }
		filterChainDefinitionMap.put("/jsonTest", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/photos/**", "anon");
		filterChainDefinitionMap.put("/registUser", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/guest/**", "anon");
		filterChainDefinitionMap.put("/software/**", "anon");

		// }
		// 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
		//filterChainDefinitionMap.put("/logout", "logout");
		// 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
		filterChainDefinitionMap.put("/**", "user");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	// 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
	@Bean
	public CustomRealm realm() {
		return new CustomRealm();
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 配置 SecurityManager，并注入 shiroRealm
		securityManager.setRealm(realm());
		// 配置 rememberMeCookie
		securityManager.setRememberMeManager(rememberMeManager());
		// 配置 缓存管理类 cacheManager
		securityManager.setCacheManager(cacheManager());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		// shiro 生命周期处理器
		return new LifecycleBeanPostProcessor();
	}

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
	 * 用于开启 Thymeleaf 中的 shiro 标签的使用
	 *
	 * @return ShiroDialect shiro 方言对象
	 */
//	@Bean
//	public ShiroDialect shiroDialect() {
//		return new ShiroDialect();
//	}

	/**
	 * shiro 中配置 redis 缓存
	 *
	 * @return RedisManager
	 */
	private RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		return redisManager;
	}

	/**
	 * shiro 中配置 redis cache缓存
	 *
	 * @return RedisCacheManager
	 */
	private RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * rememberMe cookie 效果是重开浏览器后无需重新登录
	 *
	 * @return SimpleCookie
	 */
	private SimpleCookie rememberMeCookie() {
		// 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		// cookie.setSecure(true); // 只在 https中有效 注释掉 正常
		// 设置 cookie 的过期时间，单位为秒，这里为一天
		cookie.setMaxAge(2000);
		return cookie;
	}

	/**
	 * cookie管理对象
	 *
	 * @return CookieRememberMeManager
	 */
	private CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie 加密的密钥
		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * session 管理对象
	 *
	 * @return DefaultWebSessionManager
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		Collection<SessionListener> listeners = new ArrayList<>();
		listeners.add(new ShiroSessionListener());
		// 设置session超时时间，单位为毫秒
		sessionManager.setGlobalSessionTimeout(2000000);
		sessionManager.setSessionListeners(listeners);
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
}
