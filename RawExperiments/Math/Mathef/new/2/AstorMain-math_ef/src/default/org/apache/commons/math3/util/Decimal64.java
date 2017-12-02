package org.apache.commons.math3.util;


public class Decimal64 extends java.lang.Number implements java.lang.Comparable<org.apache.commons.math3.util.Decimal64> , org.apache.commons.math3.RealFieldElement<org.apache.commons.math3.util.Decimal64> {
	public static final org.apache.commons.math3.util.Decimal64 ZERO;

	public static final org.apache.commons.math3.util.Decimal64 ONE;

	public static final org.apache.commons.math3.util.Decimal64 NEGATIVE_INFINITY;

	public static final org.apache.commons.math3.util.Decimal64 POSITIVE_INFINITY;

	public static final org.apache.commons.math3.util.Decimal64 NAN;

	private static final long serialVersionUID = 20120227L;

	static {
		ZERO = new org.apache.commons.math3.util.Decimal64(0.0);
		ONE = new org.apache.commons.math3.util.Decimal64(1.0);
		NEGATIVE_INFINITY = new org.apache.commons.math3.util.Decimal64(java.lang.Double.NEGATIVE_INFINITY);
		POSITIVE_INFINITY = new org.apache.commons.math3.util.Decimal64(java.lang.Double.POSITIVE_INFINITY);
		NAN = new org.apache.commons.math3.util.Decimal64(java.lang.Double.NaN);
	}

	private final double value;

	public Decimal64(final double x) {
		this.value = x;
	}

	public org.apache.commons.math3.Field<org.apache.commons.math3.util.Decimal64> getField() {
		return org.apache.commons.math3.util.Decimal64Field.getInstance();
	}

	public org.apache.commons.math3.util.Decimal64 add(final org.apache.commons.math3.util.Decimal64 a) {
		return new org.apache.commons.math3.util.Decimal64(((org.apache.commons.math3.util.Decimal64.this.value) + (a.value)));
	}

	public org.apache.commons.math3.util.Decimal64 subtract(final org.apache.commons.math3.util.Decimal64 a) {
		return new org.apache.commons.math3.util.Decimal64(((org.apache.commons.math3.util.Decimal64.this.value) - (a.value)));
	}

	public org.apache.commons.math3.util.Decimal64 negate() {
		return new org.apache.commons.math3.util.Decimal64((-(org.apache.commons.math3.util.Decimal64.this.value)));
	}

	public org.apache.commons.math3.util.Decimal64 multiply(final org.apache.commons.math3.util.Decimal64 a) {
		return new org.apache.commons.math3.util.Decimal64(((org.apache.commons.math3.util.Decimal64.this.value) * (a.value)));
	}

	public org.apache.commons.math3.util.Decimal64 multiply(final int n) {
		return new org.apache.commons.math3.util.Decimal64((n * (org.apache.commons.math3.util.Decimal64.this.value)));
	}

	public org.apache.commons.math3.util.Decimal64 divide(final org.apache.commons.math3.util.Decimal64 a) {
		return new org.apache.commons.math3.util.Decimal64(((org.apache.commons.math3.util.Decimal64.this.value) / (a.value)));
	}

	public org.apache.commons.math3.util.Decimal64 reciprocal() {
		return new org.apache.commons.math3.util.Decimal64((1.0 / (org.apache.commons.math3.util.Decimal64.this.value)));
	}

	@java.lang.Override
	public byte byteValue() {
		return ((byte)(value));
	}

	@java.lang.Override
	public short shortValue() {
		return ((short)(value));
	}

	@java.lang.Override
	public int intValue() {
		return ((int)(value));
	}

	@java.lang.Override
	public long longValue() {
		return ((long)(value));
	}

	@java.lang.Override
	public float floatValue() {
		return ((float)(value));
	}

	@java.lang.Override
	public double doubleValue() {
		return value;
	}

