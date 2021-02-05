package com.project.bean.redemption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.ICommissionBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.ILoyaltyredemptionBO;
import com.project.bo.interfaces.IProductBO;
import com.project.model.commission.CommissionModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.redemption.LoyaltyredemptionModel;
import com.project.model.redemption.LoyaltyredemptionbreakdownModel;
import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.commission.DoctorCommissionBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;
import com.project.model.paginghelper.LayaltyRedemSort;
import com.project.model.paginghelper.LoyaltyredemptionBeanPagingHelper;
import com.project.model.paginghelper.PosSalesSorder;


@ManagedBean(name = "loyaltyredemptionBean")
@SessionScoped
public class LoyaltyredemptionBean {

	private String status;
	private Date dateFrom;
	private Date dateTo;
	private Integer customerId;
	protected int first;
	int activeIndex = 0;
	String loyaltyCode;
	int sno=0;
	private Integer rowId;
	
	String supplieraction="submit";
	LoyaltyredemptionbreakdownModel loyality = new LoyaltyredemptionbreakdownModel();
	List<LoyaltyredemptionbreakdownModel> layaltybreakdowns = new ArrayList<LoyaltyredemptionbreakdownModel>();
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> statuses = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<LoyaltyredemptionModel> loyaltyredemptionModel = null;
	LoyaltyredemptionModel loyaltyredemption = new LoyaltyredemptionModel();

