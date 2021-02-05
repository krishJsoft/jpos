package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_muscs database table.
 * 
 */
@Entity
@Table(name="evaluation_muscs")
public class EvaluationMusc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Musc_id;

	private byte ataxia;

	private byte atrophy_present;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String evaluation_Musc_Notes;

	private byte gait_and_station_WNL;

	private byte muscle_tone_WNL;

	private byte patient_leans_Back;

	private byte patient_leans_Front;

	private byte patient_leans_Left;

	private byte patient_leans_Right;

	private byte shuffle;

	private byte tone_is_decreased;

	private byte tone_is_increased;

	private byte wide_based_gait;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationMusc() {
	}

	public int getEvaluation_Musc_id() {
		return this.evaluation_Musc_id;
	}

	public void setEvaluation_Musc_id(int evaluation_Musc_id) {
		this.evaluation_Musc_id = evaluation_Musc_id;
	}

	public byte getAtaxia() {
		return this.ataxia;
	}

	public void setAtaxia(byte ataxia) {
		this.ataxia = ataxia;
	}

	public byte getAtrophy_present() {
		return this.atrophy_present;
	}

	public void setAtrophy_present(byte atrophy_present) {
		this.atrophy_present = atrophy_present;
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

	public String getEvaluation_Musc_Notes() {
		return this.evaluation_Musc_Notes;
	}

	public void setEvaluation_Musc_Notes(String evaluation_Musc_Notes) {
		this.evaluation_Musc_Notes = evaluation_Musc_Notes;
	}

	public byte getGait_and_station_WNL() {
		return this.gait_and_station_WNL;
	}

	public void setGait_and_station_WNL(byte gait_and_station_WNL) {
		this.gait_and_station_WNL = gait_and_station_WNL;
	}

	public byte getMuscle_tone_WNL() {
		return this.muscle_tone_WNL;
	}

	public void setMuscle_tone_WNL(byte muscle_tone_WNL) {
		this.muscle_tone_WNL = muscle_tone_WNL;
	}

	public byte getPatient_leans_Back() {
		return this.patient_leans_Back;
	}

	public void setPatient_leans_Back(byte patient_leans_Back) {
		this.patient_leans_Back = patient_leans_Back;
	}

	public byte getPatient_leans_Front() {
		return this.patient_leans_Front;
	}

	public void setPatient_leans_Front(byte patient_leans_Front) {
		this.patient_leans_Front = patient_leans_Front;
	}

	public byte getPatient_leans_Left() {
		return this.patient_leans_Left;
	}

	public void setPatient_leans_Left(byte patient_leans_Left) {
		this.patient_leans_Left = patient_leans_Left;
	}

	public byte getPatient_leans_Right() {
		return this.patient_leans_Right;
	}

	public void setPatient_leans_Right(byte patient_leans_Right) {
		this.patient_leans_Right = patient_leans_Right;
	}

	public byte getShuffle() {
		return this.shuffle;
	}

	public void setShuffle(byte shuffle) {
		this.shuffle = shuffle;
	}

	public byte getTone_is_decreased() {
		return this.tone_is_decreased;
	}

	public void setTone_is_decreased(byte tone_is_decreased) {
		this.tone_is_decreased = tone_is_decreased;
	}

	public byte getTone_is_increased() {
		return this.tone_is_increased;
	}

	public void setTone_is_increased(byte tone_is_increased) {
		this.tone_is_increased = tone_is_increased;
	}

	public byte getWide_based_gait() {
		return this.wide_based_gait;
	}

	public void setWide_based_gait(byte wide_based_gait) {
		this.wide_based_gait = wide_based_gait;
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