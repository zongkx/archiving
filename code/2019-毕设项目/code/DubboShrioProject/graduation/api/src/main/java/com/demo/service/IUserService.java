package com.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
import com.demo.entity.UserWithRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-08
 */
public interface IUserService extends IService<User> {
	public void updateLoginTime(String nickname);
	public void registUser(User user);
	public boolean checkNickName(String nickname) ;
	public List<User> selectUserPage(Integer offset,Integer limit);
	public Integer selectUserPageTotal();
	
//	public User selectRoleByUserId(Integer id);
	
	public List<UserWithRole> findUserWithRole(Integer userId);
	
	public User findByNickName(String nickname);
	
	
	public Boolean updateUser(User user);
	
//	public boolean SaveEs(User u);
}
