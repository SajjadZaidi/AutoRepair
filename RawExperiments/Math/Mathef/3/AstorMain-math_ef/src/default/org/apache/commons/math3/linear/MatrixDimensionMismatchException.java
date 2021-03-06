package org.apache.commons.math3.linear;


public class MatrixDimensionMismatchException extends org.apache.commons.math3.exception.MultiDimensionMismatchException {
	private static final long serialVersionUID = -8415396756375798143L;

	public MatrixDimensionMismatchException(int wrongRowDim ,int wrongColDim ,int expectedRowDim ,int expectedColDim) {
		super(org.apache.commons.math3.exception.util.LocalizedFormats.DIMENSIONS_MISMATCH_2x2, new java.lang.Integer[]{ wrongRowDim , wrongColDim }, new java.lang.Integer[]{ expectedRowDim , expectedColDim });
	}

	public int getWrongRowDimension() {
		return getWrongDimension(0);
	}

	public int getExpectedRowDimension() {
		return getExpectedDimension(0);
	}

	public int getWrongColumnDimension() {
		return getWrongDimension(1);
	}

	public int getExpectedColumnDimension() {
		return getExpectedDimension(1);
	}
}

