package com.project.bo.util;

import java.text.*;
import java.math.BigDecimal;

/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */
public class DecimalUtil
{

    public DecimalUtil()
    {
    }

    public static String normalizeNumber(String s, String s1)
        throws ParseException
    {
        s1 = s1.trim();
        DecimalFormat decimalformat = new DecimalFormat();
        decimalformat.applyPattern(s);
        Number number = decimalformat.parse(s1);
        decimalformat.applyPattern("#0.00;-#0.00");
        StringBuffer stringbuffer = new StringBuffer("");
        stringbuffer = decimalformat.format(number, stringbuffer, new FieldPosition(0));
        return stringbuffer.toString();
    }

    public static String formatDecimal(String s)
        throws Exception
    {
        DecimalFormat decimalformat = new DecimalFormat();
        decimalformat.applyPattern("#0.00;-#0.00");
        Number number = decimalformat.parse(s);
        decimalformat.applyPattern("#,##0.00;-#,##0.00");
        String s1 = decimalformat.format(number);
        return s1;
    }
    
    public static String formatNumber(String s) throws Exception{
    	DecimalFormat decimalformat = new DecimalFormat();
        decimalformat.applyPattern("00;-00");
        Number number = decimalformat.parse(s);
        decimalformat.applyPattern("00;-00");
        String s1 = decimalformat.format(number);
        return s1;
    }
    
    public static String formatDecimal(String s, String s1)
        throws Exception
    {
        DecimalFormat decimalformat = new DecimalFormat();
        decimalformat.applyPattern("#0.00;-#0.00");
        Number number = decimalformat.parse(s);
        decimalformat.applyPattern(s1);
        String s2 = decimalformat.format(number);
        return s2;
    }

    public static String toString(int i)
    {
        return String.valueOf(i);
    }

    public static String toString(long l)
    {
        return String.valueOf(l);
    }

  

    public static boolean equalsTo(int i, int j)
    {
        return i == j;
    }

    public static boolean greaterThan(int i, int j)
    {
        return i > j;
    }

    public static boolean lessThan(int i, int j)
    {
        return i < j;
    }


    public static boolean equalsTo(double d, double d1)
    {
        return d == d1;
    }

    public static boolean greaterThan(double d, double d1)
    {
        return d > d1;
    }

    public static boolean lessThan(double d, double d1)
    {
        return d < d1;
    }

    public static int add(int i, int j)
    {
        return i + j;
    }

    public static double add(double d, double d1)
    {
        return d + d1;
    }

    public static double add(Double double1, Double double2)
    {
        return double1.doubleValue() + double2.doubleValue();
    }

    public static int subtract(int i, int j)
    {
        return i - j;
    }

    public static double subtract(double d, double d1)
    {
        return d - d1;
    }

    public static double subtract(Double double1, Double double2)
    {
        return double1.doubleValue() - double2.doubleValue();
    }

    public static String format(long l, int i)
    {
        String s = "" + l;
        if(s.length() > i)
            throw new NumberFormatException("Original number length is longer than specified length, number=" + l + ",len=" + i);
        StringBuffer stringbuffer = new StringBuffer();
        int j = i - s.length();
        for(int k = 0; k < j; k++)
            stringbuffer.append('0');

        stringbuffer.append(s);
        return stringbuffer.toString();
    }

    public static String restoreDecimal(String s, int i, boolean flag)
        throws ParseException
    {
        if(s.length() <= i)
            throw new ParseException("Number string is too short, must be longer than fraction. str=" + s + ",fraction=" + i, 0);
        if(i < 0)
            throw new ParseException("fraction cannot be negative:" + i, 0);
        char ac[] = s.toCharArray();
        for(int j = 0; j < ac.length; j++)
            if(!Character.isDigit(ac[j]))
                throw new ParseException("String includes non-digit char:" + s, j);

        String s1 = s.substring(0, s.length() - i);
        String s2 = s.substring(s.length() - i, s.length());
        if(!flag)
            return s2.length() <= 0 ? s1 : s1 + "." + s2;
        StringBuffer stringbuffer = new StringBuffer();
        char ac1[] = s1.toCharArray();
        for(int k = 0; k < ac1.length; k++)
        {
            if(ac1[k] == '0')
                continue;
            stringbuffer.append(s1.substring(k, s1.length()));
            break;
        }

        if(stringbuffer.length() == 0)
            stringbuffer.append('0');
        if(s2.length() > 0)
        {
            stringbuffer.append('.');
            stringbuffer.append(s2);
        }
        return stringbuffer.toString();
    }

