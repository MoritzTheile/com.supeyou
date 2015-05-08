package com.supeyou.crudie.web.client.resources;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;

public class GWTSTATICS {

	public static final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

	public static final String fieldInvalidStyle = "field-invalid-style";

	public static final String fieldDirtyStyle = "field-dirty-style";

	public static final String FILE_ID_TOKEN = "file-id-as-string-randomasdfioeo";

	public static final String PARAMKEY_SENDASOCTETSTREAM = "PARAMKEY_SENDASOCTETSTREAM";

	public static final String HIDESTYLE_CSS = "hide";

	public static String formatDateNullsafe(Date date) {

		if (date == null) {
			return "";
		} else {
			return dateFormat.format(date);
		}
	}

	public static boolean nullsafeEquals(Object o1, Object o2) {

		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		if (o1 == null && o2 != null) {
			return false;
		}

		return o1.equals(o2);

	}

	public static void setJClassAsCSSClass(Widget widget) {
		widget.addStyleName(widget.getClass().getName().replaceAll("\\.", "-"));
	}

	public static String removeNonDigits(String string) {

		return string.replaceAll("[^0-9]*", "");
	}

	public static Integer euro2cent(String euroString) {

		if (euroString == null || euroString.isEmpty()) {
			return null;
		}

		try {

			euroString = euroString.trim();
			euroString = euroString.replaceAll("\\.", ",");
			String[] euroCentArray = euroString.split(",");

			if (euroCentArray.length == 0) {
				return null; // not possible actually because array is not empty
			}
			if (euroCentArray.length == 1) {
				// keine Nachkommastellen
				return Integer.parseInt(euroCentArray[0] + "00");
			}
			if (euroCentArray.length == 2) {
				if (euroCentArray[1].length() == 1) {
					// eine Nachkommastelle
					return Integer.parseInt(euroCentArray[0] + euroCentArray[1] + "0");
				}
				if (euroCentArray[1].length() == 2) {
					// zwei Nachkommastellen
					return Integer.parseInt(euroCentArray[0] + euroCentArray[1]);
				}
			}

			// mehr als zwei Nachkommastellen
			return Integer.parseInt(euroCentArray[0] + euroCentArray[1].substring(0, 2));

		} catch (Exception e) {
			System.out.println("amount couldn't be parsed from " + euroString);
		}
		return null;
	}

}
