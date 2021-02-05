package com.project.bo.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.common.config.Configuration;
import com.project.model.datamodel.purchase.PurchaseorderModel;


public class EmailProcess {
	
	public static Logger log = LoggerFactory.getLogger(EmailProcess.class);
	
	private String fromAddressUName = Configuration.getConfiguration().getValue("common.email.fromAddress.username");
	private String fromAddressPassword = Configuration.getConfiguration().getValue("common.email.fromAddress.password");

	
	    private static String HOST = "smtp.gmail.com";
	    private static String USER = "gerrykegan82ppsb@gmail.com";
	    private static String PASSWORD = "Grindking";
	    private static String PORT = "465";
	    private static String FROM = "put_from_address_here";
	    private static String TO = "put_to_address_here";
	 
	    private static String STARTTLS = "true";
	    private static String AUTH = "true";
	    private static String DEBUG = "true";
	    private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    private static String SUBJECT = "Testing JavaMail API";
	    private static String TEXT = "This is a test message from my java application. Just ignore it";
	 

	
	
	 public void processPurchaseOrder(String filePath, PurchaseorderModel purchaseorderobj)
	 		{
	   
	    	fromAddressUName = Configuration.getConfiguration().getValue("common.email.fromAddress.username");
	    	fromAddressPassword = Configuration.getConfiguration().getValue("common.email.fromAddress.password");

	    	String  host = Configuration.getConfiguration().getValue("common.email.smpt.host");
	    	String  port = Configuration.getConfiguration().getValue("common.email.smtp.port");
	    	String  authentication = Configuration.getConfiguration().getValue("common.email.smpt.auth");
	    	String  debug = Configuration.getConfiguration().getValue("common.email.smtp.debug");	    	
	    
	    	Session session = null;
	        String s2 = fromAddressUName;	        
	        String s4 = "smtp.gmail.com";
	        boolean flag = Boolean.valueOf("true").booleanValue();	       
	        String s5 = Configuration.getConfiguration().getValue("common.email.subject.alert");
	      
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.auth", authentication);
	        properties.put("mail.debug", debug);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.socketFactory.port", port);
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        properties.put("mail.smtp.socketFactory.fallback", "false");
	        properties.put("mail.smtp.starttls.enable", STARTTLS);
	        properties.put("mail.smtp.user", USER);
	        if(flag)
	        {
	            properties.put("mail.debug", debug);
	        }	               
	         session = Session.getInstance(properties,new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(fromAddressUName,fromAddressPassword);
	         }
	         });	         
	         session.setDebug(flag);             
	        try
	        {
	        	MimeMessage mimemessage = new MimeMessage(session);
	            MimeBodyPart mimebodypart = new MimeBodyPart();
	            MimeMultipart mimemultipart = new MimeMultipart("related");
	            mimemessage.setFrom(new InternetAddress(s2));
	            
	            InternetAddress ainternetaddress[] = new InternetAddress[1];
	            for(int i = 0; i < 1; i++)
	            {
	                ainternetaddress[i] = new InternetAddress(purchaseorderobj.getSupplier().getEmail());
	            }

	            mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, ainternetaddress);
	            mimemessage.setSubject(s5);
	            mimemessage.setSentDate(new Date());
	            mimebodypart.setContent("Purchase Order Details : Purchase Order No:"+purchaseorderobj.getPurchaseOrderNo(), "text/html");
	            mimemultipart.addBodyPart(mimebodypart);
	            
	            mimebodypart = new MimeBodyPart();
	            MimeBodyPart mimebodypart1 = new MimeBodyPart();
	            FileDataSource filedatasource = new FileDataSource(filePath);
	            mimebodypart1.setDataHandler(new DataHandler(filedatasource));
	            mimebodypart1.setFileName(purchaseorderobj.getPurchaseOrderNo()+".pdf");
	            mimemultipart.addBodyPart(mimebodypart1);	  
	            mimemessage.setContent(mimemultipart);
	            Transport.send(mimemessage);	   
	            
	        }
	        catch(MessagingException messagingexception)
	        {
	            messagingexception.printStackTrace();
	            Object obj = messagingexception;
	            do
	            {
	                if(obj instanceof SendFailedException)
	                {
	                    SendFailedException sendfailedexception = (SendFailedException)obj;
	                    javax.mail.Address aaddress[] = sendfailedexception.getInvalidAddresses();
	                    if(aaddress != null)
	                    {
	                        if(aaddress == null);
	                    }
	                    javax.mail.Address aaddress1[] = sendfailedexception.getValidUnsentAddresses();
	                    if(aaddress1 != null)
	                    {
	                        if(aaddress1 == null);
	                    }
	                    javax.mail.Address aaddress2[] = sendfailedexception.getValidSentAddresses();
	                    if(aaddress2 != null)
	                    {
	                        if(aaddress2 == null);
	                    }
	                }
	                if(obj instanceof MessagingException)
	                {
	                    obj = ((MessagingException)obj).getNextException();
	                } else
	                {
	                    obj = null;
	                }
	            } while(obj != null);
	        }
	    }
	    
	
	 public void sendEmailToNewUser(String userEmailId, String subject, String message)
		{

	 fromAddressUName = Configuration.getConfiguration().getValue("common.email.fromAddress.username");
	 fromAddressPassword = Configuration.getConfiguration().getValue("common.email.fromAddress.password");

	String  host = Configuration.getConfiguration().getValue("common.email.smpt.host");
	String  port = Configuration.getConfiguration().getValue("common.email.smtp.port");
	String  authentication = Configuration.getConfiguration().getValue("common.email.smpt.auth");
	String  debug = Configuration.getConfiguration().getValue("common.email.smtp.debug");	    	
	String  sessionType = Configuration.getConfiguration().getValue("common.email.smtp.session");;
	Session session = null;
  String s2 = fromAddressUName;	        
  String s4 = "smtp.gmail.com";
  boolean flag = Boolean.valueOf("true").booleanValue();	       
  String s5 = "Alpha PloyClinic alert";

  Properties properties = new Properties();
  properties.put("mail.smtp.host", host);
  properties.put("mail.smtp.auth", authentication);
  properties.put("mail.debug", debug);
  properties.put("mail.smtp.port", port);
  properties.put("mail.smtp.socketFactory.port", port);
  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
  properties.put("mail.smtp.socketFactory.fallback", "false");
  properties.put("mail.smtp.starttls.enable", STARTTLS);
  properties.put("mail.smtp.user", USER);
  if(flag)
  {
      properties.put("mail.debug", debug);
  }
 
  if(sessionType.equalsIgnoreCase("default"))
  {         
   session = Session.getInstance(properties,
  			new javax.mail.Authenticator() {
  				protected PasswordAuthentication getPasswordAuthentication() {
  					return new PasswordAuthentication(fromAddressUName,fromAddressPassword);
  				}
  			});
   
   session.setDebug(flag);
  }	        
  else
  {
  session = Session.getInstance(properties, null);
  session.setDebug(flag);
  }
         
  try
  {
      MimeMessage mimemessage = new MimeMessage(session);
      MimeBodyPart mimebodypart = new MimeBodyPart();
      MimeMultipart mimemultipart = new MimeMultipart("related");
      mimemessage.setFrom(new InternetAddress(fromAddressUName));
      InternetAddress[] addressTo = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
			addressTo[i] = new InternetAddress(userEmailId);
			}				
      mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);           
      mimemessage.setSentDate(new Date());           		
			mimemessage.setSubject(subject);
			mimemessage.setText(message);
			mimemessage.setContent( message, "text/plain");           
      Transport.send(mimemessage);
  }
  catch(MessagingException messagingexception)
  {
      messagingexception.printStackTrace();
      Object obj = messagingexception;
      do
      {
          if(obj instanceof SendFailedException)
          {
              SendFailedException sendfailedexception = (SendFailedException)obj;
              javax.mail.Address aaddress[] = sendfailedexception.getInvalidAddresses();
              if(aaddress != null)
              {
                  if(aaddress == null);
              }
              javax.mail.Address aaddress1[] = sendfailedexception.getValidUnsentAddresses();
              if(aaddress1 != null)
              {
                  if(aaddress1 == null);
              }
              javax.mail.Address aaddress2[] = sendfailedexception.getValidSentAddresses();
              if(aaddress2 != null)
              {
                  if(aaddress2 == null);
              }
          }
          if(obj instanceof MessagingException)
          {
              obj = ((MessagingException)obj).getNextException();
          } else
          {
              obj = null;
          }
      } while(obj != null);
  }
}
 


	
	
}
