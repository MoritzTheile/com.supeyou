package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class ActingUser2SessionIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820234979692L;

	public ActingUser2SessionIDType() {

	}

	public ActingUser2SessionIDType(Long value) {
		setValue(value);
	}

	public ActingUser2SessionIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}