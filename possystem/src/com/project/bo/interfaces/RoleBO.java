package com.project.bo.interfaces;

import java.util.List;


import com.project.model.datamodel.MasterFunctionModel;
import com.project.model.datamodel.ModuleMasterFunctionModel;
import com.project.model.datamodel.RoleModel;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.model.his.Function;
import com.project.model.his.Role;

public interface RoleBO {

	void save(Role role);

	void update(Role role);

	boolean findRoleNameExits(String roleName);

	List<Role> findByRoleListAll(Integer branchId);

	List<Function> findbyRoleModuleListAll();

	Role get(Integer id);

	List<Role> findByRolesingleList(Integer id);

	List<Role> searchList(Integer id);

	void save(RoleModel rm, String loginName); // for save(Role role)

	void update(RoleModel rm); // for update(Role role)

	void delete(Integer id);

	List<RoleModel> getAllRoles(Integer roleId,Integer branchId); // for findByRoleListAll()

	List<RolePrivilegesModel> getAllPrivilagesInfo(); // for
														// findbyRoleModuleListAll()
	
	List<ModuleMasterFunctionModel> getAllMasterPrivilagesInfo(); // for
	// findbyRoleModuleListAll()
	

	RoleModel getRoleInfo(Integer roleId); // for get(Integer id)

}
