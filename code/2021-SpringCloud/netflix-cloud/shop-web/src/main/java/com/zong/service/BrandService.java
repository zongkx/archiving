package com.zong.service;

import com.zong.goods.pojo.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value="GOODS",fallback = BrandServiceImpl.class)
@Service
public interface BrandService {

    @GetMapping("/goods/brand/all")
    List<Brand> findAll();


}
