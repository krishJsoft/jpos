package com.project.home;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jfree.util.Log;
import org.primefaces.context.RequestContext;

import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.util.DateUtil;
import com.project.bo.interfaces.IBranchBO;
import com.project.bean.admin.BranchBean;
import com.project.bean.admin.BranchStaffBean;
import com.project.bean.admin.DepartmentBean;
import com.project.bean.admin.DiscountBean;
import com.project.bean.admin.HoteltableBean;
import com.project.bean.admin.ItemRemarksBean;
import com.project.bean.admin.PoscounterBean;
import com.project.bean.admin.ProductBean;
import com.project.bean.admin.ProductCategoryBean;
import com.project.bean.admin.RoleBean;
import com.project.bean.admin.ShiftBean;
import com.project.bean.admin.SystemAutonumBean;
import com.project.bean.admin.TaxBean;
import com.project.bean.membership.MembershipPaymentBean;
import com.project.bean.onlineorder.OnlineOrderBean;
import com.project.bean.sales.sale.ExpensesBean;
import com.project.bean.sales.sale.OrderBean;
import com.project.bean.sales.sale.PosBean;
import com.project.bean.sales.sale.PosOptionBean;
import com.project.bean.sales.sale.SalesSummaryBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;



@ManagedBean(name = "projectHome")
@SessionScoped
// Author Gopal Ar
// 2013-05
public class ProjectHome implements Serializable {

	private static final long serialVersionUID = 8526508697197341007L;

	private Long id;
	private  List<String> pageTracker=new ArrayList<String>();
	
	private String prevPage;
	private String homePage;
	private String contenttpage;
	private String posKitchenOrderPage;
	private String terminalOrderContentPage;
	private String headerPage;
	private String menuPage;
	private String userid;
	private String titlePage;
	private String initPage;
	private String reportNum;
	private int reportsMenu=0;

	public Integer getReportsMenu() {
		return reportsMenu;
	}

	public void setReportsMenu(Integer reportsMenu) {
		this.reportsMenu = reportsMenu;
	}

