package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pathology_categories database table.
 * 
 */
@Entity
@Table(name="pathology_categories")
public class PathologyCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int pat_category_Id;

	private byte active;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(nullable=false, length=256)
	private String name;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Pathology
	@OneToMany(mappedBy="pathologyCategory")
	private List<Pathology> pathologies;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	public PathologyCategory() {
	}

	public int getPat_category_Id() {
		return this.pat_category_Id;
	}

	public void setPat_category_Id(int pat_category_Id) {
		this.pat_category_Id = pat_category_Id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Pathology> getPathologies() {
		return this.pathologies;
	}

	public void setPathologies(List<Pathology> pathologies) {
		this.pathologies = pathologies;
	}

	public Pathology addPathology(Pathology pathology) {
		getPathologies().add(pathology);
		pathology.setPathologyCategory(this);

		return pathology;
	}

	public Pathology removePathology(Pathology pathology) {
		getPathologies().remove(pathology);
		pathology.setPathologyCategory(null);

		return pathology;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}