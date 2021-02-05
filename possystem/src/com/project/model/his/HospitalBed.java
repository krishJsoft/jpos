package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hospital_bed database table.
 * 
 */
@Entity
@Table(name="hospital_bed")
public class HospitalBed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int hospital_bed_Id;

	@Column(nullable=false, length=16)
	private String bed_Type;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String extra_Info;

	@Column(length=16)
	private String state;

	@Column(length=128)
	private String telephone_Number;

	private int ward_ID;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to HospitalWard
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Hospital_ward_Id")
	private HospitalWard hospitalWard;

	//bi-directional many-to-one association to InpatientReg
	@OneToMany(mappedBy="hospitalBed")
	private List<InpatientReg> inpatientRegs;

	public HospitalBed() {
	}

	public int getHospital_bed_Id() {
		return this.hospital_bed_Id;
	}

	public void setHospital_bed_Id(int hospital_bed_Id) {
		this.hospital_bed_Id = hospital_bed_Id;
	}

	public String getBed_Type() {
		return this.bed_Type;
	}

	public void setBed_Type(String bed_Type) {
		this.bed_Type = bed_Type;
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

	public String getExtra_Info() {
		return this.extra_Info;
	}

	public void setExtra_Info(String extra_Info) {
		this.extra_Info = extra_Info;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelephone_Number() {
		return this.telephone_Number;
	}

	public void setTelephone_Number(String telephone_Number) {
		this.telephone_Number = telephone_Number;
	}

	public int getWard_ID() {
		return this.ward_ID;
	}

	public void setWard_ID(int ward_ID) {
		this.ward_ID = ward_ID;
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

	public HospitalWard getHospitalWard() {
		return this.hospitalWard;
	}

	public void setHospitalWard(HospitalWard hospitalWard) {
		this.hospitalWard = hospitalWard;
	}

	public List<InpatientReg> getInpatientRegs() {
		return this.inpatientRegs;
	}

	public void setInpatientRegs(List<InpatientReg> inpatientRegs) {
		this.inpatientRegs = inpatientRegs;
	}

	public InpatientReg addInpatientReg(InpatientReg inpatientReg) {
		getInpatientRegs().add(inpatientReg);
		inpatientReg.setHospitalBed(this);

		return inpatientReg;
	}

	public InpatientReg removeInpatientReg(InpatientReg inpatientReg) {
		getInpatientRegs().remove(inpatientReg);
		inpatientReg.setHospitalBed(null);

		return inpatientReg;
	}

}