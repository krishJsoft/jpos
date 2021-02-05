package com.project.bean.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IStaffBO;
import com.project.bo.interfaces.RoleBO;
import com.project.bo.interfaces.RoleFunctionLinkBO;
import com.project.model.datamodel.MasterFunctionModel;
import com.project.model.datamodel.ModuleMasterFunctionModel;
import com.project.model.datamodel.RoleModel;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;


@ManagedBean(name = "roleBean")
@SessionScoped
public class RoleBean {
	
	public static Logger log = LoggerFactory.getLogger(RoleBean.class);
	

	private  List<RoleModel> roleInfoList;
	private RoleModel roleInfo = new RoleModel();
	private List<RolePrivilegesModel> allPrivilagesList;
	private List<ModuleMasterFunctionModel> allMasterPrivilagesList;
	private List<RolePrivilegesModel> selectedRolePrivilages;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private int selectedRoleId;    // used while deleting and other process
	private String roleAction;  // Save / Update
	private boolean userAssignedForRole;   // used while deleting Roles
	private Map<Integer, Boolean> checkMap = new HashMap<Integer, Boolean>();   // for Privilages checkbox
	
	private final String save = "save";
	private final String update = "update";
	private final String saveSuccMsg = "Role Successfully Saved!";
	private final String updateSuccMsg = "Role Successfully Updated!";
	private final String privSaveSuccMsg = "Privilages Successfully Saved!";
	
	private String successMessage = null;
	
	private boolean checkAllAdd;  
	private boolean checkAllEdit;  
	private boolean checkAllView;  
	
	private boolean checkAllDelete;  
	private boolean checkAllApprove;  
	private boolean checkAllPrint;  
	private Integer roleId;	
	
	
	RoleBO roleBO=objectMapController.getRoleBO();

