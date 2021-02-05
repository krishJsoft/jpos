package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Poscashtransaction;
import com.project.model.his.Poscounter;
import com.project.model.sale.sales.PoscashtransactionModel;

public interface IPoscounterDAO {	
	
	public boolean createNewPoscounter(Poscounter poscounter) throws Exception;

	public boolean updatePoscounter(Poscounter poscounter) throws Exception;

	public boolean deletePoscounter(Poscounter poscounter) throws Exception;
	
	public List<Poscounter> findByPoscounterList(Integer branchId) throws Exception;
		
	public Poscounter getPoscounterDetails(Integer counterId) throws Exception;
	
    public boolean findPoscounterExites(String counterNo,Integer branchId) throws Exception;
    
    public boolean createNewPoscashtransaction(Poscashtransaction poscashtransaction) throws Exception;
    
    public List<Poscashtransaction> findByPoscashtransactionList(Integer counterId , String transactionType,String status,Date dateFrom, Date dateTo,String transactionStatus,Integer branchRecordId) throws Exception;
    
    public List<Object[]> getPoscashtransactionCounter(Integer counterId ,Date dateFrom, Date dateTo,String status,String transactionStatus,Integer branchRecordId) throws Exception;

    public Poscashtransaction getPoscashtransactionDetails(Integer cashid) throws Exception;
    
    public boolean updatePoscashtransaction(Poscashtransaction poscashtransaction) throws Exception;
    
    public boolean updatePosCounterClose(String stringQuery) throws Exception;

	List<Object[]> getposcounterReportDaily(Integer counterId, Date dateFrom, Date dateTo, String status,
			String transactionStatus, String transactionType, Integer branchId) throws Exception;

	public List<Object[]> getAnnualPosCounterReport(Integer counterId, Date dateFrom, Date dateTo, String status,
			String transactionStatus, String transactionType, Integer branchId) throws Exception;
}
