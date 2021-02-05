package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.dao.interfaces.ISystemconfigurationDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.KitchenprintercategorylinkModal;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.SystemTypeModel;
import com.project.model.datamodel.SystemconfigurationModel;
import com.project.model.his.Branch;
import com.project.model.his.Kitchenprintercategorylink;
import com.project.model.his.Kitchenprinterlist;
import com.project.model.his.Systemconfiguration;
import com.project.model.his.Systemtype;

@Service("systemconfigurationBO")
public class SystemconfigurationBOImpl implements ISystemconfigurationBO {
	
	public static Logger log = LoggerFactory.getLogger(SupplierBOImpl.class);
	
	@Resource(name = "systemconfigurationRepository")
	private ISystemconfigurationDAO systemconfigurationRepository;
	
	
	@Override
	public SystemconfigurationModel getConfiguration(int branchId) throws Exception {
		SystemconfigurationModel configModel=new SystemconfigurationModel();
		try {
			
			Systemconfiguration data=systemconfigurationRepository.getConfiguration(branchId);
			
			configModel.setSystemConfigId(data.getId());
			
			SystemTypeModel systemType=new SystemTypeModel();
			systemType.setTypeId(data.getSystemtype().getId());
			systemType.setKitchenOrderType(data.getSystemtype().getKitchenOrderType());
			systemType.setAdvanceBillOption(data.getSystemtype().getAdvanceBillOption());
			systemType.setMergeBillOption(data.getSystemtype().getMergeBillOption());
			systemType.setSplitBillOption(data.getSystemtype().getSplitBillOption());
			systemType.setHasTableNo(data.getSystemtype().getHasTableNo());
			systemType.setHasCardNo(data.getSystemtype().getHasCardNo());
			systemType.setHasPaxNo(data.getSystemtype().getHasPaxNo());
			configModel.setSystemType(systemType);
			
			configModel.setKitchenOrder(data.getKitchenOrder());
			configModel.setMobileKitchenOrder(data.getMobileKitchenOrder());
			configModel.setTerminalKitchenOrder(data.getTerminalKitchenOrder());
			configModel.setCashierKitchenOrder(data.getCashierKitchenOrder());
			configModel.setBillPrinter(data.getBillPrinter());
			configModel.setPaxNo(data.getPaxNo());
			configModel.setOrderScreenProductSearchBy(data.getOrderScreenProductSearchBy());
			configModel.setPrintMode(data.getPrintMode());
			
			BranchModel branch=new BranchModel();
			branch.setBranchId(data.getBranch().getBranchId());
			configModel.setBranch(branch);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getConfiguration SystemconfigurationBOImpl " +ex);
			throw ex;
		}
		
		return configModel;
	}


