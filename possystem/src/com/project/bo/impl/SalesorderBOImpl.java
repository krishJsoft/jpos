package com.project.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;








import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.bo.util.DecimalUtil;
import com.project.common.factory.BeanContext;
import com.project.dao.interfaces.IBranchDAO;
import com.project.dao.interfaces.ICommonListDAO;
import com.project.dao.interfaces.ICustomerDAO;
import com.project.dao.interfaces.IProductDAO;
import com.project.dao.interfaces.ISalesorderDAO;
import com.project.dao.interfaces.ISalesorderonlineDAO;
import com.project.login.LoginBean;
import com.project.model.his.Autonum;
import com.project.model.his.Branch;
import com.project.model.his.Branchinvoicebreakdown;
import com.project.model.his.Branchiteminvoice;
import com.project.model.his.Branchiteminvoicebreakdown;
import com.project.model.his.Customer;
import com.project.model.his.Deliveryorderbreakdown;
import com.project.model.his.Doctorprescription;
import com.project.model.his.Hoteltable;
import com.project.model.his.Invoicedispatch;
import com.project.model.his.Itemremarkslist;
import com.project.model.his.Kitchenorderbreakdown;
import com.project.model.his.Kitchenorderremarksbreakdown;
import com.project.model.his.Kitchensorder;
import com.project.model.his.Poscounter;
import com.project.model.his.Pospayment;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Purchaserequestbreakdown;
import com.project.model.his.Quotation;
import com.project.model.his.Quotationbreakdown;
import com.project.model.his.Salesitem;
import com.project.model.his.Salesitembreakdown;
import com.project.model.his.Salesorderbreakdown;
import com.project.model.his.Salesorder;
import com.project.model.his.Salesorderbreakdownhold;
import com.project.model.his.Salesorderhold;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.sale.sales.BranchSalesModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.KitchenorderremarksbreakdownModel;
import com.project.model.sale.sales.PaymentCollectionModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.DateUtil;

/*
 * @author Gopal Ar
 * @since 10 Sep 2013
 */

@Service("salesOrderBO")
public class SalesorderBOImpl implements ISalesorderBO {
	
	public static Logger log = LoggerFactory.getLogger(SalesorderBOImpl.class);

	
	@Resource(name = "commonListRepository")
	private ICommonListDAO commonListRepository;
	
	
	@Resource(name = "productRepository")
	private IProductDAO productRepository;	
	
	@Resource(name = "salesOrderRepository")
	private ISalesorderDAO salesOrderRepository;
	
	@Resource(name = "salesOrderOnlineRepository")
	private ISalesorderonlineDAO salesOrderOnlineRepository;
	
	@Resource(name = "poscounterBO")
	private IPoscounterBO poscounterBO;
	
	@Resource(name = "staffBO")
	private IStaffBO staffBO;
	
	@Resource(name = "branchRepository")
	private IBranchDAO branchRepository;
	

	@Resource(name = "customerRepository")
	private ICustomerDAO customerRepository;
	 
	
	@Override
	public List<SalesorderModel> findBySalesorderListAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSalesorderCount(String salesOrderNo, Integer customerId,
			Integer branchId, Date dateFrom, Date dateTo, String status,Integer branchRecordId,String salesType,String paymentType, String salesRep)
			throws Exception {
		try {
			return salesOrderRepository.getSalesorderCount(salesOrderNo, customerId, branchId, dateFrom, dateTo, status,branchRecordId,salesType,paymentType,salesRep);
		} catch (Exception e) {
			log.info("Error in getSalesorderCount of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	@Override
	public List<SalesorderModel> getSalesorderList(int[] ids,String salesOrderNo,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, int start, int howMany,Integer branchRecordId,String salesType,String paymentType, String salesRep) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
	try {
	List<Salesorder> salesorderList = salesOrderRepository.getSalesorderList(ids,salesOrderNo, customerId, branchId, dateFrom, dateTo, status, start, howMany,branchRecordId,salesType,paymentType,salesRep);

	for (Salesorder salesorder : salesorderList) {
		
		SalesorderModel salesorderObj = new SalesorderModel();
		
		salesorderObj.setCreatedBy(salesorder.getCreatedBy());
		salesorderObj.setCreatedDate(salesorder.getCreatedDate());
		salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
		salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
		salesorderObj.setDueDate(salesorder.getDueDate());
		salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
		salesorderObj.setOrderPayment(salesorder.getOrderPayment());
		salesorderObj.setOrderReturned(salesorder.getOrderReturned());
		salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
		salesorderObj.setPaymentType(salesorder.getPaymentType());
		salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
		
		salesorderObj.setRemarks(salesorder.getRemarks());
		salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
		salesorderObj.setSalesDate(salesorder.getSalesDate());
		salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
		salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesorderObj.setSalesRep(salesorder.getSalesRep());
		salesorderObj.setSalesType(salesorder.getSalesType());
		salesorderObj.setShippedDate(salesorder.getShippedDate());
		salesorderObj.setStatus(salesorder.getStatus());
		salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
		salesorderObj.setTotalAmount(salesorder.getTotalAmount());
		salesorderObj.setTotalTax(salesorder.getTotalTax());
		salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());

		
		if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
		{
				
			salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
			salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
			salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
			salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
		}		
		salesorderModelObjList.add(salesorderObj);
	}
	}
	catch(Exception e)
	{
		log.info("Error in getDeliveryorderList of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorderModelObjList;
	}
	
	
	
	@Override
	public List<SalesorderModel> getSalesorderReportList(String salesOrderNo,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status,Integer branchRecordId, String paymentType , String salesRep) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
	try {
	List<Salesorder> salesorderList = salesOrderRepository.getSalesorderReportList(salesOrderNo, customerId, branchId, dateFrom, dateTo, status, branchRecordId,paymentType,salesRep);

	for (Salesorder salesorder : salesorderList) {
		
		SalesorderModel salesorderObj = new SalesorderModel();
		
		salesorderObj.setCreatedBy(salesorder.getCreatedBy());
		salesorderObj.setCreatedDate(salesorder.getCreatedDate());
		salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
		salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
		salesorderObj.setDueDate(salesorder.getDueDate());
		salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
		salesorderObj.setOrderPayment(salesorder.getOrderPayment());
		salesorderObj.setOrderReturned(salesorder.getOrderReturned());
		salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
		salesorderObj.setPaymentType(salesorder.getPaymentType());
		salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
		
		
		salesorderObj.setRemarks(salesorder.getRemarks());
		salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
		salesorderObj.setSalesDate(salesorder.getSalesDate());
		salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
		salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesorderObj.setSalesRep(salesorder.getSalesRep());
		salesorderObj.setSalesType(salesorder.getSalesType());
		salesorderObj.setShippedDate(salesorder.getShippedDate());
		salesorderObj.setStatus(salesorder.getStatus());
		salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
		salesorderObj.setTotalAmount(salesorder.getTotalAmount());
		salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
		salesorderObj.setTotalTax(salesorder.getTotalTax());
		
		if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
		{
				
			salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
			salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
			salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
			salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
		}	
		
		salesorderModelObjList.add(salesorderObj);
	}
	}
	catch(Exception e)
	{
		log.info("Error in getSalesorderReportList of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorderModelObjList;
	}
	
	
	
	@Override
	public List<SalesorderModel> getSalesorderReportListowner(String salesOrderNo,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status,Integer branchRecordId) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
	try {
	List<Salesitem> salesorderList = salesOrderRepository.getSalesorderReportListowner(salesOrderNo, customerId, branchId, dateFrom, dateTo, status, branchRecordId);

	for (Salesitem salesorder : salesorderList) {
		
		SalesorderModel salesorderObj = new SalesorderModel();
		
		salesorderObj.setCreatedBy(salesorder.getCreatedBy());
		salesorderObj.setCreatedDate(salesorder.getCreatedDate());
		salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
		salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
		salesorderObj.setDueDate(salesorder.getDueDate());
		salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
		salesorderObj.setOrderPayment(salesorder.getOrderPayment());
		salesorderObj.setOrderReturned(salesorder.getOrderReturned());
		salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
		salesorderObj.setPaymentType(salesorder.getPaymentType());
		salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
		
		salesorderObj.setRemarks(salesorder.getRemarks());
		salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
		salesorderObj.setSalesDate(salesorder.getSalesDate());
		salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
		salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesorderObj.setSalesRep(salesorder.getSalesRep());
		salesorderObj.setSalesType(salesorder.getSalesType());
		salesorderObj.setShippedDate(salesorder.getShippedDate());
		salesorderObj.setStatus(salesorder.getStatus());
		salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
		salesorderObj.setTotalAmount(salesorder.getTotalAmount());
		salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
		salesorderObj.setTotalTax(salesorder.getTotalTax());
		
		if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
		{
				
			salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
			salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
			salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
			salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
		}		
		salesorderModelObjList.add(salesorderObj);
	}
	}
	catch(Exception e)
	{
		log.info("Error in getSalesorderReportList of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorderModelObjList;
	}
	

	
	
	
	@Override
	public List<SalesorderModel> getSalesorderholdList(Integer salesOrderId, Integer counterId,Integer branchRecordId) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
	try {
	List<Salesorderhold> salesorderList = salesOrderRepository.getSalesorderholdList(salesOrderId, counterId,branchRecordId);
	CustomerModel customer = new CustomerModel();
	BranchModel branch = new BranchModel();
	List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();
	for (Salesorderhold salesorder : salesorderList) {
		
		SalesorderModel salesorderObj = new SalesorderModel();
		
		salesorderObj.setCreatedBy(salesorder.getCreatedBy());
		salesorderObj.setCreatedDate(salesorder.getCreatedDate());
		salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
		salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
		salesorderObj.setDueDate(salesorder.getDueDate());
		salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
		salesorderObj.setOrderPayment(salesorder.getOrderPayment());
		salesorderObj.setOrderReturned(salesorder.getOrderReturned());
		salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
		salesorderObj.setPaymentType(salesorder.getPaymentType());
		salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
		
		salesorderObj.setRemarks(salesorder.getRemarks());
		salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
		salesorderObj.setSalesDate(salesorder.getSalesDate());
		salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
		salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesorderObj.setSalesRep(salesorder.getSalesRep());
		salesorderObj.setSalesType(salesorder.getSalesType());
		salesorderObj.setShippedDate(salesorder.getShippedDate());
		salesorderObj.setStatus(salesorder.getStatus());
		salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
		salesorderObj.setTotalAmount(salesorder.getTotalAmount());
		salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
		salesorderObj.setTotalTax(salesorder.getTotalTax());		
		
		
		int id = 0;	
		
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
			salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());				
			salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
			branch.setAddress(salesorder.getBranch2().getAddress());
			branch.setCity(salesorder.getBranch2().getCity());
			branch.setState(salesorder.getBranch2().getState());
			branch.setPhoneNo(salesorder.getBranch2().getPhoneNo());
			branch.setPostCode(salesorder.getBranch2().getPostCode());
			branch.setBranchId(salesorder.getBranch2().getBranchId());
			branch.setBranchName(salesorder.getBranch2().getBranchName());
			

			
		}
		if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
			salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			customer.setCustomerName(salesorder.getCustomer().getCustomerName());
			customer.setAddress(salesorder.getCustomer().getAddress());
			customer.setCity(salesorder.getCustomer().getCity());
			customer.setCountry(salesorder.getCustomer().getCountry());
			customer.setState(salesorder.getCustomer().getState());
			customer.setPhoneNo(salesorder.getCustomer().getPhoneNo());
			customer.setPostCode(salesorder.getCustomer().getPostCode());
			customer.setCustomerId(salesorder.getCustomer().getCustomerId());
			customer.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
			salesorderObj.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
		}	
		
		salesorderObj.setCustomer(customer);
		salesorderObj.setBranch(branch);	
		
		
		salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
		for(Salesorderbreakdownhold tempdata:salesorder.getSalesorderbreakdowns())
		{
			SalesorderbreakdownModel data=new SalesorderbreakdownModel();
						
			data.setDiscount(tempdata.getDiscount());
			data.setDiscountAmount(tempdata.getDiscountAmount());							
			data.setProductId(tempdata.getProduct().getProductId());						
			data.setProductName(tempdata.getProduct().getProductName());
			data.setProductCode(tempdata.getProduct().getBarcode());
			data.setUom(tempdata.getProduct().getUom());
			data.setQuantity(tempdata.getQuantity());			
			data.setSubTotal(tempdata.getSubTotal());
			data.setUnitPrice(tempdata.getUnitPrice());	
			data.setPurchasePrice(tempdata.getPurchasePrice());
			data.setReturnquantity(tempdata.getReturnquantity());
			data.setSalesCommission(tempdata.getSalesCommission());
			data.setTaxAmount(tempdata.getTaxAmount());
			data.setTaxCode(tempdata.getTaxCode());
			data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
			
		
			data.setId(id + "");				
			salesorderbreakdownList.add(data);	
			id = id + 1;
		}				
		salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
				
		salesorderModelObjList.add(salesorderObj);
	}
	}
	catch(Exception e)
	{
		log.info("Error in getSalesorderholdList of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorderModelObjList;
	}
	
	
	
	
	@Override
	public SalesorderModel getSalesorderDetails(Integer salesOrderId , String salesType)
			throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();	
		CustomerModel customer = new CustomerModel();
		BranchModel branch = new BranchModel();
		BigDecimal totalsales = new BigDecimal(0.00);
		BigDecimal totalamount = new BigDecimal(0.00);
		BigDecimal totalgst = new BigDecimal(0.00);
		
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();
	try {
		    Salesorder salesorder = salesOrderRepository.getSalesorderDetails(salesOrderId,salesType);
		    salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getChangeAmount());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			if(!salesorderObj.getPaymentType().equalsIgnoreCase("cash")) {
				
				List<PaymentCollectionModel> posPaymentObjList=new ArrayList<PaymentCollectionModel>();
				for(Pospayment paymentData:salesOrderRepository.getPospaymentList(null,salesOrderId )) {
					PaymentCollectionModel paymentObj=new PaymentCollectionModel();
					
					paymentObj.setAmountType(paymentObj.getAmountType());
					paymentObj.setAmount(paymentData.getAmount());
					
					paymentObj.setCardType(paymentData.getCardType());
					
					paymentObj.setCardNo(paymentData.getCardNo());
					paymentObj.setExpdate(paymentData.getExpDate());
					
					paymentObj.setReferenceNo(paymentData.getReferenceNo());
					
					posPaymentObjList.add(paymentObj);
				}
				salesorderObj.setPospayments(posPaymentObjList);
				
			}
			int id = 0;	
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());				
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
				branch.setAddress(salesorder.getBranch2().getAddress());
				branch.setCity(salesorder.getBranch2().getCity());
				branch.setState(salesorder.getBranch2().getState());
				branch.setPhoneNo(salesorder.getBranch2().getPhoneNo());
				branch.setPostCode(salesorder.getBranch2().getPostCode());
				branch.setBranchId(salesorder.getBranch2().getBranchId());
				branch.setBranchName(salesorder.getBranch2().getBranchName());
				

				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				customer.setCustomerName(salesorder.getCustomer().getCustomerName());
				customer.setAddress(salesorder.getCustomer().getAddress());
				customer.setCity(salesorder.getCustomer().getCity());
				customer.setCountry(salesorder.getCustomer().getCountry());
				customer.setState(salesorder.getCustomer().getState());
				customer.setPhoneNo(salesorder.getCustomer().getPhoneNo());
				customer.setPostCode(salesorder.getCustomer().getPostCode());
				customer.setCustomerId(salesorder.getCustomer().getCustomerId());
				customer.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
				salesorderObj.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
			}	
			
			salesorderObj.setCustomer(customer);
			salesorderObj.setBranch(branch);	
			
			
			salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Salesorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
				data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());	
				data.setQty(tempdata.getQuantity().intValue());
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setUnitPricetax(tempdata.getUnitPrice().add((tempdata.getTaxAmount().divide(tempdata.getQuantity()))));
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				data.setPacking(tempdata.getPacking());
				data.setStatus(tempdata.getStatus());
				data.setSetOption(tempdata.getSetOption());

				// set item child list
				List<SalesorderbreakdownModel> setItemListObj=new ArrayList<SalesorderbreakdownModel>(); 
				for(Salesorderbreakdown setItemData:tempdata.getChildSetItem()) {
					SalesorderbreakdownModel setItem=new SalesorderbreakdownModel();
					
					ProductModel setItemProduct=new ProductModel();
					setItemProduct.setProductId(setItemData.getProduct().getProductId());
					setItemProduct.setCategoryId(setItemData.getProduct().getProductcategory().getCategoryId());
					setItemProduct.setProductName(setItemData.getProduct().getProductName());
					setItem.setProduct(setItemProduct);
					
					setItem.setSalesOrderBreakdownId(setItemData.getSalesOrderBreakdownId());
					setItem.setDiscount(setItemData.getDiscount());
					setItem.setDiscountAmount(setItemData.getDiscountAmount());							
					setItem.setProductId(setItemData.getProduct().getProductId());						
					setItem.setProductName(setItemData.getProduct().getProductName());
					setItem.setProductCode(setItemData.getProduct().getBarcode());
					setItem.setUom(setItemData.getProduct().getUom());
					setItem.setQuantity(setItemData.getQuantity());	
					setItem.setQuantityBefore(setItemData.getQuantity());
					setItem.setSubTotal(setItemData.getSubTotal());
					setItem.setUnitPrice(setItemData.getUnitPrice());
					//setItem.setSno(count++);
					setItem.setPurchasePrice(setItemData.getPurchasePrice());
					setItem.setReturnquantity(setItemData.getReturnquantity());
					setItem.setSalesCommission(setItemData.getSalesCommission());
					setItem.setTaxAmount(setItemData.getTaxAmount());
					setItem.setTaxCode(setItemData.getTaxCode());
					setItem.setSalesOrderBreakdownId(setItemData.getSalesOrderBreakdownId());
					setItem.setStatus(setItemData.getStatus());
					setItem.setPacking(setItemData.getPacking());
					setItem.setSetOption(setItemData.getSetOption());
					
					setItemListObj.add(setItem);
					
				}
				data.setSalesProductSetList(setItemListObj);

				
				totalgst=totalgst.add(tempdata.getTaxAmount());

				if(salesorder.getSalesType().equalsIgnoreCase("1"))
				{
				data.setBalanceEditQuantity(tempdata.getDeliveryorderbreakdown().getBalanceQuantity().add(tempdata.getQuantity()));					
				data.setDeliveryOrderBreakdownId(tempdata.getDeliveryorderbreakdown().getDeliveryOrderBreakdownId());
				data.setDeliveryQuoationBalanceQuantity(tempdata.getDeliveryorderbreakdown().getBalanceQuantity());
				data.setDeliveryQuoationSoldQuantity(tempdata.getDeliveryorderbreakdown().getSoldQuantity());			
				}
				if(salesorder.getSalesType().equalsIgnoreCase("2"))
				{
				data.setBalanceEditQuantity(tempdata.getQuotationbreakdown().getBalanceQuantity().add(tempdata.getQuantity()));				
				data.setDeliveryOrderBreakdownId(tempdata.getQuotationbreakdown().getQuotationBreakdownId());
				data.setDeliveryQuoationBalanceQuantity(tempdata.getQuotationbreakdown().getBalanceQuantity());
				data.setDeliveryQuoationSoldQuantity(tempdata.getQuotationbreakdown().getSoldQuantity());
				
				}
				data.setId(id + "");				
				salesorderbreakdownList.add(data);	
				id = id + 1;
				
