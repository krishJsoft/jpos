package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the examcategories database table.
 * 
 */
@Entity
@Table(name="examcategories")
public class Examcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int examCategory_id;

	@Column(length=510)
	private String category_Name;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Examtype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ExamType_id")
	private Examtype examtype;

	public Examcategory() {
	}

	public int getExamCategory_id() {
		return this.examCategory_id;
	}

	public void setExamCategory_id(int examCategory_id) {
		this.examCategory_id = examCategory_id;
	}

	public String getCategory_Name() {
		return this.category_Name;
	}

	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
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

	public Examtype getExamtype() {
		return this.examtype;
	}

	public void setExamtype(Examtype examtype) {
		this.examtype = examtype;
	}

}