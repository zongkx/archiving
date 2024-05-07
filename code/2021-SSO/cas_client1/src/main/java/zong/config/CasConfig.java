package zong.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CasConfig {
//    private static final String CAS_SERVER_URL_LOGIN = "http://localhost:8080/cas/login";
//    private static final String SERVER_NAME = "http://localhost:8087/";

    @Value(value = "${cas.client-host-url}")
    private String server_name ;
    @Value(value = "${cas.server-login-url}")
    private String login_url ;


    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // AuthenticationFilter  该过滤器负责用户的认证工作
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", login_url);
        initParameters.put("serverName", server_name);
        // 忽略 /logoutSuccess 的路径
        initParameters.put("ignorePattern", "/logoutSuccess/*");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getLogin_url() {
        return login_url;
    }

    public void setLogin_url(String login_url) {
        this.login_url = login_url;
    }
}
