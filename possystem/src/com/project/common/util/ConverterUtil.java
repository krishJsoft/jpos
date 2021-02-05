/**
 * 
 */
package com.project.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;


/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */
public class ConverterUtil {


	/**
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * @since 02 MAY 2011
	 * @param    value   the value to be represented by 
	 *  				 {@code Double} object is the amount. Cannot be null
	 * @param roundingMode @see RoundingMode
	 * <p> default format and pattern "#########0.00" </p>
	 * 
	 * @return return a formated Double amount
	 *	
	 */			
	public static Double RinggitCutter(Double value, RoundingMode roundingMode){
		return converterDoubleCutter(value, 0.00, "#########0.00", roundingMode);
	}
	
	/**
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * @since 02 MAY 2011
	 * @param    value   the value to be represented by 
	 *  				 {@code String} object is the amount. Cannot be null
	 * @param roundingMode @see RoundingMode
	 * <p> default format and pattern "#########0.00" 
	 * 
	 * </p>
	 * 
	 * @return return a formated Double amount
	 *	
	 */
	public static Double RinggitCutter(String value, RoundingMode roundingMode){
		return converterDoubleCutter(value, "0.00", "#########0.00", roundingMode);
	}
	
	/**
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * @since 02 MAY 2011
	 * @param    value   the value to be represented by 
	 *  				 {@code Double} object is the amount. Cannot be null
	 * <p> default format and pattern "#########0.00" </p>
	 * 
	 * @return return a formated Double amount
	 *	
	 */			
	public static Double Ringgit(Double value){
		return converterDouble(value, 0.00, "#########0.00");
	}

	/**
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * @since 02 MAY 2011
	 * @param    value   the value to be represented by 
	 *  				 {@code String} object is the amount. Cannot be null
	 * <p> default format and pattern "#########0.00" </p>
	 * 
	 * @return return a formated Double amount
	 *	
	 */
	public static Double Ringgit(String value){
		return converterDouble(value, 0.00, "#########0.00");
	}

	
	public static String RinggitFormat(String value){

		return formatDouble(value, "0.00",  "#,###,###,##0.00");
	}	


	public static String RinggitFormat(Double value){

		return formatDouble(value, "0.00",  "#,###,###,##0.00");

	}


	public static Double converterDouble(Double value){
		Double defaultValue = 0.00;
		return converterDouble(value, defaultValue, "#########0.00");
	}

	public static Double converterDouble(String value){
		Double defaultValue = 0.00;
		return converterDouble(value, defaultValue, "#########0.00");

	}


	public static Double converterDouble(Double value, Double defaultValue){

		return converterDouble(value, defaultValue, "#########0.00");
	}

	public static Double converterDouble(String value, Double defaultValue){

		return converterDouble(value, defaultValue, "#########0.00");

	}


