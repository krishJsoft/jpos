package com.project.model.invoice.customer;

import java.math.BigDecimal;
import java.util.Date;

public class InvoicetaxshipModel {

	private int invoiceTaxShipId;

	private String city;

	private String country;

	private String invoiceNo;

	private String postCode;

	private String pricingCurrency;

	private String recipient;

	private String recipientAddress;

	private Date requireShipDate;

	private String state;

	private BigDecimal taxingScheme;

	private String address;

	public int getInvoiceTaxShipId() {
		return invoiceTaxShipId;
	}

	public void setInvoiceTaxShipId(int invoiceTaxShipId) {
		this.invoiceTaxShipId = invoiceTaxShipId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPricingCurrency() {
		return pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public Date getRequireShipDate() {
		return requireShipDate;
	}

	public void setRequireShipDate(Date requireShipDate) {
		this.requireShipDate = requireShipDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getTaxingScheme() {
		return taxingScheme;
	}

	public void setTaxingScheme(BigDecimal taxingScheme) {
		this.taxingScheme = taxingScheme;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
