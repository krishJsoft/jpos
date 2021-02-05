package com.project.login;


import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.project.model.his.Rolefunctionlink;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.SystemconfigurationModel;
import com.project.model.datamodel.UserLoginModel;
import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.bo.interfaces.IUserLoginBO;
import com.project.bo.interfaces.RoleFunctionLinkBO;
import com.project.activation.ActvBean;
import com.project.bean.admin.CommonListBean;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.admin.ProductBean;
import com.project.bean.admin.SupplierBean;
import com.project.bean.admin.SystemSettingBean;
import com.project.bean.sales.sale.OrderBean;
import com.project.bean.sales.sale.PosBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.EmailProcess;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.home.ThemeSwitcherBean;

//Author Gopal Ar 
//2013-04

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2693418724418548849L;
	private String userName;
	private String password;
	private Integer branchId;
	private Integer posCounterId;
	private ArrayList<String> moduleList = new ArrayList<String>();
	private ArrayList<String> functionList = new ArrayList<String>();
	private String roleName;
	private String loginName;	
	private int forcereset = 0;
	private String curAction;
	private String icNo;
	private int userId;	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;	
	private int Roleid = 0;
	
	private String succMsg="Login details was successfully sent to your E-mailID";
	private String succMsgForgPwd;
	private String reminder;
	private String newReminder;
	private String reminderAppendType;
	private boolean sessionExpired;

	private String screenWidth;
	
	private Integer columnGrid;
	
	List<BranchstaffmemberModel> branchstaffmember = null;
	Configuration config = Configuration.getConfiguration();
	String projectHomeFile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	BranchstaffmemberModel logdetail = new BranchstaffmemberModel();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	UserLoginModel userLoginModel = new UserLoginModel();
	String action;
	BranchModel branch = new BranchModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private IBranchBO branchBO=objectMapController.getBranchBO();


	@ManagedProperty("#{userLoginBO}")
	private IUserLoginBO userLoginBO; // injected Spring defined service

	public IUserLoginBO getUserLoginBO() {
		return userLoginBO;
	}

	public void setUserLoginBO(IUserLoginBO userLoginBO) {
		this.userLoginBO = userLoginBO;
	}

	@ManagedProperty("#{staffBO}")
	private IStaffBO staffBO; // injected Spring defined service	
	
	public IStaffBO getStaffBO() {
		return staffBO;
	}

	public void setStaffBO(IStaffBO staffBO) {
		this.staffBO = staffBO;
	}

	@ManagedProperty("#{rolefunctionLinkBO}")
	private RoleFunctionLinkBO rolefunctionLinkBO; // injected Spring defined service

	
	
	public RoleFunctionLinkBO getRolefunctionLinkBO() {
		return rolefunctionLinkBO;
	}

	public void setRolefunctionLinkBO(RoleFunctionLinkBO rolefunctionLinkBO) {
		this.rolefunctionLinkBO = rolefunctionLinkBO;
	}	
	

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getPosCounterId() {
		return posCounterId;
	}

	public void setPosCounterId(Integer posCounterId) {
		this.posCounterId = posCounterId;
	}

	public ArrayList<String> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<String> moduleList) {
		this.moduleList = moduleList;
	}

	public ArrayList<String> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(ArrayList<String> functionList) {
		this.functionList = functionList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getForcereset() {
		return forcereset;
	}

	public void setForcereset(int forcereset) {
		this.forcereset = forcereset;
	}	
	
	public UserLoginModel getUserLoginModel() {		
		
		return userLoginModel;
	}

	public void setUserLoginModel(UserLoginModel userLoginModel) {
		this.userLoginModel = userLoginModel;
	}

	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

	public String getCurAction() {
		return curAction;
	}

	public void setCurAction(String curAction) {
		this.curAction = curAction;
	}

	public String getIcNo() {
		return icNo;
	}

	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSuccMsgForgPwd() {
		return succMsgForgPwd;
	}

	public void setSuccMsgForgPwd(String succMsgForgPwd) {
		this.succMsgForgPwd = succMsgForgPwd;
	}

	public boolean isSessionExpired() {
		return sessionExpired;
	}

	public void setSessionExpired(boolean sessionExpired) {
		this.sessionExpired = sessionExpired;
	}
	

	public String getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Integer getColumnGrid() {
		return columnGrid;
	}

	public void setColumnGrid(Integer columnGrid) {
		this.columnGrid = columnGrid;
	}

	public BranchstaffmemberModel getLogdetail() {
		return logdetail;
	}

	public void setLogdetail(BranchstaffmemberModel logdetail) {
		this.logdetail = logdetail;
	}
	
	public void setLoginDetails(String username,String password, String branchId) {
	    
	    this.setUserName(username);
	    this.setPassword(password);
	    
	    login();
	}
	
	public String login() {		
					
			boolean isMobile=false;
		    action = "login";
		    if(!loginValidation()){
			return action; 
			}
					action = "login";
					CommonListBeanInfo commonListBeanInfo  =new CommonListBeanInfo();
					ThemeSwitcherBean themeSwitcherBean = (ThemeSwitcherBean) BeanContext.getReference("themeSwitcherBean");
					try {
                    int invalidcount = 3;
					int invalidAttampts = 0;
					String userPassword="";
					String status="";
					UserLoginModel userLoginModel = new UserLoginModel();
						
					FacesContext context = FacesContext.getCurrentInstance();
					ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	
					List<BranchstaffmemberModel> branchstaffmember = null;
					//branchstaffmember = staffBO.findStaffMemberlistLogin(this.userName);
					
					branchstaffmember=staffBO.findStaffMemberlistLogin2(null,this.password,null,this.branchId);
					if (branchstaffmember.isEmpty()) {
						userName = "";						
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"loginPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage("loginpage.label.username.notValid"),null));
						
					} 
					else					
						{				
					Iterator<BranchstaffmemberModel> logincheck = branchstaffmember.iterator();
					while (logincheck.hasNext()) {
					logdetail = logincheck.next();
					loginName = logdetail.getFirstName();
					userName = logdetail.getEmailAddress();
					userPassword = logdetail.getPassword();
					invalidAttampts = logdetail.getInvalidAttempts();
					status = logdetail.getStatus();
					Roleid = logdetail.getRoleId();
					//reminder = logdetail.getReminder();
					userId = logdetail.getStaffId();
					forcereset = Integer.parseInt(logdetail.getForceReset());
					userLoginModel.setBranchName(logdetail.getBranchName());
					userLoginModel.setBranchId(logdetail.getBranchId());
					this.setUserLoginModel(userLoginModel);
					this.setBranch(branchBO.getBranchDetails(logdetail.getBranchId()));
					}						
					if ((userName == null) || (userName.equals(""))) {
						
					context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory()
					.getMessage("loginpage.label.username.notValid"),null));
					}
					else if ((userPassword != null) && (!userPassword.equals(password))) 
					{
						
					if (invalidAttampts != invalidcount) {
					logdetail.setInvalidAttempts(invalidAttampts + 1);
					staffBO.updateStaff(logdetail);
					context.addMessage( UIComponent.findComponent( context.getViewRoot(), "loginPanel").getClientId(context),
					new FacesMessage( FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage(
					"loginpage.label.Password.notValid"),null));
					}
					else {
						
					logdetail.setInvalidAttempts(invalidcount);
					logdetail.setForceReset("1");
					staffBO.updateStaff(logdetail);
					context.addMessage(UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
					"loginpage.label.username.Locked"), null));
					}
					}						
					else if (invalidAttampts == 3) {
						
					logdetail.setForceReset("1");
					context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
					"loginpage.label.username.Locked"), null));
					}
					else if (status == "0") {
						
					context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
							"loginpage.label.username.Locked"), null));
					}
					else {	
						
						if(this.getScreenWidth()==null || this.getScreenWidth().isEmpty()) {
							this.setScreenWidth("1024");
						}
						Integer gridsCol=Integer.parseInt(this.getScreenWidth())/131;
						this.setColumnGrid(gridsCol);
						//ActvBean actvBean = new ActvBean();
						///System.out.println("ABCD"+actvBean.isExpired());
					SystemSettingBean systemSettingBean = (SystemSettingBean) BeanContext.getReference("systemSettingBean");	
					systemSettingBean.loadSystemConfiguration();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", this.userName);
					HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
					if(logdetail.getThemeName()!=null &&(!logdetail.getThemeName().equalsIgnoreCase("")))
							{
						
					themeSwitcherBean.setTheme(logdetail.getThemeName());
							}					
					if (forcereset == 1) {
						
						projectHome.setTitlePage("Change Password");
						projectHome.setContentpage("/home/preSetPasswordForm.xhtml");

					} else {
						
						if(logdetail.getRoleName().equalsIgnoreCase("Waiter"))
						{
							
							HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
							String userAgent = request.getHeader("user-agent");

								projectHome.setTitlePage("Dashboard");
							if(userAgent.matches(".*Android.*")){
								projectHome.setContentpage("/mobileVersionUI/sales/mainMenu.xhtml");
							}else {
								OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
								ProductBean productBean = (ProductBean) BeanContext
										.getReference("productBean");
								
								productBean.initProduct();
								orderBean.resetTerminalOrder();
								projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
								projectHome.setInitPage(projectHome.getTerminalOrderContentPage());
								projectHome.setHomePage(projectHome.getContenttpage());
								projectHome.setContentpage("/terminalOrder/sales/terminalHome.xhtml");
								
							}
								
								
								projectHome.setHomePage(projectHome.getContenttpage());
							//}
							
						}else if(logdetail.getRoleName().equalsIgnoreCase("cashier")) {
							
							
							PosBean posBean = (PosBean) BeanContext
									.getReference("posBean");
							posBean.setPosTerminalPage(this.getPosCounterId());
						}	
						else
						{
							
							projectHome.setTitlePage("Dashboard");
							projectHome.setContentpage("/home/dashBoardMaster.xhtml");
							projectHome.setHeaderPage("/templates/header.xhtml");
						}							
					}
					
							
				
					List<Rolefunctionlink> rolefunctionslinkList = rolefunctionLinkBO.findfunctionbyRoleListAll(Roleid);
					Iterator<Rolefunctionlink> rolefunctionslinkItr = rolefunctionslinkList.iterator();
					
					while (rolefunctionslinkItr.hasNext()) {
						Rolefunctionlink rolefunctionslink = rolefunctionslinkItr.next();
						roleName = rolefunctionslink.getRole().getRoleName();
						String functionName = rolefunctionslink.getFunction().getFunctionName();						
						this.getFunctionList().add(functionName);
					}					
					logdetail.setInvalidAttempts(0);
					staffBO.updateStaff(logdetail);
					commonListBeanInfo.initLoadMethods();
					this.setRoleName(roleName);
					action=projectHomeFile;
						if(!isMobile)
							response.sendRedirect(projectHomeFile);
						}				
					}		
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}	
					
					return action;
		}
	
	
	
	public String passwordchange() {
		
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			boolean valid = true;
			valid = validPassword();
			if (valid) {
				
				logdetail.setPassword(this.getConfirmPassword());
				logdetail.setForceReset("0");
				staffBO.updateStaff(logdetail);
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				action = "loginPage";
			}
		} catch (Exception e) {
			context.addMessage( UIComponent.findComponent(context.getViewRoot(), "presetPassword").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}

		return action;
	}
	
	public void resetLogin() {
		this.setPassword(null);
		this.setUserName(null);
	}
	
	
	public void changePasswordReset() {
		this.setOldPassword("");
		this.setNewPassword("");
		this.setConfirmPassword("");
	}
	
	public boolean loginValidation() {
		boolean valid = true;

		/*
		 removed to enable login with password only
		if (factoryBean.checkIsNullAssignMessage(this.getUserName(),
				"loginpage.label.username", "userName")) {
			valid = false;
		}
		*/
		if (factoryBean.checkIsNullAssignMessage(this.getPassword(),
				"loginpage.label.password", "password")) {
			valid = false;
		}

		return valid;
	}
	
	public boolean validPassword() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean valid = true;
		if (factoryBean.checkIsNullAssignMessage(getOldPassword(), "loginpage.label.oldPassword", "opassword")) {

			valid = false;
		}
		if (factoryBean.checkIsNullAssignMessage(getNewPassword(), "loginpage.label.newPassword", "npassword")) {
			valid = false;
		}
		if (factoryBean.checkIsNullAssignMessage(getConfirmPassword(), "loginpage.label.confirmPassword", "cpassword")) {
			valid = false;
		}

		if (!newPassword.equals(confirmPassword)) {
			valid = false;

			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"npassword").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
					"loginpage.label.samepassword"), null));
		}
		if (!this.password.equals(this.oldPassword)) {

			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"opassword").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
					"loginpage.label.Password.notValid"), null));
			valid = false;
		}

		return valid;
	}
	
	
	public void submitForgotPwd(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");	
			
			if(! isValidData()){
				return;
			}
					
			logdetail=staffBO.getBranchstaffmemberDetails(logdetail.getStaffId());
			logdetail.setForceReset("0");
			logdetail.setInvalidAttempts(0);
			staffBO.updateStaff(logdetail);	
			EmailProcess emailProcess = new EmailProcess();
			emailProcess.sendEmailToNewUser(logdetail.getStaffCode(), userName, logdetail.getPassword());
			
			this.setUserName(null);
			this.setIcNo(null);
			this.setPassword(null);
			this.setCurAction(null);			
			
			context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,succMsg , null));
			
		} catch (Exception e) {
			context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() , null));
		}
		
		//factoryBean.displayErrorMessage("success.forgetpassword", "loginForm");
	}
	
	
	
	public void updateUserTheme()
	{	FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
		ThemeSwitcherBean themeSwitcherBean = (ThemeSwitcherBean) BeanContext.getReference("themeSwitcherBean");
		logdetail.setThemeName(themeSwitcherBean.getTheme());		
		staffBO.updateStaff(logdetail);	
		
	} catch (Exception e) {
		context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() , null));
	}
	}
	
	
	public boolean isValidData(){
		try {
			boolean valid = true;
			CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
			if(factoryBean.checkIsNullAssignMessage(getUserName(), "forgotPassword.label.username", "userNameFp")) {
				valid = false;
			} 
			if(factoryBean.checkIsNullAssignMessage(getIcNo(), "forgotPassword.label.icno", "icNo")) {
				valid = false;
			}
			if(!valid){
				return valid;
			}
			
			branchstaffmember = staffBO.findStaffMemberlistLogin(this.userName);	
			
			if(branchstaffmember == null  || branchstaffmember.size() == 0  || branchstaffmember.get(0) == null){
				factoryBean.invalidError("forgotPassword.label.username", "userNameFp");
				return false;
			}
			
			logdetail = branchstaffmember.get(0);
			if(! logdetail.getIdentificationNumber().equalsIgnoreCase(getIcNo())){
				factoryBean.invalidError("forgotPassword.label.icno", "icNo");
				return false;
			}
			
			return valid;
		} catch (Exception e) {
			factoryBean.invalidError("forgotPassword.label.username", "userNameFp");
			return false;
		}
	}

	
	public void click_forgotPassword(){
		
		this.setCurAction("forgotPassword"); 
		this.setUserName(null);
		this.setIcNo(null);
		this.setPassword(null);
		this.setSuccMsgForgPwd(null);
		this.setSessionExpired(false);
	}
	
	public void cancelForgotPwd(){
		this.setUserName(null);
		this.setIcNo(null);
		this.setPassword(null);
		this.setCurAction(null);
		this.setSuccMsgForgPwd(null);
	}
	
	public void updateUserStatus(BranchstaffmemberModel logdetailTemp)
	{	FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		logdetail=staffBO.getBranchstaffmemberDetails(logdetailTemp.getStaffId());
		logdetail.setThemeName(logdetailTemp.getThemeName());
		logdetail.setReminder(logdetailTemp.getReminder());
		staffBO.updateStaff(logdetail);	
		
	} catch (Exception e) {
		context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() , null));
	}
	}
	
	public void newSupplierRegistration()
	{
		SupplierBean supplierBean = (SupplierBean) BeanContext.getReference("supplierBean");
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", "newsupplier");
			supplierBean.newSupplier();
		}
		catch(Exception e)
		{
			context.addMessage( UIComponent.findComponent(context.getViewRoot(), "loginPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() , null));
		}
	}
	
}
