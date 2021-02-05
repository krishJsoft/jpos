package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the perinatal_monitors database table.
 * 
 */
@Entity
@Table(name="perinatal_monitors")
public class PerinatalMonitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="perinatal_monitor_id", unique=true, nullable=false)
	private int perinatalMonitorId;

	private byte bleeding;

	private int contractions;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int diastolic;

	private int dilation;

	@Column(name="f_frequency")
	private int fFrequency;

	@Column(name="fetus_position", length=32)
	private String fetusPosition;

	private int frequency;

	@Column(name="fundal_height")
	private int fundalHeight;

	private byte meconium;

	private int systolic;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Perinatal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Perinatal_id")
	private Perinatal perinatal;

	public PerinatalMonitor() {
	}

	public int getPerinatalMonitorId() {
		return this.perinatalMonitorId;
	}

	public void setPerinatalMonitorId(int perinatalMonitorId) {
		this.perinatalMonitorId = perinatalMonitorId;
	}

	public byte getBleeding() {
		return this.bleeding;
	}

	public void setBleeding(byte bleeding) {
		this.bleeding = bleeding;
	}

	public int getContractions() {
		return this.contractions;
	}

	public void setContractions(int contractions) {
		this.contractions = contractions;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDiastolic() {
		return this.diastolic;
	}

	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}

	public int getDilation() {
		return this.dilation;
	}

	public void setDilation(int dilation) {
		this.dilation = dilation;
	}

	public int getFFrequency() {
		return this.fFrequency;
	}

	public void setFFrequency(int fFrequency) {
		this.fFrequency = fFrequency;
	}

	public String getFetusPosition() {
		return this.fetusPosition;
	}

	public void setFetusPosition(String fetusPosition) {
		this.fetusPosition = fetusPosition;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getFundalHeight() {
		return this.fundalHeight;
	}

	public void setFundalHeight(int fundalHeight) {
		this.fundalHeight = fundalHeight;
	}

	public byte getMeconium() {
		return this.meconium;
	}

	public void setMeconium(byte meconium) {
		this.meconium = meconium;
	}

	public int getSystolic() {
		return this.systolic;
	}

	public void setSystolic(int systolic) {
		this.systolic = systolic;
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

	public Perinatal getPerinatal() {
		return this.perinatal;
	}

	public void setPerinatal(Perinatal perinatal) {
		this.perinatal = perinatal;
	}

}