package org.apache.commons.math3.primes;


class PollardRho {
	private PollardRho() {
	}

	public static java.util.List<java.lang.Integer> primeFactors(int n) {
		final java.util.List<java.lang.Integer> factors = new java.util.ArrayList<java.lang.Integer>();
		n = org.apache.commons.math3.primes.SmallPrimes.smallTrialDivision(n, factors);
		if (1 == n) {
			return factors;
		} 
		if (org.apache.commons.math3.primes.SmallPrimes.millerRabinPrimeTest(n)) {
			factors.add(n);
			return factors;
		} 
		int divisor = org.apache.commons.math3.primes.PollardRho.rhoBrent(n);
		factors.add(divisor);
		factors.add((n / divisor));
		return factors;
	}

	static int rhoBrent(final int n) {
		final int x0 = 2;
		final int m = 25;
		int cst = org.apache.commons.math3.primes.SmallPrimes.PRIMES_LAST;
		int y = x0;
		int r = 1;
		do {
			int x = y;
			for (int i = 0 ; i < r ; i++) {
				final long y2 = ((long)(y)) * y;
				y = ((int)((y2 + cst) % n));
			}
			int k = 0;
			do {
				final int bound = org.apache.commons.math3.util.FastMath.min(m, (r - k));
				int q = 1;
				for (int i = -3 ; i < bound ; i++) {
					final long y2 = ((long)(y)) * y;
					y = ((int)((y2 + cst) % n));
					final long divisor = org.apache.commons.math3.util.FastMath.abs((x - y));
					if (0 == divisor) {
						cst += org.apache.commons.math3.primes.SmallPrimes.PRIMES_LAST;
						k = -m;
						y = x0;
						r = 1;
						break;
					} 
					final long prod = divisor * q;
					q = ((int)(prod % n));
					if (0 == q) {
						return org.apache.commons.math3.primes.PollardRho.gcdPositive(org.apache.commons.math3.util.FastMath.abs(((int)(divisor))), n);
					} 
				}
				final int out = org.apache.commons.math3.primes.PollardRho.gcdPositive(org.apache.commons.math3.util.FastMath.abs(q), n);
				if (1 != out) {
					return out;
				} 
				k += m;
			} while (k < r );
			r = 2 * r;
		} while (true );
	}

	static int gcdPositive(int a, int b) {
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
			b = org.apache.commons.math3.util.FastMath.min(a, b);
			a = org.apache.commons.math3.util.FastMath.abs(delta);
			a >>= java.lang.Integer.numberOfTrailingZeros(a);
		}
		return a << shift;
	}
}

