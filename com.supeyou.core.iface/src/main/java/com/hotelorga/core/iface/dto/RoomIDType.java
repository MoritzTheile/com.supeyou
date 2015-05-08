package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.TypeException;

public class RoomIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820234979692L;

	public RoomIDType() {

	}

	public RoomIDType(Long value) {
		setValue(value);
	}

	public RoomIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}