package com.hotelorga.foundation.web.client.fields.types;

import com.hotelorga.foundation.iface.datatype.types.EmailAddressType;

public class FieldForEmailAddressType extends AbstrTextBoxField<EmailAddressType> {

	public FieldForEmailAddressType() {
		super(null, false);
	}

	public FieldForEmailAddressType(EmailAddressType emailAddress) {
		super(emailAddress, false);
	}

	public FieldForEmailAddressType(EmailAddressType emailAddress, boolean focused) {
		super(emailAddress, focused);
	}

	@Override
	protected boolean valid(String value) {

		try {

			new EmailAddressType(value);

			return true;

		} catch (Exception e) {

			return false;

		}

	}

	@Override
	protected String getWrongFormatMessage() {

		return "codemarker=a9g9t4l9";

	}

	@Override
	protected EmailAddressType string2value(String string) {
		return new EmailAddressType(string);
	}

	@Override
	protected String value2String(EmailAddressType value) {
		if (value == null) {
			return "";
		} else {
			return value.value();
		}
	}

}
