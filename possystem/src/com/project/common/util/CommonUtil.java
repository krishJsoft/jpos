package com.project.common.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.constants.StringConstants;
import com.project.common.validation.UIComponent;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class CommonUtil {

	public static Configuration config = Configuration.getConfiguration();

	// This method for get IdType Label based on IdType value
	public static String getIdTypeLabel(String idType) {
		if (config.getIntValue(IConfiguration.COMMON_IDTYPE_RESIDENT_VALUE) == Integer
				.parseInt(idType)) {
			return StringConstants.IDTYPE_RESIDENT;
		} else if (config
				.getIntValue(IConfiguration.COMMON_IDTYPE_ARMYPOLICE_VALUE) == Integer
				.parseInt(idType)) {
			return StringConstants.IDTYPE_ARMYPOLICE;
		} else if (config
				.getIntValue(IConfiguration.COMMON_IDTYPE_FOREIGNER_VALUE) == Integer
				.parseInt(idType)) {
			return StringConstants.IDTYPE_FOREIGNER;
		} else {
		}
		return null;
	}

	public static String getGenderLabel(String gender) {
		if (config.getIntValue(IConfiguration.COMMON_GENDER_MALE_VALUE) == Integer
				.parseInt(gender)) {
			return StringConstants.GENDER_MALE;
		} else {
			return StringConstants.GENDER_FEMALE;
		}
	}

	public static String getRaceLabel(String race) {
		if (config.getIntValue(IConfiguration.COMMON_RACE_MALAY_VALUE) == Integer
				.parseInt(race)) {
			return StringConstants.RACE_MALAY;
		} else if (config.getIntValue(IConfiguration.COMMON_RACE_CHINESE_VALUE) == Integer
				.parseInt(race)) {
			return StringConstants.RACE_CHINESE;
		} else if (config.getIntValue(IConfiguration.COMMON_RACE_INDIAN_VALUE) == Integer
				.parseInt(race)) {
			return StringConstants.RACE_INDIAN;
		} else {
			return StringConstants.RACE_OTHER;
		}
	}
	
	
	
	public static void showExceptionMessage(String excepMsg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"mymasterpanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_INFO,excepMsg,null));				
	}
	
	
	
}
