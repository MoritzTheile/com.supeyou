package com.hotelorga.foundation.iface.datatype.types;

public class UserIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 2217296690996765037L;

	public UserIDType() {
	}

	public UserIDType(Long value) {
		setValue(value);
	}

	public UserIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}