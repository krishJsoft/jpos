package com.project.model.datamodel;

import java.math.BigDecimal;

public class ProductbundleModel {

	private int productbundleId;

	private BigDecimal quantity;

	private BranchModel branch;

	private ProductModel product;

	public int getProductbundleId() {
		return productbundleId;
	}

	public void setProductbundleId(int productbundleId) {
		this.productbundleId = productbundleId;
	}	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

}
