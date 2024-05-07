package com.demo.mapper;

import com.demo.entity.Software;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-03-21
 */
@Mapper
public interface SoftwareMapper extends BaseMapper<Software> {
	
	
	public List<Software> getSoftwareByScore();
	public List<Software> getSoftwarePageByType(Integer offset, Integer limit, String type);
}
