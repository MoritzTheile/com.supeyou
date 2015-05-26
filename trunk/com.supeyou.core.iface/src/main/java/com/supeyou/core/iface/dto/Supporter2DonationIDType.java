package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.TypeException;

public class Supporter2DonationIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 8269363895692L;

	public Supporter2DonationIDType() {
	}

	public Supporter2DonationIDType(Long value) {
		setValue(value);
	}

	public Supporter2DonationIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}

	}

}