package com.project.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.admin.ProductBean;
import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.dao.interfaces.IProductDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductbundleModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.stock.BranchstockProductModel;
import com.project.model.his.Branch;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Productbundle;
import com.project.model.his.Productcategory;
import com.project.model.his.Productprice;
import com.project.model.his.Productsetlist;
import com.project.model.his.Productsupplier;
import com.project.model.his.Supplier;



/*
 * @author Gopal Ar
 * @version 1.0
 * @since 9 July 2013
 * 
 */

@Service("productBO")
public class ProductBOImpl implements IProductBO {
	
	public static Logger log = LoggerFactory.getLogger(BranchBOImpl.class);

	@Resource(name = "productRepository")
	private IProductDAO productRepository;
	
	
	@Resource(name = "branchBO")
	private IBranchBO branchBO;
	
	@Resource(name = "productCategoryBO")
	private IProductCategoryBO productCategoryBO;
	
	
	
	
	@Override
	public List<ProductModel> findByProductListAll(Integer branchId) throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		
		try {
		List<Productbranchlink> productList = productRepository.findByProductListAll(branchId);

		for (Productbranchlink product : productList) {
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			if(product.getTaxCode()==null) {
				product.setTaxCode(new BigDecimal("0"));
			}
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
			
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductList of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}
	
	/*
	public List<ProductModel> getCustomProductList(Integer branchId) throws Exception{
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		

		try {
			List<Productbranchlink> productList = productRepository.getCustomProductList(branchId);
			for (Productbranchlink product : productList) {
				ProductModel productObj = new ProductModel();
				productObj.setBarcode(product.getProduct().getBarcode());
				productObj.setCreatedBy(product.getProduct().getCreatedBy());
				productObj.setCreatedDate(product.getProduct().getCreatedDate());
				productObj.setDescription(product.getProduct().getDescription());
				productObj.setDiscount(product.getProduct().getDiscount());
				productObj.setExpiryDate(product.getProduct().getCreatedDate());
				productObj.setHeight(product.getProduct().getHeight());
				productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
				productObj.setLength(product.getProduct().getLength());
				productObj.setProductCode(product.getProduct().getProductCode());
				productObj.setProductName(product.getProduct().getProductName());
				productObj.setBrandName(product.getProduct().getBrandName());
				productObj.setQuantityonHand(product.getQuantityonHand());
				productObj.setReorderPoint(product.getProduct().getReorderPoint());
				productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
				productObj.setStatus(product.getProduct().getStatus());
				productObj.setWeight(product.getProduct().getWeight());
				productObj.setWidth(product.getProduct().getWidth());
				productObj.setProductId(product.getProduct().getProductId());				
				productObj.setNormalPrice(product.getNormalPrice());
				productObj.setPurchasePrice(product.getPurchasePrice());			
				productObj.setDoctorPrice(product.getDoctorPrice());
				productObj.setSalesrepPrice(product.getSalesrepPrice());
				productObj.setQuantityonalert(product.getQuantityonalert());		
				productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
				productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
				productObj.setUom(product.getProduct().getUom());
				productObj.setProductDesc(product.getProduct().getProductDesc());
				productObj.setFullredemptionpoint(product.getFullredemptionpoint());
				productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
				productObj.setRedemAmount(product.getRedemAmount());
				productObj.setTaxAmount(product.getTaxAmount());
				productObj.setGstAmount(product.getGstAmount());
				productObj.setTaxCode(product.getTaxCode());
				productObj.setGstCode(product.getGstCode());
				productObj.setCustomerPoint(product.getCustomerPoint());
				productObj.setSalesType(product.getProduct().getSalesType());
				
				productObj.setProductBranchLinkId(product.getProductBranchLinkId());
				
				productObjList.add(productObj);
				
			}
			
		}catch(Exception e)
		{
			log.info("Error in getCustomProductList of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}
	*/
	
	@Override
	public List<ProductModel> findByProductAll() throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		
		try {
			
		List<Object[]> tempdataList=productRepository.findProduct();
		for(Object[] tempdata :tempdataList)
		{
			ProductModel productObj = new ProductModel();	  
			
			productObj.setProductId(((Number) tempdata[0]).intValue());			
			productObj.setProductCode((String)tempdata[1]);
			productObj.setBarcode((String)tempdata[1]);
			productObj.setProductName((String)tempdata[2]);
			productObj.setUom((String)tempdata[3]);
			productObjList.add(productObj);
		}

		/*List<Product> productList = productRepository.findByProductAll();

		for (Product product : productList) {
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getBarcode());
			productObj.setCreatedBy(product.getCreatedBy());
			productObj.setCreatedDate(product.getCreatedDate());
			//productObj.setDescription(product.getDescription());
			//productObj.setDiscount(product.getDiscount());
		//	productObj.setExpiryDate(product.getCreatedDate());
			productObj.setHeight(product.getHeight());
			productObj.setLastModifiedDate(product.getLastModifiedDate());
			productObj.setLength(product.getLength());
			productObj.setProductCode(product.getProductCode());
			productObj.setProductName(product.getProductName());			
			productObj.setStatus(product.getStatus());
			productObj.setWeight(product.getWeight());
			productObj.setWidth(product.getWidth());
			productObj.setProductId(product.getProductId());				
			productObj.setUom(product.getUom());
			//productObj.setProductDesc(product.getProductDesc());
			
			productObjList.add(productObj);
		}*/
		}
		catch(Exception e)
		{
			log.info("Error in findByProductAll of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}

	
	

	@Override
	public int getProductCount(List<Integer> categoryId, Integer supplierId,String barcode, String status,Integer branchId,String productName, Integer quantityonHand,String brandName,String salesType) throws Exception {
		try {
			return productRepository.getProductCount(categoryId, supplierId, barcode, status,branchId,productName,quantityonHand,brandName,salesType);
		} catch (Exception e) {
			log.info("Error in getProductCount of ProductBOImpl "+ e.toString());
			throw e;
		}
	}


	@Override
	public List<ProductModel> getProductList(int[] ids,List<Integer> categoryId,
			Integer supplierId, String barcode, String status, int start,
			int howMany,Integer branchId,String productName, Integer quantityonHand,String brandName,String salesType) throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		
		try {
		List<Productbranchlink> productList = productRepository.getProductList(ids,categoryId, supplierId, barcode, status, start, howMany,branchId,productName,quantityonHand,brandName,salesType);

		for (Productbranchlink product : productList) {			
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());

			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			productObj.setTaxType(product.getTaxType());
			
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
		
			
			
		/*	for(Productsupplier supplier : product.getProductsuppliers())
			{
				ProductsupplierModel supplierObj = new ProductsupplierModel();	
				supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
				supplierObj.setSupplierName(supplier.getSupplier().getSupplierName());
				supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
				supplierObj.setProductId(product.getProductId());
				supplierObj.setProductName(product.getProductName());
				
				supplierList.add(supplierObj);
			}
			productObj.setSupplierlist(supplierList);	*/
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductList of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}

	
	
	
	
	@Override
	public List<ProductModel> getProductListReport(String categoryId,
			Integer supplierId, String barcode, String status, Integer branchId,String productName,String brandName,String imageDirectory) throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		
		try {
			
			List<Integer> requestIds1= new ArrayList<Integer>();
			if(categoryId!=null && categoryId!="")
			{		
				if(!categoryId.equalsIgnoreCase(""))
				{
			List<String> requestIds2 = Arrays.asList(categoryId.split(","));			
			for(String id:requestIds2)
			{
				requestIds1.add(Integer.parseInt(id));
			}
				}
			}
		List<Productbranchlink> productList = productRepository.getProductListReport(requestIds1, supplierId, barcode, status,branchId,productName,brandName);

		for (Productbranchlink product : productList) {			
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());
			
			
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			productObj.setImageDirectory(product.getProduct().getImageDirectory());
			productObj.setIsCustomeList(product.getProduct().getIsCustomList());
			productObj.setSetItem(product.getProduct().getSetItem());
			if(productObj.isSetItem()) {
				List<ProductsetModel> setObjList=new ArrayList<ProductsetModel>();
				
				for(Productsetlist data : productRepository.getProductSetList(product.getProduct().getProductId())) {
					ProductsetModel setObj=new ProductsetModel();
					
					ProductModel productSetObj=new ProductModel();
					productSetObj.setProductName(data.getProduct().getProductName());
					productSetObj.setBarcode(data.getProduct().getBarcode());
					productSetObj.setProductId(data.getProduct().getProductId());
					productSetObj.setCategoryId(data.getProduct().getProductcategory().getCategoryId());
					
					setObj.setSetId(data.getSetId());
					setObj.setProduct(productSetObj);
					
					setObjList.add(setObj);
				}
				productObj.setProductSetList(setObjList);
			}
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
			
			
			
			
		/*	for(Productsupplier supplier : product.getProductsuppliers())
			{
				ProductsupplierModel supplierObj = new ProductsupplierModel();	
				supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
				supplierObj.setSupplierName(supplier.getSupplier().getSupplierName());
				supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
				supplierObj.setProductId(product.getProductId());
				supplierObj.setProductName(product.getProductName());
				
				supplierList.add(supplierObj);
			}
			productObj.setSupplierlist(supplierList);	*/
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductList of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}

	
	
	
	@Override
	public List<ProductModel> getProductListUsingLikeProductCode(
			String productCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductModel getProductDetails(int productId,Integer branchId) throws Exception {
		ProductModel productObj = new ProductModel();	
		List<ProductsupplierModel> supplierList = new ArrayList<ProductsupplierModel>();
		List<ProductbundleModel> productbundleList = new ArrayList<ProductbundleModel>();
		
		
		List<ProductpriceModel> priceList = new ArrayList<ProductpriceModel>();
		try {			
			Productbranchlink product = productRepository.getProductDetails(productId,branchId);
			
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			productObj.setTaxType(product.getTaxType());

			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setImageDirectory(product.getProduct().getImageDirectory());
			
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
			productObj.setSetItem(product.getProduct().getSetItem());
			productObj.setOnline(product.getProduct().getOnline());
			
			/*for(Productsupplier supplier : product.getProduct().getProductsuppliers())
			{
				ProductsupplierModel supplierObj = new ProductsupplierModel();	
				supplierObj.setProductsupplierId(supplier.getProductsupplierId());
				supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
				supplierObj.setSupplierName(supplier.getSupplier().getCompanyName());
				supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
				supplierObj.setProductId(product.getProduct().getProductId());
				supplierObj.setProductName(product.getProduct().getProductName());
				supplierObj.setPurchasePrice(supplier.getPurchasePrice());
				
				supplierList.add(supplierObj);
			}*/
			productObj.setSupplierlist(supplierList);			
			
			
			if(product.getProduct().getSalesType()!=null && product.getProduct().getSalesType().equalsIgnoreCase("bundle"))
			{				
			for(Productbundle bundle : productRepository.getProductbundleList(product.getProduct().getProductId(),branchId))
			{
				ProductbundleModel bundleObj = new ProductbundleModel();				
				
				ProductModel productObj1 = new ProductModel();
				productObj1.setProductId(bundle.getProduct().getProductId());	
				productObj1.setProductCode(bundle.getProduct().getBarcode());
				productObj1.setBarcode(bundle.getProduct().getBarcode());
				productObj1.setProductName(bundle.getProduct().getProductName());	
				
				bundleObj.setProduct(productObj1);				
				bundleObj.setQuantity(bundle.getQuantity());
				bundleObj.setProductbundleId(bundle.getProductbundleId());
				
				productbundleList.add(bundleObj);
			}
			productObj.setProductbundleList(productbundleList);
			}
			
			if(productObj.isSetItem()) {
				List<ProductsetModel> setObjList=new ArrayList<ProductsetModel>();
				
				for(Productsetlist data : productRepository.getProductSetList(product.getProduct().getProductId())) {
					ProductsetModel setObj=new ProductsetModel();
					
					ProductModel productSetObj=new ProductModel();
					productSetObj.setProductName(data.getProduct().getProductName());
					productSetObj.setBarcode(data.getProduct().getBarcode());
					productSetObj.setProductId(data.getProduct().getProductId());
					productSetObj.setCategoryId(data.getProduct().getProductcategory().getCategoryId());
					
					setObj.setSetId(data.getSetId());
					setObj.setProduct(productSetObj);
					
					setObjList.add(setObj);
				}
				productObj.setProductSetList(setObjList);
			}
			
			
			//productObj.setProductbatchList(productbatchBO.getProductbatchDetails(null,null, productId,"1",null,null,null,null,null));
			
			priceList=this.getProductpriceByBarcode(productObj, product.getProduct().getBarcode(), branchId);
			productObj.setPriceList(priceList); // For Report
		}
		catch (Exception e) {
			log.info("Error in getProductDetailsByBarcode ProductBOImpl " + e);
			throw e;
		}
		return productObj;
	}
	
	
	@Override
	public ProductModel getProductPriceDetails(int productId,Integer branchId) throws Exception {
		ProductModel productObj = new ProductModel();	
		List<ProductpriceModel> priceList = new ArrayList<ProductpriceModel>();
		
		try {			
			Productbranchlink product = productRepository.getProductPriceDetails(productId,branchId);
			
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
			
			for (Productprice price : product.getProduct().getProductprices()) {				
				
				ProductpriceModel priceObj = new ProductpriceModel();	
				priceObj.setBarCode(price.getBarCode());
				priceObj.setDiscountAmount(price.getDiscountAmount());
				priceObj.setProductName(price.getProduct().getProductName());
				priceObj.setProductId(price.getProduct().getProductId());
				priceObj.setQuantityFrom(price.getQuantityFrom());				
				priceObj.setUom(price.getProduct().getUom());	
				priceObj.setProductPriceId(price.getProductPriceId());
				priceList.add(priceObj);			
			}	
			
			productObj.setPriceList(priceList);
		}
		catch (Exception e) {
			log.info("Error in getProductPriceDetails ProductBOImpl " + e);
			throw e;
		}
		return productObj;
	}

	
	@Override
	public List<ProductpriceModel> getSortedProductpriceByBarcode(String barcode,Integer branchId)
			throws Exception {
		List<ProductpriceModel> priceListObj = new ArrayList<ProductpriceModel>();
		ProductpriceModel priceObj = new ProductpriceModel();
		try {			
			List<Productprice> priceList = productRepository.getSortedProductpriceByBarcode(barcode,branchId);
			
			for (Productprice price : priceList) {				
		
				priceObj = new ProductpriceModel();	
				priceObj.setBarCode(price.getBarCode());
				priceObj.setDiscountAmount(price.getDiscountAmount());
				priceObj.setProductName(price.getProduct().getProductName());
				priceObj.setProductId(price.getProduct().getProductId());
				priceObj.setQuantityFrom(price.getQuantityFrom());
				//priceObj.setQuantityTo(price.getQuantityTo());
				priceObj.setUom(price.getProduct().getUom());	
				priceObj.setProductPriceId(price.getProductPriceId());
				priceListObj.add(priceObj);			
			}
			
		}
		catch (Exception e) {
			log.info("Error in getProductpriceByBarcode ProductBOImpl " + e);
			throw e;
		}
		return priceListObj;
	}
	
	
	@Override
	public List<ProductpriceModel> getSortedProductpriceBarcode(int productId,Integer branchId)
			throws Exception {
		List<ProductpriceModel> priceListObj = new ArrayList<ProductpriceModel>();
		ProductpriceModel priceObj = new ProductpriceModel();
		try {			
			List<Productprice> priceList = productRepository.getSortedProductpriceBarcode(productId,branchId);
			
			for (Productprice price : priceList) {					
				priceObj = new ProductpriceModel();	
				priceObj.setBarCode(price.getBarCode());
				priceObj.setDiscountAmount(price.getDiscountAmount());				
				priceObj.setQuantityFrom(price.getQuantityFrom());				
				priceObj.setProductPriceId(price.getProductPriceId());
				priceListObj.add(priceObj);			
			}
			
		}
		catch (Exception e) {
			log.info("Error in getProductpriceByBarcode ProductBOImpl " + e);
			throw e;
		}
		return priceListObj;
	}
	
	
	

	@Transactional
	@Override
	public boolean createNewProduct(ProductModel product) throws Exception {
		boolean saveSuccess = false;
		Product productObj = new Product();
		
		List<Productsupplier> supplierList = new ArrayList<Productsupplier>();
		List<Productbundle> productbundleList = new ArrayList<Productbundle>();
		List<Productsetlist> productsetlist = new ArrayList<Productsetlist>();
		
		List<Productprice> newPriceList  = new ArrayList<Productprice>(); 
		List<Productbranchlink> productbranchlinks= new ArrayList<Productbranchlink>(); 
		List<ProductpriceModel> pricelist = new ArrayList<ProductpriceModel>();
		List<BranchModel> branchdata = null;	
		try {
			
			productObj.setBarcode(product.getBarcode());
			productObj.setCreatedBy(product.getCreatedBy());
			productObj.setCreatedDate(product.getCreatedDate());
			productObj.setDescription(product.getDescription());
			productObj.setDiscount(product.getDiscount());
			
			productObj.setHeight(product.getHeight());
			productObj.setLastModifiedDate(product.getLastModifiedDate());
			productObj.setLength(product.getLength());
			productObj.setProductCode(product.getProductCode());
			productObj.setProductName(product.getProductName());	
			productObj.setBrandName(product.getBrandName());
			productObj.setReorderPoint(product.getReorderPoint());
			productObj.setReorderQuantity(product.getReorderQuantity());
			productObj.setStatus(product.getStatus());
			productObj.setWeight(product.getWeight());
			productObj.setWidth(product.getWidth());
			productObj.setUom(product.getUom());
			productObj.setProductDesc(product.getProductDesc());
			productObj.setSalesType(product.getSalesType());
			productObj.setImageDirectory(product.getImageDirectory());
			productObj.setOnline(product.getOnline());
							
		 
			 
			 for(ProductsetModel setItem:product.getProductSetList()) {
				Productsetlist setObj=new Productsetlist();
				
				Product setItemObj=new Product();
				setItemObj.setProductId(setItem.getProduct().getProductId());
				setObj.setProduct(setItemObj);
				setObj.setQuantity(setItem.getQuantity());
				setObj.setProductset(productObj);
				
				productsetlist.add(setObj);
				
			}
			
			 productObj.setSetItem(product.isSetItem());
			 productObj.setProductsetlist(productsetlist);
			 
			branchdata = branchBO.findByBranchList(null, null, null, null);
			 for(BranchModel b:branchdata)
			 {
				
				 
			Branch branch = new Branch();	 
			Productbranchlink productbranchlink=new Productbranchlink();
			productbranchlink.setQuantityonHand(new BigDecimal(0.00)); // for other branches
			if(b.getBranchId()==product.getBranchId())
			{
			productbranchlink.setQuantityonHand(product.getQuantityonHand());
			
			if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
			{
			for(ProductbundleModel bundle : product.getProductbundleList())
			{
				Productbundle bundleObj = new Productbundle();	
				Branch branch1 = new Branch();
				branch1.setBranchId(product.getBranchId());				
				bundleObj.setBranch(branch1);
				
				Product productObj1 = new Product();
				productObj1.setProductId(bundle.getProduct().getProductId());				
				bundleObj.setProduct(productObj1);
				
				bundleObj.setQuantity(bundle.getQuantity());
				bundleObj.setProductm(productObj);
				
				productbundleList.add(bundleObj);
			}
			productObj.setProductbundles(productbundleList);
			}
			}
			productbranchlink.setNormalPrice(product.getNormalPrice());
			productbranchlink.setPackingPrice(product.getPackingPrice());
			productbranchlink.setPurchasePrice(product.getPurchasePrice());			
			productbranchlink.setDoctorPrice(product.getDoctorPrice());
			productbranchlink.setPackingPrice(product.getPackingPrice());
			productbranchlink.setSalesrepPrice(product.getSalesrepPrice());
			productbranchlink.setQuantityonalert(product.getQuantityonalert());
			productbranchlink.setFullredemptionpoint(product.getFullredemptionpoint());
			productbranchlink.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productbranchlink.setRedemAmount(product.getRedemAmount());
			productbranchlink.setExpiryDate(product.getCreatedDate());
			productbranchlink.setTaxAmount(product.getTaxAmount());
			productbranchlink.setGstAmount(product.getGstAmount());
			productbranchlink.setTaxCode(product.getTaxCode());
			productbranchlink.setGstCode(product.getGstCode());
			productbranchlink.setCustomerPoint(product.getCustomerPoint());
			productbranchlink.setTaxType(product.getTaxType());

			productbranchlink.setProduct(productObj);			
			branch.setBranchId(b.getBranchId());
			productbranchlink.setBranch(branch);
			productbranchlinks.add(productbranchlink);		
			
			 }
			
			productObj.setProductbranchlinks(productbranchlinks);
			
			
			for(ProductsupplierModel supplier : product.getSupplierlist())
			{
				Productsupplier supplierObj = new Productsupplier();	
				Supplier supplier1 = new Supplier();
				supplier1.setSupplierId(supplier.getSupplierId());	
				supplierObj.setPurchasePrice(supplier.getPurchasePrice());
				supplierObj.setProduct(productObj);
				supplierObj.setSupplier(supplier1);
				
				supplierList.add(supplierObj);
			}
			productObj.setProductsuppliers(supplierList);
			
			
			
			Productcategory productcategory = new Productcategory();
			productcategory.setCategoryId(product.getCategoryId());
			productObj.setProductcategory(productcategory);			
			
			saveSuccess =productRepository.createNewProduct(productObj);
			
			/* Insert Default Price */
			 for(BranchModel b:branchdata)
			 {	
			Branch branch = new Branch();	
			Productprice newPrice = new Productprice();
			newPrice.setDiscountAmount(new BigDecimal(0.00));					
			newPrice.setQuantityFrom(new BigDecimal(1.00));					
			newPrice.setUom(product.getUom());	
			newPrice.setCreatedBy(product.getCreatedBy());
			newPrice.setLastModifiedDate(new Date());
			newPrice.setBarCode(product.getBarcode());
			newPrice.setTotalQuantity(new BigDecimal(0.00));
			newPrice.setProduct(productObj);
			
			branch.setBranchId(b.getBranchId());
			newPrice.setBranch(branch);
			
			newPriceList.add(newPrice);
			
			productObj.setProductprices(newPriceList);
			 }
			productRepository.updateProduct(productObj);
			
		}

		catch (Exception e) {
			log.info("Error in createNewProduct ProductBOImpl " + e);
			throw e;
		}
		return saveSuccess;
		
	
	}

	@Transactional
	@Override
	public boolean updateProduct(ProductModel product) throws Exception {
		boolean updateSuccess = false;
		Product productObj = new Product();
		Productbranchlink productbranchlink = productRepository.getProductDetails(product.getProductId(),product.getBranchId());	
		List<Productsetlist> productsetlist = new ArrayList<Productsetlist>();

		productObj=productbranchlink.getProduct();
		if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
		{
		productObj.setProductbundles(productRepository.getProductbundleList(product.getProductId(),product.getBranchId()));
		}
		boolean supplierRemoved = true;
		boolean bunldeRemoved = true;
		List<Productsupplier> supplierList = new ArrayList<Productsupplier>();
		List<Productbundle> productbundleList = new ArrayList<Productbundle>();
		List<Integer> supplierIds = new  ArrayList<Integer>();
		List<Integer> bundleIds = new  ArrayList<Integer>();
		List<Productbranchlink> productbranchlinks= new ArrayList<Productbranchlink>();
		try {			
			
			ProductModel p = this.getProductDetails(product.getProductId(),product.getBranchId());	
			
			//Suppliers
			 for(ProductsupplierModel oldSupplier:p.getSupplierlist()){
				 supplierRemoved = true;
			for (ProductsupplierModel newSupplier : product.getSupplierlist()) {								 
					if(oldSupplier.getProductsupplierId()==newSupplier.getProductsupplierId()){
						supplierRemoved = false;							
						break;									
					}
			}
			if(supplierRemoved){							
				this.deleteProductsupplier(oldSupplier);					
				}			
			 }	
			 
			productObj.setBarcode(product.getBarcode());
			productObj.setCreatedBy(product.getCreatedBy());
			productObj.setCreatedDate(product.getCreatedDate());
			productObj.setDescription(product.getDescription());
			productObj.setDiscount(product.getDiscount());
			productObj.setImageDirectory(product.getImageDirectory());
			
			productObj.setHeight(product.getHeight());
			productObj.setLastModifiedDate(product.getLastModifiedDate());
			productObj.setLength(product.getLength());
			productObj.setProductCode(product.getProductCode());
			productObj.setProductName(product.getProductName());
			productObj.setBrandName(product.getBrandName());
			productObj.setReorderPoint(product.getReorderPoint());
			productObj.setReorderQuantity(product.getReorderQuantity());
			productObj.setStatus(product.getStatus());
			productObj.setWeight(product.getWeight());
			productObj.setWidth(product.getWidth());
			productObj.setUom(product.getUom());
			productObj.setProductDesc(product.getProductDesc());
			productObj.setSalesType(product.getSalesType());
			productObj.setOnline(product.getOnline());
		
			productbranchlink.setQuantityonHand(product.getQuantityonHand());
			productbranchlink.setNormalPrice(product.getNormalPrice());
			productbranchlink.setPackingPrice(product.getPackingPrice());
			productbranchlink.setPurchasePrice(product.getPurchasePrice());			
			productbranchlink.setDoctorPrice(product.getDoctorPrice());
			productbranchlink.setSalesrepPrice(product.getSalesrepPrice());
			productbranchlink.setQuantityonalert(product.getQuantityonalert());
			productbranchlink.setFullredemptionpoint(product.getFullredemptionpoint());
			productbranchlink.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productbranchlink.setRedemAmount(product.getRedemAmount());
			productbranchlink.setExpiryDate(product.getCreatedDate());
			productbranchlink.setTaxAmount(product.getTaxAmount());
			productbranchlink.setGstAmount(product.getGstAmount());
			productbranchlink.setTaxCode(product.getTaxCode());
			productbranchlink.setGstCode(product.getGstCode());
			productbranchlink.setCustomerPoint(product.getCustomerPoint());
			productbranchlink.setTaxType(product.getTaxType());

			
			productbranchlink.setProduct(productObj);			
			productbranchlinks.add(productbranchlink);		
			
			productObj.setProductbranchlinks(productbranchlinks);
			
		    List<Integer> indexToRemove=new ArrayList<Integer>();
			List<Productsetlist> oldSetItem=productRepository.getProductSetList(productObj.getProductId());
			int i=0;
			for(Productsetlist oldItem:oldSetItem) {
				indexToRemove.add(i);
				for(ProductsetModel newItem:product.getProductSetList()) {
					if(oldItem.getSetId()==newItem.getSetId()){
						 indexToRemove.remove(indexToRemove.size()-1);
						 break;									
					 }
					
				}
				i++;
			}
			Collections.sort(indexToRemove,Collections.reverseOrder());
			for(int index:indexToRemove) {
				 boolean delSuccess=false;
				 delSuccess=productRepository.deleteSetItemList(oldSetItem.get(index));
			}
			
			for(ProductsetModel setItem:product.getProductSetList()) {
				Productsetlist setObj=new Productsetlist();
				
				Product setItemObj=new Product();
				setItemObj.setProductId(setItem.getProduct().getProductId());
				setObj.setSetId(setItem.getSetId());
				setObj.setProduct(setItemObj);
				setObj.setQuantity(setItem.getQuantity());
				setObj.setProductset(productObj);
				
				productsetlist.add(setObj);				
			}
			
			 productObj.setSetItem(product.isSetItem());
			 productObj.setProductsetlist(productsetlist);
		
			
			for(Productsupplier newSupplier : productObj.getProductsuppliers()){
				supplierIds.add(newSupplier.getProductsupplierId());
			}			
			for (ProductsupplierModel oldSupplier : product.getSupplierlist()) {								 
				
				if(supplierIds.contains(oldSupplier.getProductsupplierId()))
				{	
					for(Productsupplier newSupplier1 : productObj.getProductsuppliers()){
						if(newSupplier1.getProductsupplierId()==oldSupplier.getProductsupplierId())
						{							
							newSupplier1.setPurchasePrice(oldSupplier.getPurchasePrice());
							supplierList.add(newSupplier1);
						}
					}					
				}
				else
				{									
					Productsupplier supplierObj = new Productsupplier();	
					Supplier supplier1 = new Supplier();
					supplier1.setSupplierId(oldSupplier.getSupplierId());
					supplierObj.setPurchasePrice(oldSupplier.getPurchasePrice());
					supplierObj.setProduct(productObj);
					supplierObj.setSupplier(supplier1);					
					supplierList.add(supplierObj);
					
				}
			} 
			productObj.setProductsuppliers(supplierList);	
			
			
			
		/*	 if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
				{		
				 
			for(Productbundle newBundle : productObj.getProductbundles()){
				bundleIds.add(newBundle.getProductbundleId());
			}			
			for (ProductbundleModel oldBundle : product.getProductbundleList()) {								 
				
				if(bundleIds.contains(oldBundle.getProductbundleId()))
				{	
					for(Productbundle newnewBundle1 : productObj.getProductbundles()){
						if(newnewBundle1.getProductbundleId()==oldBundle.getProductbundleId())
						{									
							newnewBundle1.setQuantity(oldBundle.getQuantity());
							productbundleList.add(newnewBundle1);
						}
					}					
				}
				else
				{									
					
					Productbundle bundleObj = new Productbundle();	
					Branch branch1 = new Branch();
					branch1.setBranchId(product.getBranchId());				
					bundleObj.setBranch(branch1);
					
					Product productObj1 = new Product();
					productObj1.setProductId(oldBundle.getProduct().getProductId());				
					bundleObj.setProduct(productObj1);
					
					bundleObj.setQuantity(oldBundle.getQuantity());
					bundleObj.setProductm(productObj);
					
					productbundleList.add(bundleObj);
					
					
				}
			} 
			productObj.setProductbundles(productbundleList);	
			}
			*/
			
			 
			 if(product.getSalesType()!=null && product.getSalesType().equalsIgnoreCase("bundle"))
				{
				for(ProductbundleModel bundle : product.getProductbundleList())
				{
					Productbundle bundleObj = new Productbundle();	
					Branch branch1 = new Branch();
					branch1.setBranchId(product.getBranchId());				
					bundleObj.setBranch(branch1);
					
					Product productObj1 = new Product();
					productObj1.setProductId(bundle.getProduct().getProductId());				
					bundleObj.setProduct(productObj1);
					
					bundleObj.setQuantity(bundle.getQuantity());
					bundleObj.setProductm(productObj);
					
					productbundleList.add(bundleObj);
				}
				productObj.setProductbundles(productbundleList);
				}
			
			
			
			Productcategory productcategory = new Productcategory();
			productcategory.setCategoryId(product.getCategoryId());
			productObj.setProductcategory(productcategory);			
			
			updateSuccess =productRepository.updateProduct(productObj);
			
			productbranchlink=productRepository.getProductPriceDetails(product.getProductId(),product.getBranchId());
			for(Productprice newPrice:productbranchlink.getProduct().getProductprices())
			{
				newPrice.setBarCode(product.getBarcode());
				productRepository.updateProductPrice(newPrice);
			}
			
			
			//productCategoryBO.createMaterials(product.getBomlist(), product.getProductId(), "1");
			
		}

		catch (Exception e) {
			log.info("Error in updateProduct ProductBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}

	@Transactional
	@Override
	public boolean deleteProduct(ProductModel product) throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Productbranchlink productObj = productRepository.getProductDetails(product.getProductId(),product.getBranchId());			
			deleteSuccess = productRepository.deleteProduct(productObj.getProduct());			
			}
		catch (Exception e) {
			log.info("Error in deleteProduct ProductBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}

	@Override
	public ProductModel getProductDetailsByBarcode(String barcode,Integer branchId)
			throws Exception {
		    ProductModel productObj = new ProductModel();	
			List<ProductsupplierModel> supplierList = new ArrayList<ProductsupplierModel>();

			try {			
				Productbranchlink product = productRepository.getProductDetailsByBarcode(barcode,branchId);
				
				productObj.setBarcode(product.getProduct().getBarcode());
				productObj.setBaroldcode(product.getProduct().getBarcode());
				productObj.setCreatedBy(product.getProduct().getCreatedBy());
				productObj.setCreatedDate(product.getProduct().getCreatedDate());
				productObj.setDescription(product.getProduct().getDescription());
				productObj.setDiscount(product.getProduct().getDiscount());
				productObj.setExpiryDate(product.getProduct().getCreatedDate());
				productObj.setHeight(product.getProduct().getHeight());
				productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
				productObj.setLength(product.getProduct().getLength());
				productObj.setProductCode(product.getProduct().getProductCode());
				productObj.setProductName(product.getProduct().getProductName());
				productObj.setBrandName(product.getProduct().getBrandName());
				productObj.setQuantityonHand(product.getQuantityonHand());
				productObj.setReorderPoint(product.getProduct().getReorderPoint());
				productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
				productObj.setStatus(product.getProduct().getStatus());
				productObj.setWeight(product.getProduct().getWeight());
				productObj.setWidth(product.getProduct().getWidth());
				productObj.setProductId(product.getProduct().getProductId());				
				productObj.setNormalPrice(product.getNormalPrice());
				productObj.setPackingPrice(product.getPackingPrice());
				productObj.setPurchasePrice(product.getPurchasePrice());			
				productObj.setDoctorPrice(product.getDoctorPrice());
				productObj.setSalesrepPrice(product.getSalesrepPrice());
				productObj.setQuantityonalert(product.getQuantityonalert());		
				productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
				productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
				productObj.setUom(product.getProduct().getUom());
				productObj.setProductDesc(product.getProduct().getProductDesc());
				productObj.setFullredemptionpoint(product.getFullredemptionpoint());
				productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
				productObj.setRedemAmount(product.getRedemAmount());
				
				productObj.setTaxAmount(product.getTaxAmount());
				productObj.setGstAmount(product.getGstAmount());
				productObj.setTaxCode(product.getTaxCode());
				productObj.setGstCode(product.getGstCode());
				productObj.setCustomerPoint(product.getCustomerPoint());
				productObj.setSalesType(product.getProduct().getSalesType());
				productObj.setTaxType(product.getTaxType());

				productObj.setBranchId(branchId);
				productObj.setProductBranchLinkId(product.getProductBranchLinkId());
				
				for(Productsupplier supplier : product.getProduct().getProductsuppliers())
				{
					ProductsupplierModel supplierObj = new ProductsupplierModel();	
					supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
					supplierObj.setSupplierName(supplier.getSupplier().getSupplierName());
					supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
					supplierObj.setProductId(product.getProduct().getProductId());
					supplierObj.setProductName(product.getProduct().getProductName());		
					supplierObj.setPurchasePrice(supplier.getPurchasePrice());
					supplierList.add(supplierObj);
				}
				productObj.setSupplierlist(supplierList);	
				
			}
			catch (Exception e) {
				log.info("Error in getProductDetailsByBarcode ProductBOImpl " + e);
				throw e;
			}
			return productObj;
	}

	@Override
	public boolean findProductExites(String product) throws Exception {
		boolean exists = false;		
		try {
			exists = productRepository.findProductExites(product);
		}
		catch (Exception e) {
			log.info("Error in findProductExites ProductBOImpl " + e);
			throw e;
		}
		return exists;	
	}

	@Override
	public boolean findbarcodeExites(String barcode) throws Exception {
		boolean exists = false;		
		try {
			exists = productRepository.findbarcodeExites(barcode);
		}
		catch (Exception e) {
			log.info("Error in findbarcodeExites ProductBOImpl " + e);
			throw e;
		}
		return exists;	
	}

	@Override
	public List<ProductModel> getProductList(String barcode,String productName,Integer branchId) throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();
		try {
		List<Productbranchlink> productList = productRepository.getProductList(barcode,productName,branchId);
		for (Productbranchlink product : productList) {
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setSalesType(product.getProduct().getSalesType());
			productObj.setTaxType(product.getTaxType());

			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			
			productObj.setGstCode(product.getGstCode());
			
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
			
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductList By barcode of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}

	
	
	
	@Override
	public List<BranchstockProductModel> getProductBranchStockList(String barcode,String productName,Integer branchId) throws Exception {
		List<BranchstockProductModel> productObjList = new ArrayList<BranchstockProductModel>();
		try {
		List<Productbranchlink> productList = productRepository.getProductBranchStockList(barcode,productName,branchId);
		for (Productbranchlink product : productList) {
			
			BranchstockProductModel productObj = new BranchstockProductModel();
			
			BranchModel branch =  new BranchModel();
			branch.setBranchCode(product.getBranch().getBranchCode());
			branch.setAddress(product.getBranch().getAddress());
			branch.setBranchId(product.getBranch().getBranchId());
			branch.setBranchName(product.getBranch().getBranchName());
			branch.setPhoneNo(product.getBranch().getPhoneNo());
			branch.setPostCode(product.getBranch().getPostCode());
			branch.setEmailAddress(product.getBranch().getEmailAddress());			
			productObj.setBranch(branch);
			productObj.setQuantityonHand(product.getQuantityonHand());
			
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductBranchStockList By barcode of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}
	
	
	
	
	@Override
	public List<ProductsupplierModel> getProductSuppliersByBarcode(String barcode,Integer productId)
			throws Exception {
		
		List<ProductsupplierModel> supplierList = new ArrayList<ProductsupplierModel>();
		ProductModel productObj = new ProductModel();
		try {			
			List<Productsupplier> productList = productRepository.getProductSuppliersByBarcode(barcode,productId);
			
			for (Productsupplier supplier : productList) {				
		
				ProductsupplierModel supplierObj = new ProductsupplierModel();	
				supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
				supplierObj.setSupplierName(supplier.getSupplier().getCompanyName());
				supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
				supplierObj.setProductId(supplier.getProduct().getProductId());
				supplierObj.setProductName(supplier.getProduct().getProductName());
				supplierObj.setPurchasePrice(supplier.getPurchasePrice());
				
				supplierList.add(supplierObj);
			
			}
			
		}
		catch (Exception e) {
			log.info("Error in getProductDetailsByBarcode ProductBOImpl " + e);
			throw e;
		}
		return supplierList;
	}

	@Override
	public List<ProductpriceModel> getProductpriceByBarcode(ProductModel dataTemp,String barcode,Integer branchId)
			throws Exception {
		List<ProductpriceModel> priceListObj = new ArrayList<ProductpriceModel>();
		ProductpriceModel priceObj = new ProductpriceModel();
		try {			
			List<Productprice> priceList = productRepository.getSortedProductpriceBarcode(dataTemp.getProductId(),branchId);
			
			for (Productprice price : priceList) {				
		
				priceObj = new ProductpriceModel();	
				priceObj.setBarCode(price.getBarCode());
				priceObj.setDiscountAmount(price.getDiscountAmount());
				priceObj.setProductName(dataTemp.getProductName());
				priceObj.setProductId(dataTemp.getProductId());
				priceObj.setQuantityFrom(price.getQuantityFrom());				
				priceObj.setUom(dataTemp.getUom());	
				priceObj.setProductPriceId(price.getProductPriceId());
				
				//Productbranchlink p = productRepository.getProductPriceDetails(price.getProduct().getProductId(),branchId);	
				
				priceObj.setUnitPrice(dataTemp.getNormalPrice());
				priceObj.setDiscountPrice(dataTemp.getNormalPrice().subtract(price.getDiscountAmount()));
				priceListObj.add(priceObj);			
			}
			
		}
		catch (Exception e) {
			log.info("Error in getProductpriceByBarcode ProductBOImpl " + e);
			throw e;
		}
		return priceListObj;
	}

	
	
	
	@Override
	public List<ProductpriceModel> getProductpriceByProductId(Integer productId,Integer branchId) throws Exception {
		List<ProductpriceModel> priceListObj = new ArrayList<ProductpriceModel>();
		ProductpriceModel priceObj = new ProductpriceModel();
		try {			
			List<Productprice> priceList = productRepository.getSortedProductpriceBarcode(productId,branchId);
			
			for (Productprice price : priceList) {				
		
				priceObj = new ProductpriceModel();	
				priceObj.setBarCode(price.getBarCode());
				priceObj.setDiscountAmount(price.getDiscountAmount());				
				priceObj.setQuantityFrom(price.getQuantityFrom());					
				priceObj.setProductPriceId(price.getProductPriceId());				
				
				priceListObj.add(priceObj);			
			}
			
		}
		catch (Exception e) {
			log.info("Error in getProductpriceByBarcode ProductBOImpl " + e);
			throw e;
		}
		return priceListObj;
	}

	
	
	
	
	@Transactional
	@Override
	public boolean deleteProductPrice(ProductpriceModel productprice)
			throws Exception {
		boolean deleteSuccess = false;		
		try {			
			Productprice productpriceObj= new Productprice();
			productpriceObj=productRepository.getProductPriceDetailsbyId(productprice.getProductPriceId());
			deleteSuccess = productRepository.deleteProductPrice(productpriceObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteProductPrice ProductBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}
	
	
	@Transactional
	@Override
	public boolean deleteProductsupplier(ProductsupplierModel productsupplier)
			throws Exception {
		boolean deleteSuccess = false;		
		try {			
			Productsupplier productsupplierObj= new Productsupplier();
			productsupplierObj=productRepository.getProductSupplierDetailsbyId(productsupplier.getProductsupplierId());
			deleteSuccess = productRepository.deleteProductsupplier(productsupplierObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteProductsupplier ProductBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}
	
	
	
	
	@Transactional
	@Override
	public boolean deleteProductbundle(ProductbundleModel productbundle) throws Exception {
		boolean deleteSuccess = false;		
		try {			
			Productbundle productbundleObj= new Productbundle();
			productbundleObj=productRepository.getProductbunldeDetailsbyId(productbundle.getProductbundleId());
			deleteSuccess = productRepository.deleteProductbunlde(productbundleObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteProductbundle ProductBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}
	
	
	@Transactional
	@Override
	public boolean updateProductPrice(Integer productId,List<ProductpriceModel> oldproductPriceList,List<ProductpriceModel> newproductPriceList,Integer branchId)
			throws Exception {
		
		boolean updateSuccess = false;	
		boolean priceRemoved = true;
		List<Productprice> newPriceList  = new ArrayList<Productprice>(); 
		List<Integer> priceId = new  ArrayList<Integer>();
		try {			
			Productbranchlink p = productRepository.getProductPriceDetails(productId,branchId);	
			
			List<ProductpriceModel> productPriceList = this.getProductpriceByProductId(productId,branchId);			
			
			 for(ProductpriceModel oldPrice:productPriceList){
		     priceRemoved = true;
			for (ProductpriceModel newPrice : newproductPriceList) {								 
					if(oldPrice.getProductPriceId()==newPrice.getProductPriceId()){
						priceRemoved = false;							
						break;									
					}
			}
			if(priceRemoved){								
				this.deleteProductPrice(oldPrice);					
			}
			
			}			
			 
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			
			for(Productprice newPrice : p.getProduct().getProductprices()){
				priceId.add(newPrice.getProductPriceId());
			}
			
			for (ProductpriceModel oldPrice : newproductPriceList) {								 
				
				Productprice newPrice=new Productprice();
				
				if(priceId.contains(oldPrice.getProductPriceId()))
				{	
					for(Productprice newPrice1 : p.getProduct().getProductprices()){
						if(newPrice1.getProductPriceId()==oldPrice.getProductPriceId())
						{							
							newPrice1.setDiscountAmount(oldPrice.getDiscountAmount());					
							newPrice1.setQuantityFrom(oldPrice.getQuantityFrom());							
							newPrice1.setCreatedBy(oldPrice.getCreatedBy());
							newPrice1.setLastModifiedDate(new Date());	
							newPrice1.setBranch(branch);
							newPriceList.add(newPrice1);
						}
					}
					
				}
				else
				{									
					newPrice.setDiscountAmount(oldPrice.getDiscountAmount());					
					newPrice.setQuantityFrom(oldPrice.getQuantityFrom());					
					newPrice.setUom(oldPrice.getUom());	
					newPrice.setCreatedBy(oldPrice.getCreatedBy());
					newPrice.setLastModifiedDate(new Date());
					newPrice.setBarCode(oldPrice.getBarCode());
					newPrice.setTotalQuantity(new BigDecimal(0.00));
					newPrice.setProduct(p.getProduct());
					newPrice.setBranch(branch);
					newPriceList.add(newPrice);
				}
			} 
			p.getProduct().setProductprices(newPriceList);
			updateSuccess=productRepository.updateProduct(p.getProduct());	
			 
				
			 
			}
		catch (Exception e) {
			log.info("Error in updateProductPrice ProductBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}
	
	
	
	@Transactional
	@Override
	public boolean updateProductbranchlink(ProductModel Product)
			throws Exception {
		try {
			Productbranchlink p = productRepository.getProductbranchlinkDetails(Product.getProductBranchLinkId());	
			productRepository.updateProductbranchlink(p);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductbranchlink ProductDAOImpl " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean createNewProductsupplier(ProductsupplierModel productsupplier)
			throws Exception {
		try {
			Productsupplier supplierObj = new Productsupplier();	
			Supplier supplier1 = new Supplier();
			supplier1.setSupplierId(productsupplier.getSupplierId());
			
			Product product = new Product();
			product.setProductId(productsupplier.getProductId());			
			
			supplierObj.setPurchasePrice(productsupplier.getPurchasePrice());
			supplierObj.setProductsupplierId(productsupplier.getProductsupplierId());
			supplierObj.setProduct(product);
			supplierObj.setSupplier(supplier1);
			
			return productRepository.createNewProductsupplier(supplierObj);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductbranchlink ProductDAOImpl " + e);
			throw e;
		}
		
		
		
	}
	
	public void addProductIntoQuickList(List<Integer> pID) throws Exception{
		
		try {
			List<Integer> idList=pID;
			
			productRepository.addProductIntoQuickList(idList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void removeProductFromQuickList(List<Integer> pID) throws Exception{
	
		try {
			List<Integer> idList=pID;
			
			productRepository.removeProductFromQuickList(idList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<ProductModel> getHotListProduct(int branchId) throws Exception {
		List<ProductModel> productObjList = new ArrayList<ProductModel>();		
		try {
			
			
		List<Productbranchlink> productList = productRepository.getHotListProduct(branchId);

		for (Productbranchlink product : productList) {			
			
			ProductModel productObj = new ProductModel();
			productObj.setBarcode(product.getProduct().getBarcode());
			productObj.setBaroldcode(product.getProduct().getBarcode());
			productObj.setCreatedBy(product.getProduct().getCreatedBy());
			productObj.setCreatedDate(product.getProduct().getCreatedDate());
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());
			
			
			productObj.setDescription(product.getProduct().getDescription());
			productObj.setDiscount(product.getProduct().getDiscount());
			productObj.setExpiryDate(product.getProduct().getCreatedDate());
			productObj.setHeight(product.getProduct().getHeight());
			productObj.setLastModifiedDate(product.getProduct().getLastModifiedDate());
			productObj.setLength(product.getProduct().getLength());
			productObj.setProductCode(product.getProduct().getProductCode());
			productObj.setProductName(product.getProduct().getProductName());
			productObj.setBrandName(product.getProduct().getBrandName());
			productObj.setQuantityonHand(product.getQuantityonHand());
			productObj.setReorderPoint(product.getProduct().getReorderPoint());
			productObj.setReorderQuantity(product.getProduct().getReorderQuantity());
			productObj.setStatus(product.getProduct().getStatus());
			productObj.setWeight(product.getProduct().getWeight());
			productObj.setWidth(product.getProduct().getWidth());
			productObj.setProductId(product.getProduct().getProductId());				
			productObj.setNormalPrice(product.getNormalPrice());
			productObj.setPackingPrice(product.getPackingPrice());
			productObj.setPurchasePrice(product.getPurchasePrice());			
			productObj.setDoctorPrice(product.getDoctorPrice());
			productObj.setSalesrepPrice(product.getSalesrepPrice());
			productObj.setQuantityonalert(product.getQuantityonalert());		
			productObj.setCategoryId(product.getProduct().getProductcategory().getCategoryId());
			productObj.setCategoryName(product.getProduct().getProductcategory().getName());	
			productObj.setUom(product.getProduct().getUom());
			productObj.setProductDesc(product.getProduct().getProductDesc());
			productObj.setFullredemptionpoint(product.getFullredemptionpoint());
			productObj.setHalfredemptionpoint(product.getHalfredemptionpoint());
			productObj.setRedemAmount(product.getRedemAmount());
			productObj.setTaxAmount(product.getTaxAmount());
			productObj.setGstAmount(product.getGstAmount());
			productObj.setTaxCode(product.getTaxCode());
			productObj.setGstCode(product.getGstCode());
			productObj.setCustomerPoint(product.getCustomerPoint());
			productObj.setSalesType(product.getProduct().getSalesType());
			productObj.setImageDirectory(product.getProduct().getImageDirectory());
			productObj.setIsCustomeList(product.getProduct().getIsCustomList());
			
			productObj.setBranchId(branchId);
			productObj.setProductBranchLinkId(product.getProductBranchLinkId());
	
			productObjList.add(productObj);
		}
		}
		catch(Exception e)
		{
			log.info("Error in getProductList of ProductBOImpl "+ e.toString());
			throw e;
		}
		return productObjList;
	}
	

}
