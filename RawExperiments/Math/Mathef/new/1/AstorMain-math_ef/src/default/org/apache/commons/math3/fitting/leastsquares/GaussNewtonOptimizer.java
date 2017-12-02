package org.apache.commons.math3.fitting.leastsquares;


public class GaussNewtonOptimizer implements org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer {
	public static enum Decomposition {
LU {
			@java.lang.Override
			protected org.apache.commons.math3.linear.RealVector solve(final org.apache.commons.math3.linear.RealMatrix jacobian, final org.apache.commons.math3.linear.RealVector residuals) {
				try {
					final org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealMatrix, org.apache.commons.math3.linear.RealVector> normalEquation = org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix(jacobian, residuals);
					final org.apache.commons.math3.linear.RealMatrix normal = normalEquation.getFirst();
					final org.apache.commons.math3.linear.RealVector jTr = normalEquation.getSecond();
					return new org.apache.commons.math3.linear.LUDecomposition(normal , org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve(jTr);
				} catch (org.apache.commons.math3.linear.SingularMatrixException e) {
					throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM , e);
				}
			}
		}, QR {
			@java.lang.Override
			protected org.apache.commons.math3.linear.RealVector solve(final org.apache.commons.math3.linear.RealMatrix jacobian, final org.apache.commons.math3.linear.RealVector residuals) {
				try {
					return new org.apache.commons.math3.linear.QRDecomposition(jacobian , org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve(residuals);
				} catch (org.apache.commons.math3.linear.SingularMatrixException e) {
					throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM , e);
				}
			}
		}, CHOLESKY {
			@java.lang.Override
			protected org.apache.commons.math3.linear.RealVector solve(final org.apache.commons.math3.linear.RealMatrix jacobian, final org.apache.commons.math3.linear.RealVector residuals) {
				try {
					final org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealMatrix, org.apache.commons.math3.linear.RealVector> normalEquation = org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix(jacobian, residuals);
					final org.apache.commons.math3.linear.RealMatrix normal = normalEquation.getFirst();
					final org.apache.commons.math3.linear.RealVector jTr = normalEquation.getSecond();
					return new org.apache.commons.math3.linear.CholeskyDecomposition(normal , org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.SINGULARITY_THRESHOLD , org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve(jTr);
				} catch (org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException e) {
					throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM , e);
				}
			}
		}, SVD {
			@java.lang.Override
			protected org.apache.commons.math3.linear.RealVector solve(final org.apache.commons.math3.linear.RealMatrix jacobian, final org.apache.commons.math3.linear.RealVector residuals) {
				return new org.apache.commons.math3.linear.SingularValueDecomposition(jacobian).getSolver().solve(residuals);
			}
		};
		protected abstract org.apache.commons.math3.linear.RealVector solve(org.apache.commons.math3.linear.RealMatrix jacobian, org.apache.commons.math3.linear.RealVector residuals);
	}

	private static final double SINGULARITY_THRESHOLD = 1.0E-11;

	private final org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition decomposition;

	public GaussNewtonOptimizer() {
		this(org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition.QR);
	}

	public GaussNewtonOptimizer(final org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition decomposition) {
		this.decomposition = decomposition;
	}

	public org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition getDecomposition() {
		return org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.this.decomposition;
	}

	public org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer withDecomposition(final org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition newDecomposition) {
		return new org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer(newDecomposition);
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum optimize(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem lsp) {
		final org.apache.commons.math3.util.Incrementor evaluationCounter = lsp.getEvaluationCounter();
		final org.apache.commons.math3.util.Incrementor iterationCounter = lsp.getIterationCounter();
		final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker = lsp.getConvergenceChecker();
		if (checker == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		org.apache.commons.math3.linear.RealVector currentPoint = lsp.getStart();
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation current = null;
		while (true) {
			iterationCounter.incrementCount();
			org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation previous = current;
			evaluationCounter.incrementCount();
			current = lsp.evaluate(currentPoint);
			final org.apache.commons.math3.linear.RealVector currentResiduals = current.getResiduals();
			final org.apache.commons.math3.linear.RealMatrix weightedJacobian = current.getJacobian();
			currentPoint = current.getPoint();
			if (previous != null) {
				if (checker.converged(iterationCounter.getCount(), previous, current)) {
					return new org.apache.commons.math3.fitting.leastsquares.OptimumImpl(current , evaluationCounter.getCount() , iterationCounter.getCount());
				} 
			} 
			final org.apache.commons.math3.linear.RealVector dX = org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.this.decomposition.solve(weightedJacobian, currentResiduals);
			currentPoint = currentPoint.add(dX);
		}
	}

	@java.lang.Override
	public java.lang.String toString() {
		return (("GaussNewtonOptimizer{" + "decomposition=") + (decomposition)) + '}';
	}

	private static org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealMatrix, org.apache.commons.math3.linear.RealVector> computeNormalMatrix(final org.apache.commons.math3.linear.RealMatrix jacobian, final org.apache.commons.math3.linear.RealVector residuals) {
		final int nR = jacobian.getRowDimension();
		final int nC = jacobian.getColumnDimension();
		final org.apache.commons.math3.linear.RealMatrix normal = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(nC, nC);
		final org.apache.commons.math3.linear.RealVector jTr = new org.apache.commons.math3.linear.ArrayRealVector(nC);
		for (int i = 0 ; i < nR ; ++i) {
			for (int j = 0 ; j < nC ; j++) {
				jTr.setEntry(j, ((jTr.getEntry(j)) + ((residuals.getEntry(i)) * (jacobian.getEntry(i, j)))));
			}
			for (int k = 0 ; k < nC ; ++k) {
				for (int l = k ; l < nC ; ++l) {
					normal.setEntry(k, l, ((normal.getEntry(k, l)) + ((jacobian.getEntry(i, k)) * (jacobian.getEntry(i, l)))));
				}
			}
		}
		for (int i = 0 ; i < nC ; i++) {
			for (int j = 0 ; j < i ; j++) {
				normal.setEntry(i, j, normal.getEntry(j, i));
			}
		}
		return new org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealMatrix, org.apache.commons.math3.linear.RealVector>(normal , jTr);
	}
}

