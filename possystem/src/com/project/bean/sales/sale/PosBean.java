package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bean.admin.CommonListBean;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.admin.PoscounterBeanInfo;
import com.project.bean.admin.ProductBean;
import com.project.bean.admin.ProductCategoryBean;
import com.project.bean.websocket.CustomerdisplayClientEndpoint;
import com.project.bean.websocket.CustomerdisplayMessageModel;
import com.project.bean.websocket.SalesbreakdowndisplayModel;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IItemRemarksBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.CommonUtil;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;
import com.project.model.datamodel.AutonumModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ItemRemarksFunctionListModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.datamodel.purchase.PurchaseorderConsolidateModel;
import com.project.model.his.Itemremarkslist;
import com.project.model.paginghelper.PosSalesSorder;
import com.project.model.paginghelper.QuotationLazySorter;

import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.KitchenorderremarksbreakdownModel;
import com.project.model.sale.sales.PaymentCollectionModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.ConvertUtil;
import com.project.util.DateUtil;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Nov 2013
 * 
 */

@ManagedBean(name = "posBean")
@SessionScoped
public class PosBean {

	SalesorderModel salesorder = new SalesorderModel();
	SalesorderModel kitchenOrder = new SalesorderModel();


	SalesorderModel salesorderSplit = new SalesorderModel();
	List<Integer> salesId=new ArrayList<Integer>();
	private String salesType = "3";
	List<SalesorderbreakdownModel> salesorderbreakdowns = new ArrayList<SalesorderbreakdownModel>();
	List<SalesorderbreakdownModel> salesSetItemList=new ArrayList<SalesorderbreakdownModel>();
	List<Integer> splitbreakdownids=new ArrayList<Integer>();
	List<SalesorderbreakdownModel> salesitembreakdowns = new ArrayList<SalesorderbreakdownModel>();
	List<SalesorderbreakdownModel> remarksItemBreakdowns=new ArrayList<SalesorderbreakdownModel>();
	List<SalesorderModel> salesOrderList = new ArrayList<SalesorderModel>();
	List<SalesorderbreakdownModel> salesordersplitbreakdowns = new ArrayList<SalesorderbreakdownModel>();
	
	private Integer remarksId;
	private Integer subItemId;
	private Integer categoryId;
	private Integer discountRateId;
	private Integer discountRemarkId;
	private Integer discountRate;
	private String discountRemarks;
	private BigDecimal totalBeforeDiscount;
	private Integer[] selectedRemarksId;
	
	private List<SelectItem> remarksList;
	private boolean isPacking;
	private boolean viewSubList=false;
	private Integer subListId;
	public String cardOption;
	public Integer cardSplitInc;
	private String currentTableName;
	private HashMap remarksListMap=new HashMap();

	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext
			.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<SalesorderModel> salesorderModel = null;
	private LazyDataModel<SalesorderbreakdownModel> salesorderbreakdownModel = null;
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext
			.getReference("objectMapController");
	PoscounterModel poscounter = new PoscounterModel();
	IPoscounterBO poscounterBO = objectMapController.getPoscounterBO();
	IItemRemarksBO itemRemarksBO=objectMapController.getItemRemarksBO();
	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();

	List<SalesorderModel> kitchedOrder = new ArrayList<SalesorderModel>();
	SalesorderModel kitchenOrderBeforeSales=new SalesorderModel();


	String projectgst = config.getValue(IConfiguration.PRODUCT_GST_AMOUNT);
	String projectdefaultgst = config.getValue(IConfiguration.PRODUCT_DEFAULTGST_AMOUNT);

	String projectregno = config.getValue(IConfiguration.PRODUCT_REG_NUMBER);
	public List<Integer> getSalesId() {
		return salesId;
	}

	public void setSalesId(List<Integer> salesId) {
		this.salesId = salesId;
	}

	String projectgsnno = config.getValue(IConfiguration.PRODUCT_GST_NUMBER);
	
	BigDecimal unitpricedeductionAmt = new BigDecimal(projectgst);
	BigDecimal unitpricetaxAmt = new BigDecimal(projectdefaultgst);
	private String salesOrderNo;
	private Integer salesOrderId;
	private Integer catId;
	
	private String posOption="sales";
	private String orderstatus;
	private String itemOrderStatus;

	int sno = 0;
	private Integer branchId;
	private Integer customerId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private CustomerModel customer;
	private String barcode;
	private BigDecimal itemPrice;
	private String CardMessageInUse;
	ProductModel product = new ProductModel();
	BigDecimal itemCount = new BigDecimal(1.00);
	private BigDecimal totalAmount = new BigDecimal(0.00);
	private BigDecimal totalDiscountAmount;
	private BigDecimal grandTotalAmount;
	private BigDecimal totalTaxAmount = new BigDecimal(0.00);
	boolean paymentConfirm = false;
	private Integer rowId;
	private int setOption;
	private String applet = "";
	private String prescptNo;
	DoctorsPrescriptionsModel dpModel = new DoctorsPrescriptionsModel();
	Date date;
	String cardNo;
	String tableName;
	String cardNoList;
	String tableNameList;
	SalesorderbreakdownModel item = new SalesorderbreakdownModel();

	private BigDecimal receivedAmount = new BigDecimal(0.0);
	private BigDecimal balanceAmount = new BigDecimal(0.0);
	PaymentCollectionModel paymentModel = new PaymentCollectionModel();

	List<PaymentCollectionModel> paymentCollectionModel = new ArrayList<PaymentCollectionModel>();
	private Map<String, Integer> amountCount = new HashMap<String, Integer>();
	private Map<Integer, BigDecimal> priceQty = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> itemcountQty = new HashMap<Integer, BigDecimal>();

	ISalesorderBO salesOrderBO = objectMapController.getSalesOrderBO();
	ICustomerBO customerBO = objectMapController.getCustomerBO();
	IProductBO productBO = objectMapController.getProductBO();

	private String filterProductName;
	
	private int selectedItemQty; 
	
	public SalesorderModel getKitchenOrderBeforeSales() {
		return kitchenOrderBeforeSales;
	}

	public void setKitchenOrderBeforeSales(SalesorderModel kitchenOrderBeforeSales) {
		this.kitchenOrderBeforeSales = kitchenOrderBeforeSales;
	}
	public boolean isViewSubList() {
		return viewSubList;
		
	}

	public void setViewSubList(boolean viewSubList) {
		this.viewSubList = viewSubList;
		
	}

	public Integer getSubListId() {
		return subListId;
		
	}

	public void setSubListId(Integer subListId) {
		this.subListId = subListId;
	}

	public String getCardOption() {
		return cardOption;
	}

	public void setCardOption(String cardOption) {
		this.cardOption = cardOption;
	}

	public Integer getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(Integer remarksId) {
		this.remarksId = remarksId;
	}

	public boolean isPacking() {
		return isPacking;
	}

	public void setPacking(boolean isPacking) {
		this.isPacking = isPacking;
	}

	public Integer getSubItemId() {
		return subItemId;
	}

