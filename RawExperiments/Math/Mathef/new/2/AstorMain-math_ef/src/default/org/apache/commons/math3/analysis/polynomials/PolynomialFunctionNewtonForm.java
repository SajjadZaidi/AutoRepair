package org.apache.commons.math3.analysis.polynomials;


public class PolynomialFunctionNewtonForm implements org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction {
	private double[] coefficients;

	private final double[] c;

	private final double[] a;

	private boolean coefficientsComputed;

	public PolynomialFunctionNewtonForm(double[] a ,double[] c) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray(a, c);
		this.a = new double[a.length];
		this.c = new double[c.length];
		java.lang.System.arraycopy(a, 0, org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.this.a, 0, a.length);
		java.lang.System.arraycopy(c, 0, org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.this.c, 0, c.length);
		coefficientsComputed = false;
	}

	public double value(double z) {
		return org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate(a, c, z);
	}

	public org.apache.commons.math3.analysis.differentiation.DerivativeStructure value(final org.apache.commons.math3.analysis.differentiation.DerivativeStructure t) {
		org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray(a, c);
		final int n = c.length;
		org.apache.commons.math3.analysis.differentiation.DerivativeStructure value = new org.apache.commons.math3.analysis.differentiation.DerivativeStructure(t.getFreeParameters() , t.getOrder() , a[n]);
		for (int i = n - 1 ; i >= 0 ; i--) {
			value = t.subtract(c[i]).multiply(value).add(a[i]);
		}
		return value;
	}

	public int degree() {
		return c.length;
	}

	public double[] getNewtonCoefficients() {
		double[] out = new double[a.length];
		java.lang.System.arraycopy(a, 0, out, 0, a.length);
		return out;
	}

	public double[] getCenters() {
		double[] out = new double[c.length];
		java.lang.System.arraycopy(c, 0, out, 0, c.length);
		return out;
	}

	public double[] getCoefficients() {
		if (!(coefficientsComputed)) {
			computeCoefficients();
		} 
		double[] out = new double[coefficients.length];
		java.lang.System.arraycopy(coefficients, 0, out, 0, coefficients.length);
		return out;
	}

	public static double evaluate(double[] a, double[] c, double z) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray(a, c);
		final int n = c.length;
		double value = a[n];
		for (int i = n - 1 ; i >= 0 ; i--) {
			value = (a[i]) + ((z - (c[i])) * value);
		}
		return value;
	}

	protected void computeCoefficients() {
		final int n = degree();
		coefficients = new double[n + 1];
		for (int i = 0 ; i <= n ; i++) {
			coefficients[i] = 0.0;
		}
		coefficients[0] = a[n];
		for (int i = n - 1 ; i >= 0 ; i--) {
			for (int j = n - i ; j > 0 ; j--) {
				coefficients[j] = (coefficients[(j - 1)]) - ((c[i]) * (coefficients[j]));
			}
			coefficients[0] = (a[i]) - ((c[i]) * (coefficients[0]));
		}
		coefficientsComputed = true;
	}

	protected static void verifyInputArray(double[] a, double[] c) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(a);
		org.apache.commons.math3.util.MathUtils.checkNotNull(c);
		if (((a.length) == 0) || ((c.length) == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
		} 
		if ((a.length) != ((c.length) + 1)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(org.apache.commons.math3.exception.util.LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1 , a.length , c.length);
		} 
	}
}

