package org.apache.commons.math3.optim.nonlinear.scalar.gradient;


public class NonLinearConjugateGradientOptimizer extends org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer {
	private final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula;

	private final org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner preconditioner;

	private final org.apache.commons.math3.optim.nonlinear.scalar.LineSearch line;

	public static enum Formula {
FLETCHER_REEVES, POLAK_RIBIERE;	}

	@java.lang.Deprecated
	public static class BracketingStep implements org.apache.commons.math3.optim.OptimizationData {
		private final double initialStep;

		public BracketingStep(double step) {
			initialStep = step;
		}

		public double getBracketingStep() {
			return initialStep;
		}
	}

	public NonLinearConjugateGradientOptimizer(final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker) {
		this(updateFormula, checker, 1.0E-8, 1.0E-8, 1.0E-8, new org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.IdentityPreconditioner());
	}

	@java.lang.Deprecated
	public NonLinearConjugateGradientOptimizer(final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker ,final org.apache.commons.math3.analysis.solvers.UnivariateSolver lineSearchSolver) {
		this(updateFormula, checker, lineSearchSolver, new org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.IdentityPreconditioner());
	}

	public NonLinearConjugateGradientOptimizer(final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker ,double relativeTolerance ,double absoluteTolerance ,double initialBracketingRange) {
		this(updateFormula, checker, relativeTolerance, absoluteTolerance, initialBracketingRange, new org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.IdentityPreconditioner());
	}

	@java.lang.Deprecated
	public NonLinearConjugateGradientOptimizer(final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker ,final org.apache.commons.math3.analysis.solvers.UnivariateSolver lineSearchSolver ,final org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner preconditioner) {
		this(updateFormula, checker, lineSearchSolver.getRelativeAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), preconditioner);
	}

	public NonLinearConjugateGradientOptimizer(final org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula updateFormula ,org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker ,double relativeTolerance ,double absoluteTolerance ,double initialBracketingRange ,final org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner preconditioner) {
		super(checker);
		this.updateFormula = updateFormula;
		this.preconditioner = preconditioner;
		line = new org.apache.commons.math3.optim.nonlinear.scalar.LineSearch(org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.this , relativeTolerance , absoluteTolerance , initialBracketingRange);
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) throws org.apache.commons.math3.exception.TooManyEvaluationsException {
		return super.optimize(optData);
	}

	@java.lang.Override
	protected org.apache.commons.math3.optim.PointValuePair doOptimize() {
		final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker = getConvergenceChecker();
		final double[] point = getStartPoint();
		final org.apache.commons.math3.optim.nonlinear.scalar.GoalType goal = getGoalType();
		final int n = point.length;
		double[] r = computeObjectiveGradient(point);
		if (goal == (org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE)) {
			for (int i = 0 ; i < n ; i++) {
				r[i] = -(r[i]);
			}
		} 
		double[] steepestDescent = preconditioner.precondition(point, r);
		double[] searchDirection = steepestDescent.clone();
		double delta = 0;
		for (int i = 0 ; i < n ; ++i) {
			delta += (r[i]) * (searchDirection[i]);
		}
		org.apache.commons.math3.optim.PointValuePair current = null;
		while (true) {
			incrementIterationCount();
			final double objective = computeObjectiveValue(point);
			org.apache.commons.math3.optim.PointValuePair previous = current;
			current = new org.apache.commons.math3.optim.PointValuePair(point , objective);
			if ((previous != null) && (checker.converged(getIterations(), previous, current))) {
				return current;
			} 
			final double step = line.search(point, searchDirection).getPoint();
			for (int i = 0 ; i < (point.length) ; ++i) {
				point[i] += step * (searchDirection[i]);
			}
			r = computeObjectiveGradient(point);
			if (goal == (org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE)) {
				for (int i = 0 ; i < n ; ++i) {
					r[i] = -(r[i]);
				}
			} 
			final double deltaOld = delta;
			final double[] newSteepestDescent = preconditioner.precondition(point, r);
			delta = 0;
			for (int i = 0 ; i < n ; ++i) {
				delta += (r[i]) * (newSteepestDescent[i]);
			}
			final double beta;
			switch (updateFormula) {
				case FLETCHER_REEVES :
					beta = delta / deltaOld;
					break;
				case POLAK_RIBIERE :
					double deltaMid = 0;
					for (int i = 0 ; i < (r.length) ; ++i) {
						deltaMid += (r[i]) * (steepestDescent[i]);
					}
					beta = (delta - deltaMid) / deltaOld;
					break;
				default :
					throw new org.apache.commons.math3.exception.MathInternalError();
			}
			steepestDescent = newSteepestDescent;
			if ((((getIterations()) % n) == 0) || (beta < 0)) {
				searchDirection = steepestDescent.clone();
			} else {
				for (int i = 0 ; i < n ; ++i) {
					searchDirection[i] = (steepestDescent[i]) + (beta * (searchDirection[i]));
				}
			}
		}
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		checkParameters();
	}

	public static class IdentityPreconditioner implements org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner {
		public double[] precondition(double[] variables, double[] r) {
			return r.clone();
		}
	}

	private void checkParameters() {
		if (((getLowerBound()) != null) || ((getUpperBound()) != null)) {
			throw new org.apache.commons.math3.exception.MathUnsupportedOperationException(org.apache.commons.math3.exception.util.LocalizedFormats.CONSTRAINT);
		} 
	}
}

