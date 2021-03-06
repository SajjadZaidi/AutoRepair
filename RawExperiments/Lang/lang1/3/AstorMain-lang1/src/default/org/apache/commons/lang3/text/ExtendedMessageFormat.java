package org.apache.commons.lang3.text;


public class ExtendedMessageFormat extends java.text.MessageFormat {
	private static final long serialVersionUID = -2362048321261811743L;

	private static final int HASH_SEED = 31;

	private static final java.lang.String DUMMY_PATTERN = "";

	private static final java.lang.String ESCAPED_QUOTE = "''";

	private static final char START_FMT = ',';

	private static final char END_FE = '}';

	private static final char START_FE = '{';

	private static final char QUOTE = '\'';

	private java.lang.String toPattern;

	private final java.util.Map<java.lang.String, ? extends org.apache.commons.lang3.text.FormatFactory> registry;

	public ExtendedMessageFormat(final java.lang.String pattern) {
		this(pattern, java.util.Locale.getDefault());
	}

	public ExtendedMessageFormat(final java.lang.String pattern ,final java.util.Locale locale) {
		this(pattern, locale, null);
	}

	public ExtendedMessageFormat(final java.lang.String pattern ,final java.util.Map<java.lang.String, ? extends org.apache.commons.lang3.text.FormatFactory> registry) {
		this(pattern, java.util.Locale.getDefault(), registry);
	}

	public ExtendedMessageFormat(final java.lang.String pattern ,final java.util.Locale locale ,final java.util.Map<java.lang.String, ? extends org.apache.commons.lang3.text.FormatFactory> registry) {
		super(org.apache.commons.lang3.text.ExtendedMessageFormat.DUMMY_PATTERN);
		setLocale(locale);
		this.registry = registry;
		applyPattern(pattern);
	}

	@java.lang.Override
	public java.lang.String toPattern() {
		return toPattern;
	}

	@java.lang.Override
	public final void applyPattern(final java.lang.String pattern) {
		if ((registry) == null) {
			super.applyPattern(pattern);
			toPattern = super.toPattern();
			return ;
		} 
		final java.util.ArrayList<java.text.Format> foundFormats = new java.util.ArrayList<java.text.Format>();
		final java.util.ArrayList<java.lang.String> foundDescriptions = new java.util.ArrayList<java.lang.String>();
		final java.lang.StringBuilder stripCustom = new java.lang.StringBuilder(pattern.length());
		final java.text.ParsePosition pos = new java.text.ParsePosition(0);
		final char[] c = pattern.toCharArray();
		int fmtCount = 0;
		while ((pos.getIndex()) < (pattern.length())) {
			switch (c[pos.getIndex()]) {
				case org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE :
					appendQuotedString(pattern, pos, stripCustom, true);
					break;
				case org.apache.commons.lang3.text.ExtendedMessageFormat.START_FE :
					fmtCount++;
					seekNonWs(pattern, pos);
					final int start = pos.getIndex();
					final int index = readArgumentIndex(pattern, next(pos));
					stripCustom.append(org.apache.commons.lang3.text.ExtendedMessageFormat.START_FE).append(index);
					seekNonWs(pattern, pos);
					java.text.Format format = null;
					java.lang.String formatDescription = null;
					if ((c[pos.getIndex()]) == (org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT)) {
						formatDescription = parseFormatDescription(pattern, next(pos));
						format = getFormat(formatDescription);
						if (format == null) {
							stripCustom.append(org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT).append(formatDescription);
						} 
					} 
					foundFormats.add(format);
					foundDescriptions.add((format == null ? null : formatDescription));
					org.apache.commons.lang3.Validate.isTrue(((foundFormats.size()) == fmtCount));
					org.apache.commons.lang3.Validate.isTrue(((foundDescriptions.size()) == fmtCount));
					if ((c[pos.getIndex()]) != (org.apache.commons.lang3.text.ExtendedMessageFormat.END_FE)) {
						throw new java.lang.IllegalArgumentException(("Unreadable format element at position " + start));
					} 
				default :
					stripCustom.append(c[pos.getIndex()]);
					next(pos);
			}
		}
		super.applyPattern(stripCustom.toString());
		toPattern = insertFormats(super.toPattern(), foundDescriptions);
		if (containsElements(foundFormats)) {
			final java.text.Format[] origFormats = getFormats();
			int i = 0;
			for (final java.util.Iterator<java.text.Format> it = foundFormats.iterator() ; it.hasNext() ; i++) {
				final java.text.Format f = it.next();
				if (f != null) {
					origFormats[i] = f;
				} 
			}
			super.setFormats(origFormats);
		} 
	}

