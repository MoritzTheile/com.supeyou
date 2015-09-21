package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class EventIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 820234979692L;

	public EventIDType() {

	}

	public EventIDType(Long value) {
		setValue(value);
	}

	public EventIDType(String stringValue) {
		setValue(new Long(stringValue));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}