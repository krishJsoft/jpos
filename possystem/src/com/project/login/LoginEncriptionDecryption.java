package com.project.login;

import java.security.*;
import javax.crypto.*;

public class LoginEncriptionDecryption {

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

	public void main(String bud[]) {

		try {
			byte[] encryptionBytes = encrypt("Test");
			String passw = new String(encryptionBytes);

			System.out.println("passw   :   " + passw);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
