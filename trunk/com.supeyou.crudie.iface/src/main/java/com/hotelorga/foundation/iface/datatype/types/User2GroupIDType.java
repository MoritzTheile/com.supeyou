package com.hotelorga.foundation.iface.datatype.types;

public class User2GroupIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public User2GroupIDType() {
	}

	public User2GroupIDType(Long value) {
		setValue(value);
	}

	public User2GroupIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}