	public void setSubItemId(Integer subItemId) {
		this.subItemId = subItemId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDiscountRemarkId() {
		return discountRemarkId;
	}

	public void setDiscountRemarkId(Integer discountRemarkId) {
		this.discountRemarkId = discountRemarkId;
	}
	
	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public String getDiscountRemarks() {
		return discountRemarks;
	}

	public void setDiscountRemarks(String discountRemarks) {
		this.discountRemarks = discountRemarks;
	}

	public Integer getDiscountRateId() {
		return discountRateId;
	}

	public void setDiscountRateId(Integer discountRateId) {
		this.discountRateId = discountRateId;
	}


	public BigDecimal getTotalBeforeDiscount() {
		return totalBeforeDiscount;
	}

	public void setTotalBeforeDiscount(BigDecimal totalBeforeDiscount) {
		this.totalBeforeDiscount = totalBeforeDiscount;
	}

	public Integer[] getSelectedRemarksId() {
		return selectedRemarksId;
	}

	public void setSelectedRemarksId(Integer[] selectedRemarksId) {
		this.selectedRemarksId = selectedRemarksId;
	}

	public List<SelectItem> getRemarksList() {
		return remarksList;
	}

	public void setRemarksList(List<SelectItem> remarksList) {
		this.remarksList = remarksList;
	}

	
	public int getSetOption() {
		return setOption;
	}

	public void setSetOption(int setOption) {
		this.setOption = setOption;
	}

	public Integer getCardSplitInc() {
		return cardSplitInc;
	}

	public void setCardSplitInc(Integer cardSplitInc) {
		this.cardSplitInc = cardSplitInc;
	}

	public String getCurrentTableName() {
		return currentTableName;
	}

	public void setCurrentTableName(String currentTableName) {
		this.currentTableName = currentTableName;
	}

	public HashMap getRemarksListMap() {
		return remarksListMap;
	}

	public void setRemarksListMap(HashMap remarksListMap) {
		this.remarksListMap = remarksListMap;
	}

	public int getSelectedItemQty() {
		return selectedItemQty;
	}

	public void setSelectedItemQty(int selectedItemQty) {
		this.selectedItemQty = selectedItemQty;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	

	public String getItemOrderStatus() {
		return itemOrderStatus;
	}

	public void setItemOrderStatus(String itemOrderStatus) {
		this.itemOrderStatus = itemOrderStatus;
	}

	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}



	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	public SalesorderModel getKitchenOrder() {
		return kitchenOrder;
	}

	public void setKitchenOrder(SalesorderModel kitchenOrder) {
		this.kitchenOrder = kitchenOrder;
	}

	public SalesorderModel getSalesorderSplit() {
		return salesorderSplit;
	}

	public void setSalesorderSplit(SalesorderModel salesorderSplit) {
		this.salesorderSplit = salesorderSplit;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Map<Integer, BigDecimal> getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(Map<Integer, BigDecimal> priceQty) {
		this.priceQty = priceQty;
	}

	public Map<Integer, BigDecimal> getItemcountQty() {
		return itemcountQty;
	}

	public void setItemcountQty(Map<Integer, BigDecimal> itemcountQty) {
		
		this.itemcountQty = itemcountQty;
	}

	public PoscounterModel getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(PoscounterModel poscounter) {
		this.poscounter = poscounter;
	}

	public IPoscounterBO getPoscounterBO() {
		return poscounterBO;
	}

	public void setPoscounterBO(IPoscounterBO poscounterBO) {
		this.poscounterBO = poscounterBO;
	}

	public BigDecimal getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getPrescptNo() {
		return prescptNo;
	}

	public void setPrescptNo(String prescptNo) {
		this.prescptNo = prescptNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<SalesorderbreakdownModel> getSalesorderbreakdowns() {
		return salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<SalesorderbreakdownModel> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public List<SalesorderbreakdownModel> getSalesSetItemList() {
		return salesSetItemList;
	}

	public void setSalesSetItemList(List<SalesorderbreakdownModel> salesSetItemList) {
		this.salesSetItemList = salesSetItemList;
	}

	public List<SalesorderbreakdownModel> getSalesitembreakdowns() {
		return salesitembreakdowns;
	}

	public void setSalesitembreakdowns(
			List<SalesorderbreakdownModel> salesitembreakdowns) {
		this.salesitembreakdowns = salesitembreakdowns;
	}

	public LazyDataModel<SalesorderModel> getSalesorderModel() {
		return salesorderModel;
	}

	public void setSalesorderModel(
			LazyDataModel<SalesorderModel> salesorderModel) {
		this.salesorderModel = salesorderModel;
	}

	public LazyDataModel<SalesorderbreakdownModel> getSalesorderbreakdownModel() {
		return salesorderbreakdownModel;
	}

	public void setSalesorderbreakdownModel(
			LazyDataModel<SalesorderbreakdownModel> salesorderbreakdownModel) {
		this.salesorderbreakdownModel = salesorderbreakdownModel;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public Map<String, Integer> getAmountCount() {
		return amountCount;
	}

	public void setAmountCount(Map<String, Integer> amountCount) {
		this.amountCount = amountCount;
	}

	public List<SalesorderbreakdownModel> getRemarksItemBreakdowns() {
		return remarksItemBreakdowns;
	}

	public void setRemarksItemBreakdowns(List<SalesorderbreakdownModel> remarksItemBreakdowns) {
		this.remarksItemBreakdowns = remarksItemBreakdowns;
	}

	public BigDecimal getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public BigDecimal getGrandTotalAmount() {
		return grandTotalAmount;
	}

	public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
		this.grandTotalAmount = grandTotalAmount;
	}

	public boolean isPaymentConfirm() {
		return paymentConfirm;
	}

	public void setPaymentConfirm(boolean paymentConfirm) {
		this.paymentConfirm = paymentConfirm;
	}

	public SalesorderbreakdownModel getItem() {
		return item;
	}

	public void setItem(SalesorderbreakdownModel item) {
		this.item = item;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public List<Integer> getSplitbreakdownids() {
		return splitbreakdownids;
	}

	public void setSplitbreakdownids(List<Integer> splitbreakdownids) {
		this.splitbreakdownids = splitbreakdownids;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public List<SalesorderModel> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesorderModel> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public List<SalesorderbreakdownModel> getSalesordersplitbreakdowns() {
		return salesordersplitbreakdowns;
	}

	public void setSalesordersplitbreakdowns(List<SalesorderbreakdownModel> salesordersplitbreakdowns) {
		this.salesordersplitbreakdowns = salesordersplitbreakdowns;
	}

	public DoctorsPrescriptionsModel getDpModel() {
		return dpModel;
	}

	public void setDpModel(DoctorsPrescriptionsModel dpModel) {
		this.dpModel = dpModel;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public List<PaymentCollectionModel> getPaymentCollectionModel() {
		return paymentCollectionModel;
	}

	public void setPaymentCollectionModel(
			List<PaymentCollectionModel> paymentCollectionModel) {
		this.paymentCollectionModel = paymentCollectionModel;
	}

	public PaymentCollectionModel getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(PaymentCollectionModel paymentModel) {
		this.paymentModel = paymentModel;
	}

	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getPosOption() {
		return posOption;
	}

	public void setPosOption(String posOption) {
		this.posOption = posOption;
	}

	public List<SalesorderModel> getKitchedOrder() {
		return kitchedOrder;
	}

	public void setKitchedOrder(List<SalesorderModel> kitchedOrder) {
		this.kitchedOrder = kitchedOrder;
	}

	public String getCardMessageInUse() {
		return CardMessageInUse;
	}

	public void setCardMessageInUse(String cardMessageInUse) {
		CardMessageInUse = cardMessageInUse;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardNoList() {
		return cardNoList;
	}

	public void setCardNoList(String cardNoList) {
		this.cardNoList = cardNoList;
	}

	public String getTableNameList() {
		return tableNameList;
	}

	public void setTableNameList(String tableNameList) {
		this.tableNameList = tableNameList;
	}

	public void changeCardOption(ActionEvent event) {
		String cardOption=(String) event.getComponent().getAttributes()
				.get("cardOption");
		this.resetPos();
		this.setCurrentTableName("");
		this.setCardOption(cardOption);
		this.getSalesorderSplit().setCardNo("");
		this.getSalesorderSplit().setTableName("");
		this.getSalesorderSplit().setSplitInc("");
		this.getSalesordersplitbreakdowns().clear();
		
	}
	
//mobile
	public void mobileOrderList() {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			String userAgent = request.getHeader("user-agent");
			

			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			

			ProjectHome projectHome = (ProjectHome) BeanContext
					.getReference("projectHome");
			
			projectHome.setTitlePage("Sales --> Product Selection");
			projectHome.setPrevPage(projectHome.getContenttpage());
			List<String> pages=projectHome.getPageTracker();
			pages.add(projectHome.getPrevPage());
			projectHome.setPageTracker(pages);
			projectHome.setContentpage("/mobileVersionUI/sales/orderList.xhtml");
			homeinfo.menuPageRedirect();	
			
			
			this.setViewSubList(false);
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	
	public void mobileGetKitchenOrders() {
		getKitchenOrders();
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
		
		projectHome.setPrevPage(projectHome.getContenttpage());
		List<String> pages=projectHome.getPageTracker();
		pages.add(projectHome.getPrevPage());
		projectHome.setPageTracker(pages);
		projectHome.setContentpage("/mobileVersionUI/sales/viewKitchenOrder.xhtml");
		projectHome.setInitPage(projectHome.getContenttpage());
		homeinfo.menuPageRedirect();
		
	}
	
	public void getKitchenOrders() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Date dateNow = new Date();
		SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
		String nowYYYYMMDD = new String(dateformatDDMMYYYY.format(dateNow));

		Date startDate = DateUtil.getFirstDate();
		startDate = DateUtil.addDaysToDate(-10, startDate);

		this.setDateFrom(startDate);

		Date endDate = DateUtil.getToTodayDateTime();
		endDate = DateUtil.addDaysToDate(1, endDate);

		this.setDateTo(endDate);

		
		try {
			/*kitchedOrder = salesOrderBO.getKitchenorderReportList(tableName, cardNo,
					salesOrderNo, loginBean.getUserName(), customerId,
					branchId, dateFrom, dateTo, status, loginBean.getBranch()
							.getBranchId());
							*/
			this.setStatus("1");
			kitchedOrder = salesOrderBO.getKitchenorderReportList(tableName, cardNo,
					salesOrderNo, null, customerId,
					branchId, dateFrom, dateTo, status, loginBean.getBranch()
							.getBranchId());
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	public void getKitchenOrderFromTerminal() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Date dateNow = new Date();
		SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
		String nowYYYYMMDD = new String(dateformatDDMMYYYY.format(dateNow));

		Date startDate = DateUtil.getFirstDate();
		startDate = DateUtil.addDaysToDate(-10, startDate);

		this.setDateFrom(startDate);

		Date endDate = DateUtil.getToTodayDateTime();
		endDate = DateUtil.addDaysToDate(1, endDate);

		this.setDateTo(endDate);

		
		try {
			/*kitchedOrder = salesOrderBO.getKitchenorderReportList(tableName, cardNo,
					salesOrderNo, loginBean.getUserName(), customerId,
					branchId, dateFrom, dateTo, status, loginBean.getBranch()
							.getBranchId());
							*/
			this.setStatus("1");
			kitchedOrder = salesOrderBO.getKitchenorderReportList(tableName, cardNo,
					salesOrderNo, null, customerId,
					branchId, dateFrom, dateTo, status, loginBean.getBranch()
							.getBranchId());
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	
	public void getKitchenOrderReport() {
		FacesContext context = FacesContext.getCurrentInstance();

		Date dateNow = new Date();
		SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
		String nowYYYYMMDD = new String(dateformatDDMMYYYY.format(dateNow));

		Date startDate = DateUtil.getFirstDate();
		startDate = DateUtil.addDaysToDate(-10, startDate);

		this.setDateFrom(startDate);

		Date endDate = DateUtil.getToTodayDateTime();
		endDate = DateUtil.addDaysToDate(1, endDate);

		this.setDateTo(endDate);

		try {
			kitchedOrder = salesOrderBO.getKitchenorderReportList(tableName, cardNo,
					salesOrderNo,null, customerId,
					branchId, dateFrom, dateTo, status, loginBean.getBranch()
							.getBranchId());
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	
	
	
	public String getApplet() {
		applet = "<applet id=\"appletVisor\" z.autohide=\"true\" name=\"appletVisor\"  ARCHIVE = \"jasperreports-applet-4.0.2.jar,commons-collections-2.1.1.jar,commons-logging-1.0.4.jar,printingapplet.jar\" width=\"80%\" height=\"40 \" CODE = \"PrinterApplet.class\"  CODEBASE=\"applets\" MAYSCRIPT></applet>";
		return applet;
	}

	public void setApplet(String applet) {
		this.applet = applet;
	}

	public String getFilterProductName() {
		return filterProductName;
	}

	public void setFilterProductName(String filterProductName) {
		this.filterProductName = filterProductName;
	}

	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void handleSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			CommonListBeanInfo commoninfo = new CommonListBeanInfo();
			// commoninfo.getAllSupplierListByProduct(this.getProduct().getBarcode());
			customer = customerBO.getCustomerDetails(this.getCustomer()
					.getCustomerId());
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	void addKitchenItemToSalesSplit(SalesorderbreakdownModel data) {
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		ProductModel producttemp = new ProductModel();
		

		try{
			item=data;
			
			List<ProductModel> productList = new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, data.getProductCode(), null, 0, 1, loginBean.getBranch()
					.getBranchId(), null, null, null, null);
			producttemp = productList.get(0);

			item.setProduct(producttemp);
			item.setProductId(producttemp.getProductId());
			item.setProductName(producttemp.getProductName());
			
			BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
			
			BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
			if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
			{
				priceAmt=priceAmt.add(taxAmt);
			}
			
			item.setTaxAmount(taxAmt);
			BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
			
			BigDecimal tmpSubTotal = extractSubtotal(
					item.getUnitPrice(),
					item.getDiscountAmount(),
					item.getQuantity(),
					producttemp.getTaxCode());
			tmpSubTotal = priceAmt; // new
			item.setSubTotal(tmpSubTotal);
			this.getSalesordersplitbreakdowns().add(item);
			
			resetAddItem();
	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	void addKitchenItemToSales(SalesorderbreakdownModel data) {
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		try{
			item=data;

			BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
			
			item.setSalesOrderBreakdownId(data.getSalesOrderBreakdownId());
			item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));
			item.setSno(sno);
			item.setTaxAmount(DecimalUtil.formatRoundupCents(item.getTaxAmount().abs()));
			item.setSubTotal(DecimalUtil.formatRoundupCents(data.getSubTotal().abs()));
			
			if(item.getStatus()!=null && (item.getStatus().equalsIgnoreCase("canceled") || item.getStatus().equalsIgnoreCase("refunded"))) {
				item.setTaxAmount(item.getTaxAmount().negate());
				item.setSubTotal(item.getSubTotal().negate());
			}
			
			this.priceQty.put(sno,item.getUnitPrice());
			this.itemcountQty.put(sno,item.getQuantity());
			
			this.getDynamicSalesItemList(item);
		
			sno = sno + 1;
	
			extractQuoteTotal();
			Collections.sort(salesorderbreakdowns, new PosSalesSorder());
			resetAddItem();
			
			sendToDisplay("ADD SALES ITEM",this.getSalesorderbreakdowns());

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
	}


	//moo2
	public void addSalesItem() {
		
		
		
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		//this.setItemCount(new BigDecimal(""+selectedItemQty));
		if(this.getItemCount().compareTo(BigDecimal.ZERO) == 0)
			this.setItemCount(new BigDecimal(1.00));
		boolean exits = false;
		try {

			if (this.getBarcode() != null
					&& (!this.getBarcode().equalsIgnoreCase(""))
					&& this.getBarcode().length() > 0) {

				List<ProductModel> productList = new ArrayList<ProductModel>();
				productList = productBO.getProductList(null, null, null, this.getBarcode(), null, 0, 1, loginBean.getBranch()
						.getBranchId(), null, null, null, null);

				if (productList.size() == 0) {
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null,	"Barcode not Exists"));
				} else {
					producttemp = productList.get(0);
					productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(), loginBean.getBranch().getBranchId());

					if (productPriceList != null && productPriceList.size() == 1) {
						productPriceList.add(productPriceList.get(0));
					}
					
//moo
					item.setProduct(producttemp);
					item.setProductId(producttemp.getProductId());
					item.setProductName(producttemp.getProductName());
					
					item.setProductCode(this.getBarcode());
					item.setUom(producttemp.getUom());
					item.setExpiryDate(producttemp.getExpiryDate());
					
					item.setPacking(0);
					if(this.getPosOption().equalsIgnoreCase("take-away")) {
						this.setPacking(true);
					}
					
					if(!this.getSalesSetItemList().isEmpty()) {
						System.out.println(this.getSalesSetItemList().size());
						//item.setSalesProductSetList(this.getSalesSetItemList());
					}
					// Briyani set
					/*if(item.getProduct().getProductId()==574 || item.getProduct().getProductId()==575
							|| item.getProduct().getProductId()==837 || item.getProduct().getProductId()==838 || 
							item.getProduct().getProductId()==839 || item.getProduct().getProductId() == 850 ||
							item.getProduct().getProductId()==851 || item.getProduct().getProductId()==852 ||
							item.getProduct().getProductId()==853
							) {
						item.setSetOption(1);
					}else {
						
					}
					*/
					item.setSetOption(0);
					
				//	if(( item.getProductName().equalsIgnoreCase("FOODS") || item.getProductName().equalsIgnoreCase("DRINKS"))&& this.itemPrice!=null) {
						//item.setUnitPrice(this.getItemPrice());
						
				//	}
				//	else {
						if(this.isPacking()) {
							item.setUnitPrice(producttemp.getPackingPrice());
							item.setPacking(1);
						}else {
							item.setUnitPrice(producttemp.getNormalPrice());
							item.setPacking(0);
						}
					//}
					
						
						
					//this.priceQty.put(sno,producttemp.getNormalPrice());
					
					item.setDiscount("" + producttemp.getDiscount());
					
					item.setQuantity(this.getItemCount());
					this.itemcountQty.put(sno,item.getQuantity());
					
					item.setTaxCode(producttemp.getTaxCode());
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
					
					
					BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					
					taxAmt= DecimalUtil.formatRoundupCents(taxAmt);
					
					ItemRemarksListModel remarksObj=new ItemRemarksListModel();
					if(this.remarksId>1) {
						remarksObj.setRemarksListID(this.getRemarksId());
					}else {
						remarksObj.setRemarksListID(1);
					}
					
					List<KitchenorderremarksbreakdownModel> remarksObjList=new ArrayList<KitchenorderremarksbreakdownModel>();
					KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
					
					ItemRemarksListModel remark=new ItemRemarksListModel();
					remark.setRemarksListID(1);
					remark.setRemarksName("normal");
					obj.setRemarks(remark);
					
					remarksObjList.add(obj);
					
					item.setKitchenorderremarksbreakdownModel(remarksObjList);
					
					
					item.setRemarks(remarksObj);
					/*if(item.getRemarks().getRemarksListID()==0) {
						remarksObj.setRemarksListID(1);
						item.setRemarks(remarksObj);
					}
					
					*/

					item.setQuantityBefore(item.getQuantity());
					if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
					{
						priceAmt=priceAmt.add(taxAmt);

					}
					else
					{
						
						//item.setUnitPrice(item.getUnitPrice().subtract(taxAmt));
						BigDecimal itemTax= producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
						item.setUnitPrice(item.getUnitPrice().subtract(itemTax));
						item.setUnitPrice(DecimalUtil.formatRoundupCents(item.getUnitPrice()));
			
					}
					
					if(this.getItemOrderStatus()!=null && (this.getItemOrderStatus().equalsIgnoreCase("cancel") || this.getItemOrderStatus().equalsIgnoreCase("refund"))) {
						item.setUnitPrice(item.getUnitPrice().negate());
						priceAmt=priceAmt.negate();
						taxAmt=taxAmt.negate();
						item.setStatus(this.getItemOrderStatus());						
					}
					this.priceQty.put(sno,item.getUnitPrice());

					item.setTaxAmount(taxAmt);
					
					BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
					item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));
					item.setSno(sno);
					item.setProduct(producttemp);

					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList
										.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList
										.get(j).getQuantityFrom();
								if (item.getQuantity().doubleValue() >= initVal
										.doubleValue()
										&& item.getQuantity().doubleValue() < nextVal
												.doubleValue()) {
									item.setDiscountAmount(productPriceList
											.get(i).getDiscountAmount());
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList
											.get(j).getDiscountAmount());
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(
									item.getUnitPrice(),
									item.getDiscountAmount(),
									item.getQuantity(),
									producttemp.getTaxCode());
							tmpSubTotal = priceAmt; // new
							item.setSubTotal(tmpSubTotal);
							
							ProductBean productBean = (ProductBean) BeanContext
									.getReference("productBean");
							for(ProductModel productObj:productBean.getProductAll()) {
								
								if(productObj.getProductId()==item.getProductId())
								{
									productObj.setStatus("1");
								}
								
								
							}
							
							this.getDynamicSalesItemList(item);
							sno = sno + 1;

						}
					}
				
					extractQuoteTotal();
					Collections.sort(salesorderbreakdowns, new PosSalesSorder());
					resetAddItem();
				}
			}
			
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				// do nothing
			}else {
				sendToDisplay("ADD SALES ITEM",this.getSalesorderbreakdowns());
			}

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(), "posPanel").getClientId(context), 
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void addSalesPrice() {
		
	}
	
	public void addSalesPriceModifyItem(Integer rowId) {
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {

			item = salesorderbreakdowns.get(rowId);
			List<ProductModel> productList = new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, item
					.getProductCode(), null, 0, 1, loginBean.getBranch()
					.getBranchId(), null, null, null, null);

			if (productList.size() == 0) {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
								"Barcode not Exists"));
			} else {
				producttemp = productList.get(0);
				productPriceList = productBO.getSortedProductpriceBarcode(
						producttemp.getProductId(), loginBean.getBranch()
								.getBranchId());

				if (ValidatorUtil.isNotNull(this.getPriceQty().get(
						item.getProductId()))) {
					item.setUnitPrice(this.getPriceQty().get(
							item.getProductId()));
				}

				if (ValidatorUtil.isNotNull(this.getItemcountQty().get(
						item.getProductId()))) {
					item.setQuantity(this.getItemcountQty().get(
							item.getProductId()));
				}

				if (productPriceList != null && productPriceList.size() == 1) {
					productPriceList.add(productPriceList.get(0));
				}

				
				
				
				BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
				BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				
				if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
				{
					taxAmt =	(item.getUnitPrice().multiply(item.getQuantity()).multiply(producttemp.getTaxCode())).divide(new BigDecimal(100.00));
				}
				else
				{
				BigDecimal taxrate =	(item.getUnitPrice().multiply(item.getQuantity()).multiply(producttemp.getTaxCode())).divide(new BigDecimal(100.00));
				BigDecimal expanea= (item.getUnitPrice().multiply(item.getQuantity())).multiply(taxrate);
				BigDecimal expaneb= new BigDecimal(100.00).add(taxrate);
				taxAmt=expanea.divide(expaneb, 2, RoundingMode.CEILING);
				}
				
				
				item.setTaxAmount(taxAmt);
				
				BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));
				
				if(producttemp.getCategoryName().equalsIgnoreCase("Others"))
				{
				unitpricedeductionAmt=new BigDecimal("6");
				priceAmt = item.getUnitPrice().multiply(item.getQuantity());
				taxAmt = unitpricedeductionAmt.multiply((priceAmt.divide(new BigDecimal(100))));
				item.setUnitPrice(item.getUnitPrice().subtract(taxAmt));
				item.setUnitduplicatePrice(item.getUnitPrice());
				item.setTaxAmount(taxAmt);
				}
				

				for (int i = 0; i < productPriceList.size(); i++) {
					if (!exits) {
						for (int j = i + 1; j < productPriceList.size(); j++) {
							BigDecimal initVal = productPriceList.get(i)
									.getQuantityFrom();
							BigDecimal nextVal = productPriceList.get(j)
									.getQuantityFrom();
							if (item.getQuantity().doubleValue() >= initVal
									.doubleValue()
									&& item.getQuantity().doubleValue() < nextVal
											.doubleValue()) {
								item.setDiscountAmount(productPriceList.get(i)
										.getDiscountAmount());
								exits = true;
								break;
							} else {
								item.setDiscountAmount(productPriceList.get(j)
										.getDiscountAmount());
								i++;
							}
						}
						BigDecimal tmpSubTotal = extractSubtotal(
								item.getUnitPrice(), item.getDiscountAmount(),
								item.getQuantity(), producttemp.getTaxCode());
						item.setSubTotal(tmpSubTotal);
						salesorderbreakdowns.set(rowId, item);
					}
				}

				extractQuoteTotal();
				Collections.sort(salesorderbreakdowns, new PosSalesSorder());
				resetAddItem();
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	
	
	
	public void addSalesPriceModifyItem1(Integer rowId) {
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {

			item = salesorderbreakdowns.get(rowId);
			List<ProductModel> productList = new ArrayList<ProductModel>();
			
			productList = productBO.getProductList(null, null, null, item.getProductCode(), null, 0, 1, loginBean.getBranch()
					.getBranchId(), null, null, null, null);
			
			if (productList.size() == 0) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null,	"Barcode not Exists"));
			} else {
				producttemp = productList.get(0);
				productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(), loginBean.getBranch().getBranchId());

				if (ValidatorUtil.isNotNull(this.getPriceQty().get(item.getSno()))) {					
					String price=""+this.getPriceQty().get(item.getSno());
					item.setUnitPrice(new BigDecimal(price));
				
				}

				if (ValidatorUtil.isNotNull(this.getItemcountQty().get(item.getSno()))) {
					
					String qty=""+this.getItemcountQty().get(item.getSno());
					item.setQuantity(new BigDecimal(qty));
				}

				if (productPriceList != null && productPriceList.size() == 1) {
					productPriceList.add(productPriceList.get(0));
				}				
				
				//moo
				
				if(producttemp.getNormalPrice().compareTo(BigDecimal.ZERO) != 0) 
				{
					if(item.getPacking()==1) {
						item.setUnitPrice(producttemp.getPackingPrice());
					}
					else {
						item.setUnitPrice(producttemp.getNormalPrice());
					}
				}

				// removed for LOW COST CAFE
				//producttemp.setTaxCode(unitpricetaxAmt); // Dynamic
				
				// price=itemPrice * qty
				BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
				
				// taxAmt=taxt% * price;
				
				BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				taxAmt= DecimalUtil.formatRoundupCents(taxAmt);
				if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
				{
					priceAmt=priceAmt.add(taxAmt);
					//taxAmt =	(item.getUnitPrice().multiply(item.getQuantity()).multiply(producttemp.getTaxCode())).divide(new BigDecimal(100.00));

				}
				else
				{
					BigDecimal itemTax= producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
					item.setUnitPrice(item.getUnitPrice().subtract(itemTax));
					item.setUnitPrice(DecimalUtil.formatRoundupCents(item.getUnitPrice()));

				}
				

				BigDecimal singletaxAmt = producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				
				item.setTaxAmount(taxAmt);		
				item.setUnitPricetax(item.getUnitPrice());
				this.priceQty.put(item.getSno(), item.getUnitPrice());

				
				int qtyLatest=item.getQuantity().intValue();
				int qtyBefore=item.getQuantityBefore().intValue();
				//int qty=qtyLatest-qtyBefore;
				//Integer.parseInt(""+ (item.getQuantity().subtract(item.getQuantityBefore())));
				/*for(int i=0;i<qty;i++) {
					item.setItemRemarksId(item.getItemRemarksId()+",0");
				}
				*/
				if(item.getStatus()==null)
					item.setQuantityBefore(item.getQuantity());
				
				
				
				//item.setUnitPrice(item.getUnitPrice().subtract(singletaxAmt));
				//for no gst
				//taxAmt=new BigDecimal(0);
				
				BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				
				item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));				
				
				item.setDiscountAmount(new BigDecimal(0.00));
				//BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(), item.getDiscountAmount(),	item.getQuantity(), producttemp.getTaxCode());
				
				// for no gst
				//priceAmt=item.getUnitPrice();
				
				BigDecimal tmpSubTotal = DecimalUtil.formatRoundupCents(priceAmt);
				
				item.setSubTotal(tmpSubTotal);

				salesorderbreakdowns.set(rowId, item);
				
				
				extractQuoteTotal();
				
				//Collections.sort(salesorderbreakdowns, new PosSalesSorder());
				resetAddItem();
			}
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				// do nothing
			}else {
				sendToDisplay("MODIFY", this.getSalesorderbreakdowns());
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(), "posPanel").getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	
	
	
	public void selectProduct(SelectEvent event) {
		this.product = ((ProductModel) event.getObject());
		this.setBarcode(product.getBarcode());
		addSalesItem();
	}

	public void selectedProductQty() {
		this.setItemCount(new BigDecimal(""+selectedItemQty));
		addSalesItem();
		this.setItemCount(new BigDecimal(0.00));
	}
	
	//mobile
	
	public void selectProductMenu(ActionEvent event) {
		
		this.product = (ProductModel) event.getComponent().getAttributes()
				.get("product");
		this.setBarcode(product.getBarcode());
		this.setItemCount(new BigDecimal(0.00));
		
		this.selectedItemQty=1;
		this.setRemarksId(1);
		this.setPacking(false);
		addSalesItem();
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Item added to order") );
		
		
	}

	//moo
	public void selectProductMenuViaPos(ActionEvent event) {
		
		try {
			this.product = (ProductModel) event.getComponent().getAttributes()
					.get("product");
			
			this.setBarcode(product.getBarcode());
			this.setItemCount(new BigDecimal(0.00));
			this.selectedItemQty=1;
			
			this.setRemarksId(1);
			this.setPacking(false);
			loadRemarksList();
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}

	public void resetCategory() {
		ProductBean productBean = (ProductBean) BeanContext
				.getReference("productBean");
		productBean.loadProduct();
		this.setCatId(null);
	}
	
	//mobile
	public void productListMenu(ActionEvent event) {
		ProductBean productBean = (ProductBean) BeanContext
				.getReference("productBean");
		this.setFilterProductName(null);
		String categoryName=(String) event.getComponent()
				.getAttributes().get("categoryId");
		productBean.setCategoryName(categoryName);
		productBean.loadAllProduct();
		productBean.checkOrderedProduct(salesorderbreakdowns);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		

		projectHome.setTitlePage("Sales --> Product Selection");
		projectHome.setPrevPage(projectHome.getContenttpage());
		List<String> pages=projectHome.getPageTracker();
		pages.add(projectHome.getPrevPage());
		projectHome.setPageTracker(pages);
		projectHome.setContentpage("/mobileVersionUI/sales/productSelection.xhtml");
		homeinfo.menuPageRedirect();
		
	}

	public void selectCategoryMenuBar(ActionEvent event) {
		try {
			
			ProductBean productBean = (ProductBean) BeanContext
					.getReference("productBean");
			List<Integer> categoryIds = new ArrayList<Integer>();
			ProductcategoryModel pcat = (ProductcategoryModel) event.getComponent()
					.getAttributes().get("productcat");
			categoryIds.add(pcat.getCategoryId());
			productBean.setCategoryName("" + pcat.getCategoryId());
			productBean.loadAllProduct();
			productBean.checkOrderedProduct(salesorderbreakdowns);
			
			this.setCatId(pcat.getCategoryId());
			this.getSalesorder().setCardNo("TableOrder"+this.getSalesorder().getTableName());
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			String userAgent = request.getHeader("user-agent");
			
			if(this.getSalesorder().getCardNo() == null
					|| this.getSalesorder().getCardNo()
					.equalsIgnoreCase("")) {
			
				factoryBean.reportErrorToMessageHandler(
						"Card No Required ", "", "cardNo");
			}
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	
	public void hotListMenu() {
		try {
			this.setFilterProductName(null);

			ProductBean productBean = (ProductBean) BeanContext
					.getReference("productBean");
			productBean.loadHotListProduct();
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			
			projectHome.setContentpage("/mobileVersionUI/sales/productSelection.xhtml");
			homeinfo.menuPageRedirect();
			
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	//mobile
	public void selectCategoryMenu(ActionEvent event) {
		try {
			this.setFilterProductName(null);
			ProductBean productBean = (ProductBean) BeanContext
					.getReference("productBean");
			List<Integer> categoryIds = new ArrayList<Integer>();
			ProductcategoryModel pcat = (ProductcategoryModel) event.getComponent()
					.getAttributes().get("productcat");
			categoryIds.add(pcat.getCategoryId());
			
			productBean.setCategoryName("" + pcat.getCategoryId());
			productBean.loadAllProduct();
			productBean.checkOrderedProduct(salesorderbreakdowns);
			
			this.setCatId(pcat.getCategoryId());
		
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			String userAgent = request.getHeader("user-agent");
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
			ProjectHome projectHome = (ProjectHome) BeanContext
					.getReference("projectHome");
			if(this.getSalesorder().getCardNo() == null
					|| this.getSalesorder().getCardNo()
					.equalsIgnoreCase("")) {
			
				factoryBean.reportErrorToMessageHandler(
						"Card No Required ", "", "cardNo");
			}else {
				
				if(userAgent.matches(".*Android.*")) {
					projectHome.setTitlePage("Sales --> Product Selection");
					projectHome.setPrevPage(projectHome.getContenttpage());
					List<String> pages=projectHome.getPageTracker();
					pages.add(projectHome.getPrevPage());
					projectHome.setPageTracker(pages);
					projectHome.setContentpage("/mobileVersionUI/sales/productSelection.xhtml");
					homeinfo.menuPageRedirect();	
				}else {
					OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
					orderBean.loadSubItems();
					
				}
					
			}
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}

	public void selectCustomer(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		BigDecimal amount = new BigDecimal(0.00);
		try {
			this.customer = ((CustomerModel) event.getObject());
			customer = customerBO.getCustomerDetails(this.getCustomer()
					.getCustomerId());

			if (this.getCustomer() != null
					&& this.getCustomer().getAvailablePoints() != 0) {
				amount = new BigDecimal(""
						+ (this.getCustomer().getAvailablePoints() / loginBean
								.getBranch().getRedemPoint()));
				customer.setAvailableLoyaltyAmount(amount);
			} else {
				customer.setAvailableLoyaltyAmount(new BigDecimal(0.00));
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void resetAddItem() {
		this.setItemCount(new BigDecimal(1.00));
		this.setBarcode(null);
		this.setRemarksId(1);
		this.setPacking(false);
	}

	public void resetPos() {
		this.salesId.clear();
		
		salesorderbreakdowns.clear();
		this.priceQty.clear();
		this.setItemCount(new BigDecimal(1.00));
		this.setBarcode(null);
		this.setTotalAmount(new BigDecimal("0.00"));
		this.setTotalDiscountAmount(new BigDecimal("0.00"));
		this.setTotalBeforeDiscount(new BigDecimal("0.00"));
		this.setReceivedAmount(new BigDecimal("0.00"));
		this.setBalanceAmount(new BigDecimal("0.00"));
		this.setCustomer(new CustomerModel());
		this.setPrescptNo(null);
		this.setDpModel(new DoctorsPrescriptionsModel());
		this.setCardNoList("");
		this.setTableNameList("");
		this.getSalesorder().setCardNo(null);
		this.getSalesorder().setTableName(null);
		this.setCardNo(null);
		this.setTableName(null);
		this.setPosOption("sales");
		this.getSalesorder().setCustomerName(null);
		if(this.getPoscounter()!=null && this.getPoscounter().getCounterNo()!=null) {
			sendToDisplay("RESET",this.getSalesorderbreakdowns());	
		}
		

	}

	public BigDecimal extractSubtotal(BigDecimal unitPrice,
			BigDecimal discAmount, BigDecimal quant, BigDecimal taxCode) {
		BigDecimal quantityValue = quant;
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt = priceAmt.add(taxAmt);
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}

	public void extractQuoteTotal() {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			BigDecimal totalAmount = new BigDecimal(0.00);
			BigDecimal totalDiscountAmount = new BigDecimal(0.00);
			BigDecimal grandTotalAmount = new BigDecimal(0.00);
			BigDecimal totalTaxAmount = new BigDecimal(0.00);

			for (SalesorderbreakdownModel item : salesorderbreakdowns) {
				totalAmount = totalAmount.add(item.getSubTotal());
				totalDiscountAmount = totalDiscountAmount.add(item
						.getDiscountAmount());
				totalTaxAmount = totalTaxAmount.add(item.getTaxAmount());
		
				
			}
			this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));
			//this.setTotalAmount(totalAmount);
			this.setTotalDiscountAmount(totalDiscountAmount);
			this.setTotalTaxAmount(totalTaxAmount);
			// grandTotalAmount=(totalAmount.add(totalTaxAmount)).subtract(totalDiscountAmount);
			grandTotalAmount = (totalAmount.add(totalTaxAmount));
			this.setGrandTotalAmount(grandTotalAmount);
			this.setTotalBeforeDiscount(this.getTotalAmount());
			
		
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}

	}

	public void getDynamicSplitSalesItemList(SalesorderbreakdownModel item) {
		this.salesordersplitbreakdowns.add(item);
	}
	
	public void getDynamicSalesItemList(SalesorderbreakdownModel item) {
		this.salesorderbreakdowns.add(item);

	}

	public void addSalesAmountItem(ActionEvent event) {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		String paymentId = "";
		int rowCount = 0;
		boolean datamatch = false;
		// paymentId = (String)
		// event.getComponent().getAttributes().get("id").toString();
		this.setPaymentConfirm(false);
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		if (externalContext.getRequestParameterMap().get("id") != null) {
			paymentId = externalContext.getRequestParameterMap().get("id");
		}

		try {
			item = new PaymentCollectionModel();
			if (paymentId.equalsIgnoreCase("Excat Card")) {
				item.setAmountType("Cash");
				item.setAmount(this.getTotalAmount());
				item.setQuantity(1);
			} else if (paymentId.equalsIgnoreCase("Excat Cash")) {
				item.setAmountType("Cash");
				item.setAmount(this.getTotalAmount());
				item.setQuantity(1);
			} else if (paymentId.equalsIgnoreCase("Cash")) {
				item.setAmountType("Cash");
				item.setAmount(this.getTotalAmount());
				item.setQuantity(1);
			} else {
				item.setAmountType("Cash");
				item.setAmount(new BigDecimal(paymentId));
				item.setQuantity(1); // new for jpos

				for (PaymentCollectionModel data : paymentCollectionModel) {
					if (data.getAmountType().equalsIgnoreCase(paymentId)) {
						PaymentCollectionModel c = paymentCollectionModel
								.get(rowCount);

						if (ValidatorUtil.isNotNull(this.getAmountCount().get(
								paymentId))) {
							String temp = ""
									+ this.getAmountCount().get(paymentId);
							if (!temp.equalsIgnoreCase("")) {
								item.setQuantity(Integer.parseInt(temp)
										+ c.getQuantity());
							} else {
								item.setQuantity(1 + c.getQuantity());
							}
							this.getAmountCount().put(paymentId, 1);
						}

						item.setSubTotal(item.getAmount().multiply(
								new BigDecimal(item.getQuantity())));
						datamatch = true;
						paymentCollectionModel.set(rowCount, item);
					}
					rowCount++;
				}

			}
			if (!datamatch) {
				if (ValidatorUtil.isNotNull(this.getAmountCount()
						.get(paymentId))) {
					String temp = "" + this.getAmountCount().get(paymentId);
					if (!temp.equalsIgnoreCase("")) {
						item.setQuantity(Integer.parseInt(temp));
					} else {
						item.setQuantity(1);
					}
					this.getAmountCount().put(paymentId, 1);
				}

				item.setSubTotal(item.getAmount().multiply(
						new BigDecimal(item.getQuantity())));
				paymentCollectionModel.add(item);
			}
			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
			
			sendToDisplay("PAID AMOUNT", this.getSalesorderbreakdowns());

		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}

	public void instantRemove(ActionEvent event) {

		removeItem(event);
		removeItemConfirm();
		ProductBean productBean = (ProductBean) BeanContext
				.getReference("productBean");
		productBean.loadAllProduct();
		productBean.checkOrderedProduct(salesorderbreakdowns);
	}
	
	public void removeItemFromSplitPayment(ActionEvent event) {
		removeItem(event);
		removeItemFromSplitPaymentConfirm();
		
	}

	public void removeItemFromSplitPaymentConfirm(){
		
		SalesorderbreakdownModel c = salesorderbreakdowns.get(this.getRowId());
		
		this.addKitchenItemToSalesSplit(c);
		salesorderbreakdowns.remove(c);
		extractQuoteTotal();
		
		
	}
	
	public void removeItemFromSplit(ActionEvent event) {
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes()
				.get("posBreakdownId").toString();
		this.setRowId(Integer.parseInt(quotationBreakdownId));
	}
	
	public void removeItemFromSplitConfirm() {
		SalesorderbreakdownModel c = salesordersplitbreakdowns.get(this.getRowId());
		salesordersplitbreakdowns.remove(c);
	}

	public void removeItem(ActionEvent event) {
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes()
				.get("posBreakdownId").toString();
		this.setRowId(Integer.parseInt(quotationBreakdownId));
	}

	public void removeItemConfirm() {
		SalesorderbreakdownModel c = salesorderbreakdowns.get(this.getRowId());
		OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
		salesorderbreakdowns.remove(c);
		if(c.getStatus() !=null && c.getStatus().equalsIgnoreCase("cancel")) {
			orderBean.getKitchenOrderMapping().put(c.getProductId(), orderBean.getKitchenOrderMapping().get(c.getProduct().getProductId())+1);
		}
		extractQuoteTotal();
		if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
			// do nothing
		}else {
			sendToDisplay("REMOVE SALES ITEM", this.getSalesorderbreakdowns());	
		}
		
		

	}

	public void addOrder() {
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
		
		projectHome.setPrevPage(projectHome.getContenttpage());
		List<String> pages=projectHome.getPageTracker();
		pages.add(projectHome.getPrevPage());
		projectHome.setPageTracker(pages);
		projectHome.setContentpage("/mobileVersionUI/sales/categorySelection.xhtml");
		homeinfo.menuPageRedirect();
		
		
	}
	
	public void loadRemarksList() {
		remarksList=null;
		remarksListMap=new HashMap<Integer,String>();
		List<SelectItem> itemRemarksList=new ArrayList<SelectItem>();
		try {
			
			for(ItemRemarksFunctionListModel data : itemRemarksBO.fetchItemRemarksFunctionList(null, null, null, this.getCatId())) {
				if(data.getRemarks().getRemarksListID()>1) {
					itemRemarksList.add(new SelectItem(data.getRemarks().getRemarksListID(),data.getRemarks().getRemarksName()));
					remarksListMap.put(data.getRemarks().getRemarksListID(), data.getRemarks().getRemarksName());	
				}
				
			}
		
		
			this.setRemarksList(itemRemarksList);	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadSubList() {
		try{
			remarksItemBreakdowns.clear();
		
			SalesorderbreakdownModel obj=new SalesorderbreakdownModel();
			obj.setSno(sno);
			obj.setProductName(item.getProductName());
			List<ItemRemarksListModel> remarksObj=itemRemarksBO.fetchItemRemarksList(item.getRemarks().getRemarksListID(), null);
			obj.setRemarks(remarksObj.get(0));
			
			remarksItemBreakdowns.add(obj);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void resetProductList() {
		try {
			ProductBean productBean = (ProductBean) BeanContext
					.getReference("productBean");
			
			List<ProductModel> filteredProducts=new ArrayList<ProductModel>();
			this.setFilterProductName(null);
			productBean.setProductAll(productBean.getUnFilteredProductList());				
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filterProduct() {
		try {
			ProductBean productBean = (ProductBean) BeanContext
					.getReference("productBean");
			
			List<ProductModel> filteredProducts=new ArrayList<ProductModel>();
			if(this.getFilterProductName()!=null && !this.getFilterProductName().isEmpty()) {
				
				this.setFilterProductName(this.getFilterProductName().replaceAll("\\h"," "));
				
				for(ProductModel obj: productBean.getUnFilteredProductList()) {
			
					if( obj.getProductName().toUpperCase().contains(this.getFilterProductName().toUpperCase()) ) {
						filteredProducts.add(obj);
					}
				}
				productBean.setProductAll(filteredProducts);

			}else {
				productBean.setProductAll(productBean.getUnFilteredProductList());				
			}
			
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*deleteRem
	public void loadRemarksBreakdown() {
		try {
			
			
			remarksItemBreakdowns.clear();
			int qty=item.getQuantity().intValue();
			
			String[] remarksId=item.getRemarks().getRemarksListID();
			for(int i=0;i<qty;i++) {
				SalesorderbreakdownModel obj=new SalesorderbreakdownModel();
				ItemRemarksListModel remarksObj=new ItemRemarksListModel();
				
				obj.setSno(i);
				obj.setProductName(item.getProductName());
				if(remarksId[i].equals("0")){
					obj.setRemarks("Normal");
				}else {
					
					Integer id=Integer.parseInt(remarksId[i]);
					List<ItemRemarksListModel> objRemarks=itemRemarksBO.fetchItemRemarksList(id, null);
					obj.setRemarks(objRemarks.get(0).getRemarksName());
				}
				
				remarksItemBreakdowns.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	*/
	public void subViewButtonClicked() {
		if(this.isViewSubList())
		{
			if(this.getSubListId()!=null && this.getSubListId()!=this.getRowId())
			{
				this.setSubListId(this.getRowId());
			}else {
				this.setViewSubList(false);
			}
			
		}
		else {
			this.setSubListId(this.getRowId());
			this.setViewSubList(true);
			
		}
	}
	
	public void listItemBreakdowns(ActionEvent event) throws Exception {
		
			String quotationBreakdownId = "";
			quotationBreakdownId = (String) event.getComponent().getAttributes()
					.get("posBreakdownId").toString();
			this.setRowId(Integer.parseInt(quotationBreakdownId));
			
			item = salesorderbreakdowns.get(this.getRowId());
			//ProductcategoryModel catModel=productCategoryBO.getProductcategoryDetails(item.getProduct().getCategoryId());
			this.setCatId(item.getProduct().getCategoryId());
			//loadRemarksBreakdown();
			loadSubList();
			loadRemarksList();
			subViewButtonClicked();
			//
			
	}
	
	public void editRemarks(ActionEvent event) {
		String subItemId = "";
		subItemId = (String) event.getComponent().getAttributes()
				.get("subItemId").toString();
		this.setSubItemId(Integer.parseInt(subItemId));
		this.setRemarksId(item.getRemarks().getRemarksListID());
		
		KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
		
		Integer[] remark = new Integer[item.getKitchenorderremarksbreakdownModel().size()];
		int i=0;
		for(KitchenorderremarksbreakdownModel data:item.getKitchenorderremarksbreakdownModel()) {
			remark[i++]=data.getRemarks().getRemarksListID();
		}
		this.setSelectedRemarksId(remark);
		
		
		if(item.getPacking()==0) {
			this.setPacking(false);
		}else {
			this.setPacking(true);	
		}
		// Briyani set
		/*if(item.getProductId()==574 || item.getProductId()==575
				|| item.getProduct().getProductId()==837 || item.getProduct().getProductId()==838 || 
				item.getProduct().getProductId()==839 || item.getProduct().getProductId() == 850 ||
				item.getProduct().getProductId()==851 || item.getProduct().getProductId()==852 ||
				item.getProduct().getProductId()==853
				) {
			this.setOption=item.getSetOption();
		}
		*/
		
	}

	public void saveRemarksToItem() {
		item = salesorderbreakdowns.get(this.getRowId());
		ItemRemarksListModel remarksObj=new ItemRemarksListModel();
		remarksObj.setRemarksListID(this.getRemarksId());
		List<KitchenorderremarksbreakdownModel> remarksList=new ArrayList<KitchenorderremarksbreakdownModel>();
		
		for(Integer remarkId:selectedRemarksId) {
			KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
			
			ItemRemarksListModel remark=new ItemRemarksListModel();
			remark.setRemarksListID(remarkId);
			
			remark.setRemarksName(""+this.getRemarksListMap().get(remarkId));
			obj.setRemarks(remark);
			
			remarksList.add(obj);	
		}
		
		item.setKitchenorderremarksbreakdownModel(remarksList);
		
		this.setSelectedRemarksId(null);

		
		if(this.isPacking()) {
			item.setPacking(1);
			item.setUnitPrice(item.getProduct().getPackingPrice());
		}else {
			item.setPacking(0);
		}
		// Briyani set
		/*if(item.getProductId()==574 || item.getProductId()==575
				|| item.getProduct().getProductId()==837 || item.getProduct().getProductId()==838 || 
				item.getProduct().getProductId()==839 || item.getProduct().getProductId() == 850 ||
				item.getProduct().getProductId()==851 || item.getProduct().getProductId()==852 ||
				item.getProduct().getProductId()==853
				) {
			item.setSetOption(this.getSetOption());
		}
		*/
		item.setRemarks(remarksObj);
		this.loadSubList();
		addSalesPriceModifyItem1(this.getRowId());
		
	}	
	
	public void remarksItem(ActionEvent event) {
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes()
				.get("posBreakdownId").toString();
		this.setRowId(Integer.parseInt(quotationBreakdownId));
		item = salesorderbreakdowns.get(this.getRowId());
		
	}

	public void editRemarksPOS(ActionEvent event) {
		
		String value = "";
		value = (String) event.getComponent().getAttributes()
				.get("posPanelRowId").toString();
		 //String value = FacesContext.getCurrentInstance().
			//		getExternalContext().getRequestParameterMap().get("posPanelRowId");
		this.setRowId(Integer.parseInt(value));
		
		item = salesorderbreakdowns.get(this.getRowId());
		this.setCatId(item.getProduct().getCategoryId());
		
		this.setRemarksId(item.getRemarks().getRemarksListID());
		if(item.getPacking()==0) {
			this.setPacking(false);
		}else {
			this.setPacking(true);	
		}
		
		KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
		
		Integer[] remark = new Integer[item.getKitchenorderremarksbreakdownModel().size()];
		int i=0;
		for(KitchenorderremarksbreakdownModel data:item.getKitchenorderremarksbreakdownModel()) {
			remark[i++]=data.getRemarks().getRemarksListID();
		}
		this.setSelectedRemarksId(remark);
		
		/*
KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
			
			ItemRemarksListModel remark=new ItemRemarksListModel();
			remark.setRemarksListID(remarkId);
			
			remark.setRemarksName(""+this.getRemarksListMap().get(remarkId));
			obj.setRemarks(remark);
			
			remarksList.add(obj);	 
		 */
		loadRemarksList();

		
	}
	
	public void saveRemarksToItemPOS() {
		//  String value = FacesContext.getCurrentInstance().
			//		getExternalContext().getRequestParameterMap().get("posPanelRowId");
		  
		item = salesorderbreakdowns.get(this.getRowId());
		ItemRemarksListModel remarksObj=new ItemRemarksListModel();
		remarksObj.setRemarksListID(this.getRemarksId());
		
		List<KitchenorderremarksbreakdownModel> remarksList=new ArrayList<KitchenorderremarksbreakdownModel>();
		
		for(Integer remarkId:selectedRemarksId) {
			KitchenorderremarksbreakdownModel obj=new KitchenorderremarksbreakdownModel();
			
			ItemRemarksListModel remark=new ItemRemarksListModel();
			remark.setRemarksListID(remarkId);
			
			remark.setRemarksName(""+this.getRemarksListMap().get(remarkId));
			obj.setRemarks(remark);
			
			remarksList.add(obj);	
		}
		
		item.setKitchenorderremarksbreakdownModel(remarksList);
		
		this.setSelectedRemarksId(null);

		
		if(this.isPacking()) {
			item.setPacking(1);
			item.setUnitPrice(item.getProduct().getPackingPrice());
		}else {
			item.setPacking(0);
		}
		item.setRemarks(remarksObj);
		addSalesPriceModifyItem1(this.getRowId());

	}
	
	public void remarksItemConfirm() {
		SalesorderbreakdownModel c = salesorderbreakdowns.get(this.getRowId());
		c.setRemarks(item.getRemarks());
		salesorderbreakdowns.set(this.getRowId(), c);
	}

	public BigDecimal getSalesPaymentItemTotal(
			List<PaymentCollectionModel> paymentCollectionModel) {
		BigDecimal total = new BigDecimal(0);
		for (PaymentCollectionModel temp : paymentCollectionModel) {
			total = total.add(temp.getSubTotal());
		}
		return total;
	}

	public void removeSalesPaymentItem() {
		this.setPaymentConfirm(false);
		receivedAmount = this.getSalesPaymentItemTotal(paymentCollectionModel);
		balanceAmount = receivedAmount.subtract(totalAmount);
		if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
				String.valueOf(totalAmount))) {
			this.setPaymentConfirm(true);
		}
		sendToDisplay("REMOVE PAYMENT AMOUNT", this.getSalesorderbreakdowns());

	}

	public void paymentresetSales() {
		
		paymentCollectionModel.clear();
		
		receivedAmount = this.getSalesPaymentItemTotal(paymentCollectionModel);
		this.setPaymentConfirm(false);
		this.getSalesorder().setPaymentType("Cash");
		this.setTotalAmount(this.getTotalBeforeDiscount());
		balanceAmount = receivedAmount.subtract(totalAmount);
		this.paymentModel.setAmount(totalAmount);
		this.paymentModel.setCardNo("");
		this.paymentModel.setExpdate(null);
		//this.paymentModel.setReferenceNo("");
		this.paymentModel.setCardType("");
		this.setDiscountRate(0);
		this.setDiscountRateId(0);
		
		
		CustomerdisplayClientEndpoint clientEndpoint = (CustomerdisplayClientEndpoint) BeanContext.getReference("customerdisplayClientEndpoint");
		sendToDisplay("PAYMENT",this.getSalesorderbreakdowns());
		
		if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
				String.valueOf(totalAmount))) {
			this.setPaymentConfirm(true);
		}
	}

	public void addCashDirectPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("Cash");
			item.setAmount(paymentModel.getAmount());
			item.setQuantity(1);
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			if (paymentModel.getAmount() != null
					&& paymentModel.getAmount().doubleValue() != 0.00) {
				paymentCollectionModel.add(item);
			}

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
			sendToDisplay("PAID AMOUNT", this.getSalesorderbreakdowns());

		} catch (Exception e) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}
	
	public void addGrabPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("GrabPay");
			item.setAmount(paymentModel.getAmount());
			item.setCardNo(paymentModel.getCardNo());
			//item.setCardType(paymentModel.getCardType());
			item.setReferenceNo(paymentModel.getReferenceNo());
			item.setQuantity(1);
			
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			paymentCollectionModel.add(item);

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}

	public void addCardPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("Card");
			item.setAmount(paymentModel.getAmount());
			item.setCardNo(paymentModel.getCardNo());
			item.setCardType(paymentModel.getCardType());
			item.setReferenceNo(paymentModel.getReferenceNo());
			
			item.setQuantity(1);
			
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			paymentCollectionModel.add(item);

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}
	
	

	public void addChequePayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("Cheque");
			item.setAmount(paymentModel.getAmount());
			item.setReferenceNo(paymentModel.getReferenceNo());
			item.setBankName(paymentModel.getBankName());
			item.setQuantity(1);
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			paymentCollectionModel.add(item);

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));
		}
	}

	public void addVoucherPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("Voucher");
			item.setAmount(paymentModel.getAmount());
			item.setReferenceNo(paymentModel.getReferenceNo());
			item.setQuantity(1);
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			paymentCollectionModel.add(item);

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}
			this.setPaymentModel(new PaymentCollectionModel());
		}

		catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}

	public void addLoyaltyPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);

		try {
			item = new PaymentCollectionModel();
			item.setAmountType("Loyalty");
			item.setAmount(paymentModel.getAmount());
			item.setConsumePoints(paymentModel.getConsumePoints());
			item.setQuantity(1);
			item.setSubTotal(paymentModel.getAmount().multiply(
					new BigDecimal(1)));

			paymentCollectionModel.add(item);

			receivedAmount = this
					.getSalesPaymentItemTotal(paymentCollectionModel);
			balanceAmount = receivedAmount.subtract(totalAmount);
			if (DecimalUtil.greaterThanEqual(String.valueOf(receivedAmount),
					String.valueOf(totalAmount))) {
				this.setPaymentConfirm(true);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}

	public void checkLoyaltyPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);
		BigDecimal amount = new BigDecimal(0.00);
		try {

			if (paymentModel.getConsumePoints() != null
					&& paymentModel.getConsumePoints() != 0
					&& this.getCustomer() != null
					&& this.getCustomer().getAvailablePoints() != 0) {
				if (paymentModel.getConsumePoints() <= this.getCustomer()
						.getAvailablePoints()) {
					amount = new BigDecimal(""
							+ (paymentModel.getConsumePoints() / loginBean
									.getBranch().getRedemPoint()));
					paymentModel.setAmount(amount);
				} else {
					paymentModel.setAmount(new BigDecimal(0.00));
					paymentModel.setConsumePoints(0);
				}
			} else {
				paymentModel.setAmount(new BigDecimal(0.00));
				paymentModel.setConsumePoints(0);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}

	public void checkLoyaltyCashPayment() {
		PaymentCollectionModel item = null;
		FacesContext context = FacesContext.getCurrentInstance();
		this.setPaymentConfirm(false);
		BigDecimal amount = new BigDecimal(0.00);
		Integer points = 0;
		try {

			if (this.getCustomer() != null
					&& this.getCustomer().getAvailableLoyaltyAmount() != null
					&& this.getCustomer().getAvailableLoyaltyAmount()
							.doubleValue() != 0.00) {
				if (DecimalUtil.lessThan(String.valueOf(paymentModel
						.getAmount()), String.valueOf(this.getCustomer()
						.getAvailableLoyaltyAmount()))) {
					points = loginBean.getBranch().getRedemPoint()
							* (paymentModel.getAmount().intValue());
					paymentModel.setConsumePoints(points);
				} else {
					paymentModel.setAmount(new BigDecimal(0.00));
					paymentModel.setConsumePoints(0);
				}
			} else {
				paymentModel.setAmount(new BigDecimal(0.00));
				paymentModel.setConsumePoints(0);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"salespaymenterrorPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),
							null));

		}
	}
	
	public void saveSplitOrder() {
		try {
			String servername = config
					.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
			String printerName = config
					.getValue(IConfiguration.PROJECT_PRINTER_NAME);
			if(this.salesorderSplit.getSplitInc()==null || this.salesorderSplit.getSplitInc().isEmpty()) {
				this.salesorderSplit.setSplitInc(".1");
			}
			this.getSalesorder().setCardNo(this.salesorderSplit.getCardNo()+this.salesorderSplit.getSplitInc());
			this.getSalesorder().setTableName(this.salesorderSplit.getTableName());
			boolean valid = true;
			SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
			ProjectHome projectHome = (ProjectHome) BeanContext
					.getReference("projectHome");
			
			
			
			if (kitchenValidation()) {
			
				valid = sinfo.saveSplitOrder();
			
				if(valid==true) {
					
				}
				
				Collections.sort(this.getSalesordersplitbreakdowns(), new PosSalesSorder());
				
				char splitInc=this.getSalesorderSplit().getSplitInc().charAt(1);
				splitInc++;
				this.getSalesorderSplit().setSplitInc("."+splitInc);
				valid = sinfo.updateSplitOrder(this.getSalesordersplitbreakdowns());
				
				this.setCardNo(this.getSalesorderSplit().getTableName());
				this.loadBarKitchenOrderforSaleSplit();
				
			}
			
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void modifyDiscountRate() {
		CommonListBean cbl=(CommonListBean) BeanContext.getReference("commonListBean");
		String rmrk="";
		BigDecimal discountRate=new BigDecimal("0");
		for(int i=0;i<cbl.getDiscountRate().size();i++) {
			if(this.getDiscountRateId()==cbl.getDiscountRate().get(i).getValue()) {
				discountRate=new BigDecimal((cbl.getDiscountRate().get(i).getLabel().substring(0, cbl.getDiscountRate().get(i).getLabel().length()-1)));
				break;
			}
		}
		
		this.setDiscountRate(discountRate.intValue());
	
		for(int i=0;i<cbl.getDiscountRemark().size();i++) {
			if(this.getDiscountRemarkId()==cbl.getDiscountRemark().get(i).getValue()) {
				rmrk=cbl.getDiscountRemark().get(i).getLabel();
				break;
			}
		}
		
		
		try {
			this.setDiscountRemarks(rmrk);

			BigDecimal discountAmount=new BigDecimal("0");
			discountAmount=this.getTotalBeforeDiscount().multiply(discountRate.divide(new BigDecimal("100")));

			BigDecimal amountAfterDiscount=this.getTotalBeforeDiscount().subtract(discountAmount);
			this.setTotalAmount(DecimalUtil.formatRoundupCents(amountAfterDiscount));
			BigDecimal discountedTaxAmount=new BigDecimal("0");
			discountedTaxAmount=this.getTotalTaxAmount().multiply(discountRate.divide(new BigDecimal("100")));
			BigDecimal taxAfterDiscount=this.getTotalTaxAmount().subtract(discountedTaxAmount);

			this.setTotalTaxAmount(DecimalUtil.formatRoundupCents(taxAfterDiscount));
			this.setBalanceAmount(this.getReceivedAmount().subtract(this.getTotalAmount()));
			sendToDisplay("ADD DISCOUNT",this.getSalesorderbreakdowns());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public void savePOS() {
		String servername = config
				.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
		String printerName = config
				.getValue(IConfiguration.PROJECT_PRINTER_NAME);
		boolean valid = true;
		
		if(this.getCardOption().equalsIgnoreCase("split")) {
			if(kitchenOrder!=null || kitchedOrder.size()!=0) {
				try {
					kitchenOrderBeforeSales=salesOrderBO.getKitchenorderDetails(kitchedOrder.get(0).getSalesOrderId());
					List<SalesorderbreakdownModel> removedData=new ArrayList<SalesorderbreakdownModel>();
				
					//kitchenOrderBeforeSales.getSalesorderbreakdowns().clear();
					BigDecimal totalAmount=new BigDecimal("0");
					BigDecimal totalTax=new BigDecimal("0");
					for(SalesorderbreakdownModel data:kitchenOrderBeforeSales.getSalesorderbreakdowns()) {
						for(SalesorderbreakdownModel data2:this.getSalesorderbreakdowns()) {
							
							if(data2.getSalesOrderBreakdownId()>0) {
								//kitchenOrderBeforeSales.getSalesorderbreakdowns().add(data2);
								if(data.getSalesOrderBreakdownId()==data2.getSalesOrderBreakdownId()) {
									if(data.getQuantity().compareTo(data2.getQuantity())>0) {
										data.setQuantity(data.getQuantity().subtract(data2.getQuantity()));
										data.setSubTotal(data.getSubTotal().subtract(data2.getSubTotal()));
										data.setTaxAmount(data.getTaxAmount().subtract(data2.getTaxAmount()));
									}else if(data.getQuantity().compareTo(data2.getQuantity())==0){
										data.setSubTotal(BigDecimal.ZERO);
										data.setTaxAmount(BigDecimal.ZERO);
										removedData.add(data);
										
									}
									totalAmount=totalAmount.add(data.getSubTotal());
									totalTax=totalTax.add(data.getTaxAmount());
									break;
								}
							}
						}
					}
					
					
					kitchenOrderBeforeSales.getSalesorderbreakdowns().removeAll(removedData);
					kitchenOrderBeforeSales.setTotalAmount(totalAmount);
					kitchenOrderBeforeSales.setTotalTax(totalTax);
					salesOrderBO.updateKitchenorderSplit(kitchenOrderBeforeSales);
					this.getSalesId().clear();
					//salesOrderBO.deleteKitchenorderbreakdown(removedData.get(0));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		}
		
		
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		
		
		valid = sinfo.savePOSSalesOrder();
		
		
		SalesorderBean salesorderBean = (SalesorderBean) BeanContext
				.getReference("salesorderBean");
		PosOptionBean posOptionBean = (PosOptionBean) BeanContext
				.getReference("posOptionBean");
				
		try {
			salesorderBean.viewSalesOrderDetailPrint(salesOrderId);
			this.setBalanceAmount(null);
			this.setReceivedAmount(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String printServerURL =""; 
				//"/" + servername + "/PrintKot?salesOrderId="
				//+ salesOrderId;

		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext reqcontext = RequestContext.getCurrentInstance();
		reqcontext.addCallbackParam("validity", valid);
		reqcontext.addCallbackParam("salesOrderId", this.getSalesOrderId());
		reqcontext.addCallbackParam("printname", printerName);
		reqcontext.addCallbackParam("printServerURL", printServerURL);
	}
	
	public void saveBarKitchenOrder() {
		String servername = config
				.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
		String printerName = config
				.getValue(IConfiguration.PROJECT_PRINTER_NAME);
		boolean valid = true;
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		
		
		
		if (kitchenValidation()) {
			valid = sinfo.saveKitchenOrder();
			if(valid==true) {
				
			}
			this.resetPos();
		}
	}

	public void saveKitchenOrder() {
		String servername = config
				.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
		String printerName = config
				.getValue(IConfiguration.PROJECT_PRINTER_NAME);
		boolean valid = true;
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		
		OrderBean orderBean=(OrderBean) BeanContext.getReference("orderBean");
		
		if(this.getSalesorder().getCardNo()==null || this.getSalesorder().getCardNo().isEmpty()) {
			
			orderBean.setCardNoView("orderlist");
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cardNoDlg').show();");
		}else {
			
			if (kitchenValidation()) {
				valid = sinfo.saveKitchenOrder();
				if(valid==true) {
					
				}
				FacesContext context = FacesContext.getCurrentInstance();
	
				HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
				String userAgent = request.getHeader("user-agent");
				
				if(userAgent.matches(".*Android.*")) {
					// do nothiing 
				}else {
					projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
				}
				if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("Waiter")) {
					projectHome.userHome();	
				}else {//cashier kitchen order
					orderBean.resetTerminalOrder();
					projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
				}
				
				
			}
		}
	}

	public void updateBarKitchenorder() {
		String servername = config
				.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
		String printerName = config
				.getValue(IConfiguration.PROJECT_PRINTER_NAME);
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		boolean valid = true;
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		if (kitchenValidation()) {
			valid = sinfo.updateKitchenorder();
		}
		this.resetPos();

	}
	
	public void updateKitchenorder() {
		String servername = config
				.getValue(IConfiguration.PROJECT_INSTANCE_SERVER);
		String printerName = config
				.getValue(IConfiguration.PROJECT_PRINTER_NAME);
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		boolean valid = true;
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		if (kitchenValidation()) {
			valid = sinfo.updateKitchenorder();
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("Waiter")) {
				projectHome.userHome();	
			}else {//cashier kitchen order
				OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
				orderBean.resetTerminalOrder();
				projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
			}
			
		}

	}

	public void holdPOS() {
		boolean valid = true;
		SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
		valid = sinfo.holdPOSSalesOrder();
	}

	public void editCounter(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ProductBean productBean = (ProductBean) BeanContext
				.getReference("productBean");
		// this.setMessage(null);
		try {
			String counterId = "";
			counterId = (String) event.getComponent().getAttributes()
					.get("counterId").toString();
			this.setPoscounter(poscounterBO.getPoscounterDetails(Integer
					.parseInt(counterId)));
			this.getPoscounter().setStatus("1");
			poscounterBO.updatePoscounter(this.getPoscounter());
			productBean.loadProduct();
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			productCategoryBean.loadAllValidCategory();
			productBean.initProduct();
			resetPos();
			this.setCardOption("normal");
			newPOS();
		} catch (Exception e) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"counterPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
			throw e;
		}
	}

	public void newPOS() {
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
	
		ProjectHome projectHome = (ProjectHome) BeanContext
				.getReference("projectHome");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		projectHome.setTitlePage("Sales --> POS");
		projectHome.setContentpage("/sales/addEditPos.xhtml");
       
    	CustomerdisplayClientEndpoint clientEndpoint = (CustomerdisplayClientEndpoint) BeanContext.getReference("customerdisplayClientEndpoint");
		CustomerdisplayMessageModel message=new CustomerdisplayMessageModel();
		message.setMessageType("INIT POS");
		message.setTerminalName(this.getPoscounter().getCounterNo());
		clientEndpoint.sendMessage(message);

		homeinfo.menuPageRedirect();

	}

	
	public void loadKitchenOrder() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			if (this.getCardNo() != null
					&& (!this.getCardNo().equalsIgnoreCase(""))
					&& this.getCardNo() != "") {
				kitchedOrder = salesOrderBO.getKitchenorderReportList(null,
						cardNo, salesOrderNo, loginBean.getUserName(),
						customerId, branchId, dateFrom, dateTo, "1", loginBean
								.getBranch().getBranchId());

				resetPos();
				if (kitchedOrder != null && kitchedOrder.size() != 0) {
					SalesorderModel data = kitchedOrder.get(0);
					data.setKitchenOrderId(data.getSalesOrderId());
					this.setSalesorder(data);

					this.setCustomer(data.getCustomer());

					for (SalesorderbreakdownModel bData : data
							.getSalesorderbreakdowns()) {
						this.setBarcode(bData.getProductCode());
						this.setItemCount(bData.getQuantity());
						addSalesItem();
					}
				}
			} else {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid CARD No", null));
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void validateCardNo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			boolean inUse=salesOrderBO.cardInUse(this.getSalesorder().getCardNo());
			if(inUse) {
				this.setCardMessageInUse("Card in use !" );
				
			}else {
				this.setCardMessageInUse("  ");
				
			}
		}catch(Exception ex) {
				ex.printStackTrace();
		}
	}
	
	public void addToSalesSplit(ActionEvent event) {
		
		SalesorderbreakdownModel sobd=(SalesorderbreakdownModel) event.getComponent().getAttributes()
				.get("posbreakdown");
		
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes()
				.get("posBreakdownId").toString();
		
		this.setRowId(Integer.parseInt(quotationBreakdownId));
		this.removeItemFromSplitConfirm();
		
		addKitchenItemToSales(sobd);
		
	}

	
	public void loadBarKitchenOrderforSaleSplit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			if (this.getCardNo() != null
					&& (!this.getCardNo().equalsIgnoreCase(""))
					&& this.getCardNo() != "") {
				this.setCurrentTableName(this.getCardNo().toUpperCase());
				//this.setCardNo("TableOrder"+this.getCardNo());
				kitchedOrder = salesOrderBO.getKitchenorderReportList(null,
						cardNo, salesOrderNo, null,
						customerId, branchId, dateFrom, dateTo, "1", loginBean
								.getBranch().getBranchId());
				this.getSalesordersplitbreakdowns().clear();
				resetPos();
				this.setCardSplitInc(0);
				if (kitchedOrder != null && kitchedOrder.size() != 0) {
					cardSplitInc++;
					this.getSalesorderSplit().setCardNo(this.getCardNo());
					this.getSalesorderSplit().setTableName(this.getTableName());
					this.getSalesorderSplit().getSplitInc();
					SalesorderModel data = kitchedOrder.get(0);
					
					data.setKitchenOrderId(data.getSalesOrderId());
					this.setSalesorderSplit(data);
					
					
					this.setCustomer(data.getCustomer());
					
//moo
					for (SalesorderbreakdownModel bData : data
							.getSalesorderbreakdowns()) {
						//this.setBarcode(bData.getProductCode());
						//this.setItemPrice(bData.getSubTotal());
						//this.setItemCount(bData.getQuantity());
						//addSalesItem();
						splitbreakdownids.add(bData.getSalesOrderBreakdownId());
						if(bData.getQuantity().compareTo(BigDecimal.ONE)>0) {
							BigDecimal unitPrice=bData.getSubTotal().divide(bData.getQuantity());
							Integer qty=bData.getQuantity().intValue();
							while(qty>0) {
								
								bData.setUnitPrice(unitPrice);
								bData.setQuantity(BigDecimal.ONE);
								bData.setSubTotal(unitPrice);
								qty--;
								addKitchenItemToSalesSplit(bData);
							}
							
							
						}else {
							addKitchenItemToSalesSplit(bData);
						}
						
					}
				
					this.setTableName(null);
					this.setCardNo(null);
				}else {
					this.setCurrentTableName("");
				}
			} else {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid TABLE NO", null));
			}

		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
	
	public void loadBarKitchenOrderforSale() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			if (this.getCardNo() != null
					&& (!this.getCardNo().equalsIgnoreCase(""))
					&& this.getCardNo() != "") {
				this.setCardNo("TableOrder"+this.getCardNo());

				kitchedOrder = salesOrderBO.getKitchenorderReportList(null,
						cardNo, salesOrderNo, null,
						customerId, branchId, dateFrom, dateTo, "1", loginBean
								.getBranch().getBranchId());
				if(this.getCardOption().equals("merge")) {
					// do nothing
				}else {
					resetPos();
				}
				
				if (kitchedOrder != null && kitchedOrder.size() != 0) {
					this.getSalesorder().setCardNo(this.getCardNo());
					this.getSalesorder().setTableName(this.getTableName());
					SalesorderModel data = kitchedOrder.get(0);
					
					if(!this.salesId.contains(data.getSalesOrderId())){
						this.cardNoList+=data.getCardNo()+",";
						this.tableNameList+=data.getTableName()+",";
						data.setKitchenOrderId(data.getSalesOrderId());
						//this.getSalesorder().setCardNo(this.getCardNo());
						//this.getSalesorder().setTableName(this.getTableName());
						this.setSalesorder(data);
						this.salesId.add(data.getSalesOrderId());
						this.setCustomer(data.getCustomer());
						this.getSalesorder().setCardNo(this.getCardNoList());
						this.getSalesorder().setTableName(this.getTableNameList());
//moo
						for (SalesorderbreakdownModel bData : data
								.getSalesorderbreakdowns()) {
							//this.setBarcode(bData.getProductCode());
							//this.setItemPrice(bData.getSubTotal());
							//this.setItemCount(bData.getQuantity());
							//addSalesItem();
							addKitchenItemToSales(bData);
						}
					}
					this.setTableName(null);					
					this.setCardNo(null);
				}
			} else {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid TABLE NO", null));
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
	
	public void loadKitchenOrderforSale() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			if (this.getCardNo() != null
					&& (!this.getCardNo().equalsIgnoreCase(""))
					&& this.getCardNo() != "" && !this.getCardNo().equals("0")) {
				kitchedOrder = salesOrderBO.getKitchenorderReportList(null,
						cardNo, salesOrderNo, null,
						customerId, branchId, dateFrom, dateTo, "1", loginBean
								.getBranch().getBranchId());
				if(this.getCardOption().equals("merge")) {
					// do nothing
				}else if(this.getCardOption().equals("split")){
					resetPos();
				}else {
					resetPos();
				}

				if (kitchedOrder != null && kitchedOrder.size() != 0) {
					SalesorderModel data = kitchedOrder.get(0);
					
					if(!this.salesId.contains(data.getSalesOrderId())){
						data.setKitchenOrderId(data.getSalesOrderId());
						if(this.cardNoList.length()>0) {
							this.cardNoList+=",";
						}
						this.cardNoList+=data.getCardNo();
						
						this.setSalesorder(data);
						this.salesId.add(data.getSalesOrderId());
						this.setCustomer(data.getCustomer());
						
						for (SalesorderbreakdownModel bData : data
								.getSalesorderbreakdowns()) {
							addKitchenItemToSales(bData);
						}
					}
					this.setCardNo(null);

				}
			} else {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid CARD No", null));
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
	
	public void editSalesOrder(SalesorderModel salesOrderEdit) {
		
		try {
			SalesorderModel data = salesOrderBO.getSalesorderDetails(salesOrderEdit.getSalesOrderId(),salesOrderEdit.getSalesType());			
			this.setSalesorder(data);
			this.setCustomer(data.getCustomer());
			for (SalesorderbreakdownModel bData : data
					.getSalesorderbreakdowns()) {
				addKitchenItemToSales(bData);
			}
			this.setPosOption("refund");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateSalesOrder() {
		try {
			SalesorderBeanInfo sinfo = new SalesorderBeanInfo();
			sinfo.updatePOSSalesOrder();
			
			SalesorderBean salesorderBean = (SalesorderBean) BeanContext
					.getReference("salesorderBean");
			salesorderBean.viewSalesOrderDetailPrint(salesOrderId);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void refundSalesItem(SalesorderbreakdownModel orderedItem) {
		
		ProductModel product=new ProductModel();
		product.setProductId(orderedItem.getProductId());
		this.setProduct(product);
		this.setBarcode(orderedItem.getProductCode());
		this.setItemCount(new BigDecimal(0.00));
		
		this.setSelectedItemQty(1);
		this.setRemarksId(1);
		if(orderedItem.getPacking()!=0) {
			this.setPacking(true);
		}else {
			this.setPacking(false);
		}
		
		this.setItemOrderStatus("refund");
		if(orderedItem.getSetOption()==1) {
			List<SalesorderbreakdownModel> salesSetItemList=new ArrayList<SalesorderbreakdownModel>();
			
			for(SalesorderbreakdownModel setItem:orderedItem.getSalesProductSetList()) {
				SalesorderbreakdownModel refundSetItem=new SalesorderbreakdownModel();
				
				ProductModel refundSetItemProduct=new ProductModel();
				refundSetItemProduct.setProductId(setItem.getProduct().getProductId());
				refundSetItemProduct.setCategoryId(setItem.getProduct().getCategoryId());
				refundSetItemProduct.setProductName(setItem.getProduct().getProductName());
				refundSetItem.setProduct(refundSetItemProduct);
				
				refundSetItem.setDiscount(setItem.getDiscount());
				refundSetItem.setDiscountAmount(setItem.getDiscountAmount());							
				refundSetItem.setProductId(setItem.getProduct().getProductId());						
				refundSetItem.setProductName(setItem.getProduct().getProductName());
				refundSetItem.setProductCode(setItem.getProduct().getBarcode());
				refundSetItem.setUom(setItem.getProduct().getUom());
				refundSetItem.setQuantity(setItem.getQuantity());	
				refundSetItem.setQuantityBefore(setItem.getQuantity());
				refundSetItem.setSubTotal(setItem.getSubTotal());
				refundSetItem.setUnitPrice(setItem.getUnitPrice());

				refundSetItem.setPurchasePrice(setItem.getPurchasePrice());
				refundSetItem.setReturnquantity(setItem.getReturnquantity());
				refundSetItem.setSalesCommission(setItem.getSalesCommission());
				refundSetItem.setTaxAmount(setItem.getTaxAmount());
				refundSetItem.setTaxCode(setItem.getTaxCode());
				
				refundSetItem.setStatus(setItem.getStatus());
				refundSetItem.setPacking(setItem.getPacking());
				refundSetItem.setSetOption(setItem.getSetOption());

				refundSetItem.setStatus("refund");
				
				salesSetItemList.add(refundSetItem);
			}
			this.addSalesItem();
			this.salesorderbreakdowns.get(0).setStatus("refund");
			this.salesorderbreakdowns.get(0).setSalesProductSetList(salesSetItemList);
			this.salesorderbreakdowns.get(0).setSetOption(1);
		}else {
			this.addSalesItem();	
		}
		
		
		
		this.setItemOrderStatus(null);
		
	}
	
	public void loadHoldSale(Integer rowId) {
		FacesContext context = FacesContext.getCurrentInstance();
		PosOptionBean posOptionBean = (PosOptionBean) BeanContext
				.getReference("posOptionBean");
		try {

			List<SalesorderModel> salesOrderList = posOptionBean
					.getSalesOrder();
			resetPos();
			if (salesOrderList != null && salesOrderList.size() != 0) {
				SalesorderModel data = salesOrderList.get(rowId);
				this.setCustomer(data.getCustomer());

				for (SalesorderbreakdownModel bData : data
						.getSalesorderbreakdowns()) {
					this.setBarcode(bData.getProductCode());
					this.setItemCount(bData.getQuantity());
					addSalesItem();
					salesOrderBO.deleteSalesorderbreakdownhold(bData);
				}
				salesOrderBO.deleteSalesorderhold(data);
			} else {
				context.addMessage(
						UIComponent.findComponent(context.getViewRoot(),
								"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid Prescription No", null));
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void printBill() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Map parameters = new HashMap();
			parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					this.getSalesOrderList());
			String reportPath = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getRealPath("/report/newBillReport.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					parameters, beanCollectionDataSource);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=report.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void printBillTest(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Map parameters = new HashMap();
			parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);

			String salesOrderNo = "";
			salesOrderNo = (String) event.getComponent().getAttributes()
					.get("salesOrderNo").toString();
		//	List<SalesorderModel> dataList = salesOrderBO.getSalesorderReportList(salesOrderNo, null, null, null,null, null, loginBean.getBranch().getBranchId());
			List<SalesorderModel> dataList = salesOrderBO.getSalesorderReportList(salesOrderNo, null, null, null, null, null, loginBean.getBranch().getBranchId(), null, null);

			SalesorderModel so = dataList.get(0);
			so = salesOrderBO
					.getSalesorderPosDetails(so.getSalesOrderId(), "3");
			// salesOrder = salesOrderBO.getSalesorderPosDetails(70, "3");

			dataList = new ArrayList<SalesorderModel>();
			dataList.add(so);

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					dataList);
			String reportPath = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getRealPath("/report/newBillReport.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					parameters, beanCollectionDataSource);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=report.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void printCounterBill(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String salesOrderNo = "";
			salesOrderNo = (String) event.getComponent().getAttributes()
					.get("salesOrderNo").toString();
			List<SalesorderModel> dataList = salesOrderBO.getSalesorderReportList(salesOrderNo, null, null, null, null, null, loginBean.getBranch().getBranchId(), null, null);

			
			this.setSalesOrderId(dataList.get(0).getSalesOrderId());
			RequestContext reqcontext = RequestContext.getCurrentInstance();
			reqcontext.addCallbackParam("salesOrderId", this.getSalesOrderId());

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}

	public void compileReport() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {

			String reportDesignPath = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/report/posBill.jrxml");
			InputStream input = new FileInputStream(new File(reportDesignPath));
			JasperDesign jasperDesign = JRXmlLoader.load(input);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}

	}

	public boolean kitchenValidation() {
		boolean valid = true;
		SalesorderBean salesorderBean = (SalesorderBean) BeanContext
				.getReference("salesorderBean");
		String messageValue = null;
		ISupplierBO supplierBO = null;
		ISalesorderBO salesOrderBO = salesorderBean.getSalesOrderBO();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (this.getSalesorder() != null) {
				if (this.getSalesorder().getCardNo() == null
						|| this.getSalesorder().getCardNo()
								.equalsIgnoreCase("")) {
					factoryBean.reportErrorToMessageHandler(
							"Card No Required ", messageValue, "posPanel");
					valid = false;

				}

				if (this.getSalesorderbreakdowns().size() == 0) {
					factoryBean.reportErrorToMessageHandler("Items Required ",
							messageValue, "posPanel");
					valid = false;
				}

				else {
					if (this.salesorder.getSalesOrderId() == 0) {
						if (salesOrderBO.findKitchenCardExites(this
								.getSalesorder().getCardNo())) {
							messageValue = factoryBean.getErrorFactory()
									.getError("kitchen.cardno.exists");
							factoryBean.reportErrorToMessageHandler(
									messageValue, messageValue, "posPanel");
							valid = false;
						}
					} else {

						if (!this
								.getSalesorder()
								.getCardNo()
								.equalsIgnoreCase(
										this.getSalesorder().getOldcardNo())) {

							if (salesOrderBO.findKitchenCardExites(this
									.getSalesorder().getCardNo())) {
								messageValue = factoryBean.getErrorFactory()
										.getError("kitchen.cardno.exists");
								factoryBean.reportErrorToMessageHandler(
										messageValue, messageValue, "posPanel");
								valid = false;
							}
						}
					}
				}

			}

			else {
				factoryBean.reportErrorToMessageHandler("Card No Required ",
						messageValue, "posPanel");
				valid = false;
			}

		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
		return valid;

	}

	public void makeduplicateSales() {
		FacesContext context = FacesContext.getCurrentInstance();

		SalesorderbreakdownModel itemdata = new SalesorderbreakdownModel();

		List<SalesorderbreakdownModel> salesitembreakdowns = new ArrayList<SalesorderbreakdownModel>();

		try {
			for (SalesorderbreakdownModel data : salesorderbreakdowns) {
				// itemdata
			}
		} catch (Exception e) {
			context.addMessage(
					UIComponent
							.findComponent(context.getViewRoot(), "posPanel")
							.getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}

	public void viewKitchenOrder(ActionEvent event) throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			kitchenOrder = (SalesorderModel) event.getComponent().getAttributes().get("sales");
			
			
	
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}	
	
	
	
	public void sendToDisplay(String messageType, List<SalesorderbreakdownModel> orderbreakdown) {
		CustomerdisplayClientEndpoint clientEndpoint = (CustomerdisplayClientEndpoint) BeanContext.getReference("customerdisplayClientEndpoint");
		CustomerdisplayMessageModel message=new CustomerdisplayMessageModel();
		message.setMessageType(messageType);
		message.setTerminalName(this.getPoscounter().getCounterNo());
		message.setTotalAmount(this.getTotalAmount());
		message.setBalanceAmount(this.getBalanceAmount());
		message.setPaidAmount(this.getReceivedAmount());
		message.setTotalBeforediscount(this.getTotalBeforeDiscount());
		
		List<SalesbreakdowndisplayModel> objList=new ArrayList<SalesbreakdowndisplayModel>();
		for(SalesorderbreakdownModel data:orderbreakdown) {
			SalesbreakdowndisplayModel obj=new SalesbreakdowndisplayModel();
			//obj.setId(Integer.parseInt(data.getId()));
			obj.setProductName(data.getProductName());
			obj.setQuantity(data.getQuantity().intValueExact());
			obj.setPrice(data.getSubTotal());
			objList.add(obj);
		}
		message.setSalesbreakdown(objList);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
			// do nothing
		}else {
			//clientEndpoint.sendMessage(message);	
		}
		
	}
	
	public void setPosTerminalPage(Integer counterId) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ProductBean productBean = (ProductBean) BeanContext
				.getReference("productBean");
		// this.setMessage(null);
		try {
			this.setPoscounter(poscounterBO.getPoscounterDetails(counterId))
			;
			this.getPoscounter().setStatus("1");
			
			poscounterBO.updatePoscounter(this.getPoscounter());
			productBean.loadProduct();
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			productCategoryBean.loadAllValidCategory();
			resetPos();
			productBean.initProduct();
			this.setCardOption("normal");
			
			
			ProjectHome projectHome = (ProjectHome) BeanContext
					.getReference("projectHome");
			projectHome.setTitlePage("Sales --> POS");
			projectHome.setContentpage("/sales/addEditPos.xhtml");
	       
	    	CustomerdisplayClientEndpoint clientEndpoint = (CustomerdisplayClientEndpoint) BeanContext.getReference("customerdisplayClientEndpoint");
			CustomerdisplayMessageModel message=new CustomerdisplayMessageModel();
			message.setMessageType("INIT POS");
			message.setTerminalName(this.getPoscounter().getCounterNo());
			clientEndpoint.sendMessage(message);
			
		} catch (Exception e) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"counterPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
			throw e;
		}
	}
	
	public void togglePosOption(String option) {
		if(option.equalsIgnoreCase("take-away")) {
			
			
			CommonListBean commonListBean = (CommonListBean) BeanContext
					.getReference("commonListBean");
			ICommonListBO commonListBO=commonListBean.getCommonListBO();
			AutonumModel autonumModel=new AutonumModel();
			try {
				
				autonumModel=commonListBO.getAutonumList(loginBean.getBranch().getBranchId()).get(0);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.getSalesorder().setTableName("Take Away");
			this.getSalesorder().setCardNo(""+autonumModel.getTakeAwayNo());
			this.getSalesorder().setCustomerName(null);

		}
		
		this.setPosOption(option);
	}
	
	public void loadPaymentType(String type,String value) {
		if(type.equalsIgnoreCase("card")) {
			this.salesorder.setPaymentType("Card");
			this.paymentModel.setCardType(value);
		}
	}
	
	public void addSalesPriceModifyItemV2(SalesorderbreakdownModel sobd) {
		SalesorderbreakdownModel item = new SalesorderbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {

			item = sobd;
			List<ProductModel> productList = new ArrayList<ProductModel>();
			
			productList = productBO.getProductList(null, null, null, item.getProductCode(), null, 0, 1, loginBean.getBranch()
					.getBranchId(), null, null, null, null);
			
			if (productList.size() == 0) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null,	"Barcode not Exists"));
			} else {
				producttemp = productList.get(0);
				productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(), loginBean.getBranch().getBranchId());

				if (ValidatorUtil.isNotNull(this.getPriceQty().get(item.getSno()))) {					
					String price=""+this.getPriceQty().get(item.getSno());
					item.setUnitPrice(new BigDecimal(price));
				
				}

				if (ValidatorUtil.isNotNull(this.getItemcountQty().get(item.getSno()))) {
					
					String qty=""+this.getItemcountQty().get(item.getSno());
					item.setQuantity(new BigDecimal(qty));
				}

				if (productPriceList != null && productPriceList.size() == 1) {
					productPriceList.add(productPriceList.get(0));
				}				
				
				if(producttemp.getNormalPrice().compareTo(BigDecimal.ZERO) != 0) 
				{
					if(item.getPacking()==1) {
						item.setUnitPrice(producttemp.getPackingPrice());
					}
					else {
						item.setUnitPrice(producttemp.getNormalPrice());
					}
				}
				BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
				BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				taxAmt= DecimalUtil.formatRoundupCents(taxAmt);
				if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
				{
					priceAmt=priceAmt.add(taxAmt);
				}
				else
				{
					BigDecimal itemTax= producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
					item.setUnitPrice(item.getUnitPrice().subtract(itemTax));
					item.setUnitPrice(DecimalUtil.formatRoundupCents(item.getUnitPrice()));

				}
				
				BigDecimal singletaxAmt = producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				
				item.setTaxAmount(taxAmt);		
				item.setUnitPricetax(item.getUnitPrice());
				this.priceQty.put(item.getSno(), item.getUnitPrice());

				
				int qtyLatest=item.getQuantity().intValue();
				int qtyBefore=item.getQuantityBefore().intValue();
				
				if(item.getStatus()==null)
					item.setQuantityBefore(item.getQuantity());
				
				BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				
				item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));				
				
				item.setDiscountAmount(new BigDecimal(0.00));
				
				BigDecimal tmpSubTotal = DecimalUtil.formatRoundupCents(priceAmt);
				
				item.setSubTotal(tmpSubTotal);

				salesorderbreakdowns.set(rowId, item);
				
				
				extractQuoteTotal();
				
				resetAddItem();
			}
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				// do nothing
			}else {
				sendToDisplay("MODIFY", this.getSalesorderbreakdowns());
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(), "posPanel").getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
	
	/*
	 
	  
	  
			List<ProductModel> productList = new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, data.getProductCode(), null, 0, 1, loginBean.getBranch()
					.getBranchId(), null, null, null, null);
			producttemp = productList.get(0);

			item.setProduct(producttemp);
			item.setProductId(producttemp.getProductId());
			item.setProductName(producttemp.getProductName());
			item.setUom(producttemp.getUom());
			item.setExpiryDate(producttemp.getExpiryDate());
			item.setDiscount("" + producttemp.getDiscount());
			item.setTaxCode(producttemp.getTaxCode());
			

			if(producttemp.getNormalPrice().compareTo(BigDecimal.ZERO) != 0) 
			{
				if(item.getPacking()==1) {
					item.setUnitPrice(producttemp.getPackingPrice());
				}
				else {
					item.setUnitPrice(producttemp.getNormalPrice());
				}
			}
			
			BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantity());
			
			BigDecimal taxAmt = producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
			taxAmt= DecimalUtil.formatRoundupCents(taxAmt);

			if(producttemp.getTaxType()!=null && producttemp.getTaxType().equalsIgnoreCase("Exclusive"))
			{
				priceAmt=priceAmt.add(taxAmt);
			}else
			{
				BigDecimal itemTax= producttemp.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				item.setUnitPrice(item.getUnitPrice().subtract(itemTax));
				item.setUnitPrice(DecimalUtil.formatRoundupCents(item.getUnitPrice()));

			}
			item.setTaxAmount(taxAmt);

			BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((item.getUnitPrice().divide(new BigDecimal(100))));
			item.setUnitduplicatePrice(item.getUnitPrice().subtract(unitpriceAmt));
			item.setSno(sno);
			item.setProduct(producttemp);
			
			BigDecimal tmpSubTotal = extractSubtotal(
					item.getUnitPrice(),
					item.getDiscountAmount(),
					item.getQuantity(),
					producttemp.getTaxCode());
		//	tmpSubTotal = priceAmt; // new
			//item.setSubTotal(tmpSubTotal);
			item.setSubTotal(DecimalUtil.formatRoundupCents(data.getSubTotal().abs()));
			this.priceQty.put(sno,item.getUnitPrice());
			this.itemcountQty.put(sno,item.getQuantity());
			if(data.getStatus().equalsIgnoreCase("canceled")) {
				item.setTaxAmount(item.getTaxAmount().negate());
				priceAmt=priceAmt.negate();
				taxAmt=taxAmt.negate();
				item.setSubTotal(item.getSubTotal().negate());
			}
			
			this.getDynamicSalesItemList(item);
		
			sno = sno + 1;

	
			extractQuoteTotal();
			Collections.sort(salesorderbreakdowns, new PosSalesSorder());
			resetAddItem();
			
			sendToDisplay("ADD SALES ITEM",this.getSalesorderbreakdowns());

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	 * 
	 */
	
}
