package com.project.bean.membership;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TabChangeEvent;

import com.project.bo.interfaces.IMembershipBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.MembershipConfigurationModel;

@ManagedBean(name = "membershipConfigurationBean")
@SessionScoped
public class MembershipConfigurationBean {
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IMembershipBO membershipBO=objectMapController.getMembershipBO();

	private MembershipConfigurationModel config=new MembershipConfigurationModel();
	private BigDecimal fee;
	
	public MembershipConfigurationModel getConfig() {
		return config;
	}

	public void setConfig(MembershipConfigurationModel config) {
		this.config = config;
	}
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public void initConfiguration() {
		try {
			config=membershipBO.fetchConfiguration(loginBean.getBranch().getBranchId());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void updateConfiguration() {
		try{
			
			boolean updateSuccess=membershipBO.updateConfiguration(config);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
