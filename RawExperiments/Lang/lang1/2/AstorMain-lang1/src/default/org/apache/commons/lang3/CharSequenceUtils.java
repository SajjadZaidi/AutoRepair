package org.apache.commons.lang3;


public class CharSequenceUtils {
	public CharSequenceUtils() {
		super();
	}

	public static java.lang.CharSequence subSequence(final java.lang.CharSequence cs, final int start) {
		return cs == null ? null : cs.subSequence(start, cs.length());
	}

	static int indexOf(final java.lang.CharSequence cs, final int searchChar, int start) {
		if (cs instanceof java.lang.String) {
			return ((java.lang.String)(cs)).indexOf(searchChar, start);
		} else {
			final int sz = cs.length();
			if (start < 0) {
				start = 0;
			} 
			for (int i = start ; i < sz ; i++) {
				if ((cs.charAt(i)) == searchChar) {
					return i;
				} 
			}
			return -1;
		}
	}

	static int indexOf(final java.lang.CharSequence cs, final java.lang.CharSequence searchChar, final int start) {
		return cs.toString().indexOf(searchChar.toString(), start);
	}

	static int lastIndexOf(final java.lang.CharSequence cs, final int searchChar, int start) {
		if (cs instanceof java.lang.String) {
			return ((java.lang.String)(cs)).lastIndexOf(searchChar, start);
		} else {
			final int sz = cs.length();
			if (start < 0) {
				return -1;
			} 
			if (start >= sz) {
				start = sz - 1;
			} 
			for (int i = start ; i >= 0 ; --i) {
				if ((cs.charAt(i)) == searchChar) {
					return i;
				} 
			}
			return -1;
		}
	}

	static int lastIndexOf(final java.lang.CharSequence cs, final java.lang.CharSequence searchChar, final int start) {
		return cs.toString().lastIndexOf(searchChar.toString(), start);
	}

	static char[] toCharArray(final java.lang.CharSequence cs) {
		if (cs instanceof java.lang.String) {
			return ((java.lang.String)(cs)).toCharArray();
		} else {
			final int sz = cs.length();
			final char[] array = new char[cs.length()];
			for (int i = 0 ; i < sz ; i++) {
				array[i] = cs.charAt(i);
			}
			return array;
		}
	}

	static boolean regionMatches(final java.lang.CharSequence cs, final boolean ignoreCase, final int thisStart, final java.lang.CharSequence substring, final int start, final int length) {
		if ((cs instanceof java.lang.String) && (substring instanceof java.lang.String)) {
			return ((java.lang.String)(cs)).regionMatches(ignoreCase, thisStart, ((java.lang.String)(substring)), start, length);
		} else {
			int index1 = thisStart;
			int index2 = start;
			int tmpLen = length;
			while ((tmpLen--) > 0) {
				char c1 = cs.charAt((index1++));
				char c2 = substring.charAt((index2++));
				if (c1 == c2) {
					continue;
				} 
				if (!ignoreCase) {
					return false;
				} 
				if (((java.lang.Character.toUpperCase(c1)) != (java.lang.Character.toUpperCase(c2))) && ((java.lang.Character.toLowerCase(c1)) != (java.lang.Character.toLowerCase(c2)))) {
					return false;
				} 
			}
			return true;
		}
	}
}

