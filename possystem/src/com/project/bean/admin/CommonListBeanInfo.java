package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IDiscountBO;
import com.project.bo.interfaces.IExpensesBO;
import com.project.bo.interfaces.IItemRemarksBO;
import com.project.bo.interfaces.IMembershipBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.IShiftBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.bo.interfaces.RoleBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.TaxcodeModel;

import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.DepartmentModel;
import com.project.model.datamodel.ItemRemarksFunctionListModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.MembershipModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.RoleModel;
import com.project.model.datamodel.ShiftModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.SystemTypeModel;
import com.project.model.datamodel.TaxmasterModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.sale.sales.DiscountModel;
import com.project.model.sale.sales.DiscountremarksModel;
import com.project.model.sale.sales.ExpensesListModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.common.config.Configuration;
import com.project.common.factory.BeanContext;
import com.project.common.util.CommonUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.home.ChartBean;
import com.project.home.DashboardBean;
import com.project.login.LoginBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CommonListBeanInfo {
	
	
	Configuration config = Configuration.getConfiguration();
	public static Logger log = LoggerFactory.getLogger(CommonListBeanInfo.class);
	
	
	public void initLoadMethods()
	{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
		PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		chartBean.setSalesyear(year);
		
		String usertype=	loginBean.getLogdetail().getDesignation();
		getAllDepartmentList();
		//getAllBranchtList();
		//getAllBrancht();
		getAllRoleList();
		getAllCategoryList();
		//getAllSupplierList();
		getAllUomList();
		getAllTerminalList();
		getStaffList();
		getAllTaxList();
		poscounterBean.getPoscounterList();
		dashboardBean.getLatestDashboardRecord();
		
		dashboardBean.getSalesRecord(); // Sales Summary		
		//dashboardBean.getLatestDashboardSalesRecord();
		//dashboardBean.getTopsalesProduct();
		//dashboardBean.getTopSupplier();
		getCategory();
		getProductAll();
		getTaxcodeList();
		getExpensesList();
		getMembershipList();
		getItemRemarksLIst();
		getRemarksItemCategoryList();
		getDiscountRateList();
		getDiscountRemarksList();
		getShiftList();
		getSystemTypeList();
		getCategoryPrinterList();
	}
	
	
	public static List<SelectItem> getAllDepartmentList() { // Gopal// Ar

		
		List<SelectItem> departmentQty = null;
		ICommonListBO commonListBO;
		departmentQty = new ArrayList<SelectItem>();		
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			commonListBO = commonListBean.getCommonListBO();
			List<DepartmentModel> departments = commonListBO.getDepartmentListByBranch(loginBean.getBranch().getBranchId());
			for (DepartmentModel departmentModelData : departments) {
				departmentQty.add(new SelectItem(departmentModelData.getDepartmentId(), departmentModelData.getDepartmentName()));
			}
			commonListBean.setSelectDepartmentList(departmentQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllDepartmentList()", e);
			CommonUtil.showExceptionMessage("Error! get All Department List : "+e.getMessage());
		}
		return departmentQty;
	}

	
	
	public static List<SelectItem> getAllBranchtList() { // Gopal// Ar

		
		
		List<SelectItem> branchQty = null;
		IBranchBO branchBO;
		branchQty = new ArrayList<SelectItem>();
		BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {			
			branchQty.add(new SelectItem(loginBean.getBranch().getBranchId(), loginBean.getBranch().getBranchName()));			
			commonListBean.setSelectBranch(branchQty);			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllBranchtList()", e);
			CommonUtil.showExceptionMessage("Error! get All Branch List : "+e.getMessage());
		}
		return branchQty;
	}
	
	public static List<SelectItem> getAllBrancht() { // Gopal// Ar		
		
		List<SelectItem> branchQty = null;
		List<SelectItem> branchotherQty = null;
		IBranchBO branchBO;
		branchQty = new ArrayList<SelectItem>();
		branchotherQty = new ArrayList<SelectItem>();
		BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			branchBO = branchBean.getBranchBO();
			
			for (BranchModel branchModelData : branchBO.findByBranchList(null, null, null, null)) {
				branchQty.add(new SelectItem(branchModelData.getBranchId(), branchModelData.getBranchName()));
				if(loginBean.getBranch().getBranchId()!=branchModelData.getBranchId())
				{
					branchotherQty.add(new SelectItem(branchModelData.getBranchId(), branchModelData.getBranchName()));
				}
			}
			commonListBean.setSelectBranchList(branchQty);
			commonListBean.setBranchotherQty(branchotherQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllBranchtList()", e);
			CommonUtil.showExceptionMessage("Error! get All Branch List : "+e.getMessage());
		}
		return branchQty;
	}
	
	
	
	
	
      public static List<SelectItem> getAllRoleList() { // Gopal// Ar

		
		
		List<SelectItem> roleQty = null;
		RoleBO roleBO;
		roleQty = new ArrayList<SelectItem>();		
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			roleBO = commonListBean.getRoleBO();
			
			for (RoleModel roleModelData : roleBO.getAllRoles(0,loginBean.getBranch().getBranchId())) {
				roleQty.add(new SelectItem(roleModelData.getRoleId(), roleModelData.getRoleName()));
			}
			commonListBean.setSelectRoleList(roleQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllRoleList()", e);
			CommonUtil.showExceptionMessage("Error! get All Role List : "+e.getMessage());
		}
		return roleQty;
	}
	

      
	public static List<SelectItem> getAllSupplierList() { // Gopal// Ar

		
		
		List<SelectItem> supplierQty = null;
		ISupplierBO supplierBO;
		supplierQty = new ArrayList<SelectItem>();
		SupplierBean supplierBean = (SupplierBean) BeanContext.getReference("supplierBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		try {
			supplierBO =supplierBean.getSupplierBO();
			
			for (SupplierModel supplierModelData : supplierBO.findBySupplierList(null, null)) {
				supplierQty.add(new SelectItem(supplierModelData.getSupplierId(), supplierModelData.getCompanyName()));
			}
			commonListBean.setSelectSupplierList(supplierQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllSupplierList()", e);
			CommonUtil.showExceptionMessage("Error! get AllSupplierList  : "+e.getMessage());
		}
		return supplierQty;
	}
	
	
	

	


	
		public static List<SelectItem> getAllUomList() { // Gopal// Ar
		
		
		List<SelectItem> uomQty = null;
		IProductCategoryBO productCategoryBO;
		uomQty = new ArrayList<SelectItem>();
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		try {
			productCategoryBO = productCategoryBean.getProductCategoryBO();
			
			for (UomtypeModel uomtypeModelData : productCategoryBO.findByUomtypeList(null)) {
				uomQty.add(new SelectItem(uomtypeModelData.getUOMName(), uomtypeModelData.getUOMName()));
			}
			commonListBean.setSelectUomList(uomQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllUomList()", e);
			CommonUtil.showExceptionMessage("Error!   getAllUomList : "+e.getMessage());
		}
		return uomQty;
	}
	
	
	
	public static List<SelectItem> getAllCategoryList() { // Gopal// Ar

		
		
		List<SelectItem> categoryQty = null;
		IProductCategoryBO productCategoryBO;
		categoryQty = new ArrayList<SelectItem>();
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		try {
			productCategoryBO = productCategoryBean.getProductCategoryBO();
			
			for (ProductcategoryModel productcategoryModelData : productCategoryBO.findByProductcategoryList(null, null)) {
				categoryQty.add(new SelectItem(productcategoryModelData.getCategoryId(), productcategoryModelData.getName()));
			}
			commonListBean.setSelectCategoryList(categoryQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllCategoryList()", e);
			CommonUtil.showExceptionMessage("Error! get  getAllCategoryList : "+e.getMessage());
		}
		return categoryQty;
	}
	
	
	
	public static List<SelectItem> getAllTerminalList() { // Gopal// Ar

		
		
		List<SelectItem> terminalQty = null;
		
		ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			IPoscounterBO poscounterBO=objectMapController.getPoscounterBO();
			terminalQty = new ArrayList<SelectItem>();
			for (PoscounterModel poscounterModelData : poscounterBO.findByPoscounterList(loginBean.getBranch().getBranchId())) {
				terminalQty.add(new SelectItem(poscounterModelData.getCounterId(), poscounterModelData.getCounterNo()));
			}
			commonListBean.setSelectTerminalList(terminalQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllTerminalList()", e);
			CommonUtil.showExceptionMessage("Error! get  getAllCategoryList : "+e.getMessage());
		}
		return terminalQty;
	}
	

	public static List<SelectItem> getStaffList() { // Gopal// Ar

		
		List<SelectItem> doctorQty = null;
		List<SelectItem> staffcasherList = new ArrayList<SelectItem>();
		List<SelectItem> staffAllList = new ArrayList<SelectItem>();
		List<SelectItem> staffwaiterList = new ArrayList<SelectItem>();
		
		ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			IStaffBO staffBO=objectMapController.getStaffBO();
			doctorQty = new ArrayList<SelectItem>();
			for (BranchstaffmemberModel data : staffBO.findByStaffList(loginBean.getBranch().getBranchId(), null, null, null)) {
				doctorQty.add(new SelectItem(data.getStaffId(), data.getFirstName().concat("-").concat(data.getEmailAddress())));
				
				if(data.getRoleName().equalsIgnoreCase("Cashier"))
				{
					staffcasherList.add(new SelectItem(data.getFirstName() , data.getFirstName() ));
					staffAllList.add(new SelectItem(data.getStaffId() , data.getFirstName() ));
				}
				if(data.getRoleName().equalsIgnoreCase("Waiter"))
				{
					staffwaiterList.add(new SelectItem(data.getFirstName() , data.getFirstName() ));
					staffAllList.add(new SelectItem(data.getStaffId() , data.getFirstName() ));
				}
				
			}
			commonListBean.setDoctorList(doctorQty);
			commonListBean.setStaffcasherList(staffcasherList);
			commonListBean.setStaffwaiterList(staffwaiterList);
			commonListBean.setStaffAllList(staffAllList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getStaffList()", e);
			CommonUtil.showExceptionMessage("Error! get  getStaffList : "+e.getMessage());
		}
		return doctorQty;
	}
	
	
	
	public static List<SelectItem> getTaxcodeList() { // Gopal// Ar
		
		List<SelectItem> taxQty = null;
		
		ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			ICommonListBO commonListBO=objectMapController.getCommonListBO();
			taxQty = new ArrayList<SelectItem>();
			for (TaxcodeModel data : commonListBO.getTaxcodeList(loginBean.getBranch().getBranchId())) {
				taxQty.add(new SelectItem(data.getTaxCode(), ""+data.getTaxCode()));
			}
			commonListBean.setSelectTaxcodeList(taxQty);			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getTaxcodeList()", e);
			CommonUtil.showExceptionMessage("Error! get  getTaxcodeList : "+e.getMessage());
		}
		return taxQty;
	}
	
	
public static List<SelectItem> getAllTaxList() { // Gopal// Ar	
		
		List<SelectItem> departmentQty = null;
		ICommonListBO commonListBO;
		departmentQty = new ArrayList<SelectItem>();		
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try {
			commonListBO = commonListBean.getCommonListBO();
			List<TaxmasterModel> departments = commonListBO.getTaxmasterList(loginBean.getBranch().getBranchId());
			for (TaxmasterModel departmentModelData : departments) {
				departmentQty.add(new SelectItem(departmentModelData.getTaxid(), departmentModelData.getTaxname()+"-"+departmentModelData.getTaxvalue()));
			}
			commonListBean.setSelectTaxList(departmentQty);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getAllTaxList()", e);
			CommonUtil.showExceptionMessage("Error! get All getAllTaxList List : "+e.getMessage());
		}
		return departmentQty;
}



	

	
		public  List<String> getAllProductListByString(String productquery ) { // Gopal// Ar		
		
		List<String> productQty = null;
		IProductBO productBO;
		productQty = new ArrayList<String>();
		List<ProductModel> productList=null;
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");	
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

		try {
			productBO = productBean.getProductBO();
			productList= productBO.getProductList(productquery,null,loginBean.getBranch().getBranchId());
			
			for (ProductModel data : productList) {
				productQty.add(data.getBarcode());
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in getAllProductListByString of CommonListBeanInfo", e);			
		}
		return productQty;
	}
	
		
	public  List<ProductModel> getAllProductList(String productCode ) { // Gopal// Ar				
			
			IProductBO productBO;			
			List<ProductModel> productList=null;
			ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");	
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

			try {
				productBO = productBean.getProductBO();
				productList= productBO.getProductList(productCode,null,loginBean.getBranch().getBranchId());					
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Error  in getAllProductList of CommonListBeanInfo", e);			
			}
			return productList;
		}
	
	
	public  List<ProductModel> getAllProductListByName(String productName ) { // Gopal// Ar				
		
		IProductBO productBO;			
		List<ProductModel> productListTemp= new ArrayList<ProductModel>();
		List<Integer> productIds= new ArrayList<Integer>();
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");	
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		productListTemp.clear();
		productIds.clear();
		int count=0;
		try {
			productBO = productBean.getProductBO();
			//productListTemp= productBO.getProductList(null,productName,loginBean.getBranch().getBranchId());				
			for(ProductModel product:commonListBean.getProductlist())
			{
				
				if(product.getProductName().toLowerCase().startsWith(productName.toLowerCase()))
				{
					productListTemp.add(product);
					count=count+1;
				}
				if(count==500)
				{
					break;
				}
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in getAllProductList of CommonListBeanInfo", e);			
		}
		return productListTemp;
	}
	

	public  List<CustomerModel> getAllCustomerList(String customerName ) { // Gopal// Ar		
				
		ICustomerBO customerBO;	
		List<CustomerModel> customerList=null;
		CustomerBean customerBean = (CustomerBean) BeanContext.getReference("customerBean");	
		try {
			customerBO = customerBean.getCustomerBO();
			customerList= customerBO.getCustomerList(customerName);					
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in getAllCustomerList of CommonListBeanInfo", e);			
		}
		return customerList;
	}
	
	
	
	public  DeliveryorderbreakdownModel getSalesPrice(DeliveryorderbreakdownModel c ,BigDecimal salableQuantity ) { // Gopal// Ar		
		
		IProductBO productBO;			
		boolean exits = false;		
	
		List<ProductpriceModel> productPriceList;
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");	
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

		try {
			productBO = productBean.getProductBO();	
			productPriceList = productBO.getSortedProductpriceByBarcode(c.getBarcode(),loginBean.getBranch().getBranchId());
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				c.setDiscountAmount(productPriceList.get(0).getDiscountAmount());
			}
			else
			{
			for (int i = 0; i < productPriceList.size(); i++) {
				if (!exits) {
					for (int j = i + 1; j < productPriceList.size(); j++) {
						BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
						BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
						if (salableQuantity.doubleValue() >= initVal.doubleValue() && salableQuantity.doubleValue()< nextVal.doubleValue()) {
							c.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
							exits = true;
							break;
						} else {
							c.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
							i++;
						}
					}					
				}
			}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in getSalesPrice of CommonListBeanInfo", e);			
		}
		return c;
	}

	
	public List<PoscounterModel> getPoscounterList() {
		
		   List<PoscounterModel> poscounterListObj=null;
					ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			try {
				IPoscounterBO poscounterBO=objectMapController.getPoscounterBO();				
				poscounterListObj=poscounterBO.findByPoscounterList(loginBean.getBranch().getBranchId());	
			
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Error  in CommonListBeanInfo to check  getPoscounterList()", e);
				CommonUtil.showExceptionMessage("Error! get  getAllCategoryList : "+e.getMessage());
			}	
		
		return poscounterListObj;
	
	}
	
	public void getCategory()
	{try
	{
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		commonListBean.setRootNode(productCategoryBean.getDefaultRootNode());
	}
	 catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getCategory()", e);
			CommonUtil.showExceptionMessage("Error! get  getAllCategoryList : "+e.getMessage());
		}	
	}
	
	public void getProductAll(){
		try
		{ 	CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
			IProductBO productBO=commonListBean.getProductBO();
			commonListBean.setProductlist(productBO.findByProductAll());
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getProductAll()", e);
			CommonUtil.showExceptionMessage("Error! get  getAllCategoryList : "+e.getMessage());
		}	
		
	}
	
	public static void getExpensesList() {
		List<SelectItem> expensesList=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

			IExpensesBO expensesBO=objectMapController.getExpensesBO();
			for(ExpensesListModel data:expensesBO.expensesListAll(loginBean.getBranch().getBranchId())) {
				expensesList.add(new SelectItem(data.getExpID(),data.getExpName()));
			}
			commonListBean.setExpensesList(expensesList);
		}catch(Exception ex){
			log.info("Error in CommonListBeanInfo to check getExpensesList()"+ex);
			CommonUtil.showExceptionMessage("Error! getExpensesList : "+ex.getMessage());
		}
	}
	
	public static void getMembershipList() {
		List<SelectItem> membershipList=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			IMembershipBO membershipBO=objectMapController.getMembershipBO();
			for(MembershipModel data : membershipBO.fetchActiveMembershipList(loginBean.getBranch().getBranchId())) {
				membershipList.add(new SelectItem(data.getId(),data.getName()));
			}
			commonListBean.setMembershipList(membershipList);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getMembershipList() "+ex);
			CommonUtil.showExceptionMessage("Error! getMembershipList : "+ex.getMessage());
		}
		
	}
	
	public static void getItemRemarksLIst() {
		List<SelectItem> itemRemarksList=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			IItemRemarksBO itemRemarksBO=objectMapController.getItemRemarksBO();
			for(ItemRemarksListModel data : itemRemarksBO.fetchItemRemarksList(null,loginBean.getBranch().getBranchId())) {
				itemRemarksList.add(new SelectItem(data.getRemarksListID(),data.getRemarksName()));
			}
			commonListBean.setItemRemarksList(itemRemarksList);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getItemRemarksLIst() "+ex);
			CommonUtil.showExceptionMessage("Error! getItemRemarksLIst : "+ex.getMessage());
		}
	}
	
	public static void getRemarksItemCategoryList() {
		List<SelectItem> remarksItemCategoryList=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			IItemRemarksBO itemRemarksBO=objectMapController.getItemRemarksBO();
			for(ProductcategoryModel data : itemRemarksBO.fetchRemarksItemCategory()) {
				remarksItemCategoryList.add(new SelectItem(data.getCategoryId(),data.getName()));
			}
			commonListBean.setRemarksItemCategory(remarksItemCategoryList);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getRemarksItemCategoryList() "+ex);
			CommonUtil.showExceptionMessage("Error! getRemarksItemCategoryList : "+ex.getMessage());
		}
	}

	private void getDiscountRateList() {
		List<SelectItem> discountRateList=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			IDiscountBO discountBO=objectMapController.getDiscountBO();
			for(DiscountModel data : discountBO.fetchDiscountList(loginBean.getBranch().getBranchId())) {
				discountRateList.add(new SelectItem(data.getDiscountId(),data.getDiscountRate().intValue()+"%"));
			}
			
			commonListBean.setDiscountRate(discountRateList);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getDiscountRateList() "+ex);
			CommonUtil.showExceptionMessage("Error! getDiscountRateList : "+ex.getMessage());
		}
		
	}
	
	private void getDiscountRemarksList() {
		List<SelectItem> discountRemark=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			IDiscountBO discountBO=objectMapController.getDiscountBO();
			for(DiscountremarksModel data : discountBO.fetchDiscountRemarksList(loginBean.getBranch().getBranchId())) {
				discountRemark.add(new SelectItem(data.getDiscountRemarksId(),data.getRemarks()) );
			}
			
			commonListBean.setDiscountRemark(discountRemark);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getDiscountRemarkList() "+ex);
			CommonUtil.showExceptionMessage("Error! getDiscountRemarkList : "+ex.getMessage());
		}
	}

	private void getSystemTypeList() {
		List<SelectItem> systemType=new ArrayList<SelectItem>();
		try {
			CommonListBean commonListBean=(CommonListBean) BeanContext.getReference("commonListBean");
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			
			ISystemconfigurationBO systemconfigurationBO=objectMapController.getSystemconfigurationBO();
			for(SystemTypeModel data : systemconfigurationBO.fetchSystemTypeList() ) {
				systemType.add(new SelectItem(data.getTypeId(),data.getName()) );
			}
			
			commonListBean.setSystemType(systemType);
		}catch(Exception ex) {
			log.info("Error in commonListBeanInfo to check getSystemTypeList() "+ex);
			CommonUtil.showExceptionMessage("Error! getSystemTypeList : "+ex.getMessage());
		}
		
	}


	public static void getShiftList() {
		List<SelectItem> shift=new ArrayList<SelectItem>();
		IShiftBO shiftBO;
		CommonListBean commonListBean2=(CommonListBean) BeanContext.getReference("commonListBean");
		ShiftBean shiftBean=(ShiftBean) BeanContext.getReference("shiftBean");
		try {
			shiftBO=shiftBean.getShiftBO();
			List<ShiftModel> shiftData=shiftBO.loadShiftAll();
			for(ShiftModel data:shiftData) {
				shift.add(new SelectItem(data.getShiftID(),data.getShiftName()));
				
			}
			
			commonListBean2.setShiftList(shift);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getShiftList()", ex);
			CommonUtil.showExceptionMessage("Error! get All getShiftList List : "+ex.getMessage());
		}
		
	}
	
	public static void getCategoryPrinterList() {
		List<SelectItem> categoryPrinterList=new ArrayList<SelectItem>();

		IProductCategoryBO productCategoryBO;
		CommonListBean commonListBean2=(CommonListBean) BeanContext.getReference("commonListBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		try {

			productCategoryBO = productCategoryBean.getProductCategoryBO();
			List<ProductcategoryModel> categoryPrinter=productCategoryBO.getCategoryPrinterList(loginBean.getBranch().getBranchId());
			for(ProductcategoryModel data:categoryPrinter) {
				categoryPrinterList.add(new SelectItem(data.getParentCategoryId(),data.getPrinter().getKitchenName()));
			}
			
			commonListBean2.setCategoryKitchenPrinter(categoryPrinterList);
			SelectItem as=new SelectItem();
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error  in CommonListBeanInfo to check  getCategoryPrinterList()", ex);
			CommonUtil.showExceptionMessage("Error! get All getCategoryPrinterList List : "+ex.getMessage());
		}
		
	}
}
