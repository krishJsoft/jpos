package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IStaffBO;
import com.project.dao.interfaces.IStaffDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Department;
import com.project.model.his.Role;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

@Service("staffBO")
public class StaffBOImpl implements IStaffBO {
	
	public static Logger log = LoggerFactory.getLogger(BranchBOImpl.class);

	@Resource(name = "staffRepository")
	private IStaffDAO staffRepository;
	
	@Transactional
	@Override
	public boolean createNewStaff(BranchstaffmemberModel staff)
			throws Exception {
		boolean saveSuccess = false;
		Branchstaffmember staffObj = new Branchstaffmember();
		try {
			
			staffObj.setAllowAllBranches(staff.getAllowAllBranches());
			staffObj.setDesignation(staff.getDesignation());
			staffObj.setEmailAddress(staff.getEmailAddress());
			staffObj.setFirstName(staff.getFirstName());
			staffObj.setIdentificationNumber(staff.getIdentificationNumber());
			staffObj.setForceReset("1");
			staffObj.setInvalidAttempts(staff.getInvalidAttempts());
			staffObj.setLastName(staff.getLastName());
			staffObj.setPassword(staff.getPassword());
			staffObj.setStaffCode(staff.getStaffCode());
			
			staffObj.setStatus(staff.getStatus());		
			staffObj.setAddress(staff.getAddress());
			staffObj.setCity(staff.getCity());
			staffObj.setCountry(staff.getCountry());			
			staffObj.setPhoneNo(staff.getPhoneNo());
			staffObj.setPostCode(staff.getPostCode());
			staffObj.setState(staff.getState());
			staffObj.setCommission(String.valueOf(staff.isCommission()));
			staffObj.setBankName(staff.getBankName());
			staffObj.setBankbranchName(staff.getBankbranchName());
			staffObj.setAccountNo(staff.getAccountNo());
			
			
			
			Branch branch = new Branch();
			branch.setBranchId(staff.getBranchId());			
			staffObj.setBranch(branch);
			
			Role role = new Role();
			role.setRoleId(staff.getRoleId());
			staffObj.setRole(role);
			
			Department department = new Department();
			department.setDepartmentId(staff.getIdDepartment());
			staffObj.setDepartment(department);
			
			
			saveSuccess = staffRepository.createNewStaff(staffObj);			
		}

		catch (Exception e) {
			log.info("Error in createNewStaff StaffBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	
	}

	@Transactional
	@Override
	public boolean updateStaff(BranchstaffmemberModel staff) throws Exception {
		boolean updateSuccess = false;
		Branchstaffmember staffObj = staffRepository.getBranchstaffmemberDetails(staff.getStaffId());
		try {
			
			staffObj.setAllowAllBranches(staff.getAllowAllBranches());
			staffObj.setDesignation(staff.getDesignation());
			staffObj.setEmailAddress(staff.getEmailAddress());
			staffObj.setFirstName(staff.getFirstName());
			staffObj.setIdentificationNumber(staff.getIdentificationNumber());
			staffObj.setForceReset(staff.getForceReset());
			staffObj.setInvalidAttempts(staff.getInvalidAttempts());
			staffObj.setLastName(staff.getLastName());
			staffObj.setPassword(staff.getPassword());
			staffObj.setStatus(staff.getStatus());		
			staffObj.setCommission(String.valueOf(staff.isCommission()));
			staffObj.setAddress(staff.getAddress());
			staffObj.setCity(staff.getCity());
			staffObj.setCountry(staff.getCountry());			
			staffObj.setPhoneNo(staff.getPhoneNo());
			staffObj.setPostCode(staff.getPostCode());
			staffObj.setState(staff.getState());
			staffObj.setThemeName(staff.getThemeName());
			staffObj.setReminder(staff.getReminder());
			staffObj.setStaffCode(staff.getStaffCode());
			staffObj.setBankName(staff.getBankName());
			staffObj.setBankbranchName(staff.getBankbranchName());
			staffObj.setAccountNo(staff.getAccountNo());
			
			Branch branch = new Branch();
			branch.setBranchId(staff.getBranchId());			
			staffObj.setBranch(branch);
			
			Role role = new Role();
			role.setRoleId(staff.getRoleId());
			staffObj.setRole(role);
			
			Department department = new Department();
			department.setDepartmentId(staff.getIdDepartment());
			staffObj.setDepartment(department);			
			
			updateSuccess = staffRepository.createNewStaff(staffObj);			
		}

		catch (Exception e) {
			log.info("Error in updateStaff StaffBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}

	@Transactional
	@Override
	public boolean deleteStaff(BranchstaffmemberModel staff) throws Exception {
			boolean deleteSuccess = false;
		
		try {
			Branchstaffmember staffObj = staffRepository.getBranchstaffmemberDetails(staff.getStaffId());			
			deleteSuccess = staffRepository.deleteStaff(staffObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteStaff StaffBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}

	@Override
	public List<BranchstaffmemberModel> findByStaffList(Integer branchId,
			String icNo, Integer roleId, String Status) throws Exception {
		  List<BranchstaffmemberModel> staffObjList = new ArrayList<BranchstaffmemberModel>();	
		   List<Branchstaffmember> staffList = new ArrayList<Branchstaffmember>();
		try {			
			staffList = staffRepository.findByStaffList(branchId, icNo, roleId, Status);
			
			for (Branchstaffmember staff : staffList) {				
			BranchstaffmemberModel staffObj = new BranchstaffmemberModel();	
			staffObj.setAllowAllBranches(staff.getAllowAllBranches());
			staffObj.setDesignation(staff.getDesignation());
			staffObj.setEmailAddress(staff.getEmailAddress());
			staffObj.setEmailOldAddress(staff.getEmailAddress());
			staffObj.setFirstName(staff.getFirstName());
			staffObj.setIdentificationNumber(staff.getIdentificationNumber());
			staffObj.setForceReset(staff.getForceReset());
			staffObj.setInvalidAttempts(staff.getInvalidAttempts());
			staffObj.setLastName(staff.getLastName());
			staffObj.setPassword(staff.getPassword());
			staffObj.setStatus(staff.getStatus());				
			staffObj.setStaffId(staff.getStaffId());
			staffObj.setCommission(Boolean.parseBoolean(staff.getCommission()));
			staffObj.setRoleName(staff.getRole().getRoleName());
			staffObj.setRoleId(staff.getRole().getRoleId());
			staffObj.setBranchName(staff.getBranch().getBranchName());
			staffObj.setBranchId(staff.getBranch().getBranchId());
			staffObj.setDepartmentName(staff.getDepartment().getDepartmentName());
			staffObj.setIdDepartment(staff.getDepartment().getDepartmentId());
			staffObj.setStaffCode(staff.getStaffCode());
			staffObj.setBankName(staff.getBankName());
			staffObj.setBankbranchName(staff.getBankbranchName());
			staffObj.setAccountNo(staff.getAccountNo());
			staffObj.setPhotoName(staff.getIdentificationNumber() + ".jpg");			
			
			staffObj.setAddress(staff.getAddress());
			staffObj.setCity(staff.getCity());
			staffObj.setCountry(staff.getCountry());			
			staffObj.setPhoneNo(staff.getPhoneNo());
			staffObj.setPostCode(staff.getPostCode());
			staffObj.setState(staff.getState());
			staffObj.setThemeName(staff.getThemeName());
			staffObj.setReminder(staff.getReminder());
			
			staffObjList.add(staffObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByStaffList StaffBOImpl " + e);
			throw e;
		}
		return staffObjList;
	}

	@Override
	public BranchstaffmemberModel getBranchstaffmemberDetails(Integer staffId)
			throws Exception {
		  BranchstaffmemberModel staffObj = new BranchstaffmemberModel();	
		
		try {			
			Branchstaffmember staff = staffRepository.getBranchstaffmemberDetails(staffId);
			BranchModel branch = new BranchModel();
			staffObj.setAllowAllBranches(staff.getAllowAllBranches());
			staffObj.setDesignation(staff.getDesignation());
			staffObj.setEmailAddress(staff.getEmailAddress());
			staffObj.setEmailOldAddress(staff.getEmailAddress());
			staffObj.setFirstName(staff.getFirstName());
			staffObj.setIdentificationNumber(staff.getIdentificationNumber());			
			staffObj.setIdentificationOldNumber(staff.getIdentificationNumber());			
			staffObj.setForceReset(staff.getForceReset());
			staffObj.setInvalidAttempts(staff.getInvalidAttempts());
			staffObj.setLastName(staff.getLastName());
			staffObj.setPassword(staff.getPassword());
			staffObj.setStatus(staff.getStatus());
			staffObj.setStaffId(staff.getStaffId());
			staffObj.setCommission(Boolean.parseBoolean(staff.getCommission()));
			staffObj.setRoleName(staff.getRole().getRoleName());
			staffObj.setRoleId(staff.getRole().getRoleId());
			staffObj.setBranchName(staff.getBranch().getBranchName());
			staffObj.setBranchId(staff.getBranch().getBranchId());
			staffObj.setDepartmentName(staff.getDepartment().getDepartmentName());
			staffObj.setIdDepartment(staff.getDepartment().getDepartmentId());
			staffObj.setStaffCode(staff.getStaffCode());
			staffObj.setAddress(staff.getAddress());
			staffObj.setCity(staff.getCity());
			staffObj.setCountry(staff.getCountry());			
			staffObj.setPhoneNo(staff.getPhoneNo());
			staffObj.setPostCode(staff.getPostCode());
			staffObj.setState(staff.getState());
			staffObj.setThemeName(staff.getThemeName());
			staffObj.setReminder(staff.getReminder());
			staffObj.setBankName(staff.getBankName());
			staffObj.setBankbranchName(staff.getBankbranchName());
			staffObj.setAccountNo(staff.getAccountNo());
			
			branch.setBranchName(staff.getBranch().getBranchName());
			branch.setBranchId(staff.getBranch().getBranchId());
			branch.setAddress(staff.getBranch().getAddress());
			branch.setBranchCode(staff.getBranch().getBranchCode());
			branch.setCity(staff.getBranch().getCity());
			branch.setState(staff.getBranch().getState());
			branch.setPhoneNo(staff.getBranch().getPhoneNo());
			branch.setPostCode(staff.getBranch().getPostCode());
			branch.setEmailAddress(staff.getBranch().getEmailAddress());
			branch.setFaxNo(staff.getBranch().getFaxNo());			
			staffObj.setBranch(branch);
			
			staffObj.setPhotoName(staff.getIdentificationNumber() + ".jpg");	
		}
		catch (Exception e) {
			log.info("Error in getBranchstaffmemberDetails StaffBOImpl " + e);
			throw e;
		}
		return staffObj;
	}

	@Override
	public boolean findStaffIcExites(String IcNo) throws Exception {
		boolean exists = false;		
		try {
			exists = staffRepository.findStaffIcExites(IcNo);
		}
		catch (Exception e) {
			log.info("Error in findStaffIcExites StaffBOImpl " + e);
			throw e;
		}
		return exists;	
	}
	
	
	@Override
	public boolean findStaffEmailExites(String email) throws Exception {
		boolean exists = false;		
		try {
			exists = staffRepository.findStaffEmailExites(email);
		}
		catch (Exception e) {
			log.info("Error in findStaffEmailExites StaffBOImpl " + e);
			throw e;
		}
		return exists;	
	}

	@Override
	public List<BranchstaffmemberModel> findStaffMemberlistLogin(String username) throws Exception {
		 List<BranchstaffmemberModel> staffObjList = new ArrayList<BranchstaffmemberModel>();	
		   List<Branchstaffmember> staffList = new ArrayList<Branchstaffmember>();
		try {			
			staffList = staffRepository.findStaffMemberlistLogin(username);
			
			for (Branchstaffmember staff : staffList) {				
			BranchstaffmemberModel staffObj = new BranchstaffmemberModel();	
			staffObj.setAllowAllBranches(staff.getAllowAllBranches());
			staffObj.setDesignation(staff.getDesignation());
			staffObj.setEmailAddress(staff.getEmailAddress());
			staffObj.setFirstName(staff.getFirstName());
			staffObj.setIdentificationNumber(staff.getIdentificationNumber());
			staffObj.setForceReset(staff.getForceReset());
			staffObj.setInvalidAttempts(staff.getInvalidAttempts());
			staffObj.setLastName(staff.getLastName());
			staffObj.setPassword(staff.getPassword());
			staffObj.setStatus(staff.getStatus());				
			staffObj.setStaffId(staff.getStaffId());
			staffObj.setCommission(Boolean.parseBoolean(staff.getCommission()));
			staffObj.setRoleName(staff.getRole().getRoleName());
			staffObj.setRoleId(staff.getRole().getRoleId());
			staffObj.setBranchName(staff.getBranch().getBranchName());
			staffObj.setBranchId(staff.getBranch().getBranchId());
			staffObj.setBranchtype(staff.getBranch().getBranchtype());
			staffObj.setDepartmentName(staff.getDepartment().getDepartmentName());
			staffObj.setIdDepartment(staff.getDepartment().getDepartmentId());
			staffObj.setStaffCode(staff.getStaffCode());
			staffObj.setAddress(staff.getAddress());
			staffObj.setCity(staff.getCity());
			staffObj.setCountry(staff.getCountry());			
			staffObj.setPhoneNo(staff.getPhoneNo());
			staffObj.setPostCode(staff.getPostCode());
			staffObj.setState(staff.getState());
			staffObj.setThemeName(staff.getThemeName());
			staffObj.setReminder(staff.getReminder());
			staffObj.setBankName(staff.getBankName());
			staffObj.setBankbranchName(staff.getBankbranchName());
			staffObj.setAccountNo(staff.getAccountNo());
			staffObj.setPhotoName(staff.getIdentificationNumber() + ".jpg");	
			staffObjList.add(staffObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findStaffMemberlistLogin StaffBOImpl " + e);
			throw e;
		}
		return staffObjList;
	}

	
	@Override
	public BranchstaffmemberModel findStaffMemberLogin(String username) throws Exception {
		 BranchstaffmemberModel staffObj = new BranchstaffmemberModel();	
		   List<Branchstaffmember> staffList = new ArrayList<Branchstaffmember>();
		try {			
			staffList = staffRepository.findStaffMemberlistLogin(username);			
			if(staffList!=null && staffList.size()>0)
			{
			Branchstaffmember staff=staffList.get(0);						
			staffObj=convertBranchstaffmemberModel(staff);	
			}
		}
		catch (Exception e) {
			log.info("Error in findStaffMemberLogin StaffBOImpl " + e);
			throw e;
		}
		return staffObj;
	}

	
	
	public BranchstaffmemberModel convertBranchstaffmemberModel(Branchstaffmember staff)
	{
		BranchstaffmemberModel staffObj = new BranchstaffmemberModel();
		
		staffObj.setAllowAllBranches(staff.getAllowAllBranches());
		staffObj.setDesignation(staff.getDesignation());
		staffObj.setEmailAddress(staff.getEmailAddress());
		staffObj.setEmailOldAddress(staff.getEmailAddress());
		staffObj.setFirstName(staff.getFirstName());
		staffObj.setIdentificationNumber(staff.getIdentificationNumber());
		staffObj.setForceReset(staff.getForceReset());
		staffObj.setInvalidAttempts(staff.getInvalidAttempts());
		staffObj.setLastName(staff.getLastName());
		staffObj.setPassword(staff.getPassword());
		staffObj.setStatus(staff.getStatus());				
		staffObj.setStaffId(staff.getStaffId());
		staffObj.setCommission(Boolean.parseBoolean(staff.getCommission()));
		staffObj.setRoleName(staff.getRole().getRoleName());
		staffObj.setRoleId(staff.getRole().getRoleId());
		staffObj.setBranchName(staff.getBranch().getBranchName());
		staffObj.setBranchId(staff.getBranch().getBranchId());
		staffObj.setBranchtype(staff.getBranch().getBranchtype());
		staffObj.setDepartmentName(staff.getDepartment().getDepartmentName());
		staffObj.setIdDepartment(staff.getDepartment().getDepartmentId());
		staffObj.setStaffCode(staff.getStaffCode());
		staffObj.setAddress(staff.getAddress());
		staffObj.setCity(staff.getCity());
		staffObj.setCountry(staff.getCountry());			
		staffObj.setPhoneNo(staff.getPhoneNo());
		staffObj.setPostCode(staff.getPostCode());
		staffObj.setState(staff.getState());
		staffObj.setThemeName(staff.getThemeName());
		staffObj.setReminder(staff.getReminder());
		staffObj.setBankName(staff.getBankName());
		staffObj.setBankbranchName(staff.getBankbranchName());
		staffObj.setAccountNo(staff.getAccountNo());
		staffObj.setPhotoName(staff.getIdentificationNumber() + ".jpg");
		
		return staffObj;
	}
	
	
	public Branchstaffmember convertBranchstaffmember(BranchstaffmemberModel staff)
	{
		Branchstaffmember staffObj = new Branchstaffmember();
		
		staffObj.setAllowAllBranches(staff.getAllowAllBranches());
		staffObj.setDesignation(staff.getDesignation());
		staffObj.setEmailAddress(staff.getEmailAddress());
		staffObj.setFirstName(staff.getFirstName());
		staffObj.setIdentificationNumber(staff.getIdentificationNumber());
		staffObj.setForceReset(staff.getForceReset());
		staffObj.setInvalidAttempts(staff.getInvalidAttempts());
		staffObj.setLastName(staff.getLastName());
		staffObj.setPassword(staff.getPassword());
		staffObj.setStatus(staff.getStatus());		
		staffObj.setCommission(String.valueOf(staff.isCommission()));
		staffObj.setAddress(staff.getAddress());
		staffObj.setCity(staff.getCity());
		staffObj.setCountry(staff.getCountry());			
		staffObj.setPhoneNo(staff.getPhoneNo());
		staffObj.setPostCode(staff.getPostCode());
		staffObj.setState(staff.getState());		
		staffObj.setThemeName(staff.getThemeName());
		staffObj.setReminder(staff.getReminder());
		staffObj.setStaffCode(staff.getStaffCode());
		staffObj.setBankName(staff.getBankName());
		staffObj.setBankbranchName(staff.getBankbranchName());
		staffObj.setAccountNo(staff.getAccountNo());
		
		return staffObj;
	}

	@Override
	public boolean isRoleAssigned(Integer roleId, int branchId) throws Exception {
		boolean isRoleAssigned = false;
		try {
			isRoleAssigned=staffRepository.isRoleAssigned(roleId,branchId);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in isRoleAssigned of StaffBOImpl "+ ex);

		}
		return isRoleAssigned;
	}

	@Override
	public List<BranchstaffmemberModel> findStaffMemberlistLogin2(String username, String password, String status,
			Integer branchId) throws Exception {
		List<BranchstaffmemberModel> staffObjList = new ArrayList<BranchstaffmemberModel>();	
		   List<Branchstaffmember> staffList = new ArrayList<Branchstaffmember>();
		try {			
			staffList = staffRepository.findStaffMemberlistLogin2(username, password, status,branchId);
			
			for (Branchstaffmember staff : staffList) {				
				BranchstaffmemberModel staffObj = new BranchstaffmemberModel();	
				staffObj.setAllowAllBranches(staff.getAllowAllBranches());
				staffObj.setDesignation(staff.getDesignation());
				staffObj.setEmailAddress(staff.getEmailAddress());
				staffObj.setEmailOldAddress(staff.getEmailAddress());
				staffObj.setFirstName(staff.getFirstName());
				staffObj.setIdentificationNumber(staff.getIdentificationNumber());
				staffObj.setForceReset(staff.getForceReset());
				staffObj.setInvalidAttempts(staff.getInvalidAttempts());
				staffObj.setLastName(staff.getLastName());
				staffObj.setPassword(staff.getPassword());
				staffObj.setStatus(staff.getStatus());				
				staffObj.setStaffId(staff.getStaffId());
				staffObj.setCommission(Boolean.parseBoolean(staff.getCommission()));
				staffObj.setRoleName(staff.getRole().getRoleName());
				staffObj.setRoleId(staff.getRole().getRoleId());
				staffObj.setBranchName(staff.getBranch().getBranchName());
				staffObj.setBranchId(staff.getBranch().getBranchId());
				staffObj.setDepartmentName(staff.getDepartment().getDepartmentName());
				staffObj.setIdDepartment(staff.getDepartment().getDepartmentId());
				staffObj.setStaffCode(staff.getStaffCode());
				staffObj.setBankName(staff.getBankName());
				staffObj.setBankbranchName(staff.getBankbranchName());
				staffObj.setAccountNo(staff.getAccountNo());
				staffObj.setPhotoName(staff.getIdentificationNumber() + ".jpg");			
				
				staffObj.setAddress(staff.getAddress());
				staffObj.setCity(staff.getCity());
				staffObj.setCountry(staff.getCountry());			
				staffObj.setPhoneNo(staff.getPhoneNo());
				staffObj.setPostCode(staff.getPostCode());
				staffObj.setState(staff.getState());
				staffObj.setThemeName(staff.getThemeName());
				staffObj.setReminder(staff.getReminder());
				
				staffObjList.add(staffObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findStaffMemberlistLogin2 StaffBOImpl " + e);
			throw e;
		}
		return staffObjList;
	}
	
}
