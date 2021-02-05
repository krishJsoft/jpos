package com.project.bo.interfaces;

import java.util.List;
import java.util.Map;



import com.project.bo.interfaces.RoleFunctionLinkBO;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.model.his.Rolefunctionlink;

public interface RoleFunctionLinkBO {

	void save(Rolefunctionlink rolefunctionLink);

	void update(Rolefunctionlink rolefunctionLink);

	void delete(Integer id);

	List<Rolefunctionlink> findbyRolefunctionListAll(Integer id);

	List<Rolefunctionlink> findfunctionbyRoleListAll(Integer roleId);

	List<RolePrivilegesModel> findPrivilagesByRole(Integer roleId);

	void save(int roleId, Map<Integer, Boolean> checkMap,
			List<Integer> oldPrivFunctionIds);

}
