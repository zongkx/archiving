package com.demo.mapper;

import com.demo.entity.Share;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-04-04
 */
@Mapper
public interface ShareMapper extends BaseMapper<Share> {
	public List<Share> getShareByCommentAid(String nickname);
	

}
