package org.apache.commons.math3.analysis.interpolation;


public interface TrivariateGridInterpolator {
	org.apache.commons.math3.analysis.TrivariateFunction interpolate(double[] xval, double[] yval, double[] zval, double[][][] fval) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NonMonotonicSequenceException, org.apache.commons.math3.exception.NumberIsTooSmallException;
}