	@Override
	public List<KitchenprinterlistModal> getKitchenPrinterList(String kitchenName, int branchId)
			throws Exception {
		List<KitchenprinterlistModal> objList=new ArrayList<KitchenprinterlistModal>();
		try {
			List<Kitchenprinterlist> dataList=systemconfigurationRepository.getKitchenPrinterList(kitchenName,branchId);
			for(Kitchenprinterlist data:dataList) {
				KitchenprinterlistModal obj=new KitchenprinterlistModal();
				obj.setId(data.getId());
				obj.setKitchenName(data.getKitchenName());
				obj.setPrinterName(data.getPrinterName());
				
				BranchModel branch=new BranchModel();
				branch.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branch);
				
				objList.add(obj);
			}
			
		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterList SystemconfigurationBOImpl " +ex);
			throw ex;
		}
		return objList;
		
	}


	@Override
	public Boolean createNewKitchenPrinter(KitchenprinterlistModal kitchenPrinter) throws Exception {
		Boolean saveSuccess=false;
		try {
			Kitchenprinterlist printer=new Kitchenprinterlist();
			printer.setPrinterName(kitchenPrinter.getPrinterName());
			printer.setKitchenName(kitchenPrinter.getKitchenName());
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(kitchenPrinter.getBranch().getBranchId());
			printer.setBranch(branchObj);
			
			saveSuccess=systemconfigurationRepository.createNewKitchenPrinter(printer);
			
		}catch(Exception ex) {
			log.info("Error in createNewKitchenPrinter SystemconfigurationBOImpl "+ex);
			throw ex;
		}
		return saveSuccess;
	}


	@Override
	public List<KitchenprintercategorylinkModal> getKitchenPrinterCategoryLinkList(Integer printerId,
			Integer categoryId, int branchId) throws Exception {
		List<KitchenprintercategorylinkModal> objList=new ArrayList<KitchenprintercategorylinkModal>();
		try {
			List<Kitchenprintercategorylink> printerlinks=systemconfigurationRepository.getKitchenPrinterCategoryLinkList(printerId,categoryId,branchId);
			for(Kitchenprintercategorylink data:printerlinks) {
				KitchenprintercategorylinkModal obj=new KitchenprintercategorylinkModal();
				obj.setId(data.getId());
				obj.setCategoryId(data.getCategoryId());
				
				
				KitchenprinterlistModal printerObject=new KitchenprinterlistModal();
				printerObject.setId(data.getPrinter().getId());
				obj.setPrinter(printerObject);
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				objList.add(obj);
			}
		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterCategoryLinkList SystemconfigurationBOImpl "+ex);
			throw ex;
		}
		return objList;
	}


	@Override
	public KitchenprinterlistModal getKitchenPrinterDetails(Integer printerId) throws Exception {
		KitchenprinterlistModal obj=new KitchenprinterlistModal();
		try {
			Kitchenprinterlist data=systemconfigurationRepository.getKitchenPrinterDetails(printerId);
			obj.setId(data.getId());
			obj.setKitchenName(data.getKitchenName());
			obj.setPrinterName(data.getPrinterName());
			
			BranchModel branch=new BranchModel();
			branch.setBranchId(data.getBranch().getBranchId());
			obj.setBranch(branch);

		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterDetails SystemconfigurationBOImpl " +ex);
			throw ex;
		}
		return obj;
	}


	@Override
	public List<SystemTypeModel> fetchSystemTypeList() throws Exception {
		List<SystemTypeModel> objList=new ArrayList<SystemTypeModel>();
		

		try {
			
			for(Systemtype data:systemconfigurationRepository.fetchSystemTypeList()) {
				SystemTypeModel obj=new SystemTypeModel();
				obj.setTypeId(data.getId());
				obj.setName(data.getName());
				objList.add(obj);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchSystemTypeList SystemTypeBOImpl " +ex);
			throw ex;
		}
		
		return objList;
	}

	
	@Transactional
	@Override
	public boolean updateSystemConfiguration(SystemconfigurationModel systemconfiguration) throws Exception {
		boolean updateSuccess = false;

		try {
			Systemconfiguration config=new Systemconfiguration();
			config.setId(systemconfiguration.getSystemConfigId());
			config.setKitchenOrder(systemconfiguration.getKitchenOrder());
			config.setBillPrinter(systemconfiguration.getBillPrinter());
			config.setPaxNo(systemconfiguration.getPaxNo());
			config.setOrderScreenProductSearchBy(systemconfiguration.getOrderScreenProductSearchBy());
			config.setPrintMode(systemconfiguration.getPrintMode());
			config.setCashierKitchenOrder(systemconfiguration.getCashierKitchenOrder());
			config.setMobileKitchenOrder(systemconfiguration.getMobileKitchenOrder());
			config.setTerminalKitchenOrder(systemconfiguration.getTerminalKitchenOrder());
			
			Systemtype type=new Systemtype();
			type.setId(systemconfiguration.getSystemType().getTypeId());
			config.setSystemtype(type);
			
			Branch branch=new Branch();
			branch.setBranchId(systemconfiguration.getBranch().getBranchId());
			config.setBranch(branch);
			
			
			updateSuccess=systemconfigurationRepository.updateSystemConfiguration(config);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateSystemConfiguration SystemTypeBOImpl " +ex);
			throw ex;
		}
		return updateSuccess;
	}


	@Override
	public SystemTypeModel getSystemTypeDetails(int typeId) throws Exception {
		SystemTypeModel obj=new SystemTypeModel();
		

		try {
			
			Systemtype data=systemconfigurationRepository.getSystemTypeDetails(typeId);
			obj.setTypeId(data.getId());
			obj.setName(data.getName());
			obj.setKitchenOrderType(data.getKitchenOrderType());
			obj.setMergeBillOption(data.getMergeBillOption());
			obj.setSplitBillOption(data.getSplitBillOption());
			obj.setHasTableNo(data.getHasTableNo());
			obj.setHasCardNo(data.getHasCardNo());
			obj.setHasPaxNo(data.getHasPaxNo());
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getSystemTypeDetails SystemTypeBOImpl " +ex);
			throw ex;
		}
		
		return obj;
	}

	@Transactional
	@Override
	public Boolean updateKitchenPrinter(KitchenprinterlistModal kitchenPrinter) throws Exception {
		boolean updateSuccess = false;

		try {
			
			Kitchenprinterlist printer=new Kitchenprinterlist();
			printer.setId(kitchenPrinter.getId());
			printer.setKitchenName(kitchenPrinter.getKitchenName());
			printer.setPrinterName(kitchenPrinter.getPrinterName());
			
			Branch branch=new Branch();
			branch.setBranchId(kitchenPrinter.getBranch().getBranchId());
			
			printer.setBranch(branch);
			
			
			updateSuccess=systemconfigurationRepository.updateKitchenPrinter(printer);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateKitchenPrinter SystemTypeBOImpl " +ex);
			throw ex;
		}
		return updateSuccess;
	}
}
