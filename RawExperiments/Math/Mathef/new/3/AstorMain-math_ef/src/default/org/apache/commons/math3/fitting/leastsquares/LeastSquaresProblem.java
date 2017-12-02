package org.apache.commons.math3.fitting.leastsquares;


public interface LeastSquaresProblem extends org.apache.commons.math3.optim.OptimizationProblem<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> {
	org.apache.commons.math3.linear.RealVector getStart();

	int getObservationSize();

	int getParameterSize();

	org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation evaluate(org.apache.commons.math3.linear.RealVector point);

	public interface Evaluation {
		org.apache.commons.math3.linear.RealMatrix getCovariances(double threshold);

		org.apache.commons.math3.linear.RealVector getSigma(double covarianceSingularityThreshold);

		double getRMS();

		org.apache.commons.math3.linear.RealMatrix getJacobian();

		double getCost();

		org.apache.commons.math3.linear.RealVector getResiduals();

		org.apache.commons.math3.linear.RealVector getPoint();
	}
}