	public int compareTo(final org.apache.commons.math3.util.Decimal64 o) {
		return java.lang.Double.compare(org.apache.commons.math3.util.Decimal64.this.value, o.value);
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj instanceof org.apache.commons.math3.util.Decimal64) {
			final org.apache.commons.math3.util.Decimal64 that = ((org.apache.commons.math3.util.Decimal64)(obj));
			return (java.lang.Double.doubleToLongBits(org.apache.commons.math3.util.Decimal64.this.value)) == (java.lang.Double.doubleToLongBits(that.value));
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		long v = java.lang.Double.doubleToLongBits(value);
		return ((int)(v ^ (v >>> 32)));
	}

	@java.lang.Override
	public java.lang.String toString() {
		return java.lang.Double.toString(value);
	}

	public boolean isInfinite() {
		return java.lang.Double.isInfinite(value);
	}

	public boolean isNaN() {
		return java.lang.Double.isNaN(value);
	}

	public double getReal() {
		return value;
	}

	public org.apache.commons.math3.util.Decimal64 add(final double a) {
		return new org.apache.commons.math3.util.Decimal64(((value) + a));
	}

	public org.apache.commons.math3.util.Decimal64 subtract(final double a) {
		return new org.apache.commons.math3.util.Decimal64(((value) - a));
	}

	public org.apache.commons.math3.util.Decimal64 multiply(final double a) {
		return new org.apache.commons.math3.util.Decimal64(((value) * a));
	}

	public org.apache.commons.math3.util.Decimal64 divide(final double a) {
		return new org.apache.commons.math3.util.Decimal64(((value) / a));
	}

