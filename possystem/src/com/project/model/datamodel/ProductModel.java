package com.project.model.datamodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

import com.project.model.datamodel.stock.ProductbatchModel;

public class ProductModel {

	private int productId;
	private String barcode;
	private String baroldcode;
	private String createdBy;
	private Date createdDate;
	private String description;
	private BigDecimal discount;
	private Date expiryDate;
	private String height;
	private Date lastModifiedDate;
	private String length;
	private String productCode;
	private String productOldCode;
	private String productName;
	private String brandName;
	private String productDesc;
	private String productOldName;
	private BigDecimal quantityonHand;
	private String reorderPoint;
	private int reorderQuantity;
	private String status;
	private String weight;
	private String width;
	private int supplierId;
	private String supplierName;
	private int categoryId;
	private String categoryName;
	private String uom;
	private BigDecimal normalPrice;
	private BigDecimal purchasePrice;
	private BigDecimal packingPrice;
	private BigDecimal doctorPrice;
	private BigDecimal salesrepPrice;
	private int quantityonalert;
	private String batchNo;
	private BigDecimal supplierpurchasePrice;
	private int fullredemptionpoint;
	private int halfredemptionpoint;
	private BigDecimal redemAmount;
	private int productBranchLinkId;
	private Integer branchId;
	private String prefixData;
	private BigDecimal taxCode;
	private BigDecimal taxAmount;
	private BigDecimal gstCode;
	private BigDecimal gstAmount;
	private String taxType;
	private int customerPoint;
	private String salesType;
	private BigDecimal quantity;
	private String remarks;
	private String imageDirectory;
	private UploadedFile image;
	private int isCustomeList;
	
	private Boolean online;
	private boolean setItem;
	private int tempSetId;
	

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	List<ProductsupplierModel> supplierlist = new ArrayList<ProductsupplierModel>();
	List<ProductpriceModel> priceList = new ArrayList<ProductpriceModel>();
	List<ProductbatchModel> productbatchList = new ArrayList<ProductbatchModel>();
	private List<ProductmaterailModel> bomlist = new ArrayList<ProductmaterailModel>();
	List<ProductbundleModel> productbundleList = new ArrayList<ProductbundleModel>();
	List<ProductsetModel> productSetList=new ArrayList<ProductsetModel>();

	
	public String getImageDirectory() {
		
		return imageDirectory;
		
	}

	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public BigDecimal getQuantityonHand() {
		return quantityonHand;
	}

	public void setQuantityonHand(BigDecimal quantityonHand) {
		this.quantityonHand = quantityonHand;
	}

	public String getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(String reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public int getReorderQuantity() {
		return reorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public BigDecimal getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getPackingPrice() {
		return packingPrice;
	}

	public void setPackingPrice(BigDecimal packingPrice) {
		this.packingPrice = packingPrice;
	}

	public String getProductOldCode() {
		return productOldCode;
	}

	public void setProductOldCode(String productOldCode) {
		this.productOldCode = productOldCode;
	}

	public String getProductOldName() {
		return productOldName;
	}

	public void setProductOldName(String productOldName) {
		this.productOldName = productOldName;
	}

	public String getBaroldcode() {
		return baroldcode;
	}

	public void setBaroldcode(String baroldcode) {
		this.baroldcode = baroldcode;
	}

	public List<ProductsupplierModel> getSupplierlist() {
		return supplierlist;
	}

	public void setSupplierlist(List<ProductsupplierModel> supplierlist) {
		this.supplierlist = supplierlist;
	}

	public List<ProductpriceModel> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<ProductpriceModel> priceList) {
		this.priceList = priceList;
	}

	public BigDecimal getDoctorPrice() {
		return doctorPrice;
	}

	public void setDoctorPrice(BigDecimal doctorPrice) {
		this.doctorPrice = doctorPrice;
	}

	public BigDecimal getSalesrepPrice() {
		return salesrepPrice;
	}

	public void setSalesrepPrice(BigDecimal salesrepPrice) {
		this.salesrepPrice = salesrepPrice;
	}

	public int getQuantityonalert() {
		return quantityonalert;
	}

	public void setQuantityonalert(int quantityonalert) {
		this.quantityonalert = quantityonalert;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public List<ProductbatchModel> getProductbatchList() {
		return productbatchList;
	}

	public void setProductbatchList(List<ProductbatchModel> productbatchList) {
		this.productbatchList = productbatchList;
	}

	public BigDecimal getSupplierpurchasePrice() {
		return supplierpurchasePrice;
	}

	public void setSupplierpurchasePrice(BigDecimal supplierpurchasePrice) {
		this.supplierpurchasePrice = supplierpurchasePrice;
	}

	public List<ProductbundleModel> getProductbundleList() {
		return productbundleList;
	}

	public void setProductbundleList(List<ProductbundleModel> productbundleList) {
		this.productbundleList = productbundleList;
	}

	public List<ProductsetModel> getProductSetList() {
		return productSetList;
	}

	public void setProductSetList(List<ProductsetModel> productSetList) {
		this.productSetList = productSetList;
	}

	public int getFullredemptionpoint() {
		return fullredemptionpoint;
	}

	public void setFullredemptionpoint(int fullredemptionpoint) {
		this.fullredemptionpoint = fullredemptionpoint;
	}

	public int getHalfredemptionpoint() {
		return halfredemptionpoint;
	}

	public void setHalfredemptionpoint(int halfredemptionpoint) {
		this.halfredemptionpoint = halfredemptionpoint;
	}

	public BigDecimal getRedemAmount() {
		return redemAmount;
	}

	public void setRedemAmount(BigDecimal redemAmount) {
		this.redemAmount = redemAmount;
	}

	public int getProductBranchLinkId() {
		return productBranchLinkId;
	}

	public void setProductBranchLinkId(int productBranchLinkId) {
		this.productBranchLinkId = productBranchLinkId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getPrefixData() {
		return prefixData;
	}

	public void setPrefixData(String prefixData) {
		this.prefixData = prefixData;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getGstCode() {
		return gstCode;
	}

	public void setGstCode(BigDecimal gstCode) {
		this.gstCode = gstCode;
	}

	public BigDecimal getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}

	public int getCustomerPoint() {
		return customerPoint;
	}

	public void setCustomerPoint(int customerPoint) {
		this.customerPoint = customerPoint;
	}	
	
	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public List<ProductmaterailModel> getBomlist() {
		return bomlist;
	}

	public void setBomlist(List<ProductmaterailModel> bomlist) {
		this.bomlist = bomlist;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public int getIsCustomeList() {
		return isCustomeList;
	}

	public void setIsCustomeList(int isCustomeList) {
		this.isCustomeList = isCustomeList;
	}
/*	public void setIsSetItem(boolean isSetItem) {
		this.isSetItem = isSetItem;
	}*/

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public boolean isSetItem() {
		return setItem;
	}

	public void setSetItem(boolean setItem) {
		this.setItem = setItem;
	}

	public int getTempSetId() {
		return tempSetId;
	}

	public void setTempSetId(int tempSetId) {
		this.tempSetId = tempSetId;
	}


	
}
