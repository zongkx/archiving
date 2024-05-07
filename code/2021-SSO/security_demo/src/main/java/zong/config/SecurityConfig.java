package zong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zong.wrapper.AccessDeniedAuthenticationHandler;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)//三种注解,true代表开启

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    AccessDeniedAuthenticationHandler accessDeniedAuthenticationHandler;

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
        /*
            The most basic configuration defaults to automatically generating a login page at
            the URL "/login", redirecting to "/login?error" for authentication failure.
         */
        http.formLogin();
        //注销, 开启了注销功能, 跳到首页

        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/");
        //get 明文, a标签, 不安全      post 表单, 安全
        //SpringSecurity默认开启了防止csrf攻击的设置, 使用disable可以将其关闭
        http.csrf().disable();

        http.authorizeRequests().
                anyRequest()
                .authenticated()
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedAuthenticationHandler);

    }

    //认证
    //密码编码:  PassWordEncoding
    //在SpringSecurity 5.0+ 新增了很多的加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //此处的数据是从内存中读的, 而正常情况下应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("test").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role1")
                .and()
                .withUser("root").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role1", "role2", "role3")
                .and()
                .withUser("guest").password(
                        new BCryptPasswordEncoder().encode("123456")).roles("role2");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
