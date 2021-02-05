package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_skins database table.
 * 
 */
@Entity
@Table(name="evaluation_skins")
public class EvaluationSkin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Skin_id;

	private byte bullae;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte ecchymoses;

	@Lob
	private String evaluation_Skin_Notes;

	private byte other_Skin_Disease;

	@Column(length=50)
	private String other_Skin_Disease_Value;

	private byte pressure_Ulcer_Stage_1;

	private byte pressure_Ulcer_Stage_2;

	private byte pressure_Ulcer_Stage_3;

	private byte pressure_Ulcer_Stage_4;

	private byte rash;

	private byte skin_WNL;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationSkin() {
	}

	public int getEvaluation_Skin_id() {
		return this.evaluation_Skin_id;
	}

	public void setEvaluation_Skin_id(int evaluation_Skin_id) {
		this.evaluation_Skin_id = evaluation_Skin_id;
	}

	public byte getBullae() {
		return this.bullae;
	}

	public void setBullae(byte bullae) {
		this.bullae = bullae;
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

	public byte getEcchymoses() {
		return this.ecchymoses;
	}

	public void setEcchymoses(byte ecchymoses) {
		this.ecchymoses = ecchymoses;
	}

	public String getEvaluation_Skin_Notes() {
		return this.evaluation_Skin_Notes;
	}

	public void setEvaluation_Skin_Notes(String evaluation_Skin_Notes) {
		this.evaluation_Skin_Notes = evaluation_Skin_Notes;
	}

	public byte getOther_Skin_Disease() {
		return this.other_Skin_Disease;
	}

	public void setOther_Skin_Disease(byte other_Skin_Disease) {
		this.other_Skin_Disease = other_Skin_Disease;
	}

	public String getOther_Skin_Disease_Value() {
		return this.other_Skin_Disease_Value;
	}

	public void setOther_Skin_Disease_Value(String other_Skin_Disease_Value) {
		this.other_Skin_Disease_Value = other_Skin_Disease_Value;
	}

	public byte getPressure_Ulcer_Stage_1() {
		return this.pressure_Ulcer_Stage_1;
	}

	public void setPressure_Ulcer_Stage_1(byte pressure_Ulcer_Stage_1) {
		this.pressure_Ulcer_Stage_1 = pressure_Ulcer_Stage_1;
	}

	public byte getPressure_Ulcer_Stage_2() {
		return this.pressure_Ulcer_Stage_2;
	}

	public void setPressure_Ulcer_Stage_2(byte pressure_Ulcer_Stage_2) {
		this.pressure_Ulcer_Stage_2 = pressure_Ulcer_Stage_2;
	}

	public byte getPressure_Ulcer_Stage_3() {
		return this.pressure_Ulcer_Stage_3;
	}

	public void setPressure_Ulcer_Stage_3(byte pressure_Ulcer_Stage_3) {
		this.pressure_Ulcer_Stage_3 = pressure_Ulcer_Stage_3;
	}

	public byte getPressure_Ulcer_Stage_4() {
		return this.pressure_Ulcer_Stage_4;
	}

	public void setPressure_Ulcer_Stage_4(byte pressure_Ulcer_Stage_4) {
		this.pressure_Ulcer_Stage_4 = pressure_Ulcer_Stage_4;
	}

	public byte getRash() {
		return this.rash;
	}

	public void setRash(byte rash) {
		this.rash = rash;
	}

	public byte getSkin_WNL() {
		return this.skin_WNL;
	}

	public void setSkin_WNL(byte skin_WNL) {
		this.skin_WNL = skin_WNL;
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