package org.apache.commons.lang3.mutable;


public class MutableShort extends java.lang.Number implements java.lang.Comparable<org.apache.commons.lang3.mutable.MutableShort> , org.apache.commons.lang3.mutable.Mutable<java.lang.Number> {
	private static final long serialVersionUID = -2135791679L;

	private short value;

	public MutableShort() {
		super();
	}

	public MutableShort(final short value) {
		super();
		org.apache.commons.lang3.mutable.MutableShort.this.value = value;
	}

	public MutableShort(final java.lang.Number value) {
		super();
		org.apache.commons.lang3.mutable.MutableShort.this.value = value.shortValue();
	}

	public MutableShort(final java.lang.String value) throws java.lang.NumberFormatException {
		super();
		org.apache.commons.lang3.mutable.MutableShort.this.value = java.lang.Short.parseShort(value);
	}

	@java.lang.Override
	public java.lang.Short getValue() {
		return java.lang.Short.valueOf(org.apache.commons.lang3.mutable.MutableShort.this.value);
	}

	public void setValue(final short value) {
		org.apache.commons.lang3.mutable.MutableShort.this.value = value;
	}

	@java.lang.Override
	public void setValue(final java.lang.Number value) {
		org.apache.commons.lang3.mutable.MutableShort.this.value = value.shortValue();
	}

	public void increment() {
		(value)++;
	}

	public void decrement() {
		(value)--;
	}

	public void add(final short operand) {
		org.apache.commons.lang3.mutable.MutableShort.this.value += operand;
	}

	public void add(final java.lang.Number operand) {
		org.apache.commons.lang3.mutable.MutableShort.this.value += operand.shortValue();
	}

	public void subtract(final short operand) {
		org.apache.commons.lang3.mutable.MutableShort.this.value -= operand;
	}

	public void subtract(final java.lang.Number operand) {
		org.apache.commons.lang3.mutable.MutableShort.this.value -= operand.shortValue();
	}

	@java.lang.Override
	public short shortValue() {
		return value;
	}

	@java.lang.Override
	public int intValue() {
		return value;
	}

	@java.lang.Override
	public long longValue() {
		return value;
	}

	@java.lang.Override
	public float floatValue() {
		return value;
	}

	@java.lang.Override
	public double doubleValue() {
		return value;
	}

	public java.lang.Short toShort() {
		return java.lang.Short.valueOf(shortValue());
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj instanceof org.apache.commons.lang3.mutable.MutableShort) {
			return (value) == (((org.apache.commons.lang3.mutable.MutableShort)(obj)).shortValue());
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		return value;
	}

	@java.lang.Override
	public int compareTo(final org.apache.commons.lang3.mutable.MutableShort other) {
		final short anotherVal = other.value;
		return (value) < anotherVal ? -1 : (value) == anotherVal ? 0 : 1;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(value);
	}
}

