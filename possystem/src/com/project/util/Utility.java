package com.project.util;

import java.util.List;

public class Utility {

	public static boolean isNull(String value) {
		return (value == null || value == "") ? true : false;
	}

	
	public static boolean isZero(Object obj) {
		return (obj == null || obj == "" || obj.equals(null)  || obj.equals("") || obj.equals("0")) ? true : false;
	}

	public static boolean isNotNull(String value) {
		return (value != null && value != "" && value.length() > 0) ? true
				: false;
	}

	public static boolean isNull(Object obj) {
		return (obj == null || obj == "" || obj.equals(null)  || obj.equals("")) ? true : false;
	}

	public static boolean isNotNull(Object obj) {
		return (obj != null) ? true : false;
	}

	public static boolean isNotNull(List<Object> list) {
		return (list != null && list.size() > 0) ? true : false;
	}
}
