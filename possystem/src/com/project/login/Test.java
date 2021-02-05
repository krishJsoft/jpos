package com.project.login;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class Test {

	private static String algorithm = "DESede";
	private static Key key = null;
	private static Cipher cipher = null;

	private static byte[] encrypt(String input) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] inputBytes = input.getBytes();
		return cipher.doFinal(inputBytes);
	}

	private static String decrypt(byte[] encryptionBytes) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
		String recovered = new String(recoveredBytes);
		return recovered;
	}

	public static void main(String[] args) {

		try {

			StringBuffer buffer = new StringBuffer();
			key = KeyGenerator.getInstance(algorithm).generateKey();
			cipher = Cipher.getInstance(algorithm);

			byte[] encryptionBytes = encrypt("Test");
			String passw = new String(encryptionBytes);
			System.out.println("passw   :   " + passw);
			System.out.println("Your password is: "+decrypt(passw.getBytes()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
