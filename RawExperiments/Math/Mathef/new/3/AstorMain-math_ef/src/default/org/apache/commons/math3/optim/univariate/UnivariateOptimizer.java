package org.apache.commons.math3.optim.univariate;


public abstract class UnivariateOptimizer extends org.apache.commons.math3.optim.BaseOptimizer<org.apache.commons.math3.optim.univariate.UnivariatePointValuePair> {
	private org.apache.commons.math3.analysis.UnivariateFunction function;

	private org.apache.commons.math3.optim.nonlinear.scalar.GoalType goal;

	private double start;

	private double min;

	private double max;

	protected UnivariateOptimizer(org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.univariate.UnivariatePointValuePair> checker) {
		super(checker);
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.univariate.UnivariatePointValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) throws org.apache.commons.math3.exception.TooManyEvaluationsException {
		return super.optimize(optData);
	}

	public org.apache.commons.math3.optim.nonlinear.scalar.GoalType getGoalType() {
		return goal;
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		for (org.apache.commons.math3.optim.OptimizationData data : optData) {
			if (data instanceof org.apache.commons.math3.optim.univariate.SearchInterval) {
				final org.apache.commons.math3.optim.univariate.SearchInterval interval = ((org.apache.commons.math3.optim.univariate.SearchInterval)(data));
				min = interval.getMin();
				max = interval.getMax();
				start = interval.getStartValue();
				continue;
			} 
			if (data instanceof org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction) {
				function = ((org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction)(data)).getObjectiveFunction();
				continue;
			} 
			if (data instanceof org.apache.commons.math3.optim.nonlinear.scalar.GoalType) {
				goal = ((org.apache.commons.math3.optim.nonlinear.scalar.GoalType)(data));
				continue;
			} 
		}
	}

	public double getStartValue() {
		return start;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	protected double computeObjectiveValue(double x) {
		super.incrementEvaluationCount();
		return function.value(x);
	}
}

