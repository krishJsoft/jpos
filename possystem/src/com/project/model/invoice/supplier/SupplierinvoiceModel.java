package com.project.model.invoice.supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.invoice.customer.InvoicedispatchModel;
import com.project.model.invoice.customer.InvoicetaxshipModel;

public class SupplierinvoiceModel {

	private int supplierInvoiceId;

	private String approvedBy;

	private Date approvedDate;

	private String branchName;

	private String createdBy;

	private Date createdDate;

	private Date deliveryOrderDate;

	private String deliveryOrderNo;

	private BigDecimal dispatchAmount;

	private BigDecimal invoiceAmount;

	private BigDecimal paidAmount;

	private BigDecimal totalAmount;

	private BigDecimal pendingAmount;

	private String invoiceNo;

	private String invoiceOldNo;

	private Date invoiceDate;

	private Date purchaseOrderDate;

	private String purchaseOrderNo;

	private String status;

	private String invoicetype;

	private BigDecimal tax;

	private String remarks;

	SupplierModel supplier = new SupplierModel();

	InvoicetaxshipModel invoicetaxship = new InvoicetaxshipModel();

	InvoicedispatchModel invoicedispatch = new InvoicedispatchModel();

	BranchModel branch = new BranchModel();

	BranchModel supplybranch = new BranchModel();

	List<SupplierinvoicebreakdownModel> supplierinvoicebreakdowns = new ArrayList<SupplierinvoicebreakdownModel>();

	Integer branchRecordId;

	public int getSupplierInvoiceId() {
		return supplierInvoiceId;
	}

	public void setSupplierInvoiceId(int supplierInvoiceId) {
		this.supplierInvoiceId = supplierInvoiceId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public Date getDeliveryOrderDate() {
		return deliveryOrderDate;
	}

	public void setDeliveryOrderDate(Date deliveryOrderDate) {
		this.deliveryOrderDate = deliveryOrderDate;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public BigDecimal getDispatchAmount() {
		return dispatchAmount;
	}

	public void setDispatchAmount(BigDecimal dispatchAmount) {
		this.dispatchAmount = dispatchAmount;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public InvoicetaxshipModel getInvoicetaxship() {
		return invoicetaxship;
	}

	public void setInvoicetaxship(InvoicetaxshipModel invoicetaxship) {
		this.invoicetaxship = invoicetaxship;
	}

	public InvoicedispatchModel getInvoicedispatch() {
		return invoicedispatch;
	}

	public void setInvoicedispatch(InvoicedispatchModel invoicedispatch) {
		this.invoicedispatch = invoicedispatch;
	}

	public List<SupplierinvoicebreakdownModel> getSupplierinvoicebreakdowns() {
		return supplierinvoicebreakdowns;
	}

	public void setSupplierinvoicebreakdowns(
			List<SupplierinvoicebreakdownModel> supplierinvoicebreakdowns) {
		this.supplierinvoicebreakdowns = supplierinvoicebreakdowns;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getInvoiceOldNo() {
		return invoiceOldNo;
	}

	public void setInvoiceOldNo(String invoiceOldNo) {
		this.invoiceOldNo = invoiceOldNo;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public BranchModel getSupplybranch() {
		return supplybranch;
	}

	public void setSupplybranch(BranchModel supplybranch) {
		this.supplybranch = supplybranch;
	}

}
