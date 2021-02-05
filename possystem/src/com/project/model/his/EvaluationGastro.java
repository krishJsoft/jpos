package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_gastros database table.
 * 
 */
@Entity
@Table(name="evaluation_gastros")
public class EvaluationGastro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Gastro_id;

	private byte abdomen_WNL;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte enlarged_Liver;

	private byte enlarged_spleen;

	@Lob
	private String evaluation_Gastro_Notes;

	private byte liver_and_spleen_palpitation_WNL;

	private byte mass_present_LLQ;

	private byte mass_present_LUQ;

	private byte mass_present_RLQ;

	private byte mass_present_RUQ;

	private byte pulsatile;

	private byte unable_To_palpate_Liver;

	private byte unable_To_palpate_Spleen;

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

	public EvaluationGastro() {
	}

	public int getEvaluation_Gastro_id() {
		return this.evaluation_Gastro_id;
	}

	public void setEvaluation_Gastro_id(int evaluation_Gastro_id) {
		this.evaluation_Gastro_id = evaluation_Gastro_id;
	}

	public byte getAbdomen_WNL() {
		return this.abdomen_WNL;
	}

	public void setAbdomen_WNL(byte abdomen_WNL) {
		this.abdomen_WNL = abdomen_WNL;
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

	public byte getEnlarged_Liver() {
		return this.enlarged_Liver;
	}

	public void setEnlarged_Liver(byte enlarged_Liver) {
		this.enlarged_Liver = enlarged_Liver;
	}

	public byte getEnlarged_spleen() {
		return this.enlarged_spleen;
	}

	public void setEnlarged_spleen(byte enlarged_spleen) {
		this.enlarged_spleen = enlarged_spleen;
	}

	public String getEvaluation_Gastro_Notes() {
		return this.evaluation_Gastro_Notes;
	}

	public void setEvaluation_Gastro_Notes(String evaluation_Gastro_Notes) {
		this.evaluation_Gastro_Notes = evaluation_Gastro_Notes;
	}

	public byte getLiver_and_spleen_palpitation_WNL() {
		return this.liver_and_spleen_palpitation_WNL;
	}

	public void setLiver_and_spleen_palpitation_WNL(byte liver_and_spleen_palpitation_WNL) {
		this.liver_and_spleen_palpitation_WNL = liver_and_spleen_palpitation_WNL;
	}

	public byte getMass_present_LLQ() {
		return this.mass_present_LLQ;
	}

	public void setMass_present_LLQ(byte mass_present_LLQ) {
		this.mass_present_LLQ = mass_present_LLQ;
	}

	public byte getMass_present_LUQ() {
		return this.mass_present_LUQ;
	}

	public void setMass_present_LUQ(byte mass_present_LUQ) {
		this.mass_present_LUQ = mass_present_LUQ;
	}

	public byte getMass_present_RLQ() {
		return this.mass_present_RLQ;
	}

	public void setMass_present_RLQ(byte mass_present_RLQ) {
		this.mass_present_RLQ = mass_present_RLQ;
	}

	public byte getMass_present_RUQ() {
		return this.mass_present_RUQ;
	}

	public void setMass_present_RUQ(byte mass_present_RUQ) {
		this.mass_present_RUQ = mass_present_RUQ;
	}

	public byte getPulsatile() {
		return this.pulsatile;
	}

	public void setPulsatile(byte pulsatile) {
		this.pulsatile = pulsatile;
	}

	public byte getUnable_To_palpate_Liver() {
		return this.unable_To_palpate_Liver;
	}

	public void setUnable_To_palpate_Liver(byte unable_To_palpate_Liver) {
		this.unable_To_palpate_Liver = unable_To_palpate_Liver;
	}

	public byte getUnable_To_palpate_Spleen() {
		return this.unable_To_palpate_Spleen;
	}

	public void setUnable_To_palpate_Spleen(byte unable_To_palpate_Spleen) {
		this.unable_To_palpate_Spleen = unable_To_palpate_Spleen;
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