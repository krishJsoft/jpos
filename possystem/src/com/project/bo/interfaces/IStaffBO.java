package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.his.Branchstaffmember;



public interface IStaffBO {

	
	public boolean createNewStaff(BranchstaffmemberModel staff) throws Exception;

	public boolean updateStaff(BranchstaffmemberModel staff) throws Exception;

	public boolean deleteStaff(BranchstaffmemberModel staff) throws Exception;
	
	public List<BranchstaffmemberModel> findByStaffList(Integer branchId,String icNo,Integer roleId,String Status) throws Exception;
		
	public BranchstaffmemberModel getBranchstaffmemberDetails(Integer staffId) throws Exception;
	
    public boolean findStaffIcExites(String IcNo) throws Exception;
	
    public boolean findStaffEmailExites(String email) throws Exception;
    
    public List<BranchstaffmemberModel> findStaffMemberlistLogin(String username) throws Exception;
    
    public BranchstaffmemberModel findStaffMemberLogin(String username) throws Exception;
    
    public List<BranchstaffmemberModel> findStaffMemberlistLogin2(String username,String password,String status,Integer branchId) throws Exception;

	public boolean isRoleAssigned(Integer roleId, int branchId)  throws Exception;

}
