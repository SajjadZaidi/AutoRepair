package org.apache.commons.lang3.text.translate;


public class LookupTranslator extends org.apache.commons.lang3.text.translate.CharSequenceTranslator {
	private final java.util.HashMap<java.lang.String, java.lang.CharSequence> lookupMap;

	private final int shortest;

	private final int longest;

	public LookupTranslator(final java.lang.CharSequence[]... lookup) {
		lookupMap = new java.util.HashMap<java.lang.String, java.lang.CharSequence>();
		int _shortest = java.lang.Integer.MAX_VALUE;
		int _longest = 0;
		if (lookup != null) {
			for (final java.lang.CharSequence[] seq : lookup) {
				org.apache.commons.lang3.text.translate.LookupTranslator.this.lookupMap.put(seq[0].toString(), seq[1]);
				final int sz = seq[0].length();
				if (sz < _shortest) {
					_shortest = sz;
				} 
				if (sz > _longest) {
					_longest = sz;
				} 
			}
		} 
		shortest = _shortest;
		longest = _longest;
	}

	@java.lang.Override
	public int translate(final java.lang.CharSequence input, final int index, final java.io.Writer out) throws java.io.IOException {
		int max = longest;
		if ((index + (longest)) > (input.length())) {
			max = (input.length()) - index;
		} 
		for (int i = max ; i >= (shortest) ; i--) {
			final java.lang.CharSequence subSeq = input.subSequence(index, (index + i));
			final java.lang.CharSequence result = lookupMap.get(subSeq.toString());
			if (result != null) {
				out.write(result.toString());
				return i;
			} 
		}
		return 0;
	}
}

