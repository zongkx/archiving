# 1.简介

***重要的类(SpringSecurity的核心)***

- **WebSecurityConfigureAdapter: 自定义Security策略**
- **AuthenticationManagerBuilder: 自定义认证策略**
- **@EnableWebSecurity: 开启WebSecurity模式**

两个核心目标是"认证"和"授权" (访问控制)

认证 ====> Authentication

授权 ====> Authorization

# 2.依赖

```xml
<!--security-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

# 3.配置类

> extends WebSecurityConfigurerAdapter

```java
package zong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问, 功能页只有对应有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests().antMatchers("/").permitAll()//允许所有
                .antMatchers("/level1/**").hasRole("role1")//含有level1的需要角色role1
                .antMatchers("/level2/**").hasRole("role2")//含有level2的需要角色role2
                .antMatchers("/level3/**").hasRole("role3");

        //没有权限, 默认回到登录页面(/login), 需要开启登录的页面
        http.formLogin();
        //自定义登录页面
	    // http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");
        //注销, 开启了注销功能, 跳到首页
        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/");
        //get 明文, a标签, 不安全      post 表单, 安全
        //SpringSecurity默认开启了防止csrf攻击的设置, 使用disable可以将其关闭
        http.csrf().disable();
        //开启记住我功能   cookie 默认保存两周   自定义rememberMe对应的前端的name属性
		http.rememberMe().rememberMeParameter("rememberMe");
    }

    //认证
    //密码编码:  PassWordEncoding
    //在SpringSecurity 5.0+ 新增了很多的加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //此处的数据正常情况下应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("test").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role2", "role3")
                .and()
                .withUser("root").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role1", "role2", "role3")
                .and()
                .withUser("guest").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role1");

    }
}

```

# 4.测试

访问

> http://localhost:8080/level1/1

登录test用户无法访问接口,登录root即可.

# 5.注解

## 5.1配置

Spring Security默认是禁用注解的，要想开启注解，要在继承`WebSecurityConfigurerAdapter`的类加`@EnableMethodSecurity`注解，并在该类中将`AuthenticationManager`定义为Bean。



```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)//三种注解,true代表开启
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

```

## 5.2 JSR-250注解

- @DenyAll
- @RolesAllowed > @RolesAllowed({"USER", "ADMIN"}) 
- @PermitAll

## 5.3securedEnabled注解

@Secured

在方法上指定安全性要求 角色/权限,@Secured对应的角色必须要有ROLE_前缀。

##  5.4prePostEnabled注解

- @PreAuthorize --适合进入方法之前验证授权
- @PostAuthorize --检查授权方法之后才被执行
- @PostFilter --在方法执行之后执行，而且这里可以调用方法的返回值，然后对返回值进行过滤或处理或修改并返回
- @PreFilter --在方法执行之前执行，而且这里可以调用方法的参数，然后对参数值进行过滤或处理或修改

##  5.5 异常处理

```java
@Component
@Slf4j
public class AccessDeniedAuthenticationHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    public AccessDeniedAuthenticationHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.info("没有权限");
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
    }

}

```

```java
        http.authorizeRequests().
                anyRequest()
                .authenticated()
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedAuthenticationHandler);
```

