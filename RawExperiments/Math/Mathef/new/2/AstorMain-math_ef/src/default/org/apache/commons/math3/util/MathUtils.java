package org.apache.commons.math3.util;


public final class MathUtils {
	public static final double TWO_PI = 2 * (org.apache.commons.math3.util.FastMath.PI);

	public static final double PI_SQUARED = (org.apache.commons.math3.util.FastMath.PI) * (org.apache.commons.math3.util.FastMath.PI);

	private MathUtils() {
	}

	public static int hash(double value) {
		return new java.lang.Double(value).hashCode();
	}

	public static boolean equals(double x, double y) {
		return new java.lang.Double(x).equals(new java.lang.Double(y));
	}

	public static int hash(double[] value) {
		return java.util.Arrays.hashCode(value);
	}

	public static double normalizeAngle(double a, double center) {
		return a - ((org.apache.commons.math3.util.MathUtils.TWO_PI) * (org.apache.commons.math3.util.FastMath.floor((((a + (org.apache.commons.math3.util.FastMath.PI)) - center) / (org.apache.commons.math3.util.MathUtils.TWO_PI)))));
	}

	public static double reduce(double a, double period, double offset) {
		final double p = org.apache.commons.math3.util.FastMath.abs(period);
		return (a - (p * (org.apache.commons.math3.util.FastMath.floor(((a - offset) / p))))) - offset;
	}

	public static byte copySign(byte magnitude, byte sign) throws org.apache.commons.math3.exception.MathArithmeticException {
		if (((magnitude >= 0) && (sign >= 0)) || ((magnitude < 0) && (sign < 0))) {
			return magnitude;
		} else if ((sign >= 0) && (magnitude == (java.lang.Byte.MIN_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
		} else {
			return ((byte)(-magnitude));
		}
	}

	public static short copySign(short magnitude, short sign) throws org.apache.commons.math3.exception.MathArithmeticException {
		if (((magnitude >= 0) && (sign >= 0)) || ((magnitude < 0) && (sign < 0))) {
			return magnitude;
		} else if ((sign >= 0) && (magnitude == (java.lang.Short.MIN_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
		} else {
			return ((short)(-magnitude));
		}
	}

	public static int copySign(int magnitude, int sign) throws org.apache.commons.math3.exception.MathArithmeticException {
		if (((magnitude >= 0) && (sign >= 0)) || ((magnitude < 0) && (sign < 0))) {
			return magnitude;
		} else if ((sign >= 0) && (magnitude == (java.lang.Integer.MIN_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
		} else {
			return -magnitude;
		}
	}

	public static long copySign(long magnitude, long sign) throws org.apache.commons.math3.exception.MathArithmeticException {
		if (((magnitude >= 0) && (sign >= 0)) || ((magnitude < 0) && (sign < 0))) {
			return magnitude;
		} else if ((sign >= 0) && (magnitude == (java.lang.Long.MIN_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
		} else {
			return -magnitude;
		}
	}

	public static void checkFinite(final double x) throws org.apache.commons.math3.exception.NotFiniteNumberException {
		if ((java.lang.Double.isInfinite(x)) || (java.lang.Double.isNaN(x))) {
			throw new org.apache.commons.math3.exception.NotFiniteNumberException(x);
		} 
	}

	public static void checkFinite(final double[] val) throws org.apache.commons.math3.exception.NotFiniteNumberException {
		for (int i = 0 ; i < (val.length) ; i++) {
			final double x = val[i];
			if ((java.lang.Double.isInfinite(x)) || (java.lang.Double.isNaN(x))) {
				throw new org.apache.commons.math3.exception.NotFiniteNumberException(org.apache.commons.math3.exception.util.LocalizedFormats.ARRAY_ELEMENT , x , i);
			} 
		}
	}

	public static void checkNotNull(java.lang.Object o, org.apache.commons.math3.exception.util.Localizable pattern, java.lang.Object... args) throws org.apache.commons.math3.exception.NullArgumentException {
		if (o == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(pattern , args);
		} 
	}

	public static void checkNotNull(java.lang.Object o) throws org.apache.commons.math3.exception.NullArgumentException {
		if (o == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
	}
}

