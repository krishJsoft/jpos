package com.project.bean.staffperformance;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.model.sale.sales.SalesorderbreakdownModel;

@ManagedBean(name = "staffPerformanceChartBean")
@SessionScoped
public class StaffPerformanceChartBean {

	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();

	
	private HorizontalBarChartModel horizontalBarModel;

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}
	
	@PostConstruct
    public void init() {
        createBarModels();
    }
	
	  private void createBarModels() {
	      createHorizontalBarModel();
	  }
  
	  private void createHorizontalBarModel() {
	      horizontalBarModel = new HorizontalBarChartModel();
	
	      Calendar today = Calendar.getInstance();
	      
	      Integer top=5;
	      Integer staffId=11;
	      List<SalesorderbreakdownModel> objList=salesOrderBO.getTopCategorySalesByStaff(top,staffId,today.getTime());
	      
	      ChartSeries boys = new ChartSeries();
	      boys.setLabel("Mee Goreng");
	      boys.set("", 50);
	      //boys.set("2005", 96);
	      //boys.set("2006", 44);
	      //boys.set("2007", 55);
	      //boys.set("2008", 25);
	
	      ChartSeries girls = new ChartSeries();
	      girls.setLabel("Normal Drinks");
	      //girls.set("2004", 52);
	      girls.set("",90);
	      //girls.set("2006", 82);
	      //girls.set("2007", 35);
	      //girls.set("2008", 120);
	
	      horizontalBarModel.addSeries(boys);
	      horizontalBarModel.addSeries(girls);
	      
	      horizontalBarModel.setExtender("ext1");
	      
	     
	       
	      Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
	      xAxis.setLabel("No of orders");
	      xAxis.setMin(0);
	      xAxis.setMax(100);
	       
	      Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
	      yAxis.setLabel("Category");        
	  }
}
