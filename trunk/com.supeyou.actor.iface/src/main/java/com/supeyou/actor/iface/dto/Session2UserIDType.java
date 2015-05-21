package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Session2UserIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820234979692L;

	public Session2UserIDType() {

	}

	public Session2UserIDType(Long value) {
		setValue(value);
	}

	public Session2UserIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}