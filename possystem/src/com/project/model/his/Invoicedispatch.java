package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the invoicedispatches database table.
 * 
 */
@Entity
@Table(name="invoicedispatches")
public class Invoicedispatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int invoiceDispatchesId;

	@Column(length=45)
	private String consignmentNo;

	@Column(length=45)
	private String courierCompany;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=45)
	private String deliveryPerson;

	@Column(precision=10, scale=2)
	private BigDecimal despatchAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dispatchDate;

	@Column(length=45)
	private String dispatchNo;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=45)
	private String recipientName;

	@Column(length=245)
	private String remarks;

	@Column(length=1)
	private String status;

	@Column(length=45)
	private String transportType;

	@Column(length=45)
	private String type;

	@Column(length=45)
	private String vehicleNo;

	public Invoicedispatch() {
	}

	public int getInvoiceDispatchesId() {
		return this.invoiceDispatchesId;
	}

	public void setInvoiceDispatchesId(int invoiceDispatchesId) {
		this.invoiceDispatchesId = invoiceDispatchesId;
	}

	public String getConsignmentNo() {
		return this.consignmentNo;
	}

	public void setConsignmentNo(String consignmentNo) {
		this.consignmentNo = consignmentNo;
	}

	public String getCourierCompany() {
		return this.courierCompany;
	}

	public void setCourierCompany(String courierCompany) {
		this.courierCompany = courierCompany;
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

	public String getDeliveryPerson() {
		return this.deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public BigDecimal getDespatchAmount() {
		return this.despatchAmount;
	}

	public void setDespatchAmount(BigDecimal despatchAmount) {
		this.despatchAmount = despatchAmount;
	}

	public Date getDispatchDate() {
		return this.dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDispatchNo() {
		return this.dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getRecipientName() {
		return this.recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
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

	public String getTransportType() {
		return this.transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicleNo() {
		return this.vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

}