				salesorderObj.setTotalTax(totalgst);	
				
			
				
				
				LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
				Productbranchlink product=productRepository.getProductDetails(data.getProductId(), loginBean.getBranch().getBranchId());
				if(product.getTaxType().equalsIgnoreCase("Exclusive"))
				{
					salesorderObj.setTotalSales(product.getNormalPrice().multiply(data.getQuantity()));
					
				}
				else
				{
					salesorderObj.setTotalSales(salesorder.getTotalAmount().subtract(totalgst));
				}
				
				
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getSalesorderDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderObj;
	}

	
	
	
	@Override
	public SalesorderModel getSalesorderPosDetails(Integer salesOrderId , String salesType)
			throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();	
		CustomerModel customer = new CustomerModel();
		BranchModel branch = new BranchModel();
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();
	try {
		    Salesorder salesorder = salesOrderRepository.getBranchSalesorderDetails(salesOrderId,salesType);
		    salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			int id = 0;	
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());				
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
				branch.setAddress(salesorder.getBranch2().getAddress());
				branch.setCity(salesorder.getBranch2().getCity());
				branch.setState(salesorder.getBranch2().getState());
				branch.setPhoneNo(salesorder.getBranch2().getPhoneNo());
				branch.setPostCode(salesorder.getBranch2().getPostCode());
				branch.setBranchId(salesorder.getBranch2().getBranchId());
				branch.setBranchName(salesorder.getBranch2().getBranchName());
				

				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				customer.setCustomerName(salesorder.getCustomer().getCustomerName());
				customer.setAddress(salesorder.getCustomer().getAddress());
				customer.setCity(salesorder.getCustomer().getCity());
				customer.setCountry(salesorder.getCustomer().getCountry());
				customer.setState(salesorder.getCustomer().getState());
				customer.setPhoneNo(salesorder.getCustomer().getPhoneNo());
				customer.setPostCode(salesorder.getCustomer().getPostCode());
				customer.setCustomerId(salesorder.getCustomer().getCustomerId());
				customer.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
				salesorderObj.setLoyaltyCardCode(salesorder.getCustomer().getLoyaltyCardCode());
				
				
				salesorderObj.setSalesBranchName(salesorder.getBranch1().getBranchName());
				salesorderObj.setSalesBranchAddress(salesorder.getBranch1().getAddress());
				salesorderObj.setSalesBranchPhone(salesorder.getBranch1().getPhoneNo());
				salesorderObj.setSalesBranchPost(salesorder.getBranch1().getPostCode());
			}	
			
			salesorderObj.setCustomer(customer);
			salesorderObj.setBranch(branch);	
			
			
			salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Salesorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
							
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				
				if(salesorder.getSalesType().equalsIgnoreCase("1"))
				{
				data.setBalanceEditQuantity(tempdata.getDeliveryorderbreakdown().getBalanceQuantity().add(tempdata.getQuantity()));					
				data.setDeliveryOrderBreakdownId(tempdata.getDeliveryorderbreakdown().getDeliveryOrderBreakdownId());
				data.setDeliveryQuoationBalanceQuantity(tempdata.getDeliveryorderbreakdown().getBalanceQuantity());
				data.setDeliveryQuoationSoldQuantity(tempdata.getDeliveryorderbreakdown().getSoldQuantity());			
				}
				if(salesorder.getSalesType().equalsIgnoreCase("2"))
				{
				data.setBalanceEditQuantity(tempdata.getQuotationbreakdown().getBalanceQuantity().add(tempdata.getQuantity()));				
				data.setDeliveryOrderBreakdownId(tempdata.getQuotationbreakdown().getQuotationBreakdownId());
				data.setDeliveryQuoationBalanceQuantity(tempdata.getQuotationbreakdown().getBalanceQuantity());
				data.setDeliveryQuoationSoldQuantity(tempdata.getQuotationbreakdown().getSoldQuantity());
				
				}
				data.setId(id + "");				
				salesorderbreakdownList.add(data);	
				id = id + 1;
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getSalesorderDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderObj;
	}

	
	public SalesorderModel getSalesorderDetailsCategoryBased(Date dateFrom,Date dateTo,Integer categoryId) throws Exception {
		SalesorderModel salesorderObj=new SalesorderModel();
		List<SalesorderbreakdownModel> sobdList=new ArrayList<SalesorderbreakdownModel>();
		
		try {
			List<Object[]> so=salesOrderRepository.getSalesorderDetailsCategoryBased(dateFrom,dateTo,categoryId);
			int id=0;
			for(Object[] soDao:so) {
				
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
				data.setProductId((Integer)soDao[0]);
				data.setProductName(soDao[1].toString());
				data.setUnitPrice((BigDecimal)soDao[2]);
				data.setTaxAmount((BigDecimal)soDao[3]);
				data.setQtty((BigDecimal)soDao[4]);
				data.setSubTotal((BigDecimal) soDao[5]);
				data.setCategoryName(soDao[6].toString());
				data.setId(id + "");
				sobdList.add(data);
				id++;
				
			}
			salesorderObj.setSalesorderbreakdowns(sobdList);
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in getSalesorderItemDetailsCategoryBased of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		
		return salesorderObj;
		
	}
	
	@Override
	public SalesorderModel getBranchSalesItemDetails(Integer salesOrderId , String salesType)	throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();	
		CustomerModel customer = new CustomerModel();
		BranchModel branch = new BranchModel();
		List<PaymentCollectionModel> pospayments = new ArrayList<PaymentCollectionModel>();
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();
		
		BigDecimal totalsales = new BigDecimal(0.00);
		BigDecimal totalamount = new BigDecimal(0.00);
		BigDecimal totalgst = new BigDecimal(0.00);
	try {
			Salesitem salesorder = salesOrderRepository.getBranchSalesitemDetails(salesOrderId,salesType);
		    salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getChangeAmount());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			int id = 0;	
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());				
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
				branch.setAddress(salesorder.getBranch2().getAddress());
				branch.setCity(salesorder.getBranch2().getCity());
				branch.setState(salesorder.getBranch2().getState());
				branch.setPhoneNo(salesorder.getBranch2().getPhoneNo());
				branch.setPostCode(salesorder.getBranch2().getPostCode());
				branch.setBranchId(salesorder.getBranch2().getBranchId());
				branch.setBranchName(salesorder.getBranch2().getBranchName());
				

				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				customer.setCustomerName(salesorder.getCustomer().getCustomerName());
				customer.setAddress(salesorder.getCustomer().getAddress());
				customer.setCity(salesorder.getCustomer().getCity());
				customer.setCountry(salesorder.getCustomer().getCountry());
				customer.setState(salesorder.getCustomer().getState());
				customer.setPhoneNo(salesorder.getCustomer().getPhoneNo());
				customer.setPostCode(salesorder.getCustomer().getPostCode());
				customer.setCustomerId(salesorder.getCustomer().getCustomerId());
				
						
			}	
			
			//salesorderObj.setPospayments(pospayments);
			salesorderObj.setCustomer(customer);
			salesorderObj.setBranch(branch);	
			
			
			salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Salesitembreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
							
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());	
				data.setQty(tempdata.getQuantity().intValue());
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				
				totalgst=totalgst.add(tempdata.getTaxAmount());
				
				data.setId(id + "");				
				salesorderbreakdownList.add(data);	
				id = id + 1;
				
				
				
			}		
			salesorderObj.setTotalTax(totalgst);				
			salesorderObj.setTotalSales(salesorder.getTotalAmount().subtract(totalgst));	
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
		
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesItemDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderObj;
	}

	
	
	
	
	
	
	
	
	
	@Override
	public SalesorderModel getBranchSalesorderDetails(Integer salesOrderId , String salesType)	throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();	
		CustomerModel customer = new CustomerModel();
		BranchModel branch = new BranchModel();
		List<PaymentCollectionModel> pospayments = new ArrayList<PaymentCollectionModel>();
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();
		
		BigDecimal totalsales = new BigDecimal(0.00);
		BigDecimal totalamount = new BigDecimal(0.00);
		BigDecimal totalgst = new BigDecimal(0.00);
	try {
		    Salesorder salesorder = salesOrderRepository.getBranchSalesorderDetails(salesOrderId,salesType);
		    salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getChangeAmount());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			int id = 0;	
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());				
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
				branch.setAddress(salesorder.getBranch2().getAddress());
				branch.setCity(salesorder.getBranch2().getCity());
				branch.setState(salesorder.getBranch2().getState());
				branch.setPhoneNo(salesorder.getBranch2().getPhoneNo());
				branch.setPostCode(salesorder.getBranch2().getPostCode());
				branch.setBranchId(salesorder.getBranch2().getBranchId());
				branch.setBranchName(salesorder.getBranch2().getBranchName());
				

				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				customer.setCustomerName(salesorder.getCustomer().getCustomerName());
				customer.setAddress(salesorder.getCustomer().getAddress());
				customer.setCity(salesorder.getCustomer().getCity());
				customer.setCountry(salesorder.getCustomer().getCountry());
				customer.setState(salesorder.getCustomer().getState());
				customer.setPhoneNo(salesorder.getCustomer().getPhoneNo());
				customer.setPostCode(salesorder.getCustomer().getPostCode());
				customer.setCustomerId(salesorder.getCustomer().getCustomerId());
				
				if(salesorder.getSalesType().equalsIgnoreCase("3"))
				{				
				for(Pospayment posdata:salesOrderRepository.getPospaymentList(null, salesOrderId))
				{					
					PaymentCollectionModel posdataTemp=new PaymentCollectionModel();							
					beanUtils.copyProperties(posdataTemp, posdata);								
					pospayments.add(posdataTemp);
				}			
				}				
			}	
			
			salesorderObj.setPospayments(pospayments);
			salesorderObj.setCustomer(customer);
			salesorderObj.setBranch(branch);	
			
			
			salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Salesorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
							
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());	
				data.setQty(tempdata.getQuantity().intValue());
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				data.setPacking(tempdata.getPacking());
				data.setStatus(tempdata.getStatus());
				totalgst=totalgst.add(tempdata.getTaxAmount());
				
				data.setId(id + "");				
				salesorderbreakdownList.add(data);	
				id = id + 1;
				
				
				
			}		
			
			salesorderObj.setTotalTax(totalgst);				
			salesorderObj.setTotalSales(salesorder.getTotalAmount().subtract(totalgst));	
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
		
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesorderDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderObj;
	}

	
	
	@Override
	public SalesorderModel getSalesorderMasterDetails(Integer salesOrderId)
			throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();		
		
	try {
		    Salesorder salesorder = salesOrderRepository.getSalesorderMasterDetails(salesOrderId);						
		    salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("2"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());	
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
			}						
			
		}
		catch(Exception e)
		{
			log.info("Error in getSalesorderMasterDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderObj;
	}
	
	@Transactional
	@Override
	public SalesorderModel createNewSalesorder(SalesorderModel salesorder,List<Integer> salesId)
			throws Exception {
		List<Salesorderbreakdown> salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();
		List<Salesitembreakdown> salesitembreakdownList = new ArrayList<Salesitembreakdown>();
		List<Pospayment> pospayments = new ArrayList<Pospayment>();
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		List<BranchinvoicebreakdownModel> branchinvoicebreakdownList = new ArrayList<BranchinvoicebreakdownModel>();	
		List<Branchiteminvoicebreakdown> branchinvoiceitembreakdownList = new ArrayList<Branchiteminvoicebreakdown>();	

		
		boolean saveSuccess = false;
		Salesorder salesorderObj = new Salesorder();
		Salesitem  salesitemObj = new Salesitem();
		
		int customerTotalPoints=0;
			try {
			Autonum autoNum = commonListRepository.getAutonumDetail(salesorder.getBranchRecordId());				
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
			
				Branch branch = new Branch();
				branch.setBranchId(salesorder.getCustomerBranchId());
				salesorderObj.setBranch2(branch);		
				salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
				
				salesitemObj.setBranch1(branch);		
				salesitemObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
				
				
				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				
				Customer customer = new Customer();		
				customer.setCustomerId(salesorder.getCustomerBranchId());				
				salesorderObj.setCustomer(customer);	
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				
				salesitemObj.setCustomer(customer);	
				salesitemObj.setQuoteNo(salesorder.getQuoteNo());
				
				Branch branch = new Branch();
				branch.setBranchId(1); // Default
				salesorderObj.setBranch2(branch);	
				
				salesitemObj.setBranch1(branch);	
			}	
			
			Branch branchRecord = new Branch();
			branchRecord.setBranchId(salesorder.getBranchRecordId());
			salesorderObj.setBranch1(branchRecord);
			salesorderObj.setBranchtype(salesorder.getBranchtype());
			
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getChangeAmount());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());

			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			
			salesorderObj.setSalesOrderNo(autoNum.getSalesOrderFormat().concat(""+salesorder.getBranchRecordId()).concat(String.valueOf(StringUtils.leftPad(autoNum.getSalesOrder()+"", 8, "0"))));
			
			// Duplicate Bill
			
			salesitemObj.setBranch2(branchRecord);
			salesitemObj.setBranchtype(salesorder.getBranchtype());
			
			
			salesitemObj.setCreatedBy(salesorder.getCreatedBy());
			salesitemObj.setCreatedDate(salesorder.getCreatedDate());
			salesitemObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesitemObj.setDueDate(salesorder.getDueDate());
			salesitemObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesitemObj.setOrderPayment(salesorder.getOrderPayment());
			salesitemObj.setOrderReturned(salesorder.getOrderReturned());
			salesitemObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesitemObj.setPaymentType(salesorder.getPaymentType());
			salesitemObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesitemObj.setRemarks(salesorder.getRemarks());
			salesitemObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesitemObj.setSalesDate(salesorder.getSalesDate());
			salesitemObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesitemObj.setChangeAmount(salesorder.getChangeAmount());
			salesitemObj.setCardNo(salesorder.getCardNo());
			salesitemObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setCustomerName(salesorder.getCustomerName());
			salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			salesitemObj.setSalesRep(salesorder.getSalesRep());
			salesitemObj.setSalesType(salesorder.getSalesType());
			salesitemObj.setShippedDate(salesorder.getShippedDate());
			salesitemObj.setStatus(salesorder.getStatus());
			salesitemObj.setTaxingScheme(salesorder.getTaxingScheme());
			
			salesitemObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			
			salesitemObj.setSalesOrderNo(autoNum.getSalesOrderFormat().concat(""+salesorder.getBranchRecordId()).concat(String.valueOf(StringUtils.leftPad(autoNum.getSalesOrder()+"", 8, "0"))));	
			salesorder.setSalesOrderNo(salesitemObj.getSalesOrderNo());
			
			

			salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();			
			for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
			{
				Salesorderbreakdown data=new Salesorderbreakdown();
				Salesitembreakdown data1=new Salesitembreakdown();
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setStatus(tempdata.getStatus());
				
				
				data1.setProduct(productTemp);	
				data1.setCreatedDate(DateUtil.getTodayDate());
				data1.setDiscount(tempdata.getDiscount());
				data1.setDiscountAmount(tempdata.getDiscountAmount());				
				data1.setQuantity(tempdata.getQuantity());	
				
				BigDecimal subtotal=extractSubtotal(tempdata.getUnitduplicatePrice(),tempdata.getDiscountAmount(),tempdata.getQuantity(),tempdata.getTaxCode());				
				data1.setSubTotal(subtotal);
				data1.setUnitPrice(tempdata.getUnitduplicatePrice());	
				data1.setExpDateDate(tempdata.getExpDate());
				data1.setBatchNo(tempdata.getBatchNo());
				data1.setReturnquantity(new BigDecimal(0.00));
				data1.setTaxCode(tempdata.getTaxCode());
				
				BigDecimal priceAmt = tempdata.getUnitduplicatePrice().multiply(tempdata.getQuantity());
				BigDecimal taxAmt= tempdata.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				data1.setTaxAmount(taxAmt);
				
				data.setPacking(tempdata.getPacking());
				//data1.setTaxAmount(tempdata.getTaxAmount());
				
				Productbranchlink product=productRepository.getProductbranchlinkMasterDetails(tempdata.getProductId(),salesorder.getBranchId());

				/*data.setPurchasePrice(product.getPurchasePrice().multiply(tempdata.getQuantity()));	// we will modify
				data.setSalesCommission(product.getSalesrepPrice().multiply(tempdata.getQuantity()));
				data.setQuantityBefore(product.getQuantityonHand());
				data.setQuantityAfter(product.getQuantityonHand().subtract(tempdata.getQuantity()));			
				product.setQuantityonHand(product.getQuantityonHand().subtract(tempdata.getQuantity()));
				productRepository.updateProductbranchlink(product); // Update Product Stock		new 20-02-2014*/
				
				data.setExpDateDate(product.getExpiryDate());
				data.setBatchNo(product.getBatchNo());
				data.setSalesorder(salesorderObj);
				
				data.setSetOption(tempdata.getSetOption());
				
				data1.setExpDateDate(product.getExpiryDate());
				data1.setBatchNo(product.getBatchNo());
				data1.setSalesorder(salesitemObj);
				
				salesitembreakdownList.add(data1);
				salesorderbreakdownList.add(data);	
				customerTotalPoints=customerTotalPoints+product.getCustomerPoint();
				
				
				
				//Duplicate  Bill Generate
				
				// set item child list
				
				for(SalesorderbreakdownModel setItem:tempdata.getSalesProductSetList()) {
					Salesorderbreakdown setItemdata=new Salesorderbreakdown();
					Salesitembreakdown setItemdata1=new Salesitembreakdown();
					
					Product productTemp1 = new Product();			
					productTemp1.setProductId(setItem.getProductId());
					productTemp1.setProductName(setItem.getProductName());
					setItemdata.setProduct(productTemp1);	
					
					setItemdata.setCreatedDate(DateUtil.getTodayDate());
					setItemdata.setDiscount(setItem.getDiscount());
					setItemdata.setDiscountAmount(setItem.getDiscountAmount());				
					setItemdata.setQuantity(setItem.getQuantity());			
					setItemdata.setSubTotal(setItem.getSubTotal());
					setItemdata.setUnitPrice(setItem.getUnitPrice());	
					setItemdata.setExpDateDate(setItem.getExpDate());
					setItemdata.setBatchNo(setItem.getBatchNo());
					setItemdata.setReturnquantity(new BigDecimal(0.00));
					setItemdata.setTaxCode(setItem.getTaxCode());
					setItemdata.setTaxAmount(setItem.getTaxAmount());
					setItemdata.setStatus(setItem.getStatus());
					
					
					setItemdata1.setProduct(productTemp);	
					setItemdata1.setCreatedDate(DateUtil.getTodayDate());
					setItemdata1.setDiscount(setItem.getDiscount());
					setItemdata1.setDiscountAmount(BigDecimal.ZERO);				
					setItemdata1.setQuantity(setItem.getQuantity());	
					
					
					BigDecimal subtotal1=BigDecimal.ZERO;//extractSubtotal(setItem.getUnitduplicatePrice(),setItem.getDiscountAmount(),setItem.getQuantity(),setItem.getTaxCode());				
					setItemdata1.setSubTotal(subtotal1);
					setItemdata1.setUnitPrice(setItem.getUnitduplicatePrice());	
					setItemdata1.setExpDateDate(setItem.getExpDate());
					setItemdata1.setBatchNo(setItem.getBatchNo());
					setItemdata1.setReturnquantity(new BigDecimal(0.00));
					setItemdata1.setTaxCode(setItem.getTaxCode());
					
					BigDecimal priceAmt1 = BigDecimal.ZERO;//setItem.getUnitduplicatePrice().multiply(setItem.getQuantity());
					BigDecimal taxAmt1= BigDecimal.ZERO;//setItem.getTaxCode().multiply((priceAmt1.divide(new BigDecimal(100))));
					setItemdata1.setTaxAmount(taxAmt1);
					
					setItemdata.setPacking(setItem.getPacking());
					
					
					Productbranchlink product1=productRepository.getProductbranchlinkMasterDetails(setItem.getProductId(),salesorder.getBranchId());
	
		
					setItemdata.setExpDateDate(product1.getExpiryDate());
					setItemdata.setBatchNo(product1.getBatchNo());
					setItemdata.setParentSetItem(data);
					setItemdata.setSalesorder(salesorderObj);
					
					setItemdata.setSetOption(setItem.getSetOption());
					
					setItemdata1.setExpDateDate(product1.getExpiryDate());
					setItemdata1.setBatchNo(product1.getBatchNo());
					setItemdata1.setParentSetItem(data1);
					setItemdata1.setSalesorder(salesitemObj);
					
					
					
					salesitembreakdownList.add(setItemdata1);
					salesorderbreakdownList.add(setItemdata);	
					//customerTotalPoints=customerTotalPoints+product.getCustomerPoint();
				}				
				// end of set item child list
				
			}				
			
			
			
			salesorder=extractQuoteTotal(salesitembreakdownList , salesorder);
			Integer discountRate=salesorderObj.getDiscountRate();
			
			BigDecimal discountAmount=salesorder.getTotalAmount().multiply(new BigDecimal("00."+discountRate));
			
			
			salesitemObj.setTotalAmount( DecimalUtil.formatRoundupCents(salesorder.getTotalAmount().subtract(discountAmount)) );
			//salesitemObj.setTotalAmount(salesorder.getTotalAmount());		
			
			BigDecimal discountedTaxAmount=salesorder.getTotalTax().multiply(new BigDecimal("00."+discountRate));
			
			salesitemObj.setTotalTax( DecimalUtil.formatRoundupCents(salesorder.getTotalTax().subtract(discountedTaxAmount)) );
