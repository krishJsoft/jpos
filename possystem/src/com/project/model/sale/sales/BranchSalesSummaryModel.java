package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;

public class BranchSalesSummaryModel {

	private String branchName;
	private Integer branchId;
	private Date dateFrom;
	private Date dateTo;
	
	private String customerName;
	private Integer customerId;
    private String loyalityCode;
	private Integer totalQuantity;
	private BigDecimal normalPriceTotal;
	private BigDecimal purchasePriceTotal;
	private BigDecimal marginTotal;
	private BigDecimal margin;
	private Integer totalCount;
	private String supplierName;
	private Integer supplierId;
	private String address;
	private String phoneNo;
	private String postCode;
	private String email;

	List<BranchSalesModel> product = new ArrayList<BranchSalesModel>();
	BranchModel branch = new BranchModel();
	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<BranchSalesModel> getProduct() {
		return product;
	}

	public void setProduct(List<BranchSalesModel> product) {
		this.product = product;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getNormalPriceTotal() {
		return normalPriceTotal;
	}

	public void setNormalPriceTotal(BigDecimal normalPriceTotal) {
		this.normalPriceTotal = normalPriceTotal;
	}

	public BigDecimal getPurchasePriceTotal() {
		return purchasePriceTotal;
	}

	public void setPurchasePriceTotal(BigDecimal purchasePriceTotal) {
		this.purchasePriceTotal = purchasePriceTotal;
	}

	public BigDecimal getMarginTotal() {
		return marginTotal;
	}

	public void setMarginTotal(BigDecimal marginTotal) {
		this.marginTotal = marginTotal;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getLoyalityCode() {
		return loyalityCode;
	}

	public void setLoyalityCode(String loyalityCode) {
		this.loyalityCode = loyalityCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
