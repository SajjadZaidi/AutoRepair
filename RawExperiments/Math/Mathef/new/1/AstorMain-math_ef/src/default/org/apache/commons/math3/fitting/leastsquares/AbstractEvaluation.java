package org.apache.commons.math3.fitting.leastsquares;


public abstract class AbstractEvaluation implements org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation {
	private final int observationSize;

	AbstractEvaluation(final int observationSize) {
		this.observationSize = observationSize;
	}

	public org.apache.commons.math3.linear.RealMatrix getCovariances(double threshold) {
		final org.apache.commons.math3.linear.RealMatrix j = org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.this.getJacobian();
		final org.apache.commons.math3.linear.RealMatrix jTj = j.transpose().multiply(j);
		final org.apache.commons.math3.linear.DecompositionSolver solver = new org.apache.commons.math3.linear.QRDecomposition(jTj , threshold).getSolver();
		return solver.getInverse();
	}

	public org.apache.commons.math3.linear.RealVector getSigma(double covarianceSingularityThreshold) {
		final org.apache.commons.math3.linear.RealMatrix cov = org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.this.getCovariances(covarianceSingularityThreshold);
		final int nC = cov.getColumnDimension();
		final org.apache.commons.math3.linear.RealVector sig = new org.apache.commons.math3.linear.ArrayRealVector(nC);
		for (int i = 0 ; i < nC ; ++i) {
			sig.setEntry(i, org.apache.commons.math3.util.FastMath.sqrt(cov.getEntry(i, i)));
		}
		return sig;
	}

	public double getRMS() {
		final double cost = org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.this.getCost();
		return org.apache.commons.math3.util.FastMath.sqrt(((cost * cost) / (org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.this.observationSize)));
	}

	public double getCost() {
		final org.apache.commons.math3.linear.ArrayRealVector r = new org.apache.commons.math3.linear.ArrayRealVector(org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.this.getResiduals());
		return org.apache.commons.math3.util.FastMath.sqrt(r.dotProduct(r));
	}
}

