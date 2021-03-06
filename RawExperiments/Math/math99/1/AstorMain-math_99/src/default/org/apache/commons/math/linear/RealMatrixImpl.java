package org.apache.commons.math.linear;


public class RealMatrixImpl extends org.apache.commons.math.linear.AbstractRealMatrix implements java.io.Serializable {
	private static final long serialVersionUID = -1067294169172445528L;

	protected double[][] data;

	public RealMatrixImpl() {
	}

	public RealMatrixImpl(final int rowDimension ,final int columnDimension) throws java.lang.IllegalArgumentException {
		super(rowDimension, columnDimension);
		data = new double[rowDimension][columnDimension];
	}

	public RealMatrixImpl(final double[][] d) throws java.lang.IllegalArgumentException , java.lang.NullPointerException {
		copyIn(d);
	}

	public RealMatrixImpl(final double[][] d ,final boolean copyArray) throws java.lang.IllegalArgumentException , java.lang.NullPointerException {
		if (copyArray) {
			copyIn(d);
		} else {
			if (d == null) {
				throw new java.lang.NullPointerException();
			} 
			final int nRows = d.length;
			if (nRows == 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("matrix must have at least one row", null);
			} 
			final int nCols = d[0].length;
			if (nCols == 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("matrix must have at least one column", null);
			} 
			for (int r = 1 ; r < nRows ; r++) {
				if ((d[r].length) != nCols) {
					throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException(("some rows have length {0} while" + " others have length {1}"), new java.lang.Object[]{ nCols , d[r].length });
				} 
			}
			data = d;
		}
	}

	public RealMatrixImpl(final double[] v) {
		final int nRows = v.length;
		data = new double[nRows][1];
		for (int row = 0 ; row < nRows ; row++) {
			data[row][0] = v[row];
		}
	}

	public org.apache.commons.math.linear.RealMatrix createMatrix(final int rowDimension, final int columnDimension) throws java.lang.IllegalArgumentException {
		return new org.apache.commons.math.linear.RealMatrixImpl(rowDimension , columnDimension);
	}

	public org.apache.commons.math.linear.RealMatrix copy() {
		return new org.apache.commons.math.linear.RealMatrixImpl(copyOut() , false);
	}

	public org.apache.commons.math.linear.RealMatrix add(final org.apache.commons.math.linear.RealMatrix m) throws java.lang.IllegalArgumentException {
		try {
			return add(((org.apache.commons.math.linear.RealMatrixImpl)(m)));
		} catch (java.lang.ClassCastException cce) {
			return super.add(m);
		}
	}

	public org.apache.commons.math.linear.RealMatrixImpl add(final org.apache.commons.math.linear.RealMatrixImpl m) throws java.lang.IllegalArgumentException {
		checkAdditionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final double[][] outData = new double[rowCount][columnCount];
		for (int row = 0 ; row < rowCount ; row++) {
			final double[] dataRow = data[row];
			final double[] mRow = m.data[row];
			final double[] outDataRow = outData[row];
			for (int col = 0 ; col < columnCount ; col++) {
				outDataRow[col] = (dataRow[col]) + (mRow[col]);
			}
		}
		return new org.apache.commons.math.linear.RealMatrixImpl(outData , false);
	}

	public org.apache.commons.math.linear.RealMatrix subtract(final org.apache.commons.math.linear.RealMatrix m) throws java.lang.IllegalArgumentException {
		try {
			return subtract(((org.apache.commons.math.linear.RealMatrixImpl)(m)));
		} catch (java.lang.ClassCastException cce) {
			return super.subtract(m);
		}
	}

	public org.apache.commons.math.linear.RealMatrixImpl subtract(final org.apache.commons.math.linear.RealMatrixImpl m) throws java.lang.IllegalArgumentException {
		checkSubtractionCompatible(m);
		final int rowCount = getRowDimension();
		final int columnCount = getColumnDimension();
		final double[][] outData = new double[rowCount][columnCount];
		for (int row = 0 ; row < rowCount ; row++) {
			final double[] dataRow = data[row];
			final double[] mRow = m.data[row];
			final double[] outDataRow = outData[row];
			for (int col = 0 ; col < columnCount ; col++) {
				outDataRow[col] = (dataRow[col]) - (mRow[col]);
			}
		}
		return new org.apache.commons.math.linear.RealMatrixImpl(outData , false);
	}

