package org.apache.commons.lang3;


public class CharUtils {
	private static final java.lang.String[] CHAR_STRING_ARRAY = new java.lang.String[128];

	public static final char LF = '\n';

	public static final char CR = '\r';

	static {
		for (char c = 0 ; c < (org.apache.commons.lang3.CharUtils.CHAR_STRING_ARRAY.length) ; c++) {
			org.apache.commons.lang3.CharUtils.CHAR_STRING_ARRAY[c] = java.lang.String.valueOf(c);
		}
	}

	public CharUtils() {
		super();
	}

	@java.lang.Deprecated
	public static java.lang.Character toCharacterObject(final char ch) {
		return java.lang.Character.valueOf(ch);
	}

	public static java.lang.Character toCharacterObject(final java.lang.String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return null;
		} 
		return java.lang.Character.valueOf(str.charAt(0));
	}

	public static char toChar(final java.lang.Character ch) {
		if (ch == null) {
			throw new java.lang.IllegalArgumentException("The Character must not be null");
		} 
		return ch.charValue();
	}

	public static char toChar(final java.lang.Character ch, final char defaultValue) {
		if (ch == null) {
			return defaultValue;
		} 
		return ch.charValue();
	}

	public static char toChar(final java.lang.String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			throw new java.lang.IllegalArgumentException("The String must not be empty");
		} 
		return str.charAt(0);
	}

	public static char toChar(final java.lang.String str, final char defaultValue) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return defaultValue;
		} 
		return str.charAt(0);
	}

	public static int toIntValue(final char ch) {
		if ((org.apache.commons.lang3.CharUtils.isAsciiNumeric(ch)) == false) {
			throw new java.lang.IllegalArgumentException((("The character " + ch) + " is not in the range '0' - '9'"));
		} 
		return ch - 48;
	}

	public static int toIntValue(final char ch, final int defaultValue) {
		if ((org.apache.commons.lang3.CharUtils.isAsciiNumeric(ch)) == false) {
			return defaultValue;
		} 
		return ch - 48;
	}

	public static int toIntValue(final java.lang.Character ch) {
		if (ch == null) {
			throw new java.lang.IllegalArgumentException("The character must not be null");
		} 
		return org.apache.commons.lang3.CharUtils.toIntValue(ch.charValue());
	}

	public static int toIntValue(final java.lang.Character ch, final int defaultValue) {
		if (ch == null) {
			return defaultValue;
		} 
		return org.apache.commons.lang3.CharUtils.toIntValue(ch.charValue(), defaultValue);
	}

	public static java.lang.String toString(final char ch) {
		if (ch < 128) {
			return org.apache.commons.lang3.CharUtils.CHAR_STRING_ARRAY[ch];
		} 
		return new java.lang.String(new char[]{ ch });
	}

	public static java.lang.String toString(final java.lang.Character ch) {
		if (ch == null) {
			return null;
		} 
		return org.apache.commons.lang3.CharUtils.toString(ch.charValue());
	}

	public static java.lang.String unicodeEscaped(final char ch) {
		if (ch < 16) {
			return "\\u000" + (java.lang.Integer.toHexString(ch));
		} else if (ch < 256) {
			return "\\u00" + (java.lang.Integer.toHexString(ch));
		} else if (ch < 4096) {
			return "\\u0" + (java.lang.Integer.toHexString(ch));
		} 
		return "\\u" + (java.lang.Integer.toHexString(ch));
	}

	public static java.lang.String unicodeEscaped(final java.lang.Character ch) {
		if (ch == null) {
			return null;
		} 
		return org.apache.commons.lang3.CharUtils.unicodeEscaped(ch.charValue());
	}

	public static boolean isAscii(final char ch) {
		return ch < 128;
	}

	public static boolean isAsciiPrintable(final char ch) {
		return (ch >= 32) && (ch < 127);
	}

	public static boolean isAsciiControl(final char ch) {
		return (ch < 32) || (ch == 127);
	}

	public static boolean isAsciiAlpha(final char ch) {
		return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'));
	}

	public static boolean isAsciiAlphaUpper(final char ch) {
		return (ch >= 'A') && (ch <= 'Z');
	}

	public static boolean isAsciiAlphaLower(final char ch) {
		return (ch >= 'a') && (ch <= 'z');
	}

	public static boolean isAsciiNumeric(final char ch) {
		return (ch >= '0') && (ch <= '9');
	}

	public static boolean isAsciiAlphanumeric(final char ch) {
		return (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))) || ((ch >= '0') && (ch <= '9'));
	}
}

