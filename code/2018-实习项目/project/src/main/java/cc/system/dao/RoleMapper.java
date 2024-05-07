package cc.system.dao;

import java.util.List;

import cc.common.config.MyMapper;
import cc.system.domain.Role;
import cc.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}