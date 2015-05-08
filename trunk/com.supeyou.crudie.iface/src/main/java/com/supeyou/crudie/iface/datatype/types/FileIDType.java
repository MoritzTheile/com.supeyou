package com.supeyou.crudie.iface.datatype.types;

public class FileIDType extends AbstrType<Long> {

	private static final long serialVersionUID = 569296690996765037L;

	public FileIDType() {
	}

	public FileIDType(Long value) {
		setValue(value);
	}

	public FileIDType(String valueAsString) {
		setValue(new Long(valueAsString));
	}

	@Override
	protected void isValid(Long value) throws TypeException {
		if (value == null) {
			throw new TypeException("null is not allowed");
		}
	}

}