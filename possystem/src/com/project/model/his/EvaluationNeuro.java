package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_neuros database table.
 * 
 */
@Entity
@Table(name="evaluation_neuros")
public class EvaluationNeuro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Neuro_id;

	private byte affect_is_WNL;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String evaluation_Neuro_Notes;

	private byte not_oriented_to_person;

	private byte not_oriented_to_place;

	private byte not_oriented_to_time;

	private byte oriented;

	private byte patient_appears_Agitated;

	private byte patient_appears_Anxious;

	private byte patient_appears_Depressed;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationNeuro() {
	}

	public int getEvaluation_Neuro_id() {
		return this.evaluation_Neuro_id;
	}

	public void setEvaluation_Neuro_id(int evaluation_Neuro_id) {
		this.evaluation_Neuro_id = evaluation_Neuro_id;
	}

	public byte getAffect_is_WNL() {
		return this.affect_is_WNL;
	}

	public void setAffect_is_WNL(byte affect_is_WNL) {
		this.affect_is_WNL = affect_is_WNL;
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

	public String getEvaluation_Neuro_Notes() {
		return this.evaluation_Neuro_Notes;
	}

	public void setEvaluation_Neuro_Notes(String evaluation_Neuro_Notes) {
		this.evaluation_Neuro_Notes = evaluation_Neuro_Notes;
	}

	public byte getNot_oriented_to_person() {
		return this.not_oriented_to_person;
	}

	public void setNot_oriented_to_person(byte not_oriented_to_person) {
		this.not_oriented_to_person = not_oriented_to_person;
	}

	public byte getNot_oriented_to_place() {
		return this.not_oriented_to_place;
	}

	public void setNot_oriented_to_place(byte not_oriented_to_place) {
		this.not_oriented_to_place = not_oriented_to_place;
	}

	public byte getNot_oriented_to_time() {
		return this.not_oriented_to_time;
	}

	public void setNot_oriented_to_time(byte not_oriented_to_time) {
		this.not_oriented_to_time = not_oriented_to_time;
	}

	public byte getOriented() {
		return this.oriented;
	}

	public void setOriented(byte oriented) {
		this.oriented = oriented;
	}

	public byte getPatient_appears_Agitated() {
		return this.patient_appears_Agitated;
	}

	public void setPatient_appears_Agitated(byte patient_appears_Agitated) {
		this.patient_appears_Agitated = patient_appears_Agitated;
	}

	public byte getPatient_appears_Anxious() {
		return this.patient_appears_Anxious;
	}

	public void setPatient_appears_Anxious(byte patient_appears_Anxious) {
		this.patient_appears_Anxious = patient_appears_Anxious;
	}

	public byte getPatient_appears_Depressed() {
		return this.patient_appears_Depressed;
	}

	public void setPatient_appears_Depressed(byte patient_appears_Depressed) {
		this.patient_appears_Depressed = patient_appears_Depressed;
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