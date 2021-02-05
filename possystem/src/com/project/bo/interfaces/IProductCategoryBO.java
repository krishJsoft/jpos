package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.ProductmaterailModel;
import com.project.model.datamodel.UomtypeModel;
import com.project.model.his.Uomtype;


public interface IProductCategoryBO {	

	public boolean createNewProductcategory(ProductcategoryModel pcategory) throws Exception;

	public boolean updateProductcategory(ProductcategoryModel pcategory) throws Exception;

	public boolean deleteProductcategory(Integer categoryId) throws Exception;
	
	public List<ProductcategoryModel> findByProductcategoryList(Integer categoryId,String Status) throws Exception;
	
	public List<ProductcategoryModel> findByProductcategoryChildList(Integer parentCategoryId,String Status) throws Exception;
	
	public ProductcategoryModel getProductcategoryDetails(Integer categoryId) throws Exception;
	
    public boolean findCategoryExites(Integer categoryId ,Integer parentCategoryId, String categoryName) throws Exception;
    
    
    
    public boolean createNewBom(ProductmaterailModel bom) throws Exception;

    public boolean  createMaterials(List<ProductmaterailModel> boms,Integer productId,String status) throws Exception;
    
	public boolean updateBom(ProductmaterailModel bom) throws Exception;

	public boolean deleteBom(Integer bomId) throws Exception;
	
	public List<ProductmaterailModel> findByBomList(Integer productId,String Status) throws Exception;
	
	public List<ProductmaterailModel> findByBomChildList(Integer parentproductId,String Status) throws Exception;
	
	public ProductmaterailModel getBomDetails(Integer bomId) throws Exception;
	
    public boolean findBomExites(Integer categoryId ,Integer parentproductId, String categoryName) throws Exception;
    
    
    public boolean createNewUom(UomtypeModel uom) throws Exception;

	public boolean updateUom(UomtypeModel uom) throws Exception;

	public boolean deleteUom(UomtypeModel uom) throws Exception;
	
	public List<UomtypeModel> findByUomtypeList(String UOMName) throws Exception;
	
    public boolean findUomtypeExites( String UOMName) throws Exception;
    
    public UomtypeModel getUomtypeDetails(Integer UOMId) throws Exception;


	public List<Integer> getValidCategoryIdWithProducts() throws Exception;

	public List<ProductcategoryModel> findByParentCategoryId(Integer categoryId, String status) throws Exception;

	public List<ProductcategoryModel> loadCategoryListForPrinter(Integer printerId, int branchId) throws Exception;

	List<ProductcategoryModel> loadCategoryWithoutPrinter(int branchId) throws Exception;

	public void updatePrinterIdForCategory(Integer integer, List<Integer> id) throws Exception;

	public ProductcategoryModel getProductCategoryDetails2(int categoryId) throws Exception;

	public List<Integer> getValidCategoryIdWithProducts2(int branchId) throws Exception;

	public List<ProductcategoryModel> findByProductcategoryList2(Integer categoryId, String status, Integer branchId, Boolean online, Integer syncStatus) throws Exception;

	public List<ProductcategoryModel> getCategoryPrinterList(Integer branchId) throws Exception;

	public ProductcategoryModel fetchOnlineCategoryDetails(Integer categoryId, String name, Integer level,
			String status, Integer onlineBranchId) throws Exception;

	public Boolean createNewOnlineProductcategory(ProductcategoryModel obj) throws Exception;



}
