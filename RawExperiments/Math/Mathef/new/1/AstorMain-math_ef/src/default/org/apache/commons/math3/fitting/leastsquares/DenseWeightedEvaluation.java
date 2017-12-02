package org.apache.commons.math3.fitting.leastsquares;


class DenseWeightedEvaluation extends org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation {
	private final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation unweighted;

	private final org.apache.commons.math3.linear.RealMatrix weightSqrt;

	DenseWeightedEvaluation(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation unweighted ,final org.apache.commons.math3.linear.RealMatrix weightSqrt) {
		super(weightSqrt.getColumnDimension());
		this.unweighted = unweighted;
		this.weightSqrt = weightSqrt;
	}

	public org.apache.commons.math3.linear.RealMatrix getJacobian() {
		return weightSqrt.multiply(org.apache.commons.math3.fitting.leastsquares.DenseWeightedEvaluation.this.unweighted.getJacobian());
	}

	public org.apache.commons.math3.linear.RealVector getResiduals() {
		return org.apache.commons.math3.fitting.leastsquares.DenseWeightedEvaluation.this.weightSqrt.operate(org.apache.commons.math3.fitting.leastsquares.DenseWeightedEvaluation.this.unweighted.getResiduals());
	}

	public org.apache.commons.math3.linear.RealVector getPoint() {
		return unweighted.getPoint();
	}
}

