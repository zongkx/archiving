package com.raynor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.raynor.service.IDictionaryDetailService;
import com.zong.sys.pojo.DictionaryDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dic/detail/")
@Slf4j
public class DictionaryDetailController {
    @Resource
    private IDictionaryDetailService  iDictionaryDetailService;

    @GetMapping("/query/{did}")
    public List<DictionaryDetail> dictionaryDetailList(@PathVariable Long did){
        QueryWrapper<DictionaryDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("t_did",did);
        return iDictionaryDetailService.list(queryWrapper);
    }
    @GetMapping("/{id}")
    public DictionaryDetail dictionaryDetailOne(@PathVariable Long id){
        return iDictionaryDetailService.getById(id);
    }

    @PostMapping("/update")
    public boolean dictionaryDetailUpdate(@RequestBody DictionaryDetail dictionaryDetail){
        return iDictionaryDetailService.updateById(dictionaryDetail);
    }
    @PostMapping("/add")
    public boolean dictionaryDetailAdd(@RequestBody DictionaryDetail dictionaryDetail){
        return iDictionaryDetailService.save(dictionaryDetail);
    }

    @PostMapping("/delete/{id}")
    public boolean dictionaryDetailDelete(@PathVariable Long id){
        return iDictionaryDetailService.removeById(id);
    }
}
