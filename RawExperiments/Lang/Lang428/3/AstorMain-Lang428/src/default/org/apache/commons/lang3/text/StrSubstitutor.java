package org.apache.commons.lang3.text;


public class StrSubstitutor {
	public static final char DEFAULT_ESCAPE = '$';

	public static final org.apache.commons.lang3.text.StrMatcher DEFAULT_PREFIX = org.apache.commons.lang3.text.StrMatcher.stringMatcher("${");

	public static final org.apache.commons.lang3.text.StrMatcher DEFAULT_SUFFIX = org.apache.commons.lang3.text.StrMatcher.stringMatcher("}");

	private char escapeChar;

	private org.apache.commons.lang3.text.StrMatcher prefixMatcher;

	private org.apache.commons.lang3.text.StrMatcher suffixMatcher;

	private org.apache.commons.lang3.text.StrLookup<?> variableResolver;

	private boolean enableSubstitutionInVariables;

	public static <V>java.lang.String replace(java.lang.Object source, java.util.Map<java.lang.String, V> valueMap) {
		return new org.apache.commons.lang3.text.StrSubstitutor(valueMap).replace(source);
	}

	public static <V>java.lang.String replace(java.lang.Object source, java.util.Map<java.lang.String, V> valueMap, java.lang.String prefix, java.lang.String suffix) {
		return new org.apache.commons.lang3.text.StrSubstitutor(valueMap , prefix , suffix).replace(source);
	}

	public static java.lang.String replace(java.lang.Object source, java.util.Properties valueProperties) {
		if (valueProperties == null) {
			return source.toString();
		} 
		java.util.Map<java.lang.String, java.lang.String> valueMap = new java.util.HashMap<java.lang.String, java.lang.String>();
		java.util.Enumeration<?> propNames = valueProperties.propertyNames();
		while (propNames.hasMoreElements()) {
			java.lang.String propName = ((java.lang.String)(propNames.nextElement()));
			java.lang.String propValue = valueProperties.getProperty(propName);
			valueMap.put(propName, propValue);
		}
		return org.apache.commons.lang3.text.StrSubstitutor.replace(source, valueMap);
	}

	public static java.lang.String replaceSystemProperties(java.lang.Object source) {
		return new org.apache.commons.lang3.text.StrSubstitutor(org.apache.commons.lang3.text.StrLookup.systemPropertiesLookup()).replace(source);
	}

	public StrSubstitutor() {
		this(((org.apache.commons.lang3.text.StrLookup<?>)(null)), org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_PREFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_SUFFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_ESCAPE);
	}

	public <V>StrSubstitutor(java.util.Map<java.lang.String, V> valueMap) {
		this(org.apache.commons.lang3.text.StrLookup.mapLookup(valueMap), org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_PREFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_SUFFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_ESCAPE);
	}

	public <V>StrSubstitutor(java.util.Map<java.lang.String, V> valueMap ,java.lang.String prefix ,java.lang.String suffix) {
		this(org.apache.commons.lang3.text.StrLookup.mapLookup(valueMap), prefix, suffix, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_ESCAPE);
	}

	public <V>StrSubstitutor(java.util.Map<java.lang.String, V> valueMap ,java.lang.String prefix ,java.lang.String suffix ,char escape) {
		this(org.apache.commons.lang3.text.StrLookup.mapLookup(valueMap), prefix, suffix, escape);
	}

	public StrSubstitutor(org.apache.commons.lang3.text.StrLookup<?> variableResolver) {
		this(variableResolver, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_PREFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_SUFFIX, org.apache.commons.lang3.text.StrSubstitutor.DEFAULT_ESCAPE);
	}

	public StrSubstitutor(org.apache.commons.lang3.text.StrLookup<?> variableResolver ,java.lang.String prefix ,java.lang.String suffix ,char escape) {
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariableResolver(variableResolver);
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariablePrefix(prefix);
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariableSuffix(suffix);
		org.apache.commons.lang3.text.StrSubstitutor.this.setEscapeChar(escape);
	}

	public StrSubstitutor(org.apache.commons.lang3.text.StrLookup<?> variableResolver ,org.apache.commons.lang3.text.StrMatcher prefixMatcher ,org.apache.commons.lang3.text.StrMatcher suffixMatcher ,char escape) {
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariableResolver(variableResolver);
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariablePrefixMatcher(prefixMatcher);
		org.apache.commons.lang3.text.StrSubstitutor.this.setVariableSuffixMatcher(suffixMatcher);
		org.apache.commons.lang3.text.StrSubstitutor.this.setEscapeChar(escape);
	}

