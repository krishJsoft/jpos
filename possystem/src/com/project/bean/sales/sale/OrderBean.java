package com.project.bean.sales.sale;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import com.project.bean.admin.HoteltableBean;
import com.project.bean.admin.PoscounterBean;
import com.project.bean.admin.ProductBean;
import com.project.bean.admin.ProductCategoryBean;
import com.project.bean.admin.SystemSettingBean;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.sale.sales.HoteltableModel;
import com.project.model.sale.sales.KitchenorderremarksbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Nov 2013
 * 
 */

@ManagedBean(name = "orderBean")
@SessionScoped
public class OrderBean {

	String action = "login";
	String orderAction="new";
	Configuration config = Configuration.getConfiguration();
	String projectHomeFile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	ICommonListBO commonListBO=objectMapController.getCommonListBO();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	List<HoteltableModel> tableList = new ArrayList<HoteltableModel>();
	List<HoteltableModel> activeTableList = new ArrayList<HoteltableModel>();
	List<SalesorderModel> activeSalesOrder = new ArrayList<SalesorderModel>();
	ISalesorderBO salesOrderBO = objectMapController.getSalesOrderBO();

	private boolean pollForSalesEnabled=false;
	SalesorderModel salesorder = new SalesorderModel();
	private int height;
	private int width;
	private int noOfColumns;
	private String cardNoView="tablelist";
	private String subContentView="categoryList";
	
	Map<Integer, Integer> kitchenOrderMapping = new HashMap<Integer, Integer>();
	
	HoteltableModel selectedHotelTable=new HoteltableModel();
	SalesorderModel selectedKitchenOrder=new SalesorderModel();
	
	public String getOrderAction() {
		return orderAction;
	}

	public void setOrderAction(String orderAction) {
		this.orderAction = orderAction;
	}

	public SalesorderModel getSelectedKitchenOrder() {
		return selectedKitchenOrder;
	}

	public void setSelectedKitchenOrder(SalesorderModel selectedKitchenOrder) {
		this.selectedKitchenOrder = selectedKitchenOrder;
	}

	public HoteltableModel getSelectedHotelTable() {
		return selectedHotelTable;
	}

	public void setSelectedHotelTable(HoteltableModel selectedHotelTable) {
		this.selectedHotelTable = selectedHotelTable;
	}

	public List<HoteltableModel> getTableList() {
		return tableList;
	}

	public void setTableList(List<HoteltableModel> tableList) {
		this.tableList = tableList;
	}
	
	public boolean isPollForSalesEnabled() {
		return pollForSalesEnabled;
	}

	public void setPollForSalesEnabled(boolean pollForSalesEnabled) {
		this.pollForSalesEnabled = pollForSalesEnabled;
	}

	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getNoOfColumns() {
		return noOfColumns;
	}

	public void setNoOfColumns(int noOfColumns) {
		this.noOfColumns = noOfColumns;
	}

	public String getCardNoView() {
		return cardNoView;
	}

	public void setCardNoView(String cardNoView) {
		this.cardNoView = cardNoView;
	}

	public String getSubContentView() {
		return subContentView;
	}

	public void setSubContentView(String subContentView) {
		this.subContentView = subContentView;
	}

	public Map<Integer, Integer> getKitchenOrderMapping() {
		return kitchenOrderMapping;
	}

	public void setKitchenOrderMapping(Map<Integer, Integer> kitchenOrderMapping) {
		this.kitchenOrderMapping = kitchenOrderMapping;
	}

