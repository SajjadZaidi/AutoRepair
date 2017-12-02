package org.apache.commons.math3.analysis.interpolation;


public class AkimaSplineInterpolator implements org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator {
	private static final int MINIMUM_NUMBER_POINTS = 5;

	public org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction interpolate(double[] xvals, double[] yvals) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NonMonotonicSequenceException, org.apache.commons.math3.exception.NumberIsTooSmallException {
		if ((xvals == null) || (yvals == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if ((xvals.length) != (yvals.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xvals.length , yvals.length);
		} 
		if ((xvals.length) < (org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.MINIMUM_NUMBER_POINTS)) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_POINTS , xvals.length , org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.MINIMUM_NUMBER_POINTS , true);
		} 
		org.apache.commons.math3.util.MathArrays.checkOrder(xvals);
		final int numberOfDiffAndWeightElements = (xvals.length) - 1;
		final double[] differences = new double[numberOfDiffAndWeightElements];
		final double[] weights = new double[numberOfDiffAndWeightElements];
		for (int i = 0 ; i < (differences.length) ; i++) {
			differences[i] = ((yvals[(i + 1)]) - (yvals[i])) / ((xvals[(i + 1)]) - (xvals[i]));
		}
		for (int i = 1 ; i < (weights.length) ; i++) {
			weights[i] = org.apache.commons.math3.util.FastMath.abs(((differences[i]) - (differences[(i - 1)])));
		}
		final double[] firstDerivatives = new double[xvals.length];
		for (int i = 2 ; i < ((firstDerivatives.length) - 2) ; i++) {
			final double wP = weights[(i + 1)];
			final double wM = weights[(i - 1)];
			if ((org.apache.commons.math3.util.Precision.equals(wP, 0.0)) && (org.apache.commons.math3.util.Precision.equals(wM, 0.0))) {
				final double xv = xvals[i];
				final double xvP = xvals[(i + 1)];
				final double xvM = xvals[(i - 1)];
				firstDerivatives[i] = (((xvP - xv) * (differences[(i - 1)])) + ((xv - xvM) * (differences[i]))) / (xvP - xvM);
			} else {
				firstDerivatives[i] = ((wP * (differences[(i - 1)])) + (wM * (differences[i]))) / (wP + wM);
			}
		}
		firstDerivatives[0] = differentiateThreePoint(xvals, yvals, 0, 0, 1, 2);
		firstDerivatives[1] = differentiateThreePoint(xvals, yvals, 1, 0, 1, 2);
		firstDerivatives[((xvals.length) - 2)] = differentiateThreePoint(xvals, yvals, ((xvals.length) - 2), ((xvals.length) - 3), ((xvals.length) - 2), ((xvals.length) - 1));
		firstDerivatives[((xvals.length) - 1)] = differentiateThreePoint(xvals, yvals, ((xvals.length) - 1), ((xvals.length) - 3), ((xvals.length) - 2), ((xvals.length) - 1));
		return interpolateHermiteSorted(xvals, yvals, firstDerivatives);
	}

	private double differentiateThreePoint(double[] xvals, double[] yvals, int indexOfDifferentiation, int indexOfFirstSample, int indexOfSecondsample, int indexOfThirdSample) {
		final double x0 = yvals[indexOfFirstSample];
		final double x1 = yvals[indexOfSecondsample];
		final double x2 = yvals[indexOfThirdSample];
		final double t = (xvals[indexOfDifferentiation]) - (xvals[indexOfFirstSample]);
		final double t1 = (xvals[indexOfSecondsample]) - (xvals[indexOfFirstSample]);
		final double t2 = (xvals[indexOfThirdSample]) - (xvals[indexOfFirstSample]);
		final double a = ((x2 - x0) - ((t2 / t1) * (x1 - x0))) / ((t2 * t2) - (t1 * t2));
		final double b = ((x1 - x0) - ((a * t1) * t1)) / t1;
		return ((2 * a) * t) + b;
	}

	private org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction interpolateHermiteSorted(double[] xvals, double[] yvals, double[] firstDerivatives) {
		if ((xvals.length) != (yvals.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xvals.length , yvals.length);
		} 
		if ((xvals.length) != (firstDerivatives.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xvals.length , firstDerivatives.length);
		} 
		final int minimumLength = 2;
		if ((xvals.length) < minimumLength) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_POINTS , xvals.length , minimumLength , true);
		} 
		final int size = (xvals.length) - 1;
		final org.apache.commons.math3.analysis.polynomials.PolynomialFunction[] polynomials = new org.apache.commons.math3.analysis.polynomials.PolynomialFunction[size];
		final double[] coefficients = new double[4];
		for (int i = 0 ; i < (polynomials.length) ; i++) {
			final double w = (xvals[(i + 1)]) - (xvals[i]);
			final double w2 = w * w;
			final double yv = yvals[i];
			final double yvP = yvals[(i + 1)];
			final double fd = firstDerivatives[i];
			final double fdP = firstDerivatives[(i + 1)];
			coefficients[0] = yv;
			coefficients[1] = firstDerivatives[i];
			coefficients[2] = ((((3 * (yvP - yv)) / w) - (2 * fd)) - fdP) / w;
			coefficients[3] = ((((2 * (yv - yvP)) / w) + fd) + fdP) / w2;
			polynomials[i] = new org.apache.commons.math3.analysis.polynomials.PolynomialFunction(coefficients);
		}
		return new org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction(xvals , polynomials);
	}
}

