package com.project.dao.interfaces;

import java.util.List;


import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductsetModel;
import com.project.model.his.Product;
import com.project.model.his.Productbranchlink;
import com.project.model.his.Productbundle;
import com.project.model.his.Productprice;
import com.project.model.his.Productsetlist;
import com.project.model.his.Productsupplier;

public interface IProductDAO {

	
	public List<Productbranchlink> findByProductListAll(Integer branchId) throws Exception;

	//public List<Productbranchlink> getCustomProductList(Integer branchId) throws Exception;
	
	public List<Product> findByProductAll() throws Exception;
	
	public List<Object[]> findProduct() throws Exception;
	
	public int getProductCount(List<Integer> categoryId,Integer supplierId , String barcode , String status,Integer branchId,String productName , Integer quantityonHand,String brandName,String salesType) throws Exception;

	public List<Productbranchlink> getProductList(int[] ids,List<Integer> categoryId,Integer supplierId , String barcode , String status, int start, int howMany,Integer branchId,String productName,Integer quantityonHand,String brandName,String salesType)
			throws Exception;
	
	public List<Productbranchlink> getProductListReport(List<Integer> categoryId,Integer supplierId, String barcode, String status, Integer branchId,String productName,String brandName) throws Exception;
	
	public List<Productbranchlink> getProductListUsingLikeProductCode(String productCode,Integer branchId) throws Exception;
	
	public Productbranchlink getProductDetails(int productId,Integer branchId) throws Exception;
	
	public Productbranchlink getProductbranchlinkDetails(Integer productBranchLinkId) throws Exception;
	
	public Productbranchlink getProductPriceDetails(int productId,Integer branchId) throws Exception;
	
	public boolean createNewProduct(Product Product) throws Exception;

	public boolean updateProduct(Product Product) throws Exception;
	
	public boolean updateProductbranchlink(Productbranchlink productbranchlink) throws Exception;
	
	public boolean deleteProduct(Product Product) throws Exception;
	
	public Productbranchlink getProductDetailsByBarcode(String barcode,Integer branchId) throws Exception;
	
	public boolean findProductExites(String Product) throws Exception;
	
	public boolean findbarcodeExites(String barcode) throws Exception;
	
	public List<Productbranchlink> getProductList(String barCode , String productName,Integer branchId) throws Exception;

	public List<Productbranchlink> getProductBranchStockList(String barcode,String productName,Integer branchId) throws Exception;
	
	public List<Productsupplier> getProductSuppliersByBarcode(String barcode,Integer productId) throws Exception;
	
	public List<Productsupplier> getProductPriceSuppliers(String barcode,Integer productId,Integer supplierId) throws Exception;
	
	public List<Productprice> getProductpriceByBarcode(String barcode,Integer branchId) throws Exception;
	
	
	public List<Productprice> getSortedProductpriceByBarcode(String barCode,Integer branchId)throws Exception;
	
	public List<Productprice> getSortedProductpriceBarcode(int productId,Integer branchId)	throws Exception;
			
	public boolean deleteProductPrice(Productprice productprice) throws Exception;
	
	public boolean updateProductPrice(Productprice Product) throws Exception;
	
	public Productprice getProductPriceDetailsbyId(int productpriceId) throws Exception;
	
	public boolean deleteProductsupplier(Productsupplier productsupplier) throws Exception;
	
	public Productsupplier getProductSupplierDetailsbyId(Integer productsupplierId) throws Exception;
	
	public Productbranchlink getProductbranchlinkMasterDetails(int productId,Integer branchId) throws Exception;
	
	public boolean createNewProductbranchlink(Productbranchlink productbranchlink) throws Exception;
	
	public boolean createNewProductprice(Productprice productprice) throws Exception;
	
	public boolean createNewProductsupplier(Productsupplier productsupplier) throws Exception;
			
	public List<Productbundle> getProductbundleList(int productId,Integer branchId)	throws Exception;
	
	public boolean deleteProductbunlde(Productbundle productbundle)	throws Exception;
	
	public Productbundle getProductbunldeDetailsbyId(Integer productbundleId) throws Exception;
	
	public boolean deleteProductbranchlink(Productbranchlink Product) throws Exception;
	
	public void addProductIntoQuickList(List<Integer> pID) throws Exception;
	
	public void removeProductFromQuickList(List<Integer> pID) throws Exception;

	public List<Productbranchlink> getHotListProduct(Integer branchId) throws Exception;

	public List<Productsetlist> getProductSetList(Integer productId) throws Exception;

	public boolean deleteSetItemList(Productsetlist productsetlist) throws Exception;;
}
