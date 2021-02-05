package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_ents database table.
 * 
 */
@Entity
@Table(name="evaluation_ents")
public class EvaluationEnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Ent_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte dental_caries;

	private byte dentition_and_gums_WNL;

	@Lob
	private String evaluation_Ent_Notes;

	private byte gingivitis;

	private byte maliampati_I;

	private byte maliampati_II;

	private byte maliampati_III;

	private byte maliampati_IV;

	private byte nasal_mucosa_septum_and_turbinates_Edema_or_erythema_present;

	private byte nasal_mucosa_septum_and_turbinates_WNL;

	private byte oral_Petechiae;

	private byte oral_ulcers;

	private byte oropharynx_Edema_or_Erythema_present;

	private byte oropharynx_WNL;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationEnt() {
	}

	public int getEvaluation_Ent_id() {
		return this.evaluation_Ent_id;
	}

	public void setEvaluation_Ent_id(int evaluation_Ent_id) {
		this.evaluation_Ent_id = evaluation_Ent_id;
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

	public byte getDental_caries() {
		return this.dental_caries;
	}

	public void setDental_caries(byte dental_caries) {
		this.dental_caries = dental_caries;
	}

	public byte getDentition_and_gums_WNL() {
		return this.dentition_and_gums_WNL;
	}

	public void setDentition_and_gums_WNL(byte dentition_and_gums_WNL) {
		this.dentition_and_gums_WNL = dentition_and_gums_WNL;
	}

	public String getEvaluation_Ent_Notes() {
		return this.evaluation_Ent_Notes;
	}

	public void setEvaluation_Ent_Notes(String evaluation_Ent_Notes) {
		this.evaluation_Ent_Notes = evaluation_Ent_Notes;
	}

	public byte getGingivitis() {
		return this.gingivitis;
	}

	public void setGingivitis(byte gingivitis) {
		this.gingivitis = gingivitis;
	}

	public byte getMaliampati_I() {
		return this.maliampati_I;
	}

	public void setMaliampati_I(byte maliampati_I) {
		this.maliampati_I = maliampati_I;
	}

	public byte getMaliampati_II() {
		return this.maliampati_II;
	}

	public void setMaliampati_II(byte maliampati_II) {
		this.maliampati_II = maliampati_II;
	}

	public byte getMaliampati_III() {
		return this.maliampati_III;
	}

	public void setMaliampati_III(byte maliampati_III) {
		this.maliampati_III = maliampati_III;
	}

	public byte getMaliampati_IV() {
		return this.maliampati_IV;
	}

	public void setMaliampati_IV(byte maliampati_IV) {
		this.maliampati_IV = maliampati_IV;
	}

	public byte getNasal_mucosa_septum_and_turbinates_Edema_or_erythema_present() {
		return this.nasal_mucosa_septum_and_turbinates_Edema_or_erythema_present;
	}

	public void setNasal_mucosa_septum_and_turbinates_Edema_or_erythema_present(byte nasal_mucosa_septum_and_turbinates_Edema_or_erythema_present) {
		this.nasal_mucosa_septum_and_turbinates_Edema_or_erythema_present = nasal_mucosa_septum_and_turbinates_Edema_or_erythema_present;
	}

	public byte getNasal_mucosa_septum_and_turbinates_WNL() {
		return this.nasal_mucosa_septum_and_turbinates_WNL;
	}

	public void setNasal_mucosa_septum_and_turbinates_WNL(byte nasal_mucosa_septum_and_turbinates_WNL) {
		this.nasal_mucosa_septum_and_turbinates_WNL = nasal_mucosa_septum_and_turbinates_WNL;
	}

	public byte getOral_Petechiae() {
		return this.oral_Petechiae;
	}

	public void setOral_Petechiae(byte oral_Petechiae) {
		this.oral_Petechiae = oral_Petechiae;
	}

	public byte getOral_ulcers() {
		return this.oral_ulcers;
	}

	public void setOral_ulcers(byte oral_ulcers) {
		this.oral_ulcers = oral_ulcers;
	}

	public byte getOropharynx_Edema_or_Erythema_present() {
		return this.oropharynx_Edema_or_Erythema_present;
	}

	public void setOropharynx_Edema_or_Erythema_present(byte oropharynx_Edema_or_Erythema_present) {
		this.oropharynx_Edema_or_Erythema_present = oropharynx_Edema_or_Erythema_present;
	}

	public byte getOropharynx_WNL() {
		return this.oropharynx_WNL;
	}

	public void setOropharynx_WNL(byte oropharynx_WNL) {
		this.oropharynx_WNL = oropharynx_WNL;
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