package com.supeyou.crudie.iface.datatype.types;

public final class URLType extends AbstrType<String> {

	private static final long serialVersionUID = -99415265221348L;

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
		if (!value.matches("^([a-zA-Z][a-zA-Z0-9+.-]*):\\/.*")) {
			throw new TypeException("'" + value + "' is not an URI");
		}
		if (value.matches("(.*)?(\\s)(.*)")) {
			throw new TypeException("'" + value + "' is not an URI because it contains whitespace");
		}

	}

	public static void main(String[] args) {
		System.out.println(new URLType("http://unitedforhope.orga"));
	}

}
