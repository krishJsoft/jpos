package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.IProductBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.BranchsalePagingHelper;
import com.project.model.paginghelper.PosSalesSorder;
import com.project.model.paginghelper.QuotationOrderSort;

@ManagedBean(name = "branchsaleBean")
@SessionScoped
public class BranchsaleBean {

	
	BranchsaleModel branchsale = new BranchsaleModel();
	List<BranchsalesbreakdownModel> branchsalesbreakdowns = new ArrayList<BranchsalesbreakdownModel>();
	private LazyDataModel<BranchsaleModel> branchsaleModel = null;
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> supplierQty = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

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
	
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscountAmount;
	private BigDecimal grandTotalAmount;
	private BigDecimal totalTaxAmount = new BigDecimal(0.00);
	private int branchId;
	private String branchName;
	private Date dateFrom;
	private Date dateTo;
	private String action = "submit";
	private String itemaction = "submit";
	private Integer rowId;
	private ProductModel product;
	private boolean productCodedisable = false;
	List<String> productcodes = new ArrayList<String>();
	private BranchModel branch;
	private boolean customerNamedisable = false;
	List<String> customerNames = new ArrayList<String>();
	int sno=0;
	private Map<Integer, BigDecimal> priceQty = new HashMap<Integer, BigDecimal>();
	IProductBO productBO=objectMapController.getProductBO();
	IBranchsalesBO branchsalesBO=objectMapController.getBranchsalesBO();
	
		
	public IBranchsalesBO getBranchsalesBO() {
		return branchsalesBO;
	}

	public void setBranchsalesBO(IBranchsalesBO branchsalesBO) {
		this.branchsalesBO = branchsalesBO;
	}

	public boolean isCustomerNamedisable() {
		return customerNamedisable;
	}

