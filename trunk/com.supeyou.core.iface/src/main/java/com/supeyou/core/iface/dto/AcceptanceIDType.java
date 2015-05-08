package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class AcceptanceIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820928979692L;

	public AcceptanceIDType() {
	}

	public AcceptanceIDType(Long value) {
		setValue(value);
	}

	public AcceptanceIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}