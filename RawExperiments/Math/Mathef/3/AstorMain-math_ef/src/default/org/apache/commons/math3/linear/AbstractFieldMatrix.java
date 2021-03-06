package org.apache.commons.math3.linear;


public abstract class AbstractFieldMatrix<T extends org.apache.commons.math3.FieldElement<T>> implements org.apache.commons.math3.linear.FieldMatrix<T> {
	private final org.apache.commons.math3.Field<T> field;

	protected AbstractFieldMatrix() {
		field = null;
	}

	protected AbstractFieldMatrix(final org.apache.commons.math3.Field<T> field) {
		this.field = field;
	}

	protected AbstractFieldMatrix(final org.apache.commons.math3.Field<T> field ,final int rowDimension ,final int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (rowDimension <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.DIMENSION , rowDimension);
		} 
		if (columnDimension <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.DIMENSION , columnDimension);
		} 
		this.field = field;
	}

	protected static <T extends org.apache.commons.math3.FieldElement<T>>org.apache.commons.math3.Field<T> extractField(final T[][] d) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		if (d == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if ((d.length) == 0) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_ROW);
		} 
		if ((d[0].length) == 0) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_COLUMN);
		} 
		return d[0][0].getField();
	}

	protected static <T extends org.apache.commons.math3.FieldElement<T>>org.apache.commons.math3.Field<T> extractField(final T[] d) throws org.apache.commons.math3.exception.NoDataException {
		if ((d.length) == 0) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_ROW);
		} 
		return d[0].getField();
	}

	@java.lang.Deprecated
	protected static <T extends org.apache.commons.math3.FieldElement<T>>T[][] buildArray(final org.apache.commons.math3.Field<T> field, final int rows, final int columns) {
		return org.apache.commons.math3.util.MathArrays.buildArray(field, rows, columns);
	}

	@java.lang.Deprecated
	protected static <T extends org.apache.commons.math3.FieldElement<T>>T[] buildArray(final org.apache.commons.math3.Field<T> field, final int length) {
		return org.apache.commons.math3.util.MathArrays.buildArray(field, length);
	}

	public org.apache.commons.math3.Field<T> getField() {
		return field;
	}

	public abstract org.apache.commons.math3.linear.FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws org.apache.commons.math3.exception.NotStrictlyPositiveException;

	public abstract org.apache.commons.math3.linear.FieldMatrix<T> copy();

	public org.apache.commons.math3.linear.FieldMatrix<T> add(org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkAdditionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; ++row) {
			for (int col = 0 ; col < columnCount ; ++col) {
				out.setEntry(row, col, getEntry(row, col).add(m.getEntry(row, col)));
			}
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> subtract(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkSubtractionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; ++row) {
			for (int col = 0 ; col < columnCount ; ++col) {
				out.setEntry(row, col, getEntry(row, col).subtract(m.getEntry(row, col)));
			}
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> scalarAdd(final T d) {
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; ++row) {
			for (int col = 0 ; col < columnCount ; ++col) {
				out.setEntry(row, col, getEntry(row, col).add(d));
			}
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> scalarMultiply(final T d) {
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(rowCount, columnCount);
		for (int row = 0 ; row < rowCount ; ++row) {
			for (int col = 0 ; col < columnCount ; ++col) {
				out.setEntry(row, col, getEntry(row, col).multiply(d));
			}
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> multiply(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		checkMultiplicationCompatible(m);
		final int nRows = getRowDimension();
		final int nCols = m.getColumnDimension();
		final int nSum = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(nRows, nCols);
		for (int row = 0 ; row < nRows ; ++row) {
			for (int col = 0 ; col < nCols ; ++col) {
				T sum = field.getZero();
				for (int i = 0 ; i < nSum ; ++i) {
					sum = sum.add(getEntry(row, i).multiply(m.getEntry(i, col)));
				}
				out.setEntry(row, col, sum);
			}
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> preMultiply(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		return m.multiply(org.apache.commons.math3.linear.AbstractFieldMatrix.this);
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> power(final int p) throws org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.linear.NonSquareMatrixException {
		if (p < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(p);
		} 
		if (!(isSquare())) {
			throw new org.apache.commons.math3.linear.NonSquareMatrixException(getRowDimension() , getColumnDimension());
		} 
		if (p == 0) {
			return org.apache.commons.math3.linear.MatrixUtils.createFieldIdentityMatrix(org.apache.commons.math3.linear.AbstractFieldMatrix.this.getField(), org.apache.commons.math3.linear.AbstractFieldMatrix.this.getRowDimension());
		} 
		if (p == 1) {
			return org.apache.commons.math3.linear.AbstractFieldMatrix.this.copy();
		} 
		final int power = p - 1;
		final char[] binaryRepresentation = java.lang.Integer.toBinaryString(power).toCharArray();
		final java.util.ArrayList<java.lang.Integer> nonZeroPositions = new java.util.ArrayList<java.lang.Integer>();
		for (int i = 0 ; i < (binaryRepresentation.length) ; ++i) {
			if ((binaryRepresentation[i]) == '1') {
				final int pos = ((binaryRepresentation.length) - i) - 1;
				nonZeroPositions.add(pos);
			} 
		}
		java.util.ArrayList<org.apache.commons.math3.linear.FieldMatrix<T>> results = new java.util.ArrayList<org.apache.commons.math3.linear.FieldMatrix<T>>(binaryRepresentation.length);
		results.add(0, org.apache.commons.math3.linear.AbstractFieldMatrix.this.copy());
		for (int i = 1 ; i < (binaryRepresentation.length) ; ++i) {
			final org.apache.commons.math3.linear.FieldMatrix<T> s = results.get((i - 1));
			final org.apache.commons.math3.linear.FieldMatrix<T> r = s.multiply(s);
			results.add(i, r);
		}
		org.apache.commons.math3.linear.FieldMatrix<T> result = org.apache.commons.math3.linear.AbstractFieldMatrix.this.copy();
		for (java.lang.Integer i : nonZeroPositions) {
			result = result.multiply(results.get(i));
		}
		return result;
	}

	public T[][] getData() {
		final T[][] data = org.apache.commons.math3.util.MathArrays.buildArray(field, getRowDimension(), getColumnDimension());
		for (int i = 0 ; i < (data.length) ; ++i) {
			final T[] dataI = data[i];
			for (int j = 0 ; j < (dataI.length) ; ++j) {
				dataI[j] = getEntry(i, j);
			}
		}
		return data;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		final org.apache.commons.math3.linear.FieldMatrix<T> subMatrix = createMatrix(((endRow - startRow) + 1), ((endColumn - startColumn) + 1));
		for (int i = startRow ; i <= endRow ; ++i) {
			for (int j = startColumn ; j <= endColumn ; ++j) {
				subMatrix.setEntry((i - startRow), (j - startColumn), getEntry(i, j));
			}
		}
		return subMatrix;
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(selectedRows, selectedColumns);
		final org.apache.commons.math3.linear.FieldMatrix<T> subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
		subMatrix.walkInOptimizedOrder(new org.apache.commons.math3.linear.DefaultFieldMatrixChangingVisitor<T>(field.getZero()) {
			@java.lang.Override
			public T visit(final int row, final int column, final T value) {
				return getEntry(selectedRows[row], selectedColumns[column]);
			}
		});
		return subMatrix;
	}

	public void copySubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn, final T[][] destination) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		final int rowsCount = (endRow + 1) - startRow;
		final int columnsCount = (endColumn + 1) - startColumn;
		if (((destination.length) < rowsCount) || ((destination[0].length) < columnsCount)) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(destination.length , destination[0].length , rowsCount , columnsCount);
		} 
		walkInOptimizedOrder(new org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor<T>(field.getZero()) {
			private int startRow;

			private int startColumn;

			@java.lang.Override
			public void start(final int rows, final int columns, final int startRow, final int endRow, final int startColumn, final int endColumn) {
				this.startRow = startRow;
				this.startColumn = startColumn;
			}

			@java.lang.Override
			public void visit(final int row, final int column, final T value) {
				destination[(row - (startRow))][(column - (startColumn))] = value;
			}
		}, startRow, endRow, startColumn, endColumn);
	}

	public void copySubMatrix(int[] selectedRows, int[] selectedColumns, T[][] destination) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkSubMatrixIndex(selectedRows, selectedColumns);
		if (((destination.length) < (selectedRows.length)) || ((destination[0].length) < (selectedColumns.length))) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(destination.length , destination[0].length , selectedRows.length , selectedColumns.length);
		} 
		for (int i = 0 ; i < (selectedRows.length) ; i++) {
			final T[] destinationI = destination[i];
			for (int j = 0 ; j < (selectedColumns.length) ; j++) {
				destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]);
			}
		}
	}

	public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException {
		if (subMatrix == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		final int nRows = subMatrix.length;
		if (nRows == 0) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_ROW);
		} 
		final int nCols = subMatrix[0].length;
		if (nCols == 0) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.AT_LEAST_ONE_COLUMN);
		} 
		for (int r = 1 ; r < nRows ; ++r) {
			if ((subMatrix[r].length) != nCols) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(nCols , subMatrix[r].length);
			} 
		}
		checkRowIndex(row);
		checkColumnIndex(column);
		checkRowIndex(((nRows + row) - 1));
		checkColumnIndex(((nCols + column) - 1));
		for (int i = 0 ; i < nRows ; ++i) {
			for (int j = 0 ; j < nCols ; ++j) {
				setEntry((row + i), (column + j), subMatrix[i][j]);
			}
		}
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> getRowMatrix(final int row) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		final int nCols = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(1, nCols);
		for (int i = 0 ; i < nCols ; ++i) {
			out.setEntry(0, i, getEntry(row, i));
		}
		return out;
	}

	public void setRowMatrix(final int row, final org.apache.commons.math3.linear.FieldMatrix<T> matrix) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkRowIndex(row);
		final int nCols = getColumnDimension();
		if (((matrix.getRowDimension()) != 1) || ((matrix.getColumnDimension()) != nCols)) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(matrix.getRowDimension() , matrix.getColumnDimension() , 1 , nCols);
		} 
		for (int i = 0 ; i < nCols ; ++i) {
			setEntry(row, i, matrix.getEntry(0, i));
		}
	}

	public org.apache.commons.math3.linear.FieldMatrix<T> getColumnMatrix(final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkColumnIndex(column);
		final int nRows = getRowDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(nRows, 1);
		for (int i = 0 ; i < nRows ; ++i) {
			out.setEntry(i, 0, getEntry(i, column));
		}
		return out;
	}

	public void setColumnMatrix(final int column, final org.apache.commons.math3.linear.FieldMatrix<T> matrix) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkColumnIndex(column);
		final int nRows = getRowDimension();
		if (((matrix.getRowDimension()) != nRows) || ((matrix.getColumnDimension()) != 1)) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(matrix.getRowDimension() , matrix.getColumnDimension() , nRows , 1);
		} 
		for (int i = 0 ; i < nRows ; ++i) {
			setEntry(i, column, matrix.getEntry(i, 0));
		}
	}

	public org.apache.commons.math3.linear.FieldVector<T> getRowVector(final int row) throws org.apache.commons.math3.exception.OutOfRangeException {
		return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , getRow(row) , false);
	}

	public void setRowVector(final int row, final org.apache.commons.math3.linear.FieldVector<T> vector) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkRowIndex(row);
		final int nCols = getColumnDimension();
		if ((vector.getDimension()) != nCols) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(1 , vector.getDimension() , 1 , nCols);
		} 
		for (int i = 0 ; i < nCols ; ++i) {
			setEntry(row, i, vector.getEntry(i));
		}
	}

	public org.apache.commons.math3.linear.FieldVector<T> getColumnVector(final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , getColumn(column) , false);
	}

	public void setColumnVector(final int column, final org.apache.commons.math3.linear.FieldVector<T> vector) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkColumnIndex(column);
		final int nRows = getRowDimension();
		if ((vector.getDimension()) != nRows) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(vector.getDimension() , 1 , nRows , 1);
		} 
		for (int i = 0 ; i < nRows ; ++i) {
			setEntry(i, column, vector.getEntry(i));
		}
	}

	public T[] getRow(final int row) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(row);
		final int nCols = getColumnDimension();
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nCols);
		for (int i = 0 ; i < nCols ; ++i) {
			out[i] = getEntry(row, i);
		}
		return out;
	}

	public void setRow(final int row, final T[] array) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkRowIndex(row);
		final int nCols = getColumnDimension();
		if ((array.length) != nCols) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(1 , array.length , 1 , nCols);
		} 
		for (int i = 0 ; i < nCols ; ++i) {
			setEntry(row, i, array[i]);
		}
	}

	public T[] getColumn(final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		checkColumnIndex(column);
		final int nRows = getRowDimension();
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nRows);
		for (int i = 0 ; i < nRows ; ++i) {
			out[i] = getEntry(i, column);
		}
		return out;
	}

	public void setColumn(final int column, final T[] array) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		checkColumnIndex(column);
		final int nRows = getRowDimension();
		if ((array.length) != nRows) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(array.length , 1 , nRows , 1);
		} 
		for (int i = 0 ; i < nRows ; ++i) {
			setEntry(i, column, array[i]);
		}
	}

	public abstract T getEntry(int row, int column) throws org.apache.commons.math3.exception.OutOfRangeException;

	public abstract void setEntry(int row, int column, T value) throws org.apache.commons.math3.exception.OutOfRangeException;

	public abstract void addToEntry(int row, int column, T increment) throws org.apache.commons.math3.exception.OutOfRangeException;

	public abstract void multiplyEntry(int row, int column, T factor) throws org.apache.commons.math3.exception.OutOfRangeException;

	public org.apache.commons.math3.linear.FieldMatrix<T> transpose() {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		final org.apache.commons.math3.linear.FieldMatrix<T> out = createMatrix(nCols, nRows);
		walkInOptimizedOrder(new org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor<T>(field.getZero()) {
			@java.lang.Override
			public void visit(final int row, final int column, final T value) {
				out.setEntry(column, row, value);
			}
		});
		return out;
	}

	public boolean isSquare() {
		return (getColumnDimension()) == (getRowDimension());
	}

	public abstract int getRowDimension();

	public abstract int getColumnDimension();

	public T getTrace() throws org.apache.commons.math3.linear.NonSquareMatrixException {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if (nRows != nCols) {
			throw new org.apache.commons.math3.linear.NonSquareMatrixException(nRows , nCols);
		} 
		T trace = field.getZero();
		for (int i = 0 ; i < nRows ; ++i) {
			trace = trace.add(getEntry(i, i));
		}
		return trace;
	}

	public T[] operate(final T[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if ((v.length) != nCols) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(v.length , nCols);
		} 
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nRows);
		for (int row = 0 ; row < nRows ; ++row) {
			T sum = field.getZero();
			for (int i = 0 ; i < nCols ; ++i) {
				sum = sum.add(getEntry(row, i).multiply(v[i]));
			}
			out[row] = sum;
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldVector<T> operate(final org.apache.commons.math3.linear.FieldVector<T> v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		try {
			return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , operate(((org.apache.commons.math3.linear.ArrayFieldVector<T>)(v)).getDataRef()) , false);
		} catch (java.lang.ClassCastException cce) {
			final int nRows = getRowDimension();
			final int nCols = getColumnDimension();
			if ((v.getDimension()) != nCols) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(v.getDimension() , nCols);
			} 
			final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nRows);
			for (int row = 0 ; row < nRows ; ++row) {
				T sum = field.getZero();
				for (int i = 0 ; i < nCols ; ++i) {
					sum = sum.add(getEntry(row, i).multiply(v.getEntry(i)));
				}
				out[row] = sum;
			}
			return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , out , false);
		}
	}

	public T[] preMultiply(final T[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if ((v.length) != nRows) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(v.length , nRows);
		} 
		final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nCols);
		for (int col = 0 ; col < nCols ; ++col) {
			T sum = field.getZero();
			for (int i = 0 ; i < nRows ; ++i) {
				sum = sum.add(getEntry(i, col).multiply(v[i]));
			}
			out[col] = sum;
		}
		return out;
	}

	public org.apache.commons.math3.linear.FieldVector<T> preMultiply(final org.apache.commons.math3.linear.FieldVector<T> v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		try {
			return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , preMultiply(((org.apache.commons.math3.linear.ArrayFieldVector<T>)(v)).getDataRef()) , false);
		} catch (java.lang.ClassCastException cce) {
			final int nRows = getRowDimension();
			final int nCols = getColumnDimension();
			if ((v.getDimension()) != nRows) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(v.getDimension() , nRows);
			} 
			final T[] out = org.apache.commons.math3.util.MathArrays.buildArray(field, nCols);
			for (int col = 0 ; col < nCols ; ++col) {
				T sum = field.getZero();
				for (int i = 0 ; i < nRows ; ++i) {
					sum = sum.add(getEntry(i, col).multiply(v.getEntry(i)));
				}
				out[col] = sum;
			}
			return new org.apache.commons.math3.linear.ArrayFieldVector<T>(field , out , false);
		}
	}

	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int row = 0 ; row < rows ; ++row) {
			for (int column = 0 ; column < columns ; ++column) {
				final T oldValue = getEntry(row, column);
				final T newValue = visitor.visit(row, column, oldValue);
				setEntry(row, column, newValue);
			}
		}
		return visitor.end();
	}

	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int row = 0 ; row < rows ; ++row) {
			for (int column = 0 ; column < columns ; ++column) {
				visitor.visit(row, column, getEntry(row, column));
			}
		}
		return visitor.end();
	}

	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int row = startRow ; row <= endRow ; ++row) {
			for (int column = startColumn ; column <= endColumn ; ++column) {
				final T oldValue = getEntry(row, column);
				final T newValue = visitor.visit(row, column, oldValue);
				setEntry(row, column, newValue);
			}
		}
		return visitor.end();
	}

	public T walkInRowOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int row = startRow ; row <= endRow ; ++row) {
			for (int column = startColumn ; column <= endColumn ; ++column) {
				visitor.visit(row, column, getEntry(row, column));
			}
		}
		return visitor.end();
	}

	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int column = 0 ; column < columns ; ++column) {
			for (int row = 0 ; row < rows ; ++row) {
				final T oldValue = getEntry(row, column);
				final T newValue = visitor.visit(row, column, oldValue);
				setEntry(row, column, newValue);
			}
		}
		return visitor.end();
	}

	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor) {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int column = 0 ; column < columns ; ++column) {
			for (int row = 0 ; row < rows ; ++row) {
				visitor.visit(row, column, getEntry(row, column));
			}
		}
		return visitor.end();
	}

	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int column = startColumn ; column <= endColumn ; ++column) {
			for (int row = startRow ; row <= endRow ; ++row) {
				final T oldValue = getEntry(row, column);
				final T newValue = visitor.visit(row, column, oldValue);
				setEntry(row, column, newValue);
			}
		}
		return visitor.end();
	}

	public T walkInColumnOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int column = startColumn ; column <= endColumn ; ++column) {
			for (int row = startRow ; row <= endRow ; ++row) {
				visitor.visit(row, column, getEntry(row, column));
			}
		}
		return visitor.end();
	}

	public T walkInOptimizedOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor) {
		return walkInRowOrder(visitor);
	}

	public T walkInOptimizedOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor) {
		return walkInRowOrder(visitor);
	}

	public T walkInOptimizedOrder(final org.apache.commons.math3.linear.FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
	}

	public T walkInOptimizedOrder(final org.apache.commons.math3.linear.FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
	}

	@java.lang.Override
	public java.lang.String toString() {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		final java.lang.StringBuffer res = new java.lang.StringBuffer();
		java.lang.String fullClassName = getClass().getName();
		java.lang.String shortClassName = fullClassName.substring(((fullClassName.lastIndexOf('.')) + 1));
		res.append(shortClassName).append("{");
		for (int i = 0 ; i < nRows ; ++i) {
			if (i > 0) {
				res.append(",");
			} 
			res.append("{");
			for (int j = 0 ; j < nCols ; ++j) {
				if (j > 0) {
					res.append(",");
				} 
				res.append(getEntry(i, j));
			}
			res.append("}");
		}
		res.append("}");
		return res.toString();
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object object) {
		if (object == (org.apache.commons.math3.linear.AbstractFieldMatrix.this)) {
			return true;
		} 
		if ((object instanceof org.apache.commons.math3.linear.FieldMatrix<?>) == false) {
			return false;
		} 
		org.apache.commons.math3.linear.FieldMatrix<?> m = ((org.apache.commons.math3.linear.FieldMatrix<?>)(object));
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if (((m.getColumnDimension()) != nCols) || ((m.getRowDimension()) != nRows)) {
			return false;
		} 
		for (int row = 0 ; row < nRows ; ++row) {
			for (int col = 0 ; col < nCols ; ++col) {
				if (!(getEntry(row, col).equals(m.getEntry(row, col)))) {
					return false;
				} 
			}
		}
		return true;
	}

	@java.lang.Override
	public int hashCode() {
		int ret = 322562;
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		ret = (ret * 31) + nRows;
		ret = (ret * 31) + nCols;
		for (int row = 0 ; row < nRows ; ++row) {
			for (int col = 0 ; col < nCols ; ++col) {
				ret = (ret * 31) + (((11 * (row + 1)) + (17 * (col + 1))) * (getEntry(row, col).hashCode()));
			}
		}
		return ret;
	}

	protected void checkRowIndex(final int row) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((row < 0) || (row >= (getRowDimension()))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.ROW_INDEX , row , 0 , ((getRowDimension()) - 1));
		} 
	}

	protected void checkColumnIndex(final int column) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((column < 0) || (column >= (getColumnDimension()))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.COLUMN_INDEX , column , 0 , ((getColumnDimension()) - 1));
		} 
	}

	protected void checkSubMatrixIndex(final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.OutOfRangeException {
		checkRowIndex(startRow);
		checkRowIndex(endRow);
		if (endRow < startRow) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW , endRow , startRow , true);
		} 
		checkColumnIndex(startColumn);
		checkColumnIndex(endColumn);
		if (endColumn < startColumn) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN , endColumn , startColumn , true);
		} 
	}

	protected void checkSubMatrixIndex(final int[] selectedRows, final int[] selectedColumns) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException {
		if ((selectedRows == null) || (selectedColumns == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if (((selectedRows.length) == 0) || ((selectedColumns.length) == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		for (final int row : selectedRows) {
			checkRowIndex(row);
		}
		for (final int column : selectedColumns) {
			checkColumnIndex(column);
		}
	}

	protected void checkAdditionCompatible(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		if (((getRowDimension()) != (m.getRowDimension())) || ((getColumnDimension()) != (m.getColumnDimension()))) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(m.getRowDimension() , m.getColumnDimension() , getRowDimension() , getColumnDimension());
		} 
	}

	protected void checkSubtractionCompatible(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.linear.MatrixDimensionMismatchException {
		if (((getRowDimension()) != (m.getRowDimension())) || ((getColumnDimension()) != (m.getColumnDimension()))) {
			throw new org.apache.commons.math3.linear.MatrixDimensionMismatchException(m.getRowDimension() , m.getColumnDimension() , getRowDimension() , getColumnDimension());
		} 
	}

	protected void checkMultiplicationCompatible(final org.apache.commons.math3.linear.FieldMatrix<T> m) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((getColumnDimension()) != (m.getRowDimension())) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(m.getRowDimension() , getColumnDimension());
		} 
	}
}

