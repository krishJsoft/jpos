package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the puerperium_monitors database table.
 * 
 */
@Entity
@Table(name="puerperium_monitors")
public class PuerperiumMonitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="puerperium_monitor_id", unique=true, nullable=false)
	private int puerperiumMonitorId;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(name="lochia_amount", length=32)
	private String lochiaAmount;

	@Column(name="lochia_color", length=32)
	private String lochiaColor;

	@Column(name="lochia_odor", length=32)
	private String lochiaOdor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="puerperium_create_date")
	private Date puerperiumCreateDate;

	@Column(name="puerperium_diastolic")
	private int puerperiumDiastolic;

	@Column(name="puerperium_frequency")
	private int puerperiumFrequency;

	@Column(name="puerperium_systolic")
	private int puerperiumSystolic;

	@Column(name="puerperium_temperature")
	private double puerperiumTemperature;

	@Column(name="uterus_involution")
	private int uterusInvolution;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Perinatal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Perinatal_id")
	private Perinatal perinatal;

	public PuerperiumMonitor() {
	}

	public int getPuerperiumMonitorId() {
		return this.puerperiumMonitorId;
	}

	public void setPuerperiumMonitorId(int puerperiumMonitorId) {
		this.puerperiumMonitorId = puerperiumMonitorId;
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

	public String getLochiaAmount() {
		return this.lochiaAmount;
	}

	public void setLochiaAmount(String lochiaAmount) {
		this.lochiaAmount = lochiaAmount;
	}

	public String getLochiaColor() {
		return this.lochiaColor;
	}

	public void setLochiaColor(String lochiaColor) {
		this.lochiaColor = lochiaColor;
	}

	public String getLochiaOdor() {
		return this.lochiaOdor;
	}

	public void setLochiaOdor(String lochiaOdor) {
		this.lochiaOdor = lochiaOdor;
	}

	public Date getPuerperiumCreateDate() {
		return this.puerperiumCreateDate;
	}

	public void setPuerperiumCreateDate(Date puerperiumCreateDate) {
		this.puerperiumCreateDate = puerperiumCreateDate;
	}

	public int getPuerperiumDiastolic() {
		return this.puerperiumDiastolic;
	}

	public void setPuerperiumDiastolic(int puerperiumDiastolic) {
		this.puerperiumDiastolic = puerperiumDiastolic;
	}

	public int getPuerperiumFrequency() {
		return this.puerperiumFrequency;
	}

	public void setPuerperiumFrequency(int puerperiumFrequency) {
		this.puerperiumFrequency = puerperiumFrequency;
	}

	public int getPuerperiumSystolic() {
		return this.puerperiumSystolic;
	}

	public void setPuerperiumSystolic(int puerperiumSystolic) {
		this.puerperiumSystolic = puerperiumSystolic;
	}

	public double getPuerperiumTemperature() {
		return this.puerperiumTemperature;
	}

	public void setPuerperiumTemperature(double puerperiumTemperature) {
		this.puerperiumTemperature = puerperiumTemperature;
	}

	public int getUterusInvolution() {
		return this.uterusInvolution;
	}

	public void setUterusInvolution(int uterusInvolution) {
		this.uterusInvolution = uterusInvolution;
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