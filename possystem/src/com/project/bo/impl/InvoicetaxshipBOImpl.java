package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IInvoicetaxshipBO;
import com.project.dao.interfaces.IInvoicetaxshipDAO;
import com.project.model.his.Invoicetaxship;
import com.project.model.invoice.customer.InvoicetaxshipModel;

import org.apache.commons.beanutils.BeanUtilsBean;
/*
 * @author Gopal Ar
 * @version 1.0
 * @since 20 Sep 2013
 */

@Service("invoicetaxshipBO")
public class InvoicetaxshipBOImpl implements IInvoicetaxshipBO {
	
	public static Logger log = LoggerFactory.getLogger(InvoicetaxshipBOImpl.class);		
	
	@Resource(name = "invoicetaxshipRepository")
	private IInvoicetaxshipDAO invoicetaxshipRepository;
	
	@Transactional
	@Override
	public boolean createInvoicetaxship(InvoicetaxshipModel invoicetaxship)
			throws Exception {
		boolean saveSuccess = false;
		BeanUtilsBean beanUtils = new BeanUtilsBean();		
		try {			
			Invoicetaxship invoicetaxshipObj = new Invoicetaxship();
			beanUtils.copyProperties(invoicetaxshipObj, invoicetaxship);			
			saveSuccess = invoicetaxshipRepository.createInvoicetaxship(invoicetaxshipObj);			
		}
		catch (Exception e) {
			log.info("Error in createInvoicetaxship InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}

	@Transactional
	@Override
	public boolean updateInvoicetaxship(InvoicetaxshipModel invoicetaxship)
			throws Exception {
		boolean updateSuccess = false;
		BeanUtilsBean beanUtils = new BeanUtilsBean();		
		try {			
			Invoicetaxship invoicetaxshipObj = invoicetaxshipRepository.getInvoicetaxshipDetails(invoicetaxship.getInvoiceTaxShipId());
			beanUtils.copyProperties(invoicetaxshipObj, invoicetaxship);			
			updateSuccess = invoicetaxshipRepository.updateInvoicetaxship(invoicetaxshipObj);			
			}
		catch (Exception e) {
			log.info("Error in updateInvoicetaxship InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}

	@Transactional
	@Override
	public boolean deleteInvoicetaxship(InvoicetaxshipModel invoicetaxship)
			throws Exception {
		boolean deleteSuccess = false;
		BeanUtilsBean beanUtils = new BeanUtilsBean();		
		try {			
			Invoicetaxship invoicetaxshipObj = invoicetaxshipRepository.getInvoicetaxshipDetails(invoicetaxship.getInvoiceTaxShipId());
			beanUtils.copyProperties(invoicetaxshipObj, invoicetaxship);			
			deleteSuccess = invoicetaxshipRepository.deleteInvoicetaxship(invoicetaxshipObj);			
		}
		catch (Exception e) {
			log.info("Error in deleteInvoicetaxship InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}

	@Override
	public InvoicetaxshipModel getInvoicetaxshipDetails(Integer invoiceTaxShipId)
			throws Exception {
		BeanUtilsBean beanUtils = new BeanUtilsBean();	
		InvoicetaxshipModel invoicetaxship = new InvoicetaxshipModel();
		try {				
			Invoicetaxship invoicetaxshipObj = invoicetaxshipRepository.getInvoicetaxshipDetails(invoiceTaxShipId);
			beanUtils.copyProperties(invoicetaxship, invoicetaxshipObj);
			}
		catch (Exception e) {
			log.info("Error in getInvoicetaxshipDetails InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return invoicetaxship;
	}

	@Override
	public InvoicetaxshipModel getInvoicetaxshipDetails(String invoiceNo)
			throws Exception {
		BeanUtilsBean beanUtils = new BeanUtilsBean();	
		InvoicetaxshipModel invoicetaxship = new InvoicetaxshipModel();
		try {				
			Invoicetaxship invoicetaxshipObj = invoicetaxshipRepository.getInvoicetaxshipDetails(invoiceNo);
			beanUtils.copyProperties(invoicetaxship, invoicetaxshipObj);
		}
		catch (Exception e) {
			log.info("Error in getInvoicetaxshipDetails InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return invoicetaxship;
	}

	@Override
	public List<InvoicetaxshipModel> getInvoicetaxshipList(String invoiceNo)
			throws Exception {
		BeanUtilsBean beanUtils = new BeanUtilsBean();	
		List<InvoicetaxshipModel> invoicetaxship = new ArrayList<InvoicetaxshipModel>();
		List<Invoicetaxship> invoicetaxshipObj = new ArrayList<Invoicetaxship>();
		try {				
			invoicetaxshipObj = invoicetaxshipRepository.getInvoicetaxshipList(invoiceNo);					
			 if(invoicetaxshipObj!=null && invoicetaxshipObj.size()!=0)
			 {
				 InvoicetaxshipModel  data=new InvoicetaxshipModel();
				 beanUtils.copyProperties(data, invoicetaxshipObj.get(0));
				 invoicetaxship.add(data);
			 }
			
			 
		}
		catch (Exception e) {
			log.info("Error in getInvoicetaxshipDetails InvoicetaxshipBOImpl " + e);
			throw e;
		}
		return invoicetaxship;
	}

}
