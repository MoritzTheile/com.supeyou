package com.supeyou.crudie.iface.datatype.types;

public final class URLType extends AbstrType<String> {

	private static final long serialVersionUID = -99415265221348L;

	// from http://snipplr.com/view/6889/regular-expressions-for-uri-validationparsing/
	public static final String REGEX = "^([a-z][a-z0-9+.-]*):(?:\\/\\/((?:(?=((?:[a-z0-9-._~!$&'()*+,;=:]|%[0-9A-F]{2})*))(\\3)@)?(?=(\\[[0-9A-F:.]{2,}\\]|(?:[a-z0-9-._~!$&'()*+,;=]|%[0-9A-F]{2})*))\\5(?::(?=(\\d*))\\6)?)(\\/(?=((?:[a-z0-9-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})*))\\8)?|(\\/?(?!\\/)(?=((?:[a-z0-9-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})*))\\10)?)(?:\\?(?=((?:[a-z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9A-F]{2})*))\\11)?(?:#(?=((?:[a-z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9A-F]{2})*))\\12)?$";

	public URLType() {
	}

	public URLType(String value) {
		setValue(value);
	}

	@Override
	protected void isValid(String value) throws TypeException {
		if (value == null) {
			throw new TypeException("value is null");
		}
		if (!value.matches(REGEX)) {
			throw new TypeException("'" + value + "' is not an URI");
		}
	}

	public static void main(String[] args) {
		System.out.println(new URLType("https://lasf"));
	}

}
