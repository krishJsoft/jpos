package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hospital_units database table.
 * 
 */
@Entity
@Table(name="hospital_units")
public class HospitalUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int hospital_unit_Id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=64)
	private String description;

	@Lob
	private String extra_Info;

	@Column(length=128)
	private String unit_Name;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to HospitalWard
	@OneToMany(mappedBy="hospitalUnit")
	private List<HospitalWard> hospitalWards;

	public HospitalUnit() {
	}

	public int getHospital_unit_Id() {
		return this.hospital_unit_Id;
	}

	public void setHospital_unit_Id(int hospital_unit_Id) {
		this.hospital_unit_Id = hospital_unit_Id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtra_Info() {
		return this.extra_Info;
	}

	public void setExtra_Info(String extra_Info) {
		this.extra_Info = extra_Info;
	}

	public String getUnit_Name() {
		return this.unit_Name;
	}

	public void setUnit_Name(String unit_Name) {
		this.unit_Name = unit_Name;
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

	public List<HospitalWard> getHospitalWards() {
		return this.hospitalWards;
	}

	public void setHospitalWards(List<HospitalWard> hospitalWards) {
		this.hospitalWards = hospitalWards;
	}

	public HospitalWard addHospitalWard(HospitalWard hospitalWard) {
		getHospitalWards().add(hospitalWard);
		hospitalWard.setHospitalUnit(this);

		return hospitalWard;
	}

	public HospitalWard removeHospitalWard(HospitalWard hospitalWard) {
		getHospitalWards().remove(hospitalWard);
		hospitalWard.setHospitalUnit(null);

		return hospitalWard;
	}

}