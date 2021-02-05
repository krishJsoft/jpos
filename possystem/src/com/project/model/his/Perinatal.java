package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the perinatals database table.
 * 
 */
@Entity
@Table(name="perinatals")
public class Perinatal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int perinatal_id;

	private byte abortion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="admission_date")
	private Date admissionDate;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(name="died_at_delivery")
	private byte diedAtDelivery;

	@Column(name="died_at_the_hospital")
	private byte diedAtTheHospital;

	@Column(name="died_being_transferred")
	private byte diedBeingTransferred;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dismissed;

	private byte episiotomy;

	@Column(name="fetus_presentation", length=32)
	private String fetusPresentation;

	private byte forceps;

	@Column(name="gestational_days")
	private int gestationalDays;

	@Column(name="gestational_weeks")
	private int gestationalWeeks;

	@Column(name="gravida_number")
	private int gravidaNumber;

	@Column(length=256)
	private String name;

	@Lob
	private String notes;

	@Column(name="placenta_incomplete")
	private byte placentaIncomplete;

	@Column(name="placenta_retained")
	private byte placentaRetained;

	@Column(name="prenatal_evaluations")
	private int prenatalEvaluations;

	@Column(name="start_labor_mode", length=32)
	private String startLaborMode;

	@Column(name="vaginal_tearing")
	private byte vaginalTearing;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Newborn
	@OneToMany(mappedBy="perinatal")
	private List<Newborn> newborns;

	//bi-directional many-to-one association to PerinatalMonitor
	@OneToMany(mappedBy="perinatal")
	private List<PerinatalMonitor> perinatalMonitors;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to PuerperiumMonitor
	@OneToMany(mappedBy="perinatal")
	private List<PuerperiumMonitor> puerperiumMonitors;

	public Perinatal() {
	}

	public int getPerinatal_id() {
		return this.perinatal_id;
	}

	public void setPerinatal_id(int perinatal_id) {
		this.perinatal_id = perinatal_id;
	}

	public byte getAbortion() {
		return this.abortion;
	}

	public void setAbortion(byte abortion) {
		this.abortion = abortion;
	}

	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
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

	public byte getDiedAtDelivery() {
		return this.diedAtDelivery;
	}

	public void setDiedAtDelivery(byte diedAtDelivery) {
		this.diedAtDelivery = diedAtDelivery;
	}

	public byte getDiedAtTheHospital() {
		return this.diedAtTheHospital;
	}

	public void setDiedAtTheHospital(byte diedAtTheHospital) {
		this.diedAtTheHospital = diedAtTheHospital;
	}

	public byte getDiedBeingTransferred() {
		return this.diedBeingTransferred;
	}

	public void setDiedBeingTransferred(byte diedBeingTransferred) {
		this.diedBeingTransferred = diedBeingTransferred;
	}

	public Date getDismissed() {
		return this.dismissed;
	}

	public void setDismissed(Date dismissed) {
		this.dismissed = dismissed;
	}

	public byte getEpisiotomy() {
		return this.episiotomy;
	}

	public void setEpisiotomy(byte episiotomy) {
		this.episiotomy = episiotomy;
	}

	public String getFetusPresentation() {
		return this.fetusPresentation;
	}

	public void setFetusPresentation(String fetusPresentation) {
		this.fetusPresentation = fetusPresentation;
	}

	public byte getForceps() {
		return this.forceps;
	}

	public void setForceps(byte forceps) {
		this.forceps = forceps;
	}

	public int getGestationalDays() {
		return this.gestationalDays;
	}

	public void setGestationalDays(int gestationalDays) {
		this.gestationalDays = gestationalDays;
	}

	public int getGestationalWeeks() {
		return this.gestationalWeeks;
	}

	public void setGestationalWeeks(int gestationalWeeks) {
		this.gestationalWeeks = gestationalWeeks;
	}

	public int getGravidaNumber() {
		return this.gravidaNumber;
	}

	public void setGravidaNumber(int gravidaNumber) {
		this.gravidaNumber = gravidaNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public byte getPlacentaIncomplete() {
		return this.placentaIncomplete;
	}

	public void setPlacentaIncomplete(byte placentaIncomplete) {
		this.placentaIncomplete = placentaIncomplete;
	}

	public byte getPlacentaRetained() {
		return this.placentaRetained;
	}

	public void setPlacentaRetained(byte placentaRetained) {
		this.placentaRetained = placentaRetained;
	}

	public int getPrenatalEvaluations() {
		return this.prenatalEvaluations;
	}

	public void setPrenatalEvaluations(int prenatalEvaluations) {
		this.prenatalEvaluations = prenatalEvaluations;
	}

	public String getStartLaborMode() {
		return this.startLaborMode;
	}

	public void setStartLaborMode(String startLaborMode) {
		this.startLaborMode = startLaborMode;
	}

	public byte getVaginalTearing() {
		return this.vaginalTearing;
	}

	public void setVaginalTearing(byte vaginalTearing) {
		this.vaginalTearing = vaginalTearing;
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

	public List<Newborn> getNewborns() {
		return this.newborns;
	}

	public void setNewborns(List<Newborn> newborns) {
		this.newborns = newborns;
	}

	public Newborn addNewborn(Newborn newborn) {
		getNewborns().add(newborn);
		newborn.setPerinatal(this);

		return newborn;
	}

	public Newborn removeNewborn(Newborn newborn) {
		getNewborns().remove(newborn);
		newborn.setPerinatal(null);

		return newborn;
	}

	public List<PerinatalMonitor> getPerinatalMonitors() {
		return this.perinatalMonitors;
	}

	public void setPerinatalMonitors(List<PerinatalMonitor> perinatalMonitors) {
		this.perinatalMonitors = perinatalMonitors;
	}

	public PerinatalMonitor addPerinatalMonitor(PerinatalMonitor perinatalMonitor) {
		getPerinatalMonitors().add(perinatalMonitor);
		perinatalMonitor.setPerinatal(this);

		return perinatalMonitor;
	}

	public PerinatalMonitor removePerinatalMonitor(PerinatalMonitor perinatalMonitor) {
		getPerinatalMonitors().remove(perinatalMonitor);
		perinatalMonitor.setPerinatal(null);

		return perinatalMonitor;
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

	public List<PuerperiumMonitor> getPuerperiumMonitors() {
		return this.puerperiumMonitors;
	}

	public void setPuerperiumMonitors(List<PuerperiumMonitor> puerperiumMonitors) {
		this.puerperiumMonitors = puerperiumMonitors;
	}

	public PuerperiumMonitor addPuerperiumMonitor(PuerperiumMonitor puerperiumMonitor) {
		getPuerperiumMonitors().add(puerperiumMonitor);
		puerperiumMonitor.setPerinatal(this);

		return puerperiumMonitor;
	}

	public PuerperiumMonitor removePuerperiumMonitor(PuerperiumMonitor puerperiumMonitor) {
		getPuerperiumMonitors().remove(puerperiumMonitor);
		puerperiumMonitor.setPerinatal(null);

		return puerperiumMonitor;
	}

}