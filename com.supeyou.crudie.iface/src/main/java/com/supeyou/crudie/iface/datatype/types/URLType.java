package com.supeyou.crudie.iface.datatype.types;

public final class URLType extends AbstrType<String> {

	private static final long serialVersionUID = -99415265221348L;

	// from http://snipplr.com/view/6889/regular-expressions-for-uri-validationparsing/
	// modification a-z -> a-zA-Z
	public static final String REGEX = "^([a-zA-Z][a-zA-Z0-9+.-]*):(?:\\/\\/((?:(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:]|%[0-9A-F]{2})*))(\\3)@)?(?=(\\[[0-9A-F:.]{2,}\\]|(?:[a-zA-Z0-9-._~!$&'()*+,;=]|%[0-9A-F]{2})*))\\5(?::(?=(\\d*))\\6)?)(\\/(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})*))\\8)?|(\\/?(?!\\/)(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})*))\\10)?)(?:\\?(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9A-F]{2})*))\\11)?(?:#(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9A-F]{2})*))\\12)?$";

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
		System.out.println(new URLType("http://www.youtube.com/embed/2P"));
	}

}
