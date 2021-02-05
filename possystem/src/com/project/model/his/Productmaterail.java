package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the Productmaterail database table.
 * 
 */

@Entity
@Table(name = "productmaterials")
public class Productmaterail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int bomId;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "childproductId")
	private Product productchild;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentproductId")
	private Product productparent;

	@Column(length = 15)
	private String status;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantity;

	public Productmaterail() {
	}

	public Product getProductchild() {
		return productchild;
	}

	public void setProductchild(Product productchild) {
		this.productchild = productchild;
	}

	public Product getProductparent() {
		return productparent;
	}

	public void setProductparent(Product productparent) {
		this.productparent = productparent;
	}

	
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public int getBomId() {
		return bomId;
	}

	public void setBomId(int bomId) {
		this.bomId = bomId;
	}

}