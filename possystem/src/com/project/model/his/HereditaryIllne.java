package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hereditary_illnes database table.
 * 
 */
@Entity
@Table(name="hereditary_illnes")
public class HereditaryIllne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int hereditary_Illnesses_Id;

	private byte alzheimers_disease;

	private byte asthma;

	private byte autism;

	private byte breast_cancer_or_ovarian_cancer;

	private byte cancers;

	private byte ciliopathies;

	private byte cleft_palate;

	private byte colon_cancer;

	private byte COPD_Chronic_Obstructive_Pulmonary_Disease;

	private byte coronary_artery_disease;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte cri_du_chat_syndrome;

	private byte cystic_fibrosis;

	private byte diabetes;

	private byte down_syndrome;

	private byte heart_disease;

	private byte hypertension;

	private byte hypothyroidism;

	private byte inflammatory_bowel_disease;

	private byte mental_retardation;

	private byte multiple_sclerosis;

	private byte obesity;

	private byte other;

	@Column(length=50)
	private String other_description;

	private byte pancreatitis;

	private byte peripheral_Artery_Disease;

	private byte renal_Dysfunction;

	private byte thrombotic_disorder;

	private byte thyroid_Disease;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public HereditaryIllne() {
	}

	public int getHereditary_Illnesses_Id() {
		return this.hereditary_Illnesses_Id;
	}

	public void setHereditary_Illnesses_Id(int hereditary_Illnesses_Id) {
		this.hereditary_Illnesses_Id = hereditary_Illnesses_Id;
	}

	public byte getAlzheimers_disease() {
		return this.alzheimers_disease;
	}

	public void setAlzheimers_disease(byte alzheimers_disease) {
		this.alzheimers_disease = alzheimers_disease;
	}

	public byte getAsthma() {
		return this.asthma;
	}

	public void setAsthma(byte asthma) {
		this.asthma = asthma;
	}

	public byte getAutism() {
		return this.autism;
	}

	public void setAutism(byte autism) {
		this.autism = autism;
	}

	public byte getBreast_cancer_or_ovarian_cancer() {
		return this.breast_cancer_or_ovarian_cancer;
	}

	public void setBreast_cancer_or_ovarian_cancer(byte breast_cancer_or_ovarian_cancer) {
		this.breast_cancer_or_ovarian_cancer = breast_cancer_or_ovarian_cancer;
	}

	public byte getCancers() {
		return this.cancers;
	}

	public void setCancers(byte cancers) {
		this.cancers = cancers;
	}

	public byte getCiliopathies() {
		return this.ciliopathies;
	}

	public void setCiliopathies(byte ciliopathies) {
		this.ciliopathies = ciliopathies;
	}

	public byte getCleft_palate() {
		return this.cleft_palate;
	}

	public void setCleft_palate(byte cleft_palate) {
		this.cleft_palate = cleft_palate;
	}

	public byte getColon_cancer() {
		return this.colon_cancer;
	}

	public void setColon_cancer(byte colon_cancer) {
		this.colon_cancer = colon_cancer;
	}

	public byte getCOPD_Chronic_Obstructive_Pulmonary_Disease() {
		return this.COPD_Chronic_Obstructive_Pulmonary_Disease;
	}

	public void setCOPD_Chronic_Obstructive_Pulmonary_Disease(byte COPD_Chronic_Obstructive_Pulmonary_Disease) {
		this.COPD_Chronic_Obstructive_Pulmonary_Disease = COPD_Chronic_Obstructive_Pulmonary_Disease;
	}

	public byte getCoronary_artery_disease() {
		return this.coronary_artery_disease;
	}

	public void setCoronary_artery_disease(byte coronary_artery_disease) {
		this.coronary_artery_disease = coronary_artery_disease;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUid() {
		return this.createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public byte getCri_du_chat_syndrome() {
		return this.cri_du_chat_syndrome;
	}

	public void setCri_du_chat_syndrome(byte cri_du_chat_syndrome) {
		this.cri_du_chat_syndrome = cri_du_chat_syndrome;
	}

	public byte getCystic_fibrosis() {
		return this.cystic_fibrosis;
	}

	public void setCystic_fibrosis(byte cystic_fibrosis) {
		this.cystic_fibrosis = cystic_fibrosis;
	}

	public byte getDiabetes() {
		return this.diabetes;
	}

	public void setDiabetes(byte diabetes) {
		this.diabetes = diabetes;
	}

	public byte getDown_syndrome() {
		return this.down_syndrome;
	}

	public void setDown_syndrome(byte down_syndrome) {
		this.down_syndrome = down_syndrome;
	}

	public byte getHeart_disease() {
		return this.heart_disease;
	}

	public void setHeart_disease(byte heart_disease) {
		this.heart_disease = heart_disease;
	}

	public byte getHypertension() {
		return this.hypertension;
	}

	public void setHypertension(byte hypertension) {
		this.hypertension = hypertension;
	}

	public byte getHypothyroidism() {
		return this.hypothyroidism;
	}

	public void setHypothyroidism(byte hypothyroidism) {
		this.hypothyroidism = hypothyroidism;
	}

	public byte getInflammatory_bowel_disease() {
		return this.inflammatory_bowel_disease;
	}

	public void setInflammatory_bowel_disease(byte inflammatory_bowel_disease) {
		this.inflammatory_bowel_disease = inflammatory_bowel_disease;
	}

	public byte getMental_retardation() {
		return this.mental_retardation;
	}

	public void setMental_retardation(byte mental_retardation) {
		this.mental_retardation = mental_retardation;
	}

	public byte getMultiple_sclerosis() {
		return this.multiple_sclerosis;
	}

	public void setMultiple_sclerosis(byte multiple_sclerosis) {
		this.multiple_sclerosis = multiple_sclerosis;
	}

	public byte getObesity() {
		return this.obesity;
	}

	public void setObesity(byte obesity) {
		this.obesity = obesity;
	}

	public byte getOther() {
		return this.other;
	}

	public void setOther(byte other) {
		this.other = other;
	}

	public String getOther_description() {
		return this.other_description;
	}

	public void setOther_description(String other_description) {
		this.other_description = other_description;
	}

	public byte getPancreatitis() {
		return this.pancreatitis;
	}

	public void setPancreatitis(byte pancreatitis) {
		this.pancreatitis = pancreatitis;
	}

	public byte getPeripheral_Artery_Disease() {
		return this.peripheral_Artery_Disease;
	}

	public void setPeripheral_Artery_Disease(byte peripheral_Artery_Disease) {
		this.peripheral_Artery_Disease = peripheral_Artery_Disease;
	}

	public byte getRenal_Dysfunction() {
		return this.renal_Dysfunction;
	}

	public void setRenal_Dysfunction(byte renal_Dysfunction) {
		this.renal_Dysfunction = renal_Dysfunction;
	}

	public byte getThrombotic_disorder() {
		return this.thrombotic_disorder;
	}

	public void setThrombotic_disorder(byte thrombotic_disorder) {
		this.thrombotic_disorder = thrombotic_disorder;
	}

	public byte getThyroid_Disease() {
		return this.thyroid_Disease;
	}

	public void setThyroid_Disease(byte thyroid_Disease) {
		this.thyroid_Disease = thyroid_Disease;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriteUid() {
		return this.writeUid;
	}

	public void setWriteUid(String writeUid) {
		this.writeUid = writeUid;
	}

	public Clinicchart getClinicchart() {
		return this.clinicchart;
	}

	public void setClinicchart(Clinicchart clinicchart) {
		this.clinicchart = clinicchart;
	}

}