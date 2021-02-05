package com.project.common.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.project.common.validation.ValidatorUtil;


/**
 * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * <strong> Integer Validator
 */
@FacesValidator(value="integerUtil")
public class IntegerUtil implements Validator{
	
	/**
	 * 
	 */
	public IntegerUtil() {
		// TODO Auto-generated constructor stub
	}

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String MobileNoPrefix = (String)value;
        String messageSummary = null;
        String messageDetails = null;
		/*MessageFactory messageFactory = MessageFactory.getInstance();
        
        if(!ValidatorUtil.isValidNumber(MobileNoPrefix)){
            messageSummary = messageFactory.getMessage("invalidInteger");
            messageDetails = messageFactory.getMessage("invalidInteger");
         }
        */
        if (messageSummary!=null){ 
        	FacesMessage message = new FacesMessage();             
        	message.setSeverity(FacesMessage.SEVERITY_ERROR);             
        	message.setSummary(messageSummary);             
        	message.setDetail(messageDetails);             
        	context.addMessage(component.getId(), message);             
        	throw new ValidatorException(message);         
        }
    }	

}
