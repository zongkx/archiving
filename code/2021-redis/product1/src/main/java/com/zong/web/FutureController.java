package com.zong.web;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class FutureController {

    @GetMapping("/future")
    public String test(HttpServletResponse response) throws ExecutionException, InterruptedException {

        AtomicReference<String> name = new AtomicReference<>("begin");
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000L);
                name.set("ssss");
                System.out.println("aa这个线程开始运行了"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sss";
        });
        return  name.get();
    }
}
