package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliniccharts database table.
 * 
 */
@Entity
@Table(name="cliniccharts")
public class Clinicchart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int clinicChart_Id;

	@Column(nullable=false, length=200)
	private String clinic_Chart_Description;

	@Column(nullable=false, length=200)
	private String clinic_Chart_Extra_info;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to EvaluationCardio
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationCardio> evaluationCardios;

	//bi-directional many-to-one association to EvaluationConstitutional
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationConstitutional> evaluationConstitutionals;

	//bi-directional many-to-one association to EvaluationEnt
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationEnt> evaluationEnts;

	//bi-directional many-to-one association to EvaluationExtrem
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationExtrem> evaluationExtrems;

	//bi-directional many-to-one association to EvaluationGastro
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationGastro> evaluationGastros;

	//bi-directional many-to-one association to EvaluationLymph
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationLymph> evaluationLymphs;

	//bi-directional many-to-one association to EvaluationMusc
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationMusc> evaluationMuscs;

	//bi-directional many-to-one association to EvaluationNeck
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationNeck> evaluationNecks;

	//bi-directional many-to-one association to EvaluationNeuro
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationNeuro> evaluationNeuros;

	//bi-directional many-to-one association to EvaluationRespi
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationRespi> evaluationRespis;

	//bi-directional many-to-one association to EvaluationSkin
	@OneToMany(mappedBy="clinicchart")
	private List<EvaluationSkin> evaluationSkins;

	//bi-directional many-to-one association to HereditaryIllne
	@OneToMany(mappedBy="clinicchart")
	private List<HereditaryIllne> hereditaryIllnes;

	public Clinicchart() {
	}

	public int getClinicChart_Id() {
		return this.clinicChart_Id;
	}

	public void setClinicChart_Id(int clinicChart_Id) {
		this.clinicChart_Id = clinicChart_Id;
	}

	public String getClinic_Chart_Description() {
		return this.clinic_Chart_Description;
	}

	public void setClinic_Chart_Description(String clinic_Chart_Description) {
		this.clinic_Chart_Description = clinic_Chart_Description;
	}

	public String getClinic_Chart_Extra_info() {
		return this.clinic_Chart_Extra_info;
	}

	public void setClinic_Chart_Extra_info(String clinic_Chart_Extra_info) {
		this.clinic_Chart_Extra_info = clinic_Chart_Extra_info;
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

	public List<EvaluationCardio> getEvaluationCardios() {
		return this.evaluationCardios;
	}

	public void setEvaluationCardios(List<EvaluationCardio> evaluationCardios) {
		this.evaluationCardios = evaluationCardios;
	}

	public EvaluationCardio addEvaluationCardio(EvaluationCardio evaluationCardio) {
		getEvaluationCardios().add(evaluationCardio);
		evaluationCardio.setClinicchart(this);

		return evaluationCardio;
	}

	public EvaluationCardio removeEvaluationCardio(EvaluationCardio evaluationCardio) {
		getEvaluationCardios().remove(evaluationCardio);
		evaluationCardio.setClinicchart(null);

		return evaluationCardio;
	}

	public List<EvaluationConstitutional> getEvaluationConstitutionals() {
		return this.evaluationConstitutionals;
	}

	public void setEvaluationConstitutionals(List<EvaluationConstitutional> evaluationConstitutionals) {
		this.evaluationConstitutionals = evaluationConstitutionals;
	}

	public EvaluationConstitutional addEvaluationConstitutional(EvaluationConstitutional evaluationConstitutional) {
		getEvaluationConstitutionals().add(evaluationConstitutional);
		evaluationConstitutional.setClinicchart(this);

		return evaluationConstitutional;
	}

	public EvaluationConstitutional removeEvaluationConstitutional(EvaluationConstitutional evaluationConstitutional) {
		getEvaluationConstitutionals().remove(evaluationConstitutional);
		evaluationConstitutional.setClinicchart(null);

		return evaluationConstitutional;
	}

	public List<EvaluationEnt> getEvaluationEnts() {
		return this.evaluationEnts;
	}

	public void setEvaluationEnts(List<EvaluationEnt> evaluationEnts) {
		this.evaluationEnts = evaluationEnts;
	}

	public EvaluationEnt addEvaluationEnt(EvaluationEnt evaluationEnt) {
		getEvaluationEnts().add(evaluationEnt);
		evaluationEnt.setClinicchart(this);

		return evaluationEnt;
	}

	public EvaluationEnt removeEvaluationEnt(EvaluationEnt evaluationEnt) {
		getEvaluationEnts().remove(evaluationEnt);
		evaluationEnt.setClinicchart(null);

		return evaluationEnt;
	}

	public List<EvaluationExtrem> getEvaluationExtrems() {
		return this.evaluationExtrems;
	}

	public void setEvaluationExtrems(List<EvaluationExtrem> evaluationExtrems) {
		this.evaluationExtrems = evaluationExtrems;
	}

	public EvaluationExtrem addEvaluationExtrem(EvaluationExtrem evaluationExtrem) {
		getEvaluationExtrems().add(evaluationExtrem);
		evaluationExtrem.setClinicchart(this);

		return evaluationExtrem;
	}

	public EvaluationExtrem removeEvaluationExtrem(EvaluationExtrem evaluationExtrem) {
		getEvaluationExtrems().remove(evaluationExtrem);
		evaluationExtrem.setClinicchart(null);

		return evaluationExtrem;
	}

	public List<EvaluationGastro> getEvaluationGastros() {
		return this.evaluationGastros;
	}

	public void setEvaluationGastros(List<EvaluationGastro> evaluationGastros) {
		this.evaluationGastros = evaluationGastros;
	}

	public EvaluationGastro addEvaluationGastro(EvaluationGastro evaluationGastro) {
		getEvaluationGastros().add(evaluationGastro);
		evaluationGastro.setClinicchart(this);

		return evaluationGastro;
	}

	public EvaluationGastro removeEvaluationGastro(EvaluationGastro evaluationGastro) {
		getEvaluationGastros().remove(evaluationGastro);
		evaluationGastro.setClinicchart(null);

		return evaluationGastro;
	}

	public List<EvaluationLymph> getEvaluationLymphs() {
		return this.evaluationLymphs;
	}

	public void setEvaluationLymphs(List<EvaluationLymph> evaluationLymphs) {
		this.evaluationLymphs = evaluationLymphs;
	}

	public EvaluationLymph addEvaluationLymph(EvaluationLymph evaluationLymph) {
		getEvaluationLymphs().add(evaluationLymph);
		evaluationLymph.setClinicchart(this);

		return evaluationLymph;
	}

	public EvaluationLymph removeEvaluationLymph(EvaluationLymph evaluationLymph) {
		getEvaluationLymphs().remove(evaluationLymph);
		evaluationLymph.setClinicchart(null);

		return evaluationLymph;
	}

	public List<EvaluationMusc> getEvaluationMuscs() {
		return this.evaluationMuscs;
	}

	public void setEvaluationMuscs(List<EvaluationMusc> evaluationMuscs) {
		this.evaluationMuscs = evaluationMuscs;
	}

	public EvaluationMusc addEvaluationMusc(EvaluationMusc evaluationMusc) {
		getEvaluationMuscs().add(evaluationMusc);
		evaluationMusc.setClinicchart(this);

		return evaluationMusc;
	}

	public EvaluationMusc removeEvaluationMusc(EvaluationMusc evaluationMusc) {
		getEvaluationMuscs().remove(evaluationMusc);
		evaluationMusc.setClinicchart(null);

		return evaluationMusc;
	}

	public List<EvaluationNeck> getEvaluationNecks() {
		return this.evaluationNecks;
	}

	public void setEvaluationNecks(List<EvaluationNeck> evaluationNecks) {
		this.evaluationNecks = evaluationNecks;
	}

	public EvaluationNeck addEvaluationNeck(EvaluationNeck evaluationNeck) {
		getEvaluationNecks().add(evaluationNeck);
		evaluationNeck.setClinicchart(this);

		return evaluationNeck;
	}

	public EvaluationNeck removeEvaluationNeck(EvaluationNeck evaluationNeck) {
		getEvaluationNecks().remove(evaluationNeck);
		evaluationNeck.setClinicchart(null);

		return evaluationNeck;
	}

	public List<EvaluationNeuro> getEvaluationNeuros() {
		return this.evaluationNeuros;
	}

	public void setEvaluationNeuros(List<EvaluationNeuro> evaluationNeuros) {
		this.evaluationNeuros = evaluationNeuros;
	}

	public EvaluationNeuro addEvaluationNeuro(EvaluationNeuro evaluationNeuro) {
		getEvaluationNeuros().add(evaluationNeuro);
		evaluationNeuro.setClinicchart(this);

		return evaluationNeuro;
	}

	public EvaluationNeuro removeEvaluationNeuro(EvaluationNeuro evaluationNeuro) {
		getEvaluationNeuros().remove(evaluationNeuro);
		evaluationNeuro.setClinicchart(null);

		return evaluationNeuro;
	}

	public List<EvaluationRespi> getEvaluationRespis() {
		return this.evaluationRespis;
	}

	public void setEvaluationRespis(List<EvaluationRespi> evaluationRespis) {
		this.evaluationRespis = evaluationRespis;
	}

	public EvaluationRespi addEvaluationRespi(EvaluationRespi evaluationRespi) {
		getEvaluationRespis().add(evaluationRespi);
		evaluationRespi.setClinicchart(this);

		return evaluationRespi;
	}

	public EvaluationRespi removeEvaluationRespi(EvaluationRespi evaluationRespi) {
		getEvaluationRespis().remove(evaluationRespi);
		evaluationRespi.setClinicchart(null);

		return evaluationRespi;
	}

	public List<EvaluationSkin> getEvaluationSkins() {
		return this.evaluationSkins;
	}

	public void setEvaluationSkins(List<EvaluationSkin> evaluationSkins) {
		this.evaluationSkins = evaluationSkins;
	}

	public EvaluationSkin addEvaluationSkin(EvaluationSkin evaluationSkin) {
		getEvaluationSkins().add(evaluationSkin);
		evaluationSkin.setClinicchart(this);

		return evaluationSkin;
	}

	public EvaluationSkin removeEvaluationSkin(EvaluationSkin evaluationSkin) {
		getEvaluationSkins().remove(evaluationSkin);
		evaluationSkin.setClinicchart(null);

		return evaluationSkin;
	}

	public List<HereditaryIllne> getHereditaryIllnes() {
		return this.hereditaryIllnes;
	}

	public void setHereditaryIllnes(List<HereditaryIllne> hereditaryIllnes) {
		this.hereditaryIllnes = hereditaryIllnes;
	}

	public HereditaryIllne addHereditaryIllne(HereditaryIllne hereditaryIllne) {
		getHereditaryIllnes().add(hereditaryIllne);
		hereditaryIllne.setClinicchart(this);

		return hereditaryIllne;
	}

	public HereditaryIllne removeHereditaryIllne(HereditaryIllne hereditaryIllne) {
		getHereditaryIllnes().remove(hereditaryIllne);
		hereditaryIllne.setClinicchart(null);

		return hereditaryIllne;
	}

}