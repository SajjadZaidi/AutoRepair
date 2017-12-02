package org.apache.commons.lang3.text;


public class StrTokenizer implements java.lang.Cloneable , java.util.ListIterator<java.lang.String> {
	private static final org.apache.commons.lang3.text.StrTokenizer CSV_TOKENIZER_PROTOTYPE;

	private static final org.apache.commons.lang3.text.StrTokenizer TSV_TOKENIZER_PROTOTYPE;

	static {
		CSV_TOKENIZER_PROTOTYPE = new org.apache.commons.lang3.text.StrTokenizer();
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(org.apache.commons.lang3.text.StrMatcher.commaMatcher());
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(org.apache.commons.lang3.text.StrMatcher.doubleQuoteMatcher());
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(org.apache.commons.lang3.text.StrMatcher.noneMatcher());
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(org.apache.commons.lang3.text.StrMatcher.trimMatcher());
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
		org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
		TSV_TOKENIZER_PROTOTYPE = new org.apache.commons.lang3.text.StrTokenizer();
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(org.apache.commons.lang3.text.StrMatcher.tabMatcher());
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(org.apache.commons.lang3.text.StrMatcher.doubleQuoteMatcher());
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(org.apache.commons.lang3.text.StrMatcher.noneMatcher());
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(org.apache.commons.lang3.text.StrMatcher.trimMatcher());
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
		org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
	}

	private char[] chars;

	private java.lang.String[] tokens;

	private int tokenPos;

	private org.apache.commons.lang3.text.StrMatcher delimMatcher = org.apache.commons.lang3.text.StrMatcher.splitMatcher();

	private org.apache.commons.lang3.text.StrMatcher quoteMatcher = org.apache.commons.lang3.text.StrMatcher.noneMatcher();

	private org.apache.commons.lang3.text.StrMatcher ignoredMatcher = org.apache.commons.lang3.text.StrMatcher.noneMatcher();

	private org.apache.commons.lang3.text.StrMatcher trimmerMatcher = org.apache.commons.lang3.text.StrMatcher.noneMatcher();

	private boolean emptyAsNull = false;

	private boolean ignoreEmptyTokens = true;

	private static org.apache.commons.lang3.text.StrTokenizer getCSVClone() {
		return ((org.apache.commons.lang3.text.StrTokenizer)(org.apache.commons.lang3.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.clone()));
	}

	public static org.apache.commons.lang3.text.StrTokenizer getCSVInstance() {
		return org.apache.commons.lang3.text.StrTokenizer.getCSVClone();
	}

	public static org.apache.commons.lang3.text.StrTokenizer getCSVInstance(final java.lang.String input) {
		final org.apache.commons.lang3.text.StrTokenizer tok = org.apache.commons.lang3.text.StrTokenizer.getCSVClone();
		tok.reset(input);
		return tok;
	}

	public static org.apache.commons.lang3.text.StrTokenizer getCSVInstance(final char[] input) {
		final org.apache.commons.lang3.text.StrTokenizer tok = org.apache.commons.lang3.text.StrTokenizer.getCSVClone();
		tok.reset(input);
		return tok;
	}

	private static org.apache.commons.lang3.text.StrTokenizer getTSVClone() {
		return ((org.apache.commons.lang3.text.StrTokenizer)(org.apache.commons.lang3.text.StrTokenizer.TSV_TOKENIZER_PROTOTYPE.clone()));
	}

	public static org.apache.commons.lang3.text.StrTokenizer getTSVInstance() {
		return org.apache.commons.lang3.text.StrTokenizer.getTSVClone();
	}

	public static org.apache.commons.lang3.text.StrTokenizer getTSVInstance(final java.lang.String input) {
		final org.apache.commons.lang3.text.StrTokenizer tok = org.apache.commons.lang3.text.StrTokenizer.getTSVClone();
		tok.reset(input);
		return tok;
	}

	public static org.apache.commons.lang3.text.StrTokenizer getTSVInstance(final char[] input) {
		final org.apache.commons.lang3.text.StrTokenizer tok = org.apache.commons.lang3.text.StrTokenizer.getTSVClone();
		tok.reset(input);
		return tok;
	}

	public StrTokenizer() {
		super();
		org.apache.commons.lang3.text.StrTokenizer.this.chars = null;
	}

	public StrTokenizer(final java.lang.String input) {
		super();
		if (input != null) {
			chars = input.toCharArray();
		} else {
			chars = null;
		}
	}

