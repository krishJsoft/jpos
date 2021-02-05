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

import com.project.bo.interfaces.IExpensesBO;
import com.project.dao.interfaces.IExpensesDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.ExpensesList;
import com.project.model.his.ExpensesTransaction;
import com.project.model.his.Poscounter;
import com.project.model.sale.sales.ExpensesListModel;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.model.sale.sales.PoscounterModel;

@Service("expensesBO")
public class ExpensesBOImpl implements IExpensesBO {
	public static Logger log = LoggerFactory.getLogger(ExpensesBOImpl.class);

	@Resource(name="expensesRepository")
	private IExpensesDAO expensesRepository;
	
	
	@Override
	public boolean createNewExpTrans(ExpensesTransactionModel expTrans) throws Exception {
		Boolean saveSuccess=false;
		try {

			ExpensesTransaction obj=new ExpensesTransaction();
			
			obj.setAmmount(expTrans.getAmmount());
			obj.setCreatedDate(expTrans.getCreatedDate());
			obj.setSupplierResitNo(expTrans.getSupplierResistNo());
			obj.setSuppResitDate(expTrans.getSupplierResitDate());
			obj.setActiveStatus(1);
			
			Branchstaffmember branchStaffObj=new Branchstaffmember();
			branchStaffObj.setStaffId(expTrans.getStaffID());
			obj.setStaff(branchStaffObj);
			
			if(expTrans.getStaffCreditedTo().getStaffId()>0) {
				Branchstaffmember branchStaffCreditedToObj=new Branchstaffmember();
				branchStaffCreditedToObj.setStaffId(expTrans.getStaffCreditedTo().getStaffId());
				obj.setStaffCreditedTo(branchStaffCreditedToObj);
			}
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(expTrans.getBranchID());
			obj.setBranch(branchObj);
			
			ExpensesList expListObj=new ExpensesList();
			expListObj.setId(expTrans.getExpTransId());
			obj.setExpList(expListObj);
			
			Poscounter counterObj=new Poscounter();
			counterObj.setCounterId(expTrans.getPoscounter().getCounterId());
			obj.setPoscounter(counterObj);
			
			saveSuccess=expensesRepository.createNewExpTrans(obj);
		}catch(Exception ex) {
			log.info("Error in createNewEXpTrans ExpensesBOImpl "+ex);
			throw ex;
		}
		return saveSuccess;
	}

	@Override
	public boolean createNewExpName(ExpensesListModel exp) throws Exception {

		boolean saveSuccess=false;
		try {
			
			ExpensesList obj=new ExpensesList();
			
			obj.setExpensesName(exp.getExpName());
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(exp.getBranchId());
			obj.setBranch(branchObj);
			
			saveSuccess=expensesRepository.createNewExpName(obj);
			
		}catch(Exception ex) {
			log.info("Error in createNewEXpName ExpensesBOImpl "+ex);
			throw ex;
		}
		
		return saveSuccess;
	}

