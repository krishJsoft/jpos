package com.project.common.validation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.ErrorFactory;
import com.project.common.factory.MessageFactory;
import com.project.common.util.DateUtil;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class ValidatorBassUtil {

	static MessageFactory messageFactory = MessageFactory.getInstance();
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	public static boolean isNull(String value) {
		return (value == null || value == "") ? true : false;
	}

	
	public static boolean isZero(Object obj) {
		return (obj == null || obj == "" || obj.equals(null)  || obj.equals("") || obj.equals("0") || obj.equals("0.0") || obj.equals("0.00")) ? true : false;
	}
	
	public static boolean isZeroDecimal(BigDecimal obj) {
		return (obj == null || obj.doubleValue()==0) ? true : false;
	}

	public static boolean isNotNull(String value) {
		return (value != null && value != "" && value.length() > 0) ? true
				: false;
	}

	public static boolean isNull(Object obj) {
		return (obj == null || obj == "" || obj.equals(null)  || obj.equals("")) ? true : false;
	}

	public static boolean isNotNull(Object obj) {
		return (obj != null) ? true : false;
	}

	public static boolean isNotNull(List<Object> list) {
		return (list != null && list.size() > 0) ? true : false;
	}

	public static boolean isValidAmountPattern(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[0-9\\,\\.\\+\\-]+");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidAlphaText(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[a-zA-Z ]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidAlphaNumeric(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[a-zA-Z0-9]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidAlphaNumericWithSpace(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[a-zA-Z0-9\\s]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidArmyPoliceChar(String value) {
		boolean status = true;

		Pattern p = Pattern.compile("[GIT]*");
		Matcher m = p.matcher(value);
		status = m.matches();

		return status;
	}

	public static boolean isValidAlphaNumericBr(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[a-zA-Z0-9-]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidEmail(String email) {
		boolean status = true;
		if (isNotNull(email.trim())) {

			Pattern p = Pattern
					.compile("([a-zA-Z0-9_\\-\\.]+)@((\\[a-z]{1,3}\\.[a-z]{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})");
			Matcher m = p.matcher(email);
			status = m.matches();
		}
		return status;
	}

	public static boolean isValidName(String value) {
		boolean status;
		Pattern p = Pattern.compile("[a-zA-Z@/. ]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidRefName(String value) {
		boolean status;
		Pattern p = Pattern.compile("[a-zA-Z@/.\\-\\' ]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidDecimalNumber(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[0-9.]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean isValidNumber(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}
	
	public static boolean isValidDouble(String value) {
		boolean status = true;
		try {
			new Double(value);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
	
	// Project validation ..
	public static boolean isValidNricAddYear(String nric) {
		String strDobYYMMdd = nric.substring(0, 6);
		return DateUtil.isDate(strDobYYMMdd, "yyMMdd");
	}
	
	public static boolean OldICFormater(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("([a-zA-Z][0-9]{7})|([0-9]{7})");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean OldICNumberOnlyFormater(String value) {
		boolean status = true;
		// Pattern p = Pattern.compile("([a-zA-Z][0-9]{7})|([0-9]{7})");
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}

	public static boolean OldICAlphaNumericFormater(String value) {
		boolean status = true;
		Pattern p = Pattern.compile("[a-zA-Z][0-9]*");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}
	
	public static boolean isValidNricBirthPlace(String idNumber) {

		boolean isValid = true;
		Configuration config = Configuration.getConfiguration();

		String malaysiaBirthPlace = config
				.getValue(IConfiguration.BIRTH_PLACE_MALAYSIA);
		String outsideMalaysiaBirthPlace = config
				.getValue(IConfiguration.BIRTH_PLACE_OUTSIDE_MALAYSIA);

		String[] birthPlaceMalaysia = malaysiaBirthPlace.split(",");
		String[] birthPlaceOutsideMalaysia = outsideMalaysiaBirthPlace
				.split(",");

		String birthPlace = idNumber.substring(6, 8);

		isValid = ArrayUtils.contains(birthPlaceMalaysia, birthPlace)
				|| ArrayUtils.contains(birthPlaceOutsideMalaysia, birthPlace);
		return isValid;
	}
	
	// Army police id formatter..
	public static boolean ArmyPoliceIDFormater(String value){
		boolean status = true;
		Pattern p = Pattern.compile("(([a-zA-Z][a-zA-Z0-9])|([0-9][0-9]))[0-9]{1,8}");
		Matcher m = p.matcher(value);
		status = m.matches();
		return status;
	}
	
	public static int calculateAge(Date dob) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dob);
		Calendar now = new GregorianCalendar();
		int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		cal.add(Calendar.YEAR, res);
		if (now.getTime().before(cal.getTime())) {
			res--;
		}
		return res;
	}

}
