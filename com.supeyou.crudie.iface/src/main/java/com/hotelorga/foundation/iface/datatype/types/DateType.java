package com.hotelorga.foundation.iface.datatype.types;

public class DateType extends AbstrType<String> {

	private static final long serialVersionUID = 5867411380320491256L;

	public static final String dateFormat = "yyyy-MM-dd";

	public DateType() {
	}

	public DateType(String value) {
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

		String[] splittedDate = date.split("-");

		if (splittedDate.length != 3 || !splittedDate[0].matches("[0-9]{4}")) {
			throw new TypeException("Date must be of Format jjjj-mm-dd it was " + date);
		}

		if (date.equals("1904-02-29")) {
			return;
		}
		if (date.equals("1908-02-29")) {
			return;
		}
		if (date.equals("1912-02-29")) {
			return;
		}
		if (date.equals("1916-02-29")) {
			return;
		}
		if (date.equals("1920-02-29")) {
			return;
		}
		if (date.equals("1924-02-29")) {
			return;
		}
		if (date.equals("1928-02-29")) {
			return;
		}
		if (date.equals("1932-02-29")) {
			return;
		}
		if (date.equals("1936-02-29")) {
			return;
		}
		if (date.equals("1940-02-29")) {
			return;
		}
		if (date.equals("1944-02-29")) {
			return;
		}
		if (date.equals("1948-02-29")) {
			return;
		}
		if (date.equals("1952-02-29")) {
			return;
		}
		if (date.equals("1956-02-29")) {
			return;
		}
		if (date.equals("1960-02-29")) {
			return;
		}
		if (date.equals("1964-02-29")) {
			return;
		}
		if (date.equals("1968-02-29")) {
			return;
		}
		if (date.equals("1972-02-29")) {
			return;
		}
		if (date.equals("1976-02-29")) {
			return;
		}
		if (date.equals("1980-02-29")) {
			return;
		}
		if (date.equals("1984-02-29")) {
			return;
		}
		if (date.equals("1988-02-29")) {
			return;
		}
		if (date.equals("1992-02-29")) {
			return;
		}
		if (date.equals("1996-02-29")) {
			return;
		}
		if (date.equals("2004-02-29")) {
			return;
		}
		if (date.equals("2008-02-29")) {
			return;
		}
		if (date.equals("2012-02-29")) {
			return;
		}
		if (date.equals("2016-02-29")) {
			return;
		}
		if (date.equals("2020-02-29")) {
			return;
		}
		if (date.equals("2024-02-29")) {
			return;
		}
		if (date.equals("2028-02-29")) {
			return;
		}
		if (date.equals("2032-02-29")) {
			return;
		}
		if (date.equals("2036-02-29")) {
			return;
		}
		if (date.equals("2040-02-29")) {
			return;
		}
		if (date.equals("2044-02-29")) {
			return;
		}
		if (date.equals("2048-02-29")) {
			return;
		}
		if (date.equals("2052-02-29")) {
			return;
		}
		if (date.equals("2056-02-29")) {
			return;
		}
		if (date.equals("2060-02-29")) {
			return;
		}
		if (date.equals("2064-02-29")) {
			return;
		}
		if (date.equals("2068-02-29")) {
			return;
		}
		if (date.equals("2072-02-29")) {
			return;
		}
		if (date.equals("2076-02-29")) {
			return;
		}
		if (date.equals("2080-02-29")) {
			return;
		}
		if (date.equals("2084-02-29")) {
			return;
		}
		if (date.equals("2088-02-29")) {
			return;
		}
		if (date.equals("2092-02-29")) {
			return;
		}
		if (date.equals("2096-02-29")) {
			return;
		}

		String dayAndMonth = "-" + splittedDate[1] + "-" + splittedDate[2];

		if (dayAndMonth.equals("-01-01")) {
			return;
		}
		if (dayAndMonth.equals("-01-02")) {
			return;
		}
		if (dayAndMonth.equals("-01-03")) {
			return;
		}
		if (dayAndMonth.equals("-01-04")) {
			return;
		}
		if (dayAndMonth.equals("-01-05")) {
			return;
		}
		if (dayAndMonth.equals("-01-06")) {
			return;
		}
		if (dayAndMonth.equals("-01-07")) {
			return;
		}
		if (dayAndMonth.equals("-01-08")) {
			return;
		}
		if (dayAndMonth.equals("-01-09")) {
			return;
		}
		if (dayAndMonth.equals("-01-10")) {
			return;
		}
		if (dayAndMonth.equals("-01-11")) {
			return;
		}
		if (dayAndMonth.equals("-01-12")) {
			return;
		}
		if (dayAndMonth.equals("-01-13")) {
			return;
		}
		if (dayAndMonth.equals("-01-14")) {
			return;
		}
		if (dayAndMonth.equals("-01-15")) {
			return;
		}
		if (dayAndMonth.equals("-01-16")) {
			return;
		}
		if (dayAndMonth.equals("-01-17")) {
			return;
		}
		if (dayAndMonth.equals("-01-18")) {
			return;
		}
		if (dayAndMonth.equals("-01-19")) {
			return;
		}
		if (dayAndMonth.equals("-01-20")) {
			return;
		}
		if (dayAndMonth.equals("-01-21")) {
			return;
		}
		if (dayAndMonth.equals("-01-22")) {
			return;
		}
		if (dayAndMonth.equals("-01-23")) {
			return;
		}
		if (dayAndMonth.equals("-01-24")) {
			return;
		}
		if (dayAndMonth.equals("-01-25")) {
			return;
		}
		if (dayAndMonth.equals("-01-26")) {
			return;
		}
		if (dayAndMonth.equals("-01-27")) {
			return;
		}
		if (dayAndMonth.equals("-01-28")) {
			return;
		}
		if (dayAndMonth.equals("-01-29")) {
			return;
		}
		if (dayAndMonth.equals("-01-30")) {
			return;
		}
		if (dayAndMonth.equals("-01-31")) {
			return;
		}
		if (dayAndMonth.equals("-02-01")) {
			return;
		}
		if (dayAndMonth.equals("-02-02")) {
			return;
		}
		if (dayAndMonth.equals("-02-03")) {
			return;
		}
		if (dayAndMonth.equals("-02-04")) {
			return;
		}
		if (dayAndMonth.equals("-02-05")) {
			return;
		}
		if (dayAndMonth.equals("-02-06")) {
			return;
		}
		if (dayAndMonth.equals("-02-07")) {
			return;
		}
		if (dayAndMonth.equals("-02-08")) {
			return;
		}
		if (dayAndMonth.equals("-02-09")) {
			return;
		}
		if (dayAndMonth.equals("-02-10")) {
			return;
		}
		if (dayAndMonth.equals("-02-11")) {
			return;
		}
		if (dayAndMonth.equals("-02-12")) {
			return;
		}
		if (dayAndMonth.equals("-02-13")) {
			return;
		}
		if (dayAndMonth.equals("-02-14")) {
			return;
		}
		if (dayAndMonth.equals("-02-15")) {
			return;
		}
		if (dayAndMonth.equals("-02-16")) {
			return;
		}
		if (dayAndMonth.equals("-02-17")) {
			return;
		}
		if (dayAndMonth.equals("-02-18")) {
			return;
		}
		if (dayAndMonth.equals("-02-19")) {
			return;
		}
		if (dayAndMonth.equals("-02-20")) {
			return;
		}
		if (dayAndMonth.equals("-02-21")) {
			return;
		}
		if (dayAndMonth.equals("-02-22")) {
			return;
		}
		if (dayAndMonth.equals("-02-23")) {
			return;
		}
		if (dayAndMonth.equals("-02-24")) {
			return;
		}
		if (dayAndMonth.equals("-02-25")) {
			return;
		}
		if (dayAndMonth.equals("-02-26")) {
			return;
		}
		if (dayAndMonth.equals("-02-27")) {
			return;
		}
		if (dayAndMonth.equals("-02-28")) {
			return;
		}
		if (dayAndMonth.equals("-03-01")) {
			return;
		}
		if (dayAndMonth.equals("-03-02")) {
			return;
		}
		if (dayAndMonth.equals("-03-03")) {
			return;
		}
		if (dayAndMonth.equals("-03-04")) {
			return;
		}
		if (dayAndMonth.equals("-03-05")) {
			return;
		}
		if (dayAndMonth.equals("-03-06")) {
			return;
		}
		if (dayAndMonth.equals("-03-07")) {
			return;
		}
		if (dayAndMonth.equals("-03-08")) {
			return;
		}
		if (dayAndMonth.equals("-03-09")) {
			return;
		}
		if (dayAndMonth.equals("-03-10")) {
			return;
		}
		if (dayAndMonth.equals("-03-11")) {
			return;
		}
		if (dayAndMonth.equals("-03-12")) {
			return;
		}
		if (dayAndMonth.equals("-03-13")) {
			return;
		}
		if (dayAndMonth.equals("-03-14")) {
			return;
		}
		if (dayAndMonth.equals("-03-15")) {
			return;
		}
		if (dayAndMonth.equals("-03-16")) {
			return;
		}
		if (dayAndMonth.equals("-03-17")) {
			return;
		}
		if (dayAndMonth.equals("-03-18")) {
			return;
		}
		if (dayAndMonth.equals("-03-19")) {
			return;
		}
		if (dayAndMonth.equals("-03-20")) {
			return;
		}
		if (dayAndMonth.equals("-03-21")) {
			return;
		}
		if (dayAndMonth.equals("-03-22")) {
			return;
		}
		if (dayAndMonth.equals("-03-23")) {
			return;
		}
		if (dayAndMonth.equals("-03-24")) {
			return;
		}
		if (dayAndMonth.equals("-03-25")) {
			return;
		}
		if (dayAndMonth.equals("-03-26")) {
			return;
		}
		if (dayAndMonth.equals("-03-27")) {
			return;
		}
		if (dayAndMonth.equals("-03-28")) {
			return;
		}
		if (dayAndMonth.equals("-03-29")) {
			return;
		}
		if (dayAndMonth.equals("-03-30")) {
			return;
		}
		if (dayAndMonth.equals("-03-31")) {
			return;
		}
		if (dayAndMonth.equals("-04-01")) {
			return;
		}
		if (dayAndMonth.equals("-04-02")) {
			return;
		}
		if (dayAndMonth.equals("-04-03")) {
			return;
		}
		if (dayAndMonth.equals("-04-04")) {
			return;
		}
		if (dayAndMonth.equals("-04-05")) {
			return;
		}
		if (dayAndMonth.equals("-04-06")) {
			return;
		}
		if (dayAndMonth.equals("-04-07")) {
			return;
		}
		if (dayAndMonth.equals("-04-08")) {
			return;
		}
		if (dayAndMonth.equals("-04-09")) {
			return;
		}
		if (dayAndMonth.equals("-04-10")) {
			return;
		}
		if (dayAndMonth.equals("-04-11")) {
			return;
		}
		if (dayAndMonth.equals("-04-12")) {
			return;
		}
		if (dayAndMonth.equals("-04-13")) {
			return;
		}
		if (dayAndMonth.equals("-04-14")) {
			return;
		}
		if (dayAndMonth.equals("-04-15")) {
			return;
		}
		if (dayAndMonth.equals("-04-16")) {
			return;
		}
		if (dayAndMonth.equals("-04-17")) {
			return;
		}
		if (dayAndMonth.equals("-04-18")) {
			return;
		}
		if (dayAndMonth.equals("-04-19")) {
			return;
		}
		if (dayAndMonth.equals("-04-20")) {
			return;
		}
		if (dayAndMonth.equals("-04-21")) {
			return;
		}
		if (dayAndMonth.equals("-04-22")) {
			return;
		}
		if (dayAndMonth.equals("-04-23")) {
			return;
		}
		if (dayAndMonth.equals("-04-24")) {
			return;
		}
		if (dayAndMonth.equals("-04-25")) {
			return;
		}
		if (dayAndMonth.equals("-04-26")) {
			return;
		}
		if (dayAndMonth.equals("-04-27")) {
			return;
		}
		if (dayAndMonth.equals("-04-28")) {
			return;
		}
		if (dayAndMonth.equals("-04-29")) {
			return;
		}
		if (dayAndMonth.equals("-04-30")) {
			return;
		}
		if (dayAndMonth.equals("-05-01")) {
			return;
		}
		if (dayAndMonth.equals("-05-02")) {
			return;
		}
		if (dayAndMonth.equals("-05-03")) {
			return;
		}
		if (dayAndMonth.equals("-05-04")) {
			return;
		}
		if (dayAndMonth.equals("-05-05")) {
			return;
		}
		if (dayAndMonth.equals("-05-06")) {
			return;
		}
		if (dayAndMonth.equals("-05-07")) {
			return;
		}
		if (dayAndMonth.equals("-05-08")) {
			return;
		}
		if (dayAndMonth.equals("-05-09")) {
			return;
		}
		if (dayAndMonth.equals("-05-10")) {
			return;
		}
		if (dayAndMonth.equals("-05-11")) {
			return;
		}
		if (dayAndMonth.equals("-05-12")) {
			return;
		}
		if (dayAndMonth.equals("-05-13")) {
			return;
		}
		if (dayAndMonth.equals("-05-14")) {
			return;
		}
		if (dayAndMonth.equals("-05-15")) {
			return;
		}
		if (dayAndMonth.equals("-05-16")) {
			return;
		}
		if (dayAndMonth.equals("-05-17")) {
			return;
		}
		if (dayAndMonth.equals("-05-18")) {
			return;
		}
		if (dayAndMonth.equals("-05-19")) {
			return;
		}
		if (dayAndMonth.equals("-05-20")) {
			return;
		}
		if (dayAndMonth.equals("-05-21")) {
			return;
		}
		if (dayAndMonth.equals("-05-22")) {
			return;
		}
		if (dayAndMonth.equals("-05-23")) {
			return;
		}
		if (dayAndMonth.equals("-05-24")) {
			return;
		}
		if (dayAndMonth.equals("-05-25")) {
			return;
		}
		if (dayAndMonth.equals("-05-26")) {
			return;
		}
		if (dayAndMonth.equals("-05-27")) {
			return;
		}
		if (dayAndMonth.equals("-05-28")) {
			return;
		}
		if (dayAndMonth.equals("-05-29")) {
			return;
		}
		if (dayAndMonth.equals("-05-30")) {
			return;
		}
		if (dayAndMonth.equals("-05-31")) {
			return;
		}
		if (dayAndMonth.equals("-06-01")) {
			return;
		}
		if (dayAndMonth.equals("-06-02")) {
			return;
		}
		if (dayAndMonth.equals("-06-03")) {
			return;
		}
		if (dayAndMonth.equals("-06-04")) {
			return;
		}
		if (dayAndMonth.equals("-06-05")) {
			return;
		}
		if (dayAndMonth.equals("-06-06")) {
			return;
		}
		if (dayAndMonth.equals("-06-07")) {
			return;
		}
		if (dayAndMonth.equals("-06-08")) {
			return;
		}
		if (dayAndMonth.equals("-06-09")) {
			return;
		}
		if (dayAndMonth.equals("-06-10")) {
			return;
		}
		if (dayAndMonth.equals("-06-11")) {
			return;
		}
		if (dayAndMonth.equals("-06-12")) {
			return;
		}
		if (dayAndMonth.equals("-06-13")) {
			return;
		}
		if (dayAndMonth.equals("-06-14")) {
			return;
		}
		if (dayAndMonth.equals("-06-15")) {
			return;
		}
		if (dayAndMonth.equals("-06-16")) {
			return;
		}
		if (dayAndMonth.equals("-06-17")) {
			return;
		}
		if (dayAndMonth.equals("-06-18")) {
			return;
		}
		if (dayAndMonth.equals("-06-19")) {
			return;
		}
		if (dayAndMonth.equals("-06-20")) {
			return;
		}
		if (dayAndMonth.equals("-06-21")) {
			return;
		}
		if (dayAndMonth.equals("-06-22")) {
			return;
		}
		if (dayAndMonth.equals("-06-23")) {
			return;
		}
		if (dayAndMonth.equals("-06-24")) {
			return;
		}
		if (dayAndMonth.equals("-06-25")) {
			return;
		}
		if (dayAndMonth.equals("-06-26")) {
			return;
		}
		if (dayAndMonth.equals("-06-27")) {
			return;
		}
		if (dayAndMonth.equals("-06-28")) {
			return;
		}
		if (dayAndMonth.equals("-06-29")) {
			return;
		}
		if (dayAndMonth.equals("-06-30")) {
			return;
		}
		if (dayAndMonth.equals("-07-01")) {
			return;
		}
		if (dayAndMonth.equals("-07-02")) {
			return;
		}
		if (dayAndMonth.equals("-07-03")) {
			return;
		}
		if (dayAndMonth.equals("-07-04")) {
			return;
		}
		if (dayAndMonth.equals("-07-05")) {
			return;
		}
		if (dayAndMonth.equals("-07-06")) {
			return;
		}
		if (dayAndMonth.equals("-07-07")) {
			return;
		}
		if (dayAndMonth.equals("-07-08")) {
			return;
		}
		if (dayAndMonth.equals("-07-09")) {
			return;
		}
		if (dayAndMonth.equals("-07-10")) {
			return;
		}
		if (dayAndMonth.equals("-07-11")) {
			return;
		}
		if (dayAndMonth.equals("-07-12")) {
			return;
		}
		if (dayAndMonth.equals("-07-13")) {
			return;
		}
		if (dayAndMonth.equals("-07-14")) {
			return;
		}
		if (dayAndMonth.equals("-07-15")) {
			return;
		}
		if (dayAndMonth.equals("-07-16")) {
			return;
		}
		if (dayAndMonth.equals("-07-17")) {
			return;
		}
		if (dayAndMonth.equals("-07-18")) {
			return;
		}
		if (dayAndMonth.equals("-07-19")) {
			return;
		}
		if (dayAndMonth.equals("-07-20")) {
			return;
		}
		if (dayAndMonth.equals("-07-21")) {
			return;
		}
		if (dayAndMonth.equals("-07-22")) {
			return;
		}
		if (dayAndMonth.equals("-07-23")) {
			return;
		}
		if (dayAndMonth.equals("-07-24")) {
			return;
		}
		if (dayAndMonth.equals("-07-25")) {
			return;
		}
		if (dayAndMonth.equals("-07-26")) {
			return;
		}
		if (dayAndMonth.equals("-07-27")) {
			return;
		}
		if (dayAndMonth.equals("-07-28")) {
			return;
		}
		if (dayAndMonth.equals("-07-29")) {
			return;
		}
		if (dayAndMonth.equals("-07-30")) {
			return;
		}
		if (dayAndMonth.equals("-07-31")) {
			return;
		}
		if (dayAndMonth.equals("-08-01")) {
			return;
		}
		if (dayAndMonth.equals("-08-02")) {
			return;
		}
		if (dayAndMonth.equals("-08-03")) {
			return;
		}
		if (dayAndMonth.equals("-08-04")) {
			return;
		}
		if (dayAndMonth.equals("-08-05")) {
			return;
		}
		if (dayAndMonth.equals("-08-06")) {
			return;
		}
		if (dayAndMonth.equals("-08-07")) {
			return;
		}
		if (dayAndMonth.equals("-08-08")) {
			return;
		}
		if (dayAndMonth.equals("-08-09")) {
			return;
		}
		if (dayAndMonth.equals("-08-10")) {
			return;
		}
		if (dayAndMonth.equals("-08-11")) {
			return;
		}
		if (dayAndMonth.equals("-08-12")) {
			return;
		}
		if (dayAndMonth.equals("-08-13")) {
			return;
		}
		if (dayAndMonth.equals("-08-14")) {
			return;
		}
		if (dayAndMonth.equals("-08-15")) {
			return;
		}
		if (dayAndMonth.equals("-08-16")) {
			return;
		}
		if (dayAndMonth.equals("-08-17")) {
			return;
		}
		if (dayAndMonth.equals("-08-18")) {
			return;
		}
		if (dayAndMonth.equals("-08-19")) {
			return;
		}
		if (dayAndMonth.equals("-08-20")) {
			return;
		}
		if (dayAndMonth.equals("-08-21")) {
			return;
		}
		if (dayAndMonth.equals("-08-22")) {
			return;
		}
		if (dayAndMonth.equals("-08-23")) {
			return;
		}
		if (dayAndMonth.equals("-08-24")) {
			return;
		}
		if (dayAndMonth.equals("-08-25")) {
			return;
		}
		if (dayAndMonth.equals("-08-26")) {
			return;
		}
		if (dayAndMonth.equals("-08-27")) {
			return;
		}
		if (dayAndMonth.equals("-08-28")) {
			return;
		}
		if (dayAndMonth.equals("-08-29")) {
			return;
		}
		if (dayAndMonth.equals("-08-30")) {
			return;
		}
		if (dayAndMonth.equals("-08-31")) {
			return;
		}
		if (dayAndMonth.equals("-09-01")) {
			return;
		}
		if (dayAndMonth.equals("-09-02")) {
			return;
		}
		if (dayAndMonth.equals("-09-03")) {
			return;
		}
		if (dayAndMonth.equals("-09-04")) {
			return;
		}
		if (dayAndMonth.equals("-09-05")) {
			return;
		}
		if (dayAndMonth.equals("-09-06")) {
			return;
		}
		if (dayAndMonth.equals("-09-07")) {
			return;
		}
		if (dayAndMonth.equals("-09-08")) {
			return;
		}
		if (dayAndMonth.equals("-09-09")) {
			return;
		}
		if (dayAndMonth.equals("-09-10")) {
			return;
		}
		if (dayAndMonth.equals("-09-11")) {
			return;
		}
		if (dayAndMonth.equals("-09-12")) {
			return;
		}
		if (dayAndMonth.equals("-09-13")) {
			return;
		}
		if (dayAndMonth.equals("-09-14")) {
			return;
		}
		if (dayAndMonth.equals("-09-15")) {
			return;
		}
		if (dayAndMonth.equals("-09-16")) {
			return;
		}
		if (dayAndMonth.equals("-09-17")) {
			return;
		}
		if (dayAndMonth.equals("-09-18")) {
			return;
		}
		if (dayAndMonth.equals("-09-19")) {
			return;
		}
		if (dayAndMonth.equals("-09-20")) {
			return;
		}
		if (dayAndMonth.equals("-09-21")) {
			return;
		}
		if (dayAndMonth.equals("-09-22")) {
			return;
		}
		if (dayAndMonth.equals("-09-23")) {
			return;
		}
		if (dayAndMonth.equals("-09-24")) {
			return;
		}
		if (dayAndMonth.equals("-09-25")) {
			return;
		}
		if (dayAndMonth.equals("-09-26")) {
			return;
		}
		if (dayAndMonth.equals("-09-27")) {
			return;
		}
		if (dayAndMonth.equals("-09-28")) {
			return;
		}
		if (dayAndMonth.equals("-09-29")) {
			return;
		}
		if (dayAndMonth.equals("-09-30")) {
			return;
		}
		if (dayAndMonth.equals("-10-01")) {
			return;
		}
		if (dayAndMonth.equals("-10-02")) {
			return;
		}
		if (dayAndMonth.equals("-10-03")) {
			return;
		}
		if (dayAndMonth.equals("-10-04")) {
			return;
		}
		if (dayAndMonth.equals("-10-05")) {
			return;
		}
		if (dayAndMonth.equals("-10-06")) {
			return;
		}
		if (dayAndMonth.equals("-10-07")) {
			return;
		}
		if (dayAndMonth.equals("-10-08")) {
			return;
		}
		if (dayAndMonth.equals("-10-09")) {
			return;
		}
		if (dayAndMonth.equals("-10-10")) {
			return;
		}
		if (dayAndMonth.equals("-10-11")) {
			return;
		}
		if (dayAndMonth.equals("-10-12")) {
			return;
		}
		if (dayAndMonth.equals("-10-13")) {
			return;
		}
		if (dayAndMonth.equals("-10-14")) {
			return;
		}
		if (dayAndMonth.equals("-10-15")) {
			return;
		}
		if (dayAndMonth.equals("-10-16")) {
			return;
		}
		if (dayAndMonth.equals("-10-17")) {
			return;
		}
		if (dayAndMonth.equals("-10-18")) {
			return;
		}
		if (dayAndMonth.equals("-10-19")) {
			return;
		}
		if (dayAndMonth.equals("-10-20")) {
			return;
		}
		if (dayAndMonth.equals("-10-21")) {
			return;
		}
		if (dayAndMonth.equals("-10-22")) {
			return;
		}
		if (dayAndMonth.equals("-10-23")) {
			return;
		}
		if (dayAndMonth.equals("-10-24")) {
			return;
		}
		if (dayAndMonth.equals("-10-25")) {
			return;
		}
		if (dayAndMonth.equals("-10-26")) {
			return;
		}
		if (dayAndMonth.equals("-10-27")) {
			return;
		}
		if (dayAndMonth.equals("-10-28")) {
			return;
		}
		if (dayAndMonth.equals("-10-29")) {
			return;
		}
		if (dayAndMonth.equals("-10-30")) {
			return;
		}
		if (dayAndMonth.equals("-10-31")) {
			return;
		}
		if (dayAndMonth.equals("-11-01")) {
			return;
		}
		if (dayAndMonth.equals("-11-02")) {
			return;
		}
		if (dayAndMonth.equals("-11-03")) {
			return;
		}
		if (dayAndMonth.equals("-11-04")) {
			return;
		}
		if (dayAndMonth.equals("-11-05")) {
			return;
		}
		if (dayAndMonth.equals("-11-06")) {
			return;
		}
		if (dayAndMonth.equals("-11-07")) {
			return;
		}
		if (dayAndMonth.equals("-11-08")) {
			return;
		}
		if (dayAndMonth.equals("-11-09")) {
			return;
		}
		if (dayAndMonth.equals("-11-10")) {
			return;
		}
		if (dayAndMonth.equals("-11-11")) {
			return;
		}
		if (dayAndMonth.equals("-11-12")) {
			return;
		}
		if (dayAndMonth.equals("-11-13")) {
			return;
		}
		if (dayAndMonth.equals("-11-14")) {
			return;
		}
		if (dayAndMonth.equals("-11-15")) {
			return;
		}
		if (dayAndMonth.equals("-11-16")) {
			return;
		}
		if (dayAndMonth.equals("-11-17")) {
			return;
		}
		if (dayAndMonth.equals("-11-18")) {
			return;
		}
		if (dayAndMonth.equals("-11-19")) {
			return;
		}
		if (dayAndMonth.equals("-11-20")) {
			return;
		}
		if (dayAndMonth.equals("-11-21")) {
			return;
		}
		if (dayAndMonth.equals("-11-22")) {
			return;
		}
		if (dayAndMonth.equals("-11-23")) {
			return;
		}
		if (dayAndMonth.equals("-11-24")) {
			return;
		}
		if (dayAndMonth.equals("-11-25")) {
			return;
		}
		if (dayAndMonth.equals("-11-26")) {
			return;
		}
		if (dayAndMonth.equals("-11-27")) {
			return;
		}
		if (dayAndMonth.equals("-11-28")) {
			return;
		}
		if (dayAndMonth.equals("-11-29")) {
			return;
		}
		if (dayAndMonth.equals("-11-30")) {
			return;
		}
		if (dayAndMonth.equals("-12-01")) {
			return;
		}
		if (dayAndMonth.equals("-12-02")) {
			return;
		}
		if (dayAndMonth.equals("-12-03")) {
			return;
		}
		if (dayAndMonth.equals("-12-04")) {
			return;
		}
		if (dayAndMonth.equals("-12-05")) {
			return;
		}
		if (dayAndMonth.equals("-12-06")) {
			return;
		}
		if (dayAndMonth.equals("-12-07")) {
			return;
		}
		if (dayAndMonth.equals("-12-08")) {
			return;
		}
		if (dayAndMonth.equals("-12-09")) {
			return;
		}
		if (dayAndMonth.equals("-12-10")) {
			return;
		}
		if (dayAndMonth.equals("-12-11")) {
			return;
		}
		if (dayAndMonth.equals("-12-12")) {
			return;
		}
		if (dayAndMonth.equals("-12-13")) {
			return;
		}
		if (dayAndMonth.equals("-12-14")) {
			return;
		}
		if (dayAndMonth.equals("-12-15")) {
			return;
		}
		if (dayAndMonth.equals("-12-16")) {
			return;
		}
		if (dayAndMonth.equals("-12-17")) {
			return;
		}
		if (dayAndMonth.equals("-12-18")) {
			return;
		}
		if (dayAndMonth.equals("-12-19")) {
			return;
		}
		if (dayAndMonth.equals("-12-20")) {
			return;
		}
		if (dayAndMonth.equals("-12-21")) {
			return;
		}
		if (dayAndMonth.equals("-12-22")) {
			return;
		}
		if (dayAndMonth.equals("-12-23")) {
			return;
		}
		if (dayAndMonth.equals("-12-24")) {
			return;
		}
		if (dayAndMonth.equals("-12-25")) {
			return;
		}
		if (dayAndMonth.equals("-12-26")) {
			return;
		}
		if (dayAndMonth.equals("-12-27")) {
			return;
		}
		if (dayAndMonth.equals("-12-28")) {
			return;
		}
		if (dayAndMonth.equals("-12-29")) {
			return;
		}
		if (dayAndMonth.equals("-12-30")) {
			return;
		}
		if (dayAndMonth.equals("-12-31")) {
			return;
		}

		throw new TypeException("Day not known " + date);

	}

}
