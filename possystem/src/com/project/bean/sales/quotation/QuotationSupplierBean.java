package com.project.bean.sales.quotation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.bo.interfaces.IQuotationSupplierBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.purchase.PurchaseorderConsolidateModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaseorderdeliveryaddressModel;
import com.project.model.datamodel.purchase.PurchaserequestConsolidateModel;
import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.his.Autonum;
import com.project.model.his.Branch;
import com.project.model.his.Product;
import com.project.model.his.Purchaseorder;
import com.project.model.his.Purchaseorderbreakdown;
import com.project.model.his.Purchaseorderdeliveryaddress;
import com.project.model.his.Quotationsupplierdetail;
import com.project.model.his.Supplier;


import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationSupplierModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.purchase.PurchaseOrderBean;
import com.project.bean.purchase.PurchaseRequestBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.EmailProcess;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.PosSalesSorder;
import com.project.model.paginghelper.QuotationOrderSort;
import com.project.model.paginghelper.QuotationPagingHelper;
import com.project.model.paginghelper.QuotationSupplierPagingHelper;

@ManagedBean(name = "quotationsupplierBean")
@SessionScoped
public class QuotationSupplierBean {

	QuotationModel quotation = new QuotationModel();
	List<QuotationbreakdownModel> quotationbreakdowns = new ArrayList<QuotationbreakdownModel>();
	private LazyDataModel<QuotationModel> quotationModel = null;
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> supplierQty = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private Map<Integer, BigDecimal> priceQty = new HashMap<Integer, BigDecimal>();
	private Map<Integer, BigDecimal> itemQty = new HashMap<Integer, BigDecimal>();
	List<PurchaserequestConsolidateModel> branchProductList=new ArrayList<PurchaserequestConsolidateModel>();
	
	private Map<Integer, Boolean> checkMap = new HashMap<Integer, Boolean>();
	private String productCode;
	private int quotationId;
	private String createdBy;
	private Date createdDate;
	private String deliveryTerms;
	private Date lastModifedDate;
	private String paymentTerms;
	private Date quotationDate;
	private Date quotationDueDate;
	private String quotationNo;
	private BigDecimal itemCount= new BigDecimal(1.00);
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscountAmount;
	private BigDecimal grandTotalAmount;
	private BigDecimal totalTaxAmount = new BigDecimal(0.00);
	private int customerId;
	private String customerName;
	private Date dateFrom;
	private Date dateTo;
	private String action = "submit";
	private String itemaction = "submit";
	private Integer rowId;
	private ProductModel product;
	private boolean productCodedisable = false;
	List<String> productcodes = new ArrayList<String>();
	private CustomerModel customer;
	private boolean customerNamedisable = false;
	List<String> customerNames = new ArrayList<String>();
	List<ProductsupplierModel> supplierObjList = new ArrayList<ProductsupplierModel>();	
	List<ProductsupplierModel> supplierProducts = new ArrayList<ProductsupplierModel>();
	List  productIds  = new ArrayList<Integer>();
	List supplierIds = new ArrayList<Integer>();
	int sno=0;
	public SupplierModel supplierdata = new SupplierModel();
	
	IQuotationSupplierBO quotationBO=objectMapController.getQuotationsupplierBO();							
	IProductBO productBO=objectMapController.getProductBO();
	IPurchaseOrderBO purchaseOrderBO=objectMapController.getPurchaseOrderBO();
	IPurchaserequestBO  purchaseRequestBO=objectMapController.getPurchaseRequestBO();
	
	public IQuotationSupplierBO getQuotationBO() {
		return quotationBO;
	}

	public void setQuotationBO(IQuotationSupplierBO quotationBO) {
		this.quotationBO = quotationBO;
	}

	
	public QuotationModel getQuotation() {
		return quotation;
	}

	public void setQuotation(QuotationModel quotation) {
		this.quotation = quotation;
	}
		
	
	public Map<Integer, BigDecimal> getItemQty() {
		return itemQty;
	}

	public void setItemQty(Map<Integer, BigDecimal> itemQty) {
		this.itemQty = itemQty;
	}

