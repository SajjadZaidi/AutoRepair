package org.apache.commons.lang.math;


public final class FloatRange extends org.apache.commons.lang.math.Range implements java.io.Serializable {
	private static final long serialVersionUID = 71849363892750L;

	private final float min;

	private final float max;

	private transient java.lang.Float minObject = null;

	private transient java.lang.Float maxObject = null;

	private transient int hashCode = 0;

	private transient java.lang.String toString = null;

	public FloatRange(float number) {
		super();
		if (java.lang.Float.isNaN(number)) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		this.min = number;
		this.max = number;
	}

	public FloatRange(java.lang.Number number) {
		super();
		if (number == null) {
			throw new java.lang.IllegalArgumentException("The number must not be null");
		} 
		this.min = number.floatValue();
		this.max = number.floatValue();
		if ((java.lang.Float.isNaN(min)) || (java.lang.Float.isNaN(max))) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		if (number instanceof java.lang.Float) {
			org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number));
			org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number));
		} 
	}

	public FloatRange(float number1 ,float number2) {
		super();
		if ((java.lang.Float.isNaN(number1)) || (java.lang.Float.isNaN(number2))) {
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

	public FloatRange(java.lang.Number number1 ,java.lang.Number number2) {
		super();
		if ((number1 == null) || (number2 == null)) {
			throw new java.lang.IllegalArgumentException("The numbers must not be null");
		} 
		float number1val = number1.floatValue();
		float number2val = number2.floatValue();
		if ((java.lang.Float.isNaN(number1val)) || (java.lang.Float.isNaN(number2val))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2val < number1val) {
			this.min = number2val;
			this.max = number1val;
			if (number2 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number2));
			} 
			if (number1 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number1));
			} 
		} else {
			this.min = number1val;
			this.max = number2val;
			if (number1 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number1));
			} 
			if (number2 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number2));
			} 
		}
	}

	public java.lang.Number getMinimumNumber() {
		if ((minObject) == null) {
			minObject = new java.lang.Float(min);
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
		return min;
	}

	public java.lang.Number getMaximumNumber() {
		if ((maxObject) == null) {
			maxObject = new java.lang.Float(max);
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
		return max;
	}

	public boolean containsNumber(java.lang.Number number) {
		if (number == null) {
			return false;
		} 
		return containsFloat(number.floatValue());
	}

	public boolean containsFloat(float value) {
		return (value >= (min)) && (value <= (max));
	}

	public boolean containsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return (containsFloat(range.getMinimumFloat())) && (containsFloat(range.getMaximumFloat()));
	}

	public boolean overlapsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return ((range.containsFloat(min)) || (range.containsFloat(max))) || (containsFloat(range.getMinimumFloat()));
	}

	public boolean equals(java.lang.Object obj) {
		if (obj == (org.apache.commons.lang.math.FloatRange.this)) {
			return true;
		} 
		if ((obj instanceof org.apache.commons.lang.math.FloatRange) == false) {
			return false;
		} 
		org.apache.commons.lang.math.FloatRange range = ((org.apache.commons.lang.math.FloatRange)(obj));
		return ((java.lang.Float.floatToIntBits(min)) == (java.lang.Float.floatToIntBits(range.min))) && ((java.lang.Float.floatToIntBits(max)) == (java.lang.Float.floatToIntBits(range.max)));
	}

	public int hashCode() {
		if ((hashCode) == 0) {
			hashCode = 17;
			hashCode = (37 * (hashCode)) + (getClass().hashCode());
			hashCode = (37 * (hashCode)) + (java.lang.Float.floatToIntBits(min));
			hashCode = (37 * (hashCode)) + (java.lang.Float.floatToIntBits(max));
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

public final class FloatRange extends org.apache.commons.lang.math.Range implements java.io.Serializable {
	private static final long serialVersionUID = 71849363892750L;

	private final float min;

	private final float max;

	private transient java.lang.Float minObject = null;

	private transient java.lang.Float maxObject = null;

	private transient int hashCode = 0;

	private transient java.lang.String toString = null;

	public FloatRange(float number) {
		super();
		if (java.lang.Float.isNaN(number)) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		this.min = number;
		this.max = number;
	}

	public FloatRange(java.lang.Number number) {
		super();
		if (number == null) {
			throw new java.lang.IllegalArgumentException("The number must not be null");
		} 
		this.min = number.floatValue();
		this.max = number.floatValue();
		if ((java.lang.Float.isNaN(min)) || (java.lang.Float.isNaN(max))) {
			throw new java.lang.IllegalArgumentException("The number must not be NaN");
		} 
		if (number instanceof java.lang.Float) {
			org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number));
			org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number));
		} 
	}

	public FloatRange(float number1 ,float number2) {
		super();
		if ((java.lang.Float.isNaN(number1)) || (java.lang.Float.isNaN(number2))) {
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

	public FloatRange(java.lang.Number number1 ,java.lang.Number number2) {
		super();
		if ((number1 == null) || (number2 == null)) {
			throw new java.lang.IllegalArgumentException("The numbers must not be null");
		} 
		float number1val = number1.floatValue();
		float number2val = number2.floatValue();
		if ((java.lang.Float.isNaN(number1val)) || (java.lang.Float.isNaN(number2val))) {
			throw new java.lang.IllegalArgumentException("The numbers must not be NaN");
		} 
		if (number2val < number1val) {
			this.min = number2val;
			this.max = number1val;
			if (number2 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number2));
			} 
			if (number1 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number1));
			} 
		} else {
			this.min = number1val;
			this.max = number2val;
			if (number1 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.minObject = ((java.lang.Float)(number1));
			} 
			if (number2 instanceof java.lang.Float) {
				org.apache.commons.lang.math.FloatRange.this.maxObject = ((java.lang.Float)(number2));
			} 
		}
	}

	public java.lang.Number getMinimumNumber() {
		if ((minObject) == null) {
			minObject = new java.lang.Float(min);
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
		return min;
	}

	public java.lang.Number getMaximumNumber() {
		if ((maxObject) == null) {
			maxObject = new java.lang.Float(max);
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
		return max;
	}

	public boolean containsNumber(java.lang.Number number) {
		if (number == null) {
			return false;
		} 
		return containsFloat(number.floatValue());
	}

	public boolean containsFloat(float value) {
		return (value >= (min)) && (value <= (max));
	}

	public boolean containsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return (containsFloat(range.getMinimumFloat())) && (containsFloat(range.getMaximumFloat()));
	}

	public boolean overlapsRange(org.apache.commons.lang.math.Range range) {
		if (range == null) {
			return false;
		} 
		return ((range.containsFloat(min)) || (range.containsFloat(max))) || (containsFloat(range.getMinimumFloat()));
	}

	public boolean equals(java.lang.Object obj) {
		if (obj == (org.apache.commons.lang.math.FloatRange.this)) {
			return true;
		} 
		if ((obj instanceof org.apache.commons.lang.math.FloatRange) == false) {
			return false;
		} 
		org.apache.commons.lang.math.FloatRange range = ((org.apache.commons.lang.math.FloatRange)(obj));
		return ((java.lang.Float.floatToIntBits(min)) == (java.lang.Float.floatToIntBits(range.min))) && ((java.lang.Float.floatToIntBits(max)) == (java.lang.Float.floatToIntBits(range.max)));
	}

	public int hashCode() {
		if ((hashCode) == 0) {
			hashCode = 17;
			hashCode = (37 * (hashCode)) + (getClass().hashCode());
			hashCode = (37 * (hashCode)) + (java.lang.Float.floatToIntBits(min));
			hashCode = (37 * (hashCode)) + (java.lang.Float.floatToIntBits(max));
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

