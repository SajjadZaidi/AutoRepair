package org.apache.commons.math3.analysis.interpolation;


public interface MultivariateInterpolator {
	org.apache.commons.math3.analysis.MultivariateFunction interpolate(double[][] xval, double[] yval) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MathIllegalArgumentException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException;
}

