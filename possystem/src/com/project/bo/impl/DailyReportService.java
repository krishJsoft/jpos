package com.project.bo.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.dao.interfaces.IStaffDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.util.ConfigurationLoad;
import com.project.util.DateUtil;
import com.project.util.EmailUtils;
import com.project.util.IConfigurationLoad;


@Service
public class DailyReportService {

	ConfigurationLoad config = ConfigurationLoad.getConfiguration();
	
	
	 @Autowired
	 private ServletContext servletContext;

	 
		PoscounterModel poscounter = new PoscounterModel();
		
		@Resource(name = "salesOrderBO")
		private ISalesorderBO salesOrderBO;
		
		@Resource(name = "commonListBO")
		private ICommonListBO commonListBO;	
	
		
		@Resource(name = "poscounterBO")
		private IPoscounterBO poscounterBO;
		
		@Resource(name = "branchBO")
		private IBranchBO branchBO;
		
		 
	 List<PoscashtransactionModel> poscashtransactionListObj =new ArrayList<PoscashtransactionModel>();
	 
	 public void dailyReport()
	 {
		 adminReport();
		 ownerReport();
	 }
	 
	 
	 
	 public void adminReport()
	 {
		 poscashtransactionListObj =new ArrayList<PoscashtransactionModel>();
		 PoscashtransactionModel  poscashtransaction = new PoscashtransactionModel();
		 
		  poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
		  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
		  poscashtransaction.setCreditamount(new BigDecimal(0.00));
		  poscashtransaction.setSalesamount(new BigDecimal(0.00));
		  
		 Integer branchId=2;
		 Integer counterId=1;
		 
		 Date dateFrom =DateUtil.getFromTodayDateTime();
		 Date dateTo =DateUtil.getToTodayDateTime();
		 
		
		 
		 try
		 {
		 poscashtransactionListObj=poscounterBO.findByPoscashtransactionList(counterId, null,null,dateFrom,dateTo,null,branchId);
		 
         poscashtransaction=poscounterBO.getPoscashtransactionCounter(poscounter.getCounterId(), dateFrom, dateTo, null,null,branchId);
         if(poscashtransaction!=null)
         {		
          poscashtransaction.setBalanceAmount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getDebitAmount()));
		  poscashtransaction.setTotalAmount(getSalesConterTotal(poscashtransactionListObj));	 
		  poscashtransaction.setCreditamount(poscashtransaction.getReceivedAmount().subtract(poscashtransaction.getTotalAmount()));
		  poscashtransaction.setSalesamount(poscashtransaction.getTotalAmount().subtract(poscashtransaction.getTotalTax()));
         }
         
         
         generateWeekExpensePDF(poscashtransactionListObj, poscashtransaction, "dailyreport.pdf", "Daily Report", branchId);
         
