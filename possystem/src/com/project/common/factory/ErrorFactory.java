package com.project.common.factory;

import java.util.ResourceBundle;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */


public class ErrorFactory {
	static ResourceBundle resourceBundle;
	static ErrorFactory errorFactory;

	private ErrorFactory() {

	}
	
	static {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle
					.getBundle("META-INF/ErrorsResources");
		}
	}

	public static ErrorFactory getInstance() {
		if (errorFactory == null) {
			errorFactory = new ErrorFactory();
		}
		return errorFactory;
	}

	public String getError(String key, String... replacement) {
		String msg = resourceBundle.getString(key);
		String placeHolder;
		for (int i = 0; i < replacement.length; i++) {
			placeHolder = "{" + i + "}";
			msg = msg.replace(placeHolder, replacement[i]);
		}
		return msg;
	}
}
