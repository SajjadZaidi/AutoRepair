package org.apache.commons.math3.distribution.fitting;


public class MultivariateNormalMixtureExpectationMaximization {
	private static final int DEFAULT_MAX_ITERATIONS = 1000;

	private static final double DEFAULT_THRESHOLD = 1.0E-5;

	private final double[][] data;

	private org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution fittedModel;

	private double logLikelihood = 0.0;

	public MultivariateNormalMixtureExpectationMaximization(double[][] data) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooSmallException {
		if ((data.length) < 1) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(data.length);
		} 
		this.data = new double[data.length][data[0].length];
		for (int i = 0 ; i < (data.length) ; i++) {
			if ((data[i].length) != (data[0].length)) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(data[i].length , data[0].length);
			} 
			if ((data[i].length) < 2) {
				throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_TOO_SMALL , data[i].length , 2 , true);
			} 
			org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.this.data[i] = org.apache.commons.math3.util.MathArrays.copyOf(data[i], data[i].length);
		}
	}

	public void fit(final org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution initialMixture, final int maxIterations, final double threshold) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.linear.SingularMatrixException {
		if (maxIterations < 1) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(maxIterations);
		} 
		if (threshold < (java.lang.Double.MIN_VALUE)) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(threshold);
		} 
		final int n = data.length;
		final int numCols = data[0].length;
		final int k = initialMixture.getComponents().size();
		final int numMeanColumns = initialMixture.getComponents().get(0).getSecond().getMeans().length;
		if (numMeanColumns != numCols) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(numMeanColumns , numCols);
		} 
		int numIterations = 0;
		double previousLogLikelihood = 0.0;
		logLikelihood = java.lang.Double.NEGATIVE_INFINITY;
		fittedModel = new org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution(initialMixture.getComponents());
		while (((numIterations++) <= maxIterations) && ((org.apache.commons.math3.util.FastMath.abs((previousLogLikelihood - (logLikelihood)))) > threshold)) {
			previousLogLikelihood = logLikelihood;
			double sumLogLikelihood = 0.0;
			final java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> components = fittedModel.getComponents();
			final double[] weights = new double[k];
			final org.apache.commons.math3.distribution.MultivariateNormalDistribution[] mvns = new org.apache.commons.math3.distribution.MultivariateNormalDistribution[k];
			for (int j = 0 ; j < k ; j++) {
				weights[j] = components.get(j).getFirst();
				mvns[j] = components.get(j).getSecond();
			}
			final double[][] gamma = new double[n][k];
			final double[] gammaSums = new double[k];
			final double[][] gammaDataProdSums = new double[k][numCols];
			for (int i = 0 ; i < n ; i++) {
				final double rowDensity = fittedModel.density(data[i]);
				sumLogLikelihood += org.apache.commons.math3.util.FastMath.log(rowDensity);
				for (int j = 0 ; j < k ; j++) {
					gamma[i][j] = ((weights[j]) * (mvns[j].density(data[i]))) / rowDensity;
					gammaSums[j] += gamma[i][j];
					for (int col = 0 ; col < numCols ; col++) {
						gammaDataProdSums[j][col] += (gamma[i][j]) * (data[i][col]);
					}
				}
			}
			logLikelihood = sumLogLikelihood / n;
			final double[] newWeights = new double[k];
			final double[][] newMeans = new double[k][numCols];
			for (int j = 0 ; j < k ; j++) {
				newWeights[j] = (gammaSums[j]) / n;
				for (int col = 0 ; col < numCols ; col++) {
					newMeans[j][col] = (gammaDataProdSums[j][col]) / (gammaSums[j]);
				}
			}
			final org.apache.commons.math3.linear.RealMatrix[] newCovMats = new org.apache.commons.math3.linear.RealMatrix[k];
			for (int j = 0 ; j < k ; j++) {
				newCovMats[j] = new org.apache.commons.math3.linear.Array2DRowRealMatrix(numCols , numCols);
			}
			for (int i = 0 ; i < n ; i++) {
				for (int j = 0 ; j < k ; j++) {
					final org.apache.commons.math3.linear.RealMatrix vec = new org.apache.commons.math3.linear.Array2DRowRealMatrix(org.apache.commons.math3.util.MathArrays.ebeSubtract(data[i], newMeans[j]));
					final org.apache.commons.math3.linear.RealMatrix dataCov = vec.multiply(vec.transpose()).scalarMultiply(gamma[i][j]);
					newCovMats[j] = newCovMats[j].add(dataCov);
				}
			}
			final double[][][] newCovMatArrays = new double[k][numCols][numCols];
			for (int j = 0 ; j < k ; j++) {
				newCovMats[j] = newCovMats[j].scalarMultiply((1.0 / (gammaSums[j])));
				newCovMatArrays[j] = newCovMats[j].getData();
			}
			fittedModel = new org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution(newWeights , newMeans , newCovMatArrays);
		}
		if ((org.apache.commons.math3.util.FastMath.abs((previousLogLikelihood - (logLikelihood)))) > threshold) {
			throw new org.apache.commons.math3.exception.ConvergenceException();
		} 
	}

	public void fit(org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution initialMixture) throws org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.linear.SingularMatrixException {
		fit(initialMixture, org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DEFAULT_MAX_ITERATIONS, org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DEFAULT_THRESHOLD);
	}

	public static org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution estimate(final double[][] data, final int numComponents) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if ((data.length) < 2) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(data.length);
		} 
		if (numComponents < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(numComponents , 2 , true);
		} 
		if (numComponents > (data.length)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(numComponents , data.length , true);
		} 
		final int numRows = data.length;
		final int numCols = data[0].length;
		final org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow[] sortedData = new org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow[numRows];
		for (int i = 0 ; i < numRows ; i++) {
			sortedData[i] = new org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow(data[i]);
		}
		java.util.Arrays.sort(sortedData);
		final double weight = 1.0 / numComponents;
		final java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> components = new java.util.ArrayList<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>>(numComponents);
		for (int binIndex = 0 ; binIndex < numComponents ; binIndex++) {
			final int minIndex = (binIndex * numRows) / numComponents;
			final int maxIndex = ((binIndex + 1) * numRows) / numComponents;
			final int numBinRows = maxIndex - minIndex;
			final double[][] binData = new double[numBinRows][numCols];
			final double[] columnMeans = new double[numCols];
			for (int i = minIndex, iBin = 0 ; i < maxIndex ; i++ , iBin++) {
				for (int j = 0 ; j < numCols ; j++) {
					final double val = sortedData[i].getRow()[j];
					columnMeans[j] += val;
					binData[iBin][j] = val;
				}
			}
			org.apache.commons.math3.util.MathArrays.scaleInPlace((1.0 / numBinRows), columnMeans);
			final double[][] covMat = new org.apache.commons.math3.stat.correlation.Covariance(binData).getCovarianceMatrix().getData();
			final org.apache.commons.math3.distribution.MultivariateNormalDistribution mvn = new org.apache.commons.math3.distribution.MultivariateNormalDistribution(columnMeans , covMat);
			components.add(new org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>(weight , mvn));
		}
		return new org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution(components);
	}

	public double getLogLikelihood() {
		return logLikelihood;
	}

	public org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution getFittedModel() {
		return new org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution(fittedModel.getComponents());
	}

	private static class DataRow implements java.lang.Comparable<org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow> {
		private final double[] row;

		private java.lang.Double mean;

		DataRow(final double[] data) {
			row = data;
			mean = 0.0;
			for (int i = 0 ; i < (data.length) ; i++) {
				mean += data[i];
			}
			mean /= data.length;
		}

		public int compareTo(final org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow other) {
			return mean.compareTo(other.mean);
		}

		@java.lang.Override
		public boolean equals(java.lang.Object other) {
			if ((org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow.this) == other) {
				return true;
			} 
			if (other instanceof org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow) {
				return org.apache.commons.math3.util.MathArrays.equals(row, ((org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow)(other)).row);
			} 
			return false;
		}

		@java.lang.Override
		public int hashCode() {
			return java.util.Arrays.hashCode(row);
		}

		public double[] getRow() {
			return row;
		}
	}
}

