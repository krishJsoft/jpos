package com.project.dao.interfaces;

import java.util.List;

import com.project.modal.report.ReturnvalueModal;
import com.project.model.his.Branch;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

public interface IBranchDAO {
	
	
	public ReturnvalueModal createNewBranch(Branch branch) throws Exception;

	public boolean updateBranch(Branch branch) throws Exception;

	public boolean deleteBranch(Branch branch) throws Exception;
	
	public List<Branch> findByBranchList(Integer branchId,String cityName,String stateName,String Status) throws Exception;
	
	public Branch getBranchDetailsByName(String branchName) throws Exception;
	
	public Branch getBranchDetails(Integer branchId) throws Exception;
	
    public boolean findBranchNameExites(String branchName) throws Exception;
	
	public boolean findBranchCodeExites(String branchCode) throws Exception;
	
	

}
