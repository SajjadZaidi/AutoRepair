package org.apache.commons.math3.linear;


public class RRQRDecomposition extends org.apache.commons.math3.linear.QRDecomposition {
	private int[] p;

	private org.apache.commons.math3.linear.RealMatrix cachedP;

	public RRQRDecomposition(org.apache.commons.math3.linear.RealMatrix matrix) {
		this(matrix, 0.0);
	}

	public RRQRDecomposition(org.apache.commons.math3.linear.RealMatrix matrix ,double threshold) {
		super(matrix, threshold);
	}

	@java.lang.Override
	protected void decompose(double[][] qrt) {
		p = new int[qrt.length];
		for (int i = 0 ; i < (p.length) ; i++) {
			p[i] = i;
		}
		super.decompose(qrt);
	}

	@java.lang.Override
	protected void performHouseholderReflection(int minor, double[][] qrt) {
		double l2NormSquaredMax = 0;
		int l2NormSquaredMaxIndex = minor;
		for (int i = minor ; i < (qrt.length) ; i++) {
			double l2NormSquared = 0;
			for (int j = 0 ; j < (qrt[i].length) ; j++) {
				l2NormSquared += (qrt[i][j]) * (qrt[i][j]);
			}
			if (l2NormSquared > l2NormSquaredMax) {
				l2NormSquaredMax = l2NormSquared;
				l2NormSquaredMaxIndex = i;
			} 
		}
		if (l2NormSquaredMaxIndex != minor) {
			double[] tmp1 = qrt[minor];
			qrt[minor] = qrt[l2NormSquaredMaxIndex];
			qrt[l2NormSquaredMaxIndex] = tmp1;
			int tmp2 = p[minor];
			p[minor] = p[l2NormSquaredMaxIndex];
			p[l2NormSquaredMaxIndex] = tmp2;
		} 
		super.performHouseholderReflection(minor, qrt);
	}

	public org.apache.commons.math3.linear.RealMatrix getP() {
		if ((cachedP) == null) {
			int n = p.length;
			cachedP = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(n, n);
			for (int i = 0 ; i < n ; i++) {
				cachedP.setEntry(p[i], i, 1);
			}
		} 
		return cachedP;
	}

	public int getRank(final double dropThreshold) {
		org.apache.commons.math3.linear.RealMatrix r = getR();
		int rows = r.getRowDimension();
		int columns = r.getColumnDimension();
		int rank = 1;
		double lastNorm = r.getFrobeniusNorm();
		double rNorm = lastNorm;
		while (rank < (org.apache.commons.math3.util.FastMath.min(rows, columns))) {
			double thisNorm = r.getSubMatrix(rank, (rows - 1), rank, (columns - 1)).getFrobeniusNorm();
			if ((thisNorm == 0) || (((thisNorm / lastNorm) * rNorm) < dropThreshold)) {
				break;
			} 
			lastNorm = thisNorm;
			rank++;
		}
		return rank;
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.DecompositionSolver getSolver() {
		return new org.apache.commons.math3.linear.RRQRDecomposition.Solver(super.getSolver() , org.apache.commons.math3.linear.RRQRDecomposition.this.getP());
	}

	private static class Solver implements org.apache.commons.math3.linear.DecompositionSolver {
		private final org.apache.commons.math3.linear.DecompositionSolver upper;

		private org.apache.commons.math3.linear.RealMatrix p;

		private Solver(final org.apache.commons.math3.linear.DecompositionSolver upper ,final org.apache.commons.math3.linear.RealMatrix p) {
			this.upper = upper;
			org.apache.commons.math3.linear.RRQRDecomposition.Solver.this.p = p;
		}

		public boolean isNonSingular() {
			return upper.isNonSingular();
		}

		public org.apache.commons.math3.linear.RealVector solve(org.apache.commons.math3.linear.RealVector b) {
			return p.operate(upper.solve(b));
		}

		public org.apache.commons.math3.linear.RealMatrix solve(org.apache.commons.math3.linear.RealMatrix b) {
			return p.multiply(upper.solve(b));
		}

		public org.apache.commons.math3.linear.RealMatrix getInverse() {
			return solve(org.apache.commons.math3.linear.MatrixUtils.createRealIdentityMatrix(p.getRowDimension()));
		}
	}
}

