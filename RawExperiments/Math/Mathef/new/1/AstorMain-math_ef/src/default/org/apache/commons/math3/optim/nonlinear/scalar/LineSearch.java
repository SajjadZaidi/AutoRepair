package org.apache.commons.math3.optim.nonlinear.scalar;


public class LineSearch {
	private static final double REL_TOL_UNUSED = 1.0E-15;

	private static final double ABS_TOL_UNUSED = java.lang.Double.MIN_VALUE;

	private final org.apache.commons.math3.optim.univariate.UnivariateOptimizer lineOptimizer;

	private final org.apache.commons.math3.optim.univariate.BracketFinder bracket = new org.apache.commons.math3.optim.univariate.BracketFinder();

	private final double initialBracketingRange;

	private final org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer mainOptimizer;

	public LineSearch(org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer optimizer ,double relativeTolerance ,double absoluteTolerance ,double initialBracketingRange) {
		mainOptimizer = optimizer;
		lineOptimizer = new org.apache.commons.math3.optim.univariate.BrentOptimizer(org.apache.commons.math3.optim.nonlinear.scalar.LineSearch.REL_TOL_UNUSED , org.apache.commons.math3.optim.nonlinear.scalar.LineSearch.ABS_TOL_UNUSED , new org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker(relativeTolerance , absoluteTolerance));
		this.initialBracketingRange = initialBracketingRange;
	}

	public org.apache.commons.math3.optim.univariate.UnivariatePointValuePair search(final double[] startPoint, final double[] direction) {
		final int n = startPoint.length;
		final org.apache.commons.math3.analysis.UnivariateFunction f = new org.apache.commons.math3.analysis.UnivariateFunction() {
			public double value(double alpha) {
				final double[] x = new double[n];
				for (int i = 0 ; i < n ; i++) {
					x[i] = (startPoint[i]) + (alpha * (direction[i]));
				}
				final double obj = mainOptimizer.computeObjectiveValue(x);
				return obj;
			}
		};
		final org.apache.commons.math3.optim.nonlinear.scalar.GoalType goal = mainOptimizer.getGoalType();
		bracket.search(f, goal, 0, initialBracketingRange);
		return lineOptimizer.optimize(new org.apache.commons.math3.optim.MaxEval(java.lang.Integer.MAX_VALUE), new org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction(f), goal, new org.apache.commons.math3.optim.univariate.SearchInterval(bracket.getLo() , bracket.getHi() , bracket.getMid()));
	}
}

