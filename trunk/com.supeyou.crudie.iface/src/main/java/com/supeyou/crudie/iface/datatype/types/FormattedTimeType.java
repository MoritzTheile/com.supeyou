package com.supeyou.crudie.iface.datatype.types;

public class FormattedTimeType extends AbstrType<String> {

	private static final long serialVersionUID = 5867411380320491256L;

	public static final String dateFormat = "yyyy-MM-dd_HH:mm:ss.SSS";

	public FormattedTimeType() {
	}

	public FormattedTimeType(String value) {
		setValue(value);
	}

	@Override
	protected void isValid(String date) throws TypeException {
		validDateString(date);
	}

	/**
	 * knows valid dates for 20th and 21th century
	 * 
	 * @param date
	 */
	private static void validDateString(String date) {

		if (!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}_[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}")) {
			throw new TypeException("Date must be of Format " + dateFormat + " it was " + date);
		}
	}

	public static void main(String[] args) {
		validDateString("2012-02-02_14:53:03.8032");
	}
}
