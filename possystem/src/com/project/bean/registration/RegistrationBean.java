package com.project.bean.registration;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.model.datamodel.BranchModel;

@ManagedBean(name = "registrationBean")
@ViewScoped
public class RegistrationBean {
	
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private IBranchBO branchBO=objectMapController.getBranchBO();
	private IStaffBO branchStaffBO=objectMapController.getStaffBO();

	
	String firstName;
	String lastName;
	String contactNo;
	String emailAddress;
	String shopName;
	String shopAddress;
	String shopRegistrationNumber;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopRegistrationNumber() {
		return shopRegistrationNumber;
	}

	public void setShopRegistrationNumber(String shopRegistrationNumber) {
		this.shopRegistrationNumber = shopRegistrationNumber;
	}
	
	public void save() {
		ResourceBundle rb = ResourceBundle.getBundle("../META-INF/app-config");

		try {
		boolean saveSuccess=false;
			if(validateShop()) {
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchName(shopName);
				branchObj.setBranchCode(shopRegistrationNumber);
				branchObj.setAddress(shopAddress);
				branchObj.setCity("");
				branchObj.setState("");
				branchObj.setCountry("");
				branchObj.setBranchtype("1");
				branchObj.setEmailAddress(emailAddress);
				branchObj.setContactPerson(firstName+" "+lastName);
				branchObj.setPhoneNo(contactNo);
				
				saveSuccess=branchBO.createNewBranch(branchObj);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success!","Registration success, please check your email for login details."));

				
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!","Sorry, Shop already exist"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal!","Registration failed, please email us at "+rb.getString("common.contact.email.support")));	

		}
		
	}
	
	public boolean validateShop() {
		boolean valid=true;
		try {
			
			
			if(branchBO.findBranchCodeExites(shopRegistrationNumber)) {
				valid=false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

}
