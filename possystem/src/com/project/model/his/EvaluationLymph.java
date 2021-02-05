package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation_lymphs database table.
 * 
 */
@Entity
@Table(name="evaluation_lymphs")
public class EvaluationLymph implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int evaluation_Lymph_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String evaluation_Lymph_Notes;

	private byte examined_Axila;

	private byte examined_Groin;

	private byte examined_Neck;

	private byte examined_Other;

	@Column(length=25)
	private String examined_Other_value;

	private byte lymph_node_exam_WNL;

	private byte lymphadenopathy_noted_in_Axila;

	private byte lymphadenopathy_noted_in_Groin;

	private byte lymphadenopathy_noted_in_Neck;

	private byte lymphadenopathy_noted_in_Other;

	@Column(length=25)
	private String lymphadenopathy_noted_in_Other_value;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Clinicchart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClinicChart_Id")
	private Clinicchart clinicchart;

	public EvaluationLymph() {
	}

	public int getEvaluation_Lymph_id() {
		return this.evaluation_Lymph_id;
	}

	public void setEvaluation_Lymph_id(int evaluation_Lymph_id) {
		this.evaluation_Lymph_id = evaluation_Lymph_id;
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

	public String getEvaluation_Lymph_Notes() {
		return this.evaluation_Lymph_Notes;
	}

	public void setEvaluation_Lymph_Notes(String evaluation_Lymph_Notes) {
		this.evaluation_Lymph_Notes = evaluation_Lymph_Notes;
	}

	public byte getExamined_Axila() {
		return this.examined_Axila;
	}

	public void setExamined_Axila(byte examined_Axila) {
		this.examined_Axila = examined_Axila;
	}

	public byte getExamined_Groin() {
		return this.examined_Groin;
	}

	public void setExamined_Groin(byte examined_Groin) {
		this.examined_Groin = examined_Groin;
	}

	public byte getExamined_Neck() {
		return this.examined_Neck;
	}

	public void setExamined_Neck(byte examined_Neck) {
		this.examined_Neck = examined_Neck;
	}

	public byte getExamined_Other() {
		return this.examined_Other;
	}

	public void setExamined_Other(byte examined_Other) {
		this.examined_Other = examined_Other;
	}

	public String getExamined_Other_value() {
		return this.examined_Other_value;
	}

	public void setExamined_Other_value(String examined_Other_value) {
		this.examined_Other_value = examined_Other_value;
	}

	public byte getLymph_node_exam_WNL() {
		return this.lymph_node_exam_WNL;
	}

	public void setLymph_node_exam_WNL(byte lymph_node_exam_WNL) {
		this.lymph_node_exam_WNL = lymph_node_exam_WNL;
	}

	public byte getLymphadenopathy_noted_in_Axila() {
		return this.lymphadenopathy_noted_in_Axila;
	}

	public void setLymphadenopathy_noted_in_Axila(byte lymphadenopathy_noted_in_Axila) {
		this.lymphadenopathy_noted_in_Axila = lymphadenopathy_noted_in_Axila;
	}

	public byte getLymphadenopathy_noted_in_Groin() {
		return this.lymphadenopathy_noted_in_Groin;
	}

	public void setLymphadenopathy_noted_in_Groin(byte lymphadenopathy_noted_in_Groin) {
		this.lymphadenopathy_noted_in_Groin = lymphadenopathy_noted_in_Groin;
	}

	public byte getLymphadenopathy_noted_in_Neck() {
		return this.lymphadenopathy_noted_in_Neck;
	}

	public void setLymphadenopathy_noted_in_Neck(byte lymphadenopathy_noted_in_Neck) {
		this.lymphadenopathy_noted_in_Neck = lymphadenopathy_noted_in_Neck;
	}

	public byte getLymphadenopathy_noted_in_Other() {
		return this.lymphadenopathy_noted_in_Other;
	}

	public void setLymphadenopathy_noted_in_Other(byte lymphadenopathy_noted_in_Other) {
		this.lymphadenopathy_noted_in_Other = lymphadenopathy_noted_in_Other;
	}

	public String getLymphadenopathy_noted_in_Other_value() {
		return this.lymphadenopathy_noted_in_Other_value;
	}

	public void setLymphadenopathy_noted_in_Other_value(String lymphadenopathy_noted_in_Other_value) {
		this.lymphadenopathy_noted_in_Other_value = lymphadenopathy_noted_in_Other_value;
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

	public Clinicchart getClinicchart() {
		return this.clinicchart;
	}

	public void setClinicchart(Clinicchart clinicchart) {
		this.clinicchart = clinicchart;
	}

}