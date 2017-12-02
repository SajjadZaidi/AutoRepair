package org.apache.commons.lang3.exception;


public interface ExceptionContext {
	public org.apache.commons.lang3.exception.ExceptionContext addContextValue(java.lang.String label, java.lang.Object value);

	public org.apache.commons.lang3.exception.ExceptionContext setContextValue(java.lang.String label, java.lang.Object value);

	public java.util.List<java.lang.Object> getContextValues(java.lang.String label);

	public java.lang.Object getFirstContextValue(java.lang.String label);

	public java.util.Set<java.lang.String> getContextLabels();

	public java.util.List<org.apache.commons.lang3.tuple.Pair<java.lang.String, java.lang.Object>> getContextEntries();

	public java.lang.String getFormattedExceptionMessage(java.lang.String baseMessage);
}

