package com.project.bo.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.sale.sales.ExpensesListModel;
import com.project.model.sale.sales.ExpensesTransactionModel;

public interface IExpensesBO {

	public boolean createNewExpTrans(ExpensesTransactionModel expTrans) throws Exception;
	
	public boolean createNewExpName(ExpensesListModel exp)throws Exception;
	
	public List<ExpensesListModel> expensesListAll(int branchId) throws Exception;
	
	public List<ExpensesTransactionModel> expensesTranAll(int branchId) throws Exception;
	
	public ExpensesTransactionModel expensesTrans(int expTransId) throws Exception;

	public boolean updateExpTrans(ExpensesTransactionModel expTrans) throws Exception;

	public List<ExpensesTransactionModel> getAnnualExpenses(Date firstDayYear, Date endDayYear, int branchId) throws Exception;

	public List<ExpensesTransactionModel> getMonthlyExpensesDetail(Date startDate, Date endDate, Integer expensesId, Integer staffId, int branchId) throws Exception;

	public boolean nameIsExist(int branchId,String expName) throws Exception;

	public List<ExpensesTransactionModel> getExpensesTransactionList(int counterId, Date dateFrom, Date dateTo, int branchId) throws Exception;

	List<ExpensesTransactionModel> getDailyExpensesDetail(Date startDate, Date endDate, Integer expensesId, Integer staffId, int branchId) throws Exception;

	public List<ExpensesTransactionModel> loadExpensesByPosTerminal(int counterId, int branchId) throws Exception;


	
}
