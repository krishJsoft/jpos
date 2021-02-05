package com.project.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import com.project.model.datamodel.ProductModel;

@FacesConverter("pickListProductConverter")
public class ProductConverter implements Converter  {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
	        Object value) {
		  String str = "";
		    if (value instanceof ProductModel) {
		        str = "" + ((ProductModel) value).getProductId();
		    }
		    return str;
	}
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		 Object ret = null;
		    if (arg1 instanceof PickList) {
		        Object dualList = ((PickList) arg1).getValue();
		        DualListModel dl = (DualListModel) dualList;
		        for (Object o : dl.getSource()) {
		            String id = "" + ((ProductModel) o).getProductId();
		            if (value.equals(id)) {
		                ret = o;
		                break;
		            }
		        }
		        if (ret == null)
		            for (Object o : dl.getTarget()) {
		                String id = "" + ((ProductModel) o).getProductId();
		                if (value.equals(id)) {
		                    ret = o;
		                    break;
		                }
		            }
		    }
		    return ret;
	}
}
