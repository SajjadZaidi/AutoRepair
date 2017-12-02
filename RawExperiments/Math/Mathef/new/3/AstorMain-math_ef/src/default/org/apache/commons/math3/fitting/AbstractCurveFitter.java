package org.apache.commons.math3.fitting;


public abstract class AbstractCurveFitter {
	public double[] fit(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> points) {
		return getOptimizer().optimize(getProblem(points)).getPoint().toArray();
	}

	protected org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer getOptimizer() {
		return new org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer();
	}

	protected abstract org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem getProblem(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> points);

	protected static class TheoreticalValuesFunction {
		private final org.apache.commons.math3.analysis.ParametricUnivariateFunction f;

		private final double[] points;

		public TheoreticalValuesFunction(final org.apache.commons.math3.analysis.ParametricUnivariateFunction f ,final java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> observations) {
			this.f = f;
			final int len = observations.size();
			this.points = new double[len];
			int i = 0;
			for (org.apache.commons.math3.fitting.WeightedObservedPoint obs : observations) {
				org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction.this.points[(i++)] = obs.getX();
			}
		}

		public org.apache.commons.math3.analysis.MultivariateVectorFunction getModelFunction() {
			return new org.apache.commons.math3.analysis.MultivariateVectorFunction() {
				public double[] value(double[] p) {
					final int len = points.length;
					final double[] values = new double[len];
					for (int i = 0 ; i < len ; i++) {
						values[i] = f.value(points[i], p);
					}
					return values;
				}
			};
		}

		public org.apache.commons.math3.analysis.MultivariateMatrixFunction getModelFunctionJacobian() {
			return new org.apache.commons.math3.analysis.MultivariateMatrixFunction() {
				public double[][] value(double[] p) {
					final int len = points.length;
					final double[][] jacobian = new double[len][];
					for (int i = 0 ; i < len ; i++) {
						jacobian[i] = f.gradient(points[i], p);
					}
					return jacobian;
				}
			};
		}
	}
}