//			salesitemObj.setTotalTax(salesorder.getTotalTax());
			

			salesitemObj.setSalesorderbreakdowns(salesitembreakdownList);
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);	
			
			
			for(PaymentCollectionModel posdata:salesorder.getPospayments())
			{
				Pospayment posdataTemp=new Pospayment();							
				beanUtils.copyProperties(posdataTemp, posdata);	
				posdataTemp.setSalesorder(salesorderObj);
				posdataTemp.setBranch(branchRecord);	
				pospayments.add(posdataTemp);
			}			
			salesorderObj.setPospayments(pospayments);				
			autoNum.setSalesOrder(autoNum.getSalesOrder()+1);
			autoNum=commonListRepository.updateAutonumDetail(autoNum);		
			saveSuccess=salesOrderRepository.createNewSalesorder(salesorderObj);
			
			saveSuccess=salesOrderRepository.createNewSalesduplicateorder(salesitemObj); // Duplicate Order
			
			salesorder.setSalesOrderId(salesorderObj.getSalesOrderId());
			
			Customer customerTemp = customerRepository.getCustomerDetails(salesorder.getCustomerBranchId());	
			customerTemp.setAvailablePoints(customerTemp.getAvailablePoints()+salesorderObj.getTotalAmount().intValue());
			customerRepository.updateCustomer(customerTemp);
			
			if(salesorder.getSalesType().equalsIgnoreCase("3")) // POS Transaction Terminal Updates	
				{				
				
				BranchinvoiceModel branchinvoiceObj = new BranchinvoiceModel(); // INVOICE GENERATION WITH STATUS CLOSED OR (OPEN IF PARTIAL PAYMENT) 				
				Branchiteminvoice branchiteminvoiceObj = new Branchiteminvoice(); // Duplicate invoice

				
				PoscashtransactionModel poscashtransaction =salesorder.getPoscashtransaction();
				poscashtransaction.setSalesOrderNo(salesorderObj.getSalesOrderNo());					
				poscashtransaction.setBranchRecordId(salesorder.getBranchRecordId());				
				poscashtransaction.setBranchtype(salesorder.getBranchtype());
				poscashtransaction.setReceivedAmount(salesorderObj.getTotalAmount()); // if Fully Paid
				poscashtransaction.setCreditamount(salesorderObj.getTotalAmount());	
				poscashtransaction.setTotalTax(salesorderObj.getTotalTax());	
				
				branchinvoiceObj.setApprovedBy(salesorder.getCreatedBy());
				branchinvoiceObj.setApprovedDate(salesorder.getCreatedDate());
				branchinvoiceObj.setCreatedBy(salesorder.getCreatedBy());
				branchinvoiceObj.setCreatedDate(salesorder.getCreatedDate());	
				branchinvoiceObj.setInvoiceDate(salesorder.getCreatedDate());	
				
				
				branchiteminvoiceObj.setApprovedBy(salesitemObj.getCreatedBy());
				branchiteminvoiceObj.setApprovedDate(salesitemObj.getCreatedDate());
				branchiteminvoiceObj.setCreatedBy(salesitemObj.getCreatedBy());
				branchiteminvoiceObj.setCreatedDate(salesitemObj.getCreatedDate());	
				branchiteminvoiceObj.setInvoiceDate(salesitemObj.getCreatedDate());
				branchiteminvoiceObj.setDispatchAmount(new BigDecimal(0.00));
				branchiteminvoiceObj.setInvoiceAmount(salesitemObj.getTotalAmount().subtract(salesitemObj.getTotalTax()));					
				branchiteminvoiceObj.setPaidAmount(salesitemObj.getTotalAmount());
				branchiteminvoiceObj.setPendingAmount(new BigDecimal(0.00));							
				branchiteminvoiceObj.setStatus("3");
				branchiteminvoiceObj.setTax(salesitemObj.getTotalTax());
				branchiteminvoiceObj.setTotalAmount(salesitemObj.getTotalAmount());		
				
				if(salesorder.getReceivedAmount().doubleValue()<salesorder.getTotalAmount().doubleValue()) // Partial Payments
				{
				branchinvoiceObj.setDispatchAmount(new BigDecimal(0.00));
				branchinvoiceObj.setInvoiceAmount(salesorder.getTotalAmount().subtract(salesorder.getTotalTax()));					
				branchinvoiceObj.setPaidAmount(salesorder.getReceivedAmount());
				branchinvoiceObj.setPendingAmount(salesorder.getTotalAmount().subtract(salesorder.getReceivedAmount()));
				
				branchinvoiceObj.setStatus("2");
				branchinvoiceObj.setTax(salesorder.getTotalTax());
				branchinvoiceObj.setTotalAmount(salesorder.getTotalAmount());	
				poscashtransaction.setReceivedAmount(salesorderObj.getReceivedAmount()); // if Partial Paid
				poscashtransaction.setCreditamount(salesorderObj.getTotalAmount());
				poscashtransaction.setTotalTax(salesorderObj.getTotalTax());
				}
				else
				{
				branchinvoiceObj.setDispatchAmount(new BigDecimal(0.00));
				branchinvoiceObj.setInvoiceAmount(salesorder.getTotalAmount().subtract(salesorder.getTotalTax()));					
				branchinvoiceObj.setPaidAmount(salesorder.getTotalAmount());
				branchinvoiceObj.setPendingAmount(new BigDecimal(0.00));							
				branchinvoiceObj.setStatus("3");
				branchinvoiceObj.setTax(salesorder.getTotalTax());
				branchinvoiceObj.setTotalAmount(salesorder.getTotalAmount());			
				}
				
				branchinvoiceObj.setSalesOrderNo(salesorderObj.getSalesOrderNo());	
				branchinvoiceObj.setInvoiceType("2");
				branchinvoiceObj.setRemarks(salesorder.getRemarks());
				branchinvoiceObj.setSalesType(salesorder.getSalesType());
				branchinvoiceObj.setBranchRecordId(salesorder.getBranchRecordId());				
				branchinvoiceObj.getBranch().setBranchId(salesorder.getBranchRecordId());
				
				CustomerModel customer = new CustomerModel();		
				customer.setCustomerId(salesorder.getCustomerBranchId());	
				
				Customer customeren = new Customer();		
				customeren.setCustomerId(salesorder.getCustomerBranchId());	
				
				branchinvoiceObj.setCustomer(customer);	
				branchiteminvoiceObj.setCustomer(customeren);
				
				
				for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
				{
					BranchinvoicebreakdownModel data=new BranchinvoicebreakdownModel();
					data.setProductId(tempdata.getProductId());
					data.setDiscount(tempdata.getDiscount());
					data.setDiscountAmount(tempdata.getDiscountAmount());
					data.setQuantity(tempdata.getQuantity());
					data.setSubTotal(tempdata.getSubTotal());
					data.setUnitPrice(tempdata.getUnitPrice());
					data.setPurchasePrice(tempdata.getPurchasePrice());
					data.setSalesOrderNo(salesorder.getSalesOrderNo());
					data.setTaxAmount(tempdata.getTaxAmount());
					data.setTaxCode(tempdata.getTaxCode());
					
					branchinvoicebreakdownList.add(data);
				}
				branchinvoiceObj.setBranchinvoicebreakdowns(branchinvoicebreakdownList);
				
				
				for(Salesitembreakdown tempdata:salesitemObj.getSalesorderbreakdowns())
				{
					Branchiteminvoicebreakdown data=new Branchiteminvoicebreakdown();
					data.setProduct(tempdata.getProduct());
					data.setDiscount(tempdata.getDiscount());
					data.setDiscountAmount(tempdata.getDiscountAmount());
					data.setQuantity(tempdata.getQuantity());
					data.setSubTotal(tempdata.getSubTotal());
					data.setUnitPrice(tempdata.getUnitPrice());
					data.setPurchasePrice(tempdata.getPurchasePrice());
					data.setSalesOrderNo(salesorder.getSalesOrderNo());
					data.setTaxAmount(tempdata.getTaxAmount());
					data.setTaxCode(tempdata.getTaxCode());
					
					data.setBranchinvoice(branchiteminvoiceObj);
					
					branchinvoiceitembreakdownList.add(data);
				}
				branchiteminvoiceObj.setBranchinvoicebreakdowns(branchinvoiceitembreakdownList);
				
				
				
				
				poscounterBO.createNewPoscashtransaction(poscashtransaction);
				
				// Update Doctor Prescription in to sold
				if(salesorder.getPrescriptionNo()!=null)
				{
					//doctorsPrescriptionsBO.approveDoctorPrescription(salesorder.getDpModel());
				}
				
			/*	// Update Kitchen Order
				if(salesorder.getKitchenOrderId()!=0)
				{
					Kitchensorder=salesOrderRepository.getKitchensorderDetails(salesorder.getKitchenOrderId());
					kitchensorder.setStatus("E");
					salesOrderRepository.updateKitchenorder(kitchensorder);
				}
				*/
				
				//New for merge bill
				List<Hoteltable> tableList =null;	
				
				if(!salesId.isEmpty())
				{
					Kitchensorder	kitchensorder=null;
					for(Integer id:salesId) {
						kitchensorder=salesOrderRepository.getKitchensorderDetails(id);
						kitchensorder.setStatus("E");
						
						salesOrderRepository.updateKitchenorder(kitchensorder);
						if(!kitchensorder.getTableName().contains("Take Away")) {
							if(kitchensorder.getSplitFrom()==null || kitchensorder.getSplitFrom().equalsIgnoreCase("0")) {
								tableList=commonListRepository.getHoteltableList(salesorder.getBranchRecordId() , kitchensorder.getTableName());
								Hoteltable tab =tableList.get(0);
								
								tab.setStatus("0");
								commonListRepository.createHoteltable(tab);
							}
						}
					}
					
				}
				
				/*
				List<Hoteltable> tableList =	commonListRepository.getHoteltableList(salesorder.getBranchRecordId() , salesorder.getTableName());
				if(tableList!=null && tableList.size()!=0)
				{
					Hoteltable tab =tableList.get(0);
					tab.setStatus("0");
					commonListRepository.createHoteltable(tab);
				}
				*/
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Error in createNewSalesorder of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorder;
	}
	
	
	
	public BigDecimal extractSubtotal(BigDecimal unitPrice,	BigDecimal discAmount, BigDecimal quant,BigDecimal taxCode) {
		BigDecimal quantityValue =quant;
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);		
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}
	
	
	
		public SalesorderModel extractQuoteTotal(List<Salesitembreakdown>  salesorderbreakdowns , SalesorderModel salesorder) {
			
	
		    BigDecimal totalAmount =new BigDecimal(0.00);
		    BigDecimal totalDiscountAmount =new BigDecimal(0.00);
		    BigDecimal grandTotalAmount =new BigDecimal(0.00);
		    BigDecimal totalTaxAmount =new BigDecimal(0.00);
		  //  SalesorderModel salesorder  = new SalesorderModel();
			for(Salesitembreakdown item : salesorderbreakdowns)
			{
				totalAmount=totalAmount.add(item.getSubTotal());
				totalDiscountAmount=totalDiscountAmount.add(item.getDiscountAmount());
				totalTaxAmount=totalTaxAmount.add(item.getTaxAmount());
			}
			//salesorder.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));	
			salesorder.setTotalTax(totalTaxAmount);
			grandTotalAmount=(totalAmount.add(totalTaxAmount)).subtract(totalDiscountAmount);
			
			// owner changes
			//grandTotalAmount=(totalAmount.add(totalTaxAmount));
			grandTotalAmount=totalAmount;
			
			salesorder.setTotalAmount(DecimalUtil.formatRoundupCents(grandTotalAmount));
		
			return salesorder;
		}
	


	@Transactional
	@Override
	public SalesorderModel createNewKitchensorder(SalesorderModel salesorder)	throws Exception {
		List<Kitchenorderbreakdown> salesorderbreakdownList = new ArrayList<Kitchenorderbreakdown>();
		List<Kitchenorderbreakdown> salesorderbreakdownList2 = new ArrayList<Kitchenorderbreakdown>();

		BeanUtilsBean beanUtils = new BeanUtilsBean();
			
		boolean saveSuccess = false;
		Kitchensorder salesorderObj = new Kitchensorder();
		
		int customerTotalPoints=0;
			try {
			Autonum autoNum = commonListRepository.getAutonumDetail(salesorder.getBranchRecordId());				
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				Branch branch = new Branch();
				branch.setBranchId(salesorder.getCustomerBranchId());
				salesorderObj.setBranch2(branch);		
				salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				Customer customer = new Customer();		
				customer.setCustomerId(salesorder.getCustomerBranchId());				
				salesorderObj.setCustomer(customer);	
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				
				Branch branch = new Branch();
				branch.setBranchId(1); // Default
				salesorderObj.setBranch2(branch);	
			}	
			
			Branch branchRecord = new Branch();
			branchRecord.setBranchId(salesorder.getBranchRecordId());
			salesorderObj.setBranch1(branchRecord);
			salesorderObj.setBranchtype(salesorder.getBranchtype());
			
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getBalanceAmount());
		
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
			salesorderObj.setCustomerName(salesorder.getCustomerName());
			//salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
			//salesorderObj.setDiscountRate(salesorder.getDiscountRate());
			//salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setSalesOrderNo(autoNum.getSalesOrderFormat().concat(""+salesorder.getBranchRecordId()).concat(String.valueOf(StringUtils.leftPad(autoNum.getSalesOrder()+"", 8, "0"))));	

			salesorderbreakdownList = new ArrayList<Kitchenorderbreakdown>();			
			for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
			{
				Kitchenorderbreakdown data=new Kitchenorderbreakdown();
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setStatus("ordered");
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(tempdata.getRemarks().getRemarksListID());
				data.setRemarks(remarksObj);
				
				List<Kitchenorderremarksbreakdown> remarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
				for(KitchenorderremarksbreakdownModel remarksData:tempdata.getKitchenorderremarksbreakdownModel()) {
					Kitchenorderremarksbreakdown obj=new Kitchenorderremarksbreakdown();
					
					Itemremarkslist remark=new Itemremarkslist();
					remark.setId(remarksData.getRemarks().getRemarksListID());
					
					obj.setRemarks(remark);
					obj.setKitchenorder(data);
					remarksObjList.add(obj);
				}
				data.setKitchenorderremarksbreakdown(remarksObjList);
				
				data.setPacking(tempdata.getPacking());
				
				data.setSetOption(tempdata.getSetOption());
				
				data.setSalesorder(salesorderObj);
				salesorderbreakdownList.add(data);
				
				salesorderbreakdownList2 = new ArrayList<Kitchenorderbreakdown>();
				
				for(SalesorderbreakdownModel setItemData:tempdata.getSalesProductSetList()) {
					Kitchenorderbreakdown setItem=new Kitchenorderbreakdown();
					
					Product setItemProduct = new Product();			
					setItemProduct.setProductId(setItemData.getProductId());
					setItemProduct.setProductName(setItemData.getProductName());
					
					setItem.setProduct(setItemProduct);	
					setItem.setCreatedDate(DateUtil.getTodayDate());
					setItem.setDiscount(setItemData.getDiscount());
					setItem.setDiscountAmount(setItemData.getDiscountAmount());				
					setItem.setQuantity(setItemData.getQuantity());			
					setItem.setSubTotal(setItemData.getSubTotal());
					setItem.setUnitPrice(setItemData.getUnitPrice());	
					setItem.setExpDateDate(setItemData.getExpDate());
					setItem.setBatchNo(setItemData.getBatchNo());
					setItem.setReturnquantity(new BigDecimal(0.00));
					setItem.setTaxCode(setItemData.getTaxCode());
					setItem.setTaxAmount(setItemData.getTaxAmount());
					setItem.setStatus("ordered");
					
					List<Kitchenorderremarksbreakdown> setItemremarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
					for(KitchenorderremarksbreakdownModel setItemRemarksData:setItemData.getKitchenorderremarksbreakdownModel()) {
						Kitchenorderremarksbreakdown setItemRemarksObj=new Kitchenorderremarksbreakdown();
						Itemremarkslist setItemRemarks=new Itemremarkslist();
						setItemRemarks.setId(setItemRemarksData.getRemarks().getRemarksListID());
						
						setItemRemarksObj.setRemarks(setItemRemarks);
						setItemRemarksObj.setKitchenorder(setItem);
						setItemremarksObjList.add(setItemRemarksObj);
					}
					setItem.setKitchenorderremarksbreakdown(setItemremarksObjList);
					setItem.setPacking(setItemData.getPacking());
					setItem.setSetOption(setItemData.getSetOption());
					
					setItem.setParentSetItem(data);
					setItem.setSalesorder(data.getSalesorder());
					
					//salesorderbreakdownList2.add(setItem);
					salesorderbreakdownList.add(setItem);
				}
				//data.setChildSetItem(salesorderbreakdownList2);
				
			}
			
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
			autoNum.setSalesOrder(autoNum.getSalesOrder()+1);		
			autoNum.setTakeAwayNo(autoNum.getTakeAwayNo()+1);
			autoNum=commonListRepository.updateAutonumDetail(autoNum);				
			
			saveSuccess=salesOrderRepository.createNewKitchensorder(salesorderObj);
			salesorder.setSalesOrderId(salesorderObj.getSalesOrderId());
			
			
			
			List<Hoteltable> tableList =	commonListRepository.getHoteltableList(salesorder.getBranchRecordId() , salesorder.getTableName());
			if(tableList!=null && tableList.size()!=0)
			{
				Hoteltable tab =tableList.get(0);
				tab.setStatus("1");
				commonListRepository.createHoteltable(tab);
			}
		}
		catch(Exception e)
		{
			log.info("Error in createNewKitchensorder of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorder;
	}
	
	
	@Override
	public List<SalesorderbreakdownModel> getOrderRerportListCategoryBased(Date dateFrom, Date dateTo)	throws Exception {
		 List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();	


		
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		
		BigDecimal totalsales = new BigDecimal(0.00);
		BigDecimal totalamount = new BigDecimal(0.00);
		BigDecimal totalgst = new BigDecimal(0.00);
	try {
			List<Object[]> salesorderbreakdown = salesOrderRepository.getSalesorderReportListCategoryBased(dateFrom,dateTo);
			
			
			//for(Salesorderbreakdown sobd: salesorderbreakdown)
			 int id = 0;
			 for(Object[] sobd: salesorderbreakdown)
			 {	
				 SalesorderbreakdownModel data=new SalesorderbreakdownModel();
				 
				 data.setCategoryName(sobd[0].toString());
				 data.setUnitPrice((BigDecimal)sobd[1]);
				 data.setTaxAmount((BigDecimal)sobd[2]);
				 
				 data.setQtty((BigDecimal)sobd[3]);
				 data.getProduct().setCategoryId((int) sobd[4]);
				 
				 data.setId(id + "");				
					
				 salesorderbreakdownList.add(data);	
				 id = id + 1;
				
				
			 }
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getSalesorderDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderbreakdownList;
	}
	
	
	public List<SalesorderModel> getDailySalesListChart(Date dateFrom,Date dateTo) throws Exception{
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		try {
			List<Object[]> soList=salesOrderRepository.getDailySalesListChart(dateFrom,dateTo);
			for(Object[] data:soList) {
			
				SalesorderModel soObj=new SalesorderModel();
				soObj.setTotalAmount((BigDecimal) data[0]);
				soObj.setCreatedDate((Date) data[1]);
				salesorderModelObjList.add(soObj);
			}
			 
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getDailySalesListChart of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}
	
	public List<SalesorderModel> getDailyStaffSalesChart(Date dateFrom,Date dateTo,Integer staffId) throws Exception{
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		try {
			List<Object[]> soList=salesOrderRepository.getDailyStaffSalesChart(dateFrom,dateTo,staffId);
			for(Object[] data:soList) {
			
				SalesorderModel soObj=new SalesorderModel();
				soObj.setCreatedDate((Date) data[1]);
				soObj.setTotalAmount((BigDecimal) data[2]);
				salesorderModelObjList.add(soObj);
			}
			
			 
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getDailyStaffSalesChart of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}
	
	public List<SalesorderbreakdownModel> getDailyTopSalesItemChart(Date dateFrom,Date dateTo, int rank) throws Exception{
		List<SalesorderbreakdownModel> salesorderModelObjList = new ArrayList<SalesorderbreakdownModel>();
		try {
			List<Object[]> soList=salesOrderRepository.getDailyTopSalesItemChart(dateFrom,dateTo,rank);
			soList=salesOrderRepository.getDailyTopSalesItemChart(dateFrom,dateTo,rank);
			String output="";
			for(Object[] data:soList) {
			
				
				SalesorderbreakdownModel soObj=new SalesorderbreakdownModel();
				soObj.setProductName((String) data[0]);
				soObj.setCreatedDate((Date) data[1]);
				soObj.setSubTotal((BigDecimal) data[2]);
				
				output+= soObj.getCreatedDate() + " : " + soObj.getProductName() +" : " + soObj.getSubTotal();
				salesorderModelObjList.add(soObj);
			}
			 
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getDailySalesListChart of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}
	
	public List<SalesorderModel> getQuartelySalesListChart(int year,int branchId) throws Exception{
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		try {
			List<Object[]> soList=salesOrderRepository.getQuartelySalesListChart(year,branchId);
			for(Object[] data:soList) {
				SalesorderModel soObj=new SalesorderModel();
				soObj.setTotalAmount((BigDecimal) data[0]);
				soObj.setQuarterOfTheYear((int) data[1]);
				salesorderModelObjList.add(soObj);
			}
			 
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getQuartelySalesListChart of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}
	
	
	public List<SalesorderModel> getMonthlySalesListChart(int year,int branchId) throws Exception{
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		try {
			List<Object[]> soList=salesOrderRepository.getMonthlySalesListChart(year,branchId);
			for(Object[] data:soList) {
			
				SalesorderModel soObj=new SalesorderModel();
				soObj.setTotalAmount((BigDecimal) data[0]);
				soObj.setSalesMonth((int) data[1]);
				salesorderModelObjList.add(soObj);
				
			}
			 
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Error in getMonthlySalesListChart of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}
	
	
	@Override
	public List<SalesorderModel> getKitchenorderReportList(String tableNo,String cardNo,String salesOrderNo,String createdby,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status,Integer branchRecordId) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
	try {
	List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(tableNo,cardNo,salesOrderNo, createdby,customerId, branchId, dateFrom, dateTo, status, branchRecordId);

	int id=0;
	
	for (Kitchensorder salesorder : salesorderList) {
		
		SalesorderModel salesorderObj = new SalesorderModel();
		
		salesorderObj.setCreatedBy(salesorder.getCreatedBy());
		salesorderObj.setCreatedDate(salesorder.getCreatedDate());
		salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
		salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
		salesorderObj.setDueDate(salesorder.getDueDate());
		salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
		salesorderObj.setOrderPayment(salesorder.getOrderPayment());
		salesorderObj.setOrderReturned(salesorder.getOrderReturned());
		salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
		salesorderObj.setPaymentType(salesorder.getPaymentType());
		salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
		
		salesorderObj.setRemarks(salesorder.getRemarks());
		salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
		salesorderObj.setSalesDate(salesorder.getSalesDate());
		salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
		salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesorderObj.setSalesRep(salesorder.getSalesRep());
		salesorderObj.setSalesType(salesorder.getSalesType());
		salesorderObj.setShippedDate(salesorder.getShippedDate());
		salesorderObj.setStatus(salesorder.getStatus());
		salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
		salesorderObj.setTotalAmount(salesorder.getTotalAmount());
		salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
		salesorderObj.setTotalTax(salesorder.getTotalTax());
		salesorderObj.setCardNo(salesorder.getCardNo());
		salesorderObj.setSplitInc(salesorder.getSplitInc());
		salesorderObj.setSplitFrom(salesorder.getSplitFrom());
		salesorderObj.setPax(salesorder.getPax());
		salesorderObj.setCustomerName(salesorder.getCustomerName());
	//	salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
	//	salesorderObj.setDiscountRate(salesorder.getDiscountRate());
	//	salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
		salesorderObj.setOldcardNo(salesorder.getCardNo());
		salesorderObj.setTableName(salesorder.getTableName());
		
		if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
		{
				
			salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
			salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
			salesorderObj.setQuoteNo(salesorder.getQuoteNo());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
			salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
			salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
		}	
		
		
		int count=0;
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
		for(Kitchenorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
		{
			SalesorderbreakdownModel data=new SalesorderbreakdownModel();
						
			data.setDiscount(tempdata.getDiscount());
			data.setDiscountAmount(tempdata.getDiscountAmount());							
			data.setProductId(tempdata.getProduct().getProductId());						
			data.setProductName(tempdata.getProduct().getProductName());
			data.setProductCode(tempdata.getProduct().getBarcode());
			data.setUom(tempdata.getProduct().getUom());
			data.setQuantity(tempdata.getQuantity());	
			data.setQuantityBefore(data.getQuantity());
			data.setSubTotal(tempdata.getSubTotal());
			data.setUnitPrice(tempdata.getUnitPrice());
			data.setSno(count++);
			data.setPurchasePrice(tempdata.getPurchasePrice());
			data.setReturnquantity(tempdata.getReturnquantity());
			data.setSalesCommission(tempdata.getSalesCommission());
			data.setTaxAmount(tempdata.getTaxAmount());
			data.setTaxCode(tempdata.getTaxCode());
			data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
			data.setStatus(tempdata.getStatus());
			ItemRemarksListModel remarksObj=new ItemRemarksListModel();
			remarksObj.setRemarksListID(tempdata.getRemarks().getId());
			data.setRemarks(remarksObj);
			data.setPacking(tempdata.getPacking());
			data.setSetOption(tempdata.getSetOption());
			
			List<KitchenorderremarksbreakdownModel> remarksbreakdownlist1=new ArrayList<KitchenorderremarksbreakdownModel>();
			for(Kitchenorderremarksbreakdown remarksBreakdown1:tempdata.getKitchenorderremarksbreakdown()) {
				KitchenorderremarksbreakdownModel obj1=new KitchenorderremarksbreakdownModel();
				
				ItemRemarksListModel remark1=new ItemRemarksListModel();
				remark1.setRemarksListID(remarksBreakdown1.getRemarks().getId());
				
				obj1.setRemarks(remark1);
				remarksbreakdownlist1.add(obj1);
			}
			data.setKitchenorderremarksbreakdownModel(remarksbreakdownlist1);
			
			// set item child list
			List<SalesorderbreakdownModel> setItemListObj=new ArrayList<SalesorderbreakdownModel>(); 
			for(Kitchenorderbreakdown setItemData:tempdata.getChildSetItem()) {
				SalesorderbreakdownModel setItem=new SalesorderbreakdownModel();
				
				setItem.setSalesOrderBreakdownId(setItemData.getSalesOrderBreakdownId());
				setItem.setDiscount(setItemData.getDiscount());
				setItem.setDiscountAmount(setItemData.getDiscountAmount());							
				setItem.setProductId(setItemData.getProduct().getProductId());						
				setItem.setProductName(setItemData.getProduct().getProductName());
				setItem.setProductCode(setItemData.getProduct().getBarcode());
				setItem.setUom(setItemData.getProduct().getUom());
				setItem.setQuantity(setItemData.getQuantity());	
				setItem.setQuantityBefore(setItemData.getQuantity());
				setItem.setSubTotal(setItemData.getSubTotal());
				setItem.setUnitPrice(setItemData.getUnitPrice());
				//setItem.setSno(count++);
				setItem.setPurchasePrice(setItemData.getPurchasePrice());
				setItem.setReturnquantity(setItemData.getReturnquantity());
				setItem.setSalesCommission(setItemData.getSalesCommission());
				setItem.setTaxAmount(setItemData.getTaxAmount());
				setItem.setTaxCode(setItemData.getTaxCode());
				setItem.setSalesOrderBreakdownId(setItemData.getSalesOrderBreakdownId());
				setItem.setStatus(setItemData.getStatus());
				
				List<KitchenorderremarksbreakdownModel> remarksbreakdownlist2=new ArrayList<KitchenorderremarksbreakdownModel>();
				for(Kitchenorderremarksbreakdown remarksBreakdown2:setItemData.getKitchenorderremarksbreakdown()) {
					KitchenorderremarksbreakdownModel obj2=new KitchenorderremarksbreakdownModel();
					
					ItemRemarksListModel remark2=new ItemRemarksListModel();
					remark2.setRemarksListID(remarksBreakdown2.getRemarks().getId());
					
					obj2.setRemarks(remark2);
					remarksbreakdownlist2.add(obj2);
				}
				setItem.setKitchenorderremarksbreakdownModel(remarksbreakdownlist2);
				
				setItem.setPacking(setItemData.getPacking());
				setItem.setSetOption(setItemData.getSetOption());
				
				ProductModel setItemProduct=new ProductModel();
				setItemProduct.setProductId(setItemData.getProduct().getProductId());
				setItemProduct.setCategoryId(setItemData.getProduct().getProductcategory().getCategoryId());
				setItemProduct.setProductName(setItemData.getProduct().getProductName());
				setItem.setProduct(setItemProduct);
				
				//setItem.setId(id + "");
				
				setItemListObj.add(setItem);
				
			}
			data.setSalesProductSetList(setItemListObj);
			
			ProductModel pro=new ProductModel();
			pro.setProductId(tempdata.getProduct().getProductId());
			pro.setCategoryId(tempdata.getProduct().getProductcategory().getCategoryId());
			data.setProduct(pro);
			data.setId(id + "");
			salesorderbreakdownList.add(data);	
			id = id + 1;
		}				
		salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);	
		
		salesorderModelObjList.add(salesorderObj);
	}
	}
	catch(Exception e)
	{
		log.info("Error in getKitchenorderReportList of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorderModelObjList;
	}
	
	
	@Transactional
	@Override
	public boolean deleteKitchenorder(SalesorderModel salesorder)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@Override
	public boolean deleteKitchenorderbreakdown(
			SalesorderbreakdownModel salesorderbreakdown) throws Exception {
		boolean deleteSuccess=false;
		try {
			Kitchenorderbreakdown obj=new Kitchenorderbreakdown();
			obj.setSalesOrderBreakdownId(salesorderbreakdown.getSalesOrderBreakdownId());
			deleteSuccess=salesOrderRepository.deleteKitchenorderbreakdown(obj);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteKitchenorderbreakdown of SalesorderBOImpl "+ ex.toString());
			throw ex;
		}
		return deleteSuccess;
	}
	
	@Transactional
	@Override
	public SalesorderModel updateSplitOrder(SalesorderModel salesorder)
			throws Exception {
		List<Quotationbreakdown> quotationbreakdownList = new ArrayList<Quotationbreakdown>();		
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		List<Integer> itemIds = new  ArrayList<Integer>();		
		Kitchensorder salesorderObj = new Kitchensorder();
		
		//List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), salesorder.getCreatedBy(),null, null, null, null, null, salesorder.getBranchRecordId());
		List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), null,null, null, null, null, null, salesorder.getBranchRecordId());
		
		if(salesorderList!=null && salesorderList.size()!=0)
		{
			salesorderObj  = salesorderList.get(0);
		}
		
		
		
	try {
		
		 for(Kitchenorderbreakdown oldItem:salesorderObj.getSalesorderbreakdowns()){
			 itemRemoved = true;
		 for (SalesorderbreakdownModel newItem :salesorder.getSalesorderbreakdowns() ) {
				if(oldItem.getSalesOrderBreakdownId()==newItem.getSalesOrderBreakdownId()){
					itemRemoved = false;							
					break;									
				}
		}
		if(itemRemoved){
			
			//salesOrderRepository.deleteKitchenorderbreakdown(oldItem);					
			}			
		 }
		 int i=0;
		 
		 	//salesorderObj.setCreatedBy(salesorder.getCreatedBy()); avoid overwrite creator
			//salesorderObj.setCreatedDate(salesorder.getCreatedDate()); // avoid overwrite creator
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getBalanceAmount());
		
			
			//salesorderObj.setSalesRep(salesorder.getSalesRep()); avoid overwrite creator
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
	//		salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
	//		salesorderObj.setDiscountRate(salesorder.getDiscountRate());
	//		salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());

		
		
			List<Kitchenorderbreakdown> salesorderbreakdownList = new ArrayList<Kitchenorderbreakdown>();
		
		for(Kitchenorderbreakdown newItem : salesorderObj.getSalesorderbreakdowns()){
			itemIds.add(newItem.getSalesOrderBreakdownId());
		}
		
		for (SalesorderbreakdownModel tempdata : salesorder.getSalesorderbreakdowns()) {								 
			
			if(itemIds.contains(tempdata.getSalesOrderBreakdownId()))
			{	
				
				for(Kitchenorderbreakdown newItem1 : salesorderObj.getSalesorderbreakdowns()){
					if(newItem1.getSalesOrderBreakdownId()==tempdata.getSalesOrderBreakdownId())
					{	
						
						Product productTemp = new Product();			
						productTemp.setProductId(tempdata.getProductId());
						productTemp.setProductName(tempdata.getProductName());
						if(newItem1.getStatus()==null) {
							newItem1.setStatus("ordered");
						}
						
						newItem1.setProduct(productTemp);	
						newItem1.setCreatedDate(DateUtil.getTodayDate());
						newItem1.setDiscount(tempdata.getDiscount());
						newItem1.setDiscountAmount(tempdata.getDiscountAmount());				
						newItem1.setQuantity(tempdata.getQuantity());			
						newItem1.setSubTotal(tempdata.getSubTotal());
						newItem1.setUnitPrice(tempdata.getUnitPrice());	
						newItem1.setExpDateDate(tempdata.getExpDate());
						newItem1.setBatchNo(tempdata.getBatchNo());
						newItem1.setReturnquantity(new BigDecimal(0.00));
						newItem1.setTaxCode(tempdata.getTaxCode());
						newItem1.setTaxAmount(tempdata.getTaxAmount());
						
						Itemremarkslist remarksObj=new Itemremarkslist();
						remarksObj.setId(tempdata.getRemarks().getRemarksListID());
						newItem1.setRemarks(remarksObj);
						newItem1.setSalesorder(salesorderObj);
						newItem1.setPacking(tempdata.getPacking());
						newItem1.setSetOption(tempdata.getSetOption());
						salesorderbreakdownList.add(newItem1);	
						
					}
				}					
			}
			else
			{									
				//new order
				Kitchenorderbreakdown data= new Kitchenorderbreakdown();	
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				//productTemp.setStatus("ordered");
				data.setStatus("ordered");
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(tempdata.getRemarks().getRemarksListID());
				data.setRemarks(remarksObj);
				data.setPacking(tempdata.getPacking());
				data.setSetOption(tempdata.getSetOption());
				data.setSalesorder(salesorderObj);
				salesorderbreakdownList.add(data);	
				
			}
		} 			
		
		salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
		salesorderObj=salesOrderRepository.updateKitchenorder(salesorderObj);
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.info("Error in updateKitchenorder of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorder;
	}
	
	@Transactional
	@Override
	public SalesorderModel updateKitchenorder(SalesorderModel salesorder)
			throws Exception {
		List<Quotationbreakdown> quotationbreakdownList = new ArrayList<Quotationbreakdown>();		
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		List<Integer> itemIds = new  ArrayList<Integer>();		
		Kitchensorder salesorderObj = new Kitchensorder();
		
		//List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), salesorder.getCreatedBy(),null, null, null, null, null, salesorder.getBranchRecordId());
		List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), null,null, null, null, null, null, salesorder.getBranchRecordId());
		List<Kitchenorderbreakdown> salesorderbreakdownList2 = new ArrayList<Kitchenorderbreakdown>();

		
		if(salesorderList!=null && salesorderList.size()!=0)
		{
			salesorderObj  = salesorderList.get(0);
		}
		
		
		
	try {
		
		 for(Kitchenorderbreakdown oldItem:salesorderObj.getSalesorderbreakdowns()){
			 itemRemoved = true;
		 for (SalesorderbreakdownModel newItem :salesorder.getSalesorderbreakdowns() ) {
				if(oldItem.getSalesOrderBreakdownId()==newItem.getSalesOrderBreakdownId()){
					itemRemoved = false;							
					break;									
				}
		}
		if(itemRemoved){
			
			
		//	salesOrderRepository.deleteKitchenorderbreakdown(oldItem);					
			}			
		 }		
		 
		 	//salesorderObj.setCreatedBy(salesorder.getCreatedBy()); avoid overwrite creator
			//salesorderObj.setCreatedDate(salesorder.getCreatedDate()); // avoid overwrite creator
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getBalanceAmount());
		
			
			//salesorderObj.setSalesRep(salesorder.getSalesRep()); avoid overwrite creator
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
	//		salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
	//		salesorderObj.setDiscountRate(salesorder.getDiscountRate());
	//		salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
		
		
			List<Kitchenorderbreakdown> salesorderbreakdownList = new ArrayList<Kitchenorderbreakdown>();
		
		for(Kitchenorderbreakdown newItem : salesorderObj.getSalesorderbreakdowns()){
			itemIds.add(newItem.getSalesOrderBreakdownId());
		}			
		for (SalesorderbreakdownModel tempdata : salesorder.getSalesorderbreakdowns()) {								 
			
			if(itemIds.contains(tempdata.getSalesOrderBreakdownId()))
			{	
				
				for(Kitchenorderbreakdown newItem1 : salesorderObj.getSalesorderbreakdowns()){
					if(newItem1.getSalesOrderBreakdownId()==tempdata.getSalesOrderBreakdownId())
					{	
						Product productTemp = new Product();			
						productTemp.setProductId(tempdata.getProductId());
						productTemp.setProductName(tempdata.getProductName());
						if(newItem1.getStatus()==null) {
							newItem1.setStatus("ordered");
						}
						
						newItem1.setProduct(productTemp);	
						newItem1.setCreatedDate(DateUtil.getTodayDate());
						newItem1.setDiscount(tempdata.getDiscount());
						newItem1.setDiscountAmount(tempdata.getDiscountAmount());				
						newItem1.setQuantity(tempdata.getQuantity());			
						newItem1.setSubTotal(tempdata.getSubTotal());
						newItem1.setUnitPrice(tempdata.getUnitPrice());	
						newItem1.setExpDateDate(tempdata.getExpDate());
						newItem1.setBatchNo(tempdata.getBatchNo());
						newItem1.setReturnquantity(new BigDecimal(0.00));
						newItem1.setTaxCode(tempdata.getTaxCode());
						newItem1.setTaxAmount(tempdata.getTaxAmount());
						
						Itemremarkslist remarksObj=new Itemremarkslist();
						remarksObj.setId(tempdata.getRemarks().getRemarksListID());
						
						List<Kitchenorderremarksbreakdown> remarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
						/*
						for(KitchenorderremarksbreakdownModel remarksData:tempdata.getKitchenorderremarksbreakdownModel()) {
							Kitchenorderremarksbreakdown obj=new Kitchenorderremarksbreakdown();
							
							Itemremarkslist remark=new Itemremarkslist();
							remark.setId(remarksData.getRemarks().getRemarksListID());
							
							obj.setRemarks(remark);
							obj.setKitchenorder(newItem1);
							remarksObjList.add(obj);
						}
						newItem1.setKitchenorderremarksbreakdown(remarksObjList);
						*/
						
						newItem1.setRemarks(remarksObj);
						newItem1.setSalesorder(salesorderObj);
						newItem1.setPacking(tempdata.getPacking());
						newItem1.setSetOption(tempdata.getSetOption());
						salesorderbreakdownList.add(newItem1);	
						salesorderbreakdownList2 = new ArrayList<Kitchenorderbreakdown>();
						
						// set item child list
						for(SalesorderbreakdownModel setItemData:tempdata.getSalesProductSetList()) {
							Kitchenorderbreakdown setItem=new Kitchenorderbreakdown();
							
							Product setItemProduct = new Product();			
							setItemProduct.setProductId(setItemData.getProductId());
							setItemProduct.setProductName(setItemData.getProductName());
							
							setItem.setSalesOrderBreakdownId(setItemData.getSalesOrderBreakdownId());
							setItem.setProduct(setItemProduct);	
							setItem.setCreatedDate(DateUtil.getTodayDate());
							setItem.setDiscount(setItemData.getDiscount());
							setItem.setDiscountAmount(setItemData.getDiscountAmount());				
							setItem.setQuantity(setItemData.getQuantity());			
							setItem.setSubTotal(setItemData.getSubTotal());
							setItem.setUnitPrice(setItemData.getUnitPrice());	
							setItem.setExpDateDate(setItemData.getExpDate());
							setItem.setBatchNo(setItemData.getBatchNo());
							setItem.setReturnquantity(new BigDecimal(0.00));
							setItem.setTaxCode(setItemData.getTaxCode());
							setItem.setTaxAmount(setItemData.getTaxAmount());
							setItem.setStatus("ordered");
							/*
							List<Kitchenorderremarksbreakdown> setItemremarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
							for(KitchenorderremarksbreakdownModel setItemRemarksData:setItemData.getKitchenorderremarksbreakdownModel()) {
								Kitchenorderremarksbreakdown setItemRemarksObj=new Kitchenorderremarksbreakdown();
								Itemremarkslist setItemRemarks=new Itemremarkslist();
								setItemRemarks.setId(setItemRemarksData.getRemarks().getRemarksListID());
								
								setItemRemarksObj.setRemarks(setItemRemarks);
								setItemRemarksObj.setKitchenorder(setItem);
								setItemremarksObjList.add(setItemRemarksObj);
							}
							setItem.setKitchenorderremarksbreakdown(setItemremarksObjList);
							*/
							setItem.setPacking(setItemData.getPacking());
							setItem.setSetOption(setItemData.getSetOption());
							
							setItem.setParentSetItem(newItem1);
							setItem.setSalesorder(newItem1.getSalesorder());
							
							//salesorderbreakdownList2.add(setItem);
							salesorderbreakdownList.add(setItem);
						}
						
					}
				}					
			}
			else
			{									
				//new order
				Kitchenorderbreakdown data= new Kitchenorderbreakdown();	
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				//productTemp.setStatus("ordered");
				
				if(tempdata.getStatus()!=null && tempdata.getStatus().equalsIgnoreCase("cancel")) {
					
					data.setStatus("canceled");
				}else {
					
					data.setStatus("ordered");	
				}
				
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(tempdata.getRemarks().getRemarksListID());
				data.setRemarks(remarksObj);
				
				
				List<Kitchenorderremarksbreakdown> remarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
				for(KitchenorderremarksbreakdownModel remarksData:tempdata.getKitchenorderremarksbreakdownModel()) {
					Kitchenorderremarksbreakdown obj=new Kitchenorderremarksbreakdown();
					
					Itemremarkslist remark=new Itemremarkslist();
					remark.setId(remarksData.getRemarks().getRemarksListID());
					
					obj.setRemarks(remark);
					obj.setKitchenorder(data);
					remarksObjList.add(obj);
				}
				data.setKitchenorderremarksbreakdown(remarksObjList);
				
				data.setPacking(tempdata.getPacking());
				data.setSetOption(tempdata.getSetOption());
				data.setSalesorder(salesorderObj);
				salesorderbreakdownList.add(data);
				
				salesorderbreakdownList2 = new ArrayList<Kitchenorderbreakdown>();
				//set item child list
				for(SalesorderbreakdownModel setItemData:tempdata.getSalesProductSetList()) {
					Kitchenorderbreakdown setItem=new Kitchenorderbreakdown();
					
					Product setItemProduct = new Product();			
					setItemProduct.setProductId(setItemData.getProductId());
					setItemProduct.setProductName(setItemData.getProductName());
					
					setItem.setProduct(setItemProduct);	
					setItem.setCreatedDate(DateUtil.getTodayDate());
					setItem.setDiscount(setItemData.getDiscount());
					setItem.setDiscountAmount(setItemData.getDiscountAmount());				
					setItem.setQuantity(setItemData.getQuantity());			
					setItem.setSubTotal(setItemData.getSubTotal());
					setItem.setUnitPrice(setItemData.getUnitPrice());	
					setItem.setExpDateDate(setItemData.getExpDate());
					setItem.setBatchNo(setItemData.getBatchNo());
					setItem.setReturnquantity(new BigDecimal(0.00));
					setItem.setTaxCode(setItemData.getTaxCode());
					setItem.setTaxAmount(setItemData.getTaxAmount());
					
					if(setItemData.getStatus()!=null && setItemData.getStatus().equalsIgnoreCase("cancel")) {
						setItem.setStatus("canceled");
					}else {
						
						setItem.setStatus("ordered");
					}
					
					List<Kitchenorderremarksbreakdown> setItemremarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
					if(setItem.getStatus().equalsIgnoreCase("ordered"))
					for(KitchenorderremarksbreakdownModel setItemRemarksData:setItemData.getKitchenorderremarksbreakdownModel()) {
						Kitchenorderremarksbreakdown setItemRemarksObj=new Kitchenorderremarksbreakdown();
						Itemremarkslist setItemRemarks=new Itemremarkslist();
						setItemRemarks.setId(setItemRemarksData.getRemarks().getRemarksListID());
						
						setItemRemarksObj.setRemarks(setItemRemarks);
						setItemRemarksObj.setKitchenorder(setItem);
						setItemremarksObjList.add(setItemRemarksObj);
					}
					setItem.setKitchenorderremarksbreakdown(setItemremarksObjList);
					setItem.setPacking(setItemData.getPacking());
					setItem.setSetOption(setItemData.getSetOption());
					
					setItem.setParentSetItem(data);
					setItem.setSalesorder(data.getSalesorder());
					
					//salesorderbreakdownList2.add(setItem);
					salesorderbreakdownList.add(setItem);
				}
				
			}
		} 			
		
		salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
		salesorderObj=salesOrderRepository.updateKitchenorder(salesorderObj);
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.info("Error in updateKitchenorder of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorder;
	}
	
	
	
	
	
	@Transactional
	@Override
	public SalesorderModel holdSalesorder(SalesorderModel salesorder)
			throws Exception {
		List<Salesorderbreakdownhold> salesorderbreakdownList = new ArrayList<Salesorderbreakdownhold>();	
		boolean saveSuccess = false;
		Salesorderhold salesorderObj = new Salesorderhold();	

			try {					
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				Branch branch = new Branch();
				branch.setBranchId(salesorder.getCustomerBranchId());
				salesorderObj.setBranch2(branch);		
				salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
				Customer customer = new Customer();		
				customer.setCustomerId(salesorder.getCustomerBranchId());				
				salesorderObj.setCustomer(customer);	
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				
				Branch branch = new Branch();
				branch.setBranchId(1); // Default
				salesorderObj.setBranch2(branch);	
			}	
			
			Branch branchRecord = new Branch();
			branchRecord.setBranchId(salesorder.getBranchRecordId());
			salesorderObj.setBranch1(branchRecord);
			salesorderObj.setBranchtype(salesorder.getBranchtype());	
			
			
			Poscounter poscounter = new Poscounter();
			poscounter.setCounterId(salesorder.getCounterId());
			salesorderObj.setPoscounter(poscounter);
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getBalanceAmount());
		
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());			
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());			
			
			salesorderbreakdownList = new ArrayList<Salesorderbreakdownhold>();			
			for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
			{
				Salesorderbreakdownhold data=new Salesorderbreakdownhold();
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				
				data.setSalesorder(salesorderObj);
				salesorderbreakdownList.add(data);	
				
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);					
			saveSuccess=salesOrderRepository.holdSalesorder(salesorderObj);
			
	
		}
		catch(Exception e)
		{
			log.info("Error in holdSalesorder of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorder;
	}
	
	
	
	
	@Transactional
	@Override
	public boolean createNewSalesorder( DeliveryorderModel deliveryorder , QuotationModel quotationModel,SalesorderModel salesorder)
			throws Exception {
		List<Salesorderbreakdown> salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();		
		boolean saveSuccess = false;
		Salesorder salesorderObj = new Salesorder();	
		BigDecimal totalTax= new BigDecimal("0.00");
			try {
			Autonum autoNum = commonListRepository.getAutonumDetail(salesorder.getBranchRecordId());				
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				Branch branch = new Branch();
				branch.setBranchId(salesorder.getCustomerBranchId());
				salesorderObj.setBranch2(branch);		
				salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
				
				Customer customer = new Customer();		
				customer.setCustomerId(1);	// Default			
				salesorderObj.setCustomer(customer);	
				
			}
			if(salesorder.getSalesType().equalsIgnoreCase("2"))
			{
				Customer customer = new Customer();		
				customer.setCustomerId(salesorder.getCustomerBranchId());				
				salesorderObj.setCustomer(customer);	
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
				
				Branch branch = new Branch();
				branch.setBranchId(1); // Default
				salesorderObj.setBranch2(branch);				
				
			}	
			
			Branch branchRecord = new Branch();
			branchRecord.setBranchId(salesorder.getBranchRecordId());
			salesorderObj.setBranch1(branchRecord);
			salesorderObj.setBranchtype(salesorder.getBranchtype());
			
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());	
			
			
			salesorderObj.setSalesOrderNo(autoNum.getSalesOrderFormat().concat(""+salesorder.getBranchRecordId()).concat(String.valueOf(StringUtils.leftPad(autoNum.getSalesOrder()+"", 8, "0"))));	
			
			salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();			
			for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
			{
				Salesorderbreakdown data=new Salesorderbreakdown();
				
				//Product productTemp = productRepository.getProductDetails(tempdata.getProductId());		
				Productbranchlink productTemp=productRepository.getProductbranchlinkMasterDetails(tempdata.getProductId(),salesorder.getBranchRecordId());
				
				data.setExpDateDate(productTemp.getExpiryDate());
				data.setBatchNo(productTemp.getBatchNo());
				data.setProduct(productTemp.getProduct());
				data.setPurchasePrice(productTemp.getPurchasePrice().multiply(tempdata.getQuantity()));	// we will modify
				
				if(salesorder.getSalesType().equalsIgnoreCase("1"))
				{
					Deliveryorderbreakdown deliveryorderbreakdown = new Deliveryorderbreakdown();
					deliveryorderbreakdown.setDeliveryOrderBreakdownId(tempdata.getDeliveryOrderBreakdownId());
					data.setDeliveryorderbreakdown(deliveryorderbreakdown);
				}				
				
				if(salesorder.getSalesType().equalsIgnoreCase("2"))
				{
				Quotationbreakdown quotationbreakdown =new  Quotationbreakdown();				
				quotationbreakdown.setQuotationBreakdownId(tempdata.getDeliveryOrderBreakdownId());
				data.setQuotationbreakdown(quotationbreakdown);
				}
				
				data.setCreatedDate(DateUtil.getTodayDate());				
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());					
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(productTemp.getTaxCode());
				totalTax=totalTax.add(tempdata.getTaxAmount());
				data.setSalesorder(salesorderObj);
				data.setPacking(tempdata.getPacking());
				
				salesorderbreakdownList.add(data);			
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);			
			salesorderObj.setTotalTax(totalTax);
			
			autoNum.setSalesOrder(autoNum.getSalesOrder()+1);
				
			autoNum=commonListRepository.updateAutonumDetail(autoNum);		
			saveSuccess=salesOrderRepository.createNewSalesorder(salesorderObj);
			
			if(saveSuccess)
			{
			if(salesorder.getSalesType().equalsIgnoreCase("2"))
			{
				//quotationBO.updateQuotation(quotationModel);
			}
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{			
			//	deliveryOrderBO.updateDeliveryorder(deliveryorder);
			}
			}
		}
		catch(Exception e)
		{
			log.info("Error in createNewSalesorder of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return saveSuccess;
	}
	
	
	
	@Transactional
	@Override
	public boolean updateSalesorder(SalesorderModel salesorder)
			throws Exception {
		    Salesorder salesorderObj = new Salesorder();
		    Salesitem salesitemObj=new Salesitem();
		    BigDecimal totalTax= new BigDecimal("0.00");
		    List<Salesorderbreakdown> salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();
		    List<Salesitembreakdown> salesitembreakdownList = new ArrayList<Salesitembreakdown>();
		    List<Integer> indexToRemove=new ArrayList<Integer>();
		    int customerTotalPoints=0;
		boolean updateSuccess = false;
		try {
			
			    salesorderObj = salesOrderRepository.getSalesorderDetails(salesorder.getSalesOrderId(),salesorder.getSalesType());
				salesitemObj=salesOrderRepository.getBranchSalesitemDetails(salesorder.getSalesOrderId(), salesorder.getSalesType());
				
				salesorderObj.setTotalAmount(salesorder.getTotalAmount());
				salesorderObj.setTotalTax(salesorder.getTotalTax());
				salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
				
				//salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());


				int i=0;
				 for(Salesorderbreakdown oldItem:salesorderObj.getSalesorderbreakdowns()){
					 indexToRemove.add(i);
					 
					 for (SalesorderbreakdownModel newItem :salesorder.getSalesorderbreakdowns() ) {
						 if(oldItem.getSalesOrderBreakdownId()==newItem.getSalesOrderBreakdownId()){
							 indexToRemove.remove(indexToRemove.size()-1);
							 break;									
						 }
					 }
					 
					i++;
				 }
				 
				 Collections.sort(indexToRemove,Collections.reverseOrder());
				 for(int index:indexToRemove) {
					 boolean delSuccess=false;
					 delSuccess=salesOrderRepository.deleteSalesorderbreakdown(salesorderObj.getSalesorderbreakdowns().get(index));
					 salesorderObj.getSalesorderbreakdowns().remove(index);
					 delSuccess=salesOrderRepository.deleteSalesitembreakdown(salesitemObj.getSalesorderbreakdowns().get(index));
					 salesitemObj.getSalesorderbreakdowns().remove(index);
				 }

				 
				 for(SalesorderbreakdownModel tempdata:salesorder.getSalesorderbreakdowns())
				{
					Salesorderbreakdown data=new Salesorderbreakdown();
					Salesitembreakdown data1=new Salesitembreakdown();
					if(tempdata.getSalesOrderBreakdownId()>0) {
						data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
						data1.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
					}
					
					Product productTemp = new Product();			
					productTemp.setProductId(tempdata.getProductId());
					productTemp.setProductName(tempdata.getProductName());
					data.setProduct(productTemp);	
					data.setCreatedDate(DateUtil.getTodayDate());
					data.setDiscount(tempdata.getDiscount());
					data.setDiscountAmount(tempdata.getDiscountAmount());				
					data.setQuantity(tempdata.getQuantity());			
					data.setSubTotal(tempdata.getSubTotal());
					data.setUnitPrice(tempdata.getUnitPrice());	
					data.setExpDateDate(tempdata.getExpDate());
					data.setBatchNo(tempdata.getBatchNo());
					data.setReturnquantity(new BigDecimal(0.00));
					data.setTaxCode(tempdata.getTaxCode());
					data.setTaxAmount(tempdata.getTaxAmount());
					data.setStatus(tempdata.getStatus());
					if(data.getStatus()!=null && data.getStatus().equalsIgnoreCase("refund")) {
						data.setStatus("refunded");
					}
					data.setSetOption(tempdata.getSetOption());
					
					data1.setProduct(productTemp);	
					data1.setCreatedDate(DateUtil.getTodayDate());
					data1.setDiscount(tempdata.getDiscount());
					data1.setDiscountAmount(tempdata.getDiscountAmount());				
					data1.setQuantity(tempdata.getQuantity());	
					
					BigDecimal subtotal=extractSubtotal(tempdata.getUnitduplicatePrice(),tempdata.getDiscountAmount(),tempdata.getQuantity(),tempdata.getTaxCode());				
					data1.setSubTotal(subtotal);
					data1.setUnitPrice(tempdata.getUnitduplicatePrice());	
					data1.setExpDateDate(tempdata.getExpDate());
					data1.setBatchNo(tempdata.getBatchNo());
					data1.setReturnquantity(new BigDecimal(0.00));
					data1.setTaxCode(tempdata.getTaxCode());
					
					BigDecimal priceAmt = tempdata.getUnitduplicatePrice().multiply(tempdata.getQuantity());
					BigDecimal taxAmt= tempdata.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					data1.setTaxAmount(taxAmt);
					
					data.setPacking(tempdata.getPacking());						
					Productbranchlink product=productRepository.getProductbranchlinkMasterDetails(tempdata.getProductId(),salesorder.getBranchId());

					
					data.setExpDateDate(product.getExpiryDate());
					data.setBatchNo(product.getBatchNo());
					data.setSalesorder(salesorderObj);
					
					data1.setExpDateDate(product.getExpiryDate());
					data1.setBatchNo(product.getBatchNo());
					data1.setSalesorder(salesitemObj);
					
					salesitembreakdownList.add(data1);
					salesorderbreakdownList.add(data);	
					customerTotalPoints=customerTotalPoints+product.getCustomerPoint();
					

					// set item child list
					
					for(SalesorderbreakdownModel setItem:tempdata.getSalesProductSetList()) {
						Salesorderbreakdown setItemdata=new Salesorderbreakdown();
						Salesitembreakdown setItemdata1=new Salesitembreakdown();
						
						Product productTemp1 = new Product();			
						productTemp1.setProductId(setItem.getProductId());

						productTemp1.setProductName(setItem.getProductName());
						setItemdata.setProduct(productTemp1);	
						
						
						setItemdata.setSalesOrderBreakdownId(setItem.getSalesOrderBreakdownId());
						setItemdata.setCreatedDate(DateUtil.getTodayDate());
						setItemdata.setDiscount(setItem.getDiscount());
						setItemdata.setDiscountAmount(setItem.getDiscountAmount());				
						setItemdata.setQuantity(setItem.getQuantity());			
						setItemdata.setSubTotal(setItem.getSubTotal());
						setItemdata.setUnitPrice(setItem.getUnitPrice());	
						setItemdata.setExpDateDate(setItem.getExpDate());
						setItemdata.setBatchNo(setItem.getBatchNo());
						setItemdata.setReturnquantity(new BigDecimal(0.00));
						setItemdata.setTaxCode(setItem.getTaxCode());
						setItemdata.setTaxAmount(setItem.getTaxAmount());
						setItemdata.setStatus(setItem.getStatus());
						
						if(setItemdata.getStatus()!=null && setItemdata.getStatus().equalsIgnoreCase("refund")) {
							setItemdata.setStatus("refunded");
						}
						
						
						setItemdata1.setProduct(productTemp);	
						setItemdata1.setCreatedDate(DateUtil.getTodayDate());
						setItemdata1.setDiscount(setItem.getDiscount());
						setItemdata1.setDiscountAmount(BigDecimal.ZERO);				
						setItemdata1.setQuantity(setItem.getQuantity());	
						
						
						BigDecimal subtotal1=BigDecimal.ZERO;//extractSubtotal(setItem.getUnitduplicatePrice(),setItem.getDiscountAmount(),setItem.getQuantity(),setItem.getTaxCode());				
						setItemdata1.setSubTotal(subtotal1);
						setItemdata1.setUnitPrice(setItem.getUnitduplicatePrice());	
						setItemdata1.setExpDateDate(setItem.getExpDate());
						setItemdata1.setBatchNo(setItem.getBatchNo());
						setItemdata1.setReturnquantity(new BigDecimal(0.00));
						setItemdata1.setTaxCode(setItem.getTaxCode());
						
						BigDecimal priceAmt1 = BigDecimal.ZERO;//setItem.getUnitduplicatePrice().multiply(setItem.getQuantity());
						BigDecimal taxAmt1= BigDecimal.ZERO;//setItem.getTaxCode().multiply((priceAmt1.divide(new BigDecimal(100))));
						setItemdata1.setTaxAmount(taxAmt1);
						
						setItemdata.setPacking(setItem.getPacking());
						
						
						Productbranchlink product1=productRepository.getProductbranchlinkMasterDetails(setItem.getProductId(),salesorder.getBranchId());
		
			
						setItemdata.setExpDateDate(product1.getExpiryDate());
						setItemdata.setBatchNo(product1.getBatchNo());
						setItemdata.setParentSetItem(data);
						setItemdata.setSalesorder(salesorderObj);
						
						setItemdata.setSetOption(setItem.getSetOption());
						
						setItemdata1.setExpDateDate(product1.getExpiryDate());
						setItemdata1.setBatchNo(product1.getBatchNo());
						setItemdata1.setParentSetItem(data1);
						setItemdata1.setSalesorder(salesitemObj);
						
						
						
						salesitembreakdownList.add(setItemdata1);
						salesorderbreakdownList.add(setItemdata);	
					}				
					// end of set item child list
					
				}				
				salesorder=extractQuoteTotal(salesitembreakdownList , salesorder);
				Integer discountRate=salesorderObj.getDiscountRate();
				
				BigDecimal discountAmount=salesorder.getTotalAmount().multiply(new BigDecimal("00."+discountRate));
				
				
				salesitemObj.setTotalAmount( DecimalUtil.formatRoundupCents(salesorder.getTotalAmount().subtract(discountAmount)) );
				
				BigDecimal discountedTaxAmount=salesorder.getTotalTax().multiply(new BigDecimal("00."+discountRate));
				
				salesitemObj.setTotalTax( DecimalUtil.formatRoundupCents(salesorder.getTotalTax().subtract(discountedTaxAmount)) );

				salesitemObj.setSalesorderbreakdowns(salesitembreakdownList);
				salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);	
					
				salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
				salesitemObj.setSalesorderbreakdowns(salesitembreakdownList);
				updateSuccess=salesOrderRepository.updateSalesorder(salesorderObj);
				updateSuccess=salesOrderRepository.updateSalesitem(salesitemObj);
	
			}
			catch(Exception e)
			{
				log.info("Error in updateSalesorder of SalesorderBOImpl "+ e.toString());
				throw e;
			}
			return updateSuccess;
	}
	
	
	
	

	@Transactional
	@Override
	public boolean deleteSalesorder(SalesorderModel salesorder)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<SalesorderbreakdownModel> getSalesorderbreakdownReportList(Date dateFrom, Date dateTo, Integer branchRecordId,String barcode , String productName)
			throws Exception {
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
		try {				
		for(Salesorderbreakdown tempdata :salesOrderRepository.getSalesorderbreakdownReportList(dateFrom, dateTo, branchRecordId, barcode,productName) )
		{
			SalesorderbreakdownModel data=new SalesorderbreakdownModel();						
			data.setDiscount(tempdata.getDiscount());
			data.setDiscountAmount(tempdata.getDiscountAmount());
			data.setProductId(tempdata.getProduct().getProductId());
			data.setProductName(tempdata.getProduct().getProductName());
			data.setProductCode(tempdata.getProduct().getBarcode());
			data.setUom(tempdata.getProduct().getUom());
			data.setQuantity(tempdata.getQuantity());			
			data.setSubTotal(tempdata.getSubTotal());
			data.setUnitPrice(tempdata.getUnitPrice());	
			data.setPurchasePrice(tempdata.getPurchasePrice());
			data.setSalesOrderNo(tempdata.getSalesorder().getSalesOrderNo());	
			data.setCreatedDate(tempdata.getCreatedDate());
			data.setQuantityAfter(tempdata.getQuantityAfter());
			data.setQuantityBefore(tempdata.getQuantityBefore());
			data.setReturnquantity(tempdata.getReturnquantity());
			data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
			
			salesorderbreakdownList.add(data);			
		}					
		}
		catch (Exception e) {
			log.info("Error in getSalesorderbreakdownList SalesorderBOImpl " + e);
			throw e;
		}
		return salesorderbreakdownList;
	}

	

	@Override
	public List<SalesorderbreakdownModel> getSalesorderbreakdownList(int[] ids,Integer breakdownId,
			Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId)
			throws Exception {
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
		try {				
		for(Salesorderbreakdown tempdata :salesOrderRepository.getSalesorderbreakdownList(ids,breakdownId,dateFrom, dateTo, start, howMany,branchRecordId) )
		{
			SalesorderbreakdownModel data=new SalesorderbreakdownModel();						
			data.setDiscount(tempdata.getDiscount());
			data.setDiscountAmount(tempdata.getDiscountAmount());							
			data.setProductId(tempdata.getProduct().getProductId());						
			data.setProductName(tempdata.getProduct().getProductName());
			data.setProductCode(tempdata.getProduct().getBarcode());
			data.setUom(tempdata.getProduct().getUom());
			data.setQuantity(tempdata.getQuantity());			
			data.setSubTotal(tempdata.getSubTotal());
			data.setUnitPrice(tempdata.getUnitPrice());	
			data.setPurchasePrice(tempdata.getPurchasePrice());
			data.setSalesOrderNo(tempdata.getSalesorder().getSalesOrderNo());	
			data.setCreatedDate(tempdata.getCreatedDate());
			data.setQuantityAfter(tempdata.getQuantityAfter());
			data.setQuantityBefore(tempdata.getQuantityBefore());
			data.setReturnquantity(tempdata.getReturnquantity());
			data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
			
			salesorderbreakdownList.add(data);			
		}					
		}
		catch (Exception e) {
			log.info("Error in getSalesorderbreakdownList SalesorderBOImpl " + e);
			throw e;
		}
		return salesorderbreakdownList;
	}

	@Override
	public int getSalesorderbreakdownCount(Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception {
		try {
			return salesOrderRepository.getSalesorderbreakdownCount(dateFrom, dateTo,branchRecordId);
		} catch (Exception e) {
			log.info("Error in getSalesorderbreakdownCount of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean deleteSalesorderbreakdown(
			SalesorderbreakdownModel salesorderbreakdown) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean approveSalesorder(SalesorderModel salesorder,DeliveryorderModel deliveryorder, QuotationModel quotationModel,Integer branchId)
			throws Exception {
		
		boolean updateSuccess = false;
		BigDecimal soldQuantityCount= new BigDecimal(0.00);
		BigDecimal balanceQuantityCount= new BigDecimal(0.00);
		List<Salesorderbreakdown> salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
			soldQuantityCount=new BigDecimal(deliveryorder.getSoldQuantityCount());
			balanceQuantityCount=new BigDecimal(deliveryorder.getBalanceQuantityCount());
		}
		else
		{
			 soldQuantityCount=quotationModel.getSoldQuantityCount();
			 balanceQuantityCount=quotationModel.getBalanceQuantityCount();
		}
		
		try {
			    Salesorder salesorderObj = salesOrderRepository.getSalesorderDetails(salesorder.getSalesOrderId(), salesorder.getSalesType());		   
				salesorderObj.setStatus(salesorder.getStatus());
				//salesorderObj.setSalesstatus("1");	
				
				for(Salesorderbreakdown tempdata:salesorderObj.getSalesorderbreakdowns())
				{
				soldQuantityCount=soldQuantityCount.add(tempdata.getQuantity());
				balanceQuantityCount=balanceQuantityCount.subtract(tempdata.getQuantity());
				
				//Product product=productRepository.getProductDetails(tempdata.getProduct().getProductId());		
				Productbranchlink product=productRepository.getProductbranchlinkMasterDetails(tempdata.getProduct().getProductId(),branchId);

				tempdata.setQuantityBefore(product.getQuantityonHand());
				tempdata.setQuantityAfter(product.getQuantityonHand().subtract(tempdata.getQuantity()));		
				tempdata.setPurchasePrice(product.getPurchasePrice().multiply(tempdata.getQuantity()));		
				tempdata.setSalesCommission(product.getSalesrepPrice().multiply(tempdata.getQuantity()));
				tempdata.setExpDateDate(product.getExpiryDate());
								
							
				product.setQuantityonHand(product.getQuantityonHand().subtract(tempdata.getQuantity()));
				if(salesorder.getSalesType().equalsIgnoreCase("2")) //Only Quotation, not for Branch
				{
				tempdata.setBatchNo(product.getBatchNo());
				productRepository.updateProductbranchlink(product);  // Update Product Stock	
				}
				salesorderbreakdownList.add(tempdata);	
				}
				
				salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
				
				
				/* update Delivery Order Total Count and Status*/
				if(salesorder.getSalesType().equalsIgnoreCase("1"))
				{					
				deliveryorder.setSoldQuantityCount(soldQuantityCount.intValue());
				deliveryorder.setBalanceQuantityCount(balanceQuantityCount.intValue());			
				if(deliveryorder.getTotalItemCount()==deliveryorder.getSoldQuantityCount())
				{
					deliveryorder.setStatus("3");
					deliveryorder.setSalesstatus("1");					
				}			
				//deliveryOrderBO.updateDeliveryorderMaster(deliveryorder);
				}
				
				/* update Delivery Order Total Count and Status*/
				
				
				
				/* update Quotation Order Total Count and Status*/
				
				if(salesorder.getSalesType().equalsIgnoreCase("2"))
				{					
					quotationModel.setSoldQuantityCount(soldQuantityCount);
					quotationModel.setBalanceQuantityCount(balanceQuantityCount);			
				if(quotationModel.getTotalItemCount().doubleValue()==quotationModel.getSoldQuantityCount().doubleValue())
				{
					quotationModel.setStatus("3");
				}			
				//updateSuccess=quotationBO.updateQuotationMaster(quotationModel);
				}				
				/* update Quotation Order Total Count and Status*/				
				
				
				/* update Sales Order status as processed*/		
									
			  updateSuccess=salesOrderRepository.updateSalesorder(salesorderObj);
			
				
				
				
								
			}
			catch(Exception e)
			{
				log.info("Error in approveSalesorder of SalesorderBOImpl "+ e.toString());
				throw e;
			}
			return updateSuccess;
	}

	
	
	@Override
	public List<BranchSalesModel> getBranchSalesByProduct(Integer branchId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
		
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		BranchSalesSummaryModel branchList = new BranchSalesSummaryModel();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getBranchSalesByProduct(branchId,dateFrom,dateTo,start,howMany,branchRecordId);
			
			
			branchData.setDateFrom(dateFrom);
			branchData.setDateTo(dateTo);				
			branchData.setBranchId(branchId);
			
			salesConsolidateList = new ArrayList<BranchSalesModel>();
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesModel data=new BranchSalesModel();			
			
				Salesorderbreakdown r = (Salesorderbreakdown) tempdata[0];				
				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);			    
				data.setProductName(r.getProduct().getProductName());
				data.setProductCode(r.getProduct().getBarcode());				
				data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));
				
				  BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
				  double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				  data.setMargin(new BigDecimal(margin));
							
				salesConsolidateList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesByProduct of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesConsolidateList;
	}

	@Override
	public List<BranchSalesSummaryModel> getBranchSalesByBranch(
			List<Integer> branchIds, Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception {
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		List<BranchSalesSummaryModel> branchList = new ArrayList<BranchSalesSummaryModel>();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getBranchSalesByBranch(branchIds,dateFrom,dateTo,branchRecordId);			
			
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesSummaryModel data=new BranchSalesSummaryModel();	
				Salesorder r = (Salesorder) tempdata[0];				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);	
			    data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));
			    data.setBranchId(r.getBranch2().getBranchId());
			    data.setBranchName(r.getBranch2().getBranchName());	
			    
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
			  
			    branchList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesByProduct of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return branchList;
	}
	
	
	
	
	@Override
	public List<BranchSalesSummaryModel> getBranchSalesByBranchReport(
			String branchIdss, Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception {
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		List<BranchSalesSummaryModel> branchList = new ArrayList<BranchSalesSummaryModel>();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			
			List<Integer> branchIds= new ArrayList<Integer>();
			if(branchIdss!=null && branchIdss!="")
			{		
				if(!branchIdss.equalsIgnoreCase(""))
				{
			List<String> requestIds2 = Arrays.asList(branchIdss.split(","));			
			for(String id:requestIds2)
			{
				branchIds.add(Integer.parseInt(id));
			}
			}
			}
			
			List<Object[]> tempdataList=salesOrderRepository.getBranchSalesByBranch(branchIds,dateFrom,dateTo,branchRecordId);			
			
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesSummaryModel data=new BranchSalesSummaryModel();	
				Salesorder r = (Salesorder) tempdata[0];				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);	
			    data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));
			    data.setBranchId(r.getBranch2().getBranchId());
			    data.setBranchName(r.getBranch2().getBranchName());	
			    
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
			  
			    branchList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesByProduct of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return branchList;
	}
	
	
	
	
	@Override
	public List<BranchSalesModel> getBranchSalesByProductReport(Integer branchId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		BranchSalesSummaryModel branchList = new BranchSalesSummaryModel();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getBranchSalesByProductReport(branchId,dateFrom,dateTo,branchRecordId);
			
			
			branchData.setDateFrom(dateFrom);
			branchData.setDateTo(dateTo);				
			branchData.setBranchId(branchId);
			
			salesConsolidateList = new ArrayList<BranchSalesModel>();
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesModel data=new BranchSalesModel();			
			
				Salesorderbreakdown r = (Salesorderbreakdown) tempdata[0];				
				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);			    
				data.setProductName(r.getProduct().getProductName());
				data.setProductCode(r.getProduct().getBarcode());				
				data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));
				
				  BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
				  double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				  data.setMargin(new BigDecimal(margin));
							
				salesConsolidateList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getBranchSalesByProduct of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesConsolidateList;
	}

	
	
	
	
	@Override
	public List<BranchSalesModel> getCustomerSalesByProductReport(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		BranchSalesSummaryModel branchList = new BranchSalesSummaryModel();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getCustomerSalesByProductReport(customerId,dateFrom,dateTo,branchRecordId);
			
			
			branchData.setDateFrom(dateFrom);
			branchData.setDateTo(dateTo);				
			branchData.setCustomerId(customerId);
			
			salesConsolidateList = new ArrayList<BranchSalesModel>();
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesModel data=new BranchSalesModel();			
			
				Salesorderbreakdown r = (Salesorderbreakdown) tempdata[0];				
				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);			    
				data.setProductName(r.getProduct().getProductName());
				data.setProductCode(r.getProduct().getBarcode());				
				data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));			
				
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
				
							
				salesConsolidateList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getCustomerSalesByProductReport of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesConsolidateList;
	}
	
	
	@Override
	public List<BranchSalesModel> getCustomerSalesByProduct(Integer customerId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
		
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		BranchSalesSummaryModel branchList = new BranchSalesSummaryModel();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getCustomerSalesByProduct(customerId,dateFrom,dateTo,start,howMany,branchRecordId);
			
			
			branchData.setDateFrom(dateFrom);
			branchData.setDateTo(dateTo);				
			branchData.setCustomerId(customerId);
			
			salesConsolidateList = new ArrayList<BranchSalesModel>();
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesModel data=new BranchSalesModel();			
			
				Salesorderbreakdown r = (Salesorderbreakdown) tempdata[0];				
				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);			    
				data.setProductName(r.getProduct().getProductName());
				data.setProductCode(r.getProduct().getBarcode());				
				data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));			
				
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
				
							
				salesConsolidateList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getCustomerSalesByProduct of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesConsolidateList;
	}

	
	
	@Override
	public List<BranchSalesSummaryModel> getCustomerSalesByBranchReport(String customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception {
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		List<BranchSalesSummaryModel> branchList = new ArrayList<BranchSalesSummaryModel>();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		
		List<Integer> Ids= new ArrayList<Integer>();
		if(customerIds!=null && customerIds!=""&& customerIds!="0")
		{		
			if(!customerIds.equalsIgnoreCase("")&& (!customerIds.equalsIgnoreCase("0")))
			{
		List<String> requestIds2 = Arrays.asList(customerIds.split(","));			
		for(String id:requestIds2)
		{
			Ids.add(Integer.parseInt(id));
		}
		}
		}
		
		try {
			List<Object[]> tempdataList=salesOrderRepository.getCustomerSalesByBranchReport(Ids,dateFrom,dateTo,branchRecordId);			
			
			int id = 0;		
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesSummaryModel data=new BranchSalesSummaryModel();	
				Salesorder r = (Salesorder) tempdata[0];				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);	
			    data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));			   
			    data.setCustomerId(r.getCustomer().getCustomerId());
			    data.setCustomerName(r.getCustomer().getCustomerName());
			    data.setLoyalityCode(r.getCustomer().getLoyaltyCardCode());
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
				
			    branchList.add(data);
				id = id + 1;
			}			
		
			branchData.setProduct(salesConsolidateList);
			
		}
		catch(Exception e)
		{
			log.info("Error in getCustomerSalesByBranchReport of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return branchList;
	}
	
	
	
	@Override
	public List<BranchSalesSummaryModel> getCustomerSalesByBranch(List<Integer> customerIds ,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId)
			throws Exception {
		List<BranchSalesModel> salesConsolidateList = new ArrayList<BranchSalesModel>();
		List<BranchSalesSummaryModel> branchList = new ArrayList<BranchSalesSummaryModel>();
		BranchSalesSummaryModel branchData = new BranchSalesSummaryModel();
		try {
			List<Object[]> tempdataList=salesOrderRepository.getCustomerSalesByBranch(customerIds,dateFrom,dateTo,start,howMany,branchRecordId);			
			
			int id = 0;		
			if(tempdataList!=null)
			{
			for(Object[] tempdata :tempdataList)
			{
				BranchSalesSummaryModel data=new BranchSalesSummaryModel();	
				Salesorder r = (Salesorder) tempdata[0];				
			    int count = ((Number) tempdata[1]).intValue();	  	    
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);	
			    data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));			   
			    data.setCustomerId(r.getCustomer().getCustomerId());
			    data.setCustomerName(r.getCustomer().getCustomerName());
			  
			    BigDecimal margin1=(data.getMarginTotal().multiply(new BigDecimal(100)));
			    double margin= (margin1.doubleValue())/(data.getNormalPriceTotal().doubleValue());			    
				data.setMargin(new BigDecimal(margin));
				
			    branchList.add(data);
				id = id + 1;
			}			
			branchData.setProduct(salesConsolidateList);
			}
		}
		catch(Exception e)
		{
			log.info("Error in getCustomerSalesByBranch of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return branchList;
	}

	@Override
	public BranchSalesSummaryModel getBranchSalesByProductCount(Integer branchId, Date dateFrom,
			Date dateTo,Integer branchRecordId) throws Exception {
			List<Object[]> tempdataList=null;
			BranchSalesSummaryModel data=null;
		try {
			tempdataList=salesOrderRepository.getBranchSalesByProductCount(branchId,dateFrom, dateTo,branchRecordId);
			for(Object[] tempdata :tempdataList)
			{
				data=new BranchSalesSummaryModel();					
				int totalCount = ((Number) tempdata[0]).intValue();
			    int count = ((Number) tempdata[1]).intValue();	
			    data.setTotalCount(totalCount);
			    data.setTotalQuantity(count);
			    data.setNormalPriceTotal((BigDecimal)tempdata[2]);
			    data.setPurchasePriceTotal((BigDecimal)tempdata[3]);	
			    data.setMarginTotal(data.getNormalPriceTotal().subtract(data.getPurchasePriceTotal()));			   
			   			
			}			
			
			return data;
			
		} catch (Exception e) {
			log.info("Error in getBranchSalesByProductCount of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	@Override
	public int getCustomerSalesByProductCount(Integer customerId,
			Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		try {
			return salesOrderRepository.getCustomerSalesByProductCount(customerId,dateFrom, dateTo,branchRecordId);
		} catch (Exception e) {
			log.info("Error in getCustomerSalesByProductCount of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	@Override
	public int getCustomerSalesByBranchCount(List<Integer> customerIds,
			Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		try {
			return salesOrderRepository.getCustomerSalesByBranchCount(customerIds,dateFrom, dateTo,branchRecordId);
		} catch (Exception e) {
			log.info("Error in getCustomerSalesByBranchCount of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean updateSalesorderbreakdown(
			SalesorderbreakdownModel tempdata) throws Exception {
		boolean updateSuccess=false;
		Salesorderbreakdown data=null;
		try {
			List<Salesorderbreakdown> sList=salesOrderRepository.getSalesorderbreakdownList(null,tempdata.getSalesOrderBreakdownId(), null, null, 0, 1,null);
			if(sList!=null && sList.size()!=0)
			{
				data=sList.get(0);				
				data.setReturnquantity(tempdata.getReturnquantity());
				updateSuccess=salesOrderRepository.updateSalesorderbreakdown(data);	
			}						
			return updateSuccess;
		} catch (Exception e) {
			log.info("Error in updateSalesorderbreakdown of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	
	@Override
	public List<SalesorderModel> getStaffSalesCommission(String status,Date dateFrom, Date dateTo,Integer staffId) throws Exception{
		List<SalesorderModel> saleslList = new ArrayList<SalesorderModel>();
		try {
			List<BranchstaffmemberModel> branchstaffmember = null;
			List<Object[]> commssionStaffList=salesOrderRepository.getStaffSalesCommission(status,dateFrom,dateTo);			
			if(commssionStaffList == null || commssionStaffList.size() == 0){
				log.info("getStaffSalesCommission List is Empty @ SalesorderBOImpl()   ");
				return null;
			}					
				for(Object[] tempdata :commssionStaffList)
				{					
				Salesorder salesorder = (Salesorder) tempdata[0];	
				
				SalesorderModel salesorderObj = new SalesorderModel();				
				salesorderObj.setCommissionAmount((BigDecimal)tempdata[1]);				
				salesorderObj.setCreatedBy(salesorder.getCreatedBy());
				salesorderObj.setCreatedDate(salesorder.getCreatedDate());
				salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
				salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
				salesorderObj.setDueDate(salesorder.getDueDate());
				salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
				salesorderObj.setOrderPayment(salesorder.getOrderPayment());
				salesorderObj.setOrderReturned(salesorder.getOrderReturned());
				salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
				salesorderObj.setPaymentType(salesorder.getPaymentType());
				salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());				
				salesorderObj.setRemarks(salesorder.getRemarks());
				salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
				salesorderObj.setSalesDate(salesorder.getSalesDate());				
				salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
				salesorderObj.setSalesRep(salesorder.getSalesRep());
				salesorderObj.setSalesType(salesorder.getSalesType());
				salesorderObj.setShippedDate(salesorder.getShippedDate());
				salesorderObj.setStatus(salesorder.getStatus());
				salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
				salesorderObj.setTotalAmount(salesorder.getTotalAmount());	
				
				branchstaffmember = staffBO.findStaffMemberlistLogin(salesorder.getSalesRep());							
				salesorderObj.setBranchstaffmember(branchstaffmember.get(0));			  
			
				saleslList.add(salesorderObj);
			}
		}
		catch(Exception e)
		{
			log.info("Error in getStaffSalesCommission of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return saleslList;
	}
	
	
	
	

	@Override
	public List<SalesorderbreakdownModel> getSalesByBranchProductReport(Date dateFrom, Date dateTo,Integer limit,Integer branchRecordId) throws Exception {
		List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
		try {				
			List<Object[]> salesList=null;	
			int count=0;
			salesList=salesOrderRepository.getSalesByBranchProductReport(dateFrom, dateTo,limit,branchRecordId);
			
			if(salesList!=null)
			{
			for(Object[] tempdata :salesList)
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();					
				Salesorderbreakdown salesData = (Salesorderbreakdown) tempdata[0];				
				int totalCount = ((Number) tempdata[1]).intValue();				
				data.setQuantity(new BigDecimal(totalCount));
				data.setSubTotal((BigDecimal)tempdata[2]);				
			    data.setPurchasePrice((BigDecimal)tempdata[3]);
			    data.setProductCode(salesData.getProduct().getBarcode());
			    data.setProductName(salesData.getProduct().getProductName());
			    data.setUom(salesData.getProduct().getUom());
			    data.setProductId(salesData.getProduct().getProductId());
			    data.setSno(count++);
			    salesorderbreakdownList.add(data);		
			}	
			}
						
		}
		catch (Exception e) {
			log.info("Error in getSalesByBranchProductReport SalesorderBOImpl " + e);
			throw e;
		}
		return salesorderbreakdownList;
	}

	
	
	@Override
	public List<SalesPurchaseDashboradModel> getSalesDashboard(Date dateFrom, Date dateTo,Integer branchRecordId,String usertype) throws Exception {
		List<SalesPurchaseDashboradModel> salesorderbreakdownList = new ArrayList<SalesPurchaseDashboradModel>();		
		List<SalesPurchaseDashboradModel> dashList = new ArrayList<SalesPurchaseDashboradModel>();			
		try {				
			List<Object[]> salesList=null;	
			List<Integer> month = new ArrayList<Integer>();
			int count=0;
			salesList=salesOrderRepository.getSalesDashboard(dateFrom, dateTo,branchRecordId,usertype);
			if(salesList!=null)
			{
			for(Object[] tempdata :salesList)
			{
				SalesPurchaseDashboradModel data=new SalesPurchaseDashboradModel();	
				
				if(usertype.equalsIgnoreCase("0"))
				{
					Salesorderbreakdown salesData = (Salesorderbreakdown) tempdata[0];
					}
				if(usertype.equalsIgnoreCase("1"))
				{
					Salesitembreakdown salesData = (Salesitembreakdown) tempdata[0];	
				}				
							
				data.setSubTotal((BigDecimal)tempdata[1]);				
				data.setMonthNo((Integer)tempdata[2]);
				month.add((Integer)tempdata[2]);	
				data.setQuantity((BigDecimal)tempdata[3]);
				data.setOriginalTax((BigDecimal)tempdata[4]);
			    salesorderbreakdownList.add(data);		
			}			
			}
		}
		catch (Exception e) {
			log.info("Error in getSalesDashboard SalesorderBOImpl " + e);
			throw e;
		}
		return salesorderbreakdownList;
	}

	
	@Transactional
	@Override
	public boolean deleteSalesorderhold(SalesorderModel salesorder)
			throws Exception {
		try {			
			Salesorderhold sales = new Salesorderhold();
			sales.setSalesOrderId(salesorder.getSalesOrderId());
			
			return salesOrderRepository.deleteSalesorderhold(sales);
		} catch (Exception e) {
			log.info("Error in deleteSalesorderhold of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}
	
	

	@Transactional
	@Override
	public boolean deleteSalesorderbreakdownhold(
			SalesorderbreakdownModel salesorder) throws Exception {
		try {			
			Salesorderbreakdownhold sales = new Salesorderbreakdownhold();
			sales.setSalesOrderBreakdownId(salesorder.getSalesOrderBreakdownId());		
			return salesOrderRepository.deleteSalesorderbreakdownhold(sales);
		} catch (Exception e) {
			log.info("Error in deleteSalesorderbreakdownhold of SalesorderBOImpl "+ e.toString());
			throw e;
		}
	}

	
	@Override
	public boolean findKitchenCardExites(String cardNo)	throws Exception {
		boolean exists = false;		
		try {
			exists = salesOrderRepository.findKitchenCardExites(cardNo);
		}
		catch (Exception e) {
			log.info("Error in findKitchenCardExites SalesorderBOImpl " + e);
			throw e;
		}
		return exists;
	}
	
	
	public boolean deleteSalesordernative(String salesOrderNo ,Integer salesOrderId , Integer branchRecordId) throws Exception
	{
		boolean exists = false;		
		try {
			exists = salesOrderRepository.deleteSalesordernative(salesOrderNo,salesOrderId,branchRecordId);
		}
		catch (Exception e) {
			log.info("Error in deleteSalesordernative SalesorderBOImpl " + e);
			throw e;
		}
		return exists;
	}
	
	public boolean cardInUse(String cardNo) throws Exception{
		
		boolean inUse=false;
		try {
			inUse = salesOrderRepository.cardInUse(cardNo);
		}
		catch (Exception ex) {
			log.info("Error in cardInUse SalesorderBOImpl " + ex);
			throw ex;
		}
		return inUse;
	}

	@Override
	public List<SalesorderbreakdownModel> getTopCategorySalesByStaff(Integer top, Integer staffId, Date date) {
		List<SalesorderbreakdownModel> objList=new ArrayList<SalesorderbreakdownModel>();
		try {
			SalesorderbreakdownModel obj=salesOrderRepository.getTopCategorySalesByStaff(top,staffId,date);
		}catch(Exception ex) {
			log.info("Error in getTopCategorySalesByStaff SalesorderBOImpl " + ex);
			throw ex;
		}
		return null;
	}

	@Override
	public List<SalesorderModel> getTopSalesStaff(Integer top, Date date, int branchId) throws Exception{
		List<SalesorderModel> salesList = new ArrayList<SalesorderModel>();
		try {
			
			List<Kitchensorder>  kitchenData=salesOrderRepository.getTopSalesStaff(top,date,branchId);
			for(Kitchensorder data:kitchenData) {
			
				SalesorderModel salesorderObj = new SalesorderModel();	
				salesorderObj.setCreatedBy(data.getCreatedBy());
				//salesorderObj.setCreatedDate(data.getCreatedDate());
				//salesorderObj.setBalanceAmount(data.getBalanceAmount());
				//salesorderObj.setDeliveryOrderNo(data.getDeliveryOrderNo());
				//salesorderObj.setDueDate(data.getDueDate());
				//salesorderObj.setLastModifiedDate(data.getLastModifiedDate());
				//salesorderObj.setOrderPayment(data.getOrderPayment());
				//salesorderObj.setOrderReturned(data.getOrderReturned());
				//salesorderObj.setOrderOnhold(data.getOrderOnhold());
				//salesorderObj.setPaymentType(data.getPaymentType());
				//salesorderObj.setPricingCurrency(data.getPricingCurrency());
				
				//salesorderObj.setRemarks(data.getRemarks());
				//salesorderObj.setRequestedShipDate(data.getRequestedShipDate());
				salesorderObj.setSalesDate(data.getSalesDate());
				//salesorderObj.setSalesOrderId(data.getSalesOrderId());
				//salesorderObj.setSalesOrderNo(data.getSalesOrderNo());
				salesorderObj.setSalesRep(data.getSalesRep());
				//salesorderObj.setSalesType(data.getSalesType());
				//salesorderObj.setShippedDate(data.getShippedDate());
				//salesorderObj.setStatus(data.getStatus());
				//salesorderObj.setTaxingScheme(data.getTaxingScheme());
				salesorderObj.setTotalAmount(data.getTotalAmount());
				//salesorderObj.setTotalTax(data.getTotalTax());
				//salesorderObj.setPrescriptionNo(data.getPrescriptionNo());
	
				salesList.add(salesorderObj);
			}
		}catch(Exception ex) {
			log.info("Error in getTopSalesStaff SalesorderBOImpl " + ex);
			throw ex;
		}
		return salesList;
	}

	
	@Override
	public List<SalesorderModel> getKitchenorderList2(Date dateFrom, Date dateTo, String status,
			int branchId2) throws Exception {
		List<SalesorderModel> salesorderModelObjList = new ArrayList<SalesorderModel>();
		
		try {
		List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderList2(dateFrom,dateTo,status,branchId2);

		int id=0;
		
		for (Kitchensorder salesorder : salesorderList) {
			
			SalesorderModel salesorderObj = new SalesorderModel();
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setOldcardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());

	//		salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
	//		salesorderObj.setDiscountRate(salesorder.getDiscountRate());
	//		salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			
			
			
			
			int count=0;
			List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Kitchenorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
							
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());	
				data.setQuantityBefore(data.getQuantity());
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());
				data.setSno(count++);
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
				data.setStatus(tempdata.getStatus());
				ItemRemarksListModel remarksObj=new ItemRemarksListModel();
				remarksObj.setRemarksListID(tempdata.getRemarks().getId());
				data.setRemarks(remarksObj);
				data.setPacking(tempdata.getPacking());
				data.setSetOption(tempdata.getSetOption());
				
				ProductModel pro=new ProductModel();
				pro.setProductId(tempdata.getProduct().getProductId());
				pro.setCategoryId(tempdata.getProduct().getProductcategory().getCategoryId());
				data.setProduct(pro);
				data.setId(id + "");				
				salesorderbreakdownList.add(data);	
				id = id + 1;
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);	
			
			salesorderModelObjList.add(salesorderObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getKitchenordertList2 of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderModelObjList;
	}

	@Override
	public List<PaymentCollectionModel> getPosPaymentDetails(Date datesFrom, Date datesTo, String type,
			Integer branchId) throws Exception{
		List<PaymentCollectionModel> objList=new ArrayList<PaymentCollectionModel>();
		try{
			List<Pospayment> dataList=salesOrderRepository.getPospaymentDetails(datesFrom,datesTo,type,branchId);
			for(Pospayment data:dataList) {
				PaymentCollectionModel obj=new PaymentCollectionModel();
				obj.setAmount(data.getAmount());
				obj.setAmountType(data.getAmountType());
				objList.add(obj);
			}
		}catch(Exception ex)
		{
			log.info("Error in getPosPaymentDetails of SalesorderBOImpl "+ ex.toString());
			throw ex;
		}
		return objList;
	
	}

	@Override
	public List<SalesorderbreakdownModel> getOrderRerportListCategoryDateBased(Date dateFrom, Date dateTo,int branchId)	throws Exception {
		 List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();	


		
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		
		BigDecimal totalsales = new BigDecimal(0.00);
		BigDecimal totalamount = new BigDecimal(0.00);
		BigDecimal totalgst = new BigDecimal(0.00);
	try {
			List<Object[]> salesorderbreakdown = salesOrderRepository.getSalesorderReportListCategoryDateBased(dateFrom,dateTo,branchId);
			
			
			//for(Salesorderbreakdown sobd: salesorderbreakdown)
			 int id = 0;
			 for(Object[] sobd: salesorderbreakdown)
			 {	
				 SalesorderbreakdownModel data=new SalesorderbreakdownModel();
				 
				 data.setCategoryName(sobd[0].toString());
				 data.setUnitPrice((BigDecimal)sobd[1]);
				 data.setTaxAmount((BigDecimal)sobd[2]);
				 
				 data.setQtty((BigDecimal)sobd[3]);
				 data.getProduct().setCategoryId((int) sobd[4]);
				 
				 data.setCreatedDate((Date) sobd[5]);
				 
				 data.setId(id + "");				
					
				 salesorderbreakdownList.add(data);	
				 id = id + 1;
				
				
			 }
			 
		}
		catch(Exception e)
		{
			
			log.info("Error in getSalesorderDetails of SalesorderBOImpl "+ e.toString());
			throw e;
		}
		return salesorderbreakdownList;
	}
	
	@Override
	public List<SalesorderModel> getDiscountReport(Date dateFrom, Date dateTo,  int branchId) throws Exception {
		List<SalesorderModel> salesObjList = new ArrayList<SalesorderModel>();
		
		try {
			List<Salesorder> dataObj=salesOrderRepository.getDiscountReport(dateFrom,dateTo,branchId);
		
			for(Salesorder data:dataObj) {
				SalesorderModel obj=new SalesorderModel();
				obj.setSalesOrderId(data.getSalesOrderId());
				obj.setTotalBeforeDiscount(data.getTotalBeforeDiscount());
				obj.setTotalAmount(data.getTotalAmount());
				obj.setTotalTax(data.getTotalTax());
				obj.setReceivedAmount(data.getReceivedAmount());
				obj.setDiscountRate(data.getDiscountRate());
				obj.setDiscountRemarks(data.getDiscountRemarks());
				obj.setCreatedDate(data.getCreatedDate());
				obj.setSalesOrderNo(data.getSalesOrderNo());
			//	BranchModel branchObj=new BranchModel();
			//	branchObj.setBranchId(data.getBranch2().getBranchId());
			//	obj.setBranch(branchObj);
				
				salesObjList.add(obj);
				
			}
		}catch(Exception ex){
			log.info("Error in getDiscountReport of SalesorderBOImpl "+ ex.toString());
			throw ex;
		}
		return salesObjList;
	}

	@Override
	public List<SalesorderModel> getCategoryReportWithItems(Date dateFrom, Date dateTo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean productISSales(int productId) throws Exception {
		boolean isSales = false;
		try {
			isSales=salesOrderRepository.productIsSales(productId);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in productISSales of SalesorderBOImpl "+ ex);

		}
		return isSales;
	}

	@Override
	public boolean productIsKitchenOrder(int productId) throws Exception {
		boolean isKitchenOrder = false;
		try {
			isKitchenOrder=salesOrderRepository.productIsKitchenOrder(productId);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in productIsKitchenOrder of SalesorderBOImpl "+ ex);

		}
		return isKitchenOrder;
	}

	@Override
	public List<SalesorderModel> getDailySales(Date dateFrom, Date dateTo, int branchId, String paymentType ,  String salesRep) throws Exception {
		List<SalesorderModel> objList=new ArrayList<SalesorderModel>();
		try {
			List<Object[]> dataList=salesOrderRepository.getDailySales(dateFrom,dateTo,branchId,paymentType,salesRep);
			
			for(Object[]  data:dataList) {
				SalesorderModel obj=new SalesorderModel();
				obj.setCreatedDate((Date) data[0]);
				obj.setTotalAmount((BigDecimal) data[1]);
				obj.setTotalTax((BigDecimal) data[2]);
				obj.setTotalBeforeDiscount((BigDecimal) data[3]);
				
				
				objList.add(obj);
			}
			
		}catch(Exception ex){
			log.info("Error in getDailySales of SalesorderBOImpl "+ ex.toString());
			throw ex;
		}
		return objList;
	}

	@Override
	public SalesorderModel getKitchenorderDetails(int kitchenOrderId) throws Exception {
		SalesorderModel salesorderObj = new SalesorderModel();

		try {
			Kitchensorder salesorder = salesOrderRepository.getKitchensorderDetails(kitchenOrderId);
		
			
			salesorderObj.setCreatedBy(salesorder.getCreatedBy());
			salesorderObj.setCreatedDate(salesorder.getCreatedDate());
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());
			salesorderObj.setDeliveryOrderNo(salesorder.getDeliveryOrderNo());
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());
			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setSalesOrderId(salesorder.getSalesOrderId());
			salesorderObj.setSalesOrderNo(salesorder.getSalesOrderNo());
			salesorderObj.setSalesRep(salesorder.getSalesRep());
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());
			salesorderObj.setPrescriptionNo(salesorder.getPrescriptionNo());
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
		//	salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
		//	salesorderObj.setDiscountRate(salesorder.getDiscountRate());
		//	salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
			salesorderObj.setOldcardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
				
			if(salesorder.getSalesType().equalsIgnoreCase("2") || salesorder.getSalesType().equalsIgnoreCase("3"))
			{
					
				salesorderObj.setCustomerBranchId(salesorder.getCustomer().getCustomerId());
				salesorderObj.setCustomerBranchName(salesorder.getCustomer().getCustomerName());
				salesorderObj.setQuoteNo(salesorder.getQuoteNo());
			}
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
				salesorderObj.setCustomerBranchId(salesorder.getBranch2().getBranchId());
				salesorderObj.setCustomerBranchName(salesorder.getBranch2().getBranchName());
				salesorderObj.setQuoteNo(salesorder.getDeliveryOrderNo());
			}	
				
			
			int count=0;
			List<SalesorderbreakdownModel> salesorderbreakdownList = new ArrayList<SalesorderbreakdownModel>();			
			for(Kitchenorderbreakdown tempdata:salesorder.getSalesorderbreakdowns())
			{
				SalesorderbreakdownModel data=new SalesorderbreakdownModel();
							
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());							
				data.setProductId(tempdata.getProduct().getProductId());						
				data.setProductName(tempdata.getProduct().getProductName());
				data.setProductCode(tempdata.getProduct().getBarcode());
				data.setUom(tempdata.getProduct().getUom());
				data.setQuantity(tempdata.getQuantity());	
				data.setQuantityBefore(data.getQuantity());
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());
				data.setSno(count++);
				data.setPurchasePrice(tempdata.getPurchasePrice());
				data.setReturnquantity(tempdata.getReturnquantity());
				data.setSalesCommission(tempdata.getSalesCommission());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				data.setSalesOrderBreakdownId(tempdata.getSalesOrderBreakdownId());
				data.setStatus(tempdata.getStatus());
				ItemRemarksListModel remarksObj=new ItemRemarksListModel();
				remarksObj.setRemarksListID(tempdata.getRemarks().getId());
				data.setRemarks(remarksObj);
				data.setPacking(tempdata.getPacking());
				data.setSetOption(tempdata.getSetOption());
				
				ProductModel pro=new ProductModel();
				pro.setProductId(tempdata.getProduct().getProductId());
				pro.setCategoryId(tempdata.getProduct().getProductcategory().getCategoryId());
				data.setProduct(pro);
								
				salesorderbreakdownList.add(data);	
			}				
			salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);	
			
		}catch(Exception ex){
			log.info("Error in getKitchenorderDetails of SalesorderBOImpl "+ ex.toString());
			throw ex;
		}
		return salesorderObj;
		
	}

	@Transactional
	@Override
	public SalesorderModel updateKitchenorderSplit(SalesorderModel salesorder)
			throws Exception {
		List<Quotationbreakdown> quotationbreakdownList = new ArrayList<Quotationbreakdown>();		
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		List<Integer> itemIds = new  ArrayList<Integer>();		
		Kitchensorder salesorderObj = new Kitchensorder();
		
		//List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), salesorder.getCreatedBy(),null, null, null, null, null, salesorder.getBranchRecordId());
		List<Kitchensorder> salesorderList = salesOrderRepository.getKitchenorderReportList(null,null,salesorder.getSalesOrderNo(), null,null, null, null, null, null, salesorder.getBranchRecordId());
		
		if(salesorderList!=null && salesorderList.size()!=0)
		{
			salesorderObj  = salesorderList.get(0);
		}
		
		
		
	try {
		
		 for(Kitchenorderbreakdown oldItem:salesorderObj.getSalesorderbreakdowns()){
			 itemRemoved = true;
		 for (SalesorderbreakdownModel newItem :salesorder.getSalesorderbreakdowns() ) {
				if(oldItem.getSalesOrderBreakdownId()==newItem.getSalesOrderBreakdownId()){
					itemRemoved = false;							
					break;									
				}
		}
		if(itemRemoved){
			
			
			salesOrderRepository.deleteKitchenorderbreakdown(oldItem);					
			}			
		 }		
		 
		 	//salesorderObj.setCreatedBy(salesorder.getCreatedBy()); avoid overwrite creator
			//salesorderObj.setCreatedDate(salesorder.getCreatedDate()); // avoid overwrite creator
			salesorderObj.setBalanceAmount(salesorder.getBalanceAmount());			
			salesorderObj.setDueDate(salesorder.getDueDate());
			salesorderObj.setLastModifiedDate(salesorder.getLastModifiedDate());
			salesorderObj.setOrderPayment(salesorder.getOrderPayment());
			salesorderObj.setOrderReturned(salesorder.getOrderReturned());
			salesorderObj.setOrderOnhold(salesorder.getOrderOnhold());
			salesorderObj.setPaymentType(salesorder.getPaymentType());
			salesorderObj.setPricingCurrency(salesorder.getPricingCurrency());			
			salesorderObj.setRemarks(salesorder.getRemarks());
			salesorderObj.setRequestedShipDate(salesorder.getRequestedShipDate());
			salesorderObj.setSalesDate(salesorder.getSalesDate());
			salesorderObj.setReceivedAmount(salesorder.getReceivedAmount());
			salesorderObj.setChangeAmount(salesorder.getBalanceAmount());
		
			
			//salesorderObj.setSalesRep(salesorder.getSalesRep()); avoid overwrite creator
			salesorderObj.setSalesType(salesorder.getSalesType());
			salesorderObj.setShippedDate(salesorder.getShippedDate());
			salesorderObj.setStatus(salesorder.getStatus());
			salesorderObj.setTaxingScheme(salesorder.getTaxingScheme());
			salesorderObj.setTotalAmount(salesorder.getTotalAmount());		
			salesorderObj.setTotalTax(salesorder.getTotalTax());
			
			salesorderObj.setCardNo(salesorder.getCardNo());
			salesorderObj.setTableName(salesorder.getTableName());
			salesorderObj.setSplitInc(salesorder.getSplitInc());
			salesorderObj.setSplitFrom(salesorder.getSplitFrom());
			salesorderObj.setPax(salesorder.getPax());
	//		salesorderObj.setTotalBeforeDiscount(salesorder.getTotalBeforeDiscount());
	//		salesorderObj.setDiscountRate(salesorder.getDiscountRate());
	//		salesorderObj.setDiscountRemarks(salesorder.getDiscountRemarks());
		
		
			List<Kitchenorderbreakdown> salesorderbreakdownList = new ArrayList<Kitchenorderbreakdown>();
		
		for(Kitchenorderbreakdown newItem : salesorderObj.getSalesorderbreakdowns()){
			itemIds.add(newItem.getSalesOrderBreakdownId());
		}			
		for (SalesorderbreakdownModel tempdata : salesorder.getSalesorderbreakdowns()) {								 
			
			if(itemIds.contains(tempdata.getSalesOrderBreakdownId()))
			{	
				
				for(Kitchenorderbreakdown newItem1 : salesorderObj.getSalesorderbreakdowns()){
					if(newItem1.getSalesOrderBreakdownId()==tempdata.getSalesOrderBreakdownId())
					{	
						
						Product productTemp = new Product();			
						productTemp.setProductId(tempdata.getProductId());
						productTemp.setProductName(tempdata.getProductName());
						if(newItem1.getStatus()==null) {
							newItem1.setStatus("ordered");
						}
						
						newItem1.setProduct(productTemp);	
						newItem1.setCreatedDate(DateUtil.getTodayDate());
						newItem1.setDiscount(tempdata.getDiscount());
						newItem1.setDiscountAmount(tempdata.getDiscountAmount());				
						newItem1.setQuantity(tempdata.getQuantity());			
						newItem1.setSubTotal(tempdata.getSubTotal());
						newItem1.setUnitPrice(tempdata.getUnitPrice());	
						newItem1.setExpDateDate(tempdata.getExpDate());
						newItem1.setBatchNo(tempdata.getBatchNo());
						newItem1.setReturnquantity(new BigDecimal(0.00));
						newItem1.setTaxCode(tempdata.getTaxCode());
						newItem1.setTaxAmount(tempdata.getTaxAmount());
						
						Itemremarkslist remarksObj=new Itemremarkslist();
						remarksObj.setId(tempdata.getRemarks().getRemarksListID());
						
						List<Kitchenorderremarksbreakdown> remarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
						/*
						for(KitchenorderremarksbreakdownModel remarksData:tempdata.getKitchenorderremarksbreakdownModel()) {
							Kitchenorderremarksbreakdown obj=new Kitchenorderremarksbreakdown();
							
							Itemremarkslist remark=new Itemremarkslist();
							remark.setId(remarksData.getRemarks().getRemarksListID());
							
							obj.setRemarks(remark);
							obj.setKitchenorder(newItem1);
							remarksObjList.add(obj);
						}
						newItem1.setKitchenorderremarksbreakdown(remarksObjList);
						*/
						
						newItem1.setRemarks(remarksObj);
						newItem1.setSalesorder(salesorderObj);
						newItem1.setPacking(tempdata.getPacking());
						newItem1.setSetOption(tempdata.getSetOption());
						salesorderbreakdownList.add(newItem1);	
						
					}
				}					
			}
			else
			{									
				//new order
				Kitchenorderbreakdown data= new Kitchenorderbreakdown();	
				
				Product productTemp = new Product();			
				productTemp.setProductId(tempdata.getProductId());
				productTemp.setProductName(tempdata.getProductName());
				//productTemp.setStatus("ordered");
				data.setStatus("ordered");
				data.setProduct(productTemp);	
				data.setCreatedDate(DateUtil.getTodayDate());
				data.setDiscount(tempdata.getDiscount());
				data.setDiscountAmount(tempdata.getDiscountAmount());				
				data.setQuantity(tempdata.getQuantity());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());	
				data.setExpDateDate(tempdata.getExpDate());
				data.setBatchNo(tempdata.getBatchNo());
				data.setReturnquantity(new BigDecimal(0.00));
				data.setTaxCode(tempdata.getTaxCode());
				data.setTaxAmount(tempdata.getTaxAmount());
				
				Itemremarkslist remarksObj=new Itemremarkslist();
				remarksObj.setId(tempdata.getRemarks().getRemarksListID());
				data.setRemarks(remarksObj);
				
				
				List<Kitchenorderremarksbreakdown> remarksObjList=new ArrayList<Kitchenorderremarksbreakdown>();
				for(KitchenorderremarksbreakdownModel remarksData:tempdata.getKitchenorderremarksbreakdownModel()) {
					Kitchenorderremarksbreakdown obj=new Kitchenorderremarksbreakdown();
					
					Itemremarkslist remark=new Itemremarkslist();
					remark.setId(remarksData.getRemarks().getRemarksListID());
					
					obj.setRemarks(remark);
					obj.setKitchenorder(data);
					remarksObjList.add(obj);
				}
				data.setKitchenorderremarksbreakdown(remarksObjList);
				
				data.setPacking(tempdata.getPacking());
				data.setSetOption(tempdata.getSetOption());
				data.setSalesorder(salesorderObj);
				salesorderbreakdownList.add(data);	
				
			}
		} 			
		
		salesorderObj.setSalesorderbreakdowns(salesorderbreakdownList);
		salesorderObj=salesOrderRepository.updateKitchenorder(salesorderObj);
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.info("Error in updateKitchenorder of SalesorderBOImpl "+ e.toString());
		throw e;
	}
	return salesorder;
	}

	@Override
	public List<QuotationModel> getOnlineOrderList(String createdby, Integer customerId, Date dateFrom, Date dateTo,
			Date deliveryTimeFrom, Date deliveryTimeTo, String status, Integer branchId) throws Exception {
		 List<QuotationModel> orderList=new ArrayList<QuotationModel>();
		try {
			
			for(Quotation data:salesOrderRepository.getOnlineOrderList(createdby,customerId,dateFrom, dateTo,deliveryTimeFrom, 
					deliveryTimeTo, status,  branchId)) {
				QuotationModel orderObj=new QuotationModel();
				orderObj.setQuotationId(data.getQuotationId());
				orderObj.setQuotationNo(data.getQuotationNo());
				orderObj.setQuotationDate(data.getQuotationDate());
				orderObj.setQuotationDueDate(data.getQuotationDueDate());
				orderObj.setDeliveryTerms(data.getDeliveryTerms());
				orderObj.setPaymentTerms(data.getPaymentTerms());
				orderObj.setRemarks(data.getRemarks());
				orderObj.setCreatedBy(data.getCreatedBy());
				orderObj.setCreatedDate(data.getCreatedDate());
				orderObj.setLastModifedDate(data.getLastModifedDate());
				orderObj.setTotalAmount(data.getTotalAmount());
				orderObj.setStatus(data.getStatus());
				orderObj.setTotalItemCount(data.getTotalItemCount());
				orderObj.setSoldQuantityCount(data.getSoldQuantityCount());
				orderObj.setBalanceQuantityCount(data.getBalanceQuantityCount());
				orderObj.setTotalTax(data.getTotalTax());
				orderObj.setAddressLocationmap(data.getAddressLocationmap());
				orderObj.setCustomerName(data.getCustomerName());
				orderObj.setDeliveryAddress(data.getDeliveryAddress());
				orderObj.setDeliveryTime(data.getDeliveryTime());
				orderObj.setLocation(data.getLocation());
				orderObj.setNoofquest(data.getNoofquest());
				orderObj.setOrdertype(data.getOrdertype());
				
				CustomerModel customerObj=new CustomerModel();
				customerObj.setCustomerId(data.getCustomer().getCustomerId());
				customerObj.setCustomerName(data.getCustomer().getCustomerName());
				customerObj.setAddress(data.getCustomer().getAddress());
				customerObj.setCity(data.getCustomer().getCity());
				customerObj.setState(data.getCustomer().getState());
				customerObj.setPostCode(data.getCustomer().getPostCode());
				customerObj.setLoyaltyCardCode(data.getCustomer().getLoyaltyCardCode());
				customerObj.setAvailablePoints(data.getCustomer().getAvailablePoints());
				customerObj.setAgeGroup(data.getCustomer().getAgeGroup());
				customerObj.setRace(data.getCustomer().getRace());
				customerObj.setGender(data.getCustomer().getGender());
				customerObj.setContactPerson(data.getCustomer().getContactPerson());
				customerObj.setPhoneNo(data.getCustomer().getPhoneNo());
				customerObj.setEmailAddress(data.getCustomer().getEmailAddress());
				
				orderObj.setCustomer(customerObj);
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				
				orderObj.setBranch(branchObj);
				
				List<QuotationbreakdownModel> orderbreakdownList = new ArrayList<QuotationbreakdownModel>();	
				for(Quotationbreakdown orderBreakdown:data.getQuotationbreakdowns()) {
					QuotationbreakdownModel breakdownObj=new QuotationbreakdownModel();
					breakdownObj.setQuotationBreakdownId(orderBreakdown.getQuotationBreakdownId());
					breakdownObj.setProductId(orderBreakdown.getProduct().getProductId());
					breakdownObj.setProductName(orderBreakdown.getProduct().getProductName());
					breakdownObj.setProductCode(orderBreakdown.getProduct().getProductCode());
					breakdownObj.setUomName(orderBreakdown.getProduct().getUom());
					breakdownObj.setQuantity(orderBreakdown.getQuantity());
					breakdownObj.setDiscount(orderBreakdown.getDiscount());
					breakdownObj.setDiscountAmount(orderBreakdown.getDiscountAmount());
					breakdownObj.setSubTotal(orderBreakdown.getSubTotal());
					breakdownObj.setUnitPrice(orderBreakdown.getUnitPrice());
					breakdownObj.setSoldQuantity(orderBreakdown.getSoldQuantity());
					breakdownObj.setBalanceQuantity(orderBreakdown.getBalanceQuantity());
					breakdownObj.setExpiryDate(orderBreakdown.getExpiryDate());
					breakdownObj.setTaxCode(orderBreakdown.getTaxCode());
					breakdownObj.setTaxAmount(orderBreakdown.getTaxAmount());
					breakdownObj.setGstCode(orderBreakdown.getGstCode());
					breakdownObj.setGstAmount(orderBreakdown.getGstAmount());
					orderbreakdownList.add(breakdownObj);
				}
				orderObj.setQuotationbreakdowns(orderbreakdownList);
				orderList.add(orderObj);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in getOnlineOrderList of SalesorderBOImpl "+ e.toString());
			throw e;
		}	
		return orderList;

	}
	
	@Transactional
	@Override
	public QuotationModel updateOnlineOrder(QuotationModel orderDetail) throws Exception {
		//QuotationModel onlineOrder=new QuotationModel();
		try {
			Quotation orderObj=salesOrderRepository.getOnlineOrderDetails(null,orderDetail.getQuotationNo());
			orderObj.setStatus(orderDetail.getStatus());
			
			orderObj=salesOrderRepository.updateOnlineOrder(orderObj);
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in updateOnlineOrder of SalesorderBOImpl "+ e.toString());
			throw e;
		}	
		return orderDetail;	
		
	}

	@Override
	public QuotationModel createNewOnlineSalesOrder(QuotationModel orderDetail) throws Exception {
		List<Salesorderbreakdown> salesorderbreakdownList = new ArrayList<Salesorderbreakdown>();
		boolean saveSuccess = false;
		
		Salesorder salesorderObj = new Salesorder();
		try {
			Autonum autoNum = commonListRepository.getAutonumDetail(orderDetail.getBranch().getBranchId());
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in createNewOnlineSalesOrder of SalesorderBOImpl "+ e.toString());
			throw e;
		}	
		return orderDetail;
	}
	

	
}
