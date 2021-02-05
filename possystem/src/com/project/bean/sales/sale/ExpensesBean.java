package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.bean.admin.CommonListBeanInfo;
import com.project.bo.interfaces.IExpensesBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.home.ProjectHome;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.his.ExpensesList;
import com.project.model.sale.sales.ExpensesListModel;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.util.DateUtil;

@ManagedBean(name = "expensesBean")
@SessionScoped
public class ExpensesBean {

	ExpensesTransactionModel expTrans=new ExpensesTransactionModel();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IExpensesBO expensesBO=objectMapController.getExpensesBO();
	List<ExpensesTransactionModel> expTransList=new ArrayList<ExpensesTransactionModel>();
	List<ExpensesTransactionModel> expTransReport=new ArrayList<ExpensesTransactionModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");

	private String expName;
	
	private Integer expTransIdUpdate;
	
	private String action="submit";
	private Date dateFrom;
	private Date dateTo;
	private Date dateReportMonthDate ;
	private Integer reportStaffId;
	private Integer reportExpensesId;
	
	private String newExpName="no";
	 BigDecimal expensesTotalAmount =new BigDecimal(0.00);
	 
	public ExpensesTransactionModel getExpTrans() {
		return expTrans;
	}
	public List<ExpensesTransactionModel> getExpTransList() {
		return expTransList;
	}
	public List<ExpensesTransactionModel> getExpTransReport() {
		return expTransReport;
	}
	public void setExpTransReport(List<ExpensesTransactionModel> expTransReport) {
		this.expTransReport = expTransReport;
	}
	public void setExpTrans(ExpensesTransactionModel expTrans) {
		this.expTrans = expTrans;
	}
	public void setExpTransList(List<ExpensesTransactionModel> expTransList) {
		this.expTransList = expTransList;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	
	public Integer getExpTransIdUpdate() {
		return expTransIdUpdate;
	}
	public void setExpTransIdUpdate(Integer expTransIdUpdate) {
		this.expTransIdUpdate = expTransIdUpdate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Date getDateReportMonthDate() {
		return dateReportMonthDate;
	}
	public void setDateReportMonthDate(Date dateReportMonthDate) {
		this.dateReportMonthDate = dateReportMonthDate;
	}
	public Integer getReportStaffId() {
		return reportStaffId;
	}
	public void setReportStaffId(Integer reportStaffId) {
		this.reportStaffId = reportStaffId;
	}
	public Integer getReportExpensesId() {
		return reportExpensesId;
	}
	public void setReportExpensesId(Integer reportExpensesId) {
		this.reportExpensesId = reportExpensesId;
	}
	public String getNewExpName() {
		return newExpName;
	}
	public void setNewExpName(String newExpName) {
		this.newExpName = newExpName;
	}
	public BigDecimal getExpensesTotalAmount() {
		return expensesTotalAmount;
	}
	public void setExpensesTotalAmount(BigDecimal expensesTotalAmount) {
		this.expensesTotalAmount = expensesTotalAmount;
	}
	
	public void createNewExpTrans() {
		try {
			if(expensesValidation()) {
				//poscounter.getCounterId();
				expTrans.setCreatedDate(new Date());
				expTrans.setBranchID(loginBean.getBranch().getBranchId());
				expTrans.setStaffID(loginBean.getUserId());
				PoscounterModel poscounter = new PoscounterModel();
				PosBean posBean = (PosBean) BeanContext.getReference("posBean");
				poscounter=posBean.getPoscounter();
				expTrans.setPoscounter(poscounter);
				boolean saveSuccess=expensesBO.createNewExpTrans(expTrans);
				if(saveSuccess) {
					reset();
					loadExpensesTranAll();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success !", "Expenses successfully created"));
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateExpTrans() {
		try {
			
			expTrans.setExpTransId(this.getExpTransIdUpdate());
			boolean saveSuccess=expensesBO.updateExpTrans(expTrans);
			
			if(saveSuccess) {
				reset();
				loadExpensesTranAll();
				this.setAction("submit");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createNewExpName() {
		try {
			if(expNameValidation()) {
				ExpensesListModel expObj=new ExpensesListModel();
				expObj.setExpName(expName);
				expObj.setBranchId(loginBean.getBranch().getBranchId());
				boolean saveSuccess=expensesBO.createNewExpName(expObj);
				if(saveSuccess) {
					this.setExpName(null);
					CommonListBeanInfo.getExpensesList();
					this.setNewExpName("no");
					
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void loadExpensesByPosTerminal() {
		reset();
		try {
			PoscounterModel poscounter = new PoscounterModel();
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			poscounter=posBean.getPoscounter();
			List<ExpensesTransactionModel> objList=expensesBO.loadExpensesByPosTerminal(poscounter.getCounterId(),loginBean.getBranch().getBranchId());
			
			this.setExpTransList(objList);	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadExpensesTranAll() {
		
		try {
			
			List<ExpensesTransactionModel> objList=expensesBO.expensesTranAll(loginBean.getBranch().getBranchId());
			
			this.setExpTransList(objList);	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void editExpTrans(ActionEvent event) {
	
		try {
			String expTransId="";
			expTransId=(String)event.getComponent().getAttributes().get("expensesID").toString();
			this.setExpTransIdUpdate(Integer.parseInt(expTransId));
			ExpensesTransactionModel obj=expensesBO.expensesTrans(Integer.parseInt(expTransId));
			this.setExpTrans(obj);
			this.setAction("update");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void changeNewExpStatus(ActionEvent event) {
		newExpName = (String)event.getComponent().getAttributes().get("expAction");
	}
	
	public void reset() {
		expTrans.setAmmount(null);
		expTrans.setSupplierResistNo(null);
		expTrans.setSupplierResitDate(null);
		expTrans.getStaffCreditedTo().setStaffId(0);
		this.setExpName(null);
		this.setNewExpName("no");
	}
	
	public void cancelEdit() {
		this.setAction("submit");
		reset();
	}

	public void fetchMonthlyExpensesReport() throws Exception {
		BigDecimal total=new BigDecimal(0.00);

		expTransReport=new ArrayList<ExpensesTransactionModel>();
		this.setDateReportMonthDate(this.getDateTo());
		//List<ExpensesTransactionModel> obj=expensesBO.getMonthlyExpensesDetail(startDate, endDate, loginBean.getBranch().getBranchId());
		for(ExpensesTransactionModel data:expensesBO.getMonthlyExpensesDetail(this.dateFrom, this.dateTo, this.reportExpensesId,this.reportStaffId,loginBean.getBranch().getBranchId())){
			ExpensesTransactionModel obj=new ExpensesTransactionModel();
			obj.setAmmount(data.getAmmount());
			total=total.add(data.getAmmount());
			obj.setCreatedDate(data.getCreatedDate());
			
			ExpensesListModel expList=new ExpensesListModel();
			expList.setExpID(data.getExpList().getExpID());
			expList.setExpName(data.getExpList().getExpName());
			
			obj.setExpList(expList);
			
			expTransReport.add(obj);
			
		}
		expensesTotalAmount=total;

	}
	
	public void annualExpensesReport(ActionEvent event )throws Exception  {
		try {
			FacesContext context = FacesContext.getCurrentInstance();

			Date startDate = (Date) event.getComponent().getAttributes().get("startDate");
			Date endDate = (Date) event.getComponent().getAttributes().get("endDate");
			this.setDateFrom(startDate);
			this.setDateTo(endDate);
			fetchMonthlyExpensesReport();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();						
			projectHome.setTitlePage("Expenses --> Expenses Details ");
			projectHome.setContentpage("/reports/expensesReport/expensesReportDetails.xhtml");					
			homeinfo.menuPageRedirect();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean expNameValidation() {
		String messageValue=null;
		boolean valid=true;
		try {
			
			
			if(factoryBean.checkIsNullAssignMessage(this.getExpName(), "expenses.label.expNewName", "expNewName")) {
				valid=false;
				
			}else {
				if(expensesBO.nameIsExist(loginBean.getBranch().getBranchId(),this.getExpName())) {
					messageValue=factoryBean.getErrorFactory().getError("expenses.errors.expName.exists");
					factoryBean.reportErrorToMessageHandler(messageValue, messageValue, "expNewName");
					valid=false;
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return valid;
	}
	
	public boolean expensesValidation() {
		boolean valid=true;
		try {
			
			
			if(factoryBean.checkIsNullAssignMessage(this.expTrans.getAmmount(), "expenses.label.expensesAmount", "expenseAmount") || this.expTrans.getAmmount().compareTo(BigDecimal.ZERO) < 0) {
				valid=false;
				
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return valid;
	}
	
	public void searchExpensesDateBased() {
		
		try {
	        Calendar calendar = Calendar.getInstance();

			if(dateFrom==null) {
				this.setDateFrom(DateUtil.getFromTodayDateTime());
				
			}
			if(dateTo==null) {
				this.setDateTo(DateUtil.getToTodayDateTime());
			}else {
				calendar.setTime(dateTo);
		        calendar.set(Calendar.MILLISECOND, 0);
		        calendar.set(Calendar.SECOND, 59);
		        calendar.set(Calendar.MINUTE, 59);
		        calendar.set(Calendar.HOUR, 23);	  
		        dateTo=calendar.getTime();
			}
			BigDecimal total=new BigDecimal(0.00);
			expTransReport=expensesBO.getDailyExpensesDetail(this.getDateFrom(), this.getDateTo(),this.getReportExpensesId(),this.getReportStaffId(), loginBean.getBranch().getBranchId());
			for(ExpensesTransactionModel data:expTransReport){
				total=total.add(data.getAmmount());
				
			}
			expensesTotalAmount=total;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