	public org.apache.commons.math.linear.RealMatrix multiply(final org.apache.commons.math.linear.RealMatrix m) throws java.lang.IllegalArgumentException {
		try {
			return multiply(((org.apache.commons.math.linear.RealMatrixImpl)(m)));
		} catch (java.lang.ClassCastException cce) {
			return super.multiply(m);
		}
	}

	public org.apache.commons.math.linear.RealMatrixImpl multiply(final org.apache.commons.math.linear.RealMatrixImpl m) throws java.lang.IllegalArgumentException {
		checkMultiplicationCompatible(m);
		final int nRows = org.apache.commons.math.linear.RealMatrixImpl.this.getRowDimension();
		final int nCols = m.getColumnDimension();
		final int nSum = org.apache.commons.math.linear.RealMatrixImpl.this.getColumnDimension();
		final double[][] outData = new double[nRows][nCols];
		for (int row = 0 ; row < nRows ; row++) {
			final double[] dataRow = data[row];
			final double[] outDataRow = outData[row];
			for (int col = 0 ; col < nCols ; col++) {
				double sum = 0;
				for (int i = 0 ; i < nSum ; i++) {
					sum += (dataRow[i]) * (m.data[i][col]);
				}
				outDataRow[col] = sum;
			}
		}
		return new org.apache.commons.math.linear.RealMatrixImpl(outData , false);
	}

	public double[][] getData() {
		return copyOut();
	}

	public double[][] getDataRef() {
		return data;
	}

	public void setSubMatrix(final double[][] subMatrix, final int row, final int column) throws org.apache.commons.math.linear.MatrixIndexException {
		if ((data) == null) {
			if (row > 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalStateException("first {0} rows are not initialized yet", new java.lang.Object[]{ row });
			} 
			if (column > 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalStateException("first {0} columns are not initialized yet", new java.lang.Object[]{ column });
			} 
			final int nRows = subMatrix.length;
			if (nRows == 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("matrix must have at least one row", null);
			} 
			final int nCols = subMatrix[0].length;
			if (nCols == 0) {
				throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("matrix must have at least one column", null);
			} 
			data = new double[subMatrix.length][nCols];
			for (int i = 0 ; i < (data.length) ; ++i) {
				if ((subMatrix[i].length) != nCols) {
					throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("some rows have length {0} while others have length {1}", new java.lang.Object[]{ nCols , subMatrix[i].length });
				} 
				java.lang.System.arraycopy(subMatrix[i], 0, data[(i + row)], column, nCols);
			}
		} else {
			super.setSubMatrix(subMatrix, row, column);
		}
	}

	public double getEntry(final int row, final int column) throws org.apache.commons.math.linear.MatrixIndexException {
		try {
			return data[row][column];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new org.apache.commons.math.linear.MatrixIndexException("no entry at indices ({0}, {1}) in a {2}x{3} matrix" , new java.lang.Object[]{ row , column , getRowDimension() , getColumnDimension() });
		}
	}

	public void setEntry(final int row, final int column, final double value) throws org.apache.commons.math.linear.MatrixIndexException {
		try {
			data[row][column] = value;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new org.apache.commons.math.linear.MatrixIndexException("no entry at indices ({0}, {1}) in a {2}x{3} matrix" , new java.lang.Object[]{ row , column , getRowDimension() , getColumnDimension() });
		}
	}

	public void addToEntry(final int row, final int column, final double increment) throws org.apache.commons.math.linear.MatrixIndexException {
		try {
			data[row][column] += increment;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new org.apache.commons.math.linear.MatrixIndexException("no entry at indices ({0}, {1}) in a {2}x{3} matrix" , new java.lang.Object[]{ row , column , getRowDimension() , getColumnDimension() });
		}
	}

	public void multiplyEntry(final int row, final int column, final double factor) throws org.apache.commons.math.linear.MatrixIndexException {
		try {
			data[row][column] *= factor;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new org.apache.commons.math.linear.MatrixIndexException("no entry at indices ({0}, {1}) in a {2}x{3} matrix" , new java.lang.Object[]{ row , column , getRowDimension() , getColumnDimension() });
		}
	}

	public int getRowDimension() {
		return (data) == null ? 0 : data.length;
	}

	public int getColumnDimension() {
		return ((data) == null) || ((data[0]) == null) ? 0 : data[0].length;
	}

