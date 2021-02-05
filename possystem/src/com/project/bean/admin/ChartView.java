package com.project.bean.admin;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.LocalDate;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.DateUtil;


 
@ManagedBean(name = "chartView")
@RequestScoped
public class ChartView implements Serializable{
	
	//private List<performance> staffPerformance;
	private int salesYear=new Date().getYear();
	private int quarterlySalesYear=new Date().getYear();
	private int staffPerformanceYear=new Date().getYear();
	private int staffPerformanceMonth=new Date().getMonth()-1;
	private Date dateFrom;
	private Date dateTo;
	private String buttonClick;
	private LineChartModel lineModel;
	private LineChartModel salesLineModel=new LineChartModel();
	private LineChartModel staffPerformanceModel;
	private PieChartModel quartelySalesModel;
	private BarChartModel weeklyTopSalesModel;
	

	Integer staffId;
	
	List<BranchstaffmemberModel> branchStaffWaiters = new ArrayList<BranchstaffmemberModel>();
	List<ChartSeries> series=new ArrayList<ChartSeries>();
	
	
	BarChartSeries br;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	List<SalesorderModel> salesorder = new ArrayList<SalesorderModel>();
	List<SalesorderbreakdownModel> salesorderbreakdown = new ArrayList<SalesorderbreakdownModel>();
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
	IStaffBO staffBO=objectMapController.getStaffBO();
	
	private Map<String, Integer> seriesIndex = new LinkedHashMap<String, Integer>();
	
	 Axis x;
	 Axis y;
	
	LineChartSeries s;
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	
	private List<SelectItem> staffList= new ArrayList<SelectItem>();

	public List<SelectItem> getStaffList() {
		return staffList;
	}

