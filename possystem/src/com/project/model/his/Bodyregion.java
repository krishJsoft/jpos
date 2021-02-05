package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bodyregions database table.
 * 
 */
@Entity
@Table(name="bodyregions")
public class Bodyregion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int bodyRegion_id;

	@Column(length=100)
	private String bodyRegion_Name;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Exam
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Exam_id")
	private Exam exam;

	public Bodyregion() {
	}

	public int getBodyRegion_id() {
		return this.bodyRegion_id;
	}

	public void setBodyRegion_id(int bodyRegion_id) {
		this.bodyRegion_id = bodyRegion_id;
	}

	public String getBodyRegion_Name() {
		return this.bodyRegion_Name;
	}

	public void setBodyRegion_Name(String bodyRegion_Name) {
		this.bodyRegion_Name = bodyRegion_Name;
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

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}