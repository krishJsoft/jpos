package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pathologies database table.
 * 
 */
@Entity
@Table(name="pathologies")
public class Pathology implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int pathology_id;

	@Column(length=256)
	private String chromosome;

	@Column(length=10)
	private String code;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=256)
	private String gene;

	@Lob
	private String info;

	@Column(length=256)
	private String name;

	@Column(length=256)
	private String protein;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Consultation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Consultation_id")
	private Consultation consultation;

	//bi-directional many-to-one association to PathologyCategory
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Pat_category_Id")
	private PathologyCategory pathologyCategory;

	public Pathology() {
	}

	public int getPathology_id() {
		return this.pathology_id;
	}

	public void setPathology_id(int pathology_id) {
		this.pathology_id = pathology_id;
	}

	public String getChromosome() {
		return this.chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getGene() {
		return this.gene;
	}

	public void setGene(String gene) {
		this.gene = gene;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtein() {
		return this.protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
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

	public Consultation getConsultation() {
		return this.consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public PathologyCategory getPathologyCategory() {
		return this.pathologyCategory;
	}

	public void setPathologyCategory(PathologyCategory pathologyCategory) {
		this.pathologyCategory = pathologyCategory;
	}

}