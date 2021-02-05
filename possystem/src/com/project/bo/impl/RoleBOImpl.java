package com.project.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.RoleBO;
import com.project.dao.interfaces.IRoleDAO;
import com.project.model.datamodel.MasterFunctionModel;
import com.project.model.datamodel.ModuleMasterFunctionModel;
import com.project.model.datamodel.RoleModel;
import com.project.model.datamodel.RolePrivilegesDynamicModel;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.model.his.Branch;
import com.project.model.his.Function;
import com.project.model.his.Module;
import com.project.model.his.Role;

@Service("roleBO")
public class RoleBOImpl implements RoleBO {

	public static Logger log = LoggerFactory.getLogger(RoleBOImpl.class);

	@Resource(name = "RoleRepository")
	private IRoleDAO RoleRepository;

	@Transactional
	public void save(Role role) {
		RoleRepository.save(role);
		// TODO Auto-generated method stub
	}

	@Transactional
	public void save(RoleModel rm, String loginName) {
		if (rm == null) {
			log.error("Cannot Save Role , Input(RoleModel) is Null @ save()");
			return;
		}
		try {
			Role role = new Role();
			Branch branch = new Branch();
			role.setRoleName(rm.getRoleName());
			role.setRoleDescription(rm.getRoleDescription());
			role.setCreatedDate(new Date());
			role.setCreatedBy(loginName);
			role.setCreatedDate(new Date());
			branch.setBranchId(rm.getBranchId());
			role.setBranch(branch);
			RoleRepository.save(role);
		} catch (Exception e) {
			log.error("Exception @ save() : " + e.getMessage());
		}
	}

	@Transactional
	public void update(Role role) {
		RoleRepository.update(role);
		// TODO Auto-generated method stub
	}

	@Transactional
	public void update(RoleModel rm) {
		if (rm == null) {
			log.error("Cannot Update Role, Input(RoleModel) is Null @ update()");
			return;
		}
		try {
			Role role = RoleRepository.get(rm.getRoleId());
			if (role == null) {
				log.error("Cannot Update Role, RoleId :" + rm.getRoleId()
						+ " cannot be found in Roles Tables");
				return;
			}
			
			role.setRoleName(rm.getRoleName());
			role.setRoleDescription(rm.getRoleDescription());			
			role.setCreatedDate(new Date());
			
			
			RoleRepository.update(role);
		} catch (Exception e) {
			log.error("Exception @ update() : " + e.getMessage());
		}
	}

	@Transactional
	public void delete(Integer roleId) {
		if (roleId == null) {
			log.error("Cannot Deleted Role, Input(roleId) is Null @ delete()");
			return;
		}
		try {
			Role role = RoleRepository.get(roleId);
			if (role == null) {
				log.error("Cannot Delete Role, RoleId :" + roleId
						+ " cannot be found in Roles Tables");
				return;
			}
			RoleRepository.delete(role);
		} catch (Exception e) {
			log.error("Exception @ update() : " + e.getMessage());
		}
	}

	@Transactional
	public List<Role> findByRoleListAll(Integer branchId) {
		
		Integer roleId=0;
		return RoleRepository.findByRoleListAll(roleId,branchId);
	}

	@Transactional
	public List<RoleModel> getAllRoles(Integer roleId,Integer branchId) { // for findByRoleListAll()
		List<RoleModel> rolesList = null;
		try {
			List<Role> roles = RoleRepository.findByRoleListAll(roleId,branchId);
			if (roles == null) {
				log.error("Cannot get All Roles details, No records Available in Roles table ");
				return null;
			}
			rolesList = new ArrayList<RoleModel>();
			for (Role role : roles) {
				RoleModel rm = new RoleModel();
				rm.setRoleId(role.getRoleId());
				rm.setRoleName(role.getRoleName());
				rm.setRoleDescription(role.getRoleDescription());
				rolesList.add(rm);
			}

		} catch (Exception e) {
			log.error("Exception @ RoleBOImpl.getAllRoles() : "
					+ e.getMessage());
			// throw new Exception("Could not populate, e");
		}
		return rolesList;
	}

	@Transactional
	public List<Function> findbyRoleModuleListAll() {
		return RoleRepository.findbyRoleModuleListAll();
	}

