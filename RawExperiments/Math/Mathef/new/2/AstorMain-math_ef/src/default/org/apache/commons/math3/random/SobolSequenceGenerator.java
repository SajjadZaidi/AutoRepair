package org.apache.commons.math3.random;


public class SobolSequenceGenerator implements org.apache.commons.math3.random.RandomVectorGenerator {
	private static final int BITS = 52;

	private static final double SCALE = org.apache.commons.math3.util.FastMath.pow(2, org.apache.commons.math3.random.SobolSequenceGenerator.BITS);

	private static final int MAX_DIMENSION = 1000;

	private static final java.lang.String RESOURCE_NAME = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000";

	private static final java.lang.String FILE_CHARSET = "US-ASCII";

	private final int dimension;

	private int count = 0;

	private final long[][] direction;

	private final long[] x;

	public SobolSequenceGenerator(final int dimension) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((dimension < 1) || (dimension > (org.apache.commons.math3.random.SobolSequenceGenerator.MAX_DIMENSION))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(dimension , 1 , org.apache.commons.math3.random.SobolSequenceGenerator.MAX_DIMENSION);
		} 
		final java.io.InputStream is = getClass().getResourceAsStream(org.apache.commons.math3.random.SobolSequenceGenerator.RESOURCE_NAME);
		if (is == null) {
			throw new org.apache.commons.math3.exception.MathInternalError();
		} 
		this.dimension = dimension;
		direction = new long[dimension][(org.apache.commons.math3.random.SobolSequenceGenerator.BITS) + 1];
		x = new long[dimension];
		try {
			initFromStream(is);
		} catch (java.io.IOException e) {
			throw new org.apache.commons.math3.exception.MathInternalError();
		} catch (org.apache.commons.math3.exception.MathParseException e) {
			throw new org.apache.commons.math3.exception.MathInternalError();
		} finally {
			try {
				is.close();
			} catch (java.io.IOException e) {
			}
		}
	}

	public SobolSequenceGenerator(final int dimension ,final java.io.InputStream is) throws java.io.IOException , org.apache.commons.math3.exception.MathParseException , org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (dimension < 1) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(dimension);
		} 
		this.dimension = dimension;
		direction = new long[dimension][(org.apache.commons.math3.random.SobolSequenceGenerator.BITS) + 1];
		x = new long[dimension];
		int lastDimension = initFromStream(is);
		if (lastDimension < dimension) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(dimension , 1 , lastDimension);
		} 
	}

	private int initFromStream(final java.io.InputStream is) throws java.io.IOException, org.apache.commons.math3.exception.MathParseException {
		for (int i = 1 ; i <= (org.apache.commons.math3.random.SobolSequenceGenerator.BITS) ; i++) {
			direction[0][i] = 1L << ((org.apache.commons.math3.random.SobolSequenceGenerator.BITS) - i);
		}
		final java.nio.charset.Charset charset = java.nio.charset.Charset.forName(org.apache.commons.math3.random.SobolSequenceGenerator.FILE_CHARSET);
		final java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is , charset));
		int dim = -1;
		try {
			reader.readLine();
			int lineNumber = 2;
			int index = 1;
			java.lang.String line = null;
			while ((line = reader.readLine()) != null) {
				java.util.StringTokenizer st = new java.util.StringTokenizer(line , " ");
				try {
					dim = java.lang.Integer.parseInt(st.nextToken());
					if ((dim >= 2) && (dim <= (dimension))) {
						final int s = java.lang.Integer.parseInt(st.nextToken());
						final int a = java.lang.Integer.parseInt(st.nextToken());
						final int[] m = new int[s + 1];
						for (int i = 1 ; i <= s ; i++) {
							m[i] = java.lang.Integer.parseInt(st.nextToken());
						}
						initDirectionVector((index++), a, m);
					} 
					if (dim > (dimension)) {
						return dim;
					} 
				} catch (java.util.NoSuchElementException e) {
					throw new org.apache.commons.math3.exception.MathParseException(line , lineNumber);
				} catch (java.lang.NumberFormatException e) {
					throw new org.apache.commons.math3.exception.MathParseException(line , lineNumber);
				}
				lineNumber++;
			}
		} finally {
			reader.close();
		}
		return dim;
	}

	private void initDirectionVector(final int d, final int a, final int[] m) {
		final int s = (m.length) - 1;
		for (int i = 1 ; i <= s ; i++) {
			direction[d][i] = ((long)(m[i])) << ((org.apache.commons.math3.random.SobolSequenceGenerator.BITS) - i);
		}
		for (int i = s + 1 ; i <= (org.apache.commons.math3.random.SobolSequenceGenerator.BITS) ; i++) {
			direction[d][i] = (direction[d][(i - s)]) ^ ((direction[d][(i - s)]) >> s);
			for (int k = 1 ; k <= (s - 1) ; k++) {
				direction[d][i] ^= ((a >> ((s - 1) - k)) & 1) * (direction[d][(i - k)]);
			}
		}
	}

	public double[] nextVector() {
		final double[] v = new double[dimension];
		if ((count) == 0) {
			(count)++;
			return v;
		} 
		int c = 1;
		int value = (count) - 1;
		while ((value & 1) == 1) {
			value >>= 1;
			c++;
		}
		for (int i = 0 ; i < (dimension) ; i++) {
			x[i] ^= direction[i][c];
			v[i] = ((double)(x[i])) / (org.apache.commons.math3.random.SobolSequenceGenerator.SCALE);
		}
		(count)++;
		return v;
	}

	public double[] skipTo(final int index) throws org.apache.commons.math3.exception.NotPositiveException {
		if (index == 0) {
			java.util.Arrays.fill(x, 0);
		} else {
			final int i = index - 1;
			final long grayCode = i ^ (i >> 1);
			for (int j = 0 ; j < (dimension) ; j++) {
				long result = 0;
				for (int k = 1 ; k <= (org.apache.commons.math3.random.SobolSequenceGenerator.BITS) ; k++) {
					final long shift = grayCode >> (k - 1);
					if (shift == 0) {
						break;
					} 
					final long ik = shift & 1;
					result ^= ik * (direction[j][k]);
				}
				x[j] = result;
			}
		}
		count = index;
		return nextVector();
	}

	public int getNextIndex() {
		return count;
	}
}

