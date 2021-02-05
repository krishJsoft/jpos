package com.project.bo.interfaces;


import java.util.Date;
import java.util.List;

import com.project.model.his.Poscashtransaction;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;

public interface IPoscounterBO {
	
	public boolean createNewPoscounter(PoscounterModel poscounter) throws Exception;

	public boolean updatePoscounter(PoscounterModel poscounter) throws Exception;

	public boolean deletePoscounter(PoscounterModel poscounter) throws Exception;
	
	public List<PoscounterModel> findByPoscounterList(Integer branchId) throws Exception;
		
	public PoscounterModel getPoscounterDetails(Integer counterId) throws Exception;
	
    public boolean findPoscounterExites(String counterNo,Integer branchId) throws Exception;
    
    public boolean createNewPoscashtransaction(PoscashtransactionModel poscashtransaction) throws Exception;
    
    public List<PoscashtransactionModel> findByPoscashtransactionList(Integer counterId , String transactionType,String status,Date dateFrom, Date dateTo,String transactionStatus,Integer branchRecordId) throws Exception;
    
    public PoscashtransactionModel getPoscashtransactionCounter(Integer counterId ,Date dateFrom, Date dateTo,String status,String transactionStatus,Integer branchRecordId) throws Exception;

    public PoscashtransactionModel getPoscashtransactionDetails(Integer cashid) throws Exception;
    
    public boolean updatePoscashtransaction(PoscashtransactionModel poscashtransaction) throws Exception;
    
    public boolean approvePoscashtransaction(PoscashtransactionModel poscashtransactionModel) throws Exception;
    
    public boolean updatePosCounterClose(String stringQuery, PoscounterModel poscounter) throws Exception;

	List<PoscashtransactionModel> getposcounterReportDaily(Integer counterId, Date dateFrom, Date dateTo, String status,
			String transactionStatus, String transactionType, int branchId) throws Exception;

	public List<PoscashtransactionModel> getAnnualPosCounterReport(Integer posCounter, Date dateFrom, Date dateTo, String status,
			String transactionStatus, String transactionType, Integer branchId) throws Exception;


	
}
