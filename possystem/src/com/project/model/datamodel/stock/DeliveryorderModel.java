package com.project.model.datamodel.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.purchase.PurchaserequestBranchModel;

public class DeliveryorderModel {

	private int deliveryOrderId;

	private String createdBy;

	private Date createdDate;

	private String deliveryOrderNo;

	private String deliveryOrderOldNo;

	private Date lastModifiedDate;

	private Date purchaseOrderDate;

	private String purchaseOrderNo;

	private String remarks;

	private String status;
	private String invoiceNo;

	private BigDecimal totalAmount;

	private String companyName;

	private int supplierId;

	private int branchId;

	private String branchName;

	private int totalItemCount;

	private int itemCount;

	private int SoldQuantityCount;

	private int balanceQuantityCount;

	private String salesstatus;

	private String invoicestatus;

	private String branchtype;
	
	private String deliveryType;

	private List<DeliveryorderbreakdownModel> deliveryorderbreakdowns;

	private List<PurchaserequestBranchModel> branchModel;

	List<ProductbatchModel> productBatchList = new ArrayList<ProductbatchModel>();

	BranchModel branch = new BranchModel();

	SupplierModel supplier = new SupplierModel();
	
	BranchModel supplybranch = new BranchModel();

	private int branchRecordId;

	public int getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(int deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public List<DeliveryorderbreakdownModel> getDeliveryorderbreakdowns() {
		return deliveryorderbreakdowns;
	}

	public void setDeliveryorderbreakdowns(
			List<DeliveryorderbreakdownModel> deliveryorderbreakdowns) {
		this.deliveryorderbreakdowns = deliveryorderbreakdowns;
	}

	public List<PurchaserequestBranchModel> getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(List<PurchaserequestBranchModel> branchModel) {
		this.branchModel = branchModel;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getSoldQuantityCount() {
		return SoldQuantityCount;
	}

	public void setSoldQuantityCount(int soldQuantityCount) {
		SoldQuantityCount = soldQuantityCount;
	}

	public int getBalanceQuantityCount() {
		return balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public String getDeliveryOrderOldNo() {
		return deliveryOrderOldNo;
	}

	public void setDeliveryOrderOldNo(String deliveryOrderOldNo) {
		this.deliveryOrderOldNo = deliveryOrderOldNo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public List<ProductbatchModel> getProductBatchList() {
		return productBatchList;
	}

	public void setProductBatchList(List<ProductbatchModel> productBatchList) {
		this.productBatchList = productBatchList;
	}

	public String getSalesstatus() {
		return salesstatus;
	}

	public void setSalesstatus(String salesstatus) {
		this.salesstatus = salesstatus;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public int getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(int branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public BranchModel getSupplybranch() {
		return supplybranch;
	}

	public void setSupplybranch(BranchModel supplybranch) {
		this.supplybranch = supplybranch;
	}
	
	

}
