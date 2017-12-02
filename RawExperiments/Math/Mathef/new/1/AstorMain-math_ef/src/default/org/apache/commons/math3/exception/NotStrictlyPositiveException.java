package org.apache.commons.math3.exception;


public class NotStrictlyPositiveException extends org.apache.commons.math3.exception.NumberIsTooSmallException {
	private static final long serialVersionUID = -7824848630829852237L;

	public NotStrictlyPositiveException(java.lang.Number value) {
		super(value, org.apache.commons.math3.exception.MathIllegalNumberException.INTEGER_ZERO, false);
	}

	public NotStrictlyPositiveException(org.apache.commons.math3.exception.util.Localizable specific ,java.lang.Number value) {
		super(specific, value, org.apache.commons.math3.exception.MathIllegalNumberException.INTEGER_ZERO, false);
	}
}

