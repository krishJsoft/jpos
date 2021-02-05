package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_necks database table.
 * 
 */
@Entity
@Table(name="evaluation_necks")
public class EvaluationNeck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Neck_id;

	@Column(name="a_v_or_cannon_a_waves_present")
	private byte aVOrCannonAWavesPresent;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte erythema_or_scarring_Consistent_with;

	@Lob
	private String evaluation_Neck_Notes;

	private byte jugular_Veins_WNL;

	private byte JVD_Present;

	private byte neck_mass;

	@Column(length=30)
	private String neck_mass_value;

	private byte neck_WNL;

	private byte nodules_Palpable;

	private byte old_Radiation_dermatitis;

	private byte recent_Radiation_dermatitis;

	private byte thyroid_WNL;

	private byte thyromegaly;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationNeck() {
	}

	public int getEvaluation_Neck_id() {
		return this.evaluation_Neck_id;
	}

	public void setEvaluation_Neck_id(int evaluation_Neck_id) {
		this.evaluation_Neck_id = evaluation_Neck_id;
	}

	public byte getAVOrCannonAWavesPresent() {
		return this.aVOrCannonAWavesPresent;
	}

	public void setAVOrCannonAWavesPresent(byte aVOrCannonAWavesPresent) {
		this.aVOrCannonAWavesPresent = aVOrCannonAWavesPresent;
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

	public byte getErythema_or_scarring_Consistent_with() {
		return this.erythema_or_scarring_Consistent_with;
	}

	public void setErythema_or_scarring_Consistent_with(byte erythema_or_scarring_Consistent_with) {
		this.erythema_or_scarring_Consistent_with = erythema_or_scarring_Consistent_with;
	}

	public String getEvaluation_Neck_Notes() {
		return this.evaluation_Neck_Notes;
	}

	public void setEvaluation_Neck_Notes(String evaluation_Neck_Notes) {
		this.evaluation_Neck_Notes = evaluation_Neck_Notes;
	}

	public byte getJugular_Veins_WNL() {
		return this.jugular_Veins_WNL;
	}

	public void setJugular_Veins_WNL(byte jugular_Veins_WNL) {
		this.jugular_Veins_WNL = jugular_Veins_WNL;
	}

	public byte getJVD_Present() {
		return this.JVD_Present;
	}

	public void setJVD_Present(byte JVD_Present) {
		this.JVD_Present = JVD_Present;
	}

	public byte getNeck_mass() {
		return this.neck_mass;
	}

	public void setNeck_mass(byte neck_mass) {
		this.neck_mass = neck_mass;
	}

	public String getNeck_mass_value() {
		return this.neck_mass_value;
	}

	public void setNeck_mass_value(String neck_mass_value) {
		this.neck_mass_value = neck_mass_value;
	}

	public byte getNeck_WNL() {
		return this.neck_WNL;
	}

	public void setNeck_WNL(byte neck_WNL) {
		this.neck_WNL = neck_WNL;
	}

	public byte getNodules_Palpable() {
		return this.nodules_Palpable;
	}

	public void setNodules_Palpable(byte nodules_Palpable) {
		this.nodules_Palpable = nodules_Palpable;
	}

	public byte getOld_Radiation_dermatitis() {
		return this.old_Radiation_dermatitis;
	}

	public void setOld_Radiation_dermatitis(byte old_Radiation_dermatitis) {
		this.old_Radiation_dermatitis = old_Radiation_dermatitis;
	}

	public byte getRecent_Radiation_dermatitis() {
		return this.recent_Radiation_dermatitis;
	}

	public void setRecent_Radiation_dermatitis(byte recent_Radiation_dermatitis) {
		this.recent_Radiation_dermatitis = recent_Radiation_dermatitis;
	}

	public byte getThyroid_WNL() {
		return this.thyroid_WNL;
	}

	public void setThyroid_WNL(byte thyroid_WNL) {
		this.thyroid_WNL = thyroid_WNL;
	}

	public byte getThyromegaly() {
		return this.thyromegaly;
	}

	public void setThyromegaly(byte thyromegaly) {
		this.thyromegaly = thyromegaly;
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