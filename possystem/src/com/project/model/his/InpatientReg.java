package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inpatient_regs database table.
 * 
 */
@Entity
@Table(name="inpatient_regs")
public class InpatientReg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int inpatient_reg_Id;

	private int admission_Reason;

	@Column(length=16)
	private String admission_Type;

	private int attending_Physician;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date discharge_Date;

	@Lob
	private String discharge_Plan;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hospitalization_Date;

	@Lob
	private String info;

	@Lob
	private String nursing_Plan;

	private int operating_Physician;

	@Column(length=16)
	private String state;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to HospitalBed
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Hospital_bed_Id")
	private HospitalBed hospitalBed;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public InpatientReg() {
	}

	public int getInpatient_reg_Id() {
		return this.inpatient_reg_Id;
	}

	public void setInpatient_reg_Id(int inpatient_reg_Id) {
		this.inpatient_reg_Id = inpatient_reg_Id;
	}

	public int getAdmission_Reason() {
		return this.admission_Reason;
	}

	public void setAdmission_Reason(int admission_Reason) {
		this.admission_Reason = admission_Reason;
	}

	public String getAdmission_Type() {
		return this.admission_Type;
	}

	public void setAdmission_Type(String admission_Type) {
		this.admission_Type = admission_Type;
	}

	public int getAttending_Physician() {
		return this.attending_Physician;
	}

	public void setAttending_Physician(int attending_Physician) {
		this.attending_Physician = attending_Physician;
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

	public Date getDischarge_Date() {
		return this.discharge_Date;
	}

	public void setDischarge_Date(Date discharge_Date) {
		this.discharge_Date = discharge_Date;
	}

	public String getDischarge_Plan() {
		return this.discharge_Plan;
	}

	public void setDischarge_Plan(String discharge_Plan) {
		this.discharge_Plan = discharge_Plan;
	}

	public Date getHospitalization_Date() {
		return this.hospitalization_Date;
	}

	public void setHospitalization_Date(Date hospitalization_Date) {
		this.hospitalization_Date = hospitalization_Date;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNursing_Plan() {
		return this.nursing_Plan;
	}

	public void setNursing_Plan(String nursing_Plan) {
		this.nursing_Plan = nursing_Plan;
	}

	public int getOperating_Physician() {
		return this.operating_Physician;
	}

	public void setOperating_Physician(int operating_Physician) {
		this.operating_Physician = operating_Physician;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	public HospitalBed getHospitalBed() {
		return this.hospitalBed;
	}

	public void setHospitalBed(HospitalBed hospitalBed) {
		this.hospitalBed = hospitalBed;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}