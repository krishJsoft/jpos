package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the autonum database table.
 * 
 */
@Entity
@Table(name = "autonum")
public class Autonum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	private int deliveryOrder;

	@Column(length = 5)
	private String deliveryOrderFormat;

	@Column(nullable = false)
	private int despatchNo;

	@Column(length = 5)
	private String despatchNoFormat;

	@Column(nullable = false)
	private int invoiceNo;

	@Column(length = 5)
	private String invoiceNoFormat;

	private int paymentNo;

	private int prescriptionNo;

	@Column(length = 5)
	private String prescriptionNoFormat;

	@Column(nullable = false)
	private int productCode;

	@Column(length = 5)
	private String productCodeFormat;

	private int purchaceOrder;

	@Column(length = 5)
	private String purchaceOrderFormat;

	@Column(nullable = false)
	private int purchaseRequest;

	@Column(length = 5)
	private String purchaseRequestFormat;

	private int quoteNo;

	@Column(length = 5)
	private String quoteNoFormat;

	private int salesOrder;

	@Column(length = 5)
	private String salesOrderFormat;

	@Column(length = 5)
	private String paymentFormat;

	private int collectionNo;

	@Column(length = 5)
	private String collectionFormat;
	
	@Column(length = 5)
	private String transferFormat;
	
	private int transferNo;
	
	@Column(nullable=false,name="takeAwayNo", columnDefinition="int(10) DEFAULT '500'")
	private int takeAwayNo;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	public Autonum() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeliveryOrder() {
		return this.deliveryOrder;
	}

	public void setDeliveryOrder(int deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public String getDeliveryOrderFormat() {
		return this.deliveryOrderFormat;
	}

	public void setDeliveryOrderFormat(String deliveryOrderFormat) {
		this.deliveryOrderFormat = deliveryOrderFormat;
	}

	public int getDespatchNo() {
		return this.despatchNo;
	}

	public void setDespatchNo(int despatchNo) {
		this.despatchNo = despatchNo;
	}

	public String getDespatchNoFormat() {
		return this.despatchNoFormat;
	}

	public void setDespatchNoFormat(String despatchNoFormat) {
		this.despatchNoFormat = despatchNoFormat;
	}

	public int getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNoFormat() {
		return this.invoiceNoFormat;
	}

	public void setInvoiceNoFormat(String invoiceNoFormat) {
		this.invoiceNoFormat = invoiceNoFormat;
	}

	public int getPaymentNo() {
		return this.paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getPrescriptionNo() {
		return this.prescriptionNo;
	}

	public void setPrescriptionNo(int prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public String getPrescriptionNoFormat() {
		return this.prescriptionNoFormat;
	}

	public void setPrescriptionNoFormat(String prescriptionNoFormat) {
		this.prescriptionNoFormat = prescriptionNoFormat;
	}

	public int getProductCode() {
		return this.productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductCodeFormat() {
		return this.productCodeFormat;
	}

	public void setProductCodeFormat(String productCodeFormat) {
		this.productCodeFormat = productCodeFormat;
	}

	public int getPurchaceOrder() {
		return this.purchaceOrder;
	}

	public void setPurchaceOrder(int purchaceOrder) {
		this.purchaceOrder = purchaceOrder;
	}

	public String getPurchaceOrderFormat() {
		return this.purchaceOrderFormat;
	}

	public void setPurchaceOrderFormat(String purchaceOrderFormat) {
		this.purchaceOrderFormat = purchaceOrderFormat;
	}

	public int getPurchaseRequest() {
		return this.purchaseRequest;
	}

	public void setPurchaseRequest(int purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}

	public String getPurchaseRequestFormat() {
		return this.purchaseRequestFormat;
	}

	public void setPurchaseRequestFormat(String purchaseRequestFormat) {
		this.purchaseRequestFormat = purchaseRequestFormat;
	}

	public int getQuoteNo() {
		return this.quoteNo;
	}

	public void setQuoteNo(int quoteNo) {
		this.quoteNo = quoteNo;
	}

	public String getQuoteNoFormat() {
		return this.quoteNoFormat;
	}

	public void setQuoteNoFormat(String quoteNoFormat) {
		this.quoteNoFormat = quoteNoFormat;
	}

	public int getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(int salesOrder) {
		this.salesOrder = salesOrder;
	}

	public String getSalesOrderFormat() {
		return this.salesOrderFormat;
	}

	public void setSalesOrderFormat(String salesOrderFormat) {
		this.salesOrderFormat = salesOrderFormat;
	}

	public String getPaymentFormat() {
		return paymentFormat;
	}

	public void setPaymentFormat(String paymentFormat) {
		this.paymentFormat = paymentFormat;
	}

	public int getCollectionNo() {
		return collectionNo;
	}

	public void setCollectionNo(int collectionNo) {
		this.collectionNo = collectionNo;
	}

	public String getCollectionFormat() {
		return collectionFormat;
	}

	public void setCollectionFormat(String collectionFormat) {
		this.collectionFormat = collectionFormat;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getTransferFormat() {
		return transferFormat;
	}

	public void setTransferFormat(String transferFormat) {
		this.transferFormat = transferFormat;
	}

	public int getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(int transferNo) {
		this.transferNo = transferNo;
	}

	public int getTakeAwayNo() {
		return takeAwayNo;
	}

	public void setTakeAwayNo(int takeAwayNo) {
		this.takeAwayNo = takeAwayNo;
	}
	
	

}