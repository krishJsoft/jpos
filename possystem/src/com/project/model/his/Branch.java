package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the branch database table.
 * 
 */
@Entity
@Table(name = "branch")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int branchId;

	@Column(length = 250)
	private String address;

	@Column(length = 15)
	private String branchCode;

	@Column(length = 245)
	private String branchName;

	@Column(length = 50)
	private String branchtype;

	@Column(length = 45)
	private String city;

	@Column(length = 100)
	private String contactPerson;

	@Column(length = 45)
	private String country;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length = 145)
	private String emailAddress;

	@Column(length = 15)
	private String faxNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal fixedLicenseFee;

	@Column(precision = 10, scale = 2)
	private BigDecimal franchiseeFee;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal totalPurchaseTax;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal totalSalesTax;
	

	@Column(length = 1)
	private String isHQ;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length = 25)
	private String phoneNo;

	@Column(length = 10)
	private String postCode;

	@Column(length = 45)
	private String state;

	@Column(length = 1)
	private String status;
	
	private int customersalesPoint;
	
	@Column(nullable = false)
	private int organizationId;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal customersalesValue;
	
	private int redemPoint;
	@Column(precision = 10, scale = 2)
	
	private BigDecimal redemValue;
	
	private int onlineBranchId;
	
	// bi-directional many-to-one association to Admindespatch
	@OneToMany(mappedBy = "branch")
	private List<Admindespatch> admindespatches;

	// bi-directional many-to-one association to Organization
	@OneToMany(mappedBy = "branch")
	private List<Autonum> autonums;

	// bi-directional many-to-one association to Productbranchlink
	@OneToMany(mappedBy = "branch")
	private List<Productbranchlink> productbranchlinks;

	// bi-directional many-to-one association to Batchmomenthistry
	@OneToMany(mappedBy = "branch")
	private List<Batchmomenthistry> batchmomenthistries;

	// bi-directional many-to-one association to Branchinvoice
	@OneToMany(mappedBy = "branch1")
	private List<Branchinvoice> branchinvoices1;

	// bi-directional many-to-one association to Branchinvoice
	@OneToMany(mappedBy = "branch2")
	private List<Branchinvoice> branchinvoices2;

	// bi-directional many-to-one association to Branchpayment
	@OneToMany(mappedBy = "branch")
	private List<Branchpayment> branchpayments;

	// bi-directional many-to-one association to Branchstaffmember
	@OneToMany(mappedBy = "branch")
	private List<Branchstaffmember> branchstaffmembers;

	// bi-directional many-to-one association to Commission
	@OneToMany(mappedBy = "branch")
	private List<Commission> commissions;

	// bi-directional many-to-one association to Customer
	@OneToMany(mappedBy = "branch")
	private List<Customer> customers;

	// bi-directional many-to-one association to Customerinvoice
	@OneToMany(mappedBy = "branch")
	private List<Customerinvoice> customerinvoices;

	// bi-directional many-to-one association to Customerpayment
	@OneToMany(mappedBy = "branch")
	private List<Customerpayment> customerpayments;

	// bi-directional many-to-one association to Deliveryorder
	@OneToMany(mappedBy = "branch1")
	private List<Deliveryorder> deliveryorders1;

	// bi-directional many-to-one association to Deliveryorder
	@OneToMany(mappedBy = "branch2")
	private List<Deliveryorder> deliveryorders2;

	// bi-directional many-to-one association to Department
	@OneToMany(mappedBy = "branch")
	private List<Department> departments;

	// bi-directional many-to-one association to Despatchesadmin
	@OneToMany(mappedBy = "branch")
	private List<Despatchesadmin> despatchesadmins;

	// bi-directional many-to-one association to Doctorclinic
	@OneToMany(mappedBy = "branch")
	private List<Doctorclinic> doctorclinics;

	// bi-directional many-to-one association to Doctorprescription
	@OneToMany(mappedBy = "branch")
	private List<Doctorprescription> doctorprescriptions;

	// bi-directional many-to-one association to Inventorymovement
	@OneToMany(mappedBy = "branch")
	private List<Inventorymovement> inventorymovements;

	// bi-directional many-to-one association to Loyaltyredemption
	@OneToMany(mappedBy = "branch")
	private List<Loyaltyredemption> loyaltyredemptions;

	// bi-directional many-to-one association to Organization
	@OneToMany(mappedBy = "branch")
	private List<Organization> organizations;

	// bi-directional many-to-one association to Paymentcollection
	@OneToMany(mappedBy = "branch1")
	private List<Paymentcollection> paymentcollections1;

	// bi-directional many-to-one association to Paymentcollection
	@OneToMany(mappedBy = "branch2")
	private List<Paymentcollection> paymentcollections2;

	// bi-directional many-to-one association to Paymentsettlement
	@OneToMany(mappedBy = "branch")
	private List<Paymentsettlement> paymentsettlements;

	// bi-directional many-to-one association to Paymentsettlement
	@OneToMany(mappedBy = "branch1")
	private List<Paymentsettlement> paymentsettlements1;

	// bi-directional many-to-one association to Poscashtransaction
	@OneToMany(mappedBy = "branch")
	private List<Poscashtransaction> poscashtransactions;

	// bi-directional many-to-one association to Poscounter
	@OneToMany(mappedBy = "branch")
	private List<Poscounter> poscounters;

	// bi-directional many-to-one association to Pospayment
	@OneToMany(mappedBy = "branch")
	private List<Pospayment> pospayments;

	// bi-directional many-to-one association to Productbatch
	@OneToMany(mappedBy = "branch")
	private List<Productbatch> productbatches;

	// bi-directional many-to-one association to Productprice
	@OneToMany(mappedBy = "branch")
	private List<Productprice> productprices;

	// bi-directional many-to-one association to Purchaseorder
	@OneToMany(mappedBy = "branch")
	private List<Purchaseorder> purchaseorders;

	// bi-directional many-to-one association to Purchaseorderbreakdown
	@OneToMany(mappedBy = "branch")
	private List<Purchaseorderbreakdown> purchaseorderbreakdowns;

	// bi-directional many-to-one association to Purchaseorderdeliveryaddress
	@OneToMany(mappedBy = "branch")
	private List<Purchaseorderdeliveryaddress> purchaseorderdeliveryaddresses;

	// bi-directional many-to-one association to Purchaserequest
	@OneToMany(mappedBy = "branch")
	private List<Purchaserequest> purchaserequests;

	// bi-directional many-to-one association to Quotation
	@OneToMany(mappedBy = "branch")
	private List<Quotation> quotations;

	// bi-directional many-to-one association to Role
	@OneToMany(mappedBy = "branch")
	private List<Role> roles;

	// bi-directional many-to-one association to Salesorder
	@OneToMany(mappedBy = "branch1")
	private List<Salesorder> salesorders1;

	// bi-directional many-to-one association to Salesorder
	@OneToMany(mappedBy = "branch2")
	private List<Salesorder> salesorders2;

	// bi-directional many-to-one association to Salesorderreturn
	@OneToMany(mappedBy = "branch")
	private List<Salesorderreturn> salesorderreturns;

	// bi-directional many-to-one association to Supplierinvoice
	@OneToMany(mappedBy = "branch")
	private List<Supplierinvoice> supplierinvoices;

	// bi-directional many-to-one association to Supplierinvoice
	@OneToMany(mappedBy = "branch1")
	private List<Supplierinvoice> supplierinvoices2;

	// bi-directional many-to-one association to Supplierpayment
	@OneToMany(mappedBy = "branch")
	private List<Supplierpayment> supplierpayments;

	// bi-directional many-to-one association to Productbranchlink
	@OneToMany(mappedBy = "branch")
	private List<Taxcode> taxcodes;

	// bi-directional many-to-one association to Branchsale
	@OneToMany(mappedBy = "branch1")
	private List<Branchsale> branchsales1;

	// bi-directional many-to-one association to Branchsale
	@OneToMany(mappedBy = "branch2")
	private List<Branchsale> branchsales2;

	public Branch() {
	}

	public int getBranchId() {
		return this.branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNo() {
		return this.faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public BigDecimal getFixedLicenseFee() {
		return this.fixedLicenseFee;
	}

	public void setFixedLicenseFee(BigDecimal fixedLicenseFee) {
		this.fixedLicenseFee = fixedLicenseFee;
	}

	public BigDecimal getFranchiseeFee() {
		return this.franchiseeFee;
	}

	public void setFranchiseeFee(BigDecimal franchiseeFee) {
		this.franchiseeFee = franchiseeFee;
	}

	public String getIsHQ() {
		return this.isHQ;
	}

	public void setIsHQ(String isHQ) {
		this.isHQ = isHQ;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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
	
	

	public int getCustomersalesPoint() {
		return customersalesPoint;
	}

	public void setCustomersalesPoint(int customersalesPoint) {
		this.customersalesPoint = customersalesPoint;
	}

	public BigDecimal getCustomersalesValue() {
		return customersalesValue;
	}

	public void setCustomersalesValue(BigDecimal customersalesValue) {
		this.customersalesValue = customersalesValue;
	}

	public int getRedemPoint() {
		return redemPoint;
	}

	public void setRedemPoint(int redemPoint) {
		this.redemPoint = redemPoint;
	}

	public BigDecimal getRedemValue() {
		return redemValue;
	}

	public void setRedemValue(BigDecimal redemValue) {
		this.redemValue = redemValue;
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

	public List<Admindespatch> getAdmindespatches() {
		return this.admindespatches;
	}

	public void setAdmindespatches(List<Admindespatch> admindespatches) {
		this.admindespatches = admindespatches;
	}

	public Admindespatch addAdmindespatch(Admindespatch admindespatch) {
		getAdmindespatches().add(admindespatch);
		admindespatch.setBranch(this);

		return admindespatch;
	}

	public Admindespatch removeAdmindespatch(Admindespatch admindespatch) {
		getAdmindespatches().remove(admindespatch);
		admindespatch.setBranch(null);

		return admindespatch;
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
		batchmomenthistry.setBranch(this);

		return batchmomenthistry;
	}

	public Batchmomenthistry removeBatchmomenthistry(
			Batchmomenthistry batchmomenthistry) {
		getBatchmomenthistries().remove(batchmomenthistry);
		batchmomenthistry.setBranch(null);

		return batchmomenthistry;
	}

	public List<Branchinvoice> getBranchinvoices1() {
		return this.branchinvoices1;
	}

	public void setBranchinvoices1(List<Branchinvoice> branchinvoices1) {
		this.branchinvoices1 = branchinvoices1;
	}

	public Branchinvoice addBranchinvoices1(Branchinvoice branchinvoices1) {
		getBranchinvoices1().add(branchinvoices1);
		branchinvoices1.setBranch1(this);

		return branchinvoices1;
	}

	public Branchinvoice removeBranchinvoices1(Branchinvoice branchinvoices1) {
		getBranchinvoices1().remove(branchinvoices1);
		branchinvoices1.setBranch1(null);

		return branchinvoices1;
	}

	public List<Branchinvoice> getBranchinvoices2() {
		return this.branchinvoices2;
	}

	public void setBranchinvoices2(List<Branchinvoice> branchinvoices2) {
		this.branchinvoices2 = branchinvoices2;
	}

	public Branchinvoice addBranchinvoices2(Branchinvoice branchinvoices2) {
		getBranchinvoices2().add(branchinvoices2);
		branchinvoices2.setBranch2(this);

		return branchinvoices2;
	}

	public Branchinvoice removeBranchinvoices2(Branchinvoice branchinvoices2) {
		getBranchinvoices2().remove(branchinvoices2);
		branchinvoices2.setBranch2(null);

		return branchinvoices2;
	}

	public List<Branchpayment> getBranchpayments() {
		return this.branchpayments;
	}

	public void setBranchpayments(List<Branchpayment> branchpayments) {
		this.branchpayments = branchpayments;
	}

	public Branchpayment addBranchpayment(Branchpayment branchpayment) {
		getBranchpayments().add(branchpayment);
		branchpayment.setBranch(this);

		return branchpayment;
	}

	public Branchpayment removeBranchpayment(Branchpayment branchpayment) {
		getBranchpayments().remove(branchpayment);
		branchpayment.setBranch(null);

		return branchpayment;
	}

	public List<Branchstaffmember> getBranchstaffmembers() {
		return this.branchstaffmembers;
	}

	public void setBranchstaffmembers(List<Branchstaffmember> branchstaffmembers) {
		this.branchstaffmembers = branchstaffmembers;
	}

	public Branchstaffmember addBranchstaffmember(
			Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().add(branchstaffmember);
		branchstaffmember.setBranch(this);

		return branchstaffmember;
	}

	public Branchstaffmember removeBranchstaffmember(
			Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().remove(branchstaffmember);
		branchstaffmember.setBranch(null);

		return branchstaffmember;
	}

	public List<Commission> getCommissions() {
		return this.commissions;
	}

	public void setCommissions(List<Commission> commissions) {
		this.commissions = commissions;
	}

	public Commission addCommission(Commission commission) {
		getCommissions().add(commission);
		commission.setBranch(this);

		return commission;
	}

	public Commission removeCommission(Commission commission) {
		getCommissions().remove(commission);
		commission.setBranch(null);

		return commission;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setBranch(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setBranch(null);

		return customer;
	}

	public List<Customerinvoice> getCustomerinvoices() {
		return this.customerinvoices;
	}

	public void setCustomerinvoices(List<Customerinvoice> customerinvoices) {
		this.customerinvoices = customerinvoices;
	}

	public Customerinvoice addCustomerinvoice(Customerinvoice customerinvoice) {
		getCustomerinvoices().add(customerinvoice);
		customerinvoice.setBranch(this);

		return customerinvoice;
	}

	public Customerinvoice removeCustomerinvoice(Customerinvoice customerinvoice) {
		getCustomerinvoices().remove(customerinvoice);
		customerinvoice.setBranch(null);

		return customerinvoice;
	}

	public List<Customerpayment> getCustomerpayments() {
		return this.customerpayments;
	}

	public void setCustomerpayments(List<Customerpayment> customerpayments) {
		this.customerpayments = customerpayments;
	}

	public Customerpayment addCustomerpayment(Customerpayment customerpayment) {
		getCustomerpayments().add(customerpayment);
		customerpayment.setBranch(this);

		return customerpayment;
	}

	public Customerpayment removeCustomerpayment(Customerpayment customerpayment) {
		getCustomerpayments().remove(customerpayment);
		customerpayment.setBranch(null);

		return customerpayment;
	}

	public List<Deliveryorder> getDeliveryorders1() {
		return this.deliveryorders1;
	}

	public void setDeliveryorders1(List<Deliveryorder> deliveryorders1) {
		this.deliveryorders1 = deliveryorders1;
	}

	public Deliveryorder addDeliveryorders1(Deliveryorder deliveryorders1) {
		getDeliveryorders1().add(deliveryorders1);
		deliveryorders1.setBranch1(this);

		return deliveryorders1;
	}

	public Deliveryorder removeDeliveryorders1(Deliveryorder deliveryorders1) {
		getDeliveryorders1().remove(deliveryorders1);
		deliveryorders1.setBranch1(null);

		return deliveryorders1;
	}

	public List<Deliveryorder> getDeliveryorders2() {
		return this.deliveryorders2;
	}

	public void setDeliveryorders2(List<Deliveryorder> deliveryorders2) {
		this.deliveryorders2 = deliveryorders2;
	}

	public Deliveryorder addDeliveryorders2(Deliveryorder deliveryorders2) {
		getDeliveryorders2().add(deliveryorders2);
		deliveryorders2.setBranch2(this);

		return deliveryorders2;
	}

	public Deliveryorder removeDeliveryorders2(Deliveryorder deliveryorders2) {
		getDeliveryorders2().remove(deliveryorders2);
		deliveryorders2.setBranch2(null);

		return deliveryorders2;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setBranch(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setBranch(null);

		return department;
	}

	public List<Despatchesadmin> getDespatchesadmins() {
		return this.despatchesadmins;
	}

	public void setDespatchesadmins(List<Despatchesadmin> despatchesadmins) {
		this.despatchesadmins = despatchesadmins;
	}

	public Despatchesadmin addDespatchesadmin(Despatchesadmin despatchesadmin) {
		getDespatchesadmins().add(despatchesadmin);
		despatchesadmin.setBranch(this);

		return despatchesadmin;
	}

	public Despatchesadmin removeDespatchesadmin(Despatchesadmin despatchesadmin) {
		getDespatchesadmins().remove(despatchesadmin);
		despatchesadmin.setBranch(null);

		return despatchesadmin;
	}

	public List<Doctorclinic> getDoctorclinics() {
		return this.doctorclinics;
	}

	public void setDoctorclinics(List<Doctorclinic> doctorclinics) {
		this.doctorclinics = doctorclinics;
	}

	public Doctorclinic addDoctorclinic(Doctorclinic doctorclinic) {
		getDoctorclinics().add(doctorclinic);
		doctorclinic.setBranch(this);

		return doctorclinic;
	}

	public Doctorclinic removeDoctorclinic(Doctorclinic doctorclinic) {
		getDoctorclinics().remove(doctorclinic);
		doctorclinic.setBranch(null);

		return doctorclinic;
	}

	public List<Doctorprescription> getDoctorprescriptions() {
		return this.doctorprescriptions;
	}

	public void setDoctorprescriptions(
			List<Doctorprescription> doctorprescriptions) {
		this.doctorprescriptions = doctorprescriptions;
	}

	public Doctorprescription addDoctorprescription(
			Doctorprescription doctorprescription) {
		getDoctorprescriptions().add(doctorprescription);
		doctorprescription.setBranch(this);

		return doctorprescription;
	}

	public Doctorprescription removeDoctorprescription(
			Doctorprescription doctorprescription) {
		getDoctorprescriptions().remove(doctorprescription);
		doctorprescription.setBranch(null);

		return doctorprescription;
	}

	public List<Inventorymovement> getInventorymovements() {
		return this.inventorymovements;
	}

	public void setInventorymovements(List<Inventorymovement> inventorymovements) {
		this.inventorymovements = inventorymovements;
	}

	public Inventorymovement addInventorymovement(
			Inventorymovement inventorymovement) {
		getInventorymovements().add(inventorymovement);
		inventorymovement.setBranch(this);

		return inventorymovement;
	}

	public Inventorymovement removeInventorymovement(
			Inventorymovement inventorymovement) {
		getInventorymovements().remove(inventorymovement);
		inventorymovement.setBranch(null);

		return inventorymovement;
	}

	public List<Loyaltyredemption> getLoyaltyredemptions() {
		return this.loyaltyredemptions;
	}

	public void setLoyaltyredemptions(List<Loyaltyredemption> loyaltyredemptions) {
		this.loyaltyredemptions = loyaltyredemptions;
	}

	public Loyaltyredemption addLoyaltyredemption(
			Loyaltyredemption loyaltyredemption) {
		getLoyaltyredemptions().add(loyaltyredemption);
		loyaltyredemption.setBranch(this);

		return loyaltyredemption;
	}

	public Loyaltyredemption removeLoyaltyredemption(
			Loyaltyredemption loyaltyredemption) {
		getLoyaltyredemptions().remove(loyaltyredemption);
		loyaltyredemption.setBranch(null);

		return loyaltyredemption;
	}

	public List<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public Organization addOrganization(Organization organization) {
		getOrganizations().add(organization);
		organization.setBranch(this);

		return organization;
	}

	public Organization removeOrganization(Organization organization) {
		getOrganizations().remove(organization);
		organization.setBranch(null);

		return organization;
	}

	public List<Paymentcollection> getPaymentcollections1() {
		return this.paymentcollections1;
	}

	public void setPaymentcollections1(
			List<Paymentcollection> paymentcollections1) {
		this.paymentcollections1 = paymentcollections1;
	}

	public Paymentcollection addPaymentcollections1(
			Paymentcollection paymentcollections1) {
		getPaymentcollections1().add(paymentcollections1);
		paymentcollections1.setBranch1(this);

		return paymentcollections1;
	}

	public Paymentcollection removePaymentcollections1(
			Paymentcollection paymentcollections1) {
		getPaymentcollections1().remove(paymentcollections1);
		paymentcollections1.setBranch1(null);

		return paymentcollections1;
	}

	public List<Paymentcollection> getPaymentcollections2() {
		return this.paymentcollections2;
	}

	public void setPaymentcollections2(
			List<Paymentcollection> paymentcollections2) {
		this.paymentcollections2 = paymentcollections2;
	}

	public Paymentcollection addPaymentcollections2(
			Paymentcollection paymentcollections2) {
		getPaymentcollections2().add(paymentcollections2);
		paymentcollections2.setBranch2(this);

		return paymentcollections2;
	}

	public Paymentcollection removePaymentcollections2(
			Paymentcollection paymentcollections2) {
		getPaymentcollections2().remove(paymentcollections2);
		paymentcollections2.setBranch2(null);

		return paymentcollections2;
	}

	public List<Paymentsettlement> getPaymentsettlements() {
		return this.paymentsettlements;
	}

	public void setPaymentsettlements(List<Paymentsettlement> paymentsettlements) {
		this.paymentsettlements = paymentsettlements;
	}

	public Paymentsettlement addPaymentsettlement(
			Paymentsettlement paymentsettlement) {
		getPaymentsettlements().add(paymentsettlement);
		paymentsettlement.setBranch(this);

		return paymentsettlement;
	}

	public Paymentsettlement removePaymentsettlement(
			Paymentsettlement paymentsettlement) {
		getPaymentsettlements().remove(paymentsettlement);
		paymentsettlement.setBranch(null);

		return paymentsettlement;
	}

	public List<Poscashtransaction> getPoscashtransactions() {
		return this.poscashtransactions;
	}

	public void setPoscashtransactions(
			List<Poscashtransaction> poscashtransactions) {
		this.poscashtransactions = poscashtransactions;
	}

	public Poscashtransaction addPoscashtransaction(
			Poscashtransaction poscashtransaction) {
		getPoscashtransactions().add(poscashtransaction);
		poscashtransaction.setBranch(this);

		return poscashtransaction;
	}

	public Poscashtransaction removePoscashtransaction(
			Poscashtransaction poscashtransaction) {
		getPoscashtransactions().remove(poscashtransaction);
		poscashtransaction.setBranch(null);

		return poscashtransaction;
	}

	public List<Poscounter> getPoscounters() {
		return this.poscounters;
	}

	public void setPoscounters(List<Poscounter> poscounters) {
		this.poscounters = poscounters;
	}

	public Poscounter addPoscounter(Poscounter poscounter) {
		getPoscounters().add(poscounter);
		poscounter.setBranch(this);

		return poscounter;
	}

	public Poscounter removePoscounter(Poscounter poscounter) {
		getPoscounters().remove(poscounter);
		poscounter.setBranch(null);

		return poscounter;
	}

	public List<Pospayment> getPospayments() {
		return this.pospayments;
	}

	public void setPospayments(List<Pospayment> pospayments) {
		this.pospayments = pospayments;
	}

	public Pospayment addPospayment(Pospayment pospayment) {
		getPospayments().add(pospayment);
		pospayment.setBranch(this);

		return pospayment;
	}

	public Pospayment removePospayment(Pospayment pospayment) {
		getPospayments().remove(pospayment);
		pospayment.setBranch(null);

		return pospayment;
	}

	public List<Productbatch> getProductbatches() {
		return this.productbatches;
	}

	public void setProductbatches(List<Productbatch> productbatches) {
		this.productbatches = productbatches;
	}

	public Productbatch addProductbatch(Productbatch productbatch) {
		getProductbatches().add(productbatch);
		productbatch.setBranch(this);

		return productbatch;
	}

	public Productbatch removeProductbatch(Productbatch productbatch) {
		getProductbatches().remove(productbatch);
		productbatch.setBranch(null);

		return productbatch;
	}

	public List<Productprice> getProductprices() {
		return this.productprices;
	}

	public void setProductprices(List<Productprice> productprices) {
		this.productprices = productprices;
	}

	public Productprice addProductprice(Productprice productprice) {
		getProductprices().add(productprice);
		productprice.setBranch(this);

		return productprice;
	}

	public Productprice removeProductprice(Productprice productprice) {
		getProductprices().remove(productprice);
		productprice.setBranch(null);

		return productprice;
	}

	public List<Purchaseorder> getPurchaseorders() {
		return this.purchaseorders;
	}

	public void setPurchaseorders(List<Purchaseorder> purchaseorders) {
		this.purchaseorders = purchaseorders;
	}

	public Purchaseorder addPurchaseorder(Purchaseorder purchaseorder) {
		getPurchaseorders().add(purchaseorder);
		purchaseorder.setBranch(this);

		return purchaseorder;
	}

	public Purchaseorder removePurchaseorder(Purchaseorder purchaseorder) {
		getPurchaseorders().remove(purchaseorder);
		purchaseorder.setBranch(null);

		return purchaseorder;
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
		purchaseorderbreakdown.setBranch(this);

		return purchaseorderbreakdown;
	}

	public Purchaseorderbreakdown removePurchaseorderbreakdown(
			Purchaseorderbreakdown purchaseorderbreakdown) {
		getPurchaseorderbreakdowns().remove(purchaseorderbreakdown);
		purchaseorderbreakdown.setBranch(null);

		return purchaseorderbreakdown;
	}

	public List<Purchaseorderdeliveryaddress> getPurchaseorderdeliveryaddresses() {
		return this.purchaseorderdeliveryaddresses;
	}

	public void setPurchaseorderdeliveryaddresses(
			List<Purchaseorderdeliveryaddress> purchaseorderdeliveryaddresses) {
		this.purchaseorderdeliveryaddresses = purchaseorderdeliveryaddresses;
	}

	public Purchaseorderdeliveryaddress addPurchaseorderdeliveryaddress(
			Purchaseorderdeliveryaddress purchaseorderdeliveryaddress) {
		getPurchaseorderdeliveryaddresses().add(purchaseorderdeliveryaddress);
		purchaseorderdeliveryaddress.setBranch(this);

		return purchaseorderdeliveryaddress;
	}

	public Purchaseorderdeliveryaddress removePurchaseorderdeliveryaddress(
			Purchaseorderdeliveryaddress purchaseorderdeliveryaddress) {
		getPurchaseorderdeliveryaddresses()
				.remove(purchaseorderdeliveryaddress);
		purchaseorderdeliveryaddress.setBranch(null);

		return purchaseorderdeliveryaddress;
	}

	public List<Purchaserequest> getPurchaserequests() {
		return this.purchaserequests;
	}

	public void setPurchaserequests(List<Purchaserequest> purchaserequests) {
		this.purchaserequests = purchaserequests;
	}

	public Purchaserequest addPurchaserequest(Purchaserequest purchaserequest) {
		getPurchaserequests().add(purchaserequest);
		purchaserequest.setBranch(this);

		return purchaserequest;
	}

	public Purchaserequest removePurchaserequest(Purchaserequest purchaserequest) {
		getPurchaserequests().remove(purchaserequest);
		purchaserequest.setBranch(null);

		return purchaserequest;
	}

	public List<Quotation> getQuotations() {
		return this.quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public Quotation addQuotation(Quotation quotation) {
		getQuotations().add(quotation);
		quotation.setBranch(this);

		return quotation;
	}

	public Quotation removeQuotation(Quotation quotation) {
		getQuotations().remove(quotation);
		quotation.setBranch(null);

		return quotation;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setBranch(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setBranch(null);

		return role;
	}

	public List<Salesorder> getSalesorders1() {
		return this.salesorders1;
	}

	public void setSalesorders1(List<Salesorder> salesorders1) {
		this.salesorders1 = salesorders1;
	}

	public Salesorder addSalesorders1(Salesorder salesorders1) {
		getSalesorders1().add(salesorders1);
		salesorders1.setBranch1(this);

		return salesorders1;
	}

	public Salesorder removeSalesorders1(Salesorder salesorders1) {
		getSalesorders1().remove(salesorders1);
		salesorders1.setBranch1(null);

		return salesorders1;
	}

	public List<Salesorder> getSalesorders2() {
		return this.salesorders2;
	}

	public void setSalesorders2(List<Salesorder> salesorders2) {
		this.salesorders2 = salesorders2;
	}

	public Salesorder addSalesorders2(Salesorder salesorders2) {
		getSalesorders2().add(salesorders2);
		salesorders2.setBranch2(this);

		return salesorders2;
	}

	public Salesorder removeSalesorders2(Salesorder salesorders2) {
		getSalesorders2().remove(salesorders2);
		salesorders2.setBranch2(null);

		return salesorders2;
	}

	public List<Salesorderreturn> getSalesorderreturns() {
		return this.salesorderreturns;
	}

	public void setSalesorderreturns(List<Salesorderreturn> salesorderreturns) {
		this.salesorderreturns = salesorderreturns;
	}

	public Salesorderreturn addSalesorderreturn(
			Salesorderreturn salesorderreturn) {
		getSalesorderreturns().add(salesorderreturn);
		salesorderreturn.setBranch(this);

		return salesorderreturn;
	}

	public Salesorderreturn removeSalesorderreturn(
			Salesorderreturn salesorderreturn) {
		getSalesorderreturns().remove(salesorderreturn);
		salesorderreturn.setBranch(null);

		return salesorderreturn;
	}

	public List<Supplierinvoice> getSupplierinvoices() {
		return this.supplierinvoices;
	}

	public void setSupplierinvoices(List<Supplierinvoice> supplierinvoices) {
		this.supplierinvoices = supplierinvoices;
	}

	public Supplierinvoice addSupplierinvoice(Supplierinvoice supplierinvoice) {
		getSupplierinvoices().add(supplierinvoice);
		supplierinvoice.setBranch(this);

		return supplierinvoice;
	}

	public Supplierinvoice removeSupplierinvoice(Supplierinvoice supplierinvoice) {
		getSupplierinvoices().remove(supplierinvoice);
		supplierinvoice.setBranch(null);

		return supplierinvoice;
	}

	public List<Supplierpayment> getSupplierpayments() {
		return this.supplierpayments;
	}

	public void setSupplierpayments(List<Supplierpayment> supplierpayments) {
		this.supplierpayments = supplierpayments;
	}

	public Supplierpayment addSupplierpayment(Supplierpayment supplierpayment) {
		getSupplierpayments().add(supplierpayment);
		supplierpayment.setBranch(this);

		return supplierpayment;
	}

	public Supplierpayment removeSupplierpayment(Supplierpayment supplierpayment) {
		getSupplierpayments().remove(supplierpayment);
		supplierpayment.setBranch(null);

		return supplierpayment;
	}

	public List<Productbranchlink> getProductbranchlinks() {
		return productbranchlinks;
	}

	public void setProductbranchlinks(List<Productbranchlink> productbranchlinks) {
		this.productbranchlinks = productbranchlinks;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public List<Supplierinvoice> getSupplierinvoices2() {
		return supplierinvoices2;
	}

	public void setSupplierinvoices2(List<Supplierinvoice> supplierinvoices2) {
		this.supplierinvoices2 = supplierinvoices2;
	}

	public List<Paymentsettlement> getPaymentsettlements1() {
		return paymentsettlements1;
	}

	public void setPaymentsettlements1(
			List<Paymentsettlement> paymentsettlements1) {
		this.paymentsettlements1 = paymentsettlements1;
	}

	public List<Autonum> getAutonums() {
		return autonums;
	}

	public void setAutonums(List<Autonum> autonums) {
		this.autonums = autonums;
	}

	public List<Taxcode> getTaxcodes() {
		return taxcodes;
	}

	public void setTaxcodes(List<Taxcode> taxcodes) {
		this.taxcodes = taxcodes;
	}

	public List<Branchsale> getBranchsales1() {
		return branchsales1;
	}

	public void setBranchsales1(List<Branchsale> branchsales1) {
		this.branchsales1 = branchsales1;
	}

	public List<Branchsale> getBranchsales2() {
		return branchsales2;
	}

	public void setBranchsales2(List<Branchsale> branchsales2) {
		this.branchsales2 = branchsales2;
	}

	public BigDecimal getTotalPurchaseTax() {
		return totalPurchaseTax;
	}

	public void setTotalPurchaseTax(BigDecimal totalPurchaseTax) {
		this.totalPurchaseTax = totalPurchaseTax;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public void setTotalSalesTax(BigDecimal totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}

	public int getOnlineBranchId() {
		return onlineBranchId;
	}

	public void setOnlineBranchId(int onlineBranchId) {
		this.onlineBranchId = onlineBranchId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	
	
	

}