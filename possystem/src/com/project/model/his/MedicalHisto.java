package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the medical_histos database table.
 * 
 */
@Entity
@Table(name="medical_histos")
public class MedicalHisto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int medical_History_Id;

	private byte achalasia;

	private byte adrenal_dysfunction;

	private byte alcohol_Use;

	private byte antibiotic_use;

	private byte anxiety;

	private byte arrhythmias;

	private byte arteriovenous_Malformations;

	private byte arthritis;

	private byte arthritis_RA;

	private byte asthma;

	private byte autoimmune_hepatitis;

	private byte barretts_esophagus;

	private byte benign_Liver_biopsy;

	private byte benign_Polypectomy;

	private byte benzodiazepines;

	private byte bipolar_disorder;

	private byte blood_clots;

	private byte blood_clots_DVT;

	private byte blood_clots_PE;

	private byte budd_Chiari_Syndrome;

	private byte buproprion_or_nortriptyline;

	private byte c_difficile_Colitis;

	private byte cabg;

	private byte carcinoid_Syndrome;

	private byte celiac_Disease;

	private byte chemical_or_fires;

	private byte chemotherapy;

	private byte cholecystitis;

	private byte cholelithiasis;

	private byte cirrhosis;

	@Column(nullable=false)
	private int clinicChart_Id;

	private byte colitis;

	private byte colonoscopy;

	private byte congestive_Heart_Failure;

	private byte copd;

	private byte coronary_Artery_Disease;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte crohn_Disease;

	private byte current_Suicide_attempts;

	private byte cystic_Fibrosis;

	private byte depression;

	private byte diabetes;

	private byte diabetes_Type_1;

	private byte diabetes_Type_2;

	private byte diabetes1;

	private byte diabetes2;

	private byte diverticulitis;

	private byte diverticulosis;

	private byte drug_Dependency;

	private byte drug_Ingestion;

	private byte drug_Inhalation;

	private byte drug_Injection;

	private byte ECHO_Stress_test;

	private byte endocarditis;

	private byte esophageal_dilation;

	private byte esophageal_Stricture;

	private byte esophageal_Varices;

	private byte ever_smoker;

	private byte familial_Mediterranean_Fever;

	private byte full_Colectomy;

	private byte gastric_bypass;

	private byte gastric_volume_reduction;

	private byte gerd;

	private byte gout;

	private byte h_pylori_positive;

	private byte heart_Failure;

	private byte hemochromatosis;

	private byte hemodialysis;

	private byte hemolytic_anemia;

	private byte hemorrhoids;

	private byte hepatic_dysfunction;

	private byte hepatitis;

	private byte hernia;

	private byte herpes;

	private byte histiocytosis;

	private byte history_of_Electroconvulsive_Shock_Therapy;

	@Column(name="HIV_AIDS")
	private byte hivAids;

	private byte homicidal_Ideation;

	private byte hospitalizations_for_psychiatric_illnesses;

	private byte hypertension;

	private byte immunosuppressive_therapy;

	private byte inflammatory_Bowel_Disease;

	private byte inorganic_dust;

	private byte ischemic_colitis;

	private byte jaundice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date last_Chemotherapy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date last_cTx;

	private byte last_rTX;

	private byte liver_disease;

	private byte lung_resection;

	private byte malaria;

	private byte malignancy_Breast;

	private byte malignancy_Colon;

	private byte malignancy_Colorectal;

	private byte malignancy_Hepatic;

	private byte malignancy_Leukemia_Lymphoma;

	private byte malignancy_Lung;

	private byte malignancy_Melanoma;

	private byte malignancy_Other;

	private byte malignancy_Pituitary;

	private byte malignancy_Prostate;

	private byte malignancy_Renal_cell;

	private byte malignancy_Testicular;

	private byte malignancy_Thyroid;

	private byte malignant_Hyperthermia;

	private byte malignant_Liver_biopsy;

	private byte malignant_Polypectomy;

	private byte mammogram;

	private byte mania;

	private byte military_Experience;

	private byte narcotics;

	private byte neoroleptic_Malignant_Syndrome;

	private byte neuromuscular_weakness;

	private byte nicotine_receptor_blockade;

	private byte nicotine_replacement;

	private byte nonalcoholic_Fatty_Liver_Disease;

	private byte noxious_fumes;

	private byte num_Cigarettes_per_day;

	private byte num_Drinks_per_day;

	private byte num_Drinks_Per_week;

	private byte num_Packs_per_day;

	private byte organ_failure;

	private byte organ_transplant;

	private byte organic_dust;

	private byte osteoporosis;

	private byte other_Surgery;

	private byte pancreatic_stent;

	private byte pancreatitis;

	private byte pap_Smear;

	private byte parasitic_infections;

	private byte partial_Colectomy;

	private byte PEG_PEJ_tube;

	private byte peripheral_Artery_Disease;

	private byte peritoneal_dialysis;

	private byte peritonitis;

	private byte personality_disorder;

	private byte PFTs;

	private byte pituitary_infarct_or_hemorrhage;

	private byte pleurodesis;

	private byte polyps;

	private byte porphyria;

	private byte prior_intubations;

	private byte prior_Suicide_attempts;

	private byte protein_deficiency_C;

	private byte protein_deficiency_S;

	private byte psychosis;

	@Temporal(TemporalType.TIMESTAMP)
	private Date quit_Tobacco_date;

	private byte radiation;

	private byte radiation_Exposure;

	private byte radio_ablation;

	private byte recreational_Drug_Use;

	private byte renal_dysfunction;

	private byte renal_dysfunction_ESRD;

	private byte sarcoidosis;

	private byte schizophrenia;

	private byte sclerosing_cholangitis;

	private byte seizure_disorder;

	private byte seizures;

	private byte sickle_Cell_Disease;

	private byte sleep_Apnea;

	private byte sleep_Apnea_;

	private byte sleep_Apnea_BiPAP;

	private byte sleep_Apnea_CPAP;

	private byte sleep_study;

	private byte smoke_Yes;

	private byte splenectomy;

	private byte steroid_Use_for_other;

	private byte steroid_use_Pain;

	private byte suicidal_ideation;

	private byte surgical_Resection;

	private byte syphilis;

	private byte systemic_Lupus_Erythematosis;

	private byte thrombocytopenia;

	private byte thrombocytopenia_ITP;

	private byte thrombocytopenia_TTP;

	private byte thyroid_disease;

	private byte thyroid_disease_hyper;

	private byte thyroid_disease_hypo;

	private byte thyroid_hyper;

	private byte thyroid_hypo;

	private byte tuberculosis;

	private byte tuberculosis_PPD;

	private byte tuberculosis_Test_Date;

	private byte tuberculosis_Test_Negative;

	private byte tuberculosis_Test_Positive;

	private byte tuberculosis_Treatment;

	private byte ulcerative_colitis;

	private byte ulcers;

	private byte vl_Poma;

	private byte whipple_s_disease;

	private byte willing_to_quit_Alcohol;

	private byte willing_to_quit_Drugs_Rec;

	private byte willing_to_quit_Medicated_Drugs;

	private byte willing_to_quit_Tobacco;

	private byte wilson_s_Disease;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	private byte zollinger_Ellison_Syndrome;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public MedicalHisto() {
	}

	public int getMedical_History_Id() {
		return this.medical_History_Id;
	}

	public void setMedical_History_Id(int medical_History_Id) {
		this.medical_History_Id = medical_History_Id;
	}

	public byte getAchalasia() {
		return this.achalasia;
	}

	public void setAchalasia(byte achalasia) {
		this.achalasia = achalasia;
	}

	public byte getAdrenal_dysfunction() {
		return this.adrenal_dysfunction;
	}

	public void setAdrenal_dysfunction(byte adrenal_dysfunction) {
		this.adrenal_dysfunction = adrenal_dysfunction;
	}

	public byte getAlcohol_Use() {
		return this.alcohol_Use;
	}

	public void setAlcohol_Use(byte alcohol_Use) {
		this.alcohol_Use = alcohol_Use;
	}

	public byte getAntibiotic_use() {
		return this.antibiotic_use;
	}

	public void setAntibiotic_use(byte antibiotic_use) {
		this.antibiotic_use = antibiotic_use;
	}

	public byte getAnxiety() {
		return this.anxiety;
	}

	public void setAnxiety(byte anxiety) {
		this.anxiety = anxiety;
	}

	public byte getArrhythmias() {
		return this.arrhythmias;
	}

	public void setArrhythmias(byte arrhythmias) {
		this.arrhythmias = arrhythmias;
	}

	public byte getArteriovenous_Malformations() {
		return this.arteriovenous_Malformations;
	}

	public void setArteriovenous_Malformations(byte arteriovenous_Malformations) {
		this.arteriovenous_Malformations = arteriovenous_Malformations;
	}

	public byte getArthritis() {
		return this.arthritis;
	}

	public void setArthritis(byte arthritis) {
		this.arthritis = arthritis;
	}

	public byte getArthritis_RA() {
		return this.arthritis_RA;
	}

	public void setArthritis_RA(byte arthritis_RA) {
		this.arthritis_RA = arthritis_RA;
	}

	public byte getAsthma() {
		return this.asthma;
	}

	public void setAsthma(byte asthma) {
		this.asthma = asthma;
	}

	public byte getAutoimmune_hepatitis() {
		return this.autoimmune_hepatitis;
	}

	public void setAutoimmune_hepatitis(byte autoimmune_hepatitis) {
		this.autoimmune_hepatitis = autoimmune_hepatitis;
	}

	public byte getBarretts_esophagus() {
		return this.barretts_esophagus;
	}

	public void setBarretts_esophagus(byte barretts_esophagus) {
		this.barretts_esophagus = barretts_esophagus;
	}

	public byte getBenign_Liver_biopsy() {
		return this.benign_Liver_biopsy;
	}

	public void setBenign_Liver_biopsy(byte benign_Liver_biopsy) {
		this.benign_Liver_biopsy = benign_Liver_biopsy;
	}

	public byte getBenign_Polypectomy() {
		return this.benign_Polypectomy;
	}

	public void setBenign_Polypectomy(byte benign_Polypectomy) {
		this.benign_Polypectomy = benign_Polypectomy;
	}

	public byte getBenzodiazepines() {
		return this.benzodiazepines;
	}

	public void setBenzodiazepines(byte benzodiazepines) {
		this.benzodiazepines = benzodiazepines;
	}

	public byte getBipolar_disorder() {
		return this.bipolar_disorder;
	}

	public void setBipolar_disorder(byte bipolar_disorder) {
		this.bipolar_disorder = bipolar_disorder;
	}

	public byte getBlood_clots() {
		return this.blood_clots;
	}

	public void setBlood_clots(byte blood_clots) {
		this.blood_clots = blood_clots;
	}

	public byte getBlood_clots_DVT() {
		return this.blood_clots_DVT;
	}

	public void setBlood_clots_DVT(byte blood_clots_DVT) {
		this.blood_clots_DVT = blood_clots_DVT;
	}

	public byte getBlood_clots_PE() {
		return this.blood_clots_PE;
	}

	public void setBlood_clots_PE(byte blood_clots_PE) {
		this.blood_clots_PE = blood_clots_PE;
	}

	public byte getBudd_Chiari_Syndrome() {
		return this.budd_Chiari_Syndrome;
	}

	public void setBudd_Chiari_Syndrome(byte budd_Chiari_Syndrome) {
		this.budd_Chiari_Syndrome = budd_Chiari_Syndrome;
	}

	public byte getBuproprion_or_nortriptyline() {
		return this.buproprion_or_nortriptyline;
	}

	public void setBuproprion_or_nortriptyline(byte buproprion_or_nortriptyline) {
		this.buproprion_or_nortriptyline = buproprion_or_nortriptyline;
	}

	public byte getC_difficile_Colitis() {
		return this.c_difficile_Colitis;
	}

	public void setC_difficile_Colitis(byte c_difficile_Colitis) {
		this.c_difficile_Colitis = c_difficile_Colitis;
	}

	public byte getCabg() {
		return this.cabg;
	}

	public void setCabg(byte cabg) {
		this.cabg = cabg;
	}

	public byte getCarcinoid_Syndrome() {
		return this.carcinoid_Syndrome;
	}

	public void setCarcinoid_Syndrome(byte carcinoid_Syndrome) {
		this.carcinoid_Syndrome = carcinoid_Syndrome;
	}

	public byte getCeliac_Disease() {
		return this.celiac_Disease;
	}

	public void setCeliac_Disease(byte celiac_Disease) {
		this.celiac_Disease = celiac_Disease;
	}

	public byte getChemical_or_fires() {
		return this.chemical_or_fires;
	}

	public void setChemical_or_fires(byte chemical_or_fires) {
		this.chemical_or_fires = chemical_or_fires;
	}

	public byte getChemotherapy() {
		return this.chemotherapy;
	}

	public void setChemotherapy(byte chemotherapy) {
		this.chemotherapy = chemotherapy;
	}

	public byte getCholecystitis() {
		return this.cholecystitis;
	}

	public void setCholecystitis(byte cholecystitis) {
		this.cholecystitis = cholecystitis;
	}

	public byte getCholelithiasis() {
		return this.cholelithiasis;
	}

	public void setCholelithiasis(byte cholelithiasis) {
		this.cholelithiasis = cholelithiasis;
	}

	public byte getCirrhosis() {
		return this.cirrhosis;
	}

	public void setCirrhosis(byte cirrhosis) {
		this.cirrhosis = cirrhosis;
	}

	public int getClinicChart_Id() {
		return this.clinicChart_Id;
	}

	public void setClinicChart_Id(int clinicChart_Id) {
		this.clinicChart_Id = clinicChart_Id;
	}

	public byte getColitis() {
		return this.colitis;
	}

	public void setColitis(byte colitis) {
		this.colitis = colitis;
	}

	public byte getColonoscopy() {
		return this.colonoscopy;
	}

	public void setColonoscopy(byte colonoscopy) {
		this.colonoscopy = colonoscopy;
	}

	public byte getCongestive_Heart_Failure() {
		return this.congestive_Heart_Failure;
	}

	public void setCongestive_Heart_Failure(byte congestive_Heart_Failure) {
		this.congestive_Heart_Failure = congestive_Heart_Failure;
	}

	public byte getCopd() {
		return this.copd;
	}

	public void setCopd(byte copd) {
		this.copd = copd;
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

	public byte getCrohn_Disease() {
		return this.crohn_Disease;
	}

	public void setCrohn_Disease(byte crohn_Disease) {
		this.crohn_Disease = crohn_Disease;
	}

	public byte getCurrent_Suicide_attempts() {
		return this.current_Suicide_attempts;
	}

	public void setCurrent_Suicide_attempts(byte current_Suicide_attempts) {
		this.current_Suicide_attempts = current_Suicide_attempts;
	}

	public byte getCystic_Fibrosis() {
		return this.cystic_Fibrosis;
	}

	public void setCystic_Fibrosis(byte cystic_Fibrosis) {
		this.cystic_Fibrosis = cystic_Fibrosis;
	}

	public byte getDepression() {
		return this.depression;
	}

	public void setDepression(byte depression) {
		this.depression = depression;
	}

	public byte getDiabetes() {
		return this.diabetes;
	}

	public void setDiabetes(byte diabetes) {
		this.diabetes = diabetes;
	}

	public byte getDiabetes_Type_1() {
		return this.diabetes_Type_1;
	}

	public void setDiabetes_Type_1(byte diabetes_Type_1) {
		this.diabetes_Type_1 = diabetes_Type_1;
	}

	public byte getDiabetes_Type_2() {
		return this.diabetes_Type_2;
	}

	public void setDiabetes_Type_2(byte diabetes_Type_2) {
		this.diabetes_Type_2 = diabetes_Type_2;
	}

	public byte getDiabetes1() {
		return this.diabetes1;
	}

	public void setDiabetes1(byte diabetes1) {
		this.diabetes1 = diabetes1;
	}

	public byte getDiabetes2() {
		return this.diabetes2;
	}

	public void setDiabetes2(byte diabetes2) {
		this.diabetes2 = diabetes2;
	}

	public byte getDiverticulitis() {
		return this.diverticulitis;
	}

	public void setDiverticulitis(byte diverticulitis) {
		this.diverticulitis = diverticulitis;
	}

	public byte getDiverticulosis() {
		return this.diverticulosis;
	}

	public void setDiverticulosis(byte diverticulosis) {
		this.diverticulosis = diverticulosis;
	}

	public byte getDrug_Dependency() {
		return this.drug_Dependency;
	}

	public void setDrug_Dependency(byte drug_Dependency) {
		this.drug_Dependency = drug_Dependency;
	}

	public byte getDrug_Ingestion() {
		return this.drug_Ingestion;
	}

	public void setDrug_Ingestion(byte drug_Ingestion) {
		this.drug_Ingestion = drug_Ingestion;
	}

	public byte getDrug_Inhalation() {
		return this.drug_Inhalation;
	}

	public void setDrug_Inhalation(byte drug_Inhalation) {
		this.drug_Inhalation = drug_Inhalation;
	}

	public byte getDrug_Injection() {
		return this.drug_Injection;
	}

	public void setDrug_Injection(byte drug_Injection) {
		this.drug_Injection = drug_Injection;
	}

	public byte getECHO_Stress_test() {
		return this.ECHO_Stress_test;
	}

	public void setECHO_Stress_test(byte ECHO_Stress_test) {
		this.ECHO_Stress_test = ECHO_Stress_test;
	}

	public byte getEndocarditis() {
		return this.endocarditis;
	}

	public void setEndocarditis(byte endocarditis) {
		this.endocarditis = endocarditis;
	}

	public byte getEsophageal_dilation() {
		return this.esophageal_dilation;
	}

	public void setEsophageal_dilation(byte esophageal_dilation) {
		this.esophageal_dilation = esophageal_dilation;
	}

	public byte getEsophageal_Stricture() {
		return this.esophageal_Stricture;
	}

	public void setEsophageal_Stricture(byte esophageal_Stricture) {
		this.esophageal_Stricture = esophageal_Stricture;
	}

	public byte getEsophageal_Varices() {
		return this.esophageal_Varices;
	}

	public void setEsophageal_Varices(byte esophageal_Varices) {
		this.esophageal_Varices = esophageal_Varices;
	}

	public byte getEver_smoker() {
		return this.ever_smoker;
	}

	public void setEver_smoker(byte ever_smoker) {
		this.ever_smoker = ever_smoker;
	}

	public byte getFamilial_Mediterranean_Fever() {
		return this.familial_Mediterranean_Fever;
	}

	public void setFamilial_Mediterranean_Fever(byte familial_Mediterranean_Fever) {
		this.familial_Mediterranean_Fever = familial_Mediterranean_Fever;
	}

	public byte getFull_Colectomy() {
		return this.full_Colectomy;
	}

	public void setFull_Colectomy(byte full_Colectomy) {
		this.full_Colectomy = full_Colectomy;
	}

	public byte getGastric_bypass() {
		return this.gastric_bypass;
	}

	public void setGastric_bypass(byte gastric_bypass) {
		this.gastric_bypass = gastric_bypass;
	}

	public byte getGastric_volume_reduction() {
		return this.gastric_volume_reduction;
	}

	public void setGastric_volume_reduction(byte gastric_volume_reduction) {
		this.gastric_volume_reduction = gastric_volume_reduction;
	}

	public byte getGerd() {
		return this.gerd;
	}

	public void setGerd(byte gerd) {
		this.gerd = gerd;
	}

	public byte getGout() {
		return this.gout;
	}

	public void setGout(byte gout) {
		this.gout = gout;
	}

	public byte getH_pylori_positive() {
		return this.h_pylori_positive;
	}

	public void setH_pylori_positive(byte h_pylori_positive) {
		this.h_pylori_positive = h_pylori_positive;
	}

	public byte getHeart_Failure() {
		return this.heart_Failure;
	}

	public void setHeart_Failure(byte heart_Failure) {
		this.heart_Failure = heart_Failure;
	}

	public byte getHemochromatosis() {
		return this.hemochromatosis;
	}

	public void setHemochromatosis(byte hemochromatosis) {
		this.hemochromatosis = hemochromatosis;
	}

	public byte getHemodialysis() {
		return this.hemodialysis;
	}

	public void setHemodialysis(byte hemodialysis) {
		this.hemodialysis = hemodialysis;
	}

	public byte getHemolytic_anemia() {
		return this.hemolytic_anemia;
	}

	public void setHemolytic_anemia(byte hemolytic_anemia) {
		this.hemolytic_anemia = hemolytic_anemia;
	}

	public byte getHemorrhoids() {
		return this.hemorrhoids;
	}

	public void setHemorrhoids(byte hemorrhoids) {
		this.hemorrhoids = hemorrhoids;
	}

	public byte getHepatic_dysfunction() {
		return this.hepatic_dysfunction;
	}

	public void setHepatic_dysfunction(byte hepatic_dysfunction) {
		this.hepatic_dysfunction = hepatic_dysfunction;
	}

	public byte getHepatitis() {
		return this.hepatitis;
	}

	public void setHepatitis(byte hepatitis) {
		this.hepatitis = hepatitis;
	}

	public byte getHernia() {
		return this.hernia;
	}

	public void setHernia(byte hernia) {
		this.hernia = hernia;
	}

	public byte getHerpes() {
		return this.herpes;
	}

	public void setHerpes(byte herpes) {
		this.herpes = herpes;
	}

	public byte getHistiocytosis() {
		return this.histiocytosis;
	}

	public void setHistiocytosis(byte histiocytosis) {
		this.histiocytosis = histiocytosis;
	}

	public byte getHistory_of_Electroconvulsive_Shock_Therapy() {
		return this.history_of_Electroconvulsive_Shock_Therapy;
	}

	public void setHistory_of_Electroconvulsive_Shock_Therapy(byte history_of_Electroconvulsive_Shock_Therapy) {
		this.history_of_Electroconvulsive_Shock_Therapy = history_of_Electroconvulsive_Shock_Therapy;
	}

	public byte getHivAids() {
		return this.hivAids;
	}

	public void setHivAids(byte hivAids) {
		this.hivAids = hivAids;
	}

	public byte getHomicidal_Ideation() {
		return this.homicidal_Ideation;
	}

	public void setHomicidal_Ideation(byte homicidal_Ideation) {
		this.homicidal_Ideation = homicidal_Ideation;
	}

	public byte getHospitalizations_for_psychiatric_illnesses() {
		return this.hospitalizations_for_psychiatric_illnesses;
	}

	public void setHospitalizations_for_psychiatric_illnesses(byte hospitalizations_for_psychiatric_illnesses) {
		this.hospitalizations_for_psychiatric_illnesses = hospitalizations_for_psychiatric_illnesses;
	}

	public byte getHypertension() {
		return this.hypertension;
	}

	public void setHypertension(byte hypertension) {
		this.hypertension = hypertension;
	}

	public byte getImmunosuppressive_therapy() {
		return this.immunosuppressive_therapy;
	}

	public void setImmunosuppressive_therapy(byte immunosuppressive_therapy) {
		this.immunosuppressive_therapy = immunosuppressive_therapy;
	}

	public byte getInflammatory_Bowel_Disease() {
		return this.inflammatory_Bowel_Disease;
	}

	public void setInflammatory_Bowel_Disease(byte inflammatory_Bowel_Disease) {
		this.inflammatory_Bowel_Disease = inflammatory_Bowel_Disease;
	}

	public byte getInorganic_dust() {
		return this.inorganic_dust;
	}

	public void setInorganic_dust(byte inorganic_dust) {
		this.inorganic_dust = inorganic_dust;
	}

	public byte getIschemic_colitis() {
		return this.ischemic_colitis;
	}

	public void setIschemic_colitis(byte ischemic_colitis) {
		this.ischemic_colitis = ischemic_colitis;
	}

	public byte getJaundice() {
		return this.jaundice;
	}

	public void setJaundice(byte jaundice) {
		this.jaundice = jaundice;
	}

	public Date getLast_Chemotherapy() {
		return this.last_Chemotherapy;
	}

	public void setLast_Chemotherapy(Date last_Chemotherapy) {
		this.last_Chemotherapy = last_Chemotherapy;
	}

	public Date getLast_cTx() {
		return this.last_cTx;
	}

	public void setLast_cTx(Date last_cTx) {
		this.last_cTx = last_cTx;
	}

	public byte getLast_rTX() {
		return this.last_rTX;
	}

	public void setLast_rTX(byte last_rTX) {
		this.last_rTX = last_rTX;
	}

	public byte getLiver_disease() {
		return this.liver_disease;
	}

	public void setLiver_disease(byte liver_disease) {
		this.liver_disease = liver_disease;
	}

	public byte getLung_resection() {
		return this.lung_resection;
	}

	public void setLung_resection(byte lung_resection) {
		this.lung_resection = lung_resection;
	}

	public byte getMalaria() {
		return this.malaria;
	}

	public void setMalaria(byte malaria) {
		this.malaria = malaria;
	}

	public byte getMalignancy_Breast() {
		return this.malignancy_Breast;
	}

	public void setMalignancy_Breast(byte malignancy_Breast) {
		this.malignancy_Breast = malignancy_Breast;
	}

	public byte getMalignancy_Colon() {
		return this.malignancy_Colon;
	}

	public void setMalignancy_Colon(byte malignancy_Colon) {
		this.malignancy_Colon = malignancy_Colon;
	}

	public byte getMalignancy_Colorectal() {
		return this.malignancy_Colorectal;
	}

	public void setMalignancy_Colorectal(byte malignancy_Colorectal) {
		this.malignancy_Colorectal = malignancy_Colorectal;
	}

	public byte getMalignancy_Hepatic() {
		return this.malignancy_Hepatic;
	}

	public void setMalignancy_Hepatic(byte malignancy_Hepatic) {
		this.malignancy_Hepatic = malignancy_Hepatic;
	}

	public byte getMalignancy_Leukemia_Lymphoma() {
		return this.malignancy_Leukemia_Lymphoma;
	}

	public void setMalignancy_Leukemia_Lymphoma(byte malignancy_Leukemia_Lymphoma) {
		this.malignancy_Leukemia_Lymphoma = malignancy_Leukemia_Lymphoma;
	}

	public byte getMalignancy_Lung() {
		return this.malignancy_Lung;
	}

	public void setMalignancy_Lung(byte malignancy_Lung) {
		this.malignancy_Lung = malignancy_Lung;
	}

	public byte getMalignancy_Melanoma() {
		return this.malignancy_Melanoma;
	}

	public void setMalignancy_Melanoma(byte malignancy_Melanoma) {
		this.malignancy_Melanoma = malignancy_Melanoma;
	}

	public byte getMalignancy_Other() {
		return this.malignancy_Other;
	}

	public void setMalignancy_Other(byte malignancy_Other) {
		this.malignancy_Other = malignancy_Other;
	}

	public byte getMalignancy_Pituitary() {
		return this.malignancy_Pituitary;
	}

	public void setMalignancy_Pituitary(byte malignancy_Pituitary) {
		this.malignancy_Pituitary = malignancy_Pituitary;
	}

	public byte getMalignancy_Prostate() {
		return this.malignancy_Prostate;
	}

	public void setMalignancy_Prostate(byte malignancy_Prostate) {
		this.malignancy_Prostate = malignancy_Prostate;
	}

	public byte getMalignancy_Renal_cell() {
		return this.malignancy_Renal_cell;
	}

	public void setMalignancy_Renal_cell(byte malignancy_Renal_cell) {
		this.malignancy_Renal_cell = malignancy_Renal_cell;
	}

	public byte getMalignancy_Testicular() {
		return this.malignancy_Testicular;
	}

	public void setMalignancy_Testicular(byte malignancy_Testicular) {
		this.malignancy_Testicular = malignancy_Testicular;
	}

	public byte getMalignancy_Thyroid() {
		return this.malignancy_Thyroid;
	}

	public void setMalignancy_Thyroid(byte malignancy_Thyroid) {
		this.malignancy_Thyroid = malignancy_Thyroid;
	}

	public byte getMalignant_Hyperthermia() {
		return this.malignant_Hyperthermia;
	}

	public void setMalignant_Hyperthermia(byte malignant_Hyperthermia) {
		this.malignant_Hyperthermia = malignant_Hyperthermia;
	}

	public byte getMalignant_Liver_biopsy() {
		return this.malignant_Liver_biopsy;
	}

	public void setMalignant_Liver_biopsy(byte malignant_Liver_biopsy) {
		this.malignant_Liver_biopsy = malignant_Liver_biopsy;
	}

	public byte getMalignant_Polypectomy() {
		return this.malignant_Polypectomy;
	}

	public void setMalignant_Polypectomy(byte malignant_Polypectomy) {
		this.malignant_Polypectomy = malignant_Polypectomy;
	}

	public byte getMammogram() {
		return this.mammogram;
	}

	public void setMammogram(byte mammogram) {
		this.mammogram = mammogram;
	}

	public byte getMania() {
		return this.mania;
	}

	public void setMania(byte mania) {
		this.mania = mania;
	}

	public byte getMilitary_Experience() {
		return this.military_Experience;
	}

	public void setMilitary_Experience(byte military_Experience) {
		this.military_Experience = military_Experience;
	}

	public byte getNarcotics() {
		return this.narcotics;
	}

	public void setNarcotics(byte narcotics) {
		this.narcotics = narcotics;
	}

	public byte getNeoroleptic_Malignant_Syndrome() {
		return this.neoroleptic_Malignant_Syndrome;
	}

	public void setNeoroleptic_Malignant_Syndrome(byte neoroleptic_Malignant_Syndrome) {
		this.neoroleptic_Malignant_Syndrome = neoroleptic_Malignant_Syndrome;
	}

	public byte getNeuromuscular_weakness() {
		return this.neuromuscular_weakness;
	}

	public void setNeuromuscular_weakness(byte neuromuscular_weakness) {
		this.neuromuscular_weakness = neuromuscular_weakness;
	}

	public byte getNicotine_receptor_blockade() {
		return this.nicotine_receptor_blockade;
	}

	public void setNicotine_receptor_blockade(byte nicotine_receptor_blockade) {
		this.nicotine_receptor_blockade = nicotine_receptor_blockade;
	}

	public byte getNicotine_replacement() {
		return this.nicotine_replacement;
	}

	public void setNicotine_replacement(byte nicotine_replacement) {
		this.nicotine_replacement = nicotine_replacement;
	}

	public byte getNonalcoholic_Fatty_Liver_Disease() {
		return this.nonalcoholic_Fatty_Liver_Disease;
	}

	public void setNonalcoholic_Fatty_Liver_Disease(byte nonalcoholic_Fatty_Liver_Disease) {
		this.nonalcoholic_Fatty_Liver_Disease = nonalcoholic_Fatty_Liver_Disease;
	}

	public byte getNoxious_fumes() {
		return this.noxious_fumes;
	}

	public void setNoxious_fumes(byte noxious_fumes) {
		this.noxious_fumes = noxious_fumes;
	}

	public byte getNum_Cigarettes_per_day() {
		return this.num_Cigarettes_per_day;
	}

	public void setNum_Cigarettes_per_day(byte num_Cigarettes_per_day) {
		this.num_Cigarettes_per_day = num_Cigarettes_per_day;
	}

	public byte getNum_Drinks_per_day() {
		return this.num_Drinks_per_day;
	}

	public void setNum_Drinks_per_day(byte num_Drinks_per_day) {
		this.num_Drinks_per_day = num_Drinks_per_day;
	}

	public byte getNum_Drinks_Per_week() {
		return this.num_Drinks_Per_week;
	}

	public void setNum_Drinks_Per_week(byte num_Drinks_Per_week) {
		this.num_Drinks_Per_week = num_Drinks_Per_week;
	}

	public byte getNum_Packs_per_day() {
		return this.num_Packs_per_day;
	}

	public void setNum_Packs_per_day(byte num_Packs_per_day) {
		this.num_Packs_per_day = num_Packs_per_day;
	}

	public byte getOrgan_failure() {
		return this.organ_failure;
	}

	public void setOrgan_failure(byte organ_failure) {
		this.organ_failure = organ_failure;
	}

	public byte getOrgan_transplant() {
		return this.organ_transplant;
	}

	public void setOrgan_transplant(byte organ_transplant) {
		this.organ_transplant = organ_transplant;
	}

	public byte getOrganic_dust() {
		return this.organic_dust;
	}

	public void setOrganic_dust(byte organic_dust) {
		this.organic_dust = organic_dust;
	}

	public byte getOsteoporosis() {
		return this.osteoporosis;
	}

	public void setOsteoporosis(byte osteoporosis) {
		this.osteoporosis = osteoporosis;
	}

	public byte getOther_Surgery() {
		return this.other_Surgery;
	}

	public void setOther_Surgery(byte other_Surgery) {
		this.other_Surgery = other_Surgery;
	}

	public byte getPancreatic_stent() {
		return this.pancreatic_stent;
	}

	public void setPancreatic_stent(byte pancreatic_stent) {
		this.pancreatic_stent = pancreatic_stent;
	}

	public byte getPancreatitis() {
		return this.pancreatitis;
	}

	public void setPancreatitis(byte pancreatitis) {
		this.pancreatitis = pancreatitis;
	}

	public byte getPap_Smear() {
		return this.pap_Smear;
	}

	public void setPap_Smear(byte pap_Smear) {
		this.pap_Smear = pap_Smear;
	}

	public byte getParasitic_infections() {
		return this.parasitic_infections;
	}

	public void setParasitic_infections(byte parasitic_infections) {
		this.parasitic_infections = parasitic_infections;
	}

	public byte getPartial_Colectomy() {
		return this.partial_Colectomy;
	}

	public void setPartial_Colectomy(byte partial_Colectomy) {
		this.partial_Colectomy = partial_Colectomy;
	}

	public byte getPEG_PEJ_tube() {
		return this.PEG_PEJ_tube;
	}

	public void setPEG_PEJ_tube(byte PEG_PEJ_tube) {
		this.PEG_PEJ_tube = PEG_PEJ_tube;
	}

	public byte getPeripheral_Artery_Disease() {
		return this.peripheral_Artery_Disease;
	}

	public void setPeripheral_Artery_Disease(byte peripheral_Artery_Disease) {
		this.peripheral_Artery_Disease = peripheral_Artery_Disease;
	}

	public byte getPeritoneal_dialysis() {
		return this.peritoneal_dialysis;
	}

	public void setPeritoneal_dialysis(byte peritoneal_dialysis) {
		this.peritoneal_dialysis = peritoneal_dialysis;
	}

	public byte getPeritonitis() {
		return this.peritonitis;
	}

	public void setPeritonitis(byte peritonitis) {
		this.peritonitis = peritonitis;
	}

	public byte getPersonality_disorder() {
		return this.personality_disorder;
	}

	public void setPersonality_disorder(byte personality_disorder) {
		this.personality_disorder = personality_disorder;
	}

	public byte getPFTs() {
		return this.PFTs;
	}

	public void setPFTs(byte PFTs) {
		this.PFTs = PFTs;
	}

	public byte getPituitary_infarct_or_hemorrhage() {
		return this.pituitary_infarct_or_hemorrhage;
	}

	public void setPituitary_infarct_or_hemorrhage(byte pituitary_infarct_or_hemorrhage) {
		this.pituitary_infarct_or_hemorrhage = pituitary_infarct_or_hemorrhage;
	}

	public byte getPleurodesis() {
		return this.pleurodesis;
	}

	public void setPleurodesis(byte pleurodesis) {
		this.pleurodesis = pleurodesis;
	}

	public byte getPolyps() {
		return this.polyps;
	}

	public void setPolyps(byte polyps) {
		this.polyps = polyps;
	}

	public byte getPorphyria() {
		return this.porphyria;
	}

	public void setPorphyria(byte porphyria) {
		this.porphyria = porphyria;
	}

	public byte getPrior_intubations() {
		return this.prior_intubations;
	}

	public void setPrior_intubations(byte prior_intubations) {
		this.prior_intubations = prior_intubations;
	}

	public byte getPrior_Suicide_attempts() {
		return this.prior_Suicide_attempts;
	}

	public void setPrior_Suicide_attempts(byte prior_Suicide_attempts) {
		this.prior_Suicide_attempts = prior_Suicide_attempts;
	}

	public byte getProtein_deficiency_C() {
		return this.protein_deficiency_C;
	}

	public void setProtein_deficiency_C(byte protein_deficiency_C) {
		this.protein_deficiency_C = protein_deficiency_C;
	}

	public byte getProtein_deficiency_S() {
		return this.protein_deficiency_S;
	}

	public void setProtein_deficiency_S(byte protein_deficiency_S) {
		this.protein_deficiency_S = protein_deficiency_S;
	}

	public byte getPsychosis() {
		return this.psychosis;
	}

	public void setPsychosis(byte psychosis) {
		this.psychosis = psychosis;
	}

	public Date getQuit_Tobacco_date() {
		return this.quit_Tobacco_date;
	}

	public void setQuit_Tobacco_date(Date quit_Tobacco_date) {
		this.quit_Tobacco_date = quit_Tobacco_date;
	}

	public byte getRadiation() {
		return this.radiation;
	}

	public void setRadiation(byte radiation) {
		this.radiation = radiation;
	}

	public byte getRadiation_Exposure() {
		return this.radiation_Exposure;
	}

	public void setRadiation_Exposure(byte radiation_Exposure) {
		this.radiation_Exposure = radiation_Exposure;
	}

	public byte getRadio_ablation() {
		return this.radio_ablation;
	}

	public void setRadio_ablation(byte radio_ablation) {
		this.radio_ablation = radio_ablation;
	}

	public byte getRecreational_Drug_Use() {
		return this.recreational_Drug_Use;
	}

	public void setRecreational_Drug_Use(byte recreational_Drug_Use) {
		this.recreational_Drug_Use = recreational_Drug_Use;
	}

	public byte getRenal_dysfunction() {
		return this.renal_dysfunction;
	}

	public void setRenal_dysfunction(byte renal_dysfunction) {
		this.renal_dysfunction = renal_dysfunction;
	}

	public byte getRenal_dysfunction_ESRD() {
		return this.renal_dysfunction_ESRD;
	}

	public void setRenal_dysfunction_ESRD(byte renal_dysfunction_ESRD) {
		this.renal_dysfunction_ESRD = renal_dysfunction_ESRD;
	}

	public byte getSarcoidosis() {
		return this.sarcoidosis;
	}

	public void setSarcoidosis(byte sarcoidosis) {
		this.sarcoidosis = sarcoidosis;
	}

	public byte getSchizophrenia() {
		return this.schizophrenia;
	}

	public void setSchizophrenia(byte schizophrenia) {
		this.schizophrenia = schizophrenia;
	}

	public byte getSclerosing_cholangitis() {
		return this.sclerosing_cholangitis;
	}

	public void setSclerosing_cholangitis(byte sclerosing_cholangitis) {
		this.sclerosing_cholangitis = sclerosing_cholangitis;
	}

	public byte getSeizure_disorder() {
		return this.seizure_disorder;
	}

	public void setSeizure_disorder(byte seizure_disorder) {
		this.seizure_disorder = seizure_disorder;
	}

	public byte getSeizures() {
		return this.seizures;
	}

	public void setSeizures(byte seizures) {
		this.seizures = seizures;
	}

	public byte getSickle_Cell_Disease() {
		return this.sickle_Cell_Disease;
	}

	public void setSickle_Cell_Disease(byte sickle_Cell_Disease) {
		this.sickle_Cell_Disease = sickle_Cell_Disease;
	}

	public byte getSleep_Apnea() {
		return this.sleep_Apnea;
	}

	public void setSleep_Apnea(byte sleep_Apnea) {
		this.sleep_Apnea = sleep_Apnea;
	}

	public byte getSleep_Apnea_() {
		return this.sleep_Apnea_;
	}

	public void setSleep_Apnea_(byte sleep_Apnea_) {
		this.sleep_Apnea_ = sleep_Apnea_;
	}

	public byte getSleep_Apnea_BiPAP() {
		return this.sleep_Apnea_BiPAP;
	}

	public void setSleep_Apnea_BiPAP(byte sleep_Apnea_BiPAP) {
		this.sleep_Apnea_BiPAP = sleep_Apnea_BiPAP;
	}

	public byte getSleep_Apnea_CPAP() {
		return this.sleep_Apnea_CPAP;
	}

	public void setSleep_Apnea_CPAP(byte sleep_Apnea_CPAP) {
		this.sleep_Apnea_CPAP = sleep_Apnea_CPAP;
	}

	public byte getSleep_study() {
		return this.sleep_study;
	}

	public void setSleep_study(byte sleep_study) {
		this.sleep_study = sleep_study;
	}

	public byte getSmoke_Yes() {
		return this.smoke_Yes;
	}

	public void setSmoke_Yes(byte smoke_Yes) {
		this.smoke_Yes = smoke_Yes;
	}

	public byte getSplenectomy() {
		return this.splenectomy;
	}

	public void setSplenectomy(byte splenectomy) {
		this.splenectomy = splenectomy;
	}

	public byte getSteroid_Use_for_other() {
		return this.steroid_Use_for_other;
	}

	public void setSteroid_Use_for_other(byte steroid_Use_for_other) {
		this.steroid_Use_for_other = steroid_Use_for_other;
	}

	public byte getSteroid_use_Pain() {
		return this.steroid_use_Pain;
	}

	public void setSteroid_use_Pain(byte steroid_use_Pain) {
		this.steroid_use_Pain = steroid_use_Pain;
	}

	public byte getSuicidal_ideation() {
		return this.suicidal_ideation;
	}

	public void setSuicidal_ideation(byte suicidal_ideation) {
		this.suicidal_ideation = suicidal_ideation;
	}

	public byte getSurgical_Resection() {
		return this.surgical_Resection;
	}

	public void setSurgical_Resection(byte surgical_Resection) {
		this.surgical_Resection = surgical_Resection;
	}

	public byte getSyphilis() {
		return this.syphilis;
	}

	public void setSyphilis(byte syphilis) {
		this.syphilis = syphilis;
	}

	public byte getSystemic_Lupus_Erythematosis() {
		return this.systemic_Lupus_Erythematosis;
	}

	public void setSystemic_Lupus_Erythematosis(byte systemic_Lupus_Erythematosis) {
		this.systemic_Lupus_Erythematosis = systemic_Lupus_Erythematosis;
	}

	public byte getThrombocytopenia() {
		return this.thrombocytopenia;
	}

	public void setThrombocytopenia(byte thrombocytopenia) {
		this.thrombocytopenia = thrombocytopenia;
	}

	public byte getThrombocytopenia_ITP() {
		return this.thrombocytopenia_ITP;
	}

	public void setThrombocytopenia_ITP(byte thrombocytopenia_ITP) {
		this.thrombocytopenia_ITP = thrombocytopenia_ITP;
	}

	public byte getThrombocytopenia_TTP() {
		return this.thrombocytopenia_TTP;
	}

	public void setThrombocytopenia_TTP(byte thrombocytopenia_TTP) {
		this.thrombocytopenia_TTP = thrombocytopenia_TTP;
	}

	public byte getThyroid_disease() {
		return this.thyroid_disease;
	}

	public void setThyroid_disease(byte thyroid_disease) {
		this.thyroid_disease = thyroid_disease;
	}

	public byte getThyroid_disease_hyper() {
		return this.thyroid_disease_hyper;
	}

	public void setThyroid_disease_hyper(byte thyroid_disease_hyper) {
		this.thyroid_disease_hyper = thyroid_disease_hyper;
	}

	public byte getThyroid_disease_hypo() {
		return this.thyroid_disease_hypo;
	}

	public void setThyroid_disease_hypo(byte thyroid_disease_hypo) {
		this.thyroid_disease_hypo = thyroid_disease_hypo;
	}

	public byte getThyroid_hyper() {
		return this.thyroid_hyper;
	}

	public void setThyroid_hyper(byte thyroid_hyper) {
		this.thyroid_hyper = thyroid_hyper;
	}

	public byte getThyroid_hypo() {
		return this.thyroid_hypo;
	}

	public void setThyroid_hypo(byte thyroid_hypo) {
		this.thyroid_hypo = thyroid_hypo;
	}

	public byte getTuberculosis() {
		return this.tuberculosis;
	}

	public void setTuberculosis(byte tuberculosis) {
		this.tuberculosis = tuberculosis;
	}

	public byte getTuberculosis_PPD() {
		return this.tuberculosis_PPD;
	}

	public void setTuberculosis_PPD(byte tuberculosis_PPD) {
		this.tuberculosis_PPD = tuberculosis_PPD;
	}

	public byte getTuberculosis_Test_Date() {
		return this.tuberculosis_Test_Date;
	}

	public void setTuberculosis_Test_Date(byte tuberculosis_Test_Date) {
		this.tuberculosis_Test_Date = tuberculosis_Test_Date;
	}

	public byte getTuberculosis_Test_Negative() {
		return this.tuberculosis_Test_Negative;
	}

	public void setTuberculosis_Test_Negative(byte tuberculosis_Test_Negative) {
		this.tuberculosis_Test_Negative = tuberculosis_Test_Negative;
	}

	public byte getTuberculosis_Test_Positive() {
		return this.tuberculosis_Test_Positive;
	}

	public void setTuberculosis_Test_Positive(byte tuberculosis_Test_Positive) {
		this.tuberculosis_Test_Positive = tuberculosis_Test_Positive;
	}

	public byte getTuberculosis_Treatment() {
		return this.tuberculosis_Treatment;
	}

	public void setTuberculosis_Treatment(byte tuberculosis_Treatment) {
		this.tuberculosis_Treatment = tuberculosis_Treatment;
	}

	public byte getUlcerative_colitis() {
		return this.ulcerative_colitis;
	}

	public void setUlcerative_colitis(byte ulcerative_colitis) {
		this.ulcerative_colitis = ulcerative_colitis;
	}

	public byte getUlcers() {
		return this.ulcers;
	}

	public void setUlcers(byte ulcers) {
		this.ulcers = ulcers;
	}

	public byte getVl_Poma() {
		return this.vl_Poma;
	}

	public void setVl_Poma(byte vl_Poma) {
		this.vl_Poma = vl_Poma;
	}

	public byte getWhipple_s_disease() {
		return this.whipple_s_disease;
	}

	public void setWhipple_s_disease(byte whipple_s_disease) {
		this.whipple_s_disease = whipple_s_disease;
	}

	public byte getWilling_to_quit_Alcohol() {
		return this.willing_to_quit_Alcohol;
	}

	public void setWilling_to_quit_Alcohol(byte willing_to_quit_Alcohol) {
		this.willing_to_quit_Alcohol = willing_to_quit_Alcohol;
	}

	public byte getWilling_to_quit_Drugs_Rec() {
		return this.willing_to_quit_Drugs_Rec;
	}

	public void setWilling_to_quit_Drugs_Rec(byte willing_to_quit_Drugs_Rec) {
		this.willing_to_quit_Drugs_Rec = willing_to_quit_Drugs_Rec;
	}

	public byte getWilling_to_quit_Medicated_Drugs() {
		return this.willing_to_quit_Medicated_Drugs;
	}

	public void setWilling_to_quit_Medicated_Drugs(byte willing_to_quit_Medicated_Drugs) {
		this.willing_to_quit_Medicated_Drugs = willing_to_quit_Medicated_Drugs;
	}

	public byte getWilling_to_quit_Tobacco() {
		return this.willing_to_quit_Tobacco;
	}

	public void setWilling_to_quit_Tobacco(byte willing_to_quit_Tobacco) {
		this.willing_to_quit_Tobacco = willing_to_quit_Tobacco;
	}

	public byte getWilson_s_Disease() {
		return this.wilson_s_Disease;
	}

	public void setWilson_s_Disease(byte wilson_s_Disease) {
		this.wilson_s_Disease = wilson_s_Disease;
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

	public byte getZollinger_Ellison_Syndrome() {
		return this.zollinger_Ellison_Syndrome;
	}

	public void setZollinger_Ellison_Syndrome(byte zollinger_Ellison_Syndrome) {
		this.zollinger_Ellison_Syndrome = zollinger_Ellison_Syndrome;
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