	ILoyaltyredemptionBO redemptionBO = objectMapController.getRedemptionBO();
	IProductBO productBO=objectMapController.getProductBO();
	ICustomerBO customerBo = objectMapController.getCustomerBO();
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}	
	
	public String getLoyaltyCode() {
		return loyaltyCode;
	}

	public void setLoyaltyCode(String loyaltyCode) {
		this.loyaltyCode = loyaltyCode;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public ObjectMapController getObjectMapController() {
		return objectMapController;
	}

	public void setObjectMapController(ObjectMapController objectMapController) {
		this.objectMapController = objectMapController;
	}

	public CommonFactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(CommonFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	public List<SelectItem> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<SelectItem> statuses) {
		this.statuses = statuses;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public LazyDataModel<LoyaltyredemptionModel> getLoyaltyredemptionModel() {
		return loyaltyredemptionModel;
	}

	public void setLoyaltyredemptionModel(
			LazyDataModel<LoyaltyredemptionModel> loyaltyredemptionModel) {
		this.loyaltyredemptionModel = loyaltyredemptionModel;
	}

	public LoyaltyredemptionModel getLoyaltyredemption() {
		return loyaltyredemption;
	}

	public void setLoyaltyredemption(LoyaltyredemptionModel loyaltyredemption) {
		this.loyaltyredemption = loyaltyredemption;
	}

	public ILoyaltyredemptionBO getRedemptionBO() {
		return redemptionBO;
	}

	public void setRedemptionBO(ILoyaltyredemptionBO redemptionBO) {
		this.redemptionBO = redemptionBO;
	}	
	
	
	public LoyaltyredemptionbreakdownModel getLoyality() {
		return loyality;
	}

	public void setLoyality(LoyaltyredemptionbreakdownModel loyality) {
		this.loyality = loyality;
	}	
	

	public List<LoyaltyredemptionbreakdownModel> getLayaltybreakdowns() {
		return layaltybreakdowns;
	}

	public void setLayaltybreakdowns(
			List<LoyaltyredemptionbreakdownModel> layaltybreakdowns) {
		this.layaltybreakdowns = layaltybreakdowns;
	}	

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}	

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getSupplieraction() {
		return supplieraction;
	}

	public void setSupplieraction(String supplieraction) {
		this.supplieraction = supplieraction;
	}

	public LazyDataModel<LoyaltyredemptionModel> getRedemptionList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		this.setLoyaltyredemptionModel(null);	
		loyaltyredemptionModel = new LoyaltyredemptionBeanPagingHelper(dateFrom, dateTo, status, customerId, loyaltyCode, redemptionBO,loginBean.getBranch().getBranchId());		
		this.setLoyaltyredemptionModel(loyaltyredemptionModel);	
		}
		catch(Exception e){
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"redemPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return loyaltyredemptionModel;
	}
	
	
	public void searchRedemption()
	{		
		try
		{   if(!this.validateRedemptionSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getRedemptionList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetRedemptionSearch()
	{
		this.setCustomerId(0);		
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);	
		this.setLoyaltyCode(null);
		searchRedemption();
	}
	
	
	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"redemPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void handleSelect(SelectEvent event) {  
		try
		{
			this.getLoyaltyredemption().setCustomer(customerBo.getCustomerDetails(this.getLoyaltyredemption().getCustomer().getCustomerId()));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void getCustomerDetail()
	{
		try
		{
			this.getLoyaltyredemption().setCustomer(customerBo.getCustomerDetails(this.getLoyaltyredemption().getCustomer().getCustomerId()));
		}
		catch(Exception e)
		{
			
		}

	}
	
	
	
	public void productSelect(SelectEvent event) {  
		try
		{		
		ProductModel productModel =productBO.getProductDetailsByBarcode(this.loyality.getProduct().getBarcode(),loginBean.getBranch().getBranchId());
		this.loyality.setProduct(productModel);		
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public void listRedemption() {
		
		LoyaltyredemptionBeanInfo linfo = new LoyaltyredemptionBeanInfo();
		linfo.listRedemption();
	}

	
	
	public void newRedemption() {
		LoyaltyredemptionBeanInfo linfo = new LoyaltyredemptionBeanInfo();
		linfo.newRedemption();
		
	}
	
	
	public void addSalesItem() {
		LoyaltyredemptionbreakdownModel item = new LoyaltyredemptionbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		boolean exits = false;
		try {
			producttemp = productBO.getProductDetailsByBarcode(this.loyality.getProduct().getBarcode(),loginBean.getBranch().getBranchId());
			productPriceList = productBO.getSortedProductpriceByBarcode(this.loyality.getProduct().getBarcode(),loginBean.getBranch().getBranchId());
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}

			for (LoyaltyredemptionbreakdownModel data : layaltybreakdowns) {
				if (data.getProduct().getBarcode().equalsIgnoreCase(this.loyality.getProduct().getBarcode())) {
					LoyaltyredemptionbreakdownModel c = layaltybreakdowns.get(rowCount);
					
					item.setProduct(producttemp);					
					item.setQuantity(this.loyality.getQuantity().add(c.getQuantity()));
					item.setRedemAmount(producttemp.getRedemAmount());
					item.setRedemType(this.loyality.getRedemType());
					if(this.loyality.getRedemType().equalsIgnoreCase("1"))
					{
						item.setRedemAmount(producttemp.getNormalPrice());
						item.setRedemPoint(Integer.parseInt(""+producttemp.getFullredemptionpoint()*item.getQuantity().doubleValue()));
					}
					if(this.loyality.getRedemType().equalsIgnoreCase("2"))
					{
						item.setRedemAmount((producttemp.getNormalPrice().divide(new BigDecimal(2))));
						item.setRedemPoint(Integer.parseInt(""+((producttemp.getFullredemptionpoint()*item.getQuantity().doubleValue())/2)));
					}					
					item.setSno(sno);
					
					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
								if (this.loyality.getQuantity().doubleValue() >= initVal.doubleValue()	&& this.loyality.getQuantity().doubleValue() < nextVal.doubleValue()) {
									item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());									
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());									
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(item.getRedemAmount(),item.getDiscountAmount(),item.getQuantity());
							item.setSubtotalAmount(tmpSubTotal);
							layaltybreakdowns.set(rowCount, item);	
							
							sno=sno+1;
						}
					}					
					datamatch = true;
				}
				rowCount++;
				
			}
			if (!datamatch) {
				item.setProduct(producttemp);					
				
				item.setRedemAmount(producttemp.getRedemAmount());
				item.setRedemType(this.loyality.getRedemType());			
				item.setQuantity(this.loyality.getQuantity());
				if(this.loyality.getRedemType().equalsIgnoreCase("1"))
				{
					item.setRedemAmount(producttemp.getNormalPrice());
					item.setRedemPoint(Integer.parseInt(""+producttemp.getFullredemptionpoint()*item.getQuantity().doubleValue()));
				}
				if(this.loyality.getRedemType().equalsIgnoreCase("2"))
				{
					item.setRedemAmount((producttemp.getNormalPrice().divide(new BigDecimal(2))));
					item.setRedemPoint(Integer.parseInt(""+((producttemp.getFullredemptionpoint()*item.getQuantity().doubleValue())/2)));
				}		
				item.setSno(sno);
				
				for (int i = 0; i < productPriceList.size(); i++) {
					if (!exits) {
						for (int j = i + 1; j < productPriceList.size(); j++) {
							BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
							BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
							if (this.loyality.getQuantity().doubleValue() >= initVal.doubleValue()	&& this.loyality.getQuantity().doubleValue() < nextVal.doubleValue()) {
								item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());								
								exits = true;
								break;
							} else {
								item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());								
								i++;
							}
						}
						BigDecimal tmpSubTotal = extractSubtotal(item.getRedemAmount(),item.getDiscountAmount() ,item.getQuantity());
						item.setSubtotalAmount(tmpSubTotal);
						this.getDynamicSalesItemList(item);
						sno=sno+1;
						
					}
				}
			}
			
			extractQuoteTotal();
			Collections.sort(layaltybreakdowns, new LayaltyRedemSort());
			resetAddItem();

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"lredemPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	public void resetAddItem()
	{
		this.setLoyality(new LoyaltyredemptionbreakdownModel());
		this.setSupplieraction("submit");
	}
	
	
	public void resetLoyalty()
	{
		this.setLoyality(new LoyaltyredemptionbreakdownModel());
		this.setSupplieraction("submit");
		layaltybreakdowns.clear();
		loyaltyredemption = new LoyaltyredemptionModel();
	}
	
	public void editLoyaltyItem(ActionEvent event)
	{
		String loyalty = "";
		loyalty = (String) event.getComponent().getAttributes().get("loyalty").toString();		
		this.setRowId(Integer.parseInt(loyalty));
		LoyaltyredemptionbreakdownModel c = layaltybreakdowns.get(this.getRowId());
		this.setLoyality(c);		
		this.setSupplieraction("update");		
	}
	
	public void updateLoyaltyItemConfirm()
	{			
		LoyaltyredemptionbreakdownModel c = layaltybreakdowns.get(this.getRowId());
		layaltybreakdowns.remove(c);		
		addSalesItem();
		resetAddItem();		
	}	
	
	
	public void removeLoyaltyItem(ActionEvent event)
	{
		String loyalty = "";
		loyalty = (String) event.getComponent().getAttributes().get("loyalty").toString();		
		this.setRowId(Integer.parseInt(loyalty));			
	}
	
	public void removeLoyaltyItemConfirm()
	{			
		LoyaltyredemptionbreakdownModel c = layaltybreakdowns.get(this.getRowId());
		layaltybreakdowns.remove(c);		
		extractQuoteTotal();
	}	
	
	public BigDecimal extractSubtotal(BigDecimal unitPrice,	BigDecimal discAmount, BigDecimal quant) {
		BigDecimal quantityValue = quant;
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);
		BigDecimal tempTot = priceAmt.subtract(discAmt);			
			return tempTot;
		}
		
	
		public void extractQuoteTotal() {
			    BigDecimal totalAmount =new BigDecimal(0.00);
			    Integer totalPoints =0;
			    BigDecimal grandTotalAmount =new BigDecimal(0.00);
			    
				for(LoyaltyredemptionbreakdownModel item : layaltybreakdowns)
				{
					if(item.getRedemType().equalsIgnoreCase("2"))
					{
					totalAmount=totalAmount.add(item.getSubtotalAmount());
					}
					totalPoints=totalPoints+(item.getRedemPoint());
				}
				this.loyaltyredemption.setTotalredemAmount(totalAmount);
				this.loyaltyredemption.setTotalredemPoint(totalPoints);
			}
		

		public void getDynamicSalesItemList(LoyaltyredemptionbreakdownModel item) {
			this.layaltybreakdowns.add(item);
			
		}

		
		public void saveLoyalty()
		{
			boolean saveSuccess=false;
			FacesContext context = FacesContext.getCurrentInstance();
			try
			{
				loyaltyredemption.setLoyaltyredemptionbreakdowns(layaltybreakdowns);
				loyaltyredemption.setRedemptionDate(new Date());
				loyaltyredemption.setStatus("1");
				loyaltyredemption.setBranchRecordId(loginBean.getBranch().getBranchId());
				
				saveSuccess=redemptionBO.createNewLoyaltyredemption(loyaltyredemption);
				if(saveSuccess)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"lredemPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",null));
					this.resetLoyalty();					
				}				
			}
			catch(Exception e)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"lredemPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		}
		
		
		
	
	public boolean validateRedemptionSearch() {
		boolean valid = true;					
				
		if(this.getCustomerId()!=null)
		{
			if(this.getCustomerId()==0)
			{
				this.setCustomerId(null);
			}
		}				
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}	
		
		if(this.getLoyaltyCode()!=null)
		{
			if(this.getLoyaltyCode().equalsIgnoreCase("") || this.getLoyaltyCode().equalsIgnoreCase("0") )
			{
				this.setLoyaltyCode(null);
			}
		}	
		
		if(this.getCustomerId()==null &&  this.getDateFrom()==null &&this.getDateTo()==null && this.getStatus()==null && this.getLoyaltyCode()==null )
		{	
			valid = false;
		}			
		else
		{
			valid = true;
		}			
		return valid;

	}	
	
	public void printRedemption(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		loyaltyredemption = (LoyaltyredemptionModel) event.getComponent().getAttributes().get("redem");	
		
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/redemption/redemptionDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&redemptionId=" +loyaltyredemption.getRedemptionId()+"&userId="+loginBean.getUserId());
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
