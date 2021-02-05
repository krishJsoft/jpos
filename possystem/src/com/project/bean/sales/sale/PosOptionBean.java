package com.project.bean.sales.sale;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.project.bean.admin.PoscounterBean;
import com.project.bean.admin.ShiftBean;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.IExpensesBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.IShiftBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;

import com.project.login.LoginBean;
import com.project.modal.report.PoscounterreportModal;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.sale.sales.PaymentCollectionModel;
import com.project.model.datamodel.ShiftModel;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.scheduledtask.EmailDailyReportAdmin;
import com.project.scheduledtask.GmKlangEmail;
import com.project.util.DateUtil;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 Nov 2013
 * 
 */

@ManagedBean(name = "posOptionBean")
@SessionScoped
public class PosOptionBean {

	PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();
	private String salesType = "3";
	List<SalesorderbreakdownModel> salesorderbreakdowns = new ArrayList<SalesorderbreakdownModel>();
	List<PoscashtransactionModel> poscashtransactionList = new ArrayList<PoscashtransactionModel>();
	List<PoscashtransactionModel> poscashtransactionListObj = new ArrayList<PoscashtransactionModel>();
	List<PoscashtransactionModel> poscashtransactionItems = new ArrayList<PoscashtransactionModel>();
	List<PoscounterreportModal> poscounterReport = new ArrayList<PoscounterreportModal>();


	List<SalesorderModel> salesOrder = new ArrayList<SalesorderModel>(); 
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext
			.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	PosBean posBean = (PosBean) BeanContext.getReference("posBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	String tempFileName="Test";
	
	IPoscounterBO poscounterBO=objectMapController.getPoscounterBO();
	ICommonListBO commonListBO=objectMapController.getCommonListBO();
	ISalesorderBO salesOrderBO = objectMapController.getSalesOrderBO();
	IExpensesBO expensesBO=objectMapController.getExpensesBO();
	private IShiftBO shiftBO=objectMapController.getShiftBO();

	PoscounterModel poscounter = new PoscounterModel();
	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	private String xreportLocation=servletContext.getRealPath("") + File.separator+ "document" + File.separator + "purchase_order" + File.separator+tempFileName+".pdf";
	

	private Date dateFrom;
	private Date dateTo;
	private Date datesFrom;
	private Date datesTo;
	private Date shiftDate=new Date();
	private String status;
	private Integer counterId;
	private Integer cashid;
	private String transactionStatus;
	boolean paymentConfirm=true;
	boolean validity=true;
	private Integer rowId;
	private BigDecimal totalamount = new BigDecimal(0.00);
	private BigDecimal totalCashAmount=new BigDecimal(0.00);
	private BigDecimal totalCreditCardAmount=new BigDecimal(0.00);
	private BigDecimal totalChequeAmmount=new BigDecimal(0.00);
	private BigDecimal totalVoucherAmmount=new BigDecimal(0.00);
	private BigDecimal totalLoyaltyAmmount=new BigDecimal(0.00);
	private Date counterReportFrom;
	private Date counterReportTo;
	private Integer counterReportCounterId;
	
	private BigDecimal totalExpenses = new BigDecimal(0.00);
	private String shiftName;


	private Integer shiftId;
	
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
	
	

	public Date getDatesFrom() {
		return datesFrom;
	}

	public void setDatesFrom(Date datesFrom) {
		this.datesFrom = datesFrom;
	}

	public Date getDatesTo() {
		return datesTo;
	}

	public void setDatesTo(Date datesTo) {
		this.datesTo = datesTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}
	
	public Integer getCashid() {
		return cashid;
	}

	public void setCashid(Integer cashid) {
		this.cashid = cashid;
	}

	public String getXreportLocation() {
		return xreportLocation;
	}

	public void setXreportLocation(String xreportLocation) {
		this.xreportLocation = xreportLocation;
	}

	public boolean isPaymentConfirm() {
		return paymentConfirm;
	}

	public void setPaymentConfirm(boolean paymentConfirm) {
		this.paymentConfirm = paymentConfirm;
	}

	public PoscashtransactionModel getPoscashtransaction() {
		return poscashtransaction;
	}

	public void setPoscashtransaction(PoscashtransactionModel poscashtransaction) {
		this.poscashtransaction = poscashtransaction;
	}	
	
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public List<PoscashtransactionModel> getPoscashtransactionListObj() {
		return poscashtransactionListObj;
	}

	public void setPoscashtransactionListObj(
			List<PoscashtransactionModel> poscashtransactionListObj) {
		this.poscashtransactionListObj = poscashtransactionListObj;
	}	
	

	public List<SalesorderModel> getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(List<SalesorderModel> salesOrder) {
		this.salesOrder = salesOrder;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}	

	public PoscounterModel getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(PoscounterModel poscounter) {
		this.poscounter = poscounter;
	}
	
	

	public List<PoscashtransactionModel> getPoscashtransactionItems() {
		return poscashtransactionItems;
	}

	public void setPoscashtransactionItems(
			List<PoscashtransactionModel> poscashtransactionItems) {
		this.poscashtransactionItems = poscashtransactionItems;
	}	
	

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	
	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}

	public BigDecimal getTotalCashAmount() {
		return totalCashAmount;
	}

	public void setTotalCashAmount(BigDecimal totalCashAmount) {
		this.totalCashAmount = totalCashAmount;
	}

	public BigDecimal getTotalCreditCardAmount() {
		return totalCreditCardAmount;
	}

	public void setTotalCreditCardAmount(BigDecimal totalCreditCardAmount) {
		this.totalCreditCardAmount = totalCreditCardAmount;
	}

	public BigDecimal getTotalChequeAmmount() {
		return totalChequeAmmount;
	}

	public void setTotalChequeAmmount(BigDecimal totalChequeAmmount) {
		this.totalChequeAmmount = totalChequeAmmount;
	}

	public BigDecimal getTotalVoucherAmmount() {
		return totalVoucherAmmount;
	}

	public void setTotalVoucherAmmount(BigDecimal totalVoucherAmmount) {
		this.totalVoucherAmmount = totalVoucherAmmount;
	}

	public BigDecimal getTotalLoyaltyAmmount() {
		return totalLoyaltyAmmount;
	}

