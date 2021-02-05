package com.project.common.validation;

import java.util.Iterator;


/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class UIComponent {
	
	public static javax.faces.component.UIComponent findComponent(javax.faces.component.UIComponent parent, String id){
		if(id.equals(parent.getId())){
			return parent;
		}
		Iterator<javax.faces.component.UIComponent> kids = parent.getFacetsAndChildren();
		while(kids.hasNext()){
			javax.faces.component.UIComponent kid = kids.next();
			javax.faces.component.UIComponent found = findComponent(kid, id);
			if(found != null){
				return found;
			}
		}
		return null;
	}
}