    public static BigDecimal add(BigDecimal bigdecimal, String s)
    {
        BigDecimal bigdecimal1 = new BigDecimal(s);
        return bigdecimal.add(bigdecimal1);
    }

    public static boolean greaterThan(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.compareTo(bigdecimal1) > 0;
    }

    public static boolean equalsTo(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.compareTo(bigdecimal1) == 0;
    }

    public static boolean lessThan(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.compareTo(bigdecimal1) < 0;
    }
    
    public static boolean greaterThanEqual(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        return bigdecimal.compareTo(bigdecimal1) >= 0;
    }
    
    

    public static String absolute(String s)
    {
        if(s.startsWith("-"))
            return s.substring(1);
        else
            return s;
    }

    public static boolean isNegative(String s)
    {
        return s.startsWith("-");
    }

    public static boolean isZero(String s)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        return bigdecimal.compareTo(new BigDecimal("0")) == 0;
    }

    public static boolean isPositive(String s)
    {
        return !isNegative(s);
    }

    public static String add(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        BigDecimal bigdecimal2 = bigdecimal.add(bigdecimal1);
        return bigdecimal2.toString();
    }

    public static String addCredit(String s, String s1)
    {
        BigDecimal bigdecimal = null;
        BigDecimal bigdecimal1 = new BigDecimal(s);
        if(s1.indexOf("-") != -1)
        {
            BigDecimal bigdecimal2 = new BigDecimal(s1);
            bigdecimal2 = bigdecimal2.abs();
            bigdecimal = bigdecimal1.add(bigdecimal2);
        } else
        {
            bigdecimal = bigdecimal1;
        }
        return bigdecimal.toString();
    }

    public static String addDebit(String s, String s1)
    {
        BigDecimal bigdecimal = null;
        BigDecimal bigdecimal1 = new BigDecimal(s);
        if(s1.indexOf("-") != -1)
        {
            bigdecimal = bigdecimal1;
        } else
        {
            BigDecimal bigdecimal2 = new BigDecimal(s1);
            bigdecimal2 = bigdecimal2.abs();
            bigdecimal = bigdecimal1.add(bigdecimal2);
        }
        return bigdecimal.toString();
    }

    public static String add(String s, String s1, int i)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        BigDecimal bigdecimal2 = bigdecimal.add(bigdecimal1);
        bigdecimal2 = bigdecimal2.setScale(i);
        return bigdecimal2.toString();
    }

    public static String subtract(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        BigDecimal bigdecimal2 = bigdecimal.subtract(bigdecimal1);
        return bigdecimal2.toString();
    }

    public static String multiply(String s, String s1)
    {
        BigDecimal bigdecimal = new BigDecimal(s);
        BigDecimal bigdecimal1 = new BigDecimal(s1);
        BigDecimal bigdecimal2 = bigdecimal.multiply(bigdecimal1);
        return bigdecimal2.toString();
    }

 

    public static String toPrecision(String s, int i)
    {
        if(s == null || s.length() == 0)
            return s;
        if(i < 0)
            throw new IllegalArgumentException("DecimalUtil.toPrecision: precision cannot be negative");
        int j = s.indexOf('.');
        if(j < 0 || s.length() - j - 1 != i)
        {
            BigDecimal bigdecimal = new BigDecimal(s);
            bigdecimal = bigdecimal.setScale(i, 4);
            return bigdecimal.toString();
        } else
        {
            return s;
        }
    }

    public static String toAmount(String s)
    {
        if(s == null || s.length() == 0)
            return s;
        else
            return toPrecision(s, 2);
    }
    
    public static String formatDecimal(double number)
    throws Exception
    {
    DecimalFormat decimalformat = new DecimalFormat();
    decimalformat.applyPattern("#0.00;-#0.00");
    String s1 = decimalformat.format(number);
    return s1;
	}

    public static String formatDecimal(int number)
    throws Exception
    {
    DecimalFormat decimalformat = new DecimalFormat();
    decimalformat.applyPattern("#0.00;-#0.00");
    String s1 = decimalformat.format(number);
    return s1;
    }
    
public static String formatDecimalNoSeprator(String s)
    throws Exception
{
    DecimalFormat decimalformat = new DecimalFormat();
    decimalformat.applyPattern("#0.00;-#0.00");
    Number number = decimalformat.parse(s);
    decimalformat.applyPattern("#0.00;-#0.00");
    String s1 = decimalformat.format(number);
    return s1;
}



public static BigDecimal formatRoundupCents(BigDecimal amount){
    double rounded = ((double) (long) (amount.doubleValue() * 20 + 0.5)) / 20;
    BigDecimal result=new BigDecimal(0.00);
	result =  new BigDecimal(""+rounded);	
    return result;
}



}