	public void setTotalLoyaltyAmmount(BigDecimal totalLoyaltyAmmount) {
		this.totalLoyaltyAmmount = totalLoyaltyAmmount;
	}
	
	public BigDecimal getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(BigDecimal totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	

	public Date getCounterReportFrom() {
		return counterReportFrom;
	}

	public void setCounterReportFrom(Date counterReportFrom) {
		this.counterReportFrom = counterReportFrom;
	}

	public Date getCounterReportTo() {
		return counterReportTo;
	}

	public void setCounterReportTo(Date counterReportTo) {
		this.counterReportTo = counterReportTo;
	}

	public Integer getCounterReportCounterId() {
		return counterReportCounterId;
	}

	public void setCounterReportCounterId(Integer counterReportCounterId) {
		this.counterReportCounterId = counterReportCounterId;
	}

	public List<PoscounterreportModal> getPoscounterReport() {
		return poscounterReport;
	}

	public void setPoscounterReport(List<PoscounterreportModal> poscounterReport) {
		this.poscounterReport = poscounterReport;
	}
	
	public void cashCredit()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {
			  
				if(validate1())
				{
					
					this.poscashtransaction.setLastupdatedBy(loginBean.getLogdetail().getEmailAddress());
					this.poscashtransaction.setLastupdatedDate(new Date());
					this.poscashtransaction.setType("1");					
					this.poscashtransaction.setCreditamount(poscashtransaction.getAmount());
					this.poscashtransaction.setStatus("1");
					poscashtransaction.setTransactionType("0");
					poscashtransaction.setTransactionStatus("1");						
					poscashtransaction.setPaymentCount(1);
					poscashtransaction.setPaymentType("Cash");
					this.poscashtransaction.setBranchRecordId(loginBean.getBranch().getBranchId());
					this.poscashtransaction.setBranchtype(loginBean.getBranch().getBranchtype());
					this.poscashtransaction.setReceivedAmount(poscashtransaction.getAmount());
					
					
					poscounterBO.createNewPoscashtransaction(poscashtransaction);
					resetPosTransaction();
					this.setTransactionStatus("1");
					getPoscashtransactionSupList();
					
				}
			}
		catch (Exception e) 
	 	{		
	 		System.out.println(e.toString());
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
	}
	
	
	public void cashDebit()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		boolean updateSuccess=true;
		try {
			poscounter=posBean.getPoscounter();
			
			if(paymentvalidate())
			{
				for(PoscashtransactionModel data:poscashtransactionItems)
				{				
				updateSuccess=poscounterBO.createNewPoscashtransaction(data);
				}				
				if(updateSuccess)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_INFO,"Payment Success",null));
					resetPosTransaction();					
				}				
			}		
			}
		catch (Exception e) 
	 	{			 		
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
	}
	
	
	public void addPaymentItem()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		boolean updateSuccess=true;
		try {
			poscounter=posBean.getPoscounter();
			
			if(validate())
			{
				this.poscashtransaction.setCounterNo(poscounter.getCounterNo());
				this.poscashtransaction.setLastupdatedBy(loginBean.getLogdetail().getEmailAddress());
				this.poscashtransaction.setLastupdatedDate(new Date());
				this.poscashtransaction.setType("0");
				this.poscashtransaction.setStatus("1");
				poscashtransaction.setTransactionType("0");
				this.poscashtransaction.setCounterId(poscounter.getCounterId());
				this.poscashtransaction.setDebitAmount(poscashtransaction.getAmount());
				this.poscashtransaction.setTransactionStatus("1");
				this.poscashtransaction.setBranchRecordId(loginBean.getBranch().getBranchId());
				this.poscashtransaction.setBranchtype(loginBean.getBranch().getBranchtype());
				
				poscashtransactionItems.add(this.poscashtransaction);				
				poscashtransaction = new PoscashtransactionModel();
				calculateTotalAmount();
			}		
			}
		catch (Exception e) 
	 	{			 		
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}
		
	}
	
	
	public void removePaymentItem(ActionEvent event)
	{
		String paymentId = "";
		paymentId = (String) event.getComponent().getAttributes().get("paymentId").toString();
		this.setRowId(Integer.parseInt(paymentId));
	}
	
	public void removePaymentItemConfirm()
	{
		PoscashtransactionModel c = poscashtransactionItems.get(this.getRowId());
		poscashtransactionItems.remove(c);	
		calculateTotalAmount();
	}	
	
	
	public void calculateTotalAmount()
	{
		totalamount =new  BigDecimal(0.00);
		for(PoscashtransactionModel data:poscashtransactionItems)
		{
			totalamount=totalamount.add(data.getAmount());
		}
		
	}
	
	
	public boolean paymentvalidate()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean valid=true;
		RequestContext reqcontext = RequestContext.getCurrentInstance(); 
		try {
			if(totalamount==null)
			{
				valid=false;
				this.setValidity(false);
			}
			if(totalamount!=null)
			{
				if(totalamount.doubleValue()==0.00)
				{
					valid=false;
					this.setValidity(false);
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"cashamount").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter  Amount",null));
				}
				
			}
			
			if(valid)
			{
				valid=true;
				this.setValidity(true);
			}
			
			reqcontext.addCallbackParam("validity", this.isValidity());
		}
		
			
		catch (Exception e) 
	 	{		
	 		System.out.println(e.toString());
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
		return valid;
	}
	
	
	
	
	
	public boolean validate()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean valid=true;
		RequestContext reqcontext = RequestContext.getCurrentInstance(); 
		try {
			if(poscashtransaction.getAmount()==null)
			{
				valid=false;
				this.setValidity(false);
			}
			if(poscashtransaction.getAmount()!=null)
			{
				if(poscashtransaction.getAmount().doubleValue()==0.00)
				{
					valid=false;
					this.setValidity(false);
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"cashamount").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Valid Amount",null));
				}
				
			}
			
			if(this.poscashtransaction.getPaymentType()!=null && this.poscashtransaction.getPaymentType().equalsIgnoreCase("0") )
			{
				this.setValidity(false);
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"paymentType").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Payment Type",null));
			
			}
			if(this.poscashtransaction.getCollectedBy()!=null && this.poscashtransaction.getCollectedBy().equalsIgnoreCase("0"))
			{
				this.setValidity(false);
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"collectedBy").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Collected By",null));
			
			}
			
			if(valid)
			{
				valid=true;
				this.setValidity(true);
			}
			
			reqcontext.addCallbackParam("validity", this.isValidity());
		}
		
			
		catch (Exception e) 
	 	{		
	 		System.out.println(e.toString());
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
		return valid;
	}
	
	
	public boolean validate1()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean valid=true;
	    RequestContext reqcontext = RequestContext.getCurrentInstance();  
		try {
			if(poscashtransaction.getAmount()==null)
			{
				valid=false;
				this.setValidity(false);
			}
			if(poscashtransaction.getAmount()!=null)
			{
				if(poscashtransaction.getAmount().doubleValue()==0.00)
				{
					valid=false;
					this.setValidity(false);
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"cashamount").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Valid Amount",null));
				}				
			}
			
			if(this.poscashtransaction.getCounterId()==0)
			{
				this.setValidity(false);
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"counterId1").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Terminal No",null));
			
			}			
			if(valid)
			{
				valid=true;
				this.setValidity(true);
			}
			
			 reqcontext.addCallbackParam("validity", this.isValidity());
		}
		
			
		catch (Exception e) 
	 	{		
	 		System.out.println(e.toString());
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
		return valid;
	}
	
	
	public void resetPosTransaction()
	{
		poscashtransaction = new PoscashtransactionModel();
		poscashtransactionItems.clear();
		totalamount =new  BigDecimal(0.00);
	}
	
	
	

	public List<SalesorderModel> getPosholdList() {
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		salesOrder.clear();
		poscounter=posBean.getPoscounter();
		try {			
			salesOrder=salesOrderBO.getSalesorderholdList(null,poscounter.getCounterId(),loginBean.getBranch().getBranchId());			
		}
		catch (Exception e) 
		{		
	    context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 	new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));	 		
	 	}	 		
		return salesOrder;
	}
	
	
	
	public List<PoscashtransactionModel> getPoscashtransactionList() {
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		poscashtransactionListObj.clear();
		poscounter=posBean.getPoscounter();
		try {			
		poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(poscounter.getCounterId(), "1","1",null,null,"2",loginBean.getBranch().getBranchId());			
		}
		catch (Exception e) 
		{		
	    context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 	new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));	 		
	 	}	 		
		return poscashtransactionListObj;
	}
	
	

	public void setPoscashtransactionList(
			List<PoscashtransactionModel> poscashtransactionList) {
		this.poscashtransactionList = poscashtransactionList;
	}
	
	
	public List<PoscashtransactionModel> getPoscashtransactionSupList() {
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		poscashtransactionListObj.clear();
		poscounter=posBean.getPoscounter();
		try {	
		if(!this.validateTransactionSearch())	
		{					
		Date dateNow = new Date ();				 
	    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
	    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());	
		this.setTransactionStatus("1");
		}	
		poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(this.getCounterId(),null,this.getStatus(),this.getDateFrom(),this.getDateTo(),this.getTransactionStatus(),loginBean.getBranch().getBranchId());			
		
		}
		catch (Exception e) 
		{		
	    context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 	new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));	 		
	 	}	 		
		return poscashtransactionListObj;
	}
	
	
	public void resetCashcollection()
	{
		Date dateNow = new Date ();				 
	    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
	    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());	
		
		this.setTransactionStatus("0");
		this.setCounterId(null);
		getPoscashtransactionSupList();
		//this.setTransactionStatus("1");
	}
	
	
	
	public void counterCloseData()	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		poscounter=posBean.getPoscounter();
	    ShiftBean shiftBean = (ShiftBean) BeanContext.getReference("shiftBean");
		try {		
			ShiftModel shift=shiftBean.getShiftBO().loadShift(this.getShiftId());
		    
		    Date shiftDate=this.getShiftDate();
		    
		    
		    this.setShiftName(shift.getShiftName());

		    String in=shift.getTimein()+"";
		    String out=shift.getTimeout()+"";
		    
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(shiftDate);
		    cal.set(Calendar.HOUR_OF_DAY,shift.getTimein().getHours());
		    cal.set(Calendar.MINUTE,shift.getTimein().getMinutes());
		    cal.set(Calendar.SECOND,0);
		    cal.set(Calendar.MILLISECOND,0);

		    Date timeIn = cal.getTime();
		    cal.set(Calendar.HOUR_OF_DAY,shift.getTimeout().getHours());
		    cal.set(Calendar.MINUTE,shift.getTimeout().getMinutes());
		    cal.set(Calendar.SECOND,59);
		    Date timeOut= cal.getTime();
		    
		    Calendar cal2 = Calendar.getInstance();
		    cal2.setTime(timeIn);
		    if (cal2.get(Calendar.AM_PM) == Calendar.PM && cal.get(Calendar.AM_PM) == Calendar.AM) {
		    	cal.setTime(timeOut);
		    	cal.add(Calendar.DATE,1);
		    	timeOut=cal.getTime();
		    	
		    }
		    
			//poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), timeIn, timeOut, null,null,loginBean.getBranch().getBranchId());
		    
		    PoscashtransactionModel obj=new PoscashtransactionModel();
		    // SALES 
			List<PoscashtransactionModel> posCounterData=poscounterBO.getposcounterReportDaily(poscounter.getCounterId(), timeIn, timeOut, 
					null,null,"1",loginBean.getBranch().getBranchId());
			for(PoscashtransactionModel data:posCounterData) {
				obj.setSalesamount(data.getCreditamount());
				obj.setTotalTax(data.getTotalTax());
				
			}
			
			// CASH IN CASH OUT
			posCounterData=poscounterBO.getposcounterReportDaily(poscounter.getCounterId(), timeIn, timeOut, 
					null,null,"0",loginBean.getBranch().getBranchId());
			for(PoscashtransactionModel data:posCounterData) {
				obj.setCreditamount(data.getCreditamount());
				obj.setDebitAmount(data.getDebitAmount());
			}
			
			this.poscashtransaction=obj;
			BigDecimal expensesTotalAmount=new BigDecimal("0");
			List<ExpensesTransactionModel> expList=expensesBO.getExpensesTransactionList(poscounter.getCounterId(),timeIn,timeOut,loginBean.getBranch().getBranchId());
			for(ExpensesTransactionModel data:expList) {
				
				expensesTotalAmount=expensesTotalAmount.add(data.getAmmount());
			}
			this.setTotalExpenses(expensesTotalAmount);
			
			//poscashtransaction.setBalanceAmount(poscashtransaction.getCreditamount().subtract(poscashtransaction.getDebitAmount())); //
			//poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount())); //gop

			if(poscashtransaction!=null)
			{
		
				if(poscashtransaction.getBalanceAmount().doubleValue()==0.00)
				{
					this.setPaymentConfirm(false);				
				}
				else
				{
					this.setPaymentConfirm(true);				
				}
			}
			else
			{
				poscashtransaction= new PoscashtransactionModel();	 
			}
		}
		catch (Exception e) 
		{		
	    context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
	 	new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));	 		
	 	}	 		
	}
	
	
	public void counterClose()	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {
			poscounter=posBean.getPoscounter();
			poscounter.setCurrentbalance(new BigDecimal(0.00));
			poscounterBO.updatePosCounterClose(null, poscounter);
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");	
			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");	
			projectHome.setTitlePage("Sales --> POS");
			projectHome.setContentpage("/sales/pos/posCounterSelect.xhtml");
			loginBean.setForcereset(0);
			poscounterBean.searchCounter();
			
			}
			catch (Exception e) 
			{		
		    context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
		 	new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));	 		
		 	}	 		
	}
	
	
	public BigDecimal getSalesConterTotal(List<PoscashtransactionModel> poscashtransactionListObj )
	{
		BigDecimal amount = new BigDecimal("0.00");
		
		for(PoscashtransactionModel data:poscashtransactionListObj)
		{
			amount=amount.add(data.getCreditamount());
		}
		return amount;
	}
	
	
	public void approveCashTransaction(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String cashid = "";
		cashid = (String) event.getComponent().getAttributes().get("cashid").toString();
		this.setCashid(Integer.parseInt(cashid));	
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
	}
	}
	
	
	public void approveCashTransactionConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {						
			PoscashtransactionModel poscashtransaction=poscounterBO.getPoscashtransactionDetails(cashid);
			poscashtransaction.setApprovedBy(loginBean.getLogdetail().getEmailAddress());
			poscashtransaction.setApprovedDate(new Date());
			poscashtransaction.setTransactionStatus("2");
			updateSuccess=poscounterBO.approvePoscashtransaction(poscashtransaction);
			
			if (updateSuccess) {
				getPoscashtransactionSupList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Approved Success",null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public boolean validateTransactionSearch() {
		boolean valid = true;
		PosOptionBean posOptionBean = (PosOptionBean) BeanContext.getReference("posOptionBean");		
	
		
		if(posOptionBean.getCounterId()!=null)
		{
			if(posOptionBean.getCounterId()==0)
			{
				posOptionBean.setCounterId(null);
			}
		}		
		if(posOptionBean.getTransactionStatus()!=null)
		{
			if(posOptionBean.getTransactionStatus().equalsIgnoreCase("") || posOptionBean.getTransactionStatus().equalsIgnoreCase("0") )
			{
				posOptionBean.setTransactionStatus(null);
			}
		}		
		
		if(posOptionBean.getCounterId()==null && posOptionBean.getDateFrom()==null && posOptionBean.getDateTo()==null && posOptionBean.getTransactionStatus()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	public void pdf(){      		        
      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			//listOfShoppingCart = commonListBO.getShoppingCartList();
			//JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfShoppingCart);
	        //String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/report.jasper");
	       // JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
	        //HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	      //  ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	      //  httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
	       // ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
	        //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	       // JasperExportManager.exportReportToPdfFile(jasperPrint, servletContext.getRealPath("") + File.separator+ "document" + File.separator + "purchase_order" + File.separator	+tempFileName+".pdf");
	      //  this.setXreportLocation("document" + File.separator + "purchase_order" + File.separator+tempFileName+".pdf");
	       // FacesContext.getCurrentInstance().responseComplete();
			  poscounter=posBean.getPoscounter();
	          poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), null, null, "1","2",loginBean.getBranch().getBranchId());
	          if(poscashtransaction!=null)
	          {
			  ////poscashtransaction.setBalanceAmount(poscashtransaction.getCreditamount().subtract(poscashtransaction.getDebitAmount()));
			 // poscashtransaction.setTotalAmount(getSalesConterTotal(this.getPoscashtransactionList()));	 
			  //poscashtransaction.setCreditamount(poscashtransaction.getCreditamount().subtract(poscashtransaction.getTotalAmount()));
			  
			  poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
			  poscashtransaction.setTotalAmount(getSalesConterTotal(this.getPoscashtransactionList()));	 
			  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
			  
	          }
	          else
	          {
	        	  poscashtransaction=new PoscashtransactionModel();
	          }
			  
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	public String reportImage(BigDecimal totalSale,BigDecimal totalTax,BigDecimal cashIn,
			BigDecimal cashOut,BigDecimal expensesAmount,String fileName) {
		 int width = 255, height = 550;
			ResourceBundle rb = ResourceBundle.getBundle("../META-INF/app-config");

		    BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
		        .getDefaultScreenDevice().getDefaultConfiguration()
		        .createCompatibleImage(width, height);
		    Graphics graphics = image.createGraphics();
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy hh:mm a");		 

		    Calendar cal=Calendar.getInstance();
		    String date=dateFormat.format(cal.getTime());
		String html = "<table  style='width:150px;border:1px solid black'>"
	    		+ "<tr>"
	    		+ "<td colspan='3' align='center'>";
	    		if(fileName.toUpperCase().contains("admin".toUpperCase())) {
	    			html+="Daily Admin Report";
	    		}else {
	    			html+="Daily Report";
	    		}
	    		html+= "</td>"
	    		+ "<tr>"
	    		+ "<td colspan='3' align='center'>"+date
	    		+ "</td>"
	    		+ "</tr>"
	    		+ "</tr>"
	    		+ "<tr>"
	    		+ "<td colspan='3'>----------------------------------------------"
	    		+ "</td>"
	    		+ "</tr>"
	    		+ "<tr>"
	    		+ "<td colspan='3' ><b>"+loginBean.getBranch().getBranchName()+"</b>"
	    		+"<br/>"+loginBean.getBranch().getAddress()
	    		+ "</td>"
	    		+ "</tr>"
	    		+ "<tr>"
	    		+ "<td colspan='3'>----------------------------------------------"
	    		+ "</td>"
	    		+ "</tr>"
	    		
	    		+ "<tr>"
	    		+"<td>GST NO"
	    		+ "</td>"
	    		+ "<td> : "
	    		+ "</td>"
	    		+ "<td style='width:75%'>"+rb.getString("project.gst.number")
	    		+ "</td>"
	    		+ "</tr>"
	    		
	    		+ "<tr>"
	    		+"<td>REG NO"
	    		+ "</td>"
	    		+ "<td> : "
	    		+ "</td>"
	    		+ "<td style='width:75%'>"+rb.getString("project.reg.number")
	    		+ "</td>"
	    		+ "</tr>"
	    		
				+ "<tr>"
				+ "<td colspan='3'>----------------------------------------------"
				+ "</td>"
				+ "</tr>"
				
				+ "<tr>"
				+ "<td colspan='3' align='center'>CASH COLLECTION"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ "CASH IN"
				+ "</td>"
				+ "<td>"
				+ " : "
				+ "</td>"
				+ "<td>"
				+cashIn
				+ "</td>"
				+ "</tr>"
				
				+ "<tr>"
				+ "<td>"
				+ "CASH OUT"
				+ "</td>"
				+ "<td>"
				+ " : "
				+ "</td>"
				+ "<td>"
				+cashOut
				+ "</td>"
				+ "</tr>"

				
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ "Sales"
				+ "</td>"
				+ "<td>"
				+ " : "
				+ "</td>"
				+ "<td>"
				+totalSale
				+ "</td>"
				+ "</tr>"
				
				
				+ "<tr>"
				+ "<td>"
				+ "Expenses"
				+ "</td>"
				+ "<td>"
				+ " : "
				+ "</td>"
				+ "<td>"
				+expensesAmount
				+ "</td>"
				+ "</tr>"
        						
	    		
	    		+ "</table>";
	    		/*
	    		 
	    		 
				+ "<tr>"
				+"<td>TOTAL SALES "
				+ "</td>"
				+ "<td> : "
				+ "</td>"
				+ "<td style='width:75%'>"+totalSale
				+ "</td>"
				+ "</tr>"
	    		
				+ "<tr>"
				+"<td>TOTAL GST "
				+ "</td>"
				+ "<td> : "
				+ "</td>"
				+ "<td style='width:75%'>"+totalTax
				+ "</td>"
				+ "</tr>" 
	    		 */
	    		JEditorPane jep = new JEditorPane("text/html", html);
	 		    jep.setSize(width, height);
	 		    jep.print(graphics);
		
		 try {
				ImageIO.write(image, "png", new File("C:/possystem/generatedReport/"+fileName+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return html;
	}
	
	@org.springframework.scheduling.annotation.Async
	public void scheduleReport() {
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy hh:mm a");	
		    Calendar cal=Calendar.getInstance();
		    String date=dateFormat.format(cal.getTime());
			
		    String[] adminData=new String[2];
		    String[] cashierData=new String[5];
		    
			dailyadminreport();
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			List<String> lines = Arrays.asList("Time="+date,"sales="+poscashtransaction.getTotalAmount(),
					"tax="+poscashtransaction.getTotalTax());
			Path file = Paths.get("C:/possystem/generatedReport/dailyAdminReportData.txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
			String htmlString=reportImage(poscashtransaction.getTotalAmount(), 
					poscashtransaction.getTotalTax(),
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					"dailyAdminReport");
			
			adminData[0]=""+poscashtransaction.getTotalAmount();
			adminData[1]=""+poscashtransaction.getTotalTax();
			
			
			dailyreportsummary();
			lines = Arrays.asList("Time="+date,"sales="+poscashtransaction.getTotalAmount(),
					"tax="+poscashtransaction.getTotalTax());
			file = Paths.get("C:/possystem/generatedReport/dailyReportData.txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
			//BigDecimal sales=poscashtransaction.getTotalAmount().subtract(poscashtransaction.getReceivedAmount());
			this.reportImage(poscashtransaction.getSalesamount(),
					poscashtransaction.getTotalTax(),
					poscashtransaction.getReceivedAmount(),
					poscashtransaction.getDebitAmount(),
					this.getTotalExpenses(),
					"dailyReport");
			cashierData[0]=""+poscashtransaction.getSalesamount();
			cashierData[1]=""+poscashtransaction.getTotalTax();
			cashierData[2]=""+poscashtransaction.getCreditamount();
			cashierData[3]=""+poscashtransaction.getDebitAmount();
			cashierData[4]=""+this.getTotalExpenses();
			
			/*
			 
			 
		        		+ "<tr>"
		        		+ "<td colspan='3'><b>"
		        		+ "ADMIN REPORT"
		        		+ "<b></td>"
		        		+ "</tr>"
		        		+ "<tr>"
		        		+ "<td>"
		        		+ "Sales"
		        		+ "</td>"
		        		+ "<td>"
		        		+ " : "
		        		+ "</td>"
		        		+ "<td>"
		        		+adminData[0]
		        		+ "</td>"
		        		+ "</tr>"
		        		+ ""
		        		+ "<tr>"
		        		+ "<td>"
		        		+ "Tax"
		        		+ "</td>"
		        		+ "<td>"
		        		+ " : "
		        		+ "</td>"
		        		+ "<td>"
		        		+adminData[1]
		        		+ "</td>"
		        		+ "</tr>"
		        		
		        		+ "<tr>"
						+ "<td colspan='3'><b>"
						+ "CASHIER REPORT"
						+ "<b></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>"
						+ "Sales"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[0]
						+ "</td>"
						+ "</tr>"
						+ ""
						+ "<tr>"
						+ "<td>"
						+ "Tax"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[1]
						+ "</td>"
						+ "</tr>"
		    
		        		
			 
			 */
			String adminReport="<tr>"
	        		+ "<td colspan='3'><b>"
	        		+ "ADMIN REPORT"
	        		+ "<b></td>"
	        		+ "</tr>"
	        		+ "<tr>"
	        		+ "<td>"
	        		+ "Sales"
	        		+ "</td>"
	        		+ "<td>"
	        		+ " : "
	        		+ "</td>"
	        		+ "<td>"
	        		+adminData[0]
	        		+ "</td>"
	        		+ "</tr>"
	        		+ ""
	        		+ "<tr>"
	        		+ "<td>"
	        		+ "Tax"
	        		+ "</td>"
	        		+ "<td>"
	        		+ " : "
	        		+ "</td>"
	        		+ "<td>"
	        		+adminData[1]
	        		+ "</td>"
	        		+ "</tr>";
			ResourceBundle rb = ResourceBundle.getBundle("../META-INF/app-config");

	        String message ="";
	        		if(rb.getString("project.isPrettysFoodCorner").equalsIgnoreCase("true")) {
	        			message+=adminReport;
	        		}
	        		message+="<table>"
		        		+ "<tr>"
		        		+ "<td colspan='3'><b>"
		        		+date
		        		+ "<b></td>"
		        		+ "</tr>"	
		
						+ "<tr>"
						+ "<td colspan='3'><b>"
						+ "CASHIER REPORT"
						+ "<b></td>"
						
						
						+ "<tr>"
						+ "<td>"
						+ "CASH IN"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[2]
						+ "</td>"
						+ "</tr>"
						
						+ "<tr>"
						+ "<td>"
						+ "CASH OUT"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[3]
						+ "</td>"
						+ "</tr>"

						
						+ "</tr>"
						+ "<tr>"
						+ "<td>"
						+ "Sales"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[0]
						+ "</td>"
						+ "</tr>"
						
						
						+ "<tr>"
						+ "<td>"
						+ "Expenses"
						+ "</td>"
						+ "<td>"
						+ " : "
						+ "</td>"
						+ "<td>"
						+cashierData[4]
						+ "</td>"
						+ "</tr>"
		        		
		        		+ "</table>";
			
	        
			
			GmKlangEmail email = (GmKlangEmail) BeanContext.getReference("gmKlangEmail");
			String mailFrom="jsoftsolutionreport@gmail.com";
			String password="wd2022c1111";
			String mailTo=loginBean.getBranch().getEmailAddress();
			String subject="DAILY REPORT - "+date;
			/* "C:/possystem/generatedReport/dailyAdminReport.png" 
			 * C:/possystem/generatedReport/dailyReport.png
			 * */
			boolean sendSuccess=false;
			if(rb.getString("project.isPrettysFoodCorner").equalsIgnoreCase("true")) {
				String[] attachmentFile= {
						"C:/possystem/generatedReport/dailyReport.png","C:/possystem/generatedReport/dailyAdminReport.png"};
						 sendSuccess=email.sendHtmlMail(mailFrom, password, mailTo, subject, message,attachmentFile);

    		}else {
    			String[] attachmentFile= {
				"C:/possystem/generatedReport/dailyReport.png"};
    			 sendSuccess=email.sendHtmlMail(mailFrom, password, mailTo, subject, message,attachmentFile);
    		}
			
			
			
			String delMsg="";
			if(sendSuccess) {
				delMsg="EMAIL SENT SUCCESSFULLY";
			}else {
				delMsg="EMAIL FAILED TO SEND";
			}
			FacesMessage status = new FacesMessage(FacesMessage.SEVERITY_INFO, "EMAIL", "EMAIL SENT SUCCESSFULLY");
			   RequestContext.getCurrentInstance().showMessageInDialog(status);
			//EmailDailyReportAdmin em=new EmailDailyReportAdmin();

			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void dailyreport(){      		        
	      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			  poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			
			if(this.getDateFrom()==null && this.getDateTo()==null)
			{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}
			
			poscashtransactionListObj.clear();
						
			poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(poscounter.getCounterId(), null,null,dateFrom,dateTo,null,loginBean.getBranch().getBranchId());			
				
			
			  poscounter=posBean.getPoscounter();
	          poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), dateFrom, dateTo, null,null,loginBean.getBranch().getBranchId());
	          if(poscashtransaction!=null)
	          {			 
			  poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
			  poscashtransaction.setTotalAmount(getSalesConterTotal(poscashtransactionListObj));	 
			  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
			  poscashtransaction.setSalesamount(poscashtransaction.getTotalAmount().subtract(poscashtransaction.getTotalTax()));
	          }
	          else
	          {
	        	  poscashtransaction=new PoscashtransactionModel();
	          }
			  
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	
	public void dailyadminreport(){      		        
	      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			  poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			
			  BigDecimal totalAmount = new BigDecimal(0.00);
			  BigDecimal totalTax = new BigDecimal(0.00);
			  
			if(this.getDateFrom()==null && this.getDateTo()==null)
			{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}			
			salesOrder.clear();
			salesOrder=salesOrderBO.getSalesorderReportListowner(null, null, null, dateFrom, dateTo, null, loginBean.getBranch().getBranchId());
			
			for(SalesorderModel data : salesOrder)
			{
				totalAmount=totalAmount.add(data.getTotalAmount());
				totalTax=totalTax.add(data.getTotalTax());
			}
					
			poscashtransaction.setTotalAmount(totalAmount);
			poscashtransaction.setSalesamount(totalAmount.subtract(totalTax));
			poscashtransaction.setTotalTax(totalTax);
			
			poscounter=posBean.getPoscounter();
			poscashtransaction.setPoscounter(poscounter);

		
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	public void dailyreportsummary(){      		        
	     
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			 poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			  
			if(this.getDatesFrom()==null && this.getDatesTo()==null)
			{
				this.setDatesFrom(DateUtil.getFromTodayDateTime());
				this.setDatesTo(DateUtil.getToTodayDateTime());
			}
			
			poscashtransactionListObj.clear();
						
			poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(poscounter.getCounterId(), null,null,datesFrom,datesTo,null,loginBean.getBranch().getBranchId());			
				
			
			  poscounter=posBean.getPoscounter();
	         // poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), datesFrom, datesTo, null,null,loginBean.getBranch().getBranchId());
			  PoscashtransactionModel obj=new PoscashtransactionModel();
			    // SALES 
			List<PoscashtransactionModel> posCounterData=poscounterBO.getposcounterReportDaily(poscounter.getCounterId(), datesFrom, datesTo, 
					null,null,"1",loginBean.getBranch().getBranchId());
			for(PoscashtransactionModel data:posCounterData) {
				obj.setSalesamount(data.getCreditamount());
				obj.setTotalTax(data.getTotalTax());
					
			}
				
			// CASH IN CASH OUT
			posCounterData=poscounterBO.getposcounterReportDaily(poscounter.getCounterId(), datesFrom, datesTo, 
					null,null,"0",loginBean.getBranch().getBranchId());
			for(PoscashtransactionModel data:posCounterData) {
				obj.setCreditamount(data.getCreditamount());
				obj.setDebitAmount(data.getDebitAmount());
			}
			
			this.poscashtransaction=obj;
			BigDecimal expensesTotalAmount=new BigDecimal("0");
			List<ExpensesTransactionModel> expList=expensesBO.getExpensesTransactionList(poscounter.getCounterId(),datesFrom,datesTo,loginBean.getBranch().getBranchId());
			for(ExpensesTransactionModel data:expList) {
				
				expensesTotalAmount=expensesTotalAmount.add(data.getAmmount());
			}
			this.setTotalExpenses(expensesTotalAmount);
			
	          if(poscashtransaction!=null)
	          {			 
			//  poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
		//	  poscashtransaction.setTotalAmount(getSalesConterTotal(poscashtransactionListObj));	 
			//  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
			//  poscashtransaction.setSalesamount(poscashtransaction.getTotalAmount().subtract(poscashtransaction.getTotalTax()));
	          }
	          else
	          {
	        	  poscashtransaction=new PoscashtransactionModel();
	          }
			  BigDecimal totalCash=new BigDecimal(0.00);
	          BigDecimal totalCheque=new BigDecimal(0.00);
	          BigDecimal totalCredit=new BigDecimal(0.00);
	          BigDecimal totalVoucher=new BigDecimal(0.00);
	          BigDecimal totalLoyalty=new BigDecimal(0.00);
	          List<PaymentCollectionModel> pospayments=salesOrderBO.getPosPaymentDetails(datesFrom, datesTo,null,loginBean.getBranch().getBranchId());
	          for(PaymentCollectionModel data:pospayments) {
	        	 
	        	  if(data.getAmountType().equalsIgnoreCase("CASH")) {
	        		 // totalCash=totalCash.add(data.getAmount());
	        	  }else if(data.getAmountType().equalsIgnoreCase("CARD")) {
	        		  totalCredit=totalCredit.add(data.getAmount());
	        	  }else if(data.getAmountType().equalsIgnoreCase("CHEQUE")) {
	        		  //totalCheque=totalCheque.add(data.getAmount());
	        	  }else if(data.getAmountType().equalsIgnoreCase("VOUCHER")) {
	        		  //totalVoucher=totalVoucher.add(data.getAmount());
	        	  }else if(data.getAmountType().equalsIgnoreCase("LOYALTY")) {
	        		  //totalLoyalty=totalLoyalty.add(data.getAmount());
	        	  }
	          }
	          
	          this.setTotalCashAmount(poscashtransaction.getTotalAmount().subtract(totalCredit));
	          //this.setTotalChequeAmmount(totalCheque);
	          this.setTotalCreditCardAmount(totalCredit);
	          //this.setTotalLoyaltyAmmount(totalLoyalty);
	          //this.setTotalVoucherAmmount(totalVoucher);
	          
			  
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	
	
	public void dailyreportReset(){      		        
	      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			 poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			  
			  this.setDateFrom(DateUtil.getFromTodayDateTime());
			  this.setDateTo(DateUtil.getToTodayDateTime());
		     
			
			poscashtransactionListObj.clear();
						
			poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(poscounter.getCounterId(), null,null,dateFrom,dateTo,null,loginBean.getBranch().getBranchId());			
				
			
			  poscounter=posBean.getPoscounter();
	          poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), dateFrom, dateTo, null,null,loginBean.getBranch().getBranchId());
	          if(poscashtransaction!=null)
	          {			 
			  poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
			  poscashtransaction.setTotalAmount(getSalesConterTotal(poscashtransactionListObj));	 
			  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
			  poscashtransaction.setSalesamount(poscashtransaction.getTotalAmount().subtract(poscashtransaction.getTotalTax()));
	          }
	          else
	          {
	        	  poscashtransaction=new PoscashtransactionModel();
	          }
			  
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	public void dailyadminreportReset(){      		        
	      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			  poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			
			  BigDecimal totalAmount = new BigDecimal(0.00);
			  BigDecimal totalTax = new BigDecimal(0.00);
			  
			
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
						
			salesOrder.clear();
			salesOrder=salesOrderBO.getSalesorderReportListowner(null, null, null, dateFrom, dateTo, null, loginBean.getBranch().getBranchId());
			
			for(SalesorderModel data : salesOrder)
			{
				totalAmount=totalAmount.add(data.getTotalAmount());
				totalTax=totalTax.add(data.getTotalTax());
			}
					
			poscashtransaction.setTotalAmount(totalAmount);
			poscashtransaction.setSalesamount(totalAmount.subtract(totalTax));
			poscashtransaction.setTotalTax(totalTax);
			
			poscounter=posBean.getPoscounter();
			poscashtransaction.setPoscounter(poscounter);
		
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	
	public void dailyreportsummaryReset(){      		        
	      
        PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try {		
			
			  poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			  
			  this.setDatesFrom(DateUtil.getFromTodayDateTime());
			  this.setDatesTo(DateUtil.getToTodayDateTime());
		     
			
			poscashtransactionListObj.clear();
						
			poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(poscounter.getCounterId(), null,null,datesFrom,datesTo,null,loginBean.getBranch().getBranchId());			
				
			
			  poscounter=posBean.getPoscounter();
	          poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), datesFrom, datesTo, null,null,loginBean.getBranch().getBranchId());
	          if(poscashtransaction!=null)
	          {			 
			  poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
			  poscashtransaction.setTotalAmount(getSalesConterTotal(poscashtransactionListObj));	 
			  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
			  poscashtransaction.setSalesamount(poscashtransaction.getTotalAmount().subtract(poscashtransaction.getTotalTax()));
	          }
	          else
	          {
	        	  poscashtransaction=new PoscashtransactionModel();
	          }
			  
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}

	
	public void searchPosCounterReport() {
		if(this.getCounterReportFrom()==null) {
			this.setCounterReportFrom(DateUtil.getFromTodayDateTime());
			
		}
		if(this.getCounterReportTo()==null) {
			this.setCounterReportTo(DateUtil.getToTodayDateTime());
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.poscounterReport.clear();
			

			String in=null;
			String out=null;
			for(ShiftModel shift:shiftBO.loadShiftAll()) {
				in=shift.getTimein()+"";
				out=shift.getTimeout()+"";
				
			    Calendar reportFrom = Calendar.getInstance();
			    reportFrom.setTime(this.getCounterReportFrom());
			    
			    Calendar reportTo = Calendar.getInstance();
			    reportTo.setTime(this.getCounterReportTo());
			    for (Date date = reportFrom.getTime(); !reportFrom.after(reportTo); reportFrom.add(Calendar.DATE, 1), date = reportFrom.getTime()) {
			    	
			    	Calendar cal = Calendar.getInstance();
				    cal.setTime(date);
				    cal.set(Calendar.HOUR_OF_DAY,shift.getTimein().getHours());
				    cal.set(Calendar.MINUTE,shift.getTimein().getMinutes());
				    cal.set(Calendar.SECOND,0);

				    Date timeIn = cal.getTime();
				    
				    cal.set(Calendar.HOUR_OF_DAY,shift.getTimeout().getHours());
				    cal.set(Calendar.MINUTE,shift.getTimeout().getMinutes());
				    cal.set(Calendar.SECOND,59);
				    
				    Date timeOut= cal.getTime();
				    
				    Calendar cal2 = Calendar.getInstance();
				    cal2.setTime(timeIn);
				    if (cal2.get(Calendar.AM_PM) == Calendar.PM && cal.get(Calendar.AM_PM) == Calendar.AM) {
				    	cal.setTime(timeOut);
				    	cal.add(Calendar.DATE,1);
				    	timeOut=cal.getTime();
				    	
				    }
				
					 PoscounterreportModal reportObj=new PoscounterreportModal();
					 reportObj.setLastupdatedDate(timeIn);
					 reportObj.setShiftName(shift.getShiftName());
				    // SALES 
					List<PoscashtransactionModel> posCounterData=poscounterBO.getposcounterReportDaily(this.getCounterReportCounterId(), timeIn, timeOut, 
							null,null,"1",loginBean.getBranch().getBranchId());
					for(PoscashtransactionModel data:posCounterData) {
						 reportObj.setSalesamount(data.getCreditamount());
						 reportObj.setTotalTax(data.getTotalTax());
					}
					
					// CASH IN CASH OUT
					posCounterData=poscounterBO.getposcounterReportDaily(this.getCounterReportCounterId(), timeIn, timeOut, 
							null,null,"0",loginBean.getBranch().getBranchId());
					for(PoscashtransactionModel data:posCounterData) {
						 reportObj.setCreditamount(data.getCreditamount());
						 reportObj.setDebitAmount(data.getDebitAmount());
					}
					
					BigDecimal expensesTotalAmount=new BigDecimal("0");

					List<ExpensesTransactionModel> expList=expensesBO.getExpensesTransactionList(poscounter.getCounterId(),timeIn,timeOut,loginBean.getBranch().getBranchId());
					for(ExpensesTransactionModel data:expList) {
						
						expensesTotalAmount=expensesTotalAmount.add(data.getAmmount());
					}
					
					reportObj.setExpensesAmount(expensesTotalAmount);
					reportObj.setBalanceAmount(reportObj.getCreditamount().add(reportObj.getSalesamount()).subtract(reportObj.getDebitAmount()).subtract(reportObj.getExpensesAmount()));
					
					this.poscounterReport.add(reportObj);
					
					
					
					
			    }
			}

			Collections.sort(poscounterReport, new Comparator<PoscounterreportModal>() {
				  public int compare(PoscounterreportModal list1, PoscounterreportModal list2) {
				      return list1.getLastupdatedDate().compareTo(list2.getLastupdatedDate());
				  }
			});
			 
			/* for(PoscounterreportModal data:poscounterReport) {
						 +data.getBalanceAmount()+" : "+data.getCreditamount()+ " : "+data.getDebitAmount() +
						 " EXP : "+data.getExpensesAmount() + " balance "+data.getBalanceAmount());
			 }
			 */
			 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}

	public void resetSearchPosCounterReport() {
		this.setCounterReportFrom(null);
		this.setCounterReportTo(null);
		this.setCounterReportCounterId(1);
		searchPosCounterReport();
	}
	
	public void annualSummaryPosCashTranscationReport(ActionEvent event) {
		Date startDate = (Date) event.getComponent().getAttributes().get("startDate");
		Date endDate = (Date) event.getComponent().getAttributes().get("endDate");
		this.setDatesFrom(startDate);
		this.setDatesTo(endDate);
		dailyreportsummary();
	}
	

	public void counterCloseReportPDF() {
		String home = System.getProperty("user.home");
		String fileName="posCounter.pdf";
		String dest=home+"/downloads/"+fileName;
		Document document = new Document();
		try {
			
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        PdfWriter.getInstance(document,  new FileOutputStream(dest));
		        document.open();
		        PdfPTable table = new PdfPTable(5);
		        table.setWidths(new int[]{ 1, 2, 2, 2, 1});
		        PdfPCell cell;
		        cell = new PdfPCell(new Phrase("S/N"));
		        cell.setRowspan(2);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Name"));
		        cell.setColspan(3);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Age"));
		        cell.setRowspan(2);
		        table.addCell(cell);
		        table.addCell("SURNAME");
		        table.addCell("FIRST NAME");
		        table.addCell("MIDDLE NAME");
		        table.addCell("1");
		        table.addCell("James");
		        table.addCell("Fish");
		        table.addCell("Stone");
		        table.addCell("17");
		        document.add(table);
		        document.close();
		      
		        
		        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
	}
	
}
