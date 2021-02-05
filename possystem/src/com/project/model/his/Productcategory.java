package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productcategory database table.
 * 
 */
@Entity
@Table(name="productcategory")
public class Productcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int categoryId;

	@Column(length=45)
	private String code;

	@Column(length=500)
	private String description;

	@Column(nullable=false)
	private int level;

	@Column(length=45)
	private String name;

	
	
	
	private int parentCategoryId;
	
	@Column(nullable=false)
	private Boolean online;
	
	private int syncStatus;


	@Column(length=45)
	private String parentCategoryName;

	@Column(length=45)
	private String status;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productcategory")
	private List<Product> products;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "printerId")
	private Kitchenprinterlist printer;
	
	public Kitchenprinterlist getPrinter() {
		return printer;
	}

	public void setPrinter(Kitchenprinterlist printer) {
		this.printer = printer;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Productcategory() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentCategoryId() {
		return this.parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getParentCategoryName() {
		return this.parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductcategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductcategory(null);

		return product;
	}

}