package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the gstaccount database table.
 * 
 */
@Entity
@Table(name="gstaccount")
public class Gstaccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int accounttaxid;

	@Column(precision=10, scale=2)
	private BigDecimal balancepaid;

	@Column(precision=10, scale=2)
	private BigDecimal balancepay;

	@Temporal(TemporalType.TIMESTAMP)
	private Date genaratedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taxinvoiceperiod;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taxinvoiceperiodto;
	
	@Column(precision=10, scale=2)
	private BigDecimal totalpurchasetax;

	@Column(precision=10, scale=2)
	private BigDecimal totalsalestax;
	
	@Column(length = 1)
	private String status;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Gstpurchaseaccountbreakdown
	@OneToMany(mappedBy="gstaccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Gstpurchaseaccountbreakdown> gstpurchaseaccountbreakdowns;

	//bi-directional many-to-one association to Gstsalesaccountbreakdown
	@OneToMany(mappedBy="gstaccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Gstsalesaccountbreakdown> gstsalesaccountbreakdowns;

	public Gstaccount() {
	}

	public int getAccounttaxid() {
		return this.accounttaxid;
	}

	public void setAccounttaxid(int accounttaxid) {
		this.accounttaxid = accounttaxid;
	}

	public BigDecimal getBalancepaid() {
		return this.balancepaid;
	}

	public void setBalancepaid(BigDecimal balancepaid) {
		this.balancepaid = balancepaid;
	}

	public BigDecimal getBalancepay() {
		return this.balancepay;
	}

	public void setBalancepay(BigDecimal balancepay) {
		this.balancepay = balancepay;
	}

	public Date getGenaratedDate() {
		return this.genaratedDate;
	}

	public void setGenaratedDate(Date genaratedDate) {
		this.genaratedDate = genaratedDate;
	}

	public Date getTaxinvoiceperiod() {
		return this.taxinvoiceperiod;
	}

	public void setTaxinvoiceperiod(Date taxinvoiceperiod) {
		this.taxinvoiceperiod = taxinvoiceperiod;
	}

	public BigDecimal getTotalpurchasetax() {
		return this.totalpurchasetax;
	}

	public void setTotalpurchasetax(BigDecimal totalpurchasetax) {
		this.totalpurchasetax = totalpurchasetax;
	}

	public BigDecimal getTotalsalestax() {
		return this.totalsalestax;
	}

	public void setTotalsalestax(BigDecimal totalsalestax) {
		this.totalsalestax = totalsalestax;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Gstpurchaseaccountbreakdown> getGstpurchaseaccountbreakdowns() {
		return this.gstpurchaseaccountbreakdowns;
	}

	public void setGstpurchaseaccountbreakdowns(List<Gstpurchaseaccountbreakdown> gstpurchaseaccountbreakdowns) {
		this.gstpurchaseaccountbreakdowns = gstpurchaseaccountbreakdowns;
	}

	public Gstpurchaseaccountbreakdown addGstpurchaseaccountbreakdown(Gstpurchaseaccountbreakdown gstpurchaseaccountbreakdown) {
		getGstpurchaseaccountbreakdowns().add(gstpurchaseaccountbreakdown);
		gstpurchaseaccountbreakdown.setGstaccount(this);

		return gstpurchaseaccountbreakdown;
	}

	public Gstpurchaseaccountbreakdown removeGstpurchaseaccountbreakdown(Gstpurchaseaccountbreakdown gstpurchaseaccountbreakdown) {
		getGstpurchaseaccountbreakdowns().remove(gstpurchaseaccountbreakdown);
		gstpurchaseaccountbreakdown.setGstaccount(null);

		return gstpurchaseaccountbreakdown;
	}

	public List<Gstsalesaccountbreakdown> getGstsalesaccountbreakdowns() {
		return this.gstsalesaccountbreakdowns;
	}

	public void setGstsalesaccountbreakdowns(List<Gstsalesaccountbreakdown> gstsalesaccountbreakdowns) {
		this.gstsalesaccountbreakdowns = gstsalesaccountbreakdowns;
	}

	public Gstsalesaccountbreakdown addGstsalesaccountbreakdown(Gstsalesaccountbreakdown gstsalesaccountbreakdown) {
		getGstsalesaccountbreakdowns().add(gstsalesaccountbreakdown);
		gstsalesaccountbreakdown.setGstaccount(this);

		return gstsalesaccountbreakdown;
	}

	public Gstsalesaccountbreakdown removeGstsalesaccountbreakdown(Gstsalesaccountbreakdown gstsalesaccountbreakdown) {
		getGstsalesaccountbreakdowns().remove(gstsalesaccountbreakdown);
		gstsalesaccountbreakdown.setGstaccount(null);

		return gstsalesaccountbreakdown;
	}

	public Date getTaxinvoiceperiodto() {
		return taxinvoiceperiodto;
	}

	public void setTaxinvoiceperiodto(Date taxinvoiceperiodto) {
		this.taxinvoiceperiodto = taxinvoiceperiodto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}