package org.apache.commons.math3.random;


public class RandomGeneratorFactory {
	private RandomGeneratorFactory() {
	}

	public static org.apache.commons.math3.random.RandomGenerator createRandomGenerator(final java.util.Random rng) {
		return new org.apache.commons.math3.random.RandomGenerator() {
			public void setSeed(int seed) {
				rng.setSeed(((long)(seed)));
			}

			public void setSeed(int[] seed) {
				rng.setSeed(org.apache.commons.math3.random.RandomGeneratorFactory.convertToLong(seed));
			}

			public void setSeed(long seed) {
				rng.setSeed(seed);
			}

			public void nextBytes(byte[] bytes) {
				rng.nextBytes(bytes);
			}

			public int nextInt() {
				return rng.nextInt();
			}

			public int nextInt(int n) {
				if (n <= 0) {
					throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(n);
				} 
				return rng.nextInt(n);
			}

			public long nextLong() {
				return rng.nextLong();
			}

			public boolean nextBoolean() {
				return rng.nextBoolean();
			}

			public float nextFloat() {
				return rng.nextFloat();
			}

			public double nextDouble() {
				return rng.nextDouble();
			}

			public double nextGaussian() {
				return rng.nextGaussian();
			}
		};
	}

	public static long convertToLong(int[] seed) {
		final long prime = 4294967291L;
		long combined = 0L;
		for (int s : seed) {
			combined = (combined * prime) + s;
		}
		return combined;
	}
}

