package org.apache.commons.math3.random;


public abstract class BitsStreamGenerator implements java.io.Serializable , org.apache.commons.math3.random.RandomGenerator {
	private static final long serialVersionUID = 20130104L;

	private double nextGaussian;

	public BitsStreamGenerator() {
		nextGaussian = java.lang.Double.NaN;
	}

	public abstract void setSeed(int seed);

	public abstract void setSeed(int[] seed);

	public abstract void setSeed(long seed);

	protected abstract int next(int bits);

	public boolean nextBoolean() {
		return (next(1)) != 0;
	}

	public void nextBytes(byte[] bytes) {
		int i = 0;
		final int iEnd = (bytes.length) - 3;
		while (i < iEnd) {
			final int random = next(32);
			bytes[i] = ((byte)(random & 255));
			bytes[(i + 1)] = ((byte)((random >> 8) & 255));
			bytes[(i + 2)] = ((byte)((random >> 16) & 255));
			bytes[(i + 3)] = ((byte)((random >> 24) & 255));
			i += 4;
		}
		int random = next(32);
		while (i < (bytes.length)) {
			bytes[(i++)] = ((byte)(random & 255));
			random >>= 8;
		}
	}

	public double nextDouble() {
		final long high = ((long)(next(26))) << 26;
		final int low = next(26);
		return (high | low) * 2.220446049250313E-16;
	}

	public float nextFloat() {
		return (next(23)) * 1.1920929E-7F;
	}

	public double nextGaussian() {
		final double random;
		if (java.lang.Double.isNaN(nextGaussian)) {
			final double x = nextDouble();
			final double y = nextDouble();
			final double alpha = (2 * (org.apache.commons.math3.util.FastMath.PI)) * x;
			final double r = org.apache.commons.math3.util.FastMath.sqrt(((-2) * (org.apache.commons.math3.util.FastMath.log(y))));
			random = r * (org.apache.commons.math3.util.FastMath.cos(alpha));
			nextGaussian = r * (org.apache.commons.math3.util.FastMath.sin(alpha));
		} else {
			random = nextGaussian;
			nextGaussian = java.lang.Double.NaN;
		}
		return random;
	}

	public int nextInt() {
		return next(32);
	}

	public int nextInt(int n) throws java.lang.IllegalArgumentException {
		if (n > 0) {
			if ((n & (-n)) == n) {
				return ((int)((n * ((long)(next(31)))) >> 31));
			} 
			int bits;
			int val;
			do {
				bits = next(31);
				val = bits % n;
			} while (((bits - val) + (n - 1)) < 0 );
			return val;
		} 
		throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(n);
	}

	public long nextLong() {
		final long high = ((long)(next(32))) << 32;
		final long low = ((long)(next(32))) & 4294967295L;
		return high | low;
	}

	public long nextLong(long n) throws java.lang.IllegalArgumentException {
		if (n > 0) {
			long bits;
			long val;
			do {
				bits = ((long)(next(31))) << 32;
				bits |= ((long)(next(32))) & 4294967295L;
				val = bits % n;
			} while (((bits - val) + (n - 1)) < 0 );
			return val;
		} 
		throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(n);
	}

	public void clear() {
		nextGaussian = java.lang.Double.NaN;
	}
}