         String filename = servletContext.getRealPath("") + File.separator+ "document" + File.separator + "dailyreport" + File.separator	+"dailyreport.pdf";
		 List<String> attachments = new ArrayList<String>();
		 attachments.add(filename);
		 EmailUtils.dailyreportEmail(attachments);
		
			
		 }
		 catch(Exception e)
		 {
			 
		 }

		 
	 }
	 
	 public void ownerReport()
	 {
		 poscashtransactionListObj =new ArrayList<PoscashtransactionModel>();
		 PoscashtransactionModel  poscashtransaction = new PoscashtransactionModel();
			List<SalesorderModel> salesOrder = new ArrayList<SalesorderModel>(); 

			 poscashtransaction.setBalanceAmount(new BigDecimal(0.00));
			  poscashtransaction.setTotalAmount(new BigDecimal(0.00));	 
			  poscashtransaction.setCreditamount(new BigDecimal(0.00));
			  poscashtransaction.setSalesamount(new BigDecimal(0.00));
			

		 BigDecimal totalAmount = new BigDecimal(0.00);
		  BigDecimal totalTax = new BigDecimal(0.00);
		  
		 Integer branchId=2;
		 Integer counterId=1;
		 
		 Date dateFrom =DateUtil.getFromTodayDateTime();
		 Date dateTo =DateUtil.getToTodayDateTime();
		 
		 try
		 {
		salesOrder=salesOrderBO.getSalesorderReportListowner(null, null, null, dateFrom, dateTo, null, branchId);

		for(SalesorderModel data : salesOrder)
		{
			totalAmount=totalAmount.add(data.getTotalAmount());
			totalTax=totalTax.add(data.getTotalTax());
		}
		
		poscashtransaction.setTotalAmount(totalAmount);
		poscashtransaction.setSalesamount(totalAmount.subtract(totalTax));
		poscashtransaction.setTotalTax(totalTax);
		
		
		generateAdminReportPDF(salesOrder, poscashtransaction, "dailyadminreport.pdf", "Daily Admin Report", branchId);
        
        String filename = servletContext.getRealPath("") + File.separator+ "document" + File.separator + "dailyreport" + File.separator	+"dailyadminreport.pdf";
		 List<String> attachments = new ArrayList<String>();
		 attachments.add(filename);
		 EmailUtils.dailyreportEmail(attachments);
		
		 }
		 catch(Exception e)
		 {
			 
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
		
	 
	 
	 
	 
	 public void generateWeekExpensePDF(List<PoscashtransactionModel> expenseList ,PoscashtransactionModel poscashtransaction, String filepdfname , String reporttitle ,Integer branchId)
		{			
		try {						
			String companylogo = "logo";
			Document document = new Document(PageSize.A4, 10, 10, 5, 5);				
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(servletContext.getRealPath("") + File.separator+ "document" + File.separator + "dailyreport" + File.separator	+filepdfname));			
		
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

			float f = document.getPageSize().getWidth();
			float f1 = document.getPageSize().getHeight();		
			float headerTitle[] = { 100F};
			float af[] = { 60F, 40F };	
			float headerColumn[] = { 15F, 20F, 65F };		
			float purchaseordrmaster3Column[] = { 20F, 30F , 20F , 30F };		
			float afcolumn5[] = { 5F, 15F, 20F,10F, 30F, 10F};
			float afcolumn3[] = { 20F, 40F, 40F};

			
			BranchModel branch = branchBO.getBranchDetails(branchId);
			String logoName = servletContext.getRealPath("")  + File.separator + "report" + File.separator + "images" + File.separator	+ branch.getBranchId() + ".jpg";

			String GST= config.getValue(IConfigurationLoad.PRODUCT_GST_NUMBER);
			String REG= config.getValue(IConfigurationLoad.PRODUCT_REG_NUMBER);

			
			HeaderFooter footer;
			footer = new HeaderFooter(	new Paragraph("\t\t @ \t "+branch.getBranchName()+""
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
					
			header1.addCell(new Phrase(branch.getBranchName() , font15b));					
			header1.addCell(new Phrase(branch.getAddress(), font8bb));		
			header1.addCell(new Phrase(branch.getEmailAddress(), font8bb));								
			header1.addCell(new Phrase(branch.getPhoneNo(), font8bb));
			
			PdfPCell table_cel2=new PdfPCell(header1);				
			table_cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cel2.setVerticalAlignment(Element.ALIGN_TOP); 
			table_cel2.setBorder(0);
			
			header.addCell(image);	
			header.addCell("");
			header.addCell(table_cel2);				
			
						
				
			
			document.add(header);
			
			PdfPTable pdfptableTitleHader = new PdfPTable(headerTitle);
			pdfptableTitleHader.getDefaultCell().setBorder(1);			
			pdfptableTitleHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfptableTitleHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP); 
			pdfptableTitleHader.setTotalWidth(f - 22F);
			pdfptableTitleHader.setLockedWidth(true);
			pdfptableTitleHader.getDefaultCell().setPadding(5f);
			pdfptableTitleHader.getDefaultCell().setGrayFill(0.75f);
			pdfptableTitleHader.addCell(new Phrase(reporttitle+"  -   " +DateUtil.getFromTodayDateTime() + "  \t\t\t    \t\t\t\t \t\t\t\t\t\t\t", font12b));
			document.add(pdfptableTitleHader);	
			
			
			PdfPTable purchasemaster1 = new PdfPTable(purchaseordrmaster3Column);	
			purchasemaster1.getDefaultCell().setBorder(1);
			purchasemaster1.setHorizontalAlignment(0);
			purchasemaster1.setTotalWidth(f - 22F);
			purchasemaster1.setLockedWidth(true);
			purchasemaster1.getDefaultCell().setPadding(2f);		
		
			purchasemaster1.addCell(new Phrase("GST No ", font8b));
			purchasemaster1.addCell(new Phrase(GST, font8));
			purchasemaster1.addCell(new Phrase("REG No", font8b));
			purchasemaster1.addCell(new Phrase(REG, font8));	
			purchasemaster1.addCell(new Phrase("Terminal", font8b));
			purchasemaster1.addCell(new Phrase("Terminal 1", font8));
			
			
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
			

			PdfPTable pdfptableproductHader = new PdfPTable(afcolumn3);				
			pdfptableproductHader.setHorizontalAlignment(0);
			//pdfptableproductHader.getDefaultCell().setBorder(1);
			pdfptableproductHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfptableproductHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE); 
			pdfptableproductHader.setTotalWidth(f - 22F);
			pdfptableproductHader.setLockedWidth(true);

			pdfptableproductHader.getDefaultCell().setPadding(5f);
			pdfptableproductHader.getDefaultCell().setGrayFill(0.75f);

			pdfptableproductHader.addCell(new Phrase("SNo     ", font8b));
			pdfptableproductHader.addCell(new Phrase("Invoice No", font8b));
			pdfptableproductHader.addCell(new Phrase("Amount", font8b));
			
				document.add(pdfptableproductHader);			
				PdfPTable pdfptableProductData = new PdfPTable(afcolumn3);
				int sno=1;
				int totalQuantity=0;
				sumsubTotal = new BigDecimal(0.00);
				for(PoscashtransactionModel data:expenseList)
				{
					
					pdfptableProductData.setHorizontalAlignment(0);
					pdfptableProductData.setTotalWidth(f - 22F);				
					pdfptableProductData.setLockedWidth(true);
					pdfptableProductData.getDefaultCell().setPadding(5f);		

					pdfptableProductData.addCell(new Phrase(""+sno, font8));
					pdfptableProductData.addCell(new Phrase(data.getSalesOrderNo(), font8));
					pdfptableProductData.addCell(new Phrase(""+data.getCreditamount(), font8));
					
					sno=sno+1;
				}			
					
				
				document.add(pdfptableProductData);	
				
				document.add(new Paragraph(" CASH COLLECTION "));
				
				document.add(new Paragraph(" "));

				
				PdfPTable purchasemaster2 = new PdfPTable(purchaseordrmaster3Column);	
				purchasemaster2.getDefaultCell().setBorder(0);
				purchasemaster2.setHorizontalAlignment(0);
				purchasemaster2.setTotalWidth(f - 22F);
				purchasemaster2.setLockedWidth(true);
				purchasemaster2.getDefaultCell().setPadding(2f);	
				
				purchasemaster2.addCell(new Phrase("Total Amount In (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getTotalAmount(), font8));
				purchasemaster2.addCell(new Phrase("Total Amount Out (RM)", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getDebitAmount(), font8));	
				purchasemaster2.addCell(new Phrase("Total Sales (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getSalesamount(), font8));
				purchasemaster2.addCell(new Phrase("Total GST (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getTotalTax(), font8));
				
				
				document.add(purchasemaster2);	
			
				document.add(new Paragraph(" "));
				
				
			
			document.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	 
	 
	 public void generateAdminReportPDF(List<SalesorderModel> expenseList ,PoscashtransactionModel poscashtransaction, String filepdfname , String reporttitle ,Integer branchId)
		{			
		try {						
			String companylogo = "logo";
			Document document = new Document(PageSize.A4, 10, 10, 5, 5);				
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(servletContext.getRealPath("") + File.separator+ "document" + File.separator + "dailyreport" + File.separator	+filepdfname));			
		
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

			float f = document.getPageSize().getWidth();
			float f1 = document.getPageSize().getHeight();		
			float headerTitle[] = { 100F};
			float af[] = { 60F, 40F };	
			float headerColumn[] = { 15F, 20F, 65F };		
			float purchaseordrmaster3Column[] = { 20F, 30F , 20F , 30F };		
			float afcolumn5[] = { 5F, 15F, 20F,10F, 30F, 10F};
			float afcolumn3[] = { 20F, 40F, 40F};

			
			BranchModel branch = branchBO.getBranchDetails(branchId);
			String logoName = servletContext.getRealPath("")  + File.separator + "report" + File.separator + "images" + File.separator	+ branch.getBranchId() + ".jpg";

			String GST= config.getValue(IConfigurationLoad.PRODUCT_GST_NUMBER);
			String REG= config.getValue(IConfigurationLoad.PRODUCT_REG_NUMBER);

			
			HeaderFooter footer;
			footer = new HeaderFooter(	new Paragraph("\t\t @ \t "+branch.getBranchName()+""
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
					
			header1.addCell(new Phrase(branch.getBranchName() , font15b));					
			header1.addCell(new Phrase(branch.getAddress(), font8bb));		
			header1.addCell(new Phrase(branch.getEmailAddress(), font8bb));								
			header1.addCell(new Phrase(branch.getPhoneNo(), font8bb));
			
			PdfPCell table_cel2=new PdfPCell(header1);				
			table_cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cel2.setVerticalAlignment(Element.ALIGN_TOP); 
			table_cel2.setBorder(0);
			
			header.addCell(image);	
			header.addCell("");
			header.addCell(table_cel2);				
			
						
				
			
			document.add(header);
			
			PdfPTable pdfptableTitleHader = new PdfPTable(headerTitle);
			pdfptableTitleHader.getDefaultCell().setBorder(1);			
			pdfptableTitleHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfptableTitleHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP); 
			pdfptableTitleHader.setTotalWidth(f - 22F);
			pdfptableTitleHader.setLockedWidth(true);
			pdfptableTitleHader.getDefaultCell().setPadding(5f);
			pdfptableTitleHader.getDefaultCell().setGrayFill(0.75f);
			pdfptableTitleHader.addCell(new Phrase(reporttitle+"  -   " +DateUtil.getFromTodayDateTime() + "  \t\t\t    \t\t\t\t \t\t\t\t\t\t\t", font12b));
			document.add(pdfptableTitleHader);	
			
			
			PdfPTable purchasemaster1 = new PdfPTable(purchaseordrmaster3Column);	
			purchasemaster1.getDefaultCell().setBorder(1);
			purchasemaster1.setHorizontalAlignment(0);
			purchasemaster1.setTotalWidth(f - 22F);
			purchasemaster1.setLockedWidth(true);
			purchasemaster1.getDefaultCell().setPadding(2f);		
		
			purchasemaster1.addCell(new Phrase("GST No ", font8b));
			purchasemaster1.addCell(new Phrase(GST, font8));
			purchasemaster1.addCell(new Phrase("REG No", font8b));
			purchasemaster1.addCell(new Phrase(REG, font8));	
			purchasemaster1.addCell(new Phrase("Terminal ", font8b));
			purchasemaster1.addCell(new Phrase("Terminal 1", font8));
			
			
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
			

			PdfPTable pdfptableproductHader = new PdfPTable(afcolumn3);				
			pdfptableproductHader.setHorizontalAlignment(0);
			//pdfptableproductHader.getDefaultCell().setBorder(1);
			pdfptableproductHader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfptableproductHader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE); 
			pdfptableproductHader.setTotalWidth(f - 22F);
			pdfptableproductHader.setLockedWidth(true);

			pdfptableproductHader.getDefaultCell().setPadding(5f);
			pdfptableproductHader.getDefaultCell().setGrayFill(0.75f);

			pdfptableproductHader.addCell(new Phrase("SNo     ", font8b));
			pdfptableproductHader.addCell(new Phrase("Invoice No", font8b));
			pdfptableproductHader.addCell(new Phrase("Amount", font8b));
			
				document.add(pdfptableproductHader);			
				PdfPTable pdfptableProductData = new PdfPTable(afcolumn3);
				int sno=1;
				int totalQuantity=0;
				sumsubTotal = new BigDecimal(0.00);
				for(SalesorderModel data:expenseList)
				{
					
					pdfptableProductData.setHorizontalAlignment(0);
					pdfptableProductData.setTotalWidth(f - 22F);				
					pdfptableProductData.setLockedWidth(true);
					pdfptableProductData.getDefaultCell().setPadding(5f);		

					pdfptableProductData.addCell(new Phrase(""+sno, font8));
					pdfptableProductData.addCell(new Phrase(data.getSalesOrderNo(), font8));
					pdfptableProductData.addCell(new Phrase(""+data.getTotalAmount(), font8));
					
					sno=sno+1;
				}			
					
				
				document.add(pdfptableProductData);	
				
				document.add(new Paragraph(" CASH COLLECTION "));
				
				document.add(new Paragraph(" "));

				PdfPTable purchasemaster2 = new PdfPTable(purchaseordrmaster3Column);	
				purchasemaster2.getDefaultCell().setBorder(0);
				purchasemaster2.setHorizontalAlignment(0);
				purchasemaster2.setTotalWidth(f - 22F);
				purchasemaster2.setLockedWidth(true);
				purchasemaster2.getDefaultCell().setPadding(2f);	
				
				purchasemaster2.addCell(new Phrase("Total Amount In (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getTotalAmount(), font8));
				purchasemaster2.addCell(new Phrase("Total Amount Out (RM)", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getDebitAmount(), font8));	
				purchasemaster2.addCell(new Phrase("Total Sales (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getSalesamount(), font8));
				purchasemaster2.addCell(new Phrase("Total GST (RM) ", font8b));
				purchasemaster2.addCell(new Phrase(""+poscashtransaction.getTotalTax(), font8));
				
				
				document.add(purchasemaster2);	
			
				document.add(new Paragraph(" "));
				
				
			
			document.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	 
}
