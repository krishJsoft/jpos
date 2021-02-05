package com.project.bean.staffperformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.his.Salesorder;
import com.project.model.sale.sales.SalesorderModel;

@ManagedBean(name = "staffPerformanceBean")
@SessionScoped
public class StaffPerformanceBean {
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
	IStaffBO staffBO=objectMapController.getStaffBO();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	BranchstaffmemberModel topStaff = new BranchstaffmemberModel();

	public BranchstaffmemberModel getTopStaff() {
		return topStaff;
	}

	public void setTopStaff(BranchstaffmemberModel topStaff) {
		this.topStaff = topStaff;
	}

	public void fetchTopSalesStaff() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		Integer top=1;
		 
		try {
			List<SalesorderModel> salesObj=salesOrderBO.getTopSalesStaff(top,today.getTime(),loginBean.getBranch().getBranchId());
			List<BranchstaffmemberModel> staffObj=staffBO.findStaffMemberlistLogin(salesObj.get(0).getCreatedBy());
			topStaff=staffObj.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
