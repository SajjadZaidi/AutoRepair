package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;


public class SimplexOptimizer extends org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer {
	private org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex simplex;

	public SimplexOptimizer(org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker) {
		super(checker);
	}

	public SimplexOptimizer(double rel ,double abs) {
		this(new org.apache.commons.math3.optim.SimpleValueChecker(rel , abs));
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) {
		return super.optimize(optData);
	}

	@java.lang.Override
	protected org.apache.commons.math3.optim.PointValuePair doOptimize() {
		checkParameters();
		final org.apache.commons.math3.analysis.MultivariateFunction evalFunc = new org.apache.commons.math3.analysis.MultivariateFunction() {
			public double value(double[] point) {
				return computeObjectiveValue(point);
			}
		};
		final boolean isMinim = (getGoalType()) == (org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE);
		final java.util.Comparator<org.apache.commons.math3.optim.PointValuePair> comparator = new java.util.Comparator<org.apache.commons.math3.optim.PointValuePair>() {
			public int compare(final org.apache.commons.math3.optim.PointValuePair o1, final org.apache.commons.math3.optim.PointValuePair o2) {
				final double v1 = o1.getValue();
				final double v2 = o2.getValue();
				return isMinim ? java.lang.Double.compare(v1, v2) : java.lang.Double.compare(v2, v1);
			}
		};
		simplex.build(getStartPoint());
		simplex.evaluate(evalFunc, comparator);
		org.apache.commons.math3.optim.PointValuePair[] previous = null;
		int iteration = 0;
		final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker = getConvergenceChecker();
		while (true) {
			if ((getIterations()) > 0) {
				boolean converged = true;
				for (int i = 0 ; i < (simplex.getSize()) ; i++) {
					org.apache.commons.math3.optim.PointValuePair prev = previous[i];
					converged = converged && (checker.converged(iteration, prev, simplex.getPoint(i)));
				}
				if (converged) {
					return simplex.getPoint(0);
				} 
			} 
			previous = simplex.getPoints();
			simplex.iterate(evalFunc, comparator);
			incrementIterationCount();
		}
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		for (org.apache.commons.math3.optim.OptimizationData data : optData) {
			if (data instanceof org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex) {
				simplex = ((org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex)(data));
				break;
			} 
		}
	}

	private void checkParameters() {
		if ((simplex) == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if (((getLowerBound()) != null) || ((getUpperBound()) != null)) {
			throw new org.apache.commons.math3.exception.MathUnsupportedOperationException(org.apache.commons.math3.exception.util.LocalizedFormats.CONSTRAINT);
		} 
	}
}

