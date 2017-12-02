package org.apache.commons.lang3;


public class StringUtils {
	public static final java.lang.String SPACE = " ";

	public static final java.lang.String EMPTY = "";

	public static final java.lang.String LF = "\n";

	public static final java.lang.String CR = "\r";

	public static final int INDEX_NOT_FOUND = -1;

	private static final int PAD_LIMIT = 8192;

	private static final java.util.regex.Pattern WHITESPACE_PATTERN = java.util.regex.Pattern.compile("(?: \\s|[\\s&&[^ ]])\\s*");

	public StringUtils() {
		super();
	}

	public static boolean isEmpty(final java.lang.CharSequence cs) {
		return (cs == null) || ((cs.length()) == 0);
	}

	public static boolean isNotEmpty(final java.lang.CharSequence cs) {
		return !(org.apache.commons.lang3.StringUtils.isEmpty(cs));
	}

	public static boolean isBlank(final java.lang.CharSequence cs) {
		int strLen;
		if ((cs == null) || ((strLen = cs.length()) == 0)) {
			return true;
		} 
		for (int i = 0 ; i < strLen ; i++) {
			if ((java.lang.Character.isWhitespace(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isNotBlank(final java.lang.CharSequence cs) {
		return !(org.apache.commons.lang3.StringUtils.isBlank(cs));
	}

	public static java.lang.String trim(final java.lang.String str) {
		return str == null ? null : str.trim();
	}

	public static java.lang.String trimToNull(final java.lang.String str) {
		final java.lang.String ts = org.apache.commons.lang3.StringUtils.trim(str);
		return org.apache.commons.lang3.StringUtils.isEmpty(ts) ? null : ts;
	}

	public static java.lang.String trimToEmpty(final java.lang.String str) {
		return str == null ? org.apache.commons.lang3.StringUtils.EMPTY : str.trim();
	}

	public static java.lang.String strip(final java.lang.String str) {
		return org.apache.commons.lang3.StringUtils.strip(str, null);
	}

	public static java.lang.String stripToNull(java.lang.String str) {
		if (str == null) {
			return null;
		} 
		str = org.apache.commons.lang3.StringUtils.strip(str, null);
		return str.isEmpty() ? null : str;
	}

	public static java.lang.String stripToEmpty(final java.lang.String str) {
		return str == null ? org.apache.commons.lang3.StringUtils.EMPTY : org.apache.commons.lang3.StringUtils.strip(str, null);
	}

	public static java.lang.String strip(java.lang.String str, final java.lang.String stripChars) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		str = org.apache.commons.lang3.StringUtils.stripStart(str, stripChars);
		return org.apache.commons.lang3.StringUtils.stripEnd(str, stripChars);
	}

	public static java.lang.String stripStart(final java.lang.String str, final java.lang.String stripChars) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		} 
		int start = 0;
		if (stripChars == null) {
			while ((start != strLen) && (java.lang.Character.isWhitespace(str.charAt(start)))) {
				start++;
			}
		} else if (stripChars.isEmpty()) {
			return str;
		} else {
			while ((start != strLen) && ((stripChars.indexOf(str.charAt(start))) != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND))) {
				start++;
			}
		}
		return str.substring(start);
	}

	public static java.lang.String stripEnd(final java.lang.String str, final java.lang.String stripChars) {
		int end;
		if ((str == null) || ((end = str.length()) == 0)) {
			return str;
		} 
		if (stripChars == null) {
			while ((end != 0) && (java.lang.Character.isWhitespace(str.charAt((end - 1))))) {
				end--;
			}
		} else if (stripChars.isEmpty()) {
			return str;
		} else {
			while ((end != 0) && ((stripChars.indexOf(str.charAt((end - 1)))) != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND))) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	public static java.lang.String[] stripAll(final java.lang.String... strs) {
		return org.apache.commons.lang3.StringUtils.stripAll(strs, null);
	}

	public static java.lang.String[] stripAll(final java.lang.String[] strs, final java.lang.String stripChars) {
		int strsLen;
		if ((strs == null) || ((strsLen = strs.length) == 0)) {
			return strs;
		} 
		final java.lang.String[] newArr = new java.lang.String[strsLen];
		for (int i = 0 ; i < strsLen ; i++) {
			newArr[i] = org.apache.commons.lang3.StringUtils.strip(strs[i], stripChars);
		}
		return newArr;
	}

	public static java.lang.String stripAccents(final java.lang.String input) {
		if (input == null) {
			return null;
		} 
		final java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		final java.lang.String decomposed = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
		return pattern.matcher(decomposed).replaceAll("");
	}

	public static boolean equals(final java.lang.CharSequence cs1, final java.lang.CharSequence cs2) {
		if (cs1 == cs2) {
			return true;
		} 
		if ((cs1 == null) || (cs2 == null)) {
			return false;
		} 
		if ((cs1 instanceof java.lang.String) && (cs2 instanceof java.lang.String)) {
			return cs1.equals(cs2);
		} 
		return org.apache.commons.lang3.CharSequenceUtils.regionMatches(cs1, false, 0, cs2, 0, java.lang.Math.max(cs1.length(), cs2.length()));
	}

	public static boolean equalsIgnoreCase(final java.lang.CharSequence str1, final java.lang.CharSequence str2) {
		if ((str1 == null) || (str2 == null)) {
			return str1 == str2;
		} else if (str1 == str2) {
			return true;
		} else if ((str1.length()) != (str2.length())) {
			return false;
		} else {
			return org.apache.commons.lang3.CharSequenceUtils.regionMatches(str1, true, 0, str2, 0, str1.length());
		}
	}

	public static int indexOf(final java.lang.CharSequence seq, final int searchChar) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchChar, 0);
	}

