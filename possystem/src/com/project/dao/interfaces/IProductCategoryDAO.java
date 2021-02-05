package com.project.dao.interfaces;

import java.util.List;

import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.his.Kategoriler;
import com.project.model.his.Productcategory;
import com.project.model.his.Productmaterail;
import com.project.model.his.Uomtype;

public interface IProductCategoryDAO {

	
	public boolean createNewProductcategory(Productcategory pcategory) throws Exception;

	public boolean updateProductcategory(Productcategory pcategory) throws Exception;

	public boolean deleteProductcategory(Productcategory pcategory) throws Exception;
	
	public List<Productcategory> findByProductcategoryList(Integer categoryId,String Status) throws Exception;
	
	public List<Productcategory> findByProductcategoryChildList(Integer parentCategoryId,String Status) throws Exception;
	
	public Productcategory getProductcategoryDetails(Integer categoryId) throws Exception;
	
    public boolean findCategoryExites(Integer categoryId ,Integer parentCategoryId, String categoryName) throws Exception;
    
	public List<Kategoriler> findAll();
	public Kategoriler findByKategoriId(Integer kategoriId);
	public List<Kategoriler> findByKategoriAdi(Integer kategoriAdi);
	public List<Kategoriler> findByKatUstId(Integer katUstId);

    
	public boolean createNewUom(Uomtype uom) throws Exception;

	public boolean updateUom(Uomtype uom) throws Exception;

	public boolean deleteUom(Uomtype uom) throws Exception;
	
	public List<Uomtype> findByUomtypeList(String UOMName) throws Exception;
	
    public boolean findUomtypeExites( String UOMName) throws Exception;
    
	public Uomtype getUomtypeDetails(Integer UOMId) throws Exception;
	
	
	public boolean createNewBom(Productmaterail bom) throws Exception;

	public boolean updateBom(Productmaterail bom) throws Exception;

	public boolean deleteBom(Productmaterail bom) throws Exception;
	
	public List<Productmaterail> findByBomList(Integer productId,String Status) throws Exception;
	
	public List<Productmaterail> findByBomChildList(Integer parentproductId,String Status) throws Exception;
	
	public Productmaterail getBomDetails(Integer bomId) throws Exception;
	
    public boolean findBomExites(Integer productId ,Integer parentproductId, String categoryName) throws Exception;


	public List<Integer> getValidCategoryIdWithProducts() throws Exception;

	public List<Productcategory> findByParentCategoryId(Integer categoryId, String status) throws Exception;

	public List<Productcategory> loadCategoryListForPrinter(Integer printerId, Integer branchId) throws Exception;

	List<Productcategory> loadCategoryWithoutPrinter(Integer branchId) throws Exception;

	public void updatePrinterIdForCategory(Integer printerId, List<Integer> idList)  throws Exception;

	public Productcategory getProductcategoryDetails2(int categoryId) throws Exception;

	public List<Integer> getValidCategoryIdWithProducts2(int branchId) throws Exception;

	public List<Productcategory> findByProductcategoryList2(Integer categoryId, String status, Integer branchId, Boolean online, Integer syncStatus) throws Exception;

	public  List<Productcategory> getCategoryPrinterList(Integer branchId) throws Exception;


}
