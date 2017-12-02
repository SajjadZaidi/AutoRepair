package org.apache.commons.math3.stat.inference;


public class KolmogorovSmirnovTest {
	protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;

	protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20;

	protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10;

	protected static final int SMALL_SAMPLE_PRODUCT = 200;

	protected static final int LARGE_SAMPLE_PRODUCT = 10000;

	protected static final int MONTE_CARLO_ITERATIONS = 1000000;

	private final org.apache.commons.math3.random.RandomGenerator rng;

	public KolmogorovSmirnovTest() {
		rng = new org.apache.commons.math3.random.Well19937c();
	}

	public KolmogorovSmirnovTest(org.apache.commons.math3.random.RandomGenerator rng) {
		this.rng = rng;
	}

	public double kolmogorovSmirnovTest(org.apache.commons.math3.distribution.RealDistribution distribution, double[] data, boolean exact) {
		return 1.0 - (cdf(kolmogorovSmirnovStatistic(distribution, data), data.length, exact));
	}

	public double kolmogorovSmirnovStatistic(org.apache.commons.math3.distribution.RealDistribution distribution, double[] data) {
		checkArray(data);
		final int n = data.length;
		final double nd = n;
		final double[] dataCopy = new double[n];
		java.lang.System.arraycopy(data, 0, dataCopy, 0, n);
		java.util.Arrays.sort(dataCopy);
		double d = 0.0;
		for (int i = 1 ; i <= n ; i++) {
			final double yi = distribution.cumulativeProbability(dataCopy[(i - 1)]);
			final double currD = org.apache.commons.math3.util.FastMath.max((yi - ((i - 1) / nd)), ((i / nd) - yi));
			if (currD > d) {
				d = currD;
			} 
		}
		return d;
	}

