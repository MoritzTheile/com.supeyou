package com.supeyou.crudie.iface.datatype.types;

/**
 * Amount is always given as cents.
 * 
 * @author MoritzTheile
 * 
 */
public class AmountType extends AbstrType<Integer> {

	private static final long serialVersionUID = 6015926059842613507L;

	public AmountType() {
	}

	public AmountType(Integer value) {
		setValue(value);
	}

	public AmountType(String stringValue) {
		setValue(new Integer(stringValue));
	}

	@Override
	protected void isValid(Integer value) throws TypeException {
		// always valid
	}

}
