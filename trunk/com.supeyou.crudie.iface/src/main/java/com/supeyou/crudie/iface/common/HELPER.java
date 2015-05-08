package com.supeyou.crudie.iface.common;

import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;

public class HELPER {

	public static String cent2euro(Integer centValue) {
		String centString = centValue + "";
		String euroString = "";
		if ((centString).length() == 1) {
			euroString = "0,0" + centString;
		} else if ((centString).length() == 2) {
			euroString = "0," + centString;
		} else {
			euroString = centString.substring(0, centString.length() - 2) + "," + centString.substring(centString.length() - 2, centString.length());
		}
		return euroString;
	}

	public static String amount2eurostring(AmountType value) {
		if (value == null) {
			return "";
		}
		return HELPER.cent2euro(value.value());
	}

	public static String date2string(DateType value) {
		if (value == null) {
			return "";
		}
		String[] splittedDate = value.value().split("-");
		return splittedDate[2] + "." + splittedDate[1] + "." + splittedDate[0];
	}

}
