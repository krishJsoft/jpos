package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.his.Productsupplier;

public interface ISupplierBO {

	
	public boolean createNewSupplier(SupplierModel supplier) throws Exception;

	public boolean updateSupplier(SupplierModel supplier) throws Exception;

	public boolean deleteSupplier(SupplierModel supplier) throws Exception;
	
	public List<SupplierModel> findBySupplierList(String companyRegNo,String Status) throws Exception;
		
	public SupplierModel getSupplierDetails(Integer supplierId) throws Exception;
	
    public boolean findSupplierCompanyRegNoExites(String companyRegNo) throws Exception;
	
    public boolean findSupplierCodeExites(String supplierCode)	throws Exception;
    
    public boolean findSupplierEmailExites(String email)	throws Exception;
 
    public List<SupplierModel> findsupplierLogin(String email) throws Exception;
    
    public List<ProductsupplierModel> findproductsupplierList(Integer supplierId,Integer productId) throws Exception;
    
}