	RoleFunctionLinkBO rolefunctionlinkBO=objectMapController.getRolefunctionlinkBO();
	
	
	public RoleBO getRoleBO() {
		return roleBO;
	}

	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}

	public RoleFunctionLinkBO getRolefunctionlinkBO() {
		return rolefunctionlinkBO;
	}

	public void setRolefunctionlinkBO(RoleFunctionLinkBO rolefunctionlinkBO) {
		this.rolefunctionlinkBO = rolefunctionlinkBO;
	}
	

	public List<RoleModel> getRoleInfoList() {
		return roleInfoList;
	}

	public void setRoleInfoList(List<RoleModel> roleInfoList) {
		this.roleInfoList = roleInfoList;
	}

	public RoleModel getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleModel roleInfo) {
		this.roleInfo = roleInfo;
	}

	public List<RolePrivilegesModel> getAllPrivilagesList() {
		return allPrivilagesList;
	}

	public void setAllPrivilagesList(List<RolePrivilegesModel> allPrivilagesList) {
		this.allPrivilagesList = allPrivilagesList;
	}	
	
	public List<ModuleMasterFunctionModel> getAllMasterPrivilagesList() {
		return allMasterPrivilagesList;
	}

	public void setAllMasterPrivilagesList(
			List<ModuleMasterFunctionModel> allMasterPrivilagesList) {
		this.allMasterPrivilagesList = allMasterPrivilagesList;
	}

	public List<RolePrivilegesModel> getSelectedRolePrivilages() {
		return selectedRolePrivilages;
	}

	public void setSelectedRolePrivilages(List<RolePrivilegesModel> selectedRolePrivilages) {
		this.selectedRolePrivilages = selectedRolePrivilages;
	}

	public int getSelectedRoleId() {
		return selectedRoleId;
	}

	public void setSelectedRoleId(int selectedRoleId) {
		this.selectedRoleId = selectedRoleId;
	}

	
	public String getRoleAction() {
		return roleAction;
	}

	public void setRoleAction(String roleAction) {
		this.roleAction = roleAction;
	}

	public boolean isUserAssignedForRole() {
		return userAssignedForRole;
	}

	public void setUserAssignedForRole(boolean userAssignedForRole) {
		this.userAssignedForRole = userAssignedForRole;
	}

	public Map<Integer, Boolean> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(Map<Integer, Boolean> checkMap) {
		this.checkMap = checkMap;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}	
	
	public boolean isCheckAllAdd() {
		return checkAllAdd;
	}

	public void setCheckAllAdd(boolean checkAllAdd) {
		this.checkAllAdd = checkAllAdd;
	}

	public boolean isCheckAllEdit() {
		return checkAllEdit;
	}

	public void setCheckAllEdit(boolean checkAllEdit) {
		this.checkAllEdit = checkAllEdit;
	}

	public boolean isCheckAllView() {
		return checkAllView;
	}

	public void setCheckAllView(boolean checkAllView) {
		this.checkAllView = checkAllView;
	}

	public boolean isCheckAllDelete() {
		return checkAllDelete;
	}

	public void setCheckAllDelete(boolean checkAllDelete) {
		this.checkAllDelete = checkAllDelete;
	}

	public boolean isCheckAllApprove() {
		return checkAllApprove;
	}

	public void setCheckAllApprove(boolean checkAllApprove) {
		this.checkAllApprove = checkAllApprove;
	}

	public boolean isCheckAllPrint() {
		return checkAllPrint;
	}

	public void setCheckAllPrint(boolean checkAllPrint) {
		this.checkAllPrint = checkAllPrint;
	}

	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	// Called on first time when the page loads
	public void initValues(){
		resetAll();
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());  // get roleInfoList
		allPrivilagesList = roleBO.getAllPrivilagesInfo();
		allMasterPrivilagesList=roleBO.getAllMasterPrivilagesInfo();// get allPrivilagesList, default list.
	}
	
	
	public void searchRole()
	{
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());
	}
	
	public void resetSearchRole()
	{
		this.setRoleId(null);
		searchRole();
	}
	
	
	public void resetAll(){
		setRoleInfoList(null);
		setRoleInfo(null);
		setAllPrivilagesList(null);
		setSelectedRolePrivilages(null);
		setSelectedRoleId(0);
		setRoleAction(null);
		setUserAssignedForRole(false);
		setCheckMap(null);
		setSuccessMessage(null);
	}
	
	public void onClick_Edit(ActionEvent actionEvent){
		setSuccessMessage(null);
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		Integer roleId = (Integer) actionEvent.getComponent().getAttributes().get("editrole");
		roleInfo = new RoleModel();
		this.roleInfo = roleBO.getRoleInfo(roleId); 
		if(roleInfo == null){
			log.error("Cannot Edit Role, Role not found for roleId : "+roleId);
			return;
		}
		setRoleAction(update);
		roleBeanInfo.editRole();  // for navigating to addRole.xhtml
	}
	
	
	public void updateRole(){
		
		FacesContext context = FacesContext.getCurrentInstance();

		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		// Validating 
		if(!roleBeanInfo.validateRole(roleInfo, roleInfoList, true)){
			log.error("Cannot Update Role, Validation Error @ updateRole()");
			return;
		}
		
		roleBO.update(roleInfo);

		// resetting the values
		this.roleInfo = new RoleModel();           
		setRoleAction("");
		
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());  // getting updated rolesInfo list
		
	//	setSuccessMessage(updateSuccMsg);
		roleBeanInfo.goToRoleHomePage(); // for Navigation
		CommonListBeanInfo.getAllRoleList();
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_INFO,updateSuccMsg,null));	
				
		
	}
	
	public void onClick_Delete(ActionEvent actionEvent){
		setSuccessMessage(null);
		Integer roleId = (Integer) actionEvent.getComponent().getAttributes().get("deleterole");
		setSelectedRoleId(roleId);
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		// checking the user is already assigned access rights  and setting the boolean FLAG
		setUserAssignedForRole(roleBeanInfo.isRoleAssigned());  
	}
	
	public void deleteRoleConfirm(ActionEvent actionEvent) {
		if(selectedRoleId == 0){
			log.error("Cannot Delete current role , SelectedRoleId is Null");
			return;
		}
		try {
			// deleting all privilages(rolefunctionlink),  only then we can delete the role
			List<RolePrivilegesModel> rolePrivilages = rolefunctionlinkBO.findPrivilagesByRole(selectedRoleId);
			if(rolePrivilages !=null && rolePrivilages.size() !=0 ){
				for(RolePrivilegesModel rolePrivilegesModel : rolePrivilages){
					rolefunctionlinkBO.delete(rolePrivilegesModel.getRoleFunctionLinkId());
				}
			}
			
			roleBO.delete(selectedRoleId);
			 // resetting the value
			selectedRoleId = 0;                  
			this.roleInfo = new RoleModel();
			roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());  // getting updated rolesInfo list
			
			//setSuccessMessage("Role Successfully Deleted!");
			
			
		} catch (Exception e) {
			log.error("Exception @ deleteRoleConfirm : "+e.getMessage());
		}
	}

	
	public void onClick_Privilages(ActionEvent actionEvent){
		
		setSuccessMessage(null);
		Integer roleId = (Integer) actionEvent.getComponent().getAttributes().get("editrolepri");		
		this.roleInfo = new RoleModel();
		this.roleInfo = roleBO.getRoleInfo(roleId); 
		
		if(roleInfo == null){
			log.error("Cannot Edit Privilages, Role not found for roleId : "+roleId);
			return;
		}		
		this.setSelectedRoleId(roleId);
		this.setRoleInfo(roleInfo);
		initCheckMap();
		
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		roleBeanInfo.addPrivileges();  // for navigating 
	}
	

	public void cancelPrivilages(){
		setSelectedRolePrivilages(null);
		setSelectedRoleId(0);
		setRoleInfo(null);
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		roleBeanInfo.cancel();  // for Navigation
	}
	
	
	public void addNewRole(){
		
		setSuccessMessage(null);
		// reset roleInfo
		this.roleInfo = new RoleModel();
		setRoleAction(save);
		
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		roleBeanInfo.newRole(); // for Navigation
	}
	
	public void saveRole(){
		
		FacesContext context = FacesContext.getCurrentInstance();

		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		// Validating 
		if(!roleBeanInfo.validateRole(roleInfo, roleInfoList, false)){
			log.error("Cannot Save Role, Validation Error @ saveRole()");
			return;
		}
		
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		roleInfo.setBranchId(loginBean.getBranch().getBranchId());
		roleBO.save(roleInfo, loginBean.getLoginName());
		CommonListBeanInfo.getAllRoleList();
		setSuccessMessage(saveSuccMsg);
		
		
		

		// resetting the values
		this.roleInfo = new RoleModel();           
		setRoleAction("");
		
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());  // getting updated rolesInfo list
		
		roleBeanInfo.goToRoleHomePage(); // for Navigation
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_INFO,saveSuccMsg,null));	
				
		
		
	}
	
	
	public void resetRole(){
		if(StringUtils.isBlank(roleAction)){
			this.roleInfo = new RoleModel();
		}else if(roleAction.equals(save)){
			this.roleInfo = new RoleModel();
		}else if(roleAction.equals(update)){
			this.roleInfo.setRoleDescription(null);
			this.roleInfo.setRoleName(null);
		}
	}
	
	
	public void cancelRole(){
		
		resetRole();
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());  // getting updated rolesInfo list
		
		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		roleBeanInfo.cancel();    // for Navigation
	}
	
	
	
	public void initCheckMap(){
		
		checkMap = new HashMap<Integer, Boolean>();
		selectedRolePrivilages = rolefunctionlinkBO.findPrivilagesByRole(this.getSelectedRoleId());
		
		if(selectedRolePrivilages == null || selectedRolePrivilages.size()==0){
			log.debug(" No Privilages Available for selected user, roleID : "+selectedRoleId+", roleName : "+this.roleInfo.getRoleName());
		
			for (RolePrivilegesModel priv : allPrivilagesList) {
					checkMap.put(priv.getFunctionId(), Boolean.FALSE);
			}
			
		}else{
			List<Integer> currUsersFunctionIds = new ArrayList<Integer>();
			
			for (RolePrivilegesModel priv : selectedRolePrivilages) {
				currUsersFunctionIds.add(priv.getFunctionId());
			}

			for (RolePrivilegesModel priv : allPrivilagesList) {
				if (currUsersFunctionIds.contains(priv.getFunctionId())) {
					checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				} else {
					checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}
		}
	}
	
	
	public void savePrivilages() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean validChecked=false;

		RoleBeanInfo roleBeanInfo = new RoleBeanInfo();
		List<Integer> oldPrivFunctionIds = null;
		// findPrivialges by roleId, to get existing privilages of the role
		List<RolePrivilegesModel> oldPrivilages = rolefunctionlinkBO.findPrivilagesByRole(this.roleInfo.getRoleId());
		
		for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
			if (entry.getValue()) {
				validChecked=true;
				break;
			}
		}		
		
		if(validChecked)
		{
		if(oldPrivilages != null &&  oldPrivilages.size()!=0 ){
			oldPrivFunctionIds = new ArrayList<Integer>();
			for(RolePrivilegesModel oldPriv : oldPrivilages){
				oldPrivFunctionIds.add(oldPriv.getFunctionId());
			}
			
			// Deleting the removed privilages
			boolean privilageRemoved = true;
			for(RolePrivilegesModel oldPriv : oldPrivilages){
				privilageRemoved = true;
				for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
					if (entry.getValue()) {  // If checked (selected)
						if(entry.getKey().equals(oldPriv.getFunctionId())){
							privilageRemoved = false;
							break;
						}
					}
				}
				if(privilageRemoved){
					rolefunctionlinkBO.delete(oldPriv.getRoleFunctionLinkId());
				}
			}
		}
				
		rolefunctionlinkBO.save(this.roleInfo.getRoleId(), checkMap, oldPrivFunctionIds);
				
		//setSuccessMessage(privSaveSuccMsg);
		//roleBeanInfo.goToRoleHomePage(); // for Navigation
		
		//roleBeanInfo.saveRolePrivileges();    // Success Message
		roleInfoList = roleBO.getAllRoles(roleId,loginBean.getBranch().getBranchId());
		roleBeanInfo.goToRoleHomePage(); // for Navigation
		
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,privSaveSuccMsg,null));	
					// getting updated rolesInfo list		
		
		}
		else
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please Select Privileges ",null));	
			
		}

		
		
	}
	
	
	public void selectAllPrivilages(){
		checkMap = new HashMap<Integer, Boolean>();
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==1)
			{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
			}
			
		}
	}
	
	
	public void removeAllPrivilages(){
		checkMap = new HashMap<Integer, Boolean>();
		for (RolePrivilegesModel priv : allPrivilagesList) {
			checkMap.put(priv.getFunctionId(), Boolean.FALSE);
		}
	}
	
	
	public void resetPrivilages(){
		
		initCheckMap();
	}

	
	
	
	public void selectAddRemove_AddPrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			
			if(priv.getMasterfunctionId()==1)
			{	
				if(checkAllAdd)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
		}
	
	
	public void selectAddRemove_EditPrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==2)
			{
				if(checkAllEdit)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
		}
	
	
	public void selectAddRemove_ViewPrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==3)
			{
				if(checkAllView)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
		}
	
	
	public void selectAddRemove_DeletePrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==4)
			{
				if(checkAllDelete)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
		}
	
	
	public void selectAddRemove_ApprovePrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==5)
			{
				if(checkAllApprove)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
		}
	
	
	public void selectAddRemove_PrintPrivilages(){
		
		for (RolePrivilegesModel priv : allPrivilagesList) {			
			
			if(priv.getMasterfunctionId()==6)
			{
				if(checkAllPrint)
				{
				checkMap.put(priv.getFunctionId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(priv.getFunctionId(), Boolean.FALSE);
				}
			}			
		}
	}
	
	public void deleteRole(ActionEvent actionEvent) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			Integer roleId = (Integer) actionEvent.getComponent().getAttributes().get("deleterole");

			IStaffBO staffBO=objectMapController.getStaffBO();
			
			boolean roleAssigned=false;
			boolean deleteSuccess=false;
			
			
			roleAssigned=staffBO.isRoleAssigned(roleId,loginBean.getBranch().getBranchId());
			
			if(roleAssigned) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete, there are users assigned with that role.", null));
			}else {
				// deleting all privilages(rolefunctionlink),  only then we can delete the role
				List<RolePrivilegesModel> rolePrivilages = rolefunctionlinkBO.findPrivilagesByRole(roleId);
				if(rolePrivilages !=null && rolePrivilages.size() !=0 ){
					for(RolePrivilegesModel rolePrivilegesModel : rolePrivilages){
						rolefunctionlinkBO.delete(rolePrivilegesModel.getRoleFunctionLinkId());
					}
				}
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Role successfully deleted", null));
				roleBO.delete(roleId);           
				resetSearchRole();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"addRole").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	

	}
	
	
	
}
