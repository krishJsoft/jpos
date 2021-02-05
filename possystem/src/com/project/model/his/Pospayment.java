package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the pospayment database table.
 * 
 */
@Entity
@Table(name="pospayment")
public class Pospayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int pospaymentId;

	@Column(precision=10, scale=2)
	private BigDecimal amount;

	@Column(length=20)
	private String amountType;

	@Column(length=50)
	private String bankName;

	@Column(length=25)
	private String cardNo;

	@Column(length=25)
	private String cardType;

	@Column(length=25)
	private String referenceNo;

	@Temporal(TemporalType.DATE)
	private Date expDate;

	private int quantity;
	
	private Integer consumePoints;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Salesorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesOrderId")
	private Salesorder salesorder;

	public Pospayment() {
	}

	public int getPospaymentId() {
		return this.pospaymentId;
	}

	public void setPospaymentId(int pospaymentId) {
		this.pospaymentId = pospaymentId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAmountType() {
		return this.amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Salesorder getSalesorder() {
		return this.salesorder;
	}

	public void setSalesorder(Salesorder salesorder) {
		this.salesorder = salesorder;
	}

	public Integer getConsumePoints() {
		return consumePoints;
	}

	public void setConsumePoints(Integer consumePoints) {
		this.consumePoints = consumePoints;
	}
	
	

}