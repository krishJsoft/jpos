package com.project.bean.purchase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.util.DateUtil;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.bean.admin.AdminDespatchBean;
import com.project.bean.admin.BranchBeanInfo;
import com.project.bean.admin.ProductCategoryBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

import java.awt.Color;

public class PurchaseRequestBeanInfo {

	
	PurchaseRequestBean purchaseRequestBean = (PurchaseRequestBean) BeanContext.getReference("purchaseRequestBean");
	ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	
	
	
	public void listPurchaseRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/purchaseRequest.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Request -->Search Purchase Request");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newPurchaseRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/addEditpurchaseRequest.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Request --> Add/Edit Purchase Request");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void consolidatePurchaseRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/purchaseRequestProducts.xhtml");
		projectHome.setTitlePage("Purchase --> PurchaseRequest -->Consolidate Purchase Request");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void consolidateSummaryPurchaseRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/purchaseRequestConsolidateSummary.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Request --> Consolidate Summary Purchase Request ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void purchaseRequestView() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/purchaseRequestConsolidate.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Request --> Consolidate Purchase Request ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void createNewPurchaseRequest()
	{
		
		PurchaserequestModel purchaserequest = new PurchaserequestModel();
		IPurchaserequestBO purchaseRequestBO;
		boolean saveSuccess = false;
		try
		{	
			if(validateRequestSave())
			{
			
			purchaseRequestBO=purchaseRequestBean.getPurchaseRequestBO();
		    purchaserequest = purchaseRequestBean.getPurchaserequest();
		    purchaserequest.setCreatedBy(loginBean.getUserName());
		    purchaserequest.setBranchtype(loginBean.getBranch().getBranchtype());
		    purchaserequest.setCreatedDate(DateUtil.getTodayDate());
		    purchaserequest.setLastModifiedDate(DateUtil.getTodayDate());		    
		    purchaserequest.setStatus("1");
		    purchaserequest.setBranchstatus("1");		    
		    LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");			
			if(loginBean.getBranch().getBranchtype().contentEquals(config.getValue(IConfiguration.PROJECT_TYPE_HQ_VALUE)))
			{
				  purchaserequest.setBranchview("1");
			}	
			else
			{
				  purchaserequest.setBranchview("0");
			}
		  
		    
		    purchaserequest.setPurchaserequestbreakdowns(purchaseRequestBean.getPurchaserequestbreakdowns());		    
		    saveSuccess=purchaseRequestBO.createNewPurchaserequest(purchaserequest);
			if (saveSuccess) {					
				purchaseRequestBean.resetPurchaseRequest();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaserequest.label.save.success"),null));				
			}
			}
			
			
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			
		}
		
	}	
	
	
	
	
	public void updatePurchaseRequest()
	{		
		PurchaserequestModel purchaserequest = new PurchaserequestModel();
		IPurchaserequestBO purchaseRequestBO;
		boolean updateSuccess = false;
		boolean itenRemoved = true;
		try
		{	purchaseRequestBO=purchaseRequestBean.getPurchaseRequestBO();
		    purchaserequest = purchaseRequestBean.getPurchaserequest();
		    purchaserequest.setCreatedBy(loginBean.getUserName());
		    purchaserequest.setCreatedDate(DateUtil.getTodayDate());
		    purchaserequest.setLastModifiedDate(DateUtil.getTodayDate());
		    purchaserequest.setStatus("1");
		    
		    purchaserequest.setPurchaserequestbreakdowns(purchaseRequestBean.getPurchaserequestbreakdowns());		    
		    
		   purchaseRequestBean.setItemaction("submit"); 
		   updateSuccess=purchaseRequestBO.updatePurchaserequest(purchaserequest);
			if (updateSuccess) {					
				purchaseRequestBean.resetPurchaseRequest();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaserequest.label.save.success"),null));				
			}
			
			
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			
		}
		
	}	
	
	
	
	public void generatePOViewIndividual(PurchaserequestModel purchaserequestModel)
	{		
		List<Integer> branchids = new ArrayList<Integer>();
		branchids.add(purchaserequestModel.getBranchId());		
		List<Integer> requestIds = new ArrayList<Integer>();
		requestIds.add(purchaserequestModel.getPurchaseRequestId());	
	}
	
	
	public PurchaserequestModel editPurchaseRequest(Integer purchaseRequestId)
	{
		 IPurchaserequestBO purchaseRequestBO;		
		 PurchaserequestModel purchaserequestData=null;
		try {
			 purchaseRequestBO=purchaseRequestBean.getPurchaseRequestBO();
			 purchaserequestData=purchaseRequestBO.getPurchaserequestDetails(purchaseRequestId);
			 purchaseRequestBean.setPurchaserequest(purchaserequestData);		 
			 purchaseRequestBean.setItemaction("update");
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}		
		return  purchaserequestData;
	}
	
	
	
	public boolean validateRequestSearch() {
		boolean valid = true;
		PurchaseRequestBean purchaseRequestBean = (PurchaseRequestBean) BeanContext.getReference("purchaseRequestBean");	
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		
		if(loginBean.getBranch().getBranchtype().contentEquals(config.getValue(IConfiguration.PROJECT_TYPE_BRANCH_VALUE)))
		{
			purchaseRequestBean.setBranchId(loginBean.getBranch().getBranchId());
			purchaseRequestBean.setBranchview(null);
		}	
		else
		{
			purchaseRequestBean.setBranchview("1");
		}
		
		if(purchaseRequestBean.getReferenceNo()!=null)
		{
			if(purchaseRequestBean.getReferenceNo().equalsIgnoreCase(""))
			{
				purchaseRequestBean.setReferenceNo(null);
			}
		}		
		
		if(purchaseRequestBean.getBranchId()!=null)
		{
			if(purchaseRequestBean.getBranchId()==0)
			{
				purchaseRequestBean.setBranchId(null);
			}
		}		
		if(purchaseRequestBean.getStatus()!=null)
		{
			if(purchaseRequestBean.getStatus().equalsIgnoreCase("") || purchaseRequestBean.getStatus().equalsIgnoreCase("0") )
			{
				purchaseRequestBean.setStatus(null);
			}
		}		
		
		if(purchaseRequestBean.getDateFrom()==null && purchaseRequestBean.getDateTo()==null && purchaseRequestBean.getStatus()==null  && purchaseRequestBean.getReferenceNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	public boolean validateRequestSave()
	{
		boolean valid = true;
		PurchaseRequestBean purchaseRequestBean = (PurchaseRequestBean) BeanContext.getReference("purchaseRequestBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			if (factoryBean.checkIsNullAssignMessage(purchaseRequestBean.getPurchaserequest().getDeliveryDate(),
					"purchaserequest.label.deliveryDate", "deliveryDate")) {
				valid = false;
			}	
			if (factoryBean.checkIsNullAssignMessage(purchaseRequestBean.getPurchaserequest().getDeliveryType(),
					"purchaserequest.label.deliveryType", "deliveryType")) {
				valid = false;
			}	
			if (factoryBean.checkIsNullAssignMessage(purchaseRequestBean.getPurchaserequest().getPurchaseType(),
					"purchaserequest.label.purchaseType", "purchaseType")) {
				valid = false;
			}	
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
	public void createPurchaseRequestPDF(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();			
		
		try {		
			
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");		
			
			HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();
			String Permai = "Permai";

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition",
					"attachment;filename=\"CustomerPurchaseOrder.pdf\"");

			Document document = new Document(PageSize.A4, 10, 10, 5, 5);

			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			com.lowagie.text.Font font3 = FontFactory.getFont("Helvetica", 8F);
			com.lowagie.text.Font font8 = FontFactory.getFont("Helvetica", 8F,
					1);
			com.lowagie.text.Font font5 = FontFactory.getFont("Helvetica", 9F);
			com.lowagie.text.Font font9Bold = FontFactory.getFont("Helvetica",
					9F, 1);
			com.lowagie.text.Font font10Bold = FontFactory.getFont("Helvetica",
					10F, 1);

			// Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
			Font font8bb = FontFactory.getFont(FontFactory.HELVETICA, 7,
					Font.BOLD, new Color(0x00, 0x00, 0xFF));

			Font font8b = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.BOLD);
			Font font10b = FontFactory.getFont(FontFactory.HELVETICA, 10,
					Font.BOLD);
			Font font9b = FontFactory.getFont(FontFactory.HELVETICA, 9,
					Font.BOLD);
			Font font15b = FontFactory.getFont(FontFactory.HELVETICA, 25,
					Font.BOLD, new Color(0x00, 0x00, 0xFF));
			com.lowagie.text.Font font2 = FontFactory.getFont("Helvetica", 9F,
					1);

			Font blue = FontFactory.getFont(FontFactory.HELVETICA, 9,
					Font.BOLD, new Color(0x00, 0x00, 0xFF));

			float width = document.getPageSize().getWidth();
			float height = document.getPageSize().getHeight();

			float f = document.getPageSize().getWidth();
			float f1 = document.getPageSize().getHeight();
			float afheader[] = { 80F, 20F };
			float af[] = { 60F, 40F };				

			
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String logoName = servletContext.getRealPath("") + File.separator+ "resources" + File.separator + "images" + File.separator	+ Permai + ".jpg";

			String logo = "";
			String newFileName = servletContext.getRealPath("")	+ File.separator + "resources" + File.separator + "images"	+ File.separator + Permai + ".jpg";

			HeaderFooter footer;
			footer = new HeaderFooter(	new Paragraph(loginBean.getUserName()+	"\t\t @ \t Alpha Pharmacy"
									+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
									+ new java.util.Date(), font8b), false);
			footer.disableBorderSide(footer.BOTTOM);
			document.setFooter(footer);

			document.open();

			Image image = Image.getInstance(logoName);
			image.setAlignment(Image.RIGHT);
			image.scalePercent(10, 10);
			

			Paragraph headertitle = new Paragraph("Test", font15b);
			headertitle.setAlignment(Element.ALIGN_RIGHT);

			PdfPTable pdfHeaderTable = new PdfPTable(afheader);
			pdfHeaderTable.getDefaultCell().setBorder(0);
			pdfHeaderTable.setHorizontalAlignment(0);		
			pdfHeaderTable.setWidthPercentage(100);
			pdfHeaderTable.setLockedWidth(true);

			PdfPTable addressTable = new PdfPTable(1);
			addressTable.getDefaultCell().setBorder(0);
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_ADDRESS1), font8));
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_ADDRESS2), font8));
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_PHONENO1), font8));
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_PHONENO2), font8));
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_EMAIL), font8));
			addressTable.addCell(new Phrase(config.getValue(IConfiguration.COMPANY_POSTCODE), font8));
			
			pdfHeaderTable.addCell(addressTable);
			pdfHeaderTable.addCell(image);			

			document.add(pdfHeaderTable);			
			document.add(new Paragraph(" "));
			Paragraph paragraph = new Paragraph("Purchase Order  \t\t\t    \t\t\t\t \t\t\t\t\t\t\t",font8b);
			paragraph.setAlignment(1);
			document.add(paragraph);
			document.add(new Paragraph(" "));			

			document.close();
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			baos.flush();
			faces.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
}
