package org.apache.commons.math3.exception;


public class MathIllegalNumberException extends org.apache.commons.math3.exception.MathIllegalArgumentException {
	protected static final java.lang.Integer INTEGER_ZERO = java.lang.Integer.valueOf(0);

	private static final long serialVersionUID = -7447085893598031110L;

	private final java.lang.Number argument;

	protected MathIllegalNumberException(org.apache.commons.math3.exception.util.Localizable pattern ,java.lang.Number wrong ,java.lang.Object... arguments) {
		super(pattern, wrong, arguments);
		argument = wrong;
	}

	public java.lang.Number getArgument() {
		return argument;
	}
}

