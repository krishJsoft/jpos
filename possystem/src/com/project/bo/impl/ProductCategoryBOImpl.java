package com.project.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IProductCategoryBO;
import com.project.dao.interfaces.IProductCategoryDAO;
import com.project.dao.interfaces.IProductcategoryonlineDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductmaterailModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.model.his.Branch;
import com.project.model.his.Product;
import com.project.model.his.Productcategory;
import com.project.model.his.Productmaterail;
import com.project.model.his.Quotationbreakdown;
import com.project.model.his.Uomtype;
import com.project.model.sale.sales.QuotationbreakdownModel;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 07 July 2013
 * 
 */

@Service("productCategoryBO")
public class ProductCategoryBOImpl implements IProductCategoryBO {
	
	public static Logger log = LoggerFactory.getLogger(ProductCategoryBOImpl.class);

	@Resource(name = "productCategoryRepository")
	private IProductCategoryDAO productCategoryRepository;
	
	@Resource(name = "productCategoryonlineRepository")
	private IProductcategoryonlineDAO productCategoryonlineRepository;
	
	@Transactional
	@Override
	public boolean createNewProductcategory(ProductcategoryModel pcategory)
			throws Exception {
		boolean saveSuccess = false;
		Productcategory categoryObj = new Productcategory();
		Productcategory categoryChildObj = new Productcategory();
		try {			
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());	
			categoryObj.setParentCategoryId(pcategory.getCategoryId());
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel()+1);
			categoryObj.setParentCategoryName(pcategory.getName());
			categoryObj.setOnline(pcategory.getOnline());
			categoryObj.setSyncStatus(pcategory.getSyncStatus());
			
			Branch branch=new Branch();
			branch.setBranchId(pcategory.getBranch().getBranchId());
			categoryObj.setBranch(branch);
			
