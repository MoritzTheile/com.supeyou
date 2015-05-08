package com.hotelorga.core.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hotelorga.foundation.iface.datatype.types.DateType;

public class STATICS {

	private static Logger log = Logger.getLogger(STATICS.class.getName());

	public static Long getDeltaDays(DateType from, DateType to) {

		try {
			long fromTime = new SimpleDateFormat(DateType.dateFormat).parse(from.value()).getTime();
			long toTime = new SimpleDateFormat(DateType.dateFormat).parse(to.value()).getTime();
			long deltaTime = toTime - fromTime;

			// adding half a day to be more robust:
			deltaTime = deltaTime + (12L * 60L * 60L * 1000L);

			return deltaTime / (24L * 60L * 60L * 1000L);

		} catch (ParseException e) {

			log.log(Level.WARNING, "problems on calculating delta days from=" + from + " to=" + to);
		}

		return null;

	}

	public static DateType incDateByDays(DateType date, int days) {

		try {

			long timestampOfDay = new SimpleDateFormat(DateType.dateFormat).parse(date.value()).getTime();

			// Attention days have different length (e.g. 27.10.2014=86400000, 26.10.2014=90000000)
			// so using the middle of the day:
			timestampOfDay += 12L * 60L * 60L * 1000L;

			return incDateByDays(timestampOfDay, days);

		} catch (ParseException e) {
			log.log(Level.WARNING, "problems on incrementing date=" + date + " by days=" + days);
		}

		return null;
	}

	public static DateType incDateByDays(long timestampOfDay, int days) {

		long daysInMillisecs = (24L * 60L * 60L * 1000L) * new Long(days);
		long incrementedTimestamp = daysInMillisecs + timestampOfDay;
		String incrementedDateString = new SimpleDateFormat(DateType.dateFormat).format(new Date(incrementedTimestamp));
		return new DateType(incrementedDateString);

	}

	public static Long getDateAsTimestamp(DateType date) {
		try {
			Date javaUtilDate = new SimpleDateFormat(DateType.dateFormat).parse(date.value());
			return javaUtilDate.getTime();
		} catch (ParseException e) {
			log.log(Level.WARNING, "problems on parsing date=" + date);
		}
		return null;
	}

	public static List<DateType> getDates(DateType from, DateType to) {

		// to have 'toDate' also in List;
		to = incDateByDays(to, 1);

		List<DateType> dates = new ArrayList<>();

		if (getDateAsTimestamp(from) >= getDateAsTimestamp(to)) {

			return dates;

		}

		DateType runningDate = from;

		while (!runningDate.equals(to)) {

			dates.add(runningDate);
			runningDate = incDateByDays(runningDate, 1);
		}

		return dates;
	}

	public static boolean isInTimeFrame(DateType date, DateType fromDate, DateType toDate) {

		return getDateAsTimestamp(date) >= getDateAsTimestamp(fromDate) &&
				getDateAsTimestamp(date) <= getDateAsTimestamp(toDate);

	}

	public static void main(String[] args) throws ParseException {

		// long timestampOfDay = new SimpleDateFormat(DateType.dateFormat).parse("2014-10-27").getTime();
		// long timestampOfNextDay = new SimpleDateFormat(DateType.dateFormat).parse("2014-10-28").getTime();
		//
		// System.out.println(timestampOfNextDay - timestampOfDay);

		System.out.println(incDateByDays(new DateType("2014-10-26"), 1));

	}
}
