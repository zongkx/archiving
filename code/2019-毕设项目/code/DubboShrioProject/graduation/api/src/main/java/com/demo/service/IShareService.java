package com.demo.service;

import com.demo.domain.ResultInfo;
import com.demo.entity.Share;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-04-04
 */
@Service

public interface IShareService extends IService<Share> {
	public ResultInfo getSharePageByUserId(String offset,String limit,String userId);
	public ResultInfo getSharePage(String offset,String limit);
	public List<Share> getShareByCommentAid(String nickname);
	public Integer addShareAndGetId(Share share);
}
