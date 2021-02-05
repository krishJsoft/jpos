package com.project.model.invoice.customer;

import java.math.BigDecimal;
import java.util.Date;

public class InvoicedispatchModel {

	private int invoiceDispatchesId;

	private String consignmentNo;

	private String courierCompany;

	private String createdBy;

	private Date createdDate;

	private String deliveryPerson;

	private BigDecimal despatchAmount;

	private Date dispatchDate;

	private String dispatchNo;

	private String invoiceNo;

	private String recipientName;

	private String remarks;

	private String status;

	private String transportType;

	private String type;

	private String vehicleNo;
	
	private Integer dispatch;
	

	public int getInvoiceDispatchesId() {
		return invoiceDispatchesId;
	}

	public void setInvoiceDispatchesId(int invoiceDispatchesId) {
		this.invoiceDispatchesId = invoiceDispatchesId;
	}

	public String getConsignmentNo() {
		return consignmentNo;
	}

	public void setConsignmentNo(String consignmentNo) {
		this.consignmentNo = consignmentNo;
	}

	public String getCourierCompany() {
		return courierCompany;
	}

	public void setCourierCompany(String courierCompany) {
		this.courierCompany = courierCompany;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public BigDecimal getDespatchAmount() {
		return despatchAmount;
	}

	public void setDespatchAmount(BigDecimal despatchAmount) {
		this.despatchAmount = despatchAmount;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Integer getDispatch() {
		return dispatch;
	}

	public void setDispatch(Integer dispatch) {
		this.dispatch = dispatch;
	}

	
	
}
