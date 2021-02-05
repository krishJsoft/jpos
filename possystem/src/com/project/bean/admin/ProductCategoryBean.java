package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 02 July 2013
 * 
 */

@ManagedBean(name = "productCategoryBean")
@SessionScoped
public class ProductCategoryBean {

	private String action = "submit";
	private String status;
	private int categoryId;
	static String cNameTemp="";
	static int count=0;
	static String cNameTemp1="";
	static String lastParent="";
	static String lastCategryName="";
	static String lastParentCategryName="";
	private String parentCategoryName;
	
	ProductcategoryModel productcategory = new ProductcategoryModel();
	List<ProductcategoryModel> childCategoryList=new ArrayList<ProductcategoryModel>();
	private static List<ProductcategoryModel> productcategories = new ArrayList<ProductcategoryModel>();	
	private  List<ProductcategoryModel> productcategoriesList = new ArrayList<ProductcategoryModel>();	
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	Configuration config = Configuration.getConfiguration();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

	UomtypeModel uomtypeModel = new UomtypeModel();
	private List<UomtypeModel> uomtypeModelList = new ArrayList<UomtypeModel>();
	private List<UomtypeModel> uomtypeModelListObj = new ArrayList<UomtypeModel>();
	
	//List<ProductcategoryModel> productcategories = new ArrayList<ProductcategoryModel>();
	
	private static  List<ProductcategoryModel> liste;
	private static List<ProductcategoryModel> araListe;
	private ProductcategoryModel katNesnesi;
	private List<ProductcategoryModel> subList2;	    
	    

	private TreeNode rootNode;
    private TreeNode selectedNode;
    private TreeNode donanim;
    private TreeNode lastSelectedNode;
    private TreeNode[] selectedNodes;

	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();
	IProductBO productBO=objectMapController.getProductBO();	

	
	public IProductCategoryBO getProductCategoryBO() {
		return productCategoryBO;
	}

	public void setProductCategoryBO(IProductCategoryBO productCategoryBO) {
		this.productCategoryBO = productCategoryBO;	
	}
	
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public TreeNode getLastSelectedNode() {
		return lastSelectedNode;
	}

	public void setLastSelectedNode(TreeNode lastSelectedNode) {
		this.lastSelectedNode = lastSelectedNode;
	}

	
	
	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductcategoryModel> getChildCategoryList() {
		return childCategoryList;
	}

	public void setChildCategoryList(List<ProductcategoryModel> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}

	public ProductcategoryModel getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(ProductcategoryModel productcategory) {
		this.productcategory = productcategory;
	}

	public List<ProductcategoryModel> getProductcategories() {
		return productcategories;
	}

	public void setProductcategories(List<ProductcategoryModel> productcategories) {
		this.productcategories = productcategories;
	}
	

	public static void setListe(List<ProductcategoryModel> liste) {
		ProductCategoryBean.liste = liste;
	}

	public static List<ProductcategoryModel> getAraListe() {
		return araListe;
	}

	public static void setAraListe(List<ProductcategoryModel> araListe) {
		ProductCategoryBean.araListe = araListe;
	}

	public ProductcategoryModel getKatNesnesi() {
		return katNesnesi;
	}

	public void setKatNesnesi(ProductcategoryModel katNesnesi) {
		this.katNesnesi = katNesnesi;
	}

	public List<ProductcategoryModel> getSubList2() {
		return subList2;
	}

	public void setSubList2(List<ProductcategoryModel> subList2) {
		this.subList2 = subList2;
	}

	public TreeNode getRootNode() {		
		return rootNode;
	}
	
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	
	
	public List<ProductcategoryModel> getProductcategoriesList() {
		return productcategoriesList;
	}

	public void setProductcategoriesList(
			List<ProductcategoryModel> productcategoriesList) {
		this.productcategoriesList = productcategoriesList;
	}

