package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Productsupplier;
import com.project.model.his.Supplier;

public interface ISupplierDAO {

	
	public boolean createNewSupplier(Supplier supplier) throws Exception;

	public boolean updateSupplier(Supplier supplier) throws Exception;

	public boolean deleteSupplier(Supplier supplier) throws Exception;
	
	public List<Supplier> findBySupplierList(String companyRegNo,String Status) throws Exception;
		
	public Supplier getSupplierDetails(Integer supplierId) throws Exception;
	
    public boolean findSupplierCompanyRegNoExites(String companyRegNo) throws Exception;
	
    public boolean findSupplierEmailExites(String email) throws Exception;
    
    public List<Supplier> findsupplierLogin(String email) throws Exception;
    
    public boolean findSupplierCodeExites(String supplierCode)	throws Exception;
    
    public List<Productsupplier> findproductsupplierList(Integer supplierId,Integer productId) throws Exception;
    
}
