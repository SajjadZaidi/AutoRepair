package org.apache.commons.math3.fitting.leastsquares;


public class LeastSquaresFactory {
	private LeastSquaresFactory() {
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem create(final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model, final org.apache.commons.math3.linear.RealVector observed, final org.apache.commons.math3.linear.RealVector start, final org.apache.commons.math3.linear.RealMatrix weight, final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker, final int maxEvaluations, final int maxIterations, final boolean lazyEvaluation, final org.apache.commons.math3.fitting.leastsquares.ParameterValidator paramValidator) {
		final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem p = new org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.LocalLeastSquaresProblem(model , observed , start , checker , maxEvaluations , maxIterations , lazyEvaluation , paramValidator);
		if (weight != null) {
			return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.weightMatrix(p, weight);
		} else {
			return p;
		}
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem create(final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model, final org.apache.commons.math3.linear.RealVector observed, final org.apache.commons.math3.linear.RealVector start, final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker, final int maxEvaluations, final int maxIterations) {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.create(model, observed, start, null, checker, maxEvaluations, maxIterations, false, null);
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem create(final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model, final org.apache.commons.math3.linear.RealVector observed, final org.apache.commons.math3.linear.RealVector start, final org.apache.commons.math3.linear.RealMatrix weight, final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker, final int maxEvaluations, final int maxIterations) {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.weightMatrix(org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.create(model, observed, start, checker, maxEvaluations, maxIterations), weight);
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem create(final org.apache.commons.math3.analysis.MultivariateVectorFunction model, final org.apache.commons.math3.analysis.MultivariateMatrixFunction jacobian, final double[] observed, final double[] start, final org.apache.commons.math3.linear.RealMatrix weight, final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker, final int maxEvaluations, final int maxIterations) {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.create(org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model(model, jacobian), new org.apache.commons.math3.linear.ArrayRealVector(observed , false), new org.apache.commons.math3.linear.ArrayRealVector(start , false), weight, checker, maxEvaluations, maxIterations);
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem weightMatrix(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem problem, final org.apache.commons.math3.linear.RealMatrix weights) {
		final org.apache.commons.math3.linear.RealMatrix weightSquareRoot = org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.squareRoot(weights);
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresAdapter(problem) {
			@java.lang.Override
			public org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation evaluate(final org.apache.commons.math3.linear.RealVector point) {
				return new org.apache.commons.math3.fitting.leastsquares.DenseWeightedEvaluation(super.evaluate(point) , weightSquareRoot);
			}
		};
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem weightDiagonal(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem problem, final org.apache.commons.math3.linear.RealVector weights) {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.weightMatrix(problem, new org.apache.commons.math3.linear.DiagonalMatrix(weights.toArray()));
	}

	public static org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem countEvaluations(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem problem, final org.apache.commons.math3.util.Incrementor counter) {
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresAdapter(problem) {
			public org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation evaluate(final org.apache.commons.math3.linear.RealVector point) {
				counter.incrementCount();
				return super.evaluate(point);
			}
		};
	}

	public static org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> evaluationChecker(final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointVectorValuePair> checker) {
		return new org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation>() {
			public boolean converged(final int iteration, final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation previous, final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation current) {
				return checker.converged(iteration, new org.apache.commons.math3.optim.PointVectorValuePair(previous.getPoint().toArray() , previous.getResiduals().toArray() , false), new org.apache.commons.math3.optim.PointVectorValuePair(current.getPoint().toArray() , current.getResiduals().toArray() , false));
			}
		};
	}

	private static org.apache.commons.math3.linear.RealMatrix squareRoot(final org.apache.commons.math3.linear.RealMatrix m) {
		if (m instanceof org.apache.commons.math3.linear.DiagonalMatrix) {
			final int dim = m.getRowDimension();
			final org.apache.commons.math3.linear.RealMatrix sqrtM = new org.apache.commons.math3.linear.DiagonalMatrix(dim);
			for (int i = 0 ; i < dim ; i++) {
				sqrtM.setEntry(i, i, org.apache.commons.math3.util.FastMath.sqrt(m.getEntry(i, i)));
			}
			return sqrtM;
		} else {
			final org.apache.commons.math3.linear.EigenDecomposition dec = new org.apache.commons.math3.linear.EigenDecomposition(m);
			return dec.getSquareRoot();
		}
	}

	public static org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model(final org.apache.commons.math3.analysis.MultivariateVectorFunction value, final org.apache.commons.math3.analysis.MultivariateMatrixFunction jacobian) {
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.LocalValueAndJacobianFunction(value , jacobian);
	}

	private static class LocalValueAndJacobianFunction implements org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction {
		private final org.apache.commons.math3.analysis.MultivariateVectorFunction value;

		private final org.apache.commons.math3.analysis.MultivariateMatrixFunction jacobian;

		LocalValueAndJacobianFunction(final org.apache.commons.math3.analysis.MultivariateVectorFunction value ,final org.apache.commons.math3.analysis.MultivariateMatrixFunction jacobian) {
			this.value = value;
			this.jacobian = jacobian;
		}

		public org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealVector, org.apache.commons.math3.linear.RealMatrix> value(final org.apache.commons.math3.linear.RealVector point) {
			final double[] p = point.toArray();
			return new org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealVector, org.apache.commons.math3.linear.RealMatrix>(computeValue(p) , computeJacobian(p));
		}

		public org.apache.commons.math3.linear.RealVector computeValue(final double[] params) {
			return new org.apache.commons.math3.linear.ArrayRealVector(value.value(params) , false);
		}

		public org.apache.commons.math3.linear.RealMatrix computeJacobian(final double[] params) {
			return new org.apache.commons.math3.linear.Array2DRowRealMatrix(jacobian.value(params) , false);
		}
	}

	private static class LocalLeastSquaresProblem extends org.apache.commons.math3.optim.AbstractOptimizationProblem<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> implements org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem {
		private final org.apache.commons.math3.linear.RealVector target;

		private final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model;

		private final org.apache.commons.math3.linear.RealVector start;

		private final boolean lazyEvaluation;

		private final org.apache.commons.math3.fitting.leastsquares.ParameterValidator paramValidator;

		LocalLeastSquaresProblem(final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model ,final org.apache.commons.math3.linear.RealVector target ,final org.apache.commons.math3.linear.RealVector start ,final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker ,final int maxEvaluations ,final int maxIterations ,final boolean lazyEvaluation ,final org.apache.commons.math3.fitting.leastsquares.ParameterValidator paramValidator) {
			super(maxEvaluations, maxIterations, checker);
			this.target = target;
			this.model = model;
			this.start = start;
			this.lazyEvaluation = lazyEvaluation;
			this.paramValidator = paramValidator;
			if (lazyEvaluation && (!(model instanceof org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction))) {
				throw new org.apache.commons.math3.exception.MathIllegalStateException(org.apache.commons.math3.exception.util.LocalizedFormats.INVALID_IMPLEMENTATION , model.getClass().getName());
			} 
		}

		public int getObservationSize() {
			return target.getDimension();
		}

		public int getParameterSize() {
			return start.getDimension();
		}

		public org.apache.commons.math3.linear.RealVector getStart() {
			return (start) == null ? null : start.copy();
		}

		public org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation evaluate(final org.apache.commons.math3.linear.RealVector point) {
			final org.apache.commons.math3.linear.RealVector p = (paramValidator) == null ? point.copy() : paramValidator.validate(point.copy());
			if (lazyEvaluation) {
				return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.LocalLeastSquaresProblem.LazyUnweightedEvaluation(((org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction)(model)) , target , p);
			} else {
				final org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealVector, org.apache.commons.math3.linear.RealMatrix> value = model.value(p);
				return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.LocalLeastSquaresProblem.UnweightedEvaluation(value.getFirst() , value.getSecond() , target , p);
			}
		}

		private static class UnweightedEvaluation extends org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation {
			private final org.apache.commons.math3.linear.RealVector point;

			private final org.apache.commons.math3.linear.RealMatrix jacobian;

			private final org.apache.commons.math3.linear.RealVector residuals;

			private UnweightedEvaluation(final org.apache.commons.math3.linear.RealVector values ,final org.apache.commons.math3.linear.RealMatrix jacobian ,final org.apache.commons.math3.linear.RealVector target ,final org.apache.commons.math3.linear.RealVector point) {
				super(target.getDimension());
				this.jacobian = jacobian;
				this.point = point;
				this.residuals = target.subtract(values);
			}

			public org.apache.commons.math3.linear.RealMatrix getJacobian() {
				return jacobian;
			}

			public org.apache.commons.math3.linear.RealVector getPoint() {
				return point;
			}

			public org.apache.commons.math3.linear.RealVector getResiduals() {
				return residuals;
			}
		}

		private static class LazyUnweightedEvaluation extends org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation {
			private final org.apache.commons.math3.linear.RealVector point;

			private final org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction model;

			private final org.apache.commons.math3.linear.RealVector target;

			private LazyUnweightedEvaluation(final org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction model ,final org.apache.commons.math3.linear.RealVector target ,final org.apache.commons.math3.linear.RealVector point) {
				super(target.getDimension());
				this.model = model;
				this.point = point;
				this.target = target;
			}

			public org.apache.commons.math3.linear.RealMatrix getJacobian() {
				return model.computeJacobian(point.toArray());
			}

			public org.apache.commons.math3.linear.RealVector getPoint() {
				return point;
			}

			public org.apache.commons.math3.linear.RealVector getResiduals() {
				return target.subtract(model.computeValue(point.toArray()));
			}
		}
	}
}

