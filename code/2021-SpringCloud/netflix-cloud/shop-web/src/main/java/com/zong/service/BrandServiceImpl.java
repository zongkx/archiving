package com.zong.service;

import com.zong.goods.pojo.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Brand的熔断器类
 */
@Component
public class BrandServiceImpl implements BrandService{
    @Override
    public List<Brand> findAll() {
        System.out.println("熔断器类");
        return null;
    }
}
