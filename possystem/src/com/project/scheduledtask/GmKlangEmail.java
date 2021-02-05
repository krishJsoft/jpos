package com.project.scheduledtask;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;


@ManagedBean(name = "gmKlangEmail")
@Service
public class GmKlangEmail {

	public boolean sendHtmlMail(String mailFrom,String password,String mailTo,String subject,
			String message,String[] attachment) throws Exception {
		boolean sendSuccess=false;
		try{
			
			String host = "smtp.gmail.com";
			
		    String port = "587";
		    
		    Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(mailFrom, password);
	            }
	        };
	        
	        Session session = Session.getInstance(properties, auth);
	        Message msg = new MimeMessage(session);
	        
	        msg.setFrom(new InternetAddress(mailFrom));
	        InternetAddress[] toAddresses = { new InternetAddress(mailTo)};
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	        
	        Multipart multipart = new MimeMultipart();

	        multipart.addBodyPart(messageBodyPart);
	        
	        for(int i=0;i<attachment.length;i++) {
	        	 messageBodyPart = new MimeBodyPart();
		         String filename = attachment[i];
		         DataSource source = new FileDataSource(filename);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(source.getName());
		         multipart.addBodyPart(messageBodyPart);
		         
	        }
	        msg.setContent(multipart);
	        Transport.send(msg);
	     

		}catch(Exception ex) {
			throw ex;
			
		}
		return sendSuccess;
	}
}
