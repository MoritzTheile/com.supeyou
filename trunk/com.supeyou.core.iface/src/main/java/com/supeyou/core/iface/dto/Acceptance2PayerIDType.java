package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Acceptance2PayerIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Acceptance2PayerIDType() {
	}

	public Acceptance2PayerIDType(Long value) {
		setValue(value);
	}

	public Acceptance2PayerIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}