package org.apache.commons.math3.util;


public final class CombinatoricsUtils {
	static final long[] FACTORIALS = new long[]{ 1L , 1L , 2L , 6L , 24L , 120L , 720L , 5040L , 40320L , 362880L , 3628800L , 39916800L , 479001600L , 6227020800L , 87178291200L , 1307674368000L , 20922789888000L , 355687428096000L , 6402373705728000L , 121645100408832000L , 2432902008176640000L };

	static final java.util.concurrent.atomic.AtomicReference<long[][]> STIRLING_S2 = new java.util.concurrent.atomic.AtomicReference<long[][]>(null);

	private CombinatoricsUtils() {
	}

	public static long binomialCoefficient(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial(n, k);
		if ((n == k) || (k == 0)) {
			return 1;
		} 
		if ((k == 1) || (k == (n - 1))) {
			return n;
		} 
		if (k > (n / 2)) {
			return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(n, (n - k));
		} 
		long result = 1;
		if (n <= 61) {
			int i = (n - k) + 1;
			for (int j = 1 ; j <= k ; j++) {
				result = (result * i) / j;
				i++;
			}
		} else if (n <= 66) {
			int i = (n - k) + 1;
			for (int j = 1 ; j <= k ; j++) {
				final long d = org.apache.commons.math3.util.ArithmeticUtils.gcd(i, j);
				result = (result / (j / d)) * (i / d);
				i++;
			}
		} else {
			int i = (n - k) + 1;
			for (int j = 1 ; j <= k ; j++) {
				final long d = org.apache.commons.math3.util.ArithmeticUtils.gcd(i, j);
				result = org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck((result / (j / d)), (i / d));
				i++;
			}
		}
		return result;
	}

	public static double binomialCoefficientDouble(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial(n, k);
		if ((n == k) || (k == 0)) {
			return 1.0;
		} 
		if ((k == 1) || (k == (n - 1))) {
			return n;
		} 
		if (k > (n / 2)) {
			return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble(n, (n - k));
		} 
		if (n < 67) {
			return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(n, k);
		} 
		double result = 1.0;
		for (int i = 1 ; i <= k ; i++) {
			result *= ((double)(((n - k) + i))) / ((double)(i));
		}
		return org.apache.commons.math3.util.FastMath.floor((result + 0.5));
	}

	public static double binomialCoefficientLog(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial(n, k);
		if ((n == k) || (k == 0)) {
			return 0;
		} 
		if ((k == 1) || (k == (n - 1))) {
			return org.apache.commons.math3.util.FastMath.log(n);
		} 
		if (n < 67) {
			return org.apache.commons.math3.util.FastMath.log(org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(n, k));
		} 
		if (n < 1030) {
			return org.apache.commons.math3.util.FastMath.log(org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble(n, k));
		} 
		if (k > (n / 2)) {
			return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog(n, (n - k));
		} 
		double logSum = 0;
		for (int i = (n - k) + 1 ; i <= n ; i++) {
			logSum += org.apache.commons.math3.util.FastMath.log(i);
		}
		for (int i = 2 ; i <= k ; i++) {
			logSum -= org.apache.commons.math3.util.FastMath.log(i);
		}
		return logSum;
	}

	public static long factorial(final int n) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException {
		if (n < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER , n);
		} 
		if (n > 20) {
			throw new org.apache.commons.math3.exception.MathArithmeticException();
		} 
		return org.apache.commons.math3.util.CombinatoricsUtils.FACTORIALS[n];
	}

	public static double factorialDouble(final int n) throws org.apache.commons.math3.exception.NotPositiveException {
		if (n < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER , n);
		} 
		if (n < 21) {
			return org.apache.commons.math3.util.CombinatoricsUtils.FACTORIALS[n];
		} 
		return org.apache.commons.math3.util.FastMath.floor(((org.apache.commons.math3.util.FastMath.exp(org.apache.commons.math3.util.CombinatoricsUtils.factorialLog(n))) + 0.5));
	}

	public static double factorialLog(final int n) throws org.apache.commons.math3.exception.NotPositiveException {
		if (n < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER , n);
		} 
		if (n < 21) {
			return org.apache.commons.math3.util.FastMath.log(org.apache.commons.math3.util.CombinatoricsUtils.FACTORIALS[n]);
		} 
		double logSum = 0;
		for (int i = 2 ; i <= n ; i++) {
			logSum += org.apache.commons.math3.util.FastMath.log(i);
		}
		return logSum;
	}

	public static long stirlingS2(final int n, final int k) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (k < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(k);
		} 
		if (k > n) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(k , n , true);
		} 
		long[][] stirlingS2 = org.apache.commons.math3.util.CombinatoricsUtils.STIRLING_S2.get();
		if (stirlingS2 == null) {
			final int maxIndex = 26;
			stirlingS2 = new long[maxIndex][];
			stirlingS2[0] = new long[]{ 1L };
			for (int i = 1 ; i < (stirlingS2.length) ; ++i) {
				stirlingS2[i] = new long[i + 1];
				stirlingS2[i][0] = 0;
				stirlingS2[i][1] = 1;
				stirlingS2[i][i] = 1;
				for (int j = 2 ; j < i ; ++j) {
					stirlingS2[i][j] = (j * (stirlingS2[(i - 1)][j])) + (stirlingS2[(i - 1)][(j - 1)]);
				}
			}
			org.apache.commons.math3.util.CombinatoricsUtils.STIRLING_S2.compareAndSet(null, stirlingS2);
		} 
		if (n < (stirlingS2.length)) {
			return stirlingS2[n][k];
		} else {
			if (k == 0) {
				return 0;
			} else if ((k == 1) || (k == n)) {
				return 1;
			} else if (k == 2) {
				return (1L << (n - 1)) - 1L;
			} else if (k == (n - 1)) {
				return org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(n, 2);
			} else {
				long sum = 0;
				long sign = (k & 1) == 0 ? 1 : -1;
				for (int j = 1 ; j <= k ; ++j) {
					sign = -sign;
					sum += (sign * (org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient(k, j))) * (org.apache.commons.math3.util.ArithmeticUtils.pow(j, n));
					if (sum < 0) {
						throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN , n , 0 , ((stirlingS2.length) - 1));
					} 
				}
				return sum / (org.apache.commons.math3.util.CombinatoricsUtils.factorial(k));
			}
		}
	}

	public static java.util.Iterator<int[]> combinationsIterator(int n, int k) {
		return new org.apache.commons.math3.util.Combinations(n , k).iterator();
	}

	public static void checkBinomial(final int n, final int k) throws org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (n < k) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER , k , n , true);
		} 
		if (n < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER , n);
		} 
	}
}

