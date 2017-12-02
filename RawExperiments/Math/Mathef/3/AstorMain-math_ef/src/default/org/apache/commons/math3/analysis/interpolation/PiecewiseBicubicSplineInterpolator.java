package org.apache.commons.math3.analysis.interpolation;


public class PiecewiseBicubicSplineInterpolator implements org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator {
	public org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NonMonotonicSequenceException, org.apache.commons.math3.exception.NullArgumentException {
		if ((((xval == null) || (yval == null)) || (fval == null)) || ((fval[0]) == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if ((((xval.length) == 0) || ((yval.length) == 0)) || ((fval.length) == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		org.apache.commons.math3.util.MathArrays.checkOrder(xval);
		org.apache.commons.math3.util.MathArrays.checkOrder(yval);
		return new org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction(xval , yval , fval);
	}
}

