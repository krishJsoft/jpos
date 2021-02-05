package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.primefaces.event.TabChangeEvent;

import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.SystemTypeModel;
import com.project.model.datamodel.SystemconfigurationModel;

@ManagedBean(name = "systemSettingBean")
@SessionScoped
public class SystemSettingBean {

	SystemconfigurationModel systemConfig=new SystemconfigurationModel();
	SystemconfigurationModel myConfig=new SystemconfigurationModel();

	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	List<SelectItem> installedPrinters=new ArrayList<SelectItem>();
	List<SelectItem> kitchenPrinters=new ArrayList<SelectItem>();
	
	List<KitchenprinterlistModal> kitchenPrinterList=new ArrayList<KitchenprinterlistModal>();
	KitchenprinterlistModal kitchenPrinter=new KitchenprinterlistModal();


	List<ProductcategoryModel> unassignedPrinterCategory=new ArrayList<ProductcategoryModel>();
	List<ProductcategoryModel> printerCategoryWithPrinter=new ArrayList<ProductcategoryModel>();
	List<ProductcategoryModel> assignedPrinterCategory=new ArrayList<ProductcategoryModel>();
	List<ProductcategoryModel> categoryAddToPrinter=new ArrayList<ProductcategoryModel>();
	List<ProductcategoryModel> categoryRemoveFromPrinter=new ArrayList<ProductcategoryModel>();

	String kitchenName;
	String installedPrinterValue;
	Integer kitchenPrinterId;
	String action="submit";
	
	private ISystemconfigurationBO systemConfigBO=objectMapController.getSystemconfigurationBO();
	private IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();

	public List<SelectItem> getInstalledPrinters() {
		return installedPrinters;
	}

	public void setInstalledPrinters(List<SelectItem> installedPrinters) {
		this.installedPrinters = installedPrinters;
	}

	public List<SelectItem> getKitchenPrinters() {
		return kitchenPrinters;
	}

	public void setKitchenPrinters(List<SelectItem> kitchenPrinters) {
		this.kitchenPrinters = kitchenPrinters;
	}

	public List<KitchenprinterlistModal> getKitchenPrinterList() {
		return kitchenPrinterList;
	}

	public void setKitchenPrinterList(List<KitchenprinterlistModal> kitchenPrinterList) {
		this.kitchenPrinterList = kitchenPrinterList;
	}
	
	public KitchenprinterlistModal getKitchenPrinter() {
		return kitchenPrinter;
	}

	public void setKitchenPrinter(KitchenprinterlistModal kitchenPrinter) {
		this.kitchenPrinter = kitchenPrinter;
	}
	

	public List<ProductcategoryModel> getUnassignedPrinterCategory() {
		return unassignedPrinterCategory;
	}

	public void setUnassignedPrinterCategory(List<ProductcategoryModel> unassignedPrinterCategory) {
		this.unassignedPrinterCategory = unassignedPrinterCategory;
	}

	public List<ProductcategoryModel> getPrinterCategoryWithPrinter() {
		return printerCategoryWithPrinter;
	}

	public void setPrinterCategoryWithPrinter(List<ProductcategoryModel> printerCategoryWithPrinter) {
		this.printerCategoryWithPrinter = printerCategoryWithPrinter;
	}

	public List<ProductcategoryModel> getAssignedPrinterCategory() {
		return assignedPrinterCategory;
	}

	public void setAssignedPrinterCategory(List<ProductcategoryModel> assignedPrinterCategory) {
		this.assignedPrinterCategory = assignedPrinterCategory;
	}

	public List<ProductcategoryModel> getCategoryAddToPrinter() {
		return categoryAddToPrinter;
	}

	public void setCategoryAddToPrinter(List<ProductcategoryModel> categoryAddToPrinter) {
		this.categoryAddToPrinter = categoryAddToPrinter;
	}

	public List<ProductcategoryModel> getCategoryRemoveFromPrinter() {
		return categoryRemoveFromPrinter;
	}

	public void setCategoryRemoveFromPrinter(List<ProductcategoryModel> categoryRemoveFromPrinter) {
		this.categoryRemoveFromPrinter = categoryRemoveFromPrinter;
	}

	public String getKitchenName() {
		return kitchenName;
	}

	public void setKitchenName(String kitchenName) {
		this.kitchenName = kitchenName;
	}

