package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int productId;

	@Column(length = 45)
	private String barcode;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length = 45)
	private String description;

	@Column(precision = 10, scale = 2)
	private BigDecimal discount;

	@Column(length = 15)
	private String height;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length = 15)
	private String length;

	@Column(length = 45)
	private String productCode;
	@Lob
	private String productDesc;

	@Column(length = 100)
	private String productName;

	@Column(length = 100)
	private String brandName;

	@Column(length = 45)
	private String reorderPoint;

	@Column(nullable = false)
	private int reorderQuantity;

	@Column(length = 45)
	private String status;

	@Column(length = 15)
	private String uom;

	@Column(length = 15)
	private String weight;

	@Column(length = 15)
	private String width;
	
	@Column(length = 15)
	private String salesType;
	
	@Column(length = 100)
	private String imageDirectory;
	
	@Column(length =1)
	private int isCustomList;
	
	@Column(nullable=false)
	private Boolean online;
	
	@Column(nullable=false)
	private Boolean setItem;
	

	// bi-directional many-to-one association to Batchmomenthistry
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Batchmomenthistry> batchmomenthistries;

	// bi-directional many-to-one association to Branchinvoicebreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Branchinvoicebreakdown> branchinvoicebreakdowns;

	// bi-directional many-to-one association to Customerinvoicebreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Customerinvoicebreakdown> customerinvoicebreakdowns;

	// bi-directional many-to-one association to Deliveryorderbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deliveryorderbreakdown> deliveryorderbreakdowns;

	// bi-directional many-to-one association to Doctorprescriptionbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Doctorprescriptionbreakdown> doctorprescriptionbreakdowns;

	// bi-directional many-to-one association to Supplier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SupplierId")
	private Supplier supplier;

	// bi-directional many-to-one association to Productcategory
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryId")
	private Productcategory productcategory;

	// bi-directional many-to-one association to Productbatch
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productbatch> productbatches;

	// bi-directional many-to-one association to Productbranchlink
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productbranchlink> productbranchlinks;

	// bi-directional many-to-one association to Productprice
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productprice> productprices;

	// bi-directional many-to-one association to Productsupplier
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productsupplier> productsuppliers;

	// bi-directional many-to-one association to Purchaseorderbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchaseorderbreakdown> purchaseorderbreakdowns;

	// bi-directional many-to-one association to Purchaserequestbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchaserequestbreakdown> purchaserequestbreakdowns;

	// bi-directional many-to-one association to Quotationbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Quotationbreakdown> quotationbreakdowns;

	// bi-directional many-to-one association to Salesdiscounthistry
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Salesdiscounthistry> salesdiscounthistries;

	// bi-directional many-to-one association to Salesorderbreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Salesorderbreakdown> salesorderbreakdowns;

	// bi-directional many-to-one association to Supplierinvoicebreakdown
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Supplierinvoicebreakdown> supplierinvoicebreakdowns;

	// bi-directional many-to-one association to Loyaltyredemptionbreakdown
	@OneToMany(mappedBy = "product")
	private List<Loyaltyredemptionbreakdown> loyaltyredemptionbreakdowns;

	// bi-directional many-to-one association to Productbundle
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productbundle> productbundles;
	
	// bi-directional many-to-one association to Productbundle
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Productsetlist> productsetlist;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReorderPoint() {
		return this.reorderPoint;
	}

	public void setReorderPoint(String reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public int getReorderQuantity() {
		return this.reorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}	

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public List<Batchmomenthistry> getBatchmomenthistries() {
		return this.batchmomenthistries;
	}

	public void setBatchmomenthistries(
			List<Batchmomenthistry> batchmomenthistries) {
		this.batchmomenthistries = batchmomenthistries;
	}

	public Batchmomenthistry addBatchmomenthistry(
			Batchmomenthistry batchmomenthistry) {
		getBatchmomenthistries().add(batchmomenthistry);
		batchmomenthistry.setProduct(this);

		return batchmomenthistry;
	}

	public Batchmomenthistry removeBatchmomenthistry(
			Batchmomenthistry batchmomenthistry) {
		getBatchmomenthistries().remove(batchmomenthistry);
		batchmomenthistry.setProduct(null);

		return batchmomenthistry;
	}

	public List<Branchinvoicebreakdown> getBranchinvoicebreakdowns() {
		return this.branchinvoicebreakdowns;
	}

	public void setBranchinvoicebreakdowns(
			List<Branchinvoicebreakdown> branchinvoicebreakdowns) {
		this.branchinvoicebreakdowns = branchinvoicebreakdowns;
	}

	public Branchinvoicebreakdown addBranchinvoicebreakdown(
			Branchinvoicebreakdown branchinvoicebreakdown) {
		getBranchinvoicebreakdowns().add(branchinvoicebreakdown);
		branchinvoicebreakdown.setProduct(this);

		return branchinvoicebreakdown;
	}

	public Branchinvoicebreakdown removeBranchinvoicebreakdown(
			Branchinvoicebreakdown branchinvoicebreakdown) {
		getBranchinvoicebreakdowns().remove(branchinvoicebreakdown);
		branchinvoicebreakdown.setProduct(null);

		return branchinvoicebreakdown;
	}

	public List<Customerinvoicebreakdown> getCustomerinvoicebreakdowns() {
		return this.customerinvoicebreakdowns;
	}

	public void setCustomerinvoicebreakdowns(
			List<Customerinvoicebreakdown> customerinvoicebreakdowns) {
		this.customerinvoicebreakdowns = customerinvoicebreakdowns;
	}

	public Customerinvoicebreakdown addCustomerinvoicebreakdown(
			Customerinvoicebreakdown customerinvoicebreakdown) {
		getCustomerinvoicebreakdowns().add(customerinvoicebreakdown);
		customerinvoicebreakdown.setProduct(this);

		return customerinvoicebreakdown;
	}

	public Customerinvoicebreakdown removeCustomerinvoicebreakdown(
			Customerinvoicebreakdown customerinvoicebreakdown) {
		getCustomerinvoicebreakdowns().remove(customerinvoicebreakdown);
		customerinvoicebreakdown.setProduct(null);

		return customerinvoicebreakdown;
	}

	public List<Deliveryorderbreakdown> getDeliveryorderbreakdowns() {
		return this.deliveryorderbreakdowns;
	}

	public void setDeliveryorderbreakdowns(
			List<Deliveryorderbreakdown> deliveryorderbreakdowns) {
		this.deliveryorderbreakdowns = deliveryorderbreakdowns;
	}

	public Deliveryorderbreakdown addDeliveryorderbreakdown(
			Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().add(deliveryorderbreakdown);
		deliveryorderbreakdown.setProduct(this);

		return deliveryorderbreakdown;
	}

	public Deliveryorderbreakdown removeDeliveryorderbreakdown(
			Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().remove(deliveryorderbreakdown);
		deliveryorderbreakdown.setProduct(null);

		return deliveryorderbreakdown;
	}

	public List<Doctorprescriptionbreakdown> getDoctorprescriptionbreakdowns() {
		return this.doctorprescriptionbreakdowns;
	}

	public void setDoctorprescriptionbreakdowns(
			List<Doctorprescriptionbreakdown> doctorprescriptionbreakdowns) {
		this.doctorprescriptionbreakdowns = doctorprescriptionbreakdowns;
	}

	public Doctorprescriptionbreakdown addDoctorprescriptionbreakdown(
			Doctorprescriptionbreakdown doctorprescriptionbreakdown) {
		getDoctorprescriptionbreakdowns().add(doctorprescriptionbreakdown);
		doctorprescriptionbreakdown.setProduct(this);

		return doctorprescriptionbreakdown;
	}

	public Doctorprescriptionbreakdown removeDoctorprescriptionbreakdown(
			Doctorprescriptionbreakdown doctorprescriptionbreakdown) {
		getDoctorprescriptionbreakdowns().remove(doctorprescriptionbreakdown);
		doctorprescriptionbreakdown.setProduct(null);

		return doctorprescriptionbreakdown;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Productcategory getProductcategory() {
		return this.productcategory;
	}

	public void setProductcategory(Productcategory productcategory) {
		this.productcategory = productcategory;
	}

	public List<Productbatch> getProductbatches() {
		return this.productbatches;
	}

	public void setProductbatches(List<Productbatch> productbatches) {
		this.productbatches = productbatches;
	}

	public Productbatch addProductbatch(Productbatch productbatch) {
		getProductbatches().add(productbatch);
		productbatch.setProduct(this);

		return productbatch;
	}

	public Productbatch removeProductbatch(Productbatch productbatch) {
		getProductbatches().remove(productbatch);
		productbatch.setProduct(null);

		return productbatch;
	}

	public List<Productbranchlink> getProductbranchlinks() {
		return this.productbranchlinks;
	}

	public void setProductbranchlinks(List<Productbranchlink> productbranchlinks) {
		this.productbranchlinks = productbranchlinks;
	}

	public List<Productprice> getProductprices() {
		return this.productprices;
	}

	public void setProductprices(List<Productprice> productprices) {
		this.productprices = productprices;
	}

	public Productprice addProductprice(Productprice productprice) {
		getProductprices().add(productprice);
		productprice.setProduct(this);

		return productprice;
	}

	public Productprice removeProductprice(Productprice productprice) {
		getProductprices().remove(productprice);
		productprice.setProduct(null);

		return productprice;
	}

	public List<Productsupplier> getProductsuppliers() {
		return this.productsuppliers;
	}

	public void setProductsuppliers(List<Productsupplier> productsuppliers) {
		this.productsuppliers = productsuppliers;
	}

	public Productsupplier addProductsupplier(Productsupplier productsupplier) {
		getProductsuppliers().add(productsupplier);
		productsupplier.setProduct(this);

		return productsupplier;
	}

	public Productsupplier removeProductsupplier(Productsupplier productsupplier) {
		getProductsuppliers().remove(productsupplier);
		productsupplier.setProduct(null);

		return productsupplier;
	}

	public List<Purchaseorderbreakdown> getPurchaseorderbreakdowns() {
		return this.purchaseorderbreakdowns;
	}

	public void setPurchaseorderbreakdowns(
			List<Purchaseorderbreakdown> purchaseorderbreakdowns) {
		this.purchaseorderbreakdowns = purchaseorderbreakdowns;
	}

	public Purchaseorderbreakdown addPurchaseorderbreakdown(
			Purchaseorderbreakdown purchaseorderbreakdown) {
		getPurchaseorderbreakdowns().add(purchaseorderbreakdown);
		purchaseorderbreakdown.setProduct(this);

		return purchaseorderbreakdown;
	}

	public Purchaseorderbreakdown removePurchaseorderbreakdown(
			Purchaseorderbreakdown purchaseorderbreakdown) {
		getPurchaseorderbreakdowns().remove(purchaseorderbreakdown);
		purchaseorderbreakdown.setProduct(null);

		return purchaseorderbreakdown;
	}

	public List<Purchaserequestbreakdown> getPurchaserequestbreakdowns() {
		return this.purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(
			List<Purchaserequestbreakdown> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}

	public Purchaserequestbreakdown addPurchaserequestbreakdown(
			Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().add(purchaserequestbreakdown);
		purchaserequestbreakdown.setProduct(this);

		return purchaserequestbreakdown;
	}

	public Purchaserequestbreakdown removePurchaserequestbreakdown(
			Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().remove(purchaserequestbreakdown);
		purchaserequestbreakdown.setProduct(null);

		return purchaserequestbreakdown;
	}

	public List<Quotationbreakdown> getQuotationbreakdowns() {
		return this.quotationbreakdowns;
	}

	public void setQuotationbreakdowns(
			List<Quotationbreakdown> quotationbreakdowns) {
		this.quotationbreakdowns = quotationbreakdowns;
	}

	public Quotationbreakdown addQuotationbreakdown(
			Quotationbreakdown quotationbreakdown) {
		getQuotationbreakdowns().add(quotationbreakdown);
		quotationbreakdown.setProduct(this);

		return quotationbreakdown;
	}

	public Quotationbreakdown removeQuotationbreakdown(
			Quotationbreakdown quotationbreakdown) {
		getQuotationbreakdowns().remove(quotationbreakdown);
		quotationbreakdown.setProduct(null);

		return quotationbreakdown;
	}

	public List<Salesdiscounthistry> getSalesdiscounthistries() {
		return this.salesdiscounthistries;
	}

	public void setSalesdiscounthistries(
			List<Salesdiscounthistry> salesdiscounthistries) {
		this.salesdiscounthistries = salesdiscounthistries;
	}

	public Salesdiscounthistry addSalesdiscounthistry(
			Salesdiscounthistry salesdiscounthistry) {
		getSalesdiscounthistries().add(salesdiscounthistry);
		salesdiscounthistry.setProduct(this);

		return salesdiscounthistry;
	}

	public Salesdiscounthistry removeSalesdiscounthistry(
			Salesdiscounthistry salesdiscounthistry) {
		getSalesdiscounthistries().remove(salesdiscounthistry);
		salesdiscounthistry.setProduct(null);

		return salesdiscounthistry;
	}

	public List<Salesorderbreakdown> getSalesorderbreakdowns() {
		return this.salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<Salesorderbreakdown> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public Salesorderbreakdown addSalesorderbreakdown(
			Salesorderbreakdown salesorderbreakdown) {
		getSalesorderbreakdowns().add(salesorderbreakdown);
		salesorderbreakdown.setProduct(this);

		return salesorderbreakdown;
	}

	public Salesorderbreakdown removeSalesorderbreakdown(
			Salesorderbreakdown salesorderbreakdown) {
		getSalesorderbreakdowns().remove(salesorderbreakdown);
		salesorderbreakdown.setProduct(null);

		return salesorderbreakdown;
	}

	public List<Supplierinvoicebreakdown> getSupplierinvoicebreakdowns() {
		return this.supplierinvoicebreakdowns;
	}

	public void setSupplierinvoicebreakdowns(
			List<Supplierinvoicebreakdown> supplierinvoicebreakdowns) {
		this.supplierinvoicebreakdowns = supplierinvoicebreakdowns;
	}

	public Supplierinvoicebreakdown addSupplierinvoicebreakdown(
			Supplierinvoicebreakdown supplierinvoicebreakdown) {
		getSupplierinvoicebreakdowns().add(supplierinvoicebreakdown);
		supplierinvoicebreakdown.setProduct(this);

		return supplierinvoicebreakdown;
	}

	public Supplierinvoicebreakdown removeSupplierinvoicebreakdown(
			Supplierinvoicebreakdown supplierinvoicebreakdown) {
		getSupplierinvoicebreakdowns().remove(supplierinvoicebreakdown);
		supplierinvoicebreakdown.setProduct(null);

		return supplierinvoicebreakdown;
	}

	public List<Loyaltyredemptionbreakdown> getLoyaltyredemptionbreakdowns() {
		return loyaltyredemptionbreakdowns;
	}

	public void setLoyaltyredemptionbreakdowns(
			List<Loyaltyredemptionbreakdown> loyaltyredemptionbreakdowns) {
		this.loyaltyredemptionbreakdowns = loyaltyredemptionbreakdowns;
	}

	public List<Productbundle> getProductbundles() {
		return productbundles;
	}

	public void setProductbundles(List<Productbundle> productbundles) {
		this.productbundles = productbundles;
	}

	public List<Productsetlist> getProductsetlist() {
		return productsetlist;
	}

	public void setProductsetlist(List<Productsetlist> productsetlist) {
		this.productsetlist = productsetlist;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getImageDirectory() {
		return imageDirectory;
	}

	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}

	public int getIsCustomList() {
		return isCustomList;
	}

	public void setIsCustomList(int isCustomList) {
		this.isCustomList = isCustomList;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean getSetItem() {
		return setItem;
	}

	public void setSetItem(Boolean setItem) {
		this.setItem = setItem;
	}

}