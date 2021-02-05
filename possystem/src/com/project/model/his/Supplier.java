package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the supplier database table.
 * 
 */
@Entity
@Table(name="supplier")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int supplierId;

	@Column(length=45)
	private String accountNumber;

	@Column(length=145)
	private String address;

	@Column(length=45)
	private String bankName;

	@Column(length=45)
	private String branchName;

	@Column(length=45)
	private String city;

	@Column(length=145)
	private String companyName;

	@Column(length=45)
	private String companyRegNo;

	@Column(length=45)
	private String contactPerson;

	@Column(length=15)
	private String contactPersonNumber;

	@Column(length=45)
	private String country;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=45)
	private String currency;

	@Column(length=45)
	private String deliveryMethod;

	@Column(length=60)
	private String email;
	
	@Column(length=15)
	private String password;

	@Column(length=15)
	private String faxNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length=15)
	private String mobileNo;

	@Column(length=45)
	private String paymentTerms;

	@Column(length=15)
	private String phoneNo;

	@Column(length=15)
	private String postCode;

	@Column(length=245)
	private String remarks;

	@Column(length=45)
	private String state;

	@Column(length=1)
	private String status;

	@Column(length=6)
	private String supplierCode;

	@Column(length=45)
	private String supplierName;

	@Column(length=245)
	private String supportingFileName;

	@Column(length=45)
	private String taxSchedule;

	@Column(length=55)
	private String websiteName;
	
	@Column(length=45)
	private String themeName;
	
	@Column(nullable=false)
	private int invalidAttempts;
	
	@Column(length=1)
	private String forceReset;


	//bi-directional many-to-one association to Deliveryorder
	@OneToMany(mappedBy="supplier")
	private List<Deliveryorder> deliveryorders;

	//bi-directional many-to-one association to Paymentsettlement
	@OneToMany(mappedBy="supplier")
	private List<Paymentsettlement> paymentsettlements;

	//bi-directional many-to-one association to Productsupplier
	@OneToMany(mappedBy="supplier")
	private List<Productsupplier> productsuppliers;

	//bi-directional many-to-one association to Purchaseorder
	@OneToMany(mappedBy="supplier")
	private List<Purchaseorder> purchaseorders;

	//bi-directional many-to-one association to Purchaserequestbreakdown
	@OneToMany(mappedBy="supplier")
	private List<Purchaserequestbreakdown> purchaserequestbreakdowns;

	//bi-directional many-to-one association to Salesreturn
	@OneToMany(mappedBy="supplier")
	private List<Salesreturn> salesreturns;

	//bi-directional many-to-one association to Supplierdocument
	@OneToMany(mappedBy="supplier")
	private List<Supplierdocument> supplierdocuments;

	//bi-directional many-to-one association to Supplierinvoice
	@OneToMany(mappedBy="supplier")
	private List<Supplierinvoice> supplierinvoices;

	public Supplier() {
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyRegNo() {
		return this.companyRegNo;
	}

	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonNumber() {
		return this.contactPersonNumber;
	}

	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDeliveryMethod() {
		return this.deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNo() {
		return this.faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupplierCode() {
		return this.supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupportingFileName() {
		return this.supportingFileName;
	}

	public void setSupportingFileName(String supportingFileName) {
		this.supportingFileName = supportingFileName;
	}

	public String getTaxSchedule() {
		return this.taxSchedule;
	}

	public void setTaxSchedule(String taxSchedule) {
		this.taxSchedule = taxSchedule;
	}

	public String getWebsiteName() {
		return this.websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public List<Deliveryorder> getDeliveryorders() {
		return this.deliveryorders;
	}

	public void setDeliveryorders(List<Deliveryorder> deliveryorders) {
		this.deliveryorders = deliveryorders;
	}

	public Deliveryorder addDeliveryorder(Deliveryorder deliveryorder) {
		getDeliveryorders().add(deliveryorder);
		deliveryorder.setSupplier(this);

		return deliveryorder;
	}

	public Deliveryorder removeDeliveryorder(Deliveryorder deliveryorder) {
		getDeliveryorders().remove(deliveryorder);
		deliveryorder.setSupplier(null);

		return deliveryorder;
	}

	public List<Paymentsettlement> getPaymentsettlements() {
		return this.paymentsettlements;
	}

	public void setPaymentsettlements(List<Paymentsettlement> paymentsettlements) {
		this.paymentsettlements = paymentsettlements;
	}

	public Paymentsettlement addPaymentsettlement(Paymentsettlement paymentsettlement) {
		getPaymentsettlements().add(paymentsettlement);
		paymentsettlement.setSupplier(this);

		return paymentsettlement;
	}

	public Paymentsettlement removePaymentsettlement(Paymentsettlement paymentsettlement) {
		getPaymentsettlements().remove(paymentsettlement);
		paymentsettlement.setSupplier(null);

		return paymentsettlement;
	}

	public List<Productsupplier> getProductsuppliers() {
		return this.productsuppliers;
	}

	public void setProductsuppliers(List<Productsupplier> productsuppliers) {
		this.productsuppliers = productsuppliers;
	}

	public Productsupplier addProductsupplier(Productsupplier productsupplier) {
		getProductsuppliers().add(productsupplier);
		productsupplier.setSupplier(this);

		return productsupplier;
	}

	public Productsupplier removeProductsupplier(Productsupplier productsupplier) {
		getProductsuppliers().remove(productsupplier);
		productsupplier.setSupplier(null);

		return productsupplier;
	}

	public List<Purchaseorder> getPurchaseorders() {
		return this.purchaseorders;
	}

	public void setPurchaseorders(List<Purchaseorder> purchaseorders) {
		this.purchaseorders = purchaseorders;
	}

	public Purchaseorder addPurchaseorder(Purchaseorder purchaseorder) {
		getPurchaseorders().add(purchaseorder);
		purchaseorder.setSupplier(this);

		return purchaseorder;
	}

	public Purchaseorder removePurchaseorder(Purchaseorder purchaseorder) {
		getPurchaseorders().remove(purchaseorder);
		purchaseorder.setSupplier(null);

		return purchaseorder;
	}

	public List<Purchaserequestbreakdown> getPurchaserequestbreakdowns() {
		return this.purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(List<Purchaserequestbreakdown> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}

	public Purchaserequestbreakdown addPurchaserequestbreakdown(Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().add(purchaserequestbreakdown);
		purchaserequestbreakdown.setSupplier(this);

		return purchaserequestbreakdown;
	}

	public Purchaserequestbreakdown removePurchaserequestbreakdown(Purchaserequestbreakdown purchaserequestbreakdown) {
		getPurchaserequestbreakdowns().remove(purchaserequestbreakdown);
		purchaserequestbreakdown.setSupplier(null);

		return purchaserequestbreakdown;
	}

	public List<Salesreturn> getSalesreturns() {
		return this.salesreturns;
	}

	public void setSalesreturns(List<Salesreturn> salesreturns) {
		this.salesreturns = salesreturns;
	}

	public Salesreturn addSalesreturn(Salesreturn salesreturn) {
		getSalesreturns().add(salesreturn);
		salesreturn.setSupplier(this);

		return salesreturn;
	}

	public Salesreturn removeSalesreturn(Salesreturn salesreturn) {
		getSalesreturns().remove(salesreturn);
		salesreturn.setSupplier(null);

		return salesreturn;
	}

	public List<Supplierdocument> getSupplierdocuments() {
		return this.supplierdocuments;
	}

	public void setSupplierdocuments(List<Supplierdocument> supplierdocuments) {
		this.supplierdocuments = supplierdocuments;
	}

	public Supplierdocument addSupplierdocument(Supplierdocument supplierdocument) {
		getSupplierdocuments().add(supplierdocument);
		supplierdocument.setSupplier(this);

		return supplierdocument;
	}

	public Supplierdocument removeSupplierdocument(Supplierdocument supplierdocument) {
		getSupplierdocuments().remove(supplierdocument);
		supplierdocument.setSupplier(null);

		return supplierdocument;
	}

	public List<Supplierinvoice> getSupplierinvoices() {
		return this.supplierinvoices;
	}

	public void setSupplierinvoices(List<Supplierinvoice> supplierinvoices) {
		this.supplierinvoices = supplierinvoices;
	}

	public Supplierinvoice addSupplierinvoice(Supplierinvoice supplierinvoice) {
		getSupplierinvoices().add(supplierinvoice);
		supplierinvoice.setSupplier(this);

		return supplierinvoice;
	}

	public Supplierinvoice removeSupplierinvoice(Supplierinvoice supplierinvoice) {
		getSupplierinvoices().remove(supplierinvoice);
		supplierinvoice.setSupplier(null);

		return supplierinvoice;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public int getInvalidAttempts() {
		return invalidAttempts;
	}

	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}

	public String getForceReset() {
		return forceReset;
	}

	public void setForceReset(String forceReset) {
		this.forceReset = forceReset;
	}
	
	

}