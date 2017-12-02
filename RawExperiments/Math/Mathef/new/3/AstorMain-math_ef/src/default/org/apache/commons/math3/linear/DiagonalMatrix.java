package org.apache.commons.math3.linear;


public class DiagonalMatrix extends org.apache.commons.math3.linear.AbstractRealMatrix implements java.io.Serializable {
	private static final long serialVersionUID = 20121229L;

	private final double[] data;

	public DiagonalMatrix(final int dimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		super(dimension, dimension);
		data = new double[dimension];
	}

	public DiagonalMatrix(final double[] d) {
		this(d, true);
	}

	public DiagonalMatrix(final double[] d ,final boolean copyArray) throws org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(d);
		data = copyArray ? d.clone() : d;
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.RealMatrix createMatrix(final int rowDimension, final int columnDimension) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (rowDimension != columnDimension) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(rowDimension , columnDimension);
		} 
		return new org.apache.commons.math3.linear.DiagonalMatrix(rowDimension);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.RealMatrix copy() {
		return new org.apache.commons.math3.linear.DiagonalMatrix(data);
	}

	public org.apache.commons.math3.linear.DiagonalMatrix add(final org.apache.commons.math3.linear.DiagonalMatrix m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible(org.apache.commons.math3.linear.DiagonalMatrix.this, m);
		final int dim = getRowDimension();
		final double[] outData = new double[dim];
		for (int i = 0 ; i < dim ; i++) {
			outData[i] = (data[i]) + (m.data[i]);
		}
		return new org.apache.commons.math3.linear.DiagonalMatrix(outData , false);
	}

	public org.apache.commons.math3.linear.DiagonalMatrix subtract(final org.apache.commons.math3.linear.DiagonalMatrix m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible(org.apache.commons.math3.linear.DiagonalMatrix.this, m);
		final int dim = getRowDimension();
		final double[] outData = new double[dim];
		for (int i = 0 ; i < dim ; i++) {
			outData[i] = (data[i]) - (m.data[i]);
		}
		return new org.apache.commons.math3.linear.DiagonalMatrix(outData , false);
	}

	public org.apache.commons.math3.linear.DiagonalMatrix multiply(final org.apache.commons.math3.linear.DiagonalMatrix m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible(org.apache.commons.math3.linear.DiagonalMatrix.this, m);
		final int dim = getRowDimension();
		final double[] outData = new double[dim];
		for (int i = 0 ; i < dim ; i++) {
			outData[i] = (data[i]) * (m.data[i]);
		}
		return new org.apache.commons.math3.linear.DiagonalMatrix(outData , false);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.RealMatrix multiply(final org.apache.commons.math3.linear.RealMatrix m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if (m instanceof org.apache.commons.math3.linear.DiagonalMatrix) {
			return multiply(((org.apache.commons.math3.linear.DiagonalMatrix)(m)));
		} else {
			org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible(org.apache.commons.math3.linear.DiagonalMatrix.this, m);
			final int nRows = m.getRowDimension();
			final int nCols = m.getColumnDimension();
			final double[][] product = new double[nRows][nCols];
			for (int r = 0 ; r < nRows ; r++) {
				for (int c = 0 ; c < nCols ; c++) {
					product[r][c] = (data[r]) * (m.getEntry(r, c));
				}
			}
			return new org.apache.commons.math3.linear.Array2DRowRealMatrix(product , false);
		}
	}

	@java.lang.Override
	public double[][] getData() {
		final int dim = getRowDimension();
		final double[][] out = new double[dim][dim];
		for (int i = 0 ; i < dim ; i++) {
			out[i][i] = data[i];
		}
		return out;
	}

	public double[] getDataRef() {
		return data;
	}

	@java.lang.Override
	public double getEntry(final int row, final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		org.apache.commons.math3.linear.MatrixUtils.checkMatrixIndex(org.apache.commons.math3.linear.DiagonalMatrix.this, row, column);
		return row == column ? data[row] : 0;
	}

	@java.lang.Override
	public void setEntry(final int row, final int column, final double value) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.OutOfRangeException {
		if (row == column) {
			org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.DiagonalMatrix.this, row);
			data[row] = value;
		} else {
			ensureZero(value);
		}
	}

	@java.lang.Override
	public void addToEntry(final int row, final int column, final double increment) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.OutOfRangeException {
		if (row == column) {
			org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.DiagonalMatrix.this, row);
			data[row] += increment;
		} else {
			ensureZero(increment);
		}
	}

	@java.lang.Override
	public void multiplyEntry(final int row, final int column, final double factor) throws org.apache.commons.math3.exception.OutOfRangeException {
		if (row == column) {
			org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.DiagonalMatrix.this, row);
			data[row] *= factor;
		} 
	}

	@java.lang.Override
	public int getRowDimension() {
		return data.length;
	}

	@java.lang.Override
	public int getColumnDimension() {
		return data.length;
	}

	@java.lang.Override
	public double[] operate(final double[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		return multiply(new org.apache.commons.math3.linear.DiagonalMatrix(v , false)).getDataRef();
	}

	@java.lang.Override
	public double[] preMultiply(final double[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		return operate(v);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.RealVector preMultiply(final org.apache.commons.math3.linear.RealVector v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final double[] vectorData;
		if (v instanceof org.apache.commons.math3.linear.ArrayRealVector) {
			vectorData = ((org.apache.commons.math3.linear.ArrayRealVector)(v)).getDataRef();
		} else {
			vectorData = v.toArray();
		}
		return org.apache.commons.math3.linear.MatrixUtils.createRealVector(preMultiply(vectorData));
	}

	private void ensureZero(final double value) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (!(org.apache.commons.math3.util.Precision.equals(0.0, value, 1))) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.util.FastMath.abs(value) , 0 , true);
		} 
	}

	public org.apache.commons.math3.linear.DiagonalMatrix inverse() throws org.apache.commons.math3.linear.SingularMatrixException {
		return inverse(0);
	}

	public org.apache.commons.math3.linear.DiagonalMatrix inverse(double threshold) throws org.apache.commons.math3.linear.SingularMatrixException {
		if (isSingular(threshold)) {
			throw new org.apache.commons.math3.linear.SingularMatrixException();
		} 
		final double[] result = new double[data.length];
		for (int i = 0 ; i < (data.length) ; i++) {
			result[i] = 1.0 / (data[i]);
		}
		return new org.apache.commons.math3.linear.DiagonalMatrix(result , false);
	}

	public boolean isSingular(double threshold) {
		for (int i = 0 ; i < (data.length) ; i++) {
			if (org.apache.commons.math3.util.Precision.equals(data[i], 0.0, threshold)) {
				return true;
			} 
		}
		return false;
	}
}