	public void setCustomerNamedisable(boolean customerNamedisable) {
		this.customerNamedisable = customerNamedisable;
	}

	

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Map<Integer, BigDecimal> getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(Map<Integer, BigDecimal> priceQty) {
		this.priceQty = priceQty;
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
	
	public BranchsaleModel getBranchsale() {
		return branchsale;
	}

	public void setBranchsale(BranchsaleModel branchsale) {
		this.branchsale = branchsale;
	}

	public List<BranchsalesbreakdownModel> getBranchsalesbreakdowns() {
		return branchsalesbreakdowns;
	}

	public void setBranchsalesbreakdowns(
			List<BranchsalesbreakdownModel> branchsalesbreakdowns) {
		this.branchsalesbreakdowns = branchsalesbreakdowns;
	}

	public LazyDataModel<BranchsaleModel> getBranchsaleModel() {
		return branchsaleModel;
	}

	public void setBranchsaleModel(LazyDataModel<BranchsaleModel> branchsaleModel) {
		this.branchsaleModel = branchsaleModel;
	}

	

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<String> getProductcodes() {
		return productcodes;
	}

	public void setProductcodes(List<String> productcodes) {
		this.productcodes = productcodes;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public List<String> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<String> customerNames) {
		this.customerNames = customerNames;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public LazyDataModel<BranchsaleModel> getSalesList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.setBranchsaleModel(null);
			branchsaleModel = new BranchsalePagingHelper(null, branchId,dateFrom, dateTo, status, branchsalesBO,loginBean.getBranch().getBranchId());
			this.setBranchsaleModel(branchsaleModel);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return branchsaleModel;
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

	BigDecimal itemCount=new BigDecimal(1.00);
	
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

	public void searchSales() {
		try {
			if(!this.validateSalesSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			this.getSalesList();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetSalesSearch()
	{	
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());			
			this.setStatus(null);
			this.setQuotationNo(null);
			this.getSalesList();
			this.setBranchId(0);
	}

	public void newSales() {
		BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
		branchsaleBeanInfo.newSales();
		this.resetSales();
	}
	
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());			
	}
	
	public void selectCustomer(SelectEvent event)
	{
		//this.customer=((CustomerModel) event.getObject());			
	}
	

	public void addSalesItem() {
		BranchsalesbreakdownModel item = new BranchsalesbreakdownModel();
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

			for (BranchsalesbreakdownModel data : branchsalesbreakdowns) {
				if (data.getProductCode().equalsIgnoreCase(
						this.product.getBarcode())) {
					BranchsalesbreakdownModel c = branchsalesbreakdowns.get(rowCount);

					item.setProductId(producttemp.getProductId());
					item.setProductName(producttemp.getProductName());
					item.setProductCode(this.product.getBarcode());
					item.setUomName(producttemp.getUom());
					item.setExpiryDate(producttemp.getExpiryDate());
					item.setBatchNo(producttemp.getBatchNo());
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
							branchsalesbreakdowns.set(rowCount, item);	
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
				item.setBatchNo(producttemp.getBatchNo());
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
						this.getDynamicSalesItemList(item);
						sno=sno+1;
					}
				}
			}
			
			extractQuoteTotal();
			//Collections.sort(branchsalesbreakdowns, new QuotationOrderSort());
			resetAddItem();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	public void addSalesPriceModifyItem(Integer rowId) {
		BranchsalesbreakdownModel item = new BranchsalesbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		boolean exits = false;
		try {
			
			item = branchsalesbreakdowns.get(rowId);					
			List<ProductModel> productList= new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, item.getProductCode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null);
			
			if(productList.size()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
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
							branchsalesbreakdowns.set(rowId, item);								
						}
					}										
			
			extractQuoteTotal();
			//Collections.sort(branchsalesbreakdowns, new PosSalesSorder());
			resetAddItem();
			}

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	
	

	public BigDecimal extractSubtotal(BigDecimal unitPrice,
		BigDecimal discAmount, BigDecimal quant,BigDecimal taxCode) {
		BigDecimal quantityValue =quant;
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
		    
			for(BranchsalesbreakdownModel item:branchsalesbreakdowns)
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
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		
		}
		
	
	

	public void getDynamicSalesItemList(BranchsalesbreakdownModel item) {
		this.branchsalesbreakdowns.add(item);
	}

	public List<ProductModel> getProductCode(String productString) {
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllProductList(productString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
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
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void resetSalesList() {

		this.setItemCount(new BigDecimal(1.00));
		this.branchsalesbreakdowns.clear();
		branchsale = new BranchsaleModel();
		this.setBranchsale(branchsale);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
	}

	public void saveSales() {
		BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
		branchsaleBeanInfo.createNewSales();
	}
	
	public void updateSales() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
			branchsaleBeanInfo.updateSales();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void resetSales(){
		
		this.setItemCount(new BigDecimal(1.00));
		this.branchsalesbreakdowns.clear();		
		branchsale = new BranchsaleModel();
		this.setBranchsale(branchsale);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
		//customer = new CustomerModel();
		product = new ProductModel();
		//this.setCustomer(customer);
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


	public void listSales() {

		BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
		branchsaleBeanInfo.listSales();
	}

	public void removeItem(ActionEvent event) {
		String branchsalesId = "";
		branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
		this.setRowId(Integer.parseInt(branchsalesId));
	}

	public void removeItemConfirm() {
		BranchsalesbreakdownModel c = branchsalesbreakdowns.get(this.getRowId());
		branchsalesbreakdowns.remove(c);
		extractQuoteTotal();
	}

	public void viewSales(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String branchsalesId = "";
			branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
			BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
			branchsale = branchsaleBeanInfo.editSales(Integer.parseInt(branchsalesId));
			branchsalesbreakdowns = branchsale.getBranchsalesbreakdowns();
			this.setTotalAmount(branchsale.getTotalAmount());			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}

	public void editSales(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String branchsalesId = "";
			branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();		
			BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
			branchsale = branchsaleBeanInfo.editSales(Integer.parseInt(branchsalesId));	
			branchsalesbreakdowns = branchsale.getBranchsalesbreakdowns();
			this.setSno(branchsale.getSno());
			this.setTotalAmount(branchsale.getTotalAmount());			
			this.setBranch(branchsale.getBranch());		
			this.setBranchId(branchsale.getBranch().getBranchId());
			this.priceQty.clear();
			for(BranchsalesbreakdownModel item:branchsalesbreakdowns)
			{
				this.priceQty.put(item.getProductId(), item.getUnitPrice());
			}
			
			branchsaleBeanInfo.newSales();
			extractQuoteTotal();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void loadSales()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
			//branchsale = quotationBeanInfo.editQuotation(quotation.getQuotationId());	
			//branchsalesbreakdowns = branchsale.getQuotationbreakdowns();		
			//this.setTotalAmount(branchsale.getTotalAmount());			
			CustomerModel customer = new CustomerModel();			
			//customer.setCustomerId(quotation.getCustomerId());
			//customer.setCustomerName(quotation.getCustomerName());
			//this.setCustomer(customer);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	

	
	
	
	public void approveSales(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String branchsalesId = "";
		branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
		BranchsaleBeanInfo branchsaleBeanInfo = new BranchsaleBeanInfo();
		branchsale = branchsaleBeanInfo.editSales(Integer.parseInt(branchsalesId));
		branchsalesbreakdowns = branchsale.getBranchsalesbreakdowns();
		this.setTotalAmount(branchsale.getTotalAmount());			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
	}
	}
	
	
	public void approveSalesConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {						
			branchsale.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_PROCESSED_VALUE));			
			updateSuccess=branchsalesBO.approveBranchsales(branchsale);
			
			if (updateSuccess) {					
				this.resetSales();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("quotation.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public boolean validateSalesSearch() {
		boolean valid = true;		
	
		
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getBranchId()==0 && this.getDateFrom()==null && this.getDateTo()==null && this.getStatus()==null  && this.getQuotationNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	public void printSales(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String branchsalesId = "";
		branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/quotationDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&branchsalesId=" + branchsalesId+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
