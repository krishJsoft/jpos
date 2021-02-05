package com.project.model.datamodel;

import java.util.Date;
import java.util.List;

/**
 * Gopal Ar , gopalabe@gmail.com , Dec 01-2012
 */

public class EmployeeModel {

	private int idemployee;
	private String empnewic;
	private String firstname;
	private String gender;
	private Date joiningdate;
	private String lastname;
	private int loginlocked;
	private Date releavingdate;
	private String status;
	private String title;
	private String userid;

	private int departmentId;
	private String departmentName;
	private int designationId;
	private String designationName;

	private int roleId;
	private String roleName;

	

	private List<UserLoginModel> userLoginModelObj;

	public int getIdemployee() {
		return idemployee;
	}

	public void setIdemployee(int idemployee) {
		this.idemployee = idemployee;
	}

	public String getEmpnewic() {
		return empnewic;
	}

	public void setEmpnewic(String empnewic) {
		this.empnewic = empnewic;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getLoginlocked() {
		return loginlocked;
	}

	public void setLoginlocked(int loginlocked) {
		this.loginlocked = loginlocked;
	}

	public Date getReleavingdate() {
		return releavingdate;
	}

	public void setReleavingdate(Date releavingdate) {
		this.releavingdate = releavingdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

	public List<UserLoginModel> getUserLoginModelObj() {
		return userLoginModelObj;
	}

	public void setUserLoginModelObj(List<UserLoginModel> userLoginModelObj) {
		this.userLoginModelObj = userLoginModelObj;
	}

}
