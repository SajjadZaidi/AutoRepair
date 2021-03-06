package org.apache.commons.lang;


public class CharUtils {
	private static final java.lang.String CHAR_STRING = " " + ("\b\t\n\f\r" + ("" + ("" + (" !\"#$%&\'" + ("()*+,-./" + ("01234567" + ("89:;<=>?" + ("@ABCDEFG" + ("HIJKLMNO" + ("PQRSTUVW" + ("XYZ[\\]^_" + ("`abcdefg" + ("hijklmno" + ("pqrstuvw" + "xyz{|}~"))))))))))))));

	private static final java.lang.String[] CHAR_STRING_ARRAY = new java.lang.String[128];

	private static final java.lang.Character[] CHAR_ARRAY = new java.lang.Character[128];

	public static final char LF = '\n';

	public static final char CR = '\r';

	static {
		for (int i = 127 ; i >= 0 ; i--) {
			org.apache.commons.lang.CharUtils.CHAR_STRING_ARRAY[i] = org.apache.commons.lang.CharUtils.CHAR_STRING.substring(i, (i + 1));
			org.apache.commons.lang.CharUtils.CHAR_ARRAY[i] = new java.lang.Character(((char)(i)));
		}
	}

	public CharUtils() {
		super();
	}

	public static java.lang.Character toCharacterObject(char ch) {
		if (ch < (org.apache.commons.lang.CharUtils.CHAR_ARRAY.length)) {
			return org.apache.commons.lang.CharUtils.CHAR_ARRAY[ch];
		} 
		return new java.lang.Character(ch);
	}

	public static java.lang.Character toCharacterObject(java.lang.String str) {
		if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
			return null;
		} 
		return org.apache.commons.lang.CharUtils.toCharacterObject(str.charAt(0));
	}

	public static char toChar(java.lang.Character ch) {
		if (ch == null) {
			throw new java.lang.IllegalArgumentException("The Character must not be null");
		} 
		return ch.charValue();
	}

	public static char toChar(java.lang.Character ch, char defaultValue) {
		if (ch == null) {
			return defaultValue;
		} 
		return ch.charValue();
	}

	public static char toChar(java.lang.String str) {
		if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
			throw new java.lang.IllegalArgumentException("The String must not be empty");
		} 
		return str.charAt(0);
	}

	public static char toChar(java.lang.String str, char defaultValue) {
		if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
			return defaultValue;
		} 
		return str.charAt(0);
	}

	public static int toIntValue(char ch) {
		if ((org.apache.commons.lang.CharUtils.isAsciiNumeric(ch)) == false) {
			throw new java.lang.IllegalArgumentException((("The character " + ch) + " is not in the range '0' - '9'"));
		} 
		return ch - 48;
	}

	public static int toIntValue(char ch, int defaultValue) {
		if ((org.apache.commons.lang.CharUtils.isAsciiNumeric(ch)) == false) {
			return defaultValue;
		} 
		return ch - 48;
	}

	public static int toIntValue(java.lang.Character ch) {
		if (ch == null) {
			throw new java.lang.IllegalArgumentException("The character must not be null");
		} 
		return org.apache.commons.lang.CharUtils.toIntValue(ch.charValue());
	}

	public static int toIntValue(java.lang.Character ch, int defaultValue) {
		if (ch == null) {
			return defaultValue;
		} 
		return org.apache.commons.lang.CharUtils.toIntValue(ch.charValue(), defaultValue);
	}

	public static java.lang.String toString(char ch) {
		if (ch < 128) {
			return org.apache.commons.lang.CharUtils.CHAR_STRING_ARRAY[ch];
		} 
		return new java.lang.String(new char[]{ ch });
	}

	public static java.lang.String toString(java.lang.Character ch) {
		if (ch == null) {
			return null;
		} 
		return org.apache.commons.lang.CharUtils.toString(ch.charValue());
	}

	public static java.lang.String unicodeEscaped(char ch) {
		if (ch < 16) {
			return "\\u000" + (java.lang.Integer.toHexString(ch));
		} else if (ch < 256) {
			return "\\u00" + (java.lang.Integer.toHexString(ch));
		} else if (ch < 4096) {
			return "\\u0" + (java.lang.Integer.toHexString(ch));
		} 
		return "\\u" + (java.lang.Integer.toHexString(ch));
	}

	public static java.lang.String unicodeEscaped(java.lang.Character ch) {
		if (ch == null) {
			return null;
		} 
		return org.apache.commons.lang.CharUtils.unicodeEscaped(ch.charValue());
	}

	public static boolean isAscii(char ch) {
		return ch < 128;
	}

	public static boolean isAsciiPrintable(char ch) {
		return (ch >= 32) && (ch < 127);
	}

	public static boolean isAsciiControl(char ch) {
		return (ch < 32) || (ch == 127);
	}

	public static boolean isAsciiAlpha(char ch) {
		return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'));
	}

	public static boolean isAsciiAlphaUpper(char ch) {
		return (ch >= 'A') && (ch <= 'Z');
	}

	public static boolean isAsciiAlphaLower(char ch) {
		return (ch >= 'a') && (ch <= 'z');
	}

	public static boolean isAsciiNumeric(char ch) {
		return (ch >= '0') && (ch <= '9');
	}

	public static boolean isAsciiAlphanumeric(char ch) {
		return (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))) || ((ch >= '0') && (ch <= '9'));
	}
}

