package com.supeyou.crudie.web.client.fields.types;

import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public class FieldForDateType extends AbstrTextBoxField<DateType> {

	public FieldForDateType(DateType date) {
		super(date, false);
	}

	public FieldForDateType(boolean focused, DateType emailAddress) {
		super(emailAddress, focused);
	}

	@Override
	protected boolean valid(String value) {

		try {

			tryToFormat(value);

			return true;

		} catch (Exception e) {

			return false;

		}

	}

	@Override
	protected String getWrongFormatMessage() {

		return "codemarker=q08tuq0";

	}

	@Override
	protected DateType string2value(String string) {

		String formattedDate = "";

		try {
			formattedDate = tryToFormat(string);
		} catch (Exception e) {
			// nothing
			// e.printStackTrace();
		}

		return new DateType(formattedDate);
	}

	private String tryToFormat(String string) throws Exception {

		String formattedDate = "";

		try {
			String[] splitted = string.split("\\.");

			if (splitted.length == 3) {

				String day = ensure2digitsOrNull(splitted[0]);
				String month = ensure2digitsOrNull(splitted[1]);
				String year = GWTSTATICS.removeNonDigits(splitted[2]);

				if (day != null && month != null && year.length() == 4) {
					formattedDate = year + "-" + month + "-" + day;
				}

			}

		} catch (Exception e) {
			// problems on parsing, so doing nothing
		}
		if ("".equals(formattedDate)) {
			throw new Exception("couln't format date " + string);
		}
		return formattedDate;
	}

	private String ensure2digitsOrNull(String string) {

		string = GWTSTATICS.removeNonDigits(string);

		if (string.length() == 0) {
			return null;
		}
		if (string.length() == 1) {
			return "0" + string;
		}
		if (string.length() == 2) {
			return string;
		}
		return null;

	}

	@Override
	protected String value2String(DateType value) {
		return HELPER.date2string(value);
	}

}
