
#### 1.下载安装Cas

由于6.0以上的版本需要JDK11,所以这里下载5.3(maven3.3/jdk8)

> https://github.com/apereo/cas-overlay-template/tree/5.3

1. 下载后导入到Idea中,右键-git-branchs 切换到5.3
2. 命令行输入
   
    > mvn clean package
3. 执行完毕之后在target目录下找到cas.war
4. 用Tomcat运行这个war,浏览器访问
   
   > http://localhost:8080/cas/login 


5. application.properties中修改账号密码,默认:casuser/Mellon

6. 由于CAS默认使用的是基于https协议,需要改为兼容使用http协议,并修改端口为8080

```properties
cas.authn.accept.users=root::123123
#兼容 Http 协议
cas.tgc.secure=false
# 开启识别Json文件，默认false
cas.serviceRegistry.initFromJson=true
# 允许退出登录后重定向到其他页面
cas.logout.followServiceRedirects=true
```

7. 默认的Https添加http
    cas\WEB-INF\classes\services目录下的HTTPSandIMAPS-10000001.json
   修改内容如下,即添加http

> "serviceId" : "^(https|http|imaps)://.*",


#### 2.客户端1:8088
1. 依赖
```pom
        <dependency>
            <!--第三方starter,可免去配置类,不过它没有配置登出,可手动注册登出bean-->
            <groupId>net.unicon.cas</groupId>
            <artifactId>cas-client-autoconfig-support</artifactId>
            <version>2.1.0-GA</version>
        </dependency>
```
2. 配置
```

server.port=8088
#cas服务端的地址
cas.server-url-prefix=http://localhost:8080/cas
#cas服务端的登录地址
cas.server-login-url=http://localhost:8080/cas/login
#当前服务器的地址(客户端)
cas.client-host-url=http://localhost:8088
#Ticket校验器使用Cas30ProxyReceivingTicketValidationFilter
cas.validation-type=cas3

```

```java
import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableCasClient//开启cas客户端服务
@SpringBootApplication
public class CasApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasApplication.class, args);
    }

    @Value(value = "${cas.client-host-url}")
    private String server_name ;
    @Value(value = "${cas.server-login-url}")
    private String login_url ;


    @Bean//登出用
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

}

```
3. 测试接口
```java
    @RequestMapping("/test1")
    public String test1(){
        return "test1....";
    }    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:http://localhost:8080/cas/logout?service=http://localhost:8088/logoutSuccess";
    }
        @RequestMapping("/logoutSuccess")
    @ResponseBody
    public String logoutSuccess() {
        return "member logoutSuccess";
    }
```
浏览器访问test1接口自动跳转到cas的登录页面,输入密码后返回结果,浏览器访问logout接口,返回结果logoutSuccess

#### 3.客户端2:8089

除了其端口不一样以外,其余均一致

启动两个客户端,访问登录任一接口,另外一个几口便不需要登录;

登出同理.

#### 4.cas配置JDBC

1. 在源码5.3的pom中添加以下依赖,并重新打war包

```xml
    <dependencies>
        <!-- Database Authentication Begin -->
        <dependency>
            <groupId>org.apereo.cas</groupId>
            <artifactId>cas-server-support-jdbc</artifactId>
            <version>${cas.version}</version>
        </dependency>
        <!--自适配数据库驱动，其中包括HSQLDB、Oracle、MYSQL(8)、PostgreSQL、MariaDB、Microsoft SQL Server-->
        <dependency>
            <groupId>org.apereo.cas</groupId>
            <artifactId>cas-server-support-jdbc-drivers</artifactId>
            <version>${cas.version}</version>
        </dependency>
        <!-- Database Authentication End -->
    </dependencies>	
    
    
```

2.  重新打包,并修改application文件

```properties
# 兼容 Http 协议
cas.tgc.secure=false
# 开启识别Json文件，默认false
cas.serviceRegistry.initFromJson=true
# 允许退出登录后重定向到其他页面
cas.logout.followServiceRedirects=true


##
# CAS Authentication Credentials
#
#cas.authn.accept.users=casuser::Mellon
#jdbc验证配置
#Query Database Authentication 数据库查询校验用户名开始
#查询账号密码sql，必须包含密码字段
cas.authn.jdbc.query[0].sql=select * from user where name=? 
#指定上面的sql查询字段名（必须）
cas.authn.jdbc.query[0].fieldPassword=password
#指定过期字段，1为过期，若过期需要修改密码
#cas.authn.jdbc.query[0].fieldExpired=expired
#为不可用字段段，1为不可用，
#cas.authn.jdbc.query[0].fieldDisabled=disabled
#数据库方言hibernate的知识
cas.authn.jdbc.query[0].dialect=org.hibernate.dialect.MySQLDialect
#数据库驱动
cas.authn.jdbc.query[0].driverClass=com.mysql.cj.jdbc.Driver
#数据库连接
cas.authn.jdbc.query[0].url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
#数据库用户名
cas.authn.jdbc.query[0].user=root
#数据库密码
cas.authn.jdbc.query[0].password=123456
#默认加密策略，通过encodingAlgorithm来指定算法，默认NONE不加密
#cas.authn.jdbc.query[0].passwordEncoder.type=com.sso.config.MyPasswordEncoder
cas.authn.jdbc.query[0].passwordEncoder.characterEncoding=UTF-8
#cas.authn.jdbc.query[0].passwordEncoder.encodingAlgorithm=MD5
#Query Database Authentication 数据库查询校验用户名结束
#jdbc验证配置
```

3. 启动客户端,即可.