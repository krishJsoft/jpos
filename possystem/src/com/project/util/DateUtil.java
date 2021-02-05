package com.project.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import com.project.model.datamodel.ProductModel;


/**
 * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 */
public class DateUtil {
	/** Default simple date format as 'dd/MM/yyyy' */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	public static final String MONTH_DATE_FORMAT = "dd/MMM/yyyy";
	
	public static final String DEFAULT_DATE_FORMAT_KENEN = "dd-MM-yyyy";

	public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	/** Default simple date format as 'dd/MM/yyyy hh:mm:ss a' */
	public static final String DEFAULT_DATETIME_FORMAT = "dd/MM/yyyy hh:mm:ss a";

	// declared for date util
	public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

	// DB date format
	public static final String DATABASE_DATE_FORMAT = "dd-MMM-yy";

	public static final String DEFAULT_TIME_FORMAT = "hh:mm:ss a";

	// Zaini - 20110608
	public static final String STANDARD_DATE_FORMAT_TIME = "dd-MM-yyyy HH:mm:ss";

	// Zaini - 20110510 - iccs date
	public static final String DEFAULT_DATETIME_FORMAT_ICCS = "yyyyMMddHHmmss";

	private static Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * Parse the date string into a java.util.Date object based on the default
	 * date format <code>dd/MM/yyyy</code>
	 * 
	 * @param dateStr
	 *            takes a string representing the date
	 * @return Date returns a java.util.Date
	 */
	public static java.util.Date toUtilDate(String dateStr) {
		java.util.Date date = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

		try {
			date = sdFormat.parse(dateStr);
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}

		return date;
	}

	/**
	 * Parse the date string into a java.util.Date object based on the passing
	 * date format
	 * 
	 * @param dateStr
	 *            takes a string representing the date
	 * @param format
	 *            takes a string representing the date format
	 * @return Date returns a java.util.Date
	 */
	public static java.util.Date toUtilDate(String dateStr, String format) {
		java.util.Date date = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat(format);
		Date today = new Date(System.currentTimeMillis());

		try {
			date = sdFormat.parse(dateStr);

			if (format != null && format.indexOf("yyyy") < 0) {
				if (date.after(today)) {
					sdFormat = new SimpleDateFormat("yyyyMMdd");
					String newdate = sdFormat.format(date).replaceFirst("20",
							"19");
					date = sdFormat.parse(newdate);
				}
			}
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}

		return date;
	}

	/**
	 * Convert the java.sql.Timestamp into java.util.Date
	 * 
	 * @param sTimestamp
	 *            takes a java.sql.Timestamp to be converted into java.util.Date
	 * @return java.util.Date returns a converted java.util.Date
	 */
	public static java.util.Date toUtilDate(java.sql.Timestamp sTimestamp) {
		return new java.util.Date(sTimestamp.getTime());
	}

	/**
	 * Convert the java.sql.Date into java.util.Date
	 * 
	 * @param sDate
	 *            takes a java.sql.Date to be converted into java.util.Date
	 * @return java.util.Date returns a converted java.util.Date
	 */
	public static java.util.Date toUtilDate(java.sql.Date sDate) {
		return new java.util.Date(sDate.getTime());
	}

	/**
	 * Format the java.util.Date object into a string based on the default date
	 * format <code>dd/MM/yyyy</code>
	 * 
	 * @param uDate
	 *            takes a java.util.Date object
	 * @return String returns a formatted string representing the date
	 */
	public static String toString(java.util.Date uDate) {
		String dateStr = null;

		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(
					DEFAULT_DATE_FORMAT);
			dateStr = sdFormat.format(uDate);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = null;
		}

