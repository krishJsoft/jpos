package com.project.bean.admin;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class ProductCategoryBeanInfo {

	    ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
		FacesContext context = FacesContext.getCurrentInstance();
		Configuration config = Configuration.getConfiguration();
		String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
		CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
		static ErrorFactory errorFactory = ErrorFactory.getInstance();
		
		public void listCategory() {
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navHandler = context.getApplication().getNavigationHandler();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			projectHome.setContentpage("/admin/productCategory.xhtml");
			projectHome.setTitlePage("Admin --> Product Category -->Search Category");
			navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
		}

		
		
		public void newCategory() {
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navHandler = context.getApplication().getNavigationHandler();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			projectHome.setContentpage("/admin/addEditCategory.xhtml");
			projectHome.setTitlePage("Admin --> Category -->Add/Edit Category");
			navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
		}
		
		
		public void editCategory(Integer categoryId)
		{
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			try {
				productCategoryBean.setProductcategory(productCategoryBO.getProductcategoryDetails(categoryId));
				productCategoryBean.setAction("update");
				newCategory();
			} catch (Exception e) {
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				e.printStackTrace();
			}
		}
		
		
		public void addCategory(Integer categoryId)
		{
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			ProductcategoryModel defaultCat = new ProductcategoryModel();
			defaultCat.setName(config.getValue(IConfiguration.PROJECT_DEFAULT_CATEGORY));
			defaultCat.setDescription(config.getValue(IConfiguration.PROJECT_DEFAULT_CATEGORY));
			defaultCat.setCategoryId(0);
			defaultCat.setCode("111");
			defaultCat.setParentCategoryId(0);
			defaultCat.setStatus("1");
			try {
				if(categoryId!=0)
				{
					productCategoryBean.setProductcategory(productCategoryBO.getProductcategoryDetails(categoryId));
				}
				else
				{
					productCategoryBean.setProductcategory(defaultCat);
				}
				resetCategory();
				newCategory();
			} catch (Exception e) {
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				e.printStackTrace();
			}
		}
		
		
		public ProductcategoryModel selectCategory(Integer categoryId)
		{
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			ProductcategoryModel productcategoryModel=null;
			try {
				productcategoryModel=productCategoryBO.getProductcategoryDetails(categoryId);				
			} catch (Exception e) {
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				e.printStackTrace();
			}
			
			return productcategoryModel;
		}
		
		
		public void deleteCategory(Integer categoryId)
		{
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			CommonListBeanInfo info = new CommonListBeanInfo();
			 boolean deleteSuccess = false;	
			try {
				deleteSuccess=productCategoryBO.deleteProductcategory(categoryId);				
				
				if (deleteSuccess) {					
					info.getCategory();
					listCategory();
					//productCategoryBean.expand(productCategoryBean.getLastSelectedNode());
    				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
    				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("productcategory.label.delete.success"),null));				
    			}
				
			} catch (Exception e) {
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				e.printStackTrace();
			}
		}
		
		
		
		public void saveCategory()
		    {	
			   IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			   boolean saveSuccess = false;	
			   CommonListBeanInfo info = new CommonListBeanInfo();
		    	if(validateCategory())
		    	{
		    		try
		    		{
		    			BranchModel branch=new BranchModel();
		    			branch.setBranchId(loginBean.getBranch().getBranchId());
		    			productCategoryBean.getProductcategory().setBranch(branch);
		    			
		    			saveSuccess=productCategoryBO.createNewProductcategory(productCategoryBean.getProductcategory());		    			
		    			if (saveSuccess) {					
		    				info.getCategory();
		    				listCategory();
		    				CommonListBeanInfo.getAllCategoryList();
		    				info.getCategory();
		    				//productCategoryBean.expand(productCategoryBean.getLastSelectedNode());
		    				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		    				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("productcategory.label.save.success"),null));				
		    			}
		    			
		    		}
		    		catch(Exception e)
		    		{
		    			context.addMessage(
		    			UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("productcategory.label.save.failed")));

		    		}
		    	}
		    }    
		
		
		    
	    public void updateCategory()
		    {
	    	IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			   boolean updateSuccess = false;	
			   CommonListBeanInfo info = new CommonListBeanInfo();
		    	if(validateCategory())
		    	{
		    		try
		    		{
		    			BranchModel branch=new BranchModel();
		    			branch.setBranchId(loginBean.getBranch().getBranchId());
		    			productCategoryBean.getProductcategory().setBranch(branch);
		    			
		    			updateSuccess=productCategoryBO.updateProductcategory(productCategoryBean.getProductcategory());	    			
		    			if (updateSuccess) {					
		    				info.getCategory();
		    				listCategory();
		    				CommonListBeanInfo.getAllCategoryList();
		    				info.getCategory();
		    				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		    				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("productcategory.label.update.success"),null));				
		    			}
		    			
		    		}
		    		catch(Exception e)
		    		{
		    			context.addMessage(
		    			UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("productcategory.label.update.failed")));

		    		}
		    	}
		    }
		    
		   public void resetCategory()
		   {
			    productCategoryBean.setAction("submit");
				productCategoryBean.getProductcategory().setName(null);
				productCategoryBean.getProductcategory().setDescription(null);
				productCategoryBean.getProductcategory().setStatus("1");
		   }
		   
		   
		   
		   
		   public void editUom(Integer UOMId)
			{
				IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
				try {
					productCategoryBean.setUomtypeModel(productCategoryBO.getUomtypeDetails(UOMId));
					productCategoryBean.setAction("update");					
				} catch (Exception e) {
					context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
					e.printStackTrace();
				}
			}
		   
		   
		   
		   public void saveUOM()
		    {	
			   IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			   boolean saveSuccess = false;	
		    	if(validateUOM())
		    	{
		    		try
		    		{
		    			saveSuccess=productCategoryBO.createNewUom(productCategoryBean.getUomtypeModel());		    			
		    			if (saveSuccess) {		  
		    				productCategoryBean.getUomtypeModelList();
		    				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),
		    				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("uom.label.save.success"),null));				
		    			}		    			
		    		}
		    		catch(Exception e)
		    		{
		    			context.addMessage(
		    			UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("uom.label.save.failed")));

		    		}
		    	}
		    }    
		
		
		    
	    public void updateUOM()
		    {
	    	IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			   boolean updateSuccess = false;	
		    	if(validateUOM())
		    	{
		    		try
		    		{
		    			updateSuccess=productCategoryBO.updateUom(productCategoryBean.getUomtypeModel());    			
		    			if (updateSuccess) {	
		    				productCategoryBean.getUomtypeModelList();
		    				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),
		    				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("uom.label.update.success"),null));				
		    			}		    			
		    		}
		    		catch(Exception e)
		    		{
		    			context.addMessage(
		    			UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("uom.label.update.failed")));

		    		}
		    	}
		    }
		    
		   public void resetUOM()
		   {
			    productCategoryBean.setAction("submit");				
				productCategoryBean.getUomtypeModel().setUOMName(null);
				productCategoryBean.getUomtypeModel().setDescription(null);
				
		   }
		   
		   
		   
		   /* Start Uom  Validation */
			
			public boolean validateUOM() {
				boolean valid = true;		
				IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
				String messageValue=null;
				
				try
				{
				
				if (factoryBean.checkIsNullAssignMessage(productCategoryBean.getUomtypeModel().getUOMName(),
						"uom.label.UOMName", "UOMName")) {
					valid = false;
				}			
				
				else {
					if (productCategoryBean.getAction().equalsIgnoreCase("submit")) {
						if (productCategoryBO.findUomtypeExites(productCategoryBean.getUomtypeModel().getUOMName())) {
							messageValue = factoryBean.getErrorFactory().getError("uom.errors.name.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "UOMName");
							valid = false;
						}
					}else{  
						
						if(! productCategoryBean.getUomtypeModel().getUOMName().equalsIgnoreCase(productCategoryBean.getUomtypeModel().getUOMOldName()) ){				
							
							if (productCategoryBO.findUomtypeExites(productCategoryBean.getUomtypeModel().getUOMName())) {
								messageValue = factoryBean.getErrorFactory().getError("uom.errors.name.exists");
								factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "UOMName");
								valid = false;
							}
						}
					}
				}			
				
				}
				catch(Exception e)
				{
					context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

				}				
				return valid;

			}
			
		   
		    
	    /* Start Category  Validation */
		
		public boolean validateCategory() {
			boolean valid = true;		
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			String messageValue=null;
			
			try
			{
			
			if (factoryBean.checkIsNullAssignMessage(productCategoryBean.getProductcategory().getName(),
					"productcategory.label.name", "name")) {
				valid = false;
			}			
			
			else {
				if (productCategoryBean.getAction().equalsIgnoreCase("submit")) {
					if (productCategoryBO.findCategoryExites(productCategoryBean.getProductcategory().getCategoryId(),productCategoryBean.getProductcategory().getParentCategoryId(),productCategoryBean.getProductcategory().getName())) {
						messageValue = factoryBean.getErrorFactory().getError("productcategory.errors.name.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "name");
						valid = false;
					}
				}else{  
					
					if(! productCategoryBean.getProductcategory().getName().equalsIgnoreCase(productCategoryBean.getProductcategory().getOldname()) ){				
						
						if (productCategoryBO.findCategoryExites(productCategoryBean.getProductcategory().getCategoryId(),productCategoryBean.getProductcategory().getParentCategoryId(),productCategoryBean.getProductcategory().getName())) {
							messageValue = factoryBean.getErrorFactory().getError("productcategory.errors.name.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "name");
							valid = false;
						}
					}
				}
			}			
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			
			
			return valid;

		}
		
		
		public boolean validateCategoryDelete() 
		{
			boolean valid = true;		
			IProductCategoryBO productCategoryBO=productCategoryBean.getProductCategoryBO();
			String messageValue=null;
			try
			{
			
			if (factoryBean.checkIsNullAssignMessage(productCategoryBean.getProductcategory().getName(),
					"productcategory.label.name", "name")) {
				valid = false;
			}	
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}					
			return valid;		
		}

		/* End Category Validation */
    
		    
		
}
