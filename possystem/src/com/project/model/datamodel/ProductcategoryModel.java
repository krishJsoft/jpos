package com.project.model.datamodel;

import java.util.List;

public class ProductcategoryModel {

	private int categoryId;
	private String code;
	private String description;
	private String name;
	private String oldname;
	private int level;
	private String status;
	private int parentCategoryId;
	private String parentCategoryName;
	
	private Boolean online;
	private int syncStatus;
	
	ProductcategoryModel productcategory;
	List<ProductcategoryModel> productcategories;
	
	KitchenprinterlistModal printer = new KitchenprinterlistModal();

	BranchModel branch = new BranchModel();
	
	public KitchenprinterlistModal getPrinter() {
		return printer;
	}

	public void setPrinter(KitchenprinterlistModal printer) {
		this.printer = printer;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public 	ProductcategoryModel()
	{
		
	}
	
    public 	ProductcategoryModel(int categoryId, String code,String description,String name, String oldname,int level, String status, int parentCategoryId, String parentCategoryName)
    {
    	this.categoryId=categoryId;
    	this.code=code;
    	this.description=description;
    	this.name=name;
    	this.oldname=oldname;
    	this.level=level;
    	this.status=status;
    	this.parentCategoryId=parentCategoryId;
    	this.parentCategoryName=parentCategoryName;
    }

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
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

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ProductcategoryModel getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(ProductcategoryModel productcategory) {
		this.productcategory = productcategory;
	}

	public List<ProductcategoryModel> getProductcategories() {
		return productcategories;
	}

	public void setProductcategories(
			List<ProductcategoryModel> productcategories) {
		this.productcategories = productcategories;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	
	@Override
    public String toString() {
        return getName();
    }

}
