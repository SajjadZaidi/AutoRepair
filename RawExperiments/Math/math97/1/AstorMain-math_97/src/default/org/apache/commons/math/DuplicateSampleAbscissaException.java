package org.apache.commons.math;


public class DuplicateSampleAbscissaException extends org.apache.commons.math.MathException {
	private static final long serialVersionUID = -2271007547170169872L;

	public DuplicateSampleAbscissaException(double abscissa ,int i1 ,int i2) {
		super("Abscissa {0} is duplicated at both indices {1} and {2}", new java.lang.Object[]{ java.lang.Double.valueOf(abscissa) , java.lang.Integer.valueOf(i1) , java.lang.Integer.valueOf(i2) });
	}

	public double getDuplicateAbscissa() {
		return ((java.lang.Double)(getArguments()[0])).doubleValue();
	}
}

