package com.supeyou.actor.iface.datatype.enums;

import com.supeyou.crudie.iface.datatype.types.DateType;

public enum MONTH {

	OKT2014("Okt. 2014", new DateType("2014-10-01"), new DateType("2014-10-31")),
	NOV2014("Nov. 2014", new DateType("2014-11-01"), new DateType("2014-11-30")),
	DEZ2014("Dez. 2014", new DateType("2014-12-01"), new DateType("2014-12-31")),
	JAN2015("Jan. 2015", new DateType("2015-01-01"), new DateType("2015-01-31")),
	FEB2015("Feb. 2015", new DateType("2015-02-01"), new DateType("2015-02-28")),
	MRZ2015("Mrz. 2015", new DateType("2015-03-01"), new DateType("2015-03-31")),
	APR2015("Apr. 2015", new DateType("2015-04-01"), new DateType("2015-04-30")),
	MAI2015("Mai  2015", new DateType("2015-05-01"), new DateType("2015-05-31")),
	JUN2015("Jun. 2015", new DateType("2015-06-01"), new DateType("2015-06-30")),
	JUL2015("Jul. 2015", new DateType("2015-07-01"), new DateType("2015-07-31")),
	AUG2015("Aug. 2015", new DateType("2015-08-01"), new DateType("2015-08-31")),
	SEP2015("Sep. 2015", new DateType("2015-09-01"), new DateType("2015-09-30")),
	OKT2015("Okt. 2015", new DateType("2015-10-01"), new DateType("2015-10-31")),
	NOV2015("Nov. 2015", new DateType("2015-11-01"), new DateType("2015-11-30")),
	DEZ2015("Dez. 2015", new DateType("2015-12-01"), new DateType("2015-12-31"));

	public final String label;
	public final DateType from;
	public final DateType to;

	private MONTH(String label, DateType from, DateType to) {
		this.label = label;
		this.from = from;
		this.to = to;
	}
}