	public StrTokenizer(final java.lang.String input ,final char delim) {
		this(input);
		setDelimiterChar(delim);
	}

	public StrTokenizer(final java.lang.String input ,final java.lang.String delim) {
		this(input);
		setDelimiterString(delim);
	}

	public StrTokenizer(final java.lang.String input ,final org.apache.commons.lang3.text.StrMatcher delim) {
		this(input);
		setDelimiterMatcher(delim);
	}

	public StrTokenizer(final java.lang.String input ,final char delim ,final char quote) {
		this(input, delim);
		setQuoteChar(quote);
	}

	public StrTokenizer(final java.lang.String input ,final org.apache.commons.lang3.text.StrMatcher delim ,final org.apache.commons.lang3.text.StrMatcher quote) {
		this(input, delim);
		setQuoteMatcher(quote);
	}

	public StrTokenizer(final char[] input) {
		super();
		org.apache.commons.lang3.text.StrTokenizer.this.chars = org.apache.commons.lang3.ArrayUtils.clone(input);
	}

	public StrTokenizer(final char[] input ,final char delim) {
		this(input);
		setDelimiterChar(delim);
	}

	public StrTokenizer(final char[] input ,final java.lang.String delim) {
		this(input);
		setDelimiterString(delim);
	}

	public StrTokenizer(final char[] input ,final org.apache.commons.lang3.text.StrMatcher delim) {
		this(input);
		setDelimiterMatcher(delim);
	}

	public StrTokenizer(final char[] input ,final char delim ,final char quote) {
		this(input, delim);
		setQuoteChar(quote);
	}

	public StrTokenizer(final char[] input ,final org.apache.commons.lang3.text.StrMatcher delim ,final org.apache.commons.lang3.text.StrMatcher quote) {
		this(input, delim);
		setQuoteMatcher(quote);
	}

	public int size() {
		checkTokenized();
		return tokens.length;
	}

	public java.lang.String nextToken() {
		if (hasNext()) {
			return tokens[((tokenPos)++)];
		} 
		return null;
	}

	public java.lang.String previousToken() {
		if (hasPrevious()) {
			return tokens[(--(tokenPos))];
		} 
		return null;
	}

	public java.lang.String[] getTokenArray() {
		checkTokenized();
		return tokens.clone();
	}

	public java.util.List<java.lang.String> getTokenList() {
		checkTokenized();
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>(tokens.length);
		for (final java.lang.String element : tokens) {
			list.add(element);
		}
		return list;
	}

