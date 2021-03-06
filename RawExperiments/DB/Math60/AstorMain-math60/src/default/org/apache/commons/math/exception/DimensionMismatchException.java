package org.apache.commons.math.exception;


public class DimensionMismatchException extends org.apache.commons.math.exception.MathIllegalNumberException {
	private static final long serialVersionUID = -8415396756375798143L;

	private final int dimension;

	public DimensionMismatchException(org.apache.commons.math.exception.util.Localizable specific ,int wrong ,int expected) {
		super(specific, org.apache.commons.math.exception.util.LocalizedFormats.DIMENSIONS_MISMATCH, wrong, expected);
		dimension = expected;
	}

	public DimensionMismatchException(int wrong ,int expected) {
		this(org.apache.commons.math.exception.util.LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, wrong, expected);
	}

	public int getDimension() {
		return dimension;
	}
}

