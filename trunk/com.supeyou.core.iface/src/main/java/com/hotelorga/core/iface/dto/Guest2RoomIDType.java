package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.TypeException;

public class Guest2RoomIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Guest2RoomIDType() {

	}

	public Guest2RoomIDType(Long value) {
		setValue(value);
	}

	public Guest2RoomIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}