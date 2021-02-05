package com.project.bean.tempFix;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "tempFixBean")
@SessionScoped
public class TempFixBean {

	Integer memoryHitCount=0;
	
	public Integer getMemoryHitCount() {
		return memoryHitCount;
	}

	public void setMemoryHitCount(Integer memoryHitCount) {
		this.memoryHitCount = memoryHitCount;
	}

	
	
	public void memoryRefresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		try {
			this.memoryHitCount++;
			
			if(memoryHitCount>80) {
				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());	
				this.memoryHitCount=0;
			}
			
		} catch (IOException ex) {
			
			ex.printStackTrace();
		}
	}

	
}