			saveSuccess = productCategoryRepository.createNewProductcategory(categoryObj);
		}

		catch (Exception e) {
			log.info("Error in createNewProductcategory ProductCategoryBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	
	}

	@Transactional
	@Override
	public boolean updateProductcategory(ProductcategoryModel pcategory)
			throws Exception {
		boolean updateSuccess = false;
		Productcategory categoryObj = productCategoryRepository.getProductcategoryDetails(pcategory.getCategoryId());
		
		try {			
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());				
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel());
			updateSuccess = productCategoryRepository.updateProductcategory(categoryObj);
		}

		catch (Exception e) {
			log.info("Error in updateProductcategory ProductCategoryBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}
	
	@Transactional
	@Override
	public boolean deleteProductcategory(Integer categoryId)
			throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Productcategory categoryObj = productCategoryRepository.getProductcategoryDetails(categoryId);		
			deleteSuccess = productCategoryRepository.deleteProductcategory(categoryObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteProductcategory ProductCategoryBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	
	}

	@Override
	public List<ProductcategoryModel> findByProductcategoryList(
			Integer categoryId, String Status) throws Exception {
		   List<ProductcategoryModel> categoryObjList = new ArrayList<ProductcategoryModel>();	
		   List<ProductcategoryModel> categorySubObjList = new ArrayList<ProductcategoryModel>();
		   List<Productcategory> categoryList = new ArrayList<Productcategory>();
		try {			
			categoryList = productCategoryRepository.findByProductcategoryList(categoryId, Status);
			
			for (Productcategory pcategory : categoryList) {	
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
			    categoryObjList.add(categoryObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByProductcategoryList ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObjList;
	}

	@Override
	public List<ProductcategoryModel> findByProductcategoryChildList(
			Integer parentCategoryId, String Status) throws Exception {
		List<ProductcategoryModel> categoryObjList = new ArrayList<ProductcategoryModel>();	
		   List<ProductcategoryModel> categorySubObjList = new ArrayList<ProductcategoryModel>();
		   List<Productcategory> categoryList = new ArrayList<Productcategory>();
		try {			
			categoryList = productCategoryRepository.findByProductcategoryChildList(parentCategoryId, Status);
			
			for (Productcategory pcategory : categoryList) {	
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
			    categoryObjList.add(categoryObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByProductcategoryList ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObjList;
	}

	@Override
	public ProductcategoryModel getProductcategoryDetails(Integer categoryId)
			throws Exception {
		  ProductcategoryModel categoryObj = new ProductcategoryModel();
		
		try {			
			Productcategory pcategory = productCategoryRepository.getProductcategoryDetails(categoryId);
						
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());	
			categoryObj.setCategoryId(pcategory.getCategoryId());
			categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel());
			categoryObj.setOldname(pcategory.getName());
			categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
		}
		catch (Exception e) {
			log.info("Error in getProductcategoryDetails ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObj;
	}

	@Override
	public boolean findCategoryExites(Integer categoryId,Integer parentCategoryId, String categoryName)
			throws Exception {
		
		boolean exists = false;		
		try {
			exists = productCategoryRepository.findCategoryExites(categoryId, parentCategoryId, categoryName);
		}
		catch (Exception e) {
			log.info("Error in findCategoryExites ProductCategoryBOImpl " + e);
			throw e;
		}
		return exists;
	}
		
	
	
	
	@Transactional
	@Override
	public boolean createNewBom(ProductmaterailModel bom)	throws Exception {
		boolean saveSuccess = false;
		Productmaterail bomObj = new Productmaterail();
		Productmaterail bomChildObj = new Productmaterail();
		try {					
			Product product = new Product();
			product.setProductId(bom.getProduct().getProductId());			
			
			Product childproduct = new Product();
			childproduct.setProductId(bom.getProductchild().getProductId());
			
			Product parentproduct = new Product();
			parentproduct.setProductId(bom.getProductparent().getProductId());
			
			bomObj.setProduct(product);
			bomObj.setProductchild(childproduct);
			bomObj.setProductparent(parentproduct);
			bomObj.setQuantity(bom.getQuantity());			
			
			bomObj.setStatus(bom.getStatus());		
			
			saveSuccess = productCategoryRepository.createNewBom(bomObj);
		}
		catch (Exception e) {
			log.info("Error in createNewProductcategory ProductCategoryBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	
	}

	@Transactional
	@Override
	public boolean updateBom(ProductmaterailModel bom)
			throws Exception {
		boolean updateSuccess = false;
		Productmaterail bomObj = productCategoryRepository.getBomDetails(bom.getBomId());		
		try {			
			bomObj.setQuantity(bom.getQuantity());
			updateSuccess = productCategoryRepository.updateBom(bomObj);
		}
		catch (Exception e) {
			log.info("Error in updateBom ProductCategoryBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}
	
	@Transactional
	@Override	
	public boolean  createMaterials(List<ProductmaterailModel> boms,Integer productId,String status) throws Exception 
	{
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		List<Integer> itemIds = new  ArrayList<Integer>();
		List<Productmaterail> productmaterailList = new ArrayList<Productmaterail>();
		try {		
			productmaterailList = productCategoryRepository.findByBomList(productId, status);
			
			for(Productmaterail oldItem:productmaterailList){
				 itemRemoved = true;
			 for (ProductmaterailModel newItem :boms ) {								 
					if(oldItem.getBomId()==newItem.getBomId()){
						itemRemoved = false;							
						break;									
					}
			}
			if(itemRemoved){
				productCategoryRepository.deleteBom(oldItem);					
				}			
			 }					
			
			for(Productmaterail newItem : productmaterailList){
				itemIds.add(newItem.getBomId());
			}			
			for (ProductmaterailModel tempdata : boms) {								 
				
				if(itemIds.contains(tempdata.getBomId()))
				{	
					for(Productmaterail newItem1 : productmaterailList){
						if(newItem1.getBomId()==tempdata.getBomId())
						{	
					
							Product product = new Product();
							product.setProductId(tempdata.getProduct().getProductId());			
							
							Product childproduct = new Product();
							childproduct.setProductId(tempdata.getProductchild().getProductId());
							
							Product parentproduct = new Product();
							parentproduct.setProductId(tempdata.getProductparent().getProductId());
							
							newItem1.setProduct(product);
							newItem1.setProductchild(childproduct);
							newItem1.setProductparent(parentproduct);
							newItem1.setQuantity(tempdata.getQuantity());			
							
							newItem1.setStatus(tempdata.getStatus());	
							
							updateSuccess = productCategoryRepository.updateBom(newItem1);
						}
					}					
				}
				else
				{									
					Productmaterail bomObj = new Productmaterail();			
					
					Product product = new Product();
					product.setProductId(tempdata.getProduct().getProductId());			
					
					Product childproduct = new Product();
					childproduct.setProductId(tempdata.getProductchild().getProductId());
					
					Product parentproduct = new Product();
					parentproduct.setProductId(tempdata.getProductparent().getProductId());
					
					bomObj.setProduct(product);
					bomObj.setProductchild(childproduct);
					bomObj.setProductparent(parentproduct);
					bomObj.setQuantity(tempdata.getQuantity());		
					
					bomObj.setStatus(tempdata.getStatus());						
					updateSuccess = productCategoryRepository.updateBom(bomObj);
					
				}
			}
			
			
			}
		catch (Exception e) {
			log.info("Error in createMaterials ProductCategoryBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}
	
	
	@Transactional
	@Override
	public boolean deleteBom(Integer bomId)	throws Exception {
		boolean deleteSuccess = false;		
		try {
			Productmaterail bomObj = productCategoryRepository.getBomDetails(bomId);	
			deleteSuccess = productCategoryRepository.deleteBom(bomObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteBom ProductCategoryBOImpl " + e);
			throw e;
		}
		return deleteSuccess;	
	}

	@Override
	public List<ProductmaterailModel> findByBomList(Integer productId, String Status) throws Exception {
		   List<ProductmaterailModel> categoryObjList = new ArrayList<ProductmaterailModel>();	
		   List<ProductcategoryModel> categorySubObjList = new ArrayList<ProductcategoryModel>();
		   List<Productmaterail> productmaterailList = new ArrayList<Productmaterail>();
		   BeanUtilsBean beanUtils = new BeanUtilsBean();		
		   
		try {			
			productmaterailList = productCategoryRepository.findByBomList(productId, Status);
			
			for (Productmaterail pcategory : productmaterailList) {	
				ProductmaterailModel categoryObj = new ProductmaterailModel();
				
				categoryObj.setBomId(pcategory.getBomId());
				categoryObj.setQuantity(pcategory.getQuantity());
				categoryObj.setStatus(pcategory.getStatus());
				
				ProductModel product = new ProductModel();
				product.setProductId(pcategory.getProduct().getProductId());
				product.setProductCode(pcategory.getProduct().getBarcode());
				product.setBarcode(pcategory.getProduct().getBarcode());
				product.setProductName(pcategory.getProduct().getProductName());	
				categoryObj.setProduct(product);
				
				ProductModel childproduct = new ProductModel();
				childproduct.setProductId(pcategory.getProductchild().getProductId());
				childproduct.setProductCode(pcategory.getProductchild().getBarcode());
				childproduct.setBarcode(pcategory.getProductchild().getBarcode());
				childproduct.setProductName(pcategory.getProductchild().getProductName());				
				categoryObj.setProductchild(childproduct);				
				
				ProductModel parentproduct = new ProductModel();
				parentproduct.setProductId(pcategory.getProductparent().getProductId());
				parentproduct.setProductCode(pcategory.getProductparent().getBarcode());
				parentproduct.setBarcode(pcategory.getProductparent().getBarcode());
				parentproduct.setProductName(pcategory.getProductparent().getProductName());							
				categoryObj.setProductparent(parentproduct);
				
			    categoryObjList.add(categoryObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByBomList ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObjList;
	}

	@Override
	public List<ProductmaterailModel> findByBomChildList(Integer parentCategoryId, String Status) throws Exception {
		List<ProductmaterailModel> categoryObjList = new ArrayList<ProductmaterailModel>();	
		   List<ProductmaterailModel> categorySubObjList = new ArrayList<ProductmaterailModel>();
		   List<Productcategory> categoryList = new ArrayList<Productcategory>();
		try {			
			categoryList = productCategoryRepository.findByProductcategoryChildList(parentCategoryId, Status);
			
			for (Productcategory pcategory : categoryList) {	
				ProductmaterailModel categoryObj = new ProductmaterailModel();				
			    categoryObjList.add(categoryObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByBomChildList ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObjList;
	}

	@Override
	public ProductmaterailModel getBomDetails(Integer categoryId)	throws Exception {	
			ProductmaterailModel categoryObj = new ProductmaterailModel();
		    BeanUtilsBean beanUtils = new BeanUtilsBean();	
		try {			
			Productmaterail pcategory = productCategoryRepository.getBomDetails(categoryId);					
			
			categoryObj.setBomId(pcategory.getBomId());
			categoryObj.setQuantity(pcategory.getQuantity());
			categoryObj.setStatus(pcategory.getStatus());
			
			beanUtils = new BeanUtilsBean();	
			ProductModel childproduct = new ProductModel();
			beanUtils.copyProperties(childproduct, pcategory.getProductchild());	
			
			beanUtils = new BeanUtilsBean();	
			ProductModel parentproduct = new ProductModel();
			beanUtils.copyProperties(parentproduct, pcategory.getProductparent());		
			
			}
		catch (Exception e) {
			log.info("Error in getBomDetails ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObj;
	}

	@Override
	public boolean findBomExites(Integer categoryId,Integer parentCategoryId, String categoryName)
			throws Exception {		
		boolean exists = false;		
		try {
			exists = productCategoryRepository.findBomExites(categoryId, parentCategoryId, categoryName);
		}
		catch (Exception e) {
			log.info("Error in findBomExites ProductCategoryBOImpl " + e);
			throw e;
		}
		return exists;
	}

	
	
	
	

	@Transactional
	@Override
	public boolean createNewUom(UomtypeModel uom) throws Exception {
		boolean saveSuccess = false;
		Uomtype uomtypeObj = new Uomtype();		
		try {			
			uomtypeObj.setUOMName(uom.getUOMName());
			uomtypeObj.setDescription(uom.getDescription());
			saveSuccess = productCategoryRepository.createNewUom(uomtypeObj);
		}

		catch (Exception e) {
			log.info("Error in createNewUom ProductCategoryBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	
	}

	@Transactional
	@Override
	public boolean updateUom(UomtypeModel uom) throws Exception {
		boolean updateSuccess = false;
		Uomtype uomtypeObj = productCategoryRepository.getUomtypeDetails(uom.getUOMId());
		
		try {			
			uomtypeObj.setUOMName(uom.getUOMName());	
			uomtypeObj.setDescription(uom.getDescription());
			updateSuccess = productCategoryRepository.updateUom(uomtypeObj);
		}

		catch (Exception e) {
			log.info("Error in updateUom ProductCategoryBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}

	@Transactional
	@Override
	public boolean deleteUom(UomtypeModel uom) throws Exception {
		boolean deleteSuccess = false;
		Uomtype uomtypeObj = productCategoryRepository.getUomtypeDetails(uom.getUOMId());
		
		try {						
			deleteSuccess = productCategoryRepository.deleteUom(uomtypeObj);
		}

		catch (Exception e) {
			log.info("Error in updateUom ProductCategoryBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}

	@Override
	public List<UomtypeModel> findByUomtypeList(String UOMName)
			throws Exception {
		List<UomtypeModel> uomObjList = new ArrayList<UomtypeModel>();			 
		   List<Uomtype> uomList = new ArrayList<Uomtype>();
		try {			
			uomList = productCategoryRepository.findByUomtypeList(UOMName);
			
			for (Uomtype uom : uomList) {	
				UomtypeModel uomtypeObj = new UomtypeModel();
				uomtypeObj.setUOMId(uom.getUOMId());
				uomtypeObj.setUOMName(uom.getUOMName());
				uomtypeObj.setUOMOldName(uom.getUOMName());
				uomtypeObj.setDescription(uom.getDescription());
				uomObjList.add(uomtypeObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByUomtypeList ProductCategoryBOImpl " + e);
			throw e;
		}
		return uomObjList;
	}

	@Override
	public boolean findUomtypeExites(String UOMName) throws Exception {
		boolean exists = false;		
		try {
			exists = productCategoryRepository.findUomtypeExites(UOMName);
		}
		catch (Exception e) {
			log.info("Error in findUomtypeExites ProductCategoryBOImpl " + e);
			throw e;
		}
		return exists;
	}

	@Override
	public UomtypeModel getUomtypeDetails(Integer UOMId) throws Exception {
		UomtypeModel uomtypeObj = new UomtypeModel();
		try
		{
			Uomtype uom = productCategoryRepository.getUomtypeDetails(UOMId);
			uomtypeObj.setUOMId(uom.getUOMId());
			uomtypeObj.setUOMName(uom.getUOMName());
			uomtypeObj.setUOMOldName(uom.getUOMName());
			uomtypeObj.setDescription(uom.getDescription());
			
		}
		catch(Exception e)
		{
			log.info("Error in getUomtypeDetails ProductCategoryBOImpl " + e);
			throw e;
		}
		return uomtypeObj;
	}

	@Override
	public List<Integer> getValidCategoryIdWithProducts() throws Exception{
		List<Integer> validCat=null;
		try {
			validCat=productCategoryRepository.getValidCategoryIdWithProducts();
			
		}catch (Exception ex) {
			log.info("Error in getCategoryWithProducts ProductCategoryBOImpl " + ex);
			throw ex;
		}
		return validCat;
	}
	
	@Override
	public List<ProductcategoryModel> findByParentCategoryId(Integer categoryId, String status) 
	throws Exception{
		List<ProductcategoryModel> objList=new <ProductcategoryModel>ArrayList();
		try {
			List<Productcategory> data=productCategoryRepository.findByParentCategoryId(categoryId,status);
			for (Productcategory pcategory : data) {	
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
				objList.add(categoryObj);
			}
		}catch (Exception ex) {
			log.info("Error in findByParentCategoryId ProductCategoryBOImpl " + ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public List<ProductcategoryModel> loadCategoryWithoutPrinter(int branchId) throws Exception {
		List<ProductcategoryModel> objList=new <ProductcategoryModel>ArrayList();
		try {
			List<Productcategory> data=productCategoryRepository.loadCategoryWithoutPrinter(branchId);
			for (Productcategory pcategory : data) {	
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(pcategory.getBranch().getBranchId());
				categoryObj.setBranch(branchObj);
			
				objList.add(categoryObj);
			}
		}catch (Exception ex) {
			log.info("Error in loadCategoryListForPrinter ProductCategoryBOImpl " + ex);
			throw ex;
		}
		return objList;
	}
	@Override
	public List<ProductcategoryModel> loadCategoryListForPrinter(Integer printerId, int branchId) throws Exception {
		List<ProductcategoryModel> objList=new <ProductcategoryModel>ArrayList();
		try {
			List<Productcategory> data=productCategoryRepository.loadCategoryListForPrinter(printerId,branchId);
			for (Productcategory pcategory : data) {	
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(pcategory.getBranch().getBranchId());
				categoryObj.setBranch(branchObj);
				
				KitchenprinterlistModal printerObj=new KitchenprinterlistModal();
				if(pcategory.getPrinter()!=null) {
					printerObj.setId(pcategory.getPrinter().getId());
					printerObj.setKitchenName(pcategory.getPrinter().getKitchenName());
					printerObj.setPrinterName(pcategory.getPrinter().getPrinterName());
				}
				

				categoryObj.setPrinter(printerObj);
				
				objList.add(categoryObj);
			}
		}catch (Exception ex) {
			log.info("Error in loadCategoryListForPrinter ProductCategoryBOImpl " + ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public void updatePrinterIdForCategory(Integer printerId,List<Integer> id) throws Exception {

		try {
			List<Integer> idList=id;
			
			productCategoryRepository.updatePrinterIdForCategory(printerId,idList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ProductcategoryModel getProductCategoryDetails2(int categoryId) throws Exception {
ProductcategoryModel categoryObj = new ProductcategoryModel();
		
		try {			
			Productcategory pcategory = productCategoryRepository.getProductcategoryDetails2(categoryId);
						
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());	
			categoryObj.setCategoryId(pcategory.getCategoryId());
			categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel());
			categoryObj.setOldname(pcategory.getName());
			categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
			
			if(pcategory.getPrinter()!=null) {
				KitchenprinterlistModal printer=new KitchenprinterlistModal();
				printer.setId(pcategory.getPrinter().getId());
				categoryObj.setPrinter(printer);
			}
			
			BranchModel branch=new BranchModel();
		    branch.setBranchId(pcategory.getBranch().getBranchId());
		    categoryObj.setBranch(branch);
			    
			
		}
		catch (Exception e) {
			log.info("Error in getProductCategoryDetails2 ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObj;
	}

	@Override
	public List<Integer> getValidCategoryIdWithProducts2(int branchId) throws Exception {
		List<Integer> validCat=null;
		try {
			validCat=productCategoryRepository.getValidCategoryIdWithProducts2(branchId);
			
		}catch (Exception ex) {
			log.info("Error in getValidCategoryIdWithProducts2 ProductCategoryBOImpl " + ex);
			throw ex;
		}
		return validCat;
	}

	@Override
	public List<ProductcategoryModel> findByProductcategoryList2(Integer categoryId, String status, 
			Integer branchId, Boolean online,Integer syncStatus) throws Exception{
	 List<ProductcategoryModel> categoryObjList = new ArrayList<ProductcategoryModel>();	
	   List<ProductcategoryModel> categorySubObjList = new ArrayList<ProductcategoryModel>();
	   List<Productcategory> categoryList = new ArrayList<Productcategory>();
	try {			
		categoryList = productCategoryRepository.findByProductcategoryList2(categoryId, status,branchId,online,syncStatus);
		
		for (Productcategory pcategory : categoryList) {	
			ProductcategoryModel categoryObj = new ProductcategoryModel();
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());	
			categoryObj.setCategoryId(pcategory.getCategoryId());
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel());
			categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
			categoryObj.setParentCategoryName(pcategory.getParentCategoryName());
		    categoryObj.setOnline(pcategory.getOnline());
		    categoryObj.setSyncStatus(pcategory.getSyncStatus());
			
			
		    BranchModel branch=new BranchModel();
		    branch.setBranchId(pcategory.getBranch().getBranchId());
		    categoryObj.setBranch(branch);
		    
		    
		    categoryObjList.add(categoryObj);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		log.info("Error in findByProductcategoryList2 ProductCategoryBOImpl " + e);
		throw e;
	}
	return categoryObjList;
	}

	@Override
	public List<ProductcategoryModel> getCategoryPrinterList(Integer branchId) throws Exception {
		 List<ProductcategoryModel> categoryObjList = new ArrayList<ProductcategoryModel>();
		 try {
				
			for (Productcategory pcategory : productCategoryRepository.getCategoryPrinterList(branchId)) {
				ProductcategoryModel categoryObj = new ProductcategoryModel();
				categoryObj.setCode(pcategory.getCode());
				categoryObj.setDescription(pcategory.getDescription());
				categoryObj.setName(pcategory.getName());	
				categoryObj.setCategoryId(pcategory.getCategoryId());
				categoryObj.setStatus(pcategory.getStatus());
				categoryObj.setLevel(pcategory.getLevel());
				categoryObj.setParentCategoryId(pcategory.getParentCategoryId());	
				categoryObj.setParentCategoryName(pcategory.getParentCategoryName());

				KitchenprinterlistModal printer=new KitchenprinterlistModal();
				printer.setId(pcategory.getPrinter().getId());
				printer.setKitchenName(pcategory.getPrinter().getKitchenName());
				categoryObj.setPrinter(printer);
					
			    BranchModel branch=new BranchModel();
			    branch.setBranchId(pcategory.getBranch().getBranchId());
			    categoryObj.setBranch(branch);
			    
			    categoryObjList.add(categoryObj);	
				
			}
		 }catch (Exception e) {
			e.printStackTrace();
			log.info("Error in getCategoryPrinterList ProductCategoryBOImpl " + e);
			throw e;
		}
		return categoryObjList;
	}

	@Override
	public ProductcategoryModel fetchOnlineCategoryDetails(Integer categoryId, String name, Integer level,
			String status, Integer onlineBranchId) throws Exception {
		ProductcategoryModel obj=new ProductcategoryModel();
		try {
			Productcategory data=productCategoryonlineRepository.fetchOnlineCategoryDetails(categoryId,name,level,status,onlineBranchId);
			obj.setCategoryId(data.getCategoryId());
			obj.setCode(data.getCode());
			obj.setDescription(data.getDescription());
			
			obj.setName(data.getName());
			obj.setParentCategoryId(data.getCategoryId());
			obj.setStatus(data.getStatus());
			obj.setParentCategoryName(data.getParentCategoryName());
			obj.setLevel(data.getLevel());
			
			
			obj.setOnline(data.getOnline());
			obj.setSyncStatus(data.getSyncStatus());
			
			BranchModel branchObj=new BranchModel();
			branchObj.setBranchId(data.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in fetchOnlineCategoryDetails ProductCategoryBOImpl " + e);
			throw e;
		}
		
		return obj;
	}

	@Transactional
	@Override
	public Boolean createNewOnlineProductcategory(ProductcategoryModel pcategory) throws Exception {
		boolean saveSuccess = false;
		Productcategory categoryObj = new Productcategory();
		try {			
			categoryObj.setCode(pcategory.getCode());
			categoryObj.setDescription(pcategory.getDescription());
			categoryObj.setName(pcategory.getName());	
			categoryObj.setParentCategoryId(pcategory.getParentCategoryId());
			categoryObj.setStatus(pcategory.getStatus());
			categoryObj.setLevel(pcategory.getLevel());
			categoryObj.setParentCategoryName(pcategory.getName());
			categoryObj.setOnline(pcategory.getOnline());
			categoryObj.setSyncStatus(pcategory.getSyncStatus());
			

			Branch branch=new Branch();
			branch.setBranchId(pcategory.getBranch().getBranchId());
			categoryObj.setBranch(branch);
			saveSuccess = productCategoryonlineRepository.createNewProductcategory(categoryObj);
		}

		catch (Exception e) {
			log.info("Error in createNewOnlineProductcategory ProductCategoryBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}
}