	public double[] operate(final double[] v) throws java.lang.IllegalArgumentException {
		final int nRows = org.apache.commons.math.linear.RealMatrixImpl.this.getRowDimension();
		final int nCols = org.apache.commons.math.linear.RealMatrixImpl.this.getColumnDimension();
		if ((v.length) != nCols) {
			throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException(("vector length mismatch:" + " got {0} but expected {1}"), new java.lang.Object[]{ v.length , nCols });
		} 
		final double[] out = new double[nRows];
		for (int row = 0 ; row < nRows ; row++) {
			final double[] dataRow = data[row];
			double sum = 0;
			for (int i = 0 ; i < nCols ; i++) {
				sum += (dataRow[i]) * (v[i]);
			}
			out[row] = sum;
		}
		return out;
	}

	public double[] preMultiply(final double[] v) throws java.lang.IllegalArgumentException {
		final int nRows = getRowDimension();
		final int nCols = getColumnDimension();
		if ((v.length) != nRows) {
			throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException(("vector length mismatch:" + " got {0} but expected {1}"), new java.lang.Object[]{ v.length , nRows });
		} 
		final double[] out = new double[nCols];
		for (int col = 0 ; col < nCols ; ++col) {
			double sum = 0;
			for (int i = 0 ; i < nRows ; ++i) {
				sum += (data[i][col]) * (v[i]);
			}
			out[col] = sum;
		}
		return out;
	}

	public double walkInRowOrder(final org.apache.commons.math.linear.RealMatrixChangingVisitor visitor) throws org.apache.commons.math.linear.MatrixVisitorException {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int i = 0 ; i < rows ; ++i) {
			final double[] rowI = data[i];
			for (int j = 0 ; j < columns ; ++j) {
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInRowOrder(final org.apache.commons.math.linear.RealMatrixPreservingVisitor visitor) throws org.apache.commons.math.linear.MatrixVisitorException {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int i = 0 ; i < rows ; ++i) {
			final double[] rowI = data[i];
			for (int j = 0 ; j < columns ; ++j) {
				visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInRowOrder(final org.apache.commons.math.linear.RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math.linear.MatrixIndexException, org.apache.commons.math.linear.MatrixVisitorException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int i = startRow ; i <= endRow ; ++i) {
			final double[] rowI = data[i];
			for (int j = startColumn ; j <= endColumn ; ++j) {
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInRowOrder(final org.apache.commons.math.linear.RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math.linear.MatrixIndexException, org.apache.commons.math.linear.MatrixVisitorException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int i = startRow ; i <= endRow ; ++i) {
			final double[] rowI = data[i];
			for (int j = startColumn ; j <= endColumn ; ++j) {
				visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInColumnOrder(final org.apache.commons.math.linear.RealMatrixChangingVisitor visitor) throws org.apache.commons.math.linear.MatrixVisitorException {
		final int rows = getRowDimension();
		final int columns = getColumnDimension();
		visitor.start(rows, columns, 0, (rows - 1), 0, (columns - 1));
		for (int j = 0 ; j < columns ; ++j) {
			for (int i = 0 ; i < rows ; ++i) {
				final double[] rowI = data[i];
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInColumnOrder(final org.apache.commons.math.linear.RealMatrixPreservingVisitor visitor) throws org.apache.commons.math.linear.MatrixVisitorException {
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

	public double walkInColumnOrder(final org.apache.commons.math.linear.RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math.linear.MatrixIndexException, org.apache.commons.math.linear.MatrixVisitorException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int j = startColumn ; j <= endColumn ; ++j) {
			for (int i = startRow ; i <= endRow ; ++i) {
				final double[] rowI = data[i];
				rowI[j] = visitor.visit(i, j, rowI[j]);
			}
		}
		return visitor.end();
	}

	public double walkInColumnOrder(final org.apache.commons.math.linear.RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws org.apache.commons.math.linear.MatrixIndexException, org.apache.commons.math.linear.MatrixVisitorException {
		checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
		visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
		for (int j = startColumn ; j <= endColumn ; ++j) {
			for (int i = startRow ; i <= endRow ; ++i) {
				visitor.visit(i, j, data[i][j]);
			}
		}
		return visitor.end();
	}

	private double[][] copyOut() {
		final int nRows = org.apache.commons.math.linear.RealMatrixImpl.this.getRowDimension();
		final double[][] out = new double[nRows][org.apache.commons.math.linear.RealMatrixImpl.this.getColumnDimension()];
		for (int i = 0 ; i < nRows ; i++) {
			java.lang.System.arraycopy(data[i], 0, out[i], 0, data[i].length);
		}
		return out;
	}

	private void copyIn(final double[][] in) {
		setSubMatrix(in, 0, 0);
	}
}