	Configuration config = Configuration.getConfiguration();
	String projectHomeFile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);

	List moduleList = new ArrayList();
	List functionList = new ArrayList();

	String user_categery = "";
	FacesContext context = FacesContext.getCurrentInstance();
	String action = "login";

	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	public Long getId() {
		return id;
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenttpage() {
		return contenttpage;
	}

	public void setContentpage(String contenttpage) {
		this.contenttpage = contenttpage;
	}


	public String getPosKitchenOrderPage() {
		return posKitchenOrderPage;
	}

	public void setPosKitchenOrderPage(String posKitchenOrderPage) {
		this.posKitchenOrderPage = posKitchenOrderPage;
	}

	public String getTerminalOrderContentPage() {
		return terminalOrderContentPage;
	}

	public void setTerminalOrderContentPage(String terminalOrderContentPage) {
		this.terminalOrderContentPage = terminalOrderContentPage;
	}

	public String getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
		
	}

	public List<String> getPageTracker() {
		return pageTracker;
	}

	public void setPageTracker(List<String> pageTracker) {
		this.pageTracker = pageTracker;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getHeaderPage() {
		return headerPage;
	}

	public void setHeaderPage(String headerPage) {
		this.headerPage = headerPage;
	}

	public String getMenuPage() {
		return menuPage;
	}

	public void setMenuPage(String menuPage) {
		this.menuPage = menuPage;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitlePage() {
		return titlePage;
	}

	public void setTitlePage(String titlePage) {

		this.titlePage = titlePage;
	}

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getreportNum() {
		return reportNum;
	}

	public void setreportNum(String reportNum) {
		this.reportNum = reportNum;
	}

	
	public String menuDepartment() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();
			
			action = projectHomeFile;
			DepartmentBean departmentBean = (DepartmentBean) BeanContext.getReference("departmentBean");
			departmentBean.getDepartmentList();
			projectHome.setTitlePage("Admin --> Departments ");
			projectHome.setContentpage("/admin/department.xhtml");
			action = projectHomeFile;					
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menudesignation() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Admin --> New Designation");
			projectHome.setContentpage("/admin/designation.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuRole() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Admin --> Roles --> Search Role");
			projectHome.setContentpage("/admin/Roleslist.xhtml");
			RoleBean roleBean = (RoleBean) BeanContext.getReference("roleBean");
			roleBean.initValues();
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
			
	public String menuStaff() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Admin --> Staffs");
			projectHome.setContentpage("/admin/branchStaff.xhtml");
			BranchStaffBean branchStaffBean = (BranchStaffBean) BeanContext.getReference("branchStaffBean");			
			branchStaffBean.searchBranchStaff();
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
			
	
	public String menuCustomer() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Admin --> Customers --> Search Customer");
			projectHome.setContentpage("/admin/customer.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuCategory() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			//ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			//productCategoryBean.getDefaultRootNode();
			projectHome.setTitlePage("Admin --> Product Category");
			projectHome.setContentpage("/admin/productCategory.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	
	
	public String menuProduct() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Admin --> Products -->Search Product");
			projectHome.setContentpage("/admin/products.xhtml");
			ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");			
			productBean.searchProduct1();
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	public String menuTax() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			TaxBean taxBean = (TaxBean) BeanContext.getReference("taxBean");
			taxBean.getBranchTaxList();
			projectHome.setTitlePage("Admin --> Tax Code");
			projectHome.setContentpage("/admin/taxmaster.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuTerminal() {
		this.setReportsMenu(0);

		try {
			
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("System Settings --> Terminal Setup");
			projectHome.setContentpage("/admin/terminals.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			poscounterBean.getPoscounterList();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	public String menupos() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();	
			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
			PosBean posBean = (PosBean) BeanContext.getReference("posBean");
			ProductBean productBean = (ProductBean) BeanContext.getReference("productBean");
			projectHome.setTitlePage("Sales --> POS");
			projectHome.setContentpage("/sales/pos/posCounterSelect.xhtml");
			poscounterBean.getPoscounterList();
			
			action = projectHomeFile;	
			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	public String menuorder() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();	
			PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			projectHome.setTitlePage("Sales --> New Order");
			projectHome.setContentpage("/sales/addEditPosres.xhtml");
			action = projectHomeFile;	
			poscounterBean.getPoscounterList();			
			productCategoryBean.loadCategory();		
			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	public String menucashcollection() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");		
			PosOptionBean posOptionBean = (PosOptionBean) BeanContext.getReference("posOptionBean");		
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> POS Cash Collection");
			projectHome.setContentpage("/sales/pos/posCashTransaction.xhtml");	
			action = projectHomeFile;		
			
			posOptionBean.setTransactionStatus("0");
			Date dateNow = new Date ();
			SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
		    posOptionBean.setDateFrom(DateUtil.getFromTodayDateTime());
		    posOptionBean.setDateTo(DateUtil.getToTodayDateTime());	
			posOptionBean.getPoscashtransactionSupList();
			
			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	public String menusalesOrder() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Sales Order List");
			projectHome.setContentpage("/sales/salesOrder.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	public String menusalesOrderSummary() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Sales Order Summary");
			projectHome.setContentpage("/sales/salesOrdersummary.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuReport() {
		this.setReportsMenu(1);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Annaul Sales Summary");
			projectHome.setContentpage("/reports/mainMenu.xhtml");
			action = projectHomeFile;
			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuannualsalesSummary() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Annaul Sales Summary");
			projectHome.setContentpage("/sales/annualsummary.xhtml");
			
			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getSalesRecord();
			
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menuSalesDailyReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Report --> Sales Report --> Daliy");
			projectHome.setContentpage("/reports/salesReport/dailyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuMonthlySummaryReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Report --> Sales Report --> Monthly-Summary");
			projectHome.setContentpage("/reports/salesReport/monthlySummaryReport.xhtml");

			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getSalesSummaryRecord();
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuDailySummaryReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Report --> Sales Report --> Daliy-Summary");
			LoginBean loginBean=(LoginBean) BeanContext.getReference("loginBean");

			if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
			{
				projectHome.setContentpage("/reports/salesReport/admindailySummaryReport.xhtml");
			}
			else
			{
				projectHome.setContentpage("/reports/salesReport/dailysummaryReport.xhtml");
			}
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuDailySalesItemReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Report --> Sales Item Report --> Daliy");
			projectHome.setContentpage("/reports/salesItemReport/dailyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	
	public String menuDailySalesPaymentReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Report --> Sales Payment Report --> Daliy");
			projectHome.setContentpage("/reports/salesReport/dailyReportbyPayment.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	
	
	public String menuDailyExpensesReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			ExpensesBean expensesBean=(ExpensesBean) BeanContext.getReference("expensesBean");
			expensesBean.setExpTransReport(null);
			expensesBean.setDateFrom(null);
			expensesBean.setDateTo(null);
			expensesBean.setExpensesTotalAmount(new BigDecimal("0"));
			expensesBean.setReportExpensesId(0);
			expensesBean.setReportStaffId(0);

			projectHome.setTitlePage("Report --> Expenses Report --> Daliy");
			projectHome.setContentpage("/reports/expensesReport/dailyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String monthlyCounterReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			ExpensesBean expensesBean=(ExpensesBean) BeanContext.getReference("expensesBean");
			expensesBean.setExpTransReport(null);
			expensesBean.setDateFrom(null);
			expensesBean.setDateTo(null);

			projectHome.setTitlePage("Report --> Counter Report --> Monthly");
			projectHome.setContentpage("/reports/counterReport/monthlyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String dailyCounterReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			ExpensesBean expensesBean=(ExpensesBean) BeanContext.getReference("expensesBean");
			expensesBean.setExpTransReport(null);
			expensesBean.setDateFrom(null);
			expensesBean.setDateTo(null);

			projectHome.setTitlePage("Report --> Counter Report --> Daily");
			projectHome.setContentpage("/reports/counterReport/dailyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuDailyDiscountReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			ExpensesBean expensesBean=(ExpensesBean) BeanContext.getReference("expensesBean");
			expensesBean.setExpTransReport(null);
			expensesBean.setDateFrom(null);
			expensesBean.setDateTo(null);

			projectHome.setTitlePage("Report --> Discount Report --> Daliy");
			projectHome.setContentpage("/reports/discountReport/dailyReport.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuYearlySalesSumaaryCategoryBased() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Sales --> Annual Summary (Category Based)");
			projectHome.setContentpage("/reports/salesItemReport/annualSummaryReport.xhtml");

			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getSalesRecord();
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuAnnualSalesSumaaryCategoryBased() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Sales --> Annual Sales Summary (Category Based)");
			projectHome.setContentpage("/reports/salesItemReport/annualSalesSummaryCategoryBased.xhtml");

			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getSalesRecord();
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}

	public String menuExpensesReport() {
		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Expenses --> Annual Expenses Report ");
			projectHome.setContentpage("/reports/expensesReport/annualExpensesReport.xhtml");
			
			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getSalesRecord();
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	
	public String menuStaffPerformance() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("STAFF PERFORMANCE");
			projectHome.setContentpage("/staffperformance/dashboard.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuPrinterSetup() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("SETUP --> PRINTER SETUP ");
			projectHome.setContentpage("/admin/kitchenPrinter/mainMenu.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuTableSetup() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			
			HoteltableBean hoteltableBean=(HoteltableBean) BeanContext.getReference("hoteltableBean");
			hoteltableBean.fetchAllHotelTableArea();
			hoteltableBean.fetchUnassignedHotelTableList();
			hoteltableBean.setAreaId(0);
			
			projectHome.setTitlePage("SETTING --> TABLE SETUP ");
			projectHome.setContentpage("/admin/tablesetup/tableSetupHome.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuSystemConfig() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("SETTING --> SYSTEM SETTING ");
			projectHome.setContentpage("/admin/systemConfig/configMenu.xhtml");
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuShiftSetup() {
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("SETTING --> SHIFT SETUP ");
			projectHome.setContentpage("/admin/shift/shiftSetting.xhtml");
			
			ShiftBean shiftBean=(ShiftBean) BeanContext.getReference("shiftBean");
			shiftBean.loadShiftList();
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menutableorderSummary() {
		this.setReportsMenu(0);


		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Table Order Report");
			projectHome.setContentpage("/sales/tableorderreport.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
	}

	
	public String menuRemarksSetting(){
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	
			ItemRemarksBean itemRemarksBean=(ItemRemarksBean) BeanContext.getReference("itemRemarksBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Setting --> Remarks Setting");
			projectHome.setContentpage("/admin/itemremarks/mainMenu.xhtml");
			
			itemRemarksBean.loadAllRemarksList();
			
			ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
			productCategoryBean.loadAllValidCategory();
			
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
	}
	
	public String discountSetting(){
		this.setReportsMenu(0);

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	
			DiscountBean discountBean=(DiscountBean) BeanContext.getReference("discountBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Setting --> Discount Setting");
			projectHome.setContentpage("/admin/discount/discountSetting.xhtml");
			discountBean.getDiscountList();
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
	}
	
	public String menusalesOrderSummaryowner() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			projectHome.setTitlePage("Sales --> Sales Order Summary");
			projectHome.setContentpage("/sales/salesOrdersummaryowner.xhtml");
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	public String menusalessummary() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			SalesSummaryBean salesSummaryBean = (SalesSummaryBean) BeanContext.getReference("salesSummaryBean");
			projectHome.setTitlePage("Sales --> Branch Sales Summary");
			projectHome.setContentpage("/sales/branchSalesSummary.xhtml");
			salesSummaryBean.resetBranchSalesSummary();	
		
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	public String menucustomersalessummary() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();			
			SalesSummaryBean salesSummaryBean = (SalesSummaryBean) BeanContext.getReference("salesSummaryBean");
			projectHome.setTitlePage("Sales --> Customer Sales Summary");
			projectHome.setContentpage("/sales/customerSalesSummary.xhtml");
			salesSummaryBean.resetCustomerSalesSummary();	
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	
	
	public String menuOrganization() {
		this.setReportsMenu(0);

		try {
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");			
			IBranchBO branchBO=objectMapController.getBranchBO();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			//homeinfo.clearSessionObject();	
			branchBean.setBranch(branchBO.getBranchDetails(loginBean.getBranch().getBranchId()));
			projectHome.setContentpage("/admin/addEditOrganization.xhtml");
			projectHome.setTitlePage("Admin -->Settings-->Organization");	
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	public String menuTaxAccount() {

		try {
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");			
			IBranchBO branchBO=objectMapController.getBranchBO();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();	
			
			projectHome.setContentpage("/commission/gstaccountlist.xhtml");
			projectHome.setTitlePage("Accounts -->Monthly Tax ");	
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
	public String menuTaxPaymentAccount() {

		try {
			ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");			
			IBranchBO branchBO=objectMapController.getBranchBO();
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
			BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();	
			
			projectHome.setContentpage("/commission/gstaccountpaymentlist.xhtml");
			projectHome.setTitlePage("Accounts -->Tax Payment ");	
			action = projectHomeFile;			
			homeinfo.menuPageRedirect();
			} catch (Exception e) {
			action = "login";
			}
			return action;
		}
	
	
			
	public String menuSelection() {

		try {
			ProjectHome projectHome = (ProjectHome) BeanContext
					.getReference("projectHome");
			LoginBean loginBean = (LoginBean) BeanContext
					.getReference("loginBean");
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();
			
			homeinfo.clearSessionObject();
			
			action = projectHomeFile;
			String index = "";
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			if (externalContext.getRequestParameterMap().get("id") != null) {
				index = externalContext.getRequestParameterMap().get("id");
			}
			
				
						
			if (index.equalsIgnoreCase("sample")) {				
				projectHome.setTitlePage("Reports --> Sample Report");
				projectHome.setContentpage("/reports/sample.xhtml");
				action = projectHomeFile;
			}	
			
			if (index.equalsIgnoreCase("purchaseRequestReport")) {				
				projectHome.setTitlePage("Reports --> PurchaseRequest Report");
				projectHome.setContentpage("/reports/purchaseRequestReport.xhtml");
				action = projectHomeFile;
			}			
			
			  homeinfo.menuPageRedirect();

		} catch (Exception e) {
			action = "login";
			Log.error("Exception menuSelection  " + e);
		}
		return action;

	}

	public String userLogout() {
		action = "loginPage";
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		ThemeSwitcherBean themeSwitcherBean = (ThemeSwitcherBean) BeanContext.getReference("themeSwitcherBean");
		BranchstaffmemberModel logdetail=loginBean.getLogdetail();
		try {
			logdetail.setThemeName(themeSwitcherBean.getTheme());
			logdetail.setReminder(logdetail.getReminder());			
			loginBean.updateUserStatus(logdetail);
			
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		} catch (Exception e) {

		}
		return action;
	}
	
	
	public String userHome()
	{
		this.setReportsMenu(0);

		action = projectHomeFile;
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			

		try {
			projectHome.setTitlePage("Dashboard --> My Home");
			
			projectHome.setContentpage("/home/dashBoardMaster.xhtml");
			if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("Waiter"))
			{
				FacesContext context1 = FacesContext.getCurrentInstance();

				HttpServletRequest request = (HttpServletRequest) context1.getExternalContext().getRequest();

				String userAgent = request.getHeader("user-agent");
				
				projectHome.setTitlePage("Dashboard");
				if(userAgent.matches(".*Android.*")) {
					projectHome.setContentpage("/mobileVersionUI/sales/mainMenu.xhtml");	
				}else {
					OrderBean orderBean = (OrderBean) BeanContext.getReference("orderBean");
					orderBean.resetTerminalOrder();
					projectHome.setTerminalOrderContentPage("/terminalOrder/sales/tablesSelection.xhtml");
					projectHome.setContentpage("/terminalOrder/sales/terminalHome.xhtml");
					
				}
				
			}
			loginBean.setForcereset(0);
			dashboardBean.getLatestDashboardRecord();
			homeinfo.menuPageRedirect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return action;
	}
	
	public String changePassword()
	{
		this.setReportsMenu(0);

		action = projectHomeFile;
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		try {
			projectHome.setTitlePage("Project --> change Password");
			projectHome.setContentpage("/home/preSetPasswordForm.xhtml");
			projectHome.setHeaderPage("/templates/header.xhtml");	
		} catch (Exception e) {

		}
		return action;
	}
	
	public String menuMembership() {
		try {
			this.setReportsMenu(0);

			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Membership");
			projectHome.setContentpage("/membership/membershipMenu.xhtml");
			MembershipPaymentBean membershipPaymentBean = (MembershipPaymentBean) BeanContext.getReference("membershipPaymentBean");
			membershipPaymentBean.loadPaymentTransactionAll();
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuOnlineOrderView() {
		
		try {
			this.setReportsMenu(0);
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Online Order");
			projectHome.setContentpage("/onlineorder/orders.xhtml");
			OnlineOrderBean onlineOrderBean = (OnlineOrderBean) BeanContext.getReference("onlineOrderBean");
			onlineOrderBean.loadOnlineOrderList();
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	
	public String menuOnlineOrderReport() {
		
		try {
			this.setReportsMenu(0);
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Online Order Report");
			projectHome.setContentpage("reports/onlineorder/orderReport/dailyReport.xhtml");
			OnlineOrderBean onlineOrderBean = (OnlineOrderBean) BeanContext.getReference("onlineOrderBean");
			onlineOrderBean.setDailyReportDeliveryStatus("1");
			onlineOrderBean.loadOnlineReport();
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuSystemTest() {

		try {
			this.setReportsMenu(0);
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("System Test");
			projectHome.setContentpage("/terminalOrder/sales/terminalHome.xhtml");
			
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public String menuShopList() {
		
		try {
			this.setReportsMenu(0);
			ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			homeinfo.clearSessionObject();
			projectHome.setTitlePage("Branch List");
			projectHome.setContentpage("admin/branches.xhtml");
			OnlineOrderBean onlineOrderBean = (OnlineOrderBean) BeanContext.getReference("onlineOrderBean");
			onlineOrderBean.setDailyReportDeliveryStatus("1");
			onlineOrderBean.loadOnlineReport();
			action=projectHomeFile;
			homeinfo.menuPageRedirect();
		}catch(Exception e) {
			action="login";
		}
		return action;
	}
	
	public void redirectToPrevPage() {
		ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");
		ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
		List<String> pages=projectHome.getPageTracker();
	
		projectHome.setPrevPage(pages.get(pages.size()-1));
		pages.remove(pages.size()-1);
		projectHome.setPageTracker(pages);
		LoginBean loginBean=(LoginBean) BeanContext.getReference("loginBean");
		
		projectHome.setContentpage(projectHome.getPrevPage());
		if(projectHome.getContenttpage().equals("/terminalOrder/sales/terminalHome.xhtml")) {
			OrderBean orderBean=(OrderBean) BeanContext.getReference("orderBean");
			orderBean.setSalesorder(null);
		}
		String kitchenOrderListPageName="/mobileVersionUI/sales/viewKitchenOrder.xhtml";
		if(projectHome.getContenttpage().equals(kitchenOrderListPageName)) {
			DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
			dashboardBean.getLatestDashboardRecord();
		}
		
		homeinfo.menuPageRedirect();

				
	}
	
	public void redirectToHomePage() {
		LoginBean loginBean=(LoginBean) BeanContext.getReference("loginBean");
		ProjectHome projectHome=(ProjectHome) BeanContext.getReference("projectHome");

		if(loginBean.getLogdetail().getRoleName().equalsIgnoreCase("waiter")) {
			ProjectHomeBeanInfo homeinfo=new ProjectHomeBeanInfo();
			projectHome.setContentpage(projectHome.getHomePage());
			
			FacesContext context1 = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context1.getExternalContext().getRequest();
			String userAgent = request.getHeader("user-agent");
			
			if(userAgent.matches(".*Android.*")) {
				// do nothing
			}else {
				PosBean posBean=(PosBean) BeanContext.getReference("posBean");
				posBean.setSalesitembreakdowns(null);
				posBean.setCatId(null);
				posBean.resetPos();
				OrderBean orderBean=(OrderBean) BeanContext.getReference("orderBean");
				orderBean.resetTerminalOrder();
				projectHome.setTerminalOrderContentPage(projectHome.getInitPage());	
			}
			
				homeinfo.menuPageRedirect();
			
		}else {
			PosBean posBean=(PosBean) BeanContext.getReference("posBean");
			posBean.setSalesitembreakdowns(null);
			posBean.setCatId(null);
			posBean.resetPos();
			OrderBean orderBean=(OrderBean) BeanContext.getReference("orderBean");
			orderBean.resetTerminalOrder();
		}
	/*		ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
			

			try {
				context2.redirect(context2.getRequestContextPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		*/
		
		
		
	}


	

}