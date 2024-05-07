package com.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.domain.ResultInfo;
import com.demo.entity.Software;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-03-21
 */
public interface ISoftwareService extends IService<Software> {
	public List<Software> getSoftwareByScoreI();
	public ResultInfo getSoftwarePageByType(String offset,String limit,String type);
	
	public ResultInfo getUserCollection(String offset,String limit,String nickname);

	
	public ResultInfo getSoftwarePageByTitle(String offset,String limit,String title);
	public ResultInfo getSoftwarePageByTitleAndAid(String offset,String limit,String title,String aid);

	public List<Object> getSfAlltype();
	
	
	
	
}
