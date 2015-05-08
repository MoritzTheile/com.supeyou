package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Guest2GuestGroupIDType extends AbstrType<Long> {

	public Guest2GuestGroupIDType() {

	}

	public Guest2GuestGroupIDType(Long value) {
		setValue(value);
	}

	public Guest2GuestGroupIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	private static final long serialVersionUID = 8269363895692L;

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}