	public void getActiveKitchenList() {
		try {
			Calendar startOfDay = Calendar.getInstance();
			startOfDay.set(Calendar.HOUR_OF_DAY, 0);
			startOfDay.set(Calendar.MINUTE, 0);
			startOfDay.set(Calendar.SECOND,0);
			
			Calendar endOfDay = Calendar.getInstance();
			endOfDay.set(Calendar.HOUR_OF_DAY, 23);
			endOfDay.set(Calendar.MINUTE, 59);
			endOfDay.set(Calendar.SECOND,59);

			activeSalesOrder=salesOrderBO.getKitchenorderList2(startOfDay.getTime(), endOfDay.getTime(), "1", loginBean.getBranch().getBranchId());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void resetTerminalOrder() {
		//getTableAll();
		HoteltableBean hoteltableBean = (HoteltableBean) BeanContext.getReference("hoteltableBean");
		hoteltableBean.fetchAllHotelTableArea();
		hoteltableBean.loadTerminalOrderTableList(hoteltableBean.getAreaId());;
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
		productBean.setProductCodeFilter(null);
		
		salesorder=null;
		selectedHotelTable=null;
		if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
			
		}
		else {	
			productBean.setCategoryName(null);
			productBean.initProduct();
			projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
			projectHome.setInitPage(projectHome.getTerminalOrderContentPage());

		}
	}
	
	public void getTableAll()
	{
			try
			{
				
				tableList=	commonListBO.getHoteltableList(loginBean.getBranch().getBranchId() , null);

			}
			catch(Exception e)
			{
				
			}
	}
	
	
	
	public String tableSelection() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				homeinfo.clearSessionObject();
			}
			
			action = projectHomeFile;	
			tableList.clear();
			//getTableAll();
			HoteltableBean hoteltableBean = (HoteltableBean) BeanContext.getReference("hoteltableBean");
			hoteltableBean.fetchAllHotelTableArea();
			hoteltableBean.loadTerminalOrderTableList(hoteltableBean.getAreaId());
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navHandler = context.getApplication().getNavigationHandler();		
			navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
			
