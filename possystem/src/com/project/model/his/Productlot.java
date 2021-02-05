package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the productlot database table.
 * 
 */
@Entity
@Table(name="productlot")
public class Productlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int productLotId;

	@Column(length=15)
	private String GRNNo;

	@Column(length=15)
	private String lotNo;

	@Column(length=15)
	private String purchaseOrderNo;

	private int quantityAfter;

	private int quantityBefore;

	@Column(length=50)
	private String receivedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedDate;

	private int receivedQuantity;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Deliveryorderbreakdown
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DeliveryOrderBreakdownId")
	private Deliveryorderbreakdown deliveryorderbreakdown;

	public Productlot() {
	}

	public int getProductLotId() {
		return this.productLotId;
	}

	public void setProductLotId(int productLotId) {
		this.productLotId = productLotId;
	}

	public String getGRNNo() {
		return this.GRNNo;
	}

	public void setGRNNo(String GRNNo) {
		this.GRNNo = GRNNo;
	}

	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getPurchaseOrderNo() {
		return this.purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public int getQuantityAfter() {
		return this.quantityAfter;
	}

	public void setQuantityAfter(int quantityAfter) {
		this.quantityAfter = quantityAfter;
	}

	public int getQuantityBefore() {
		return this.quantityBefore;
	}

	public void setQuantityBefore(int quantityBefore) {
		this.quantityBefore = quantityBefore;
	}

	public String getReceivedBy() {
		return this.receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Date getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public int getReceivedQuantity() {
		return this.receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Deliveryorderbreakdown getDeliveryorderbreakdown() {
		return this.deliveryorderbreakdown;
	}

	public void setDeliveryorderbreakdown(Deliveryorderbreakdown deliveryorderbreakdown) {
		this.deliveryorderbreakdown = deliveryorderbreakdown;
	}

}