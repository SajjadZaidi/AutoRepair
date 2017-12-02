package org.apache.commons.math3.stat.correlation;


public class SpearmansCorrelation {
	private final org.apache.commons.math3.linear.RealMatrix data;

	private final org.apache.commons.math3.stat.ranking.RankingAlgorithm rankingAlgorithm;

	private final org.apache.commons.math3.stat.correlation.PearsonsCorrelation rankCorrelation;

	public SpearmansCorrelation() {
		this(new org.apache.commons.math3.stat.ranking.NaturalRanking());
	}

	public SpearmansCorrelation(final org.apache.commons.math3.stat.ranking.RankingAlgorithm rankingAlgorithm) {
		data = null;
		this.rankingAlgorithm = rankingAlgorithm;
		rankCorrelation = null;
	}

	public SpearmansCorrelation(final org.apache.commons.math3.linear.RealMatrix dataMatrix) {
		this(dataMatrix, new org.apache.commons.math3.stat.ranking.NaturalRanking());
	}

	public SpearmansCorrelation(final org.apache.commons.math3.linear.RealMatrix dataMatrix ,final org.apache.commons.math3.stat.ranking.RankingAlgorithm rankingAlgorithm) {
		this.rankingAlgorithm = rankingAlgorithm;
		this.data = rankTransform(dataMatrix);
		rankCorrelation = new org.apache.commons.math3.stat.correlation.PearsonsCorrelation(data);
	}

	public org.apache.commons.math3.linear.RealMatrix getCorrelationMatrix() {
		return rankCorrelation.getCorrelationMatrix();
	}

	public org.apache.commons.math3.stat.correlation.PearsonsCorrelation getRankCorrelation() {
		return rankCorrelation;
	}

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(final org.apache.commons.math3.linear.RealMatrix matrix) {
		final org.apache.commons.math3.linear.RealMatrix matrixCopy = rankTransform(matrix);
		return new org.apache.commons.math3.stat.correlation.PearsonsCorrelation().computeCorrelationMatrix(matrixCopy);
	}

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(final double[][] matrix) {
		return computeCorrelationMatrix(new org.apache.commons.math3.linear.BlockRealMatrix(matrix));
	}

	public double correlation(final double[] xArray, final double[] yArray) {
		if ((xArray.length) != (yArray.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xArray.length , yArray.length);
		} else if ((xArray.length) < 2) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_DIMENSION , xArray.length , 2);
		} else {
			double[] x = xArray;
			double[] y = yArray;
			if (((rankingAlgorithm) instanceof org.apache.commons.math3.stat.ranking.NaturalRanking) && ((org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED) == (((org.apache.commons.math3.stat.ranking.NaturalRanking)(rankingAlgorithm)).getNanStrategy()))) {
				final java.util.Set<java.lang.Integer> nanPositions = new java.util.HashSet<java.lang.Integer>();
				nanPositions.addAll(getNaNPositions(xArray));
				nanPositions.addAll(getNaNPositions(yArray));
				x = removeValues(xArray, nanPositions);
				y = removeValues(yArray, nanPositions);
			} 
			return new org.apache.commons.math3.stat.correlation.PearsonsCorrelation().correlation(rankingAlgorithm.rank(x), rankingAlgorithm.rank(y));
		}
	}

	private org.apache.commons.math3.linear.RealMatrix rankTransform(final org.apache.commons.math3.linear.RealMatrix matrix) {
		org.apache.commons.math3.linear.RealMatrix transformed = null;
		if (((rankingAlgorithm) instanceof org.apache.commons.math3.stat.ranking.NaturalRanking) && ((((org.apache.commons.math3.stat.ranking.NaturalRanking)(rankingAlgorithm)).getNanStrategy()) == (org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED))) {
			final java.util.Set<java.lang.Integer> nanPositions = new java.util.HashSet<java.lang.Integer>();
			for (int i = 0 ; i < (matrix.getColumnDimension()) ; i++) {
				nanPositions.addAll(getNaNPositions(matrix.getColumn(i)));
			}
			if (!(nanPositions.isEmpty())) {
				transformed = new org.apache.commons.math3.linear.BlockRealMatrix(((matrix.getRowDimension()) - (nanPositions.size())) , matrix.getColumnDimension());
				for (int i = 0 ; i < (transformed.getColumnDimension()) ; i++) {
					transformed.setColumn(i, removeValues(matrix.getColumn(i), nanPositions));
				}
			} 
		} 
		if (transformed == null) {
			transformed = matrix.copy();
		} 
		for (int i = 0 ; i < (transformed.getColumnDimension()) ; i++) {
			transformed.setColumn(i, rankingAlgorithm.rank(transformed.getColumn(i)));
		}
		return transformed;
	}

	private java.util.List<java.lang.Integer> getNaNPositions(final double[] input) {
		final java.util.List<java.lang.Integer> positions = new java.util.ArrayList<java.lang.Integer>();
		for (int i = 0 ; i < (input.length) ; i++) {
			if (java.lang.Double.isNaN(input[i])) {
				positions.add(i);
			} 
		}
		return positions;
	}

	private double[] removeValues(final double[] input, final java.util.Set<java.lang.Integer> indices) {
		if (indices.isEmpty()) {
			return input;
		} 
		final double[] result = new double[(input.length) - (indices.size())];
		for (int i = 0, j = 0 ; i < (input.length) ; i++) {
			if (!(indices.contains(i))) {
				result[(j++)] = input[i];
			} 
		}
		return result;
	}
}

