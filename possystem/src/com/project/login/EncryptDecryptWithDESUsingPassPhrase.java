package com.project.login;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.project.common.util.DateUtil;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class EncryptDecryptWithDESUsingPassPhrase {

	private static Cipher ecipher;
	private static Cipher dcipher;

	private static final int iterationCount = 10;
	// 8-byte Salt
	private static byte[] salt = {

	(byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2,

	(byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3 };

	public static void main(String[] args) {

		try {

			String passPhrase = "My Secret Password";			
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
					iterationCount);
			
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());		

			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			
			Calendar cal = Calendar.getInstance();
			DateUtil dateUtil = new DateUtil();
			
			Calendar fromDate = Calendar.getInstance();
			Calendar toDate = Calendar.getInstance();
			int day = cal.get(Calendar.DATE);
			int month = cal.get(Calendar.MONTH);
			month=month+1;
			int year = cal.get(Calendar.YEAR);
			int dow = cal.get(Calendar.DAY_OF_WEEK);
			int dom = cal.get(Calendar.DAY_OF_MONTH);
			int doy = cal.get(Calendar.DAY_OF_YEAR);
			
			String fromDateString=String.valueOf(day)+"-"+(String.valueOf(month))+"-"+(String.valueOf(year));	
			month=month-1;
		   	fromDate.set(year, month+1, day);			
			int day1 = fromDate.get(Calendar.DATE);
			int month1 = fromDate.get(Calendar.MONTH);
			month1=month1+1;
			int year1 = fromDate.get(Calendar.YEAR);			
			
					
			String fromDateString1=String.valueOf(day1)+"-"+(String.valueOf(month1))+"-"+(String.valueOf(year1));	
			fromDateString1=fromDateString.concat("|").concat(fromDateString1);
			
			
		    
		    
			String encrypted = encrypt(fromDateString1);
			System.out.println("encrypted: " + encrypted);
			String decrypted = decrypt(encrypted);
			System.out.println("Decrypted: " + decrypted);
		} catch (InvalidAlgorithmParameterException e) {
			System.out
					.println("Invalid Alogorithm Parameter:" + e.getMessage());
			return;
		} catch (InvalidKeySpecException e) {
			System.out.println("Invalid Key Spec:" + e.getMessage());
			return;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		}

	}

	public static String encrypt(String str)  {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);	
			enc = BASE64EncoderStream.encode(enc);
			return new String(enc);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public static String decrypt(String str)  {

		try {
			//str="QPDBuzWLYykwp0fWhyOvGS2bbwg=";
			byte[] dec = BASE64DecoderStream.decode(str.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}