package org.apache.commons.math3.exception;


public class InsufficientDataException extends org.apache.commons.math3.exception.MathIllegalArgumentException {
	private static final long serialVersionUID = -2629324471511903359L;

	public InsufficientDataException() {
		this(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_DATA);
	}

	public InsufficientDataException(org.apache.commons.math3.exception.util.Localizable pattern ,java.lang.Object... arguments) {
		super(pattern, arguments);
	}
}

