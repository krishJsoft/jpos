package com.project.bean.delivery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IProductbatchBO;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.stock.BatchmomenthistryModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.datamodel.stock.ProductbatchModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.DeliveryOrderPagingHelper;
import com.project.model.sale.sales.PaymentCollectionModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 05 June 2014
 * 
 */

@ManagedBean(name = "productbatchBean")
@SessionScoped
public class ProductbatchBean {

	ProductbatchModel batch = new ProductbatchModel();
	List<ProductbatchModel> productBatchList = new ArrayList<ProductbatchModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	BatchmomenthistryModel batchmovement = new BatchmomenthistryModel();
	List<BatchmomenthistryModel> batchmovementList = new ArrayList<BatchmomenthistryModel>();
	IProductbatchBO productbatchBO=objectMapController.getProductbatchBO();
	
	private String deliveryOrderNo;
	private String batchNo;
	private Integer supplierId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String productCode;
	
	 protected int first;  
	    
	 public int getFirst() {  
	         return first;  
	    }  
	 public void setFirst(int first) {  
	         this.first = first;  
	    }  
	 public void onPageChange(PageEvent event) {  
	         this.setFirst(((DataTable) event.getSource()).getFirst());  
	    }  	

	public ProductbatchModel getBatch() {
		return batch;
	}

	public void setBatch(ProductbatchModel batch) {
		this.batch = batch;
	}

	public List<ProductbatchModel> getProductBatchList() {
		return productBatchList;
	}

	public void setProductBatchList(List<ProductbatchModel> productBatchList) {
		this.productBatchList = productBatchList;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}	
	
	public BatchmomenthistryModel getBatchmovement() {
		return batchmovement;
	}
	public void setBatchmovement(BatchmomenthistryModel batchmovement) {
		this.batchmovement = batchmovement;
	}
		
	
	public List<BatchmomenthistryModel> getBatchmovementList() {
		return batchmovementList;
	}
	public void setBatchmovementList(List<BatchmomenthistryModel> batchmovementList) {
		this.batchmovementList = batchmovementList;
	}
	
	
	public List<ProductbatchModel> getProductBatchListTemp() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setProductBatchList(null);	
		productBatchList = productbatchBO.getProductbatchDetails(batchNo, deliveryOrderNo, null, status, dateFrom, dateTo, loginBean.getBranch().getBranchId(),productCode,null);
		this.setProductBatchList(productBatchList);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return productBatchList;
	}
	
	
	public void searchBatchOrder()
	{
		
		try
		{   if(!validateBatchSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getProductBatchListTemp();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void resetsearchBatchOrder()
	{
		this.setSupplierId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus("1");
		this.setProductCode(null);
		this.setDeliveryOrderNo(null);
		searchBatchOrder();
	}
	
	
	
	public void moveBatchHistory(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			batchmovement = new BatchmomenthistryModel();
			//ProductbatchModel batchProduct = null;
			batch = (ProductbatchModel) event.getComponent().getAttributes().get("batch");				
			ProductModel product = new ProductModel();
			product.setProductName(batch.getProductName());
			product.setProductId(batch.getProductId());
			product.setProductCode(batch.getProductCode());		
			
			batchmovement.setProduct(product);
			batchmovement.setBatchNo(batch.getBatchNo());
			batchmovement.setExpdate(batch.getExpairyDate());
			batchmovement.setQuantity(new BigDecimal(batch.getAvilableQuantity()));
			batchmovement.setBatch(batch);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
		
	}
	
	public boolean checkBatchMovementQuantity()
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		boolean valid =true;		
		 	try {		 			
		 			if(batchmovement.getQuantity()!=null && batchmovement.getQuantity().intValue()==0)
		 			{
		 			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"batchmovementPanel").getClientId(context),
		 			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity should be more than zero ",null));
		 			valid=false;
		 			}
		 			else if(batchmovement.getQuantity().intValue()> this.batch.getQuantity())
		 			{
		 			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"batchmovementPanel").getClientId(context),
		 			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity should be less than or equal to available Quantity ",null));	
		 			valid=false;
		 			} 			
 		
		 	} 
		 	catch (Exception e) 
		 	{		
		 		System.out.println(e.toString());
		 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"deliveryPanel").getClientId(context),
		 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));		 		
		 	}			 	
		 	return valid;
	}
	
	
	public void saveBatchMovement()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		RequestContext reqcontext = RequestContext.getCurrentInstance(); 		
		boolean valid =true;		
		 	try {		 			
		 			if(checkBatchMovementQuantity())
		 			{		 					 			
		 				batchmovementList=productbatchBO.getBatchmomenthistryDetails(null, batchmovement.getProduct().getProductId(), "1", loginBean.getBranch().getBranchId(), null,null);
		 				if(batchmovementList!=null && batchmovementList.size()==0)
		 				{
		 				 // New Insert
		 				batchmovement.setBranchRecordId(loginBean.getBranch().getBranchId());
		 				batchmovement.setLastupdateDate(new Date());
		 				batchmovement.setLastupdateUser(loginBean.getBranch().getEmailAddress());
		 				
		 				productbatchBO.createBatchmomenthistry(batchmovement);
		 				reqcontext.addCallbackParam("validity", true);
		 				batchmovement = new BatchmomenthistryModel(); // Reset
		 				searchBatchOrder();
		 				}
		 				else
		 				{
		 				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"batchmovementPanel").getClientId(context),
		 				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Already Exists! ",null));	
		 				valid=false;
		 				reqcontext.addCallbackParam("validity", false);
		 				}
		 		
		 			} 	
		 			else
		 			{		 				
		 				reqcontext.addCallbackParam("validity", false);
		 			}
 		
		 	} 
		 	catch (Exception e) 
		 	{		
		 		System.out.println(e.toString());
		 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"deliveryPanel").getClientId(context),
		 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));		 		
		 	}			 	
		 	
	}
	
	
	
	public boolean validateBatchSearch() {
		boolean valid = true;		
	
		
		
		if(this.getDeliveryOrderNo()!=null)
		{
			if(this.getDeliveryOrderNo().equalsIgnoreCase(""))
			{
				this.setDeliveryOrderNo(null);
			}
		}		
		
		if(this.getBatchNo()!=null)
		{
			if(this.getBatchNo().equalsIgnoreCase(""))
			{
				this.setBatchNo(null);
			}
		}		
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getDateFrom()==null && this.getDateTo()==null   && this.getDeliveryOrderNo()==null && this.getBatchNo()==null  && this.getStatus()==null )
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
}