		return dateStr;
	}
	
	
	
	public static String toStringMonth(java.util.Date uDate) {
		String dateStr = null;

		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(
					MONTH_DATE_FORMAT);
			dateStr = sdFormat.format(uDate);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = null;
		}

		return dateStr;
	}

	

	/**
	 * Format the java.util.Date object into a string based on the passing date
	 * format
	 * 
	 * @param uDate
	 *            takes a java.util.Date object
	 * @param format
	 *            takes a string representing the date format
	 * @return String returns a formatted string representing the date
	 */
	public static String toString(java.util.Date uDate, String format) {
		String dateStr = null;

		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(format);
			dateStr = sdFormat.format(uDate);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = null;
		}

		return dateStr;
	}

	/**
	 * Parse the date string into a java.sql.Date object based on the default
	 * date format <code>dd/MM/yyyy</code>
	 * 
	 * @param dateStr
	 *            takes a string representing the date
	 * @return Date returns a java.sql.Date
	 */
	public static java.sql.Date toSqlDate(String dateStr) {

		if (dateStr == null)
			return null;

		java.util.Date date = toUtilDate(dateStr);
		return new java.sql.Date(date.getTime());
	}

	/**
	 * Parse the date string into a java.sql.Date object based on the passing
	 * date format
	 * 
	 * @param dateStr
	 *            takes a string representing the date
	 * @param format
	 *            takes a string representing the date format
	 * @return Date returns a java.sql.Date
	 */
	public static java.sql.Date toSqlDate(String dateStr, String format) {
		java.util.Date date = toUtilDate(dateStr, format);

		return new java.sql.Date(date.getTime());
	}

	/**
	 * Convert the java.util.Date into java.sql.Date
	 * 
	 * @param uDate
	 *            takes a java.util.Date to be converted into java.sql.Date
	 * @return java.sql.Date returns a converted java.sql.Date
	 */
	public static java.sql.Date toSqlDate(java.util.Date uDate) {
		return new java.sql.Date(uDate.getTime());
	}

	/**
	 * Convert the java.util.Date into java.sql.Timestamp
	 * 
	 * @param uDate
	 *            takes a java.util.Date to be converted into java.sql.Date
	 * @return java.sql.Timestamp returns a converted java.sql.Timestamp
	 */
	public static java.sql.Timestamp toSqlTimestamp(java.util.Date uDate) {
		return new java.sql.Timestamp(uDate.getTime());
	}

	/**
	 * Format the java.sql.Date object into a string based on the default date
	 * format <code>dd/MM/yyyy</code>
	 * 
	 * @param sDate
	 *            takes a java.sql.Date object
	 * @return String returns a formatted string representing the date
	 */
	public static String toString(java.sql.Date sDate) {
		String dateStr = toString(toUtilDate(sDate));

		return dateStr;
	}

	/**
	 * Format the java.sql.Date object into a string based on the passing date
	 * format
	 * 
	 * @param sDate
	 *            takes a java.sql.Date object
	 * @param format
	 *            takes a string representing the date format
	 * @return String returns a formatted string representing the date
	 */
	public static String toString(java.sql.Date sDate, String format) {
		String dateStr = toString(toUtilDate(sDate), format);

		return dateStr;
	}

	/**
	 * Parse the date string into a java.util.Date object based on the default
	 * date format <code>dd/MM/yyyy hh:mm:ss a</code>
	 * 
	 * @param dateStr
	 *            takes a string representing the date
	 * @return Date returns a java.util.Date in DateTime format
	 */
	public static java.util.Date toUtilDateTime(String dateStr) {
		java.util.Date date = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat(
				DEFAULT_DATETIME_FORMAT);

		try {
			date = sdFormat.parse(dateStr);
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}

		return date;
	}

	/**
	 * Convert the java.sql.Date into java.util.Date
	 * 
	 * @param sDate
	 *            takes a java.sql.Date to be converted into java.util.Date
	 * @return java.util.Date returns a converted java.util.Date
	 */
	public static java.util.Date toUtilDateFromSqlDate(java.sql.Date sDate) {
		return new java.util.Date(sDate.getTime());
	}

	/*
	 * To get the time
	 */
	public static String getTimeHHMM(long minutes) {
		StringBuffer sb = new StringBuffer();
		try {
			long hrs = minutes / 60;
			long mins = (minutes - hrs * 60);
			if (hrs < 10) {
				sb.append('0');
			}
			sb.append(hrs);
			sb.append(':');
			mins = mins * 60 / 100;
			if (mins < 10) {
				sb.append('0');
			} else if (mins >= 59) {
				mins = 59;
			}
			sb.append(mins);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param dateStr1
	 *            --
	 * @param dateStr2
	 *            -- the date string to compare
	 * @return <code>boolean</code> -- returns the status true or false
	 * @author: siva
	 * @date: 31-03-2010
	 */
	public static boolean isDate1GreaterThanDate2(String dateStr1,
			String dateStr2) {
		boolean status = false;
		Date date1 = null;
		Date date2 = null;

		try {
			date1 = toUtilDate(dateStr1, DATE_TIME_FORMAT);
			date2 = toUtilDate(dateStr2, DATE_TIME_FORMAT);
			// if given date 1 is greater than given date 2 then status is true
			if (date1.after(date2)) {
				status = true;
			} else {
				// else status is false
				status = false;
			}
		} catch (Exception e) {
			log.error("@isDate1GreaterThanDate2(): "
					+ "Error while comparing date 1 with date 2.", e);
		}
		return status;
	}

	public static boolean isDate1GreaterThanDate2(Date date1, Date date2) {
		boolean status = false;
		try {
			// if given date 1 is greater than given date 2 then status is true
			if (date1.after(date2)) {
				status = true;
			} else {
				// else status is false
				status = false;
			}
		} catch (Exception e) {
			log.error("@isDate1GreaterThanDate2(): "
					+ "Error while comparing date 1 with date 2.", e);
		}
		return status;
	}

	/**
	 * 
	 * @param date
	 *            -- siva 31-03-2010 the date to compare
	 * @return <code>boolean</code> -- returns the status true or false
	 */
	public static boolean isGreaterThanToday(Date date) {
		boolean status = false;
		try {
			// if given date is greater than today date then status is true

			Date todayDate = getTodayDateTime();
			log.info(" date : " + date + " today : " + todayDate);
			if (date.after(todayDate)) {
				status = true;
			} else {
				// else status is false
				status = false;
			}
		} catch (Exception e) {
			log.error("@isGreaterThanToday(): "
					+ "Error while comparing date with today date.", e);
		}
		return status;
	}
	
	
	public static boolean isGreaterThanToday(String date) {
		boolean status = false;
		try {
			// if given date is greater than today date then status is true

			Date date1 = null;
			date1 = toUtilDate(date);
			Date todayDate = getTodayDateTime();
			log.info(" date : " + date + " today : " + todayDate);
			if (date1.after(todayDate)) {
				status = true;
			} else {
				// else status is false
				status = false;
			}
		} catch (Exception e) {
			log.error("@isGreaterThanToday(): "
					+ "Error while comparing date with today date.", e);
		}
		return status;
	}
	
	

	/**
	 * This method get the today date from the gregorian calander object. and
	 * returns the date object.
	 * 
	 * @return today date siva 31-03-2010
	 */
	public static Date getTodayDateTime() {
		Date today = null;
		SimpleDateFormat sourceFormat = null;
		Calendar cal = null;
		String dateStr = null;
		try {
			cal = GregorianCalendar.getInstance();

			sourceFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

			// get the formated date as string
			dateStr = cal.get(Calendar.DAY_OF_MONTH) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR) + " "
					+ cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);

			today = sourceFormat.parse(dateStr);

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getTodayDateTime(): Error while ", e);
			}
		} finally {
			sourceFormat = null;
			cal = null;
			dateStr = null;
		}
		return today;
	}// end of method getTodayDateTime()
	
	
	
	
	
	public static Date getFromTodayDateTime() {
		Date today = null;
		SimpleDateFormat sourceFormat = null;
		Calendar cal = null;
		String dateStr = null;
		String hours="00";
		String min="00";
		String sce="00";
		try {
			cal = GregorianCalendar.getInstance();

			sourceFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

			// get the formated date as string
			dateStr = cal.get(Calendar.DAY_OF_MONTH) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR) + " "
					+ hours + ":"
					+ min + ":" + sce;

			today = sourceFormat.parse(dateStr);

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getTodayDateTime(): Error while ", e);
			}
		} finally {
			sourceFormat = null;
			cal = null;
			dateStr = null;
		}
		return today;
	}// end of method getTodayDateTime()
	
	
	public static Date getFromDate(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date getToTodayDateTime() {
		Date today = null;
		SimpleDateFormat sourceFormat = null;
		Calendar cal = null;
		String dateStr = null;
		String hours="23";
		String min="59";
		String sce="59";
		try {
			cal = GregorianCalendar.getInstance();

			sourceFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

			// get the formated date as string
			dateStr = cal.get(Calendar.DAY_OF_MONTH) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR) + " "
					+ hours + ":"
					+ min + ":" + sce;

			today = sourceFormat.parse(dateStr);

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getTodayDateTime(): Error while ", e);
			}
		} finally {
			sourceFormat = null;
			cal = null;
			dateStr = null;
		}
		return today;
	}// end of method getTodayDateTime()
	
	

	public static Date getTodayDate() {
		Date today = null;
		SimpleDateFormat sourceFormat = null;
		Calendar cal = null;
		String dateStr = null;
		try {
			cal = GregorianCalendar.getInstance();

			sourceFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

			// get the formated date as string
			dateStr = cal.get(Calendar.DAY_OF_MONTH) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR) + " ";

			today = sourceFormat.parse(dateStr);

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getTodayDateTime(): Error while ", e);
			}
		} finally {
			sourceFormat = null;
			cal = null;
			dateStr = null;
		}
		return today;
	}

	public static long difference(Date date1, Date date2) {
		long difference;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		long time1MilliSeconds = 0;
		long time2MilliSeconds = 0;
		if (date1 != null)
			time1MilliSeconds = cal1.getTimeInMillis();
		if (date2 != null)
			time2MilliSeconds = cal2.getTimeInMillis();
		difference = (time2MilliSeconds - time1MilliSeconds)
				/ (24 * 60 * 60 * 1000);
		return difference;
	}

	public Date CalendarToDate(Calendar cal) {
		Date date = cal.getTime();
		return date;

	}

	public static String timeDifference(Date date1, Date date2)
			throws Exception {
		String value;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		long time1MilliSeconds = 0;
		long time2MilliSeconds = 0;
		if (date1 != null)
			time1MilliSeconds = cal1.getTimeInMillis();
		if (date2 != null)
			time2MilliSeconds = cal2.getTimeInMillis();
		long difference = (time2MilliSeconds - time1MilliSeconds) / 1000;
		value = DecimalUtil.formatNumber(Long.toString(difference / 60)) + ":"
				+ DecimalUtil.formatNumber(Long.toString(difference % 60));
		return value;
	}

	public static Date toUtilDate(XMLGregorianCalendar xmlGregorianCalendar) {
		Date date = null;
		String strDate = null;
		strDate = xmlGregorianCalendar.getDay() + "/"
				+ xmlGregorianCalendar.getMonth() + "/"
				+ xmlGregorianCalendar.getYear() + " "
				+ xmlGregorianCalendar.getHour() + ":"
				+ xmlGregorianCalendar.getMinute() + ":"
				+ xmlGregorianCalendar.getSecond();
		date = DateUtil.toUtilDate(strDate, DEFAULT_DATE_TIME_FORMAT);
		return date;
	}

	public static XMLGregorianCalendar toXMLGregorialCalendar(Date date) {
		XMLGregorianCalendar xmlGregorianCalendar = null;
		GregorianCalendar gc = null;
		try {
			if (Utility.isNotNull(date)) {
				gc = new GregorianCalendar();
				gc.setTime(date);
				xmlGregorianCalendar = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(gc);
			}
		} catch (Exception e) {
			log.info("Error @toXMLGregorialCalendar()...", e);
		} finally {
			gc = null;
		}
		return xmlGregorianCalendar;
	}

	/**
	 * Returns a string reprensation of a Date object
	 * 
	 * @param date
	 * @param format
	 * @return The date in string from formatted according to format
	 * @throws Exception
	 *             If the date is null or the format is invalid
	 */
	public static String formatDate(Date date, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * Returns a string reprensation of a Date object
	 * 
	 * @param cal
	 * @param format
	 * @return The date in string from formatted according to format
	 * @throws Exception
	 *             If the date is null or the format is invalid
	 */
	public static String formatDate(Calendar cal, String format)
			throws Exception {
		if (cal == null) {
			return null;
		}

		return formatDate(cal.getTime(), format);
	}

	public static Date findPreviousDate(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}

	public static java.util.Date toDBDate(String dateStr) {
		java.util.Date date = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat(DATABASE_DATE_FORMAT);

		try {
			date = sdFormat.parse(dateStr);
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}

		return date;
	}

	// Zaini - 20110810
	public static Date xmlGregorianToDate(
			XMLGregorianCalendar xmlGregorianCalendar, String targetDateFormat) {
		Date date = null;
		String strDate = null;
		strDate = xmlGregorianCalendar.getDay() + "/"
				+ xmlGregorianCalendar.getMonth() + "/"
				+ xmlGregorianCalendar.getYear() + " "
				+ xmlGregorianCalendar.getHour() + ":"
				+ xmlGregorianCalendar.getMinute() + ":"
				+ xmlGregorianCalendar.getSecond();
		date = DateUtil.toUtilDateTime(strDate, targetDateFormat);
		return date;
	}

	public static java.util.Date toUtilDateTime(String dateStr, String format) {
		java.util.Date date = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat(format);
		Date today = new Date(System.currentTimeMillis());

		try {
			date = sdFormat.parse(dateStr);

			if (format != null && format.indexOf("yyyy") < 0) {
				if (date.after(today)) {
					sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					String newdate = sdFormat.format(date).replaceFirst("20",
							"19");
					date = sdFormat.parse(newdate);
				}
			}
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}

		return date;
	}

	/*
	 * Created By : Mathi Created On : 09 Nov 2012 Description : Checks whether
	 * the string parameter is in default date format.
	 */
	public static boolean isDate(String theDate) {
		return isDate(theDate, DEFAULT_DATE_FORMAT);
	}

	public static boolean isDate(String theDate, String format) {
		boolean isValid = true;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		simpleDateFormat.setLenient(false);
		try {
			simpleDateFormat.parse(theDate);
		} catch (ParseException pe) {
			log.error("Exception @isDate .. ", pe);
			isValid = false;
		}
		return isValid;
	}

	public static Date addMonthsToCurrentDate(int month) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, month);
		return toUtilDate(now.get(Calendar.DATE) + "/"
				+ (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR));
	}

	public static Date addDaysToDate(int days , Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DATE, days);
		return toUtilDate(now.get(Calendar.DATE) + "/"
				+ (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR));
	}
	public static Date addYearToCurrentDate(int Year) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, Year);
		return toUtilDate(now.get(Calendar.DATE) + "/"
				+ (now.get(Calendar.MONTH)) + "/" + now.get(Calendar.YEAR) + 1);
	}

	public static Date getFirstDate() {
		Calendar cal = Calendar.getInstance();
		Calendar fromDate = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		fromDate.set(year, month, 1);
		Date fromDate1 = fromDate.getTime();

		return fromDate1;

	}
	
	
	public static String getCurrentDate() {
		Date today = null;
		SimpleDateFormat sourceFormat = null;
		Calendar cal = null;
		String dateStr = null;
		try {
			cal = GregorianCalendar.getInstance();

			sourceFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			// get the formated date as string
			dateStr =""+cal.get(Calendar.DAY_OF_MONTH)+ cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+(cal.get(Calendar.HOUR))+(cal.get(Calendar.MINUTE))+(cal.get(Calendar.SECOND));

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("getTodayDateTime(): Error while ", e);
			}
		} finally {
			sourceFormat = null;
			cal = null;
			
		}
		return dateStr;
	}
	
	
	
	public static String getShortMonth(Date dt)
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(dt);
		
		DateTime dtObj = new DateTime(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), 0, 0,0,0);

		return dtObj.monthOfYear().getAsText();
	}
	
	public static int getYear(Date dt)
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(dt);
		
		return c.get(Calendar.YEAR);

	}
	
	
	public static Date getFirstDayYear() {
	
		Calendar cal = Calendar.getInstance();		
		int year = cal.get(Calendar.YEAR);
		
		cal.set(year, Calendar.JANUARY, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date getFirstDayYear(int year) {
		
		Calendar cal = Calendar.getInstance();			
		cal.set(year, Calendar.JANUARY, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	
	public static Date getEndDayYear() {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		cal.set(year, Calendar.DECEMBER, 31);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	
	
	public static Date getEndDayYear(int year) {
		
		Calendar cal = Calendar.getInstance();	
		
		cal.set(year, Calendar.DECEMBER, 31);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}


	public static String getMonthForInt(int m) {
	    String month = "invalid";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (m >= 0 && m <= 11 ) {
	        month = months[m];
	    }
	    return month;
	}


	public static Date getStartDayMonth(Integer year , Integer month) {
		
		Calendar cal = Calendar.getInstance();    
		cal.set(year,month-1,1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	
	
	public static Date getEndDayMonth(Integer year , Integer month) {
		
		Calendar cal = Calendar.getInstance();	
		
		//cal.set(year,month-1,1);
		cal.set(Calendar.YEAR  , year);
		cal.set(Calendar.MONTH, month-1);
		//cal.set(Calendar.DATE, 1);
		
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}
	

	public List<String> calMonths()
	{
		List<String> monthlist = new ArrayList<String>();
		
		return monthlist;
	}
	
}
