package com.project.bean.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.primefaces.model.UploadedFile;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import org.primefaces.component.datatable.DataTable;

import com.project.bean.sales.sale.OrderBean;
import com.project.bean.sales.sale.PosBean;
import com.project.bean.sales.sale.SalesorderBean;
//import com.project.bo.impl.Image;
//import com.project.bo.impl.ImageIcon;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductbundleModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.SupplierdocumentModel;
import com.project.model.datamodel.TaxmasterModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.BranchstockProductModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.ProductPagingHelper;
import com.project.model.sale.sales.SalesorderbreakdownModel;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 08 July 2013
 * 
 */

@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean {
	 
	 private UploadedFile image;
	 private String imageFileName;
	// private String imageForPos="productImages/25_nasi_lemak.jpg";

	
	private Integer categoryId;
	private String categoryName;
	
	private Integer supplierId;	
	private BigDecimal quantityFrom;
	private BigDecimal quantityOldFrom;
	private Integer rowId;
	private BigDecimal discountAmount;
	private Integer productId;	
	
	private String barcode;
	private String status;
	private String action = "submit";
	private String supplieraction = "submit";
	private String itemaction = "submit";
	private Boolean isSetItem=false;
	String productCodeFilter;
	String productNameFilter;
	
	
	String productName;
	List<String> productNameList = new ArrayList<>();  

	String brandName;
	String salesType;
	List<Integer> categoryIds = new ArrayList<Integer>();
	Integer branchId;
	private BigDecimal itemCount;
	String requestIdsbuf="";
	List<TaxmasterModel> taxList = new ArrayList<TaxmasterModel>();
	
	FacesContext context = FacesContext.getCurrentInstance();
	ProductModel product = new ProductModel();
	ProductpriceModel productprice = new ProductpriceModel();
	private LazyDataModel<ProductModel> productModel = null;
	List<ProductsupplierModel> supplierlist = new ArrayList<ProductsupplierModel>();
	List<ProductpriceModel> pricelist = new ArrayList<ProductpriceModel>();
	List<ProductpriceModel> pricelistTemp = new ArrayList<ProductpriceModel>();
    List<BigDecimal> pricingIds = new ArrayList<BigDecimal>();
    CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
    CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
    ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
    LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
    Configuration config = Configuration.getConfiguration();
    private boolean productCodedisable=false;
    List<BranchstockProductModel> productstocklist = new ArrayList<BranchstockProductModel>();
    List<ProductbundleModel> productbundleList = new ArrayList<ProductbundleModel>();
    ProductModel productb = new ProductModel();
    List<ProductModel> productAll = new ArrayList<ProductModel>();
    List<ProductModel> quickListProducts =new ArrayList<ProductModel>();
    List<ProductModel> nonQuickListProductObjList =new ArrayList<ProductModel>();
    List<ProductModel> selectedProductList=new ArrayList<ProductModel>();
    List<ProductModel> selectedProductQuickList=new ArrayList<ProductModel>();
    List<ProductModel> unFilteredProductList=new ArrayList<ProductModel>();
    List<ProductModel> unFilteredProductListInitLoad=new ArrayList<ProductModel>();
    
    List<ProductModel> individualProductList=new ArrayList<ProductModel>();
    DualListModel<ProductModel> dualList=new DualListModel<ProductModel>();
    
    private Map<String, Boolean> selectedTax = new HashMap<String, Boolean>();
    
    protected int first;  
    
    
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName =this.getProduct().getBarcode();
		//this.imageFileName=this.getProduct().getProductName();
	}
	

	public List<ProductModel> getUnFilteredProductList() {
		return unFilteredProductList;
	}

	public void setUnFilteredProductList(List<ProductModel> unFilteredProductList) {
		this.unFilteredProductList = unFilteredProductList;
	}

	public List<ProductModel> getUnFilteredProductListInitLoad() {
		return unFilteredProductListInitLoad;
	}

	public void setUnFilteredProductListInitLoad(List<ProductModel> unFilteredProductListInitLoad) {
		this.unFilteredProductListInitLoad = unFilteredProductListInitLoad;
	}

	public List<ProductModel> getIndividualProductList() {
		return individualProductList;
	}

	public void setIndividualProductList(List<ProductModel> individualProductList) {
		this.individualProductList = individualProductList;
	}

	public DualListModel<ProductModel> getDualList() {
		return dualList;
	}

	public void setDualList(DualListModel<ProductModel> dualList) {
		this.dualList = dualList;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
		 if (null!=image) {
			 setImageFileName(image.getFileName());
		 }
		
	}
    
    
    public int getFirst() {  
         return first;  
    }  
    public void setFirst(int first) {
    	
         this.first = first;  
    }  
    public void onPageChange(PageEvent event) {  
         this.setFirst(((DataTable) event.getSource()).getFirst());  
    }  
    
    
	List<Integer> supplierIds= new ArrayList<Integer>();
	List<Integer> productbundleIds= new ArrayList<Integer>();
	
	IProductBO productBO=objectMapController.getProductBO();	
	
	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}

	ISupplierBO supplierBO=objectMapController.getSupplierBO();		
	
	public ISupplierBO getSupplierBO() {
		return supplierBO;
	}

	public void setSupplierBO(ISupplierBO supplierBO) {
		this.supplierBO = supplierBO;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}	
	
	public String getSalesType() {
		return salesType;
	}
	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	
	
	public Boolean getIsSetItem() {
		return isSetItem;
	}

	public void setIsSetItem(Boolean isSetItem) {
		this.isSetItem = isSetItem;
	}

	public String getProductCodeFilter() {
		return productCodeFilter;
	}

	public void setProductCodeFilter(String productCodeFilter) {
		this.productCodeFilter = productCodeFilter;
	}

	public String getProductNameFilter() {
		return productNameFilter;
	}

	public void setProductNameFilter(String productNameFilter) {
		this.productNameFilter = productNameFilter;
	}

	public BigDecimal getItemCount() {
		return itemCount;
	}
	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public BigDecimal getQuantityFrom() {
		return quantityFrom;
	}

	public void setQuantityFrom(BigDecimal quantityFrom) {
		this.quantityFrom = quantityFrom;
	}
	
	public BigDecimal getQuantityOldFrom() {
		return quantityOldFrom;
	}

	public void setQuantityOldFrom(BigDecimal quantityOldFrom) {
		this.quantityOldFrom = quantityOldFrom;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}	
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductModel getProduct() {
		return product;
	}	
	
	public ProductpriceModel getProductprice() {
		return productprice;
	}

	public void setProductprice(ProductpriceModel productprice) {
		this.productprice = productprice;
	}

	public List<BigDecimal> getPricingIds() {
		return pricingIds;
	}

	public void setPricingIds(List<BigDecimal> pricingIds) {
		this.pricingIds = pricingIds;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}	

	public List<ProductsupplierModel> getSupplierlist() {
		return supplierlist;
	}
	
	public List<Integer> getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(List<Integer> supplierIds) {
		this.supplierIds = supplierIds;
	}		
	
	public List<Integer> getProductbundleIds() {
		return productbundleIds;
	}
	public void setProductbundleIds(List<Integer> productbundleIds) {
		this.productbundleIds = productbundleIds;
	}
	public List<ProductbundleModel> getProductbundleList() {
		return productbundleList;
	}
	public void setProductbundleList(List<ProductbundleModel> productbundleList) {
		this.productbundleList = productbundleList;
	}
	public String getRequestIdsbuf() {
		return requestIdsbuf;
	}
	public void setRequestIdsbuf(String requestIdsbuf) {
		this.requestIdsbuf = requestIdsbuf;
	}
	public List<Integer> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}
	public void setSupplierlist(List<ProductsupplierModel> supplierlist) {
		this.supplierlist = supplierlist;
	}

	public LazyDataModel<ProductModel> getProductModel() {
		return productModel;
	}

	public void setProductModel(LazyDataModel<ProductModel> productModel) {
		this.productModel = productModel;
	}	
	
	
	public ProductModel getProductb() {
		return productb;
	}
	public void setProductb(ProductModel productb) {
		this.productb = productb;
	}
	public boolean isProductCodedisable() {
		return productCodedisable;
	}

	public void setProductCodedisable(boolean productCodedisable) {
		this.productCodedisable = productCodedisable;
	}	
	
	public String getSupplieraction() {
		return supplieraction;
	}
	public void setSupplieraction(String supplieraction) {
		this.supplieraction = supplieraction;
	}	
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}	
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
		
	public String getItemaction() {
		return itemaction;
	}
	public void setItemaction(String itemaction) {
		this.itemaction = itemaction;
	}	
	
	
	public List<TaxmasterModel> getTaxList() {
		return taxList;
	}
	public void setTaxList(List<TaxmasterModel> taxList) {
		this.taxList = taxList;
	}
	
	
	public Map<String, Boolean> getSelectedTax() {
		return selectedTax;
	}
	public void setSelectedTax(Map<String, Boolean> selectedTax) {
		this.selectedTax = selectedTax;
	}
	public LazyDataModel<ProductModel> getProductList() {	
		try
		{
		this.setProductModel(null);
		productModel = new ProductPagingHelper(categoryIds, this.getSupplierId(), this.getBarcode(),this.getStatus(),productBO,loginBean.getBranch().getBranchId(),this.getProductName(),null,this.getBrandName(),this.getSalesType());			
		this.setProductModel(productModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return productModel;
	}	
	
	public List<ProductpriceModel> getPricelist() {
		return pricelist;
	}

	public void setPricelist(List<ProductpriceModel> pricelist) {
		this.pricelist = pricelist;
	}

	public List<ProductpriceModel> getPricelistTemp() {
		return pricelistTemp;
	}	

	public void setPricelistTemp(List<ProductpriceModel> pricelistTemp) {
		this.pricelistTemp = pricelistTemp;
	}	
	
	public String getProductName() {

		return productName;
	}
	public void setProductName(String productName) {

		this.productName = productName;
	}		
	
	
	public List<String> getProductNameList() {
		return productNameList;
	}

	public void setProductNameList(List<String> productNameList) {
		this.productNameList = productNameList;
	}

	public List<BranchstockProductModel> getProductstocklist() {
		return productstocklist;
	}
	public void setProductstocklist(List<BranchstockProductModel> productstocklist) {
		this.productstocklist = productstocklist;
	}
	
	public List<ProductModel> getProductAll() {
		return productAll;
	}
	public void setProductAll(List<ProductModel> productAll) {
		this.productAll = productAll;
		
	}
	
	
	
	public List<ProductModel> getQuickListProducts() {
		return quickListProducts;
	}

	public void setQuickListProducts(List<ProductModel> quickListProducts) {
		this.quickListProducts = quickListProducts;
	}

	public List<ProductModel> getNonQuickListProductObjList() {
		return nonQuickListProductObjList;
	}

	public void setNonQuickListProductObjList(List<ProductModel> nonQuickListProductObjList) {
		this.nonQuickListProductObjList = nonQuickListProductObjList;
	}

	public List<ProductModel> getSelectedProductList() {
		return selectedProductList;
	}

	public void setSelectedProductList(List<ProductModel> selectedQuickListProducts) {
		this.selectedProductList = selectedQuickListProducts;
	}

	public List<ProductModel> getSelectedProductQuickList() {
		return selectedProductQuickList;
	}

	public void setSelectedProductQuickList(List<ProductModel> selectedProductQuickList) {
		this.selectedProductQuickList = selectedProductQuickList;
	}

	public List<TaxmasterModel> getBranchTaxList() {
		try {
			taxList = commonListBean.getCommonListBO().getTaxmasterList(loginBean.getBranch().getBranchId());				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxList;
	}
	
	public void listProduct() {		
		this.searchProduct1();
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.listProduct();
	}

	public void listvoidProduct() {	
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.listProduct();
	}	
	
	public void listStock() {		
		resetSearchProduct1();
		this.searchProduct1();
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.listStock();
	}
	
	public void listvoidStock() {	
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.listStock();
	}	
	
	
	public void newProduct() {
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.newProduct();	
		try {
			
			this.individualProductList=productBO.findByProductAll();
			List<ProductModel> productSource=new ArrayList<ProductModel>();
			List<ProductModel> productTarget=new ArrayList<ProductModel>();
			
			for(ProductModel data:this.individualProductList) {
				ProductModel obj=new ProductModel();
				obj.setProductId(data.getProductId());
				obj.setProductName(data.getProductName());
				obj.setBarcode(data.getBarcode());
				productSource.add(obj);
			}
			this.dualList=new DualListModel<ProductModel>(productSource,productTarget);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetProduct();
	}
	
	
	public void newProductbundle() {
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.newProductbundle();	
		resetProduct();
	}
	
	public void saveProduct()
	{
		
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		product.setSalesType("normal");
		productBeanInfo.saveProduct();
	}
	
	
	public void saveProductBundle()
	{
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();	
		product.setSalesType("bundle");		
		
		ProductsupplierModel supplier = new ProductsupplierModel();
		supplier.setSupplierId(1);
		supplier.setPurchasePrice(new BigDecimal(0.00));
		supplierlist.add(supplier);		
		productBeanInfo.saveProduct();		
	}
	
	public void saveProductPrice()
	{
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.saveProductPrice();		
	}
	
	public void updateProduct()
	{
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.updateProduct();		
	}
	public void deleteProducth()
	{
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.deleteProduct();		
	}	
	public void resetProduct()
	{
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		productBeanInfo.resetProduct();
		
	}
	
	public void resetSupplierItem()
	{		
		this.product.setSupplierpurchasePrice(new BigDecimal(0.00));		
		this.setSupplieraction("submit");
		this.setProductCodedisable(false);
		this.product.setSupplierId(0);
	}
	
	public void searchProduct()
	{
		try
		{
			this.setSalesType("normal");
			this.getProductList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void loadProduct()
	{
		
		try
		{
			this.setProductAll(productBO.getProductListReport(null, null, null, null, loginBean.getBranch().getBranchId(), null, null,null));
			this.setUnFilteredProductList(this.getProductAll());

			loadCustomProductList();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//mobile
		public void checkOrderedProduct(List<SalesorderbreakdownModel> salesorderbreakdowns) {
			for(ProductModel productObj:this.getProductAll()) {
				for(SalesorderbreakdownModel sobObj:salesorderbreakdowns)
					
				if(productObj.getProductId()==sobObj.getProductId()) {
					productObj.setStatus("1");
					
				}
				
			}
		}
	
	public void loadAllProduct()
	{
		try
		{
			this.setProductAll(productBO.getProductListReport(categoryName, null, null, null, loginBean.getBranch().getBranchId(), null, null,null));
			this.setUnFilteredProductList(this.getProductAll());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void loadHotListProduct() {
		try {
			this.setProductAll(productBO.getHotListProduct(loginBean.getBranch().getBranchId()));
			this.setUnFilteredProductList(this.getProductAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void loadCustomProductList() {
		
		try {
			List<ProductModel> productObjList =new ArrayList<ProductModel>();
			List<ProductModel> nonQuickObjList=new ArrayList<ProductModel>();
			for(ProductModel p : getProductAll()) {
				
				ProductModel obj=new ProductModel();
				if(p.getIsCustomeList() ==1)
				{
					
					obj=p;
					productObjList.add(obj);
				}else {
					obj=p;
					nonQuickObjList.add(p);
					
				}
			}
			
			/*for(int i=0;i<getProductAll().size();i++) {
				ProductModel p=getProductAll().get(i);
				if(p.getIsCustomeList() == 1)
					
				
			}
			*/
			this.setNonQuickListProductObjList(nonQuickObjList);
			this.setQuickListProducts(productObjList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void resetSearchProduct()
	{
		this.supplierId=0;
		this.barcode=null;
		this.categoryId=0;
		this.status=null;
		this.brandName=null;
		this.setSalesType("normal");
		this.getProductList();
		categoryIds.clear();
	}

	
	public void searchProduct1()
	{
		try
		{
			this.setSalesType(null);
			this.getProductList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetSearchProduct1()
	{
		this.supplierId=0;
		this.barcode=null;
		this.categoryId=0;
		this.status=null;
		this.brandName=null;
		this.setProductName(null);
		this.setSalesType(null);
		this.getProductList();
		categoryIds.clear();
	}

	
	public void clearProduct()
	{
		this.supplierId=0;
		this.barcode=null;
		this.categoryId=0;
		this.status=null;
		this.brandName=null;
		//this.getProductList();
		this.setProductModel(null);
		categoryIds.clear();
	}
	
	
	public void searchBranchStockProduct()
	{
		try
		{			
			productstocklist.clear();
			if(this.getProduct()!=null)
			{
			if (this.product.getBarcode() != null && this.product.getBarcode() != "" && (!this.product.getBarcode().equalsIgnoreCase("0")) && (!this.product.getBarcode().equalsIgnoreCase(""))) 
			{				
				productstocklist=productBO.getProductBranchStockList(this.product.getBarcode(), null, null);
			}	
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());	
		this.setBarcode(this.getProduct().getBarcode());
		//searchBranchStockProduct();
	}
	
	
	public void selectProduct1(SelectEvent event)
	{
		this.productb=((ProductModel) event.getObject());	
		this.setBarcode(this.getProduct().getBarcode());
		//searchBranchStockProduct();
	}
	
	
	public void handleSelect(SelectEvent event) {  
		this.productb=((ProductModel) event.getObject());
	}
	
	public LazyDataModel<ProductModel> getBranchProductList() {	
		try
		{
		this.setProductModel(null);			
		productModel = new ProductPagingHelper(categoryIds, this.getSupplierId(), this.getBarcode(),this.getStatus(),productBO,this.getBranchId(),this.getProductName(),null,this.getBrandName(),null);			
		this.setProductModel(productModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return productModel;
	}	
	
	
	public void searchBranchProduct()
	{
		this.setProductModel(null);
		try
		{	if(validateBranchStock())
			{
			this.getBranchProductList();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetSearchBranchProduct()
	{		
		this.supplierId=0;
		this.barcode=null;
		this.brandName=null;
		this.categoryId=0;
		this.status=null;		
		searchBranchProduct();
		categoryIds.clear();		
	}

	public boolean validateBranchStock()
	{
		boolean valid= true;		
		if(this.getBranchId()==null)
		{
			valid=false;
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchId").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Branch !",null));
		}
		else if(this.getBranchId()==0)
		{
			valid=false;
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchId").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Branch !",null));
		}		
		return valid;
	}
	
	
	public void editProduct(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String productId = "";
		productId = (String) event.getComponent().getAttributes().get("productId").toString();
		supplierIds.clear();
		this.setSupplieraction("submit");
		this.setProductCodedisable(false);
		this.setProductId(Integer.parseInt(productId));
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		
		ProductmaterailBean productmaterailBean = (ProductmaterailBean) BeanContext.getReference("productmaterailBean");
		productmaterailBean.getDefaulProductMaterial();
		
		productBeanInfo.editProduct(Integer.parseInt(productId));
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void selectCategory(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		ProductcategoryModel productcategoryModel=null;
		categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();		
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productcategoryModel=productCategoryBeanInfo.selectCategory(Integer.parseInt(categoryId));
		this.setCategoryId(productcategoryModel.getCategoryId());
		this.setCategoryName(productcategoryModel.getName());
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void selectCategoryListReport(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		ProductcategoryModel productcategoryModel=null;		
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		categoryIds.clear();
		productCategoryBean.getDefaultRootNode();
		for(TreeNode node : productCategoryBean.getSelectedNodes()) {			
			productcategoryModel=(ProductcategoryModel) node.getData();			
			if(!categoryIds.contains(productcategoryModel.getCategoryId()))
			{
			categoryIds.add(productcategoryModel.getCategoryId());
			}
		}	
		
		StringBuilder requestIdsbufTemp = new StringBuilder("");
		String comma=",";
		int count=0;
	
		for(Integer id:categoryIds)
		{
			if(count==categoryIds.size()-1)
			{
				requestIdsbufTemp.append(String.valueOf(id));
			}
			else
			{
				requestIdsbufTemp.append(String.valueOf(id).concat(comma));	
			}
			count=count+1;
		}		
		
		this.setRequestIdsbuf(requestIdsbufTemp.toString());
		
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void selectCategoryList(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		ProductcategoryModel productcategoryModel=null;		
		ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
		categoryIds.clear();
		//productCategoryBean.getDefaultRootNode();
		for(TreeNode node : productCategoryBean.getSelectedNodes()) {			
			productcategoryModel=(ProductcategoryModel) node.getData();			
			if(!categoryIds.contains(productcategoryModel.getCategoryId()))
			{
			categoryIds.add(productcategoryModel.getCategoryId());
			}
		}	
		
		StringBuilder requestIdsbufTemp = new StringBuilder("");
		String comma=",";
		int count=0;
	
		for(Integer id:categoryIds)
		{
			if(count==categoryIds.size()-1)
			{
				requestIdsbufTemp.append(String.valueOf(id));
			}
			else
			{
				requestIdsbufTemp.append(String.valueOf(id).concat(comma));	
			}
			count=count+1;
		}		
		
		this.setRequestIdsbuf(requestIdsbufTemp.toString());		
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	
	public void selectCategoryNew(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String categoryId = "";
		ProductcategoryModel productcategoryModel=null;
		categoryId = (String) event.getComponent().getAttributes().get("categoryId").toString();		
		ProductCategoryBeanInfo productCategoryBeanInfo = new ProductCategoryBeanInfo();
		productcategoryModel=productCategoryBeanInfo.selectCategory(Integer.parseInt(categoryId));		
		this.product.setCategoryId(productcategoryModel.getCategoryId());			
		this.setCategoryName(productcategoryModel.getName());
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCategoryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void onRowSelect(SelectEvent event) {  
	       
		FacesContext context = FacesContext.getCurrentInstance();
		this.setProduct(null);
		try {						
			product=((ProductModel) event.getObject());	
			product=productBO.getProductDetails(product.getProductId(),loginBean.getBranch().getBranchId());
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
		
    }  
	
	
	public void viewProduct(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		this.setProduct(null);
		String productId = (String) event.getComponent().getAttributes().get("productId").toString();	
		product=productBO.getProductDetails(Integer.parseInt(productId),loginBean.getBranch().getBranchId());	
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void getDynamicSuplierItemList(ProductsupplierModel item)	
	{
		this.supplierlist.add(item);
		supplierIds.add(item.getSupplierId());
	}
	
	
	public void removeSupplierItem(ActionEvent event)
	{
		String supplierId = "";
		supplierId = (String) event.getComponent().getAttributes().get("supplierId").toString();
		this.setRowId(Integer.parseInt(supplierId));
	}
	
	public void removeSupplierItemConfirm()
	{
		ProductsupplierModel c = supplierlist.get(this.getRowId());
		supplierlist.remove(c);
		supplierIds.clear();
		for(ProductsupplierModel item:supplierlist)
		{
		supplierIds.add(item.getSupplierId());
		}
	}	
	
	
	public void editSupplierItem(ActionEvent event)
	{
		String supplierId = "";
		supplierId = (String) event.getComponent().getAttributes().get("supplierId").toString();		
		this.setRowId(Integer.parseInt(supplierId));
		ProductsupplierModel c = supplierlist.get(this.getRowId());
		this.product.setSupplierId(c.getSupplierId());
		this.product.setSupplierpurchasePrice(c.getPurchasePrice());
		this.setSupplieraction("update");
		this.setProductCodedisable(true);
	}
	
	public void updateSupplierItemConfirm()
	{
		ProductsupplierModel c = supplierlist.get(this.getRowId());
		c.setPurchasePrice(product.getSupplierpurchasePrice());
		supplierlist.set(this.getRowId(), c);
		this.setSupplieraction("submit");
		this.setProductCodedisable(false);
		this.product.setSupplierId(0);
		this.product.setPurchasePrice(c.getPurchasePrice());
	}		
	
	public void addSupplierItem()
	{			
		ProductsupplierModel item = new ProductsupplierModel();		
		SupplierModel supplier = new SupplierModel();	
		FacesContext context = FacesContext.getCurrentInstance();		
		
		 	try {	if(validateAddItem())
		 			{
		 			supplier=supplierBO.getSupplierDetails(product.getSupplierId());
			 		
		 			item.setSupplierAddress(supplier.getAddress());
		 			item.setSupplierId(supplier.getSupplierId());
		 			item.setSupplierName(supplier.getCompanyName());
		 			item.setPurchasePrice(product.getSupplierpurchasePrice());
		 			
		 			this.getDynamicSuplierItemList(item);		 			
		 			this.product.setSupplierId(0);
		 			this.product.setSupplierpurchasePrice(new BigDecimal("0.00"));
		 			this.product.setPurchasePrice(product.getSupplierpurchasePrice());
		 			}		 		   
		 		
		} catch (Exception e) {			
			
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}			
	}
	
	
	public void resetItem()
	{
		this.product.setSupplierId(0);
	}
	
	
	public void getDynamicPriceItemList(ProductpriceModel item)	
	{
		this.pricelist.add(item);
		pricingIds.add(item.getQuantityFrom());
	}	
	
	public void getLatestPriceIds()
	{
		pricingIds.clear();		
		for(ProductpriceModel data:pricelist)
		{
			pricingIds.add(data.getQuantityFrom());
		}
	}

	public void resetPriceItem()
	{
		this.setDiscountAmount(new BigDecimal("0.00"));
		this.setQuantityFrom(new BigDecimal(0.00));
		this.setProductCodedisable(false);
		this.setAction("submit");
	}
	
	public void getProductPrice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		ProductModel dataTemp = null;
		dataTemp = (ProductModel) event.getComponent().getAttributes().get("product");
		productprice.setUnitPrice(dataTemp.getDoctorPrice());
		
		this.setBarcode(dataTemp.getBarcode());
		ProductBeanInfo productBeanInfo = new ProductBeanInfo();
		pricelist=productBeanInfo.getProductPrice(dataTemp.getBarcode(),dataTemp);
		resetPriceItem();
		productBeanInfo.productPrice();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void editProductPrice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String productPriceId = "";
		productPriceId = (String) event.getComponent().getAttributes().get("productPriceId").toString();
		ProductpriceModel c = pricelist.get(Integer.parseInt(productPriceId));
		this.setQuantityFrom(c.getQuantityFrom());
		this.setQuantityOldFrom(c.getQuantityFrom());		
		this.setDiscountAmount(c.getDiscountAmount());
		this.setAction("update");
		this.setRowId(Integer.parseInt(productPriceId));
		
		if(c.getQuantityFrom().doubleValue()==1)
		{
			this.setProductCodedisable(true);
		}
		else
		{
			this.setProductCodedisable(false);
		}
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void updateProductPrice() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		
		ProductpriceModel c = pricelist.get(this.getRowId());	
		getLatestPriceIds();
		if(validateAddProductPriceItem())
		{			
		c.setQuantityFrom(this.getQuantityFrom());
		c.setDiscountAmount(this.getDiscountAmount());
		c.setDiscountPrice(c.getUnitPrice().subtract(this.getDiscountAmount()));
		pricelist.set(this.getRowId(), c);		
		this.setAction("submit");
		resetPriceItem();
		}
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void searchProductPrice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			ProductModel producttemp = productBO.getProductList(null, null, null, barcode, null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);

			ProductBeanInfo productBeanInfo = new ProductBeanInfo();
			pricelist=productBeanInfo.getProductPrice(barcode,producttemp);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
	}
	
	
	public void addPriceItem()
	{			
		ProductpriceModel item = new ProductpriceModel();		
		FacesContext context = FacesContext.getCurrentInstance();	
		this.setQuantityOldFrom(new BigDecimal(0.00));
		getLatestPriceIds();
		this.setAction("submit");
		 try {	
		 		if(validateAddProductPriceItem())
		 			{		 		
		 			item.setBarCode(this.getBarcode());
		 			item.setDiscountAmount(this.getDiscountAmount());
		 			item.setQuantityFrom(this.getQuantityFrom());
		 			item.setProductName(productprice.getProductName());
		 			item.setUom(productprice.getUom());
		 			item.setProductId(productprice.getProductId());
		 			item.setUnitPrice(productprice.getUnitPrice());
		 			item.setDiscountPrice(productprice.getUnitPrice().subtract(this.getDiscountAmount()));
		 			this.getDynamicPriceItemList(item);	
		 			this.setProductId(productprice.getProductId());
		 			resetPriceItem();
		 			}
		} catch (Exception e) {					
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}		
		
	}	
	
	public String onFlowProcess(FlowEvent event) {   
        
         ProductBeanInfo productBeanInfo = new ProductBeanInfo();
         return productBeanInfo.onFlowProcess(event);	
 		
    }  	
	
	public void removeItem(ActionEvent event)
	{
		String priceBreakdownId = "";
		priceBreakdownId = (String) event.getComponent().getAttributes().get("priceBreakdownId").toString();
		this.setRowId(Integer.parseInt(priceBreakdownId));
	}
	
	public void removeItemConfirm()
	{
		ProductpriceModel c = pricelist.get(this.getRowId());			
		pricelist.remove(c);
	}		
	
	
	public boolean validateAddItem()
	{
		boolean valid=true;
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			if(product.getSupplierId()==0)
 			{	
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierName").getClientId(context),
		 		new FacesMessage(FacesMessage.SEVERITY_ERROR,"Select the Supplier",null));
 			}			
			if(product.getSupplierpurchasePrice()==null && product.getSupplierpurchasePrice().doubleValue()==0)
			{
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierpurchasePrice").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Supplier Price !",null));
			}
			if(supplierIds.contains(product.getSupplierId()))
			{
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierName").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Supplier already Exists !",null));
			}
		}
		catch(Exception e)
		{
			
		}
		return valid;
	}
	
	
	
	public boolean validateAddProductPriceItem()
	{
		boolean valid=true;
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			if(this.getQuantityFrom()!=null && this.getQuantityFrom().doubleValue()==0)
 			{	
				valid=false;
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quantityFrom").getClientId(context),
		 		new FacesMessage(FacesMessage.SEVERITY_ERROR," Minimum Quantity not valid",null));
 			}			
			if (factoryBean.checkIsNullAssignMessage(this.getQuantityFrom(),
					"product.label.barcode", "quantityFrom")) {
				valid = false;
			}			
			else {
				if (this.getAction().equalsIgnoreCase("submit")) {
					if(pricingIds.contains(this.getQuantityFrom()))
					{
						valid=false;
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quantityFrom").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Entered Minimum Quantity already Exists !",null));
					}
				}else{  					
					if(!String.valueOf(this.getQuantityOldFrom()).equalsIgnoreCase(String.valueOf(this.getQuantityFrom()))){						
						if(pricingIds.contains(this.getQuantityFrom()))
						{
							valid=false;
							context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quantityFrom").getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"Entered Minimum Quantity already Exists !",null));
						}
					}
				}
			}		
		}
		catch(Exception e)
		{
			
		}
		return valid;
	}	
	
	
	public void printProductReport(ActionEvent event) {			
	
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		//String purchaseRequestId = "";
		//purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/stock/hqstockList.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&categoryId=" + requestIdsbuf +"&barcode="+barcode+"&productName="+productName+"&status="+status+"&branchId="+loginBean.getBranch().getBranchId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void printProductBranchReport(ActionEvent event) {			
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		//String purchaseRequestId = "";
		//purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/stock/branchstockList.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&categoryId=" + requestIdsbuf +"&barcode="+barcode+"&productName="+productName+"&status="+status+"&branchId="+branchId );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	
	
		public void printProductDetailReport(ActionEvent event) {			
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String productId = "";
		productId = (String) event.getComponent().getAttributes().get("productId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/stock/productDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&productId=" + productId +"&branchId="+loginBean.getBranch().getBranchId()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
		
		public void printProductBranchDetailReport(ActionEvent event) {			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			String productId = "";
			String branchId = "";
			productId = (String) event.getComponent().getAttributes().get("productId").toString();
			branchId = (String) event.getComponent().getAttributes().get("branchId").toString();
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/stock/productDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&productId=" + productId +"&branchId="+branchId+"&userId="+loginBean.getUserId() );
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}	

		
		public ServletContext getServeletContx()
		{
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext(); 	

			return servletContext;
		}


	   /* public void upload(FileUploadEvent event) {  	      
	    	FacesContext context = FacesContext.getCurrentInstance();	
	    	String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
	    	String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
	    	
	    	uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
	    	uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
	    			  
	        try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      
	    }  

	    public void copyFile(String fileName, InputStream in) {
	           try {
	        	    String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
	        		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
	        		
	        		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
	        		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
	        			
	        		String destination=uploadtemplocation;    
	        		
	        		String trainingDir = "C:/supplier/";    			
	    			File trDir = new File(trainingDir);   
	    			if(!trDir.exists())
	    			{
	    				trDir.mkdir();
	    			}     			
	    			
	                OutputStream out = new FileOutputStream(new File(trainingDir + fileName));             
	                int read = 0;
	                byte[] bytes = new byte[1024];
	             
	                while ((read = in.read(bytes)) != -1) {
	                    out.write(bytes, 0, read);
	                }             
	                in.close();
	                out.flush();
	                out.close();              
	                uploadfileRead(new File(trainingDir + fileName));
	                
	                } catch (IOException e) {
	                System.out.println(e.getMessage());
	                }           
	    		} 	   
	    
		*/
		@SuppressWarnings("unchecked")
		public void uploadfileRead(File file) throws IOException {
			try {		
				ProductBeanInfo info = new ProductBeanInfo();
			List<ProductModel> productModel=null;		
			int rowcount = 0;	
			productModel=new ArrayList<ProductModel>();
			productModel.clear();
			productModel = extractExcelFile(file);
			info.saveProductList(productModel);
			//postProcessXLSApportionKnockOff(productModel);
			} catch (Exception e) {
				//log.error("Exception @ TreatmentCollectionBeanInfo.uploadfileRead() :  " + e.getMessage());
			}

		}				
		
		
		public List<ProductModel> extractExcelFile(File file) {
			FileInputStream fileInputStream = null;
			POIFSFileSystem fsFileSystem = null;
		
			Sheet hssfSheet = null;
			Iterator rowIterator = null;

			List<ProductModel> productListObj = null;
			int rowPosition = 0;
			int rowcount = 0;
			try {
				productListObj = new ArrayList<ProductModel>();
				fileInputStream = new FileInputStream(file);
				Workbook wb =null ;// WorkbookFactory.create(fileInputStream);
				hssfSheet =  wb.getSheetAt(0);
				rowIterator = hssfSheet.rowIterator();
				while (rowIterator.hasNext()) {
					Row hssfRow = (Row) rowIterator.next();		
					rowPosition++;
					if (isEmptyRow(hssfRow)){
						continue;
					}
					
					ProductModel productObj = new ProductModel();		
					ProductsupplierModel supplier = new ProductsupplierModel();
					List<ProductsupplierModel> supplierList = new ArrayList<ProductsupplierModel>();
					if(rowcount!=0)
					{				
						
					productObj.setBarcode(convertCellValue(hssfRow.getCell(7)).replace("'", ""));					
					productObj.setCreatedBy("");
					productObj.setCreatedDate(new Date());
					productObj.setDescription(convertCellValue(hssfRow.getCell(5)));
					productObj.setDiscount(new BigDecimal("0.00"));
					
					productObj.setHeight("");
					productObj.setLastModifiedDate(new Date());
					productObj.setLength("0");
					productObj.setProductCode(convertCellValue(hssfRow.getCell(7)).replace("'", ""));
					productObj.setProductName(convertCellValue(hssfRow.getCell(1)));	
					productObj.setBrandName(convertCellValue(hssfRow.getCell(1)));
					
					productObj.setReorderQuantity(100);
					productObj.setStatus("1");
					productObj.setWeight("0");
					productObj.setWidth("0");
					productObj.setUom("PCS");
					productObj.setProductDesc(convertCellValue(hssfRow.getCell(5)));
					productObj.setQuantityonHand(new BigDecimal(0.00));
					
					if (ValidatorUtil.isNotNull(String.valueOf(convertCellValue(hssfRow.getCell(4))))) {						
						productObj.setNormalPrice(bigDecimalCheck(String.valueOf(convertCellValue(hssfRow.getCell(4)))));
					} else {
						productObj.setNormalPrice(new BigDecimal(0));
					}
					
					if (ValidatorUtil.isNotNull(String.valueOf(convertCellValue(hssfRow.getCell(4))))) {						
						productObj.setPurchasePrice(bigDecimalCheck(String.valueOf(convertCellValue(hssfRow.getCell(3)))));
					} else {
						productObj.setPurchasePrice(new BigDecimal(0));
					}												
					productObj.setDoctorPrice(new BigDecimal("0.00"));
					productObj.setSalesrepPrice(new BigDecimal("0.00"));
					productObj.setQuantityonalert(100);
					productObj.setFullredemptionpoint(0);
					productObj.setHalfredemptionpoint(0);
					productObj.setRedemAmount(new BigDecimal("0.00"));
					productObj.setExpiryDate(new Date());
					productObj.setTaxAmount(new BigDecimal("0.00"));
					productObj.setGstAmount(new BigDecimal("0.00"));
					productObj.setTaxCode(new BigDecimal("0.00"));
					productObj.setGstCode(new BigDecimal("0.00"));
					productObj.setCustomerPoint(20);						
					
					supplier.setSupplierId(2);	
					supplier.setPurchasePrice(productObj.getPurchasePrice());	
					supplierList.add(supplier);					
					productObj.setSupplierlist(supplierList);					
					productListObj.add(productObj);					
					}
					rowcount = rowcount + 1;						
				}
			} 
			catch (Exception e) {
				//log.error("Exception @ extractExcelFile() : "+e.getMessage()); 
			}
			return productListObj;
		}

		
		
		public boolean isEmptyRow(Row hssfRow) {
			boolean isEmpty = true;

			try {
				if (convertCellValue(hssfRow.getCell(0)) != null
						&& convertCellValue(hssfRow.getCell(0)).length() > 0)
					isEmpty = false;
				if (convertCellValue(hssfRow.getCell(1)) != null
						&& convertCellValue(hssfRow.getCell(1)).length() > 0)
					isEmpty = false;
				if (convertCellValue(hssfRow.getCell(2)) != null
						&& convertCellValue(hssfRow.getCell(2)).length() > 0)
					isEmpty = false;
				if (convertCellValue(hssfRow.getCell(3)) != null
						&& convertCellValue(hssfRow.getCell(3)).length() > 0)
					isEmpty = false;
				if (convertCellValue(hssfRow.getCell(4)) != null
						&& convertCellValue(hssfRow.getCell(4)).length() > 0)
					isEmpty = false;
				if (convertCellValue(hssfRow.getCell(5)) != null
						&& convertCellValue(hssfRow.getCell(5)).length() > 0)
					isEmpty = false;
				
			} catch (Exception e) {
				//log.error("isEmptyRow() exception --->" + e.getStackTrace());
			}
			return isEmpty;
		}
	
		public String convertCellValue(Cell cell) {
			String strCell = "";
			try {
				if (cell != null
						&& cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					double numericDouble = cell.getNumericCellValue();
					strCell = Double.toString(numericDouble);
					strCell = strCell.substring(0, strCell.length() - 2);
				} else if (cell != null
						&& cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					strCell = cell.getStringCellValue();
				}	
				
			} catch (Exception e) {
				//log.info("convertCellValue exception --->" + e.getStackTrace());
				e.printStackTrace();
			}	
			return strCell.trim();
		}

		public boolean isEmptyValue(String filed) {
			boolean isEmpty = false;
			try {
				if (filed.equalsIgnoreCase(""))
					isEmpty = true;
			} catch (Exception e) {

			}
			return isEmpty;
		}	

		public BigDecimal bigDecimalCheck(String value) {
			BigDecimal defaultDecimal = new BigDecimal(0);
			if (ValidatorUtil.isValidDecimalNumber(value)) {
				defaultDecimal = new BigDecimal(value);
			} else {
				defaultDecimal = new BigDecimal(0);
			}
			return defaultDecimal;
		}	
				
		public void postProcessXLSApportionKnockOff(List<ProductModel> productList) {
			try {
				int rowcount = 1;
				int celcount = 0;
				HSSFWorkbook wb = new HSSFWorkbook();
		        HSSFSheet sheet = wb.createSheet("sheet1");
		        HSSFRow header = sheet.createRow(0);
		        header.setHeightInPoints(35);		    

				HSSFCellStyle cellStyle1 = wb.createCellStyle();
				cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyle1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);					
				
				HSSFCellStyle cellStyleDate1 = wb.createCellStyle();
				cellStyleDate1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
				cellStyleDate1.setAlignment(CellStyle.ALIGN_LEFT);
				cellStyleDate1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyleDate1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleDate1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleDate1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleDate1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);  				
				
				HSSFCellStyle cellStyleAmount = wb.createCellStyle();		
				cellStyleAmount.setAlignment(CellStyle.ALIGN_RIGHT);
				cellStyleAmount.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyleAmount.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleAmount.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleAmount.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleAmount.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);				

				sheet.setColumnWidth(0, 256 * 7);
				sheet.setColumnWidth(1, 256 * 15);
				sheet.setColumnWidth(2, 256 * 15);
				sheet.setColumnWidth(3, 256 * 10);
				sheet.setColumnWidth(4, 256 * 15);
				sheet.setColumnWidth(5, 256 * 20);				

				HSSFCell cell1 = header.createCell(0);
				HSSFCell cell2 = header.createCell(1);
				HSSFCell cell3 = header.createCell(2);
				HSSFCell cell4 = header.createCell(3);
				HSSFCell cell5 = header.createCell(4);
				HSSFCell cell6 = header.createCell(5);			
				
				cell1.setCellValue("Barcode");
				cell2.setCellValue("Name");
				cell3.setCellValue("Dept");
				cell4.setCellValue("Cost");
				cell5.setCellValue("Sales");
				cell5.setCellValue("Description");
				
				cell1.setCellStyle(cellStyle1);
				cell2.setCellStyle(cellStyle1);
				cell3.setCellStyle(cellStyle1);
				cell4.setCellStyle(cellStyle1);
				cell5.setCellStyle(cellStyle1);
				cell6.setCellStyle(cellStyle1);			
				
				Iterator iter = productList.iterator();
				int i = 0;

				while (iter.hasNext()) {
					i = i + 1;
					
					ProductModel apportionData = (ProductModel) iter.next();
					header = sheet.createRow(rowcount);
					header.setHeightInPoints(20);

					cell1 = header.createCell(0);
					cell2 = header.createCell(1);
					cell3 = header.createCell(2);
					cell4 = header.createCell(3);
					cell5 = header.createCell(4);
					cell6 = header.createCell(5);					
					
					cell1.setCellValue(String.valueOf(apportionData.getBarcode()));
					cell2.setCellValue(apportionData.getProductName());
					//cell3.setCellValue(apportionData.getInvoiceDate());

					cell4.setCellValue(String.valueOf(apportionData.getPurchasePrice()));
					cell5.setCellValue(String.valueOf(apportionData.getNormalPrice()));
					cell6.setCellValue(apportionData.getDescription());					
					
					cell1.setCellStyle(cellStyle);
					cell2.setCellStyle(cellStyle);
					cell3.setCellStyle(cellStyleDate1);
					cell4.setCellStyle(cellStyle);
					cell5.setCellStyle(cellStyle);
					cell6.setCellStyle(cellStyle);
					rowcount = rowcount + 1;
				}

				  FacesContext context = FacesContext.getCurrentInstance();
			        HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
			        res.setContentType("application/vnd.ms-excel");
			        res.setHeader("Content-disposition", "attachment;filename=mydata.xls");
			        res.setHeader("Expires", "0");
			        res.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");			
			        res.setHeader("Pragma", "public");
					
			        ServletOutputStream out = res.getOutputStream();
			        wb.write(out);
			        out.flush();
			        out.close();
			        FacesContext.getCurrentInstance().responseComplete();			        

			} catch (Exception e) {
				System.out.println(e);
			}
		}			
			
		
		public void addBundleItem()
		{			
			ProductbundleModel item = new ProductbundleModel();			
			FacesContext context = FacesContext.getCurrentInstance();		
			boolean datamatch=false;
			int rowCount=0;
			 	try {		 		
			 		if(validatebundleAddItem())
			 		{
			 			//product=productBO.getProductDetailsByBarcode(this.getProduct().getBarcode(),loginBean.getBranch().getBranchId());
			 			productb=productBO.getProductList(null, null, null, this.getProductb().getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			 			
			 			//item.setContactPerson(product.getSupplierName());		 			
			 			 for(ProductbundleModel data:productbundleList)
			 			 {			 				
			 				 if(data.getProduct().getBarcode().equalsIgnoreCase(this.getProductb().getBarcode()))
			 				 {
			 					ProductbundleModel c = productbundleList.get(rowCount);
			 					c.setQuantity(this.getItemCount().add(c.getQuantity()));
			 					productbundleList.set(rowCount, c);		
			 					datamatch=true;
			 				 }		 				 
			 				rowCount++;
			 			 }
			 			if(!datamatch)
			 			{ 				 			 				 					
			 			item.setProduct(productb);			 			
			 			item.setQuantity(this.getItemCount());			 			
			 			this.getDynamicPurchaseRequestItemList(item);
			 			}
			 			resetBundleItem();
			 		}
			 		
			 		
			} catch (Exception e) {			
				
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			}				
		}
		
		public void removeBundleItem(ActionEvent event)
		{
			String purchaseRequestBreakdownId = "";
			purchaseRequestBreakdownId = (String) event.getComponent().getAttributes().get("purchaseRequestBreakdownId").toString();
			this.setRowId(Integer.parseInt(purchaseRequestBreakdownId));
		}
		
		public void removeBundleItemConfirm()
		{
			ProductbundleModel c = productbundleList.get(this.getRowId());			
			productbundleList.remove(c);
		}	
		
		
		public void getDynamicPurchaseRequestItemList(ProductbundleModel item)	
		{
			this.productbundleList.add(item);
		}
		
		
		public boolean validatebundleAddItem()
		{
			boolean valid=true;
			FacesContext context = FacesContext.getCurrentInstance();	
			try
			{   
				if (factoryBean.checkIsNullAssignMessage(this.getProductb().getBarcode(),
						"purchaserequest.label.productCode", "productCode")) {
					valid = false;
				}				
					 
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}		
			return valid;
		}
	
		
		
		public void editBundleProduct(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String purchaseRequestBreakdownId = "";
			purchaseRequestBreakdownId = (String) event.getComponent().getAttributes().get("purchaseRequestBreakdownId").toString();
			ProductbundleModel c = productbundleList.get(Integer.parseInt(purchaseRequestBreakdownId));
			this.setProductb(c.getProduct());		
			this.setItemCount(c.getQuantity());
			this.setItemaction("update");
			this.setProductCodedisable(true);
			this.setRowId(Integer.parseInt(purchaseRequestBreakdownId));			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
		
		public void updateBundleProduct() throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();	
		try {		
			this.setProductCodedisable(false);
			ProductbundleModel c = productbundleList.get(this.getRowId());			
			productbundleList.remove(c);
			addBundleItem();		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}

		public void resetBundleItem()
		{
			this.productb=new ProductModel();
			this.setItemCount(new BigDecimal(1));
			this.setItemaction("submit");
		}
		
		
		

		
		public void selectAddRemoveTaxAmount(Integer rowId){			
			
			FacesContext context = FacesContext.getCurrentInstance();			
			TaxmasterModel c = taxList.get(rowId);	
			
			BigDecimal total = new BigDecimal("0.00");
			try {
				if (ValidatorUtil.isNotNull(this.getSelectedTax().get(c.getId()))) 
				{
					
					for (Entry<String, Boolean> entry : selectedTax.entrySet()) {
						if (entry.getValue()) {
							
							TaxmasterModel c1 = taxList.get(Integer.parseInt(entry.getKey()));
							total=total.add(c1.getTaxvalue());
							
						}
					}						
					product.setTaxCode(total);
				}			
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"taxCode").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
				}		
				
			}
		
		public void uploadImageToFolder(){
			UploadedFile uploadedPhoto=getImage();
	       
	        //String filePath="c:/product/";
			//File uploads = new File(properties.getProperty("upload.location"));
			String filePath="";
			ResourceBundle rb = ResourceBundle.getBundle("../META-INF/MessagesResources");
			String absPath=rb.getString("product.image.absolutepath");
			if(absPath.equals("this.productImages")) {
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				filePath = servletContext.getRealPath("")	+ File.separator + "productImages"+ File.separator ;
			}
			else
				filePath=absPath;
			
	        byte[] bytes=null;
	        try{
	            if (null!=uploadedPhoto) {
	                bytes = uploadedPhoto.getContents();
	                
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+this.imageFileName)));
	                stream.write(bytes);
	                stream.close();
	                
	            }
	        }catch(IOException e) {
	        	e.printStackTrace();
	        }
	 
	        FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_INFO,"Your Photo (File Name "+ uploadedPhoto.getFileName()+ " with size "+ uploadedPhoto.getSize()+ ")  Uploaded Successfully", ""));
	    }
		
		public void addItemToQuickList(){
			try {
				if(selectedProductList.size()>0) {
					List<Integer> pID=new ArrayList<Integer>();
					for(int i=0;i<selectedProductList.size();i++) {
						pID.add(selectedProductList.get(i).getProductId());
					}
					productBO.addProductIntoQuickList(pID);
					loadProduct();
				}
			}catch(Exception ex) {
	        	ex.printStackTrace();
			}
		}
		
		public void removeProductFromQuickList() {
			try {
				if(selectedProductQuickList.size()>0) {
					List<Integer> pID=new ArrayList<Integer>();
					for(int i=0;i<selectedProductQuickList.size();i++) {
						pID.add(selectedProductQuickList.get(i).getProductId());
					}
					productBO.removeProductFromQuickList(pID);
					loadProduct();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void onCheckSetItem() {

		}
		public void editSetItem() {
		}
		
		public void deleteProduct(ActionEvent event) throws Exception 
		{
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				String productId = "";
				productId = (String) event.getComponent().getAttributes().get("productId").toString();
				
				boolean isSales=false;
				boolean isKitchen=false;
				boolean deleteSuccess=false;
				
				SalesorderBean salesorderBean = (SalesorderBean) BeanContext.getReference("salesorderBean");
				ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		
				isSales=salesOrderBO.productISSales(Integer.parseInt(productId));
				isKitchen=salesOrderBO.productIsKitchenOrder(Integer.parseInt(productId));
				if(isSales) {
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete, the item has been added to sales.", null));	
					
				}else if(isKitchen){
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete, the item is listed in kitchen order.", null));	
					
				}else {
					ProductModel prod=new ProductModel();
					prod=productBO.getProductDetails(Integer.parseInt(productId), loginBean.getBranch().getBranchId());
					deleteSuccess=productBO.deleteProduct(prod);
					if(deleteSuccess) {
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Product successfully deleted.", null));	
						
					}
				}
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void defaultPackingPrice() {
			product.getNormalPrice();
			product.setPackingPrice(product.getNormalPrice());
		}
		
		public void filterProductByName() {
			try {
				
				List<ProductModel> filteredProducts=new ArrayList<ProductModel>();
				if(this.getProductNameFilter()!=null && !this.getProductNameFilter().isEmpty()) {
					
					this.setProductNameFilter(this.getProductNameFilter().replaceAll("\\h"," "));
					
					for(ProductModel obj: this.getUnFilteredProductListInitLoad()) {
						
						if( obj.getProductName().toUpperCase().contains(this.getProductNameFilter().toUpperCase()) ) {
							filteredProducts.add(obj);
						}
					}
					
					if(filteredProducts.size()>0) {
						OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
						orderBean.setSubContentView("productList");
						
						
						if(filteredProducts.size()==1) {
							orderBean.addOrderItem(filteredProducts.get(0));
							this.setProductNameFilter(null);
						}
						
					}
					
					this.setProductAll(filteredProducts);

				}else {
					this.setProductAll(this.getUnFilteredProductList());				
				}
				
			
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void filterProductByCode() {
			try {
				
				
				List<ProductModel> filteredProducts=new ArrayList<ProductModel>();
				
				if(this.getProductCodeFilter()!=null && !this.getProductCodeFilter().isEmpty()) {
						
					this.setProductCodeFilter(this.getProductCodeFilter().replaceAll("\\h"," "));
					
					for(ProductModel obj: this.getUnFilteredProductListInitLoad()) {
						if( obj.getBarcode().toUpperCase().contains(this.getProductCodeFilter().toUpperCase()) ) {
							filteredProducts.add(obj);
						}
					}
					this.setProductAll(filteredProducts);
					if(filteredProducts.size()>0) {
						OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
						orderBean.setSubContentView("productList");
						
						
						if(filteredProducts.size()==1) {
							orderBean.addOrderItem(filteredProducts.get(0));
							this.setProductCodeFilter(null);
						}
						
					}

				}else {
					this.setProductAll(this.getUnFilteredProductListInitLoad());				
				}
				this.setProductCodeFilter(null);
			
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void initProduct() {
			
			try {
				this.setUnFilteredProductListInitLoad(productBO.getProductListReport(categoryName, null, null, null, loginBean.getBranch().getBranchId(), null, null,null));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
