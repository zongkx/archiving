package com.demo.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.domain.ResultInfo;
import com.demo.entity.Comment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-04-06
 */
public interface ICommentService extends IService<Comment> {
	List<Comment> getComment(String aid,String type);

	

	Integer AddComment(Comment comment);
	
	void delComment(Integer aid);



	List<Comment> getAllComment(String type);
	
	ResultInfo qryCommentPage(String limit,String offset,String content);
	
	Integer getUnreadMessage(String  nickname);
	
	ResultInfo getAllMessage(String limit,String offset,String nickname );
	
	void readed(String id,String type);
	
	List<Map<Integer,Long>> getMostCommentsSoftwareId();//自定义SQL
	
	List<Comment> getMostCommentsSoftwareIds();
}
