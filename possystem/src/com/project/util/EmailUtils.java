/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.util;

import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchstaffmemberModel;

/**
 *
 * @author Buzznet
 */
public class EmailUtils {

    private static String emailusername = "AKIAIQDY6OVXH62AZVUA";
    private static String emailpassword = "AtYdCcFMkQUGcfgbCjiN3kOdhgjfr0c4ZqewdD9v3cnt";
    private static int emailport = 465;
    private static String emailhost = "email-smtp.us-west-2.amazonaws.com";
    private static boolean enablessl = true;
    
//    private static String emailusername = "noreply@ask4key.com";
//    private static String emailpassword = "Ask4reply";
//    private static int emailport = 465;
//    private static String emailhost = "smtp.gmail.com";
//    private static boolean enablessl = true;

    public static void sendActiationEmail(String username, String password, String activationurl) {
        Emailer emailer = new Emailer(emailhost, emailport, emailusername, emailpassword, enablessl);
        
        String welcometemplate = " Welcome to Ask4Expense! \n\n  Hi " + username +"\n";        
        String activationtemplate = " Your Activation url is " + activationurl;
        String passwordtemplate = "Your login details are :  UserName:  " + username + " Password :  " + password;      
        String endtemplate = "Ask4Expense Team  ";   
        
        String message = " <b> <div style=\"color:#33AFDE;\">Dear Mr/Miss <b><font><color=black;>&nbsp;&nbsp;&nbsp; "
				+ username
				+ " <br>"
				+ " <br>"
				+ "</font></div> \n"
				+ "  <div style=\"color:#33AFDE;\">Welcome to Ask4Expense! <font><color=black;>"
				
				+ " </font>  Your Activation url is. <br> </div> "+ activationurl+"<br><br> \n"
				+ " <br>"
				
				+ "  <div style=\"color:#33AFDE;\">Your login details are : <br><br> UserName:  " + username + "  <br> Password:  " + password + " \n <br><br><br><br> Best Regards <br> <br><font><color=black;>"
				+ endtemplate
				+ " <br>"				
				+ " <br>"				
				+ " </font></div> </b> ";               

        String fromuser="noreply@ask4key.com";
        
        emailer.sendEmail(fromuser, username, null, null, "Ask4Bill - Activation Link", message);
    }

    public static void sendResetPasswordEmail(String username, String password) {
        Emailer emailer = new Emailer(emailhost, emailport, emailusername, emailpassword, enablessl);
        String resetpasswordtemplate = " Your new password for " + username + " is " + password;
       
        String passwordtemplate = "Your login details are :  UserName:  " + username + " Password :  " + password;      
        String endtemplate = "Ask4Expense Team  "; 
        
        String message = " <b> <div style=\"color:#33AFDE;\">Dear Mr/Miss <font><color=black;>&nbsp;&nbsp;&nbsp; "
				+ username
				+ " <br>"
				+ " <br>"
				+ "</font></div> \n"
				+ "   <div style=\"color:#33AFDE;\">You have requested a password reset for Ask4Expense ! <font><color=black;>"
				
				+ " <br><br>"
				+ " <br>"
				
				+ "<div style=\"color:#33AFDE;\">Your login details are : <br><br> UserName:  " + username + "  <br> Password:  " + password + " \n <br><br><br><br><br> Best Regards <br> <br><font><color=black;>"
				+ endtemplate
				+ " <br>"				
				+ " <br>"				
				+ "</font></div></b>";               

        String fromuser="noreply@ask4key.com";
        
        emailer.sendEmail(fromuser, username, null, null, "Ask4Bill - Forgot Password", message);
    }
    
    

    
    
    
    public static void dailyreportEmail(List<String> attachments) {
      	 Emailer emailer = new Emailer(emailhost, emailport, emailusername, emailpassword, enablessl);        
            String endtemplate = "Team  "; 
            
            String username="gopalabe@gmail.com";
            
            String message = " <b> <div style=\"color:#33AFDE;\">Dear Mr/Miss <font><color=black;>&nbsp;&nbsp;&nbsp; "
    				+ username
    				+ " <br>"
    				+ " <br>"
    				+ "</font></div> \n"
    				+ "   <div style=\"color:#33AFDE;\">Your Daily Report  ! <font><color=black;>" 				
    				
    				+ " <br><br>"
    				+ " <br>"    				
    				+ endtemplate
    				+ " <br>"				
    				+ " <br>"				
    				+ "</font></div></b>";               

            String fromuser="noreply@ask4key.com";
           // String ccs=staff.getReportingto();
            emailer.sendattachEmail(fromuser, username, null, null, "Daily Report " +new Date(), message, attachments, true);
      }
    
    
    
    public static void dailyadminreportEmail(List<String> attachments) {
     	 Emailer emailer = new Emailer(emailhost, emailport, emailusername, emailpassword, enablessl);        
           String endtemplate = "Team  "; 
           
           String username="gopalabe@gmail.com";
           
           String message = " <b> <div style=\"color:#33AFDE;\">Dear Mr/Miss <font><color=black;>&nbsp;&nbsp;&nbsp; "
   				+ username
   				+ " <br>"
   				+ " <br>"
   				+ "</font></div> \n"
   				+ "   <div style=\"color:#33AFDE;\">Your Daily Report  ! <font><color=black;>" 				
   				
   				+ " <br><br>"
   				+ " <br>"    				
   				+ endtemplate
   				+ " <br>"				
   				+ " <br>"				
   				+ "</font></div></b>";               

           String fromuser="noreply@ask4key.com";
          // String ccs=staff.getReportingto();
           emailer.sendattachEmail(fromuser, username, null, null, "Daily Admin Report " +new Date(), message, attachments, true);
     }
   
    
    
    
    
    
    
    
    public static void expensemonthreportEmail(BranchstaffmemberModel staff , List<String> attachments) {
     	 Emailer emailer = new Emailer(emailhost, emailport, emailusername, emailpassword, enablessl);        
           String endtemplate = "Ask4Expense Team  "; 
           
           String username=staff.getEmailAddress();
           
           String message = " <b> <div style=\"color:#33AFDE;\">Dear Mr/Miss <font><color=black;>&nbsp;&nbsp;&nbsp; "
   				+ username
   				+ " <br>"
   				+ " <br>"
   				+ "</font></div> \n"
   				+ "   <div style=\"color:#33AFDE;\">Your Expense Week Report  ! <font><color=black;>" 				
   				
   				+ " <br><br>"
   				+ " <br>"    				
   				+ endtemplate
   				+ " <br>"				
   				+ " <br>"				
   				+ "</font></div></b>";               

           String fromuser="noreply@ask4key.com";
          // String ccs=staff.getReportingto();
           emailer.sendattachEmail(fromuser, username, null, null, "Ask4Bill - Month Report ", message, attachments, true);
     }

}
