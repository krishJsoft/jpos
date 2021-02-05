package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hospital_wards database table.
 * 
 */
@Entity
@Table(name="hospital_wards")
public class HospitalWard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int hospital_ward_Id;

	private byte ac;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String extra_Info;

	@Column(nullable=false, length=16)
	private String gender_Privacy;

	private byte has_a_TV;

	private byte has_Bio_Hazard_Disposal;

	private byte has_Internet;

	private byte has_sofa_for_Guests;

	private byte is_Private;

	private byte microwave;

	private int number_of_beds_In_Hospital;

	@Lob
	@Column(nullable=false)
	private String number_of_beds_In_Room;

	private byte private_Bathroom;

	private byte refrigerator;

	@Column(length=16)
	private String state_of_Room;

	private byte telephone;

	private int type_of_Floor;

	private int unit_Number;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to HospitalBed
	@OneToMany(mappedBy="hospitalWard")
	private List<HospitalBed> hospitalBeds;

	//bi-directional many-to-one association to HospitalBuilding
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Hospital_Building_Id")
	private HospitalBuilding hospitalBuilding;

	//bi-directional many-to-one association to HospitalUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Hospital_unit_Id")
	private HospitalUnit hospitalUnit;

	public HospitalWard() {
	}

	public int getHospital_ward_Id() {
		return this.hospital_ward_Id;
	}

	public void setHospital_ward_Id(int hospital_ward_Id) {
		this.hospital_ward_Id = hospital_ward_Id;
	}

	public byte getAc() {
		return this.ac;
	}

	public void setAc(byte ac) {
		this.ac = ac;
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

	public String getGender_Privacy() {
		return this.gender_Privacy;
	}

	public void setGender_Privacy(String gender_Privacy) {
		this.gender_Privacy = gender_Privacy;
	}

	public byte getHas_a_TV() {
		return this.has_a_TV;
	}

	public void setHas_a_TV(byte has_a_TV) {
		this.has_a_TV = has_a_TV;
	}

	public byte getHas_Bio_Hazard_Disposal() {
		return this.has_Bio_Hazard_Disposal;
	}

	public void setHas_Bio_Hazard_Disposal(byte has_Bio_Hazard_Disposal) {
		this.has_Bio_Hazard_Disposal = has_Bio_Hazard_Disposal;
	}

	public byte getHas_Internet() {
		return this.has_Internet;
	}

	public void setHas_Internet(byte has_Internet) {
		this.has_Internet = has_Internet;
	}

	public byte getHas_sofa_for_Guests() {
		return this.has_sofa_for_Guests;
	}

	public void setHas_sofa_for_Guests(byte has_sofa_for_Guests) {
		this.has_sofa_for_Guests = has_sofa_for_Guests;
	}

	public byte getIs_Private() {
		return this.is_Private;
	}

	public void setIs_Private(byte is_Private) {
		this.is_Private = is_Private;
	}

	public byte getMicrowave() {
		return this.microwave;
	}

	public void setMicrowave(byte microwave) {
		this.microwave = microwave;
	}

	public int getNumber_of_beds_In_Hospital() {
		return this.number_of_beds_In_Hospital;
	}

	public void setNumber_of_beds_In_Hospital(int number_of_beds_In_Hospital) {
		this.number_of_beds_In_Hospital = number_of_beds_In_Hospital;
	}

	public String getNumber_of_beds_In_Room() {
		return this.number_of_beds_In_Room;
	}

	public void setNumber_of_beds_In_Room(String number_of_beds_In_Room) {
		this.number_of_beds_In_Room = number_of_beds_In_Room;
	}

	public byte getPrivate_Bathroom() {
		return this.private_Bathroom;
	}

	public void setPrivate_Bathroom(byte private_Bathroom) {
		this.private_Bathroom = private_Bathroom;
	}

	public byte getRefrigerator() {
		return this.refrigerator;
	}

	public void setRefrigerator(byte refrigerator) {
		this.refrigerator = refrigerator;
	}

	public String getState_of_Room() {
		return this.state_of_Room;
	}

	public void setState_of_Room(String state_of_Room) {
		this.state_of_Room = state_of_Room;
	}

	public byte getTelephone() {
		return this.telephone;
	}

	public void setTelephone(byte telephone) {
		this.telephone = telephone;
	}

	public int getType_of_Floor() {
		return this.type_of_Floor;
	}

	public void setType_of_Floor(int type_of_Floor) {
		this.type_of_Floor = type_of_Floor;
	}

	public int getUnit_Number() {
		return this.unit_Number;
	}

	public void setUnit_Number(int unit_Number) {
		this.unit_Number = unit_Number;
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

	public List<HospitalBed> getHospitalBeds() {
		return this.hospitalBeds;
	}

	public void setHospitalBeds(List<HospitalBed> hospitalBeds) {
		this.hospitalBeds = hospitalBeds;
	}

	public HospitalBed addHospitalBed(HospitalBed hospitalBed) {
		getHospitalBeds().add(hospitalBed);
		hospitalBed.setHospitalWard(this);

		return hospitalBed;
	}

	public HospitalBed removeHospitalBed(HospitalBed hospitalBed) {
		getHospitalBeds().remove(hospitalBed);
		hospitalBed.setHospitalWard(null);

		return hospitalBed;
	}

	public HospitalBuilding getHospitalBuilding() {
		return this.hospitalBuilding;
	}

	public void setHospitalBuilding(HospitalBuilding hospitalBuilding) {
		this.hospitalBuilding = hospitalBuilding;
	}

	public HospitalUnit getHospitalUnit() {
		return this.hospitalUnit;
	}

	public void setHospitalUnit(HospitalUnit hospitalUnit) {
		this.hospitalUnit = hospitalUnit;
	}

}