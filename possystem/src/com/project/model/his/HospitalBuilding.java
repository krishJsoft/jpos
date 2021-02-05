package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hospital_buildings database table.
 * 
 */
@Entity
@Table(name="hospital_buildings")
public class HospitalBuilding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int hospital_Building_Id;

	@Column(length=64)
	private String code;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String extra_info;

	private int institution;

	@Column(length=128)
	private String name;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to HospitalWard
	@OneToMany(mappedBy="hospitalBuilding")
	private List<HospitalWard> hospitalWards;

	public HospitalBuilding() {
	}

	public int getHospital_Building_Id() {
		return this.hospital_Building_Id;
	}

	public void setHospital_Building_Id(int hospital_Building_Id) {
		this.hospital_Building_Id = hospital_Building_Id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getExtra_info() {
		return this.extra_info;
	}

	public void setExtra_info(String extra_info) {
		this.extra_info = extra_info;
	}

	public int getInstitution() {
		return this.institution;
	}

	public void setInstitution(int institution) {
		this.institution = institution;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		hospitalWard.setHospitalBuilding(this);

		return hospitalWard;
	}

	public HospitalWard removeHospitalWard(HospitalWard hospitalWard) {
		getHospitalWards().remove(hospitalWard);
		hospitalWard.setHospitalBuilding(null);

		return hospitalWard;
	}

}