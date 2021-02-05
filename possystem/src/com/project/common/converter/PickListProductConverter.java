package com.project.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.project.model.datamodel.ProductModel;

@FacesConverter("productConverter")
public class PickListProductConverter implements Converter  {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
	        Object value) {
	    String id = (value instanceof ProductModel) ? ((ProductModel) value).getBarcode()
	            : null;
	    return (id != null) ? String.valueOf(id) : null;
	}
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		ProductModel product = new ProductModel();
		product.setBarcode(value);
	    return product;
	}
}
