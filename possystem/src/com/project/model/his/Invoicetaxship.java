package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the invoicetaxship database table.
 * 
 */
@Entity
@Table(name="invoicetaxship")
public class Invoicetaxship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int invoiceTaxShipId;

	@Column(length=45)
	private String city;

	@Column(length=45)
	private String country;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=10)
	private String postCode;

	@Column(length=45)
	private String pricingCurrency;

	@Column(length=45)
	private String recipient;

	@Column(length=350)
	private String recipientAddress;

	@Temporal(TemporalType.TIMESTAMP)
	private Date requireShipDate;

	@Column(length=45)
	private String state;

	@Column(precision=10, scale=2)
	private BigDecimal taxingScheme;

	public Invoicetaxship() {
	}

	public int getInvoiceTaxShipId() {
		return this.invoiceTaxShipId;
	}

	public void setInvoiceTaxShipId(int invoiceTaxShipId) {
		this.invoiceTaxShipId = invoiceTaxShipId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPricingCurrency() {
		return this.pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientAddress() {
		return this.recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public Date getRequireShipDate() {
		return this.requireShipDate;
	}

	public void setRequireShipDate(Date requireShipDate) {
		this.requireShipDate = requireShipDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getTaxingScheme() {
		return this.taxingScheme;
	}

	public void setTaxingScheme(BigDecimal taxingScheme) {
		this.taxingScheme = taxingScheme;
	}

}