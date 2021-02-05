package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_cardios database table.
 * 
 */
@Entity
@Table(name="evaluation_cardios")
public class EvaluationCardio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_CV_id;

	private byte clear_S1_S2;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String evaluation_Cardio_Notes;

	private byte gallop_Audible;

	private byte grade_I;

	private byte grade_II;

	private byte grade_III;

	private byte grade_IV;

	private byte grade_V;

	private byte grade_VI;

	private byte murmur_Diastolic;

	private byte murmur_present;

	private byte murmur_Systolic;

	private byte no_murmur_rub_or_gallop;

	private byte peripheral_pulse_Absent;

	private byte peripheral_pulse_palpable;

	private byte peripheral_pulse_Weak;

	private byte rub_audible;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationCardio() {
	}

	public int getEvaluation_CV_id() {
		return this.evaluation_CV_id;
	}

	public void setEvaluation_CV_id(int evaluation_CV_id) {
		this.evaluation_CV_id = evaluation_CV_id;
	}

	public byte getClear_S1_S2() {
		return this.clear_S1_S2;
	}

	public void setClear_S1_S2(byte clear_S1_S2) {
		this.clear_S1_S2 = clear_S1_S2;
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

	public String getEvaluation_Cardio_Notes() {
		return this.evaluation_Cardio_Notes;
	}

	public void setEvaluation_Cardio_Notes(String evaluation_Cardio_Notes) {
		this.evaluation_Cardio_Notes = evaluation_Cardio_Notes;
	}

	public byte getGallop_Audible() {
		return this.gallop_Audible;
	}

	public void setGallop_Audible(byte gallop_Audible) {
		this.gallop_Audible = gallop_Audible;
	}

	public byte getGrade_I() {
		return this.grade_I;
	}

	public void setGrade_I(byte grade_I) {
		this.grade_I = grade_I;
	}

	public byte getGrade_II() {
		return this.grade_II;
	}

	public void setGrade_II(byte grade_II) {
		this.grade_II = grade_II;
	}

	public byte getGrade_III() {
		return this.grade_III;
	}

	public void setGrade_III(byte grade_III) {
		this.grade_III = grade_III;
	}

	public byte getGrade_IV() {
		return this.grade_IV;
	}

	public void setGrade_IV(byte grade_IV) {
		this.grade_IV = grade_IV;
	}

	public byte getGrade_V() {
		return this.grade_V;
	}

	public void setGrade_V(byte grade_V) {
		this.grade_V = grade_V;
	}

	public byte getGrade_VI() {
		return this.grade_VI;
	}

	public void setGrade_VI(byte grade_VI) {
		this.grade_VI = grade_VI;
	}

	public byte getMurmur_Diastolic() {
		return this.murmur_Diastolic;
	}

	public void setMurmur_Diastolic(byte murmur_Diastolic) {
		this.murmur_Diastolic = murmur_Diastolic;
	}

	public byte getMurmur_present() {
		return this.murmur_present;
	}

	public void setMurmur_present(byte murmur_present) {
		this.murmur_present = murmur_present;
	}

	public byte getMurmur_Systolic() {
		return this.murmur_Systolic;
	}

	public void setMurmur_Systolic(byte murmur_Systolic) {
		this.murmur_Systolic = murmur_Systolic;
	}

	public byte getNo_murmur_rub_or_gallop() {
		return this.no_murmur_rub_or_gallop;
	}

	public void setNo_murmur_rub_or_gallop(byte no_murmur_rub_or_gallop) {
		this.no_murmur_rub_or_gallop = no_murmur_rub_or_gallop;
	}

	public byte getPeripheral_pulse_Absent() {
		return this.peripheral_pulse_Absent;
	}

	public void setPeripheral_pulse_Absent(byte peripheral_pulse_Absent) {
		this.peripheral_pulse_Absent = peripheral_pulse_Absent;
	}

	public byte getPeripheral_pulse_palpable() {
		return this.peripheral_pulse_palpable;
	}

	public void setPeripheral_pulse_palpable(byte peripheral_pulse_palpable) {
		this.peripheral_pulse_palpable = peripheral_pulse_palpable;
	}

	public byte getPeripheral_pulse_Weak() {
		return this.peripheral_pulse_Weak;
	}

	public void setPeripheral_pulse_Weak(byte peripheral_pulse_Weak) {
		this.peripheral_pulse_Weak = peripheral_pulse_Weak;
	}

	public byte getRub_audible() {
		return this.rub_audible;
	}

	public void setRub_audible(byte rub_audible) {
		this.rub_audible = rub_audible;
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