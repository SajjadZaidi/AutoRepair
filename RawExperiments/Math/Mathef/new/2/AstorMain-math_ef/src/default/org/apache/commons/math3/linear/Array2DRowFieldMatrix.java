package org.apache.commons.math3.linear;


public class Array2DRowFieldMatrix<T extends org.apache.commons.math3.FieldElement<T>> extends org.apache.commons.math3.linear.AbstractFieldMatrix<T> implements java.io.Serializable {
	private static final long serialVersionUID = 7260756672015356458L;

	private T[][] data;

	public Array2DRowFieldMatrix(final org.apache.commons.math3.Field<T> field) {
		super(field);
	}

	public Array2DRowFieldMatrix(final org.apache.commons.math3.Field<T> field ,final int rowDimension ,final int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		super(field, rowDimension, columnDimension);
		data = org.apache.commons.math3.util.MathArrays.buildArray(field, rowDimension, columnDimension);
	}

	public Array2DRowFieldMatrix(final T[][] d) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		this(org.apache.commons.math3.linear.AbstractFieldMatrix.extractField(d), d);
	}

	public Array2DRowFieldMatrix(final org.apache.commons.math3.Field<T> field ,final T[][] d) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		super(field);
		copyIn(d);
	}

	public Array2DRowFieldMatrix(final T[][] d ,final boolean copyArray) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		this(org.apache.commons.math3.linear.AbstractFieldMatrix.extractField(d), d, copyArray);
	}

	public Array2DRowFieldMatrix(final org.apache.commons.math3.Field<T> field ,final T[][] d ,final boolean copyArray) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		super(field);
		if (copyArray) {
			copyIn(d);
		} else {
			org.apache.commons.math3.util.MathUtils.checkNotNull(d);
			final int nRows = d.length;
			if (nRows == 0) {
				throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_ROW);
			} 
			final int nCols = d[0].length;
			if (nCols == 0) {
				throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_COLUMN);
			} 
			for (int r = 1 ; r < nRows ; r++) {
				if ((d[r].length) != nCols) {
					throw new org.apache.commons.math3.exception.DimensionMismatchException(nCols , d[r].length);
				} 
			}
			data = d;
		}
	}

	public Array2DRowFieldMatrix(final T[] v) throws org.apache.commons.math3.exception.NoDataException {
		this(org.apache.commons.math3.linear.AbstractFieldMatrix.extractField(v), v);
	}

	public Array2DRowFieldMatrix(final org.apache.commons.math3.Field<T> field ,final T[] v) {
		super(field);
		final int nRows = v.length;
		data = org.apache.commons.math3.util.MathArrays.buildArray(getField(), nRows, 1);
		for (int row = 0 ; row < nRows ; row++) {
			data[row][0] = v[row];
		}
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<T>(getField() , rowDimension , columnDimension);
	}

	@java.lang.Override
	public org.apache.commons.math3.linear.FieldMatrix<T> copy() {
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<T>(getField() , copyOut() , false);
	}

	public org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> add(final org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkAdditionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final T[][] outData = org.apache.commons.math3.util.MathArrays.buildArray(getField(), rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; row++) {
			final T[] dataRow = data[row];
			final T[] mRow = m.data[row];
			final T[] outDataRow = outData[row];
			for (int col = 0 ; col < columnCount ; col++) {
				outDataRow[col] = dataRow[col].add(mRow[col]);
			}
		}
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<T>(getField() , outData , false);
	}

	public org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> subtract(final org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkSubtractionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final T[][] outData = org.apache.commons.math3.util.MathArrays.buildArray(getField(), rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; row++) {
			final T[] dataRow = data[row];
			final T[] mRow = m.data[row];
			final T[] outDataRow = outData[row];
			for (int col = 0 ; col < columnCount ; col++) {
				outDataRow[col] = dataRow[col].subtract(mRow[col]);
			}
		}
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<T>(getField() , outData , false);
	}

	public org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> multiply(final org.apache.commons.math3.linear.Array2DRowFieldMatrix<T> m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		checkMultiplicationCompatible(m);
		final int nRows = org.apache.commons.math3.linear.Array2DRowFieldMatrix.this.getRowDimension();
		final int nCols = m.getColumnDimension();
		final int nSum = org.apache.commons.math3.linear.Array2DRowFieldMatrix.this.getColumnDimension();
		final T[][] outData = org.apache.commons.math3.util.MathArrays.buildArray(getField(), nRows, nCols);
		for (int row = 0 ; row < nRows ; row++) {
			final T[] dataRow = data[row];
			final T[] outDataRow = outData[row];
			for (int col = 0 ; col < nCols ; col++) {
				T sum = getField().getZero();
				for (int i = 0 ; i < nSum ; i++) {
					sum = sum.add(dataRow[i].multiply(m.data[i][col]));
				}
				outDataRow[col] = sum;
			}
		}
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<T>(getField() , outData , false);
	}

	@java.lang.Override
	public T[][] getData() {
		return copyOut();
	}

	public T[][] getDataRef() {
		return data;
	}

	@java.lang.Override
	public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException {
		if ((data) == null) {
			if (row > 0) {
				throw new org.apache.commons.math3.exception.MathIllegalStateException(org.apache.commons.math3.exception.util.LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET , row);
			} 
			if (column > 0) {
				throw new org.apache.commons.math3.exception.MathIllegalStateException(org.apache.commons.math3.exception.util.LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET , column);
			} 
			final int nRows = subMatrix.length;
			if (nRows == 0) {
				throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_ROW);
			} 
			final int nCols = subMatrix[0].length;
			if (nCols == 0) {
				throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_COLUMN);
			} 
			data = org.apache.commons.math3.util.MathArrays.buildArray(getField(), subMatrix.length, nCols);
			for (int i = 0 ; i < (data.length) ; ++i) {
				if ((subMatrix[i].length) != nCols) {
					throw new org.apache.commons.math3.exception.DimensionMismatchException(nCols , subMatrix[i].length);
				} 
				java.lang.System.arraycopy(subMatrix[i], 0, data[(i + row)], column, nCols);
			}
		} else {
			super.setSubMatrix(subMatrix, row, column);
		}
	}

	@java.lang.Override
	public T getEntry(final int row, final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		checkColumnIndex(column);
		return data[row][column];
	}

	@java.lang.Override
	public void setEntry(final int row, final int column, final T value) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		checkColumnIndex(column);
		data[row][column] = value;
	}

	@java.lang.Override
	public void addToEntry(final int row, final int column, final T increment) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		checkColumnIndex(column);
		data[row][column] = data[row][column].add(increment);
	}

	@java.lang.Override
	public void multiplyEntry(final int row, final int column, final T factor) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		checkColumnIndex(column);
		data[row][column] = data[row][column].multiply(factor);
	}

	@java.lang.Override
	public int getRowDimension() {
		return (data) == null ? 0 : data.length;
	}

	@java.lang.Override
	public int getColumnDimension() {
		return ((data) == null) || ((data[0]) == null) ? 0 : data[0].length;
	}

	@java.lang.Override
	public T[] operate(final T[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int nRows = org.apache.commons.math3.linear.Array2DRowFieldMatrix.this.getRowDimension();
		final int nCols = org.apache.commons.math3.linear.Array2DRowFieldMatrix.this.getColumnDimension();
		if ((v.length) != nCols) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(v.length , nCols);
		} 
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(getField(), nRows);
		for (int row = 0 ; row < nRows ; row++) {
			final T[] dataRow = data[row];
			T sum = getField().getZero();
			for (int i = 0 ; i < nCols ; i++) {
				sum = sum.add(dataRow[i].multiply(v[i]));
			}
			out[row] = sum;
		}
		return out;
	}

	@java.lang.Override
	public T[] preMultiply(final T[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if ((v.length) != nRows) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(v.length , nRows);
		} 
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(getField(), nCols);
		for (int col = 0 ; col < nCols ; ++col) {
			T sum = getField().getZero();
			for (int i = 0 ; i < nRows ; ++i) {
				sum = sum.add(data[i][col].multiply(v[i]));
			}
			out[col] = sum;
		}
		return out;
	}

	@java.lang.Override
	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int i = 0 ; i < rows ; ++i) {
			final T[] rowI = data[i];
			for (int j = 0 ; j < columns ; ++j) {
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int i = 0 ; i < rows ; ++i) {
			final T[] rowI = data[i];
			for (int j = 0 ; j < columns ; ++j) {
				visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int i = startRow ; i <= endRow ; ++i) {
			final T[] rowI = data[i];
			for (int j = startColumn ; j <= endColumn ; ++j) {
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int i = startRow ; i <= endRow ; ++i) {
			final T[] rowI = data[i];
			for (int j = startColumn ; j <= endColumn ; ++j) {
				visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int j = 0 ; j < columns ; ++j) {
			for (int i = 0 ; i < rows ; ++i) {
				final T[] rowI = data[i];
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int j = 0 ; j < columns ; ++j) {
			for (int i = 0 ; i < rows ; ++i) {
				visitor.visit(i, j, data[i][j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int j = startColumn ; j <= endColumn ; ++j) {
			for (int i = startRow ; i <= endRow ; ++i) {
				final T[] rowI = data[i];
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	@java.lang.Override
	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int j = startColumn ; j <= endColumn ; ++j) {
			for (int i = startRow ; i <= endRow ; ++i) {
				visitor.visit(i, j, data[i][j]);
			}
		}
		return visitor.end();
	}

	private T[][] copyOut() {
		final int nRows = org.apache.commons.math3.linear.Array2DRowFieldMatrix.this.getRowDimension();
		final T[][] out = org.apache.commons.math3.util.MathArrays.buildArray(getField(), nRows, getColumnDimension());
		for (int i = 0 ; i < nRows ; i++) {
			java.lang.System.arraycopy(data[i], 0, out[i], 0, data[i].length);
		}
		return out;
	}

	private void copyIn(final T[][] in) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		setSubMatrix(in, 0, 0);
	}
}

