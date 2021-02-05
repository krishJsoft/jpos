package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_extrems database table.
 * 
 */
@Entity
@Table(name="evaluation_extrems")
public class EvaluationExtrem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Extrem_id;

	private byte clubbing;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte cyanosis;

	private byte e_Left;

	private byte e_Right;

	@Lob
	private String evaluation_Extrem_Notes;

	private byte extremities_WNL;

	private byte petechiae;

	private byte synovitis;

	@Column(length=30)
	private String value;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationExtrem() {
	}

	public int getEvaluation_Extrem_id() {
		return this.evaluation_Extrem_id;
	}

	public void setEvaluation_Extrem_id(int evaluation_Extrem_id) {
		this.evaluation_Extrem_id = evaluation_Extrem_id;
	}

	public byte getClubbing() {
		return this.clubbing;
	}

	public void setClubbing(byte clubbing) {
		this.clubbing = clubbing;
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

	public byte getCyanosis() {
		return this.cyanosis;
	}

	public void setCyanosis(byte cyanosis) {
		this.cyanosis = cyanosis;
	}

	public byte getE_Left() {
		return this.e_Left;
	}

	public void setE_Left(byte e_Left) {
		this.e_Left = e_Left;
	}

	public byte getE_Right() {
		return this.e_Right;
	}

	public void setE_Right(byte e_Right) {
		this.e_Right = e_Right;
	}

	public String getEvaluation_Extrem_Notes() {
		return this.evaluation_Extrem_Notes;
	}

	public void setEvaluation_Extrem_Notes(String evaluation_Extrem_Notes) {
		this.evaluation_Extrem_Notes = evaluation_Extrem_Notes;
	}

	public byte getExtremities_WNL() {
		return this.extremities_WNL;
	}

	public void setExtremities_WNL(byte extremities_WNL) {
		this.extremities_WNL = extremities_WNL;
	}

	public byte getPetechiae() {
		return this.petechiae;
	}

	public void setPetechiae(byte petechiae) {
		this.petechiae = petechiae;
	}

	public byte getSynovitis() {
		return this.synovitis;
	}

	public void setSynovitis(byte synovitis) {
		this.synovitis = synovitis;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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