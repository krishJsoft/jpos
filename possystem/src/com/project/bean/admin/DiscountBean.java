package com.project.bean.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.project.bo.interfaces.IDiscountBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.sale.sales.SalesorderModel;
import com.project.util.DateUtil;

@ManagedBean(name = "discountBean")
@SessionScoped
public class DiscountBean {

	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	IDiscountBO discountBO=objectMapController.getDiscountBO();
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();

	List<SalesorderModel> salesDiscountList=new ArrayList<SalesorderModel>();
	
	Date dateFrom;
	Date dateTo;
	Integer discountRateId;
	Integer discountRemarksId;
	
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
	
	

	
	public List<SalesorderModel> getSalesDiscountList() {
		return salesDiscountList;
	}

	public void setSalesDiscountList(List<SalesorderModel> salesDiscountList) {
		this.salesDiscountList = salesDiscountList;
	}

	public Integer getDiscountRateId() {
		return discountRateId;
	}

	public void setDiscountRateId(Integer discountRateId) {
		this.discountRateId = discountRateId;
	}

	public Integer getDiscountRemarksId() {
		return discountRemarksId;
	}

	public void setDiscountRemarksId(Integer discountRemarksId) {
		this.discountRemarksId = discountRemarksId;
	}

	public void searchDiscountReport() {
		try {
	        Calendar calendar = Calendar.getInstance();

			if(this.dateFrom==null) {
				this.setDateFrom(DateUtil.getFromTodayDateTime());	
			}
			if(this.dateTo==null) {
				this.setDateTo(DateUtil.getToTodayDateTime());	
			}else {
				calendar.setTime(dateTo);
		        calendar.set(Calendar.MILLISECOND, 0);
		        calendar.set(Calendar.SECOND, 59);
		        calendar.set(Calendar.MINUTE, 59);
		        calendar.set(Calendar.HOUR, 23);	  
		        dateTo=calendar.getTime();
			}
			
	   //     calendar.setTime(dateFrom);
	     //   calendar.set(Calendar.MILLISECOND, 0);
	       // calendar.set(Calendar.SECOND, 0);
	        //calendar.set(Calendar.MINUTE, 0);
	        //calendar.set(Calendar.HOUR, 0);
	        //dateFrom=calendar.getTime();
	        
	        
	        

			this.salesDiscountList=salesOrderBO.getDiscountReport(dateFrom,dateTo,loginBean.getBranch().getBranchId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void resetSearchDiscountReport() {
        this.setDateFrom(DateUtil.getFromTodayDateTime());
        this.setDateTo(DateUtil.getToTodayDateTime());
		searchDiscountReport();
	}
	
	public void getDiscountList() {
		
		
	}

}
