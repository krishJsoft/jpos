package com.project.bean.membership;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.project.bean.admin.CommonListBeanInfo;
import com.project.bo.interfaces.IMembershipBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.MembershipModel;
import com.project.model.sale.sales.ExpensesTransactionModel;

@ManagedBean(name = "membershipBean")
@SessionScoped
public class MembershipBean {

	MembershipModel membership = new MembershipModel();
	
	List<MembershipModel> membershipList=new ArrayList<MembershipModel>(); 
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IMembershipBO membershipBO=objectMapController.getMembershipBO();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");

	
	private Date todayDate;
	private String memberName;
	private String memberIdentificationNumber;
	private String memberContactNo;
	private Date memberCreatedDate;
	private Date memberExpiryDate;
	
	private String action="submit";
	
	public MembershipModel getMembership() {
		return membership;
	}
	public void setMembership(MembershipModel membership) {
		this.membership = membership;
	}
	
	public List<MembershipModel> getMembershipList() {
		return membershipList;
	}
	public void setMembershipList(List<MembershipModel> membershipList) {
		this.membershipList = membershipList;
	}
	
	public Date getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberIdentificationNumber() {
		return memberIdentificationNumber;
	}
	public void setMemberIdentificationNumber(String memberIdentificationNumber) {
		this.memberIdentificationNumber = memberIdentificationNumber;
	}
	public String getMemberContactNo() {
		return memberContactNo;
	}
	public void setMemberContactNo(String memberContactNo) {
		this.memberContactNo = memberContactNo;
	}
	
	public Date getMemberCreatedDate() {
		return memberCreatedDate;
	}
	public void setMemberCreatedDate(Date memberCreatedDate) {
		this.memberCreatedDate = memberCreatedDate;
	}
	public Date getMemberExpiryDate() {
		return memberExpiryDate;
	}
	public void setMemberExpiryDate(Date memberExpiryDate) {
		this.memberExpiryDate = memberExpiryDate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public void loadMembershipAll() {
		//
		try {
			List<MembershipModel> objList=membershipBO.fetchMembershipList(loginBean.getBranch().getBranchId());
			
			this.setMembershipList(objList);	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void createNewMember() {
		try {
			if(memberValidation()) {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				Date cDate = c.getTime();
				c.add(Calendar.YEAR, 1);
				Date eDate=c.getTime();
				this.todayDate=new Date();
						
				membership.setCreatedDate(cDate);
				//membership.setExpirationDate(eDate);
				membership.setExpirationDate(cDate);
				
				membership.setBranchId(loginBean.getBranch().getBranchId());
				membership.setCreatedBy(loginBean.getUserId());
				membership.setUserName(membership.getIdentificationNumber());
				membership.setStatus(1);
				membership.setPassword("12345");
				
				
				this.setMemberName(membership.getName());
				this.setMemberIdentificationNumber(membership.getIdentificationNumber());
				this.setMemberContactNo(membership.getContactNo());
				this.setMemberCreatedDate(membership.getCreatedDate());
		
				/*
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(loginBean.getBranch().getBranchId());
				membership.setBranch(branchObj);
				
				BranchstaffmemberModel staffObj=new BranchstaffmemberModel();
				staffObj.setStaffId(loginBean.getUserId());
				membership.setBranchStaff(staffObj);
				*/
				boolean success=membershipBO.createNewMember(membership);
				if(success) {
					RequestContext.getCurrentInstance().execute("PF('memberprintDig').show()");
					this.reset();
					CommonListBeanInfo.getMembershipList();
					loadMembershipAll();
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	public void editMember(ActionEvent event) {
		try {
			String memberId="";
			memberId=(String)event.getComponent().getAttributes().get("membershipId").toString();
			MembershipModel obj=membershipBO.fetchSelectedMember(Integer.parseInt(memberId));
			
			this.setMembership(obj);
			this.setAction("update");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateMember() {
		try {
			
			boolean success=membershipBO.updateMember(membership);
			if(success) {
				this.reset();
				loadMembershipAll();
			}
			//this.setAction("submit");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void reset(){
		membership.setName(null);
		membership.setIdentificationNumber(null);
		membership.setGender(null);
		membership.setAddress(null);
		membership.setContactNo(null);
		membership.setEmailAddress(null);
		membership.setUserName(null);
		this.setAction("submit");
	}
	
	private boolean memberValidation() {
		String messageValue=null;
		boolean valid=true;
		try {
			
			
			if(factoryBean.checkIsNullAssignMessage(this.membership.getName(), "membership.label.name", "memberName")) {
				valid=false;
				
			}
			if(factoryBean.checkIsNullAssignMessage(this.membership.getContactNo(), "membership.label.contact", "contactNo")) {
				valid=false;
				
			}
			if(factoryBean.checkIsNullAssignMessage(this.membership.getIdentificationNumber(), "membership.label.identificationNumber", "identificationNumber")) {
				valid=false;
				
			}
			else {
				if(membershipBO.memberIsExist(loginBean.getBranch().getBranchId(),this.membership.getIdentificationNumber())) {
					messageValue=factoryBean.getErrorFactory().getError("membership.label.identificationNumber");
					factoryBean.reportErrorToMessageHandler(messageValue, messageValue, "identificationNumber");
					valid=false;
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return valid;
	}
	
}
