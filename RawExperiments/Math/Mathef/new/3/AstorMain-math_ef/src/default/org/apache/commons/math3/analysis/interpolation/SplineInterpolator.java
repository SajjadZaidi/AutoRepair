package org.apache.commons.math3.analysis.interpolation;


public class SplineInterpolator implements org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator {
	public org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction interpolate(double[] x, double[] y) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NonMonotonicSequenceException, org.apache.commons.math3.exception.NumberIsTooSmallException {
		if ((x.length) != (y.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(x.length , y.length);
		} 
		if ((x.length) < 3) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_POINTS , x.length , 3 , true);
		} 
		final int n = (x.length) - 1;
		org.apache.commons.math3.util.MathArrays.checkOrder(x);
		final double[] h = new double[n];
		for (int i = 0 ; i < n ; i++) {
			h[i] = (x[(i + 1)]) - (x[i]);
		}
		final double[] mu = new double[n];
		final double[] z = new double[n + 1];
		mu[0] = 0.0;
		z[0] = 0.0;
		double g = 0;
		for (int i = 1 ; i < n ; i++) {
			g = (2.0 * ((x[(i + 1)]) - (x[(i - 1)]))) - ((h[(i - 1)]) * (mu[(i - 1)]));
			mu[i] = (h[i]) / g;
			z[i] = (((3.0 * ((((y[(i + 1)]) * (h[(i - 1)])) - ((y[i]) * ((x[(i + 1)]) - (x[(i - 1)])))) + ((y[(i - 1)]) * (h[i])))) / ((h[(i - 1)]) * (h[i]))) - ((h[(i - 1)]) * (z[(i - 1)]))) / g;
		}
		final double[] b = new double[n];
		final double[] c = new double[n + 1];
		final double[] d = new double[n];
		z[n] = 0.0;
		c[n] = 0.0;
		for (int j = n - 1 ; j >= 0 ; j--) {
			c[j] = (z[j]) - ((mu[j]) * (c[(j + 1)]));
			b[j] = (((y[(j + 1)]) - (y[j])) / (h[j])) - (((h[j]) * ((c[(j + 1)]) + (2.0 * (c[j])))) / 3.0);
			d[j] = ((c[(j + 1)]) - (c[j])) / (3.0 * (h[j]));
		}
		final org.apache.commons.math3.analysis.polynomials.PolynomialFunction[] polynomials = new org.apache.commons.math3.analysis.polynomials.PolynomialFunction[n];
		final double[] coefficients = new double[4];
		for (int i = 0 ; i < n ; i++) {
			coefficients[0] = y[i];
			coefficients[1] = b[i];
			coefficients[2] = c[i];
			coefficients[3] = d[i];
			polynomials[i] = new org.apache.commons.math3.analysis.polynomials.PolynomialFunction(coefficients);
		}
		return new org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction(x , polynomials);
	}
}

