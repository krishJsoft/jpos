package com.project.bo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.ISupplierBO;
import com.project.dao.interfaces.ISupplierDAO;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.SupplierdocumentModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.his.Productsupplier;
import com.project.model.his.Purchaserequestbreakdown;
import com.project.model.his.Supplier;
import com.project.model.his.Supplierdocument;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */

@Service("supplierBO")
public class SupplierBOImpl implements ISupplierBO {
	
	public static Logger log = LoggerFactory.getLogger(SupplierBOImpl.class);

	@Resource(name = "supplierRepository")
	private ISupplierDAO supplierRepository;
	
	@Transactional
	@Override
	public boolean createNewSupplier(SupplierModel supplier) throws Exception {
		boolean saveSuccess = false;
		Supplier supplierObj = new Supplier();
		List<Supplierdocument> docmentList = new ArrayList<Supplierdocument>();
		try {
			
			supplierObj.setAddress(supplier.getAddress());
			supplierObj.setAccountNumber(supplier.getAccountNumber());
			supplierObj.setBankName(supplier.getBankName());
			supplierObj.setBranchName(supplier.getBranchName());
			//supplierObj.setCompanyName(supplier.getCompanyName());
			supplierObj.setCompanyName(supplier.getSupplierName());
			supplierObj.setCompanyRegNo(supplier.getCompanyRegNo());
			supplierObj.setContactPerson(supplier.getContactPerson());
			supplierObj.setContactPersonNumber(supplier.getContactPersonNumber());
			supplierObj.setCurrency(supplier.getCurrency());
			supplierObj.setEmail(supplier.getEmail());
			supplierObj.setCity(supplier.getCity());
			supplierObj.setPaymentTerms(supplier.getPaymentTerms());
			supplierObj.setRemarks(supplier.getRemarks());
			supplierObj.setSupplierName(supplier.getSupplierName());
			supplierObj.setSupportingFileName(supplier.getSupportingFileName());		
			supplierObj.setCountry(supplier.getCountry());
			supplierObj.setCreatedBy(supplier.getCreatedBy());
			supplierObj.setCreatedDate(supplier.getCreatedDate());			
			supplierObj.setDeliveryMethod(supplier.getDeliveryMethod());		
			supplierObj.setFaxNo(supplier.getFaxNo());			
			supplierObj.setLastModifiedDate(supplier.getLastModifiedDate());			
			supplierObj.setMobileNo(supplier.getMobileNo());
			supplierObj.setPhoneNo(supplier.getPhoneNo());
			supplierObj.setPostCode(supplier.getPostCode());	
			supplierObj.setState(supplier.getState());				
			supplierObj.setStatus(supplier.getStatus());	
			supplierObj.setSupplierCode(supplier.getSupplierCode());			
			supplierObj.setForceReset("1");
			supplierObj.setInvalidAttempts(0);			
			supplierObj.setPassword(supplier.getPassword());
			supplierObj.setThemeName(supplier.getThemeName());
			
			/*for(SupplierdocumentModel doc : supplier.getDoclist())
			{
				Supplierdocument docment = new Supplierdocument();		
				
				docment.setDocumentName(doc.getDocumentName());      
				docment.setFileextention(doc.getFileextention());
				docment.setFilelocation(doc.getFilelocation());
				docment.setSupplier(supplierObj);  
				docmentList.add(docment);
			}
			supplierObj.setSupplierdocuments(docmentList);*/
			saveSuccess = supplierRepository.createNewSupplier(supplierObj);
	}
		
		catch (Exception e) {
			log.info("Error in createNewSupplier SupplierBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}

	@Transactional
	@Override
	public boolean updateSupplier(SupplierModel supplier) throws Exception {
		boolean updateSuccess = false;
		Supplier supplierObj = supplierRepository.getSupplierDetails(supplier.getSupplierId());
		List<Supplierdocument> docmentList = new ArrayList<Supplierdocument>();
		Supplierdocument docment=null;
		try {
			
			supplierObj.setAddress(supplier.getAddress());
			supplierObj.setAccountNumber(supplier.getAccountNumber());
			supplierObj.setBankName(supplier.getBankName());
			supplierObj.setBranchName(supplier.getBranchName());
			//supplierObj.setCompanyName(supplier.getCompanyName());
		    supplierObj.setCompanyName(supplier.getSupplierName());
			supplierObj.setCompanyRegNo(supplier.getCompanyRegNo());
			supplierObj.setContactPerson(supplier.getContactPerson());;
			supplierObj.setContactPersonNumber(supplier.getContactPersonNumber());
			supplierObj.setCity(supplier.getCity());
			supplierObj.setCurrency(supplier.getCurrency());
			supplierObj.setEmail(supplier.getEmail());
			supplierObj.setPaymentTerms(supplier.getPaymentTerms());
			supplierObj.setRemarks(supplier.getRemarks());
			supplierObj.setSupplierName(supplier.getSupplierName());
			supplierObj.setSupportingFileName(supplier.getSupportingFileName());		
			supplierObj.setCountry(supplier.getCountry());
			supplierObj.setCreatedBy(supplier.getCreatedBy());
			supplierObj.setCreatedDate(supplier.getCreatedDate());			
			supplierObj.setDeliveryMethod(supplier.getDeliveryMethod());		
			supplierObj.setFaxNo(supplier.getFaxNo());			
			supplierObj.setLastModifiedDate(supplier.getLastModifiedDate());			
			supplierObj.setMobileNo(supplier.getMobileNo());
			supplierObj.setPhoneNo(supplier.getPhoneNo());
			supplierObj.setPostCode(supplier.getPostCode());	
			supplierObj.setState(supplier.getState());				
			supplierObj.setStatus(supplier.getStatus());			
			supplierObj.setSupplierCode(supplier.getSupplierCode());
				
			supplierObj.setPassword(supplier.getPassword());
			supplierObj.setThemeName(supplier.getThemeName());
			supplierObj.setInvalidAttempts(supplier.getInvalidAttempts());	
			supplierObj.setForceReset(supplier.getForceReset());
			
			Iterator<Supplierdocument> documentItemItr = supplierObj.getSupplierdocuments().iterator();	
		
			/*for(SupplierdocumentModel doc :supplier.getDoclist() )
			{				
				if (documentItemItr.hasNext()) {						
					docment = (Supplierdocument) documentItemItr.next();
					if(doc.getSupplierdocumentId()==docment.getSupplierdocumentId())
					{	
						
						
					}
				}
				else {
					docment = new Supplierdocument();				
					}						
				
				docment.setDocumentName(doc.getDocumentName());      
				docment.setFileextention(doc.getFileextention());
				docment.setFilelocation(doc.getFilelocation());
				docment.setSupplier(supplierObj);  
				docmentList.add(docment);			
				
			}
			supplierObj.setSupplierdocuments(docmentList);		*/	
			updateSuccess = supplierRepository.updateSupplier(supplierObj);			
		}

		catch (Exception e) {
			log.info("Error in updateSupplier SupplierBOImpl " + e);
			throw e;
		}
		return updateSuccess;

	}

	@Override
	public boolean deleteSupplier(SupplierModel supplier) throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Supplier supplierObj = supplierRepository.getSupplierDetails(supplier.getSupplierId());			
			deleteSuccess = supplierRepository.deleteSupplier(supplierObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteSupplier SupplierBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}

	@Override
	public List<SupplierModel> findBySupplierList(String companyRegNo,
			String Status) throws Exception {
		List<SupplierModel> supplierObjList = new ArrayList<SupplierModel>();	
		 List<Supplier> supplierList = new ArrayList<Supplier>();
		try {			
			supplierList = supplierRepository.findBySupplierList(companyRegNo, Status);
			
			for (Supplier supplier : supplierList) {
				
				SupplierModel supplierObj = new SupplierModel();	
				supplierObj.setAddress(supplier.getAddress());
				supplierObj.setAccountNumber(supplier.getAccountNumber());
				supplierObj.setBankName(supplier.getBankName());
				supplierObj.setBranchName(supplier.getBranchName());
				supplierObj.setCompanyName(supplier.getCompanyName());					
				supplierObj.setCompanyOldRegNo(supplier.getCompanyRegNo());
				supplierObj.setCompanyRegNo(supplier.getCompanyRegNo());
				supplierObj.setContactPerson(supplier.getContactPerson());
				supplierObj.setContactPersonNumber(supplier.getContactPersonNumber());
				supplierObj.setCurrency(supplier.getCurrency());
				supplierObj.setCity(supplier.getCity());
				supplierObj.setEmail(supplier.getEmail());
				supplierObj.setPaymentTerms(supplier.getPaymentTerms());
				supplierObj.setRemarks(supplier.getRemarks());
				supplierObj.setSupplierName(supplier.getSupplierName());
				supplierObj.setSupportingFileName(supplier.getSupportingFileName());		
				supplierObj.setCountry(supplier.getCountry());
				supplierObj.setCreatedBy(supplier.getCreatedBy());
				supplierObj.setCreatedDate(supplier.getCreatedDate());			
				supplierObj.setDeliveryMethod(supplier.getDeliveryMethod());		
				supplierObj.setFaxNo(supplier.getFaxNo());			
				supplierObj.setLastModifiedDate(supplier.getLastModifiedDate());			
				supplierObj.setMobileNo(supplier.getMobileNo());
				supplierObj.setPhoneNo(supplier.getPhoneNo());
				supplierObj.setPostCode(supplier.getPostCode());	
				supplierObj.setState(supplier.getState());				
				supplierObj.setStatus(supplier.getStatus());		
				supplierObj.setSupplierId(supplier.getSupplierId());
				supplierObj.setSupplierCode(supplier.getSupplierCode());
				supplierObj.setSupplierOldCode(supplier.getSupplierCode());
				supplierObj.setInvalidAttempts(supplier.getInvalidAttempts());
				supplierObj.setThemeName(supplier.getThemeName());
				supplierObj.setForceReset(supplier.getForceReset());
				
				supplierObjList.add(supplierObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findBySupplierList SupplierBOImpl " + e);
			throw e;
		}
		return supplierObjList;
	}

	@Override
	public SupplierModel getSupplierDetails(Integer supplierId)
			throws Exception {
			SupplierModel supplierObj = new SupplierModel();	
			List<SupplierdocumentModel> docmentList = new ArrayList<SupplierdocumentModel>();
			try {			
				Supplier supplier = supplierRepository.getSupplierDetails(supplierId);
				
				supplierObj.setAddress(supplier.getAddress());
				supplierObj.setAccountNumber(supplier.getAccountNumber());
				supplierObj.setBankName(supplier.getBankName());
				supplierObj.setBranchName(supplier.getBranchName());
				supplierObj.setCompanyName(supplier.getCompanyName());				
				supplierObj.setCompanyOldRegNo(supplier.getCompanyRegNo());
				supplierObj.setCompanyRegNo(supplier.getCompanyRegNo());
				supplierObj.setContactPerson(supplier.getContactPerson());
				supplierObj.setContactPersonNumber(supplier.getContactPersonNumber());
				supplierObj.setCurrency(supplier.getCurrency());
				supplierObj.setCity(supplier.getCity());
				supplierObj.setEmail(supplier.getEmail());
				supplierObj.setPaymentTerms(supplier.getPaymentTerms());
				supplierObj.setRemarks(supplier.getRemarks());
				supplierObj.setSupplierName(supplier.getSupplierName());
				supplierObj.setSupportingFileName(supplier.getSupportingFileName());		
				supplierObj.setCountry(supplier.getCountry());
				supplierObj.setCreatedBy(supplier.getCreatedBy());
				supplierObj.setCreatedDate(supplier.getCreatedDate());			
				supplierObj.setDeliveryMethod(supplier.getDeliveryMethod());		
				supplierObj.setFaxNo(supplier.getFaxNo());			
				supplierObj.setLastModifiedDate(supplier.getLastModifiedDate());			
				supplierObj.setMobileNo(supplier.getMobileNo());
				supplierObj.setPhoneNo(supplier.getPhoneNo());
				supplierObj.setPostCode(supplier.getPostCode());	
				supplierObj.setState(supplier.getState());				
				supplierObj.setStatus(supplier.getStatus());		
				supplierObj.setSupplierId(supplier.getSupplierId());
				supplierObj.setSupplierCode(supplier.getSupplierCode());
				supplierObj.setSupplierOldCode(supplier.getSupplierCode());
				
				supplierObj.setInvalidAttempts(supplier.getInvalidAttempts());
				supplierObj.setThemeName(supplier.getThemeName());
				supplierObj.setForceReset(supplier.getForceReset());
				
				
				/*for(Supplierdocument doc : supplier.getSupplierdocuments())
				{
					SupplierdocumentModel docment = new SupplierdocumentModel();		
					
					docment.setDocumentName(doc.getDocumentName());      
					docment.setFileextention(doc.getFileextention());
					docment.setFilelocation(doc.getFilelocation());
					docment.setSupplierdocumentId(doc.getSupplierdocumentId());
					docment.setSupplierId(supplier.getSupplierId());
					docmentList.add(docment);
				}
				supplierObj.setDoclist(docmentList);*/
				
				
				
			}
			catch (Exception e) {
				log.info("Error in getSupplierDetails SupplierBOImpl " + e);
				throw e;
			}
			return supplierObj;
	}

	@Override
	public boolean findSupplierCompanyRegNoExites(String companyRegNo)
			throws Exception {
		boolean exists = false;		
		try {
			exists = supplierRepository.findSupplierCompanyRegNoExites(companyRegNo);
		}
		catch (Exception e) {
			log.info("Error in findSupplierCompanyRegNoExites SupplierBOImpl " + e);
			throw e;
		}
		return exists;
	}
	
	
	@Override
	public boolean findSupplierCodeExites(String supplierCode)	throws Exception {
		boolean exists = false;		
		try {
			exists = supplierRepository.findSupplierCodeExites(supplierCode);
		}
		catch (Exception e) {
			log.info("Error in findSupplierCodeExites SupplierBOImpl " + e);
			throw e;
		}
		return exists;
	}
	
	@Override
	public boolean findSupplierEmailExites(String email)	throws Exception {
		boolean exists = false;		
		try {
			exists = supplierRepository.findSupplierEmailExites(email);
		}
		catch (Exception e) {
			log.info("Error in findSupplierEmailExites SupplierBOImpl " + e);
			throw e;
		}
		return exists;
	}
	
	@Override
	public List<SupplierModel> findsupplierLogin(String email) throws Exception {
		List<SupplierModel> supplierObjList = new ArrayList<SupplierModel>();	
		 List<Supplier> supplierList = new ArrayList<Supplier>();
		try {			
			supplierList = supplierRepository.findsupplierLogin(email);
			
			for (Supplier supplier : supplierList) {
				
				SupplierModel supplierObj = new SupplierModel();	
				supplierObj.setAddress(supplier.getAddress());
				supplierObj.setAccountNumber(supplier.getAccountNumber());
				supplierObj.setBankName(supplier.getBankName());
				supplierObj.setBranchName(supplier.getBranchName());
				supplierObj.setCompanyName(supplier.getCompanyName());					
				supplierObj.setCompanyOldRegNo(supplier.getCompanyRegNo());
				supplierObj.setCompanyRegNo(supplier.getCompanyRegNo());
				supplierObj.setContactPerson(supplier.getContactPerson());
				supplierObj.setContactPersonNumber(supplier.getContactPersonNumber());
				supplierObj.setCurrency(supplier.getCurrency());
				supplierObj.setCity(supplier.getCity());
				supplierObj.setEmail(supplier.getEmail());
				supplierObj.setPaymentTerms(supplier.getPaymentTerms());
				supplierObj.setRemarks(supplier.getRemarks());
				supplierObj.setSupplierName(supplier.getSupplierName());
				supplierObj.setSupportingFileName(supplier.getSupportingFileName());		
				supplierObj.setCountry(supplier.getCountry());
				supplierObj.setCreatedBy(supplier.getCreatedBy());
				supplierObj.setCreatedDate(supplier.getCreatedDate());			
				supplierObj.setDeliveryMethod(supplier.getDeliveryMethod());		
				supplierObj.setFaxNo(supplier.getFaxNo());			
				supplierObj.setLastModifiedDate(supplier.getLastModifiedDate());			
				supplierObj.setMobileNo(supplier.getMobileNo());
				supplierObj.setPhoneNo(supplier.getPhoneNo());
				supplierObj.setPassword(supplier.getPassword());
				supplierObj.setPostCode(supplier.getPostCode());	
				supplierObj.setState(supplier.getState());				
				supplierObj.setStatus(supplier.getStatus());		
				supplierObj.setSupplierId(supplier.getSupplierId());
				supplierObj.setSupplierCode(supplier.getSupplierCode());
				supplierObj.setSupplierOldCode(supplier.getSupplierCode());
				supplierObj.setInvalidAttempts(supplier.getInvalidAttempts());
				supplierObj.setThemeName(supplier.getThemeName());
				supplierObj.setForceReset(supplier.getForceReset());
				
				supplierObjList.add(supplierObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findsupplierLogin SupplierBOImpl " + e);
			throw e;
		}
		return supplierObjList;
	}
	
	
	@Override
	public List<ProductsupplierModel> findproductsupplierList(Integer supplierId,Integer productId) throws Exception {
		List<ProductsupplierModel> supplierObjList = new ArrayList<ProductsupplierModel>();	
		 List<Productsupplier> supplierList = new ArrayList<Productsupplier>();
		try {			
			supplierList = supplierRepository.findproductsupplierList(supplierId,productId);
			
			for (Productsupplier supplier : supplierList) {
				
				ProductsupplierModel supplierObj = new ProductsupplierModel();	
				supplierObj.setProductCode(supplier.getProduct().getBarcode());
				supplierObj.setProductId(supplier.getProduct().getProductId());
				supplierObj.setProductName(supplier.getProduct().getProductName());
				supplierObj.setProductsupplierId(supplier.getProductsupplierId());
				supplierObj.setPurchasePrice(supplier.getPurchasePrice());
				supplierObj.setSupplierAddress(supplier.getSupplier().getAddress());
				supplierObj.setSupplierId(supplier.getSupplier().getSupplierId());
				supplierObj.setSupplierName(supplier.getSupplier().getSupplierName());				
				
				supplierObjList.add(supplierObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findproductsupplierList SupplierBOImpl " + e);
			throw e;
		}
		return supplierObjList;
	}
	

}
