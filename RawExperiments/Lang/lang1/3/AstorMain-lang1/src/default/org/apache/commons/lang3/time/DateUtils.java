package org.apache.commons.lang3.time;


public class DateUtils {
	public static final long MILLIS_PER_SECOND = 1000;

	public static final long MILLIS_PER_MINUTE = 60 * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_SECOND);

	public static final long MILLIS_PER_HOUR = 60 * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE);

	public static final long MILLIS_PER_DAY = 24 * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR);

	public static final int SEMI_MONTH = 1001;

	private static final int[][] fields = new int[][]{ new int[]{ java.util.Calendar.MILLISECOND } , new int[]{ java.util.Calendar.SECOND } , new int[]{ java.util.Calendar.MINUTE } , new int[]{ java.util.Calendar.HOUR_OF_DAY , java.util.Calendar.HOUR } , new int[]{ java.util.Calendar.DATE , java.util.Calendar.DAY_OF_MONTH , java.util.Calendar.AM_PM } , new int[]{ java.util.Calendar.MONTH , org.apache.commons.lang3.time.DateUtils.SEMI_MONTH } , new int[]{ java.util.Calendar.YEAR } , new int[]{ java.util.Calendar.ERA } };

	public static final int RANGE_WEEK_SUNDAY = 1;

	public static final int RANGE_WEEK_MONDAY = 2;

	public static final int RANGE_WEEK_RELATIVE = 3;

	public static final int RANGE_WEEK_CENTER = 4;

	public static final int RANGE_MONTH_SUNDAY = 5;

	public static final int RANGE_MONTH_MONDAY = 6;

	private static final int MODIFY_TRUNCATE = 0;

	private static final int MODIFY_ROUND = 1;

	private static final int MODIFY_CEILING = 2;

	public DateUtils() {
		super();
	}

	public static boolean isSameDay(final java.util.Date date1, final java.util.Date date2) {
		if ((date1 == null) || (date2 == null)) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar cal1 = java.util.Calendar.getInstance();
		cal1.setTime(date1);
		final java.util.Calendar cal2 = java.util.Calendar.getInstance();
		cal2.setTime(date2);
		return org.apache.commons.lang3.time.DateUtils.isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(final java.util.Calendar cal1, final java.util.Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		return (((cal1.get(java.util.Calendar.ERA)) == (cal2.get(java.util.Calendar.ERA))) && ((cal1.get(java.util.Calendar.YEAR)) == (cal2.get(java.util.Calendar.YEAR)))) && ((cal1.get(java.util.Calendar.DAY_OF_YEAR)) == (cal2.get(java.util.Calendar.DAY_OF_YEAR)));
	}

	public static boolean isSameInstant(final java.util.Date date1, final java.util.Date date2) {
		if ((date1 == null) || (date2 == null)) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		return (date1.getTime()) == (date2.getTime());
	}

	public static boolean isSameInstant(final java.util.Calendar cal1, final java.util.Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		return (cal1.getTime().getTime()) == (cal2.getTime().getTime());
	}

	public static boolean isSameLocalTime(final java.util.Calendar cal1, final java.util.Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		return ((((((((cal1.get(java.util.Calendar.MILLISECOND)) == (cal2.get(java.util.Calendar.MILLISECOND))) && ((cal1.get(java.util.Calendar.SECOND)) == (cal2.get(java.util.Calendar.SECOND)))) && ((cal1.get(java.util.Calendar.MINUTE)) == (cal2.get(java.util.Calendar.MINUTE)))) && ((cal1.get(java.util.Calendar.HOUR_OF_DAY)) == (cal2.get(java.util.Calendar.HOUR_OF_DAY)))) && ((cal1.get(java.util.Calendar.DAY_OF_YEAR)) == (cal2.get(java.util.Calendar.DAY_OF_YEAR)))) && ((cal1.get(java.util.Calendar.YEAR)) == (cal2.get(java.util.Calendar.YEAR)))) && ((cal1.get(java.util.Calendar.ERA)) == (cal2.get(java.util.Calendar.ERA)))) && ((cal1.getClass()) == (cal2.getClass()));
	}

	public static java.util.Date parseDate(final java.lang.String str, final java.lang.String... parsePatterns) throws java.text.ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDate(str, null, parsePatterns);
	}

	public static java.util.Date parseDate(final java.lang.String str, final java.util.Locale locale, final java.lang.String... parsePatterns) throws java.text.ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDateWithLeniency(str, locale, parsePatterns, true);
	}

	public static java.util.Date parseDateStrictly(final java.lang.String str, final java.lang.String... parsePatterns) throws java.text.ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDateStrictly(str, null, parsePatterns);
	}

	public static java.util.Date parseDateStrictly(final java.lang.String str, final java.util.Locale locale, final java.lang.String... parsePatterns) throws java.text.ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDateWithLeniency(str, null, parsePatterns, false);
	}

	private static java.util.Date parseDateWithLeniency(final java.lang.String str, final java.util.Locale locale, final java.lang.String[] parsePatterns, final boolean lenient) throws java.text.ParseException {
		if ((str == null) || (parsePatterns == null)) {
			throw new java.lang.IllegalArgumentException("Date and Patterns must not be null");
		} 
		java.text.SimpleDateFormat parser;
		if (locale == null) {
			parser = new java.text.SimpleDateFormat();
		} else {
			parser = new java.text.SimpleDateFormat("" , locale);
		}
		parser.setLenient(lenient);
		final java.text.ParsePosition pos = new java.text.ParsePosition(0);
		for (final java.lang.String parsePattern : parsePatterns) {
			java.lang.String pattern = parsePattern;
			if (parsePattern.endsWith("ZZ")) {
				pattern = pattern.substring(0, ((pattern.length()) - 1));
			} 
			parser.applyPattern(pattern);
			pos.setIndex(0);
			java.lang.String str2 = str;
			if (parsePattern.endsWith("ZZ")) {
				str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
			} 
			final java.util.Date date = parser.parse(str2, pos);
			if ((date != null) && ((pos.getIndex()) == (str2.length()))) {
				return date;
			} 
		}
		throw new java.text.ParseException(("Unable to parse the date: " + str) , (-1));
	}

	public static java.util.Date addYears(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.YEAR, amount);
	}

	public static java.util.Date addMonths(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.MONTH, amount);
	}

	public static java.util.Date addWeeks(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.WEEK_OF_YEAR, amount);
	}

	public static java.util.Date addDays(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.DAY_OF_MONTH, amount);
	}

	public static java.util.Date addHours(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.HOUR_OF_DAY, amount);
	}

	public static java.util.Date addMinutes(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.MINUTE, amount);
	}

	public static java.util.Date addSeconds(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.SECOND, amount);
	}

	public static java.util.Date addMilliseconds(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.add(date, java.util.Calendar.MILLISECOND, amount);
	}

	private static java.util.Date add(final java.util.Date date, final int calendarField, final int amount) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static java.util.Date setYears(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.YEAR, amount);
	}

	public static java.util.Date setMonths(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.MONTH, amount);
	}

	public static java.util.Date setDays(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.DAY_OF_MONTH, amount);
	}

	public static java.util.Date setHours(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.HOUR_OF_DAY, amount);
	}

	public static java.util.Date setMinutes(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.MINUTE, amount);
	}

	public static java.util.Date setSeconds(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.SECOND, amount);
	}

	public static java.util.Date setMilliseconds(final java.util.Date date, final int amount) {
		return org.apache.commons.lang3.time.DateUtils.set(date, java.util.Calendar.MILLISECOND, amount);
	}

	private static java.util.Date set(final java.util.Date date, final int calendarField, final int amount) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar c = java.util.Calendar.getInstance();
		c.setLenient(false);
		c.setTime(date);
		c.set(calendarField, amount);
		return c.getTime();
	}

	public static java.util.Calendar toCalendar(final java.util.Date date) {
		final java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static java.util.Date round(final java.util.Date date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar gval = java.util.Calendar.getInstance();
		gval.setTime(date);
		org.apache.commons.lang3.time.DateUtils.modify(gval, field, org.apache.commons.lang3.time.DateUtils.MODIFY_ROUND);
		return gval.getTime();
	}

	public static java.util.Calendar round(final java.util.Calendar date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar rounded = ((java.util.Calendar)(date.clone()));
		org.apache.commons.lang3.time.DateUtils.modify(rounded, field, org.apache.commons.lang3.time.DateUtils.MODIFY_ROUND);
		return rounded;
	}

	public static java.util.Date round(final java.lang.Object date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		if (date instanceof java.util.Date) {
			return org.apache.commons.lang3.time.DateUtils.round(((java.util.Date)(date)), field);
		} else if (date instanceof java.util.Calendar) {
			return org.apache.commons.lang3.time.DateUtils.round(((java.util.Calendar)(date)), field).getTime();
		} else {
			throw new java.lang.ClassCastException(("Could not round " + date));
		}
	}

	public static java.util.Date truncate(final java.util.Date date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar gval = java.util.Calendar.getInstance();
		gval.setTime(date);
		org.apache.commons.lang3.time.DateUtils.modify(gval, field, org.apache.commons.lang3.time.DateUtils.MODIFY_TRUNCATE);
		return gval.getTime();
	}

	public static java.util.Calendar truncate(final java.util.Calendar date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar truncated = ((java.util.Calendar)(date.clone()));
		org.apache.commons.lang3.time.DateUtils.modify(truncated, field, org.apache.commons.lang3.time.DateUtils.MODIFY_TRUNCATE);
		return truncated;
	}

	public static java.util.Date truncate(final java.lang.Object date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		if (date instanceof java.util.Date) {
			return org.apache.commons.lang3.time.DateUtils.truncate(((java.util.Date)(date)), field);
		} else if (date instanceof java.util.Calendar) {
			return org.apache.commons.lang3.time.DateUtils.truncate(((java.util.Calendar)(date)), field).getTime();
		} else {
			throw new java.lang.ClassCastException(("Could not truncate " + date));
		}
	}

	public static java.util.Date ceiling(final java.util.Date date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar gval = java.util.Calendar.getInstance();
		gval.setTime(date);
		org.apache.commons.lang3.time.DateUtils.modify(gval, field, org.apache.commons.lang3.time.DateUtils.MODIFY_CEILING);
		return gval.getTime();
	}

	public static java.util.Calendar ceiling(final java.util.Calendar date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar ceiled = ((java.util.Calendar)(date.clone()));
		org.apache.commons.lang3.time.DateUtils.modify(ceiled, field, org.apache.commons.lang3.time.DateUtils.MODIFY_CEILING);
		return ceiled;
	}

	public static java.util.Date ceiling(final java.lang.Object date, final int field) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		if (date instanceof java.util.Date) {
			return org.apache.commons.lang3.time.DateUtils.ceiling(((java.util.Date)(date)), field);
		} else if (date instanceof java.util.Calendar) {
			return org.apache.commons.lang3.time.DateUtils.ceiling(((java.util.Calendar)(date)), field).getTime();
		} else {
			throw new java.lang.ClassCastException(("Could not find ceiling of for type: " + (date.getClass())));
		}
	}

	private static void modify(final java.util.Calendar val, final int field, final int modType) {
		if ((val.get(java.util.Calendar.YEAR)) > 280000000) {
			throw new java.lang.ArithmeticException("Calendar value too large for accurate calculations");
		} 
		if (field == (java.util.Calendar.MILLISECOND)) {
			return ;
		} 
		final java.util.Date date = val.getTime();
		long time = date.getTime();
		boolean done = false;
		final int millisecs = val.get(java.util.Calendar.MILLISECOND);
		if (((org.apache.commons.lang3.time.DateUtils.MODIFY_TRUNCATE) == modType) || (millisecs < 500)) {
			time = time - millisecs;
		} 
		if (field == (java.util.Calendar.SECOND)) {
			done = true;
		} 
		final int seconds = val.get(java.util.Calendar.SECOND);
		if ((!done) && (((org.apache.commons.lang3.time.DateUtils.MODIFY_TRUNCATE) == modType) || (seconds < 30))) {
			time = time - (seconds * 1000L);
		} 
		if (field == (java.util.Calendar.MINUTE)) {
			done = true;
		} 
		final int minutes = val.get(java.util.Calendar.MINUTE);
		if ((!done) && (((org.apache.commons.lang3.time.DateUtils.MODIFY_TRUNCATE) == modType) || (minutes < 30))) {
			time = time - (minutes * 60000L);
		} 
		if ((date.getTime()) != time) {
			date.setTime(time);
			val.setTime(date);
		} 
		boolean roundUp = false;
		for (final int[] aField : org.apache.commons.lang3.time.DateUtils.fields) {
			for (final int element : aField) {
				if (element == field) {
					if ((modType == (org.apache.commons.lang3.time.DateUtils.MODIFY_CEILING)) || ((modType == (org.apache.commons.lang3.time.DateUtils.MODIFY_ROUND)) && roundUp)) {
						if (field == (org.apache.commons.lang3.time.DateUtils.SEMI_MONTH)) {
							if ((val.get(java.util.Calendar.DATE)) == 1) {
								val.add(java.util.Calendar.DATE, 15);
							} else {
								val.add(java.util.Calendar.DATE, (-15));
								val.add(java.util.Calendar.MONTH, 1);
							}
						} else if (field == (java.util.Calendar.AM_PM)) {
							if ((val.get(java.util.Calendar.HOUR_OF_DAY)) == 0) {
								val.add(java.util.Calendar.HOUR_OF_DAY, 12);
							} else {
								val.add(java.util.Calendar.HOUR_OF_DAY, (-12));
								val.add(java.util.Calendar.DATE, 1);
							}
						} else {
							val.add(aField[0], 1);
						}
					} 
					return ;
				} 
			}
			int offset = 0;
			boolean offsetSet = false;
			switch (field) {
				case org.apache.commons.lang3.time.DateUtils.SEMI_MONTH :
					if ((aField[0]) == (java.util.Calendar.DATE)) {
						offset = (val.get(java.util.Calendar.DATE)) - 1;
						if (offset >= 15) {
							offset -= 15;
						} 
						roundUp = offset > 7;
						offsetSet = true;
					} 
					break;
				case java.util.Calendar.AM_PM :
					if ((aField[0]) == (java.util.Calendar.HOUR_OF_DAY)) {
						offset = val.get(java.util.Calendar.HOUR_OF_DAY);
						if (offset >= 12) {
							offset -= 12;
						} 
						roundUp = offset >= 6;
						offsetSet = true;
					} 
					break;
			}
			if (!offsetSet) {
				final int min = val.getActualMinimum(aField[0]);
				final int max = val.getActualMaximum(aField[0]);
				offset = (val.get(aField[0])) - min;
				roundUp = offset > ((max - min) / 2);
			} 
			if (offset != 0) {
				val.set(aField[0], ((val.get(aField[0])) - offset));
			} 
		}
		throw new java.lang.IllegalArgumentException((("The field " + field) + " is not supported"));
	}

	public static java.util.Iterator<java.util.Calendar> iterator(final java.util.Date focus, final int rangeStyle) {
		if (focus == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar gval = java.util.Calendar.getInstance();
		gval.setTime(focus);
		return org.apache.commons.lang3.time.DateUtils.iterator(gval, rangeStyle);
	}

	public static java.util.Iterator<java.util.Calendar> iterator(final java.util.Calendar focus, final int rangeStyle) {
		if (focus == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		java.util.Calendar start = null;
		java.util.Calendar end = null;
		int startCutoff = java.util.Calendar.SUNDAY;
		int endCutoff = java.util.Calendar.SATURDAY;
		switch (rangeStyle) {
			case org.apache.commons.lang3.time.DateUtils.RANGE_MONTH_SUNDAY :
			case org.apache.commons.lang3.time.DateUtils.RANGE_MONTH_MONDAY :
				start = org.apache.commons.lang3.time.DateUtils.truncate(focus, java.util.Calendar.MONTH);
				end = ((java.util.Calendar)(start.clone()));
				end.add(java.util.Calendar.MONTH, 1);
				end.add(java.util.Calendar.DATE, (-1));
				if (rangeStyle == (org.apache.commons.lang3.time.DateUtils.RANGE_MONTH_MONDAY)) {
					startCutoff = java.util.Calendar.MONDAY;
					endCutoff = java.util.Calendar.SUNDAY;
				} 
				break;
			case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_SUNDAY :
			case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_MONDAY :
			case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_RELATIVE :
			case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_CENTER :
				start = org.apache.commons.lang3.time.DateUtils.truncate(focus, java.util.Calendar.DATE);
				end = org.apache.commons.lang3.time.DateUtils.truncate(focus, java.util.Calendar.DATE);
				switch (rangeStyle) {
					case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_SUNDAY :
						break;
					case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_MONDAY :
						startCutoff = java.util.Calendar.MONDAY;
						endCutoff = java.util.Calendar.SUNDAY;
						break;
					case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_RELATIVE :
						startCutoff = focus.get(java.util.Calendar.DAY_OF_WEEK);
						endCutoff = startCutoff - 1;
						break;
					case org.apache.commons.lang3.time.DateUtils.RANGE_WEEK_CENTER :
						startCutoff = (focus.get(java.util.Calendar.DAY_OF_WEEK)) - 3;
						endCutoff = (focus.get(java.util.Calendar.DAY_OF_WEEK)) + 3;
						break;
				}
				break;
			default :
				throw new java.lang.IllegalArgumentException((("The range style " + rangeStyle) + " is not valid."));
		}
		if (startCutoff < (java.util.Calendar.SUNDAY)) {
			startCutoff += 7;
		} 
		if (startCutoff > (java.util.Calendar.SATURDAY)) {
			startCutoff -= 7;
		} 
		if (endCutoff < (java.util.Calendar.SUNDAY)) {
			endCutoff += 7;
		} 
		if (endCutoff > (java.util.Calendar.SATURDAY)) {
			endCutoff -= 7;
		} 
		while ((start.get(java.util.Calendar.DAY_OF_WEEK)) != startCutoff) {
			start.add(java.util.Calendar.DATE, (-1));
		}
		while ((end.get(java.util.Calendar.DAY_OF_WEEK)) != endCutoff) {
			end.add(java.util.Calendar.DATE, 1);
		}
		return new org.apache.commons.lang3.time.DateUtils.DateIterator(start , end);
	}

	public static java.util.Iterator<?> iterator(final java.lang.Object focus, final int rangeStyle) {
		if (focus == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		if (focus instanceof java.util.Date) {
			return org.apache.commons.lang3.time.DateUtils.iterator(((java.util.Date)(focus)), rangeStyle);
		} else if (focus instanceof java.util.Calendar) {
			return org.apache.commons.lang3.time.DateUtils.iterator(((java.util.Calendar)(focus)), rangeStyle);
		} else {
			throw new java.lang.ClassCastException(("Could not iterate based on " + focus));
		}
	}

	public static long getFragmentInMilliseconds(final java.util.Date date, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(date, fragment, java.util.Calendar.MILLISECOND);
	}

	public static long getFragmentInSeconds(final java.util.Date date, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(date, fragment, java.util.Calendar.SECOND);
	}

	public static long getFragmentInMinutes(final java.util.Date date, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(date, fragment, java.util.Calendar.MINUTE);
	}

	public static long getFragmentInHours(final java.util.Date date, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(date, fragment, java.util.Calendar.HOUR_OF_DAY);
	}

	public static long getFragmentInDays(final java.util.Date date, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(date, fragment, java.util.Calendar.DAY_OF_YEAR);
	}

	public static long getFragmentInMilliseconds(final java.util.Calendar calendar, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, java.util.Calendar.MILLISECOND);
	}

	public static long getFragmentInSeconds(final java.util.Calendar calendar, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, java.util.Calendar.SECOND);
	}

	public static long getFragmentInMinutes(final java.util.Calendar calendar, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, java.util.Calendar.MINUTE);
	}

	public static long getFragmentInHours(final java.util.Calendar calendar, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, java.util.Calendar.HOUR_OF_DAY);
	}

	public static long getFragmentInDays(final java.util.Calendar calendar, final int fragment) {
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, java.util.Calendar.DAY_OF_YEAR);
	}

	private static long getFragment(final java.util.Date date, final int fragment, final int unit) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		return org.apache.commons.lang3.time.DateUtils.getFragment(calendar, fragment, unit);
	}

	private static long getFragment(final java.util.Calendar calendar, final int fragment, final int unit) {
		if (calendar == null) {
			throw new java.lang.IllegalArgumentException("The date must not be null");
		} 
		final long millisPerUnit = org.apache.commons.lang3.time.DateUtils.getMillisPerUnit(unit);
		long result = 0;
		switch (fragment) {
			case java.util.Calendar.YEAR :
				result += ((calendar.get(java.util.Calendar.DAY_OF_YEAR)) * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY)) / millisPerUnit;
				break;
			case java.util.Calendar.MONTH :
				result += ((calendar.get(java.util.Calendar.DAY_OF_MONTH)) * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY)) / millisPerUnit;
				break;
		}
		switch (fragment) {
			case java.util.Calendar.YEAR :
			case java.util.Calendar.MONTH :
			case java.util.Calendar.DAY_OF_YEAR :
			case java.util.Calendar.DATE :
				result += ((calendar.get(java.util.Calendar.HOUR_OF_DAY)) * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR)) / millisPerUnit;
			case java.util.Calendar.HOUR_OF_DAY :
				result += ((calendar.get(java.util.Calendar.MINUTE)) * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE)) / millisPerUnit;
			case java.util.Calendar.MINUTE :
				result += ((calendar.get(java.util.Calendar.SECOND)) * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_SECOND)) / millisPerUnit;
			case java.util.Calendar.SECOND :
				result += ((calendar.get(java.util.Calendar.MILLISECOND)) * 1) / millisPerUnit;
				break;
			case java.util.Calendar.MILLISECOND :
				break;
			default :
				throw new java.lang.IllegalArgumentException((("The fragment " + fragment) + " is not supported"));
		}
		return result;
	}

	public static boolean truncatedEquals(final java.util.Calendar cal1, final java.util.Calendar cal2, final int field) {
		return (org.apache.commons.lang3.time.DateUtils.truncatedCompareTo(cal1, cal2, field)) == 0;
	}

	public static boolean truncatedEquals(final java.util.Date date1, final java.util.Date date2, final int field) {
		return (org.apache.commons.lang3.time.DateUtils.truncatedCompareTo(date1, date2, field)) == 0;
	}

	public static int truncatedCompareTo(final java.util.Calendar cal1, final java.util.Calendar cal2, final int field) {
		final java.util.Calendar truncatedCal1 = org.apache.commons.lang3.time.DateUtils.truncate(cal1, field);
		final java.util.Calendar truncatedCal2 = org.apache.commons.lang3.time.DateUtils.truncate(cal2, field);
		return truncatedCal1.compareTo(truncatedCal2);
	}

	public static int truncatedCompareTo(final java.util.Date date1, final java.util.Date date2, final int field) {
		final java.util.Date truncatedDate1 = org.apache.commons.lang3.time.DateUtils.truncate(date1, field);
		final java.util.Date truncatedDate2 = org.apache.commons.lang3.time.DateUtils.truncate(date2, field);
		return truncatedDate1.compareTo(truncatedDate2);
	}

	private static long getMillisPerUnit(final int unit) {
		long result = java.lang.Long.MAX_VALUE;
		switch (unit) {
			case java.util.Calendar.DAY_OF_YEAR :
			case java.util.Calendar.DATE :
				result = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
				break;
			case java.util.Calendar.HOUR_OF_DAY :
				result = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR;
				break;
			case java.util.Calendar.MINUTE :
				result = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE;
				break;
			case java.util.Calendar.SECOND :
				result = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_SECOND;
				break;
			case java.util.Calendar.MILLISECOND :
				result = 1;
				break;
			default :
				throw new java.lang.IllegalArgumentException((("The unit " + unit) + " cannot be represented is milleseconds"));
		}
		return result;
	}

	static class DateIterator implements java.util.Iterator<java.util.Calendar> {
		private final java.util.Calendar endFinal;

		private final java.util.Calendar spot;

		DateIterator(final java.util.Calendar startFinal ,final java.util.Calendar endFinal) {
			super();
			this.endFinal = endFinal;
			spot = startFinal;
			spot.add(java.util.Calendar.DATE, (-1));
		}

		@java.lang.Override
		public boolean hasNext() {
			return spot.before(endFinal);
		}

		@java.lang.Override
		public java.util.Calendar next() {
			if (spot.equals(endFinal)) {
				throw new java.util.NoSuchElementException();
			} 
			spot.add(java.util.Calendar.DATE, 1);
			return ((java.util.Calendar)(spot.clone()));
		}

		@java.lang.Override
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}
}

