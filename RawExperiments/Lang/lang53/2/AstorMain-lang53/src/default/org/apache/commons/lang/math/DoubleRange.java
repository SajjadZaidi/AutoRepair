package org.apache.commons.lang.math;


public final class DoubleRange extends org.apache.commons.lang.math.Range implements java.io.Serializable {
	private static final long serialVersionUID = 71849363892740L;

	private final double min;

	private final double max;

	private transient java.lang.Double minObject = null;

	private transient java.lang.Double maxObject = null;

	private transient int hashCode = 0;

	private transient java.lang.String toString = null;

	public DoubleRange(double number) {
		super();
		if (java.lang.Double.isNaN(number)) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		this.min = number;
		this.max = number;
	}

	public DoubleRange(java.lang.Number number) {
		super();
		if (number == null) {
			throw new java.lang.IllegalArgumentException("The number must not be null");
		} 
		this.min = number.doubleValue();
		this.max = number.doubleValue();
		if ((java.lang.Double.isNaN(min)) || (java.lang.Double.isNaN(max))) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		if (number instanceof java.lang.Double) {
			org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number));
			org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number));
		} 
	}

	public DoubleRange(double number1 ,double number2) {
		super();
		if ((java.lang.Double.isNaN(number1)) || (java.lang.Double.isNaN(number2))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2 < number1) {
			this.min = number2;
			this.max = number1;
		} else {
			this.min = number1;
			this.max = number2;
		}
	}

	public DoubleRange(java.lang.Number number1 ,java.lang.Number number2) {
		super();
		if ((number1 == null) || (number2 == null)) {
			throw new java.lang.IllegalArgumentException("The numbers must not be null");
		} 
		double number1val = number1.doubleValue();
		double number2val = number2.doubleValue();
		if ((java.lang.Double.isNaN(number1val)) || (java.lang.Double.isNaN(number2val))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2val < number1val) {
			this.min = number2val;
			this.max = number1val;
			if (number2 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number2));
			} 
			if (number1 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number1));
			} 
		} else {
			this.min = number1val;
			this.max = number2val;
			if (number1 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number1));
			} 
			if (number2 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number2));
			} 
		}
	}

	public java.lang.Number getMinimumNumber() {
		if ((minObject) == null) {
			minObject = new java.lang.Double(min);
		} 
		return minObject;
	}

	public long getMinimumLong() {
		return ((long)(min));
	}

	public int getMinimumInteger() {
		return ((int)(min));
	}

	public double getMinimumDouble() {
		return min;
	}

	public float getMinimumFloat() {
		return ((float)(min));
	}

	public java.lang.Number getMaximumNumber() {
		if ((maxObject) == null) {
			maxObject = new java.lang.Double(max);
		} 
		return maxObject;
	}

	public long getMaximumLong() {
		return ((long)(max));
	}

	public int getMaximumInteger() {
		return ((int)(max));
	}

	public double getMaximumDouble() {
		return max;
	}

	public float getMaximumFloat() {
		return ((float)(max));
	}

	public boolean containsNumber(java.lang.Number number) {
		if (number == null) {
			return false;
		} 
		return containsDouble(number.doubleValue());
	}

	public boolean containsDouble(double value) {
		return (value >= (min)) && (value <= (max));
	}

	public boolean containsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return (containsDouble(range.getMinimumDouble())) && (containsDouble(range.getMaximumDouble()));
	}

	public boolean overlapsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return ((range.containsDouble(min)) || (range.containsDouble(max))) || (containsDouble(range.getMinimumDouble()));
	}

	public boolean equals(java.lang.Object obj) {
		if (obj == (org.apache.commons.lang.math.DoubleRange.this)) {
			return true;
		} 
		if ((obj instanceof org.apache.commons.lang.math.DoubleRange) == false) {
			return false;
		} 
		org.apache.commons.lang.math.DoubleRange range = ((org.apache.commons.lang.math.DoubleRange)(obj));
		return ((java.lang.Double.doubleToLongBits(min)) == (java.lang.Double.doubleToLongBits(range.min))) && ((java.lang.Double.doubleToLongBits(max)) == (java.lang.Double.doubleToLongBits(range.max)));
	}

	public int hashCode() {
		if ((hashCode) == 0) {
			hashCode = 17;
			hashCode = (37 * (hashCode)) + (getClass().hashCode());
			long lng = java.lang.Double.doubleToLongBits(min);
			hashCode = (37 * (hashCode)) + ((int)((lng ^ (lng >> 32))));
			lng = java.lang.Double.doubleToLongBits(max);
			hashCode = (37 * (hashCode)) + ((int)((lng ^ (lng >> 32))));
		} 
		return hashCode;
	}

	public java.lang.String toString() {
		if ((toString) == null) {
			java.lang.StringBuffer buf = new java.lang.StringBuffer(32);
			buf.append("Range[");
			buf.append(min);
			buf.append(',');
			buf.append(max);
			buf.append(']');
			toString = buf.toString();
		} 
		return toString;
	}
}

