package org.apache.commons.lang.math;


public final class IntRange extends org.apache.commons.lang.math.Range implements java.io.Serializable {
	private static final long serialVersionUID = 71849363892730L;

	private final int min;

	private final int max;

	private transient java.lang.Integer minObject = null;

	private transient java.lang.Integer maxObject = null;

	private transient int hashCode = 0;

	private transient java.lang.String toString = null;

	public IntRange(int number) {
		super();
		this.min = number;
		this.max = number;
	}

	public IntRange(java.lang.Number number) {
		super();
		if (number == null) {
			throw new java.lang.IllegalArgumentException("The number must not be null");
		} 
		this.min = number.intValue();
		this.max = number.intValue();
		if (number instanceof java.lang.Integer) {
			org.apache.commons.lang.math.IntRange.this.minObject = ((java.lang.Integer)(number));
			org.apache.commons.lang.math.IntRange.this.maxObject = ((java.lang.Integer)(number));
		} 
	}

	public IntRange(int number1 ,int number2) {
		super();
		if (number2 < number1) {
			this.min = number2;
			this.max = number1;
		} else {
			this.min = number1;
			this.max = number2;
		}
	}

	public IntRange(java.lang.Number number1 ,java.lang.Number number2) {
		super();
		if ((number1 == null) || (number2 == null)) {
			throw new java.lang.IllegalArgumentException("The numbers must not be null");
		} 
		int number1val = number1.intValue();
		int number2val = number2.intValue();
		if (number2val < number1val) {
			this.min = number2val;
			this.max = number1val;
			if (number2 instanceof java.lang.Integer) {
				org.apache.commons.lang.math.IntRange.this.minObject = ((java.lang.Integer)(number2));
			} 
			if (number1 instanceof java.lang.Integer) {
				org.apache.commons.lang.math.IntRange.this.maxObject = ((java.lang.Integer)(number1));
			} 
		} else {
			this.min = number1val;
			this.max = number2val;
			if (number1 instanceof java.lang.Integer) {
				org.apache.commons.lang.math.IntRange.this.minObject = ((java.lang.Integer)(number1));
			} 
			if (number2 instanceof java.lang.Integer) {
				org.apache.commons.lang.math.IntRange.this.maxObject = ((java.lang.Integer)(number2));
			} 
		}
	}

	@java.lang.Override
	public java.lang.Number getMinimumNumber() {
		if ((minObject) == null) {
			minObject = new java.lang.Integer(min);
		} 
		return minObject;
	}

	@java.lang.Override
	public long getMinimumLong() {
		return min;
	}

	@java.lang.Override
	public int getMinimumInteger() {
		return min;
	}

	@java.lang.Override
	public double getMinimumDouble() {
		return min;
	}

	@java.lang.Override
	public float getMinimumFloat() {
		return min;
	}

	@java.lang.Override
	public java.lang.Number getMaximumNumber() {
		if ((maxObject) == null) {
			maxObject = new java.lang.Integer(max);
		} 
		return maxObject;
	}

	@java.lang.Override
	public long getMaximumLong() {
		return max;
	}

	@java.lang.Override
	public int getMaximumInteger() {
		return max;
	}

	@java.lang.Override
	public double getMaximumDouble() {
		return max;
	}

	@java.lang.Override
	public float getMaximumFloat() {
		return max;
	}

	@java.lang.Override
	public boolean containsNumber(java.lang.Number number) {
		if (number == null) {
			return false;
		} 
		return containsInteger(number.intValue());
	}

	@java.lang.Override
	public boolean containsInteger(int value) {
		return (value >= (min)) && (value <= (max));
	}

	@java.lang.Override
	public boolean containsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return (containsInteger(range.getMinimumInteger())) && (containsInteger(range.getMaximumInteger()));
	}

	@java.lang.Override
	public boolean overlapsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return ((range.containsInteger(min)) || (range.containsInteger(max))) || (containsInteger(range.getMinimumInteger()));
	}

	@java.lang.Override
	public boolean equals(java.lang.Object obj) {
		if (obj == (org.apache.commons.lang.math.IntRange.this)) {
			return true;
		} 
		if ((obj instanceof org.apache.commons.lang.math.IntRange) == false) {
			return false;
		} 
		org.apache.commons.lang.math.IntRange range = ((org.apache.commons.lang.math.IntRange)(obj));
		return ((min) == (range.min)) && ((max) == (range.max));
	}

	@java.lang.Override
	public int hashCode() {
		int temp = hashCode;
		if (temp == 0) {
			temp = 17;
			temp = (37 * temp) + (getClass().hashCode());
			temp = (37 * temp) + (min);
			temp = (37 * temp) + (max);
			hashCode = temp;
		} 
		return hashCode;
	}

	@java.lang.Override
	public java.lang.String toString() {
		if ((toString) == null) {
			java.lang.StringBuilder buf = new java.lang.StringBuilder(32);
			buf.append("Range[");
			buf.append(min);
			buf.append(',');
			buf.append(max);
			buf.append(']');
			toString = buf.toString();
		} 
		return toString;
	}

	public int[] toArray() {
		int[] array = new int[((max) - (min)) + 1];
		for (int i = 0 ; i < (array.length) ; i++) {
			array[i] = (min) + i;
		}
		return array;
	}
}

