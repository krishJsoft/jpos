package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IProductBO;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductbundleModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class ProductBeanInfo {

	
	ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
	ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/products.xhtml");
		projectHome.setTitlePage("Admin --> Product -->Search Product");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	public void listStock() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/stock/hqStockMaster.xhtml");
		projectHome.setTitlePage("Stock --> HQ Stock -->Search Product's Stock");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	
	public void newProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		//productCategoryBean.getDefaultRootNode();
		projectHome.setContentpage("/admin/addEditProduct.xhtml");
		projectHome.setTitlePage("Admin --> Product -->Add/Edit Product");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void newProductbundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		//productCategoryBean.getDefaultRootNode();
		projectHome.setContentpage("/admin/addEditProductbundle.xhtml");
		projectHome.setTitlePage("Admin --> Product -->Add/Edit Product Bundle");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void productPrice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		//productCategoryBean.getDefaultRootNode();
		projectHome.setContentpage("/admin/productPricing.xhtml");
		projectHome.setTitlePage("Admin --> Product -->Add/Edit Product Pricing");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	
	public void saveProduct()
	{	
		CommonListBeanInfo info = new CommonListBeanInfo();
		if(validateProduct())
		{	
			boolean saveSuccess = false;			
			try
			{
			ProductModel product=productBean.getProduct();
			product.setCreatedBy(loginBean.getLoginName());
			product.setBranchId(loginBean.getBranch().getBranchId());
			product.setCreatedDate(new Date());
			product.setLastModifiedDate(new Date());		
			product.setUom("1");
			product.setSupplierlist(productBean.getSupplierlist());
			product.setProductbundleList(productBean.getProductbundleList());
			product.setOnline(false);
			if(null!=productBean.getImage()){
				product.setImageDirectory(productBean.getImageFileName());
				productBean.uploadImageToFolder();
			}
			List<ProductsetModel> setObjList=new ArrayList<ProductsetModel>();
			
			for(ProductModel item:productBean.getDualList().getTarget()) {
				ProductsetModel setObj=new ProductsetModel();
				setObj.setProduct(item);
				setObj.setQuantity(1);
				setObj.setProducSetId(product.getProductId());
				setObjList.add(setObj);
			}
			

			product.setProductSetList(setObjList);
			IProductBO productBO=productBean.getProductBO();
			
			
			saveSuccess=productBO.createNewProduct(product);
			
			if (saveSuccess) {
			
					
				productBean.resetSearchProduct();
				resetProduct();
				listProduct();
				info.getProductAll();	
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("product.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.save.failed")));

			}
		}
		
	}
	
	public void saveProductPrice()
	{	
		    List<Integer> pricingIds = new ArrayList<Integer>();
			List<ProductpriceModel> productPrice=null;
			List<ProductpriceModel> deleteproductPriceList= new ArrayList<ProductpriceModel>();
			boolean saveSuccess = false;	
			boolean priceRemoved = true;
			IProductBO productBO=productBean.getProductBO();
			try {				
			/*	List<ProductpriceModel> productPriceList = productBO.getProductpriceByProductId(productBean.getProductId(),loginBean.getBranch().getBranchId());			
				
				 for(ProductpriceModel oldPrice:productPriceList){
			     priceRemoved = true;
				for (ProductpriceModel newPrice : productBean.getPricelist()) {								 
						if(oldPrice.getProductPriceId()==newPrice.getProductPriceId()){
							priceRemoved = false;							
							break;									
						}
				}
				if(priceRemoved){								
					productBO.deleteProductPrice(oldPrice);					
				}
				
				}	*/		
			saveSuccess=productBO.updateProductPrice(productBean.getProductId(),productPrice,productBean.getPricelist(),loginBean.getBranch().getBranchId());
			
			if (saveSuccess) {					
				//productBean.resetSearchProduct();
				//listStock();
				listProduct();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("productprice.label.update.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.save.failed")));

			}
		
		
	}
	
	
	
	public void editProduct(Integer producthId)
	{
		IProductBO productBO=productBean.getProductBO();
		List<Integer> supplierIds= new ArrayList<Integer>();
		try {
			ProductModel product=productBO.getProductDetails(producthId,loginBean.getBranch().getBranchId());
			productBean.setProduct(product);
			productBean.setCategoryName(product.getCategoryName());
			productBean.setAction("update");
			productBean.setSupplierlist(product.getSupplierlist());
			
			if(product.isSetItem()) {
				productBean.individualProductList=productBO.findByProductAll();
				List<ProductModel> productSource=new ArrayList<ProductModel>();
				List<ProductModel> productTarget=new ArrayList<ProductModel>();
				
				for(ProductsetModel setItem:product.getProductSetList()) {
					ProductModel obj=new ProductModel();
					obj.setTempSetId(setItem.getSetId());
					obj.setProductId(setItem.getProduct().getProductId());
					obj.setProductName(setItem.getProduct().getProductName());
					obj.setBarcode(setItem.getProduct().getBarcode());
					
					productTarget.add(obj);
				}
				for(ProductModel data:productBean.individualProductList) {
					ProductModel obj=new ProductModel();
					obj.setProductId(data.getProductId());
					obj.setProductName(data.getProductName());
					obj.setBarcode(data.getBarcode());
					boolean exist=false;
					for(ProductModel targetData:productTarget) {
						if(obj.getProductId()==targetData.getProductId()) {
							exist=true;
							break;
						}
					}
					if(!exist)
					{
						productSource.add(obj);
					}
				}
				
				productBean.dualList=new DualListModel<ProductModel>(productSource,productTarget);
			}
			for(ProductsupplierModel item:product.getSupplierlist())
			{
			supplierIds.add(item.getSupplierId());
			}
			productBean.setSupplierIds(supplierIds);
			if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
			{
			productBean.setProductbundleList(product.getProductbundleList());
			newProductbundle();			
			}
			else
			{
			newProduct();
			}
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	
	public List<ProductpriceModel> getProductPrice(String barcode,ProductModel dataTemp)
	{
		IProductBO productBO=productBean.getProductBO();
		 List<BigDecimal> pricingIds = new ArrayList<BigDecimal>();
		List<ProductpriceModel> productPrice=null;		
		
		try {
			 productPrice=productBO.getProductpriceByBarcode(dataTemp,barcode,loginBean.getBranch().getBranchId());	
			 
			 for(ProductpriceModel data:productPrice)
			 {
				 pricingIds.add(data.getQuantityFrom());
				 productBean.setProductprice(data);
				 productBean.setProductId(data.getProductId());
			 }
			 productBean.setPricingIds(pricingIds);
			 
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}		
		return  productPrice;
	}
	
	
	
	
	public void updateProduct()
	{
		CommonListBeanInfo info = new CommonListBeanInfo();
		boolean saveSuccess = false;		
		boolean supplierRemoved = true;
		ProductmaterailBean productmaterailBean = (ProductmaterailBean) BeanContext.getReference("productmaterailBean");
		
		if(validateProduct())
		{	
		try
		{		
		ProductModel product=productBean.getProduct();
		product.setUom("1");
		product.setBomlist(productmaterailBean.getProductcategories());
		
		product.setCreatedBy(loginBean.getLoginName());		
		product.setLastModifiedDate(new Date());			
		product.setOnline(false);
		if(null!=productBean.getImage()){
			product.setImageDirectory(productBean.getImageFileName());
			productBean.uploadImageToFolder();
		}
		
		IProductBO productBO=productBean.getProductBO();		
	
		
		if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
		{
			ProductModel p = productBO.getProductDetails(product.getProductId(),loginBean.getBranch().getBranchId());			
			
			for (ProductbundleModel newbundle : p.getProductbundleList()) {					
				productBO.deleteProductbundle(newbundle);
			}
		}
		
		List<ProductsetModel> setObjList=new ArrayList<ProductsetModel>();
		
		for(ProductModel item:productBean.getDualList().getTarget()) {
			ProductsetModel setObj=new ProductsetModel();
			setObj.setSetId(item.getTempSetId());
			setObj.setProduct(item);
			setObj.setQuantity(1);
			setObj.setProducSetId(product.getProductId());
			setObjList.add(setObj);
		}
		

		product.setProductSetList(setObjList);

		saveSuccess=productBO.updateProduct(product);
		
		if (saveSuccess) {	
			
			productBean.resetSearchProduct();
			resetProduct();
			listProduct();
			info.getProductAll();	
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("product.label.update.success"),null));
			}
		 }	
		catch(Exception e)
		{
			e.printStackTrace();
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.update.failed")));

		}
		}
	
	}
	public void deleteProduct()
	{
		productBean.searchProduct();
	}
	
	public void resetProduct()
	{
		ProductModel product= new ProductModel();
		product.setCustomerPoint(1);
		productBean.setProduct(product);
		productBean.setAction("save");
		productBean.getSupplierlist().clear();
		productBean.getSupplierIds().clear();
		productBean.setSupplieraction("submit");
		productBean.setProductCodedisable(false);
		productBean.setCategoryName(null);
		productBean.getProductbundleIds().clear();
		productBean.getProductbundleList().clear();
		
	}
	
	 /* Start product  Validation */
	
	public boolean validateProduct() {
		boolean valid = true;		
		IProductBO productBO=productBean.getProductBO();
		String messageValue=null;
		
		try
		{
		
		if (factoryBean.checkIsNullAssignMessage(productBean.getProduct().getProductName(),
				"product.label.productName", "productName")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(productBean.getProduct().getBarcode(),
				"product.label.barcode", "barcode")) {
			valid = false;
		}
		
		else {
			if (productBean.getAction().equalsIgnoreCase("submit")) {
				if (productBO.findbarcodeExites(productBean.getProduct().getBarcode())) {
					messageValue = factoryBean.getErrorFactory().getError("product.errors.barcode.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "barcode");
					valid = false;
				}
			}else{  
				
				if(! productBean.getProduct().getBarcode().equalsIgnoreCase(productBean.getProduct().getBaroldcode()) ){
					
					
					if (productBO.findbarcodeExites(productBean.getProduct().getBarcode())) {
						messageValue = factoryBean.getErrorFactory().getError("product.errors.barcode.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "barcode");
						valid = false;
					}
				}
			}
		}
				
		
		if (factoryBean.checkIsNullAssignMessage(productBean.getProduct().getCategoryId(),
				"product.label.categoryName", "categoryName")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(productBean.getCategoryName(),
				"product.label.categoryName", "categoryName")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(productBean.getProduct().getSupplierId(),
				"product.label.supplierName", "supplierName")) {
			valid = false;
		}
		
		/*if (factoryBean.checkIsNullAssignMessage(productBean.getProduct().getUom(),
				"product.label.uom", "uom")) {
			valid = false;
		}*/
		
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
		
		
		return valid;

	}
	
	
	 /* Start product  Validation */
	
		public boolean validateProductStp2() {
			boolean valid = true;		
			IProductBO productBO=productBean.getProductBO();
			String messageValue=null;
			
			try
			{		
				
			if (productBean.supplierlist==null) {					
					valid = false;
					context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Supplier", null));
				}
				
			if (productBean.supplierlist!=null) {
				if(productBean.supplierlist.size()==0)
				{
				valid = false;
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Supplier", null));
			}
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			
			
			return valid;
		}

	
		
	public String onFlowProcess(FlowEvent event) {
		
		boolean valid = true;
		String activeStep="";

		if (event.getOldStep().equalsIgnoreCase("step1")) {
			valid = validateProduct();
			if (valid) {	
				activeStep=event.getNewStep();	
				productBean.setSupplieraction("submit");
				productBean.setProductCodedisable(false);
				productBean.product.setSupplierId(0);
			} else {
				
				activeStep=event.getOldStep();
			}
		}

		else if (event.getOldStep().equalsIgnoreCase("step2")) {
			valid = this.validateProductStp2();
			if (valid) {										
				activeStep=event.getNewStep();
				
			}
			else
			{
				activeStep=event.getOldStep();
			}
		}		
		
		return activeStep;
		
		      }
	
	
	
	
	public void saveProductList(List<ProductModel> productList)
	{	
		
			boolean saveSuccess = false;	
			CommonListBeanInfo info = new CommonListBeanInfo();
			try
			{				
			for(ProductModel product:productList)
				{
			//ProductModel product=productBean.getProduct();
			product.setCreatedBy(loginBean.getLoginName());
			product.setBranchId(loginBean.getBranch().getBranchId());
			product.setCreatedDate(new Date());
			product.setLastModifiedDate(new Date());			
			product.setCategoryId(1);
			
			product.setSupplierlist(product.getSupplierlist());
			IProductBO productBO=productBean.getProductBO();
			
			saveSuccess=productBO.createNewProduct(product);
				}
			
			if (saveSuccess) {					
				productBean.resetSearchProduct();
				resetProduct();
				listProduct();
				info.getProductAll();	
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("product.label.save.success"),null));				
			
			}
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.save.failed")));

			}
		}
		

	
}
