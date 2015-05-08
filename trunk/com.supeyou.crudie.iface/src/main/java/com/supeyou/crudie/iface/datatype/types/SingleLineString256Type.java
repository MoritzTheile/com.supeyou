package com.supeyou.crudie.iface.datatype.types;

public final class SingleLineString256Type extends AbstrType<String> {

	private static final long serialVersionUID = -99415345285221348L;

	public SingleLineString256Type() {
	}

	public SingleLineString256Type(String value) {
		setValue(value);
	}

	@Override
	protected void isValid(String value) throws TypeException {
		if (value == null) {
			throw new TypeException("value is null");
		}
		if (value.length() > 256) {
			throw new TypeException("value is to long");
		}
		if (value.contains("\n")) {
			throw new TypeException("value contains line break");
		}

	}

}
