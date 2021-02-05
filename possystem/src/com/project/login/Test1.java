package com.project.login;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Test1 {

	public BigDecimal checkdata(BigDecimal amount)
	{
		double rounded = (double)(long)(amount.doubleValue() * 2 + 0.5) / 2;
		//System.out.println("Result " + rounded);
		double ans = java.lang.Math.round((amount.doubleValue() / 0.5)) * 0.5;
		//System.out.println("ans " + ans);
		 rounded = ((double) (long) (amount.doubleValue() * 20 + 0.5)) / 20;
		 BigDecimal result=new BigDecimal(0.00);
		 result =  new BigDecimal(""+rounded);		 
		  
		  return result;
	}
	public static void main(String[] args) {
		
		Test1 t = new Test1();	
		BigDecimal result=new BigDecimal(0.00);
		BigDecimal result1=new BigDecimal(0.00);
		for(double a=2.00;a<3.00;a=a+0.01)
		{			
			result=t.checkdata(new BigDecimal(a));
			double a1=result.doubleValue();
			result=new BigDecimal(a1).round(new MathContext(3, RoundingMode.HALF_UP));
			result1=new BigDecimal(a).round(new MathContext(3, RoundingMode.HALF_UP));
			System.out.println(""+result1+" --> " +result);
		}

	}

}