	public String getInstalledPrinterValue() {
		return installedPrinterValue;
	}

	public void setInstalledPrinterValue(String installedPrinterValue) {
		this.installedPrinterValue = installedPrinterValue;
	}

	public Integer getKitchenPrinterId() {
		return kitchenPrinterId;
	}

	public void setKitchenPrinterId(Integer kitchenPrinterId) {
		this.kitchenPrinterId = kitchenPrinterId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ISystemconfigurationBO getSystemConfigBO() {
		return systemConfigBO;
	}

	public void setSystemConfigBO(ISystemconfigurationBO systemConfigBO) {
		this.systemConfigBO = systemConfigBO;
	}

	public SystemconfigurationModel getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemconfigurationModel systemConfig) {
		this.systemConfig = systemConfig;
	}
	
	public SystemconfigurationModel getMyConfig() {
		return myConfig;
	}

	public void setMyConfig(SystemconfigurationModel myConfig) {
		this.myConfig = myConfig;
	}

	public void loadBranchSystemSetting() {
		try {
				

			systemConfig=systemConfigBO.getConfiguration(loginBean.getBranch().getBranchId());
			//systemType=systemConfigBO.getSystemTypeDetails(systemConfig.getSystemType().getTypeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadKitchenPrinterList() {
		try {
			if(kitchenPrinterList!=null) {
				kitchenPrinterList.clear();
			}
			if(kitchenPrinters!=null) {
				kitchenPrinters.clear();
			}
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			productCategoryBean.loadAllValidCategory();
			kitchenPrinterList=systemConfigBO.getKitchenPrinterList(null,loginBean.getBranch().getBranchId());
			for(KitchenprinterlistModal printer:kitchenPrinterList) {
				kitchenPrinters.add(new SelectItem(printer.getId(),printer.getKitchenName()));

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadInstalledPrinterList() {
		this.setKitchenName(null);
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		
		if(installedPrinters!=null) {
			installedPrinters.clear();
		}
		for (PrintService printer : printServices) {
			installedPrinters.add(new SelectItem(printer.getName(),printer.getName()));
		}
		this.setAction("submit");

	}
	
	
	
	public void onSettingTabChange(TabChangeEvent event) {

		   if (event.getTab().getId().equals("tab1")) {
		       
		   } else if(event.getTab().getId().equals("tab2")) {
			   loadKitchenPrinterList();
			   this.setKitchenPrinterId(null);
			   this.setAssignedPrinterCategory(null);
			  // loadAssignedCategoryList();
			   loadUnassignedCategoryList();
		   }
		
	}
	
	public void onKitchenPrinterChange() {
		if(this.getKitchenPrinterId()>0) {
			loadAssignedCategoryList();
		}else {
			this.setAssignedPrinterCategory(null);
		}
	}
	
	public void loadUnassignedCategoryList() {
		try {
			
			if(unassignedPrinterCategory!=null) {
				unassignedPrinterCategory.clear();
			}
			

			List<ProductcategoryModel> category=productCategoryBO.loadCategoryWithoutPrinter(loginBean.getBranch().getBranchId());
	
			unassignedPrinterCategory=category;

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	
	public void loadAssignedCategoryList() {
		try {
			if(assignedPrinterCategory!=null) {
				assignedPrinterCategory.clear();
			}
			
			List<ProductcategoryModel> category=productCategoryBO.loadCategoryListForPrinter(this.getKitchenPrinterId(),loginBean.getBranch().getBranchId());
		
			assignedPrinterCategory=category;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createNewKitchenPrinter() {
		KitchenprinterlistModal kitchenPrinter=new KitchenprinterlistModal();
		kitchenPrinter.setPrinterName(this.getInstalledPrinterValue());
		kitchenPrinter.setKitchenName(this.getKitchenName());
		
		BranchModel branch=new BranchModel();
		branch.setBranchId(loginBean.getBranch().getBranchId());
		kitchenPrinter.setBranch(branch);
		
		Boolean saveSucess=false;
		try {
			saveSucess=systemConfigBO.createNewKitchenPrinter(kitchenPrinter);
			if(saveSucess) {
				this.loadKitchenPrinterList();
				
			}
			this.setAction("submit");
			this.setKitchenName(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addCategoryToPrinter() {
		
		try {
			List<Integer> id=new ArrayList<Integer>();

			for(ProductcategoryModel cat:this.getCategoryAddToPrinter()) {
				id.add(cat.getCategoryId());
			}
			if(id.size()>0) {
				if(this.kitchenPrinterId>0) {
					productCategoryBO.updatePrinterIdForCategory(this.getKitchenPrinterId(),id);
					this.loadAssignedCategoryList();
					this.loadUnassignedCategoryList();
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeCategoryFromPrinter() {
		try {
			List<Integer> id=new ArrayList<Integer>();

			for(ProductcategoryModel cat:this.getCategoryRemoveFromPrinter()) {
				id.add(cat.getCategoryId());
			}
			if(id.size()>0) {
				if(this.kitchenPrinterId>0) {
					productCategoryBO.updatePrinterIdForCategory(null,id);
					this.loadAssignedCategoryList();
					this.loadUnassignedCategoryList();
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	public void loadSystemConfiguration() {
		try {
			this.setMyConfig(systemConfigBO.getConfiguration(loginBean.getBranch().getBranchId()));
			this.setSystemConfig(this.getMyConfig());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateSystemConfiguration() {
		boolean saveSuccess=false;
		try {
			saveSuccess=systemConfigBO.updateSystemConfiguration(this.getSystemConfig());
			loadSystemConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onChangeSystemType() {
		
		try {
			SystemTypeModel systemType=new SystemTypeModel();
			systemType=systemConfigBO.getSystemTypeDetails(this.getSystemConfig().getSystemType().getTypeId());
				this.getSystemConfig().setSystemType(systemType);
			//if(systemType.getKitchenOrderType().equalsIgnoreCase("AFTER PAYMENT")) {
				this.getSystemConfig().setKitchenOrder(false);
				this.getSystemConfig().setMobileKitchenOrder(false);
				this.getSystemConfig().setTerminalKitchenOrder(false);
				this.getSystemConfig().setCashierKitchenOrder(false);
				this.getSystemConfig().setBillPrinter(false);	
				this.getSystemConfig().setPaxNo(false);
		//	}else {
		//		this.getSystemConfig().setKitchenOrder(true);
		//		this.getSystemConfig().setKitchenOrder(false);
		//		this.getSystemConfig().setMobileKitchenOrder(false);
		//		this.getSystemConfig().setTerminalKitchenOrder(false);
		//		this.getSystemConfig().setCashierKitchenOrder(false);
		//		this.getSystemConfig().setBillPrinter(false);	
		//	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onKitchenOrder() {
		if(this.getSystemConfig().getKitchenOrder()==false) {
			this.getSystemConfig().setMobileKitchenOrder(false);
			this.getSystemConfig().setTerminalKitchenOrder(false);
			this.getSystemConfig().setCashierKitchenOrder(false);
			this.getSystemConfig().setBillPrinter(false);
			this.getSystemConfig().setPaxNo(false);
		}else if(this.getSystemConfig().getKitchenOrder()==true && this.getSystemConfig().getSystemType().getKitchenOrderType().equalsIgnoreCase("AFTER PAYMENT")) {
			this.getSystemConfig().setCashierKitchenOrder(true);
		}
		
	}
	
	public void editKitchenPrinter(ActionEvent event) {
		  
		  try {
			  Integer id=Integer.parseInt("" +event.getComponent().getAttributes().get("printerId"));
			  KitchenprinterlistModal printer=systemConfigBO.getKitchenPrinterDetails(id);
			  this.setInstalledPrinterValue(printer.getPrinterName());
			  this.setKitchenName(printer.getKitchenName());
			  
			  this.kitchenPrinter=new KitchenprinterlistModal();
			  this.kitchenPrinter.setId(printer.getId());
			  
			  BranchModel branch=new BranchModel();
			  branch.setBranchId(loginBean.getBranch().getBranchId());
			  
			  this.kitchenPrinter.setBranch(branch);
			  this.setAction("update");
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	}
	
	public void updateKitchenPrinter() {
		 try {
			 
			 this.getKitchenPrinter().setKitchenName(this.getKitchenName());
			 this.getKitchenPrinter().setPrinterName(this.getInstalledPrinterValue());
			 
			  Boolean updateSuccess=systemConfigBO.updateKitchenPrinter(this.getKitchenPrinter());
			  
			  this.loadKitchenPrinterList();
			  this.setKitchenName(null);
			  this.setKitchenPrinter(null);
			  this.setAction("submit");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

