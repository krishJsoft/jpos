package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the surgeries database table.
 * 
 */
@Entity
@Table(name="surgeries")
public class Surgery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int surgery_id;

	private byte aderial_bypass;

	private byte appendectomy;

	private byte cardiac_valve_repair_or_replace;

	private byte carotid_Endaderectomy;

	private byte cholecystectomy;

	private byte colon_resedion;

	private byte coronas_Ades_Bypass;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte defibnllator;

	private byte hip_replaoment;

	private byte hysterectomy;

	private byte knee_replacement;

	private byte nephrectomy;

	private byte organ_transplant;

	@Column(length=200)
	private String other_Sugery_Desc;

	private byte other_Surgery;

	private byte pacemaker;

	private byte splenedomy;

	@Lob
	private String sugeries_Notes;

	@Temporal(TemporalType.TIMESTAMP)
	private Date surgery_Date;

	@Lob
	@Column(nullable=false)
	private String surgery_Description;

	@Column(length=100)
	private String surgery_Name;

	@Lob
	@Column(nullable=false)
	private String surgery_Pathology;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Surgery() {
	}

	public int getSurgery_id() {
		return this.surgery_id;
	}

	public void setSurgery_id(int surgery_id) {
		this.surgery_id = surgery_id;
	}

	public byte getAderial_bypass() {
		return this.aderial_bypass;
	}

	public void setAderial_bypass(byte aderial_bypass) {
		this.aderial_bypass = aderial_bypass;
	}

	public byte getAppendectomy() {
		return this.appendectomy;
	}

	public void setAppendectomy(byte appendectomy) {
		this.appendectomy = appendectomy;
	}

	public byte getCardiac_valve_repair_or_replace() {
		return this.cardiac_valve_repair_or_replace;
	}

	public void setCardiac_valve_repair_or_replace(byte cardiac_valve_repair_or_replace) {
		this.cardiac_valve_repair_or_replace = cardiac_valve_repair_or_replace;
	}

	public byte getCarotid_Endaderectomy() {
		return this.carotid_Endaderectomy;
	}

	public void setCarotid_Endaderectomy(byte carotid_Endaderectomy) {
		this.carotid_Endaderectomy = carotid_Endaderectomy;
	}

	public byte getCholecystectomy() {
		return this.cholecystectomy;
	}

	public void setCholecystectomy(byte cholecystectomy) {
		this.cholecystectomy = cholecystectomy;
	}

	public byte getColon_resedion() {
		return this.colon_resedion;
	}

	public void setColon_resedion(byte colon_resedion) {
		this.colon_resedion = colon_resedion;
	}

	public byte getCoronas_Ades_Bypass() {
		return this.coronas_Ades_Bypass;
	}

	public void setCoronas_Ades_Bypass(byte coronas_Ades_Bypass) {
		this.coronas_Ades_Bypass = coronas_Ades_Bypass;
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

	public byte getDefibnllator() {
		return this.defibnllator;
	}

	public void setDefibnllator(byte defibnllator) {
		this.defibnllator = defibnllator;
	}

	public byte getHip_replaoment() {
		return this.hip_replaoment;
	}

	public void setHip_replaoment(byte hip_replaoment) {
		this.hip_replaoment = hip_replaoment;
	}

	public byte getHysterectomy() {
		return this.hysterectomy;
	}

	public void setHysterectomy(byte hysterectomy) {
		this.hysterectomy = hysterectomy;
	}

	public byte getKnee_replacement() {
		return this.knee_replacement;
	}

	public void setKnee_replacement(byte knee_replacement) {
		this.knee_replacement = knee_replacement;
	}

	public byte getNephrectomy() {
		return this.nephrectomy;
	}

	public void setNephrectomy(byte nephrectomy) {
		this.nephrectomy = nephrectomy;
	}

	public byte getOrgan_transplant() {
		return this.organ_transplant;
	}

	public void setOrgan_transplant(byte organ_transplant) {
		this.organ_transplant = organ_transplant;
	}

	public String getOther_Sugery_Desc() {
		return this.other_Sugery_Desc;
	}

	public void setOther_Sugery_Desc(String other_Sugery_Desc) {
		this.other_Sugery_Desc = other_Sugery_Desc;
	}

	public byte getOther_Surgery() {
		return this.other_Surgery;
	}

	public void setOther_Surgery(byte other_Surgery) {
		this.other_Surgery = other_Surgery;
	}

	public byte getPacemaker() {
		return this.pacemaker;
	}

	public void setPacemaker(byte pacemaker) {
		this.pacemaker = pacemaker;
	}

	public byte getSplenedomy() {
		return this.splenedomy;
	}

	public void setSplenedomy(byte splenedomy) {
		this.splenedomy = splenedomy;
	}

	public String getSugeries_Notes() {
		return this.sugeries_Notes;
	}

	public void setSugeries_Notes(String sugeries_Notes) {
		this.sugeries_Notes = sugeries_Notes;
	}

	public Date getSurgery_Date() {
		return this.surgery_Date;
	}

	public void setSurgery_Date(Date surgery_Date) {
		this.surgery_Date = surgery_Date;
	}

	public String getSurgery_Description() {
		return this.surgery_Description;
	}

	public void setSurgery_Description(String surgery_Description) {
		this.surgery_Description = surgery_Description;
	}

	public String getSurgery_Name() {
		return this.surgery_Name;
	}

	public void setSurgery_Name(String surgery_Name) {
		this.surgery_Name = surgery_Name;
	}

	public String getSurgery_Pathology() {
		return this.surgery_Pathology;
	}

	public void setSurgery_Pathology(String surgery_Pathology) {
		this.surgery_Pathology = surgery_Pathology;
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}