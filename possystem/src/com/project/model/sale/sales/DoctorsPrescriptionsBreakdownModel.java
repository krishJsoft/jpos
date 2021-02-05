package com.project.model.sale.sales;

import java.math.BigDecimal;


import com.project.model.datamodel.ProductModel;


public class DoctorsPrescriptionsBreakdownModel {

	private int prescriptionbreakdownId;
	private BigDecimal discountAmount;
	private String dose;
	private String formName;
	private String formtake;
	private String frequency;
	private String medicationPeriod;
	private BigDecimal quantity;
	private BigDecimal subTotal;
	private String takeA;
	private String takeM;
	private String takeN;
	private BigDecimal unitPrice;
	private int branchId;
	private String branchName;
	private int productId;
	private String productName;	
	private BigDecimal doctorCommission;

	ProductModel product = new ProductModel();

	public int getPrescriptionbreakdownId() {
		return prescriptionbreakdownId;
	}

	public void setPrescriptionbreakdownId(int prescriptionbreakdownId) {
		this.prescriptionbreakdownId = prescriptionbreakdownId;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getMedicationPeriod() {
		return medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getTakeA() {
		return takeA;
	}

	public void setTakeA(String takeA) {
		this.takeA = takeA;
	}

	public String getTakeM() {
		return takeM;
	}

	public void setTakeM(String takeM) {
		this.takeM = takeM;
	}

	public String getTakeN() {
		return takeN;
	}

	public void setTakeN(String takeN) {
		this.takeN = takeN;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDoctorCommission() {
		return doctorCommission;
	}

	public void setDoctorCommission(BigDecimal doctorCommission) {
		this.doctorCommission = doctorCommission;
	}

	public String getFormtake() {
		return formtake;
	}

	public void setFormtake(String formtake) {
		this.formtake = formtake;
	}

	
}
