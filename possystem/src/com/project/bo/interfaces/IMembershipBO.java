package com.project.bo.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.datamodel.MembershipConfigurationModel;
import com.project.model.datamodel.MembershipModel;
import com.project.model.datamodel.MembershipPaymentTransactionModel;

public interface IMembershipBO {

	boolean createNewMember(MembershipModel membership) throws Exception;

	boolean updateMember(MembershipModel membership) throws Exception;

	List<MembershipModel> fetchActiveMembershipList(int branchId) throws Exception;
	
	List<MembershipModel> fetchMembershipList(int branchId) throws Exception;

	MembershipModel fetchSelectedMember(int memberId) throws Exception;

	boolean createPaymentTransaction(MembershipPaymentTransactionModel membershipPayment) throws Exception;

	boolean updateConfiguration(MembershipConfigurationModel config) throws Exception;

	MembershipConfigurationModel fetchConfiguration(int branchId) throws Exception;

	boolean memberIsExist(int branchId, String identificationNumber) throws Exception;

	MembershipPaymentTransactionModel fetchSelectedPaymentTransaction(int transactionId) throws Exception;

	List<MembershipPaymentTransactionModel> fetchAllPaymentTransaction(int branchId)throws Exception;

	Boolean deleteSelectedPaymentTransaction(int transactionId) throws Exception;

	boolean adjustSelectedMembershipExpiredDate(int parseInt, Date newDate) throws Exception;

	Boolean updateSelectedPaymentTransaction(MembershipPaymentTransactionModel membershipPayment) throws Exception;



}