	public void loadCategory()
	{
		try
		{
		productcategoriesList=productCategoryBO.findByProductcategoryList(null , null );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public void loadMainCategory() {
		try {
			 productcategoriesList.clear();
			this.productcategoriesList=productCategoryBO.findByParentCategoryId(38, null);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadAllValidCategory() {
		{
			try {
				 productcategoriesList.clear();
				List<Integer> validCatId=productCategoryBO.getValidCategoryIdWithProducts2(loginBean.getBranch().getBranchId());
				for(Integer catId:validCatId) {
					ProductcategoryModel obj=productCategoryBO.getProductcategoryDetails(catId);
					productcategoriesList.add(obj);
				}
			} catch (Exception ex) {
				
				ex.printStackTrace();
			}
		}
	}
	
	public void quickMenuByParentCategory(ActionEvent event) {
		try {
			String categoryId = "";
			categoryId = (String) event.getComponent().getAttributes()
				.get("categoryId").toString();
			productcategoriesList.clear();
		
			productcategoriesList=productCategoryBO.findByParentCategoryId(Integer.parseInt(categoryId),"1");
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			//projectHome.setPrevPage(projectHome.getContenttpage());
			//List<String> pages=projectHome.getPageTracker();
			//pages.add(projectHome.getPrevPage());
			//projectHome.setPageTracker(pages);
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
				projectHome.setContentpage("/mobileVersionUI/sales/categorySelection.xhtml");
				homeinfo.menuPageRedirect();
			}else {
				ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
				context2.redirect(context2.getRequestContextPath()+"/mobileVersionUI/sales/categorySelection.xhtml");
					
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	public void quickMenuByCategory(ActionEvent event) {
		try {
			
			String[] categoryIds = event.getComponent().getAttributes()
				.get("categoryId").toString().split(",");
			
			productcategoriesList.clear();
			for(String catId:categoryIds) {
				ProductcategoryModel obj=productCategoryBO.getProductcategoryDetails(Integer.parseInt(catId));
				productcategoriesList.add(obj);
			}
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			//projectHome.setPrevPage(projectHome.getContenttpage());
			//List<String> pages=projectHome.getPageTracker();
			//pages.add(projectHome.getPrevPage());
			//projectHome.setPageTracker(pages);
			projectHome.setContentpage("/mobileVersionUI/sales/categorySelection.xhtml");
			homeinfo.menuPageRedirect();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	public List<ProductcategoryModel> loadCategoryChildList(int categoryId) {
		List<ProductcategoryModel> cat=new <ProductcategoryModel>ArrayList();
		try {
			cat=productCategoryBO.findByParentCategoryId(categoryId, null);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cat;
	}
	
	public TreeNode getDefaultRootNode()
	{
		try
		{
		ProductcategoryModel defaultCat = new ProductcategoryModel();
	
		productcategories.clear();
		
		
		 
         //liste=productCategoryBO.findByProductcategoryList(null , null );
		 liste=new ArrayList<ProductcategoryModel>();
         liste=productCategoryBO.findByProductcategoryList2(null , null, loginBean.getBranch().getBranchId(),null,null);
           	
		
		//defaultCat=liste.get(0);
		defaultCat.setName(config.getValue(IConfiguration.PROJECT_DEFAULT_CATEGORY));
		defaultCat.setParentCategoryName(config.getValue(IConfiguration.PROJECT_DEFAULT_CATEGORY));
		defaultCat.setDescription(config.getValue(IConfiguration.PROJECT_DEFAULT_CATEGORY));
		defaultCat.setCategoryId(0);
		defaultCat.setCode("1");
		defaultCat.setParentCategoryId(0);
		defaultCat.setStatus("1");
		
		
		 rootNode=new DefaultTreeNode(defaultCat,null);     
		 rootNode.setExpanded(true);
		 
		
         //donanim=new DefaultTreeNode(liste.get(1), rootNode);
        // cNameTemp=defaultCat.getName();
         productcategories.add(liste.get(1));  
         recursive(liste, 0 ,liste.get(1),rootNode); 
       /*  this.setSelectedNode(donanim);
         if(selectedNode!=null)
         {
         selectedNode.setSelected(true);
         }
 		*/
 		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		this.setRootNode(rootNode);		
		return rootNode;
		
	}
	
	
	public TreeNode getDefaultRoot()
	{
		try
		{
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		return rootNode;
		
	}
	


	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public TreeNode getDonanim() {
		return donanim;
	}

	public void setDonanim(TreeNode donanim) {
		this.donanim = donanim;
	}	

    public  void recursive(List<ProductcategoryModel> liste, int id,ProductcategoryModel category,TreeNode node){
    	    
            subList2=new ArrayList<ProductcategoryModel>();
            subList2=subKategori(id,category);
          for(ProductcategoryModel k:subList2){
            TreeNode childNode=new DefaultTreeNode(k, node);             
             recursive(subList2, k.getCategoryId(),k,childNode);
          }
    }

    public static List<ProductcategoryModel> subKategori(int i,ProductcategoryModel category)
    {	    	
    	lastCategryName=category.getName();  
    	lastParentCategryName="";
        araListe=new ArrayList<ProductcategoryModel>();
        for(ProductcategoryModel k:getListe()){
            if(k.getParentCategoryId()==i){
            	
            	ProductcategoryModel cmodelTemp = new ProductcategoryModel();            	
                araListe.add(k);   
                cNameTemp=cNameTemp.concat(lastCategryName+"->").concat(k.getName());
                cmodelTemp.setName(cNameTemp);                
                cmodelTemp.setCategoryId(k.getCategoryId());
                cmodelTemp.setParentCategoryId(k.getParentCategoryId());
                productcategories.add(cmodelTemp); 
                lastParent=lastCategryName;
                cNameTemp="";
            }
            else
            {
            	cNameTemp=lastParent;
            }
        }  
       
        return araListe;
    }
    public static List<ProductcategoryModel> getListe() {
        return liste;
    }   
	
	
    public void onNodeSelect(NodeSelectEvent event) {     	
    	this.setLastSelectedNode(event.getTreeNode());    	
    }  
    
    
    public void addCategory(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		String name = "";
		categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();
		name = (String) event.getComponent().getAttributes().get("name").toString();		
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.addCategory(Integer.parseInt(categoryId));
		this.setParentCategoryName(name);
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
    
    
    public void editCategory(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		String name = "";
		categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();	
		name = (String) event.getComponent().getAttributes().get("name").toString();
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.editCategory(Integer.parseInt(categoryId));
		this.setParentCategoryName(name);
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
    
    
    public void deleteCategoryConfirm(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();	
		this.setCategoryId(Integer.parseInt(categoryId));
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}   
    
    
    public void deleteCategory() throws Exception 
   	{
   	FacesContext context = FacesContext.getCurrentInstance();	
   	try {
   				
   		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
   		productCategoryBeanInfo.deleteCategory(this.getCategoryId());   		
   	} catch (Exception e) {
   		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
   		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
   		throw e;
   		}
   	}   
    
    
    public void saveCategory()
    {
    	ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.saveCategory();
    }    
    
    public void updateCategory()
    {
    	ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.updateCategory();
    }
    
    public void listCategory()
    {
    	ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.listCategory();
    }
    
    public void resetCategory()
    {
    	ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.resetCategory();
    }
    
   public void expand(TreeNode treeNode){
        if (treeNode.getParent()!=null){
            treeNode.getParent().setExpanded(true);
            expand(treeNode.getParent());
        }
    }

   public UomtypeModel getUomtypeModel() {
	return uomtypeModel;
   }

   public void setUomtypeModel(UomtypeModel uomtypeModel) {
	this.uomtypeModel = uomtypeModel;
   }

   public List<UomtypeModel> getUomtypeModelList() {
		FacesContext context = FacesContext.getCurrentInstance();
	   try
	  {
		  uomtypeModelList=productCategoryBO.findByUomtypeList(null);
		  this.setUomtypeModelListObj(uomtypeModelList);
	  }
	  catch(Exception e)
	  {
		  context.addMessage(UIComponent.findComponent(context.getViewRoot(),"uomPanel").getClientId(context),
		  new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
	  }
	return uomtypeModelList;
   }

   public void setUomtypeModelList(List<UomtypeModel> uomtypeModelList) {
	this.uomtypeModelList = uomtypeModelList;
   }

   public List<UomtypeModel> getUomtypeModelListObj() {
	return uomtypeModelListObj;
   }

   public void setUomtypeModelListObj(List<UomtypeModel> uomtypeModelListObj) {
	this.uomtypeModelListObj = uomtypeModelListObj;
   }

   public void editUom(ActionEvent event) throws Exception 
  	{
  	FacesContext context = FacesContext.getCurrentInstance();	
  	try {
  		String UOMId = "";
  		UOMId = (String) event.getComponent().getAttributes().get("UOMId").toString();		
  		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
  		productCategoryBeanInfo.editUom(Integer.parseInt(UOMId));
  		
  	} catch (Exception e) {
  		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
  		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
  		throw e;
  		}
  	}
   
   	
   public void saveUOM()
   {
   	ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
	productCategoryBeanInfo.saveUOM();
	CommonListBeanInfo.getAllUomList();
   }    
   
   public void updateUOM()
   {
   	    ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productCategoryBeanInfo.updateUOM();
		CommonListBeanInfo.getAllUomList();
   }
   
   public void resetUOM()
   {
	   ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
	   productCategoryBeanInfo.resetUOM(); 
   }
   
   
   public String alarStyleClass(String level) {
	   
	   	String colorValue=null;
	   
	    if(level.equals("1"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level1.color");
	    	}
	    else if(level.equals("2")) 
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level2.color");
	    	}
	    else if(level.equals("3")) 
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level3.color");
	    	}
	    else if(level.equals("4")) 
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level4.color");
	    	}
	    else if(level.equals("5"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level5.color");
	    	}
	    else if(level.equals("6")) 
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level6.color");
	    	}
	    else if(level.equals("7"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level7.color");
	    	}
	    else if(level.equals("8"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level8.color");
	    	}
	    else if(level.equals("9"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level9.color");
	    	}
	    else if(level.equals("10"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level10.color");
	    	}
	    else if(level.equals("11"))
	    	{
	    	colorValue= factoryBean.getMessageFactory().getMessage("productcategory.row.level11.color");	    
	    	}
	  
	    return colorValue;
	}

   
   public void deleteCategory2(ActionEvent event) throws Exception 
   {
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			String categoryId = "";
			categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();
			
			boolean deleteSuccess=false;
			
	   		deleteSuccess=productCategoryBO.deleteProductcategory(Integer.parseInt(categoryId));
	   		if(deleteSuccess) {
	   			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
	   					new FacesMessage(FacesMessage.SEVERITY_INFO, "Category successfully deleted ", null));
	   			CommonListBeanInfo info = new CommonListBeanInfo();
	   			info.getCategory();
	   		}
			
		} catch (Exception  e) {
			if(e.getCause().toString().toLowerCase().contains("constraintviolationexception")) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete, there are products added under that category.", null));
			}else {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}
			throw e;
		}
	}
   
   
}
