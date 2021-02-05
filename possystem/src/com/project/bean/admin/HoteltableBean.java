package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.contextmenu.ContextMenu;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.outputpanel.OutputPanel;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.project.bo.interfaces.ICommonListBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.sale.sales.HoteltableModel;
import com.project.model.sale.sales.HoteltableareaModel;

@ManagedBean(name = "hoteltableBean")
@SessionScoped
public class HoteltableBean {

	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	ICommonListBO commonListBO=objectMapController.getCommonListBO();
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

	
	List<HoteltableareaModel> hotelTableAreaList=new ArrayList<HoteltableareaModel>();
	HoteltableareaModel hotelTableArea=new HoteltableareaModel();
	
	List<HoteltableModel> unAssignedHotelTableList=new ArrayList<HoteltableModel>();
	List<HoteltableModel> assignedHotelTableList=new ArrayList<HoteltableModel>();
	List<String> tableNameList=new ArrayList<String>();
	
	private String draggedComponentId;
	private int areaId;
	
	public List<HoteltableareaModel> getHotelTableAreaList() {
		return hotelTableAreaList;
	}
	
	public void setHotelTableAreaList(List<HoteltableareaModel> hotelTableAreaList) {
		this.hotelTableAreaList = hotelTableAreaList;
	}
	
	public HoteltableareaModel getHotelTableArea() {
		return hotelTableArea;
	}
	
	public void setHotelTableArea(HoteltableareaModel hotelTableArea) {
		this.hotelTableArea = hotelTableArea;
	}

	public List<HoteltableModel> getUnAssignedHotelTableList() {
		return unAssignedHotelTableList;
	}

	public void setUnAssignedHotelTableList(List<HoteltableModel> unAssignedHotelTableList) {
		this.unAssignedHotelTableList = unAssignedHotelTableList;
	}

	public List<HoteltableModel> getAssignedHotelTableList() {
		return assignedHotelTableList;
	}

	public void setAssignedHotelTableList(List<HoteltableModel> assignedHotelTableList) {
		this.assignedHotelTableList = assignedHotelTableList;
	}

	public String getDraggedComponentId() {
		return draggedComponentId;
	}

