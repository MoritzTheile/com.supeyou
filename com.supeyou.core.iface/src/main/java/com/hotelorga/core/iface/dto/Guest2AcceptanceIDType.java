package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.TypeException;

public class Guest2AcceptanceIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Guest2AcceptanceIDType() {
	}

	public Guest2AcceptanceIDType(Long value) {
		setValue(value);
	}

	public Guest2AcceptanceIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}