	@java.lang.Override
	public void setFormat(final int formatElementIndex, final java.text.Format newFormat) {
		throw new java.lang.UnsupportedOperationException();
	}

	@java.lang.Override
	public void setFormatByArgumentIndex(final int argumentIndex, final java.text.Format newFormat) {
		throw new java.lang.UnsupportedOperationException();
	}

	@java.lang.Override
	public void setFormats(final java.text.Format[] newFormats) {
		throw new java.lang.UnsupportedOperationException();
	}

	@java.lang.Override
	public void setFormatsByArgumentIndex(final java.text.Format[] newFormats) {
		throw new java.lang.UnsupportedOperationException();
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj == (org.apache.commons.lang3.text.ExtendedMessageFormat.this)) {
			return true;
		} 
		if (obj == null) {
			return false;
		} 
		if (!(super.equals(obj))) {
			return false;
		} 
		if (org.apache.commons.lang3.ObjectUtils.notEqual(getClass(), obj.getClass())) {
			return false;
		} 
		final org.apache.commons.lang3.text.ExtendedMessageFormat rhs = ((org.apache.commons.lang3.text.ExtendedMessageFormat)(obj));
		if (org.apache.commons.lang3.ObjectUtils.notEqual(toPattern, rhs.toPattern)) {
			return false;
		} 
		if (org.apache.commons.lang3.ObjectUtils.notEqual(registry, rhs.registry)) {
			return false;
		} 
		return true;
	}

	@java.lang.Override
	public int hashCode() {
		int result = super.hashCode();
		result = ((org.apache.commons.lang3.text.ExtendedMessageFormat.HASH_SEED) * result) + (org.apache.commons.lang3.ObjectUtils.hashCode(registry));
		result = ((org.apache.commons.lang3.text.ExtendedMessageFormat.HASH_SEED) * result) + (org.apache.commons.lang3.ObjectUtils.hashCode(toPattern));
		return result;
	}

	private java.text.Format getFormat(final java.lang.String desc) {
		if ((registry) != null) {
			java.lang.String name = desc;
			java.lang.String args = null;
			final int i = desc.indexOf(org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT);
			if (i > 0) {
				name = desc.substring(0, i).trim();
				args = desc.substring((i + 1)).trim();
			} 
			final org.apache.commons.lang3.text.FormatFactory factory = registry.get(name);
			if (factory != null) {
				return factory.getFormat(name, args, getLocale());
			} 
		} 
		return null;
	}

	private int readArgumentIndex(final java.lang.String pattern, final java.text.ParsePosition pos) {
		final int start = pos.getIndex();
		seekNonWs(pattern, pos);
		final java.lang.StringBuilder result = new java.lang.StringBuilder();
		boolean error = false;
		for ( ; (!error) && ((pos.getIndex()) < (pattern.length())) ; next(pos)) {
			char c = pattern.charAt(pos.getIndex());
			if (java.lang.Character.isWhitespace(c)) {
				seekNonWs(pattern, pos);
				c = pattern.charAt(pos.getIndex());
				if ((c != (org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT)) && (c != (org.apache.commons.lang3.text.ExtendedMessageFormat.END_FE))) {
					error = true;
					continue;
				} 
			} 
			if (((c == (org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT)) || (c == (org.apache.commons.lang3.text.ExtendedMessageFormat.END_FE))) && ((result.length()) > 0)) {
				try {
					return java.lang.Integer.parseInt(result.toString());
				} catch (final java.lang.NumberFormatException e) {
				}
			} 
			error = !(java.lang.Character.isDigit(c));
			result.append(c);
		}
		if (error) {
			throw new java.lang.IllegalArgumentException(((("Invalid format argument index at position " + start) + ": ") + (pattern.substring(start, pos.getIndex()))));
		} 
		throw new java.lang.IllegalArgumentException(("Unterminated format element at position " + start));
	}

	private java.lang.String parseFormatDescription(final java.lang.String pattern, final java.text.ParsePosition pos) {
		final int start = pos.getIndex();
		seekNonWs(pattern, pos);
		final int text = pos.getIndex();
		int depth = 1;
		for ( ; (pos.getIndex()) < (pattern.length()) ; next(pos)) {
			switch (pattern.charAt(pos.getIndex())) {
				case org.apache.commons.lang3.text.ExtendedMessageFormat.START_FE :
					depth++;
					break;
				case org.apache.commons.lang3.text.ExtendedMessageFormat.END_FE :
					depth--;
					if (depth == 0) {
						return pattern.substring(text, pos.getIndex());
					} 
					break;
				case org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE :
					getQuotedString(pattern, pos, false);
					break;
			}
		}
		throw new java.lang.IllegalArgumentException(("Unterminated format element at position " + start));
	}

	private java.lang.String insertFormats(final java.lang.String pattern, final java.util.ArrayList<java.lang.String> customPatterns) {
		if (!(containsElements(customPatterns))) {
			return pattern;
		} 
		final java.lang.StringBuilder sb = new java.lang.StringBuilder(((pattern.length()) * 2));
		final java.text.ParsePosition pos = new java.text.ParsePosition(0);
		int fe = -1;
		int depth = 0;
		while ((pos.getIndex()) < (pattern.length())) {
			final char c = pattern.charAt(pos.getIndex());
			switch (c) {
				case org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE :
					appendQuotedString(pattern, pos, sb, false);
					break;
				case org.apache.commons.lang3.text.ExtendedMessageFormat.START_FE :
					depth++;
					if (depth == 1) {
						fe++;
						sb.append(org.apache.commons.lang3.text.ExtendedMessageFormat.START_FE).append(readArgumentIndex(pattern, next(pos)));
						final java.lang.String customPattern = customPatterns.get(fe);
						if (customPattern != null) {
							sb.append(org.apache.commons.lang3.text.ExtendedMessageFormat.START_FMT).append(customPattern);
						} 
					} 
					break;
				case org.apache.commons.lang3.text.ExtendedMessageFormat.END_FE :
					depth--;
				default :
					sb.append(c);
					next(pos);
			}
		}
		return sb.toString();
	}

	private void seekNonWs(final java.lang.String pattern, final java.text.ParsePosition pos) {
		int len = 0;
		final char[] buffer = pattern.toCharArray();
		do {
			len = org.apache.commons.lang3.text.StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
			pos.setIndex(((pos.getIndex()) + len));
		} while ((len > 0) && ((pos.getIndex()) < (pattern.length())) );
	}

	private java.text.ParsePosition next(final java.text.ParsePosition pos) {
		pos.setIndex(((pos.getIndex()) + 1));
		return pos;
	}

	private java.lang.StringBuilder appendQuotedString(final java.lang.String pattern, final java.text.ParsePosition pos, final java.lang.StringBuilder appendTo, final boolean escapingOn) {
		final int start = pos.getIndex();
		final char[] c = pattern.toCharArray();
		if (escapingOn && ((c[start]) == (org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE))) {
			next(pos);
			return appendTo == null ? null : appendTo.append(org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE);
		} 
		int lastHold = start;
		for (int i = pos.getIndex() ; i < (pattern.length()) ; i++) {
			if (escapingOn && (pattern.substring(i).startsWith(org.apache.commons.lang3.text.ExtendedMessageFormat.ESCAPED_QUOTE))) {
				appendTo.append(c, lastHold, ((pos.getIndex()) - lastHold)).append(org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE);
				pos.setIndex((i + (org.apache.commons.lang3.text.ExtendedMessageFormat.ESCAPED_QUOTE.length())));
				lastHold = pos.getIndex();
				continue;
			} 
			switch (c[pos.getIndex()]) {
				case org.apache.commons.lang3.text.ExtendedMessageFormat.QUOTE :
					next(pos);
					return appendTo == null ? null : appendTo.append(c, lastHold, ((pos.getIndex()) - lastHold));
				default :
					next(pos);
			}
		}
		throw new java.lang.IllegalArgumentException(("Unterminated quoted string at position " + start));
	}

	private void getQuotedString(final java.lang.String pattern, final java.text.ParsePosition pos, final boolean escapingOn) {
		appendQuotedString(pattern, pos, null, escapingOn);
	}

	private boolean containsElements(final java.util.Collection<?> coll) {
		if ((coll == null) || (coll.isEmpty())) {
			return false;
		} 
		for (final java.lang.Object name : coll) {
			if (name != null) {
				return true;
			} 
		}
		return false;
	}
}