	public void setDraggedComponentId(String draggedComponentId) {
		this.draggedComponentId = draggedComponentId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public void fetchAllHotelTableArea() {
		try{
			this.hotelTableAreaList=commonListBO.fetchHoteltablearea(null,loginBean.getBranch().getBranchId());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createHotelTableArea() {
		try{
			Boolean createSuccess=false;
			BranchModel branchObj=new BranchModel();
			branchObj.setBranchId(loginBean.getBranchId());
			this.getHotelTableArea().setBranch(branchObj);
			if(!this.getHotelTableArea().getAreaName().isEmpty()) {
				createSuccess=commonListBO.createHotelTableArea(this.getHotelTableArea());	
			}
			this.getHotelTableArea().setAreaName(null);
			this.fetchAllHotelTableArea();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void fetchUnassignedHotelTableList() {
		try {
			this.unAssignedHotelTableList=commonListBO.fetchHotelTable(null,null,null,loginBean.getBranch().getBranchId());
		}catch(Exception ex ) {
			ex.printStackTrace();
		}
		
	}
	
	public void loadTableLayout(int areaId) {
		try {
			this.setAreaId(areaId);
			tableNameList.clear();
			UIComponent comp = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:restrictPanel");
			OutputPanel container = (OutputPanel)comp;
			container.getChildren().clear();
			
			this.assignedHotelTableList=commonListBO.fetchHotelTable(null,null,areaId,loginBean.getBranch().getBranchId());
			for(HoteltableModel table : this.assignedHotelTableList) {
				addTableToDraggablePanel(table);
				tableNameList.add(table.getTableName());
			}
			
		}catch(Exception ex) {
			
		}
	}

	public void loadTerminalOrderTableList(int areaId) {
		this.setAreaId(areaId);
		
		try {
			this.assignedHotelTableList=commonListBO.fetchHotelTable(null,null,areaId,loginBean.getBranch().getBranchId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addTableToDraggablePanel(HoteltableModel table) {
		UIComponent comp = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:restrictPanel");
		OutputPanel container = (OutputPanel)comp;
	    
	    OutputLabel tableDragable=new OutputLabel();
	    tableDragable.setValue(table.getTableName());
	    int left=0,top=0,borderRadius=15;
	    if(table.getLeftPosition()!=null) {
	    	left=Integer.parseInt(table.getLeftPosition());
	    }
	    if(table.getTopPosition()!=null) {
	    	top=Integer.parseInt(table.getTopPosition());
	    }
	    if(table.getTableShape()!=null) {
	    	if(table.getTableShape().equalsIgnoreCase("round")) {
	    		borderRadius=50;	
	    	}
	    	
	    }
	    
	    tableDragable.setStyle("border:1px solid black;border-radius:"+borderRadius+"%;"
	    		+ "width:90px;"
	    		//+ "height:90px;"
	    		+ "cursor:grab;text-align:center;background:#E32526;color:White;z-index:2;"
	    		+ "font-size:2em;position:absolute;top: "+top+"px;left: "+left+"px;");
	    tableDragable.setId("table-" + tableDragable.getClientId());
	    
        
	    Draggable drag = new Draggable();
	    drag.setFor(tableDragable.getId());
	    drag.setCursor("grab");
	    drag.setContainment("parent");
	    
	    ContextMenu ctxMenu = new ContextMenu();
	    ctxMenu.setFor(tableDragable.getId());
	    
        MenuModel ctxModel = new DefaultMenuModel();
        
        DefaultSubMenu shapeMenu = new DefaultSubMenu("SHAPE");
        
        DefaultMenuItem item=new DefaultMenuItem("ROUND");
        item.setCommand("#{hoteltableBean.updateTableShape('"+tableDragable.getId()+"','round')}");
        item.setUpdate(tableDragable.getId());
        shapeMenu.addElement(item);
        
        item=new DefaultMenuItem("SQUARE");
        item.setCommand("#{hoteltableBean.updateTableShape('"+tableDragable.getId()+"','square')}");
        item.setUpdate(tableDragable.getId());
        shapeMenu.addElement(item);
        
        item=new DefaultMenuItem("DELETE");
        item.setCommand("#{hoteltableBean.deleteTable('"+tableDragable.getId()+"','"+drag.getClientId()+"','"+ctxMenu.getClientId()+"')}");
        item.setUpdate("tableSetupForm:restrictPanel");
        
        ctxModel.addElement(shapeMenu);
        ctxModel.addElement(item);
        
        ctxMenu.setModel(ctxModel);
	    

	    this.getUnAssignedHotelTableList().remove(table);
	    this.setDraggedComponentId(tableDragable.getId());
	    container.getChildren().add(tableDragable);
	    container.getChildren().add(ctxMenu);
	    container.getChildren().add(drag);
	}
	
	public void updateTableShape(String tableId,String shape) {
		UIComponent c = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:"+tableId);
	  	OutputLabel table=(OutputLabel)c;
	  	String radius="15";
	  	if(shape.equalsIgnoreCase("round")) {
	  		radius="50";
	  	}
	  	String style=table.getStyle();
	  	style=style.replaceAll("border-radius[\\s\\S]*?(;)","border-radius:"+radius+"%;");
	  	table.setStyle(style);
	  	
	}
	
	public void updateTableLayout() {
	  	UIComponent c = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:"+this.getDraggedComponentId());
	  	OutputLabel table=(OutputLabel)c;
	  	
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
	  	String style=table.getStyle();
	  	style=style.replaceAll("left[\\s\\S]*?(;)",params.get("left"));
	  	style=style.replaceAll("top[\\s\\S]*?(;)",params.get("top"));
	  	
	  	table.setStyle(style);
	}
	
	public void deleteTable(String tableId,String dragCompId,String ctxMenuId) {
		
		UIComponent comp = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:restrictPanel");
		OutputPanel container = (OutputPanel)comp;
	    
		UIComponent c = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:"+dragCompId);
		Draggable drag=(Draggable)c;
		container.getChildren().remove(drag);
		
		c = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:"+ctxMenuId);
		ContextMenu ctxtMenu=(ContextMenu)c;
		container.getChildren().remove(ctxtMenu);
		
		c = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:"+tableId);
		OutputLabel table=(OutputLabel)c;
		container.getChildren().remove(table);
		
	  		
	}
	
	public void saveTableLayout() {
		UIComponent comp = FacesContext.getCurrentInstance().getViewRoot().findComponent("tableSetupForm:restrictPanel");
		OutputPanel draggableContainer = (OutputPanel)comp;
		
		List<UIComponent> tableDragableList=new ArrayList<UIComponent>();
		tableDragableList=draggableContainer.getChildren();
		
		List<HoteltableModel> hotelTableObjList=new ArrayList<HoteltableModel>();
		
		   for ( UIComponent tableDragable : tableDragableList ) {
			   
		       if ( tableDragable instanceof OutputLabel  ) {
		    	   
		    	   OutputLabel table=(OutputLabel)tableDragable;
		    	   
		    	   String style=table.getStyle();
		    	   String left="0",top="0",borderRadius="0";
		    	   
		    	   Pattern p = Pattern.compile("left[\\s\\S]*?(;)");
		    	   Matcher m = p.matcher(style);

		    	   if (m.find())
		    	   {
		    		   left=m.group(0).substring(m.group(0).lastIndexOf(":")+2,m.group(0).lastIndexOf("p"));
		    		   //left=m.group(0).replaceAll("\\D+","");		    	      
		    	   }
		    	   
		    	   p=Pattern.compile("top[\\s\\S]*?(;)");
		    	   m=p.matcher(style);
		    	   
		    	   if(m.find()) {   
		    		   top=m.group(0).substring(m.group(0).lastIndexOf(":")+2,m.group(0).lastIndexOf("p"));
		    		   //top=m.group(0).replaceAll("\\D+","");
		    	   }
		    	   
		    	   p=Pattern.compile("border-radius[\\s\\S]*?(;)");
		    	   m=p.matcher(style);
		    	   if(m.find()) {   
		    		   borderRadius=m.group(0).substring(m.group(0).lastIndexOf(":")+1,m.group(0).lastIndexOf("%"));
		    	   }
		    	   if(!tableNameList.contains(""+table.getValue())) {
		    		   tableNameList.add(""+table.getValue());   
		    	   }
		    	   
		    	   HoteltableModel hotelTableObj=new HoteltableModel();
		    	   hotelTableObj.setLeftPosition(left);
		    	   hotelTableObj.setTopPosition(top);
		    	   if(borderRadius.equalsIgnoreCase("50")) {
		    		   hotelTableObj.setTableShape("round");
		    	   }else {
		    		   hotelTableObj.setTableShape("square");
		    	   }
		    	   hotelTableObj.setTableName(""+table.getValue());
		    	   
		    	   HoteltableareaModel tableAreaObj=new HoteltableareaModel();
		    	   tableAreaObj.setAreaId(this.getAreaId());
		    	   hotelTableObj.setTableArea(tableAreaObj);
		    	   
		    	   BranchModel branchObj=new BranchModel();
		    	   branchObj.setBranchId(loginBean.getBranchId());
		    	   hotelTableObj.setBranch(branchObj);
		    	   
		    	   hotelTableObjList.add(hotelTableObj);
		    	    
		       }
		   }
		   
		   try {
	    	   boolean updateSuccess=commonListBO.updateTableLayout(tableNameList,hotelTableObjList);
	    	   this.fetchUnassignedHotelTableList();
	    	   RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Table layout updated."));

	       } catch (Exception e) {
	    	   
	    	   e.printStackTrace();
	    	   RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Something went wrong"));
	       }
		   
	}
	
}
