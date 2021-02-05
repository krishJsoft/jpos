package com.project.model.datamodel;

public class ProductsetModel {
	
	private int setId;
	private int quantity;
	private int producSetId;
	private ProductModel product;
	
	public int getSetId() {
		return setId;
	}
	
	public void setSetId(int setId) {
		this.setId = setId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getProducSetId() {
		return producSetId;
	}
	
	public void setProducSetId(int producSetId) {
		this.producSetId = producSetId;
	}
	
	public ProductModel getProduct() {
		return product;
	}
	
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	

}
