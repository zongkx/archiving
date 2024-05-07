package com.zong.controller;

import com.zong.goods.pojo.Brand;
import com.zong.service.BrandService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/goods/brand")
@CrossOrigin //跨域:
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/all")
    public List<Brand> getAll(){
        return brandService.findAll();
    }
}
