package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_respis database table.
 * 
 */
@Entity
@Table(name="evaluation_respis")
public class EvaluationRespi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Resp_id;

	private byte accessory_muscle_use;

	private byte auscultation_WNL;

	private byte bronchial_breath_sounds;

	private byte chest_is_free_of_defects_Expands_Normally_and_Symmetrically;

	private byte chest_percussion_WNL;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte dull_Lt;

	private byte dull_Rt;

	private byte dullness_to_percussion;

	private byte egophony;

	private byte erythema_Consistent_with_Radiation_Dermatitis;

	@Lob
	private String evaluation_Resp_Notes;

	private byte hyper_Lt;

	private byte hyper_Rt;

	private byte hyperresonance;

	private byte intercostal_retractions;

	private byte other_Scar;

	@Column(length=100)
	private String other_Scar_desc;

	private byte paradoxic_movements;

	private byte rales;

	private byte resp_effort_WNL;

	private byte rhonchi;

	private byte rub_present;

	private byte scarring_consistent_with_old_healed_radiation_dermatitis;

	private byte surgical_scar_present;

	@Column(length=30)
	private String tactile_Changed_Value;

	private byte tactile_fremitus_Decreased;

	private byte tactile_fremitus_Increased;

	private byte tactile_fremitus_WNL;

	private byte wheezes;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationRespi() {
	}

	public int getEvaluation_Resp_id() {
		return this.evaluation_Resp_id;
	}

	public void setEvaluation_Resp_id(int evaluation_Resp_id) {
		this.evaluation_Resp_id = evaluation_Resp_id;
	}

	public byte getAccessory_muscle_use() {
		return this.accessory_muscle_use;
	}

	public void setAccessory_muscle_use(byte accessory_muscle_use) {
		this.accessory_muscle_use = accessory_muscle_use;
	}

	public byte getAuscultation_WNL() {
		return this.auscultation_WNL;
	}

	public void setAuscultation_WNL(byte auscultation_WNL) {
		this.auscultation_WNL = auscultation_WNL;
	}

	public byte getBronchial_breath_sounds() {
		return this.bronchial_breath_sounds;
	}

	public void setBronchial_breath_sounds(byte bronchial_breath_sounds) {
		this.bronchial_breath_sounds = bronchial_breath_sounds;
	}

	public byte getChest_is_free_of_defects_Expands_Normally_and_Symmetrically() {
		return this.chest_is_free_of_defects_Expands_Normally_and_Symmetrically;
	}

	public void setChest_is_free_of_defects_Expands_Normally_and_Symmetrically(byte chest_is_free_of_defects_Expands_Normally_and_Symmetrically) {
		this.chest_is_free_of_defects_Expands_Normally_and_Symmetrically = chest_is_free_of_defects_Expands_Normally_and_Symmetrically;
	}

	public byte getChest_percussion_WNL() {
		return this.chest_percussion_WNL;
	}

	public void setChest_percussion_WNL(byte chest_percussion_WNL) {
		this.chest_percussion_WNL = chest_percussion_WNL;
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

	public byte getDull_Lt() {
		return this.dull_Lt;
	}

	public void setDull_Lt(byte dull_Lt) {
		this.dull_Lt = dull_Lt;
	}

	public byte getDull_Rt() {
		return this.dull_Rt;
	}

	public void setDull_Rt(byte dull_Rt) {
		this.dull_Rt = dull_Rt;
	}

	public byte getDullness_to_percussion() {
		return this.dullness_to_percussion;
	}

	public void setDullness_to_percussion(byte dullness_to_percussion) {
		this.dullness_to_percussion = dullness_to_percussion;
	}

	public byte getEgophony() {
		return this.egophony;
	}

	public void setEgophony(byte egophony) {
		this.egophony = egophony;
	}

	public byte getErythema_Consistent_with_Radiation_Dermatitis() {
		return this.erythema_Consistent_with_Radiation_Dermatitis;
	}

	public void setErythema_Consistent_with_Radiation_Dermatitis(byte erythema_Consistent_with_Radiation_Dermatitis) {
		this.erythema_Consistent_with_Radiation_Dermatitis = erythema_Consistent_with_Radiation_Dermatitis;
	}

	public String getEvaluation_Resp_Notes() {
		return this.evaluation_Resp_Notes;
	}

	public void setEvaluation_Resp_Notes(String evaluation_Resp_Notes) {
		this.evaluation_Resp_Notes = evaluation_Resp_Notes;
	}

	public byte getHyper_Lt() {
		return this.hyper_Lt;
	}

	public void setHyper_Lt(byte hyper_Lt) {
		this.hyper_Lt = hyper_Lt;
	}

	public byte getHyper_Rt() {
		return this.hyper_Rt;
	}

	public void setHyper_Rt(byte hyper_Rt) {
		this.hyper_Rt = hyper_Rt;
	}

	public byte getHyperresonance() {
		return this.hyperresonance;
	}

	public void setHyperresonance(byte hyperresonance) {
		this.hyperresonance = hyperresonance;
	}

	public byte getIntercostal_retractions() {
		return this.intercostal_retractions;
	}

	public void setIntercostal_retractions(byte intercostal_retractions) {
		this.intercostal_retractions = intercostal_retractions;
	}

	public byte getOther_Scar() {
		return this.other_Scar;
	}

	public void setOther_Scar(byte other_Scar) {
		this.other_Scar = other_Scar;
	}

	public String getOther_Scar_desc() {
		return this.other_Scar_desc;
	}

	public void setOther_Scar_desc(String other_Scar_desc) {
		this.other_Scar_desc = other_Scar_desc;
	}

	public byte getParadoxic_movements() {
		return this.paradoxic_movements;
	}

	public void setParadoxic_movements(byte paradoxic_movements) {
		this.paradoxic_movements = paradoxic_movements;
	}

	public byte getRales() {
		return this.rales;
	}

	public void setRales(byte rales) {
		this.rales = rales;
	}

	public byte getResp_effort_WNL() {
		return this.resp_effort_WNL;
	}

	public void setResp_effort_WNL(byte resp_effort_WNL) {
		this.resp_effort_WNL = resp_effort_WNL;
	}

	public byte getRhonchi() {
		return this.rhonchi;
	}

	public void setRhonchi(byte rhonchi) {
		this.rhonchi = rhonchi;
	}

	public byte getRub_present() {
		return this.rub_present;
	}

	public void setRub_present(byte rub_present) {
		this.rub_present = rub_present;
	}

	public byte getScarring_consistent_with_old_healed_radiation_dermatitis() {
		return this.scarring_consistent_with_old_healed_radiation_dermatitis;
	}

	public void setScarring_consistent_with_old_healed_radiation_dermatitis(byte scarring_consistent_with_old_healed_radiation_dermatitis) {
		this.scarring_consistent_with_old_healed_radiation_dermatitis = scarring_consistent_with_old_healed_radiation_dermatitis;
	}

	public byte getSurgical_scar_present() {
		return this.surgical_scar_present;
	}

	public void setSurgical_scar_present(byte surgical_scar_present) {
		this.surgical_scar_present = surgical_scar_present;
	}

	public String getTactile_Changed_Value() {
		return this.tactile_Changed_Value;
	}

	public void setTactile_Changed_Value(String tactile_Changed_Value) {
		this.tactile_Changed_Value = tactile_Changed_Value;
	}

	public byte getTactile_fremitus_Decreased() {
		return this.tactile_fremitus_Decreased;
	}

	public void setTactile_fremitus_Decreased(byte tactile_fremitus_Decreased) {
		this.tactile_fremitus_Decreased = tactile_fremitus_Decreased;
	}

	public byte getTactile_fremitus_Increased() {
		return this.tactile_fremitus_Increased;
	}

	public void setTactile_fremitus_Increased(byte tactile_fremitus_Increased) {
		this.tactile_fremitus_Increased = tactile_fremitus_Increased;
	}

	public byte getTactile_fremitus_WNL() {
		return this.tactile_fremitus_WNL;
	}

	public void setTactile_fremitus_WNL(byte tactile_fremitus_WNL) {
		this.tactile_fremitus_WNL = tactile_fremitus_WNL;
	}

	public byte getWheezes() {
		return this.wheezes;
	}

	public void setWheezes(byte wheezes) {
		this.wheezes = wheezes;
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