	public static int indexOf(final java.lang.CharSequence seq, final int searchChar, final int startPos) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchChar, startPos);
	}

	public static int indexOf(final java.lang.CharSequence seq, final java.lang.CharSequence searchSeq) {
		if ((seq == null) || (searchSeq == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchSeq, 0);
	}

	public static int indexOf(final java.lang.CharSequence seq, final java.lang.CharSequence searchSeq, final int startPos) {
		if ((seq == null) || (searchSeq == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchSeq, startPos);
	}

	public static int ordinalIndexOf(final java.lang.CharSequence str, final java.lang.CharSequence searchStr, final int ordinal) {
		return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal, false);
	}

	private static int ordinalIndexOf(final java.lang.CharSequence str, final java.lang.CharSequence searchStr, final int ordinal, final boolean lastIndex) {
		if (((str == null) || (searchStr == null)) || (ordinal <= 0)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if ((searchStr.length()) == 0) {
			return lastIndex ? str.length() : 0;
		} 
		int found = 0;
		int index = lastIndex ? str.length() : org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		do {
			if (lastIndex) {
				index = org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(str, searchStr, (index - 1));
			} else {
				index = org.apache.commons.lang3.CharSequenceUtils.indexOf(str, searchStr, (index + 1));
			}
			if (index < 0) {
				return index;
			} 
			found++;
		} while (found < ordinal );
		return index;
	}

	public static int indexOfIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence searchStr) {
		return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, 0);
	}

	public static int indexOfIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if (startPos < 0) {
			startPos = 0;
		} 
		final int endLimit = ((str.length()) - (searchStr.length())) + 1;
		if (startPos > endLimit) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if ((searchStr.length()) == 0) {
			return startPos;
		} 
		for (int i = startPos ; i < endLimit ; i++) {
			if (org.apache.commons.lang3.CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
				return i;
			} 
		}
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final java.lang.CharSequence seq, final int searchChar) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(seq, searchChar, seq.length());
	}

	public static int lastIndexOf(final java.lang.CharSequence seq, final int searchChar, final int startPos) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(seq, searchChar, startPos);
	}

	public static int lastIndexOf(final java.lang.CharSequence seq, final java.lang.CharSequence searchSeq) {
		if ((seq == null) || (searchSeq == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(seq, searchSeq, seq.length());
	}

	public static int lastOrdinalIndexOf(final java.lang.CharSequence str, final java.lang.CharSequence searchStr, final int ordinal) {
		return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal, true);
	}

	public static int lastIndexOf(final java.lang.CharSequence seq, final java.lang.CharSequence searchSeq, final int startPos) {
		if ((seq == null) || (searchSeq == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(seq, searchSeq, startPos);
	}

	public static int lastIndexOfIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence searchStr) {
		if ((str == null) || (searchStr == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr, str.length());
	}

	public static int lastIndexOfIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if (startPos > ((str.length()) - (searchStr.length()))) {
			startPos = (str.length()) - (searchStr.length());
		} 
		if (startPos < 0) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if ((searchStr.length()) == 0) {
			return startPos;
		} 
		for (int i = startPos ; i >= 0 ; i--) {
			if (org.apache.commons.lang3.CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
				return i;
			} 
		}
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final java.lang.CharSequence seq, final int searchChar) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return false;
		} 
		return (org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchChar, 0)) >= 0;
	}

	public static boolean contains(final java.lang.CharSequence seq, final java.lang.CharSequence searchSeq) {
		if ((seq == null) || (searchSeq == null)) {
			return false;
		} 
		return (org.apache.commons.lang3.CharSequenceUtils.indexOf(seq, searchSeq, 0)) >= 0;
	}

	public static boolean containsIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		} 
		final int len = searchStr.length();
		final int max = (str.length()) - len;
		for (int i = 0 ; i <= max ; i++) {
			if (org.apache.commons.lang3.CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
				return true;
			} 
		}
		return false;
	}

	public static boolean containsWhitespace(final java.lang.CharSequence seq) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(seq)) {
			return false;
		} 
		final int strLen = seq.length();
		for (int i = 0 ; i < strLen ; i++) {
			if (java.lang.Character.isWhitespace(seq.charAt(i))) {
				return true;
			} 
		}
		return false;
	}

	public static int indexOfAny(final java.lang.CharSequence cs, final char... searchChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(cs)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(searchChars))) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		final int csLen = cs.length();
		final int csLast = csLen - 1;
		final int searchLen = searchChars.length;
		final int searchLast = searchLen - 1;
		for (int i = 0 ; i < csLen ; i++) {
			final char ch = cs.charAt(i);
			for (int j = 0 ; j < searchLen ; j++) {
				if ((searchChars[j]) == ch) {
					if (((i < csLast) && (j < searchLast)) && (java.lang.Character.isHighSurrogate(ch))) {
						if ((searchChars[(j + 1)]) == (cs.charAt((i + 1)))) {
							return i;
						} 
					} else {
						return i;
					}
				} 
			}
		}
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static int indexOfAny(final java.lang.CharSequence cs, final java.lang.String searchChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(cs)) || (org.apache.commons.lang3.StringUtils.isEmpty(searchChars))) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars.toCharArray());
	}

	public static boolean containsAny(final java.lang.CharSequence cs, final char... searchChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(cs)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(searchChars))) {
			return false;
		} 
		final int csLength = cs.length();
		final int searchLength = searchChars.length;
		final int csLast = csLength - 1;
		final int searchLast = searchLength - 1;
		for (int i = 0 ; i < csLength ; i++) {
			final char ch = cs.charAt(i);
			for (int j = 0 ; j < searchLength ; j++) {
				if ((searchChars[j]) == ch) {
					if (java.lang.Character.isHighSurrogate(ch)) {
						if (j == searchLast) {
							return true;
						} 
						if ((i < csLast) && ((searchChars[(j + 1)]) == (cs.charAt((i + 1))))) {
							return true;
						} 
					} else {
						return true;
					}
				} 
			}
		}
		return false;
	}

	public static boolean containsAny(final java.lang.CharSequence cs, final java.lang.CharSequence searchChars) {
		if (searchChars == null) {
			return false;
		} 
		return org.apache.commons.lang3.StringUtils.containsAny(cs, org.apache.commons.lang3.CharSequenceUtils.toCharArray(searchChars));
	}

	public static int indexOfAnyBut(final java.lang.CharSequence cs, final char... searchChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(cs)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(searchChars))) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		final int csLen = cs.length();
		final int csLast = csLen - 1;
		final int searchLen = searchChars.length;
		final int searchLast = searchLen - 1;
		outer : for (int i = 0 ; i < csLen ; i++) {
			final char ch = cs.charAt(i);
			for (int j = 0 ; j < searchLen ; j++) {
				if ((searchChars[j]) == ch) {
					if (((i < csLast) && (j < searchLast)) && (java.lang.Character.isHighSurrogate(ch))) {
						if ((searchChars[(j + 1)]) == (cs.charAt((i + 1)))) {
							continue outer;
						} 
					} else {
						continue outer;
					}
				} 
			}
			return i;
		}
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static int indexOfAnyBut(final java.lang.CharSequence seq, final java.lang.CharSequence searchChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(seq)) || (org.apache.commons.lang3.StringUtils.isEmpty(searchChars))) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		final int strLen = seq.length();
		for (int i = 0 ; i < strLen ; i++) {
			final char ch = seq.charAt(i);
			final boolean chFound = (org.apache.commons.lang3.CharSequenceUtils.indexOf(searchChars, ch, 0)) >= 0;
			if (((i + 1) < strLen) && (java.lang.Character.isHighSurrogate(ch))) {
				final char ch2 = seq.charAt((i + 1));
				if (chFound && ((org.apache.commons.lang3.CharSequenceUtils.indexOf(searchChars, ch2, 0)) < 0)) {
					return i;
				} 
			} else {
				if (!chFound) {
					return i;
				} 
			}
		}
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static boolean containsOnly(final java.lang.CharSequence cs, final char... valid) {
		if ((valid == null) || (cs == null)) {
			return false;
		} 
		if ((cs.length()) == 0) {
			return true;
		} 
		if ((valid.length) == 0) {
			return false;
		} 
		return (org.apache.commons.lang3.StringUtils.indexOfAnyBut(cs, valid)) == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND);
	}

	public static boolean containsOnly(final java.lang.CharSequence cs, final java.lang.String validChars) {
		if ((cs == null) || (validChars == null)) {
			return false;
		} 
		return org.apache.commons.lang3.StringUtils.containsOnly(cs, validChars.toCharArray());
	}

	public static boolean containsNone(final java.lang.CharSequence cs, final char... searchChars) {
		if ((cs == null) || (searchChars == null)) {
			return true;
		} 
		final int csLen = cs.length();
		final int csLast = csLen - 1;
		final int searchLen = searchChars.length;
		final int searchLast = searchLen - 1;
		for (int i = 0 ; i < csLen ; i++) {
			final char ch = cs.charAt(i);
			for (int j = 0 ; j < searchLen ; j++) {
				if ((searchChars[j]) == ch) {
					if (java.lang.Character.isHighSurrogate(ch)) {
						if (j == searchLast) {
							return false;
						} 
						if ((i < csLast) && ((searchChars[(j + 1)]) == (cs.charAt((i + 1))))) {
							return false;
						} 
					} else {
						return false;
					}
				} 
			}
		}
		return true;
	}

	public static boolean containsNone(final java.lang.CharSequence cs, final java.lang.String invalidChars) {
		if ((cs == null) || (invalidChars == null)) {
			return true;
		} 
		return org.apache.commons.lang3.StringUtils.containsNone(cs, invalidChars.toCharArray());
	}

	public static int indexOfAny(final java.lang.CharSequence str, final java.lang.CharSequence... searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		final int sz = searchStrs.length;
		int ret = java.lang.Integer.MAX_VALUE;
		int tmp = 0;
		for (int i = 0 ; i < sz ; i++) {
			final java.lang.CharSequence search = searchStrs[i];
			if (search == null) {
				continue;
			} 
			tmp = org.apache.commons.lang3.CharSequenceUtils.indexOf(str, search, 0);
			if (tmp == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
				continue;
			} 
			if (tmp < ret) {
				ret = tmp;
			} 
		}
		return ret == (java.lang.Integer.MAX_VALUE) ? org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND : ret;
	}

	public static int lastIndexOfAny(final java.lang.CharSequence str, final java.lang.CharSequence... searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		final int sz = searchStrs.length;
		int ret = org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		int tmp = 0;
		for (int i = 0 ; i < sz ; i++) {
			final java.lang.CharSequence search = searchStrs[i];
			if (search == null) {
				continue;
			} 
			tmp = org.apache.commons.lang3.CharSequenceUtils.lastIndexOf(str, search, str.length());
			if (tmp > ret) {
				ret = tmp;
			} 
		}
		return ret;
	}

	public static java.lang.String substring(final java.lang.String str, int start) {
		if (str == null) {
			return null;
		} 
		if (start < 0) {
			start = (str.length()) + start;
		} 
		if (start < 0) {
			start = 0;
		} 
		if (start > (str.length())) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		return str.substring(start);
	}

	public static java.lang.String substring(final java.lang.String str, int start, int end) {
		if (str == null) {
			return null;
		} 
		if (end < 0) {
			end = (str.length()) + end;
		} 
		if (start < 0) {
			start = (str.length()) + start;
		} 
		if (end > (str.length())) {
			end = str.length();
		} 
		if (start > end) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		if (start < 0) {
			start = 0;
		} 
		if (end < 0) {
			end = 0;
		} 
		return str.substring(start, end);
	}

	public static java.lang.String left(final java.lang.String str, final int len) {
		if (str == null) {
			return null;
		} 
		if (len < 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		if ((str.length()) <= len) {
			return str;
		} 
		return str.substring(0, len);
	}

	public static java.lang.String right(final java.lang.String str, final int len) {
		if (str == null) {
			return null;
		} 
		if (len < 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		if ((str.length()) <= len) {
			return str;
		} 
		return str.substring(((str.length()) - len));
	}

	public static java.lang.String mid(final java.lang.String str, int pos, final int len) {
		if (str == null) {
			return null;
		} 
		if ((len < 0) || (pos > (str.length()))) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		if (pos < 0) {
			pos = 0;
		} 
		if ((str.length()) <= (pos + len)) {
			return str.substring(pos);
		} 
		return str.substring(pos, (pos + len));
	}

	public static java.lang.String substringBefore(final java.lang.String str, final java.lang.String separator) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (separator == null)) {
			return str;
		} 
		if (separator.isEmpty()) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int pos = str.indexOf(separator);
		if (pos == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			return str;
		} 
		return str.substring(0, pos);
	}

	public static java.lang.String substringAfter(final java.lang.String str, final java.lang.String separator) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		if (separator == null) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int pos = str.indexOf(separator);
		if (pos == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		return str.substring((pos + (separator.length())));
	}

	public static java.lang.String substringBeforeLast(final java.lang.String str, final java.lang.String separator) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(separator))) {
			return str;
		} 
		final int pos = str.lastIndexOf(separator);
		if (pos == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			return str;
		} 
		return str.substring(0, pos);
	}

	public static java.lang.String substringAfterLast(final java.lang.String str, final java.lang.String separator) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		if (org.apache.commons.lang3.StringUtils.isEmpty(separator)) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int pos = str.lastIndexOf(separator);
		if ((pos == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) || (pos == ((str.length()) - (separator.length())))) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		return str.substring((pos + (separator.length())));
	}

	public static java.lang.String substringBetween(final java.lang.String str, final java.lang.String tag) {
		return org.apache.commons.lang3.StringUtils.substringBetween(str, tag, tag);
	}

	public static java.lang.String substringBetween(final java.lang.String str, final java.lang.String open, final java.lang.String close) {
		if (((str == null) || (open == null)) || (close == null)) {
			return null;
		} 
		final int start = str.indexOf(open);
		if (start != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			final int end = str.indexOf(close, (start + (open.length())));
			if (end != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
				return str.substring((start + (open.length())), end);
			} 
		} 
		return null;
	}

	public static java.lang.String[] substringsBetween(final java.lang.String str, final java.lang.String open, final java.lang.String close) {
		if (((str == null) || (org.apache.commons.lang3.StringUtils.isEmpty(open))) || (org.apache.commons.lang3.StringUtils.isEmpty(close))) {
			return null;
		} 
		final int strLen = str.length();
		if (strLen == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		final int closeLen = close.length();
		final int openLen = open.length();
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>();
		int pos = 0;
		while (pos < (strLen - closeLen)) {
			int start = str.indexOf(open, pos);
			if (start < 0) {
				break;
			} 
			start += openLen;
			final int end = str.indexOf(close, start);
			if (end < 0) {
				break;
			} 
			list.add(str.substring(start, end));
			pos = end + closeLen;
		}
		if (list.isEmpty()) {
			return null;
		} 
		return list.toArray(new java.lang.String[list.size()]);
	}

	public static java.lang.String[] split(final java.lang.String str) {
		return org.apache.commons.lang3.StringUtils.split(str, null, (-1));
	}

	public static java.lang.String[] split(final java.lang.String str, final char separatorChar) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChar, false);
	}

	public static java.lang.String[] split(final java.lang.String str, final java.lang.String separatorChars) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChars, (-1), false);
	}

	public static java.lang.String[] split(final java.lang.String str, final java.lang.String separatorChars, final int max) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChars, max, false);
	}

	public static java.lang.String[] splitByWholeSeparator(final java.lang.String str, final java.lang.String separator) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorWorker(str, separator, (-1), false);
	}

	public static java.lang.String[] splitByWholeSeparator(final java.lang.String str, final java.lang.String separator, final int max) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorWorker(str, separator, max, false);
	}

	public static java.lang.String[] splitByWholeSeparatorPreserveAllTokens(final java.lang.String str, final java.lang.String separator) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorWorker(str, separator, (-1), true);
	}

	public static java.lang.String[] splitByWholeSeparatorPreserveAllTokens(final java.lang.String str, final java.lang.String separator, final int max) {
		return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorWorker(str, separator, max, true);
	}

	private static java.lang.String[] splitByWholeSeparatorWorker(final java.lang.String str, final java.lang.String separator, final int max, final boolean preserveAllTokens) {
		if (str == null) {
			return null;
		} 
		final int len = str.length();
		if (len == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		if ((separator == null) || (org.apache.commons.lang3.StringUtils.EMPTY.equals(separator))) {
			return org.apache.commons.lang3.StringUtils.splitWorker(str, null, max, preserveAllTokens);
		} 
		final int separatorLength = separator.length();
		final java.util.ArrayList<java.lang.String> substrings = new java.util.ArrayList<java.lang.String>();
		int numberOfSubstrings = 0;
		int beg = 0;
		int end = 0;
		while (end < len) {
			end = str.indexOf(separator, beg);
			if (end > (-1)) {
				if (end > beg) {
					numberOfSubstrings += 1;
					if (numberOfSubstrings == max) {
						end = len;
						substrings.add(str.substring(beg));
					} else {
						substrings.add(str.substring(beg, end));
						beg = end + separatorLength;
					}
				} else {
					if (preserveAllTokens) {
						numberOfSubstrings += 1;
						if (numberOfSubstrings == max) {
							end = len;
							substrings.add(str.substring(beg));
						} else {
							substrings.add(org.apache.commons.lang3.StringUtils.EMPTY);
						}
					} 
					beg = end + separatorLength;
				}
			} else {
				substrings.add(str.substring(beg));
				end = len;
			}
		}
		return substrings.toArray(new java.lang.String[substrings.size()]);
	}

	public static java.lang.String[] splitPreserveAllTokens(final java.lang.String str) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, null, (-1), true);
	}

	public static java.lang.String[] splitPreserveAllTokens(final java.lang.String str, final char separatorChar) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChar, true);
	}

	private static java.lang.String[] splitWorker(final java.lang.String str, final char separatorChar, final boolean preserveAllTokens) {
		if (str == null) {
			return null;
		} 
		final int len = str.length();
		if (len == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>();
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if ((str.charAt(i)) == separatorChar) {
				if (match || preserveAllTokens) {
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				} 
				start = ++i;
				continue;
			} 
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		} 
		return list.toArray(new java.lang.String[list.size()]);
	}

	public static java.lang.String[] splitPreserveAllTokens(final java.lang.String str, final java.lang.String separatorChars) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChars, (-1), true);
	}

	public static java.lang.String[] splitPreserveAllTokens(final java.lang.String str, final java.lang.String separatorChars, final int max) {
		return org.apache.commons.lang3.StringUtils.splitWorker(str, separatorChars, max, true);
	}

	private static java.lang.String[] splitWorker(final java.lang.String str, final java.lang.String separatorChars, final int max, final boolean preserveAllTokens) {
		if (str == null) {
			return null;
		} 
		final int len = str.length();
		if (len == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			while (i < len) {
				if (java.lang.Character.isWhitespace(str.charAt(i))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if ((sizePlus1++) == max) {
							i = len;
							lastMatch = false;
						} 
						list.add(str.substring(start, i));
						match = false;
					} 
					start = ++i;
					continue;
				} 
				lastMatch = false;
				match = true;
				i++;
			}
		} else if ((separatorChars.length()) == 1) {
			final char sep = separatorChars.charAt(0);
			while (i < len) {
				if ((str.charAt(i)) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if ((sizePlus1++) == max) {
							i = len;
							lastMatch = false;
						} 
						list.add(str.substring(start, i));
						match = false;
					} 
					start = ++i;
					continue;
				} 
				lastMatch = false;
				match = true;
				i++;
			}
		} else {
			while (i < len) {
				if ((separatorChars.indexOf(str.charAt(i))) >= 0) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if ((sizePlus1++) == max) {
							i = len;
							lastMatch = false;
						} 
						list.add(str.substring(start, i));
						match = false;
					} 
					start = ++i;
					continue;
				} 
				lastMatch = false;
				match = true;
				i++;
			}
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		} 
		return list.toArray(new java.lang.String[list.size()]);
	}

	public static java.lang.String[] splitByCharacterType(final java.lang.String str) {
		return org.apache.commons.lang3.StringUtils.splitByCharacterType(str, false);
	}

	public static java.lang.String[] splitByCharacterTypeCamelCase(final java.lang.String str) {
		return org.apache.commons.lang3.StringUtils.splitByCharacterType(str, true);
	}

	private static java.lang.String[] splitByCharacterType(final java.lang.String str, final boolean camelCase) {
		if (str == null) {
			return null;
		} 
		if (str.isEmpty()) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		final char[] c = str.toCharArray();
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>();
		int tokenStart = 0;
		int currentType = java.lang.Character.getType(c[tokenStart]);
		for (int pos = tokenStart + 1 ; pos < (c.length) ; pos++) {
			final int type = java.lang.Character.getType(c[pos]);
			if (type == currentType) {
				continue;
			} 
			if ((camelCase && (type == (java.lang.Character.LOWERCASE_LETTER))) && (currentType == (java.lang.Character.UPPERCASE_LETTER))) {
				final int newTokenStart = pos - 1;
				if (newTokenStart != tokenStart) {
					list.add(new java.lang.String(c , tokenStart , (newTokenStart - tokenStart)));
					tokenStart = newTokenStart;
				} 
			} else {
				list.add(new java.lang.String(c , tokenStart , (pos - tokenStart)));
				tokenStart = pos;
			}
			currentType = type;
		}
		list.add(new java.lang.String(c , tokenStart , ((c.length) - tokenStart)));
		return list.toArray(new java.lang.String[list.size()]);
	}

	public static <T>java.lang.String join(final T... elements) {
		return org.apache.commons.lang3.StringUtils.join(elements, null);
	}

	public static java.lang.String join(final java.lang.Object[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final long[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final int[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final short[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final byte[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final char[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final float[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final double[] array, final char separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final java.lang.Object[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			if ((array[i]) != null) {
				buf.append(array[i]);
			} 
		}
		return buf.toString();
	}

	public static java.lang.String join(final long[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final int[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final byte[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final short[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final char[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final double[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final float[] array, final char separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			buf.append(array[i]);
		}
		return buf.toString();
	}

	public static java.lang.String join(final java.lang.Object[] array, final java.lang.String separator) {
		if (array == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(array, separator, 0, array.length);
	}

	public static java.lang.String join(final java.lang.Object[] array, java.lang.String separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		} 
		if (separator == null) {
			separator = org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder((noOfItems * 16));
		for (int i = startIndex ; i < endIndex ; i++) {
			if (i > startIndex) {
				buf.append(separator);
			} 
			if ((array[i]) != null) {
				buf.append(array[i]);
			} 
		}
		return buf.toString();
	}

	public static java.lang.String join(final java.util.Iterator<?> iterator, final char separator) {
		if (iterator == null) {
			return null;
		} 
		if (!(iterator.hasNext())) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.Object first = iterator.next();
		if (!(iterator.hasNext())) {
			return org.apache.commons.lang3.ObjectUtils.toString(first);
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder(256);
		if (first != null) {
			buf.append(first);
		} 
		while (iterator.hasNext()) {
			buf.append(separator);
			final java.lang.Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			} 
		}
		return buf.toString();
	}

	public static java.lang.String join(final java.util.Iterator<?> iterator, final java.lang.String separator) {
		if (iterator == null) {
			return null;
		} 
		if (!(iterator.hasNext())) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final java.lang.Object first = iterator.next();
		if (!(iterator.hasNext())) {
			return org.apache.commons.lang3.ObjectUtils.toString(first);
		} 
		final java.lang.StringBuilder buf = new java.lang.StringBuilder(256);
		if (first != null) {
			buf.append(first);
		} 
		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			} 
			final java.lang.Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			} 
		}
		return buf.toString();
	}

	public static java.lang.String join(final java.lang.Iterable<?> iterable, final char separator) {
		if (iterable == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(iterable.iterator(), separator);
	}

	public static java.lang.String join(final java.lang.Iterable<?> iterable, final java.lang.String separator) {
		if (iterable == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.join(iterable.iterator(), separator);
	}

	public static java.lang.String deleteWhitespace(final java.lang.String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		final int sz = str.length();
		final char[] chs = new char[sz];
		int count = 0;
		for (int i = 0 ; i < sz ; i++) {
			if (!(java.lang.Character.isWhitespace(str.charAt(i)))) {
				chs[(count++)] = str.charAt(i);
			} 
		}
		if (count == sz) {
			return str;
		} 
		return new java.lang.String(chs , 0 , count);
	}

	public static java.lang.String removeStart(final java.lang.String str, final java.lang.String remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(remove))) {
			return str;
		} 
		if (str.startsWith(remove)) {
			return str.substring(remove.length());
		} 
		return str;
	}

	public static java.lang.String removeStartIgnoreCase(final java.lang.String str, final java.lang.String remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(remove))) {
			return str;
		} 
		if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str, remove)) {
			return str.substring(remove.length());
		} 
		return str;
	}

	public static java.lang.String removeEnd(final java.lang.String str, final java.lang.String remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(remove))) {
			return str;
		} 
		if (str.endsWith(remove)) {
			return str.substring(0, ((str.length()) - (remove.length())));
		} 
		return str;
	}

	public static java.lang.String removeEndIgnoreCase(final java.lang.String str, final java.lang.String remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(remove))) {
			return str;
		} 
		if (org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(str, remove)) {
			return str.substring(0, ((str.length()) - (remove.length())));
		} 
		return str;
	}

	public static java.lang.String remove(final java.lang.String str, final java.lang.String remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(remove))) {
			return str;
		} 
		return org.apache.commons.lang3.StringUtils.replace(str, remove, org.apache.commons.lang3.StringUtils.EMPTY, (-1));
	}

	public static java.lang.String remove(final java.lang.String str, final char remove) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || ((str.indexOf(remove)) == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND))) {
			return str;
		} 
		final char[] chars = str.toCharArray();
		int pos = 0;
		for (int i = 0 ; i < (chars.length) ; i++) {
			if ((chars[i]) != remove) {
				chars[(pos++)] = chars[i];
			} 
		}
		return new java.lang.String(chars , 0 , pos);
	}

	public static java.lang.String replaceOnce(final java.lang.String text, final java.lang.String searchString, final java.lang.String replacement) {
		return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, 1);
	}

	public static java.lang.String replacePattern(final java.lang.String source, final java.lang.String regex, final java.lang.String replacement) {
		return java.util.regex.Pattern.compile(regex, java.util.regex.Pattern.DOTALL).matcher(source).replaceAll(replacement);
	}

	public static java.lang.String removePattern(final java.lang.String source, final java.lang.String regex) {
		return org.apache.commons.lang3.StringUtils.replacePattern(source, regex, org.apache.commons.lang3.StringUtils.EMPTY);
	}

	public static java.lang.String replace(final java.lang.String text, final java.lang.String searchString, final java.lang.String replacement) {
		return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, (-1));
	}

	public static java.lang.String replace(final java.lang.String text, final java.lang.String searchString, final java.lang.String replacement, int max) {
		if ((((org.apache.commons.lang3.StringUtils.isEmpty(text)) || (org.apache.commons.lang3.StringUtils.isEmpty(searchString))) || (replacement == null)) || (max == 0)) {
			return text;
		} 
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			return text;
		} 
		final int replLength = searchString.length();
		int increase = (replacement.length()) - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= max < 0 ? 16 : max > 64 ? 64 : max;
		final java.lang.StringBuilder buf = new java.lang.StringBuilder(((text.length()) + increase));
		while (end != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if ((--max) == 0) {
				break;
			} 
			end = text.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	public static java.lang.String replaceEach(final java.lang.String text, final java.lang.String[] searchList, final java.lang.String[] replacementList) {
		return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList, false, 0);
	}

	public static java.lang.String replaceEachRepeatedly(final java.lang.String text, final java.lang.String[] searchList, final java.lang.String[] replacementList) {
		final int timeToLive = searchList == null ? 0 : searchList.length;
		return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList, true, timeToLive);
	}

	private static java.lang.String replaceEach(final java.lang.String text, final java.lang.String[] searchList, final java.lang.String[] replacementList, final boolean repeat, final int timeToLive) {
		if ((((((text == null) || (text.isEmpty())) || (searchList == null)) || ((searchList.length) == 0)) || (replacementList == null)) || ((replacementList.length) == 0)) {
			return text;
		} 
		if (timeToLive < 0) {
			throw new java.lang.IllegalStateException(("Aborting to protect against StackOverflowError - " + "output of one loop is the input of another"));
		} 
		final int searchLength = searchList.length;
		final int replacementLength = replacementList.length;
		if (searchLength != replacementLength) {
			throw new java.lang.IllegalArgumentException(((("Search and Replace array lengths don't match: " + searchLength) + " vs ") + replacementLength));
		} 
		final boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];
		int textIndex = -1;
		int replaceIndex = -1;
		int tempIndex = -1;
		for (int i = 0 ; i < searchLength ; i++) {
			if ((((noMoreMatchesForReplIndex[i]) || ((searchList[i]) == null)) || (searchList[i].isEmpty())) || ((replacementList[i]) == null)) {
				continue;
			} 
			tempIndex = text.indexOf(searchList[i]);
			if (tempIndex == (-1)) {
				noMoreMatchesForReplIndex[i] = true;
			} else {
				if ((textIndex == (-1)) || (tempIndex < textIndex)) {
					textIndex = tempIndex;
					replaceIndex = i;
				} 
			}
		}
		if (textIndex == (-1)) {
			return text;
		} 
		int start = 0;
		int increase = 0;
		for (int i = 0 ; i < (searchList.length) ; i++) {
			if (((searchList[i]) == null) || ((replacementList[i]) == null)) {
				continue;
			} 
			final int greater = (replacementList[i].length()) - (searchList[i].length());
			if (greater > 0) {
				increase += 3 * greater;
			} 
		}
		increase = java.lang.Math.min(increase, ((text.length()) / 5));
		final java.lang.StringBuilder buf = new java.lang.StringBuilder(((text.length()) + increase));
		while (textIndex != (-1)) {
			for (int i = start ; i < textIndex ; i++) {
				buf.append(text.charAt(i));
			}
			buf.append(replacementList[replaceIndex]);
			start = textIndex + (searchList[replaceIndex].length());
			textIndex = -1;
			replaceIndex = -1;
			tempIndex = -1;
			for (int i = 0 ; i < searchLength ; i++) {
				if ((((noMoreMatchesForReplIndex[i]) || ((searchList[i]) == null)) || (searchList[i].isEmpty())) || ((replacementList[i]) == null)) {
					continue;
				} 
				tempIndex = text.indexOf(searchList[i], start);
				if (tempIndex == (-1)) {
					noMoreMatchesForReplIndex[i] = true;
				} else {
					if ((textIndex == (-1)) || (tempIndex < textIndex)) {
						textIndex = tempIndex;
						replaceIndex = i;
					} 
				}
			}
		}
		final int textLength = text.length();
		for (int i = start ; i < textLength ; i++) {
			buf.append(text.charAt(i));
		}
		final java.lang.String result = buf.toString();
		if (!repeat) {
			return result;
		} 
		return org.apache.commons.lang3.StringUtils.replaceEach(result, searchList, replacementList, repeat, (timeToLive - 1));
	}

	public static java.lang.String replaceChars(final java.lang.String str, final char searchChar, final char replaceChar) {
		if (str == null) {
			return null;
		} 
		return str.replace(searchChar, replaceChar);
	}

	public static java.lang.String replaceChars(final java.lang.String str, final java.lang.String searchChars, java.lang.String replaceChars) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(searchChars))) {
			return str;
		} 
		if (replaceChars == null) {
			replaceChars = org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		boolean modified = false;
		final int replaceCharsLength = replaceChars.length();
		final int strLength = str.length();
		final java.lang.StringBuilder buf = new java.lang.StringBuilder(strLength);
		for (int i = 0 ; i < strLength ; i++) {
			final char ch = str.charAt(i);
			final int index = searchChars.indexOf(ch);
			if (index >= 0) {
				modified = true;
				if (index < replaceCharsLength) {
					buf.append(replaceChars.charAt(index));
				} 
			} else {
				buf.append(ch);
			}
		}
		if (modified) {
			return buf.toString();
		} 
		return str;
	}

	public static java.lang.String overlay(final java.lang.String str, java.lang.String overlay, int start, int end) {
		if (str == null) {
			return null;
		} 
		if (overlay == null) {
			overlay = org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int len = str.length();
		if (start < 0) {
			start = 0;
		} 
		if (start > len) {
			start = len;
		} 
		if (end < 0) {
			end = 0;
		} 
		if (end > len) {
			end = len;
		} 
		if (start > end) {
			final int temp = start;
			start = end;
			end = temp;
		} 
		return new java.lang.StringBuilder(((((len + start) - end) + (overlay.length())) + 1)).append(str.substring(0, start)).append(overlay).append(str.substring(end)).toString();
	}

	public static java.lang.String chomp(final java.lang.String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		if ((str.length()) == 1) {
			final char ch = str.charAt(0);
			if ((ch == (org.apache.commons.lang3.CharUtils.CR)) || (ch == (org.apache.commons.lang3.CharUtils.LF))) {
				return org.apache.commons.lang3.StringUtils.EMPTY;
			} 
			return str;
		} 
		int lastIdx = (str.length()) - 1;
		final char last = str.charAt(lastIdx);
		if (last == (org.apache.commons.lang3.CharUtils.LF)) {
			if ((str.charAt((lastIdx - 1))) == (org.apache.commons.lang3.CharUtils.CR)) {
				lastIdx--;
			} 
		} else if (last != (org.apache.commons.lang3.CharUtils.CR)) {
			lastIdx++;
		} 
		return str.substring(0, lastIdx);
	}

	@java.lang.Deprecated
	public static java.lang.String chomp(final java.lang.String str, final java.lang.String separator) {
		return org.apache.commons.lang3.StringUtils.removeEnd(str, separator);
	}

	public static java.lang.String chop(final java.lang.String str) {
		if (str == null) {
			return null;
		} 
		final int strLen = str.length();
		if (strLen < 2) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int lastIdx = strLen - 1;
		final java.lang.String ret = str.substring(0, lastIdx);
		final char last = str.charAt(lastIdx);
		if ((last == (org.apache.commons.lang3.CharUtils.LF)) && ((ret.charAt((lastIdx - 1))) == (org.apache.commons.lang3.CharUtils.CR))) {
			return ret.substring(0, (lastIdx - 1));
		} 
		return ret;
	}

	public static java.lang.String repeat(final java.lang.String str, final int repeat) {
		if (str == null) {
			return null;
		} 
		if (repeat <= 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int inputLength = str.length();
		if ((repeat == 1) || (inputLength == 0)) {
			return str;
		} 
		if ((inputLength == 1) && (repeat <= (org.apache.commons.lang3.StringUtils.PAD_LIMIT))) {
			return org.apache.commons.lang3.StringUtils.repeat(str.charAt(0), repeat);
		} 
		final int outputLength = inputLength * repeat;
		switch (inputLength) {
			case 1 :
				return org.apache.commons.lang3.StringUtils.repeat(str.charAt(0), repeat);
			case 2 :
				final char ch0 = str.charAt(0);
				final char ch1 = str.charAt(1);
				final char[] output2 = new char[outputLength];
				for (int i = (repeat * 2) - 2 ; i >= 0 ; i-- , i--) {
					output2[i] = ch0;
					output2[(i + 1)] = ch1;
				}
				return new java.lang.String(output2);
			default :
				final java.lang.StringBuilder buf = new java.lang.StringBuilder(outputLength);
				for (int i = 0 ; i < repeat ; i++) {
					buf.append(str);
				}
				return buf.toString();
		}
	}

	public static java.lang.String repeat(final java.lang.String str, final java.lang.String separator, final int repeat) {
		if ((str == null) || (separator == null)) {
			return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
		} 
		final java.lang.String result = org.apache.commons.lang3.StringUtils.repeat((str + separator), repeat);
		return org.apache.commons.lang3.StringUtils.removeEnd(result, separator);
	}

	public static java.lang.String repeat(final char ch, final int repeat) {
		final char[] buf = new char[repeat];
		for (int i = repeat - 1 ; i >= 0 ; i--) {
			buf[i] = ch;
		}
		return new java.lang.String(buf);
	}

	public static java.lang.String rightPad(final java.lang.String str, final int size) {
		return org.apache.commons.lang3.StringUtils.rightPad(str, size, ' ');
	}

	public static java.lang.String rightPad(final java.lang.String str, final int size, final char padChar) {
		if (str == null) {
			return null;
		} 
		final int pads = size - (str.length());
		if (pads <= 0) {
			return str;
		} 
		if (pads > (org.apache.commons.lang3.StringUtils.PAD_LIMIT)) {
			return org.apache.commons.lang3.StringUtils.rightPad(str, size, java.lang.String.valueOf(padChar));
		} 
		return str.concat(org.apache.commons.lang3.StringUtils.repeat(padChar, pads));
	}

	public static java.lang.String rightPad(final java.lang.String str, final int size, java.lang.String padStr) {
		if (str == null) {
			return null;
		} 
		if (org.apache.commons.lang3.StringUtils.isEmpty(padStr)) {
			padStr = org.apache.commons.lang3.StringUtils.SPACE;
		} 
		final int padLen = padStr.length();
		final int strLen = str.length();
		final int pads = size - strLen;
		if (pads <= 0) {
			return str;
		} 
		if ((padLen == 1) && (pads <= (org.apache.commons.lang3.StringUtils.PAD_LIMIT))) {
			return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr.charAt(0));
		} 
		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			final char[] padding = new char[pads];
			final char[] padChars = padStr.toCharArray();
			for (int i = 0 ; i < pads ; i++) {
				padding[i] = padChars[(i % padLen)];
			}
			return str.concat(new java.lang.String(padding));
		}
	}

	public static java.lang.String leftPad(final java.lang.String str, final int size) {
		return org.apache.commons.lang3.StringUtils.leftPad(str, size, ' ');
	}

	public static java.lang.String leftPad(final java.lang.String str, final int size, final char padChar) {
		if (str == null) {
			return null;
		} 
		final int pads = size - (str.length());
		if (pads <= 0) {
			return str;
		} 
		if (pads > (org.apache.commons.lang3.StringUtils.PAD_LIMIT)) {
			return org.apache.commons.lang3.StringUtils.leftPad(str, size, java.lang.String.valueOf(padChar));
		} 
		return org.apache.commons.lang3.StringUtils.repeat(padChar, pads).concat(str);
	}

	public static java.lang.String leftPad(final java.lang.String str, final int size, java.lang.String padStr) {
		if (str == null) {
			return null;
		} 
		if (org.apache.commons.lang3.StringUtils.isEmpty(padStr)) {
			padStr = org.apache.commons.lang3.StringUtils.SPACE;
		} 
		final int padLen = padStr.length();
		final int strLen = str.length();
		final int pads = size - strLen;
		if (pads <= 0) {
			return str;
		} 
		if ((padLen == 1) && (pads <= (org.apache.commons.lang3.StringUtils.PAD_LIMIT))) {
			return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr.charAt(0));
		} 
		if (pads == padLen) {
			return padStr.concat(str);
		} else if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		} else {
			final char[] padding = new char[pads];
			final char[] padChars = padStr.toCharArray();
			for (int i = 0 ; i < pads ; i++) {
				padding[i] = padChars[(i % padLen)];
			}
			return new java.lang.String(padding).concat(str);
		}
	}

	public static int length(final java.lang.CharSequence cs) {
		return cs == null ? 0 : cs.length();
	}

	public static java.lang.String center(final java.lang.String str, final int size) {
		return org.apache.commons.lang3.StringUtils.center(str, size, ' ');
	}

	public static java.lang.String center(java.lang.String str, final int size, final char padChar) {
		if ((str == null) || (size <= 0)) {
			return str;
		} 
		final int strLen = str.length();
		final int pads = size - strLen;
		if (pads <= 0) {
			return str;
		} 
		str = org.apache.commons.lang3.StringUtils.leftPad(str, (strLen + (pads / 2)), padChar);
		str = org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
		return str;
	}

	public static java.lang.String center(java.lang.String str, final int size, java.lang.String padStr) {
		if ((str == null) || (size <= 0)) {
			return str;
		} 
		if (org.apache.commons.lang3.StringUtils.isEmpty(padStr)) {
			padStr = org.apache.commons.lang3.StringUtils.SPACE;
		} 
		final int strLen = str.length();
		final int pads = size - strLen;
		if (pads <= 0) {
			return str;
		} 
		str = org.apache.commons.lang3.StringUtils.leftPad(str, (strLen + (pads / 2)), padStr);
		str = org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
		return str;
	}

	public static java.lang.String upperCase(final java.lang.String str) {
		if (str == null) {
			return null;
		} 
		return str.toUpperCase();
	}

	public static java.lang.String upperCase(final java.lang.String str, final java.util.Locale locale) {
		if (str == null) {
			return null;
		} 
		return str.toUpperCase(locale);
	}

	public static java.lang.String lowerCase(final java.lang.String str) {
		if (str == null) {
			return null;
		} 
		return str.toLowerCase();
	}

	public static java.lang.String lowerCase(final java.lang.String str, final java.util.Locale locale) {
		if (str == null) {
			return null;
		} 
		return str.toLowerCase(locale);
	}

	public static java.lang.String capitalize(final java.lang.String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		} 
		char firstChar = str.charAt(0);
		if (java.lang.Character.isTitleCase(firstChar)) {
			return str;
		} 
		return new java.lang.StringBuilder(strLen).append(java.lang.Character.toTitleCase(firstChar)).append(str.substring(1)).toString();
	}

	public static java.lang.String uncapitalize(final java.lang.String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		} 
		char firstChar = str.charAt(0);
		if (java.lang.Character.isLowerCase(firstChar)) {
			return str;
		} 
		return new java.lang.StringBuilder(strLen).append(java.lang.Character.toLowerCase(firstChar)).append(str.substring(1)).toString();
	}

	public static java.lang.String swapCase(final java.lang.String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return str;
		} 
		final char[] buffer = str.toCharArray();
		for (int i = 0 ; i < (buffer.length) ; i++) {
			final char ch = buffer[i];
			if (java.lang.Character.isUpperCase(ch)) {
				buffer[i] = java.lang.Character.toLowerCase(ch);
			} else if (java.lang.Character.isTitleCase(ch)) {
				buffer[i] = java.lang.Character.toLowerCase(ch);
			} else if (java.lang.Character.isLowerCase(ch)) {
				buffer[i] = java.lang.Character.toUpperCase(ch);
			} 
		}
		return new java.lang.String(buffer);
	}

	public static int countMatches(final java.lang.CharSequence str, final java.lang.CharSequence sub) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(sub))) {
			return 0;
		} 
		int count = 0;
		int idx = 0;
		while ((idx = org.apache.commons.lang3.CharSequenceUtils.indexOf(str, sub, idx)) != (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	public static boolean isAlpha(final java.lang.CharSequence cs) {
		if ((cs == null) || ((cs.length()) == 0)) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isLetter(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAlphaSpace(final java.lang.CharSequence cs) {
		if (cs == null) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if (((java.lang.Character.isLetter(cs.charAt(i))) == false) && ((cs.charAt(i)) != ' ')) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAlphanumeric(final java.lang.CharSequence cs) {
		if ((cs == null) || ((cs.length()) == 0)) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isLetterOrDigit(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAlphanumericSpace(final java.lang.CharSequence cs) {
		if (cs == null) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if (((java.lang.Character.isLetterOrDigit(cs.charAt(i))) == false) && ((cs.charAt(i)) != ' ')) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAsciiPrintable(final java.lang.CharSequence cs) {
		if (cs == null) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((org.apache.commons.lang3.CharUtils.isAsciiPrintable(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isNumeric(final java.lang.CharSequence cs) {
		if ((cs == null) || ((cs.length()) == 0)) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isDigit(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isNumericSpace(final java.lang.CharSequence cs) {
		if (cs == null) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if (((java.lang.Character.isDigit(cs.charAt(i))) == false) && ((cs.charAt(i)) != ' ')) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isWhitespace(final java.lang.CharSequence cs) {
		if (cs == null) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isWhitespace(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAllLowerCase(final java.lang.CharSequence cs) {
		if ((cs == null) || (org.apache.commons.lang3.StringUtils.isEmpty(cs))) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isLowerCase(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static boolean isAllUpperCase(final java.lang.CharSequence cs) {
		if ((cs == null) || (org.apache.commons.lang3.StringUtils.isEmpty(cs))) {
			return false;
		} 
		final int sz = cs.length();
		for (int i = 0 ; i < sz ; i++) {
			if ((java.lang.Character.isUpperCase(cs.charAt(i))) == false) {
				return false;
			} 
		}
		return true;
	}

	public static java.lang.String defaultString(final java.lang.String str) {
		return str == null ? org.apache.commons.lang3.StringUtils.EMPTY : str;
	}

	public static java.lang.String defaultString(final java.lang.String str, final java.lang.String defaultStr) {
		return str == null ? defaultStr : str;
	}

	public static <T extends java.lang.CharSequence>T defaultIfBlank(final T str, final T defaultStr) {
		return org.apache.commons.lang3.StringUtils.isBlank(str) ? defaultStr : str;
	}

	public static <T extends java.lang.CharSequence>T defaultIfEmpty(final T str, final T defaultStr) {
		return org.apache.commons.lang3.StringUtils.isEmpty(str) ? defaultStr : str;
	}

	public static java.lang.String reverse(final java.lang.String str) {
		if (str == null) {
			return null;
		} 
		return new java.lang.StringBuilder(str).reverse().toString();
	}

	public static java.lang.String reverseDelimited(final java.lang.String str, final char separatorChar) {
		if (str == null) {
			return null;
		} 
		final java.lang.String[] strs = org.apache.commons.lang3.StringUtils.split(str, separatorChar);
		org.apache.commons.lang3.ArrayUtils.reverse(strs);
		return org.apache.commons.lang3.StringUtils.join(strs, separatorChar);
	}

	public static java.lang.String abbreviate(final java.lang.String str, final int maxWidth) {
		return org.apache.commons.lang3.StringUtils.abbreviate(str, 0, maxWidth);
	}

	public static java.lang.String abbreviate(final java.lang.String str, int offset, final int maxWidth) {
		if (str == null) {
			return null;
		} 
		if (maxWidth < 4) {
			throw new java.lang.IllegalArgumentException("Minimum abbreviation width is 4");
		} 
		if ((str.length()) <= maxWidth) {
			return str;
		} 
		if (offset > (str.length())) {
			offset = str.length();
		} 
		if (((str.length()) - offset) < (maxWidth - 3)) {
			offset = (str.length()) - (maxWidth - 3);
		} 
		final java.lang.String abrevMarker = "...";
		if (offset <= 4) {
			return (str.substring(0, (maxWidth - 3))) + abrevMarker;
		} 
		if (maxWidth < 7) {
			throw new java.lang.IllegalArgumentException("Minimum abbreviation width with offset is 7");
		} 
		if (((offset + maxWidth) - 3) < (str.length())) {
			return abrevMarker + (org.apache.commons.lang3.StringUtils.abbreviate(str.substring(offset), (maxWidth - 3)));
		} 
		return abrevMarker + (str.substring(((str.length()) - (maxWidth - 3))));
	}

	public static java.lang.String abbreviateMiddle(final java.lang.String str, final java.lang.String middle, final int length) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(str)) || (org.apache.commons.lang3.StringUtils.isEmpty(middle))) {
			return str;
		} 
		if ((length >= (str.length())) || (length < ((middle.length()) + 2))) {
			return str;
		} 
		final int targetSting = length - (middle.length());
		final int startOffset = (targetSting / 2) + (targetSting % 2);
		final int endOffset = (str.length()) - (targetSting / 2);
		final java.lang.StringBuilder builder = new java.lang.StringBuilder(length);
		builder.append(str.substring(0, startOffset));
		builder.append(middle);
		builder.append(str.substring(endOffset));
		return builder.toString();
	}

	public static java.lang.String difference(final java.lang.String str1, final java.lang.String str2) {
		if (str1 == null) {
			return str2;
		} 
		if (str2 == null) {
			return str1;
		} 
		final int at = org.apache.commons.lang3.StringUtils.indexOfDifference(str1, str2);
		if (at == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		return str2.substring(at);
	}

	public static int indexOfDifference(final java.lang.CharSequence cs1, final java.lang.CharSequence cs2) {
		if (cs1 == cs2) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if ((cs1 == null) || (cs2 == null)) {
			return 0;
		} 
		int i;
		for (i = 0 ; (i < (cs1.length())) && (i < (cs2.length())) ; ++i) {
			if ((cs1.charAt(i)) != (cs2.charAt(i))) {
				break;
			} 
		}
		if ((i < (cs2.length())) || (i < (cs1.length()))) {
			return i;
		} 
		return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
	}

	public static int indexOfDifference(final java.lang.CharSequence... css) {
		if ((css == null) || ((css.length) <= 1)) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		boolean anyStringNull = false;
		boolean allStringsNull = true;
		final int arrayLen = css.length;
		int shortestStrLen = java.lang.Integer.MAX_VALUE;
		int longestStrLen = 0;
		for (int i = 0 ; i < arrayLen ; i++) {
			if ((css[i]) == null) {
				anyStringNull = true;
				shortestStrLen = 0;
			} else {
				allStringsNull = false;
				shortestStrLen = java.lang.Math.min(css[i].length(), shortestStrLen);
				longestStrLen = java.lang.Math.max(css[i].length(), longestStrLen);
			}
		}
		if (allStringsNull || ((longestStrLen == 0) && (!anyStringNull))) {
			return org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
		} 
		if (shortestStrLen == 0) {
			return 0;
		} 
		int firstDiff = -1;
		for (int stringPos = 0 ; stringPos < shortestStrLen ; stringPos++) {
			final char comparisonChar = css[0].charAt(stringPos);
			for (int arrayPos = 1 ; arrayPos < arrayLen ; arrayPos++) {
				if ((css[arrayPos].charAt(stringPos)) != comparisonChar) {
					firstDiff = stringPos;
					break;
				} 
			}
			if (firstDiff != (-1)) {
				break;
			} 
		}
		if ((firstDiff == (-1)) && (shortestStrLen != longestStrLen)) {
			return shortestStrLen;
		} 
		return firstDiff;
	}

	public static java.lang.String getCommonPrefix(final java.lang.String... strs) {
		if ((strs == null) || ((strs.length) == 0)) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} 
		final int smallestIndexOfDiff = org.apache.commons.lang3.StringUtils.indexOfDifference(strs);
		if (smallestIndexOfDiff == (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND)) {
			if ((strs[0]) == null) {
				return org.apache.commons.lang3.StringUtils.EMPTY;
			} 
			return strs[0];
		} else if (smallestIndexOfDiff == 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} else {
			return strs[0].substring(0, smallestIndexOfDiff);
		}
	}

	public static int getLevenshteinDistance(java.lang.CharSequence s, java.lang.CharSequence t) {
		if ((s == null) || (t == null)) {
			throw new java.lang.IllegalArgumentException("Strings must not be null");
		} 
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		} 
		if (n > m) {
			final java.lang.CharSequence tmp = s;
			s = t;
			t = tmp;
			n = m;
			m = t.length();
		} 
		int[] p = new int[n + 1];
		int[] d = new int[n + 1];
		int[] _d;
		int i;
		int j;
		char t_j;
		int cost;
		for (i = 0 ; i <= n ; i++) {
			p[i] = i;
		}
		for (j = 1 ; j <= m ; j++) {
			t_j = t.charAt((j - 1));
			d[0] = j;
			for (i = 1 ; i <= n ; i++) {
				cost = (s.charAt((i - 1))) == t_j ? 0 : 1;
				d[i] = java.lang.Math.min(java.lang.Math.min(((d[(i - 1)]) + 1), ((p[i]) + 1)), ((p[(i - 1)]) + cost));
			}
			_d = p;
			p = d;
			d = _d;
		}
		return p[n];
	}

	public static int getLevenshteinDistance(java.lang.CharSequence s, java.lang.CharSequence t, final int threshold) {
		if ((s == null) || (t == null)) {
			throw new java.lang.IllegalArgumentException("Strings must not be null");
		} 
		if (threshold < 0) {
			throw new java.lang.IllegalArgumentException("Threshold must not be negative");
		} 
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return m <= threshold ? m : -1;
		} else if (m == 0) {
			return n <= threshold ? n : -1;
		} 
		if (n > m) {
			final java.lang.CharSequence tmp = s;
			s = t;
			t = tmp;
			n = m;
			m = t.length();
		} 
		int[] p = new int[n + 1];
		int[] d = new int[n + 1];
		int[] _d;
		final int boundary = (java.lang.Math.min(n, threshold)) + 1;
		for (int i = 0 ; i < boundary ; i++) {
			p[i] = i;
		}
		java.util.Arrays.fill(p, boundary, p.length, java.lang.Integer.MAX_VALUE);
		java.util.Arrays.fill(d, java.lang.Integer.MAX_VALUE);
		for (int j = 1 ; j <= m ; j++) {
			final char t_j = t.charAt((j - 1));
			d[0] = j;
			final int min = java.lang.Math.max(1, (j - threshold));
			final int max = java.lang.Math.min(n, (j + threshold));
			if (min > max) {
				return -1;
			} 
			if (min > 1) {
				d[(min - 1)] = java.lang.Integer.MAX_VALUE;
			} 
			for (int i = min ; i <= max ; i++) {
				if ((s.charAt((i - 1))) == t_j) {
					d[i] = p[(i - 1)];
				} else {
					d[i] = 1 + (java.lang.Math.min(java.lang.Math.min(d[(i - 1)], p[i]), p[(i - 1)]));
				}
			}
			_d = p;
			p = d;
			d = _d;
		}
		if ((p[n]) <= threshold) {
			return p[n];
		} 
		return -1;
	}

	public static boolean startsWith(final java.lang.CharSequence str, final java.lang.CharSequence prefix) {
		return org.apache.commons.lang3.StringUtils.startsWith(str, prefix, false);
	}

	public static boolean startsWithIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence prefix) {
		return org.apache.commons.lang3.StringUtils.startsWith(str, prefix, true);
	}

	private static boolean startsWith(final java.lang.CharSequence str, final java.lang.CharSequence prefix, final boolean ignoreCase) {
		if ((str == null) || (prefix == null)) {
			return (str == null) && (prefix == null);
		} 
		if ((prefix.length()) > (str.length())) {
			return false;
		} 
		return org.apache.commons.lang3.CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
	}

	public static boolean startsWithAny(final java.lang.CharSequence string, final java.lang.CharSequence... searchStrings) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(string)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(searchStrings))) {
			return false;
		} 
		for (final java.lang.CharSequence searchString : searchStrings) {
			if (org.apache.commons.lang3.StringUtils.startsWith(string, searchString)) {
				return true;
			} 
		}
		return false;
	}

	public static boolean endsWith(final java.lang.CharSequence str, final java.lang.CharSequence suffix) {
		return org.apache.commons.lang3.StringUtils.endsWith(str, suffix, false);
	}

	public static boolean endsWithIgnoreCase(final java.lang.CharSequence str, final java.lang.CharSequence suffix) {
		return org.apache.commons.lang3.StringUtils.endsWith(str, suffix, true);
	}

	private static boolean endsWith(final java.lang.CharSequence str, final java.lang.CharSequence suffix, final boolean ignoreCase) {
		if ((str == null) || (suffix == null)) {
			return (str == null) && (suffix == null);
		} 
		if ((suffix.length()) > (str.length())) {
			return false;
		} 
		final int strOffset = (str.length()) - (suffix.length());
		return org.apache.commons.lang3.CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
	}

	public static java.lang.String normalizeSpace(final java.lang.String str) {
		if (str == null) {
			return null;
		} 
		return org.apache.commons.lang3.StringUtils.WHITESPACE_PATTERN.matcher(org.apache.commons.lang3.StringUtils.trim(str)).replaceAll(org.apache.commons.lang3.StringUtils.SPACE);
	}

	public static boolean endsWithAny(final java.lang.CharSequence string, final java.lang.CharSequence... searchStrings) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(string)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(searchStrings))) {
			return false;
		} 
		for (final java.lang.CharSequence searchString : searchStrings) {
			if (org.apache.commons.lang3.StringUtils.endsWith(string, searchString)) {
				return true;
			} 
		}
		return false;
	}

	private static java.lang.String appendIfMissing(final java.lang.String str, final java.lang.CharSequence suffix, final boolean ignoreCase, final java.lang.CharSequence... suffixes) {
		if (((str == null) || (org.apache.commons.lang3.StringUtils.isEmpty(suffix))) || (org.apache.commons.lang3.StringUtils.endsWith(str, suffix, ignoreCase))) {
			return str;
		} 
		if ((suffixes != null) && ((suffixes.length) > 0)) {
			for (final java.lang.CharSequence s : suffixes) {
				if (org.apache.commons.lang3.StringUtils.endsWith(str, s, ignoreCase)) {
					return str;
				} 
			}
		} 
		return str + (suffix.toString());
	}

	public static java.lang.String appendIfMissing(final java.lang.String str, final java.lang.CharSequence suffix, final java.lang.CharSequence... suffixes) {
		return org.apache.commons.lang3.StringUtils.appendIfMissing(str, suffix, false, suffixes);
	}

	public static java.lang.String appendIfMissingIgnoreCase(final java.lang.String str, final java.lang.CharSequence suffix, final java.lang.CharSequence... suffixes) {
		return org.apache.commons.lang3.StringUtils.appendIfMissing(str, suffix, true, suffixes);
	}

	private static java.lang.String prependIfMissing(final java.lang.String str, final java.lang.CharSequence prefix, final boolean ignoreCase, final java.lang.CharSequence... prefixes) {
		if (((str == null) || (org.apache.commons.lang3.StringUtils.isEmpty(prefix))) || (org.apache.commons.lang3.StringUtils.startsWith(str, prefix, ignoreCase))) {
			return str;
		} 
		if ((prefixes != null) && ((prefixes.length) > 0)) {
			for (final java.lang.CharSequence p : prefixes) {
				if (org.apache.commons.lang3.StringUtils.startsWith(str, p, ignoreCase)) {
					return str;
				} 
			}
		} 
		return (prefix.toString()) + str;
	}

	public static java.lang.String prependIfMissing(final java.lang.String str, final java.lang.CharSequence prefix, final java.lang.CharSequence... prefixes) {
		return org.apache.commons.lang3.StringUtils.prependIfMissing(str, prefix, false, prefixes);
	}

	public static java.lang.String prependIfMissingIgnoreCase(final java.lang.String str, final java.lang.CharSequence prefix, final java.lang.CharSequence... prefixes) {
		return org.apache.commons.lang3.StringUtils.prependIfMissing(str, prefix, true, prefixes);
	}

	public static java.lang.String toString(final byte[] bytes, final java.lang.String charsetName) throws java.io.UnsupportedEncodingException {
		return charsetName == null ? new java.lang.String(bytes) : new java.lang.String(bytes , charsetName);
	}
}

