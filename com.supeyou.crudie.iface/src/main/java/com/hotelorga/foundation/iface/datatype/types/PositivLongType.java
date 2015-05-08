package com.hotelorga.foundation.iface.datatype.types;

public class PositivLongType extends AbstrType<Long> {

	private static final long serialVersionUID = 22172934525037L;

	public PositivLongType() {
	}

	public PositivLongType(Long value) {
		setValue(value);
	}

	public PositivLongType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {

		if (value == null) {
			throw new TypeException("value can't be null");
		}
		if (value.intValue() < 0) {
			throw new TypeException("value must be positiv (" + value + ")");
		}

	}

}