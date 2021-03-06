package org.apache.commons.math3.complex;


public class ComplexUtils {
	private ComplexUtils() {
	}

	public static org.apache.commons.math3.complex.Complex polar2Complex(double r, double theta) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (r < 0) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NEGATIVE_COMPLEX_MODULE , r);
		} 
		return new org.apache.commons.math3.complex.Complex((r * (org.apache.commons.math3.util.FastMath.cos(theta))) , (r * (org.apache.commons.math3.util.FastMath.sin(theta))));
	}

	public static org.apache.commons.math3.complex.Complex[] convertToComplex(double[] real) {
		final org.apache.commons.math3.complex.Complex[] c = new org.apache.commons.math3.complex.Complex[real.length];
		for (int i = 0 ; i < (real.length) ; i++) {
			c[i] = new org.apache.commons.math3.complex.Complex(real[i] , 0);
		}
		return c;
	}
}

