package org.apache.commons.math3.analysis.interpolation;


public class BicubicInterpolatingFunction implements org.apache.commons.math3.analysis.BivariateFunction {
	private static final int NUM_COEFF = 16;

	private static final double[][] AINV = new double[][]{ new double[]{ 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ -3 , 3 , 0 , 0 , -2 , -1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 2 , -2 , 0 , 0 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -3 , 3 , 0 , 0 , -2 , -1 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 2 , -2 , 0 , 0 , 1 , 1 , 0 , 0 } , new double[]{ -3 , 0 , 3 , 0 , 0 , 0 , 0 , 0 , -2 , 0 , -1 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , -3 , 0 , 3 , 0 , 0 , 0 , 0 , 0 , -2 , 0 , -1 , 0 } , new double[]{ 9 , -9 , -9 , 9 , 6 , 3 , -6 , -3 , 6 , -6 , 3 , -3 , 4 , 2 , 2 , 1 } , new double[]{ -6 , 6 , 6 , -6 , -3 , -3 , 3 , 3 , -4 , 4 , -2 , 2 , -2 , -2 , -1 , -1 } , new double[]{ 2 , 0 , -2 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 2 , 0 , -2 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 } , new double[]{ -6 , 6 , 6 , -6 , -4 , -2 , 4 , 2 , -3 , 3 , -3 , 3 , -2 , -1 , -2 , -1 } , new double[]{ 4 , -4 , -4 , 4 , 2 , 2 , -2 , -2 , 2 , -2 , 2 , -2 , 1 , 1 , 1 , 1 } };

	private final double[] xval;

	private final double[] yval;

	private final org.apache.commons.math3.analysis.interpolation.BicubicFunction[][] splines;

	public BicubicInterpolatingFunction(double[] x ,double[] y ,double[][] f ,double[][] dFdX ,double[][] dFdY ,double[][] d2FdXdY) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NonMonotonicSequenceException {
		final int xLen = x.length;
		final int yLen = y.length;
		if ((((xLen == 0) || (yLen == 0)) || ((f.length) == 0)) || ((f[0].length) == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		if (xLen != (f.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xLen , f.length);
		} 
		if (xLen != (dFdX.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xLen , dFdX.length);
		} 
		if (xLen != (dFdY.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xLen , dFdY.length);
		} 
		if (xLen != (d2FdXdY.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xLen , d2FdXdY.length);
		} 
		org.apache.commons.math3.util.MathArrays.checkOrder(x);
		org.apache.commons.math3.util.MathArrays.checkOrder(y);
		xval = x.clone();
		yval = y.clone();
		final int lastI = xLen - 1;
		final int lastJ = yLen - 1;
		splines = new org.apache.commons.math3.analysis.interpolation.BicubicFunction[lastI][lastJ];
		for (int i = 0 ; i < lastI ; i++) {
			if ((f[i].length) != yLen) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(f[i].length , yLen);
			} 
			if ((dFdX[i].length) != yLen) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(dFdX[i].length , yLen);
			} 
			if ((dFdY[i].length) != yLen) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(dFdY[i].length , yLen);
			} 
			if ((d2FdXdY[i].length) != yLen) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(d2FdXdY[i].length , yLen);
			} 
			final int ip1 = i + 1;
			final double xR = (xval[ip1]) - (xval[i]);
			for (int j = 0 ; j < lastJ ; j++) {
				final int jp1 = j + 1;
				final double yR = (yval[jp1]) - (yval[j]);
				final double xRyR = xR * yR;
				final double[] beta = new double[]{ f[i][j] , f[ip1][j] , f[i][jp1] , f[ip1][jp1] , (dFdX[i][j]) * xR , (dFdX[ip1][j]) * xR , (dFdX[i][jp1]) * xR , (dFdX[ip1][jp1]) * xR , (dFdY[i][j]) * yR , (dFdY[ip1][j]) * yR , (dFdY[i][jp1]) * yR , (dFdY[ip1][jp1]) * yR , (d2FdXdY[i][j]) * xRyR , (d2FdXdY[ip1][j]) * xRyR , (d2FdXdY[i][jp1]) * xRyR , (d2FdXdY[ip1][jp1]) * xRyR };
				splines[i][j] = new org.apache.commons.math3.analysis.interpolation.BicubicFunction(computeSplineCoefficients(beta));
			}
		}
	}

	public double value(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		final int i = searchIndex(x, xval);
		final int j = searchIndex(y, yval);
		final double xN = (x - (xval[i])) / ((xval[(i + 1)]) - (xval[i]));
		final double yN = (y - (yval[j])) / ((yval[(j + 1)]) - (yval[j]));
		return splines[i][j].value(xN, yN);
	}

	public boolean isValidPoint(double x, double y) {
		if ((((x < (xval[0])) || (x > (xval[((xval.length) - 1)]))) || (y < (yval[0]))) || (y > (yval[((yval.length) - 1)]))) {
			return false;
		} else {
			return true;
		}
	}

	private int searchIndex(double c, double[] val) {
		final int r = java.util.Arrays.binarySearch(val, c);
		if ((r == (-1)) || (r == ((-(val.length)) - 1))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(c , val[0] , val[((val.length) - 1)]);
		} 
		if (r < 0) {
			return (-r) - 2;
		} 
		final int last = (val.length) - 1;
		if (r == last) {
			return last - 1;
		} 
		return r;
	}

	private double[] computeSplineCoefficients(double[] beta) {
		final double[] a = new double[org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.NUM_COEFF];
		for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.NUM_COEFF) ; i++) {
			double result = 0;
			final double[] row = org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.AINV[i];
			for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.NUM_COEFF) ; j++) {
				result += (row[j]) * (beta[j]);
			}
			a[i] = result;
		}
		return a;
	}
}

class BicubicFunction implements org.apache.commons.math3.analysis.BivariateFunction {
	private static final short N = 4;

	private final double[][] a;

	public BicubicFunction(double[] coeff) {
		a = new double[org.apache.commons.math3.analysis.interpolation.BicubicFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicFunction.N];
		for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicFunction.N) ; j++) {
			final double[] aJ = a[j];
			for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicFunction.N) ; i++) {
				aJ[i] = coeff[((i * (org.apache.commons.math3.analysis.interpolation.BicubicFunction.N)) + j)];
			}
		}
	}

	public double value(double x, double y) {
		if ((x < 0) || (x > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(x , 0 , 1);
		} 
		if ((y < 0) || (y > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(y , 0 , 1);
		} 
		final double x2 = x * x;
		final double x3 = x2 * x;
		final double[] pX = new double[]{ 1 , x , x2 , x3 };
		final double y2 = y * y;
		final double y3 = y2 * y;
		final double[] pY = new double[]{ 1 , y , y2 , y3 };
		return apply(pX, pY, a);
	}

	private double apply(double[] pX, double[] pY, double[][] coeff) {
		double result = 0;
		for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicFunction.N) ; i++) {
			final double r = org.apache.commons.math3.util.MathArrays.linearCombination(coeff[i], pY);
			result += r * (pX[i]);
		}
		return result;
	}
}

