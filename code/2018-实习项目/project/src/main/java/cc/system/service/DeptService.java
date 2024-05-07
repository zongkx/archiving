package cc.system.service;

import java.util.List;

import cc.common.domain.Tree;
import cc.common.service.IService;
import cc.system.domain.Dept;

public interface DeptService extends IService<Dept> {

	Tree<Dept> getDeptTree();

	List<Dept> findAllDepts(Dept dept);

	Dept findByName(String deptName);

	Dept findById(Long deptId);
	
	void addDept(Dept dept);
	
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
