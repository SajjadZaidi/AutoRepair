package org.apache.commons.lang3;


public class CharEncoding {
	public static final java.lang.String ISO_8859_1 = "ISO-8859-1";

	public static final java.lang.String US_ASCII = "US-ASCII";

	public static final java.lang.String UTF_16 = "UTF-16";

	public static final java.lang.String UTF_16BE = "UTF-16BE";

	public static final java.lang.String UTF_16LE = "UTF-16LE";

	public static final java.lang.String UTF_8 = "UTF-8";

	public static boolean isSupported(final java.lang.String name) {
		if (name == null) {
			return false;
		} 
		try {
			return java.nio.charset.Charset.isSupported(name);
		} catch (final java.nio.charset.IllegalCharsetNameException ex) {
			return false;
		}
	}
}

