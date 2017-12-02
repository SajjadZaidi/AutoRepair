package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;


public class PowellOptimizer extends org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer {
	private static final double MIN_RELATIVE_TOLERANCE = 2 * (org.apache.commons.math3.util.FastMath.ulp(1.0));

	private final double relativeThreshold;

	private final double absoluteThreshold;

	private final org.apache.commons.math3.optim.nonlinear.scalar.LineSearch line;

	public PowellOptimizer(double rel ,double abs ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker) {
		this(rel, abs, org.apache.commons.math3.util.FastMath.sqrt(rel), org.apache.commons.math3.util.FastMath.sqrt(abs), checker);
	}

	public PowellOptimizer(double rel ,double abs ,double lineRel ,double lineAbs ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker) {
		super(checker);
		if (rel < (org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.MIN_RELATIVE_TOLERANCE)) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(rel , org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.MIN_RELATIVE_TOLERANCE , true);
		} 
		if (abs <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(abs);
		} 
		relativeThreshold = rel;
		absoluteThreshold = abs;
		line = new org.apache.commons.math3.optim.nonlinear.scalar.LineSearch(org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.this , lineRel , lineAbs , 1.0);
	}

	public PowellOptimizer(double rel ,double abs) {
		this(rel, abs, null);
	}

	public PowellOptimizer(double rel ,double abs ,double lineRel ,double lineAbs) {
		this(rel, abs, lineRel, lineAbs, null);
	}

	@java.lang.Override
	protected org.apache.commons.math3.optim.PointValuePair doOptimize() {
		checkParameters();
		final org.apache.commons.math3.optim.nonlinear.scalar.GoalType goal = getGoalType();
		final double[] guess = getStartPoint();
		final int n = guess.length;
		final double[][] direc = new double[n][n];
		for (int i = 0 ; i < n ; i++) {
			direc[i][i] = 1;
		}
		final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker = getConvergenceChecker();
		double[] x = guess;
		double fVal = computeObjectiveValue(x);
		double[] x1 = x.clone();
		while (true) {
			incrementIterationCount();
			double fX = fVal;
			double fX2 = 0;
			double delta = 0;
			int bigInd = 0;
			double alphaMin = 0;
			for (int i = 0 ; i < n ; i++) {
				final double[] d = org.apache.commons.math3.util.MathArrays.copyOf(direc[i]);
				fX2 = fVal;
				final org.apache.commons.math3.optim.univariate.UnivariatePointValuePair optimum = line.search(x, d);
				fVal = optimum.getValue();
				alphaMin = optimum.getPoint();
				final double[][] result = newPointAndDirection(x, d, alphaMin);
				x = result[0];
				if ((fX2 - fVal) > delta) {
					delta = fX2 - fVal;
					bigInd = i;
				} 
			}
			boolean stop = (2 * (fX - fVal)) <= (((relativeThreshold) * ((org.apache.commons.math3.util.FastMath.abs(fX)) + (org.apache.commons.math3.util.FastMath.abs(fVal)))) + (absoluteThreshold));
			final org.apache.commons.math3.optim.PointValuePair previous = new org.apache.commons.math3.optim.PointValuePair(x1 , fX);
			final org.apache.commons.math3.optim.PointValuePair current = new org.apache.commons.math3.optim.PointValuePair(x , fVal);
			if ((!stop) && (checker != null)) {
				stop = checker.converged(getIterations(), previous, current);
			} 
			if (stop) {
				if (goal == (org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE)) {
					return fVal < fX ? current : previous;
				} else {
					return fVal > fX ? current : previous;
				}
			} 
			final double[] d = new double[n];
			final double[] x2 = new double[n];
			for (int i = 0 ; i < n ; i++) {
				d[i] = (x[i]) - (x1[i]);
				x2[i] = (2 * (x[i])) - (x1[i]);
			}
			x1 = x.clone();
			fX2 = computeObjectiveValue(x2);
			if (fX > fX2) {
				double t = 2 * ((fX + fX2) - (2 * fVal));
				double temp = (fX - fVal) - delta;
				t *= temp * temp;
				temp = fX - fX2;
				t -= (delta * temp) * temp;
				if (t < 0.0) {
					final org.apache.commons.math3.optim.univariate.UnivariatePointValuePair optimum = line.search(x, d);
					fVal = optimum.getValue();
					alphaMin = optimum.getPoint();
					final double[][] result = newPointAndDirection(x, d, alphaMin);
					x = result[0];
					final int lastInd = n - 1;
					direc[bigInd] = direc[lastInd];
					direc[lastInd] = result[1];
				} 
			} 
		}
	}

	private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
		final int n = p.length;
		final double[] nP = new double[n];
		final double[] nD = new double[n];
		for (int i = 0 ; i < n ; i++) {
			nD[i] = (d[i]) * optimum;
			nP[i] = (p[i]) + (nD[i]);
		}
		final double[][] result = new double[2][];
		result[0] = nP;
		result[1] = nD;
		return result;
	}

	private void checkParameters() {
		if (((getLowerBound()) != null) || ((getUpperBound()) != null)) {
			throw new org.apache.commons.math3.exception.MathUnsupportedOperationException(org.apache.commons.math3.exception.util.LocalizedFormats.CONSTRAINT);
		} 
	}
}

