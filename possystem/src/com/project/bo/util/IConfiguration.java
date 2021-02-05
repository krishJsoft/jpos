package com.project.bo.util;

/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public interface IConfiguration {

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


	
	public static final String supplier_documentupload_location_temp = "supplier.documentupload.location.temp";
	public static final String supplier_documentupload_location = "supplier.documentupload.location";

	public static final String PURCHASEREQUEST_STATUS_NEWORDER_VALUE = "purchaserequest.status.newordervalue";
	public static final String PURCHASEREQUEST_STATUS_NEWORDER_LABLE = "purchaserequest.status.neworderlable";
	public static final String PURCHASEREQUEST_STATUS_PROCESSED_VALUE = "purchaserequest.status.processedvalue";
	public static final String PURCHASEREQUEST_STATUS_PROCESSED_LABLE = "purchaserequest.status.processedlable";
	public static final String PURCHASEREQUEST_STATUS_ORDERED_VALUE = "purchaserequest.status.orderedvalue";
	public static final String PURCHASEREQUEST_STATUS_ORDERED_LABLE = "purchaserequest.status.orderedlable";
	
	public static final String PURCHASEORDER_STATUS_NEWORDER_VALUE = "purchaseorder.status.newordervalue";
	public static final String PURCHASEORDER_STATUS_NEWORDER_LABLE = "purchaseorder.status.neworderlable";
	public static final String PURCHASEORDER_STATUS_PROCESSED_VALUE = "purchaseorder.status.processedvalue";
	public static final String PURCHASEORDER_STATUS_PROCESSED_LABLE = "purchaseorder.status.processedlable";
	public static final String PURCHASEORDER_STATUS_ORDERED_VALUE = "purchaseorder.status.orderedvalue";
	public static final String PURCHASEORDER_STATUS_ORDERED_LABLE = "purchaseorder.status.orderedlable";
	
	
	public static final String QUOTATION_STATUS_NEWORDER_VALUE = "quotationstat.status.newordervalue";
	public static final String QUOTATION_STATUS_NEWORDER_LABLE = "quotationstat.status.neworderlable";
	public static final String QUOTATION_STATUS_PROCESSED_VALUE = "quotationstat.status.processedvalue";
	public static final String QUOTATION_STATUS_PROCESSED_LABLE = "quotationstat.status.processedlable";
	public static final String QUOTATION_STATUS_ORDERED_VALUE = "quotationstat.status.orderedvalue";
	public static final String QUOTATION_STATUS_ORDERED_LABLE = "quotationstat.status.orderedlable";
	
	
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
	
	
	public static final String INVOICE_STATUS_NEWORDER_VALUE = "invoice.status.newordervalue";
	public static final String INVOICE_STATUS_NEWORDER_LABLE = "invoice.status.neworderlable";
	public static final String INVOICE_STATUS_PROCESSED_VALUE = "invoice.status.processedvalue";
	public static final String INVOICE_STATUS_PROCESSED_LABLE = "invoice.status.processedlable";
	public static final String INVOICE_STATUS_ORDERED_VALUE = "invoice.status.orderedvalue";
	public static final String INVOICE_STATUS_ORDERED_LABLE = "invoice.status.orderedlable";
	
	
	public static final String COMPANY_ADDRESS1="company.address1";
	public static final String COMPANY_ADDRESS2="company.address2";
	public static final String COMPANY_PHONENO1="company.phone1";
	public static final String COMPANY_PHONENO2="company.phone2";
	public static final String COMPANY_EMAIL="company.email";
	public static final String COMPANY_WEBSITE="company.website";
	public static final String COMPANY_POSTCODE="company.postcode";
	
}