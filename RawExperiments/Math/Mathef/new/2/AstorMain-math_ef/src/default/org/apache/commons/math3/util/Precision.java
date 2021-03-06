package org.apache.commons.math3.util;


public class Precision {
	public static final double EPSILON;

	public static final double SAFE_MIN;

	private static final long EXPONENT_OFFSET = 1023L;

	private static final long SGN_MASK = -9223372036854775808L;

	private static final int SGN_MASK_FLOAT = -2147483648;

	private static final double POSITIVE_ZERO = 0.0;

	private static final long POSITIVE_ZERO_DOUBLE_BITS = java.lang.Double.doubleToRawLongBits((+0.0));

	private static final long NEGATIVE_ZERO_DOUBLE_BITS = java.lang.Double.doubleToRawLongBits((-0.0));

	private static final int POSITIVE_ZERO_FLOAT_BITS = java.lang.Float.floatToRawIntBits((+0.0F));

	private static final int NEGATIVE_ZERO_FLOAT_BITS = java.lang.Float.floatToRawIntBits((-0.0F));

	static {
		EPSILON = java.lang.Double.longBitsToDouble((((org.apache.commons.math3.util.Precision.EXPONENT_OFFSET) - 53L) << 52));
		SAFE_MIN = java.lang.Double.longBitsToDouble((((org.apache.commons.math3.util.Precision.EXPONENT_OFFSET) - 1022L) << 52));
	}

	private Precision() {
	}

	public static int compareTo(double x, double y, double eps) {
		if (org.apache.commons.math3.util.Precision.equals(x, y, eps)) {
			return 0;
		} else if (x < y) {
			return -1;
		} 
		return 1;
	}

	public static int compareTo(final double x, final double y, final int maxUlps) {
		if (org.apache.commons.math3.util.Precision.equals(x, y, maxUlps)) {
			return 0;
		} else if (x < y) {
			return -1;
		} 
		return 1;
	}

	public static boolean equals(float x, float y) {
		return org.apache.commons.math3.util.Precision.equals(x, y, 1);
	}

	public static boolean equalsIncludingNaN(float x, float y) {
		return (x != x) || (y != y) ? !((x != x) ^ (y != y)) : org.apache.commons.math3.util.Precision.equals(x, y, 1);
	}

	public static boolean equals(float x, float y, float eps) {
		return (org.apache.commons.math3.util.Precision.equals(x, y, 1)) || ((org.apache.commons.math3.util.FastMath.abs((y - x))) <= eps);
	}

	public static boolean equalsIncludingNaN(float x, float y, float eps) {
		return (org.apache.commons.math3.util.Precision.equalsIncludingNaN(x, y)) || ((org.apache.commons.math3.util.FastMath.abs((y - x))) <= eps);
	}

	public static boolean equals(final float x, final float y, final int maxUlps) {
		final int xInt = java.lang.Float.floatToRawIntBits(x);
		final int yInt = java.lang.Float.floatToRawIntBits(y);
		final boolean isEqual;
		if (((xInt ^ yInt) & (org.apache.commons.math3.util.Precision.SGN_MASK_FLOAT)) == 0) {
			isEqual = (org.apache.commons.math3.util.FastMath.abs((xInt - yInt))) <= maxUlps;
		} else {
			final int deltaPlus;
			final int deltaMinus;
			if (xInt < yInt) {
				deltaPlus = yInt - (org.apache.commons.math3.util.Precision.POSITIVE_ZERO_FLOAT_BITS);
				deltaMinus = xInt - (org.apache.commons.math3.util.Precision.NEGATIVE_ZERO_FLOAT_BITS);
			} else {
				deltaPlus = xInt - (org.apache.commons.math3.util.Precision.POSITIVE_ZERO_FLOAT_BITS);
				deltaMinus = yInt - (org.apache.commons.math3.util.Precision.NEGATIVE_ZERO_FLOAT_BITS);
			}
			if (deltaPlus > maxUlps) {
				isEqual = false;
			} else {
				isEqual = deltaMinus <= (maxUlps - deltaPlus);
			}
		}
		return (isEqual && (!(java.lang.Float.isNaN(x)))) && (!(java.lang.Float.isNaN(y)));
	}

	public static boolean equalsIncludingNaN(float x, float y, int maxUlps) {
		return (x != x) || (y != y) ? !((x != x) ^ (y != y)) : org.apache.commons.math3.util.Precision.equals(x, y, maxUlps);
	}

