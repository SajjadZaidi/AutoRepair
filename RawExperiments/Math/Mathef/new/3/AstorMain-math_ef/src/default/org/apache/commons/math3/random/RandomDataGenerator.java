package org.apache.commons.math3.random;


public class RandomDataGenerator implements java.io.Serializable , org.apache.commons.math3.random.RandomData {
	private static final long serialVersionUID = -626730818244969716L;

	private org.apache.commons.math3.random.RandomGenerator rand = null;

	private org.apache.commons.math3.random.RandomGenerator secRand = null;

	public RandomDataGenerator() {
	}

	public RandomDataGenerator(org.apache.commons.math3.random.RandomGenerator rand) {
		org.apache.commons.math3.random.RandomDataGenerator.this.rand = rand;
	}

	public java.lang.String nextHexString(int len) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (len <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.LENGTH , len);
		} 
		org.apache.commons.math3.random.RandomGenerator ran = getRandomGenerator();
		java.lang.StringBuilder outBuffer = new java.lang.StringBuilder();
		byte[] randomBytes = new byte[(len / 2) + 1];
		ran.nextBytes(randomBytes);
		for (int i = 0 ; i < (randomBytes.length) ; i++) {
			java.lang.Integer c = java.lang.Integer.valueOf(randomBytes[i]);
			java.lang.String hex = java.lang.Integer.toHexString(((c.intValue()) + 128));
			if ((hex.length()) == 1) {
				hex = "0" + hex;
			} 
			outBuffer.append(hex);
		}
		return outBuffer.toString().substring(0, len);
	}

	public int nextInt(final int lower, final int upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		return new org.apache.commons.math3.distribution.UniformIntegerDistribution(getRandomGenerator() , lower , upper).sample();
	}

	public long nextLong(final long lower, final long upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (lower >= upper) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND , lower , upper , false);
		} 
		final long max = (upper - lower) + 1;
		if (max <= 0) {
			final org.apache.commons.math3.random.RandomGenerator rng = getRandomGenerator();
			while (true) {
				final long r = rng.nextLong();
				if ((r >= lower) && (r <= upper)) {
					return r;
				} 
			}
		} else if (max < (java.lang.Integer.MAX_VALUE)) {
			return lower + (getRandomGenerator().nextInt(((int)(max))));
		} else {
			return lower + (org.apache.commons.math3.random.RandomDataGenerator.nextLong(getRandomGenerator(), max));
		}
	}

	private static long nextLong(final org.apache.commons.math3.random.RandomGenerator rng, final long n) throws java.lang.IllegalArgumentException {
		if (n > 0) {
			final byte[] byteArray = new byte[8];
			long bits;
			long val;
			do {
				rng.nextBytes(byteArray);
				bits = 0;
				for (final byte b : byteArray) {
					bits = (bits << 8) | (((long)(b)) & 255L);
				}
				bits &= 9223372036854775807L;
				val = bits % n;
			} while (((bits - val) + (n - 1)) < 0 );
			return val;
		} 
		throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(n);
	}

	public java.lang.String nextSecureHexString(int len) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (len <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.LENGTH , len);
		} 
		final org.apache.commons.math3.random.RandomGenerator secRan = getSecRan();
		java.security.MessageDigest alg = null;
		try {
			alg = java.security.MessageDigest.getInstance("SHA-1");
		} catch (java.security.NoSuchAlgorithmException ex) {
			throw new org.apache.commons.math3.exception.MathInternalError(ex);
		}
		alg.reset();
		int numIter = (len / 40) + 1;
		java.lang.StringBuilder outBuffer = new java.lang.StringBuilder();
		for (int iter = 1 ; iter < (numIter + 1) ; iter++) {
			byte[] randomBytes = new byte[40];
			secRan.nextBytes(randomBytes);
			alg.update(randomBytes);
			byte[] hash = alg.digest();
			for (int i = 0 ; i < (hash.length) ; i++) {
				java.lang.Integer c = java.lang.Integer.valueOf(hash[i]);
				java.lang.String hex = java.lang.Integer.toHexString(((c.intValue()) + 128));
				if ((hex.length()) == 1) {
					hex = "0" + hex;
				} 
				outBuffer.append(hex);
			}
		}
		return outBuffer.toString().substring(0, len);
	}

	public int nextSecureInt(final int lower, final int upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		return new org.apache.commons.math3.distribution.UniformIntegerDistribution(getSecRan() , lower , upper).sample();
	}

	public long nextSecureLong(final long lower, final long upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (lower >= upper) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND , lower , upper , false);
		} 
		final org.apache.commons.math3.random.RandomGenerator rng = getSecRan();
		final long max = (upper - lower) + 1;
		if (max <= 0) {
			while (true) {
				final long r = rng.nextLong();
				if ((r >= lower) && (r <= upper)) {
					return r;
				} 
			}
		} else if (max < (java.lang.Integer.MAX_VALUE)) {
			return lower + (rng.nextInt(((int)(max))));
		} else {
			return lower + (org.apache.commons.math3.random.RandomDataGenerator.nextLong(rng, max));
		}
	}

	public long nextPoisson(double mean) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.PoissonDistribution(getRandomGenerator() , mean , org.apache.commons.math3.distribution.PoissonDistribution.DEFAULT_EPSILON , org.apache.commons.math3.distribution.PoissonDistribution.DEFAULT_MAX_ITERATIONS).sample();
	}

	public double nextGaussian(double mu, double sigma) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (sigma <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.STANDARD_DEVIATION , sigma);
		} 
		return (sigma * (getRandomGenerator().nextGaussian())) + mu;
	}

	public double nextExponential(double mean) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.ExponentialDistribution(getRandomGenerator() , mean , org.apache.commons.math3.distribution.ExponentialDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public double nextGamma(double shape, double scale) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.GammaDistribution(getRandomGenerator() , shape , scale , org.apache.commons.math3.distribution.GammaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) throws org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return new org.apache.commons.math3.distribution.HypergeometricDistribution(getRandomGenerator() , populationSize , numberOfSuccesses , sampleSize).sample();
	}

	public int nextPascal(int r, double p) throws org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.OutOfRangeException {
		return new org.apache.commons.math3.distribution.PascalDistribution(getRandomGenerator() , r , p).sample();
	}

	public double nextT(double df) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.TDistribution(getRandomGenerator() , df , org.apache.commons.math3.distribution.TDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public double nextWeibull(double shape, double scale) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.WeibullDistribution(getRandomGenerator() , shape , scale , org.apache.commons.math3.distribution.WeibullDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public int nextZipf(int numberOfElements, double exponent) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.ZipfDistribution(getRandomGenerator() , numberOfElements , exponent).sample();
	}

	public double nextBeta(double alpha, double beta) {
		return new org.apache.commons.math3.distribution.BetaDistribution(getRandomGenerator() , alpha , beta , org.apache.commons.math3.distribution.BetaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) {
		return new org.apache.commons.math3.distribution.BinomialDistribution(getRandomGenerator() , numberOfTrials , probabilityOfSuccess).sample();
	}

	public double nextCauchy(double median, double scale) {
		return new org.apache.commons.math3.distribution.CauchyDistribution(getRandomGenerator() , median , scale , org.apache.commons.math3.distribution.CauchyDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public double nextChiSquare(double df) {
		return new org.apache.commons.math3.distribution.ChiSquaredDistribution(getRandomGenerator() , df , org.apache.commons.math3.distribution.ChiSquaredDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public double nextF(double numeratorDf, double denominatorDf) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.distribution.FDistribution(getRandomGenerator() , numeratorDf , denominatorDf , org.apache.commons.math3.distribution.FDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
	}

	public double nextUniform(double lower, double upper) throws org.apache.commons.math3.exception.NotANumberException, org.apache.commons.math3.exception.NotFiniteNumberException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		return nextUniform(lower, upper, false);
	}

	public double nextUniform(double lower, double upper, boolean lowerInclusive) throws org.apache.commons.math3.exception.NotANumberException, org.apache.commons.math3.exception.NotFiniteNumberException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (lower >= upper) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND , lower , upper , false);
		} 
		if (java.lang.Double.isInfinite(lower)) {
			throw new org.apache.commons.math3.exception.NotFiniteNumberException(org.apache.commons.math3.exception.util.LocalizedFormats.INFINITE_BOUND , lower);
		} 
		if (java.lang.Double.isInfinite(upper)) {
			throw new org.apache.commons.math3.exception.NotFiniteNumberException(org.apache.commons.math3.exception.util.LocalizedFormats.INFINITE_BOUND , upper);
		} 
		if ((java.lang.Double.isNaN(lower)) || (java.lang.Double.isNaN(upper))) {
			throw new org.apache.commons.math3.exception.NotANumberException();
		} 
		final org.apache.commons.math3.random.RandomGenerator generator = getRandomGenerator();
		double u = generator.nextDouble();
		while ((!lowerInclusive) && (u <= 0.0)) {
			u = generator.nextDouble();
		}
		return (u * upper) + ((1.0 - u) * lower);
	}

	public int[] nextPermutation(int n, int k) throws org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (k > n) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.PERMUTATION_EXCEEDS_N , k , n , true);
		} 
		if (k <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.PERMUTATION_SIZE , k);
		} 
		int[] index = org.apache.commons.math3.util.MathArrays.natural(n);
		org.apache.commons.math3.util.MathArrays.shuffle(index, getRandomGenerator());
		return org.apache.commons.math3.util.MathArrays.copyOf(index, k);
	}

	public java.lang.Object[] nextSample(java.util.Collection<?> c, int k) throws org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException {
		int len = c.size();
		if (k > len) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE , k , len , true);
		} 
		if (k <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_SAMPLES , k);
		} 
		java.lang.Object[] objects = c.toArray();
		int[] index = nextPermutation(len, k);
		java.lang.Object[] result = new java.lang.Object[k];
		for (int i = 0 ; i < k ; i++) {
			result[i] = objects[index[i]];
		}
		return result;
	}

	public void reSeed(long seed) {
		getRandomGenerator().setSeed(seed);
	}

	public void reSeedSecure() {
		getSecRan().setSeed(java.lang.System.currentTimeMillis());
	}

	public void reSeedSecure(long seed) {
		getSecRan().setSeed(seed);
	}

	public void reSeed() {
		getRandomGenerator().setSeed(((java.lang.System.currentTimeMillis()) + (java.lang.System.identityHashCode(org.apache.commons.math3.random.RandomDataGenerator.this))));
	}

	public void setSecureAlgorithm(java.lang.String algorithm, java.lang.String provider) throws java.security.NoSuchAlgorithmException, java.security.NoSuchProviderException {
		secRand = org.apache.commons.math3.random.RandomGeneratorFactory.createRandomGenerator(java.security.SecureRandom.getInstance(algorithm, provider));
	}

	public org.apache.commons.math3.random.RandomGenerator getRandomGenerator() {
		if ((rand) == null) {
			initRan();
		} 
		return rand;
	}

	private void initRan() {
		rand = new org.apache.commons.math3.random.Well19937c(((java.lang.System.currentTimeMillis()) + (java.lang.System.identityHashCode(org.apache.commons.math3.random.RandomDataGenerator.this))));
	}

	private org.apache.commons.math3.random.RandomGenerator getSecRan() {
		if ((secRand) == null) {
			secRand = org.apache.commons.math3.random.RandomGeneratorFactory.createRandomGenerator(new java.security.SecureRandom());
			secRand.setSeed(((java.lang.System.currentTimeMillis()) + (java.lang.System.identityHashCode(org.apache.commons.math3.random.RandomDataGenerator.this))));
		} 
		return secRand;
	}
}