	public List<BranchstaffmemberModel> getBranchStaffWaiters() {
		try {
		int roleId=4;
		
		branchStaffWaiters.clear();
			branchStaffWaiters=staffBO.findByStaffList(null,null ,roleId,null);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return branchStaffWaiters;
	}

	public void setBranchStaffWaiters(List<BranchstaffmemberModel> branchStaffWaiters) {
		
		this.branchStaffWaiters = branchStaffWaiters;
	}
	
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public int getSalesYear() {
		return salesYear;
	}

	public void setSalesYear(int salesYear) {
		this.salesYear = salesYear;
	}

	public int getQuarterlySalesYear() {
		return quarterlySalesYear;
	}

	public void setQuarterlySalesYear(int quarterlySalesYear) {
		this.quarterlySalesYear = quarterlySalesYear;
	}

	public int getStaffPerformanceYear() {
		return staffPerformanceYear;
	}

	public void setStaffPerformanceYear(int staffPerformanceYear) {
		this.staffPerformanceYear = staffPerformanceYear;
	}

	public int getStaffPerformanceMonth() {
		return staffPerformanceMonth;
	}

	public void setStaffPerformanceMonth(int staffPerformanceMonth) {
		this.staffPerformanceMonth = staffPerformanceMonth;
	}

	public String getButtonClick() {
		return buttonClick;
	}

	public void setButtonClick(String buttonClick) {
		this.buttonClick = buttonClick;
	}
	
	public LineChartModel getLineModel() {
		return lineModel;
	}
	
	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public LineChartModel getSalesLineModel() {
		return salesLineModel;
	}

	public void setSalesLineModel(LineChartModel salesLineModel) {
		this.salesLineModel = salesLineModel;
	}

	public CartesianChartModel getStaffPerformanceModel() {
		return staffPerformanceModel;
	}

	public void setStaffPerformanceModel(LineChartModel staffPerformanceModel) {
		this.staffPerformanceModel = staffPerformanceModel;
	}

	public PieChartModel getQuartelySalesModel() {
		return quartelySalesModel;
	}
	
	public void setQuartelySalesModel(PieChartModel pieModel) {
		this.quartelySalesModel = pieModel;
	}

	public BarChartModel getWeeklyTopSalesModel() {
		return weeklyTopSalesModel;
	}

	public void setWeeklyTopSalesModel(BarChartModel stackedBarModel) {
		this.weeklyTopSalesModel = stackedBarModel;
	}

	public Map<String, Integer> getSeriesIndex() {
		return seriesIndex;
	}

	public void setSeriesIndex(Map<String, Integer> seriesIndex) {
		this.seriesIndex = seriesIndex;
	}

	public ChartView() {
		this.buttonClick="Monthly";
		
		createLineModel();
		createQuartelySalesModel();
		createWeeklyTopSalesModel();
		setWaitersList();
		createStaffPerformanceModel();	
	}
	
	
	private void setWaitersList() {
		staffList.clear();
		int roleId=4;
		try {
			branchStaffWaiters=staffBO.findByStaffList(null,null ,roleId,null);
			
			for(int i=0;i<branchStaffWaiters.size();i++) {
				staffList.add(new SelectItem(branchStaffWaiters.get(i).getStaffId(), branchStaffWaiters.get(i).getFirstName()));
				if(staffId==null && i==0) {
					staffId=5;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createStaffPerformanceModel() {
		
	
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE,1);
		c.set(Calendar.HOUR, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
	  
		Date date = new Date();
		dateFrom=c.getTime();
		c.add(Calendar.DATE,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		dateTo=c.getTime();
		
		getStaffPerformanceChart(this.staffId,dateFrom,dateTo);

		
	  	
	     
	  	
	}

	private void getStaffPerformanceChart(Integer staffId,Date dateFrom,Date dateTo) {
		try{ 

			staffPerformanceModel = new LineChartModel();
			staffPerformanceModel.setExtender("staffPerformanceExtender");
			staffPerformanceModel.setTitle("STAFF PERFORMANCE ");
			staffPerformanceModel.setLegendPosition("e");
			

			s = new LineChartSeries();
			
			x = staffPerformanceModel.getAxis(AxisType.X);
			y = staffPerformanceModel.getAxis(AxisType.Y);
			y.setLabel("SALES");
		  	x.setTickInterval("1");
		  	staffPerformanceModel.addSeries(s);
		  	
			BigDecimal maxVal=new BigDecimal("0.00");
				
			//SimpleDateFormat sdFrom=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		//	LocalDate monthBegin = LocalDate.now().withDayOfMonth(1);
		//	LocalDate monthEnd =  LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
	        Calendar cal = Calendar.getInstance();
 
			cal.setTime(dateFrom);;
			int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
		//	dateFrom=java.sql.Date.valueOf(monthBegin.toString());
			//dateTo=java.sql.Date.valueOf(monthEnd.toString());
			salesorder=salesOrderBO.getDailyStaffSalesChart(dateFrom,dateTo,staffId);
			int i=1;
			int m=0;
			int index=1;
			Date dateToComp=dateFrom;
			if(salesorder.size()!=0) {
				while(!DateUtils.isSameDay(dateToComp,DateUtil.addDaysToDate(1, dateTo))) {
					
					BigDecimal total=new BigDecimal("0.00");
					
					//get MaxValue for the month
					if(DateUtils.isSameDay(salesorder.get(m).getCreatedDate(), dateToComp)) {
						
						total=salesorder.get(m).getTotalAmount();
						if(total.compareTo(maxVal) > 0)
							maxVal=total;
						if(m< (salesorder.size()-1))
							m++;
					}
					
					//start plot the chart
					s.set(index++,total);
					if(DateUtils.isSameDay(dateToComp,new java.util.Date()))
						break;
					dateToComp=DateUtil.addDaysToDate(1, dateToComp);
					
				}
			}
			else {
				s.set(1, 0);
			}
			 y.setMin(0);
			 y.setMax( maxVal.multiply(new BigDecimal("2.00")));
			 x.setMin(1);
			 x.setMax(days);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(dateFrom);
		  String currentMonthName=""+new SimpleDateFormat("MMMM").format(cal.getTime());
		x.setLabel("MONTH of "+currentMonthName);
	}
	
	private void topSalesItemChart(int rank,Date dateFrom,Date dateTo) {
		
		try {
			
			weeklyTopSalesModel = new BarChartModel();
			weeklyTopSalesModel.setStacked(true);
			weeklyTopSalesModel.setExtender("weeklyTopItem");
			weeklyTopSalesModel.setTitle("WEEKLY TOP 5 ITEM");
			weeklyTopSalesModel.setLegendPosition("e");
			
	//		LocalDate weekBegin=LocalDate.now().with(DayOfWeek.MONDAY);
		//	LocalDate weekEnd=LocalDate.now().with(DayOfWeek.SUNDAY);
		
			BigDecimal maxVal=new BigDecimal("0.00");
			
		//	dateFrom=java.sql.Date.valueOf(weekBegin.toString());
		//	dateTo=java.sql.Date.valueOf(weekEnd.toString());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFrom);
			String currentMonthName=""+new SimpleDateFormat("MMMM").format(cal.getTime());
		//    cal.setMinimalDaysInFirstWeek(1);
		    int wk = cal.get(Calendar.WEEK_OF_MONTH);
		    
		    x=weeklyTopSalesModel.getAxis(AxisType.X);
			x.setLabel("WEEK " + wk +", MONTH of " +currentMonthName);
			salesorderbreakdown=salesOrderBO.getDailyTopSalesItemChart(dateFrom,dateTo,rank);
			
			Date dateToComp=dateFrom;
			
			
			//String[] topProductList=new String[rank];
			ArrayList<String> topProductList= new ArrayList<String>();
			if(series!=null) {
				series.clear();	
			}
			if(seriesIndex!=null) {
				seriesIndex.clear();	
			}
			
			ChartSeries cSeries;
			if(salesorderbreakdown.size()!=0) {
				for(int i=0;i<salesorderbreakdown.size();i++) {
					String productName=salesorderbreakdown.get(i).getProductName();
					if(topProductList.size()==0) {
						topProductList.add(productName);
						cSeries=new ChartSeries();
						cSeries.setLabel(productName);
						series.add(cSeries);
						seriesIndex.put(productName, topProductList.size()-1);
					
					}
					else
						for(int k=0;k<topProductList.size();k++) {
							if(!(topProductList.contains(productName))) {
								topProductList.add(productName);
								cSeries=new ChartSeries();
								cSeries.setLabel(productName);
								series.add(cSeries);
								seriesIndex.put(productName, topProductList.size()-1);
						
							}
				}
				
			}
			
				BigDecimal total=new BigDecimal("0.00");
				int index=0;
				for(int j=1;j<=7;j++) {
					
					for(int k=0;k<topProductList.size();k++) {	
						if(index<salesorderbreakdown.size()
								&&
								k==seriesIndex.get(salesorderbreakdown.get(index).getProductName())
								&& DateUtils.isSameDay(salesorderbreakdown.get(index).getCreatedDate(), dateToComp)
								) {
							total=salesorderbreakdown.get(index).getSubTotal();
							index++;
						}
						else {
							total=new BigDecimal("0.00");
						}
						series.get(k).set(new SimpleDateFormat("EEE").format(dateToComp), total);
					}
					dateToComp=DateUtil.addDaysToDate(1, dateToComp);
	
				}
			}
			else {
				cSeries=new ChartSeries();
				cSeries.setLabel("no products");
				series.add(cSeries);
				series.get(0).set(1, 0);
			
			}
			for(int i=0;i<series.size();i++) {
				weeklyTopSalesModel.addSeries(series.get(i));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void quartelySalesChart() {
		int year=new Date().getYear();
		quartelySalesModel = new PieChartModel();
		quartelySalesModel.setExtender("pieExtender");
		
		try { 
			  //salesorder=salesOrderBO.getQuartelySalesListChart(this.getQuarterlySalesYear(),loginBean.getBranch().getBranchId());
				List<SalesorderModel> dataList=salesOrderBO.getQuartelySalesListChart(this.getQuarterlySalesYear(),loginBean.getBranch().getBranchId());
			  for(SalesorderModel data:dataList)
			  {
				  
				  BigDecimal total=new BigDecimal("0.00");
				  total=data.getTotalAmount();
				  quartelySalesModel.set(quaterText(data.getQuarterOfTheYear()-1), total);
			  }
			  if(dataList==null || dataList.size()==0) {
				  
				  quartelySalesModel.set("NO SALES RECORDED", 1);
			  }else {
				  quartelySalesModel.setShowDataLabels(true);
			  }
			  //for(int i=0;i<3;i++) {
				//BigDecimal total=new BigDecimal("0.00");
				 //total=salesorder.get(i).getTotalAmount();
				 //quartelySalesModel.set(quaterText(i), total);
			  //}
			
			  //pieModel.setTitle("Simple Pie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createQuartelySalesModel() {
		//quartelySalesModel = new PieChartModel();
		//quartelySalesModel.setExtender("pieExtender");
		quartelySalesChart();
		
	}
	
 	ChartSeries test;
	ChartSeries test2;
	
	public void createWeeklyTopSalesModel() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date date = new Date();
		dateFrom=c.getTime();
		  
		c.add(Calendar.DATE,6);
		dateTo=c.getTime();
		topSalesItemChart(5,dateFrom,dateTo); 

	}
	
	public void getSalesChartSeries() {
		
	}

	public void createSalesLineModel() {
		LineChartModel chartModel = new LineChartModel();
		chartModel.setExtender("chartSalesLineExtender");
		chartModel.setTitle("SALES");
		
		Axis  axisX= chartModel.getAxis(AxisType.X);
		axisX.setTickInterval("1");

		Axis axisY = chartModel.getAxis(AxisType.Y);
		axisY.setLabel("AMOUNT (RM)");

		getSalesChartSeries();
		 LineChartSeries series1 = new LineChartSeries();
		 series1.set("January", 2);
		  s = new LineChartSeries();

	}
	
	public void createLineModel(){
		  
		  lineModel = new LineChartModel();
		  
		  lineModel.setExtender("chartExtender");
		  //lineModel.setAnimate(true);
		  
		  x = lineModel.getAxis(AxisType.X);
		  y = lineModel.getAxis(AxisType.Y);
		  s = new LineChartSeries();
		  s.setLabel("Sales");

	      salesOrderChart();
	
	      lineModel.addSeries(s);
	      lineModel.setLegendPosition("e");
	     
	 
	      y.setLabel("SALES (RM)");
	      x.setTickInterval("1");
	      

	  }
	
		
	
	public void salesModel(ActionEvent event){
		  this.buttonClick = (String) event.getComponent().getAttributes()
					.get("salesModelType");
		  
		  createLineModel();
	 }
	
	public void updateStaffPerformanceChart() {
		
		createStaffPerformanceModel();
	}
	
	public static String quaterText(int quater){
	    String[] quaterString = {
	    		"1st Quarter ( Jan - Mar )",
	    		"2nd Quarter ( Apr - Jun )", 
	    		"3rd Quarter ( Jul - Sep )", 
	    		"4th Quarter ( Oct - Dec )"
	    		};
	    return quaterString[quater];
	}
	
	private static String monthName(int month){
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	  
	public void monthlySalesChart() {
	  lineModel.setTitle("MONTHLY SALES");
	  
	  int year=new Date().getYear();
	  int monthNo=0;
	  try {
		  BigDecimal maxVal=new BigDecimal("0.00"); 
		  salesorder=salesOrderBO.getMonthlySalesListChart(this.getSalesYear(),loginBean.getBranch().getBranchId());
		  int index=0;
		  for(int i=1;i<=12;i++) {
			BigDecimal total=new BigDecimal("0.00");
			
			if(index<salesorder.size())
				monthNo=salesorder.get(index).getSalesMonth();
			
			if(i==monthNo) {
				total=salesorder.get(index).getTotalAmount();
				if(total.compareTo(maxVal) > 0)
					maxVal=total;
				index++;
			}
			s.set(i, total);
			
		}
		
		 y.setMin(0);
		 y.setMax(maxVal.add(new BigDecimal(150)));
		 
		 x.setMin(1);
		 x.setMax(12);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		  Calendar cal = Calendar.getInstance();
		 // x.setLabel("YEAR of "+new SimpleDateFormat("YYYY").format(cal.getTime()));
	  }
	  
	  
	  
	  private void dailySalesChart() {
		  lineModel.setTitle("DAILY SALES");
		  
		  try
			{ 
				BigDecimal maxVal=new BigDecimal("0.00");
				
				//SimpleDateFormat sdFrom=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				LocalDate monthBegin = LocalDate.now().withDayOfMonth(1);
				LocalDate monthEnd =  LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
				
				
				
				int days=monthEnd.getDayOfMonth();
				
			//	dateFrom=.valueOf(monthBegin.toString());
		//		dateTo=java.sql.Date.valueOf(monthEnd.toString());
			
				
				salesorder=salesOrderBO.getDailySalesListChart(dateFrom,dateTo);
				
				int i=1;
				int m=0;
				java.util.Date dateToComp=dateFrom;
				
				if(salesorder.size()!=0)
				for(int j=1;j<=days;j++) {
					BigDecimal total=new BigDecimal("0.00");
					if(DateUtils.isSameDay(salesorder.get(m).getCreatedDate(), dateToComp)) {
						
						total=salesorder.get(m).getTotalAmount();
						if(total.compareTo(maxVal) > 0)
							maxVal=total;
						if(m< (salesorder.size()-1))
							m++;
					}
					
					s.set(j, total);
					if(DateUtils.isSameDay(dateToComp,new java.util.Date()))
						break;
					dateToComp=DateUtil.addDaysToDate(1, dateToComp);
				}
		
				
				 y.setMin(0);
				 y.setMax(maxVal.add(new BigDecimal(150)));
				 x.setMin(1);
				 x.setMax(days);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			
		    
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(dateFrom);
		  String currentMonthName=""+new SimpleDateFormat("MMMM").format(cal.getTime());
		  x.setLabel("MONTH of "+currentMonthName);
	  }
	  
	  private void salesOrderChart(){
		  if(buttonClick.equals("Monthly"))
			  monthlySalesChart();
		  else if(buttonClick.equals("Daily"))
			  dailySalesChart();
	  }
	  
	  public void onDateSelectWeeklyTopItem(SelectEvent event) {
		  
		
		  Date selectedDate=(Date) event.getObject();
		  
		  Calendar c = Calendar.getInstance();
		  c.setTime(selectedDate);
		  c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		  Date date = new Date();
		  dateFrom=c.getTime();
		  
		  c.add(Calendar.DATE,6);
		  c.set(Calendar.HOUR, 23);
		  c.set(Calendar.MINUTE, 59);
		  c.set(Calendar.SECOND, 59);
		  dateTo=c.getTime();
		  
		  
		  topSalesItemChart(5,dateFrom,dateTo); 

		  
	  }
	  
	  public void monthlyStaffPerformance() {

		  Calendar c = Calendar.getInstance();
		  c.set(this.getStaffPerformanceYear(), this.getStaffPerformanceMonth(), 1, 00, 00, 00);
		  
		  Date date = new Date();
		  dateFrom=c.getTime();
		  c.add(Calendar.DATE,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		  c.set(Calendar.HOUR, 23);
		  c.set(Calendar.MINUTE, 59);
		  c.set(Calendar.SECOND, 59);
		  dateTo=c.getTime();
		  getStaffPerformanceChart(this.getStaffId(),dateFrom,dateTo);
	  }
	  
}