			if(height>width) {
				this.setNoOfColumns(3);
			}else {
				this.setNoOfColumns(6);
			}
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			String userAgent = request.getHeader("user-agent");
			
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			posBean.getSalesorder().setCardNo(null);
		// this gave 0 price kithcen order list? assumption 	
				HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
				if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {

					projectHome.setPrevPage(projectHome.getContenttpage());
					List<String> pages=projectHome.getPageTracker();
					pages.add(projectHome.getPrevPage());
					
					projectHome.setPageTracker(pages);
					projectHome.setContentpage("/mobileVersionUI/sales/tablesSelection.xhtml");
					projectHome.setInitPage(projectHome.getContenttpage());
					homeinfo.menuPageRedirect();

				}else {
					

					
					//projectHome.setPosKitchenOrderPage("/mobileVersionUI/sales/tablesSelection.xhtml");
			//		this.setPollForSalesEnabled(true);
					//projectHome.setInitPage("/mobileVersionUI/sales/tablesSelection.xhtml");
					//RequestContext.getCurrentInstance().update("posKitchenOrderPanel");
					
				//	ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
				//	context2.redirect(context2.getRequestContextPath()+"/mobileVersionUI/sales/tablesSelection.xhtml");
					
					
//					projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
//					projectHome.setInitPage(projectHome.getTerminalOrderContentPage());

				}
				
	
			
			} catch (Exception e) {
				e.printStackTrace();
				action = "login";
			}
			return action;
		}
	
	public void menuorderBar(ActionEvent event)
	{
		try {
			
			HoteltableModel tableNo = (HoteltableModel) event.getComponent().getAttributes().get("tableNo");		
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
				
			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			productCategoryBean.loadAllValidCategory();
			action = projectHomeFile;
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			posBean.getSalesorder().setSalesOrderId(0);
			posBean.getSalesorder().setTableName(tableNo.getTableNo());	
			posBean.getSalesorder().setCardNo("TableOrder"+tableNo.getTableNo());
			
	
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	public void orderedTableView(ActionEvent event) {
		selectedHotelTable= (HoteltableModel) event.getComponent().getAttributes().get("productcat");
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		
		posBean.setTableName(selectedHotelTable.getTableNo());
		SystemSettingBean systemSettingBean = (SystemSettingBean) BeanContext.getReference("systemSettingBean");
		salesorder=null;
		if(systemSettingBean.getMyConfig().getSystemType().getHasTableNo() && systemSettingBean.getMyConfig().getSystemType().getHasCardNo()) {
			posBean.getKitchenOrders();
			salesorder=null;
		}else if(systemSettingBean.getMyConfig().getSystemType().getHasTableNo()) {
			posBean.setCardNo(selectedHotelTable.getTableNo().replaceAll("\\s+",""));
			posBean.getKitchenOrders();
			if(posBean.getKitchedOrder().size()>0) {
				this.salesorder=posBean.getKitchedOrder().get(0);	
			}
			
		}
		
		
	}
	
	
	
	public void menuorder(ActionEvent event)
	{
		try {
			HoteltableModel tableNo = (HoteltableModel) event.getComponent().getAttributes().get("productcat");		

			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				//homeinfo.clearSessionObject();
			}

			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
			SystemSettingBean systemSettingBean = (SystemSettingBean) BeanContext.getReference("systemSettingBean");

			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");

			action = projectHomeFile;	
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			posBean.getSalesorder().setTableName(tableNo.getTableNo());	
			posBean.setOrderstatus("1");
			
			//poscounterBean.getPoscounterList();			
			//mobile
	
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
				
			String userAgent = request.getHeader("user-agent");
			productCategoryBean.loadAllValidCategory();
			posBean.getSalesorder().setCardNo(null);
			if(systemSettingBean.getMyConfig().getSystemType().getHasTableNo() && systemSettingBean.getMyConfig().getSystemType().getHasCardNo()) {
				
				this.setCardNoView("tablelist");
				RequestContext context2 = RequestContext.getCurrentInstance();
				context2.execute("PF('cardNoDlg').show();");
				
			}else if(systemSettingBean.getMyConfig().getSystemType().getHasTableNo()){
				posBean.getSalesorder().setCardNo(tableNo.getTableNo().replaceAll("\\s+",""));
					
				if(userAgent.matches(".*Android.*")) {
					HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
					projectHome.setPrevPage(projectHome.getContenttpage());
					List<String> pages=projectHome.getPageTracker();
					pages.add(projectHome.getPrevPage());
					projectHome.setPageTracker(pages);
					
					projectHome.setContentpage("/mobileVersionUI/sales/categorySelection.xhtml");
					homeinfo.menuPageRedirect();
				}else {
						
					if(systemSettingBean.getMyConfig().getPaxNo()) {						
						RequestContext context2 = RequestContext.getCurrentInstance();
						context2.execute("PF('paxNoDlg').show();");
						
					}else {
						productCategoryBean.loadMainCategory();
						projectHome.setTerminalOrderContentPage("/terminalOrder/sales/categoryProductSelection.xhtml");	
					}
					
				}
			}
			this.selectedHotelTable=null;
			this.salesorder=null;

		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	public void redirectToCategory() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");		
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
		this.setOrderAction("new");
		
		SystemSettingBean systemSettingBean = (SystemSettingBean) BeanContext.getReference("systemSettingBean");

		
		if((posBean.getSalesorder().getCardNo()==null || posBean.getSalesorder().getCardNo().isEmpty()) && (!systemSettingBean.getMyConfig().getPaxNo())) {
			
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"messages").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Card No Required",null));
		}else {
			
			if(systemSettingBean.getMyConfig().getPaxNo()) {
				posBean.getSalesorder().setCardNo(posBean.getSalesorder().getTableName().replaceAll("\\s+",""));
			}
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			projectHome.setPrevPage(projectHome.getContenttpage());
			List<String> pages=projectHome.getPageTracker();
			pages.add(projectHome.getPrevPage());
			projectHome.setPageTracker(pages);
			
			String userAgent = request.getHeader("user-agent");
			if(userAgent.matches(".*Android.*")) {
				projectHome.setContentpage("/mobileVersionUI/sales/categorySelection.xhtml");
				homeinfo.menuPageRedirect();
			}else {
				
				ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
				productCategoryBean.loadMainCategory();
				projectHome.setTerminalOrderContentPage("/terminalOrder/sales/categoryProductSelection.xhtml");
				
			}
		}
		
		
	}
	
	public void editBarKitchenOrder(ActionEvent event) {
		
		
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
		//homeinfo.clearSessionObject();
		
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		HoteltableModel tableNo = (HoteltableModel) event.getComponent().getAttributes().get("tableNo");	
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		posBean.setTableName(tableNo.getTableName());
		posBean.setCardNo("Table"+tableNo.getTableNo());
		//posBean.setCardNo("TableOrder"+tableNo.getTableNo());
		
		posBean.getKitchenOrders();
		if(!posBean.getKitchedOrder().isEmpty()) {
			salesorder=posBean.getKitchedOrder().get(0);
			posBean.setSalesorder(salesorder);
			posBean.setSno(salesorder.getSalesorderbreakdowns().get(salesorder.getSalesorderbreakdowns().size()-1).getSno()+1);
			Map<Integer, BigDecimal> itemcountQty = new HashMap<Integer, BigDecimal>();
			Map<Integer, BigDecimal> priceCountQty = new HashMap<Integer, BigDecimal>();
			
			for(SalesorderbreakdownModel product:salesorder.getSalesorderbreakdowns())
			{		
				itemcountQty.put(product.getSno(), product.getQuantity());
				priceCountQty.put(product.getSno(), product.getUnitPrice());
			}
			
			posBean.setItemcountQty(itemcountQty);
			posBean.setPriceQty(priceCountQty);
			posBean.setSalesorderbreakdowns(salesorder.getSalesorderbreakdowns());
			posBean.setTotalAmount(salesorder.getTotalAmount());
		//	posBean.setOrderstatus("1");
			productCategoryBean.loadAllValidCategory();
		}
		
			
		
		
		
	}
	
	public void mobileViewKitchenOrderList(ActionEvent event) {
		menuorderaction(event);
		menuorderedit();
	}
	
	public void menuorderaction(ActionEvent event)
	{			
		salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");
		
		
	}
	
	
	
	
	public void menuorderedit()
	{			
		this.getKitchenOrderMapping().clear();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
		if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {

			//homeinfo.clearSessionObject();
		}
		//PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		
		PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String userAgent = request.getHeader("user-agent");
	
	
		action = projectHomeFile;	
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		posBean.setSalesorder(salesorder);	
		posBean.setSno(salesorder.getSalesorderbreakdowns().get(salesorder.getSalesorderbreakdowns().size()-1).getSno()+1);
		Map<Integer, BigDecimal> itemcountQty = new HashMap<Integer, BigDecimal>();
		
		
		for(SalesorderbreakdownModel product:salesorder.getSalesorderbreakdowns())
		{		
			itemcountQty.put(product.getSno(), product.getQuantity());
			if(!this.kitchenOrderMapping.containsKey(product.getProductId())) {
				this.getKitchenOrderMapping().put(product.getProductId(), 0);
			}
			if(product.getStatus().equalsIgnoreCase("ordered")) {
				this.getKitchenOrderMapping().put(product.getProductId(), this.getKitchenOrderMapping().get(product.getProductId()) +product.getQuantity().intValue());
			}
			else{
					this.getKitchenOrderMapping().put(product.getProductId(), this.getKitchenOrderMapping().get(product.getProductId()) -1);	
			}
			
		}
		
		posBean.setItemcountQty(itemcountQty);
		posBean.setSalesorderbreakdowns(salesorder.getSalesorderbreakdowns());
		
		posBean.setTotalAmount(salesorder.getTotalAmount());
		
		posBean.setOrderstatus("1");
		this.setOrderAction("edit");
		productCategoryBean.loadCategory();		
					
		if(userAgent.matches(".*Android.*")) {
			projectHome.setPrevPage(projectHome.getContenttpage());
			List<String> pages=projectHome.getPageTracker();
			pages.add(projectHome.getPrevPage());
			projectHome.setPageTracker(pages);
			projectHome.setContentpage("/mobileVersionUI/sales/orderList.xhtml");
			homeinfo.menuPageRedirect();
		}else {
			productCategoryBean.loadMainCategory();
			projectHome.setTerminalOrderContentPage("/terminalOrder/sales/categoryProductSelection.xhtml");
			
		}
		
		
		
	}
	
	
	
	
	public void menuordereditupdate()
	{			
		
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
		homeinfo.clearSessionObject();	
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");

		posBean.setStatus("1");
		
			

		//posBean.getKitchenOrders();
		projectHome.setTitlePage("Sales --> Order List");
		projectHome.setContentpage("/home/waiterMaster.xhtml");
		action = projectHomeFile;					
		homeinfo.menuPageRedirect();
		
		
	}
	
	
	
	
	public void viewKitchenOrder(ActionEvent event) throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");	
			
			if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
			{
				salesorder=salesOrderBO.getBranchSalesItemDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType() );
			}
			else
			{
				salesorder=salesOrderBO.getBranchSalesorderDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType());
			}
			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}	
	
	public void togglePollForSales() {
	
		if(this.isPollForSalesEnabled()) {
			this.setPollForSalesEnabled(false);
		}else {
			this.setPollForSalesEnabled(true);
		}
	}

	public void loadSubItems() {
		try {
				ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
				PosBean posBean = (PosBean) BeanContext.getReference("posBean");
				List<ProductcategoryModel> catlist=productCategoryBean.loadCategoryChildList(posBean.getCatId());
				
				

				if(catlist.isEmpty()) {
					
					for(ProductcategoryModel cat:productCategoryBean.getProductcategoriesList()) {
						
						if(cat.getCategoryId()==posBean.getCatId()) {
							productCategoryBean.setChildCategoryList(catlist);
						}
					}
					this.setSubContentView("productList");
				}else {
					productCategoryBean.setChildCategoryList(catlist);
					this.setSubContentView("categoryList");
				}
				
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}

	public void selectProduct(ProductModel product) {
		
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		posBean.salesSetItemList.clear();
		if(product.isSetItem()) {
			product.getProductSetList().size();
			
			List<SalesorderbreakdownModel> salesSetItemList=new ArrayList<SalesorderbreakdownModel>();
			for(ProductsetModel setItem:product.getProductSetList()) {
				SalesorderbreakdownModel salesSetItem=new SalesorderbreakdownModel();
				
				List<KitchenorderremarksbreakdownModel> remarksObjList=new ArrayList<KitchenorderremarksbreakdownModel>();
				KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
				
				ItemRemarksListModel remark=new ItemRemarksListModel();
				remark.setRemarksListID(1);
				remark.setRemarksName("normal");
				obj.setRemarks(remark);
				remarksObjList.add(obj);
				salesSetItem.setKitchenorderremarksbreakdownModel(remarksObjList);
				
				salesSetItem.setProduct(setItem.getProduct());
				salesSetItem.setProductName(setItem.getProduct().getProductName());
				salesSetItem.setProductId(setItem.getProduct().getProductId());
				salesSetItem.setUnitPrice(BigDecimal.ZERO);
				salesSetItem.setTaxAmount(BigDecimal.ZERO);
				salesSetItem.setSubTotal(BigDecimal.ZERO);
				salesSetItem.setQuantity(BigDecimal.ONE);
				salesSetItem.setSetOption(1);
				
				salesSetItemList.add(salesSetItem);
			}
			//posBean.setSalesSetItemList(salesSetItemList);
			addOrderItem(product);
			posBean.salesorderbreakdowns.get(0).setSalesProductSetList(salesSetItemList);
			posBean.salesorderbreakdowns.get(0).setSetOption(1);
			
		}else {
			addOrderItem(product);
			//posBean.salesorderbreakdowns.get(posBean.getSalesorderbreakdowns().size()-1).setSetOption(0);
		}
	}
	
	public void loadItemRemarks(SalesorderbreakdownModel sobd) {
		
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		posBean.item=sobd;
		posBean.setCatId(sobd.getProduct().getCategoryId());
		
		posBean.loadRemarksList();
		posBean.setRowId(posBean.salesorderbreakdowns.indexOf(sobd));
		KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
		
		Integer[] remark = new Integer[sobd.getKitchenorderremarksbreakdownModel().size()];
		int i=0;
		for(KitchenorderremarksbreakdownModel data:sobd.getKitchenorderremarksbreakdownModel()) {
			remark[i++]=data.getRemarks().getRemarksListID();
		}
		posBean.setSelectedRemarksId(remark);
		
		if(sobd.getPacking()==0) {
			posBean.setPacking(false);
		}else {
			posBean.setPacking(true);	
		}
	}
	
	public void saveRemarksToItem() {
		
		ItemRemarksListModel remarksObj=new ItemRemarksListModel();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		remarksObj.setRemarksListID(posBean.getRemarksId());
		List<KitchenorderremarksbreakdownModel> remarksList=new ArrayList<KitchenorderremarksbreakdownModel>();
		
		for(Integer remarkId:posBean.getSelectedRemarksId()) {
			KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
			
			ItemRemarksListModel remark=new ItemRemarksListModel();
			remark.setRemarksListID(remarkId);
			
			remark.setRemarksName(""+posBean.getRemarksListMap().get(remarkId));
			obj.setRemarks(remark);
			
			remarksList.add(obj);	
		}
		
		posBean.item.setKitchenorderremarksbreakdownModel(remarksList);
		
		posBean.setSelectedRemarksId(null);
		
		if(posBean.isPacking()) {
			posBean.item.setPacking(1);
			posBean.item.setUnitPrice(posBean.item.getProduct().getPackingPrice());
		}else {
			posBean.item.setPacking(0);
		}
		
		posBean.item.setRemarks(remarksObj);
		posBean.addSalesPriceModifyItemV2(posBean.item);
		
	}
	
	public void loadSetItemRemarks(SalesorderbreakdownModel parentItem,SalesorderbreakdownModel item) {
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		//posBean.item=sobd;
		posBean.setCatId(item.getProduct().getCategoryId());
		
		posBean.loadRemarksList();
		//posBean.setRowId(posBean.salesorderbreakdowns.indexOf(sobd));
		
		KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
		
		Integer[] remark = new Integer[item.getKitchenorderremarksbreakdownModel().size()];
		int i=0;
		for(KitchenorderremarksbreakdownModel data:item.getKitchenorderremarksbreakdownModel()) {
			remark[i++]=data.getRemarks().getRemarksListID();
		}
		posBean.setSelectedRemarksId(remark);
		
		if(item.getPacking()==0) {
			posBean.setPacking(false);
		}else {
			posBean.setPacking(true);	
		}
	}
	
	public void saveSetItemRemarks() {
		
		ItemRemarksListModel remarksObj=new ItemRemarksListModel();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		remarksObj.setRemarksListID(posBean.getRemarksId());
		List<KitchenorderremarksbreakdownModel> remarksList=new ArrayList<KitchenorderremarksbreakdownModel>();
		
		for(Integer remarkId:posBean.getSelectedRemarksId()) {
			KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
			
			ItemRemarksListModel remark=new ItemRemarksListModel();
			remark.setRemarksListID(remarkId);
			
			remark.setRemarksName(""+posBean.getRemarksListMap().get(remarkId));
			obj.setRemarks(remark);
			
			remarksList.add(obj);	
		}
		
		posBean.item.setKitchenorderremarksbreakdownModel(remarksList);
		
		posBean.setSelectedRemarksId(null);
		
		if(posBean.isPacking()) {
			posBean.item.setPacking(1);
			posBean.item.setUnitPrice(posBean.item.getProduct().getPackingPrice());
		}else {
			posBean.item.setPacking(0);
		}
		
		posBean.item.setRemarks(remarksObj);
		posBean.addSalesPriceModifyItemV2(posBean.item);
		
	}
	
	public void addOrderItem(ProductModel product) {
		
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		
		posBean.setProduct(product);
		posBean.setBarcode(product.getBarcode());
		posBean.setItemCount(new BigDecimal(0.00));
		
		posBean.setSelectedItemQty(1);
		posBean.setRemarksId(1);
		posBean.setPacking(false);
		posBean.addSalesItem();
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Item added to order") );
	}
	
	public void cancelOrderedItem(SalesorderbreakdownModel orderedItem) {
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		
		ProductModel product=new ProductModel();
		product.setProductId(orderedItem.getProductId());
		posBean.setProduct(product);
		posBean.setBarcode(orderedItem.getProductCode());
		posBean.setItemCount(new BigDecimal(0.00));
		
		posBean.setSelectedItemQty(1);
		posBean.setRemarksId(1);
		if(orderedItem.getPacking()!=0) {
			posBean.setPacking(true);
		}else {
			posBean.setPacking(false);
		}
		
		posBean.setItemOrderStatus("cancel");
		
		if(orderedItem.getSetOption()==1) {
			List<SalesorderbreakdownModel> salesSetItemList=new ArrayList<SalesorderbreakdownModel>();
			for(SalesorderbreakdownModel setItem:orderedItem.getSalesProductSetList()) {
				
				setItem.setStatus("cancel");
				
				salesSetItemList.add(setItem);
				
			}
			posBean.addSalesItem();
			posBean.salesorderbreakdowns.get(0).setStatus("cancel");
			posBean.salesorderbreakdowns.get(0).setSalesProductSetList(salesSetItemList);
			posBean.salesorderbreakdowns.get(0).setSetOption(1);
		}else {
			posBean.addSalesItem();	
		}
		
		
		
		posBean.setItemOrderStatus("order");
		this.getKitchenOrderMapping().put(product.getProductId(), this.getKitchenOrderMapping().get(product.getProductId())-1);
		
	}
}
