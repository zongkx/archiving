package com.demo.es.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.domain.ResultInfo;
import com.demo.es.entity.EsSoftware;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-03-21
 */
@Service
public interface IEsSoftwareService extends IService<EsSoftware> {
	
	/**
     * @Description: 强制更新所有的信息
     * @param
     */
	public boolean saveAll(List<EsSoftware> list);

	
	/**
     * @Description: 新增的用户信息
     * @param
     */
    public boolean insert(EsSoftware goods);
    /**
     * @Description: 通过用户输入的值去查询数据
     * @param searchContent 查询的值
     */
    public List<EsSoftware> search(String searchContent);
    /**
     * @Description: 通过用户输入的值去查询数据 分页查询
     * @param searchContent 查询的值
     */
    public ResultInfo searchPage(Integer pageNumber, Integer pageSize, String searchContent,String softwareId) 
    		throws JsonParseException, JsonMappingException, IOException ;

    /**
     * @Description: 通过用户输入的值去查询数据 根据权重去搜索
     * @param searchContent 查询的值
     */
     
    public void delete(EsSoftware entity) ;

}
