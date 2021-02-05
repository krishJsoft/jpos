package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the loyaltyredemption database table.
 * 
 */
@Entity
@Table(name="loyaltyredemption")
public class Loyaltyredemption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int redemptionId;

	@Column(length=50)
	private String approvedBy;

	@Temporal(TemporalType.DATE)
	private Date approvedDate;

	@Column(length=50)
	private String createdBy;

	@Column(length=25)
	private String loyaltyCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date redemptionDate;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal totalredemAmount;

	private int totalredemPoint;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerId")
	private Customer customer;

	//bi-directional many-to-one association to Loyaltyredemptionbreakdown
	@OneToMany(mappedBy="loyaltyredemption",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Loyaltyredemptionbreakdown> loyaltyredemptionbreakdowns;

	public Loyaltyredemption() {
	}

	public int getRedemptionId() {
		return this.redemptionId;
	}

	public void setRedemptionId(int redemptionId) {
		this.redemptionId = redemptionId;
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

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLoyaltyCode() {
		return this.loyaltyCode;
	}

	public void setLoyaltyCode(String loyaltyCode) {
		this.loyaltyCode = loyaltyCode;
	}

	public Date getRedemptionDate() {
		return this.redemptionDate;
	}

	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalredemAmount() {
		return this.totalredemAmount;
	}

	public void setTotalredemAmount(BigDecimal totalredemAmount) {
		this.totalredemAmount = totalredemAmount;
	}

	public int getTotalredemPoint() {
		return this.totalredemPoint;
	}

	public void setTotalredemPoint(int totalredemPoint) {
		this.totalredemPoint = totalredemPoint;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Loyaltyredemptionbreakdown> getLoyaltyredemptionbreakdowns() {
		return this.loyaltyredemptionbreakdowns;
	}

	public void setLoyaltyredemptionbreakdowns(List<Loyaltyredemptionbreakdown> loyaltyredemptionbreakdowns) {
		this.loyaltyredemptionbreakdowns = loyaltyredemptionbreakdowns;
	}

	public Loyaltyredemptionbreakdown addLoyaltyredemptionbreakdown(Loyaltyredemptionbreakdown loyaltyredemptionbreakdown) {
		getLoyaltyredemptionbreakdowns().add(loyaltyredemptionbreakdown);
		loyaltyredemptionbreakdown.setLoyaltyredemption(this);

		return loyaltyredemptionbreakdown;
	}

	public Loyaltyredemptionbreakdown removeLoyaltyredemptionbreakdown(Loyaltyredemptionbreakdown loyaltyredemptionbreakdown) {
		getLoyaltyredemptionbreakdowns().remove(loyaltyredemptionbreakdown);
		loyaltyredemptionbreakdown.setLoyaltyredemption(null);

		return loyaltyredemptionbreakdown;
	}

}