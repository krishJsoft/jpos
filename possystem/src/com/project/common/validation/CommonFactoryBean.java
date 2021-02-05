package com.project.common.validation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import com.project.common.config.Configuration;
import com.project.common.factory.DesignFactory;
import com.project.common.factory.ErrorFactory;
import com.project.common.factory.MessageFactory;
import com.project.common.validation.CommonFactoryObj;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;

import java.math.BigDecimal;

@ManagedBean(name = "commonFactoryBean")
@SessionScoped
public class CommonFactoryBean  extends CommonFactoryObj implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213757957093655466L;
	FacesContext context = null;
	Configuration config = null;

	private boolean commonSuccess = false;

	public boolean isCommonSuccess() {
		return commonSuccess;
	}

	public void setCommonSuccess(boolean commonSuccess) {
		this.commonSuccess = commonSuccess;
	}

	public MessageFactory getMessageFactory() {
		return MessageFactory.getInstance();
	}
	
	

	public ErrorFactory getErrorFactory() {
		return ErrorFactory.getInstance();
	}

	public DesignFactory getDesignFactory() {
		return DesignFactory.getInstance();
	}

	public Configuration getConfig() {
		return Configuration.getConfiguration();
	}

	// use for check error message for value is required 1. send object value ,
	// 2. xhtml label Name ,
	// 3. xhtml labelValue ..
	public boolean checkIsNullAssignMessage(Object checkObjectValue,
			String labelName, String lableValue) {
		if (ValidatorUtil.isNull(checkObjectValue)) {
			this.reportErrorToMessageHandler(
					this.getErrorFactory().getError("errors.value.required",
							this.getMessageFactory().getMessage(labelName)),
					null, lableValue);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkIsZeroAssignMessage(String checkObjectValue,
			String labelName, String lableValue) {
		if (ValidatorUtil.isZero(checkObjectValue)) {
			this.reportErrorToMessageHandler(
					this.getErrorFactory().getError("errors.value.required",
							this.getMessageFactory().getMessage(labelName)),
					null, lableValue);
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean checkIsZeroDecimalAssignMessage(BigDecimal checkObjectValue,
			String labelName, String lableValue) {
		if (ValidatorUtil.isZeroDecimal(checkObjectValue)) {
			this.reportErrorToMessageHandler(
					this.getErrorFactory().getError("errors.value.required",
							this.getMessageFactory().getMessage(labelName)),
					null, lableValue);
			return true;
		} else {
			return false;
		}
	}
	
	

	
	public void minLengthError(Object checkObjectValue,
			String labelName, String lableValue,String minLength) {
			this.reportErrorToMessageHandler( this.getErrorFactory().getError("errors.length", 
					this.getMessageFactory().getMessage(labelName),minLength), null, lableValue);
	}
	
	public void requiredMessageError( String labelName, String lableValue) {
			this.reportErrorToMessageHandler( this.getErrorFactory().getError("errors.value.required",
							this.getMessageFactory().getMessage(labelName)), null, lableValue);
	}
	
	
	public void alreadyExistError( String labelName, String lableValue) {
		this.reportErrorToMessageHandler( this.getErrorFactory().getError("errors.already.exists",
						this.getMessageFactory().getMessage(labelName)), null, lableValue);
	}
	
	public void invalidFormatError(String labelName, String lableValue) {
		this.reportErrorToMessageHandler( this.getErrorFactory().getError("errors.format",
						this.getMessageFactory().getMessage(labelName)), null, lableValue);
	}
	
	public void invalidError(String labelName, String lableValue) {
		this.reportErrorToMessageHandler( this.getErrorFactory().getError("errors.invalid",
						this.getMessageFactory().getMessage(labelName)), null, lableValue);
	}
	
	public void displayErrorMessage(String labelName, String lableValue){
		this.reportErrorToMessageHandler( this.getErrorFactory().getError(this.getMessageFactory().getMessage(labelName)), null, lableValue);
	}
	
	
	// use for error message
	public void reportErrorToMessageHandler(String summary, String detail,
			String componentID) {
		getValidatorReportMessageHandler(FacesMessage.SEVERITY_ERROR, summary,
				detail, componentID);
	}

	// use for success message ..
	public void reportSuccessToMessageHandler(String summary, String detail,
			String componentID) {
		reportMessageToMessageHandler(FacesMessage.SEVERITY_INFO, summary,
				detail, componentID);
	}

	public void reportMessageToMessageHandler(FacesMessage.Severity severity,
			String summary, String detail, String componentID) {
		context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, detail, summary);
		context.addMessage(null, message);
	}

	public FacesMessage getValidatorReportMessageHandler(
			FacesMessage.Severity severity, String detail, String summary,
			String componentID) {

		context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary(summary);
		message.setDetail(detail);
		if (ValidatorUtil.isNotNull(context.getViewRoot())) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							componentID).getClientId(context), message);
		} else {
			context.addMessage(null, message);
		}

		return message;
	}

	public void defaultDialogConfig(String header, String content) {
		this.setHeader(header);
		this.setContent(content);
		this.setCheckOpenCommonDialog(true);
		this.setDialogWidth(Integer.parseInt(this.getDesignFactory().getDesign(
				"common.dialog.width")));
		this.setDialogHeight(Integer.parseInt(this.getDesignFactory()
				.getDesign("common.dialog.height")));
	}

	public void defaultDialogConfig(String header, String content,
			int dialogWidth, int dialogHeight) {
		this.setHeader(header);
		this.setContent(content);
		this.setCheckOpenCommonDialog(true);
		this.setDialogWidth(dialogWidth);
		this.setDialogHeight(dialogHeight);
	}

	public void clearDefaultDialogConfig() {
		this.setHeader("asfasdfasfsadfa");
		this.setContent("asfsadfasfasfdasd");
		this.setCheckOpenCommonDialog(false);
		this.setDialogWidth(0);
		this.setDialogHeight(0);
	}
}
