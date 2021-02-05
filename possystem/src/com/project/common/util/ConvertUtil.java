package com.project.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.project.common.validation.ValidatorUtil;

/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class ConvertUtil {

	public static BigDecimal toBigDecimal(int value){
		return new BigDecimal(value);
	}
	
	public static BigDecimal toBigDecimal(long value){
		return new BigDecimal(value);
	}
	
	public static BigDecimal toBigDecimal(String value){
		if(value!=null){
			value = removeCommaCashFormat(value);
		return new BigDecimal(value);
		}
		else{
			return new BigDecimal(0);
		}
	}
	
	public static BigInteger toBigInteger(String value){
		return new BigInteger(value);
	}
	
	public static int toInt(BigDecimal value){
		if(value != null)
			return value.intValue();
		else
			return 0;
	}
	
	public static int toInt(BigInteger value){
		if(value != null)
			return value.intValue();
		else
			return 0;
	}
	
	public static Integer toInt(Long value){
		if(value != null)
			return value.intValue();
		else
			return 0;
	}
	
	public static long toLog(BigDecimal value){
		if(value != null)
			return value.longValue();
		else
			return 0;
	}
	
	public static String toDecimal(double value){
		return new DecimalFormat("0.00").format(value);
	}
	
	public static Double toDecimalDouble(double value){
		return Double.parseDouble(new DecimalFormat("0.00").format(value));
	}
	
	public static BigDecimal toBigDecimal(double value){
		return new BigDecimal(value);
	}
	
	public static double toDouble(BigDecimal value){
		if(value != null)
			return value.doubleValue();
		else
			return 0.0;
	}
	
	public static double toDouble(BigInteger value){
		if(value != null)
			return value.doubleValue();
		else
			return 0.0;
	}
	
	public static String toString(long value){
		return String.valueOf(value);
	}
	
	public static String toString(int value){
		return String.valueOf(value);
	}
	
	public static String toString(double value){
		return String.valueOf(value);
	}
	
	public static String toString(BigDecimal value){
		return String.valueOf(value);
	}
	
	public static String toString(BigInteger value){
		return String.valueOf(value);
	}
	
	public static long toLong(String value){
		if(ValidatorUtil.isNotNull(value))
		{
			return Long.parseLong(value);
		}else{
			return 0;
		}
	}
	
	public static int toInt(String value){
		if(ValidatorUtil.isNotNull(value))
		{
			return Integer.parseInt(value);
		}else{
			return 0;
		}
		
	}
	
	public static int toStringToInt(String value ){
		if(ValidatorUtil.isNotNull(value)){
			return toInt(toBigDecimal(value));
		}else {
			return 0;
		}
	}
	
	public static Double toDouble(String value){
		if(ValidatorUtil.isNotNull(value))
		{
			return Double.parseDouble(value);
		}else{
			return 0.0;
		}
	}
	
	public static String toCashFormat(String value){
		NumberFormat formatter =  new DecimalFormat("#,###,###.00");
		double amount = Double.parseDouble(value);
		return formatter.format(amount);
	}
	
	public static String removeCommaCashFormat(String value){
		double convertValue = 0;
		//double amount = Double.parseDouble(convertValue);
		try {
			NumberFormat formatter = new DecimalFormat("#,###,###.00");
			convertValue = formatter.parse(value).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return String.valueOf(convertValue);
	}
	
}