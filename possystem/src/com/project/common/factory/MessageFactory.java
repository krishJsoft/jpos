package com.project.common.factory;

import java.util.ResourceBundle;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */

public class MessageFactory {
	static ResourceBundle resourceBundle;
	static MessageFactory messageFactory;

	private MessageFactory() {

	}

	static {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle
					.getBundle("META-INF/MessagesResources");
		}
	}

	public static MessageFactory getInstance() {
		if (messageFactory == null) {
			messageFactory = new MessageFactory();
		}
		return messageFactory;
	}

	public String getMessage(String key, String... replacement) {
		String msg = resourceBundle.getString(key);
		String placeHolder;
		for (int i = 0; i < replacement.length; i++) {
			placeHolder = "{" + i + "}";
			msg = msg.replace(placeHolder, replacement[i]);
		}
		return msg;
	}
}
