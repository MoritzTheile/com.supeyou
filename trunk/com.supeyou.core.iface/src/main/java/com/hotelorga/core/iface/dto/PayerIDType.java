package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.TypeException;

public class PayerIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820928979692L;

	public PayerIDType() {

	}

	public PayerIDType(Long value) {
		setValue(value);
	}

	public PayerIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}