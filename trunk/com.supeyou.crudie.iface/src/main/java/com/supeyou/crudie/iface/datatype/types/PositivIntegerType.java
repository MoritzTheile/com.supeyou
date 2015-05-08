package com.supeyou.crudie.iface.datatype.types;

public class PositivIntegerType extends AbstrType<Integer> {

	private static final long serialVersionUID = 2217296690996765037L;

	public PositivIntegerType() {
	}

	public PositivIntegerType(Integer value) {
		setValue(value);
	}

	public PositivIntegerType(String stringValue) {
		setValue(new Integer(stringValue));
	}

	@Override
	protected void isValid(Integer value) throws TypeException {

		if (value == null) {
			throw new TypeException("value can't be null");
		}
		if (value.intValue() < 0) {
			throw new TypeException("value must be positiv (" + value + ")");
		}

	}

}