package com.zong.service.impl;

import com.zong.dao.BrandMapper;
import com.zong.goods.pojo.Brand;
import com.zong.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper mapper;
    @Override
    public List<Brand> findAll() {
        return mapper.selectAll();
    }
}
