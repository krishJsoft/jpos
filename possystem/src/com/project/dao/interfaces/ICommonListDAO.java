package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.DepartmentModel;
import com.project.model.his.Autonum;
import com.project.model.his.Department;
import com.project.model.his.Designation;
import com.project.model.his.Function;
import com.project.model.his.Hoteltable;
import com.project.model.his.Hoteltablearea;
import com.project.model.his.Module;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Taxcode;
import com.project.model.his.Taxmaster;

public interface ICommonListDAO {
	
	public List<Department> getDepartmentListByBranch(Integer branchId) throws Exception;
	public List<Department> getDepartmentList(Integer departmentId,Integer branchId) throws Exception;
	
	public List<Designation> getDesignationList() throws Exception;
	
	public List<Module> getModuleList() throws Exception;
	public List<Function> getFunctionList(Integer moduleId) throws Exception;
	public List<Function> getFunctionList() throws Exception;
	public List<Rolefunctionlink> getRolefunctionlinkList(Integer roleId) throws Exception;
	public Autonum getAutonumDetail(Integer branchRecordId) throws Exception;
	public Autonum updateAutonumDetail(Autonum tutonum) throws Exception;
	public Department getDepartment(Integer departmentId)  throws Exception;
	
	public void saveDepartment(Department depObj)  throws Exception;
	
	public void updateDepartment(Department depObj)  throws Exception;
	
	public void deleteDepartment(Department depObj)  throws Exception;
	
	public void updateAutonum(Autonum autonum) throws Exception;	
	public void createAutonum(Autonum autonum) throws Exception;	
	
	public List<Autonum> getAutonumList(Integer branchRecordId) throws Exception;
	
	public void deleteAutonum(Autonum autonum) throws Exception;
	
	public List<Taxcode> getTaxcodeList(Integer branchId) throws Exception;
	
	public String getDatabasePath(String s) throws Exception;
	
	public Taxmaster getTaxmasterDetail(Integer currency) throws Exception;		
	
	public void updateTaxmaster(Taxmaster currency) throws Exception;	
	
	public void createTaxmaster(Taxmaster currency) throws Exception;	
	
	public List<Taxmaster> getTaxmasterList(Integer branchRecordId) throws Exception;
	
	public void deleteTaxmaster(Taxmaster currency) throws Exception;
	
	public List<Hoteltable> getHoteltableList(Integer branchId, String tableNo) throws Exception;
	
	public void createHoteltable(Hoteltable tab) throws Exception;

	public List<Hoteltablearea> fetchHoteltablearea(String areaName,Integer branchId) throws Exception;
	
	public Boolean createHotelTableArea(Hoteltablearea tableArea) throws Exception;
	
	public List<Hoteltable> fetchHotelTable(List<String> tableNameList, String status, Integer areaName, Integer branchId) throws Exception;
	
	public boolean updateTableLayout(List<Hoteltable> dataList) throws Exception;
}
