package com.project.bean.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.RoleBO;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.validation.UIComponent;

@ManagedBean(name = "commonListBean")
@SessionScoped
public class CommonListBean {

	Configuration config = Configuration.getConfiguration();
	public static Logger log = LoggerFactory.getLogger(CommonListBean.class);
	
	private TreeNode rootNode;
	List<ProductModel> productlist = new ArrayList<ProductModel>();	
	
	@ManagedProperty("#{commonListBO}")
	private ICommonListBO commonListBO; // injected Spring defined service

	public ICommonListBO getCommonListBO() {
		return commonListBO;
	}

	public void setCommonListBO(ICommonListBO commonListBO) {
		this.commonListBO = commonListBO;
	}	

	@ManagedProperty("#{productBO}")
	private IProductBO productBO;

	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}
	
	@ManagedProperty("#{roleBO}")
	private RoleBO roleBO; // injected Spring defined service for Role
	
	
	public RoleBO getRoleBO() {
		return roleBO;
	}

	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}
	

	private List<SelectItem> doctorList = new ArrayList<SelectItem>();
	
	private List<SelectItem> staffAllList=new ArrayList<SelectItem>();
	
	private List<SelectItem> staffwaiterList = new ArrayList<SelectItem>();
	
	private List<SelectItem> staffcasherList = new ArrayList<SelectItem>();
	
	private List<SelectItem> staffadminList = new ArrayList<SelectItem>();

	
	

	public List<SelectItem> getStaffwaiterList() {
		return staffwaiterList;
	}

	public void setStaffwaiterList(List<SelectItem> staffwaiterList) {
		this.staffwaiterList = staffwaiterList;
	}

	public List<SelectItem> getStaffcasherList() {
		return staffcasherList;
	}

	public void setStaffcasherList(List<SelectItem> staffcasherList) {
		this.staffcasherList = staffcasherList;
	}

	public List<SelectItem> getStaffadminList() {
		return staffadminList;
	}

	public void setStaffadminList(List<SelectItem> staffadminList) {
		this.staffadminList = staffadminList;
	}

	public List<SelectItem> getStaffAllList() {
		return staffAllList;
	}

	public void setStaffAllList(List<SelectItem> staffAllList) {
		this.staffAllList = staffAllList;
	}

	public List<SelectItem> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<SelectItem> doctorList) {
		this.doctorList = doctorList;
	}

	private List<SelectItem> selectDepartmentList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectDepartmentList() {
		return selectDepartmentList;
	}

	public void setSelectDepartmentList(List<SelectItem> selectDepartmentList) {
		this.selectDepartmentList = selectDepartmentList;
	}

	private List<SelectItem> selectDesignationList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectDesignationList() {
		return selectDesignationList;
	}

	public void setSelectDesignationList(List<SelectItem> selectDesignationList) {
		this.selectDesignationList = selectDesignationList;
	}

	
	private List<SelectItem> selectRoleList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectRoleList() {
		return selectRoleList;
	}

	public void setSelectRoleList(List<SelectItem> selectRoleList) {
		this.selectRoleList = selectRoleList;
	}
	
	
	private List<SelectItem> selectBranchList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectBranchList() {
		return selectBranchList;
	}

	public void setSelectBranchList(List<SelectItem> selectBranchList) {
		this.selectBranchList = selectBranchList;
	}
	private List<SelectItem> branchotherQty = new ArrayList<SelectItem>();	
	
	public List<SelectItem> getBranchotherQty() {
		return branchotherQty;
	}

	public void setBranchotherQty(List<SelectItem> branchotherQty) {
		this.branchotherQty = branchotherQty;
	}

	private List<SelectItem> selectBranch = new ArrayList<SelectItem>();	

	public List<SelectItem> getSelectBranch() {
		return selectBranch;
	}

	public void setSelectBranch(List<SelectItem> selectBranch) {
		this.selectBranch = selectBranch;
	}

	private List<SelectItem> selectSupplierList = new ArrayList<SelectItem>();	
	
	private List<SelectItem> selectUomList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectSupplierList() {
		return selectSupplierList;
	}
	public void setSelectSupplierList(List<SelectItem> selectSupplierList) {
		this.selectSupplierList = selectSupplierList;	}

	public List<SelectItem> getSelectUomList() {
		return selectUomList;	}

	public void setSelectUomList(List<SelectItem> selectUomList) {
		this.selectUomList = selectUomList;	}

	private List<SelectItem> selectCategoryList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectCategoryList() {
		return selectCategoryList;
	}

	public void setSelectCategoryList(List<SelectItem> selectCategoryList) {
		this.selectCategoryList = selectCategoryList;
	}
	
