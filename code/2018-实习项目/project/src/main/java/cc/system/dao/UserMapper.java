package cc.system.dao;

import java.util.List;

import cc.common.config.MyMapper;
import cc.system.domain.User;
import cc.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}