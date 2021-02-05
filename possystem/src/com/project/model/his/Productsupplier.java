package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the productsupplier database table.
 * 
 */
@Entity
@Table(name="productsupplier")
public class Productsupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int productsupplierId;

	@Column(precision = 10, scale = 2)
	private BigDecimal purchasePrice;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierId")
	private Supplier supplier;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;

	public Productsupplier() {
	}

	public int getProductsupplierId() {
		return this.productsupplierId;
	}

	public void setProductsupplierId(int productsupplierId) {
		this.productsupplierId = productsupplierId;
	}

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}