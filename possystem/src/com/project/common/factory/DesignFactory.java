package com.project.common.factory;

import java.util.ResourceBundle;

public class DesignFactory {

		static ResourceBundle resourceBundle;
		static DesignFactory designFactory;

		private DesignFactory() {

		}

		static {
			if (resourceBundle == null) {
				resourceBundle = ResourceBundle
						.getBundle("META-INF/DesignResources");
			}
		}

		public static DesignFactory getInstance() {
			if (designFactory == null) {
				designFactory = new DesignFactory();
			}
			return designFactory;
		}

		public String getDesign(String key, String... replacement) {
			String msg = resourceBundle.getString(key);
			String placeHolder;
			for (int i = 0; i < replacement.length; i++) {
				placeHolder = "{" + i + "}";
				msg = msg.replace(placeHolder, replacement[i]);
			}
			return msg;
		}

}
