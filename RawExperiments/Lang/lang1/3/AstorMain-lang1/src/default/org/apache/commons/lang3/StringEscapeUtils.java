package org.apache.commons.lang3;


public class StringEscapeUtils {
	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_JAVA = new org.apache.commons.lang3.text.translate.LookupTranslator(new java.lang.String[][]{ new java.lang.String[]{ "\"" , "\\\"" } , new java.lang.String[]{ "\\" , "\\\\" } }).with(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.JAVA_CTRL_CHARS_ESCAPE())).with(org.apache.commons.lang3.text.translate.JavaUnicodeEscaper.outsideOf(32, 127));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_ECMASCRIPT = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(new java.lang.String[][]{ new java.lang.String[]{ "'" , "\\\'" } , new java.lang.String[]{ "\"" , "\\\"" } , new java.lang.String[]{ "\\" , "\\\\" } , new java.lang.String[]{ "/" , "\\/" } }) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.JAVA_CTRL_CHARS_ESCAPE()) , org.apache.commons.lang3.text.translate.JavaUnicodeEscaper.outsideOf(32, 127));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_JSON = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(new java.lang.String[][]{ new java.lang.String[]{ "\"" , "\\\"" } , new java.lang.String[]{ "\\" , "\\\\" } , new java.lang.String[]{ "/" , "\\/" } }) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.JAVA_CTRL_CHARS_ESCAPE()) , org.apache.commons.lang3.text.translate.JavaUnicodeEscaper.outsideOf(32, 127));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_XML = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_ESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.APOS_ESCAPE()));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_HTML3 = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_ESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_HTML4 = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_ESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.HTML40_EXTENDED_ESCAPE()));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator ESCAPE_CSV = new org.apache.commons.lang3.StringEscapeUtils.CsvEscaper();

	static class CsvEscaper extends org.apache.commons.lang3.text.translate.CharSequenceTranslator {
		private static final char CSV_DELIMITER = ',';

		private static final char CSV_QUOTE = '"';

		private static final java.lang.String CSV_QUOTE_STR = java.lang.String.valueOf(org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE);

		private static final char[] CSV_SEARCH_CHARS = new char[]{ org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_DELIMITER , org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE , org.apache.commons.lang3.CharUtils.CR , org.apache.commons.lang3.CharUtils.LF };

		@java.lang.Override
		public int translate(final java.lang.CharSequence input, final int index, final java.io.Writer out) throws java.io.IOException {
			if (index != 0) {
				throw new java.lang.IllegalStateException("CsvEscaper should never reach the [1] index");
			} 
			if (org.apache.commons.lang3.StringUtils.containsNone(input.toString(), org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_SEARCH_CHARS)) {
				out.write(input.toString());
			} else {
				out.write(org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE);
				out.write(org.apache.commons.lang3.StringUtils.replace(input.toString(), org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE_STR, ((org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE_STR) + (org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE_STR))));
				out.write(org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.CSV_QUOTE);
			}
			return input.length();
		}
	}

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_JAVA = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.OctalUnescaper() , new org.apache.commons.lang3.text.translate.UnicodeUnescaper() , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(new java.lang.String[][]{ new java.lang.String[]{ "\\\\" , "\\" } , new java.lang.String[]{ "\\\"" , "\"" } , new java.lang.String[]{ "\\\'" , "'" } , new java.lang.String[]{ "\\" , "" } }));

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_ECMASCRIPT = org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_JAVA;

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_JSON = org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_JAVA;

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_HTML3 = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_UNESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_UNESCAPE()) , new org.apache.commons.lang3.text.translate.NumericEntityUnescaper());

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_HTML4 = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_UNESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_UNESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.HTML40_EXTENDED_UNESCAPE()) , new org.apache.commons.lang3.text.translate.NumericEntityUnescaper());

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_XML = new org.apache.commons.lang3.text.translate.AggregateTranslator(new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.BASIC_UNESCAPE()) , new org.apache.commons.lang3.text.translate.LookupTranslator(org.apache.commons.lang3.text.translate.EntityArrays.APOS_UNESCAPE()) , new org.apache.commons.lang3.text.translate.NumericEntityUnescaper());

	public static final org.apache.commons.lang3.text.translate.CharSequenceTranslator UNESCAPE_CSV = new org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper();

	static class CsvUnescaper extends org.apache.commons.lang3.text.translate.CharSequenceTranslator {
		private static final char CSV_DELIMITER = ',';

		private static final char CSV_QUOTE = '"';

		private static final java.lang.String CSV_QUOTE_STR = java.lang.String.valueOf(org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE);

		private static final char[] CSV_SEARCH_CHARS = new char[]{ org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_DELIMITER , org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE , org.apache.commons.lang3.CharUtils.CR , org.apache.commons.lang3.CharUtils.LF };

		@java.lang.Override
		public int translate(final java.lang.CharSequence input, final int index, final java.io.Writer out) throws java.io.IOException {
			if (index != 0) {
				throw new java.lang.IllegalStateException("CsvUnescaper should never reach the [1] index");
			} 
			if (((input.charAt(0)) != (org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE)) || ((input.charAt(((input.length()) - 1))) != (org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE))) {
				out.write(input.toString());
				return input.length();
			} 
			final java.lang.String quoteless = input.subSequence(1, ((input.length()) - 1)).toString();
			if (org.apache.commons.lang3.StringUtils.containsAny(quoteless, org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_SEARCH_CHARS)) {
				out.write(org.apache.commons.lang3.StringUtils.replace(quoteless, ((org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE_STR) + (org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE_STR)), org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.CSV_QUOTE_STR));
			} else {
				out.write(input.toString());
			}
			return input.length();
		}
	}

	public StringEscapeUtils() {
		super();
	}

	public static final java.lang.String escapeJava(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_JAVA.translate(input);
	}

	public static final java.lang.String escapeEcmaScript(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_ECMASCRIPT.translate(input);
	}

	public static final java.lang.String escapeJson(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_JSON.translate(input);
	}

	public static final java.lang.String unescapeJava(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_JAVA.translate(input);
	}

	public static final java.lang.String unescapeEcmaScript(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_ECMASCRIPT.translate(input);
	}

	public static final java.lang.String unescapeJson(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_JSON.translate(input);
	}

	public static final java.lang.String escapeHtml4(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_HTML4.translate(input);
	}

	public static final java.lang.String escapeHtml3(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_HTML3.translate(input);
	}

	public static final java.lang.String unescapeHtml4(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_HTML4.translate(input);
	}

	public static final java.lang.String unescapeHtml3(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_HTML3.translate(input);
	}

	public static final java.lang.String escapeXml(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_XML.translate(input);
	}

	public static final java.lang.String unescapeXml(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_XML.translate(input);
	}

	public static final java.lang.String escapeCsv(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.ESCAPE_CSV.translate(input);
	}

	public static final java.lang.String unescapeCsv(final java.lang.String input) {
		return org.apache.commons.lang3.StringEscapeUtils.UNESCAPE_CSV.translate(input);
	}
}

