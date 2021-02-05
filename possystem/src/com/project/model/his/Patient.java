package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the patients database table.
 * 
 */
@Entity
@Table(name="patients")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int patient_Id;

	private int abortions;

	@Column(length=100)
	private String address1;

	@Column(length=100)
	private String address2;

	private byte age;

	private int age_Start_Smoking;

	@Column(length=16)
	private String average_Income;

	private int average_Number_Meals_Day;

	private int average_Soft_Drinks_day;

	private int beers_per_Week;

	@Column(length=10)
	private String bloodRh;

	@Column(length=25)
	private String bloodType;

	private byte breast_Self_Examination;

	private byte car_Revisions_Often;

	@Column(length=20)
	private String cellPhoneNumber;

	private int cigarretes_per_day;

	@Column(nullable=false, length=50)
	private String ciudad;

	private int coffee_Cups_day;

	@Column(length=40)
	private String colonia;

	private byte colposcopy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date colposcopy_Last;

	@Column(length=40)
	private String country;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Lob
	private String critical_Info;

	private int current_Insurance;

	private byte currently_Pregnant;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deceased_date;

	@Column(length=40)
	private String department;

	@Column(length=256)
	private String diet_Info;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	private byte does_Exercises;

	private byte drinks_Alcohol;

	private byte drug_Addiction;

	private byte drug_Usage;

	@Column(length=50)
	private String DUI_Number;

	private byte eats_Alone;

	private byte eats_lots_Salt;

	@Column(length=200)
	private String education;

	private int ethnic_Group;

	private byte ex_Smoker;

	private int excercise_Minutes_Day;

	private int family;

	private byte fertile;

	@Column(length=50)
	private String financial_Status;

	@Column(length=50)
	private String first_Name;

	private int first_Sexual_Encounter;

	private int full_Term;

	@Lob
	private String general_Info;

	private byte has_Electricity;

	private byte has_Sex_With_Prostitutes;

	private byte has_Telephone;

	private byte has_Television;

	private byte has_Trash_around;

	private byte has_Water;

	private byte home_Safety;

	@Column(length=20)
	private String homePhoneNumber;

	private int hours_Spend_Outside;

	@Column(length=16)
	private String housing;

	private byte is_a_Prostitute;

	private byte is_Deceased;

	private byte is_Single_Parent;

	private byte is_Student;

	@Column(length=50)
	private String ISSS_Number;

	@Column(length=50)
	private String last_Name;

	@Lob
	private String lifestyle_Info;

	private byte likes_to_Diet;

	private int liquour_Drinks_Week;

	private byte lives_in_Hostile_Area;

	private byte mammography;

	@Temporal(TemporalType.TIMESTAMP)
	private Date mammography_Last;

	@Column(length=16)
	private String marital_Status;

	@Column(length=50)
	private String maritalStatus;

	private int menarche;

	private byte menopausal;

	private int menopause;

	private byte motorcycle_Rider;

	@Column(length=40)
	private String municipio;

	@Column(length=50)
	private String NIT_Number;

	@Column(length=350)
	private String notes;

	private int number_Of_Meals;

	@Column(length=200)
	private String occupation;

	private byte pap_Test;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pap_Test_Last;

	@Column(length=50)
	private String patient_Title_Profession;

	@Column(length=40)
	private String personalEmail;

	@Lob
	private byte[] photo;

	private int premature;

	private int primary_Care_Doctor;

	@Column(length=50)
	private String primary_Doctor;

	private byte prison_Current;

	private byte prison_in_Past;

	@Column(length=50)
	private String referring_Doctor;

	@Column(length=50)
	private String related_To_Patient;

	private byte relative_In_Prison;

	private byte schedule_Next_Apointment;

	private byte school_Withdrawal;

	private byte second_Hand_Smoker;

	@Column(length=16)
	private String sex_Anal;

	@Column(length=50)
	private String sex_Gender;

	private byte sexual_Abuse;

	@Column(length=16)
	private String sexual_Partners;

	private int sexual_Partners_Number;

	@Column(length=16)
	private String sexual_Practices;

	@Column(length=16)
	private String sexual_Preferences;

	@Lob
	private String sexuality_Info;

	private byte sleep_During_Daytime;

	private int sleep_Hours;

	private byte smokes_Heavy;

	private byte smokes_light;

	private int smoking_Number;

	private byte suffers_Domestic_Violence;

	private byte teenage_Pregnancy;

	@Lob
	private String urgent_Allergy_Info;

	@Column(length=16)
	private String uses_Anticonceptive;

	private byte uses_Anticonceptives;

	private byte uses_Car_Seat_Belt;

	private byte uses_Helmet;

	private byte uses_the_Internet;

	private int wine_Glasses_per_Week;

	@Column(length=40)
	private String workEmail;

	private byte working_Children;

	@Column(length=20)
	private String workPhoneNumber;

	private byte works_Around_Gas;

	private byte works_At_Home;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	public Patient() {
	}

	public int getPatient_Id() {
		return this.patient_Id;
	}

	public void setPatient_Id(int patient_Id) {
		this.patient_Id = patient_Id;
	}

	public int getAbortions() {
		return this.abortions;
	}

	public void setAbortions(int abortions) {
		this.abortions = abortions;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public byte getAge() {
		return this.age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public int getAge_Start_Smoking() {
		return this.age_Start_Smoking;
	}

	public void setAge_Start_Smoking(int age_Start_Smoking) {
		this.age_Start_Smoking = age_Start_Smoking;
	}

	public String getAverage_Income() {
		return this.average_Income;
	}

	public void setAverage_Income(String average_Income) {
		this.average_Income = average_Income;
	}

	public int getAverage_Number_Meals_Day() {
		return this.average_Number_Meals_Day;
	}

	public void setAverage_Number_Meals_Day(int average_Number_Meals_Day) {
		this.average_Number_Meals_Day = average_Number_Meals_Day;
	}

	public int getAverage_Soft_Drinks_day() {
		return this.average_Soft_Drinks_day;
	}

	public void setAverage_Soft_Drinks_day(int average_Soft_Drinks_day) {
		this.average_Soft_Drinks_day = average_Soft_Drinks_day;
	}

	public int getBeers_per_Week() {
		return this.beers_per_Week;
	}

	public void setBeers_per_Week(int beers_per_Week) {
		this.beers_per_Week = beers_per_Week;
	}

	public String getBloodRh() {
		return this.bloodRh;
	}

	public void setBloodRh(String bloodRh) {
		this.bloodRh = bloodRh;
	}

	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public byte getBreast_Self_Examination() {
		return this.breast_Self_Examination;
	}

	public void setBreast_Self_Examination(byte breast_Self_Examination) {
		this.breast_Self_Examination = breast_Self_Examination;
	}

	public byte getCar_Revisions_Often() {
		return this.car_Revisions_Often;
	}

	public void setCar_Revisions_Often(byte car_Revisions_Often) {
		this.car_Revisions_Often = car_Revisions_Often;
	}

	public String getCellPhoneNumber() {
		return this.cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public int getCigarretes_per_day() {
		return this.cigarretes_per_day;
	}

	public void setCigarretes_per_day(int cigarretes_per_day) {
		this.cigarretes_per_day = cigarretes_per_day;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getCoffee_Cups_day() {
		return this.coffee_Cups_day;
	}

	public void setCoffee_Cups_day(int coffee_Cups_day) {
		this.coffee_Cups_day = coffee_Cups_day;
	}

	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public byte getColposcopy() {
		return this.colposcopy;
	}

	public void setColposcopy(byte colposcopy) {
		this.colposcopy = colposcopy;
	}

	public Date getColposcopy_Last() {
		return this.colposcopy_Last;
	}

	public void setColposcopy_Last(Date colposcopy_Last) {
		this.colposcopy_Last = colposcopy_Last;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getCritical_Info() {
		return this.critical_Info;
	}

	public void setCritical_Info(String critical_Info) {
		this.critical_Info = critical_Info;
	}

	public int getCurrent_Insurance() {
		return this.current_Insurance;
	}

	public void setCurrent_Insurance(int current_Insurance) {
		this.current_Insurance = current_Insurance;
	}

	public byte getCurrently_Pregnant() {
		return this.currently_Pregnant;
	}

	public void setCurrently_Pregnant(byte currently_Pregnant) {
		this.currently_Pregnant = currently_Pregnant;
	}

	public Date getDeceased_date() {
		return this.deceased_date;
	}

	public void setDeceased_date(Date deceased_date) {
		this.deceased_date = deceased_date;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDiet_Info() {
		return this.diet_Info;
	}

	public void setDiet_Info(String diet_Info) {
		this.diet_Info = diet_Info;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public byte getDoes_Exercises() {
		return this.does_Exercises;
	}

	public void setDoes_Exercises(byte does_Exercises) {
		this.does_Exercises = does_Exercises;
	}

	public byte getDrinks_Alcohol() {
		return this.drinks_Alcohol;
	}

	public void setDrinks_Alcohol(byte drinks_Alcohol) {
		this.drinks_Alcohol = drinks_Alcohol;
	}

	public byte getDrug_Addiction() {
		return this.drug_Addiction;
	}

	public void setDrug_Addiction(byte drug_Addiction) {
		this.drug_Addiction = drug_Addiction;
	}

	public byte getDrug_Usage() {
		return this.drug_Usage;
	}

	public void setDrug_Usage(byte drug_Usage) {
		this.drug_Usage = drug_Usage;
	}

	public String getDUI_Number() {
		return this.DUI_Number;
	}

	public void setDUI_Number(String DUI_Number) {
		this.DUI_Number = DUI_Number;
	}

	public byte getEats_Alone() {
		return this.eats_Alone;
	}

	public void setEats_Alone(byte eats_Alone) {
		this.eats_Alone = eats_Alone;
	}

	public byte getEats_lots_Salt() {
		return this.eats_lots_Salt;
	}

	public void setEats_lots_Salt(byte eats_lots_Salt) {
		this.eats_lots_Salt = eats_lots_Salt;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getEthnic_Group() {
		return this.ethnic_Group;
	}

	public void setEthnic_Group(int ethnic_Group) {
		this.ethnic_Group = ethnic_Group;
	}

	public byte getEx_Smoker() {
		return this.ex_Smoker;
	}

	public void setEx_Smoker(byte ex_Smoker) {
		this.ex_Smoker = ex_Smoker;
	}

	public int getExcercise_Minutes_Day() {
		return this.excercise_Minutes_Day;
	}

	public void setExcercise_Minutes_Day(int excercise_Minutes_Day) {
		this.excercise_Minutes_Day = excercise_Minutes_Day;
	}

	public int getFamily() {
		return this.family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public byte getFertile() {
		return this.fertile;
	}

	public void setFertile(byte fertile) {
		this.fertile = fertile;
	}

	public String getFinancial_Status() {
		return this.financial_Status;
	}

	public void setFinancial_Status(String financial_Status) {
		this.financial_Status = financial_Status;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public int getFirst_Sexual_Encounter() {
		return this.first_Sexual_Encounter;
	}

	public void setFirst_Sexual_Encounter(int first_Sexual_Encounter) {
		this.first_Sexual_Encounter = first_Sexual_Encounter;
	}

	public int getFull_Term() {
		return this.full_Term;
	}

	public void setFull_Term(int full_Term) {
		this.full_Term = full_Term;
	}

	public String getGeneral_Info() {
		return this.general_Info;
	}

	public void setGeneral_Info(String general_Info) {
		this.general_Info = general_Info;
	}

	public byte getHas_Electricity() {
		return this.has_Electricity;
	}

	public void setHas_Electricity(byte has_Electricity) {
		this.has_Electricity = has_Electricity;
	}

	public byte getHas_Sex_With_Prostitutes() {
		return this.has_Sex_With_Prostitutes;
	}

	public void setHas_Sex_With_Prostitutes(byte has_Sex_With_Prostitutes) {
		this.has_Sex_With_Prostitutes = has_Sex_With_Prostitutes;
	}

	public byte getHas_Telephone() {
		return this.has_Telephone;
	}

	public void setHas_Telephone(byte has_Telephone) {
		this.has_Telephone = has_Telephone;
	}

	public byte getHas_Television() {
		return this.has_Television;
	}

	public void setHas_Television(byte has_Television) {
		this.has_Television = has_Television;
	}

	public byte getHas_Trash_around() {
		return this.has_Trash_around;
	}

	public void setHas_Trash_around(byte has_Trash_around) {
		this.has_Trash_around = has_Trash_around;
	}

	public byte getHas_Water() {
		return this.has_Water;
	}

	public void setHas_Water(byte has_Water) {
		this.has_Water = has_Water;
	}

	public byte getHome_Safety() {
		return this.home_Safety;
	}

	public void setHome_Safety(byte home_Safety) {
		this.home_Safety = home_Safety;
	}

	public String getHomePhoneNumber() {
		return this.homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public int getHours_Spend_Outside() {
		return this.hours_Spend_Outside;
	}

	public void setHours_Spend_Outside(int hours_Spend_Outside) {
		this.hours_Spend_Outside = hours_Spend_Outside;
	}

	public String getHousing() {
		return this.housing;
	}

	public void setHousing(String housing) {
		this.housing = housing;
	}

	public byte getIs_a_Prostitute() {
		return this.is_a_Prostitute;
	}

	public void setIs_a_Prostitute(byte is_a_Prostitute) {
		this.is_a_Prostitute = is_a_Prostitute;
	}

	public byte getIs_Deceased() {
		return this.is_Deceased;
	}

	public void setIs_Deceased(byte is_Deceased) {
		this.is_Deceased = is_Deceased;
	}

	public byte getIs_Single_Parent() {
		return this.is_Single_Parent;
	}

	public void setIs_Single_Parent(byte is_Single_Parent) {
		this.is_Single_Parent = is_Single_Parent;
	}

	public byte getIs_Student() {
		return this.is_Student;
	}

	public void setIs_Student(byte is_Student) {
		this.is_Student = is_Student;
	}

	public String getISSS_Number() {
		return this.ISSS_Number;
	}

	public void setISSS_Number(String ISSS_Number) {
		this.ISSS_Number = ISSS_Number;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getLifestyle_Info() {
		return this.lifestyle_Info;
	}

	public void setLifestyle_Info(String lifestyle_Info) {
		this.lifestyle_Info = lifestyle_Info;
	}

	public byte getLikes_to_Diet() {
		return this.likes_to_Diet;
	}

	public void setLikes_to_Diet(byte likes_to_Diet) {
		this.likes_to_Diet = likes_to_Diet;
	}

	public int getLiquour_Drinks_Week() {
		return this.liquour_Drinks_Week;
	}

	public void setLiquour_Drinks_Week(int liquour_Drinks_Week) {
		this.liquour_Drinks_Week = liquour_Drinks_Week;
	}

	public byte getLives_in_Hostile_Area() {
		return this.lives_in_Hostile_Area;
	}

	public void setLives_in_Hostile_Area(byte lives_in_Hostile_Area) {
		this.lives_in_Hostile_Area = lives_in_Hostile_Area;
	}

	public byte getMammography() {
		return this.mammography;
	}

	public void setMammography(byte mammography) {
		this.mammography = mammography;
	}

	public Date getMammography_Last() {
		return this.mammography_Last;
	}

	public void setMammography_Last(Date mammography_Last) {
		this.mammography_Last = mammography_Last;
	}

	public String getMarital_Status() {
		return this.marital_Status;
	}

	public void setMarital_Status(String marital_Status) {
		this.marital_Status = marital_Status;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getMenarche() {
		return this.menarche;
	}

	public void setMenarche(int menarche) {
		this.menarche = menarche;
	}

	public byte getMenopausal() {
		return this.menopausal;
	}

	public void setMenopausal(byte menopausal) {
		this.menopausal = menopausal;
	}

	public int getMenopause() {
		return this.menopause;
	}

	public void setMenopause(int menopause) {
		this.menopause = menopause;
	}

	public byte getMotorcycle_Rider() {
		return this.motorcycle_Rider;
	}

	public void setMotorcycle_Rider(byte motorcycle_Rider) {
		this.motorcycle_Rider = motorcycle_Rider;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNIT_Number() {
		return this.NIT_Number;
	}

	public void setNIT_Number(String NIT_Number) {
		this.NIT_Number = NIT_Number;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getNumber_Of_Meals() {
		return this.number_Of_Meals;
	}

	public void setNumber_Of_Meals(int number_Of_Meals) {
		this.number_Of_Meals = number_Of_Meals;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public byte getPap_Test() {
		return this.pap_Test;
	}

	public void setPap_Test(byte pap_Test) {
		this.pap_Test = pap_Test;
	}

	public Date getPap_Test_Last() {
		return this.pap_Test_Last;
	}

	public void setPap_Test_Last(Date pap_Test_Last) {
		this.pap_Test_Last = pap_Test_Last;
	}

	public String getPatient_Title_Profession() {
		return this.patient_Title_Profession;
	}

	public void setPatient_Title_Profession(String patient_Title_Profession) {
		this.patient_Title_Profession = patient_Title_Profession;
	}

	public String getPersonalEmail() {
		return this.personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getPremature() {
		return this.premature;
	}

	public void setPremature(int premature) {
		this.premature = premature;
	}

	public int getPrimary_Care_Doctor() {
		return this.primary_Care_Doctor;
	}

	public void setPrimary_Care_Doctor(int primary_Care_Doctor) {
		this.primary_Care_Doctor = primary_Care_Doctor;
	}

	public String getPrimary_Doctor() {
		return this.primary_Doctor;
	}

	public void setPrimary_Doctor(String primary_Doctor) {
		this.primary_Doctor = primary_Doctor;
	}

	public byte getPrison_Current() {
		return this.prison_Current;
	}

	public void setPrison_Current(byte prison_Current) {
		this.prison_Current = prison_Current;
	}

	public byte getPrison_in_Past() {
		return this.prison_in_Past;
	}

	public void setPrison_in_Past(byte prison_in_Past) {
		this.prison_in_Past = prison_in_Past;
	}

	public String getReferring_Doctor() {
		return this.referring_Doctor;
	}

	public void setReferring_Doctor(String referring_Doctor) {
		this.referring_Doctor = referring_Doctor;
	}

	public String getRelated_To_Patient() {
		return this.related_To_Patient;
	}

	public void setRelated_To_Patient(String related_To_Patient) {
		this.related_To_Patient = related_To_Patient;
	}

	public byte getRelative_In_Prison() {
		return this.relative_In_Prison;
	}

	public void setRelative_In_Prison(byte relative_In_Prison) {
		this.relative_In_Prison = relative_In_Prison;
	}

	public byte getSchedule_Next_Apointment() {
		return this.schedule_Next_Apointment;
	}

	public void setSchedule_Next_Apointment(byte schedule_Next_Apointment) {
		this.schedule_Next_Apointment = schedule_Next_Apointment;
	}

	public byte getSchool_Withdrawal() {
		return this.school_Withdrawal;
	}

	public void setSchool_Withdrawal(byte school_Withdrawal) {
		this.school_Withdrawal = school_Withdrawal;
	}

	public byte getSecond_Hand_Smoker() {
		return this.second_Hand_Smoker;
	}

	public void setSecond_Hand_Smoker(byte second_Hand_Smoker) {
		this.second_Hand_Smoker = second_Hand_Smoker;
	}

	public String getSex_Anal() {
		return this.sex_Anal;
	}

	public void setSex_Anal(String sex_Anal) {
		this.sex_Anal = sex_Anal;
	}

	public String getSex_Gender() {
		return this.sex_Gender;
	}

	public void setSex_Gender(String sex_Gender) {
		this.sex_Gender = sex_Gender;
	}

	public byte getSexual_Abuse() {
		return this.sexual_Abuse;
	}

	public void setSexual_Abuse(byte sexual_Abuse) {
		this.sexual_Abuse = sexual_Abuse;
	}

	public String getSexual_Partners() {
		return this.sexual_Partners;
	}

	public void setSexual_Partners(String sexual_Partners) {
		this.sexual_Partners = sexual_Partners;
	}

	public int getSexual_Partners_Number() {
		return this.sexual_Partners_Number;
	}

	public void setSexual_Partners_Number(int sexual_Partners_Number) {
		this.sexual_Partners_Number = sexual_Partners_Number;
	}

	public String getSexual_Practices() {
		return this.sexual_Practices;
	}

	public void setSexual_Practices(String sexual_Practices) {
		this.sexual_Practices = sexual_Practices;
	}

	public String getSexual_Preferences() {
		return this.sexual_Preferences;
	}

	public void setSexual_Preferences(String sexual_Preferences) {
		this.sexual_Preferences = sexual_Preferences;
	}

	public String getSexuality_Info() {
		return this.sexuality_Info;
	}

	public void setSexuality_Info(String sexuality_Info) {
		this.sexuality_Info = sexuality_Info;
	}

	public byte getSleep_During_Daytime() {
		return this.sleep_During_Daytime;
	}

	public void setSleep_During_Daytime(byte sleep_During_Daytime) {
		this.sleep_During_Daytime = sleep_During_Daytime;
	}

	public int getSleep_Hours() {
		return this.sleep_Hours;
	}

	public void setSleep_Hours(int sleep_Hours) {
		this.sleep_Hours = sleep_Hours;
	}

	public byte getSmokes_Heavy() {
		return this.smokes_Heavy;
	}

	public void setSmokes_Heavy(byte smokes_Heavy) {
		this.smokes_Heavy = smokes_Heavy;
	}

	public byte getSmokes_light() {
		return this.smokes_light;
	}

	public void setSmokes_light(byte smokes_light) {
		this.smokes_light = smokes_light;
	}

	public int getSmoking_Number() {
		return this.smoking_Number;
	}

	public void setSmoking_Number(int smoking_Number) {
		this.smoking_Number = smoking_Number;
	}

	public byte getSuffers_Domestic_Violence() {
		return this.suffers_Domestic_Violence;
	}

	public void setSuffers_Domestic_Violence(byte suffers_Domestic_Violence) {
		this.suffers_Domestic_Violence = suffers_Domestic_Violence;
	}

	public byte getTeenage_Pregnancy() {
		return this.teenage_Pregnancy;
	}

	public void setTeenage_Pregnancy(byte teenage_Pregnancy) {
		this.teenage_Pregnancy = teenage_Pregnancy;
	}

	public String getUrgent_Allergy_Info() {
		return this.urgent_Allergy_Info;
	}

	public void setUrgent_Allergy_Info(String urgent_Allergy_Info) {
		this.urgent_Allergy_Info = urgent_Allergy_Info;
	}

	public String getUses_Anticonceptive() {
		return this.uses_Anticonceptive;
	}

	public void setUses_Anticonceptive(String uses_Anticonceptive) {
		this.uses_Anticonceptive = uses_Anticonceptive;
	}

	public byte getUses_Anticonceptives() {
		return this.uses_Anticonceptives;
	}

	public void setUses_Anticonceptives(byte uses_Anticonceptives) {
		this.uses_Anticonceptives = uses_Anticonceptives;
	}

	public byte getUses_Car_Seat_Belt() {
		return this.uses_Car_Seat_Belt;
	}

	public void setUses_Car_Seat_Belt(byte uses_Car_Seat_Belt) {
		this.uses_Car_Seat_Belt = uses_Car_Seat_Belt;
	}

	public byte getUses_Helmet() {
		return this.uses_Helmet;
	}

	public void setUses_Helmet(byte uses_Helmet) {
		this.uses_Helmet = uses_Helmet;
	}

	public byte getUses_the_Internet() {
		return this.uses_the_Internet;
	}

	public void setUses_the_Internet(byte uses_the_Internet) {
		this.uses_the_Internet = uses_the_Internet;
	}

	public int getWine_Glasses_per_Week() {
		return this.wine_Glasses_per_Week;
	}

	public void setWine_Glasses_per_Week(int wine_Glasses_per_Week) {
		this.wine_Glasses_per_Week = wine_Glasses_per_Week;
	}

	public String getWorkEmail() {
		return this.workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public byte getWorking_Children() {
		return this.working_Children;
	}

	public void setWorking_Children(byte working_Children) {
		this.working_Children = working_Children;
	}

	public String getWorkPhoneNumber() {
		return this.workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public byte getWorks_Around_Gas() {
		return this.works_Around_Gas;
	}

	public void setWorks_Around_Gas(byte works_Around_Gas) {
		this.works_Around_Gas = works_Around_Gas;
	}

	public byte getWorks_At_Home() {
		return this.works_At_Home;
	}

	public void setWorks_At_Home(byte works_At_Home) {
		this.works_At_Home = works_At_Home;
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

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}