	public Map<Integer, BigDecimal> getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(Map<Integer, BigDecimal> priceQty) {
		this.priceQty = priceQty;
	}
	
	

	public SupplierModel getSupplierdata() {
		return supplierdata;
	}

	public void setSupplierdata(SupplierModel supplierdata) {
		this.supplierdata = supplierdata;
	}

	public List getProductIds() {
		return productIds;
	}

	public void setProductIds(List productIds) {
		this.productIds = productIds;
	}

	public List getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(List supplierIds) {
		this.supplierIds = supplierIds;
	}

	public Map<Integer, Boolean> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(Map<Integer, Boolean> checkMap) {
		this.checkMap = checkMap;
	}

	public LazyDataModel<QuotationModel> getQuotationModel() {
		return quotationModel;
	}

	public void setQuotationModel(LazyDataModel<QuotationModel> quotationModel) {
		this.quotationModel = quotationModel;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public boolean isCustomerNamedisable() {
		return customerNamedisable;
	}

	public void setCustomerNamedisable(boolean customerNamedisable) {
		this.customerNamedisable = customerNamedisable;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getItemaction() {
		return itemaction;
	}

	public void setItemaction(String itemaction) {
		this.itemaction = itemaction;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
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

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getProductCode() {
		return productCode;
	}	

	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	

	public List<ProductsupplierModel> getSupplierObjList() {
		return supplierObjList;
	}

	public void setSupplierObjList(List<ProductsupplierModel> supplierObjList) {
		this.supplierObjList = supplierObjList;
	}
	

	public List<PurchaserequestConsolidateModel> getBranchProductList() {
		return branchProductList;
	}

	public void setBranchProductList(
			List<PurchaserequestConsolidateModel> branchProductList) {
		this.branchProductList = branchProductList;
	}

	public LazyDataModel<QuotationModel> getquotationList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.setQuotationModel(null);
			quotationModel = new QuotationSupplierPagingHelper(quotationNo, null,	dateFrom, dateTo, status, quotationBO,loginBean.getBranch().getBranchId());
			this.setQuotationModel(quotationModel);
			// this.checkMap.clear();
		} catch (Exception e) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"productPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return quotationModel;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdowns() {
		return quotationbreakdowns;
	}

	public void setQuotationbreakdowns(
			List<QuotationbreakdownModel> quotationbreakdowns) {
		this.quotationbreakdowns = quotationbreakdowns;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdownModel() {
		return quotationbreakdowns;
	}

	public void setQuotationbreakdownModel(
			List<QuotationbreakdownModel> quotationbreakdownModel) {
		this.quotationbreakdowns = quotationbreakdownModel;
	}

	public CommonFactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(CommonFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	public List<SelectItem> getSupplierQty() {
		return supplierQty;
	}

	public void setSupplierQty(List<SelectItem> supplierQty) {
		this.supplierQty = supplierQty;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Date getQuotationDueDate() {
		return quotationDueDate;
	}

	public void setQuotationDueDate(Date quotationDueDate) {
		this.quotationDueDate = quotationDueDate;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public BigDecimal getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isProductCodedisable() {
		return productCodedisable;
	}

	public void setProductCodedisable(boolean productCodedisable) {
		this.productCodedisable = productCodedisable;
	}	

	public List<ProductsupplierModel> getSupplierProducts() {
		return supplierProducts;
	}

	public void setSupplierProducts(List<ProductsupplierModel> supplierProducts) {
		this.supplierProducts = supplierProducts;
	}

	public void searchQuotation() {
		try {
			if(!this.validateQuotationSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			this.getquotationList();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetQuotationSearch()
	{	
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());			
			this.setStatus(null);
			this.setQuotationNo(null);
			this.getquotationList();
			this.setCustomerId(0);
	}

	public void newQuotation() {
		QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
		quotationBeanInfo.newQuotation();
		this.resetQuotation();
	}
	
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());			
	}
	
	public void selectCustomer(SelectEvent event)
	{
		this.customer=((CustomerModel) event.getObject());			
	}
	

	public void addQuotationItem() {
		QuotationbreakdownModel item = new QuotationbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {
			producttemp = productBO.getProductList(null, null, null, this.product.getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}

			for (QuotationbreakdownModel data : quotationbreakdowns) {
				if (data.getProductCode().equalsIgnoreCase(
						this.product.getBarcode())) {
					QuotationbreakdownModel c = quotationbreakdowns.get(rowCount);

					item.setProductId(producttemp.getProductId());
					item.setProductName(producttemp.getProductName());
					item.setProductCode(this.product.getBarcode());
					item.setUomName(producttemp.getUom());
					item.setExpiryDate(producttemp.getExpiryDate());
					item.setUnitPrice(producttemp.getNormalPrice());
					this.priceQty.put(producttemp.getProductId(),producttemp.getNormalPrice());
					item.setDiscount(""+producttemp.getDiscount());
					item.setQuantityRequired(this.getItemCount().add(c.getQuantityRequired()));
					item.setBalanceQuantity(this.getItemCount());
					
					item.setTaxCode(producttemp.getTaxCode());					
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
					BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					item.setTaxAmount(taxAmt);
					
					item.setSno(sno);
					
					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
								if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue()	&& item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
									item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(),item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
							item.setSubTotal(tmpSubTotal);
							//this.getDynamicQuotationItemList(item);
							quotationbreakdowns.set(rowCount, item);	
							sno=sno+1;
						}
					}
					//quotationbreakdowns.remove(c);
					datamatch = true;
				}
				rowCount++;
			}
			if (!datamatch) {
				item.setProductId(producttemp.getProductId());
				item.setProductName(producttemp.getProductName());
				item.setProductCode(this.product.getBarcode());
				item.setUomName(producttemp.getUom());
				item.setExpiryDate(producttemp.getExpiryDate());
				item.setUnitPrice(producttemp.getNormalPrice());
				this.priceQty.put(producttemp.getProductId(),producttemp.getNormalPrice());
				item.setDiscount(""+producttemp.getDiscount());
				item.setQuantityRequired(this.getItemCount());
				item.setBalanceQuantity(this.getItemCount());
				
				item.setTaxCode(producttemp.getTaxCode());				
				BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
				BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				item.setTaxAmount(taxAmt);				
				
				item.setSno(sno);
				
				for (int i = 0; i < productPriceList.size(); i++) {
					if (!exits) {
						for (int j = i + 1; j < productPriceList.size(); j++) {
							BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
							BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
							if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue() && item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
								item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
								exits = true;
								break;
							} else {
								item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
								i++;
							}
						}
						BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(), item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
						item.setSubTotal(tmpSubTotal);
						this.getDynamicQuotationItemList(item);
						sno=sno+1;
					}
				}
			}
			
			extractQuoteTotal();
			Collections.sort(quotationbreakdowns, new QuotationOrderSort());
			resetAddItem();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	
	
	public void addSalesPriceModifyItem(Integer rowId) {
		QuotationbreakdownModel item = new QuotationbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		boolean exits = false;
		try {
			
			item = quotationbreakdowns.get(rowId);					
			List<ProductModel> productList= new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, item.getProductCode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null);
			
			if(productList.size()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null,"Barcode not Exists"));
			}
			else
			{
			producttemp=productList.get(0);
			productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
						
			if (ValidatorUtil.isNotNull(this.getPriceQty().get(item.getProductId()))) 
			{			
				item.setUnitPrice(this.getPriceQty().get(item.getProductId()));
			}
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}					
					
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
					BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					item.setTaxAmount(taxAmt);					
					
					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
								if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue()	&& item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
									item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(),item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
							item.setSubTotal(tmpSubTotal);
							quotationbreakdowns.set(rowId, item);								
						}
					}										
			
			extractQuoteTotal();
			//Collections.sort(branchsalesbreakdowns, new PosSalesSorder());
			resetAddItem();
			}

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	
	

