package zong.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {
    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }

    @GetMapping("/level1/1")
    public String level1(){
        return "level1!";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }


    @RolesAllowed({"role2"})
    @GetMapping("/test/1")
    public String test1(){
        return "test/1";
    }

    @Secured("role2")
    @GetMapping("/test/2")
    public String test2(){
        return "test/2";
    }
}
