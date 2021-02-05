package com.project.model.datamodel;

import java.math.BigDecimal;


public class ProductmaterailModel {

	private int bomId;

	private ProductModel product;

	private ProductModel productchild;

	private ProductModel productparent;

	private String status;

	private BigDecimal quantity;

	public int getBomId() {
		return bomId;
	}

	public void setBomId(int bomId) {
		this.bomId = bomId;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public ProductModel getProductchild() {
		return productchild;
	}

	public void setProductchild(ProductModel productchild) {
		this.productchild = productchild;
	}

	public ProductModel getProductparent() {
		return productparent;
	}

	public void setProductparent(ProductModel productparent) {
		this.productparent = productparent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
