package org.apache.commons.math3.analysis.interpolation;


public class PiecewiseBicubicSplineInterpolatingFunction implements org.apache.commons.math3.analysis.BivariateFunction {
	private static final int MIN_NUM_POINTS = 5;

	private final double[] xval;

	private final double[] yval;

	private final double[][] fval;

	public PiecewiseBicubicSplineInterpolatingFunction(double[] x ,double[] y ,double[][] f) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NonMonotonicSequenceException , org.apache.commons.math3.exception.NullArgumentException {
		if ((((x == null) || (y == null)) || (f == null)) || ((f[0]) == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		final int xLen = x.length;
		final int yLen = y.length;
		if ((((xLen == 0) || (yLen == 0)) || ((f.length) == 0)) || ((f[0].length) == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		if ((((xLen < (org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.MIN_NUM_POINTS)) || (yLen < (org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.MIN_NUM_POINTS))) || ((f.length) < (org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.MIN_NUM_POINTS))) || ((f[0].length) < (org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.MIN_NUM_POINTS))) {
			throw new org.apache.commons.math3.exception.InsufficientDataException();
		} 
		if (xLen != (f.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xLen , f.length);
		} 
		if (yLen != (f[0].length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(yLen , f[0].length);
		} 
		org.apache.commons.math3.util.MathArrays.checkOrder(x);
		org.apache.commons.math3.util.MathArrays.checkOrder(y);
		xval = x.clone();
		yval = y.clone();
		fval = f.clone();
	}

	public double value(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		final org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator interpolator = new org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator();
		final int offset = 2;
		final int count = offset + 3;
		final int i = searchIndex(x, xval, offset, count);
		final int j = searchIndex(y, yval, offset, count);
		final double[] xArray = new double[count];
		final double[] yArray = new double[count];
		final double[] zArray = new double[count];
		final double[] interpArray = new double[count];
		for (int index = 0 ; index < count ; index++) {
			xArray[index] = xval[(i + index)];
			yArray[index] = yval[(j + index)];
		}
		for (int zIndex = 0 ; zIndex < count ; zIndex++) {
			for (int index = 0 ; index < count ; index++) {
				zArray[index] = fval[(i + index)][(j + zIndex)];
			}
			final org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction spline = interpolator.interpolate(xArray, zArray);
			interpArray[zIndex] = spline.value(x);
		}
		final org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction spline = interpolator.interpolate(yArray, interpArray);
		double returnValue = spline.value(y);
		return returnValue;
	}

	public boolean isValidPoint(double x, double y) {
		if ((((x < (xval[0])) || (x > (xval[((xval.length) - 1)]))) || (y < (yval[0]))) || (y > (yval[((yval.length) - 1)]))) {
			return false;
		} else {
			return true;
		}
	}

	private int searchIndex(double c, double[] val, int offset, int count) {
		int r = java.util.Arrays.binarySearch(val, c);
		if ((r == (-1)) || (r == ((-(val.length)) - 1))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(c , val[0] , val[((val.length) - 1)]);
		} 
		if (r < 0) {
			r = ((-r) - offset) - 1;
		} else {
			r -= offset;
		}
		if (r < 0) {
			r = 0;
		} 
		if ((r + count) >= (val.length)) {
			r = (val.length) - count;
		} 
		return r;
	}
}

