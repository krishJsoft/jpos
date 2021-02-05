package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vaccinations database table.
 * 
 */
@Entity
@Table(name="vaccinations")
public class Vaccination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int vacines_Id;

	@Column(length=50)
	private String age;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=400)
	private String description;

	@Column(length=25)
	private String end_Range_high_risk;

	@Column(length=25)
	private String end_Range_Normal;

	@Column(length=100)
	private String name_Long;

	@Column(length=20)
	private String name_Short;

	private byte send_Email;

	@Column(length=25)
	private String start_Range_high_risk;

	@Column(length=25)
	private String start_Range_Normal;

	@Column(length=25)
	private String vaccine_for_Pregnant_Women;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Vaccination() {
	}

	public int getVacines_Id() {
		return this.vacines_Id;
	}

	public void setVacines_Id(int vacines_Id) {
		this.vacines_Id = vacines_Id;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public String getEnd_Range_high_risk() {
		return this.end_Range_high_risk;
	}

	public void setEnd_Range_high_risk(String end_Range_high_risk) {
		this.end_Range_high_risk = end_Range_high_risk;
	}

	public String getEnd_Range_Normal() {
		return this.end_Range_Normal;
	}

	public void setEnd_Range_Normal(String end_Range_Normal) {
		this.end_Range_Normal = end_Range_Normal;
	}

	public String getName_Long() {
		return this.name_Long;
	}

	public void setName_Long(String name_Long) {
		this.name_Long = name_Long;
	}

	public String getName_Short() {
		return this.name_Short;
	}

	public void setName_Short(String name_Short) {
		this.name_Short = name_Short;
	}

	public byte getSend_Email() {
		return this.send_Email;
	}

	public void setSend_Email(byte send_Email) {
		this.send_Email = send_Email;
	}

	public String getStart_Range_high_risk() {
		return this.start_Range_high_risk;
	}

	public void setStart_Range_high_risk(String start_Range_high_risk) {
		this.start_Range_high_risk = start_Range_high_risk;
	}

	public String getStart_Range_Normal() {
		return this.start_Range_Normal;
	}

	public void setStart_Range_Normal(String start_Range_Normal) {
		this.start_Range_Normal = start_Range_Normal;
	}

	public String getVaccine_for_Pregnant_Women() {
		return this.vaccine_for_Pregnant_Women;
	}

	public void setVaccine_for_Pregnant_Women(String vaccine_for_Pregnant_Women) {
		this.vaccine_for_Pregnant_Women = vaccine_for_Pregnant_Women;
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

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}