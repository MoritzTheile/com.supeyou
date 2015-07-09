package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Hero2SupporterIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Hero2SupporterIDType() {
	}

	public Hero2SupporterIDType(Long value) {
		setValue(value);
	}

	public Hero2SupporterIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}