	public double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) {
		final long lengthProduct = ((long)(x.length)) * (y.length);
		if (lengthProduct < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.SMALL_SAMPLE_PRODUCT)) {
			return exactP(kolmogorovSmirnovStatistic(x, y), x.length, y.length, strict);
		} 
		if (lengthProduct < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.LARGE_SAMPLE_PRODUCT)) {
			return monteCarloP(kolmogorovSmirnovStatistic(x, y), x.length, y.length, strict, org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MONTE_CARLO_ITERATIONS);
		} 
		return approximateP(kolmogorovSmirnovStatistic(x, y), x.length, y.length);
	}

	public double kolmogorovSmirnovTest(double[] x, double[] y) {
		return kolmogorovSmirnovTest(x, y, true);
	}

	public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
		checkArray(x);
		checkArray(y);
		final double[] sx = org.apache.commons.math3.util.MathArrays.copyOf(x);
		final double[] sy = org.apache.commons.math3.util.MathArrays.copyOf(y);
		java.util.Arrays.sort(sx);
		java.util.Arrays.sort(sy);
		final int n = sx.length;
		final int m = sy.length;
		double supD = 0.0;
		for (int i = 0 ; i < n ; i++) {
			final double cdf_x = (i + 1.0) / n;
			final int yIndex = java.util.Arrays.binarySearch(sy, sx[i]);
			final double cdf_y = yIndex >= 0 ? (yIndex + 1.0) / m : ((-yIndex) - 1.0) / m;
			final double curD = org.apache.commons.math3.util.FastMath.abs((cdf_x - cdf_y));
			if (curD > supD) {
				supD = curD;
			} 
		}
		for (int i = 0 ; i < m ; i++) {
			final double cdf_y = (i + 1.0) / m;
			final int xIndex = java.util.Arrays.binarySearch(sx, sy[i]);
			final double cdf_x = xIndex >= 0 ? (xIndex + 1.0) / n : ((-xIndex) - 1.0) / n;
			final double curD = org.apache.commons.math3.util.FastMath.abs((cdf_x - cdf_y));
			if (curD > supD) {
				supD = curD;
			} 
		}
		return supD;
	}

	public double kolmogorovSmirnovTest(org.apache.commons.math3.distribution.RealDistribution distribution, double[] data) {
		return kolmogorovSmirnovTest(distribution, data, false);
	}

	public boolean kolmogorovSmirnovTest(org.apache.commons.math3.distribution.RealDistribution distribution, double[] data, double alpha) {
		if ((alpha <= 0) || (alpha > 0.5)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL , alpha , 0 , 0.5);
		} 
		return (kolmogorovSmirnovTest(distribution, data)) < alpha;
	}

	public double cdf(double d, int n) throws org.apache.commons.math3.exception.MathArithmeticException {
		return cdf(d, n, false);
	}

	public double cdfExact(double d, int n) throws org.apache.commons.math3.exception.MathArithmeticException {
		return cdf(d, n, true);
	}

	public double cdf(double d, int n, boolean exact) throws org.apache.commons.math3.exception.MathArithmeticException {
		final double ninv = 1 / ((double)(n));
		final double ninvhalf = 0.5 * ninv;
		if (d <= ninvhalf) {
			return 0;
		} else if ((ninvhalf < d) && (d <= ninv)) {
			double res = 1;
			final double f = (2 * d) - ninv;
			for (int i = 1 ; i <= n ; ++i) {
				res *= i * f;
			}
			return res;
		} else if (((1 - ninv) <= d) && (d < 1)) {
			return 1 - (2 * (java.lang.Math.pow((1 - d), n)));
		} else if (1 <= d) {
			return 1;
		} 
		if (exact) {
			return exactK(d, n);
		} 
		if (n <= 140) {
			return roundedK(d, n);
		} 
		return pelzGood(d, n);
	}

	private double exactK(double d, int n) throws org.apache.commons.math3.exception.MathArithmeticException {
		final int k = ((int)(java.lang.Math.ceil((n * d))));
		final org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> H = org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.this.createExactH(d, n);
		final org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> Hpower = H.power(n);
		org.apache.commons.math3.fraction.BigFraction pFrac = Hpower.getEntry((k - 1), (k - 1));
		for (int i = 1 ; i <= n ; ++i) {
			pFrac = pFrac.multiply(i).divide(n);
		}
		return pFrac.bigDecimalValue(20, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private double roundedK(double d, int n) {
		final int k = ((int)(java.lang.Math.ceil((n * d))));
		final org.apache.commons.math3.linear.RealMatrix H = org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.this.createRoundedH(d, n);
		final org.apache.commons.math3.linear.RealMatrix Hpower = H.power(n);
		double pFrac = Hpower.getEntry((k - 1), (k - 1));
		for (int i = 1 ; i <= n ; ++i) {
			pFrac *= ((double)(i)) / ((double)(n));
		}
		return pFrac;
	}

	public double pelzGood(double d, int n) {
		final double sqrtN = org.apache.commons.math3.util.FastMath.sqrt(n);
		final double z = d * sqrtN;
		final double z2 = (d * d) * n;
		final double z4 = z2 * z2;
		final double z6 = z4 * z2;
		final double z8 = z4 * z4;
		double ret = 0;
		double sum = 0;
		double increment = 0;
		double kTerm = 0;
		double z2Term = (org.apache.commons.math3.util.MathUtils.PI_SQUARED) / (8 * z2);
		int k = 1;
		for ( ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm = (2 * k) - 1;
			increment = org.apache.commons.math3.util.FastMath.exp((((-z2Term) * kTerm) * kTerm));
			sum += increment;
			if (increment <= ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * sum)) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		ret = (sum * (org.apache.commons.math3.util.FastMath.sqrt((2 * (org.apache.commons.math3.util.FastMath.PI))))) / z;
		final double twoZ2 = 2 * z2;
		sum = 0;
		kTerm = 0;
		double kTerm2 = 0;
		for (k = 0 ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm = k + 0.5;
			kTerm2 = kTerm * kTerm;
			increment = (((org.apache.commons.math3.util.MathUtils.PI_SQUARED) * kTerm2) - z2) * (org.apache.commons.math3.util.FastMath.exp((((-(org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) / twoZ2)));
			sum += increment;
			if ((org.apache.commons.math3.util.FastMath.abs(increment)) < ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * (org.apache.commons.math3.util.FastMath.abs(sum)))) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		final double sqrtHalfPi = org.apache.commons.math3.util.FastMath.sqrt(((org.apache.commons.math3.util.FastMath.PI) / 2));
		ret += (sum * sqrtHalfPi) / ((3 * z4) * sqrtN);
		final double z4Term = 2 * z4;
		final double z6Term = 6 * z6;
		z2Term = 5 * z2;
		final double pi4 = (org.apache.commons.math3.util.MathUtils.PI_SQUARED) * (org.apache.commons.math3.util.MathUtils.PI_SQUARED);
		sum = 0;
		kTerm = 0;
		kTerm2 = 0;
		for (k = 0 ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm = k + 0.5;
			kTerm2 = kTerm * kTerm;
			increment = (((z6Term + z4Term) + (((org.apache.commons.math3.util.MathUtils.PI_SQUARED) * (z4Term - z2Term)) * kTerm2)) + (((pi4 * (1 - twoZ2)) * kTerm2) * kTerm2)) * (org.apache.commons.math3.util.FastMath.exp((((-(org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) / twoZ2)));
			sum += increment;
			if ((org.apache.commons.math3.util.FastMath.abs(increment)) < ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * (org.apache.commons.math3.util.FastMath.abs(sum)))) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		double sum2 = 0;
		kTerm2 = 0;
		for (k = 1 ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm2 = k * k;
			increment = ((org.apache.commons.math3.util.MathUtils.PI_SQUARED) * kTerm2) * (org.apache.commons.math3.util.FastMath.exp((((-(org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) / twoZ2)));
			sum2 += increment;
			if ((org.apache.commons.math3.util.FastMath.abs(increment)) < ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * (org.apache.commons.math3.util.FastMath.abs(sum2)))) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		ret += (sqrtHalfPi / n) * ((sum / ((((36 * z2) * z2) * z2) * z)) - (sum2 / ((18 * z2) * z)));
		final double pi6 = pi4 * (org.apache.commons.math3.util.MathUtils.PI_SQUARED);
		sum = 0;
		double kTerm4 = 0;
		double kTerm6 = 0;
		for (k = 0 ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm = k + 0.5;
			kTerm2 = kTerm * kTerm;
			kTerm4 = kTerm2 * kTerm2;
			kTerm6 = kTerm4 * kTerm2;
			increment = ((((((pi6 * kTerm6) * (5 - (30 * z2))) + ((pi4 * kTerm4) * (((-60) * z2) + (212 * z4)))) + (((org.apache.commons.math3.util.MathUtils.PI_SQUARED) * kTerm2) * ((135 * z4) - (96 * z6)))) - (30 * z6)) - (90 * z8)) * (org.apache.commons.math3.util.FastMath.exp((((-(org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) / twoZ2)));
			sum += increment;
			if ((org.apache.commons.math3.util.FastMath.abs(increment)) < ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * (org.apache.commons.math3.util.FastMath.abs(sum)))) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		sum2 = 0;
		for (k = 1 ; k < (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT) ; k++) {
			kTerm2 = k * k;
			kTerm4 = kTerm2 * kTerm2;
			increment = (((-pi4) * kTerm4) + (((3 * (org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) * z2)) * (org.apache.commons.math3.util.FastMath.exp((((-(org.apache.commons.math3.util.MathUtils.PI_SQUARED)) * kTerm2) / twoZ2)));
			sum2 += increment;
			if ((org.apache.commons.math3.util.FastMath.abs(increment)) < ((org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.PG_SUM_RELATIVE_ERROR) * (org.apache.commons.math3.util.FastMath.abs(sum2)))) {
				break;
			} 
		}
		if (k == (org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT)) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT);
		} 
		return ret + ((sqrtHalfPi / (sqrtN * n)) * ((sum / ((3240 * z6) * z4)) + ((+sum2) / (108 * z6))));
	}

	private org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> createExactH(double d, int n) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.fraction.FractionConversionException {
		final int k = ((int)(java.lang.Math.ceil((n * d))));
		final int m = (2 * k) - 1;
		final double hDouble = k - (n * d);
		if (hDouble >= 1) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(hDouble , 1.0 , false);
		} 
		org.apache.commons.math3.fraction.BigFraction h = null;
		try {
			h = new org.apache.commons.math3.fraction.BigFraction(hDouble , 1.0E-20 , 10000);
		} catch (final org.apache.commons.math3.fraction.FractionConversionException e1) {
			try {
				h = new org.apache.commons.math3.fraction.BigFraction(hDouble , 1.0E-10 , 10000);
			} catch (final org.apache.commons.math3.fraction.FractionConversionException e2) {
				h = new org.apache.commons.math3.fraction.BigFraction(hDouble , 1.0E-5 , 10000);
			}
		}
		final org.apache.commons.math3.fraction.BigFraction[][] Hdata = new org.apache.commons.math3.fraction.BigFraction[m][m];
		for (int i = 0 ; i < m ; ++i) {
			for (int j = 0 ; j < m ; ++j) {
				if (((i - j) + 1) < 0) {
					Hdata[i][j] = org.apache.commons.math3.fraction.BigFraction.ZERO;
				} else {
					Hdata[i][j] = org.apache.commons.math3.fraction.BigFraction.ONE;
				}
			}
		}
		final org.apache.commons.math3.fraction.BigFraction[] hPowers = new org.apache.commons.math3.fraction.BigFraction[m];
		hPowers[0] = h;
		for (int i = 1 ; i < m ; ++i) {
			hPowers[i] = h.multiply(hPowers[(i - 1)]);
		}
		for (int i = 0 ; i < m ; ++i) {
			Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
			Hdata[(m - 1)][i] = Hdata[(m - 1)][i].subtract(hPowers[((m - i) - 1)]);
		}
		if ((h.compareTo(org.apache.commons.math3.fraction.BigFraction.ONE_HALF)) == 1) {
			Hdata[(m - 1)][0] = Hdata[(m - 1)][0].add(h.multiply(2).subtract(1).pow(m));
		} 
		for (int i = 0 ; i < m ; ++i) {
			for (int j = 0 ; j < (i + 1) ; ++j) {
				if (((i - j) + 1) > 0) {
					for (int g = 2 ; g <= ((i - j) + 1) ; ++g) {
						Hdata[i][j] = Hdata[i][j].divide(g);
					}
				} 
			}
		}
		return new org.apache.commons.math3.linear.Array2DRowFieldMatrix<org.apache.commons.math3.fraction.BigFraction>(org.apache.commons.math3.fraction.BigFractionField.getInstance() , Hdata);
	}

	private org.apache.commons.math3.linear.RealMatrix createRoundedH(double d, int n) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		final int k = ((int)(java.lang.Math.ceil((n * d))));
		final int m = (2 * k) - 1;
		final double h = k - (n * d);
		if (h >= 1) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(h , 1.0 , false);
		} 
		final double[][] Hdata = new double[m][m];
		for (int i = 0 ; i < m ; ++i) {
			for (int j = 0 ; j < m ; ++j) {
				if (((i - j) + 1) < 0) {
					Hdata[i][j] = 0;
				} else {
					Hdata[i][j] = 1;
				}
			}
		}
		final double[] hPowers = new double[m];
		hPowers[0] = h;
		for (int i = 1 ; i < m ; ++i) {
			hPowers[i] = h * (hPowers[(i - 1)]);
		}
		for (int i = 0 ; i < m ; ++i) {
			Hdata[i][0] = (Hdata[i][0]) - (hPowers[i]);
			Hdata[(m - 1)][i] -= hPowers[((m - i) - 1)];
		}
		if ((java.lang.Double.compare(h, 0.5)) > 0) {
			Hdata[(m - 1)][0] += org.apache.commons.math3.util.FastMath.pow(((2 * h) - 1), m);
		} 
		for (int i = 0 ; i < m ; ++i) {
			for (int j = 0 ; j < (i + 1) ; ++j) {
				if (((i - j) + 1) > 0) {
					for (int g = 2 ; g <= ((i - j) + 1) ; ++g) {
						Hdata[i][j] /= g;
					}
				} 
			}
		}
		return org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(Hdata);
	}

	private void checkArray(double[] array) {
		if (array == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NULL_NOT_ALLOWED);
		} 
		if ((array.length) < 2) {
			throw new org.apache.commons.math3.exception.InsufficientDataException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE , array.length , 2);
		} 
	}

	public double ksSum(double t, double tolerance, int maxIterations) {
		final double x = ((-2) * t) * t;
		int sign = -1;
		long i = 1;
		double partialSum = 0.5;
		double delta = 1;
		while ((delta > tolerance) && (i < maxIterations)) {
			delta = org.apache.commons.math3.util.FastMath.exp(((x * i) * i));
			partialSum += sign * delta;
			sign *= -1;
			i++;
		}
		if (i == maxIterations) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(maxIterations);
		} 
		return partialSum * 2;
	}

	public double exactP(double d, int n, int m, boolean strict) {
		java.util.Iterator<int[]> combinationsIterator = org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator((n + m), n);
		long tail = 0;
		final double[] nSet = new double[n];
		final double[] mSet = new double[m];
		while (combinationsIterator.hasNext()) {
			final int[] nSetI = combinationsIterator.next();
			int j = 0;
			int k = 0;
			for (int i = 0 ; i < (n + m) ; i++) {
				if ((j < n) && ((nSetI[j]) == i)) {
					nSet[(j++)] = i;
				} else {
					mSet[(k++)] = i;
				}
			}
			final double curD = kolmogorovSmirnovStatistic(nSet, mSet);
			if (curD > d) {
				tail++;
			} else if ((curD == d) && (!strict)) {
				tail++;
			} 
		}
		return ((double)(tail)) / ((double)(org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient((n + m), n)));
	}

	public double approximateP(double d, int n, int m) {
		final double dm = m;
		final double dn = n;
		return 1 - (ksSum((d * (org.apache.commons.math3.util.FastMath.sqrt(((dm * dn) / (dm + dn))))), org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.KS_SUM_CAUCHY_CRITERION, org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.MAXIMUM_PARTIAL_SUM_COUNT));
	}

	public double monteCarloP(double d, int n, int m, boolean strict, int iterations) {
		final int[] nPlusMSet = org.apache.commons.math3.util.MathArrays.natural((m + n));
		final double[] nSet = new double[n];
		final double[] mSet = new double[m];
		int tail = 0;
		for (int i = 0 ; i < iterations ; i++) {
			copyPartition(nSet, mSet, nPlusMSet, n, m);
			final double curD = kolmogorovSmirnovStatistic(nSet, mSet);
			if (curD > d) {
				tail++;
			} else if ((curD == d) && (!strict)) {
				tail++;
			} 
			org.apache.commons.math3.util.MathArrays.shuffle(nPlusMSet, rng);
			java.util.Arrays.sort(nPlusMSet, 0, n);
		}
		return ((double)(tail)) / iterations;
	}

	private void copyPartition(double[] nSet, double[] mSet, int[] nSetI, int n, int m) {
		int j = 0;
		int k = 0;
		for (int i = 0 ; i < (n + m) ; i++) {
			if ((j < n) && ((nSetI[j]) == i)) {
				nSet[(j++)] = i;
			} else {
				mSet[(k++)] = i;
			}
		}
	}
}

