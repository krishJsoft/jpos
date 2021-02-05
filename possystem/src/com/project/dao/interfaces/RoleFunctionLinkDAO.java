package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Rolefunctionlink;

public interface RoleFunctionLinkDAO {

	void save(Rolefunctionlink rolefunctionLink);

	void update(Rolefunctionlink rolefunctionLink);

	void delete(Integer id);

	List<Rolefunctionlink> findbyRolefunctionListAll(Integer id);

	List<Rolefunctionlink> findfunctionbyRoleListAll(Integer roleId);
	
	public List<Rolefunctionlink> findfunctionbyRoleListBranch(Integer branchId);

}
