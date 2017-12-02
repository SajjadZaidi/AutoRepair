package org.apache.commons.lang3.time;


public class DurationFormatUtils {
	public DurationFormatUtils() {
		super();
	}

	public static final java.lang.String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";

	public static java.lang.String formatDurationHMS(final long durationMillis) {
		return org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(durationMillis, "H:mm:ss.SSS");
	}

	public static java.lang.String formatDurationISO(final long durationMillis) {
		return org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(durationMillis, org.apache.commons.lang3.time.DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN, false);
	}

	public static java.lang.String formatDuration(final long durationMillis, final java.lang.String format) {
		return org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(durationMillis, format, true);
	}

	public static java.lang.String formatDuration(long durationMillis, final java.lang.String format, final boolean padWithZeros) {
		final org.apache.commons.lang3.time.DurationFormatUtils.Token[] tokens = org.apache.commons.lang3.time.DurationFormatUtils.lexx(format);
		int days = 0;
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		int milliseconds = 0;
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.d)) {
			days = ((int)(durationMillis / (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY)));
			durationMillis = durationMillis - (days * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY));
		} 
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.H)) {
			hours = ((int)(durationMillis / (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR)));
			durationMillis = durationMillis - (hours * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR));
		} 
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.m)) {
			minutes = ((int)(durationMillis / (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE)));
			durationMillis = durationMillis - (minutes * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE));
		} 
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.s)) {
			seconds = ((int)(durationMillis / (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_SECOND)));
			durationMillis = durationMillis - (seconds * (org.apache.commons.lang3.time.DateUtils.MILLIS_PER_SECOND));
		} 
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.S)) {
			milliseconds = ((int)(durationMillis));
		} 
		return org.apache.commons.lang3.time.DurationFormatUtils.format(tokens, 0, 0, days, hours, minutes, seconds, milliseconds, padWithZeros);
	}

	public static java.lang.String formatDurationWords(final long durationMillis, final boolean suppressLeadingZeroElements, final boolean suppressTrailingZeroElements) {
		java.lang.String duration = org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(durationMillis, "d' days 'H' hours 'm' minutes 's' seconds'");
		if (suppressLeadingZeroElements) {
			duration = " " + duration;
			java.lang.String tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 days", "");
			if ((tmp.length()) != (duration.length())) {
				duration = tmp;
				tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 hours", "");
				if ((tmp.length()) != (duration.length())) {
					duration = tmp;
					tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 minutes", "");
					duration = tmp;
					if ((tmp.length()) != (duration.length())) {
						duration = org.apache.commons.lang3.StringUtils.replaceOnce(tmp, " 0 seconds", "");
					} 
				} 
			} 
			if ((duration.length()) != 0) {
				duration = duration.substring(1);
			} 
		} 
		if (suppressTrailingZeroElements) {
			java.lang.String tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 seconds", "");
			if ((tmp.length()) != (duration.length())) {
				duration = tmp;
				tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 minutes", "");
				if ((tmp.length()) != (duration.length())) {
					duration = tmp;
					tmp = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 0 hours", "");
					if ((tmp.length()) != (duration.length())) {
						duration = org.apache.commons.lang3.StringUtils.replaceOnce(tmp, " 0 days", "");
					} 
				} 
			} 
		} 
		duration = " " + duration;
		duration = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 1 seconds", " 1 second");
		duration = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 1 minutes", " 1 minute");
		duration = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 1 hours", " 1 hour");
		duration = org.apache.commons.lang3.StringUtils.replaceOnce(duration, " 1 days", " 1 day");
		return duration.trim();
	}

	public static java.lang.String formatPeriodISO(final long startMillis, final long endMillis) {
		return org.apache.commons.lang3.time.DurationFormatUtils.formatPeriod(startMillis, endMillis, org.apache.commons.lang3.time.DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN, false, java.util.TimeZone.getDefault());
	}

	public static java.lang.String formatPeriod(final long startMillis, final long endMillis, final java.lang.String format) {
		return org.apache.commons.lang3.time.DurationFormatUtils.formatPeriod(startMillis, endMillis, format, true, java.util.TimeZone.getDefault());
	}

	public static java.lang.String formatPeriod(final long startMillis, final long endMillis, final java.lang.String format, final boolean padWithZeros, final java.util.TimeZone timezone) {
		final org.apache.commons.lang3.time.DurationFormatUtils.Token[] tokens = org.apache.commons.lang3.time.DurationFormatUtils.lexx(format);
		final java.util.Calendar start = java.util.Calendar.getInstance(timezone);
		start.setTime(new java.util.Date(startMillis));
		final java.util.Calendar end = java.util.Calendar.getInstance(timezone);
		end.setTime(new java.util.Date(endMillis));
		int milliseconds = (end.get(java.util.Calendar.MILLISECOND)) - (start.get(java.util.Calendar.MILLISECOND));
		int seconds = (end.get(java.util.Calendar.SECOND)) - (start.get(java.util.Calendar.SECOND));
		int minutes = (end.get(java.util.Calendar.MINUTE)) - (start.get(java.util.Calendar.MINUTE));
		int hours = (end.get(java.util.Calendar.HOUR_OF_DAY)) - (start.get(java.util.Calendar.HOUR_OF_DAY));
		int days = (end.get(java.util.Calendar.DAY_OF_MONTH)) - (start.get(java.util.Calendar.DAY_OF_MONTH));
		int months = (end.get(java.util.Calendar.MONTH)) - (start.get(java.util.Calendar.MONTH));
		int years = (end.get(java.util.Calendar.YEAR)) - (start.get(java.util.Calendar.YEAR));
		while (milliseconds < 0) {
			milliseconds += 1000;
			seconds -= 1;
		}
		while (seconds < 0) {
			seconds += 60;
			minutes -= 1;
		}
		while (minutes < 0) {
			minutes += 60;
			hours -= 1;
		}
		while (hours < 0) {
			hours += 24;
			days -= 1;
		}
		if (org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.M)) {
			while (days < 0) {
				days += start.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				months -= 1;
				start.add(java.util.Calendar.MONTH, 1);
			}
			while (months < 0) {
				months += 12;
				years -= 1;
			}
			if ((!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.y))) && (years != 0)) {
				while (years != 0) {
					months += 12 * years;
					years = 0;
				}
			} 
		} else {
			if (!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.y))) {
				int target = end.get(java.util.Calendar.YEAR);
				if (months < 0) {
					target -= 1;
				} 
				while ((start.get(java.util.Calendar.YEAR)) != target) {
					days += (start.getActualMaximum(java.util.Calendar.DAY_OF_YEAR)) - (start.get(java.util.Calendar.DAY_OF_YEAR));
					if (((start instanceof java.util.GregorianCalendar) && ((start.get(java.util.Calendar.MONTH)) == (java.util.Calendar.FEBRUARY))) && ((start.get(java.util.Calendar.DAY_OF_MONTH)) == 29)) {
						days += 1;
					} 
					start.add(java.util.Calendar.YEAR, 1);
					days += start.get(java.util.Calendar.DAY_OF_YEAR);
				}
				years = 0;
			} 
			while ((start.get(java.util.Calendar.MONTH)) != (end.get(java.util.Calendar.MONTH))) {
				days += start.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				start.add(java.util.Calendar.MONTH, 1);
			}
			months = 0;
			while (days < 0) {
				days += start.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				months -= 1;
				start.add(java.util.Calendar.MONTH, 1);
			}
		}
		if (!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.d))) {
			hours += 24 * days;
			days = 0;
		} 
		if (!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.H))) {
			minutes += 60 * hours;
			hours = 0;
		} 
		if (!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.m))) {
			seconds += 60 * minutes;
			minutes = 0;
		} 
		if (!(org.apache.commons.lang3.time.DurationFormatUtils.Token.containsTokenWithValue(tokens, org.apache.commons.lang3.time.DurationFormatUtils.s))) {
			milliseconds += 1000 * seconds;
			seconds = 0;
		} 
		return org.apache.commons.lang3.time.DurationFormatUtils.format(tokens, years, months, days, hours, minutes, seconds, milliseconds, padWithZeros);
	}

	static java.lang.String format(final org.apache.commons.lang3.time.DurationFormatUtils.Token[] tokens, final int years, final int months, final int days, final int hours, final int minutes, final int seconds, int milliseconds, final boolean padWithZeros) {
		final java.lang.StringBuilder buffer = new java.lang.StringBuilder();
		boolean lastOutputSeconds = false;
		final int sz = tokens.length;
		for (int i = 0 ; i < sz ; i++) {
			final org.apache.commons.lang3.time.DurationFormatUtils.Token token = tokens[i];
			final java.lang.Object value = token.getValue();
			final int count = token.getCount();
			if (value instanceof java.lang.StringBuilder) {
				buffer.append(value.toString());
			} else {
				if (value == (org.apache.commons.lang3.time.DurationFormatUtils.y)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(years), count, '0') : java.lang.Integer.toString(years)));
					lastOutputSeconds = false;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.M)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(months), count, '0') : java.lang.Integer.toString(months)));
					lastOutputSeconds = false;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.d)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(days), count, '0') : java.lang.Integer.toString(days)));
					lastOutputSeconds = false;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.H)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(hours), count, '0') : java.lang.Integer.toString(hours)));
					lastOutputSeconds = false;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.m)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(minutes), count, '0') : java.lang.Integer.toString(minutes)));
					lastOutputSeconds = false;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.s)) {
					buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(seconds), count, '0') : java.lang.Integer.toString(seconds)));
					lastOutputSeconds = true;
				} else if (value == (org.apache.commons.lang3.time.DurationFormatUtils.S)) {
					if (lastOutputSeconds) {
						milliseconds += 1000;
						final java.lang.String str = padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(milliseconds), count, '0') : java.lang.Integer.toString(milliseconds);
						buffer.append(str.substring(1));
					} else {
						buffer.append((padWithZeros ? org.apache.commons.lang3.StringUtils.leftPad(java.lang.Integer.toString(milliseconds), count, '0') : java.lang.Integer.toString(milliseconds)));
					}
					lastOutputSeconds = false;
				} 
			}
		}
		return buffer.toString();
	}

	static final java.lang.Object y = "y";

	static final java.lang.Object M = "M";

	static final java.lang.Object d = "d";

	static final java.lang.Object H = "H";

	static final java.lang.Object m = "m";

	static final java.lang.Object s = "s";

	static final java.lang.Object S = "S";

	static org.apache.commons.lang3.time.DurationFormatUtils.Token[] lexx(final java.lang.String format) {
		final char[] array = format.toCharArray();
		final java.util.ArrayList<org.apache.commons.lang3.time.DurationFormatUtils.Token> list = new java.util.ArrayList<org.apache.commons.lang3.time.DurationFormatUtils.Token>(array.length);
		boolean inLiteral = false;
		java.lang.StringBuilder buffer = null;
		org.apache.commons.lang3.time.DurationFormatUtils.Token previous = null;
		final int sz = array.length;
		for (int i = 0 ; i < sz ; i++) {
			final char ch = array[i];
			if (inLiteral && (ch != '\'')) {
				buffer.append(ch);
				continue;
			} 
			java.lang.Object value = null;
			switch (ch) {
				case '\'' :
					if (inLiteral) {
						buffer = null;
						inLiteral = false;
					} else {
						buffer = new java.lang.StringBuilder();
						list.add(new org.apache.commons.lang3.time.DurationFormatUtils.Token(buffer));
						inLiteral = true;
					}
					break;
				case 'y' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.y;
					break;
				case 'M' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.M;
					break;
				case 'd' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.d;
					break;
				case 'H' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.H;
					break;
				case 'm' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.m;
					break;
				case 's' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.s;
					break;
				case 'S' :
					value = org.apache.commons.lang3.time.DurationFormatUtils.S;
					break;
				default :
					if (buffer == null) {
						buffer = new java.lang.StringBuilder();
						list.add(new org.apache.commons.lang3.time.DurationFormatUtils.Token(buffer));
					} 
					buffer.append(ch);
			}
			if (value != null) {
				if ((previous != null) && ((previous.getValue()) == value)) {
					previous.increment();
				} else {
					final org.apache.commons.lang3.time.DurationFormatUtils.Token token = new org.apache.commons.lang3.time.DurationFormatUtils.Token(value);
					list.add(token);
					previous = token;
				}
				buffer = null;
			} 
		}
		return list.toArray(new org.apache.commons.lang3.time.DurationFormatUtils.Token[list.size()]);
	}

	static class Token {
		static boolean containsTokenWithValue(final org.apache.commons.lang3.time.DurationFormatUtils.Token[] tokens, final java.lang.Object value) {
			final int sz = tokens.length;
			for (int i = 0 ; i < sz ; i++) {
				if ((tokens[i].getValue()) == value) {
					return true;
				} 
			}
			return false;
		}

		private final java.lang.Object value;

		private int count;

		Token(final java.lang.Object value) {
			this.value = value;
			org.apache.commons.lang3.time.DurationFormatUtils.Token.this.count = 1;
		}

		Token(final java.lang.Object value ,final int count) {
			this.value = value;
			org.apache.commons.lang3.time.DurationFormatUtils.Token.this.count = count;
		}

		void increment() {
			(count)++;
		}

		int getCount() {
			return count;
		}

		java.lang.Object getValue() {
			return value;
		}

		@java.lang.Override
		public boolean equals(final java.lang.Object obj2) {
			if (obj2 instanceof org.apache.commons.lang3.time.DurationFormatUtils.Token) {
				final org.apache.commons.lang3.time.DurationFormatUtils.Token tok2 = ((org.apache.commons.lang3.time.DurationFormatUtils.Token)(obj2));
				if ((org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value.getClass()) != (tok2.value.getClass())) {
					return false;
				} 
				if ((org.apache.commons.lang3.time.DurationFormatUtils.Token.this.count) != (tok2.count)) {
					return false;
				} 
				if ((org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value) instanceof java.lang.StringBuilder) {
					return org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value.toString().equals(tok2.value.toString());
				} else if ((org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value) instanceof java.lang.Number) {
					return org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value.equals(tok2.value);
				} else {
					return (org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value) == (tok2.value);
				}
			} 
			return false;
		}

		@java.lang.Override
		public int hashCode() {
			return org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value.hashCode();
		}

		@java.lang.Override
		public java.lang.String toString() {
			return org.apache.commons.lang3.StringUtils.repeat(org.apache.commons.lang3.time.DurationFormatUtils.Token.this.value.toString(), org.apache.commons.lang3.time.DurationFormatUtils.Token.this.count);
		}
	}
}

