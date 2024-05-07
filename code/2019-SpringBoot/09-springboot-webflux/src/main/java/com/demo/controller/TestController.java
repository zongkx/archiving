package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UserBean;
import com.demo.service.TestService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
* @author botter
*/
@RestController
public class TestController {

   @Autowired
   private TestService testService;

   @RequestMapping(value = "test", method = RequestMethod.GET)
   public Mono<String> testMono() {
       return testService.test();
   }

   /**
    * do userlist data
    * @param users
    * @return lastest add success  data
    */
   @RequestMapping(value = "addUser", method = RequestMethod.POST)
   public Flux<UserBean> add(@RequestBody Flux<UserBean> users) {
       return testService.createOrUpdate(users);
   }

   /**
    * get one data
    * @param id
    * @return
    */
   @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET, produces = {"application/json"})
   public Mono<UserBean> getUser(@PathVariable int id) {
       return testService.getUserBeanById(id);
   }

   @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.POST)
   public Mono<UserBean> delete(@PathVariable int id) {
       return testService.deleteById(id);
   }
}

