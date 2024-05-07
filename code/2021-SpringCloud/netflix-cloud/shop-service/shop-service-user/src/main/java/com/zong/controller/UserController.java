package com.zong.controller;

import com.alibaba.fastjson.JSON;
import com.zong.service.UserService;
import com.zong.user.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.BCrypt;
import utils.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin //跨域:
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        User user = new User();
        user.setName(BCrypt.gensalt());
        user.setPassword(BCrypt.hashpw("123456",BCrypt.gensalt() ));
        user.setEmail("111@qq.com");
        userService.add(user);
        return "ok";
    }


    @GetMapping("/login")
    public String login(String name, String password, HttpServletResponse response){
        User user = userService.findById(name);
        if(BCrypt.checkpw(password,user.getPassword())){
            //创建用户令牌信息
            Map<String, Object> map = new HashMap<>();
            map.put("role","USER");
            map.put("success","SUCCESS");
            map.put("name",name);
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(map),null);
            //存入cookie
            Cookie cookie = new Cookie("Auth", token);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            //令牌作为参数给用户
            return token;
        }
        return "error";

    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.findAll();
    }
}
