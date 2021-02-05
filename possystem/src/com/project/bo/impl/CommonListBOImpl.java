package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.ICommonListBO;
import com.project.dao.interfaces.ICommonListDAO;
import com.project.model.datamodel.AutonumModel;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.DepartmentModel;
import com.project.model.datamodel.DesignationModel;
import com.project.model.datamodel.FunctionModel;
import com.project.model.datamodel.ModuleModel;
import com.project.model.datamodel.RolefunctionlinkModel;
import com.project.model.datamodel.TaxcodeModel;
import com.project.model.datamodel.TaxmasterModel;
import com.project.model.his.Autonum;
import com.project.model.his.Branch;
import com.project.model.his.Department;
import com.project.model.his.Designation;
import com.project.model.his.Function;
import com.project.model.his.Hoteltable;
import com.project.model.his.Hoteltablearea;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Taxcode;
import com.project.model.his.Taxmaster;
import com.project.model.sale.sales.HoteltableModel;
import com.project.model.sale.sales.HoteltableareaModel;

@Service("commonListBO")
public class CommonListBOImpl implements ICommonListBO {

	static Logger logger = Logger.getLogger(CommonListBOImpl.class);

	

	@Resource(name = "commonListRepository")
	private ICommonListDAO commonListRepository;

	@Override
	public List<DepartmentModel> getDepartmentListByBranch(Integer branchId) throws Exception {

		List<Department> departmentList = null;
		List<DepartmentModel> departmentModelList = new ArrayList<DepartmentModel>();
		DepartmentModel departmentModel = null;
		try {
			departmentList = commonListRepository.getDepartmentListByBranch(branchId);
			for (Department departmentData : departmentList) {
				departmentModel = new DepartmentModel();
				//beanUtils.copyProperties(departmentModel, departmentData);				
				departmentModel.setDepartmentCode(departmentData.getDepartmentCode());
				departmentModel.setDepartmentName(departmentData.getDepartmentName());
				departmentModel.setDepartmentId(departmentData.getDepartmentId());
				departmentModel.setDescription(departmentData.getDescription());
				
				departmentModelList.add(departmentModel);
			}
		} catch (Exception e) {
			logger.info("Error in getDepartmentList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return departmentModelList;
	}

	@Override
	public List<DesignationModel> getDesignationList() throws Exception {
		List<Designation> designationList = null;
		List<DesignationModel> designationModelList = new ArrayList<DesignationModel>();
		DesignationModel designationModel = null;
		
		try {
			designationList = commonListRepository.getDesignationList();
			for (Designation designationData : designationList) {
				designationModel = new DesignationModel();
				//beanUtils.copyProperties(designationModel, designationData);
				
				designationModel.setDesignationname(designationData.getDesignationname());
				designationModel.setIddesignation(designationData.getIddesignation());
				
				designationModelList.add(designationModel);
			}
		} catch (Exception e) {
			logger.info("Error in getDesignationList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return designationModelList;
	}

	

	@Override
	public List<ModuleModel> getModuleList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FunctionModel> getFunctionList(Integer moduleId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FunctionModel> getFunctionList() throws Exception {
		List<Function> functionList = null;
		List<FunctionModel> functionModelList = new ArrayList<FunctionModel>();
		FunctionModel functionModel = null;
		try {
			functionList = commonListRepository.getFunctionList();
			for (Function functionData : functionList) {
				functionModel = new FunctionModel();
				//beanUtils.copyProperties(functionModel, functionData);
				
				functionModel.setFunctionname(functionData.getFunctionName());
				functionModel.setIdFunctions(functionData.getFunctionId());
				functionModel.setIdModule(functionData.getModule().getModuleId());
				functionModel.setModulename(functionData.getModule().getModuleName());
				
				functionModelList.add(functionModel);
			}
		} catch (Exception e) {
			logger.info("Error in getFunctionList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return functionModelList;
	}

	@Override
	public List<RolefunctionlinkModel> getRolefunctionlinkList(Integer roleId)
			throws Exception {
		List<Rolefunctionlink> rolefunctionlinkList = null;
		List<RolefunctionlinkModel> rolefunctionlinkModelList = new ArrayList<RolefunctionlinkModel>();
		RolefunctionlinkModel rolefunctionlinkModel = null;
		try {
			rolefunctionlinkList = commonListRepository
					.getRolefunctionlinkList(roleId);
			for (Rolefunctionlink rolefunctionlinkData : rolefunctionlinkList) {
				rolefunctionlinkModel = new RolefunctionlinkModel();
				//beanUtils.copyProperties(rolefunctionlinkModel,rolefunctionlinkData);
				
				rolefunctionlinkModel.setFunctionname(rolefunctionlinkData.getFunction().getFunctionName());
				rolefunctionlinkModel.setIdFunctions(rolefunctionlinkData.getFunction().getFunctionId());
				rolefunctionlinkModel.setIdrolefunctionlink(rolefunctionlinkData.getRoleFunctionLinkId());
				rolefunctionlinkModel.setIduserrole(rolefunctionlinkData.getRole().getRoleId());
				rolefunctionlinkModel.setRolename(rolefunctionlinkData.getRole().getRoleName());
				
				
				rolefunctionlinkModelList.add(rolefunctionlinkModel);
			}
		} catch (Exception e) {
			logger.info("Error in getRolefunctionlinkList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return rolefunctionlinkModelList;
	}

	@Transactional
	@Override
	public void saveDepartment(DepartmentModel depObj) throws Exception {
		Department dptModel = null;
		Branch branch = new  Branch();
		try {			
				dptModel = new Department();				
				dptModel.setDepartmentCode(depObj.getDepartmentCode());
				dptModel.setDepartmentName(depObj.getDepartmentName());		
				dptModel.setDescription(depObj.getDescription());
				branch.setBranchId(depObj.getBranchId());
				dptModel.setBranch(branch);
				commonListRepository.saveDepartment(dptModel);
		} catch (Exception e) {
			logger.info("Error in saveDepartment of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

	}

	@Transactional
	@Override
	public void updateDepartment(DepartmentModel depObj) throws Exception {
		
		Department dptModel = null;
		try {			
				dptModel = commonListRepository.getDepartment(depObj.getDepartmentId());		
				dptModel.setDepartmentCode(depObj.getDepartmentCode());
				dptModel.setDepartmentName(depObj.getDepartmentName());	
				dptModel.setDescription(depObj.getDescription());
				commonListRepository.updateDepartment(dptModel);
				
		} catch (Exception e) {
			logger.info("Error in updateDepartment of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
	}

	@Transactional
	@Override
	public void deleteDepartment(DepartmentModel depObj) throws Exception {
		
		Department dptModel = null;		
		try {			
			dptModel = commonListRepository.getDepartment(depObj.getDepartmentId());		
			commonListRepository.deleteDepartment(dptModel);
				
		} catch (Exception e) {
			logger.info("Error in deleteDepartment of CommonListBOImpl "
					+ e.toString());
			throw e;
		}		
		
	}

	@Override
	public DepartmentModel getDepartment(Integer departmentId) throws Exception {
		Department dptModel = null;
		DepartmentModel dptModelobj = new DepartmentModel();
		try {			
			dptModel = commonListRepository.getDepartment(departmentId);		
			dptModelobj.setDepartmentCode(dptModel.getDepartmentCode());
			dptModelobj.setDepartmentName(dptModel.getDepartmentName());	
			dptModelobj.setDepartmentId(dptModel.getDepartmentId());
			dptModelobj.setDescription(dptModel.getDescription());
				
		} catch (Exception e) {
			logger.info("Error in getDepartment of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return dptModelobj;
	}

	@Override
	public List<DepartmentModel> getDepartmentList(Integer departmentId,Integer branchId)
			throws Exception {
		List<Department> departmentList = null;
		List<DepartmentModel> departmentModelList = new ArrayList<DepartmentModel>();
		DepartmentModel departmentModel = null;
		try {
			departmentList = commonListRepository.getDepartmentList(departmentId,branchId);
			for (Department departmentData : departmentList) {
				departmentModel = new DepartmentModel();
				//beanUtils.copyProperties(departmentModel, departmentData);				
				departmentModel.setDepartmentCode(departmentData.getDepartmentCode());
				departmentModel.setDepartmentName(departmentData.getDepartmentName());
				departmentModel.setDepartmentId(departmentData.getDepartmentId());
				departmentModel.setDescription(departmentData.getDescription());
				departmentModelList.add(departmentModel);
			}
		} catch (Exception e) {
			logger.info("Error in getDepartmentList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return departmentModelList;
	}

	@Transactional
	@Override
	public void updateAutonum(AutonumModel autonum) throws Exception {
		Autonum autonumModel = null;
		try {			
			    autonumModel = commonListRepository.getAutonumDetail(autonum.getBranchRecordId());	
			    
			    Branch branch = new Branch();
			    branch.setBranchId(autonum.getBranchRecordId());
			    autonumModel.setBranch(branch);			    
			  
			    autonumModel.setDeliveryOrderFormat(autonum.getDeliveryOrderFormat());
			    autonumModel.setDespatchNoFormat(autonum.getDespatchNoFormat());
			    autonumModel.setInvoiceNoFormat(autonum.getInvoiceNoFormat());
			    autonumModel.setPrescriptionNoFormat(autonum.getPrescriptionNoFormat());
			    autonumModel.setProductCodeFormat(autonum.getProductCodeFormat());
			    autonumModel.setPurchaceOrderFormat(autonum.getPurchaceOrderFormat());
			    autonumModel.setPurchaseRequestFormat(autonum.getPurchaseRequestFormat());
			    autonumModel.setQuoteNoFormat(autonum.getQuoteNoFormat());
			    autonumModel.setSalesOrderFormat(autonum.getSalesOrderFormat());
			    //autonumModel.setT
			    
			    autonumModel.setCollectionFormat(autonum.getCollectionFormat());
			    autonumModel.setCollectionNo(autonum.getCollectionNo());
			    autonumModel.setDeliveryOrder(autonum.getDeliveryOrder());
			    autonumModel.setDespatchNo(autonum.getDespatchNo());
			    autonumModel.setInvoiceNo(autonum.getInvoiceNo());
			    autonumModel.setPaymentFormat(autonum.getPaymentFormat());
			    autonumModel.setPaymentNo(autonum.getPaymentNo());
			    autonumModel.setPrescriptionNo(autonum.getPrescriptionNo());
			    autonumModel.setPurchaceOrder(autonum.getPurchaceOrder());
			    autonumModel.setPurchaseRequest(autonum.getPurchaseRequest());
			    autonumModel.setQuoteNo(autonum.getQuoteNo());
			    autonumModel.setSalesOrder(autonum.getSalesOrder());
			  
			    
				commonListRepository.updateAutonumDetail(autonumModel);
				
		} catch (Exception e) {
			logger.info("Error in updateAutonum of CommonListBOImpl "	+ e.toString());
			throw e;
		}
		
	}
	
	
	@Transactional
	@Override
	public void createAutonum(AutonumModel autonum) throws Exception {
		Autonum autonumModel = null;
		try {			
			    autonumModel = new Autonum();
			    
			    Branch branch = new Branch();
			    branch.setBranchId(autonum.getBranchRecordId());
			    autonumModel.setBranch(branch);			    
			  
			    autonumModel.setDeliveryOrderFormat(autonum.getDeliveryOrderFormat());
			    autonumModel.setDespatchNoFormat(autonum.getDespatchNoFormat());
			    autonumModel.setInvoiceNoFormat(autonum.getInvoiceNoFormat());
			    autonumModel.setPrescriptionNoFormat(autonum.getPrescriptionNoFormat());
			    autonumModel.setProductCodeFormat(autonum.getProductCodeFormat());
			    autonumModel.setPurchaceOrderFormat(autonum.getPurchaceOrderFormat());
			    autonumModel.setPurchaseRequestFormat(autonum.getPurchaseRequestFormat());
			    autonumModel.setQuoteNoFormat(autonum.getQuoteNoFormat());
			    autonumModel.setSalesOrderFormat(autonum.getSalesOrderFormat());	
			    
			    autonumModel.setCollectionFormat(autonum.getCollectionFormat());
			    autonumModel.setCollectionNo(autonum.getCollectionNo());
			    autonumModel.setDeliveryOrder(autonum.getDeliveryOrder());
			    autonumModel.setDespatchNo(autonum.getDespatchNo());
			    autonumModel.setInvoiceNo(autonum.getInvoiceNo());
			    autonumModel.setPaymentFormat(autonum.getPaymentFormat());
			    autonumModel.setPaymentNo(autonum.getPaymentNo());
			    autonumModel.setPrescriptionNo(autonum.getPrescriptionNo());
			    autonumModel.setPurchaceOrder(autonum.getPurchaceOrder());
			    autonumModel.setPurchaseRequest(autonum.getPurchaseRequest());
			    autonumModel.setQuoteNo(autonum.getQuoteNo());
			    autonumModel.setSalesOrder(autonum.getSalesOrder());
			  
			    
				commonListRepository.createAutonum(autonumModel);
				
		} catch (Exception e) {
			logger.info("Error in updateAutonum of CommonListBOImpl "	+ e.toString());
			throw e;
		}
		
	}


	@Override
	public List<AutonumModel> getAutonumList(Integer branchRecordId) throws Exception {
		
		List<Autonum> autonumList = null;
		List<AutonumModel> autonumModelList = new ArrayList<AutonumModel>();
		AutonumModel autonumModel = null;
		try {
			autonumList = commonListRepository.getAutonumList(branchRecordId);
			for (Autonum autonum : autonumList) {
			
				autonumModel = new AutonumModel();
				
				autonumModel.setDeliveryOrderFormat(autonum.getDeliveryOrderFormat());
			    autonumModel.setDespatchNoFormat(autonum.getDespatchNoFormat());
			    autonumModel.setInvoiceNoFormat(autonum.getInvoiceNoFormat());
			    autonumModel.setPrescriptionNoFormat(autonum.getPrescriptionNoFormat());
			    autonumModel.setProductCodeFormat(autonum.getProductCodeFormat());
			    autonumModel.setPurchaceOrderFormat(autonum.getPurchaceOrderFormat());
			    autonumModel.setPurchaseRequestFormat(autonum.getPurchaseRequestFormat());
			    autonumModel.setQuoteNoFormat(autonum.getQuoteNoFormat());
			    autonumModel.setSalesOrderFormat(autonum.getSalesOrderFormat());
			    
			    
			    autonumModel.setCollectionFormat(autonum.getCollectionFormat());
			    autonumModel.setCollectionNo(autonum.getCollectionNo());
			    autonumModel.setDeliveryOrder(autonum.getDeliveryOrder());
			    autonumModel.setDespatchNo(autonum.getDespatchNo());
			    autonumModel.setInvoiceNo(autonum.getInvoiceNo());
			    autonumModel.setPaymentFormat(autonum.getPaymentFormat());
			    autonumModel.setPaymentNo(autonum.getPaymentNo());
			    autonumModel.setPrescriptionNo(autonum.getPrescriptionNo());
			    autonumModel.setPurchaceOrder(autonum.getPurchaceOrder());
			    autonumModel.setPurchaseRequest(autonum.getPurchaseRequest());
			    autonumModel.setQuoteNo(autonum.getQuoteNo());
			    autonumModel.setSalesOrder(autonum.getSalesOrder());
			    autonumModel.setTakeAwayNo(autonum.getTakeAwayNo());
			    
			    autonumModelList.add(autonumModel);
			}
		} catch (Exception e) {
			logger.info("Error in getAutonumList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return autonumModelList;
	}

	@Override
	public List<TaxcodeModel> getTaxcodeList(Integer branchId) throws Exception {
		List<Taxcode> taxcodeList = null;
		List<TaxcodeModel> taxcodeModelList = new ArrayList<TaxcodeModel>();
		TaxcodeModel taxcodeModel = null;
		try {
			taxcodeList = commonListRepository.getTaxcodeList(branchId);
			for (Taxcode taxData : taxcodeList) {
				taxcodeModel = new TaxcodeModel();
				taxcodeModel.setTaxCode(taxData.getTaxCode());
				taxcodeModel.setTaxcodeId(taxData.getTaxcodeId());				
				taxcodeModelList.add(taxcodeModel);
			}
		} catch (Exception e) {
			logger.info("Error in getTaxcodeList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return taxcodeModelList;
	}

	
	@Override
	public String getDatabasePath(String s) throws Exception {
		
		try {
			return commonListRepository.getDatabasePath(s);
			}
		 catch (Exception e) {
			logger.info("Error in getDatabasePath of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		
	}

	
	
	
	
	
	@Override
	public TaxmasterModel getTaxmasterDetail(Integer taxid) throws Exception {
		Taxmaster tax = null;	
		TaxmasterModel taxmasterdb = new TaxmasterModel();	
		try {
			tax = commonListRepository.getTaxmasterDetail(taxid);	
			
			BranchModel branch = new BranchModel();
			branch.setBranchId(tax.getBranch().getBranchId());			
			taxmasterdb.setBranch(branch);
			
			taxmasterdb.setGlcode(tax.getGlcode());
			taxmasterdb.setTaxname(tax.getTaxname());
			taxmasterdb.setTaxvalue(tax.getTaxvalue());			 	  
			taxmasterdb.setTaxid(tax.getTaxid());
			
		} catch (Exception e) {
			logger.info("Error in getTaxmasterDetail of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return taxmasterdb;
	}



	@Transactional
	@Override
	public void updateTaxmaster(TaxmasterModel tax) throws Exception {
		
		Taxmaster taxmasterdb = null;
		try {			
			taxmasterdb =  commonListRepository.getTaxmasterDetail(tax.getTaxid());							 	  
			taxmasterdb.setGlcode(tax.getGlcode());
			taxmasterdb.setTaxname(tax.getTaxname());
			taxmasterdb.setTaxvalue(tax.getTaxvalue());			 	  
			    
			commonListRepository.updateTaxmaster(taxmasterdb);
				
		} catch (Exception e) {
			logger.info("Error in updateTaxmaster of CommonListBOImpl "	+ e.toString());
			throw e;
		}
		
	}



	@Transactional
	@Override
	public void createTaxmaster(TaxmasterModel tax) throws Exception {
		Taxmaster taxmasterdb = null;
		try {			
			taxmasterdb =  new 	Taxmaster();
			Branch branch = new Branch();
			branch.setBranchId(tax.getBranch().getBranchId());			
			taxmasterdb.setBranch(branch);
							 	  
			taxmasterdb.setGlcode(tax.getGlcode());
			taxmasterdb.setTaxname(tax.getTaxname());
			taxmasterdb.setTaxvalue(tax.getTaxvalue());			 	  
			    
			commonListRepository.createTaxmaster(taxmasterdb);
				
		} catch (Exception e) {
			logger.info("Error in updateTaxmaster of CommonListBOImpl "	+ e.toString());
			throw e;
		}
	}




	@Override
	public List<TaxmasterModel> getTaxmasterList(Integer branchRecordId)
			throws Exception {
		List<Taxmaster> taxmasterList = null;
		List<TaxmasterModel> taxmasterdbList = new ArrayList<TaxmasterModel>();
		int id = 0;	
		try {
			taxmasterList = commonListRepository.getTaxmasterList(branchRecordId);
			for (Taxmaster tax : taxmasterList) {
			
				TaxmasterModel taxmasterdb = new TaxmasterModel();
				
				taxmasterdb.setGlcode(tax.getGlcode());
				taxmasterdb.setTaxname(tax.getTaxname());
				taxmasterdb.setTaxvalue(tax.getTaxvalue());		
				taxmasterdb.setTaxid(tax.getTaxid());
				taxmasterdb.setId(id + "");
				
				taxmasterdbList.add(taxmasterdb);
				id = id + 1;
			}
		} catch (Exception e) {
			logger.info("Error in getTaxmasterList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return taxmasterdbList;
	}

	@Transactional
	@Override
	public List<HoteltableModel> getHoteltableList(Integer branchId , String tableNo)
			throws Exception {
		List<Hoteltable> hotellistList = new ArrayList<Hoteltable>();
		List<HoteltableModel> tabledbList = new ArrayList<HoteltableModel>();
		int id = 0;	
		
		/*for(int i=1;i<=40;i++)
		{
			Hoteltable tab = new Hoteltable();
			
			Branch b = new Branch();
			b.setBranchId(branchId);			
			tab.setBranch(b);
			
			tab.setStatus("0");
			tab.setTableName(""+i);
			tab.setTableNo(""+i);
			
			
			commonListRepository.createHoteltable(tab);
			
			//.add(tab);
		}*/
		
		
		try {
			hotellistList = commonListRepository.getHoteltableList(branchId,tableNo);
			for (Hoteltable tab : hotellistList) {
			
				HoteltableModel hoteltabledb = new HoteltableModel();
				
				hoteltabledb.setStatus(tab.getStatus());
				hoteltabledb.setTableid(tab.getTableid());
				hoteltabledb.setTableName(tab.getTableName());
				hoteltabledb.setTableNo(tab.getTableNo());
				
				tabledbList.add(hoteltabledb);
				
			}
		} catch (Exception e) {
			logger.info("Error in getHoteltableList of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return tabledbList;
	}

	@Override
	public void createHoteltable(Hoteltable tab) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HoteltableareaModel> fetchHoteltablearea(String areaName, Integer branchId) throws Exception {
		
		List<HoteltableareaModel> objList = new ArrayList<HoteltableareaModel>();
		
		try {
			List<Hoteltablearea> dataList = commonListRepository.fetchHoteltablearea(areaName,branchId);
			for (Hoteltablearea data : dataList) {
			
				HoteltableareaModel obj = new HoteltableareaModel();
				obj.setAreaId(data.getAreaId());
				obj.setAreaName(data.getAreaName());
				
				objList.add(obj);
			}
			
		} catch (Exception e) {
			logger.info("Error in fetchHoteltablearea of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return objList;
	}

	@Transactional
	@Override
	public Boolean createHotelTableArea(HoteltableareaModel tableArea) throws Exception {
		Boolean createSuccess=false;
		try {
			
			Hoteltablearea obj=new Hoteltablearea();
			obj.setAreaName(tableArea.getAreaName());
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(tableArea.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			createSuccess=commonListRepository.createHotelTableArea(obj);
			
		} catch (Exception e) {
			logger.info("Error in createHotelTableArea of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return createSuccess;
	}

	@Override
	public List<HoteltableModel> fetchHotelTable(String tableName, String status, Integer areaName, Integer branchId) throws Exception {

		List<HoteltableModel> objList = new ArrayList<HoteltableModel>();
		List<String> tableNameList=new ArrayList<String>();
		try {
			if(tableName!=null && (!tableName.isEmpty())) {
				tableNameList.add(tableName);	
			}
			
			List<Hoteltable> dataList = commonListRepository.fetchHotelTable(tableNameList, status, areaName, branchId);
			for (Hoteltable data : dataList) {
			
				HoteltableModel obj=new HoteltableModel();
				obj.setTableid(data.getTableid());
				obj.setTableNo(data.getTableNo());
				obj.setTableName(data.getTableName());
				obj.setStatus(data.getStatus());
				obj.setTopPosition(data.getTopPosition());
				obj.setLeftPosition(data.getLeftPosition());
				obj.setTableShape(data.getTableShape());
				
				/*if(data.getHoteltablearea()!=null) {
					HoteltableareaModel tableAreaObj = new HoteltableareaModel();
					tableAreaObj.setAreaId(data.getHoteltablearea().getAreaId());
					tableAreaObj.setAreaName(data.getHoteltablearea().getAreaName());
					obj.setTableArea(tableAreaObj);
				}*/
				objList.add(obj);
			}
			
		} catch (Exception e) {
			logger.info("Error in fetchHotelTable of CommonListBOImpl "
					+ e.toString());
			throw e;
		}

		return objList;
	}

	@Override
	public boolean updateTableLayout(List<String>tableNameList,List<HoteltableModel> hotelTableObjList) throws Exception {
		
		boolean updateSuccess=false;
		try {
			List<Hoteltable> dataList=commonListRepository.fetchHotelTable(tableNameList, null,null,hotelTableObjList.get(0).getBranch().getBranchId());
			
			int index=0;
			Hoteltablearea tableAreaObj=new Hoteltablearea();
			tableAreaObj.setAreaId(hotelTableObjList.get(0).getTableArea().getAreaId());
			
			boolean removed=true;
			for(Hoteltable data:dataList) {
				removed=true;
				for(HoteltableModel newList:hotelTableObjList) {
					if(data.getTableName().equals(newList.getTableName())) {
						data.setLeftPosition(newList.getLeftPosition());
						data.setTopPosition(newList.getTopPosition());
						data.setTableShape(newList.getTableShape());
						data.setHoteltablearea(tableAreaObj);
						removed=false;
						break;
					}
				}
				if(removed) {
					data.setLeftPosition(null);
					data.setTopPosition(null);
					data.setTableShape(null);
					data.setHoteltablearea(null);
				}
				/*data.setLeftPosition(hotelTableObjList.get(index).getLeftPosition());
				data.setTopPosition(hotelTableObjList.get(index).getTopPosition());
				data.setTableShape(hotelTableObjList.get(index).getTableShape());
				data.setHoteltablearea(tableAreaObj);
				index++;
				*/
			}
			
			updateSuccess=commonListRepository.updateTableLayout(dataList);
			
		} catch (Exception e) {
			logger.info("Error in updateTableLayout of CommonListBOImpl "
					+ e.toString());
			throw e;
		}
		return updateSuccess;
	}



	

}
