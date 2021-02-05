package com.project.bo.interfaces;

import java.util.List;





import com.project.model.datamodel.AutonumModel;
import com.project.model.datamodel.DepartmentModel;
import com.project.model.datamodel.DesignationModel;
import com.project.model.datamodel.FunctionModel;
import com.project.model.datamodel.ModuleModel;
import com.project.model.datamodel.RolefunctionlinkModel;
import com.project.model.datamodel.TaxcodeModel;
import com.project.model.datamodel.TaxmasterModel;
import com.project.model.his.Autonum;
import com.project.model.his.Department;
import com.project.model.his.Designation;
import com.project.model.his.Function;
import com.project.model.his.Hoteltable;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Taxcode;
import com.project.model.sale.sales.HoteltableModel;
import com.project.model.sale.sales.HoteltableareaModel;

public interface ICommonListBO {

	public List<DepartmentModel> getDepartmentList(Integer departmentId,Integer branchId)
			throws Exception;

	public List<DepartmentModel> getDepartmentListByBranch(Integer branchId) throws Exception;

	public List<DesignationModel> getDesignationList() throws Exception;

	public List<ModuleModel> getModuleList() throws Exception;

	public List<FunctionModel> getFunctionList(Integer moduleId)
			throws Exception;

	public List<FunctionModel> getFunctionList() throws Exception;

	public List<RolefunctionlinkModel> getRolefunctionlinkList(Integer roleId)
			throws Exception;

	public DepartmentModel getDepartment(Integer departmentId) throws Exception;

	public void saveDepartment(DepartmentModel depObj) throws Exception;

	public void updateDepartment(DepartmentModel depObj) throws Exception;

	public void deleteDepartment(DepartmentModel depObj) throws Exception;

	public void updateAutonum(AutonumModel autonum) throws Exception;
	
	public void createAutonum(AutonumModel autonum) throws Exception;

	public List<AutonumModel> getAutonumList(Integer branchRecordId) throws Exception;
	
	public List<TaxcodeModel> getTaxcodeList(Integer branchId) throws Exception;

	public String getDatabasePath(String s) throws Exception;
	
	public TaxmasterModel getTaxmasterDetail(Integer currency) throws Exception;		
	
	public void updateTaxmaster(TaxmasterModel currency) throws Exception;	
	
	public void createTaxmaster(TaxmasterModel currency) throws Exception;	
	
	public List<TaxmasterModel> getTaxmasterList(Integer branchRecordId) throws Exception;

	public List<HoteltableModel> getHoteltableList(Integer branchId ,  String tableNo)	throws Exception;
	
	public void createHoteltable(Hoteltable tab) throws Exception;
	
	public List<HoteltableareaModel> fetchHoteltablearea(String areaName,Integer branchId)	throws Exception;
	
	public Boolean createHotelTableArea(HoteltableareaModel tableArea) throws Exception;

	public List<HoteltableModel> fetchHotelTable(String tableName, String status, Integer areaId, Integer branchId) throws Exception;

	public boolean updateTableLayout(List<String> tableNameList, List<HoteltableModel> hotelTableObjList) throws Exception;
}
