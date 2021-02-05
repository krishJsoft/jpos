package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductbundleModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.stock.BranchstockProductModel;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Productprice;
import com.project.model.his.Productsupplier;


public interface IProductBO {

	public List<ProductModel> findByProductListAll(Integer branchId) throws Exception;
	
	//public List<ProductModel> getCustomProductList(Integer branchId) throws Exception;
	
	public List<ProductModel> findByProductAll() throws Exception;
	
	public int getProductCount(List<Integer> categoryId,Integer supplierId , String barcode , String status,Integer branchId,String productName, Integer quantityonHand,String brandName,String salesType) throws Exception;

	public List<ProductModel> getProductList(int[] ids,List<Integer> categoryId,Integer supplierId , String barcode , String status, int start, int howMany,Integer branchId,String productName, Integer quantityonHand,String brandName,String salesType)
			throws Exception;
	
	public List<ProductModel> getProductListUsingLikeProductCode(String productCode) throws Exception;
	
	public ProductModel getProductDetails(int productId,Integer branchId) throws Exception;
	
	public ProductModel getProductPriceDetails(int productId,Integer branchId) throws Exception;
	
	public List<ProductpriceModel> getSortedProductpriceByBarcode(String barcode,Integer branchId) throws Exception;
	
	public List<ProductModel> getProductListReport(String categoryId,Integer supplierId, String barcode, String status, Integer branchId,String productName,String brandName,String imageDirectory) throws Exception;

	public List<BranchstockProductModel> getProductBranchStockList(String barcode,String productName,Integer branchId) throws Exception;
	
	public List<ProductpriceModel> getSortedProductpriceBarcode(int productId,Integer branchId) throws Exception;
	
	public List<ProductpriceModel> getProductpriceByProductId(Integer productId,Integer branchId) throws Exception;
	
	public boolean createNewProduct(ProductModel Product) throws Exception;

	public boolean updateProduct(ProductModel Product) throws Exception;
	
	public boolean updateProductbranchlink(ProductModel Product) throws Exception;
	
	public boolean deleteProduct(ProductModel Product) throws Exception;
	
	public ProductModel getProductDetailsByBarcode(String barcode,Integer branchId) throws Exception;
	
	public boolean findProductExites(String Product) throws Exception;
	
	public boolean findbarcodeExites(String barcode) throws Exception;
	
	public List<ProductModel> getProductList(String barcode,String productName,Integer branchId) throws Exception;
	
	
	public List<ProductsupplierModel> getProductSuppliersByBarcode(String barcode,Integer productId) throws Exception;
	
	public List<ProductpriceModel> getProductpriceByBarcode(ProductModel Product,String barcode,Integer branchId) throws Exception;
			
	public boolean deleteProductPrice(ProductpriceModel productprice) throws Exception;
	
	public boolean updateProductPrice(Integer productId,List<ProductpriceModel> deleteproductPriceList,List<ProductpriceModel> newproductPriceList,Integer branchId)
			throws Exception;
			
	public boolean deleteProductsupplier(ProductsupplierModel productsupplier) throws Exception;
	
	public boolean createNewProductsupplier(ProductsupplierModel productsupplier) throws Exception;
	
	public boolean deleteProductbundle(ProductbundleModel productbundle) throws Exception;
	
	public void addProductIntoQuickList(List<Integer> pID) throws Exception;
	
	public void removeProductFromQuickList(List<Integer> pID) throws Exception;

	public List<ProductModel> getHotListProduct(int branchId) throws Exception;
}