	public org.apache.commons.math3.util.Decimal64 remainder(final double a) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.IEEEremainder(value, a));
	}

	public org.apache.commons.math3.util.Decimal64 remainder(final org.apache.commons.math3.util.Decimal64 a) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.IEEEremainder(value, a.value));
	}

	public org.apache.commons.math3.util.Decimal64 abs() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.abs(value));
	}

	public org.apache.commons.math3.util.Decimal64 ceil() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.ceil(value));
	}

	public org.apache.commons.math3.util.Decimal64 floor() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.floor(value));
	}

	public org.apache.commons.math3.util.Decimal64 rint() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.rint(value));
	}

	public long round() {
		return org.apache.commons.math3.util.FastMath.round(value);
	}

	public org.apache.commons.math3.util.Decimal64 signum() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.signum(value));
	}

	public org.apache.commons.math3.util.Decimal64 copySign(final org.apache.commons.math3.util.Decimal64 sign) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.copySign(value, sign.value));
	}

	public org.apache.commons.math3.util.Decimal64 copySign(final double sign) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.copySign(value, sign));
	}

	public org.apache.commons.math3.util.Decimal64 scalb(final int n) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.scalb(value, n));
	}

	public org.apache.commons.math3.util.Decimal64 hypot(final org.apache.commons.math3.util.Decimal64 y) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.hypot(value, y.value));
	}

	public org.apache.commons.math3.util.Decimal64 sqrt() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.sqrt(value));
	}

	public org.apache.commons.math3.util.Decimal64 cbrt() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.cbrt(value));
	}

	public org.apache.commons.math3.util.Decimal64 rootN(final int n) {
		if ((value) < 0) {
			return new org.apache.commons.math3.util.Decimal64((-(org.apache.commons.math3.util.FastMath.pow((-(value)), (1.0 / n)))));
		} else {
			return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.pow(value, (1.0 / n)));
		}
	}

	public org.apache.commons.math3.util.Decimal64 pow(final double p) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.pow(value, p));
	}

	public org.apache.commons.math3.util.Decimal64 pow(final int n) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.pow(value, n));
	}

	public org.apache.commons.math3.util.Decimal64 pow(final org.apache.commons.math3.util.Decimal64 e) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.pow(value, e.value));
	}

	public org.apache.commons.math3.util.Decimal64 exp() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.exp(value));
	}

	public org.apache.commons.math3.util.Decimal64 expm1() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.expm1(value));
	}

	public org.apache.commons.math3.util.Decimal64 log() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.log(value));
	}

	public org.apache.commons.math3.util.Decimal64 log1p() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.log1p(value));
	}

	public org.apache.commons.math3.util.Decimal64 log10() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.log10(value));
	}

	public org.apache.commons.math3.util.Decimal64 cos() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.cos(value));
	}

	public org.apache.commons.math3.util.Decimal64 sin() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.sin(value));
	}

	public org.apache.commons.math3.util.Decimal64 tan() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.tan(value));
	}

	public org.apache.commons.math3.util.Decimal64 acos() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.acos(value));
	}

	public org.apache.commons.math3.util.Decimal64 asin() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.asin(value));
	}

	public org.apache.commons.math3.util.Decimal64 atan() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.atan(value));
	}

	public org.apache.commons.math3.util.Decimal64 atan2(final org.apache.commons.math3.util.Decimal64 x) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.atan2(value, x.value));
	}

	public org.apache.commons.math3.util.Decimal64 cosh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.cosh(value));
	}

	public org.apache.commons.math3.util.Decimal64 sinh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.sinh(value));
	}

	public org.apache.commons.math3.util.Decimal64 tanh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.tanh(value));
	}

	public org.apache.commons.math3.util.Decimal64 acosh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.acosh(value));
	}

	public org.apache.commons.math3.util.Decimal64 asinh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.asinh(value));
	}

	public org.apache.commons.math3.util.Decimal64 atanh() {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.FastMath.atanh(value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final org.apache.commons.math3.util.Decimal64[] a, final org.apache.commons.math3.util.Decimal64[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] aDouble = new double[a.length];
		final double[] bDouble = new double[b.length];
		for (int i = 0 ; i < (a.length) ; ++i) {
			aDouble[i] = a[i].value;
			bDouble[i] = b[i].value;
		}
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(aDouble, bDouble));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final double[] a, final org.apache.commons.math3.util.Decimal64[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] bDouble = new double[b.length];
		for (int i = 0 ; i < (a.length) ; ++i) {
			bDouble[i] = b[i].value;
		}
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a, bDouble));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final org.apache.commons.math3.util.Decimal64 a1, final org.apache.commons.math3.util.Decimal64 b1, final org.apache.commons.math3.util.Decimal64 a2, final org.apache.commons.math3.util.Decimal64 b2) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final double a1, final org.apache.commons.math3.util.Decimal64 b1, final double a2, final org.apache.commons.math3.util.Decimal64 b2) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final org.apache.commons.math3.util.Decimal64 a1, final org.apache.commons.math3.util.Decimal64 b1, final org.apache.commons.math3.util.Decimal64 a2, final org.apache.commons.math3.util.Decimal64 b2, final org.apache.commons.math3.util.Decimal64 a3, final org.apache.commons.math3.util.Decimal64 b3) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final double a1, final org.apache.commons.math3.util.Decimal64 b1, final double a2, final org.apache.commons.math3.util.Decimal64 b2, final double a3, final org.apache.commons.math3.util.Decimal64 b3) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final org.apache.commons.math3.util.Decimal64 a1, final org.apache.commons.math3.util.Decimal64 b1, final org.apache.commons.math3.util.Decimal64 a2, final org.apache.commons.math3.util.Decimal64 b2, final org.apache.commons.math3.util.Decimal64 a3, final org.apache.commons.math3.util.Decimal64 b3, final org.apache.commons.math3.util.Decimal64 a4, final org.apache.commons.math3.util.Decimal64 b4) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value));
	}

	public org.apache.commons.math3.util.Decimal64 linearCombination(final double a1, final org.apache.commons.math3.util.Decimal64 b1, final double a2, final org.apache.commons.math3.util.Decimal64 b2, final double a3, final org.apache.commons.math3.util.Decimal64 b3, final double a4, final org.apache.commons.math3.util.Decimal64 b4) {
		return new org.apache.commons.math3.util.Decimal64(org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value));
	}
}

