package com.supeyou.crudie.iface.datatype.types;

public class GroupIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 82692692895692L;

	public GroupIDType() {
	}

	public GroupIDType(Long value) {
		setValue(value);
	}

	public GroupIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}