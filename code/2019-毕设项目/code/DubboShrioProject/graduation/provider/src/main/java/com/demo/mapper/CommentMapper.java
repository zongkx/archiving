package com.demo.mapper;

import com.demo.entity.Comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-04-06
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
	Integer addComment(Comment comment);
	
	List<Map<Integer,Long>>getMostCommentsSoftwareId();
}
