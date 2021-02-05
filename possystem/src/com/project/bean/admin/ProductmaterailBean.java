package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.login.LoginBean;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductmaterailModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.model.paginghelper.QuotationOrderSort;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 02 July 2013
 * 
 */

@ManagedBean(name = "productmaterailBean")
@SessionScoped
public class ProductmaterailBean {

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
	
	ProductmaterailModel productcategory = new ProductmaterailModel();
	private List<ProductmaterailModel> productcategories = new ArrayList<ProductmaterailModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ProductModel product = new ProductModel();
	
	private static  List<ProductmaterailModel> liste;
	private static List<ProductmaterailModel> araListe;
	private ProductmaterailModel katNesnesi;
	private List<ProductmaterailModel> subList2;	    
	IProductBO productBO=objectMapController.getProductBO();

	private TreeNode rootNode;
    private TreeNode selectedNode;
    private TreeNode donanim;
    private TreeNode lastSelectedNode;
    private TreeNode[] selectedNodes;

	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();
	
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

	public ProductmaterailModel getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(ProductmaterailModel productcategory) {
		this.productcategory = productcategory;
	}

	public List<ProductmaterailModel> getProductcategories() {
		return productcategories;
	}

	public void setProductcategories(List<ProductmaterailModel> productcategories) {
		this.productcategories = productcategories;
	}
	

	public static void setListe(List<ProductmaterailModel> liste) {
		ProductmaterailBean.liste = liste;
	}

	public static List<ProductmaterailModel> getAraListe() {
		return araListe;
	}

	public static void setAraListe(List<ProductmaterailModel> araListe) {
		ProductmaterailBean.araListe = araListe;
	}

	public ProductmaterailModel getKatNesnesi() {
		return katNesnesi;
	}

	public void setKatNesnesi(ProductmaterailModel katNesnesi) {
		this.katNesnesi = katNesnesi;
	}

	public List<ProductmaterailModel> getSubList2() {
		return subList2;
	}

	public void setSubList2(List<ProductmaterailModel> subList2) {
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

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public TreeNode getDefaultRootNode()
	{
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
		try
		{
		ProductmaterailModel defaultCat = new ProductmaterailModel();
		defaultCat.setProduct(productBean.getProduct());
		defaultCat.setQuantity(new BigDecimal(0.00));
		
		defaultCat.setProductparent(productBean.getProduct());
		defaultCat.setProductchild(this.getProduct());
		
		defaultCat.setStatus("1");
		productcategories.clear();
		
		 liste=new ArrayList<ProductmaterailModel>();
		 rootNode=new DefaultTreeNode("Root",null);     
		 rootNode.setExpanded(true);
		 
         liste=productCategoryBO.findByBomList(productBean.getProduct().getProductId() , null );
         donanim=new DefaultTreeNode(defaultCat, rootNode);       
         productcategories.add(defaultCat);  
         recursive(liste, 0,defaultCat,donanim); 
      
 		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		this.setRootNode(rootNode);		
		return rootNode;
		
	}
	
	
	
	public List<ProductmaterailModel> getDefaulProductMaterial()
	{
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
		try
		{			
		productcategories.clear();		
		productcategories=new ArrayList<ProductmaterailModel>();
		productcategories=productCategoryBO.findByBomList(productBean.getProduct().getProductId() , null );          		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}				
		return productcategories;		
	}
	
	
	
	public void saveBomMaterials()
	{
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
		try
		{
			productCategoryBO.createMaterials(productcategories,productBean.getProduct().getProductId(),"1");
		}
		catch(Exception e)
		{
			
		}
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

    public  void recursive(List<ProductmaterailModel> liste, int id,ProductmaterailModel category,TreeNode node){
    	    
            subList2=new ArrayList<ProductmaterailModel>();
            subList2=subKategori(id,category);
          for(ProductmaterailModel k:subList2){
            TreeNode childNode=new DefaultTreeNode(k, node);             
             recursive(subList2, k.getProductchild().getProductId(),k,childNode);
          }
    }

    public  List<ProductmaterailModel> subKategori(int i,ProductmaterailModel category)
    {	    	
    
        araListe=new ArrayList<ProductmaterailModel>();
        for(ProductmaterailModel k:getListe()){
            if(k.getProductparent().getProductId()==i){
            	
            	ProductmaterailModel cmodelTemp = new ProductmaterailModel();            	
                araListe.add(k);           
                productcategories.add(k); 
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
    public static List<ProductmaterailModel> getListe() {
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

   public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());			
	}
   
  
   public void addBomItem() {
	    ProductmaterailModel item = this.productcategory;
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
		
		boolean exits = false;
		try {
			producttemp = productBO.getProductList(null, null, null, this.product.getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}

			for (ProductmaterailModel data : productcategories) {
				
				if (data.getProductchild().getBarcode().equalsIgnoreCase(this.product.getBarcode())) {
					ProductmaterailModel c = productcategories.get(rowCount);
			
					item.setProduct(productBean.getProduct());
					item.setProductparent(productBean.getProduct());			
					item.setProductchild(producttemp);			
					item.setQuantity(item.getQuantity().add(c.getQuantity()));
					productcategories.set(rowCount, item);	
					
					datamatch = true;
				}
				rowCount++;
			}
			if (!datamatch) {
			
				item.setProduct(productBean.getProduct());
				item.setProductparent(productBean.getProduct());			
				item.setProductchild(producttemp);	
				
				productcategories.add(item);
			}
				
			resetAddItem();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
   public void resetAddItem()
   {
	   ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
	   productcategory = new ProductmaterailModel();
	   productcategory.setProduct(productBean.getProduct());
	   product = new ProductModel();
   }
   
}
