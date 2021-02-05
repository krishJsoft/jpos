package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paymentcollections database table.
 * 
 */
@Entity
@Table(name="paymentcollections")
public class Paymentcollection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int collectionId;

	@Column(length=45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(precision=10, scale=2)
	private BigDecimal clearAmount;

	@Column(precision=10, scale=2)
	private BigDecimal collectionAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date collectionDate;

	@Column(length=1)
	private String collectionType;

	@Column(length=500)
	private String comments;

	@Column(length=25)
	private String paymentMode;

	@Column(length=45)
	private String paymentNo;

	@Column(length=45)
	private String processedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date processedDate;

	@Column(length=45)
	private String referenceNumber;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal unclearAmount;

	//bi-directional many-to-one association to Paymentcollectionapportion
	@OneToMany(mappedBy="paymentcollection", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Paymentcollectionapportion> paymentcollectionapportions;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch1;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch2;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerId")
	private Customer customer;

	public Paymentcollection() {
	}

	public int getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public BigDecimal getClearAmount() {
		return this.clearAmount;
	}

	public void setClearAmount(BigDecimal clearAmount) {
		this.clearAmount = clearAmount;
	}

	public BigDecimal getCollectionAmount() {
		return this.collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public Date getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getCollectionType() {
		return this.collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentNo() {
		return this.paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getProcessedBy() {
		return this.processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedDate() {
		return this.processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUnclearAmount() {
		return this.unclearAmount;
	}

	public void setUnclearAmount(BigDecimal unclearAmount) {
		this.unclearAmount = unclearAmount;
	}

	public List<Paymentcollectionapportion> getPaymentcollectionapportions() {
		return this.paymentcollectionapportions;
	}

	public void setPaymentcollectionapportions(List<Paymentcollectionapportion> paymentcollectionapportions) {
		this.paymentcollectionapportions = paymentcollectionapportions;
	}

	public Paymentcollectionapportion addPaymentcollectionapportion(Paymentcollectionapportion paymentcollectionapportion) {
		getPaymentcollectionapportions().add(paymentcollectionapportion);
		paymentcollectionapportion.setPaymentcollection(this);

		return paymentcollectionapportion;
	}

	public Paymentcollectionapportion removePaymentcollectionapportion(Paymentcollectionapportion paymentcollectionapportion) {
		getPaymentcollectionapportions().remove(paymentcollectionapportion);
		paymentcollectionapportion.setPaymentcollection(null);

		return paymentcollectionapportion;
	}

	public Branch getBranch1() {
		return this.branch1;
	}

	public void setBranch1(Branch branch1) {
		this.branch1 = branch1;
	}

	public Branch getBranch2() {
		return this.branch2;
	}

	public void setBranch2(Branch branch2) {
		this.branch2 = branch2;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}