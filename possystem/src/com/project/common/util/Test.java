package com.project.common.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.MaskFormatter;

public class Test {

	public static BigDecimal roundBigDecimal(final BigDecimal input){
	    return input.round( new MathContext(input.toBigInteger().toString().length(),RoundingMode.HALF_UP
	        )
	    );
	    
	    
	}
	
	
	public static void main(String[] args) {	
		    
	    double amount1 = 990.48;
	    double rounded = ((double) (long) (amount1 * 20 + 0.5)) / 20;
	    System.out.println(new BigDecimal(rounded));
	    
	   
	    
	}

}