	public List<RolePrivilegesModel> getAllPrivilagesInfo() {
		List<RolePrivilegesModel> privilagesList = null;
		try {
			List<Function> privilages = RoleRepository.findbyRoleModuleListAll();
			if (privilages == null) {
				log.error("Cannot get All Privialges details, No records Available in Function table");
				return null;
			}
			privilagesList = new ArrayList<RolePrivilegesModel>();
			for (Function priv : privilages) {
				RolePrivilegesModel p = new RolePrivilegesModel();
				p.setFunctionId(priv.getFunctionId());
				p.setFunctionName(priv.getFunctionName());
				p.setModuleName(priv.getModule().getModuleName());
				p.setMasterfunctionId(priv.getFunctionmaster().getMasterfunctionId());
				privilagesList.add(p);
			}

		} catch (Exception e) {
			log.error("Exception @ RoleBOImpl.getAllPrivilagesInfo() : "
					+ e.getMessage());
		}
		return privilagesList;
	}

	
	
	
	public List<ModuleMasterFunctionModel> getAllMasterPrivilagesInfo() {
		
		List<MasterFunctionModel> privilagesList =new ArrayList<MasterFunctionModel>();
		
		List<ModuleMasterFunctionModel> privilagesList1 =new ArrayList<ModuleMasterFunctionModel>();
		
		List<Integer> mastermoduleId = new ArrayList<Integer>();
		
		try {
			List<Function> privilages = RoleRepository
					.findbyRoleModuleListAll();
			if (privilages == null) {
				log.error("Cannot get All Privialges details, No records Available in Function table");
				return null;
			}			
			
			for(Module module :RoleRepository.findModuleListAll() )
			{							
				
				MasterFunctionModel masterFunctionModel = new MasterFunctionModel();				
				masterFunctionModel.setModuleName(module.getModuleName());
				masterFunctionModel.setModuleMasterId(module.getModulemaster().getMastermoduleId());
				masterFunctionModel.setModuleMasterName(module.getModulemaster().getMastermoduleName());
				
			for (Function priv : privilages) {		
				
				
				if(module.getModuleId()==priv.getModule().getModuleId())
				{				
				
				if(priv.getFunctionmaster().getMasterfunctionId()==1)
				{
					masterFunctionModel.setAddFunctionId(priv.getFunctionId());
				}
				
				if(priv.getFunctionmaster().getMasterfunctionId()==2)
				{
					masterFunctionModel.setEditFunctionId(priv.getFunctionId());
				}
				
				if(priv.getFunctionmaster().getMasterfunctionId()==3)
				{
					masterFunctionModel.setViewFunctionId(priv.getFunctionId());
				}
				
				if(priv.getFunctionmaster().getMasterfunctionId()==4)
				{
					masterFunctionModel.setDeleteFunctionId(priv.getFunctionId());
				}
				
				if(priv.getFunctionmaster().getMasterfunctionId()==5)
				{
					masterFunctionModel.setApproveFunctionId(priv.getFunctionId());
				}
				
				if(priv.getFunctionmaster().getMasterfunctionId()==6)
				{
					masterFunctionModel.setPrintFunctionId(priv.getFunctionId());
				}				
				}
				
				
			}
			
			privilagesList.add(masterFunctionModel);
			
			if(!mastermoduleId.contains(module.getModulemaster().getMastermoduleId()))	
			{				
				mastermoduleId.add(module.getModulemaster().getMastermoduleId());	
			}			
			
			}
			
			
			for(Integer id: mastermoduleId)
			{
				List<MasterFunctionModel> privilagesListTemp =new ArrayList<MasterFunctionModel>();				
				ModuleMasterFunctionModel functionData = new ModuleMasterFunctionModel();
				String masterName = "";
				for(MasterFunctionModel data:privilagesList)
				{
					if(data.getModuleMasterId()==id)
					{
						MasterFunctionModel data1=new MasterFunctionModel();
						data1.setModuleName(data.getModuleName());
						data1.setModuleMasterId(data.getModuleMasterId());
						data1.setModuleMasterName(data.getModuleMasterName());
						data1.setAddFunctionId(data.getAddFunctionId());
						data1.setEditFunctionId(data.getEditFunctionId());
						data1.setViewFunctionId(data.getViewFunctionId());
						data1.setDeleteFunctionId(data.getDeleteFunctionId());
						data1.setApproveFunctionId(data.getApproveFunctionId());
						data1.setPrintFunctionId(data.getPrintFunctionId());						
						masterName=data.getModuleMasterName();
						privilagesListTemp.add(data1);
					}
				}
				
				functionData.setModuleMasterId(id);
				functionData.setModuleMasterName(masterName);
				functionData.setFunctions(privilagesListTemp);				
				privilagesList1.add(functionData);
			}
			
			
			
		} catch (Exception e) {
			log.error("Exception @ RoleBOImpl.getAllPrivilagesInfo() : "
					+ e.getMessage());
		}
		return privilagesList1;
	}

	
	
	
	
	@Transactional
	public Role get(Integer id) {
		return RoleRepository.get(id);
	}

	@Transactional
	public RoleModel getRoleInfo(Integer roleId) {

		if (roleId == null || roleId == 0) {
			log.error("roleId is null @ getRoleInfo()");
			return null;
		}
		RoleModel rm = null;
		try {
			Role role = RoleRepository.get(roleId);
			if (role == null) {
				log.error("No record found for roleId : " + roleId
						+ ", @ getRoleInfo()");
				return null;
			}
			rm = new RoleModel();
			rm.setRoleDescription(role.getRoleDescription());
			rm.setRoleId(role.getRoleId());
			rm.setRoleName(role.getRoleName());

		} catch (Exception e) {
			log.error("Exception @ getRoleInfo() : " + e.getMessage());
		}
		return rm;
	}

	@Transactional
	public List<Role> findByRolesingleList(Integer id) {
		return RoleRepository.findByRolesingleList(id);
	}

	@Transactional
	public boolean findRoleNameExits(String roleName) {
		return RoleRepository.findRoleNameExits(roleName);
	}

	@Transactional
	public List<Role> searchList(Integer id) {
		return RoleRepository.searchList(id);
	}

}
