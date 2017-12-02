package org.apache.commons.lang3.text.translate;


public class UnicodeUnescaper extends org.apache.commons.lang3.text.translate.CharSequenceTranslator {
	@java.lang.Override
	public int translate(final java.lang.CharSequence input, final int index, final java.io.Writer out) throws java.io.IOException {
		if ((((input.charAt(index)) == '\\') && ((index + 1) < (input.length()))) && ((input.charAt((index + 1))) == 'u')) {
			int i = 2;
			while (((index + i) < (input.length())) && ((input.charAt((index + i))) == 'u')) {
				i++;
			}
			if (((index + i) < (input.length())) && ((input.charAt((index + i))) == '+')) {
				i++;
			} 
			if (((index + i) + 4) <= (input.length())) {
				final java.lang.CharSequence unicode = input.subSequence((index + i), ((index + i) + 4));
				try {
					final int value = java.lang.Integer.parseInt(unicode.toString(), 16);
					out.write(((char)(value)));
				} catch (final java.lang.NumberFormatException nfe) {
					throw new java.lang.IllegalArgumentException(("Unable to parse unicode value: " + unicode) , nfe);
				}
				return i + 4;
			} else {
				throw new java.lang.IllegalArgumentException((("Less than 4 hex digits in unicode value: '" + (input.subSequence(index, input.length()))) + "' due to end of CharSequence"));
			}
		} 
		return 0;
	}
}

