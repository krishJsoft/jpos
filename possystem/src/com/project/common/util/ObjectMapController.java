package com.project.common.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.IBranchtransferBO;
import com.project.bo.interfaces.ICommissionBO;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.ICustomerinvoiceBO;
import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.IDiscountBO;
import com.project.bo.interfaces.IDoctorClinicBO;
import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.bo.interfaces.IExpensesBO;
import com.project.bo.interfaces.IGstaccounBO;
import com.project.bo.interfaces.IItemRemarksBO;
import com.project.bo.interfaces.ILoyaltyredemptionBO;
import com.project.bo.interfaces.IMembershipBO;
import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.bo.interfaces.IPaymentsettlementBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.IProductbatchBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.bo.interfaces.IQuotationSupplierBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISalesreturnBO;
import com.project.bo.interfaces.IShiftBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.bo.interfaces.ISupplierinvoiceBO;
import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.bo.interfaces.RoleBO;
import com.project.bo.interfaces.RoleFunctionLinkBO;

@ManagedBean(name = "objectMapController")
@SessionScoped
public class ObjectMapController {

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

	@ManagedProperty("#{supplierBO}")
	private ISupplierBO supplierBO; // injected Spring defined service

	public ISupplierBO getSupplierBO() {
		return supplierBO;
	}

	public void setSupplierBO(ISupplierBO supplierBO) {
		this.supplierBO = supplierBO;
	}

	@ManagedProperty("#{adminDespatchBO}")
	private IAdminDespatchBO adminDespatchBO; // injected Spring defined service

	public IAdminDespatchBO getAdminDespatchBO() {
		return adminDespatchBO;
	}

	public void setAdminDespatchBO(IAdminDespatchBO adminDespatchBO) {
		this.adminDespatchBO = adminDespatchBO;
	}

	@ManagedProperty("#{branchBO}")
	private IBranchBO branchBO; // injected Spring defined service

	public IBranchBO getBranchBO() {
		return branchBO;
	}

	public void setBranchBO(IBranchBO branchBO) {
		this.branchBO = branchBO;
	}

	@ManagedProperty("#{staffBO}")
	private IStaffBO staffBO; // injected Spring defined service

	public IStaffBO getStaffBO() {
		return staffBO;
	}

	public void setStaffBO(IStaffBO staffBO) {
		this.staffBO = staffBO;
	}

	@ManagedProperty("#{customerBO}")
	private ICustomerBO customerBO; // injected Spring defined service

	public ICustomerBO getCustomerBO() {
		return customerBO;
	}

	public void setCustomerBO(ICustomerBO customerBO) {
		this.customerBO = customerBO;
	}

	@ManagedProperty("#{doctorBO}")
	private IDoctorClinicBO doctorBO; // injected Spring defined service

	public IDoctorClinicBO getDoctorBO() {
		return doctorBO;
	}

	public void setDoctorBO(IDoctorClinicBO doctorBO) {
		this.doctorBO = doctorBO;
	}

	@ManagedProperty("#{productCategoryBO}")
	private IProductCategoryBO productCategoryBO; // injected Spring defined
													// service

	public IProductCategoryBO getProductCategoryBO() {
		return productCategoryBO;
	}

	public void setProductCategoryBO(IProductCategoryBO productCategoryBO) {
		this.productCategoryBO = productCategoryBO;
	}

	@ManagedProperty("#{roleBO}")
	private RoleBO roleBO; // injected Spring defined service for Role

	@ManagedProperty("#{rolefunctionLinkBO}")
	private RoleFunctionLinkBO rolefunctionlinkBO; // injected Spring defined

