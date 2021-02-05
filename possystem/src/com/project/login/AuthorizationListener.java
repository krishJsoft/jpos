package com.project.login;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isMobile;
	
	public boolean isMobile() {
		return isMobile;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}
	
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		String loginPage="loginPage";
		boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
		
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		String userAgent = request.getHeader("user-agent");
		if(userAgent.matches(".*Android.*")) {
			this.setMobile(true);
			isLoginPage=(currentPage.lastIndexOf("mobileIndex.xhtml") > -1);
			loginPage="mobileLoginPage";
		
		}
		else {
			this.setMobile(false);
		}
		if (session == null) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, loginPage);
		}

		else {
			Object currentUser = session.getAttribute("username");

			if (!isLoginPage && (currentUser == null || currentUser == "")) {
				NavigationHandler nh = facesContext.getApplication()
						.getNavigationHandler();
				nh.handleNavigation(facesContext, null, loginPage);
			}

		}
	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
