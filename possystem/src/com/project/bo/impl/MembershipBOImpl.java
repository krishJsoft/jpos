package com.project.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.IMembershipBO;
import com.project.dao.interfaces.IMembershipDAO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.MembershipConfigurationModel;
import com.project.model.datamodel.MembershipModel;
import com.project.model.datamodel.MembershipPaymentTransactionModel;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.model.his.Membership;
import com.project.model.his.MembershipConfiguration;
import com.project.model.his.MembershipPaymentTransaction;

@Service("membershipBO")
public class MembershipBOImpl implements IMembershipBO {

	public static Logger log = LoggerFactory.getLogger(MembershipBOImpl.class);

	@Resource(name="membershipRepository")
	private IMembershipDAO membershipRepository;
	
	@Transactional
	@Override
	public boolean createNewMember(MembershipModel membership) throws Exception {
		boolean saveSuccess=false;
		try {

			Membership obj=new Membership();
			obj=convert2MembershipDB(membership);

			Branch branchObj=new Branch();
			branchObj.setBranchId(membership.getBranchId());
			
			Branchstaffmember staffObj=new Branchstaffmember();
			staffObj.setStaffId(membership.getCreatedBy());
			
			obj.setBranch(branchObj);
			obj.setCreatedBy(staffObj);
			
			saveSuccess=membershipRepository.createNewMember(obj);
		}catch(Exception ex) {
			log.info("Error in createNewMember membershipBOImp "+ex);
			throw ex;
		}
		return saveSuccess;
	}

	@Override
	public boolean updateMember(MembershipModel membership) throws Exception {
		boolean updateSuccess=false;
		try {
			Membership obj=new Membership();
			obj=convert2MembershipDB(membership);
			obj.setId(membership.getId());
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(membership.getBranchId());
			obj.setBranch(branchObj);
			
			Branchstaffmember staffObj=new Branchstaffmember();
			staffObj.setStaffId(membership.getCreatedBy());
			obj.setCreatedBy(staffObj);
			
			updateSuccess=membershipRepository.updateMember(obj);
		}catch(Exception ex) {
			log.info("Error in createNewMember membershipBOImp "+ex);
			throw ex;
		}
		return updateSuccess;	
	}
	
	private MembershipModel convert2MembershipModel(Membership membership) {
		MembershipModel obj=new MembershipModel();
		obj.setId(membership.getId());
		obj.setName(membership.getName());
		obj.setIdentificationNumber(membership.getIdentificationNumber());
		obj.setGender(membership.getGender());
		obj.setAddress(membership.getAddress());
		obj.setContactNo(membership.getContactNo());
		obj.setEmailAddress(membership.getEmail());
		obj.setUserName(membership.getUserName());
		obj.setPassword(membership.getPassword());
		obj.setExpirationDate(membership.getExpirationDate());
		obj.setCreatedDate(membership.getCreatedDate());
		obj.setStatus(membership.getStatus());
		
		
		
		return obj;
	}
	
	private Membership convert2MembershipDB(MembershipModel membership) {
		Membership obj=new Membership();
		obj.setName(membership.getName());
		obj.setIdentificationNumber(membership.getIdentificationNumber());
		obj.setGender(membership.getGender());
		obj.setAddress(membership.getAddress());
		obj.setContactNo(membership.getContactNo());
		obj.setEmail(membership.getEmailAddress());
		obj.setUserName(membership.getUserName());
		obj.setPassword(membership.getPassword());
		obj.setExpirationDate(membership.getExpirationDate());
		obj.setCreatedDate(membership.getCreatedDate());
		obj.setStatus(membership.getStatus());
		
		return obj;
		
	}
	
