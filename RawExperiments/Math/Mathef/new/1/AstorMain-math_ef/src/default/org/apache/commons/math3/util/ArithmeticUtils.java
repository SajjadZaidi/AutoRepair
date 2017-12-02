package org.apache.commons.math3.util;


public final class ArithmeticUtils {
	private ArithmeticUtils() {
		super();
	}

	public static int addAndCheck(int x, int y) throws org.apache.commons.math3.exception.MathArithmeticException {
		long s = ((long)(x)) + ((long)(y));
		if ((s < (java.lang.Integer.MIN_VALUE)) || (s > (java.lang.Integer.MAX_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW_IN_ADDITION , x , y);
		} 
		return ((int)(s));
	}

	public static long addAndCheck(long a, long b) throws org.apache.commons.math3.exception.MathArithmeticException {
		return org.apache.commons.math3.util.ArithmeticUtils.addAndCheck(a, b, org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW_IN_ADDITION);
	}

	@java.lang.Deprecated
	public static long binomialCoefficient(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(n, k);
	}

	@java.lang.Deprecated
	public static double binomialCoefficientDouble(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble(n, k);
	}

	@java.lang.Deprecated
	public static double binomialCoefficientLog(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog(n, k);
	}

	@java.lang.Deprecated
	public static long factorial(final int n) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException {
		return org.apache.commons.math3.util.CombinatoricsUtils.factorial(n);
	}

	@java.lang.Deprecated
	public static double factorialDouble(final int n) throws org.apache.commons.math3.exception.NotPositiveException {
		return org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble(n);
	}

	@java.lang.Deprecated
	public static double factorialLog(final int n) throws org.apache.commons.math3.exception.NotPositiveException {
		return org.apache.commons.math3.util.CombinatoricsUtils.factorialLog(n);
	}

	public static int gcd(int p, int q) throws org.apache.commons.math3.exception.MathArithmeticException {
		int a = p;
		int b = q;
		if ((a == 0) || (b == 0)) {
			if ((a == (java.lang.Integer.MIN_VALUE)) || (b == (java.lang.Integer.MIN_VALUE))) {
				throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_32_BITS , p , q);
			} 
			return org.apache.commons.math3.util.FastMath.abs((a + b));
		} 
		long al = a;
		long bl = b;
		boolean useLong = false;
		if (a < 0) {
			if ((java.lang.Integer.MIN_VALUE) == a) {
				useLong = true;
			} else {
				a = -a;
			}
			al = -al;
		} 
		if (b < 0) {
			if ((java.lang.Integer.MIN_VALUE) == b) {
				useLong = true;
			} else {
				b = -b;
			}
			bl = -bl;
		} 
		if (useLong) {
			if (al == bl) {
				throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_32_BITS , p , q);
			} 
			long blbu = bl;
			bl = al;
			al = blbu % al;
			if (al == 0) {
				if (bl > (java.lang.Integer.MAX_VALUE)) {
					throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_32_BITS , p , q);
				} 
				return ((int)(bl));
			} 
			blbu = bl;
			b = ((int)(al));
			a = ((int)(blbu % al));
		} 
		return org.apache.commons.math3.util.ArithmeticUtils.gcdPositive(a, b);
	}

	private static int gcdPositive(int a, int b) {
		if (a == 0) {
			return b;
		} else if (b == 0) {
			return a;
		} 
		final int aTwos = java.lang.Integer.numberOfTrailingZeros(a);
		a >>= aTwos;
		final int bTwos = java.lang.Integer.numberOfTrailingZeros(b);
		b >>= bTwos;
		final int shift = org.apache.commons.math3.util.FastMath.min(aTwos, bTwos);
		while (a != b) {
			final int delta = a - b;
			b = java.lang.Math.min(a, b);
			a = java.lang.Math.abs(delta);
			a >>= java.lang.Integer.numberOfTrailingZeros(a);
		}
		return a << shift;
	}

	public static long gcd(final long p, final long q) throws org.apache.commons.math3.exception.MathArithmeticException {
		long u = p;
		long v = q;
		if ((u == 0) || (v == 0)) {
			if ((u == (java.lang.Long.MIN_VALUE)) || (v == (java.lang.Long.MIN_VALUE))) {
				throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS , p , q);
			} 
			return (org.apache.commons.math3.util.FastMath.abs(u)) + (org.apache.commons.math3.util.FastMath.abs(v));
		} 
		if (u > 0) {
			u = -u;
		} 
		if (v > 0) {
			v = -v;
		} 
		int k = 0;
		while ((((u & 1) == 0) && ((v & 1) == 0)) && (k < 63)) {
			u /= 2;
			v /= 2;
			k++;
		}
		if (k == 63) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS , p , q);
		} 
		long t = (u & 1) == 1 ? v : -(u / 2);
		do {
			while ((t & 1) == 0) {
				t /= 2;
			}
			if (t > 0) {
				u = -t;
			} else {
				v = t;
			}
			t = (v - u) / 2;
		} while (t != 0 );
		return (-u) * (1L << k);
	}

	public static int lcm(int a, int b) throws org.apache.commons.math3.exception.MathArithmeticException {
		if ((a == 0) || (b == 0)) {
			return 0;
		} 
		int lcm = org.apache.commons.math3.util.FastMath.abs(org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck((a / (org.apache.commons.math3.util.ArithmeticUtils.gcd(a, b))), b));
		if (lcm == (java.lang.Integer.MIN_VALUE)) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.LCM_OVERFLOW_32_BITS , a , b);
		} 
		return lcm;
	}

	public static long lcm(long a, long b) throws org.apache.commons.math3.exception.MathArithmeticException {
		if ((a == 0) || (b == 0)) {
			return 0;
		} 
		long lcm = org.apache.commons.math3.util.FastMath.abs(org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck((a / (org.apache.commons.math3.util.ArithmeticUtils.gcd(a, b))), b));
		if (lcm == (java.lang.Long.MIN_VALUE)) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.LCM_OVERFLOW_64_BITS , a , b);
		} 
		return lcm;
	}

	public static int mulAndCheck(int x, int y) throws org.apache.commons.math3.exception.MathArithmeticException {
		long m = ((long)(x)) * ((long)(y));
		if ((m < (java.lang.Integer.MIN_VALUE)) || (m > (java.lang.Integer.MAX_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException();
		} 
		return ((int)(m));
	}

	public static long mulAndCheck(long a, long b) throws org.apache.commons.math3.exception.MathArithmeticException {
		long ret;
		if (a > b) {
			ret = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck(b, a);
		} else {
			if (a < 0) {
				if (b < 0) {
					if (a >= ((java.lang.Long.MAX_VALUE) / b)) {
						ret = a * b;
					} else {
						throw new org.apache.commons.math3.exception.MathArithmeticException();
					}
				} else if (b > 0) {
					if (((java.lang.Long.MIN_VALUE) / b) <= a) {
						ret = a * b;
					} else {
						throw new org.apache.commons.math3.exception.MathArithmeticException();
					}
				} else {
					ret = 0;
				}
			} else if (a > 0) {
				if (a <= ((java.lang.Long.MAX_VALUE) / b)) {
					ret = a * b;
				} else {
					throw new org.apache.commons.math3.exception.MathArithmeticException();
				}
			} else {
				ret = 0;
			}
		}
		return ret;
	}

	public static int subAndCheck(int x, int y) throws org.apache.commons.math3.exception.MathArithmeticException {
		long s = ((long)(x)) - ((long)(y));
		if ((s < (java.lang.Integer.MIN_VALUE)) || (s > (java.lang.Integer.MAX_VALUE))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW_IN_SUBTRACTION , x , y);
		} 
		return ((int)(s));
	}

	public static long subAndCheck(long a, long b) throws org.apache.commons.math3.exception.MathArithmeticException {
		long ret;
		if (b == (java.lang.Long.MIN_VALUE)) {
			if (a < 0) {
				ret = a - b;
			} else {
				throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW_IN_ADDITION , a , (-b));
			}
		} else {
			ret = org.apache.commons.math3.util.ArithmeticUtils.addAndCheck(a, (-b), org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW_IN_ADDITION);
		}
		return ret;
	}

	public static int pow(final int k, final int e) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		try {
			int exp = e;
			int result = 1;
			int k2p = k;
			while (true) {
				if ((exp & 1) != 0) {
					result = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck(result, k2p);
				} 
				exp >>= 1;
				if (exp == 0) {
					break;
				} 
				k2p = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck(k2p, k2p);
			}
			return result;
		} catch (org.apache.commons.math3.exception.MathArithmeticException mae) {
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.BASE, k);
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT, e);
			throw mae;
		}
	}

	@java.lang.Deprecated
	public static int pow(final int k, long e) throws org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		int result = 1;
		int k2p = k;
		while (e != 0) {
			if ((e & 1) != 0) {
				result *= k2p;
			} 
			k2p *= k2p;
			e >>= 1;
		}
		return result;
	}

	public static long pow(final long k, final int e) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		try {
			int exp = e;
			long result = 1;
			long k2p = k;
			while (true) {
				if ((exp & 1) != 0) {
					result = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck(result, k2p);
				} 
				exp >>= 1;
				if (exp == 0) {
					break;
				} 
				k2p = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck(k2p, k2p);
			}
			return result;
		} catch (org.apache.commons.math3.exception.MathArithmeticException mae) {
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.OVERFLOW);
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.BASE, k);
			mae.getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT, e);
			throw mae;
		}
	}

	@java.lang.Deprecated
	public static long pow(final long k, long e) throws org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		long result = 1L;
		long k2p = k;
		while (e != 0) {
			if ((e & 1) != 0) {
				result *= k2p;
			} 
			k2p *= k2p;
			e >>= 1;
		}
		return result;
	}

	public static java.math.BigInteger pow(final java.math.BigInteger k, int e) throws org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		return k.pow(e);
	}

	public static java.math.BigInteger pow(final java.math.BigInteger k, long e) throws org.apache.commons.math3.exception.NotPositiveException {
		if (e < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		java.math.BigInteger result = java.math.BigInteger.ONE;
		java.math.BigInteger k2p = k;
		while (e != 0) {
			if ((e & 1) != 0) {
				result = result.multiply(k2p);
			} 
			k2p = k2p.multiply(k2p);
			e >>= 1;
		}
		return result;
	}

	public static java.math.BigInteger pow(final java.math.BigInteger k, java.math.BigInteger e) throws org.apache.commons.math3.exception.NotPositiveException {
		if ((e.compareTo(java.math.BigInteger.ZERO)) < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.EXPONENT , e);
		} 
		java.math.BigInteger result = java.math.BigInteger.ONE;
		java.math.BigInteger k2p = k;
		while (!(java.math.BigInteger.ZERO.equals(e))) {
			if (e.testBit(0)) {
				result = result.multiply(k2p);
			} 
			k2p = k2p.multiply(k2p);
			e = e.shiftRight(1);
		}
		return result;
	}

	@java.lang.Deprecated
	public static long stirlingS2(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2(n, k);
	}

	private static long addAndCheck(long a, long b, org.apache.commons.math3.exception.util.Localizable pattern) throws org.apache.commons.math3.exception.MathArithmeticException {
		final long result = a + b;
		if (!(((a ^ b) < 0) | ((a ^ result) >= 0))) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(pattern , a , b);
		} 
		return result;
	}

	public static boolean isPowerOfTwo(long n) {
		return (n > 0) && ((n & (n - 1)) == 0);
	}
}