private List<SelectItem> selectTaxList = new ArrayList<SelectItem>();	
	
	public List<SelectItem> getSelectTaxList() {
		return selectTaxList;
	}

	public void setSelectTaxList(List<SelectItem> selectTaxList) {
		this.selectTaxList = selectTaxList;
	}

	private List<SelectItem> selectTaxcodeList = new ArrayList<SelectItem>();
	
	public List<SelectItem> getSelectTaxcodeList() {
		return selectTaxcodeList;
	}

	public void setSelectTaxcodeList(List<SelectItem> selectTaxcodeList) {
		this.selectTaxcodeList = selectTaxcodeList;
	}

	private List<SelectItem> purchaseRequestStatusList = new ArrayList<SelectItem>();

	public List<SelectItem> getPurchaseRequestStatusList() {		
		List<SelectItem> purchaseRequestStatusList = null;
		purchaseRequestStatusList = new ArrayList<SelectItem>();
		purchaseRequestStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_NEWORDER_LABLE)));
		purchaseRequestStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_PROCESSED_LABLE)));
		purchaseRequestStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_ORDERED_LABLE)));
		return purchaseRequestStatusList;
	}

	public void setPurchaseRequestStatusList(
			List<SelectItem> purchaseRequestStatusList) {
		this.purchaseRequestStatusList = purchaseRequestStatusList;
	}
	
	
	private List<SelectItem> purchaseOrderStatusList = new ArrayList<SelectItem>();	
	
	
	public List<SelectItem> getPurchaseOrderStatusList() {
		
		List<SelectItem> purchaseOrderStatusList = null;
		purchaseOrderStatusList = new ArrayList<SelectItem>();
		purchaseOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEORDER_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.PURCHASEORDER_STATUS_NEWORDER_LABLE)));
		purchaseOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEORDER_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.PURCHASEORDER_STATUS_PROCESSED_LABLE)));
		purchaseOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_LABLE)));
		purchaseOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.PURCHASEORDER_STATUS_CLOSED_VALUE), config.getValue(IConfiguration.PURCHASEORDER_STATUS_CLOSED_LABLE)));
		return purchaseOrderStatusList;
	}

	public void setPurchaseOrderStatusList(List<SelectItem> purchaseOrderStatusList) {
		this.purchaseOrderStatusList = purchaseOrderStatusList;
	}
	
	private List<SelectItem> deliveryOrderStatusList = new ArrayList<SelectItem>();
	
	public List<SelectItem> getDeliveryOrderStatusList() {
		List<SelectItem> deliveryOrderStatusList = null;
		
		deliveryOrderStatusList = new ArrayList<SelectItem>();
		deliveryOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_LABLE)));
		deliveryOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.DELIVERYORDER_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.DELIVERYORDER_STATUS_PROCESSED_LABLE)));
		deliveryOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.DELIVERYORDER_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.DELIVERYORDER_STATUS_ORDERED_LABLE)));
		deliveryOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.DELIVERYORDER_STATUS_CLOSED_VALUE), config.getValue(IConfiguration.DELIVERYORDER_STATUS_CLOSED_LABLE)));
		deliveryOrderStatusList.add(new SelectItem(config.getValue(IConfiguration.DELIVERYORDER_STATUS_CANCEL_VALUE), config.getValue(IConfiguration.DELIVERYORDER_STATUS_CANCEL_LABLE)));

		
		return deliveryOrderStatusList;
	}

	public void setDeliveryOrderStatusList(List<SelectItem> deliveryOrderStatusList) {
		this.deliveryOrderStatusList = deliveryOrderStatusList;
	}

	public List<ProductModel> getProductCode(String productCode)
	{
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			results=commoninfo.getAllProductList(productCode);				
		}
		catch(Exception e)
		{
			log.info("Error  in getProductCode of CommonListBean", e.toString());		
		}		
		return results;
	}			
	
	
	public List<ProductModel> getAllProductListByName(String productName)
	{
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			results=commoninfo.getAllProductListByName(productName);				
		}
		catch(Exception e)
		{
			log.info("Error  in getAllProductListByName of CommonListBean", e.toString());		
		}		
		return results;
	}			
	
	
	public List<CustomerModel> getCustomerCode(String customerName)
	{
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			results=commoninfo.getAllCustomerList(customerName);				
		}
		catch(Exception e)
		{
			log.info("Error  in getCustomerCode of CommonListBean", e.toString());			
		}		
		return results;
	}			
	
	
	
	private List<SelectItem> quotationStatusList = new ArrayList<SelectItem>();
	
	public List<SelectItem> getQuotationStatusList(){
		List<SelectItem> quotationStatusList = null;
		quotationStatusList = new ArrayList<SelectItem>();
		quotationStatusList.add(new SelectItem(config.getValue(IConfiguration.QUOTATION_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.QUOTATION_STATUS_NEWORDER_LABLE)));
		quotationStatusList.add(new SelectItem(config.getValue(IConfiguration.QUOTATION_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.QUOTATION_STATUS_PROCESSED_LABLE)));
		quotationStatusList.add(new SelectItem(config.getValue(IConfiguration.QUOTATION_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.QUOTATION_STATUS_ORDERED_LABLE)));
		quotationStatusList.add(new SelectItem(config.getValue(IConfiguration.QUOTATION_STATUS_DECLINED_VALUE), config.getValue(IConfiguration.QUOTATION_STATUS_DECLINED_LABLE)));
		return quotationStatusList;
	}
	
	public void setQuotationStatusList(
			List<SelectItem> quotationStatusList) {
		this.quotationStatusList = quotationStatusList;
	}
	
	
	private List<SelectItem> selectTerminalList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectTerminalList() {
		return selectTerminalList;
	}

	public void setSelectTerminalList(List<SelectItem> selectTerminalList) {
		this.selectTerminalList = selectTerminalList;
	}

	
	private List<SelectItem> salesStatusList = new ArrayList<SelectItem>();
	private List<SelectItem> invoiceStatusList = new ArrayList<SelectItem>();
	private List<SelectItem> collectionStatusList = new ArrayList<SelectItem>();

	public List<SelectItem> getSalesStatusList() {
		
		List<SelectItem> salesStatusList = null;
		salesStatusList = new ArrayList<SelectItem>();
		salesStatusList.add(new SelectItem(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_LABLE)));
		salesStatusList.add(new SelectItem(config.getValue(IConfiguration.SALESORDER_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.SALESORDER_STATUS_PROCESSED_LABLE)));
		salesStatusList.add(new SelectItem(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_LABLE)));
		salesStatusList.add(new SelectItem(config.getValue(IConfiguration.SALESORDER_STATUS_CLOSED_VALUE), config.getValue(IConfiguration.SALESORDER_STATUS_CLOSED_LABLE)));

		return salesStatusList;
	}

	public void setSalesStatusList(List<SelectItem> salesStatusList) {
		this.salesStatusList = salesStatusList;
	}

	
	
	
	public List<SelectItem> getInvoiceStatusList() {
		
		List<SelectItem> invoiceStatusList = null;
		invoiceStatusList = new ArrayList<SelectItem>();
		invoiceStatusList.add(new SelectItem(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_LABLE)));
		invoiceStatusList.add(new SelectItem(config.getValue(IConfiguration.INVOICE_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.INVOICE_STATUS_PROCESSED_LABLE)));
		invoiceStatusList.add(new SelectItem(config.getValue(IConfiguration.INVOICE_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.INVOICE_STATUS_ORDERED_LABLE)));
		return invoiceStatusList;
	}

	public void setInvoiceStatusList(List<SelectItem> invoiceStatusList) {
		this.invoiceStatusList = invoiceStatusList;
	}

	public List<SelectItem> getCollectionStatusList() {
		
		List<SelectItem> collectionStatusList = null;
		collectionStatusList = new ArrayList<SelectItem>();
		//collectionStatusList.add(new SelectItem(config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_LABLE)));
		collectionStatusList.add(new SelectItem(config.getValue(IConfiguration.COLLECTION_STATUS_APPROVED_VALUE),config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_LABLE)));
		collectionStatusList.add(new SelectItem(config.getValue(IConfiguration.COLLECTION_STATUS_APPORTIONED_VALUE), config.getValue(IConfiguration.COLLECTION_STATUS_APPORTIONED_LABLE)));
		collectionStatusList.add(new SelectItem(config.getValue(IConfiguration.COLLECTION_STATUS_SETTLED_VALUE), config.getValue(IConfiguration.COLLECTION_STATUS_SETTLED_LABLE)));
		collectionStatusList.add(new SelectItem(config.getValue(IConfiguration.COLLECTION_STATUS_CLOSED_VALUE), config.getValue(IConfiguration.COLLECTION_STATUS_CLOSED_LABLE)));

		return collectionStatusList;
	}

	public void setCollectionStatusList(List<SelectItem> collectionStatusList) {
		this.collectionStatusList = collectionStatusList;
	}
	
	
	
	private List<SelectItem> returnStatusList = new ArrayList<SelectItem>();
	private List<SelectItem> doctorStatusList = new ArrayList<SelectItem>();

	public List<SelectItem> getReturnStatusList() {
		List<SelectItem> returnStatusList = null;
		returnStatusList = new ArrayList<SelectItem>();
		returnStatusList.add(new SelectItem(config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_LABLE)));
		returnStatusList.add(new SelectItem(config.getValue(IConfiguration.RETURN_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.RETURN_STATUS_PROCESSED_LABLE)));
		returnStatusList.add(new SelectItem(config.getValue(IConfiguration.RETURN_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.RETURN_STATUS_ORDERED_LABLE)));
		return returnStatusList;
	}

	public void setReturnStatusList(List<SelectItem> returnStatusList) {
		this.returnStatusList = returnStatusList;
	}

	public List<SelectItem> getDoctorStatusList() {
		List<SelectItem> doctorStatusList = null;
		doctorStatusList = new ArrayList<SelectItem>();
		doctorStatusList.add(new SelectItem(config.getValue(IConfiguration.DOCTOR_STATUS_NEWORDER_VALUE),config.getValue(IConfiguration.DOCTOR_STATUS_NEWORDER_LABLE)));
		doctorStatusList.add(new SelectItem(config.getValue(IConfiguration.DOCTOR_STATUS_PROCESSED_VALUE),config.getValue(IConfiguration.DOCTOR_STATUS_PROCESSED_LABLE)));
		doctorStatusList.add(new SelectItem(config.getValue(IConfiguration.DOCTOR_STATUS_ORDERED_VALUE), config.getValue(IConfiguration.DOCTOR_STATUS_ORDERED_LABLE)));
		doctorStatusList.add(new SelectItem(config.getValue(IConfiguration.DOCTOR_STATUS_CLOSED_VALUE), config.getValue(IConfiguration.DOCTOR_STATUS_CLOSED_LABLE)));

		return doctorStatusList;
	}

	public void setDoctorStatusList(List<SelectItem> doctorStatusList) {
		this.doctorStatusList = doctorStatusList;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public List<ProductModel> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<ProductModel> productlist) {
		this.productlist = productlist;
	}
	
	
	public List<SelectItem> getMonthList() {
		List<SelectItem> monthList = new ArrayList<SelectItem>();		
		for(int i=0;i<12;i++)
		{
			monthList.add(new SelectItem(i,DateUtil.getMonthForInt(i)));
		}		
		return monthList;
	}
	
	public List<SelectItem> getYearList() {
		
		int startYear=Integer.parseInt(config.getValue(IConfiguration.PRODUCT_START_YEAR));
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		List<SelectItem> yearList = new ArrayList<SelectItem>();		
		for(int i=startYear;i<year+1;i++)
		{
			yearList.add(new SelectItem(i,""+i));
		}		
		return yearList;
	}
	
	private List<SelectItem> expensesList=new ArrayList<SelectItem>();
	private List<SelectItem> membershipList=new ArrayList<SelectItem>();
	private List<SelectItem> itemRemarksList=new ArrayList<SelectItem>();
	
	
	public List<SelectItem> getExpensesList() {
		return expensesList;
	}

	public void setExpensesList(List<SelectItem> expensesList) {
		this.expensesList = expensesList;
	}
	
	public List<SelectItem> getMembershipList() {
		return membershipList;
	}

	public void setMembershipList(List<SelectItem> membershipList) {
		this.membershipList = membershipList;
	}

	public List<SelectItem> getItemRemarksList() {
		return itemRemarksList;
	}

	public void setItemRemarksList(List<SelectItem> itemRemarksList) {
		this.itemRemarksList = itemRemarksList;
	}

	private List<SelectItem> remarksItemCategory;

	public List<SelectItem> getRemarksItemCategory() {
		return remarksItemCategory;
	}

	public void setRemarksItemCategory(List<SelectItem> remarksItemCategory) {
		this.remarksItemCategory = remarksItemCategory;
	}

	private List<SelectItem> discountRate;
	
	public List<SelectItem> getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(List<SelectItem> discountRate) {
		this.discountRate = discountRate;
	}
	
	private List<SelectItem> discountRemark;

	public List<SelectItem> getDiscountRemark() {
		return discountRemark;
	}

	public void setDiscountRemark(List<SelectItem> discountRemark) {
		this.discountRemark = discountRemark;
	}

	private List<SelectItem> systemType;
	
	public List<SelectItem> getSystemType() {
		return systemType;
	}

	public void setSystemType(List<SelectItem> systemType) {
		this.systemType = systemType;
	}

	private List<SelectItem> shiftList = new ArrayList<SelectItem>();
	
	public List<SelectItem> getShiftList() {
		return shiftList;
	}

	public void setShiftList(List<SelectItem> shiftList) {
		this.shiftList = shiftList;
	}
	
	private List<SelectItem> categoryKitchenPrinter = new ArrayList<SelectItem>();

	public List<SelectItem> getCategoryKitchenPrinter() {
		return categoryKitchenPrinter;
	}

	public void setCategoryKitchenPrinter(List<SelectItem> categoryKitchenPrinter) {
		this.categoryKitchenPrinter = categoryKitchenPrinter;
	}
	


	
}
