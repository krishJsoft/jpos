package com.project.scheduler.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.dao.interfaces.IBranchDAO;
import com.project.dao.interfaces.IBranchinvoiceDAO;
import com.project.dao.interfaces.ICommissionDAO;
import com.project.dao.interfaces.IDoctorsPrescriptionsDAO;
import com.project.dao.interfaces.IGstaccountDAO;
import com.project.dao.interfaces.ISalesorderDAO;
import com.project.dao.interfaces.ISupplierinvoiceDAO;
import com.project.model.his.Branch;
import com.project.model.his.Branchinvoice;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Commission;
import com.project.model.his.Commissionbreakdown;
import com.project.model.his.Doctorprescription;
import com.project.model.his.Gstaccount;
import com.project.model.his.Gstpurchaseaccountbreakdown;
import com.project.model.his.Gstsalesaccountbreakdown;
import com.project.model.his.Salesorder;
import com.project.model.his.Supplierinvoice;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 03 Nov 2014
 * 
 */


@Service
public class GstAccountGeneration {

	public static Logger log = LoggerFactory.getLogger(GstAccountGeneration.class);

	@Resource(name = "branchInvoiceRepository")
	private IBranchinvoiceDAO branchInvoiceRepository;	
	
	@Resource(name = "supplierInvoiceRepository")
	private ISupplierinvoiceDAO supplierInvoiceRepository;
	
	@Resource(name = "branchRepository")
	private IBranchDAO branchRepository;
		
	@Resource(name = "gstaccountRepository")
	private IGstaccountDAO gstaccountRepository;
	
	
	@SuppressWarnings("resource")
	public void executeTaxAccount() {
		try
		{		
			System.out.println("Execute execute Tax Account. Current time is :: "+ new Date());	
			Calendar aCalendar = Calendar.getInstance();
			aCalendar.set(Calendar.DATE, 1);
			aCalendar.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDateOfPreviousMonth = aCalendar.getTime();
			aCalendar.set(Calendar.DATE, 1);
			Date firstDateOfPreviousMonth = aCalendar.getTime();
			
			List<Branch> branchList = branchRepository.findByBranchList(null, null, null, "1");
					
			for(Branch branch:branchList)
			{			
			BigDecimal totalSalesTax = new  BigDecimal(0.00);
			BigDecimal totalPurchaseTax = new BigDecimal(0.00);
			Gstaccount gst  = new Gstaccount();
			List<Gstpurchaseaccountbreakdown> gstpurchaseaccountbreakdowns = new ArrayList<Gstpurchaseaccountbreakdown>();
			List<Gstsalesaccountbreakdown> gstsalesaccountbreakdowns= new ArrayList<Gstsalesaccountbreakdown>();
						
			List<Branchinvoice> getGstSalesAccountList=branchInvoiceRepository.getGstAccount(firstDateOfPreviousMonth, lastDateOfPreviousMonth, "0", branch.getBranchId());				
			List<Supplierinvoice> getGstPurchaseAccountList=supplierInvoiceRepository.getGstAccount(null, null,"3", "0", branch.getBranchId()); // fully settled invoices
			
			gst.setBranch(branch);
			gst.setBalancepaid(new  BigDecimal(0.00));
			gst.setGenaratedDate(new Date());			
			gst.setTaxinvoiceperiod(firstDateOfPreviousMonth);
			gst.setTaxinvoiceperiodto(lastDateOfPreviousMonth);
			
			
			if(getGstSalesAccountList!=null && getGstSalesAccountList.size()!=0)
			{
				for(Branchinvoice tempData:getGstSalesAccountList)
				{					
				Gstsalesaccountbreakdown salesInvoiceItem = new Gstsalesaccountbreakdown();
				salesInvoiceItem.setGstaccount(gst);
				salesInvoiceItem.setBranchinvoice(tempData);
				salesInvoiceItem.setTax(tempData.getTax());
				gstsalesaccountbreakdowns.add(salesInvoiceItem);
				
				totalSalesTax=totalSalesTax.add(tempData.getTax());	
				tempData.setAccountstatus("1"); // Closed
				this.updateBranchinvoice(tempData);
				}			
			}			
			gst.setTotalsalestax(totalSalesTax);
			
			if(getGstPurchaseAccountList!=null && getGstPurchaseAccountList.size()!=0)
			{
				for(Supplierinvoice tempData:getGstPurchaseAccountList)
				{						
				Gstpurchaseaccountbreakdown	purchaseInvoiceItem = new Gstpurchaseaccountbreakdown();
				purchaseInvoiceItem.setGstaccount(gst);
				purchaseInvoiceItem.setSupplierinvoice(tempData);
				purchaseInvoiceItem.setTax(tempData.getTax());
				gstpurchaseaccountbreakdowns.add(purchaseInvoiceItem);
				
				totalPurchaseTax=totalPurchaseTax.add(tempData.getTax());	
				tempData.setAccountstatus("1"); // Closed
				this.updateSupplierinvoice(tempData);
				}			
			}				
						
			gst.setTotalpurchasetax(totalPurchaseTax);			
			gst.setBalancepay(new  BigDecimal(0.00));	
			gst.setStatus("0"); // New  , not yet paid.
			gst.setGstpurchaseaccountbreakdowns(gstpurchaseaccountbreakdowns);
			gst.setGstsalesaccountbreakdowns(gstsalesaccountbreakdowns);						
						
			this.createNewGstaccount(gst);	
			
			branch.setTotalPurchaseTax(branch.getTotalPurchaseTax().add(totalPurchaseTax));
			branch.setTotalSalesTax(branch.getTotalSalesTax().add(totalSalesTax));			
			branchRepository.updateBranch(branch);
						
			}
			
			
		}
		catch(Exception e)
		{
			log.debug("Error in execute of executeDoctorCommission ..." +e.toString());
		}
	}
	
	
	
	
	
