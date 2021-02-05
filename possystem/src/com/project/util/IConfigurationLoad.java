package com.project.util;

/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public interface IConfigurationLoad {

	/**
	 * The application properties file contains a version identifier with this
	 * property name. We check this version id against a version id in the
	 * web.xml file at runtime to make certain that the ear file and the
	 * configuration file both come from the same build. It also provides us
	 * with version information for tracking versions that are currently running
	 * on the system.
	 */
	public static final String USERNAME_LENGTH_MIN = "username.length.min";
	public static final String PASSWORD_LENGTH_MIN = "password.length.min";

	public static final String COMMON_IDTYPE_RESIDENT_VALUE = "common.idtype.resident.value";
	public static final String COMMON_IDTYPE_ARMYPOLICE_VALUE = "common.idtype.armypolice.value";
	public static final String COMMON_IDTYPE_FOREIGNER_VALUE = "common.idtype.foreigner.value";

	public static final String COMMON_GENDER_MALE_VALUE = "common.gender.male.value";
	public static final String COMMON_GENDER_FEMALE_VALUE = "common.gender.female.value";

	public static final String COMMON_RACE_MALAY_VALUE = "common.race.malay.value";
	public static final String COMMON_RACE_CHINESE_VALUE = "common.race.chinese.value";
	public static final String COMMON_RACE_INDIAN_VALUE = "common.race.indian.value";
	public static final String COMMON_RACE_OTHER_VALUE = "common.race.other.value";

	public static final String NRIC_LENGTH = "nric.length";
	public static final String OLDIC_MIN_LENGTH = "oldic.minlength";
	public static final String OLDIC_MAX_LENGTH = "oldic.maxlength";
	public static final String ARMYNO_MINLENGTH = "armyno.minlength";
	public static final String ARMYNO_MAXLENGTH = "armyno.maxlength";
	public static final String PASSPORT_MINLENGTH = "passport.minlength";
	public static final String PASSPORT_MAXLENGTH = "passport.maxlength";
	public static final String VOUCHERCODE_MINLENGTH = "vouchercode.minlength";
	public static final String VOUCHERITMES_MINLENGTH = "voucheritem.minlength";
	public static final String POSTCODE_LENGTH = "postcode.length";
	public static final String CONTACT_NO_MIN_LENGTH = "contact.no.minlength";
	public static final String AGELIMIT_APPLY_MEMBERSHIP = "agelimit.apply.membership";

	public static final String BIRTH_PLACE_MALAYSIA = "birth.place.new.identification.malaysia";
	public static final String BIRTH_PLACE_OUTSIDE_MALAYSIA = "birth.place.new.identification.outside.malaysia";

	public static final String PAYMENT_TYPE_MASTER_CARD = "Master Card";
	public static final String PAYMENT_TYPE_VISA = "Visa";

	public static final String PAYMENT_CARD_MONTH = "payment.card.month";
	public static final String PAYMENT_CREDIT_DEBIT_CHARGE_CARD_NO_LENGTH = "paymenttype.card.length";
	public static final String CREDITCARD_MASTERS_START = "creditcard.master.starts";
	public static final String CREDITCARD_VISA_START = "creditcard.visa.starts";

	// search Id
	public static final String SEARCH_ID_REGISTRATION = "search.id.registrationno";
	public static final String SEARCH_ID_NEWICNO = "search.id.newicno";
	public static final String SEARCH_ID_OLDICNO = "search.id.oldicno";
	public static final String SEARCH_ID_ARMYPOLICENO = "search.id.armypoliceno";
	public static final String SEARCH_ID_PASSPORTNO = "search.id.passportno";
	

	public static final String PROJECT_HOME_FILENAME = "project.home.filename";
	public static final String PROJECT_THEMENAME = "project.themename";
	public static final String PROJECT_DEFAULT_PASSWORD = "project.default.password";
	public static final String PROJECT_TYPE_BRANCH = "project.type.branch";
	public static final String PROJECT_TYPE_HQ = "project.type.hq";
	public static final String PROJECT_TYPE_BRANCH_VALUE = "project.type.branch.value";
	public static final String PROJECT_TYPE_HQ_VALUE = "project.type.hq.value";
	public static final String PROJECT_INSTANCE_NAME= "project.instance.name";
	public static final String PROJECT_INSTANCE_SERVER= "project.instance.server";
	public static final String PROJECT_PRINTER_NAME= "project.printer.name";
	public static final String PROJECT_OUTSTANDING_INVOICE_VALUE= "project.outstanding.invoice.value";
	public static final String PROJECT_DEFAULT_CATEGORY = "project.default.category";
	public static final String PRODUCT_TRANSFER_SENT = "project.transfer.sent";
	public static final String PRODUCT_TRANSFER_REQUEST = "project.transfer.request";
	public static final String PRODUCT_START_YEAR = "project.start.year";
	public static final String PRODUCT_GST_AMOUNT = "project.gst.amount";
	public static final String PRODUCT_DEFAULTGST_AMOUNT = "project.defaultgst.amount";

	public static final String PRODUCT_GST_NUMBER = "project.gst.number";
	public static final String PRODUCT_REG_NUMBER = "project.reg.number";

	
	
	public static final String supplier_documentupload_location_temp = "supplier.documentupload.location.temp";
	public static final String supplier_documentupload_location = "supplier.documentupload.location";

	public static final String PURCHASEREQUEST_STATUS_NEWORDER_VALUE = "purchaserequest.status.newordervalue";
	public static final String PURCHASEREQUEST_STATUS_NEWORDER_LABLE = "purchaserequest.status.neworderlable";
	public static final String PURCHASEREQUEST_STATUS_PROCESSED_VALUE = "purchaserequest.status.processedvalue";
	public static final String PURCHASEREQUEST_STATUS_PROCESSED_LABLE = "purchaserequest.status.processedlable";
	public static final String PURCHASEREQUEST_STATUS_ORDERED_VALUE = "purchaserequest.status.orderedvalue";
	public static final String PURCHASEREQUEST_STATUS_ORDERED_LABLE = "purchaserequest.status.orderedlable";
	
	public static final String PURCHASEREQUEST_BRANCHSTATUS_NEWORDER_VALUE = "purchaserequest.branchstatus.newordervalue";
	public static final String PURCHASEREQUEST_BRANCHSTATUS_NEWORDER_LABLE = "purchaserequest.branchstatus.neworderlable";
	public static final String PURCHASEREQUEST_BRANCHSTATUS_PROCESSED_VALUE = "purchaserequest.branchstatus.processedvalue";
	public static final String PURCHASEREQUEST_BRANCHSTATUS_PROCESSED_LABLE = "purchaserequest.branchstatus.processedlable";
	public static final String PURCHASEREQUEST_BRANCHSTATUS_ORDERED_VALUE = "purchaserequest.branchstatus.orderedvalue";
	public static final String PURCHASEREQUEST_BRANCHSTATUS_ORDERED_LABLE = "purchaserequest.branchstatus.orderedlable";
	
	
	
	public static final String PURCHASEORDER_STATUS_NEWORDER_VALUE = "purchaseorder.status.newordervalue";
	public static final String PURCHASEORDER_STATUS_NEWORDER_LABLE = "purchaseorder.status.neworderlable";
	public static final String PURCHASEORDER_STATUS_PROCESSED_VALUE = "purchaseorder.status.processedvalue";
	public static final String PURCHASEORDER_STATUS_PROCESSED_LABLE = "purchaseorder.status.processedlable";
	public static final String PURCHASEORDER_STATUS_ORDERED_VALUE = "purchaseorder.status.orderedvalue";
	public static final String PURCHASEORDER_STATUS_ORDERED_LABLE = "purchaseorder.status.orderedlable";
	public static final String PURCHASEORDER_STATUS_CLOSED_VALUE = "purchaseorder.status.closedvalue";
	public static final String PURCHASEORDER_STATUS_CLOSED_LABLE = "purchaseorder.status.closedlable";
	
	
	public static final String QUOTATION_STATUS_NEWORDER_VALUE = "quotationstat.status.newordervalue";
	public static final String QUOTATION_STATUS_NEWORDER_LABLE = "quotationstat.status.neworderlable";
	public static final String QUOTATION_STATUS_PROCESSED_VALUE = "quotationstat.status.processedvalue";
	public static final String QUOTATION_STATUS_PROCESSED_LABLE = "quotationstat.status.processedlable";
	public static final String QUOTATION_STATUS_ORDERED_VALUE = "quotationstat.status.orderedvalue";
	public static final String QUOTATION_STATUS_ORDERED_LABLE = "quotationstat.status.orderedlable";
	public static final String QUOTATION_STATUS_DECLINED_VALUE = "quotationstat.status.declinevalue";
	public static final String QUOTATION_STATUS_DECLINED_LABLE = "quotationstat.status.declinelable";
	
	
	public static final String DELIVERYORDER_STATUS_NEWORDER_VALUE = "deliveryorder.status.newordervalue";
	public static final String DELIVERYORDER_STATUS_NEWORDER_LABLE = "deliveryorder.status.neworderlable";
	public static final String DELIVERYORDER_STATUS_PROCESSED_VALUE = "deliveryorder.status.processedvalue";
	public static final String DELIVERYORDER_STATUS_PROCESSED_LABLE = "deliveryorder.status.processedlable";
	public static final String DELIVERYORDER_STATUS_ORDERED_VALUE = "deliveryorder.status.orderedvalue";
	public static final String DELIVERYORDER_STATUS_ORDERED_LABLE = "deliveryorder.status.orderedlable";
	
	
	public static final String SALESORDER_STATUS_NEWORDER_VALUE = "salesorder.status.newordervalue";
	public static final String SALESORDER_STATUS_NEWORDER_LABLE = "salesorder.status.neworderlable";
	public static final String SALESORDER_STATUS_PROCESSED_VALUE = "salesorder.status.processedvalue";
	public static final String SALESORDER_STATUS_PROCESSED_LABLE = "salesorder.status.processedlable";
	public static final String SALESORDER_STATUS_ORDERED_VALUE = "salesorder.status.orderedvalue";
	public static final String SALESORDER_STATUS_ORDERED_LABLE = "salesorder.status.orderedlable";
	public static final String SALESORDER_STATUS_CLOSED_VALUE = "salesorder.status.paidvalue";
	public static final String SALESORDER_STATUS_CLOSED_LABLE = "salesorder.status.paidlable";
	
	
	public static final String INVOICE_STATUS_NEWORDER_VALUE = "invoice.status.newordervalue";
	public static final String INVOICE_STATUS_NEWORDER_LABLE = "invoice.status.neworderlable";
	public static final String INVOICE_STATUS_PROCESSED_VALUE = "invoice.status.processedvalue";
	public static final String INVOICE_STATUS_PROCESSED_LABLE = "invoice.status.processedlable";
	public static final String INVOICE_STATUS_ORDERED_VALUE = "invoice.status.orderedvalue";
	public static final String INVOICE_STATUS_ORDERED_LABLE = "invoice.status.orderedlable";
	
	public static final String COLLECTION_STATUS_NEWORDER_VALUE = "collection.status.newordervalue";
	public static final String COLLECTION_STATUS_NEWORDER_LABLE = "collection.status.neworderlable";
	public static final String COLLECTION_STATUS_APPROVED_VALUE = "collection.status.approvedvalue";
	public static final String COLLECTION_STATUS_APPROVED_LABLE = "collection.status.approvedlable";
	public static final String COLLECTION_STATUS_APPORTIONED_VALUE = "collection.status.apportionedvalue";
	public static final String COLLECTION_STATUS_APPORTIONED_LABLE = "collection.status.apportionedlable";
	public static final String COLLECTION_STATUS_SETTLED_VALUE = "collection.status.settledvalue";
	public static final String COLLECTION_STATUS_SETTLED_LABLE = "collection.status.settledlable";
	public static final String COLLECTION_STATUS_CLOSED_VALUE = "collection.status.closedvalue";
	public static final String COLLECTION_STATUS_CLOSED_LABLE = "collection.status.closedlable";
	
	
	public static final String RETURN_STATUS_NEWORDER_VALUE = "return.status.newordervalue";
	public static final String RETURN_STATUS_NEWORDER_LABLE = "return.status.neworderlable";
	public static final String RETURN_STATUS_PROCESSED_VALUE = "return.status.processedvalue";
	public static final String RETURN_STATUS_PROCESSED_LABLE = "return.status.processedlable";
	public static final String RETURN_STATUS_ORDERED_VALUE = "return.status.orderedvalue";
	public static final String RETURN_STATUS_ORDERED_LABLE = "return.status.orderedlable";
	
	
	public static final String DOCTOR_STATUS_NEWORDER_VALUE = "doctor.status.newordervalue";
	public static final String DOCTOR_STATUS_NEWORDER_LABLE = "doctor.status.neworderlable";
	public static final String DOCTOR_STATUS_PROCESSED_VALUE = "doctor.status.processedvalue";
	public static final String DOCTOR_STATUS_PROCESSED_LABLE = "doctor.status.processedlable";
	public static final String DOCTOR_STATUS_ORDERED_VALUE = "doctor.status.orderedvalue";
	public static final String DOCTOR_STATUS_ORDERED_LABLE = "doctor.status.orderedlable";
	public static final String DOCTOR_STATUS_CLOSED_VALUE = "doctor.status.closedvalue";
	public static final String DOCTOR_STATUS_CLOSED_LABLE = "doctor.status.closedlable";
	
	
	public static final String TRANSFER_STATUS_NEWORDER_VALUE = "transfer.status.newordervalue";
	public static final String TRANSFER_STATUS_NEWORDER_LABLE = "transfer.status.neworderlable";
	public static final String TRANSFER_STATUS_PROCESSED_VALUE = "transfer.status.processedvalue";
	public static final String TRANSFER_STATUS_PROCESSED_LABLE = "transfer.status.processedlable";
	public static final String TRANSFER_STATUS_ACCEPT_VALUE = "transfer.status.acceptordervalue";
	public static final String TRANSFER_STATUS_ACCEPT_LABLE = "transfer.status.acceptorderlable";
	public static final String TRANSFER_STATUS_REJECT_VALUE = "transfer.status.rejectvalue";
	public static final String TRANSFER_STATUS_REJECT_LABLE = "transfer.status.rejectlable";	
	public static final String TRANSFER_STATUS_ORDERED_VALUE = "transfer.status.orderedvalue";
	public static final String TRANSFER_STATUS_ORDERED_LABLE = "transfer.status.orderedlable";
	public static final String TRANSFER_STATUS_CLOSED_VALUE = "transfer.status.paidvalue";
	public static final String TRANSFER_STATUS_CLOSED_LABLE = "transfer.status.paidlable";
	
	
	
	public static final String COMPANY_ADDRESS1="company.address1";
	public static final String COMPANY_ADDRESS2="company.address2";
	public static final String COMPANY_PHONENO1="company.phone1";
	public static final String COMPANY_PHONENO2="company.phone2";
	public static final String COMPANY_EMAIL="company.email";
	public static final String COMPANY_WEBSITE="company.website";
	public static final String COMPANY_POSTCODE="company.postcode";
	
	public static final String hostname= "hostname";
	public static final String port= "port";
	public static final String user = "user";
	public static final String password = "password";
	public static final String db = "db";
	
	
}