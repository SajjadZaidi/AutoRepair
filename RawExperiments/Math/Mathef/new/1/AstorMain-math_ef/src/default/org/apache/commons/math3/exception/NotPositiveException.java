package org.apache.commons.math3.exception;


public class NotPositiveException extends org.apache.commons.math3.exception.NumberIsTooSmallException {
	private static final long serialVersionUID = -2250556892093726375L;

	public NotPositiveException(java.lang.Number value) {
		super(value, org.apache.commons.math3.exception.MathIllegalNumberException.INTEGER_ZERO, true);
	}

	public NotPositiveException(org.apache.commons.math3.exception.util.Localizable specific ,java.lang.Number value) {
		super(specific, value, org.apache.commons.math3.exception.MathIllegalNumberException.INTEGER_ZERO, true);
	}
}

