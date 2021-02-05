package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the branchsales database table.
 * 
 */
@Entity
@Table(name="branchsales")
public class Branchsale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int branchsalesId;

	private int balanceQuantityCount;

	@Column(length=45)
	private String createdBy;
	
	@Column(length=45)
	private String salesNo;
	
	@Column(length=45)
	private String deliveryOrderNo;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(nullable=false, length=145)
	private String deliveryTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifedDate;

	@Column(length=145)
	private String paymentTerms;

	@Column(length=400)
	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	private Date salesDate;

	private int soldQuantityCount;

	@Column(length=1)
	private String status;
	
	@Column(length=1)
	private String branchStatus;
	

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	private int totalItemCount;

	@Column(precision=10, scale=2)
	private BigDecimal totalTax;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchId")
	private Branch branch1;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch2;

	//bi-directional many-to-one association to Branchsalesbreakdown
	@OneToMany(mappedBy="branchsale", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Branchsalesbreakdown> branchsalesbreakdowns;

	public Branchsale() {
	}

	public int getBranchsalesId() {
		return this.branchsalesId;
	}

	public void setBranchsalesId(int branchsalesId) {
		this.branchsalesId = branchsalesId;
	}

	public int getBalanceQuantityCount() {
		return this.balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
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

	public String getDeliveryTerms() {
		return this.deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return this.lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSalesDate() {
		return this.salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public int getSoldQuantityCount() {
		return this.soldQuantityCount;
	}

	public void setSoldQuantityCount(int soldQuantityCount) {
		this.soldQuantityCount = soldQuantityCount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalItemCount() {
		return this.totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public BigDecimal getTotalTax() {
		return this.totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
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

	public List<Branchsalesbreakdown> getBranchsalesbreakdowns() {
		return this.branchsalesbreakdowns;
	}

	public void setBranchsalesbreakdowns(List<Branchsalesbreakdown> branchsalesbreakdowns) {
		this.branchsalesbreakdowns = branchsalesbreakdowns;
	}

	public Branchsalesbreakdown addBranchsalesbreakdown(Branchsalesbreakdown branchsalesbreakdown) {
		getBranchsalesbreakdowns().add(branchsalesbreakdown);
		branchsalesbreakdown.setBranchsale(this);

		return branchsalesbreakdown;
	}

	public Branchsalesbreakdown removeBranchsalesbreakdown(Branchsalesbreakdown branchsalesbreakdown) {
		getBranchsalesbreakdowns().remove(branchsalesbreakdown);
		branchsalesbreakdown.setBranchsale(null);

		return branchsalesbreakdown;
	}

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
	}
	
	
	

}