	public static boolean equals(double x, double y) {
		return org.apache.commons.math3.util.Precision.equals(x, y, 1);
	}

	public static boolean equalsIncludingNaN(double x, double y) {
		return (x != x) || (y != y) ? !((x != x) ^ (y != y)) : org.apache.commons.math3.util.Precision.equals(x, y, 1);
	}

	public static boolean equals(double x, double y, double eps) {
		return (org.apache.commons.math3.util.Precision.equals(x, y, 1)) || ((org.apache.commons.math3.util.FastMath.abs((y - x))) <= eps);
	}

	public static boolean equalsWithRelativeTolerance(double x, double y, double eps) {
		if (org.apache.commons.math3.util.Precision.equals(x, y, 1)) {
			return true;
		} 
		final double absoluteMax = org.apache.commons.math3.util.FastMath.max(org.apache.commons.math3.util.FastMath.abs(x), org.apache.commons.math3.util.FastMath.abs(y));
		final double relativeDifference = org.apache.commons.math3.util.FastMath.abs(((x - y) / absoluteMax));
		return relativeDifference <= eps;
	}

	public static boolean equalsIncludingNaN(double x, double y, double eps) {
		return (org.apache.commons.math3.util.Precision.equalsIncludingNaN(x, y)) || ((org.apache.commons.math3.util.FastMath.abs((y - x))) <= eps);
	}

	public static boolean equals(final double x, final double y, final int maxUlps) {
		final long xInt = java.lang.Double.doubleToRawLongBits(x);
		final long yInt = java.lang.Double.doubleToRawLongBits(y);
		final boolean isEqual;
		if (((xInt ^ yInt) & (org.apache.commons.math3.util.Precision.SGN_MASK)) == 0L) {
			isEqual = (org.apache.commons.math3.util.FastMath.abs((xInt - yInt))) <= maxUlps;
		} else {
			final long deltaPlus;
			final long deltaMinus;
			if (xInt < yInt) {
				deltaPlus = yInt - (org.apache.commons.math3.util.Precision.POSITIVE_ZERO_DOUBLE_BITS);
				deltaMinus = xInt - (org.apache.commons.math3.util.Precision.NEGATIVE_ZERO_DOUBLE_BITS);
			} else {
				deltaPlus = xInt - (org.apache.commons.math3.util.Precision.POSITIVE_ZERO_DOUBLE_BITS);
				deltaMinus = yInt - (org.apache.commons.math3.util.Precision.NEGATIVE_ZERO_DOUBLE_BITS);
			}
			if (deltaPlus > maxUlps) {
				isEqual = false;
			} else {
				isEqual = deltaMinus <= (maxUlps - deltaPlus);
			}
		}
		return (isEqual && (!(java.lang.Double.isNaN(x)))) && (!(java.lang.Double.isNaN(y)));
	}

	public static boolean equalsIncludingNaN(double x, double y, int maxUlps) {
		return (x != x) || (y != y) ? !((x != x) ^ (y != y)) : org.apache.commons.math3.util.Precision.equals(x, y, maxUlps);
	}

	public static double round(double x, int scale) {
		return org.apache.commons.math3.util.Precision.round(x, scale, java.math.BigDecimal.ROUND_HALF_UP);
	}

	public static double round(double x, int scale, int roundingMethod) {
		try {
			final double rounded = new java.math.BigDecimal(java.lang.Double.toString(x)).setScale(scale, roundingMethod).doubleValue();
			return rounded == (org.apache.commons.math3.util.Precision.POSITIVE_ZERO) ? (org.apache.commons.math3.util.Precision.POSITIVE_ZERO) * x : rounded;
		} catch (java.lang.NumberFormatException ex) {
			if (java.lang.Double.isInfinite(x)) {
				return x;
			} else {
				return java.lang.Double.NaN;
			}
		}
	}

	public static float round(float x, int scale) {
		return org.apache.commons.math3.util.Precision.round(x, scale, java.math.BigDecimal.ROUND_HALF_UP);
	}