	public static Double converterDouble(Double value, Double defaultValue, String format){

		if(value == null){
			return defaultValue;
		}

		try {
			DecimalFormat df= new DecimalFormat(format);   
			String stringValue = df.format(value);  
			return Double.parseDouble(stringValue);

		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static Double converterDouble(String value, Double defaultValue, String format){

		Double convertDouble=0.00;
		
		if(value == null){
			return defaultValue;
		}

		try {
			DecimalFormat df= new DecimalFormat(format);  

			convertDouble = Double.parseDouble(value);
			String stringValue = df.format(convertDouble);  
			convertDouble = Double.parseDouble(stringValue);
			return convertDouble;

		} catch (Exception e) {
			return convertDouble;
		}

	}

	public static String formatDouble(Double value){

		return formatDouble(value, "#,###,###,##0.00");


	}

	public static String formatDouble(String value){
		return formatDouble(value, "#,###,###,##0.00");


	}	

	/**
	 *@author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Convert a <code>String</code> to an <code>String</code>, returning
	 * <code>defaultValue</code> if the conversion fails.</p>
	 *
	 * <p>If the String is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the String to convert, cannot be null
	 * @param defaultValue  if the conversion fails
	 * @param format the default pattern and symbols
	 * @return the String represented by the string, or <code>defaultValue</code> if
	 * conversion fails
	 * @since Rover 2.0
	 */
	public static String formatDouble(String value, String defaultValue, String format){

		if(value == null){
			return defaultValue;
		}

		try {
			DecimalFormat df = new DecimalFormat(format);   
			//df.setRoundingMode(roundingMode);
			Double convertDouble = Double.parseDouble(value);
			return  df.format(convertDouble);  

		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static String formatDouble(String value, String format){

		return formatDouble(value, "0.00",  "#,###,###,##0.00");
	}

	public static String formatDoubleWithoutComma(String value){

		return formatDouble(value, "0.00",  "#0.00"); 
	}

	/**
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Convert a <code>Double</code> to an <code>String</code>, returning
	 * <code>defaultValue</code> if the conversion fails.</p>
	 *
	 * <p>If the Double is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Double to convert, cannot be null
	 * @param defaultValue  if the conversion fails
	 * @param format the default pattern and symbols
	 * @return the Double represented by the string, or <code>defaultValue</code> if
	 * conversion fails
	 * @since Rover 2.0
	 */
	public static String formatDouble(Double value, String defaultValue, String format){

		if(value == null){
			return defaultValue;
		}

		try {
			DecimalFormat df = new DecimalFormat(format);   
			//df.setRoundingMode(roundingMode);

			return df.format(value);  

		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static String formatDouble(Double value, String format){

		return formatDouble(value, "0.00",  "#,###,###,##0.00");

	}
	
	/**
	 * <p><strong>public static Object defaultIfEmpty(Object value, Object defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Object</code> for a null or NaN, returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Object is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Object check for null
	 * @param defaultValue  if the value null
	 * @return the Object represented by the class, or <code>defaultValue</code> if
	 * value  null
	 * @since Rover 2.0
	 */
	public static Object defaultIfEmpty(Object value, Object defaultValue){
		
	
		if(value == null){
			return defaultValue;
		}
		
		return value;
		
	}
	
	/**
	 * <p><strong>public static Double defaultIfEmpty(Double value, Double defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Double</code> for a null or NaN, returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Double is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Double check for null or NaN
	 * @param defaultValue  if the value null or NaN
	 * @return the Double represented by the String, or <code>defaultValue</code> if
	 * value  null or NaN
	 * @since Rover 2.0
	 */
	public static Double defaultIfEmpty(String value, Double defaultValue){
		
	
		if(value == null){
			return defaultValue;
		}
		
		return converterDouble(value);
		
	}
	
	
	
	/**
	 * <p><strong>public static Double defaultIfEmpty(Double value, Double defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Double</code> for a null or NaN, returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Double is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Double check for null or NaN
	 * @param defaultValue  if the value null or NaN
	 * @return the Double represented by the Double, or <code>defaultValue</code> if
	 * value  null or NaN
	 * @since Rover 2.0
	 */	
	public static Double defaultIfEmpty(Double value, Double defaultValue){
		
		
		if(value == null){
			return defaultValue;
		}
		
		if(value.isNaN()){
			return defaultValue;
		}
			
		return value;
		
	}
	
	
	/**
	 * <p><strong>public static BigDecimal defaultIfEmpty(BigDecimal value, BigDecimal defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>BigDecimal</code> for a null or NaN, returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the BigDecimal is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the BigDecimal check for null or NaN
	 * @param defaultValue  if the value null or NaN
	 * @return the BigDecimal represented by the BigDecimal, or <code>defaultValue</code> if
	 * value  null
	 * @since Rover 2.0
	 */	
	public static BigDecimal defaultIfEmpty(BigDecimal value, BigDecimal defaultValue){
		
		if(value == null){
			return defaultValue;
		}
			
		return value;
		
	}
	
	/**
	 * <p><strong>public static Long defaultIfEmpty(Long value, Long defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Long</code> for a null , returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Long is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Long check for null
	 * @param defaultValue  if the value null or NaN
	 * @return the Long represented by the Long, or <code>defaultValue</code> if
	 * value  null
	 * @since Rover 2.0
	 */	
	public static Long defaultIfEmpty(Long value, Long defaultValue){
		
		if(value == null){
			return defaultValue;
		}
		
		return value;
		
	}
	
	/**
	 * <p><strong>public static Integer defaultIfEmpty(Integer value, Integer defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Integer</code> for a null , returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Integer is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Integer check for null
	 * @param defaultValue  if the value null
	 * @return the Integer represented by the Integer, or <code>defaultValue</code> if
	 * value  null
	 * @since Rover 2.0
	 */	
	public static Integer defaultIfEmpty(Integer value, Integer defaultValue){
		
		if(value == null){
			return defaultValue;
		}
		
		return value;
		
	}
	
	/**
	 * <p><strong>public static Date defaultIfEmpty(Date value, Date defaultValue)</strong></p>
	 * @author <a HREF="mailto:azri.bin.mohd.ali@gmail.com">Azri Mohd Ali</a>
	 * <p>Checking the <code>Date</code> for a null or NaN, returning
	 * <code>defaultValue</code> if the value null.</p>
	 *
	 * <p>If the Date is <code>null</code>, <code>defaultValue</code> is returned.</p>
	 *
	 * @param value the Date check for null
	 * @param defaultValue  if the value null
	 * @return the Date represented by the Date, or <code>defaultValue</code> if
	 * value  null
	 * @since Rover 2.0
	 */	
	public static Date defaultIfEmpty(Date value, Date defaultValue){
		
		if(value == null){
			return defaultValue;
		}
			
		return value;
		
	}
	
	
	public static Double converterDoubleCutter(Double value, Double defaultValue, String format, RoundingMode roundingMode){

		if(value == null){
			return defaultValue;
		}

		try {
			DecimalFormat df= new DecimalFormat(format);   
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			df.setRoundingMode(roundingMode);
			String stringValue = df.format(value);  
			return Double.parseDouble(stringValue);

		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static Double converterDoubleCutter(String value, String defaultValue, String format, RoundingMode roundingMode){
		Double convertDouble = 0.00;

		if(value == null){
			return convertDouble;
		}

		try {
			DecimalFormat df= new DecimalFormat(format);  
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			df.setRoundingMode(roundingMode);
			convertDouble = Double.parseDouble(value);
			String stringValue = df.format(convertDouble);  
			convertDouble = Double.parseDouble(stringValue);
			return convertDouble;

		} catch (Exception e) {
			return convertDouble;
		}

	}
	
	
	
}
