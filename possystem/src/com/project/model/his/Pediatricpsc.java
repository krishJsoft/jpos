package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pediatricpscs database table.
 * 
 */
@Entity
@Table(name="pediatricpscs")
public class Pediatricpsc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int pediatricPSC_id;

	@Column(length=30)
	private String absent_from_school;

	@Column(length=30)
	private String acts_as_if_driven_by_a_motor;

	@Column(length=30)
	private String acts_younger_than_children_his_or_her_age;

	@Column(length=30)
	private String blames_others_for_his_or_her_troubles;

	@Column(length=30)
	private String complains_of_aches_and_painsSpends_more_time_alone;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=30)
	private String daydreams_too_much;

	@Column(length=30)
	private String distracted_easily;

	@Column(length=30)
	private String does_not_listen_to_rules;

	@Column(length=30)
	private String does_not_show_feelings;

	@Column(length=30)
	private String does_not_understand_other_people_s_feelings;

	@Temporal(TemporalType.TIMESTAMP)
	private Date evaluation_Date_;

	@Column(length=30)
	private String feels_he_or_she_is_bad;

	@Column(length=30)
	private String feels_hopeless;

	@Column(length=30)
	private String feels_sad__unhappy;

	@Column(length=30)
	private String fidgety__unable_to_sit_still;

	@Column(length=30)
	private String fights_with_other_children;

	@Column(length=30)
	private String gets_hurt_frequently;

	private byte has_emotional_or_behavioral_problems_for_which_needs_help;

	@Column(length=30)
	private String has_trouble_concentrating;

	@Column(length=30)
	private String has_trouble_sleeping;

	@Column(length=30)
	private String has_trouble_with_teacher;

	@Column(length=30)
	private String is_afraid_of_new_situations;

	@Column(length=30)
	private String is_down_on_him_or_herself;

	@Column(length=30)
	private String is_irritable__angry;

	@Column(length=30)
	private String less_interested_in_friends;

	@Column(length=30)
	private String less_interested_in_school;

	private byte need_to__receive_services_for_these_problems;

	private int PSC_Score;

	@Column(length=30)
	private String refuses_to_share;

	@Column(length=30)
	private String school_grades_dropping;

	@Column(length=30)
	private String seems_to_be_having_less_fun;

	@Column(length=30)
	private String takes_things_that_do_not_belong_to_him_or_her;

	@Column(length=30)
	private String takes_unnecessary_risks;

	@Column(length=30)
	private String teases_others;

	@Column(length=30)
	private String tires_easily__has_little_energy;

	@Column(length=30)
	private String visits_the_doctor_with_doctor_finding_nothing_wrong;

	@Column(length=30)
	private String wants_to_be_with_you_more_than_before;

	@Column(length=250)
	private String what_services;

	@Column(length=30)
	private String worries_a_lot;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Pediatricpsc() {
	}

	public int getPediatricPSC_id() {
		return this.pediatricPSC_id;
	}

	public void setPediatricPSC_id(int pediatricPSC_id) {
		this.pediatricPSC_id = pediatricPSC_id;
	}

	public String getAbsent_from_school() {
		return this.absent_from_school;
	}

	public void setAbsent_from_school(String absent_from_school) {
		this.absent_from_school = absent_from_school;
	}

	public String getActs_as_if_driven_by_a_motor() {
		return this.acts_as_if_driven_by_a_motor;
	}

	public void setActs_as_if_driven_by_a_motor(String acts_as_if_driven_by_a_motor) {
		this.acts_as_if_driven_by_a_motor = acts_as_if_driven_by_a_motor;
	}

	public String getActs_younger_than_children_his_or_her_age() {
		return this.acts_younger_than_children_his_or_her_age;
	}

	public void setActs_younger_than_children_his_or_her_age(String acts_younger_than_children_his_or_her_age) {
		this.acts_younger_than_children_his_or_her_age = acts_younger_than_children_his_or_her_age;
	}

	public String getBlames_others_for_his_or_her_troubles() {
		return this.blames_others_for_his_or_her_troubles;
	}

	public void setBlames_others_for_his_or_her_troubles(String blames_others_for_his_or_her_troubles) {
		this.blames_others_for_his_or_her_troubles = blames_others_for_his_or_her_troubles;
	}

	public String getComplains_of_aches_and_painsSpends_more_time_alone() {
		return this.complains_of_aches_and_painsSpends_more_time_alone;
	}

	public void setComplains_of_aches_and_painsSpends_more_time_alone(String complains_of_aches_and_painsSpends_more_time_alone) {
		this.complains_of_aches_and_painsSpends_more_time_alone = complains_of_aches_and_painsSpends_more_time_alone;
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

	public String getDaydreams_too_much() {
		return this.daydreams_too_much;
	}

	public void setDaydreams_too_much(String daydreams_too_much) {
		this.daydreams_too_much = daydreams_too_much;
	}

	public String getDistracted_easily() {
		return this.distracted_easily;
	}

	public void setDistracted_easily(String distracted_easily) {
		this.distracted_easily = distracted_easily;
	}

	public String getDoes_not_listen_to_rules() {
		return this.does_not_listen_to_rules;
	}

	public void setDoes_not_listen_to_rules(String does_not_listen_to_rules) {
		this.does_not_listen_to_rules = does_not_listen_to_rules;
	}

	public String getDoes_not_show_feelings() {
		return this.does_not_show_feelings;
	}

	public void setDoes_not_show_feelings(String does_not_show_feelings) {
		this.does_not_show_feelings = does_not_show_feelings;
	}

	public String getDoes_not_understand_other_people_s_feelings() {
		return this.does_not_understand_other_people_s_feelings;
	}

	public void setDoes_not_understand_other_people_s_feelings(String does_not_understand_other_people_s_feelings) {
		this.does_not_understand_other_people_s_feelings = does_not_understand_other_people_s_feelings;
	}

	public Date getEvaluation_Date_() {
		return this.evaluation_Date_;
	}

	public void setEvaluation_Date_(Date evaluation_Date_) {
		this.evaluation_Date_ = evaluation_Date_;
	}

	public String getFeels_he_or_she_is_bad() {
		return this.feels_he_or_she_is_bad;
	}

	public void setFeels_he_or_she_is_bad(String feels_he_or_she_is_bad) {
		this.feels_he_or_she_is_bad = feels_he_or_she_is_bad;
	}

	public String getFeels_hopeless() {
		return this.feels_hopeless;
	}

	public void setFeels_hopeless(String feels_hopeless) {
		this.feels_hopeless = feels_hopeless;
	}

	public String getFeels_sad__unhappy() {
		return this.feels_sad__unhappy;
	}

	public void setFeels_sad__unhappy(String feels_sad__unhappy) {
		this.feels_sad__unhappy = feels_sad__unhappy;
	}

	public String getFidgety__unable_to_sit_still() {
		return this.fidgety__unable_to_sit_still;
	}

	public void setFidgety__unable_to_sit_still(String fidgety__unable_to_sit_still) {
		this.fidgety__unable_to_sit_still = fidgety__unable_to_sit_still;
	}

	public String getFights_with_other_children() {
		return this.fights_with_other_children;
	}

	public void setFights_with_other_children(String fights_with_other_children) {
		this.fights_with_other_children = fights_with_other_children;
	}

	public String getGets_hurt_frequently() {
		return this.gets_hurt_frequently;
	}

	public void setGets_hurt_frequently(String gets_hurt_frequently) {
		this.gets_hurt_frequently = gets_hurt_frequently;
	}

	public byte getHas_emotional_or_behavioral_problems_for_which_needs_help() {
		return this.has_emotional_or_behavioral_problems_for_which_needs_help;
	}

	public void setHas_emotional_or_behavioral_problems_for_which_needs_help(byte has_emotional_or_behavioral_problems_for_which_needs_help) {
		this.has_emotional_or_behavioral_problems_for_which_needs_help = has_emotional_or_behavioral_problems_for_which_needs_help;
	}

	public String getHas_trouble_concentrating() {
		return this.has_trouble_concentrating;
	}

	public void setHas_trouble_concentrating(String has_trouble_concentrating) {
		this.has_trouble_concentrating = has_trouble_concentrating;
	}

	public String getHas_trouble_sleeping() {
		return this.has_trouble_sleeping;
	}

	public void setHas_trouble_sleeping(String has_trouble_sleeping) {
		this.has_trouble_sleeping = has_trouble_sleeping;
	}

	public String getHas_trouble_with_teacher() {
		return this.has_trouble_with_teacher;
	}

	public void setHas_trouble_with_teacher(String has_trouble_with_teacher) {
		this.has_trouble_with_teacher = has_trouble_with_teacher;
	}

	public String getIs_afraid_of_new_situations() {
		return this.is_afraid_of_new_situations;
	}

	public void setIs_afraid_of_new_situations(String is_afraid_of_new_situations) {
		this.is_afraid_of_new_situations = is_afraid_of_new_situations;
	}

	public String getIs_down_on_him_or_herself() {
		return this.is_down_on_him_or_herself;
	}

	public void setIs_down_on_him_or_herself(String is_down_on_him_or_herself) {
		this.is_down_on_him_or_herself = is_down_on_him_or_herself;
	}

	public String getIs_irritable__angry() {
		return this.is_irritable__angry;
	}

	public void setIs_irritable__angry(String is_irritable__angry) {
		this.is_irritable__angry = is_irritable__angry;
	}

	public String getLess_interested_in_friends() {
		return this.less_interested_in_friends;
	}

	public void setLess_interested_in_friends(String less_interested_in_friends) {
		this.less_interested_in_friends = less_interested_in_friends;
	}

	public String getLess_interested_in_school() {
		return this.less_interested_in_school;
	}

	public void setLess_interested_in_school(String less_interested_in_school) {
		this.less_interested_in_school = less_interested_in_school;
	}

	public byte getNeed_to__receive_services_for_these_problems() {
		return this.need_to__receive_services_for_these_problems;
	}

	public void setNeed_to__receive_services_for_these_problems(byte need_to__receive_services_for_these_problems) {
		this.need_to__receive_services_for_these_problems = need_to__receive_services_for_these_problems;
	}

	public int getPSC_Score() {
		return this.PSC_Score;
	}

	public void setPSC_Score(int PSC_Score) {
		this.PSC_Score = PSC_Score;
	}

	public String getRefuses_to_share() {
		return this.refuses_to_share;
	}

	public void setRefuses_to_share(String refuses_to_share) {
		this.refuses_to_share = refuses_to_share;
	}

	public String getSchool_grades_dropping() {
		return this.school_grades_dropping;
	}

	public void setSchool_grades_dropping(String school_grades_dropping) {
		this.school_grades_dropping = school_grades_dropping;
	}

	public String getSeems_to_be_having_less_fun() {
		return this.seems_to_be_having_less_fun;
	}

	public void setSeems_to_be_having_less_fun(String seems_to_be_having_less_fun) {
		this.seems_to_be_having_less_fun = seems_to_be_having_less_fun;
	}

	public String getTakes_things_that_do_not_belong_to_him_or_her() {
		return this.takes_things_that_do_not_belong_to_him_or_her;
	}

	public void setTakes_things_that_do_not_belong_to_him_or_her(String takes_things_that_do_not_belong_to_him_or_her) {
		this.takes_things_that_do_not_belong_to_him_or_her = takes_things_that_do_not_belong_to_him_or_her;
	}

	public String getTakes_unnecessary_risks() {
		return this.takes_unnecessary_risks;
	}

	public void setTakes_unnecessary_risks(String takes_unnecessary_risks) {
		this.takes_unnecessary_risks = takes_unnecessary_risks;
	}

	public String getTeases_others() {
		return this.teases_others;
	}

	public void setTeases_others(String teases_others) {
		this.teases_others = teases_others;
	}

	public String getTires_easily__has_little_energy() {
		return this.tires_easily__has_little_energy;
	}

	public void setTires_easily__has_little_energy(String tires_easily__has_little_energy) {
		this.tires_easily__has_little_energy = tires_easily__has_little_energy;
	}

	public String getVisits_the_doctor_with_doctor_finding_nothing_wrong() {
		return this.visits_the_doctor_with_doctor_finding_nothing_wrong;
	}

	public void setVisits_the_doctor_with_doctor_finding_nothing_wrong(String visits_the_doctor_with_doctor_finding_nothing_wrong) {
		this.visits_the_doctor_with_doctor_finding_nothing_wrong = visits_the_doctor_with_doctor_finding_nothing_wrong;
	}

	public String getWants_to_be_with_you_more_than_before() {
		return this.wants_to_be_with_you_more_than_before;
	}

	public void setWants_to_be_with_you_more_than_before(String wants_to_be_with_you_more_than_before) {
		this.wants_to_be_with_you_more_than_before = wants_to_be_with_you_more_than_before;
	}

	public String getWhat_services() {
		return this.what_services;
	}

	public void setWhat_services(String what_services) {
		this.what_services = what_services;
	}

	public String getWorries_a_lot() {
		return this.worries_a_lot;
	}

	public void setWorries_a_lot(String worries_a_lot) {
		this.worries_a_lot = worries_a_lot;
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

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}