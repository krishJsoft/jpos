package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the doctorprescriptionbreakdown database table.
 * 
 */
@Entity
@Table(name="doctorprescriptionbreakdown")
public class Doctorprescriptionbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int prescriptionbreakdownId;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Column(precision=10, scale=2)
	private BigDecimal doctorCommission;

	@Column(length=20)
	private String dose;

	@Column(length=50)
	private String formName;
	
	@Column(length=25)
	private String formtake;

	@Column(length=40)
	private String frequency;

	@Column(length=30)
	private String medicationPeriod;

	@Column(precision=10, scale=2)
	private BigDecimal quantity;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	@Column(length=5)
	private String takeA;

	@Column(length=5)
	private String takeM;

	@Column(length=5)
	private String takeN;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Doctorprescription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctorPrescriptionId")
	private Doctorprescription doctorprescription;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;

	public Doctorprescriptionbreakdown() {
	}

	public int getPrescriptionbreakdownId() {
		return this.prescriptionbreakdownId;
	}

	public void setPrescriptionbreakdownId(int prescriptionbreakdownId) {
		this.prescriptionbreakdownId = prescriptionbreakdownId;
	}

	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getDoctorCommission() {
		return this.doctorCommission;
	}

	public void setDoctorCommission(BigDecimal doctorCommission) {
		this.doctorCommission = doctorCommission;
	}

	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getMedicationPeriod() {
		return this.medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public String getTakeA() {
		return this.takeA;
	}

	public void setTakeA(String takeA) {
		this.takeA = takeA;
	}

	public String getTakeM() {
		return this.takeM;
	}

	public void setTakeM(String takeM) {
		this.takeM = takeM;
	}

	public String getTakeN() {
		return this.takeN;
	}

	public void setTakeN(String takeN) {
		this.takeN = takeN;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Doctorprescription getDoctorprescription() {
		return this.doctorprescription;
	}

	public void setDoctorprescription(Doctorprescription doctorprescription) {
		this.doctorprescription = doctorprescription;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getFormtake() {
		return formtake;
	}

	public void setFormtake(String formtake) {
		this.formtake = formtake;
	}
	
	

}