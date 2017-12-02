package org.apache.commons.math3.analysis.interpolation;


@java.lang.Deprecated
public class BicubicSplineInterpolatingFunction implements org.apache.commons.math3.analysis.BivariateFunction {
	private static final int NUM_COEFF = 16;

	private static final double[][] AINV = new double[][]{ new double[]{ 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ -3 , 3 , 0 , 0 , -2 , -1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 2 , -2 , 0 , 0 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -3 , 3 , 0 , 0 , -2 , -1 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 2 , -2 , 0 , 0 , 1 , 1 , 0 , 0 } , new double[]{ -3 , 0 , 3 , 0 , 0 , 0 , 0 , 0 , -2 , 0 , -1 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , -3 , 0 , 3 , 0 , 0 , 0 , 0 , 0 , -2 , 0 , -1 , 0 } , new double[]{ 9 , -9 , -9 , 9 , 6 , 3 , -6 , -3 , 6 , -6 , 3 , -3 , 4 , 2 , 2 , 1 } , new double[]{ -6 , 6 , 6 , -6 , -3 , -3 , 3 , 3 , -4 , 4 , -2 , 2 , -2 , -2 , -1 , -1 } , new double[]{ 2 , 0 , -2 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 } , new double[]{ 0 , 0 , 0 , 0 , 2 , 0 , -2 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 } , new double[]{ -6 , 6 , 6 , -6 , -4 , -2 , 4 , 2 , -3 , 3 , -3 , 3 , -2 , -1 , -2 , -1 } , new double[]{ 4 , -4 , -4 , 4 , 2 , 2 , -2 , -2 , 2 , -2 , 2 , -2 , 1 , 1 , 1 , 1 } };

	private final double[] xval;

	private final double[] yval;

	private final org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction[][] splines;

	private final org.apache.commons.math3.analysis.BivariateFunction[][][] partialDerivatives;

	public BicubicSplineInterpolatingFunction(double[] x ,double[] y ,double[][] f ,double[][] dFdX ,double[][] dFdY ,double[][] d2FdXdY) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NonMonotonicSequenceException {
		this(x, y, f, dFdX, dFdY, d2FdXdY, false);
	}

	public BicubicSplineInterpolatingFunction(double[] x ,double[] y ,double[][] f ,double[][] dFdX ,double[][] dFdY ,double[][] d2FdXdY ,boolean initializeDerivatives) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NonMonotonicSequenceException {
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
		splines = new org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction[lastI][lastJ];
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
			for (int j = 0 ; j < lastJ ; j++) {
				final int jp1 = j + 1;
				final double[] beta = new double[]{ f[i][j] , f[ip1][j] , f[i][jp1] , f[ip1][jp1] , dFdX[i][j] , dFdX[ip1][j] , dFdX[i][jp1] , dFdX[ip1][jp1] , dFdY[i][j] , dFdY[ip1][j] , dFdY[i][jp1] , dFdY[ip1][jp1] , d2FdXdY[i][j] , d2FdXdY[ip1][j] , d2FdXdY[i][jp1] , d2FdXdY[ip1][jp1] };
				splines[i][j] = new org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction(computeSplineCoefficients(beta) , initializeDerivatives);
			}
		}
		if (initializeDerivatives) {
			partialDerivatives = new org.apache.commons.math3.analysis.BivariateFunction[5][lastI][lastJ];
			for (int i = 0 ; i < lastI ; i++) {
				for (int j = 0 ; j < lastJ ; j++) {
					final org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction bcs = splines[i][j];
					partialDerivatives[0][i][j] = bcs.partialDerivativeX();
					partialDerivatives[1][i][j] = bcs.partialDerivativeY();
					partialDerivatives[2][i][j] = bcs.partialDerivativeXX();
					partialDerivatives[3][i][j] = bcs.partialDerivativeYY();
					partialDerivatives[4][i][j] = bcs.partialDerivativeXY();
				}
			}
		} else {
			partialDerivatives = null;
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

	public double partialDerivativeX(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		return partialDerivative(0, x, y);
	}

	public double partialDerivativeY(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		return partialDerivative(1, x, y);
	}

	public double partialDerivativeXX(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		return partialDerivative(2, x, y);
	}

	public double partialDerivativeYY(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		return partialDerivative(3, x, y);
	}

	public double partialDerivativeXY(double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		return partialDerivative(4, x, y);
	}

	private double partialDerivative(int which, double x, double y) throws org.apache.commons.math3.exception.OutOfRangeException {
		final int i = searchIndex(x, xval);
		final int j = searchIndex(y, yval);
		final double xN = (x - (xval[i])) / ((xval[(i + 1)]) - (xval[i]));
		final double yN = (y - (yval[j])) / ((yval[(j + 1)]) - (yval[j]));
		return partialDerivatives[which][i][j].value(xN, yN);
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
		final double[] a = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.NUM_COEFF];
		for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.NUM_COEFF) ; i++) {
			double result = 0;
			final double[] row = org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.AINV[i];
			for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.NUM_COEFF) ; j++) {
				result += (row[j]) * (beta[j]);
			}
			a[i] = result;
		}
		return a;
	}
}