public final class DoubleRange extends org.apache.commons.lang.math.Range implements java.io.Serializable {
	private static final long serialVersionUID = 71849363892740L;

	private final double min;

	private final double max;

	private transient java.lang.Double minObject = null;

	private transient java.lang.Double maxObject = null;

	private transient int hashCode = 0;

	private transient java.lang.String toString = null;

	public DoubleRange(double number) {
		super();
		if (java.lang.Double.isNaN(number)) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		this.min = number;
		this.max = number;
	}

	public DoubleRange(java.lang.Number number) {
		super();
		if (number == null) {
			throw new java.lang.IllegalArgumentException("The number must not be null");
		} 
		this.min = number.doubleValue();
		this.max = number.doubleValue();
		if ((java.lang.Double.isNaN(min)) || (java.lang.Double.isNaN(max))) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		if (number instanceof java.lang.Double) {
			org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number));
			org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number));
		} 
	}

	public DoubleRange(double number1 ,double number2) {
		super();
		if ((java.lang.Double.isNaN(number1)) || (java.lang.Double.isNaN(number2))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2 < number1) {
			this.min = number2;
			this.max = number1;
		} else {
			this.min = number1;
			this.max = number2;
		}
	}

	public DoubleRange(java.lang.Number number1 ,java.lang.Number number2) {
		super();
		if ((number1 == null) || (number2 == null)) {
			throw new java.lang.IllegalArgumentException("The numbers must not be null");
		} 
		double number1val = number1.doubleValue();
		double number2val = number2.doubleValue();
		if ((java.lang.Double.isNaN(number1val)) || (java.lang.Double.isNaN(number2val))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2val < number1val) {
			this.min = number2val;
			this.max = number1val;
			if (number2 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number2));
			} 
			if (number1 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number1));
			} 
		} else {
			this.min = number1val;
			this.max = number2val;
			if (number1 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.minObject = ((java.lang.Double)(number1));
			} 
			if (number2 instanceof java.lang.Double) {
				org.apache.commons.lang.math.DoubleRange.this.maxObject = ((java.lang.Double)(number2));
			} 
		}
	}

	public java.lang.Number getMinimumNumber() {
		if ((minObject) == null) {
			minObject = new java.lang.Double(min);
		} 
		return minObject;
	}

	public long getMinimumLong() {
		return ((long)(min));
	}

	public int getMinimumInteger() {
		return ((int)(min));
	}

	public double getMinimumDouble() {
		return min;
	}

	public float getMinimumFloat() {
		return ((float)(min));
	}

	public java.lang.Number getMaximumNumber() {
		if ((maxObject) == null) {
			maxObject = new java.lang.Double(max);
		} 
		return maxObject;
	}

	public long getMaximumLong() {
		return ((long)(max));
	}

	public int getMaximumInteger() {
		return ((int)(max));
	}

	public double getMaximumDouble() {
		return max;
	}

	public float getMaximumFloat() {
		return ((float)(max));
	}

	public boolean containsNumber(java.lang.Number number) {
		if (number == null) {
			return false;
		} 
		return containsDouble(number.doubleValue());
	}

	public boolean containsDouble(double value) {
		return (value >= (min)) && (value <= (max));
	}

	public boolean containsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return (containsDouble(range.getMinimumDouble())) && (containsDouble(range.getMaximumDouble()));
	}

	public boolean overlapsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return ((range.containsDouble(min)) || (range.containsDouble(max))) || (containsDouble(range.getMinimumDouble()));
	}

	public boolean equals(java.lang.Object obj) {
		if (obj == (org.apache.commons.lang.math.DoubleRange.this)) {
			return true;
		} 
		if ((obj instanceof org.apache.commons.lang.math.DoubleRange) == false) {
			return false;
		} 
		org.apache.commons.lang.math.DoubleRange range = ((org.apache.commons.lang.math.DoubleRange)(obj));
		return ((java.lang.Double.doubleToLongBits(min)) == (java.lang.Double.doubleToLongBits(range.min))) && ((java.lang.Double.doubleToLongBits(max)) == (java.lang.Double.doubleToLongBits(range.max)));
	}

	public int hashCode() {
		if ((hashCode) == 0) {
			hashCode = 17;
			hashCode = (37 * (hashCode)) + (getClass().hashCode());
			long lng = java.lang.Double.doubleToLongBits(min);
			hashCode = (37 * (hashCode)) + ((int)((lng ^ (lng >> 32))));
			lng = java.lang.Double.doubleToLongBits(max);
			hashCode = (37 * (hashCode)) + ((int)((lng ^ (lng >> 32))));
		} 
		return hashCode;
	}

	public java.lang.String toString() {
		if ((toString) == null) {
			java.lang.StringBuffer buf = new java.lang.StringBuffer(32);
			buf.append("Range[");
			buf.append(min);
			buf.append(',');
			buf.append(max);
			buf.append(']');
			toString = buf.toString();
		} 
		return toString;
	}
}

