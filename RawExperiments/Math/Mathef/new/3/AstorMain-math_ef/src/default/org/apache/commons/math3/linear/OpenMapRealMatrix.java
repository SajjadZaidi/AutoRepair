package org.apache.commons.math3.linear;


public class OpenMapRealMatrix extends org.apache.commons.math3.linear.AbstractRealMatrix implements java.io.Serializable , org.apache.commons.math3.linear.SparseRealMatrix {
	private static final long serialVersionUID = -5962461716457143437L;

	private final int rows;

	private final int columns;

	private final org.apache.commons.math3.util.OpenIntToDoubleHashMap entries;

	public OpenMapRealMatrix(int rowDimension ,int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooLargeException {
		super(rowDimension, columnDimension);
		long lRow = rowDimension;
		long lCol = columnDimension;
		if ((lRow * lCol) >= (java.lang.Integer.MAX_VALUE)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException((lRow * lCol) , java.lang.Integer.MAX_VALUE , false);
		} 
		this.rows = rowDimension;
		this.columns = columnDimension;
		this.entries = new org.apache.commons.math3.util.OpenIntToDoubleHashMap(0.0);
	}

	public OpenMapRealMatrix(org.apache.commons.math3.linear.OpenMapRealMatrix matrix) {
		this.rows = matrix.rows;
		this.columns = matrix.columns;
		this.entries = new org.apache.commons.math3.util.OpenIntToDoubleHashMap(matrix.entries);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.OpenMapRealMatrix copy() {
		return new org.apache.commons.math3.linear.OpenMapRealMatrix(org.apache.commons.math3.linear.OpenMapRealMatrix.this);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.OpenMapRealMatrix createMatrix(int rowDimension, int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return new org.apache.commons.math3.linear.OpenMapRealMatrix(rowDimension , columnDimension);
	}

	@java.lang.Override
	public int getColumnDimension() {
		return columns;
	}

	public org.apache.commons.math3.linear.OpenMapRealMatrix add(org.apache.commons.math3.linear.OpenMapRealMatrix m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible(org.apache.commons.math3.linear.OpenMapRealMatrix.this, m);
		final org.apache.commons.math3.linear.OpenMapRealMatrix out = new org.apache.commons.math3.linear.OpenMapRealMatrix(org.apache.commons.math3.linear.OpenMapRealMatrix.this);
		for (org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator() ; iterator.hasNext() ; ) {
			iterator.advance();
			final int row = (iterator.key()) / (columns);
			final int col = (iterator.key()) - (row * (columns));
			out.setEntry(row, col, ((getEntry(row, col)) + (iterator.value())));
		}
		return out;
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.OpenMapRealMatrix subtract(final org.apache.commons.math3.linear.RealMatrix m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		try {
			return subtract(((org.apache.commons.math3.linear.OpenMapRealMatrix)(m)));
		} catch (java.lang.ClassCastException cce) {
			return ((org.apache.commons.math3.linear.OpenMapRealMatrix)(super.subtract(m)));
		}
	}

	public org.apache.commons.math3.linear.OpenMapRealMatrix subtract(org.apache.commons.math3.linear.OpenMapRealMatrix m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible(org.apache.commons.math3.linear.OpenMapRealMatrix.this, m);
		final org.apache.commons.math3.linear.OpenMapRealMatrix out = new org.apache.commons.math3.linear.OpenMapRealMatrix(org.apache.commons.math3.linear.OpenMapRealMatrix.this);
		for (org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator() ; iterator.hasNext() ; ) {
			iterator.advance();
			final int row = (iterator.key()) / (columns);
			final int col = (iterator.key()) - (row * (columns));
			out.setEntry(row, col, ((getEntry(row, col)) - (iterator.value())));
		}
		return out;
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.RealMatrix multiply(final org.apache.commons.math3.linear.RealMatrix m) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		try {
			return multiply(((org.apache.commons.math3.linear.OpenMapRealMatrix)(m)));
		} catch (java.lang.ClassCastException cce) {
			org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible(org.apache.commons.math3.linear.OpenMapRealMatrix.this, m);
			final int outCols = m.getColumnDimension();
			final org.apache.commons.math3.linear.BlockRealMatrix out = new org.apache.commons.math3.linear.BlockRealMatrix(rows , outCols);
			for (org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator iterator = entries.iterator() ; iterator.hasNext() ; ) {
				iterator.advance();
				final double value = iterator.value();
				final int key = iterator.key();
				final int i = key / (columns);
				final int k = key % (columns);
				for (int j = 0 ; j < outCols ; ++j) {
					out.addToEntry(i, j, (value * (m.getEntry(k, j))));
				}
			}
			return out;
		}
	}

	public org.apache.commons.math3.linear.OpenMapRealMatrix multiply(org.apache.commons.math3.linear.OpenMapRealMatrix m) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible(org.apache.commons.math3.linear.OpenMapRealMatrix.this, m);
		final int outCols = m.getColumnDimension();
		org.apache.commons.math3.linear.OpenMapRealMatrix out = new org.apache.commons.math3.linear.OpenMapRealMatrix(rows , outCols);
		for (org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator iterator = entries.iterator() ; iterator.hasNext() ; ) {
			iterator.advance();
			final double value = iterator.value();
			final int key = iterator.key();
			final int i = key / (columns);
			final int k = key % (columns);
			for (int j = 0 ; j < outCols ; ++j) {
				final int rightKey = m.computeKey(k, j);
				if (m.entries.containsKey(rightKey)) {
					final int outKey = out.computeKey(i, j);
					final double outValue = (out.entries.get(outKey)) + (value * (m.entries.get(rightKey)));
					if (outValue == 0.0) {
						out.entries.remove(outKey);
					} else {
						out.entries.put(outKey, outValue);
					}
				} 
			}
		}
		return out;
	}

	@java.lang.Override
	public double getEntry(int row, int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, row);
		org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, column);
		return entries.get(computeKey(row, column));
	}

	@java.lang.Override
	public int getRowDimension() {
		return rows;
	}

	@java.lang.Override
	public void setEntry(int row, int column, double value) throws org.apache.commons.math3.exception.OutOfRangeException {
		org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, row);
		org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, column);
		if (value == 0.0) {
			entries.remove(computeKey(row, column));
		} else {
			entries.put(computeKey(row, column), value);
		}
	}

	@java.lang.Override
	public void addToEntry(int row, int column, double increment) throws org.apache.commons.math3.exception.OutOfRangeException {
		org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, row);
		org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, column);
		final int key = computeKey(row, column);
		final double value = (entries.get(key)) + increment;
		if (value == 0.0) {
			entries.remove(key);
		} else {
			entries.put(key, value);
		}
	}

	@java.lang.Override
	public void multiplyEntry(int row, int column, double factor) throws org.apache.commons.math3.exception.OutOfRangeException {
		org.apache.commons.math3.linear.MatrixUtils.checkRowIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, row);
		org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex(org.apache.commons.math3.linear.OpenMapRealMatrix.this, column);
		final int key = computeKey(row, column);
		final double value = (entries.get(key)) * factor;
		if (value == 0.0) {
			entries.remove(key);
		} else {
			entries.put(key, value);
		}
	}

	private int computeKey(int row, int column) {
		return (row * (columns)) + column;
	}
}