	public org.apache.commons.lang3.text.StrTokenizer reset() {
		tokenPos = 0;
		tokens = null;
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public org.apache.commons.lang3.text.StrTokenizer reset(final java.lang.String input) {
		reset();
		if (input != null) {
			org.apache.commons.lang3.text.StrTokenizer.this.chars = input.toCharArray();
		} else {
			org.apache.commons.lang3.text.StrTokenizer.this.chars = null;
		}
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public org.apache.commons.lang3.text.StrTokenizer reset(final char[] input) {
		reset();
		org.apache.commons.lang3.text.StrTokenizer.this.chars = org.apache.commons.lang3.ArrayUtils.clone(input);
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	@java.lang.Override
	public boolean hasNext() {
		checkTokenized();
		return (tokenPos) < (tokens.length);
	}

	@java.lang.Override
	public java.lang.String next() {
		if (hasNext()) {
			return tokens[((tokenPos)++)];
		} 
		throw new java.util.NoSuchElementException();
	}

	@java.lang.Override
	public int nextIndex() {
		return tokenPos;
	}

	@java.lang.Override
	public boolean hasPrevious() {
		checkTokenized();
		return (tokenPos) > 0;
	}

	@java.lang.Override
	public java.lang.String previous() {
		if (hasPrevious()) {
			return tokens[(--(tokenPos))];
		} 
		throw new java.util.NoSuchElementException();
	}

	@java.lang.Override
	public int previousIndex() {
		return (tokenPos) - 1;
	}

	@java.lang.Override
	public void remove() {
		throw new java.lang.UnsupportedOperationException("remove() is unsupported");
	}

	@java.lang.Override
	public void set(final java.lang.String obj) {
		throw new java.lang.UnsupportedOperationException("set() is unsupported");
	}

	@java.lang.Override
	public void add(final java.lang.String obj) {
		throw new java.lang.UnsupportedOperationException("add() is unsupported");
	}

	private void checkTokenized() {
		if ((tokens) == null) {
			if ((chars) == null) {
				final java.util.List<java.lang.String> split = tokenize(null, 0, 0);
				tokens = split.toArray(new java.lang.String[split.size()]);
			} else {
				final java.util.List<java.lang.String> split = tokenize(chars, 0, chars.length);
				tokens = split.toArray(new java.lang.String[split.size()]);
			}
		} 
	}

	protected java.util.List<java.lang.String> tokenize(final char[] chars, final int offset, final int count) {
		if ((chars == null) || (count == 0)) {
			return java.util.Collections.emptyList();
		} 
		final org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder();
		final java.util.List<java.lang.String> tokens = new java.util.ArrayList<java.lang.String>();
		int pos = offset;
		while ((pos >= 0) && (pos < count)) {
			pos = readNextToken(chars, pos, count, buf, tokens);
			if (pos >= count) {
				addToken(tokens, "");
			} 
		}
		return tokens;
	}

	private void addToken(final java.util.List<java.lang.String> list, java.lang.String tok) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(tok)) {
			if (isIgnoreEmptyTokens()) {
				return ;
			} 
			if (isEmptyTokenAsNull()) {
				tok = null;
			} 
		} 
		list.add(tok);
	}

	private int readNextToken(final char[] chars, int start, final int len, final org.apache.commons.lang3.text.StrBuilder workArea, final java.util.List<java.lang.String> tokens) {
		while (start < len) {
			final int removeLen = java.lang.Math.max(getIgnoredMatcher().isMatch(chars, start, start, len), getTrimmerMatcher().isMatch(chars, start, start, len));
			if (((removeLen == 0) || ((getDelimiterMatcher().isMatch(chars, start, start, len)) > 0)) || ((getQuoteMatcher().isMatch(chars, start, start, len)) > 0)) {
				break;
			} 
			start += removeLen;
		}
		if (start >= len) {
			addToken(tokens, "");
			return -1;
		} 
		final int delimLen = getDelimiterMatcher().isMatch(chars, start, start, len);
		if (delimLen > 0) {
			addToken(tokens, "");
			return start + delimLen;
		} 
		final int quoteLen = getQuoteMatcher().isMatch(chars, start, start, len);
		if (quoteLen > 0) {
			return readWithQuotes(chars, (start + quoteLen), len, workArea, tokens, start, quoteLen);
		} 
		return readWithQuotes(chars, start, len, workArea, tokens, 0, 0);
	}

	private int readWithQuotes(final char[] chars, final int start, final int len, final org.apache.commons.lang3.text.StrBuilder workArea, final java.util.List<java.lang.String> tokens, final int quoteStart, final int quoteLen) {
		workArea.clear();
		int pos = start;
		boolean quoting = quoteLen > 0;
		int trimStart = 0;
		while (pos < len) {
			if (quoting) {
				if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
					if (isQuote(chars, (pos + quoteLen), len, quoteStart, quoteLen)) {
						workArea.append(chars, pos, quoteLen);
						pos += quoteLen * 2;
						trimStart = workArea.size();
						continue;
					} 
					quoting = false;
					pos += quoteLen;
					continue;
				} 
				workArea.append(chars[(pos++)]);
				trimStart = workArea.size();
			} else {
				final int delimLen = getDelimiterMatcher().isMatch(chars, pos, start, len);
				if (delimLen > 0) {
					addToken(tokens, workArea.substring(0, trimStart));
					return pos + delimLen;
				} 
				if ((quoteLen > 0) && (isQuote(chars, pos, len, quoteStart, quoteLen))) {
					quoting = true;
					pos += quoteLen;
					continue;
				} 
				final int ignoredLen = getIgnoredMatcher().isMatch(chars, pos, start, len);
				if (ignoredLen > 0) {
					pos += ignoredLen;
					continue;
				} 
				final int trimmedLen = getTrimmerMatcher().isMatch(chars, pos, start, len);
				if (trimmedLen > 0) {
					workArea.append(chars, pos, trimmedLen);
					pos += trimmedLen;
					continue;
				} 
				workArea.append(chars[(pos++)]);
				trimStart = workArea.size();
			}
		}
		addToken(tokens, workArea.substring(0, trimStart));
		return -1;
	}

	private boolean isQuote(final char[] chars, final int pos, final int len, final int quoteStart, final int quoteLen) {
		for (int i = 0 ; i < quoteLen ; i++) {
			if (((pos + i) >= len) || ((chars[(pos + i)]) != (chars[(quoteStart + i)]))) {
				return false;
			} 
		}
		return true;
	}

