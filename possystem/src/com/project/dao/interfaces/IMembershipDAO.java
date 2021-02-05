package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.datamodel.MembershipPaymentTransactionModel;
import com.project.model.his.Membership;
import com.project.model.his.MembershipConfiguration;
import com.project.model.his.MembershipPaymentTransaction;

public interface IMembershipDAO {

	boolean createNewMember(Membership obj) throws Exception;

	boolean updateMember(Membership obj) throws Exception;

	List<Membership> fetchActiveMembershipList(int branchId) throws Exception;
	
	List<Membership> fetchMembershipList(int branchId) throws Exception;

	Membership fetchSelectedMember(int memberId) throws Exception;

	Boolean createPaymentTransaction(MembershipPaymentTransaction obj) throws Exception;

	Boolean updateConfiguration(MembershipConfiguration configData) throws Exception;

	MembershipConfiguration fetchConfiguration(int branchId) throws Exception;

	boolean memberIsExist(int branchId, String identificationNumber) throws Exception;

	MembershipPaymentTransaction fetchSelectedPaymentTransaction(int transactionId) throws Exception;

	List<MembershipPaymentTransaction> fetchAllPaymentTransaction(int branchId) throws Exception;

	boolean deleteSelectedPaymentTransaction(int transactionId) throws Exception;

	boolean updateSelectedPaymentTransaction(MembershipPaymentTransaction membershipPayment) throws Exception;

	boolean adjustSelectedMembershipExpiredDate(int parseInt, Date newDate) throws Exception;


}
