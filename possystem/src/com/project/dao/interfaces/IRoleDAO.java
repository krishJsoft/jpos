package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Function;
import com.project.model.his.Module;
import com.project.model.his.Role;

public interface IRoleDAO {

	void save(Role role);

	void update(Role role);

	void delete(Role role);

	List<Role> findByRoleListAll(Integer roleId,Integer branchId);

	List<Function> findbyRoleModuleListAll();

	List<Role> findByRolesingleList(Integer id);

	Role get(Integer id);

	boolean findRoleNameExits(String roleName);

	List<Role> searchList(Integer id);

	public List<Module> findModuleListAll();
}
