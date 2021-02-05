package com.project.home;

import java.io.Serializable;  
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.DateUtil;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;

  
@ManagedBean(name="chartBean")
@SessionScoped


//Author Gopal Ar 
//2013-05

public class ChartBean implements Serializable {  
  
    private CartesianChartModel categoryModel;  
    private CartesianChartModel categoryModel1;  
    private Date salespurchaseDate;
    int salesyear;
    int salesmovementyear;
    int limit;
    private BarChartModel topcustomers;  
    private BarChartModel topsuppliers;
    ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
    LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
    private CartesianChartModel topsalesProduct;  
    
    
    private DonutChartModel donutModel1;
    private DonutChartModel donutModel2; 
   
    public DonutChartModel getDonutModel1() {    	
    	createDonutModels();
        return donutModel1;
    }
     
    public DonutChartModel getDonutModel2() {
    	createDonutModels();
        return donutModel2;
    }
    
	public ChartBean() {  
       
        createCategoryModel1();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
   
    public CartesianChartModel getCategoryModel1() {  
        return categoryModel1;  
    }      
    
    public Date getSalespurchaseDate() {
		return salespurchaseDate;
	}

	public void setSalespurchaseDate(Date salespurchaseDate) {
		this.salespurchaseDate = salespurchaseDate;
	}	
	
	
	public BarChartModel getTopcustomers() {
		return topcustomers;
	}

	public void setTopcustomers(BarChartModel topcustomers) {
		this.topcustomers = topcustomers;
	}
		

	public BarChartModel getTopsuppliers() {
		return topsuppliers;
	}

	public void setTopsuppliers(BarChartModel topsuppliers) {
		this.topsuppliers = topsuppliers;
	}

	public int getSalesyear() {
		return salesyear;
	}

	public void setSalesyear(int salesyear) {
		this.salesyear = salesyear;
	}	
	
	public int getSalesmovementyear() {
		return salesmovementyear;
	}

	public void setSalesmovementyear(int salesmovementyear) {
		this.salesmovementyear = salesmovementyear;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void dateChange(SelectEvent event) 
	{   
		DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
		salespurchaseDate = (Date)event.getObject();
		dashboardBean.getLatestDashboardSalesRecord();
	}
	
	
	public void getSalesPurchasechart()
	{		
		createCategoryModel1();
		
	}
	
	
	
	    public void topCustomerBarchart() {  
		
		topcustomers = new BarChartModel();  
        DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
        
        ChartSeries sales = new ChartSeries();  
        sales.setLabel("Customer");                 
       
        topcustomers.setLegendPosition("ne");
        topcustomers.setAnimate(true);
        topcustomers.setShadow(true);
        topcustomers.setShowPointLabels(true);
        //topcustomers.setSeriesColors("e68027");
        
        Axis xAxis = topcustomers.getAxis(AxisType.X);        
        xAxis.setTickAngle(-25);        
        
        Axis yAxis = topcustomers.getAxis(AxisType.Y);                
        yAxis.setTickAngle(-25);    
        
        List<BranchSalesSummaryModel> topcustomerList = new ArrayList<BranchSalesSummaryModel>();	
        topcustomerList= dashboardBean.getTopcustomers();
        int count=0;
        if(topcustomerList.size()!=0)
        {
        for(BranchSalesSummaryModel data:topcustomerList)
        {
        	count=count+1;        	
        	sales.set(data.getCustomerName(), data.getNormalPriceTotal());        	
        }  
        }
        else
        {
        	for(int i=0;i<10;i++)
	        {
        	count=count+1;  
        	sales.set(""+i, 0);	        	
	        }  	
        }
        
        for(int i=1;i<10-count;i++)
        {
        	sales.set(""+i, 0);	   
        }
        
        topcustomers.addSeries(sales);           
    }
	
	
	
	    
	    public void topSupplierBarchart() {  
			
	    	topsuppliers = new BarChartModel();  
	        DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
	        
	        ChartSeries purchase = new ChartSeries();  
	        purchase.setLabel("Supplier");     
	      
	       
	        topsuppliers.setLegendPosition("ne");
	        topsuppliers.setAnimate(true);
	        topsuppliers.setShadow(true);
	        topsuppliers.setShowPointLabels(true);
	       
	        
	        Axis xAxis = topsuppliers.getAxis(AxisType.X);        
	        xAxis.setTickAngle(-25);        
	        
	        Axis yAxis = topsuppliers.getAxis(AxisType.Y);                
	        yAxis.setTickAngle(-25);    
	        
	        List<BranchSalesSummaryModel> topsupplierList = new ArrayList<BranchSalesSummaryModel>();	
	        topsupplierList= dashboardBean.getTopsuppliers();
	        int count=0;
	        if(topsupplierList.size()!=0)
	        {
	        for(BranchSalesSummaryModel data:topsupplierList)
	        {
	        	count=count+1; 
	        	purchase.set(data.getSupplierName(), data.getPurchasePriceTotal());         	
	        	
	        }      
	        }
	        else
	        {
	        	for(int i=0;i<10;i++)
		        {
	        		count=count+1; 
		        	purchase.set(""+i, 0);
		        	
		        }  	
	        }
	        
	        for(int i=1;i<10-count;i++)
	        {
	        	purchase.set(""+i, 0);	   
	        }
	        topsuppliers.addSeries(purchase);           
	    }
		
	    
	
	
	private void createCategoryModel1() {  
        categoryModel1 = new BarChartModel();  
        DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
        
        BarChartSeries sales = new BarChartSeries();  
        sales.setLabel("Sales");  
        
        LineChartSeries purchase = new LineChartSeries();  
        purchase.setLabel("Purchase");             
        //categoryModel1.setSeriesColors(",1a4779");
        
        categoryModel1.setLegendPosition("ne");
        categoryModel1.setAnimate(true);
        categoryModel1.setShadow(true);
        categoryModel1.setShowPointLabels(true);
        
        Axis xAxis = categoryModel1.getAxis(AxisType.X);
        xAxis.setLabel("Month");
         
        Axis yAxis = categoryModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Amount (RM)");
        
        
        List<SalesPurchaseDashboradModel> dashList = new ArrayList<SalesPurchaseDashboradModel>();
        dashList= dashboardBean.getDashList();
        
        for(SalesPurchaseDashboradModel data:dashList)
        {
        	sales.set(data.getMonthName().substring(0, 3), data.getSubTotal()); 
        	purchase.set(data.getMonthName().substring(0, 3), data.getPurcahsesubTotal());
        	
        }      
        categoryModel1.addSeries(sales);  
        categoryModel1.addSeries(purchase);  
    }
	

	public CartesianChartModel getTopsalesProduct() {			
		return topsalesProduct;
	}

	public void setTopsalesProduct(CartesianChartModel topsalesProduct) {
		this.topsalesProduct = topsalesProduct;
	}  
    	
	
    
	
	
	public ChartSeries getMonthSalesProducts()
	{
		ChartSeries sales = new ChartSeries();	
		
		return sales;
	}
	
	
	public void getTopsalesProductTest() {		
		
			ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
			//IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();
			
			try
			{	
				topsalesProduct = new CartesianChartModel();  
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int limit1=5;
				if(this.getSalesmovementyear()!=0)
				{
				year=this.getSalesmovementyear();
				}
				
				if(this.getLimit()!=0)
				{
				limit1=this.getLimit();
				}
				
				for(Integer i=1;i<13;i++){						
			       
		        List<SalesorderbreakdownModel> dashList = new ArrayList<SalesorderbreakdownModel>();				
				Calendar aCalendar = Calendar.getInstance();
				aCalendar.set(Calendar.YEAR, year);
				aCalendar.set(Calendar.MONTH,i-1);
				aCalendar.set(Calendar.DATE, 1);
				aCalendar.set(Calendar.HOUR, 0);
				aCalendar.set(Calendar.MINUTE, 0);
				aCalendar.set(Calendar.SECOND, 0);
				
				Date firstDateOfMonth = aCalendar.getTime();			
				
				Calendar aCalendar1 = Calendar.getInstance();
				aCalendar1.set(Calendar.YEAR, year);
				aCalendar1.set(Calendar.MONTH,i-1);				
				aCalendar1.set(Calendar.HOUR, 23);
				aCalendar1.set(Calendar.MINUTE, 59);
				aCalendar1.set(Calendar.SECOND, 59);				
				aCalendar1.set(Calendar.DATE, aCalendar1.getActualMaximum(Calendar.DATE));
				
				Date lastDateOfMonth = aCalendar1.getTime();				
				
				dashList=salesOrderBO.getSalesByBranchProductReport(firstDateOfMonth,lastDateOfMonth,limit1,loginBean.getBranch().getBranchId());
				ChartSeries sales = new ChartSeries();					
				String monthName=DateUtil.getMonthForInt(i-1); 	
	        	sales.setLabel(monthName.substring(0, 3));
	        	
				for(SalesorderbreakdownModel data:dashList){							        		        	
		        	sales.set(data.getProductCode(), data.getQuantity()); 		        	
		        }      				
				this.topsalesProduct.addSeries(sales);	 
				
			}
			}			
			catch(Exception e)
			{
				
			}	
			
		}
	
	
	public void itemSelect(ItemSelectEvent event) {
		
		  Integer seriesIndex = event.getSeriesIndex();
		  Chart cModel=((Chart) event.getSource());	
		  BigDecimal salesDate = new BigDecimal(0.00);
		  BigDecimal purchaseDate = new BigDecimal(0.00);
		  
		//BarChartModel cModel = (BarChartModel((org.primefaces.component.chart.Chart)event.getSource()).getModel();
		
		  BarChartModel cModel1=(BarChartModel)cModel.getModel();
		  
		 List<ChartSeries> salesList = cModel1.getSeries();
		 
		/* for(ChartSeries sdata:salesList)
		 {
			 Map<Object, Number> cData= sdata.getData();
			 
		 }*/
		
	     Map<Object, Number> cData = salesList.get(seriesIndex).getData();    
	     salesDate=(BigDecimal) cData.get(event.getItemIndex());
	     
	     for (Entry<Object, Number> entry : cData.entrySet()) {
	    	 if(entry.getKey().equals(event.getItemIndex())){
	    		 salesDate=(BigDecimal) cData.get(event.getItemIndex());
				 break;
				}
			}	      
	     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data", ""+salesDate);
	    // RequestContext.getCurrentInstance().showMessageInDialog(message);
       
    }
	
	
	private void createDonutModels() {
        donutModel1 = initDonutModel();
        donutModel1.setTitle("Donut Chart");
        donutModel1.setLegendPosition("w");
         
        donutModel2 = initDonutModel();
        //donutModel2.setTitle("Custom Options");
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(5);
        donutModel2.setShowDataLabels(false);
        donutModel2.setDataFormat("value");
        donutModel2.setShadow(true);
      
    }
	

	private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
         
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("Suppliers", 700);
        circle1.put("Branchs", 300);
        circle1.put("Customers", 500);
        
        model.addCircle(circle1);
         
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
        circle2.put("Suppliers", 500);
        circle2.put("Branchs", 700);
        circle2.put("Customers", 300);        
        model.addCircle(circle2);
         
        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
        circle3.put("Suppliers", 300);
        circle3.put("Branchs", 500);
        circle3.put("Customers", 700);      
        model.addCircle(circle3);
         
        return model;
    }
	

}  