/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author Admin
 */
public class Emailer {

    protected Session session = null;

    public Emailer(String host, int port, String username, String password, boolean enablessl) {
        // Set system properties
    	String  debug = ConfigurationLoad.getConfiguration().getValue("common.email.smtp.debug");
    	String STARTTLS = "true";
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port + "");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        
        properties.put("mail.smtp.auth", enablessl);
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.starttls.required", "true");

        properties.put("mail.smtp.socketFactory.port", port + "");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
       
        
        
        boolean flag = Boolean.valueOf("true").booleanValue();	  

        if(flag)
        {
            properties.put("mail.debug", debug);
        }
          
        /* session = Session.getDefaultInstance(properties,
        			new javax.mail.Authenticator() {
        				protected PasswordAuthentication getPasswordAuthentication() {
        					return new PasswordAuthentication(username,password);
        				}
        			});*/
         
        
                
        
        // Get the default Session object.
        session = Session.getDefaultInstance(properties, new GoogleMailAuthenticator(username, password));
        session.setDebug(flag);
    }

    /**
     * This method is used to send email with attachments
     *
     * @param from This will accept single email address
     * @param tos This will accept multiple email address separated by ',' or
     * ';'
     * @return boolean This returns whether email was sent successfully.
     */
    public boolean sendEmail(String from, String tos, String ccs, String bccs, String subject, String message) {
        return sendEmail(from, tos, ccs, bccs, subject, message, null, true);
    }

    public boolean sendEmail(String from, String tos, String ccs, String bccs, String subject, String message, List<String> attachments, boolean htmlcontent) {
        List<String> ltos = null, lccs = null, lbccs = null;
        if (tos != null) {
            ltos = new ArrayList<String>(Arrays.asList(tos.split("[,;]")));
        }
        if (ccs != null) {
            lccs = new ArrayList<String>(Arrays.asList(ccs.split("[,;]")));
        }
        if (bccs != null) {
            lbccs = new ArrayList<String>(Arrays.asList(bccs.split("[,;]")));
        }

        return sendEmail(from, ltos, lccs, lbccs, subject, message, attachments, htmlcontent);
    }
    
    
    
    public boolean sendattachEmail(String from, String tos, String ccs, String bccs, String subject, String message, List<String> attachments, boolean htmlcontent) {
        List<String> ltos = null, lccs = null, lbccs = null;
        if (tos != null) {
            ltos = new ArrayList<String>(Arrays.asList(tos.split("[,;]")));
        }
        if (ccs != null) {
            lccs = new ArrayList<String>(Arrays.asList(ccs.split("[,;]")));
        }
        if (bccs != null) {
            lbccs = new ArrayList<String>(Arrays.asList(bccs.split("[,;]")));
        }

        return sendEmail(from, ltos, lccs, lbccs, subject, message, attachments, htmlcontent);
    }
    
    

    public boolean sendEmail(String from, List<String> tos, List<String> ccs, List<String> bccs, String subject, String message, List<String> attachments, boolean htmlcontent) {
        if (session != null) {
            try {
                // Create a default MimeMessage object.
                Message mmessage = new MimeMessage(session);

                // Set From: header field of the header.
                mmessage.setFrom(new InternetAddress(from));

                // Set To, Cc, Bcc: header field of the header.
                for (String email : tos) {
                    if (email.trim().length() > 0) {
                        mmessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.trim()));
                    }
                }
                if (ccs != null) {
                    for (String email : ccs) {
                        if (email.trim().length() > 0) {
                            mmessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email.trim()));
                        }
                    }
                }
                if (bccs != null) {
                    for (String email : bccs) {
                        if (email.trim().length() > 0) {
                            mmessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(email.trim()));
                        }
                    }
                }

                // Set Subject: header field
                mmessage.setSubject(subject);

                // Send the actual HTML message, as big as you like
                String contentType = "text/plain";
                if (htmlcontent) {
                    contentType = "text/html; charset=utf-8";
                }
                if (attachments == null) {
                    mmessage.setContent(message, contentType);
                } else {
                    if (attachments.size() > 0) {
                        Multipart multipart = new MimeMultipart();

                        // creates body part for the message
                        MimeBodyPart messageBodyPart = new MimeBodyPart();
                        messageBodyPart.setContent(message, contentType);
                        multipart.addBodyPart(messageBodyPart);

                        for (String filepath : attachments) {
                            // JavaMail 1.4
                            MimeBodyPart attachPart = new MimeBodyPart();
                            String attachFile = filepath;
                            attachPart.attachFile(attachFile);
                            multipart.addBodyPart(attachPart);
                        }

                        // sets the multipart as message's content
                        mmessage.setContent(multipart);
                    }
                }

                // Send message
                Transport.send(mmessage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        // SMTP info
        String host = "mail.buzznetlabs.com";
        int port = 25;
        String user = "raja@buzznetlabs.com";
        String password = "password";

        // message info
        String from = "raja@buzznetlabs.com";
        String to = "raja@buzznetlabs.com;mani.a@buzznetlabs.com;";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = "D:\\Projects\\Invoice\\Source\\invoiceapp\\src\\java\\invoiceapp\\core\\dao\\utils\\Emailer.java";

        try {
            Emailer emailer = new Emailer(host, port, user, password, false);

            emailer.sendEmail(user, from, to, user, subject, message, Arrays.asList(attachFiles), true);

            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}
