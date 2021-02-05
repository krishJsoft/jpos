package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Branchstaffmember;

public interface IStaffDAO {

	
	public boolean createNewStaff(Branchstaffmember staff) throws Exception;

	public boolean updateStaff(Branchstaffmember staff) throws Exception;

	public boolean deleteStaff(Branchstaffmember staff) throws Exception;
	
	public List<Branchstaffmember> findByStaffList(Integer branchId,String icNo,Integer roleId,String Status) throws Exception;
		
	public Branchstaffmember getBranchstaffmemberDetails(Integer staffId) throws Exception;
	
    public boolean findStaffIcExites(String IcNo) throws Exception;
    
    public boolean findStaffEmailExites(String email) throws Exception;
	
    public List<Branchstaffmember> findStaffMemberlistLogin(String username);
    public List<Branchstaffmember> findByStaffList(Integer branchId,String identificationNumber, Integer roleId, String Status,String userType) throws Exception;

	public boolean isRoleAssigned(Integer roleId, int branchId) throws Exception;

	public List<Branchstaffmember> findStaffMemberlistLogin2(String username, String password, String status,Integer branchId) throws Exception;
}
