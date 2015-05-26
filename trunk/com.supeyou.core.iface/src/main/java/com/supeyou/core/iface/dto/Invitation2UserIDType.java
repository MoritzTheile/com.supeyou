package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Invitation2UserIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Invitation2UserIDType() {
	}

	public Invitation2UserIDType(Long value) {
		setValue(value);
	}

	public Invitation2UserIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}