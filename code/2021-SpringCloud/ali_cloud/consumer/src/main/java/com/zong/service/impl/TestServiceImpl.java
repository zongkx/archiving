package com.zong.service.impl;

import com.zong.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements FallbackFactory<TestService> {

    @Override
    public TestService create(Throwable throwable) {
        return new TestService() {
            @Override
            public String hi(String name) {
                return "Fall_Back";
            }
        };
    }
}
