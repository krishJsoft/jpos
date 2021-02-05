package com.project.bean.membership;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import com.project.bo.interfaces.IMembershipBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.model.datamodel.MembershipConfigurationModel;
import com.project.model.datamodel.MembershipModel;
import com.project.model.datamodel.MembershipPaymentTransactionModel;

@ManagedBean(name="membershipPaymentBean")
@SessionScoped
public class MembershipPaymentBean {

	MembershipModel membership=new MembershipModel();
	MembershipPaymentTransactionModel membershipPayment=new MembershipPaymentTransactionModel();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	
	List<MembershipPaymentTransactionModel> membershipPaymentList=new ArrayList<MembershipPaymentTransactionModel>();
	

	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IMembershipBO membershipBO=objectMapController.getMembershipBO();
	
	private Date todayDate;
	private String memberName;
	private String memberIdentificationNumber;
	private String memberContactNo;
	private Date memberCreatedDate;
	private Date memberExpiryDate;
	private String paymentDurationAdded;
	private BigDecimal paymentAmount;
	
	private int memberId;
	
	private int duration=1;
	
	private String durationType="month";
	
	private Date newExpiryDate;
	
	private BigDecimal amount;

	private String action="submit";

	public MembershipModel getMembership() {
		return membership;
	}

	public void setMembership(MembershipModel membership) {
		this.membership = membership;
	}
	
	
	public MembershipPaymentTransactionModel getMembershipPayment() {
		return membershipPayment;
	}

	public void setMembershipPayment(MembershipPaymentTransactionModel membershipPayment) {
		this.membershipPayment = membershipPayment;
	}

	public List<MembershipPaymentTransactionModel> getMembershipPaymentList() {
		return membershipPaymentList;
	}

