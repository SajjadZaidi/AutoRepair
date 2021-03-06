package org.apache.commons.math3.analysis.solvers;


public class BisectionSolver extends org.apache.commons.math3.analysis.solvers.AbstractUnivariateSolver {
	private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

	public BisectionSolver() {
		this(org.apache.commons.math3.analysis.solvers.BisectionSolver.DEFAULT_ABSOLUTE_ACCURACY);
	}

	public BisectionSolver(double absoluteAccuracy) {
		super(absoluteAccuracy);
	}

	public BisectionSolver(double relativeAccuracy ,double absoluteAccuracy) {
		super(relativeAccuracy, absoluteAccuracy);
	}

	@java.lang.Override
	protected double doSolve() throws org.apache.commons.math3.exception.TooManyEvaluationsException {
		double min = getMin();
		double max = getMax();
		verifyInterval(min, max);
		final double absoluteAccuracy = getAbsoluteAccuracy();
		double m;
		double fm;
		double fmin;
		while (true) {
			m = org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.midpoint(min, max);
			fmin = computeObjectiveValue(min);
			fm = computeObjectiveValue(m);
			if ((fm * fmin) > 0) {
				min = m;
			} else {
				max = m;
			}
			if ((org.apache.commons.math3.util.FastMath.abs((max - min))) <= absoluteAccuracy) {
				m = org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.midpoint(min, max);
				return m;
			} 
		}
	}
}

