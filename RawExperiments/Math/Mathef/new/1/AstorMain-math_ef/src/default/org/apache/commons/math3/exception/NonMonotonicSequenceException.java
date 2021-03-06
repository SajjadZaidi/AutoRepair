package org.apache.commons.math3.exception;


public class NonMonotonicSequenceException extends org.apache.commons.math3.exception.MathIllegalNumberException {
	private static final long serialVersionUID = 3596849179428944575L;

	private final org.apache.commons.math3.util.MathArrays.OrderDirection direction;

	private final boolean strict;

	private final int index;

	private final java.lang.Number previous;

	public NonMonotonicSequenceException(java.lang.Number wrong ,java.lang.Number previous ,int index) {
		this(wrong, previous, index, org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING, true);
	}

	public NonMonotonicSequenceException(java.lang.Number wrong ,java.lang.Number previous ,int index ,org.apache.commons.math3.util.MathArrays.OrderDirection direction ,boolean strict) {
		super((direction == (org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING) ? strict ? org.apache.commons.math3.exception.util.LocalizedFormats.NOT_STRICTLY_INCREASING_SEQUENCE : org.apache.commons.math3.exception.util.LocalizedFormats.NOT_INCREASING_SEQUENCE : strict ? org.apache.commons.math3.exception.util.LocalizedFormats.NOT_STRICTLY_DECREASING_SEQUENCE : org.apache.commons.math3.exception.util.LocalizedFormats.NOT_DECREASING_SEQUENCE), wrong, previous, java.lang.Integer.valueOf(index), java.lang.Integer.valueOf((index - 1)));
		this.direction = direction;
		this.strict = strict;
		this.index = index;
		this.previous = previous;
	}

	public org.apache.commons.math3.util.MathArrays.OrderDirection getDirection() {
		return direction;
	}

	public boolean getStrict() {
		return strict;
	}

	public int getIndex() {
		return index;
	}

	public java.lang.Number getPrevious() {
		return previous;
	}
}

