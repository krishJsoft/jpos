package com.project.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IPoscounterBO;
import com.project.dao.interfaces.IPoscounterDAO;
import com.project.model.his.Branch;
import com.project.model.his.Poscashtransaction;
import com.project.model.his.Poscounter;
import com.project.model.his.Salesorderbreakdown;
import com.project.model.sale.sales.BranchSalesModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 23 Nov 2013
 * 
 */

@Service("poscounterBO")
public class PoscounterBOImpl implements IPoscounterBO {
	
	
	public static Logger log = LoggerFactory.getLogger(PoscounterBOImpl.class);

	@Resource(name = "poscounterRepository")
	private IPoscounterDAO poscounterRepository;
	
	
	@Transactional
	@Override
	public boolean createNewPoscounter(PoscounterModel poscounter)
			throws Exception {
		 boolean saveSuccess = false;
		 Poscounter poscounterObj = new Poscounter();
		 Branch branch = new Branch();
		try {			
			poscounterObj.setCounterNo(poscounter.getCounterNo());
			poscounterObj.setCurrentbalance(poscounter.getCurrentbalance());
			poscounterObj.setOpeningbalance(poscounter.getOpeningbalance());
			poscounterObj.setStatus(poscounter.getStatus());			
			branch.setBranchId(poscounter.getBranchId());			
			poscounterObj.setBranch(branch);
			
			saveSuccess = poscounterRepository.createNewPoscounter(poscounterObj);
		}
		catch (Exception e) {
			log.info("Error in createNewPoscounter PoscounterBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}

	@Transactional
	@Override
	public boolean updatePoscounter(PoscounterModel poscounter)
			throws Exception {
		 boolean updateSuccess = false;
		 Poscounter poscounterObj = poscounterRepository.getPoscounterDetails(poscounter.getCounterId());
		try {			
			poscounterObj.setCounterNo(poscounter.getCounterNo());
			poscounterObj.setCurrentbalance(poscounter.getCurrentbalance());
			poscounterObj.setOpeningbalance(poscounter.getOpeningbalance());
			poscounterObj.setStatus(poscounter.getStatus());
			
			updateSuccess = poscounterRepository.updatePoscounter(poscounterObj);
		}
		catch (Exception e) {
			log.info("Error in updatePoscounter PoscounterBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}

	@Transactional
	@Override
	public boolean deletePoscounter(PoscounterModel poscounter)
			throws Exception {
		 boolean updateSuccess = false;
		 Poscounter poscounterObj = poscounterRepository.getPoscounterDetails(poscounter.getCounterId());
		try {	
			updateSuccess = poscounterRepository.deletePoscounter(poscounterObj);
			}
		catch (Exception e) {
			log.info("Error in deletePoscounter PoscounterBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}

	@Override
	public List<PoscounterModel> findByPoscounterList(Integer branchId) throws Exception {
		List<PoscounterModel> poscounterObjList = new ArrayList<PoscounterModel>();	
		   List<Poscounter> poscounterList = new ArrayList<Poscounter>();
		try {			
			poscounterList = poscounterRepository.findByPoscounterList(branchId);
			
			for (Poscounter poscounter : poscounterList) {				
				PoscounterModel poscounterObj = new PoscounterModel();	
				 
				poscounterObj.setCounterNo(poscounter.getCounterNo());
				poscounterObj.setCurrentbalance(poscounter.getCurrentbalance());
				poscounterObj.setOpeningbalance(poscounter.getOpeningbalance());
				poscounterObj.setStatus(poscounter.getStatus());
				poscounterObj.setCounterId(poscounter.getCounterId());
			
				poscounterObjList.add(poscounterObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByPoscounterList PoscounterBOImpl " + e);
			throw e;
		}
		return poscounterObjList;
	}

	@Override
	public PoscounterModel getPoscounterDetails(Integer counterId)
			throws Exception {
		PoscounterModel poscounterObj = new PoscounterModel();	
		
		try {			
			Poscounter poscounter = poscounterRepository.getPoscounterDetails(counterId);
			poscounterObj.setCounterNo(poscounter.getCounterNo());
			poscounterObj.setCurrentbalance(poscounter.getCurrentbalance());
			poscounterObj.setOpeningbalance(poscounter.getOpeningbalance());
			poscounterObj.setStatus(poscounter.getStatus());
			poscounterObj.setCounterId(poscounter.getCounterId());
		}
		catch (Exception e) {
			log.info("Error in getPoscounterDetails PoscounterBOImpl " + e);
			throw e;
		}
		return poscounterObj;
	}

	@Override
	public boolean findPoscounterExites(String counterNo,Integer branchId) throws Exception {
		boolean exists = false;		
		try {
			exists = poscounterRepository.findPoscounterExites(counterNo,branchId);
		}
		catch (Exception e) {
			log.info("Error in findPoscounterExites PoscounterBOImpl " + e);
			throw e;
		}
		return exists;	
	}

	@Transactional
	@Override
	public boolean createNewPoscashtransaction(
			PoscashtransactionModel poscashtransaction) throws Exception {
		boolean saveSuccess = false;
		Poscashtransaction poscashtransactionObj = new Poscashtransaction();
		
		try {			
			poscashtransactionObj.setCreditamount(poscashtransaction.getCreditamount());
			poscashtransactionObj.setDebitAmount(poscashtransaction.getDebitAmount());
			poscashtransactionObj.setLastupdatedBy(poscashtransaction.getLastupdatedBy());
			poscashtransactionObj.setLastupdatedDate(poscashtransaction.getLastupdatedDate());			
			poscashtransactionObj.setRemarks(poscashtransaction.getRemarks());
			poscashtransactionObj.setType(poscashtransaction.getType());	
			poscashtransactionObj.setStatus(poscashtransaction.getStatus());
			poscashtransactionObj.setSalesOrderNo(poscashtransaction.getSalesOrderNo());
			poscashtransactionObj.setTransactionType(poscashtransaction.getTransactionType());
			poscashtransactionObj.setApprovedBy(poscashtransaction.getApprovedBy());
			poscashtransactionObj.setApprovedDate(poscashtransaction.getApprovedDate());
			poscashtransactionObj.setTransactionStatus(poscashtransaction.getTransactionStatus());
			poscashtransactionObj.setCollectedBy(poscashtransaction.getCollectedBy());
			poscashtransactionObj.setPaymentCount(1);
			poscashtransactionObj.setPaymentType(poscashtransaction.getPaymentType());
			poscashtransactionObj.setReceivedAmount(poscashtransaction.getReceivedAmount());
			poscashtransactionObj.setTotalTax(poscashtransaction.getTotalTax());
		
			Branch branchRecord = new Branch();
			branchRecord.setBranchId(poscashtransaction.getBranchRecordId());
			poscashtransactionObj.setBranch(branchRecord);
			poscashtransactionObj.setBranchtype(poscashtransaction.getBranchtype());			
			
			Poscounter counter = new Poscounter();
			counter.setCounterId(poscashtransaction.getCounterId());			
			poscashtransactionObj.setPoscounter(counter);
			
			saveSuccess = poscounterRepository.createNewPoscashtransaction(poscashtransactionObj);			
			
			
		}
		catch (Exception e) {
			log.info("Error in createNewPoscashtransaction PoscounterBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}

	
	
	@Transactional
	@Override
	public boolean updatePoscashtransaction(PoscashtransactionModel poscashtransactionModel)
			throws Exception {
		 boolean updateSuccess = false;
		 Poscashtransaction poscashtransactionObj = poscounterRepository.getPoscashtransactionDetails(poscashtransactionModel.getCashid());
		try {			
			poscashtransactionObj.setApprovedBy(poscashtransactionModel.getApprovedBy());
			poscashtransactionObj.setApprovedDate(poscashtransactionModel.getApprovedDate());
			poscashtransactionObj.setTransactionStatus(poscashtransactionModel.getTransactionStatus());			
			updateSuccess = poscounterRepository.updatePoscashtransaction(poscashtransactionObj);
		}
		catch (Exception e) {
			log.info("Error in updatePoscashtransaction PoscounterBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}
	
	@Transactional
	@Override
	public boolean approvePoscashtransaction(PoscashtransactionModel poscashtransactionModel)
			throws Exception {
		 boolean updateSuccess = false;
		 Poscashtransaction poscashtransactionObj = poscounterRepository.getPoscashtransactionDetails(poscashtransactionModel.getCashid());
		try {			
			poscashtransactionObj.setApprovedBy(poscashtransactionModel.getApprovedBy());
			poscashtransactionObj.setApprovedDate(poscashtransactionModel.getApprovedDate());
			poscashtransactionObj.setTransactionStatus(poscashtransactionModel.getTransactionStatus());			
			updateSuccess = poscounterRepository.updatePoscashtransaction(poscashtransactionObj);
			
			Poscounter poscounterObj = poscounterRepository.getPoscounterDetails(poscashtransactionModel.getCounterId());
			if(poscashtransactionModel.getType().equalsIgnoreCase("1"))
			{
			poscounterObj.setCurrentbalance(poscounterObj.getCurrentbalance().add(poscashtransactionModel.getCreditamount()));
			}
			else
			{
			poscounterObj.setCurrentbalance(poscounterObj.getCurrentbalance().subtract(poscashtransactionModel.getDebitAmount()));	
			}					
			updateSuccess=poscounterRepository.updatePoscounter(poscounterObj);
			
			
		}
		catch (Exception e) {
			log.info("Error in updatePoscashtransaction PoscounterBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}
	
	
	
	@Override
	public PoscashtransactionModel getPoscashtransactionDetails(Integer cashid)
			throws Exception {
		PoscashtransactionModel poscashtransactionObj = new PoscashtransactionModel();	
		
		try {			
			Poscashtransaction poscashtransaction = poscounterRepository.getPoscashtransactionDetails(cashid);
			poscashtransactionObj.setCashid(poscashtransaction.getCashid());
			poscashtransactionObj.setCounterId(poscashtransaction.getPoscounter().getCounterId());
			poscashtransactionObj.setCounterNo(poscashtransaction.getPoscounter().getCounterNo());
			poscashtransactionObj.setCreditamount(poscashtransaction.getCreditamount());
			poscashtransactionObj.setDebitAmount(poscashtransaction.getDebitAmount());
			poscashtransactionObj.setLastupdatedBy(poscashtransaction.getLastupdatedBy());
			poscashtransactionObj.setLastupdatedDate(poscashtransaction.getLastupdatedDate());
			poscashtransactionObj.setRemarks(poscashtransaction.getRemarks());
			poscashtransactionObj.setSalesOrderNo(poscashtransaction.getSalesOrderNo());
			poscashtransactionObj.setStatus(poscashtransaction.getStatus());
			poscashtransactionObj.setTransactionType(poscashtransaction.getTransactionType());
			poscashtransactionObj.setType(poscashtransaction.getType());
			poscashtransactionObj.setApprovedBy(poscashtransaction.getApprovedBy());
			poscashtransactionObj.setApprovedDate(poscashtransaction.getApprovedDate());
			poscashtransactionObj.setTransactionStatus(poscashtransaction.getTransactionStatus());
			poscashtransactionObj.setCollectedBy(poscashtransaction.getCollectedBy());
			poscashtransactionObj.setPaymentCount(poscashtransaction.getPaymentCount());
			poscashtransactionObj.setPaymentType(poscashtransaction.getPaymentType());
			poscashtransactionObj.setReceivedAmount(poscashtransaction.getReceivedAmount());
			poscashtransactionObj.setTotalTax(poscashtransaction.getTotalTax());
		}
		catch (Exception e) {
			log.info("Error in getPoscashtransactionDetails PoscounterBOImpl " + e);
			throw e;
		}
		return poscashtransactionObj;
	}
	
	
	
	@Override
	public List<PoscashtransactionModel> findByPoscashtransactionList(
			Integer counterId, String transactionType,String status,Date dateFrom, Date dateTo,String transactionStatus,Integer branchRecordId) throws Exception {
		    List<PoscashtransactionModel> poscashtransactionObjList = new ArrayList<PoscashtransactionModel>();	
		    List<Poscashtransaction> poscashtransactionList = new ArrayList<Poscashtransaction>();
		try {			
			poscashtransactionList = poscounterRepository.findByPoscashtransactionList(counterId, transactionType,status,dateFrom , dateTo, transactionStatus,branchRecordId);
			
			for (Poscashtransaction poscashtransaction : poscashtransactionList) {				
				PoscashtransactionModel poscashtransactionObj = new PoscashtransactionModel();	
				 
				poscashtransactionObj.setCashid(poscashtransaction.getCashid());
				poscashtransactionObj.setCounterId(poscashtransaction.getPoscounter().getCounterId());
				poscashtransactionObj.setCounterNo(poscashtransaction.getPoscounter().getCounterNo());
				poscashtransactionObj.setCreditamount(poscashtransaction.getCreditamount());
				poscashtransactionObj.setDebitAmount(poscashtransaction.getDebitAmount());
				poscashtransactionObj.setLastupdatedBy(poscashtransaction.getLastupdatedBy());
				poscashtransactionObj.setLastupdatedDate(poscashtransaction.getLastupdatedDate());
				poscashtransactionObj.setRemarks(poscashtransaction.getRemarks());
				poscashtransactionObj.setSalesOrderNo(poscashtransaction.getSalesOrderNo());
				poscashtransactionObj.setStatus(poscashtransaction.getStatus());
				poscashtransactionObj.setTransactionType(poscashtransaction.getTransactionType());
				poscashtransactionObj.setType(poscashtransaction.getType());	
				poscashtransactionObj.setApprovedBy(poscashtransaction.getApprovedBy());
				poscashtransactionObj.setApprovedDate(poscashtransaction.getApprovedDate());
				poscashtransactionObj.setTransactionStatus(poscashtransaction.getTransactionStatus());
				poscashtransactionObj.setCollectedBy(poscashtransaction.getCollectedBy());
				poscashtransactionObj.setPaymentCount(poscashtransaction.getPaymentCount());
				poscashtransactionObj.setPaymentType(poscashtransaction.getPaymentType());
				poscashtransactionObj.setReceivedAmount(poscashtransaction.getReceivedAmount());
				poscashtransactionObj.setTotalTax(poscashtransaction.getTotalTax());
				
				poscashtransactionObjList.add(poscashtransactionObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByPoscashtransactionList PoscounterBOImpl " + e);
			throw e;
		}
		return poscashtransactionObjList;
	}

	@Override
	public PoscashtransactionModel getPoscashtransactionCounter(
			Integer counterId, Date dateFrom, Date dateTo, String status,String transactionStatus,Integer branchRecordId)
			throws Exception {		
		PoscashtransactionModel data=new PoscashtransactionModel();		
		try
		{
		List<Object[]> tempdataList=poscounterRepository.getPoscashtransactionCounter(counterId, dateFrom, dateTo, status,transactionStatus,branchRecordId);			
		for(Object[] tempdata :tempdataList){					
			Poscashtransaction r = (Poscashtransaction) tempdata[0];			
		    data.setCreditamount((BigDecimal)tempdata[1]);
		    data.setDebitAmount((BigDecimal)tempdata[2]);
		    data.setReceivedAmount((BigDecimal)tempdata[3]);
		    data.setTotalTax((BigDecimal)tempdata[4]);
		 
		    if(data.getDebitAmount()==null)
		    {
		    	data.setDebitAmount(new BigDecimal(0.00));
		    }
		    if(data.getCreditamount()==null)
		    {
		    	data.setCreditamount(new BigDecimal(0.00));
		    }
		    
		    if(data.getReceivedAmount()==null)
		    {
		    	data.setReceivedAmount(new BigDecimal(0.00));
		    }
		    if(data.getTotalTax()==null)
		    {
		    	data.setTotalTax(new BigDecimal(0.00));
		    }
		    
		}			
	}
	catch(Exception e)
	{
		log.info("Error in getPoscashtransactionCounter of PoscounterBOImpl "+ e.toString());
		throw e;
	}
		return data;
	}
	
	@Transactional
	@Override
	public boolean updatePosCounterClose(String stringQuery , PoscounterModel poscounter) throws Exception
	{
		 boolean updateSuccess = false;
		 List<Poscashtransaction> poscashtransactionList = new ArrayList<Poscashtransaction>();

		 try {
		 
		  poscashtransactionList = poscounterRepository.findByPoscashtransactionList(poscounter.getCounterId(), null,"1",null , null, null,null);

		  for(Poscashtransaction data: poscashtransactionList)
		  {					
		    Poscashtransaction poscashtransactionObj = poscounterRepository.getPoscashtransactionDetails(data.getCashid());					
		    poscashtransactionObj.setStatus("0");
		   	updateSuccess = poscounterRepository.updatePoscashtransaction(poscashtransactionObj);
		  }
		  
		  Poscounter poscounterObj = poscounterRepository.getPoscounterDetails(poscounter.getCounterId());
		  poscounterObj.setCurrentbalance(new BigDecimal(0.00));
		  poscounterObj.setOpeningbalance(new BigDecimal(0.00));	
		  poscounterObj.setStatus("0"); // set inactive that ready to use
		  updateSuccess=poscounterRepository.updatePoscounter(poscounterObj);
		
		  
		  }
		catch (Exception e) {
			log.info("Error in updatePosCounterClose PoscounterBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	}

	@Override
	public List<PoscashtransactionModel> getposcounterReportDaily(Integer counterId, Date dateFrom,
			Date dateTo,String status,String transactionStatus,String transactionType, int branchId) throws Exception {
		List<PoscashtransactionModel> objList=new ArrayList<PoscashtransactionModel>();	
		
		try
		{
			int i=0;
		List<Object[]> tempdataList=poscounterRepository.getposcounterReportDaily(counterId, dateFrom, dateTo, status,transactionStatus,transactionType,branchId);			
			for(Object[] tempdata :tempdataList){					
				//Poscashtransaction data = (Poscashtransaction) tempdata[i++];	
				PoscashtransactionModel data=new PoscashtransactionModel();
			    data.setCreditamount((BigDecimal)tempdata[1]);
			    data.setDebitAmount((BigDecimal)tempdata[2]);
			    data.setReceivedAmount((BigDecimal)tempdata[3]);
			    data.setTotalTax((BigDecimal)tempdata[4]);
			
			    if(data.getDebitAmount()==null)
			    {
			    	data.setDebitAmount(new BigDecimal(0.00));
			    }
			    if(data.getCreditamount()==null)
			    {
			    	data.setCreditamount(new BigDecimal(0.00));
			    }
			    
			    if(data.getReceivedAmount()==null)
			    {
			    	data.setReceivedAmount(new BigDecimal(0.00));
			    }
			    if(data.getTotalTax()==null)
			    {
			    	data.setTotalTax(new BigDecimal(0.00));
			    }
			    
			    
			    objList.add(data);

			}
				
		}
		catch(Exception e)
		{
			log.info("Error in getposcounterReport of PoscounterBOImpl "+ e.toString());
			throw e;
	}
		return objList;
	}

	@Override
	public List<PoscashtransactionModel> getAnnualPosCounterReport(Integer counterId, Date dateFrom, Date dateTo,
			String status, String transactionStatus, String transactionType, Integer branchId) throws Exception {
		List<PoscashtransactionModel> objList=new ArrayList<PoscashtransactionModel>();	
		try
		{
			int i=0;
		List<Object[]> tempdataList=poscounterRepository.getAnnualPosCounterReport(counterId, dateFrom, dateTo, status,transactionStatus,transactionType,branchId);			
			for(Object[] tempdata :tempdataList){					
				//Poscashtransaction data = (Poscashtransaction) tempdata[i++];	
				PoscashtransactionModel data=new PoscashtransactionModel();
			    data.setCreditamount((BigDecimal)tempdata[1]);
			    data.setDebitAmount((BigDecimal)tempdata[2]);
			    data.setReceivedAmount((BigDecimal)tempdata[3]);
			    data.setTotalTax((BigDecimal)tempdata[4]);
			    data.setLastupdatedDate((Date) tempdata[5]);
			
			    if(data.getDebitAmount()==null)
			    {
			    	data.setDebitAmount(new BigDecimal(0.00));
			    }
			    if(data.getCreditamount()==null)
			    {
			    	data.setCreditamount(new BigDecimal(0.00));
			    }
			    
			    if(data.getReceivedAmount()==null)
			    {
			    	data.setReceivedAmount(new BigDecimal(0.00));
			    }
			    if(data.getTotalTax()==null)
			    {
			    	data.setTotalTax(new BigDecimal(0.00));
			    }
			    
			    
			    objList.add(data);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Error in getAnnualPosCounterReport of PoscounterBOImpl "+ e.toString());
			throw e;
		}
		return objList;
	}

}
