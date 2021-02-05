package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_constitutionals database table.
 * 
 */
@Entity
@Table(name="evaluation_constitutionals")
public class EvaluationConstitutional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Cons_id;

	private int blood_Pressure_lying;

	private int blood_Pressure_sitting;

	private int blood_Pressure_standing;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String evaluation_Const_Notes;

	private int height;

	private int pulse_Rate;

	private int respiratory_Rate;

	private byte rhythm_lrregular;

	private byte rhythm_Regular;

	private int temperature;

	private int weight;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationConstitutional() {
	}

	public int getEvaluation_Cons_id() {
		return this.evaluation_Cons_id;
	}

	public void setEvaluation_Cons_id(int evaluation_Cons_id) {
		this.evaluation_Cons_id = evaluation_Cons_id;
	}

	public int getBlood_Pressure_lying() {
		return this.blood_Pressure_lying;
	}

	public void setBlood_Pressure_lying(int blood_Pressure_lying) {
		this.blood_Pressure_lying = blood_Pressure_lying;
	}

	public int getBlood_Pressure_sitting() {
		return this.blood_Pressure_sitting;
	}

	public void setBlood_Pressure_sitting(int blood_Pressure_sitting) {
		this.blood_Pressure_sitting = blood_Pressure_sitting;
	}

	public int getBlood_Pressure_standing() {
		return this.blood_Pressure_standing;
	}

	public void setBlood_Pressure_standing(int blood_Pressure_standing) {
		this.blood_Pressure_standing = blood_Pressure_standing;
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

	public String getEvaluation_Const_Notes() {
		return this.evaluation_Const_Notes;
	}

	public void setEvaluation_Const_Notes(String evaluation_Const_Notes) {
		this.evaluation_Const_Notes = evaluation_Const_Notes;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPulse_Rate() {
		return this.pulse_Rate;
	}

	public void setPulse_Rate(int pulse_Rate) {
		this.pulse_Rate = pulse_Rate;
	}

	public int getRespiratory_Rate() {
		return this.respiratory_Rate;
	}

	public void setRespiratory_Rate(int respiratory_Rate) {
		this.respiratory_Rate = respiratory_Rate;
	}

	public byte getRhythm_lrregular() {
		return this.rhythm_lrregular;
	}

	public void setRhythm_lrregular(byte rhythm_lrregular) {
		this.rhythm_lrregular = rhythm_lrregular;
	}

	public byte getRhythm_Regular() {
		return this.rhythm_Regular;
	}

	public void setRhythm_Regular(byte rhythm_Regular) {
		this.rhythm_Regular = rhythm_Regular;
	}

	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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