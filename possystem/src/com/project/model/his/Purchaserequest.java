package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the purchaserequest database table.
 * 
 */
@Entity
@Table(name="purchaserequest")
public class Purchaserequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int purchaseRequestId;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;

	@Column(length=45)
	private String deliveryType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length=45)
	private String lastPurchaseOrderNo;

	@Column(length=45)
	private String purchaseType;

	@Column(nullable=false)
	private int quantityRequired;

	@Column(length=245)
	private String reason;
	
	@Column(length = 1)
	private String branchtype;
	
	@Column(length = 1)
	private String branchstatus;


	@Column(length=45)
	private String referenceNo;

	@Column(length=245)
	private String remarks;

	@Column(length=1)
	private String status;
	
	@Column(length=1)
	private String branchview;
	
	

	@Column(length=245)
	private String supportingFile;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Purchaserequestbreakdown
	@OneToMany(mappedBy="purchaserequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchaserequestbreakdown> purchaserequestbreakdowns;

	public Purchaserequest() {
	}

	public int getPurchaseRequestId() {
		return this.purchaseRequestId;
	}

	public void setPurchaseRequestId(int purchaseRequestId) {
		this.purchaseRequestId = purchaseRequestId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastPurchaseOrderNo() {
		return this.lastPurchaseOrderNo;
	}

	public void setLastPurchaseOrderNo(String lastPurchaseOrderNo) {
		this.lastPurchaseOrderNo = lastPurchaseOrderNo;
	}

	public String getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public int getQuantityRequired() {
		return this.quantityRequired;
	}

	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupportingFile() {
		return this.supportingFile;
	}

	public void setSupportingFile(String supportingFile) {
		this.supportingFile = supportingFile;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Purchaserequestbreakdown> getPurchaserequestbreakdowns() {
		return this.purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(List<Purchaserequestbreakdown> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}

	public Purchaserequestbreakdown addPurchaserequestbreakdown(Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().add(purchaserequestbreakdown);
		purchaserequestbreakdown.setPurchaserequest(this);

		return purchaserequestbreakdown;
	}

	public Purchaserequestbreakdown removePurchaserequestbreakdown(Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().remove(purchaserequestbreakdown);
		purchaserequestbreakdown.setPurchaserequest(null);

		return purchaserequestbreakdown;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public String getBranchstatus() {
		return branchstatus;
	}

	public void setBranchstatus(String branchstatus) {
		this.branchstatus = branchstatus;
	}

	public String getBranchview() {
		return branchview;
	}

	public void setBranchview(String branchview) {
		this.branchview = branchview;
	}
	
	

}