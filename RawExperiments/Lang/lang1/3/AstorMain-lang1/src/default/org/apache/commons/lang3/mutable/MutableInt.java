package org.apache.commons.lang3.mutable;


public class MutableInt extends java.lang.Number implements java.lang.Comparable<org.apache.commons.lang3.mutable.MutableInt> , org.apache.commons.lang3.mutable.Mutable<java.lang.Number> {
	private static final long serialVersionUID = 512176391864L;

	private int value;

	public MutableInt() {
		super();
	}

	public MutableInt(final int value) {
		super();
		org.apache.commons.lang3.mutable.MutableInt.this.value = value;
	}

	public MutableInt(final java.lang.Number value) {
		super();
		org.apache.commons.lang3.mutable.MutableInt.this.value = value.intValue();
	}

	public MutableInt(final java.lang.String value) throws java.lang.NumberFormatException {
		super();
		org.apache.commons.lang3.mutable.MutableInt.this.value = java.lang.Integer.parseInt(value);
	}

	@java.lang.Override
	public java.lang.Integer getValue() {
		return java.lang.Integer.valueOf(org.apache.commons.lang3.mutable.MutableInt.this.value);
	}

	public void setValue(final int value) {
		org.apache.commons.lang3.mutable.MutableInt.this.value = value;
	}

	@java.lang.Override
	public void setValue(final java.lang.Number value) {
		org.apache.commons.lang3.mutable.MutableInt.this.value = value.intValue();
	}

	public void increment() {
		(value)++;
	}

	public void decrement() {
		(value)--;
	}

	public void add(final int operand) {
		org.apache.commons.lang3.mutable.MutableInt.this.value += operand;
	}

	public void add(final java.lang.Number operand) {
		org.apache.commons.lang3.mutable.MutableInt.this.value += operand.intValue();
	}

	public void subtract(final int operand) {
		org.apache.commons.lang3.mutable.MutableInt.this.value -= operand;
	}

	public void subtract(final java.lang.Number operand) {
		org.apache.commons.lang3.mutable.MutableInt.this.value -= operand.intValue();
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

	public java.lang.Integer toInteger() {
		return java.lang.Integer.valueOf(intValue());
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj instanceof org.apache.commons.lang3.mutable.MutableInt) {
			return (value) == (((org.apache.commons.lang3.mutable.MutableInt)(obj)).intValue());
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		return value;
	}

	@java.lang.Override
	public int compareTo(final org.apache.commons.lang3.mutable.MutableInt other) {
		final int anotherVal = other.value;
		return (value) < anotherVal ? -1 : (value) == anotherVal ? 0 : 1;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(value);
	}
}

