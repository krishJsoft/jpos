package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the systemreviews database table.
 * 
 */
@Entity
@Table(name="systemreviews")
public class Systemreview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int systemReview_Id;

	private byte abdominal_pain;

	private byte agitation;

	private byte ankle_edema;

	private byte appetite_changes;

	private byte arthralgias;

	private byte bleeding_gums;

	private byte blurred_vision;

	private byte chess_pain;

	private byte claudication_symptoms;

	private byte conjunctivitis;

	private byte constipation_or_Diarrhea;

	private byte cough;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	private byte dental_abscesses;

	private byte dental_caries;

	private byte diaphoresis;

	private byte dyspnea;

	private byte dysuria;

	private byte fatigue;

	private byte fever_or_chills;

	private byte hair_loss;

	private byte hallucinations;

	private byte headaches;

	private byte hematuria;

	private byte hemoptysis;

	private byte jaw_pain;

	private byte joint_swelling;

	private byte masses;

	private byte muscle_weakness;

	private byte myalgias;

	private byte nasal_congestion;

	private byte nausea_or_Vomiting;

	private byte neck_pain;

	private byte new_eye_pain;

	private byte new_skin_lesions;

	private byte nose_bleed;

	private byte numbness;

	private byte palpitations;

	private byte paresthesias;

	private byte phlegm;

	private byte pleuritic_symptoms;

	private byte polydipsia;

	private byte rash;

	private byte recent_trauma;

	private byte recurrent_infections;

	private byte rhinorrhea;

	private byte seizures;

	private byte sensitivity_to_Sun;

	private byte sinus_problems;

	private byte sore_throat;

	private byte swollen_lymph_nodes;

	private byte swollen_uvula;

	private byte syncope;

	private byte tremors;

	private byte unusual_bruising;

	private byte urethral_discharge;

	private byte weight_changes;

	private byte wheeze;

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

	public Systemreview() {
	}

	public int getSystemReview_Id() {
		return this.systemReview_Id;
	}

	public void setSystemReview_Id(int systemReview_Id) {
		this.systemReview_Id = systemReview_Id;
	}

	public byte getAbdominal_pain() {
		return this.abdominal_pain;
	}

	public void setAbdominal_pain(byte abdominal_pain) {
		this.abdominal_pain = abdominal_pain;
	}

	public byte getAgitation() {
		return this.agitation;
	}

	public void setAgitation(byte agitation) {
		this.agitation = agitation;
	}

	public byte getAnkle_edema() {
		return this.ankle_edema;
	}

	public void setAnkle_edema(byte ankle_edema) {
		this.ankle_edema = ankle_edema;
	}

	public byte getAppetite_changes() {
		return this.appetite_changes;
	}

	public void setAppetite_changes(byte appetite_changes) {
		this.appetite_changes = appetite_changes;
	}

	public byte getArthralgias() {
		return this.arthralgias;
	}

	public void setArthralgias(byte arthralgias) {
		this.arthralgias = arthralgias;
	}

	public byte getBleeding_gums() {
		return this.bleeding_gums;
	}

	public void setBleeding_gums(byte bleeding_gums) {
		this.bleeding_gums = bleeding_gums;
	}

	public byte getBlurred_vision() {
		return this.blurred_vision;
	}

	public void setBlurred_vision(byte blurred_vision) {
		this.blurred_vision = blurred_vision;
	}

	public byte getChess_pain() {
		return this.chess_pain;
	}

	public void setChess_pain(byte chess_pain) {
		this.chess_pain = chess_pain;
	}

	public byte getClaudication_symptoms() {
		return this.claudication_symptoms;
	}

	public void setClaudication_symptoms(byte claudication_symptoms) {
		this.claudication_symptoms = claudication_symptoms;
	}

	public byte getConjunctivitis() {
		return this.conjunctivitis;
	}

	public void setConjunctivitis(byte conjunctivitis) {
		this.conjunctivitis = conjunctivitis;
	}

	public byte getConstipation_or_Diarrhea() {
		return this.constipation_or_Diarrhea;
	}

	public void setConstipation_or_Diarrhea(byte constipation_or_Diarrhea) {
		this.constipation_or_Diarrhea = constipation_or_Diarrhea;
	}

	public byte getCough() {
		return this.cough;
	}

	public void setCough(byte cough) {
		this.cough = cough;
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

	public byte getDental_abscesses() {
		return this.dental_abscesses;
	}

	public void setDental_abscesses(byte dental_abscesses) {
		this.dental_abscesses = dental_abscesses;
	}

	public byte getDental_caries() {
		return this.dental_caries;
	}

	public void setDental_caries(byte dental_caries) {
		this.dental_caries = dental_caries;
	}

	public byte getDiaphoresis() {
		return this.diaphoresis;
	}

	public void setDiaphoresis(byte diaphoresis) {
		this.diaphoresis = diaphoresis;
	}

	public byte getDyspnea() {
		return this.dyspnea;
	}

	public void setDyspnea(byte dyspnea) {
		this.dyspnea = dyspnea;
	}

	public byte getDysuria() {
		return this.dysuria;
	}

	public void setDysuria(byte dysuria) {
		this.dysuria = dysuria;
	}

	public byte getFatigue() {
		return this.fatigue;
	}

	public void setFatigue(byte fatigue) {
		this.fatigue = fatigue;
	}

	public byte getFever_or_chills() {
		return this.fever_or_chills;
	}

	public void setFever_or_chills(byte fever_or_chills) {
		this.fever_or_chills = fever_or_chills;
	}

	public byte getHair_loss() {
		return this.hair_loss;
	}

	public void setHair_loss(byte hair_loss) {
		this.hair_loss = hair_loss;
	}

	public byte getHallucinations() {
		return this.hallucinations;
	}

	public void setHallucinations(byte hallucinations) {
		this.hallucinations = hallucinations;
	}

	public byte getHeadaches() {
		return this.headaches;
	}

	public void setHeadaches(byte headaches) {
		this.headaches = headaches;
	}

	public byte getHematuria() {
		return this.hematuria;
	}

	public void setHematuria(byte hematuria) {
		this.hematuria = hematuria;
	}

	public byte getHemoptysis() {
		return this.hemoptysis;
	}

	public void setHemoptysis(byte hemoptysis) {
		this.hemoptysis = hemoptysis;
	}

	public byte getJaw_pain() {
		return this.jaw_pain;
	}

	public void setJaw_pain(byte jaw_pain) {
		this.jaw_pain = jaw_pain;
	}

	public byte getJoint_swelling() {
		return this.joint_swelling;
	}

	public void setJoint_swelling(byte joint_swelling) {
		this.joint_swelling = joint_swelling;
	}

	public byte getMasses() {
		return this.masses;
	}

	public void setMasses(byte masses) {
		this.masses = masses;
	}

	public byte getMuscle_weakness() {
		return this.muscle_weakness;
	}

	public void setMuscle_weakness(byte muscle_weakness) {
		this.muscle_weakness = muscle_weakness;
	}

	public byte getMyalgias() {
		return this.myalgias;
	}

	public void setMyalgias(byte myalgias) {
		this.myalgias = myalgias;
	}

	public byte getNasal_congestion() {
		return this.nasal_congestion;
	}

	public void setNasal_congestion(byte nasal_congestion) {
		this.nasal_congestion = nasal_congestion;
	}

	public byte getNausea_or_Vomiting() {
		return this.nausea_or_Vomiting;
	}

	public void setNausea_or_Vomiting(byte nausea_or_Vomiting) {
		this.nausea_or_Vomiting = nausea_or_Vomiting;
	}

	public byte getNeck_pain() {
		return this.neck_pain;
	}

	public void setNeck_pain(byte neck_pain) {
		this.neck_pain = neck_pain;
	}

	public byte getNew_eye_pain() {
		return this.new_eye_pain;
	}

	public void setNew_eye_pain(byte new_eye_pain) {
		this.new_eye_pain = new_eye_pain;
	}

	public byte getNew_skin_lesions() {
		return this.new_skin_lesions;
	}

	public void setNew_skin_lesions(byte new_skin_lesions) {
		this.new_skin_lesions = new_skin_lesions;
	}

	public byte getNose_bleed() {
		return this.nose_bleed;
	}

	public void setNose_bleed(byte nose_bleed) {
		this.nose_bleed = nose_bleed;
	}

	public byte getNumbness() {
		return this.numbness;
	}

	public void setNumbness(byte numbness) {
		this.numbness = numbness;
	}

	public byte getPalpitations() {
		return this.palpitations;
	}

	public void setPalpitations(byte palpitations) {
		this.palpitations = palpitations;
	}

	public byte getParesthesias() {
		return this.paresthesias;
	}

	public void setParesthesias(byte paresthesias) {
		this.paresthesias = paresthesias;
	}

	public byte getPhlegm() {
		return this.phlegm;
	}

	public void setPhlegm(byte phlegm) {
		this.phlegm = phlegm;
	}

	public byte getPleuritic_symptoms() {
		return this.pleuritic_symptoms;
	}

	public void setPleuritic_symptoms(byte pleuritic_symptoms) {
		this.pleuritic_symptoms = pleuritic_symptoms;
	}

	public byte getPolydipsia() {
		return this.polydipsia;
	}

	public void setPolydipsia(byte polydipsia) {
		this.polydipsia = polydipsia;
	}

	public byte getRash() {
		return this.rash;
	}

	public void setRash(byte rash) {
		this.rash = rash;
	}

	public byte getRecent_trauma() {
		return this.recent_trauma;
	}

	public void setRecent_trauma(byte recent_trauma) {
		this.recent_trauma = recent_trauma;
	}

	public byte getRecurrent_infections() {
		return this.recurrent_infections;
	}

	public void setRecurrent_infections(byte recurrent_infections) {
		this.recurrent_infections = recurrent_infections;
	}

	public byte getRhinorrhea() {
		return this.rhinorrhea;
	}

	public void setRhinorrhea(byte rhinorrhea) {
		this.rhinorrhea = rhinorrhea;
	}

	public byte getSeizures() {
		return this.seizures;
	}

	public void setSeizures(byte seizures) {
		this.seizures = seizures;
	}

	public byte getSensitivity_to_Sun() {
		return this.sensitivity_to_Sun;
	}

	public void setSensitivity_to_Sun(byte sensitivity_to_Sun) {
		this.sensitivity_to_Sun = sensitivity_to_Sun;
	}

	public byte getSinus_problems() {
		return this.sinus_problems;
	}

	public void setSinus_problems(byte sinus_problems) {
		this.sinus_problems = sinus_problems;
	}

	public byte getSore_throat() {
		return this.sore_throat;
	}

	public void setSore_throat(byte sore_throat) {
		this.sore_throat = sore_throat;
	}

	public byte getSwollen_lymph_nodes() {
		return this.swollen_lymph_nodes;
	}

	public void setSwollen_lymph_nodes(byte swollen_lymph_nodes) {
		this.swollen_lymph_nodes = swollen_lymph_nodes;
	}

	public byte getSwollen_uvula() {
		return this.swollen_uvula;
	}

	public void setSwollen_uvula(byte swollen_uvula) {
		this.swollen_uvula = swollen_uvula;
	}

	public byte getSyncope() {
		return this.syncope;
	}

	public void setSyncope(byte syncope) {
		this.syncope = syncope;
	}

	public byte getTremors() {
		return this.tremors;
	}

	public void setTremors(byte tremors) {
		this.tremors = tremors;
	}

	public byte getUnusual_bruising() {
		return this.unusual_bruising;
	}

	public void setUnusual_bruising(byte unusual_bruising) {
		this.unusual_bruising = unusual_bruising;
	}

	public byte getUrethral_discharge() {
		return this.urethral_discharge;
	}

	public void setUrethral_discharge(byte urethral_discharge) {
		this.urethral_discharge = urethral_discharge;
	}

	public byte getWeight_changes() {
		return this.weight_changes;
	}

	public void setWeight_changes(byte weight_changes) {
		this.weight_changes = weight_changes;
	}

	public byte getWheeze() {
		return this.wheeze;
	}

	public void setWheeze(byte wheeze) {
		this.wheeze = wheeze;
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