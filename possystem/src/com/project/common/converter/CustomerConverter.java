package com.project.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.project.model.datamodel.CustomerModel;

@FacesConverter("customerConverter")
public class CustomerConverter implements Converter  {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
	        Object value) {
	    Integer id = (value instanceof CustomerModel) ? ((CustomerModel) value).getCustomerId()
	            : null;
	    return (id != null) ? String.valueOf(id) : null;
	}
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		CustomerModel customer = new CustomerModel();
		customer.setCustomerId(Integer.parseInt(value));
	    return customer;
	}
}