	public void setMembershipPaymentList(List<MembershipPaymentTransactionModel> membershipPaymentList) {
		this.membershipPaymentList = membershipPaymentList;
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

	public String getPaymentDurationAdded() {
		return paymentDurationAdded;
	}

	public void setPaymentDurationAdded(String paymentDurationAdded) {
		this.paymentDurationAdded = paymentDurationAdded;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public Date getNewExpiryDate() {
		return newExpiryDate;
	}

	public void setNewExpiryDate(Date newExpiryDate) {
		this.newExpiryDate = newExpiryDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void loadPaymentTransactionAll() {
		try {
			
			List<MembershipPaymentTransactionModel> objList=membershipBO.fetchAllPaymentTransaction(loginBean.getBranch().getBranchId());
			
			this.setMembershipPaymentList(objList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void onSelectMember() {
		try {
		if(this.memberId==0)	
		{
			reset();
		}else {
			membership=membershipBO.fetchSelectedMember(this.memberId);
			calculateNewExpiredDate();
			calculateAmount();
		}
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void onChangeDuration(AjaxBehaviorEvent event) {
		try {
			calculateNewExpiredDate();
			calculateAmount();
		}catch(Exception ex) {
			
		}
	}
	
	public void onSelectDurationType() {
		try {
			calculateNewExpiredDate();
			calculateAmount();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void calculateNewExpiredDate() {
		try{
			
			Calendar cal = Calendar.getInstance();
		
			cal.setTime(membership.getExpirationDate());
		
			if(durationType.equals("month")) {
			
				cal.add(Calendar.MONTH, duration);
				
				newExpiryDate=cal.getTime();
			
		}else if(durationType.equals("year")){
			
			cal.add(Calendar.YEAR, duration);
			newExpiryDate=cal.getTime();
			
		}
		}catch(Exception ex) {
				
		}
	}
	
	private void calculateAmount() {
		try {
			BigDecimal rate=new BigDecimal(0);
			MembershipConfigurationModel config=membershipBO.fetchConfiguration(loginBean.getBranch().getBranchId());
			rate=config.getAmount();
			if(durationType.equals("month")) {	
				
				amount=rate.multiply(new BigDecimal(duration));
			}else if(durationType.equals("year")){
				//int dur=duration*12;
				amount=rate.multiply(new BigDecimal(duration*12));
				
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makePayment() {
		//calculateAmount();
		try {
			
			membershipPayment.setMemberId(membership.getId());
			membershipPayment.setAmount(this.getAmount());
			membershipPayment.setCreatedDate(new Date());
			membershipPayment.setDurationAdded(this.duration+ " "+this.durationType);
			membershipPayment.setCreatedBy(loginBean.getUserId());
			membershipPayment.setBranchId(loginBean.getBranch().getBranchId());
			membershipPayment.setStatus(1);
			boolean savesuccess=membershipBO.createPaymentTransaction(membershipPayment);
			if(savesuccess)
				membership.setExpirationDate(this.getNewExpiryDate());
				membership.setBranchId(membership.getBranch().getBranchId());
				membership.setCreatedBy(membership.getBranchStaff().getStaffId());
				membershipBO.updateMember(membership);
				this.setTodayDate(new Date());
				this.setMemberName(membership.getName());
				this.setMemberIdentificationNumber(membership.getIdentificationNumber());
				this.setMemberCreatedDate(membership.getCreatedDate());
				this.setMemberExpiryDate(membership.getExpirationDate());
				this.setPaymentDurationAdded(membershipPayment.getDurationAdded());
				this.setPaymentAmount(membershipPayment.getAmount());
				RequestContext.getCurrentInstance().execute("PF('memberPaymentprintDig').show()");
				this.reset();
				this.loadPaymentTransactionAll();
			}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void updatePayment() {
		try {
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteTransaction(ActionEvent event) {
		try{
			Boolean deleteSuccess=false;
			String paymentId="",expiryDate="",addedDuration="",memberId="";
			paymentId=(String)event.getComponent().getAttributes().get("paymentTransactionId").toString();
			expiryDate=(String)event.getComponent().getAttributes().get("expiryDate").toString();
			addedDuration=(String)event.getComponent().getAttributes().get("addedDuration").toString();
			memberId=(String)event.getComponent().getAttributes().get("memberId").toString();
			
			//deleteSuccess=membershipBO.deleteSelectedPaymentTransaction(Integer.parseInt(paymentId));
			MembershipPaymentTransactionModel obj=membershipBO.fetchSelectedPaymentTransaction(Integer.parseInt(paymentId));
			obj.setStatus(0);
			deleteSuccess=membershipBO.updateSelectedPaymentTransaction(obj);
			if(deleteSuccess) {
				String durationConv[]=addedDuration.split(" ");
				Calendar cal = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				Date oldDate = df.parse(expiryDate);
				Date newDate=oldDate;
				cal.setTime(oldDate);
			
				if(durationConv[1].equals("month")) {
					cal.add(Calendar.MONTH, Integer.parseInt("-"+durationConv[0]));
					newDate=cal.getTime();
					
				}else if(durationConv[1].equals("year")){
					cal.add(Calendar.YEAR, Integer.parseInt("-"+durationConv[0]));
					newDate=cal.getTime();
					
				}
					MembershipModel membershipObj=membershipBO.fetchSelectedMember(Integer.parseInt(memberId));
					membershipObj.setExpirationDate(newDate);
					boolean adjustSuccess=membershipBO.updateMember(membershipObj);
					this.loadPaymentTransactionAll();
					this.reset();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void editMember(ActionEvent event) {
		try {
			String paymentId="";
			paymentId=(String)event.getComponent().getAttributes().get("paymentTransactionId").toString();
			MembershipPaymentTransactionModel obj=membershipBO.fetchSelectedPaymentTransaction(Integer.parseInt(paymentId));
			
			this.membership.setIdentificationNumber(obj.getMember().getIdentificationNumber());
			this.setMembershipPayment(obj);
			this.setMemberId(obj.getMember().getId());
			this.setAction("update");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean paymentValidation() {
		String messageValue=null;
		boolean valid=true;
		try {
			if(factoryBean.checkIsNullAssignMessage(this.duration, "membershipPayment.label.duration", "duration")) {
				valid=false;
				
			}
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return valid;
	}
	
	public void reset() {
		
		this.setDuration(1);
		this.membership.setExpirationDate(null);
		this.membership.setIdentificationNumber(null);
		this.setNewExpiryDate(null);
		this.setAmount(null);
	}
}
