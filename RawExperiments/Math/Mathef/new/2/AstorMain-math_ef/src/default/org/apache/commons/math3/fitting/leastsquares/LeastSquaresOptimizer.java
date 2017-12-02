package org.apache.commons.math3.fitting.leastsquares;


public interface LeastSquaresOptimizer {
	org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem leastSquaresProblem);

	interface Optimum extends org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation {
		int getEvaluations();

		int getIterations();
	}
}