	@Override
	public List<MembershipModel> fetchActiveMembershipList(int branchId) throws Exception {
		try {
			List<MembershipModel> objList=new ArrayList<MembershipModel>();
			
			for(Membership data:membershipRepository.fetchActiveMembershipList(branchId)) {
				MembershipModel obj=convert2MembershipModel(data);
				
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				
				obj.setBranch(branchObj);
				
				objList.add(obj);
				
			}
			
			return objList;
		}catch(Exception ex) {
			log.info("Error in fetchActiveMembershipList MembershipBOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public List<MembershipModel> fetchMembershipList(int branchId) throws Exception {
		try {
			List<MembershipModel> objList=new ArrayList<MembershipModel>();
			
			for(Membership data:membershipRepository.fetchMembershipList(branchId)) {
				MembershipModel obj=convert2MembershipModel(data);
				
				obj.setBranchId(data.getBranch().getBranchId());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				
				obj.setBranch(branchObj);
				
				objList.add(obj);
				
			}
			
			return objList;
		}catch(Exception ex) {
			log.info("Error in fetchMembershipList MembershipBOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public MembershipModel fetchSelectedMember(int memberId) throws Exception {
		try {
			MembershipModel obj=new MembershipModel();
			Membership data=membershipRepository.fetchSelectedMember(memberId);
			obj=convert2MembershipModel(data);
			
			obj.setBranchId(data.getBranch().getBranchId());
			obj.setCreatedBy(data.getCreatedBy().getStaffId());
			
			BranchModel branchObj=new BranchModel();
			branchObj.setBranchId(data.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			BranchstaffmemberModel staffObj=new BranchstaffmemberModel();
			staffObj.setStaffId(data.getCreatedBy().getStaffId());
			obj.setBranchStaff(staffObj);
			
			

			return obj;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchSelectedMember MembershipBOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public boolean createPaymentTransaction(MembershipPaymentTransactionModel membershipPayment) throws Exception {
		boolean savesuccess=false;

		try {
			MembershipPaymentTransaction obj=new MembershipPaymentTransaction();
			obj.setAmount(membershipPayment.getAmount());
			obj.setCreatedDate(membershipPayment.getCreatedDate());
			obj.setAddedDuration(membershipPayment.getDurationAdded());
			obj.setStatus(membershipPayment.getStatus());
			
			Membership memberObj=new Membership();
			memberObj.setId(membershipPayment.getMemberId());
			obj.setMember(memberObj);
			
			Branchstaffmember staffObj=new Branchstaffmember();
			staffObj.setStaffId(membershipPayment.getCreatedBy());
			obj.setStaff(staffObj);
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(membershipPayment.getBranchId());
			obj.setBranch(branchObj);
			
			savesuccess=membershipRepository.createPaymentTransaction(obj);
			
		}catch(Exception ex) {
			log.info("Error in createPaymentTransaction MembershipBOImpl " + ex);
			throw ex;
		}
		return savesuccess;

	}

	@Override
	public boolean updateConfiguration(MembershipConfigurationModel config) throws Exception {
		boolean updateSuccess=false;
		try {
			MembershipConfiguration configData=new MembershipConfiguration();
			
			configData.setId(config.getId());
			configData.setPaymentType(config.getPaymentType());
			configData.setAmount(config.getAmount());
			configData.setMemberType(config.getMemberType());
			
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(config.getBranchId());
			configData.setBranch(branchObj);
			
			updateSuccess=membershipRepository.updateConfiguration(configData);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateConfiguration MembershipBOImpl " + ex);
			throw ex;
		}
		return updateSuccess;
	}

	@Override
	public MembershipConfigurationModel fetchConfiguration(int branchId) throws Exception {
		MembershipConfigurationModel config=new MembershipConfigurationModel();
		try {
			MembershipConfiguration configData=membershipRepository.fetchConfiguration(branchId);
			config.setId(configData.getId());
			config.setPaymentType(config.getPaymentType());
			config.setAmount(configData.getAmount());
			config.setMemberType(configData.getMemberType());
			config.setBranchId(configData.getBranch().getBranchId());
		}catch(Exception ex) {
			log.info("Error in MembershipConfigurationModel MembershipBOImpl " + ex);
			throw ex;
		}
		return config;
	}

	@Override
	public boolean memberIsExist(int branchId, String identificationNumber) throws Exception {
		boolean exist=false;
		try {
			exist=membershipRepository.memberIsExist(branchId,identificationNumber);
		}catch(Exception ex) {
			log.info("Error in memberIdIsExist MembershipBOImpl " + ex);
			throw ex;
		}
		return exist;
	}

	@Override
	public MembershipPaymentTransactionModel fetchSelectedPaymentTransaction(int transactionId) throws Exception {
		MembershipPaymentTransactionModel obj=new MembershipPaymentTransactionModel(); 
		try {
			MembershipPaymentTransaction data=membershipRepository.fetchSelectedPaymentTransaction(transactionId);
			
			obj.setId(data.getId());
			obj.setAmount(data.getAmount());
			obj.setCreatedDate(data.getCreatedDate());
			obj.setDurationAdded(data.getAddedDuration());
			obj.setStatus(data.getStatus());
			
			BranchModel branchObj=new BranchModel();
			branchObj.setBranchId(data.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			BranchstaffmemberModel staffObj=new BranchstaffmemberModel();
			staffObj.setStaffId(data.getStaff().getStaffId());
			obj.setBranchStaff(staffObj);
			
			MembershipModel memberObj=new MembershipModel();
			memberObj.setId(data.getMember().getId());
			memberObj.setIdentificationNumber(data.getMember().getIdentificationNumber());
			obj.setMember(memberObj);
			
			
			
		}catch(Exception ex) {
			log.info("Error in fetchSelectedPaymentTransaction MembershipBOImpl " + ex);
			throw ex;
		}
		return obj;
	}

	@Override
	public List<MembershipPaymentTransactionModel> fetchAllPaymentTransaction(int branchId) throws Exception {
		List<MembershipPaymentTransactionModel> objList=new ArrayList<MembershipPaymentTransactionModel>();

		try {

			for(MembershipPaymentTransaction data:membershipRepository.fetchAllPaymentTransaction(branchId)) {
				MembershipPaymentTransactionModel obj=new MembershipPaymentTransactionModel(); 
				obj.setId(data.getId());
				obj.setAmount(data.getAmount());
				obj.setCreatedDate(data.getCreatedDate());
				obj.setDurationAdded(data.getAddedDuration());
				obj.setStatus(data.getStatus());
				
				BranchModel branchObj=new BranchModel();
				branchObj.setBranchId(data.getBranch().getBranchId());
				obj.setBranch(branchObj);
				
				BranchstaffmemberModel staffObj=new BranchstaffmemberModel();
				staffObj.setStaffId(data.getStaff().getStaffId());
				obj.setBranchStaff(staffObj);
				
				MembershipModel memberObj=new MembershipModel();
				memberObj.setId(data.getMember().getId());
				memberObj.setName(data.getMember().getName());
				memberObj.setIdentificationNumber(data.getMember().getIdentificationNumber());
				memberObj.setExpirationDate(data.getMember().getExpirationDate());
				memberObj.setCreatedDate(data.getMember().getCreatedDate());
				obj.setMember(memberObj);

				objList.add(obj);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchSelectedPaymentTransaction MembershipBOImpl " + ex);
			throw ex;
		}
	return objList;
	}

	@Override
	public Boolean deleteSelectedPaymentTransaction(int transactionId) throws Exception {
		boolean deleteSuccess=false;
		try {
			deleteSuccess=membershipRepository.deleteSelectedPaymentTransaction(transactionId);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteSelectedPaymentTransaction MembershipBOImpl " + ex);
			throw ex;
		}
		return deleteSuccess;
	}
	
	@Override
	public Boolean updateSelectedPaymentTransaction(MembershipPaymentTransactionModel membershipPayment) throws Exception {
		boolean updateSuccess=false;
		try {
			MembershipPaymentTransaction obj=new MembershipPaymentTransaction();
			obj.setId(membershipPayment.getId());
			obj.setAmount(membershipPayment.getAmount());
			obj.setCreatedDate(membershipPayment.getCreatedDate());
			obj.setAddedDuration(membershipPayment.getDurationAdded());
			obj.setStatus(membershipPayment.getStatus());
			
			Membership memberObj=new Membership();
			memberObj.setId(membershipPayment.getMember().getId());
			obj.setMember(memberObj);
			
			Branchstaffmember staffObj=new Branchstaffmember();
			staffObj.setStaffId(membershipPayment.getBranchStaff().getStaffId());
			obj.setStaff(staffObj);
			
			Branch branchObj=new Branch();
			branchObj.setBranchId(membershipPayment.getBranch().getBranchId());
			obj.setBranch(branchObj);
			
			updateSuccess=membershipRepository.updateSelectedPaymentTransaction(obj);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateSelectedPaymentTransaction MembershipBOImpl " + ex);
			throw ex;
		}
		return updateSuccess;
	}

	@Override
	public boolean adjustSelectedMembershipExpiredDate(int parseInt, Date newDate) throws Exception {
		boolean adjustSuccess=false;
		try {
			adjustSuccess=membershipRepository.adjustSelectedMembershipExpiredDate(parseInt,newDate);
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in adjustSelectedMembershipExpiredDate MembershipBOImpl " + ex);
			throw ex;
		}
		return adjustSuccess;

	}

	

}