class BicubicSplineFunction implements org.apache.commons.math3.analysis.BivariateFunction {
	private static final short N = 4;

	private final double[][] a;

	private final org.apache.commons.math3.analysis.BivariateFunction partialDerivativeX;

	private final org.apache.commons.math3.analysis.BivariateFunction partialDerivativeY;

	private final org.apache.commons.math3.analysis.BivariateFunction partialDerivativeXX;

	private final org.apache.commons.math3.analysis.BivariateFunction partialDerivativeYY;

	private final org.apache.commons.math3.analysis.BivariateFunction partialDerivativeXY;

	public BicubicSplineFunction(double[] coeff) {
		this(coeff, false);
	}

	public BicubicSplineFunction(double[] coeff ,boolean initializeDerivatives) {
		a = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
		for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; i++) {
			for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; j++) {
				a[i][j] = coeff[((i * (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N)) + j)];
			}
		}
		if (initializeDerivatives) {
			final double[][] aX = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
			final double[][] aY = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
			final double[][] aXX = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
			final double[][] aYY = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
			final double[][] aXY = new double[org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N][org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N];
			for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; i++) {
				for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; j++) {
					final double c = a[i][j];
					aX[i][j] = i * c;
					aY[i][j] = j * c;
					aXX[i][j] = (i - 1) * (aX[i][j]);
					aYY[i][j] = (j - 1) * (aY[i][j]);
					aXY[i][j] = j * (aX[i][j]);
				}
			}
			partialDerivativeX = new org.apache.commons.math3.analysis.BivariateFunction() {
				public double value(double x, double y) {
					final double x2 = x * x;
					final double[] pX = new double[]{ 0 , 1 , x , x2 };
					final double y2 = y * y;
					final double y3 = y2 * y;
					final double[] pY = new double[]{ 1 , y , y2 , y3 };
					return apply(pX, pY, aX);
				}
			};
			partialDerivativeY = new org.apache.commons.math3.analysis.BivariateFunction() {
				public double value(double x, double y) {
					final double x2 = x * x;
					final double x3 = x2 * x;
					final double[] pX = new double[]{ 1 , x , x2 , x3 };
					final double y2 = y * y;
					final double[] pY = new double[]{ 0 , 1 , y , y2 };
					return apply(pX, pY, aY);
				}
			};
			partialDerivativeXX = new org.apache.commons.math3.analysis.BivariateFunction() {
				public double value(double x, double y) {
					final double[] pX = new double[]{ 0 , 0 , 1 , x };
					final double y2 = y * y;
					final double y3 = y2 * y;
					final double[] pY = new double[]{ 1 , y , y2 , y3 };
					return apply(pX, pY, aXX);
				}
			};
			partialDerivativeYY = new org.apache.commons.math3.analysis.BivariateFunction() {
				public double value(double x, double y) {
					final double x2 = x * x;
					final double x3 = x2 * x;
					final double[] pX = new double[]{ 1 , x , x2 , x3 };
					final double[] pY = new double[]{ 0 , 0 , 1 , y };
					return apply(pX, pY, aYY);
				}
			};
			partialDerivativeXY = new org.apache.commons.math3.analysis.BivariateFunction() {
				public double value(double x, double y) {
					final double x2 = x * x;
					final double[] pX = new double[]{ 0 , 1 , x , x2 };
					final double y2 = y * y;
					final double[] pY = new double[]{ 0 , 1 , y , y2 };
					return apply(pX, pY, aXY);
				}
			};
		} else {
			partialDerivativeX = null;
			partialDerivativeY = null;
			partialDerivativeXX = null;
			partialDerivativeYY = null;
			partialDerivativeXY = null;
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
		for (int i = 0 ; i < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; i++) {
			for (int j = 0 ; j < (org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.N) ; j++) {
				result += ((coeff[i][j]) * (pX[i])) * (pY[j]);
			}
		}
		return result;
	}

	public org.apache.commons.math3.analysis.BivariateFunction partialDerivativeX() {
		return partialDerivativeX;
	}

	public org.apache.commons.math3.analysis.BivariateFunction partialDerivativeY() {
		return partialDerivativeY;
	}

	public org.apache.commons.math3.analysis.BivariateFunction partialDerivativeXX() {
		return partialDerivativeXX;
	}

	public org.apache.commons.math3.analysis.BivariateFunction partialDerivativeYY() {
		return partialDerivativeYY;
	}

	public org.apache.commons.math3.analysis.BivariateFunction partialDerivativeXY() {
		return partialDerivativeXY;
	}
}

