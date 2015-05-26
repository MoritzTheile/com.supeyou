package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class User2SupporterIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public User2SupporterIDType() {
	}

	public User2SupporterIDType(Long value) {
		setValue(value);
	}

	public User2SupporterIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}