	@Override
	public List<ExpensesListModel> expensesListAll(int branchId) throws Exception {
		List<ExpensesListModel> objList=new ArrayList<ExpensesListModel>();
		try {
			
			for(ExpensesList data:expensesRepository.expensesListAll(branchId)) {
				ExpensesListModel obj=new ExpensesListModel();
				obj.setExpID(data.getId());
				obj.setExpName(data.getExpensesName());
				
				objList.add(obj);
			}
		}catch(Exception ex) {
			log.info("Error in expensesListAll ExpensesBOImpl "+ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public List<ExpensesTransactionModel> expensesTranAll(int branchId) throws Exception {
		List<ExpensesTransactionModel> objList=new ArrayList<ExpensesTransactionModel>();
		try {
			for(ExpensesTransaction data:expensesRepository.expensesTransactionAll(branchId)) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setExpTransId(data.getId());
				obj.setAmmount(data.getAmmount());
				obj.setCreatedDate(data.getCreatedDate());
				obj.setSupplierResistNo(data.getSupplierResitNo());
				obj.setSupplierResitDate(data.getSuppResitDate());
				obj.setActiveStatus(data.getActiveStatus());
				
				
				ExpensesListModel expLObj=new ExpensesListModel();
				expLObj.setExpID(data.getExpList().getId());
				expLObj.setExpName(data.getExpList().getExpensesName());
				obj.setExpList(expLObj);
				
				objList.add(obj);
				
			}
		}catch(Exception ex) {
			log.info("Error in expensesTranAll ExpensesBOImpl "+ex);
			throw ex;
		}
		return objList;
	}

	@Override
	public ExpensesTransactionModel expensesTrans(int expTransId) throws Exception {
		ExpensesTransactionModel obj=new ExpensesTransactionModel();
		try {
			ExpensesTransaction data=expensesRepository.expensesTrans(expTransId);
			if(data.getId()>0) {
				
				obj.setExpTransId(data.getId());
				obj.setCreatedDate(data.getCreatedDate());
				obj.setAmmount(data.getAmmount());
				obj.setSupplierResistNo(data.getSupplierResitNo());
				obj.setSupplierResitDate(data.getSuppResitDate());
				obj.setActiveStatus(data.getActiveStatus());
				obj.setCreatedDate(data.getCreatedDate());
				
				ExpensesListModel expLObj=new ExpensesListModel();
				expLObj.setExpID(data.getExpList().getId());
				expLObj.setExpName((data.getExpList().getExpensesName()));
				obj.setExpList(expLObj);
				
				BranchstaffmemberModel staffObj=new BranchstaffmemberModel();
				staffObj.setStaffId(data.getStaff().getStaffId());
				obj.setBranchStaff(staffObj);
				

				
				if(data.getStaffCreditedTo()!=null) {
					BranchstaffmemberModel staffCreditedToObj=new BranchstaffmemberModel();
					staffCreditedToObj.setStaffId(data.getStaffCreditedTo().getStaffId());
					staffCreditedToObj.setFirstName(data.getStaffCreditedTo().getFirstName());
					obj.setStaffCreditedTo(staffCreditedToObj);
				}
				else {
					BranchstaffmemberModel staffCreditedToObj=new BranchstaffmemberModel();
					staffCreditedToObj.setStaffId(0);
					obj.setStaffCreditedTo(staffCreditedToObj);
				}
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				PoscounterModel poscounter = new PoscounterModel();
				poscounter.setCounterId(data.getPoscounter().getCounterId());
				obj.setPoscounter(poscounter);
				
			}
		}catch(Exception ex){
			log.info("Error in expensesTrans ExpensesBOImpl "+ex);
			throw ex;
		}
		return obj;
	}

	@Override
	public boolean updateExpTrans(ExpensesTransactionModel expTrans) throws Exception {
		boolean updateSuccess=false;
		try {

			ExpensesTransaction obj=new ExpensesTransaction();//expensesRepository.expensesTrans(expTrans.getExpTransId());
			
			Branchstaffmember branchStaffObj=new Branchstaffmember();
			branchStaffObj.setStaffId(expTrans.getBranchStaff().getStaffId());
			obj.setStaff(branchStaffObj);
			
			if(expTrans.getStaffCreditedTo().getStaffId()>0) {
				Branchstaffmember branchStaffCreditedToObj=new Branchstaffmember();
				branchStaffCreditedToObj.setStaffId(expTrans.getStaffCreditedTo().getStaffId());
				obj.setStaffCreditedTo(branchStaffCreditedToObj);
			}
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(expTrans.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			ExpensesList expListObj=new ExpensesList();
			expListObj.setId(expTrans.getExpList().getExpID());
			obj.setExpList(expListObj);
			
			Poscounter counterObj=new Poscounter();
			counterObj.setCounterId(expTrans.getPoscounter().getCounterId());
			obj.setPoscounter(counterObj);
			
			obj.setId(expTrans.getExpTransId());
			obj.setAmmount(expTrans.getAmmount());
			obj.setCreatedDate(expTrans.getCreatedDate());
			obj.setSupplierResitNo(expTrans.getSupplierResistNo());
			obj.setSuppResitDate(expTrans.getSupplierResitDate());
			obj.setActiveStatus(expTrans.getActiveStatus());
		
			updateSuccess=expensesRepository.updateExpTrans(obj);
			
		}catch(Exception ex){
			log.info("Error in updateExpensesTrans ExpensesBOImpl "+ex);
			throw ex;
		}
		
		return updateSuccess;
	}

	@Override
	public List<ExpensesTransactionModel> getAnnualExpenses(Date firstDayYear, Date endDayYear, int branchId) throws Exception {
		try {
			List<ExpensesTransactionModel>  expL=new ArrayList<ExpensesTransactionModel>();
			List<Object[]> expLData=expensesRepository.getAnnualExpenses(firstDayYear,endDayYear,branchId);
		
			for(Object[] data:expLData) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setAmmount((BigDecimal) data[1]);
				obj.setCreatedDate((Date) data[2]);
				expL.add(obj);
			}
			
				
			
			return expL;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getAnnualExpenses ExpensesBOImpl "+ex);
			throw ex;
		}
	}

	@Override
	public List<ExpensesTransactionModel> getMonthlyExpensesDetail(Date startDate, Date endDate,Integer expensesId,Integer staffId, int branchId) throws Exception {
		try {
			List<ExpensesTransactionModel>  objList=new ArrayList<ExpensesTransactionModel>();
			List<Object[]> expLData=expensesRepository.getMonthlyExpensesDetail(startDate, endDate,expensesId,staffId,branchId);
			
			for(Object[] data:expLData) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setAmmount((BigDecimal) data[1]);
				
				ExpensesListModel expList=new ExpensesListModel();
				expList.setExpID((int) data[3]);
				expList.setExpName((String) data[4]);
				if(data[5]!=null) {
					BranchstaffmemberModel staffCreditedToObj=new BranchstaffmemberModel();
					staffCreditedToObj.setFirstName((String) data[5]);
					obj.setStaffCreditedTo(staffCreditedToObj);
				}
				
				obj.setExpList(expList);
				
				objList.add(obj);
			}
			
			return objList;
		}catch(Exception ex) {
			log.info("Error in getMonthlyExpensesDetail ExpensesBOImpl "+ex);
			throw ex;
		}
		
	}

	@Override
	public boolean nameIsExist(int branchId,String expName) throws Exception {
		boolean exist=true;
		try {
			
			exist=expensesRepository.nameIsExist(branchId,expName);
		}catch(Exception ex) {
			log.info("Error in nameIsExist ExpensesBOImpl "+ex );
			throw ex;
		}
		return exist;
	}

	@Override
	public List<ExpensesTransactionModel> getExpensesTransactionList(int counterId,Date dateFrom, Date dateTo, int branchId)
			throws Exception {
		try {
			List<ExpensesTransactionModel>  objList=new ArrayList<ExpensesTransactionModel>();
			List<ExpensesTransaction> expLData=expensesRepository.getExpensesTransactionList(counterId,dateFrom, dateTo, branchId);
			for(ExpensesTransaction data:expLData) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setAmmount(data.getAmmount());
				
				ExpensesListModel expList=new ExpensesListModel();
				expList.setExpID(data.getExpList().getId());
				expList.setExpName(data.getExpList().getExpensesName());
				
				obj.setExpList(expList);
				
				objList.add(obj);
			}

			return objList;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getExpensesTransactionList ExpensesBOImpl "+ex);
			throw ex;
		}
	}

	
	@Override
	public List<ExpensesTransactionModel> getDailyExpensesDetail(Date startDate, Date endDate, Integer expensesId,Integer staffId,int branchId) throws Exception {
		try {
			List<ExpensesTransactionModel>  objList=new ArrayList<ExpensesTransactionModel>();
			List<Object[]> expLData=expensesRepository.geDailyExpensesDetail(startDate, endDate, expensesId,staffId,branchId);
			
			for(Object[] data:expLData) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setAmmount((BigDecimal) data[1]);
				obj.setCreatedDate((Date) data[2]);
				ExpensesListModel expList=new ExpensesListModel();
				expList.setExpID((int) data[3]);
				expList.setExpName((String) data[4]);
				
				if(data[5]!=null) {
					BranchstaffmemberModel staffCreditedToObj=new BranchstaffmemberModel();
					staffCreditedToObj.setFirstName((String) data[5]);
					obj.setStaffCreditedTo(staffCreditedToObj);
				}
				
				
				
				obj.setExpList(expList);
				
				objList.add(obj);
			}
			
			return objList;
		}catch(Exception ex) {
			log.info("Error in getDailyExpensesDetail ExpensesBOImpl "+ex);
			throw ex;
		}
		
	}

	@Override
	public List<ExpensesTransactionModel> loadExpensesByPosTerminal(int counterId, int branchId) throws Exception {
		try {
			List<ExpensesTransactionModel>  objList=new ArrayList<ExpensesTransactionModel>();
			List<ExpensesTransaction> expLData=expensesRepository.loadExpensesByPosTerminal(counterId, branchId);
			for(ExpensesTransaction data:expLData) {
				ExpensesTransactionModel obj=new ExpensesTransactionModel();
				obj.setAmmount(data.getAmmount());
				
				obj.setExpTransId(data.getId());
				ExpensesListModel expList=new ExpensesListModel();
				expList.setExpID(data.getExpList().getId());
				expList.setExpName(data.getExpList().getExpensesName());
				
				obj.setExpList(expList);
				
				objList.add(obj);
			}

			return objList;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getExpensesTransactionList ExpensesBOImpl "+ex);
			throw ex;
		}
	}

	
}
