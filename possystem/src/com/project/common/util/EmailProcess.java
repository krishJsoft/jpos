package com.project.common.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Security;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.common.config.Configuration;
import com.project.common.factory.BeanContext;
import com.project.login.LoginBean;

public class EmailProcess {

	public static Logger log = LoggerFactory.getLogger(EmailProcess.class);

	private String fromAddressUName = Configuration.getConfiguration()
			.getValue("common.email.fromAddress.username");
	private String fromAddressPassword = Configuration.getConfiguration()
			.getValue("common.email.fromAddress.password");

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

	public static synchronized void send() {
		Properties props = new Properties();

		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.user", USER);

		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", STARTTLS);
		props.put("mail.smtp.debug", DEBUG);

		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {

			// Obtain the default mail session
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);

			// Construct the mail message
			MimeMessage message = new MimeMessage(session);
			message.setText(TEXT);
			message.setSubject(SUBJECT);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(RecipientType.TO, new InternetAddress(TO));
			message.saveChanges();

			// Use Transport to deliver the message
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Properties getProperty() {
		Security.setProperty("ssl.SocketFactory.provider",
				"com.ibm.jsse2.SSLSocketFactoryImpl");
		Security.setProperty("ssl.ServerSocketFactory.provider",
				"com.ibm.jsse2.SSLServerSocketFactoryImpl");
		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		return props;
	}

	public void sendEmailToNewUser(String staffCode  , String userEmailId, String password) {

		fromAddressUName = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.username");
		fromAddressPassword = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.password");

		String host = Configuration.getConfiguration().getValue(
				"common.email.smpt.host");
		String port = Configuration.getConfiguration().getValue(
				"common.email.smtp.port");
		String authentication = Configuration.getConfiguration().getValue(
				"common.email.smpt.auth");
		String debug = Configuration.getConfiguration().getValue(
				"common.email.smtp.debug");
		String sessionType = Configuration.getConfiguration().getValue(
				"common.email.smtp.session");
		;
		Session session = null;
		String s2 = fromAddressUName;
		String s4 = "smtp.gmail.com";
		boolean flag = Boolean.valueOf("true").booleanValue();
		String s5 = "Alpha PloyClinic alert";

		/*
		 * Properties properties = new Properties();
		 * properties.put("mail.smtp.host", host);
		 * properties.put("mail.smtp.auth", authentication);
		 * properties.put("mail.debug", debug); properties.put("mail.smtp.port",
		 * port); properties.put("mail.smtp.socketFactory.port", port);
		 * properties.put("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * properties.put("mail.smtp.socketFactory.fallback", "false");
		 * properties.put("mail.smtp.starttls.enable", STARTTLS);
		 * properties.put("mail.smtp.user", USER); if(flag) {
		 * properties.put("mail.debug", debug); }
		 * 
		 * if(sessionType.equalsIgnoreCase("default")) { session =
		 * Session.getInstance(properties, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return
		 * new PasswordAuthentication(fromAddressUName,fromAddressPassword); }
		 * });
		 * 
		 * session.setDebug(flag); } else { session =
		 * Session.getInstance(properties, null); session.setDebug(flag); }
		 */

		// Assuming you are sending email from localhost
		host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "25");

		// Get the default Session object.
		session = Session.getInstance(properties);

		try {
			MimeMessage mimemessage = new MimeMessage(session);
			MimeBodyPart mimebodypart = new MimeBodyPart();
			MimeMultipart mimemultipart = new MimeMultipart("related");
			mimemessage.setFrom(new InternetAddress(fromAddressUName));
			InternetAddress[] addressTo = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
				addressTo[i] = new InternetAddress(userEmailId);
			}
			mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,
					addressTo);
			mimemessage.setSentDate(new Date());
			mimemessage.setSubject(s5 + " Login details ");
			mimemessage.setText(" Login details .  User Name : "
					+ userEmailId + ", Password : " + password);
			mimemessage.setContent(" Login details   User Name : "
					+ staffCode + ", Password : " + password, "text/plain");
			Transport.send(mimemessage);
		} catch (MessagingException messagingexception) {
			messagingexception.printStackTrace();
			Object obj = messagingexception;
			do {
				if (obj instanceof SendFailedException) {
					SendFailedException sendfailedexception = (SendFailedException) obj;
					javax.mail.Address aaddress[] = sendfailedexception
							.getInvalidAddresses();
					if (aaddress != null) {
						if (aaddress == null)
							;
					}
					javax.mail.Address aaddress1[] = sendfailedexception
							.getValidUnsentAddresses();
					if (aaddress1 != null) {
						if (aaddress1 == null)
							;
					}
					javax.mail.Address aaddress2[] = sendfailedexception
							.getValidSentAddresses();
					if (aaddress2 != null) {
						if (aaddress2 == null)
							;
					}
				}
				if (obj instanceof MessagingException) {
					obj = ((MessagingException) obj).getNextException();
				} else {
					obj = null;
				}
			} while (obj != null);
		}
	}

	public void emailPurchaseOrder(String filePath,
			PurchaseorderModel purchaseorderobj) {

		fromAddressUName = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.username");
		fromAddressPassword = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.password");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		String host = Configuration.getConfiguration().getValue(
				"common.email.smpt.host");
		String port = Configuration.getConfiguration().getValue(
				"common.email.smtp.port");
		String authentication = Configuration.getConfiguration().getValue(
				"common.email.smpt.auth");
		String debug = Configuration.getConfiguration().getValue(
				"common.email.smtp.debug");
		String s2 = fromAddressUName;

		// Assuming you are sending email from localhost
		host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "25");

		// Get the default Session object.
		Session session = Session.getInstance(properties);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			MimeBodyPart mimebodypart = new MimeBodyPart();
			MimeMultipart mimemultipart = new MimeMultipart("related");
			mimemessage.setFrom(new InternetAddress(s2));

			InternetAddress ainternetaddress[] = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
				ainternetaddress[i] = new InternetAddress(purchaseorderobj
						.getSupplier().getEmail());
			}

			mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,
					ainternetaddress);
			mimemessage.setSubject("PURCHASE ORDER ");
			mimemessage.setSentDate(new Date());
			String message = "<div style=\"color:blue;\">Dear Mr/Miss <b><font><color=black;> "
					+ purchaseorderobj.getSupplier().getContactPerson()
					+ "</font></b>,</div> \n"
					+ "<div style=\"color:blue;\">Please find the attached PDF file with Purchase Order No.:<font><color=black;> <b>"
					+ purchaseorderobj.getPurchaseOrderNo()
					+ " </font> </b> which detailed the items to be purchased.</div> \n"
					+ "<div style=\"color:blue;\">Please give me a call if you have any questions.  \n Thanks for your cooperation. <br><br> Sincerely <br> <font><color=black;> <b>"
					+ loginBean.getLogdetail().getFirstName()
					+ " <br>"
					+ loginBean.getLogdetail().getRoleName()
					+ " <br>"
					+ loginBean.getLogdetail().getPhoneNo()
					+ "</b> </font></div>";

			mimebodypart.setContent(message, "text/html");
			mimemultipart.addBodyPart(mimebodypart);

			mimebodypart = new MimeBodyPart();
			MimeBodyPart mimebodypart1 = new MimeBodyPart();
			FileDataSource filedatasource = new FileDataSource(filePath);
			mimebodypart1.setDataHandler(new DataHandler(filedatasource));
			mimebodypart1.setFileName(purchaseorderobj.getPurchaseOrderNo()
					+ ".pdf");
			mimemultipart.addBodyPart(mimebodypart1);
			mimemessage.setContent(mimemultipart);
			Transport.send(mimemessage);

		} catch (MessagingException messagingexception) {
			messagingexception.printStackTrace();
			Object obj = messagingexception;
			do {
				if (obj instanceof SendFailedException) {
					SendFailedException sendfailedexception = (SendFailedException) obj;
					javax.mail.Address aaddress[] = sendfailedexception
							.getInvalidAddresses();
					if (aaddress != null) {
						if (aaddress == null)
							;
					}
					javax.mail.Address aaddress1[] = sendfailedexception
							.getValidUnsentAddresses();
					if (aaddress1 != null) {
						if (aaddress1 == null)
							;
					}
					javax.mail.Address aaddress2[] = sendfailedexception
							.getValidSentAddresses();
					if (aaddress2 != null) {
						if (aaddress2 == null)
							;
					}
				}
				if (obj instanceof MessagingException) {
					obj = ((MessagingException) obj).getNextException();
				} else {
					obj = null;
				}
			} while (obj != null);
		}
	}

	public void emailPurchaseOrder1(String filePath,
			PurchaseorderModel purchaseorderobj) {

		fromAddressUName = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.username");
		fromAddressPassword = Configuration.getConfiguration().getValue(
				"common.email.fromAddress.password");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		String host = Configuration.getConfiguration().getValue(
				"common.email.smpt.host");
		String port = Configuration.getConfiguration().getValue(
				"common.email.smtp.port");
		String authentication = Configuration.getConfiguration().getValue(
				"common.email.smpt.auth");
		String debug = Configuration.getConfiguration().getValue(
				"common.email.smtp.debug");

		Session session = null;
		String s2 = fromAddressUName;
		String s4 = "smtp.gmail.com";
		boolean flag = Boolean.valueOf("true").booleanValue();
		String s5 = Configuration.getConfiguration().getValue(
				"common.email.subject.alert");

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", authentication);
		properties.put("mail.debug", debug);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.starttls.enable", STARTTLS);
		properties.put("mail.smtp.user", USER);
		if (flag) {
			properties.put("mail.debug", debug);
		}
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromAddressUName,
								fromAddressPassword);
					}
				});
		session.setDebug(flag);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			MimeBodyPart mimebodypart = new MimeBodyPart();
			MimeMultipart mimemultipart = new MimeMultipart("related");
			mimemessage.setFrom(new InternetAddress(s2));

			InternetAddress ainternetaddress[] = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
				ainternetaddress[i] = new InternetAddress(purchaseorderobj
						.getSupplier().getEmail());
			}

			mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,
					ainternetaddress);
			mimemessage.setSubject("PURCHASE ORDER " + s5);
			mimemessage.setSentDate(new Date());
			String message = "<div style=\"color:blue;\">Dear Mr/Miss <b><font><color=black;> "
					+ purchaseorderobj.getSupplier().getContactPerson()
					+ "</font></b>,</div> \n"
					+ "<div style=\"color:blue;\">Please find the attached PDF file with Purchase Order No.:<font><color=black;> <b>"
					+ purchaseorderobj.getPurchaseOrderNo()
					+ " </font> </b> which detailed the items to be purchased.</div> \n"
					+ "<div style=\"color:blue;\">Please give me a call if you have any questions.  \n Thanks for your cooperation. <br><br> Sincerely <br> <font><color=black;> <b>"
					+ loginBean.getLogdetail().getFirstName()
					+ " <br>"
					+ loginBean.getLogdetail().getRoleName()
					+ " <br>"
					+ loginBean.getLogdetail().getPhoneNo()
					+ "</b> </font></div>";

			mimebodypart.setContent(message, "text/html");
			mimemultipart.addBodyPart(mimebodypart);

			mimebodypart = new MimeBodyPart();
			MimeBodyPart mimebodypart1 = new MimeBodyPart();
			FileDataSource filedatasource = new FileDataSource(filePath);
			mimebodypart1.setDataHandler(new DataHandler(filedatasource));
			mimebodypart1.setFileName(purchaseorderobj.getPurchaseOrderNo()
					+ ".pdf");
			mimemultipart.addBodyPart(mimebodypart1);
			mimemessage.setContent(mimemultipart);
			Transport.send(mimemessage);

		} catch (MessagingException messagingexception) {
			messagingexception.printStackTrace();
			Object obj = messagingexception;
			do {
				if (obj instanceof SendFailedException) {
					SendFailedException sendfailedexception = (SendFailedException) obj;
					javax.mail.Address aaddress[] = sendfailedexception
							.getInvalidAddresses();
					if (aaddress != null) {
						if (aaddress == null)
							;
					}
					javax.mail.Address aaddress1[] = sendfailedexception
							.getValidUnsentAddresses();
					if (aaddress1 != null) {
						if (aaddress1 == null)
							;
					}
					javax.mail.Address aaddress2[] = sendfailedexception
							.getValidSentAddresses();
					if (aaddress2 != null) {
						if (aaddress2 == null)
							;
					}
				}
				if (obj instanceof MessagingException) {
					obj = ((MessagingException) obj).getNextException();
				} else {
					obj = null;
				}
			} while (obj != null);
		}
	}

	public void emailPurchaseOrder2(String filePath,PurchaseorderModel purchaseorderobj,Map<String, Object> reportParameters) {

		fromAddressUName = Configuration.getConfiguration().getValue("common.email.fromAddress.username");
		fromAddressPassword = Configuration.getConfiguration().getValue("common.email.fromAddress.password");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		String host = Configuration.getConfiguration().getValue("common.email.smpt.host");
		String port = Configuration.getConfiguration().getValue("common.email.smtp.port");
		String authentication = Configuration.getConfiguration().getValue("common.email.smpt.auth");
		String debug = Configuration.getConfiguration().getValue("common.email.smtp.debug");

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
		properties.put("mail.smtp.socketFactory.class",	"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.starttls.enable", STARTTLS);
		properties.put("mail.smtp.user", USER);
		if (flag) {
			properties.put("mail.debug", debug);
		}
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromAddressUName,
								fromAddressPassword);
					}
				});
		session.setDebug(flag);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			MimeBodyPart mimebodypart = new MimeBodyPart();
			MimeMultipart mimemultipart = new MimeMultipart("related");
			mimemessage.setFrom(new InternetAddress(s2));

			InternetAddress ainternetaddress[] = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
				ainternetaddress[i] = new InternetAddress(purchaseorderobj.getSupplier().getEmail());
			}

			mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,ainternetaddress);
			mimemessage.setSubject("PURCHASE ORDER " + s5);
			mimemessage.setSentDate(new Date());
			String message = "<div style=\"color:blue;\">Dear Mr/Miss <b><font><color=black;> "
					+ purchaseorderobj.getSupplier().getContactPerson()
					+ "</font></b>,</div> \n"
					+ "<div style=\"color:blue;\">Please find the attached PDF file with Purchase Order No.:<font><color=black;> <b>"
					+ purchaseorderobj.getPurchaseOrderNo()
					+ " </font> </b> which detailed the items to be purchased.</div> \n"
					+ "<div style=\"color:blue;\">Please give me a call if you have any questions.  \n Thanks for your cooperation. <br><br> Sincerely <br> <font><color=black;> <b>"
					+ loginBean.getLogdetail().getFirstName()
					+ " <br>"
					+ loginBean.getLogdetail().getRoleName()
					+ " <br>"
					+ loginBean.getLogdetail().getPhoneNo()
					+ "</b> </font></div>";

		

			/*MimeBodyPart mimebodypart1 = new MimeBodyPart();
			FileDataSource filedatasource = new FileDataSource(filePath);
			mimebodypart1.setDataHandler(new DataHandler(filedatasource));
			mimebodypart1.setFileName(purchaseorderobj.getPurchaseOrderNo()+".pdf");
			mimemultipart.addBodyPart(mimebodypart1);
			mimebodypart.setContent(message, "text/html");
			
			mimemessage.setContent(mimemultipart);
			Transport.send(mimemessage); */
			
		  String reportPath="report/purchase/purchaseOrderDetail.rptdesign";
		  StringBuilder fullUrl = new StringBuilder(filePath).append("/preview?__format=pdf&__report=").append(URLEncoder.encode(reportPath, "UTF-8"));
      
       // add report parameters, if any, to the URL
       if(reportParameters != null && !reportParameters.isEmpty()) {
             for(String key : reportParameters.keySet()) {
                   final Object val = reportParameters.get(key);
                   fullUrl.append('&').append(URLEncoder.encode(key, "UTF-8"))
                         .append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), "UTF-8"));
             }
       }
       			            
			Multipart multipart = new MimeMultipart();
			 
			MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("");
            textPart.setContent(message,"text/html");   
            multipart.addBodyPart(textPart);
            
			BodyPart attachmentPart = new MimeBodyPart();           
			attachmentPart.setDataHandler(new DataHandler(new URL(fullUrl.toString())));			
            attachmentPart.setFileName(purchaseorderobj.getPurchaseOrderNo()+".pdf");
            attachmentPart.setDisposition(Part.ATTACHMENT);
            attachmentPart.setHeader("Content-Type", "application/pdf");
            multipart.addBodyPart(attachmentPart);
            mimemessage.setContent(multipart);               
            
            Transport.send(mimemessage);            

		} 		
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException messagingexception) {
			messagingexception.printStackTrace();
			Object obj = messagingexception;
			do {
				if (obj instanceof SendFailedException) {
					SendFailedException sendfailedexception = (SendFailedException) obj;
					javax.mail.Address aaddress[] = sendfailedexception
							.getInvalidAddresses();
					if (aaddress != null) {
						if (aaddress == null)
							;
					}
					javax.mail.Address aaddress1[] = sendfailedexception
							.getValidUnsentAddresses();
					if (aaddress1 != null) {
						if (aaddress1 == null)
							;
					}
					javax.mail.Address aaddress2[] = sendfailedexception
							.getValidSentAddresses();
					if (aaddress2 != null) {
						if (aaddress2 == null)
							;
					}
				}
				if (obj instanceof MessagingException) {
					obj = ((MessagingException) obj).getNextException();
				} else {
					obj = null;
				}
			} while (obj != null);
		}
		
		
	}
	
	
	
	
	public void emailQuotation(String filePath,QuotationModel quotation,Map<String, Object> reportParameters,String reportPath) {

		fromAddressUName = Configuration.getConfiguration().getValue("common.email.fromAddress.username");
		fromAddressPassword = Configuration.getConfiguration().getValue("common.email.fromAddress.password");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		String host = Configuration.getConfiguration().getValue("common.email.smpt.host");
		String port = Configuration.getConfiguration().getValue("common.email.smtp.port");
		String authentication = Configuration.getConfiguration().getValue("common.email.smpt.auth");
		String debug = Configuration.getConfiguration().getValue("common.email.smtp.debug");

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
		properties.put("mail.smtp.socketFactory.class",	"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.starttls.enable", STARTTLS);
		properties.put("mail.smtp.user", USER);
		if (flag) {
			properties.put("mail.debug", debug);
		}
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromAddressUName,
								fromAddressPassword);
					}
				});
		session.setDebug(flag);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			MimeBodyPart mimebodypart = new MimeBodyPart();
			MimeMultipart mimemultipart = new MimeMultipart("related");
			mimemessage.setFrom(new InternetAddress(s2));

			InternetAddress ainternetaddress[] = new InternetAddress[1];
			for (int i = 0; i < 1; i++) {
				ainternetaddress[i] = new InternetAddress(quotation.getSupplier().getEmail());
			}

			mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,ainternetaddress);
			mimemessage.setSubject("QUOTATION ORDER " + s5);
			mimemessage.setSentDate(new Date());
			String message = "<div style=\"color:blue;\">Dear Mr/Miss <b><font><color=black;> "
					+ quotation.getSupplier().getContactPerson()
					+ "</font></b>,</div> \n"
					+ "<div style=\"color:blue;\">Please find the attached PDF file with Quotation  No.:<font><color=black;> <b>"
					+ quotation.getQuotationNo()
					+ " </font> </b> which detailed the items to be purchased.</div> \n"
					+ "<div style=\"color:blue;\">Please give me a call if you have any questions.  \n Thanks for your cooperation. <br><br> Sincerely <br> <font><color=black;> <b>"
					+ loginBean.getLogdetail().getFirstName()
					+ " <br>"
					+ loginBean.getLogdetail().getRoleName()
					+ " <br>"
					+ loginBean.getLogdetail().getPhoneNo()
					+ "</b> </font></div>";

		

			/*MimeBodyPart mimebodypart1 = new MimeBodyPart();
			FileDataSource filedatasource = new FileDataSource(filePath);
			mimebodypart1.setDataHandler(new DataHandler(filedatasource));
			mimebodypart1.setFileName(purchaseorderobj.getPurchaseOrderNo()+".pdf");
			mimemultipart.addBodyPart(mimebodypart1);
			mimebodypart.setContent(message, "text/html");
			
			mimemessage.setContent(mimemultipart);
			Transport.send(mimemessage); */
			
		 
		  StringBuilder fullUrl = new StringBuilder(filePath).append("/preview?__format=pdf&__report=").append(URLEncoder.encode(reportPath, "UTF-8"));
      
       // add report parameters, if any, to the URL
       if(reportParameters != null && !reportParameters.isEmpty()) {
             for(String key : reportParameters.keySet()) {
                   final Object val = reportParameters.get(key);
                   fullUrl.append('&').append(URLEncoder.encode(key, "UTF-8"))
                         .append('=').append(URLEncoder.encode(val == null ? "" : val.toString(), "UTF-8"));
             }
       }
       			            
			Multipart multipart = new MimeMultipart();
			 
			MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("");
            textPart.setContent(message,"text/html");   
            multipart.addBodyPart(textPart);
           
            
			BodyPart attachmentPart = new MimeBodyPart();           
			attachmentPart.setDataHandler(new DataHandler(new URL(fullUrl.toString())));			
            attachmentPart.setFileName(quotation.getQuotationNo()+".pdf");
            attachmentPart.setDisposition(Part.ATTACHMENT);
            attachmentPart.setHeader("Content-Type", "application/pdf");
            multipart.addBodyPart(attachmentPart);
            mimemessage.setContent(multipart);               
            
            Transport.send(mimemessage);            

		} 		
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException messagingexception) {
			messagingexception.printStackTrace();
			Object obj = messagingexception;
			do {
				if (obj instanceof SendFailedException) {
					SendFailedException sendfailedexception = (SendFailedException) obj;
					javax.mail.Address aaddress[] = sendfailedexception
							.getInvalidAddresses();
					if (aaddress != null) {
						if (aaddress == null)
							;
					}
					javax.mail.Address aaddress1[] = sendfailedexception
							.getValidUnsentAddresses();
					if (aaddress1 != null) {
						if (aaddress1 == null)
							;
					}
					javax.mail.Address aaddress2[] = sendfailedexception
							.getValidSentAddresses();
					if (aaddress2 != null) {
						if (aaddress2 == null);
					}
				}
				if (obj instanceof MessagingException) {
					obj = ((MessagingException) obj).getNextException();
				} else {
					obj = null;
				}
			} while (obj != null);
		}
		
		
	}
	
	

}
