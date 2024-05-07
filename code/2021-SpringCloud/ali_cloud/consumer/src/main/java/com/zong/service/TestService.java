package com.zong.service;

import com.zong.service.impl.TestServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider",fallbackFactory  = TestServiceImpl.class)
public interface TestService {

    @GetMapping("/feign")
    String hi(@RequestParam(value = "name",  required = false) String name);
}