	public java.lang.String replace(java.lang.String source) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(source);
		if ((substitute(buf, 0, source.length())) == false) {
			return source;
		} 
		return buf.toString();
	}

	public java.lang.String replace(java.lang.String source, int offset, int length) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(length).append(source, offset, length);
		if ((substitute(buf, 0, length)) == false) {
			return source.substring(offset, (offset + length));
		} 
		return buf.toString();
	}

	public java.lang.String replace(char[] source) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(source.length).append(source);
		substitute(buf, 0, source.length);
		return buf.toString();
	}

	public java.lang.String replace(char[] source, int offset, int length) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(length).append(source, offset, length);
		substitute(buf, 0, length);
		return buf.toString();
	}

	public java.lang.String replace(java.lang.StringBuffer source) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(source.length()).append(source);
		substitute(buf, 0, buf.length());
		return buf.toString();
	}

	public java.lang.String replace(java.lang.StringBuffer source, int offset, int length) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(length).append(source, offset, length);
		substitute(buf, 0, length);
		return buf.toString();
	}

	public java.lang.String replace(org.apache.commons.lang3.text.StrBuilder source) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(source.length()).append(source);
		substitute(buf, 0, buf.length());
		return buf.toString();
	}

	public java.lang.String replace(org.apache.commons.lang3.text.StrBuilder source, int offset, int length) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(length).append(source, offset, length);
		substitute(buf, 0, length);
		return buf.toString();
	}

	public java.lang.String replace(java.lang.Object source) {
		if (source == null) {
			return null;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder().append(source);
		substitute(buf, 0, buf.length());
		return buf.toString();
	}

	public boolean replaceIn(java.lang.StringBuffer source) {
		if (source == null) {
			return false;
		} 
		return replaceIn(source, 0, source.length());
	}

	public boolean replaceIn(java.lang.StringBuffer source, int offset, int length) {
		if (source == null) {
			return false;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(length).append(source, offset, length);
		if ((substitute(buf, 0, length)) == false) {
			return false;
		} 
		source.replace(offset, (offset + length), buf.toString());
		return true;
	}

	public boolean replaceIn(org.apache.commons.lang3.text.StrBuilder source) {
		if (source == null) {
			return false;
		} 
		return substitute(source, 0, source.length());
	}

	public boolean replaceIn(org.apache.commons.lang3.text.StrBuilder source, int offset, int length) {
		if (source == null) {
			return false;
		} 
		return substitute(source, offset, length);
	}

	protected boolean substitute(org.apache.commons.lang3.text.StrBuilder buf, int offset, int length) {
		return (substitute(buf, offset, length, null)) > 0;
	}

	private int substitute(org.apache.commons.lang3.text.StrBuilder buf, int offset, int length, java.util.List<java.lang.String> priorVariables) {
		org.apache.commons.lang3.text.StrMatcher prefixMatcher = getVariablePrefixMatcher();
		org.apache.commons.lang3.text.StrMatcher suffixMatcher = getVariableSuffixMatcher();
		char escape = getEscapeChar();
		boolean top = priorVariables == null;
		boolean altered = false;
		int lengthChange = 0;
		char[] chars = buf.buffer;
		int bufEnd = offset + length;
		int pos = offset;
		while (pos < bufEnd) {
			int startMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd);
			if (startMatchLen == 0) {
				pos++;
			} else {
				if ((pos > offset) && ((chars[(pos - 1)]) == escape)) {
					buf.deleteCharAt((pos - 1));
					chars = buf.buffer;
					lengthChange--;
					altered = true;
					bufEnd--;
				} else {
					int startPos = pos;
					pos += startMatchLen;
					int endMatchLen = 0;
					int nestedVarCount = 0;
					while (pos < bufEnd) {
						if ((isEnableSubstitutionInVariables()) && ((endMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd)) != 0)) {
							nestedVarCount++;
							pos += endMatchLen;
							continue;
						} 
						endMatchLen = suffixMatcher.isMatch(chars, pos, offset, bufEnd);
						if (endMatchLen == 0) {
							pos++;
						} else {
							if (nestedVarCount == 0) {
								java.lang.String varName = new java.lang.String(chars , (startPos + startMatchLen) , ((pos - startPos) - startMatchLen));
								if (isEnableSubstitutionInVariables()) {
									org.apache.commons.lang3.text.StrBuilder bufName = new org.apache.commons.lang3.text.StrBuilder(varName);
									substitute(bufName, 0, bufName.length());
									varName = bufName.toString();
								} 
								pos += endMatchLen;
								int endPos = pos;
								if (priorVariables == null) {
									priorVariables = new java.util.ArrayList<java.lang.String>();
									priorVariables.add(new java.lang.String(chars , offset , length));
								} 
								checkCyclicSubstitution(varName, priorVariables);
								priorVariables.add(varName);
								java.lang.String varValue = resolveVariable(varName, buf, startPos, endPos);
								if (varValue != null) {
									int varLen = varValue.length();
									buf.replace(startPos, endPos, varValue);
									altered = true;
									int change = substitute(buf, startPos, varLen, priorVariables);
									change = change + (varLen - (endPos - startPos));
									pos += change;
									bufEnd += change;
									lengthChange += change;
									chars = buf.buffer;
								} 
								priorVariables.remove(((priorVariables.size()) - 1));
								break;
							} else {
								nestedVarCount--;
								pos += endMatchLen;
							}
						}
					}
				}
			}
		}
		if (top) {
			return altered ? 1 : 0;
		} 
		return lengthChange;
	}

	private void checkCyclicSubstitution(java.lang.String varName, java.util.List<java.lang.String> priorVariables) {
		if ((priorVariables.contains(varName)) == false) {
			return ;
		} 
		org.apache.commons.lang3.text.StrBuilder buf = new org.apache.commons.lang3.text.StrBuilder(256);
		buf.append("Infinite loop in property interpolation of ");
		buf.append(priorVariables.remove(0));
		buf.append(": ");
		buf.appendWithSeparators(priorVariables, "->");
		throw new java.lang.IllegalStateException(buf.toString());
	}

	protected java.lang.String resolveVariable(java.lang.String variableName, org.apache.commons.lang3.text.StrBuilder buf, int startPos, int endPos) {
		org.apache.commons.lang3.text.StrLookup<?> resolver = getVariableResolver();
		if (resolver == null) {
			return null;
		} 
		return resolver.lookup(variableName);
	}

	public char getEscapeChar() {
		return org.apache.commons.lang3.text.StrSubstitutor.this.escapeChar;
	}

	public void setEscapeChar(char escapeCharacter) {
		org.apache.commons.lang3.text.StrSubstitutor.this.escapeChar = escapeCharacter;
	}

	public org.apache.commons.lang3.text.StrMatcher getVariablePrefixMatcher() {
		return prefixMatcher;
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariablePrefixMatcher(org.apache.commons.lang3.text.StrMatcher prefixMatcher) {
		if (prefixMatcher == null) {
			throw new java.lang.IllegalArgumentException("Variable prefix matcher must not be null!");
		} 
		org.apache.commons.lang3.text.StrSubstitutor.this.prefixMatcher = prefixMatcher;
		return org.apache.commons.lang3.text.StrSubstitutor.this;
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariablePrefix(char prefix) {
		return setVariablePrefixMatcher(org.apache.commons.lang3.text.StrMatcher.charMatcher(prefix));
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariablePrefix(java.lang.String prefix) {
		if (prefix == null) {
			throw new java.lang.IllegalArgumentException("Variable prefix must not be null!");
		} 
		return setVariablePrefixMatcher(org.apache.commons.lang3.text.StrMatcher.stringMatcher(prefix));
	}

	public org.apache.commons.lang3.text.StrMatcher getVariableSuffixMatcher() {
		return suffixMatcher;
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariableSuffixMatcher(org.apache.commons.lang3.text.StrMatcher suffixMatcher) {
		if (suffixMatcher == null) {
			throw new java.lang.IllegalArgumentException("Variable suffix matcher must not be null!");
		} 
		org.apache.commons.lang3.text.StrSubstitutor.this.suffixMatcher = suffixMatcher;
		return org.apache.commons.lang3.text.StrSubstitutor.this;
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariableSuffix(char suffix) {
		return setVariableSuffixMatcher(org.apache.commons.lang3.text.StrMatcher.charMatcher(suffix));
	}

	public org.apache.commons.lang3.text.StrSubstitutor setVariableSuffix(java.lang.String suffix) {
		if (suffix == null) {
			throw new java.lang.IllegalArgumentException("Variable suffix must not be null!");
		} 
		return setVariableSuffixMatcher(org.apache.commons.lang3.text.StrMatcher.stringMatcher(suffix));
	}

	public org.apache.commons.lang3.text.StrLookup<?> getVariableResolver() {
		return org.apache.commons.lang3.text.StrSubstitutor.this.variableResolver;
	}

	public void setVariableResolver(org.apache.commons.lang3.text.StrLookup<?> variableResolver) {
		org.apache.commons.lang3.text.StrSubstitutor.this.variableResolver = variableResolver;
	}

	public boolean isEnableSubstitutionInVariables() {
		return enableSubstitutionInVariables;
	}

	public void setEnableSubstitutionInVariables(boolean enableSubstitutionInVariables) {
		org.apache.commons.lang3.text.StrSubstitutor.this.enableSubstitutionInVariables = enableSubstitutionInVariables;
	}
}

