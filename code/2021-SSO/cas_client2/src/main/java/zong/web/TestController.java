package zong.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class TestController {
    @RequestMapping("/test1")
    public @ResponseBody String test1(){
        return "test1....";
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:http://localhost:8080/cas/logout?service=http://localhost:8088/logoutSuccess";

    }
    /**
     * 退出成功页
     * @return
     */
    @RequestMapping("/logoutSuccess")
    @ResponseBody
    public String logoutSuccess() {
        return "member logoutSuccess";
    }
}
