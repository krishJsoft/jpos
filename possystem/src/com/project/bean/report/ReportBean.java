package com.project.bean.report;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import com.project.common.util.ConvertUtil;

@ManagedBean(name = "reportBean")
@SessionScoped
public class ReportBean {
	
	
	private String URL = "/frameset?__report=report/testReport.rptdesign";

	public void BirtViewer() throws IOException {
		HttpServletRequest request = (HttpServletRequest) (FacesContext
				.getCurrentInstance().getExternalContext().getRequest());
		URL = request.getContextPath() + URL;
		System.out.println("-----------------------------------------------------------");
		System.out.println("BirtViewer() :: Complete Report Path=\"" + URL + "\"");
		System.out.println("-----------------------------------------------------------");

		FacesContext.getCurrentInstance().getExternalContext().redirect(URL);

	}
	
	
	
	public void executeSampleInvoice(ActionEvent event) {		
		FacesContext faces = FacesContext.getCurrentInstance();		
		try{			
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() + "/AlphaHQ/frameset?__report=/report/books_report.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false");
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