	public org.apache.commons.lang3.text.StrMatcher getDelimiterMatcher() {
		return org.apache.commons.lang3.text.StrTokenizer.this.delimMatcher;
	}

	public org.apache.commons.lang3.text.StrTokenizer setDelimiterMatcher(final org.apache.commons.lang3.text.StrMatcher delim) {
		if (delim == null) {
			org.apache.commons.lang3.text.StrTokenizer.this.delimMatcher = org.apache.commons.lang3.text.StrMatcher.noneMatcher();
		} else {
			org.apache.commons.lang3.text.StrTokenizer.this.delimMatcher = delim;
		}
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public org.apache.commons.lang3.text.StrTokenizer setDelimiterChar(final char delim) {
		return setDelimiterMatcher(org.apache.commons.lang3.text.StrMatcher.charMatcher(delim));
	}

	public org.apache.commons.lang3.text.StrTokenizer setDelimiterString(final java.lang.String delim) {
		return setDelimiterMatcher(org.apache.commons.lang3.text.StrMatcher.stringMatcher(delim));
	}

	public org.apache.commons.lang3.text.StrMatcher getQuoteMatcher() {
		return quoteMatcher;
	}

	public org.apache.commons.lang3.text.StrTokenizer setQuoteMatcher(final org.apache.commons.lang3.text.StrMatcher quote) {
		if (quote != null) {
			org.apache.commons.lang3.text.StrTokenizer.this.quoteMatcher = quote;
		} 
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public org.apache.commons.lang3.text.StrTokenizer setQuoteChar(final char quote) {
		return setQuoteMatcher(org.apache.commons.lang3.text.StrMatcher.charMatcher(quote));
	}

	public org.apache.commons.lang3.text.StrMatcher getIgnoredMatcher() {
		return ignoredMatcher;
	}

	public org.apache.commons.lang3.text.StrTokenizer setIgnoredMatcher(final org.apache.commons.lang3.text.StrMatcher ignored) {
		if (ignored != null) {
			org.apache.commons.lang3.text.StrTokenizer.this.ignoredMatcher = ignored;
		} 
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public org.apache.commons.lang3.text.StrTokenizer setIgnoredChar(final char ignored) {
		return setIgnoredMatcher(org.apache.commons.lang3.text.StrMatcher.charMatcher(ignored));
	}

	public org.apache.commons.lang3.text.StrMatcher getTrimmerMatcher() {
		return trimmerMatcher;
	}

	public org.apache.commons.lang3.text.StrTokenizer setTrimmerMatcher(final org.apache.commons.lang3.text.StrMatcher trimmer) {
		if (trimmer != null) {
			org.apache.commons.lang3.text.StrTokenizer.this.trimmerMatcher = trimmer;
		} 
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public boolean isEmptyTokenAsNull() {
		return org.apache.commons.lang3.text.StrTokenizer.this.emptyAsNull;
	}

	public org.apache.commons.lang3.text.StrTokenizer setEmptyTokenAsNull(final boolean emptyAsNull) {
		org.apache.commons.lang3.text.StrTokenizer.this.emptyAsNull = emptyAsNull;
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public boolean isIgnoreEmptyTokens() {
		return ignoreEmptyTokens;
	}

	public org.apache.commons.lang3.text.StrTokenizer setIgnoreEmptyTokens(final boolean ignoreEmptyTokens) {
		org.apache.commons.lang3.text.StrTokenizer.this.ignoreEmptyTokens = ignoreEmptyTokens;
		return org.apache.commons.lang3.text.StrTokenizer.this;
	}

	public java.lang.String getContent() {
		if ((chars) == null) {
			return null;
		} 
		return new java.lang.String(chars);
	}

	@java.lang.Override
	public java.lang.Object clone() {
		try {
			return cloneReset();
		} catch (final java.lang.CloneNotSupportedException ex) {
			return null;
		}
	}

	java.lang.Object cloneReset() throws java.lang.CloneNotSupportedException {
		final org.apache.commons.lang3.text.StrTokenizer cloned = ((org.apache.commons.lang3.text.StrTokenizer)(super.clone()));
		if ((cloned.chars) != null) {
			cloned.chars = cloned.chars.clone();
		} 
		cloned.reset();
		return cloned;
	}

	@java.lang.Override
	public java.lang.String toString() {
		if ((tokens) == null) {
			return "StrTokenizer[not tokenized yet]";
		} 
		return "StrTokenizer" + (getTokenList());
	}
}

