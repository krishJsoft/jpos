package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the medicaments database table.
 * 
 */
@Entity
@Table(name="medicaments")
public class Medicament implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int medicament_id;

	@Column(length=128)
	private String active_Component;

	@Lob
	private String adverse_reaction;

	@Lob
	private String composition;

	@Column(nullable=false, length=200)
	private String contact_Person;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String dosage;

	@Lob
	private String indications;

	@Column(nullable=false, length=200)
	private String laboratory;

	private int name;

	@Lob
	private String notes;

	@Lob
	private String overdosage;

	@Lob
	private String pregnancy;

	private byte pregnancy_Warning;

	@Lob
	private String presentation;

	@Lob
	private String storage;

	@Column(length=128)
	private String therapeutic_Action;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Prescription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prescription_id")
	private Prescription prescription;

	public Medicament() {
	}

	public int getMedicament_id() {
		return this.medicament_id;
	}

	public void setMedicament_id(int medicament_id) {
		this.medicament_id = medicament_id;
	}

	public String getActive_Component() {
		return this.active_Component;
	}

	public void setActive_Component(String active_Component) {
		this.active_Component = active_Component;
	}

	public String getAdverse_reaction() {
		return this.adverse_reaction;
	}

	public void setAdverse_reaction(String adverse_reaction) {
		this.adverse_reaction = adverse_reaction;
	}

	public String getComposition() {
		return this.composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getContact_Person() {
		return this.contact_Person;
	}

	public void setContact_Person(String contact_Person) {
		this.contact_Person = contact_Person;
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

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getIndications() {
		return this.indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getLaboratory() {
		return this.laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public int getName() {
		return this.name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOverdosage() {
		return this.overdosage;
	}

	public void setOverdosage(String overdosage) {
		this.overdosage = overdosage;
	}

	public String getPregnancy() {
		return this.pregnancy;
	}

	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}

	public byte getPregnancy_Warning() {
		return this.pregnancy_Warning;
	}

	public void setPregnancy_Warning(byte pregnancy_Warning) {
		this.pregnancy_Warning = pregnancy_Warning;
	}

	public String getPresentation() {
		return this.presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getStorage() {
		return this.storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getTherapeutic_Action() {
		return this.therapeutic_Action;
	}

	public void setTherapeutic_Action(String therapeutic_Action) {
		this.therapeutic_Action = therapeutic_Action;
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

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}