package com.project.bean.purchase;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaseorderdeliveryaddressModel;
import com.project.model.datamodel.purchase.PurchaserequestBranchModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
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
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.bean.admin.BranchBeanInfo;
import com.project.bean.admin.ProductCategoryBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class PurchaseOrderBeanInfo {
	
	PurchaseRequestBean purchaseRequestBean = (PurchaseRequestBean) BeanContext.getReference("purchaseRequestBean");
	PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
	ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(PurchaseOrderBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	
	
	public void listPurchaseOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/purchaseOrder.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Order -->Search Purchase Order");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newPurchaseOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/purchase/addEditpurchaseOrder.xhtml");
		projectHome.setTitlePage("Purchase --> Purchase Order --> Add/Edit Purchase Order");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void updatePurchaseOrder()
	{
		List<PurchaseorderdeliveryaddressModel> deliveryaddressList = new ArrayList<PurchaseorderdeliveryaddressModel>();
		IPurchaseOrderBO purchaseOrderBO=purchaseOrderBean.getPurchaseOrderBO();
		boolean updateSuccess=false;
		PurchaseorderModel purchaseorder=purchaseOrderBean.getPurchaseorder();
		purchaseorder.setPurchaseRevertItemlList(purchaseOrderBean.getPurchaseRevertItemlList());
		
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		int purchaseOrderDeliveryId=0;
		for(PurchaserequestBranchModel data:purchaseOrderBean.getPurchaseorder().getBranchModel())
		{				
		if (ValidatorUtil.isNotNull(purchaseOrderBean.getDeliveryAddress().get(data.getBranchId()))) 
		{
			for(PurchaseorderdeliveryaddressModel address:purchaseOrderBean.getPurchaseorder().getPurchaseorderdeliveryaddresses())
			{
				if(address.getBranchId()==data.getBranchId())
				{
					purchaseOrderDeliveryId=address.getPurchaseOrderDeliveryId();
				}
			}			
			PurchaseorderdeliveryaddressModel addressdata= new PurchaseorderdeliveryaddressModel();
			addressdata.setDeliveryAddress(purchaseOrderBean.getDeliveryAddress().get(data.getBranchId()));
			addressdata.setBranchId(data.getBranchId());
			addressdata.setPurchaseOrderDeliveryId(purchaseOrderDeliveryId);
			deliveryaddressList.add(addressdata);
		}	
		
		}		
		purchaseorder.setPurchaseorderdeliveryaddresses(deliveryaddressList);
		updateSuccess=purchaseOrderBO.updatePurchaseorderMaster(purchaseorder);
		
		if (updateSuccess) {					
			this.listPurchaseOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaseorder.label.update.success"),null));				
		}		
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updatePurchaseOrder of PurchaseOrderBeanInfo "+ e.toString());
		}
		
	}
	
	public void validatePurchaseOrderProcess()
	{
		boolean valid=true;
		PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
		PurchaseorderModel purchaseorder=purchaseOrderBean.getPurchaseorder();
		FacesContext faces = FacesContext.getCurrentInstance();		
		purchaseOrderBean.getErrorList().clear();
		List<String> errorList = new ArrayList<String>();
		try {
			
			if(purchaseorder.getPaymentTerms()==null)
			{
				errorList.add("Payment Terms is required");
			}
			if(purchaseorder.getTermsConditions()==null)
			{
				errorList.add("Terms and Conditions is required");
			}
			purchaseOrderBean.setErrorList(errorList);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
	
	public void generatePurchaseOrderPDF()
			{			
			FacesContext faces = FacesContext.getCurrentInstance();					
			try {						
				LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");	
				PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
				HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();
				String companylogo = "logo";
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				Document document = new Document(PageSize.A4, 10, 10, 5, 5);				
				PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(servletContext.getRealPath("") + File.separator+ "document" + File.separator + "purchase_order" + File.separator	+purchaseOrderBean.getPurchaseorder().getPurchaseOrderNo()+".pdf"));			
			
				BigDecimal sumsubTotal = new BigDecimal(0.00);
				
				com.lowagie.text.Font font3 = FontFactory.getFont("Helvetica", 8F);
				com.lowagie.text.Font font8 = FontFactory.getFont("Helvetica", 8);
				com.lowagie.text.Font font5 = FontFactory.getFont("Helvetica", 9F);
				com.lowagie.text.Font font9Bold = FontFactory.getFont("Helvetica",9F, 1);
				com.lowagie.text.Font font10Bold = FontFactory.getFont("Helvetica",	10F, 1);
				Font font8bb = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.BOLD, new Color(0x00, 0x00, 0xFF));
				Font font8b = FontFactory.getFont(FontFactory.HELVETICA, 8,	Font.BOLD);
				Font font10b = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD);
				Font font11b = FontFactory.getFont(FontFactory.HELVETICA, 11,Font.BOLD);
				Font font12b = FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD);
				Font font9b = FontFactory.getFont(FontFactory.HELVETICA, 9,	Font.BOLD);
				Font font15b = FontFactory.getFont(FontFactory.HELVETICA, 15,Font.BOLD, new Color(0x00, 0x00, 0xFF));
				com.lowagie.text.Font font2 = FontFactory.getFont("Helvetica", 9F,1);
				Font blue = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD, new Color(0x00, 0x00, 0xFF));

				float width = document.getPageSize().getWidth();
				float height = document.getPageSize().getHeight();

				float f = document.getPageSize().getWidth();
				float f1 = document.getPageSize().getHeight();
				float afheader[] = { 80F, 20F };
				float headerTitle[] = { 100F};
				float af[] = { 60F, 40F };	
				float purchaseordrmasterColumn[] = { 50F, 50F };				
				float headerColumn[] = { 15F, 20F, 65F };
				float purchaseordrmaster1Column[] = { 25F, 25F };
				float purchaseordrmaster2Column[] = { 25F, 25F };
				float purchaseordrmaster3Column[] = { 20F, 30F , 20F , 30F };
				
				float purchaseordrBranchColumn[] = { 20F, 50F ,30F};	
				float afcolumn5[] = { 5F, 18F, 15F,29F, 6F, 10F , 6F, 11F};
				float afcolumn5A[] = { 83F, 6F,11F};
				
				String logoName = servletContext.getRealPath("")  + File.separator + "report" + File.separator + "images" + File.separator	+ companylogo + ".jpg";

				String logo = "";
				String newFileName = servletContext.getRealPath("")	 + File.separator + "report"	+ File.separator + "images"	+ File.separator + companylogo + ".jpg";

				HeaderFooter footer;
				footer = new HeaderFooter(	new Paragraph(loginBean.getUserName()+	"\t\t @ \t Alpha Pharmacy"
										+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
										+ new java.util.Date(), font8b), false);
				footer.disableBorderSide(footer.BOTTOM);
				document.setFooter(footer);
				document.open();
				
				Image image = Image.getInstance(logoName);
				image.setAlignment(Image.LEFT);
				image.scalePercent(10, 10);				
				
				PdfPTable header = new PdfPTable(headerColumn);	
				header.getDefaultCell().setBorder(0);
				header.setHorizontalAlignment(0);
				header.setTotalWidth(f - 22F);
				header.setLockedWidth(true);						
			
				
				
				PdfPTable header1 = new PdfPTable(headerTitle);	
				header1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				header1.setHorizontalAlignment(0);
				header1.setTotalWidth(f - 22F);				
				header1.setLockedWidth(true);
						
				header1.addCell(new Phrase(loginBean.getBranch().getBranchName() , font15b));					
				header1.addCell(new Phrase(loginBean.getBranch().getAddress(), font8bb));		
				header1.addCell(new Phrase(loginBean.getBranch().getEmailAddress(), font8bb));								
				header1.addCell(new Phrase(loginBean.getBranch().getPhoneNo(), font8bb));
				
				PdfPCell table_cel2=new PdfPCell(header1);				
				table_cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cel2.setVerticalAlignment(Element.ALIGN_TOP); 
				table_cel2.setBorder(0);
				
				header.addCell(image);	
				header.addCell("");
				header.addCell(table_cel2);				
				
							
				
				
				
				/*PdfContentByte cb = writer.getDirectContent();

				cb.setLineWidth(2.0f); // Make a bit thicker than 1.0 default
				// cb.setGrayStroke(0.95f); // 1 = black, 0 = white
				float x = 0f;
				float y = 750f;
				cb.moveTo(x, y);
				cb.lineTo(x + 90f * 7, y);
				cb.stroke();	*/			
				
				document.add(header);
				
				PdfPTable pdfptableTitleHader = new PdfPTable(headerTitle);
				pdfptableTitleHader.getDefaultCell().setBorder(1);			
				pdfptableTitleHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfptableTitleHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP); 
				pdfptableTitleHader.setTotalWidth(f - 22F);
				pdfptableTitleHader.setLockedWidth(true);
				pdfptableTitleHader.getDefaultCell().setPadding(5f);
				pdfptableTitleHader.getDefaultCell().setGrayFill(0.75f);
				pdfptableTitleHader.addCell(new Phrase("Purchase Order  \t\t\t    \t\t\t\t \t\t\t\t\t\t\t", font12b));
				document.add(pdfptableTitleHader);	
				
				
				PdfPTable purchasemaster1 = new PdfPTable(purchaseordrmaster3Column);	
				purchasemaster1.getDefaultCell().setBorder(0);
				purchasemaster1.setHorizontalAlignment(0);
				purchasemaster1.setTotalWidth(f - 22F);
				purchasemaster1.setLockedWidth(true);
				purchasemaster1.getDefaultCell().setPadding(2f);		
			
				purchasemaster1.addCell(new Phrase("Supplier Address :", font8b));
				purchasemaster1.addCell(new Phrase(purchaseOrderBean.getPurchaseorder().getCompanyName(), font8));
				purchasemaster1.addCell(new Phrase("Order No", font8b));
				purchasemaster1.addCell(new Phrase(purchaseOrderBean.getPurchaseorder().getPurchaseOrderNo(), font8));
				
				purchasemaster1.addCell("");
				purchasemaster1.addCell(new Phrase(purchaseOrderBean.getPurchaseorder().getSupplier().getAddress(), font8));
				purchasemaster1.addCell(new Phrase("Order Date", font8b));
				purchasemaster1.addCell(new Phrase(DateUtil.toStringMonth(purchaseOrderBean.getPurchaseorder().getPurchaseOrderDate()), font8));

				
				purchasemaster1.addCell("");
				purchasemaster1.addCell(new Phrase(purchaseOrderBean.getPurchaseorder().getSupplier().getCity()+","+purchaseOrderBean.getPurchaseorder().getSupplier().getState()+","+purchaseOrderBean.getPurchaseorder().getSupplier().getPostCode(), font8));
				purchasemaster1.addCell(new Phrase("Delivery Date", font8b));
				purchasemaster1.addCell(new Phrase(DateUtil.toStringMonth(purchaseOrderBean.getPurchaseorder().getAgreedDateDelivery()), font8));				
					
				
				purchasemaster1.addCell("");
				purchasemaster1.addCell(new Phrase(purchaseOrderBean.getPurchaseorder().getSupplier().getPhoneNo(), font8));
				purchasemaster1.addCell("");
				purchasemaster1.addCell("");
				
				
				document.add(purchasemaster1);	
				document.add(new Paragraph(" "));
				
				//Main Table	
				PdfPTable maintable = new PdfPTable(1);				
				maintable.setHorizontalAlignment(0);
				//maintable.getDefaultCell().setBorder(1);
				maintable.setTotalWidth(f - 22F);
				maintable.setLockedWidth(true);
				maintable.getDefaultCell().setPadding(1f);
				maintable.getDefaultCell().setGrayFill(0.75f);					
				maintable.addCell(new Phrase("", font8b));
				document.add(maintable);
				

				PdfPTable pdfptableproductHader = new PdfPTable(afcolumn5);				
				pdfptableproductHader.setHorizontalAlignment(0);
				//pdfptableproductHader.getDefaultCell().setBorder(1);
				pdfptableproductHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfptableproductHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE); 
				pdfptableproductHader.setTotalWidth(f - 22F);
				pdfptableproductHader.setLockedWidth(true);

				pdfptableproductHader.getDefaultCell().setPadding(5f);
				pdfptableproductHader.getDefaultCell().setGrayFill(0.75f);

				pdfptableproductHader.addCell(new Phrase("SNo     ", font8b));
				pdfptableproductHader.addCell(new Phrase("Reference No", font8b));
				pdfptableproductHader.addCell(new Phrase("Product Code", font8b));
				pdfptableproductHader.addCell(new Phrase("Product Name", font8b));
				pdfptableproductHader.addCell(new Phrase("UOM", font8b));				
				pdfptableproductHader.addCell(new Phrase("Unit Price(RM)", font8b));
				pdfptableproductHader.addCell(new Phrase("Qty", font8b));
				pdfptableproductHader.addCell(new Phrase("Sub Total(RM)", font8b));
								
				
				for(PurchaserequestBranchModel branchData:purchaseOrderBean.getPurchaseorder().getBranchModel())
				{
				
					
				
					PdfPTable pdfptablebranchData = new PdfPTable(purchaseordrBranchColumn);	
					pdfptablebranchData.getDefaultCell().setBorder(0);
					pdfptablebranchData.setHorizontalAlignment(0);
					pdfptablebranchData.setTotalWidth(f - 22F);
					pdfptablebranchData.setLockedWidth(true);
					pdfptablebranchData.getDefaultCell().setPadding(5f);		
					
					pdfptablebranchData.addCell(new Phrase("Delivery Address : ", font8b));
					pdfptablebranchData.addCell(new Phrase(branchData.getBranchName()+"\n"+branchData.getAddress()+"\n"+branchData.getCity()+","+branchData.getState()+","+branchData.getPostCode(), font8));					
					pdfptablebranchData.addCell(new Phrase(branchData.getContactPerson()+"\n"+branchData.getPhoneNo(), font8));					

					document.add(pdfptablebranchData);					
					
					
					
					document.add(pdfptableproductHader);
					
					PdfPTable pdfptableProductData = new PdfPTable(afcolumn5);
					int sno=1;
					int totalQuantity=0;
					sumsubTotal = new BigDecimal(0.00);
					for(PurchaserequestbreakdownModel data:branchData.getProductList())
					{
						
						pdfptableProductData.setHorizontalAlignment(0);
						pdfptableProductData.setTotalWidth(f - 22F);
						//pdfptableProductData.getDefaultCell().setBorder(1);
						pdfptableProductData.setLockedWidth(true);
						pdfptableProductData.getDefaultCell().setPadding(5f);		

						pdfptableProductData.addCell(new Phrase(""+sno, font8));
						pdfptableProductData.addCell(new Phrase(data.getReferenceNo(), font8));
						pdfptableProductData.addCell(new Phrase(data.getProductCode(), font8));
						pdfptableProductData.addCell(new Phrase(data.getProductName(), font8));
						pdfptableProductData.addCell(new Phrase(data.getUomName(), font8));	
						
						PdfPCell table_celld=new PdfPCell(new Phrase(""+data.getUnitPrice(), font8));				
						table_celld.setHorizontalAlignment(Element.ALIGN_RIGHT);
						table_celld.setVerticalAlignment(Element.ALIGN_MIDDLE); 												
						pdfptableProductData.addCell(table_celld);
						
						PdfPCell table_celld1=new PdfPCell(new Phrase(""+data.getQuantityRequired(), font8));				
						table_celld1.setHorizontalAlignment(Element.ALIGN_RIGHT);
						table_celld1.setVerticalAlignment(Element.ALIGN_MIDDLE); 												
						pdfptableProductData.addCell(table_celld1);
						
						PdfPCell table_celld2=new PdfPCell(new Phrase(""+data.getSubTotal(), font8));				
						table_celld2.setHorizontalAlignment(Element.ALIGN_RIGHT);
						table_celld2.setVerticalAlignment(Element.ALIGN_MIDDLE); 												
						pdfptableProductData.addCell(table_celld2);						
						
						sumsubTotal=sumsubTotal.add(data.getSubTotal());			
						totalQuantity=totalQuantity+data.getQuantityRequired();
						sno=sno+1;
					}			
				
					PdfPTable pdfptableproductHader1 = new PdfPTable(afcolumn5A);				
					pdfptableproductHader1.setHorizontalAlignment(0);
					//pdfptableproductHader1.getDefaultCell().setBorder(1);
					pdfptableproductHader1.setTotalWidth(f - 22F);
					pdfptableproductHader1.setLockedWidth(true);
					pdfptableproductHader1.getDefaultCell().setGrayFill(0.75f);					
					pdfptableproductHader1.getDefaultCell().setPadding(5f);	
					
					PdfPCell table_cell=new PdfPCell(new Phrase("Total", font8b));				
					table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table_cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
					table_cell.setGrayFill(0.75f);							
					pdfptableproductHader1.addCell(table_cell);
					
					PdfPCell table_cell1=new PdfPCell(new Phrase(""+totalQuantity, font8b));				
					table_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table_cell1.setVerticalAlignment(Element.ALIGN_MIDDLE); 
					table_cell1.setGrayFill(0.75f);							
					pdfptableproductHader1.addCell(table_cell1);
					
					PdfPCell table_cell2=new PdfPCell(new Phrase(""+sumsubTotal, font8b));				
					table_cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table_cell2.setVerticalAlignment(Element.ALIGN_MIDDLE); 
					table_cell2.setGrayFill(0.75f);							
					pdfptableproductHader1.addCell(table_cell2);					
					
					
					
					document.add(pdfptableProductData);	
					document.add(pdfptableproductHader1);	
					//document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------------------- "));
					document.add(new Paragraph(" "));
					}
				
				
				
				
				document.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

	
	

}