	public static float round(float x, int scale, int roundingMethod) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		final float sign = org.apache.commons.math3.util.FastMath.copySign(1.0F, x);
		final float factor = ((float)(org.apache.commons.math3.util.FastMath.pow(10.0F, scale))) * sign;
		return ((float)(org.apache.commons.math3.util.Precision.roundUnscaled((x * factor), sign, roundingMethod))) / factor;
	}

	private static double roundUnscaled(double unscaled, double sign, int roundingMethod) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		switch (roundingMethod) {
			case java.math.BigDecimal.ROUND_CEILING :
				if (sign == (-1)) {
					unscaled = org.apache.commons.math3.util.FastMath.floor(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.NEGATIVE_INFINITY));
				} else {
					unscaled = org.apache.commons.math3.util.FastMath.ceil(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.POSITIVE_INFINITY));
				}
				break;
			case java.math.BigDecimal.ROUND_DOWN :
				unscaled = org.apache.commons.math3.util.FastMath.floor(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.NEGATIVE_INFINITY));
				break;
			case java.math.BigDecimal.ROUND_FLOOR :
				if (sign == (-1)) {
					unscaled = org.apache.commons.math3.util.FastMath.ceil(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.POSITIVE_INFINITY));
				} else {
					unscaled = org.apache.commons.math3.util.FastMath.floor(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.NEGATIVE_INFINITY));
				}
				break;
			case java.math.BigDecimal.ROUND_HALF_DOWN :
				{
					unscaled = org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.NEGATIVE_INFINITY);
					double fraction = unscaled - (org.apache.commons.math3.util.FastMath.floor(unscaled));
					if (fraction > 0.5) {
						unscaled = org.apache.commons.math3.util.FastMath.ceil(unscaled);
					} else {
						unscaled = org.apache.commons.math3.util.FastMath.floor(unscaled);
					}
					break;
				}
			case java.math.BigDecimal.ROUND_HALF_EVEN :
				{
					double fraction = unscaled - (org.apache.commons.math3.util.FastMath.floor(unscaled));
					if (fraction > 0.5) {
						unscaled = org.apache.commons.math3.util.FastMath.ceil(unscaled);
					} else if (fraction < 0.5) {
						unscaled = org.apache.commons.math3.util.FastMath.floor(unscaled);
					} else {
						if (((org.apache.commons.math3.util.FastMath.floor(unscaled)) / 2.0) == (org.apache.commons.math3.util.FastMath.floor(((org.apache.commons.math3.util.FastMath.floor(unscaled)) / 2.0)))) {
							unscaled = org.apache.commons.math3.util.FastMath.floor(unscaled);
						} else {
							unscaled = org.apache.commons.math3.util.FastMath.ceil(unscaled);
						}
					}
					break;
				}
			case java.math.BigDecimal.ROUND_HALF_UP :
				{
					unscaled = org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.POSITIVE_INFINITY);
					double fraction = unscaled - (org.apache.commons.math3.util.FastMath.floor(unscaled));
					if (fraction >= 0.5) {
						unscaled = org.apache.commons.math3.util.FastMath.ceil(unscaled);
					} else {
						unscaled = org.apache.commons.math3.util.FastMath.floor(unscaled);
					}
					break;
				}
			case java.math.BigDecimal.ROUND_UNNECESSARY :
				if (unscaled != (org.apache.commons.math3.util.FastMath.floor(unscaled))) {
					throw new org.apache.commons.math3.exception.MathArithmeticException();
				} 
				break;
			case java.math.BigDecimal.ROUND_UP :
				if (unscaled != (org.apache.commons.math3.util.FastMath.floor(unscaled))) {
					unscaled = org.apache.commons.math3.util.FastMath.ceil(org.apache.commons.math3.util.FastMath.nextAfter(unscaled, java.lang.Double.POSITIVE_INFINITY));
				} 
				break;
			default :
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INVALID_ROUNDING_METHOD , roundingMethod , "ROUND_CEILING" , java.math.BigDecimal.ROUND_CEILING , "ROUND_DOWN" , java.math.BigDecimal.ROUND_DOWN , "ROUND_FLOOR" , java.math.BigDecimal.ROUND_FLOOR , "ROUND_HALF_DOWN" , java.math.BigDecimal.ROUND_HALF_DOWN , "ROUND_HALF_EVEN" , java.math.BigDecimal.ROUND_HALF_EVEN , "ROUND_HALF_UP" , java.math.BigDecimal.ROUND_HALF_UP , "ROUND_UNNECESSARY" , java.math.BigDecimal.ROUND_UNNECESSARY , "ROUND_UP" , java.math.BigDecimal.ROUND_UP);
		}
		return unscaled;
	}

	public static double representableDelta(double x, double originalDelta) {
		return (x + originalDelta) - x;
	}
}

