package com.project.bean.membership;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TabChangeEvent;

import com.project.common.factory.BeanContext;

@ManagedBean(name = "membershipTabMenuBean")
@SessionScoped
public class MembershipTabMenuBean {
	MembershipBean membershipBean = (MembershipBean) BeanContext.getReference("membershipBean");
	MembershipPaymentBean membershipPaymentBean = (MembershipPaymentBean) BeanContext.getReference("membershipPaymentBean");
	MembershipConfigurationBean membershipConfigurationBean = (MembershipConfigurationBean) BeanContext.getReference("membershipConfigurationBean");
	
	public void onTabChange(TabChangeEvent event) {
		
		String title=event.getTab().getTitle();
		
		ResourceBundle rb = ResourceBundle.getBundle("../META-INF/MessagesResources");
		
		if(title.equals(rb.getString("membershipPayment.label.tabTitle"))) {
			membershipPaymentBean.reset();
			membershipPaymentBean.loadPaymentTransactionAll();
		}
		else if(title.equals(rb.getString("membership.label.tabTitle"))) {
			membershipBean.reset();
			membershipBean.loadMembershipAll();
			
		}
		else if(title.equals(rb.getString("membershipConfiguration.label.tabTitle"))) {
			membershipConfigurationBean.initConfiguration();
		}
	}

}
