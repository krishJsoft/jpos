package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.ExpensesList;
import com.project.model.his.ExpensesTransaction;
import com.project.model.sale.sales.ExpensesTransactionModel;

public interface IExpensesDAO {

	public boolean createNewExpTrans(ExpensesTransaction expTran) throws Exception;
	
	public boolean createNewExpName(ExpensesList exp) throws Exception;
	
	public List<ExpensesList> expensesListAll(int branchId) throws Exception;
	
	public List<ExpensesTransaction> expensesTransactionAll(int branchId) throws Exception;
	
	public ExpensesTransaction expensesTrans(int expTransId) throws Exception;

	public boolean updateExpTrans(ExpensesTransaction expTrans) throws Exception;

	public List<Object[]> getAnnualExpenses(Date firstDayYear, Date endDayYear, int branchId) throws Exception;

	public List<Object[]> getMonthlyExpensesDetail(Date startDate, Date endDate,Integer expensesId, Integer staffId, int branchId) throws Exception;

	public boolean nameIsExist(int branchId,String expName) throws Exception;

	public List<ExpensesTransaction> getExpensesTransactionList(int counterId, Date dateFrom, Date dateTo, int branchId) throws Exception;

	List<Object[]> geDailyExpensesDetail(Date startDate, Date endDate, Integer expensesId, Integer staffId, int branchId) throws Exception;

	public List<ExpensesTransaction> loadExpensesByPosTerminal(int counterId, int branchId) throws Exception;

}
