package com.project.common.util;

import java.io.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.lang.Math;

public class fingerprintMinutieaExtraction {
	int []x1;
	int []x2;
	int[]y1;
	int []y2;
	double []theta1;
	double [] theta2;
	double [] theta3;
	double [] theta4;
	int n=0;
	public BufferedImage bimg1,bimg2;
	int [][]pixels1;
	int [][]pixels2;
	int i,j,p;
    int l,m,n1,o=0;
    public double [] d1;
    public double [] d2;
    public double []d3;
    public double []d4;
    public double []d5;
    public double []d6;
    public fingerprintMinutieaExtraction(BufferedImage image1) 
    	{
    		bimg1 = image1;
    	/* This method is used to extract the minutiae from the binary image with the block of 3x3.*/
    	for (i=0;i<=bimg1.getWidth();i+=3)
    	{
    		for (j=0;j<=bimg1.getHeight();j+=3)
    		{
    			// Calculate Termination Or bifurcation Points On Image of the Fingerprint.
    			n=((pixels1[i][j]+pixels1[i-1][j-1]+pixels1[i+1][j+1])
    				+(pixels1[i][j-1]+pixels1[i][j+1])
    					+(pixels1[i-1][j]+pixels1[i+1][j])
    						+(pixels1[i+1][j+1]+pixels1[i-1][j+1]));
    		
    		// Condition to check about termination and bifurcation points.
    		if (n==2)
    		{
    		  x1[l]=i;
    		  y1[m]=j;
    		  l+=1;
    		  m+=1;	
    		}
    		if (n==4)
    		{
    			x2[n1]=i;
    			y2[o]=j;
    			n1=n1+1;
    			o=o+1;
    			
    		}
    	}// End of height parameter J co-ordinate 
    	   	
    }// End of Width parameter I co-ordinate.
    
    /*****************
    CALCULATE DISTANCES FROM THE CENTRAL POINT OF THE IMAGE TO THE MINUTIES.
    */
    int xc,yc;
    xc = bimg1.getWidth();
    xc=xc/2;
    yc= bimg1.getHeight();
    yc=yc/2;
    for (i=0;i<=l;i++)
    {
    	d1[i] = (xc- x1[i]);
    	d2[i] = (yc-y1[i]);
    	d3[i] = Math.sqrt((d1[i]*d1[i])+(d2[i]*d2[i]));
    	d4[i] = (xc-x2[i]);
    	d5[i] = (yc-y2[i]);
    	d6[i]= Math.sqrt((d4[i]*d4[i])+(d5[i]*d5[i]));
    } 
}

}