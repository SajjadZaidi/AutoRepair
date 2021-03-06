package org.apache.commons.math.analysis;


public class SplineInterpolator implements org.apache.commons.math.analysis.UnivariateRealInterpolator {
	public org.apache.commons.math.analysis.UnivariateRealFunction interpolate(double[] x, double[] y) {
		if ((x.length) != (y.length)) {
			throw new java.lang.IllegalArgumentException("Dataset arrays must have same length.");
		} 
		if ((x.length) < 3) {
			throw new java.lang.IllegalArgumentException("At least 3 datapoints are required to compute a spline interpolant");
		} 
		int n = (x.length) - 1;
		for (int i = 0 ; i < n ; i++) {
			if ((x[i]) >= (x[(i + 1)])) {
				throw new java.lang.IllegalArgumentException("Dataset x values must be strictly increasing.");
			} 
		}
		double[] h = new double[n];
		for (int i = 0 ; i < n ; i++) {
			h[i] = (x[(i + 1)]) - (x[i]);
		}
		double[] mu = new double[n];
		double[] z = new double[n + 1];
		mu[0] = 0.0;
		z[0] = 0.0;
		double g = 0;
		for (int i = 1 ; i < n ; i++) {
			g = (2.0 * ((x[(i + 1)]) - (x[(i - 1)]))) - ((h[(i - 1)]) * (mu[(i - 1)]));
			mu[i] = (h[i]) / g;
			z[i] = (((3.0 * ((((y[(i + 1)]) * (h[(i - 1)])) - ((y[i]) * ((x[(i + 1)]) - (x[(i - 1)])))) + ((y[(i - 1)]) * (h[i])))) / ((h[(i - 1)]) * (h[i]))) - ((h[(i - 1)]) * (z[(i - 1)]))) / g;
		}
		double[] b = new double[n];
		double[] c = new double[n + 1];
		double[] d = new double[n];
		z[n] = 0.0;
		c[n] = 0.0;
		for (int j = n - 1 ; j >= 0 ; j--) {
			c[j] = (z[j]) - ((mu[j]) * (c[(j + 1)]));
			b[j] = (((y[(j + 1)]) - (y[j])) / (h[j])) - (((h[j]) * ((c[(j + 1)]) + (2.0 * (c[j])))) / 3.0);
			d[j] = ((c[(j + 1)]) - (c[j])) / (3.0 * (h[j]));
		}
		org.apache.commons.math.analysis.PolynomialFunction[] polynomials = new org.apache.commons.math.analysis.PolynomialFunction[n];
		double[] coefficients = new double[4];
		for (int i = 0 ; i < n ; i++) {
			coefficients[0] = y[i];
			coefficients[1] = b[i];
			coefficients[2] = c[i];
			coefficients[3] = d[i];
			polynomials[i] = new org.apache.commons.math.analysis.PolynomialFunction(coefficients);
		}
		return new org.apache.commons.math.analysis.PolynomialSplineFunction(x , polynomials);
	}
}