	@SuppressWarnings("resource")
	public void executeGSTTaxAccount() {
		try
		{		
			System.out.println("Execute execute Tax Account. Current time is :: "+ new Date());	
			Calendar aCalendar = Calendar.getInstance();
			aCalendar.set(Calendar.DATE, 1);
			aCalendar.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDateOfPreviousMonth = aCalendar.getTime();
			aCalendar.set(Calendar.DATE, 1);
			Date firstDateOfPreviousMonth = aCalendar.getTime();
			
			List<Branch> branchList = branchRepository.findByBranchList(null, null, null, "1");
					
			for(Branch branch:branchList)
			{			
			BigDecimal totalSalesTax = new  BigDecimal(0.00);
			BigDecimal totalPurchaseTax = new BigDecimal(0.00);
			Gstaccount gst  = new Gstaccount();
			List<Gstpurchaseaccountbreakdown> gstpurchaseaccountbreakdowns = new ArrayList<Gstpurchaseaccountbreakdown>();
			List<Gstsalesaccountbreakdown> gstsalesaccountbreakdowns= new ArrayList<Gstsalesaccountbreakdown>();
						
			List<Branchinvoice> getGstSalesAccountList=branchInvoiceRepository.getGstAccount(firstDateOfPreviousMonth, lastDateOfPreviousMonth, "0", branch.getBranchId());				
			//List<Supplierinvoice> getGstPurchaseAccountList=supplierInvoiceRepository.getGstAccount(null, null,"3", "0", branch.getBranchId()); // fully settled invoices
			
			gst.setBranch(branch);
			gst.setBalancepaid(new  BigDecimal(0.00));
			gst.setGenaratedDate(new Date());			
			gst.setTaxinvoiceperiod(firstDateOfPreviousMonth);
			gst.setTaxinvoiceperiodto(lastDateOfPreviousMonth);
			
			
			if(getGstSalesAccountList!=null && getGstSalesAccountList.size()!=0)
			{
				for(Branchinvoice tempData:getGstSalesAccountList)
				{					
				Gstsalesaccountbreakdown salesInvoiceItem = new Gstsalesaccountbreakdown();
				salesInvoiceItem.setGstaccount(gst);
				salesInvoiceItem.setBranchinvoice(tempData);
				salesInvoiceItem.setTax(tempData.getTax());
				gstsalesaccountbreakdowns.add(salesInvoiceItem);
				
				totalSalesTax=totalSalesTax.add(tempData.getTax());	
				tempData.setAccountstatus("1"); // Closed
				this.updateBranchinvoice(tempData);
				}			
			}			
			gst.setTotalsalestax(totalSalesTax);
			
			
			gst.setTotalpurchasetax(totalPurchaseTax);			
			gst.setBalancepay(new  BigDecimal(0.00));	
			gst.setStatus("0"); // New  , not yet paid.
			//gst.setGstpurchaseaccountbreakdowns(gstpurchaseaccountbreakdowns);
			gst.setGstsalesaccountbreakdowns(gstsalesaccountbreakdowns);						
						
			this.createNewGstaccount(gst);	
			
			branch.setTotalPurchaseTax(branch.getTotalPurchaseTax().add(totalPurchaseTax));
			branch.setTotalSalesTax(branch.getTotalSalesTax().add(totalSalesTax));			
			branchRepository.updateBranch(branch);
						
			}
			
			
		}
		catch(Exception e)
		{
			log.debug("Error in execute of executeDoctorCommission ..." +e.toString());
		}
	}
	
	
	
	
	
	@Transactional
	public void createNewGstaccount(Gstaccount gstaccount)
	{
		try
		{ 	    boolean saveSuccess=false;			
				saveSuccess=gstaccountRepository.createNewGstaccount(gstaccount);					
		}
		catch(Exception e)
		{
			log.debug("Error in execute of createNewGstaccount ..." +e.toString());
		}
	}
			
	
	@Transactional
	public void updateBranchinvoice(Branchinvoice invoice)
	{
		try
		{ 	    boolean saveSuccess=false;			
				saveSuccess=branchInvoiceRepository.updateBranchinvoice(invoice);					
		}
		catch(Exception e)
		{
			log.debug("Error in execute of updateBranchinvoice ..." +e.toString());
		}
	}
		
	
	@Transactional
	public void updateSupplierinvoice(Supplierinvoice invoice)
	{
		try
		{ 	    boolean saveSuccess=false;			
				saveSuccess=supplierInvoiceRepository.updateSupplierinvoice(invoice);					
		}
		catch(Exception e)
		{
			log.debug("Error in execute of updateSupplierinvoice ..." +e.toString());
		}
	}
	
	
	
}
