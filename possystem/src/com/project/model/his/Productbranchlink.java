package com.project.model.his;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the productbranchlink database table.
 * 
 */
@Entity
@Table(name = "productbranchlink")
public class Productbranchlink implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int productBranchLinkId;

	@Column(length = 30)
	private String batchNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal doctorPrice;

	private int fullredemptionpoint;

	private int halfredemptionpoint;

	@Column(precision = 10, scale = 2)
	private BigDecimal normalPrice;

	@Column(precision = 3, scale = 2)
	private BigDecimal taxCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(precision = 3, scale = 2)
	private BigDecimal gstCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;
	
	private int customerPoint;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product product;

	@Column(precision = 10, scale = 2)
	private BigDecimal purchasePrice;

	private int quantityonalert;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantityonHand;

	@Column(precision = 10, scale = 2)
	private BigDecimal redemAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal salesrepPrice;

	@Column(length = 45)
	private String totalBalanceAtBranch;

	@Column(length = 45)
	private String totalIssuedQuantity;

	@Column(length = 45)
	private String totalOrderQuantity;
	
	@Column(length = 45)
	private String taxType;

	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal packingPrice;

	public Productbranchlink() {
	}

	public int getProductBranchLinkId() {
		return this.productBranchLinkId;
	}

	public void setProductBranchLinkId(int productBranchLinkId) {
		this.productBranchLinkId = productBranchLinkId;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getDoctorPrice() {
		return this.doctorPrice;
	}

	public void setDoctorPrice(BigDecimal doctorPrice) {
		this.doctorPrice = doctorPrice;
	}

	public int getFullredemptionpoint() {
		return this.fullredemptionpoint;
	}

	public void setFullredemptionpoint(int fullredemptionpoint) {
		this.fullredemptionpoint = fullredemptionpoint;
	}

	public int getHalfredemptionpoint() {
		return this.halfredemptionpoint;
	}

	public void setHalfredemptionpoint(int halfredemptionpoint) {
		this.halfredemptionpoint = halfredemptionpoint;
	}

	public BigDecimal getNormalPrice() {
		return this.normalPrice;
	}

	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getQuantityonalert() {
		return this.quantityonalert;
	}

	public void setQuantityonalert(int quantityonalert) {
		this.quantityonalert = quantityonalert;
	}


	public BigDecimal getQuantityonHand() {
		return quantityonHand;
	}

	public void setQuantityonHand(BigDecimal quantityonHand) {
		this.quantityonHand = quantityonHand;
	}

	public BigDecimal getRedemAmount() {
		return this.redemAmount;
	}

	public void setRedemAmount(BigDecimal redemAmount) {
		this.redemAmount = redemAmount;
	}

	public BigDecimal getSalesrepPrice() {
		return this.salesrepPrice;
	}

	public void setSalesrepPrice(BigDecimal salesrepPrice) {
		this.salesrepPrice = salesrepPrice;
	}

	public String getTotalBalanceAtBranch() {
		return this.totalBalanceAtBranch;
	}

	public void setTotalBalanceAtBranch(String totalBalanceAtBranch) {
		this.totalBalanceAtBranch = totalBalanceAtBranch;
	}

	public String getTotalIssuedQuantity() {
		return this.totalIssuedQuantity;
	}

	public void setTotalIssuedQuantity(String totalIssuedQuantity) {
		this.totalIssuedQuantity = totalIssuedQuantity;
	}

	public String getTotalOrderQuantity() {
		return this.totalOrderQuantity;
	}

	public void setTotalOrderQuantity(String totalOrderQuantity) {
		this.totalOrderQuantity = totalOrderQuantity;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getGstCode() {
		return gstCode;
	}

	public void setGstCode(BigDecimal gstCode) {
		this.gstCode = gstCode;
	}

	public BigDecimal getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}

	public int getCustomerPoint() {
		return customerPoint;
	}

	public void setCustomerPoint(int customerPoint) {
		this.customerPoint = customerPoint;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public BigDecimal getPackingPrice() {
		return packingPrice;
	}

	public void setPackingPrice(BigDecimal packingPrice) {
		this.packingPrice = packingPrice;
	}

	
	
}