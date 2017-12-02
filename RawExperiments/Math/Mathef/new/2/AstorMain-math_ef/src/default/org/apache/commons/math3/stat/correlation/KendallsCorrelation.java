package org.apache.commons.math3.stat.correlation;


public class KendallsCorrelation {
	private final org.apache.commons.math3.linear.RealMatrix correlationMatrix;

	public KendallsCorrelation() {
		correlationMatrix = null;
	}

	public KendallsCorrelation(double[][] data) {
		this(org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(data));
	}

	public KendallsCorrelation(org.apache.commons.math3.linear.RealMatrix matrix) {
		correlationMatrix = computeCorrelationMatrix(matrix);
	}

	public org.apache.commons.math3.linear.RealMatrix getCorrelationMatrix() {
		return correlationMatrix;
	}

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(final org.apache.commons.math3.linear.RealMatrix matrix) {
		int nVars = matrix.getColumnDimension();
		org.apache.commons.math3.linear.RealMatrix outMatrix = new org.apache.commons.math3.linear.BlockRealMatrix(nVars , nVars);
		for (int i = 0 ; i < nVars ; i++) {
			for (int j = 0 ; j < i ; j++) {
				double corr = correlation(matrix.getColumn(i), matrix.getColumn(j));
				outMatrix.setEntry(i, j, corr);
				outMatrix.setEntry(j, i, corr);
			}
			outMatrix.setEntry(i, i, 1.0);
		}
		return outMatrix;
	}

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(final double[][] matrix) {
		return computeCorrelationMatrix(new org.apache.commons.math3.linear.BlockRealMatrix(matrix));
	}

	public double correlation(final double[] xArray, final double[] yArray) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((xArray.length) != (yArray.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xArray.length , yArray.length);
		} 
		final int n = xArray.length;
		final long numPairs = org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((n - 1));
		@java.lang.SuppressWarnings(value = "unchecked")
		org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>[] pairs = new org.apache.commons.math3.util.Pair[n];
		for (int i = 0 ; i < n ; i++) {
			pairs[i] = new org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>(xArray[i] , yArray[i]);
		}
		java.util.Arrays.sort(pairs, new java.util.Comparator<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>>() {
			public int compare(org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> pair1, org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> pair2) {
				int compareFirst = pair1.getFirst().compareTo(pair2.getFirst());
				return compareFirst != 0 ? compareFirst : pair1.getSecond().compareTo(pair2.getSecond());
			}
		});
		long tiedXPairs = 0;
		long tiedXYPairs = 0;
		long consecutiveXTies = 1;
		long consecutiveXYTies = 1;
		org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> prev = pairs[0];
		for (int i = 1 ; i < n ; i++) {
			final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> curr = pairs[i];
			if (curr.getFirst().equals(prev.getFirst())) {
				consecutiveXTies++;
				if (curr.getSecond().equals(prev.getSecond())) {
					consecutiveXYTies++;
				} else {
					tiedXYPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveXYTies - 1));
					consecutiveXYTies = 1;
				}
			} else {
				tiedXPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveXTies - 1));
				consecutiveXTies = 1;
				tiedXYPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveXYTies - 1));
				consecutiveXYTies = 1;
			}
			prev = curr;
		}
		tiedXPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveXTies - 1));
		tiedXYPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveXYTies - 1));
		int swaps = 0;
		@java.lang.SuppressWarnings(value = "unchecked")
		org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>[] pairsDestination = new org.apache.commons.math3.util.Pair[n];
		for (int segmentSize = 1 ; segmentSize < n ; segmentSize <<= 1) {
			for (int offset = 0 ; offset < n ; offset += 2 * segmentSize) {
				int i = offset;
				final int iEnd = org.apache.commons.math3.util.FastMath.min((i + segmentSize), n);
				int j = iEnd;
				final int jEnd = org.apache.commons.math3.util.FastMath.min((j + segmentSize), n);
				int copyLocation = offset;
				while ((i < iEnd) || (j < jEnd)) {
					if (i < iEnd) {
						if (j < jEnd) {
							if ((pairs[i].getSecond().compareTo(pairs[j].getSecond())) <= 0) {
								pairsDestination[copyLocation] = pairs[i];
								i++;
							} else {
								pairsDestination[copyLocation] = pairs[j];
								j++;
								swaps += iEnd - i;
							}
						} else {
							pairsDestination[copyLocation] = pairs[i];
							i++;
						}
					} else {
						pairsDestination[copyLocation] = pairs[j];
						j++;
					}
					copyLocation++;
				}
			}
			final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>[] pairsTemp = pairs;
			pairs = pairsDestination;
			pairsDestination = pairsTemp;
		}
		long tiedYPairs = 0;
		long consecutiveYTies = 1;
		prev = pairs[0];
		for (int i = 1 ; i < n ; i++) {
			final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> curr = pairs[i];
			if (curr.getSecond().equals(prev.getSecond())) {
				consecutiveYTies++;
			} else {
				tiedYPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveYTies - 1));
				consecutiveYTies = 1;
			}
			prev = curr;
		}
		tiedYPairs += org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum((consecutiveYTies - 1));
		final long concordantMinusDiscordant = (((numPairs - tiedXPairs) - tiedYPairs) + tiedXYPairs) - (2 * swaps);
		final double nonTiedPairsMultiplied = (numPairs - tiedXPairs) * ((double)((numPairs - tiedYPairs)));
		return concordantMinusDiscordant / (org.apache.commons.math3.util.FastMath.sqrt(nonTiedPairsMultiplied));
	}

	private static long sum(long n) {
		return (n * (n + 1)) / 2L;
	}
}

