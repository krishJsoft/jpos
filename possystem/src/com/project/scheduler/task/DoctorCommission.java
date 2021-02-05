package com.project.scheduler.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.dao.impl.PurchaseOrderDAOImpl;
import com.project.dao.interfaces.ICommissionDAO;
import com.project.dao.interfaces.IDoctorsPrescriptionsDAO;
import com.project.dao.interfaces.ISalesorderDAO;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Commission;
import com.project.model.his.Commissionbreakdown;
import com.project.model.his.Doctorprescription;
import com.project.model.his.Salesorder;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Jan 2014
 * 
 */


@Service
public class DoctorCommission {

	
	public static Logger log = LoggerFactory.getLogger(DoctorCommission.class);

	
	@Resource(name = "doctorsPrescriptionsBO")
	private IDoctorsPrescriptionsBO doctorsPrescriptionsBO;
	
	@Resource(name = "doctorsPrescriptionsRepository")
	private IDoctorsPrescriptionsDAO doctorsPrescriptionsRepository;	
	
	@Resource(name = "salesOrderBO")
	private ISalesorderBO salesOrderBO;
	
	@Resource(name = "salesOrderRepository")
	private ISalesorderDAO salesOrderRepository;
	
	@Resource(name = "commissionRepository")
	private ICommissionDAO commissionRepository;
	
	
	
	
	@SuppressWarnings("resource")
	public void executeDoctorCommission() {
		try
		{		
			System.out.println("Doctor Commission. Current time is :: "+ new Date());	
			List<Doctorprescription> dpModelList = new ArrayList<Doctorprescription>();
			List<DoctorsPrescriptionsModel> doctorList=doctorsPrescriptionsBO.getDoctorCommission(null, null, "3", null);
			List<Commission> commList = new ArrayList<Commission>();
			
			if(doctorList!=null && doctorList.size()!=0)
			{
				for(DoctorsPrescriptionsModel tempData:doctorList)
				{
					Branchstaffmember branchstaffmember = new Branchstaffmember();
					Commission comm = new Commission();
					List<Commissionbreakdown> commissionbreakdowns = new ArrayList<Commissionbreakdown>();
					branchstaffmember.setStaffId(tempData.getBranchstaffmember().getStaffId());
					comm.setBranchstaffmember(branchstaffmember);
					comm.setCommisionDate(new Date());
					comm.setCommissionType("1"); // Doctor Commission
					comm.setCommisionAmount(DecimalUtil.formatRoundupCents(tempData.getCommissionAmount()));
					comm.setStatus("1");
					
					Branch branch = new Branch();
					branch.setBranchId(tempData.getBranchstaffmember().getBranchId());
					comm.setBranch(branch);
					
					
			dpModelList=doctorsPrescriptionsRepository.getDoctorList(null, null, null, null, null, "3",tempData.getBranchstaffmember().getStaffId());
			for(Doctorprescription subData:dpModelList)
			{
				Commissionbreakdown comsubData= new Commissionbreakdown();
				Doctorprescription db = new Doctorprescription();				
				comsubData.setCommission(comm);				
				db.setDoctorPrescriptionId(subData.getDoctorPrescriptionId());
				comsubData.setDoctorprescription(db);				
				comsubData.setAmount(new BigDecimal(0.00));
				
				commissionbreakdowns.add(comsubData);
				
			}
			comm.setCommissionbreakdowns(commissionbreakdowns);
			this.saveCommission(comm,dpModelList);
			}
			
			}
			
		}
		catch(Exception e)
		{
			log.debug("Error in execute of executeDoctorCommission ..." +e.toString());
		}
	}

	
	
	@Transactional
	public void saveCommission(Commission commissionData,List<Doctorprescription> dpModelList)
	{
		try
		{ 	    boolean saveSuccess=false;			
				saveSuccess=commissionRepository.createNewCommission(commissionData);	
				if(saveSuccess)
				{
				for(Doctorprescription dp:dpModelList)
				{
				dp.setStatus("4");	
				doctorsPrescriptionsRepository.updateDoctorPrescription(dp);
				}
				}
		}
		catch(Exception e)
		{
			log.debug("Error in execute of saveCommission ..." +e.toString());
		}
	}
	
	
	
	@Transactional
	public void saveSalesCommission(Commission commissionData,List<Salesorder> dpModelList)
	{
		try
		{ 	    boolean saveSuccess=false;			
				saveSuccess=commissionRepository.createNewCommission(commissionData);	
				if(saveSuccess)
				{
				for(Salesorder dp:dpModelList)
				{
				dp.setStatus("4");	
				salesOrderRepository.updateSalesorder(dp);
				}
				}
		}
		catch(Exception e)
		{
			log.debug("Error in execute of saveSalesCommission ..." +e.toString());
		}
	}
	

	@SuppressWarnings("resource")
	public void executeSalesCommission() {
		try
		{			
			List<Salesorder> dpModelList = new ArrayList<Salesorder>();
			List<SalesorderModel> staffList=salesOrderBO.getStaffSalesCommission("3", null, null, null);
			List<Commission> commList = new ArrayList<Commission>();
			
			if(staffList!=null && staffList.size()!=0)
			{
				for(SalesorderModel tempData:staffList)
				{
					Branchstaffmember branchstaffmember = new Branchstaffmember();
					Commission comm = new Commission();
					List<Commissionbreakdown> commissionbreakdowns = new ArrayList<Commissionbreakdown>();
					branchstaffmember.setStaffId(tempData.getBranchstaffmember().getStaffId());
					comm.setBranchstaffmember(branchstaffmember);
					comm.setCommisionDate(new Date());
					comm.setCommissionType("2"); // Staff Commission
					comm.setCommisionAmount(DecimalUtil.formatRoundupCents(tempData.getCommissionAmount()));
					comm.setStatus("1");
					
					Branch branch = new Branch();
					branch.setBranchId(tempData.getBranchstaffmember().getBranchId());
					comm.setBranch(branch);
					
			dpModelList=salesOrderRepository.getStaffSalesCommissionList(null, null, null, "3",tempData.getBranchstaffmember().getEmailAddress());
			for(Salesorder subData:dpModelList)
			{
				Commissionbreakdown comsubData= new Commissionbreakdown();
				Salesorder salesorder = new Salesorder();				
				comsubData.setCommission(comm);						
				salesorder.setSalesOrderId(subData.getSalesOrderId());
				comsubData.setSalesorder(salesorder);							
				comsubData.setAmount(new BigDecimal(0.00));				
				commissionbreakdowns.add(comsubData);
				
			}
			comm.setCommissionbreakdowns(commissionbreakdowns);
			this.saveSalesCommission(comm,dpModelList);
			}
			
			}
			
		}
		catch(Exception e)
		{
			log.debug("Error in execute of executeSalesCommission ..." +e.toString());
		}
	}
	
	
	
	
}
