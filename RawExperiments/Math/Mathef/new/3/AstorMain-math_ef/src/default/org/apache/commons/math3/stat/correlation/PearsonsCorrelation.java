package org.apache.commons.math3.stat.correlation;


public class PearsonsCorrelation {
	private final org.apache.commons.math3.linear.RealMatrix correlationMatrix;

	private final int nObs;

	public PearsonsCorrelation() {
		super();
		correlationMatrix = null;
		nObs = 0;
	}

	public PearsonsCorrelation(double[][] data) {
		this(new org.apache.commons.math3.linear.BlockRealMatrix(data));
	}

	public PearsonsCorrelation(org.apache.commons.math3.linear.RealMatrix matrix) {
		nObs = matrix.getRowDimension();
		correlationMatrix = computeCorrelationMatrix(matrix);
	}

	public PearsonsCorrelation(org.apache.commons.math3.stat.correlation.Covariance covariance) {
		org.apache.commons.math3.linear.RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
		if (covarianceMatrix == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.COVARIANCE_MATRIX);
		} 
		nObs = covariance.getN();
		correlationMatrix = covarianceToCorrelation(covarianceMatrix);
	}

	public PearsonsCorrelation(org.apache.commons.math3.linear.RealMatrix covarianceMatrix ,int numberOfObservations) {
		nObs = numberOfObservations;
		correlationMatrix = covarianceToCorrelation(covarianceMatrix);
	}

	public org.apache.commons.math3.linear.RealMatrix getCorrelationMatrix() {
		return correlationMatrix;
	}

	public org.apache.commons.math3.linear.RealMatrix getCorrelationStandardErrors() {
		int nVars = correlationMatrix.getColumnDimension();
		double[][] out = new double[nVars][nVars];
		for (int i = 0 ; i < nVars ; i++) {
			for (int j = 0 ; j < nVars ; j++) {
				double r = correlationMatrix.getEntry(i, j);
				out[i][j] = org.apache.commons.math3.util.FastMath.sqrt(((1 - (r * r)) / ((nObs) - 2)));
			}
		}
		return new org.apache.commons.math3.linear.BlockRealMatrix(out);
	}

	public org.apache.commons.math3.linear.RealMatrix getCorrelationPValues() {
		org.apache.commons.math3.distribution.TDistribution tDistribution = new org.apache.commons.math3.distribution.TDistribution(((nObs) - 2));
		int nVars = correlationMatrix.getColumnDimension();
		double[][] out = new double[nVars][nVars];
		for (int i = 0 ; i < nVars ; i++) {
			for (int j = 0 ; j < nVars ; j++) {
				if (i == j) {
					out[i][j] = 0.0;
				} else {
					double r = correlationMatrix.getEntry(i, j);
					double t = org.apache.commons.math3.util.FastMath.abs((r * (org.apache.commons.math3.util.FastMath.sqrt((((nObs) - 2) / (1 - (r * r)))))));
					out[i][j] = 2 * (tDistribution.cumulativeProbability((-t)));
				}
			}
		}
		return new org.apache.commons.math3.linear.BlockRealMatrix(out);
	}

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(org.apache.commons.math3.linear.RealMatrix matrix) {
		checkSufficientData(matrix);
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

	public org.apache.commons.math3.linear.RealMatrix computeCorrelationMatrix(double[][] data) {
		return computeCorrelationMatrix(new org.apache.commons.math3.linear.BlockRealMatrix(data));
	}

	public double correlation(final double[] xArray, final double[] yArray) {
		org.apache.commons.math3.stat.regression.SimpleRegression regression = new org.apache.commons.math3.stat.regression.SimpleRegression();
		if ((xArray.length) != (yArray.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xArray.length , yArray.length);
		} else if ((xArray.length) < 2) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_DIMENSION , xArray.length , 2);
		} else {
			for (int i = 0 ; i < (xArray.length) ; i++) {
				regression.addData(xArray[i], yArray[i]);
			}
			return regression.getR();
		}
	}

	public org.apache.commons.math3.linear.RealMatrix covarianceToCorrelation(org.apache.commons.math3.linear.RealMatrix covarianceMatrix) {
		int nVars = covarianceMatrix.getColumnDimension();
		org.apache.commons.math3.linear.RealMatrix outMatrix = new org.apache.commons.math3.linear.BlockRealMatrix(nVars , nVars);
		for (int i = 0 ; i < nVars ; i++) {
			double sigma = org.apache.commons.math3.util.FastMath.sqrt(covarianceMatrix.getEntry(i, i));
			outMatrix.setEntry(i, i, 1.0);
			for (int j = 0 ; j < i ; j++) {
				double entry = (covarianceMatrix.getEntry(i, j)) / (sigma * (org.apache.commons.math3.util.FastMath.sqrt(covarianceMatrix.getEntry(j, j))));
				outMatrix.setEntry(i, j, entry);
				outMatrix.setEntry(j, i, entry);
			}
		}
		return outMatrix;
	}

	private void checkSufficientData(final org.apache.commons.math3.linear.RealMatrix matrix) {
		int nRows = matrix.getRowDimension();
		int nCols = matrix.getColumnDimension();
		if ((nRows < 2) || (nCols < 2)) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS , nRows , nCols);
		} 
	}
}