	public RoleBO getRoleBO() {
		return roleBO;
	}

	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}

	public RoleFunctionLinkBO getRolefunctionlinkBO() {
		return rolefunctionlinkBO;
	}

	public void setRolefunctionlinkBO(RoleFunctionLinkBO rolefunctionlinkBO) {
		this.rolefunctionlinkBO = rolefunctionlinkBO;
	}

	@ManagedProperty("#{deliveryOrderBO}")
	private IDeliveryorderBO deliveryOrderBO; // injected Spring defined

	@ManagedProperty("#{purchaseOrderBO}")
	private IPurchaseOrderBO purchaseOrderBO; // injected Spring defined

	public IDeliveryorderBO getDeliveryOrderBO() {
		return deliveryOrderBO;
	}

	public void setDeliveryOrderBO(IDeliveryorderBO deliveryOrderBO) {
		this.deliveryOrderBO = deliveryOrderBO;
	}

	public IPurchaseOrderBO getPurchaseOrderBO() {
		return purchaseOrderBO;
	}

	public void setPurchaseOrderBO(IPurchaseOrderBO purchaseOrderBO) {
		this.purchaseOrderBO = purchaseOrderBO;
	}

	@ManagedProperty("#{salesOrderBO}")
	private ISalesorderBO salesOrderBO; // injected Spring defined

	@ManagedProperty("#{branchinvoiceBO}")
	private IBranchinvoiceBO branchinvoiceBO; // injected Spring defined

	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}

	public IBranchinvoiceBO getBranchinvoiceBO() {
		return branchinvoiceBO;
	}

	public void setBranchinvoiceBO(IBranchinvoiceBO branchinvoiceBO) {
		this.branchinvoiceBO = branchinvoiceBO;
	}

	@ManagedProperty("#{customerinvoiceBO}")
	private ICustomerinvoiceBO customerinvoiceBO; // injected Spring defined

	public ICustomerinvoiceBO getCustomerinvoiceBO() {
		return customerinvoiceBO;
	}

	public void setCustomerinvoiceBO(ICustomerinvoiceBO customerinvoiceBO) {
		this.customerinvoiceBO = customerinvoiceBO;
	}

	@ManagedProperty("#{purchaseRequestBO}")
	private IPurchaserequestBO purchaseRequestBO; // injected Spring defined
													// service

	public IPurchaserequestBO getPurchaseRequestBO() {
		return purchaseRequestBO;
	}

	public void setPurchaseRequestBO(IPurchaserequestBO purchaseRequestBO) {
		this.purchaseRequestBO = purchaseRequestBO;
	}

	@ManagedProperty("#{doctorsPrescriptionsBO}")
	private IDoctorsPrescriptionsBO dpBO;

	public IDoctorsPrescriptionsBO getDpBO() {
		return dpBO;
	}

	public void setDpBO(IDoctorsPrescriptionsBO dpBO) {
		this.dpBO = dpBO;
	}

	@ManagedProperty("#{quotationBO}")
	private IQuotationBO quotationBO; // injected Spring defined
										// service

	public IQuotationBO getQuotationBO() {
		return quotationBO;
	}

	public void setQuotationBO(IQuotationBO quotationBO) {
		this.quotationBO = quotationBO;
	}

	@ManagedProperty("#{supplierinvoiceBO}")
	private ISupplierinvoiceBO supplierinvoiceBO; // injected Spring defined

	// service

	public ISupplierinvoiceBO getSupplierinvoiceBO() {
		return supplierinvoiceBO;
	}

	public void setSupplierinvoiceBO(ISupplierinvoiceBO supplierinvoiceBO) {
		this.supplierinvoiceBO = supplierinvoiceBO;
	}

	@ManagedProperty("#{productbatchBO}")
	private IProductbatchBO productbatchBO; // injected Spring defined //
											// service

	public IProductbatchBO getProductbatchBO() {
		return productbatchBO;
	}

	public void setProductbatchBO(IProductbatchBO productbatchBO) {
		this.productbatchBO = productbatchBO;
	}

	@ManagedProperty("#{poscounterBO}")
	private IPoscounterBO poscounterBO; // injected Spring defined // service

	public IPoscounterBO getPoscounterBO() {
		return poscounterBO;
	}

	public void setPoscounterBO(IPoscounterBO poscounterBO) {
		this.poscounterBO = poscounterBO;
	}

	@ManagedProperty("#{paymentcollectionBO}")
	private IPaymentcollectionBO paymentcollectionBO; // injected Spring defined
														// // service

	public IPaymentcollectionBO getPaymentcollectionBO() {
		return paymentcollectionBO;
	}

	public void setPaymentcollectionBO(IPaymentcollectionBO paymentcollectionBO) {
		this.paymentcollectionBO = paymentcollectionBO;
	}

	@ManagedProperty("#{paymentsettlementBO}")
	private IPaymentsettlementBO paymentsettlementBO; // injected Spring defined
														// // service

	public IPaymentsettlementBO getPaymentsettlementBO() {
		return paymentsettlementBO;
	}

	public void setPaymentsettlementBO(IPaymentsettlementBO paymentsettlementBO) {
		this.paymentsettlementBO = paymentsettlementBO;
	}

	@ManagedProperty("#{salesreturnBO}")
	private ISalesreturnBO salesreturnBO;

	public ISalesreturnBO getSalesreturnBO() {
		return salesreturnBO;
	}

	public void setSalesreturnBO(ISalesreturnBO salesreturnBO) {
		this.salesreturnBO = salesreturnBO;
	}

	@ManagedProperty("#{commissionBO}")
	private ICommissionBO commissionBO;

	public ICommissionBO getCommissionBO() {
		return commissionBO;
	}

	public void setCommissionBO(ICommissionBO commissionBO) {
		this.commissionBO = commissionBO;
	}

	@ManagedProperty("#{redemptionBO}")
	private ILoyaltyredemptionBO redemptionBO;

	public ILoyaltyredemptionBO getRedemptionBO() {
		return redemptionBO;
	}

	public void setRedemptionBO(ILoyaltyredemptionBO redemptionBO) {
		this.redemptionBO = redemptionBO;
	}

	@ManagedProperty("#{branchsalesBO}")
	private IBranchsalesBO branchsalesBO;

	public IBranchsalesBO getBranchsalesBO() {
		return branchsalesBO;
	}

	public void setBranchsalesBO(IBranchsalesBO branchsalesBO) {
		this.branchsalesBO = branchsalesBO;
	}

	@ManagedProperty("#{branchtransferBO}")
	private IBranchtransferBO branchtransferBO;

	public IBranchtransferBO getBranchtransferBO() {
		return branchtransferBO;
	}

	public void setBranchtransferBO(IBranchtransferBO branchtransferBO) {
		this.branchtransferBO = branchtransferBO;
	}

	@ManagedProperty("#{quotationsupplierBO}")
	private IQuotationSupplierBO quotationsupplierBO; // injected Spring defined
														// service

	public IQuotationSupplierBO getQuotationsupplierBO() {
		return quotationsupplierBO;
	}

	public void setQuotationsupplierBO(IQuotationSupplierBO quotationsupplierBO) {
		this.quotationsupplierBO = quotationsupplierBO;
	}

	@ManagedProperty("#{expensesBO}")
	private IExpensesBO expensesBO;

	public IExpensesBO getExpensesBO() {
		return expensesBO;
	}

	public void setExpensesBO(IExpensesBO expensesBO) {
		this.expensesBO = expensesBO;
	}

	@ManagedProperty("#{membershipBO}")
	private IMembershipBO membershipBO;

	public IMembershipBO getMembershipBO() {
		return membershipBO;
	}

	public void setMembershipBO(IMembershipBO membershipBO) {
		this.membershipBO = membershipBO;
	}

	@ManagedProperty("#{itemRemarksBO}")
	private IItemRemarksBO itemRemarksBO;

	public IItemRemarksBO getItemRemarksBO() {
		return itemRemarksBO;
	}

	public void setItemRemarksBO(IItemRemarksBO itemRemarksBO) {
		this.itemRemarksBO = itemRemarksBO;
	}

	@ManagedProperty("#{systemconfigurationBO}")
	private ISystemconfigurationBO systemconfigurationBO;

	public ISystemconfigurationBO getSystemconfigurationBO() {
		return systemconfigurationBO;
	}

	public void setSystemconfigurationBO(ISystemconfigurationBO systemconfigurationBO) {
		this.systemconfigurationBO = systemconfigurationBO;
	}

	@ManagedProperty("#{discountBO}")
	private IDiscountBO discountBO;

	public IDiscountBO getDiscountBO() {
		return discountBO;
	}

	public void setDiscountBO(IDiscountBO discountBO) {
		this.discountBO = discountBO;
	}

	@ManagedProperty("#{shiftBO}")
	private IShiftBO shiftBO;

	public IShiftBO getShiftBO() {
		return shiftBO;
	}

	public void setShiftBO(IShiftBO shiftBO) {
		this.shiftBO = shiftBO;
	}

	@ManagedProperty("#{gstaccounBO}")
	private IGstaccounBO gstaccounBO;

	public IGstaccounBO getGstaccounBO() {
		return gstaccounBO;
	}

	public void setGstaccounBO(IGstaccounBO gstaccounBO) {
		this.gstaccounBO = gstaccounBO;
	}

}
