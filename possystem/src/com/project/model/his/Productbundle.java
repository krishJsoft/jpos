package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the Productbundle database table.
 * 
 */
@Entity
@Table(name = "productbundle")
public class Productbundle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int productbundleId;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantity;

	// bi-directional many-to-one association to Supplier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId")
	private Branch branch;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "masterproductId")
	private Product productm;

	public Productbundle() {
	}

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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProductm() {
		return productm;
	}

	public void setProductm(Product productm) {
		this.productm = productm;
	}

}