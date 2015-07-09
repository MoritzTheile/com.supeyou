package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class User2HeroIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public User2HeroIDType() {
	}

	public User2HeroIDType(Long value) {
		setValue(value);
	}

	public User2HeroIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}