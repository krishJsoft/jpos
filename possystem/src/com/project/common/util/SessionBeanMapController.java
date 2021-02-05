package com.project.common.util;

import javax.faces.context.FacesContext;

import com.project.bean.admin.BranchBean;
import com.project.bean.admin.BranchStaffBean;
import com.project.bean.admin.CustomerBean;
import com.project.bean.admin.DepartmentBean;
import com.project.bean.admin.DesignationBean;
import com.project.bean.admin.ProductBean;
import com.project.bean.admin.ProductCategoryBean;
import com.project.bean.admin.RoleBean;
import com.project.bean.admin.SupplierBean;

import com.project.bean.sales.sale.PosBean;
import com.project.bean.sales.sale.SalesSummaryBean;
import com.project.bean.sales.sale.SalesorderBean;
import com.project.common.factory.BeanContext;
import com.project.home.DashboardBean;
import com.project.login.LoginBean;


public class SessionBeanMapController {
	
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	
	public void clearAdminSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminDespatchBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("branchBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("branchStaffBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customerBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("departmentBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("designationBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("doctorClinicBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productCategoryBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("roleBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("supplierBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dashboardBean", null);
		
	}
	
	public void clearPurchaseSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("purchaseOrderBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("purchaseRequestBean", null);
	}	
	
	public void clearStockSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deliveryOrderBean", null);
		
	}
	
	public void clearSalesSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("quotationBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesorderBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesSummaryBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("posBean", null);
		
	}
	
	public void clearInvoiceSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("branchInvoiceBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customerInvoiceBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("supplierInvoiceBean", null);
	
	}
	
	public void clearPaymentsSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paymentsettlementBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paymentcollectionBean", null);
		
	}
	
	public void clearCommissionSession()
	{
		
	}
	
	public void clearReportSession()
	{
		
	}
	
	public void clearTaxSession()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gstaccountBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gstaccountpaymentBean", null);
	}
	
	
	public void activateAdminSession()
	{
		
		BranchBean branchBean = new BranchBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("branchBean", branchBean);
		
		BranchStaffBean branchStaffBean = new BranchStaffBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("branchStaffBean", branchStaffBean);
		
		CustomerBean customerBean = new CustomerBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customerBean", customerBean);
		
		DepartmentBean departmentBean = new DepartmentBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("departmentBean", departmentBean);
		
		DesignationBean designationBean = new DesignationBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("designationBean", designationBean);
		
		
		ProductBean productBean = new ProductBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productBean", productBean);
		
		ProductCategoryBean productCategoryBean = new ProductCategoryBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productCategoryBean", productCategoryBean);
		
		RoleBean roleBean = new RoleBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("roleBean", roleBean);
		
		SupplierBean supplierBean = new SupplierBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("supplierBean", supplierBean);

		DashboardBean dashboardBean = new DashboardBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dashboardBean", dashboardBean);

		
	}
	
	public void activatePurchaseSession()
	{
		
	}	
	
	public void activateStockSession()
	{
		
	}
	
	public void activateSalesSession()
	{
		
		SalesorderBean salesorderBean = new SalesorderBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesorderBean", salesorderBean);
		
		SalesSummaryBean salesSummaryBean = new SalesSummaryBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesSummaryBean", salesSummaryBean);
		
		PosBean posBean = new PosBean();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("posBean", posBean);
		loginBean.setForcereset(0);
		
	}
	
	public void activateInvoiceSession()
	{
		
	}
	
	public void activatePaymentsSession()
	{
		
	}
	
	public void activateCommissionSession()
	{
		
	}
	
	public void activateReportSession()
	{
		
	}
	
	public void activateTaxSession()
	{
		
	}
	
}
