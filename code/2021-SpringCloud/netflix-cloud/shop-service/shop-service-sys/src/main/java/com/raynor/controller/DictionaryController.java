package com.raynor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.raynor.service.IDictionaryService;
import com.zong.sys.pojo.Dictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dic")
@Slf4j
public class DictionaryController {
    @Resource
    private IDictionaryService dictionaryService;

    @GetMapping("/")
    public List<Dictionary> dictionaryList(){
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("t_state","1");
        queryWrapper.orderByAsc("t_order");
        return dictionaryService.list(queryWrapper);
    }
    @PostMapping("/list")
    public List<Dictionary> dictionaryListAll(){
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("t_order");
        return dictionaryService.list(queryWrapper);
    }
    @GetMapping("/{id}")
    public Dictionary dictionaryOne(@PathVariable Long id){
        return dictionaryService.getById(id);
    }


    @PostMapping("/add")
    public boolean dictionaryAdd(@RequestBody Dictionary dictionary){
        return dictionaryService.save(dictionary);
    }
    @PostMapping("/delete/{id}")
    public boolean dictionaryDelete(@PathVariable Long id){
        return dictionaryService.removeById(id);
    }
    @PostMapping("/update")
    public boolean dictionaryUpdate(@RequestBody Dictionary dictionary){
        return dictionaryService.updateById(dictionary);
    }
}
