package com.supeyou.crudie.web.client.fields.types;

import com.supeyou.crudie.iface.datatype.types.URLType;

public class FieldForURLType extends AbstrTextBoxField<URLType> {

	public FieldForURLType() {
		super(null, false);
	}

	public FieldForURLType(URLType emailAddress) {
		super(emailAddress, false);
	}

	public FieldForURLType(URLType emailAddress, boolean focused) {
		super(emailAddress, focused);
	}

	@Override
	protected boolean valid(String value) {

		try {

			new URLType(value);

			return true;

		} catch (Exception e) {

			return false;

		}

	}

	@Override
	protected String getWrongFormatMessage() {

		return "codemarker=a93264l9";

	}

	@Override
	protected URLType string2value(String string) {
		return new URLType(string);
	}

	@Override
	protected String value2String(URLType value) {
		if (value == null) {
			return "";
		} else {
			return value.value();
		}
	}

}
