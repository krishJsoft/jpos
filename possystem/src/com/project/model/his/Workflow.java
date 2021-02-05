package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the workflows database table.
 * 
 */
@Entity
@Table(name="workflows")
public class Workflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int workFlow_Id;

	private byte abdominal_or_Flank_Pain;

	private byte aging_Problems;

	private byte allergic_Disease;

	private byte anxiety_or_Depression;

	private byte asthma_or_COPD_or_Wheezing;

	private byte back_or_Neck_Injury_or_Pain;

	private byte bacterial_or_Chlamydial_Infections;

	private byte bleeding_Wound;

	private byte blood_Vessel_and_Lymphatic;

	private byte bone_Decay;

	private byte bone_Fracture;

	private byte breast_Problems;

	private byte cancer;

	private byte cardiovascular;

	private byte chest_Pain;

	private byte congestive_Heart_Failure;

	private byte coronary_Artery_Disease;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte dental_Pain;

	private byte dermatologic;

	private byte diabetes_Mellitus_or_Hypoglycemia;

	private byte disorders_Due_to_Physical_Agents;

	private byte ear_Nose_and_Throat;

	private byte endocrine_Disorders;

	private byte epistaxis_or_Nasal_Foreign_Body;

	private byte exposed_Fracture;

	private byte extremity_Injury_or_Pain;

	private byte eye_and_Lids;

	private byte fluid_or_Electrolyte_Disorders;

	private byte gastrointestinal_Disorders;

	private byte general_Physical_Exam_Adult;

	private byte general_Physical_Exam_Child;

	private byte gynecologic;

	private byte headache;

	private byte hematologic_Disorder;

	private byte hemostasis_Thrombosis;

	private byte hepatitis_or_Liver_Failure;

	private byte HIV_or_AIDS;

	private byte hyperlipidemia;

	private byte hypertension;

	private byte infected_Wound;

	private byte infectious_Diseases_and_Antimicrobial_Therapy;

	private byte lipid_Disorders;

	private byte liver_Biliary_Track_or_Pancreas;

	private byte metabolic_Disease;

	private byte musculoskeletal;

	private byte mycotic_Infectious;

	private byte nausea_Vomiting_Diarrhea;

	private byte nervous_System_Disorder;

	private byte neurology;

	private byte nutritional_Disorders;

	private byte obesity;

	private byte osteoporosis;

	private byte other;

	@Column(length=200)
	private String other_value;

	private byte pediatric_Illness;

	private byte poisoning;

	private byte protozoal_and_Helminthic;

	private byte psychiatric_Disorder;

	private byte pulmonary;

	private byte resistance_to_antibiotic;

	private byte rheumatic;

	private byte seizure;

	private byte sleep_Apnea;

	private byte sore_Throat;

	private byte spirochetal;

	private byte thyroid_Disease;

	private byte upper_Respiratory_Symptoms;

	private byte urologic;

	private byte vascular_Occlusive_Disease;

	private byte viral_Diseases;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Workflow() {
	}

	public int getWorkFlow_Id() {
		return this.workFlow_Id;
	}

	public void setWorkFlow_Id(int workFlow_Id) {
		this.workFlow_Id = workFlow_Id;
	}

	public byte getAbdominal_or_Flank_Pain() {
		return this.abdominal_or_Flank_Pain;
	}

	public void setAbdominal_or_Flank_Pain(byte abdominal_or_Flank_Pain) {
		this.abdominal_or_Flank_Pain = abdominal_or_Flank_Pain;
	}

	public byte getAging_Problems() {
		return this.aging_Problems;
	}

	public void setAging_Problems(byte aging_Problems) {
		this.aging_Problems = aging_Problems;
	}

	public byte getAllergic_Disease() {
		return this.allergic_Disease;
	}

	public void setAllergic_Disease(byte allergic_Disease) {
		this.allergic_Disease = allergic_Disease;
	}

	public byte getAnxiety_or_Depression() {
		return this.anxiety_or_Depression;
	}

	public void setAnxiety_or_Depression(byte anxiety_or_Depression) {
		this.anxiety_or_Depression = anxiety_or_Depression;
	}

	public byte getAsthma_or_COPD_or_Wheezing() {
		return this.asthma_or_COPD_or_Wheezing;
	}

	public void setAsthma_or_COPD_or_Wheezing(byte asthma_or_COPD_or_Wheezing) {
		this.asthma_or_COPD_or_Wheezing = asthma_or_COPD_or_Wheezing;
	}

	public byte getBack_or_Neck_Injury_or_Pain() {
		return this.back_or_Neck_Injury_or_Pain;
	}

	public void setBack_or_Neck_Injury_or_Pain(byte back_or_Neck_Injury_or_Pain) {
		this.back_or_Neck_Injury_or_Pain = back_or_Neck_Injury_or_Pain;
	}

	public byte getBacterial_or_Chlamydial_Infections() {
		return this.bacterial_or_Chlamydial_Infections;
	}

	public void setBacterial_or_Chlamydial_Infections(byte bacterial_or_Chlamydial_Infections) {
		this.bacterial_or_Chlamydial_Infections = bacterial_or_Chlamydial_Infections;
	}

	public byte getBleeding_Wound() {
		return this.bleeding_Wound;
	}

	public void setBleeding_Wound(byte bleeding_Wound) {
		this.bleeding_Wound = bleeding_Wound;
	}

	public byte getBlood_Vessel_and_Lymphatic() {
		return this.blood_Vessel_and_Lymphatic;
	}

	public void setBlood_Vessel_and_Lymphatic(byte blood_Vessel_and_Lymphatic) {
		this.blood_Vessel_and_Lymphatic = blood_Vessel_and_Lymphatic;
	}

	public byte getBone_Decay() {
		return this.bone_Decay;
	}

	public void setBone_Decay(byte bone_Decay) {
		this.bone_Decay = bone_Decay;
	}

	public byte getBone_Fracture() {
		return this.bone_Fracture;
	}

	public void setBone_Fracture(byte bone_Fracture) {
		this.bone_Fracture = bone_Fracture;
	}

	public byte getBreast_Problems() {
		return this.breast_Problems;
	}

	public void setBreast_Problems(byte breast_Problems) {
		this.breast_Problems = breast_Problems;
	}

	public byte getCancer() {
		return this.cancer;
	}

	public void setCancer(byte cancer) {
		this.cancer = cancer;
	}

	public byte getCardiovascular() {
		return this.cardiovascular;
	}

	public void setCardiovascular(byte cardiovascular) {
		this.cardiovascular = cardiovascular;
	}

	public byte getChest_Pain() {
		return this.chest_Pain;
	}

	public void setChest_Pain(byte chest_Pain) {
		this.chest_Pain = chest_Pain;
	}

	public byte getCongestive_Heart_Failure() {
		return this.congestive_Heart_Failure;
	}

	public void setCongestive_Heart_Failure(byte congestive_Heart_Failure) {
		this.congestive_Heart_Failure = congestive_Heart_Failure;
	}

	public byte getCoronary_Artery_Disease() {
		return this.coronary_Artery_Disease;
	}

	public void setCoronary_Artery_Disease(byte coronary_Artery_Disease) {
		this.coronary_Artery_Disease = coronary_Artery_Disease;
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

	public byte getDental_Pain() {
		return this.dental_Pain;
	}

	public void setDental_Pain(byte dental_Pain) {
		this.dental_Pain = dental_Pain;
	}

	public byte getDermatologic() {
		return this.dermatologic;
	}

	public void setDermatologic(byte dermatologic) {
		this.dermatologic = dermatologic;
	}

	public byte getDiabetes_Mellitus_or_Hypoglycemia() {
		return this.diabetes_Mellitus_or_Hypoglycemia;
	}

	public void setDiabetes_Mellitus_or_Hypoglycemia(byte diabetes_Mellitus_or_Hypoglycemia) {
		this.diabetes_Mellitus_or_Hypoglycemia = diabetes_Mellitus_or_Hypoglycemia;
	}

	public byte getDisorders_Due_to_Physical_Agents() {
		return this.disorders_Due_to_Physical_Agents;
	}

	public void setDisorders_Due_to_Physical_Agents(byte disorders_Due_to_Physical_Agents) {
		this.disorders_Due_to_Physical_Agents = disorders_Due_to_Physical_Agents;
	}

	public byte getEar_Nose_and_Throat() {
		return this.ear_Nose_and_Throat;
	}

	public void setEar_Nose_and_Throat(byte ear_Nose_and_Throat) {
		this.ear_Nose_and_Throat = ear_Nose_and_Throat;
	}

	public byte getEndocrine_Disorders() {
		return this.endocrine_Disorders;
	}

	public void setEndocrine_Disorders(byte endocrine_Disorders) {
		this.endocrine_Disorders = endocrine_Disorders;
	}

	public byte getEpistaxis_or_Nasal_Foreign_Body() {
		return this.epistaxis_or_Nasal_Foreign_Body;
	}

	public void setEpistaxis_or_Nasal_Foreign_Body(byte epistaxis_or_Nasal_Foreign_Body) {
		this.epistaxis_or_Nasal_Foreign_Body = epistaxis_or_Nasal_Foreign_Body;
	}

	public byte getExposed_Fracture() {
		return this.exposed_Fracture;
	}

	public void setExposed_Fracture(byte exposed_Fracture) {
		this.exposed_Fracture = exposed_Fracture;
	}

	public byte getExtremity_Injury_or_Pain() {
		return this.extremity_Injury_or_Pain;
	}

	public void setExtremity_Injury_or_Pain(byte extremity_Injury_or_Pain) {
		this.extremity_Injury_or_Pain = extremity_Injury_or_Pain;
	}

	public byte getEye_and_Lids() {
		return this.eye_and_Lids;
	}

	public void setEye_and_Lids(byte eye_and_Lids) {
		this.eye_and_Lids = eye_and_Lids;
	}

	public byte getFluid_or_Electrolyte_Disorders() {
		return this.fluid_or_Electrolyte_Disorders;
	}

	public void setFluid_or_Electrolyte_Disorders(byte fluid_or_Electrolyte_Disorders) {
		this.fluid_or_Electrolyte_Disorders = fluid_or_Electrolyte_Disorders;
	}

	public byte getGastrointestinal_Disorders() {
		return this.gastrointestinal_Disorders;
	}

	public void setGastrointestinal_Disorders(byte gastrointestinal_Disorders) {
		this.gastrointestinal_Disorders = gastrointestinal_Disorders;
	}

	public byte getGeneral_Physical_Exam_Adult() {
		return this.general_Physical_Exam_Adult;
	}

	public void setGeneral_Physical_Exam_Adult(byte general_Physical_Exam_Adult) {
		this.general_Physical_Exam_Adult = general_Physical_Exam_Adult;
	}

	public byte getGeneral_Physical_Exam_Child() {
		return this.general_Physical_Exam_Child;
	}

	public void setGeneral_Physical_Exam_Child(byte general_Physical_Exam_Child) {
		this.general_Physical_Exam_Child = general_Physical_Exam_Child;
	}

	public byte getGynecologic() {
		return this.gynecologic;
	}

	public void setGynecologic(byte gynecologic) {
		this.gynecologic = gynecologic;
	}

	public byte getHeadache() {
		return this.headache;
	}

	public void setHeadache(byte headache) {
		this.headache = headache;
	}

	public byte getHematologic_Disorder() {
		return this.hematologic_Disorder;
	}

	public void setHematologic_Disorder(byte hematologic_Disorder) {
		this.hematologic_Disorder = hematologic_Disorder;
	}

	public byte getHemostasis_Thrombosis() {
		return this.hemostasis_Thrombosis;
	}

	public void setHemostasis_Thrombosis(byte hemostasis_Thrombosis) {
		this.hemostasis_Thrombosis = hemostasis_Thrombosis;
	}

	public byte getHepatitis_or_Liver_Failure() {
		return this.hepatitis_or_Liver_Failure;
	}

	public void setHepatitis_or_Liver_Failure(byte hepatitis_or_Liver_Failure) {
		this.hepatitis_or_Liver_Failure = hepatitis_or_Liver_Failure;
	}

	public byte getHIV_or_AIDS() {
		return this.HIV_or_AIDS;
	}

	public void setHIV_or_AIDS(byte HIV_or_AIDS) {
		this.HIV_or_AIDS = HIV_or_AIDS;
	}

	public byte getHyperlipidemia() {
		return this.hyperlipidemia;
	}

	public void setHyperlipidemia(byte hyperlipidemia) {
		this.hyperlipidemia = hyperlipidemia;
	}

	public byte getHypertension() {
		return this.hypertension;
	}

	public void setHypertension(byte hypertension) {
		this.hypertension = hypertension;
	}

	public byte getInfected_Wound() {
		return this.infected_Wound;
	}

	public void setInfected_Wound(byte infected_Wound) {
		this.infected_Wound = infected_Wound;
	}

	public byte getInfectious_Diseases_and_Antimicrobial_Therapy() {
		return this.infectious_Diseases_and_Antimicrobial_Therapy;
	}

	public void setInfectious_Diseases_and_Antimicrobial_Therapy(byte infectious_Diseases_and_Antimicrobial_Therapy) {
		this.infectious_Diseases_and_Antimicrobial_Therapy = infectious_Diseases_and_Antimicrobial_Therapy;
	}

	public byte getLipid_Disorders() {
		return this.lipid_Disorders;
	}

	public void setLipid_Disorders(byte lipid_Disorders) {
		this.lipid_Disorders = lipid_Disorders;
	}

	public byte getLiver_Biliary_Track_or_Pancreas() {
		return this.liver_Biliary_Track_or_Pancreas;
	}

	public void setLiver_Biliary_Track_or_Pancreas(byte liver_Biliary_Track_or_Pancreas) {
		this.liver_Biliary_Track_or_Pancreas = liver_Biliary_Track_or_Pancreas;
	}

	public byte getMetabolic_Disease() {
		return this.metabolic_Disease;
	}

	public void setMetabolic_Disease(byte metabolic_Disease) {
		this.metabolic_Disease = metabolic_Disease;
	}

	public byte getMusculoskeletal() {
		return this.musculoskeletal;
	}

	public void setMusculoskeletal(byte musculoskeletal) {
		this.musculoskeletal = musculoskeletal;
	}

	public byte getMycotic_Infectious() {
		return this.mycotic_Infectious;
	}

	public void setMycotic_Infectious(byte mycotic_Infectious) {
		this.mycotic_Infectious = mycotic_Infectious;
	}

	public byte getNausea_Vomiting_Diarrhea() {
		return this.nausea_Vomiting_Diarrhea;
	}

	public void setNausea_Vomiting_Diarrhea(byte nausea_Vomiting_Diarrhea) {
		this.nausea_Vomiting_Diarrhea = nausea_Vomiting_Diarrhea;
	}

	public byte getNervous_System_Disorder() {
		return this.nervous_System_Disorder;
	}

	public void setNervous_System_Disorder(byte nervous_System_Disorder) {
		this.nervous_System_Disorder = nervous_System_Disorder;
	}

	public byte getNeurology() {
		return this.neurology;
	}

	public void setNeurology(byte neurology) {
		this.neurology = neurology;
	}

	public byte getNutritional_Disorders() {
		return this.nutritional_Disorders;
	}

	public void setNutritional_Disorders(byte nutritional_Disorders) {
		this.nutritional_Disorders = nutritional_Disorders;
	}

	public byte getObesity() {
		return this.obesity;
	}

	public void setObesity(byte obesity) {
		this.obesity = obesity;
	}

	public byte getOsteoporosis() {
		return this.osteoporosis;
	}

	public void setOsteoporosis(byte osteoporosis) {
		this.osteoporosis = osteoporosis;
	}

	public byte getOther() {
		return this.other;
	}

	public void setOther(byte other) {
		this.other = other;
	}

	public String getOther_value() {
		return this.other_value;
	}

	public void setOther_value(String other_value) {
		this.other_value = other_value;
	}

	public byte getPediatric_Illness() {
		return this.pediatric_Illness;
	}

	public void setPediatric_Illness(byte pediatric_Illness) {
		this.pediatric_Illness = pediatric_Illness;
	}

	public byte getPoisoning() {
		return this.poisoning;
	}

	public void setPoisoning(byte poisoning) {
		this.poisoning = poisoning;
	}

	public byte getProtozoal_and_Helminthic() {
		return this.protozoal_and_Helminthic;
	}

	public void setProtozoal_and_Helminthic(byte protozoal_and_Helminthic) {
		this.protozoal_and_Helminthic = protozoal_and_Helminthic;
	}

	public byte getPsychiatric_Disorder() {
		return this.psychiatric_Disorder;
	}

	public void setPsychiatric_Disorder(byte psychiatric_Disorder) {
		this.psychiatric_Disorder = psychiatric_Disorder;
	}

	public byte getPulmonary() {
		return this.pulmonary;
	}

	public void setPulmonary(byte pulmonary) {
		this.pulmonary = pulmonary;
	}

	public byte getResistance_to_antibiotic() {
		return this.resistance_to_antibiotic;
	}

	public void setResistance_to_antibiotic(byte resistance_to_antibiotic) {
		this.resistance_to_antibiotic = resistance_to_antibiotic;
	}

	public byte getRheumatic() {
		return this.rheumatic;
	}

	public void setRheumatic(byte rheumatic) {
		this.rheumatic = rheumatic;
	}

	public byte getSeizure() {
		return this.seizure;
	}

	public void setSeizure(byte seizure) {
		this.seizure = seizure;
	}

	public byte getSleep_Apnea() {
		return this.sleep_Apnea;
	}

	public void setSleep_Apnea(byte sleep_Apnea) {
		this.sleep_Apnea = sleep_Apnea;
	}

	public byte getSore_Throat() {
		return this.sore_Throat;
	}

	public void setSore_Throat(byte sore_Throat) {
		this.sore_Throat = sore_Throat;
	}

	public byte getSpirochetal() {
		return this.spirochetal;
	}

	public void setSpirochetal(byte spirochetal) {
		this.spirochetal = spirochetal;
	}

	public byte getThyroid_Disease() {
		return this.thyroid_Disease;
	}

	public void setThyroid_Disease(byte thyroid_Disease) {
		this.thyroid_Disease = thyroid_Disease;
	}

	public byte getUpper_Respiratory_Symptoms() {
		return this.upper_Respiratory_Symptoms;
	}

	public void setUpper_Respiratory_Symptoms(byte upper_Respiratory_Symptoms) {
		this.upper_Respiratory_Symptoms = upper_Respiratory_Symptoms;
	}

	public byte getUrologic() {
		return this.urologic;
	}

	public void setUrologic(byte urologic) {
		this.urologic = urologic;
	}

	public byte getVascular_Occlusive_Disease() {
		return this.vascular_Occlusive_Disease;
	}

	public void setVascular_Occlusive_Disease(byte vascular_Occlusive_Disease) {
		this.vascular_Occlusive_Disease = vascular_Occlusive_Disease;
	}

	public byte getViral_Diseases() {
		return this.viral_Diseases;
	}

	public void setViral_Diseases(byte viral_Diseases) {
		this.viral_Diseases = viral_Diseases;
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}