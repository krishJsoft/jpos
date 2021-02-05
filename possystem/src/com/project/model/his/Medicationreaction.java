package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the medicationreactions database table.
 * 
 */
@Entity
@Table(name="medicationreactions")
public class Medicationreaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int medicationReaction_Id;

	@Column(length=300)
	private String active_Ingredient;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date_of_Reaction;

	@Column(length=50)
	private String dosage_taken_for_Reaction;

	@Column(length=350)
	private String last_Known_Reaction_Description;

	@Column(length=100)
	private String medication_Name;

	@Column(length=300)
	private String more_Brand_Names;

	@Column(length=350)
	private String reaction_Notes;

	@Column(length=100)
	private String recallConbinationWithOtherDrug;

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

	public Medicationreaction() {
	}

	public int getMedicationReaction_Id() {
		return this.medicationReaction_Id;
	}

	public void setMedicationReaction_Id(int medicationReaction_Id) {
		this.medicationReaction_Id = medicationReaction_Id;
	}

	public String getActive_Ingredient() {
		return this.active_Ingredient;
	}

	public void setActive_Ingredient(String active_Ingredient) {
		this.active_Ingredient = active_Ingredient;
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

	public Date getDate_of_Reaction() {
		return this.date_of_Reaction;
	}

	public void setDate_of_Reaction(Date date_of_Reaction) {
		this.date_of_Reaction = date_of_Reaction;
	}

	public String getDosage_taken_for_Reaction() {
		return this.dosage_taken_for_Reaction;
	}

	public void setDosage_taken_for_Reaction(String dosage_taken_for_Reaction) {
		this.dosage_taken_for_Reaction = dosage_taken_for_Reaction;
	}

	public String getLast_Known_Reaction_Description() {
		return this.last_Known_Reaction_Description;
	}

	public void setLast_Known_Reaction_Description(String last_Known_Reaction_Description) {
		this.last_Known_Reaction_Description = last_Known_Reaction_Description;
	}

	public String getMedication_Name() {
		return this.medication_Name;
	}

	public void setMedication_Name(String medication_Name) {
		this.medication_Name = medication_Name;
	}

	public String getMore_Brand_Names() {
		return this.more_Brand_Names;
	}

	public void setMore_Brand_Names(String more_Brand_Names) {
		this.more_Brand_Names = more_Brand_Names;
	}

	public String getReaction_Notes() {
		return this.reaction_Notes;
	}

	public void setReaction_Notes(String reaction_Notes) {
		this.reaction_Notes = reaction_Notes;
	}

	public String getRecallConbinationWithOtherDrug() {
		return this.recallConbinationWithOtherDrug;
	}

	public void setRecallConbinationWithOtherDrug(String recallConbinationWithOtherDrug) {
		this.recallConbinationWithOtherDrug = recallConbinationWithOtherDrug;
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