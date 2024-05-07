package com.zong.web;


import com.zong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String get() {
        return restTemplate.getForObject("http://provider/test", String.class);
    }

    @Autowired
    private TestService testService;

    @GetMapping("/feign")
    public String hiFeign(String name){
        return testService.hi(name);
    }



}
