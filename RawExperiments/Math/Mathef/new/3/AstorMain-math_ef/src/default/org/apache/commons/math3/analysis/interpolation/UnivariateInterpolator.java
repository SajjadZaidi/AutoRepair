package org.apache.commons.math3.analysis.interpolation;


public interface UnivariateInterpolator {
	org.apache.commons.math3.analysis.UnivariateFunction interpolate(double[] xval, double[] yval) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MathIllegalArgumentException;
}