	public BigDecimal extractSubtotal(BigDecimal unitPrice,
		BigDecimal discAmount, BigDecimal quant,BigDecimal taxCode) {
		BigDecimal quantityValue = quant;
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);
		
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}
	
	public void extractQuoteTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		    BigDecimal totalAmount =new BigDecimal(0.00);
		    BigDecimal totalDiscountAmount =new BigDecimal(0.00);
		    BigDecimal grandTotalAmount =new BigDecimal(0.00);
		    BigDecimal totalTaxAmount =new BigDecimal(0.00);
		    
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				totalAmount=totalAmount.add(item.getSubTotal());
				totalDiscountAmount=totalDiscountAmount.add(item.getDiscountAmount());
				totalTaxAmount=totalTaxAmount.add(item.getTaxAmount());
			}
			this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));
			this.setTotalDiscountAmount(totalDiscountAmount);	
			this.setTotalTaxAmount(totalTaxAmount);			
			//grandTotalAmount=(totalAmount.add(totalTaxAmount)).subtract(totalDiscountAmount);
			grandTotalAmount=(totalAmount.add(totalTaxAmount));
			this.setGrandTotalAmount(grandTotalAmount);	
		}
		catch(Exception e)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		
		}
		
	
	

	public void getDynamicQuotationItemList(QuotationbreakdownModel item) {
		this.quotationbreakdowns.add(item);
	}

	public List<ProductModel> getProductCode(String productString) {
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllProductList(productString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void resetQuotationList() {

		this.setItemCount(new BigDecimal(1));
		this.quotationbreakdowns.clear();
		quotation = new QuotationModel();
		this.setQuotation(quotation);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
	}

	public void saveQuotation() {
		QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
		quotationBeanInfo.createNewQuotation();
	}
	
	public void updateQuotation() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			QuotationSupplierBeanInfo  quotationBeanInfo = new QuotationSupplierBeanInfo();
			quotationBeanInfo.updateQuotation();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void resetQuotation(){
		
		this.setItemCount(new BigDecimal(1.00));
		this.quotationbreakdowns.clear();		
		quotation = new QuotationModel();
		this.setQuotation(quotation);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
		customer = new CustomerModel();
		product = new ProductModel();
		this.setCustomer(customer);
		this.setProduct(product);
		this.setTotalAmount(new BigDecimal("0.00"));		
		this.setTotalDiscountAmount(new BigDecimal("0.00"));			
		this.setGrandTotalAmount(new BigDecimal("0.00"));	
		this.priceQty.clear();
	}
	
	public void resetAddItem()
	{
		this.setItemCount(new BigDecimal(1.00));
		this.product = new ProductModel();
	}


	public void listQuotation() {

		QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
		quotationBeanInfo.listQuotation();
	}

	public void removeItem(ActionEvent event) {
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes().get("quotationBreakdownId").toString();
		this.setRowId(Integer.parseInt(quotationBreakdownId));
	}

	public void removeItemConfirm() {
		QuotationbreakdownModel c = quotationbreakdowns.get(this.getRowId());
		quotationbreakdowns.remove(c);
		extractQuoteTotal();
	}

	public void viewQuotation(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String quotationId = "";
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			this.setTotalAmount(quotation.getTotalAmount());			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}

	public void editQuotation(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String quotationId = "";
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();		
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));	
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			this.setSno(quotation.getSno());
			this.setTotalAmount(quotation.getTotalAmount());
			
			CustomerModel customer = new CustomerModel();			
			customer.setCustomerId(quotation.getCustomerId());
			customer.setCustomerName(quotation.getCustomerName());
			this.setCustomer(customer);
			
			this.priceQty.clear();
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				this.priceQty.put(item.getProductId(), item.getUnitPrice());
			}
			
			
			quotationBeanInfo.newQuotation();
			extractQuoteTotal();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void viewSuppliers(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String quotationId = "";
			supplierProducts.clear();
			List productIds = new ArrayList<Integer>();
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();		
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));	
			quotationbreakdowns = quotation.getQuotationbreakdowns();			
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				productIds.add(item.getProductId());
			}
			supplierObjList=quotationBO.getQuotationBySupplierList(productIds,null);			
			quotationBeanInfo.quotationSuppliers();
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void viewSupplierProducts(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		supplierProducts.clear();
		try {
			String quotationId = "";
			productIds.clear();
			supplierIds.clear();
			
			if(validateSupplier()){
								
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				productIds.add(item.getProductId());
			}
			

				for(ProductsupplierModel data:supplierObjList)
					{				
				for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
					if (entry.getValue()) {  
						if(entry.getKey().equals(data.getSupplier().getSupplierId())){
							supplierIds.add(data.getSupplier().getSupplierId());							
							break;
						}
					}
				}
				}
		
			if(supplierIds.size()!=0 && productIds.size()!=0  )
			{
			supplierProducts=quotationBO.getProductBySupplierList(quotationbreakdowns,productIds,supplierIds);			
			}
			}
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	public void saveSupplierQuotation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			for(ProductsupplierModel qdata:supplierProducts)
			{			
				QuotationModel quotationTemp = new QuotationModel();					
				
				List<QuotationbreakdownModel> quotationbreakdowns = new ArrayList<QuotationbreakdownModel>();
				for(QuotationbreakdownModel tempdata :qdata.getSupplier().getQuotationbreakdownList())
				{
				
				QuotationbreakdownModel data= new QuotationbreakdownModel();
				
				data.setDiscountAmount(new BigDecimal("0.00"));					
				data.setProductId(tempdata.getProductId());				
				
				data.setQuantity(tempdata.getQuantityRequired());			
				data.setSubTotal(tempdata.getSubTotal());
				data.setUnitPrice(tempdata.getUnitPrice());		
				data.setBalanceQuantity(tempdata.getQuantityRequired());
				data.setSoldQuantity(new BigDecimal("0.00"));
				data.setExpiryDate(tempdata.getExpiryDate());	
				data.setQuantityRequired(tempdata.getQuantityRequired());
				data.setTaxAmount(tempdata.getTaxAmount());
				data.setTaxCode(tempdata.getTaxCode());
				
				quotationbreakdowns.add(data);
				}
				
				quotationTemp=this.getQuotation();
				quotationTemp.setBranchRecordId(loginBean.getBranch().getBranchId());
				quotationTemp.setSupplier(qdata.getSupplier());
				quotationTemp.setQuotationbreakdowns(quotationbreakdowns);
				
				quotationTemp=quotationBO.createNewQuotationsupplierdetail(quotationTemp);
				
			}
			
			sendQuotationEmail();
			listQuotation();
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));			
			}
	}
	
	
	public void sendQuotationEmail()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			
			quotation = quotationBO.getQuotationSupplierDetails(quotation.getQuotationId(),null);	
			quotationbreakdowns = quotation.getQuotationbreakdowns();	
			
			for(SupplierModel supplier:quotation.getSupplierList())
			{							
				QuotationModel quotationTemp = new QuotationModel();
				quotationTemp.setQuotationId(supplier.getQuotationsupplier().getQuotationId());
				quotationTemp.setQuotationNo(supplier.getQuotationsupplier().getQuotationNo());
				
				quotationTemp.setSupplier(supplier);				
				
				EmailProcess email = new EmailProcess();
				Map<String, Object> reportParameters = new HashMap<String, Object>();
				
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
				
				String urlPath=request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME);
				
				reportParameters.put("quotationId", supplier.getQuotationsupplier().getQuotationId());
				reportParameters.put("userId", loginBean.getUserId());
				String reportPath="report/sales/quotationSupplierDetailReport.rptdesign";
				 
				email.emailQuotation(urlPath, quotationTemp,reportParameters,reportPath);
				
			}
			
			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	
	public void createPurchaseOrder(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();				
			List productIds = new ArrayList<Integer>();
			resetSupplierQuotationDetail();
			List<Integer> requestIds = new ArrayList<Integer>();
			supplierdata = (SupplierModel) event.getComponent().getAttributes().get("supplier");		
			itemQty.clear();
			checkMap.clear();
			
			String asseltClasses = quotation.getRequestIds();			
			if(asseltClasses!=null && (!asseltClasses.equalsIgnoreCase("")))
					{
			String[] splits = asseltClasses.split(",");
			for(String asset: splits){
				requestIds.add(Integer.parseInt(asset));			
			}
			}		
			
			
		    branchProductList=purchaseRequestBO.getSupplierQuotationBranchAllocation(requestIds, quotation);
						
		    for(PurchaserequestConsolidateModel data:branchProductList)
			{				
				for(PurchaserequestbreakdownModel productData:data.getBranchList().iterator().next().getProductList())
				{						
					for(QuotationbreakdownModel qdata:supplierdata.getQuotationbreakdownList())
					{
						if(qdata.getProductId()==productData.getProductId())
						{
						this.itemQty.put(productData.getSno(), new BigDecimal(productData.getQuantityRequired()));
						this.checkMap.put(productData.getSno(),true);
						
						BigDecimal priceAmt = qdata.getUnitPrice().multiply(new BigDecimal(productData.getQuantityRequired()));	
						BigDecimal taxAmt = qdata.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));	
						productData.setUnitPrice(qdata.getUnitPrice());
				
						productData.setTaxCode(qdata.getTaxCode());
						productData.setTaxAmount(taxAmt);
						productData.setSubTotal(priceAmt.add(taxAmt));	
						
						break;
						}
					}			
				}
			}		    
		
		    extractQuotePurchaseTotal();  
		  
		    
		    
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	public void resetSupplierQuotationDetail()
	{
		FacesContext context = FacesContext.getCurrentInstance();
	
	try {
		quotation = quotationBO.getQuotationSupplierDetails(quotationId,null);	
		quotationbreakdowns = quotation.getQuotationbreakdowns();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	public void addPurchasePriceModifyItem(Integer rowId) {
		QuotationbreakdownModel item = new QuotationbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		boolean exits = false;
		try {
			
			item = supplierdata.getQuotationbreakdownList().get(rowId);		
				
			if (ValidatorUtil.isNotNull(this.getItemQty().get(item.getProductId()))) 
			{			
				item.setQuantityRequired(this.getItemQty().get(item.getProductId()));
			}	
						
				if(this.getCheckMap().get(item.getProductId()))
				{
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
					BigDecimal taxAmt= item.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					item.setTaxAmount(taxAmt);	
					item.setTaxCode(item.getTaxCode());
					item.setDiscountAmount(new BigDecimal(0.00));
					BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(),item.getDiscountAmount(),item.getQuantityRequired(),item.getTaxCode());
					item.setSubTotal(tmpSubTotal);					
					//extractQuoteTotal();			
					supplierdata.getQuotationbreakdownList().set(rowId, item);					
				}
				extractQuotePurchaseTotal();
					
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	public void extractQuotePurchaseTotal(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		    BigDecimal totalAmount =new BigDecimal(0.00);
		    BigDecimal totalDiscountAmount =new BigDecimal(0.00);
		    BigDecimal grandTotalAmount =new BigDecimal(0.00);
		    BigDecimal totalTaxAmount =new BigDecimal(0.00);
		    
			
				 for(PurchaserequestConsolidateModel data:branchProductList)
					{				
						for(PurchaserequestbreakdownModel productData:data.getBranchList().iterator().next().getProductList())
						{				
							
				for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
					if (entry.getValue()) {  
						if(entry.getKey().equals(productData.getSno())){
							totalAmount=totalAmount.add(productData.getSubTotal());
							if (ValidatorUtil.isNotNull(productData.getDiscountAmount())) 
							{
							totalDiscountAmount=totalDiscountAmount.add(productData.getDiscountAmount());	
							}							
							totalTaxAmount=totalTaxAmount.add(productData.getTaxAmount());							
							break;
						}
					}
				}
				}
				
			}
			this.setTotalAmount(totalAmount);
			this.setTotalDiscountAmount(totalDiscountAmount);	
			this.setTotalTaxAmount(totalTaxAmount);			
			grandTotalAmount=(totalAmount.add(totalTaxAmount));
			this.setGrandTotalAmount(DecimalUtil.formatRoundupCents(grandTotalAmount));	
		}
		catch(Exception e)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		
	}
	
	
	
	public void savePO()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		List<PurchaseorderbreakdownsModel> purchaseorderbreakdownList = new ArrayList<PurchaseorderbreakdownsModel>();	
		List<PurchaseorderdeliveryaddressModel> purchaseorderdeliveryaddresses = new ArrayList<PurchaseorderdeliveryaddressModel>();
		boolean saveSuccess = false;
		PurchaseorderModel purchaseorderObj =null;		
		PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
		
	try {		
		
		purchaseorderObj = new PurchaseorderModel();	
		int receivedQuantityCount=0;		
		int totalItemCount=0;
		BigDecimal totalTax = new BigDecimal("0.00");	
		purchaseorderObj.setSupplierId(supplierdata.getSupplierId());			
		purchaseorderObj.setSupplier(supplierdata);
		
		purchaseorderObj.setCreatedDate(DateUtil.getTodayDate());		
		purchaseorderObj.setLastModifiedDate(DateUtil.getTodayDate());		
		purchaseorderObj.setPurchaseOrderDate(DateUtil.getTodayDate());			
		purchaseorderObj.setTotalAmount(this.getGrandTotalAmount());		

		purchaseorderObj.setPurchaseOrderDate(DateUtil.getTodayDate());
		purchaseorderObj.setRemarks(quotation.getRemarks());
		purchaseorderObj.setTermsConditions(quotation.getDeliveryTerms());
		purchaseorderObj.setPaymentTerms(quotation.getPaymentTerms());

		purchaseorderObj.setBranchRecordId(loginBean.getBranch().getBranchId());
		purchaseorderObj.setStatus("2");				
		purchaseorderbreakdownList = new ArrayList<PurchaseorderbreakdownsModel>();		
		

		 for(PurchaserequestConsolidateModel data:branchProductList)
			{				
				for(PurchaserequestbreakdownModel productData:data.getBranchList().iterator().next().getProductList())
				{				
					
		for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
			if (entry.getValue()) {  
				if(entry.getKey().equals(productData.getSno())){
					PurchaseorderbreakdownsModel qdata=new PurchaseorderbreakdownsModel();			
					BigDecimal qty = new BigDecimal("0.00");
					qdata.setProductId(productData.getProductId());						
					qdata.setBranchId(data.getBranchList().iterator().next().getBranchId());		
					
					if (ValidatorUtil.isNotNull(this.getItemQty().get(productData.getSno())))
					{
						 qty=this.getItemQty().get(productData.getSno());	
					}						
					qdata.setQuantity(qty.intValue());			
					qdata.setSubTotal(productData.getSubTotal());
					qdata.setUnitPrice(productData.getUnitPrice());	
					qdata.setBalanceQuantity(qty.intValue());
					qdata.setReceivedQuantity(0);						
					qdata.setTaxAmount(productData.getTaxAmount());
					qdata.setTaxCode(productData.getTaxCode());
								
					purchaseorderbreakdownList.add(qdata);						
											
					break;
				}
			}
		}
		}
		
				
				//Add Delivery Address	
				PurchaseorderdeliveryaddressModel addressdata=new PurchaseorderdeliveryaddressModel();	
				
				addressdata.setBranchId(data.getBranchList().iterator().next().getBranchId());	
				addressdata.setDeliveryAddress(data.getBranchList().iterator().next().getAddress());	
				purchaseorderdeliveryaddresses.add(addressdata);
				
			
				
				
	}
		 
		 
		
		/*for(QuotationbreakdownModel item:supplierdata.getQuotationbreakdownList())
		{
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(item.getProductId())){
							
						PurchaseorderbreakdownsModel data=new PurchaseorderbreakdownsModel();			
								
						data.setProductId(item.getProductId());						
						data.setBranchId(loginBean.getBranch().getBranchId());					
						data.setQuantity(item.getQuantityRequired().intValue());			
						data.setSubTotal(item.getSubTotal());
						data.setUnitPrice(item.getUnitPrice());	
						data.setBalanceQuantity(item.getQuantityRequired().intValue());
						data.setReceivedQuantity(0);						
						data.setTaxAmount(item.getTaxAmount());
						data.setTaxCode(item.getTaxCode());
									
						purchaseorderbreakdownList.add(data);							
						break;
					}
				}
			}			
		}*/
		
			
		purchaseorderObj.setPurchaseorderbreakdowns(purchaseorderbreakdownList);
		purchaseorderObj.setPurchaseorderdeliveryaddresses(purchaseorderdeliveryaddresses);				
		
		purchaseorderObj.setTotalTax(this.getTotalTaxAmount());		
		purchaseorderObj=purchaseOrderBO.createQuotationPurchaseorder(purchaseorderObj);
		
		quotation = quotationBO.getQuotationsupplierdetailDetails(supplierdata.getQuotationsupplier().getQuotationId());
		quotation.setStatus("3");
		quotationBO.updateQuotationsupplierdetail(quotation);
		
		quotation = quotationBO.getQuotationSupplierDetails(this.quotationId,null);	
		quotationbreakdowns = quotation.getQuotationbreakdowns();
		
		purchaseOrderBean.sendPOEmail(purchaseorderObj); // Email Attachaed
		
		
	}
	catch(Exception e)
	{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
	}
	
	
	}
	
	
	
	
	public void quotationSupplierComparision(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			String quotationId = "";
			supplierProducts.clear();
			
			List productIds = new ArrayList<Integer>();
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
			this.setQuotationId(Integer.parseInt(quotationId));
			quotation = quotationBO.getQuotationSupplierDetails(Integer.parseInt(quotationId),null);	
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			
			quotationBeanInfo.quotationSuppliersComparision();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	public void quotationSupplierComparision1(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			String quotationId = "";
			supplierProducts.clear();
			
			List productIds = new ArrayList<Integer>();
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
			this.setQuotationId(Integer.parseInt(quotationId));
			quotation = quotationBO.getQuotationSupplierDetails(Integer.parseInt(quotationId),null);	
			quotationbreakdowns = quotation.getQuotationbreakdowns();		
			quotationBeanInfo.quotationSuppliersComparision1();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	

	
	public void declineSupplierQuotationConfirm(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {					
			supplierdata = (SupplierModel) event.getComponent().getAttributes().get("supplier");		
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	public void declineSupplierQuotation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {						
			quotation = quotationBO.getQuotationsupplierdetailDetails(supplierdata.getQuotationsupplier().getQuotationId());
			quotation.setStatus("4");
			quotation.setRemarks(remarks);
			quotationBO.updateQuotationsupplierdetail(quotation);
			
			quotation = quotationBO.getQuotationSupplierDetails(this.quotationId,null);	
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	


	
	public void loadQuotation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
			quotation = quotationBeanInfo.editQuotation(quotation.getQuotationId());	
			quotationbreakdowns = quotation.getQuotationbreakdowns();		
			this.setTotalAmount(quotation.getTotalAmount());			
			CustomerModel customer = new CustomerModel();			
			customer.setCustomerId(quotation.getCustomerId());
			customer.setCustomerName(quotation.getCustomerName());
			this.setCustomer(customer);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	

	
	
	
	public void approveQuotation(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String quotationId = "";
		quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
		QuotationSupplierBeanInfo quotationBeanInfo = new QuotationSupplierBeanInfo();
		quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));
		quotationbreakdowns = quotation.getQuotationbreakdowns();
		this.setTotalAmount(quotation.getTotalAmount());			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
	}
	}
	
	
	public void approveQuotationConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {						
			quotation.setStatus(config.getValue(IConfiguration.QUOTATION_STATUS_PROCESSED_VALUE));			
			updateSuccess=quotationBO.approveQuotation(quotation);
			
			if (updateSuccess) {					
				this.resetQuotation();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("quotation.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public boolean validateQuotationSearch() {
		boolean valid = true;		
	
		if(this.getQuotationNo()!=null)
		{
			if(this.getQuotationNo().equalsIgnoreCase(""))
			{
				this.setQuotationNo(null);
			}
		}		
		
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getDateFrom()==null && this.getDateTo()==null && this.getStatus()==null  && this.getQuotationNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	public void printQuotation(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String quotationId = "";
		quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/quotationDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&quotationId=" + quotationId+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public boolean validateSupplier() {
		boolean valid = false;		
		
		String messageValue=null;
		FacesContext context = FacesContext.getCurrentInstance();	

		try
		{			
		if (checkMap==null && checkMap.isEmpty()) {								
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose the records", null));
			valid = false;
		}	
		else
		{
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
						valid=true;
						break;
					}
				else
				{
					valid=false;
				}					
					}
					if(!valid)
					{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose the records", null));
					valid = false;
					}
					
		}
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}			
		return valid;

	}

	
	

	
}
