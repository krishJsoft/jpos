package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the newborns database table.
 * 
 */
@Entity
@Table(name="newborns")
public class Newborn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int newborn_id;

	private int apgar1;

	private int apgar5;

	private byte bd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birth_date", nullable=false)
	private Date birthDate;

	@Column(name="cephalic_perimeter")
	private int cephalicPerimeter;

	private int cod;

	@Column(nullable=false, length=128)
	private String code;

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

	private int length;

	private byte meconium;

	@Column(length=256)
	private String name;

	@Lob
	private String notes;

	@Lob
	private byte[] photo;

	@Column(name="reanimation_aspiration")
	private byte reanimationAspiration;

	@Column(name="reanimation_intubation")
	private byte reanimationIntubation;

	@Column(name="reanimation_mask")
	private byte reanimationMask;

	@Column(name="reanimation_oxygen")
	private byte reanimationOxygen;

	@Column(name="reanimation_stimulation")
	private byte reanimationStimulation;

	private int responsible;

	@Column(nullable=false, length=32)
	private String sex;

	@Column(name="test_audition")
	private byte testAudition;

	@Column(name="test_billirubin")
	private byte testBillirubin;

	@Column(name="test_chagas")
	private byte testChagas;

	@Column(name="test_metabolic")
	private byte testMetabolic;

	@Column(name="test_toxo")
	private byte testToxo;

	@Column(name="test_vdrl")
	private byte testVdrl;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tod;

	private int weight;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Perinatal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Perinatal_id")
	private Perinatal perinatal;

	public Newborn() {
	}

	public int getNewborn_id() {
		return this.newborn_id;
	}

	public void setNewborn_id(int newborn_id) {
		this.newborn_id = newborn_id;
	}

	public int getApgar1() {
		return this.apgar1;
	}

	public void setApgar1(int apgar1) {
		this.apgar1 = apgar1;
	}

	public int getApgar5() {
		return this.apgar5;
	}

	public void setApgar5(int apgar5) {
		this.apgar5 = apgar5;
	}

	public byte getBd() {
		return this.bd;
	}

	public void setBd(byte bd) {
		this.bd = bd;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getCephalicPerimeter() {
		return this.cephalicPerimeter;
	}

	public void setCephalicPerimeter(int cephalicPerimeter) {
		this.cephalicPerimeter = cephalicPerimeter;
	}

	public int getCod() {
		return this.cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
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

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getMeconium() {
		return this.meconium;
	}

	public void setMeconium(byte meconium) {
		this.meconium = meconium;
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

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte getReanimationAspiration() {
		return this.reanimationAspiration;
	}

	public void setReanimationAspiration(byte reanimationAspiration) {
		this.reanimationAspiration = reanimationAspiration;
	}

	public byte getReanimationIntubation() {
		return this.reanimationIntubation;
	}

	public void setReanimationIntubation(byte reanimationIntubation) {
		this.reanimationIntubation = reanimationIntubation;
	}

	public byte getReanimationMask() {
		return this.reanimationMask;
	}

	public void setReanimationMask(byte reanimationMask) {
		this.reanimationMask = reanimationMask;
	}

	public byte getReanimationOxygen() {
		return this.reanimationOxygen;
	}

	public void setReanimationOxygen(byte reanimationOxygen) {
		this.reanimationOxygen = reanimationOxygen;
	}

	public byte getReanimationStimulation() {
		return this.reanimationStimulation;
	}

	public void setReanimationStimulation(byte reanimationStimulation) {
		this.reanimationStimulation = reanimationStimulation;
	}

	public int getResponsible() {
		return this.responsible;
	}

	public void setResponsible(int responsible) {
		this.responsible = responsible;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public byte getTestAudition() {
		return this.testAudition;
	}

	public void setTestAudition(byte testAudition) {
		this.testAudition = testAudition;
	}

	public byte getTestBillirubin() {
		return this.testBillirubin;
	}

	public void setTestBillirubin(byte testBillirubin) {
		this.testBillirubin = testBillirubin;
	}

	public byte getTestChagas() {
		return this.testChagas;
	}

	public void setTestChagas(byte testChagas) {
		this.testChagas = testChagas;
	}

	public byte getTestMetabolic() {
		return this.testMetabolic;
	}

	public void setTestMetabolic(byte testMetabolic) {
		this.testMetabolic = testMetabolic;
	}

	public byte getTestToxo() {
		return this.testToxo;
	}

	public void setTestToxo(byte testToxo) {
		this.testToxo = testToxo;
	}

	public byte getTestVdrl() {
		return this.testVdrl;
	}

	public void setTestVdrl(byte testVdrl) {
		this.testVdrl = testVdrl;
	}

	public Date getTod() {
		return this.tod;
	}

	public void setTod(Date tod) {
		this.tod = tod;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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

	public Perinatal getPerinatal() {
		return this.perinatal;
	}

	public void setPerinatal(Perinatal perinatal) {
		this.perinatal = perinatal;
	}

}