package com.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.model.BaseDict;
/**
 * 数据字典
 */
@Mapper
public interface BaseDictDao {
	// 根据类别代码查询数据字典
    public List<BaseDict> selectBaseDictByTypeCode(String typecode);
}
