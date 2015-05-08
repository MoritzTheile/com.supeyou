package com.hotelorga.foundation.iface.datatype.types;


public final class EmailAddressType extends AbstrType<String> {

	private static final long serialVersionUID = -99415265285221348L;

	// from http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	public static final String EMAIL_VALIDATION_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailAddressType() {
	}

	public EmailAddressType(String value) {
		setValue(value);
	}

	@Override
	protected void isValid(String value) throws TypeException {
		if (value == null) {
			throw new TypeException("value is null");
		}
		if (!value.matches(EMAIL_VALIDATION_REGEX)) {
			throw new TypeException("'" + value + "' is not an email address");
		}
	}

}
