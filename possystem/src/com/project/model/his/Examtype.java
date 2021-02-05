package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the examtypes database table.
 * 
 */
@Entity
@Table(name="examtypes")
public class Examtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int examType_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=510)
	private String resultType;

	@Column(length=510)
	private String type_Name;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Examcategory
	@OneToMany(mappedBy="examtype")
	private List<Examcategory> examcategories;

	//bi-directional many-to-one association to Exam
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Exam_id")
	private Exam exam;

	public Examtype() {
	}

	public int getExamType_id() {
		return this.examType_id;
	}

	public void setExamType_id(int examType_id) {
		this.examType_id = examType_id;
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

	public String getResultType() {
		return this.resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getType_Name() {
		return this.type_Name;
	}

	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
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

	public List<Examcategory> getExamcategories() {
		return this.examcategories;
	}

	public void setExamcategories(List<Examcategory> examcategories) {
		this.examcategories = examcategories;
	}

	public Examcategory addExamcategory(Examcategory examcategory) {
		getExamcategories().add(examcategory);
		examcategory.setExamtype(this);

		return examcategory;
	}

	public Examcategory removeExamcategory(Examcategory examcategory) {
		getExamcategories().remove(examcategory);
		examcategory.setExamtype(null);

		return examcategory;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}