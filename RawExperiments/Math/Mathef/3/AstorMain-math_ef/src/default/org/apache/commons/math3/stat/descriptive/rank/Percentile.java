package org.apache.commons.math3.stat.descriptive.rank;


public class Percentile extends org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic implements java.io.Serializable {
	private static final long serialVersionUID = -8091216485095130416L;

	private static final int MAX_CACHED_LEVELS = 10;

	private static final int PIVOTS_HEAP_LENGTH = 1 << ((org.apache.commons.math3.stat.descriptive.rank.Percentile.MAX_CACHED_LEVELS) - 1);

	private final org.apache.commons.math3.util.KthSelector kthSelector;

	private final org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType estimationType;

	private final org.apache.commons.math3.stat.ranking.NaNStrategy nanStrategy;

	private double quantile;

	private int[] cachedPivots;

	public Percentile() {
		this(50.0);
	}

	public Percentile(final double quantile) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		this(quantile, org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.LEGACY, org.apache.commons.math3.stat.ranking.NaNStrategy.REMOVED, new org.apache.commons.math3.util.KthSelector(new org.apache.commons.math3.util.MedianOf3PivotingStrategy()));
	}

	public Percentile(final org.apache.commons.math3.stat.descriptive.rank.Percentile original) throws org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(original);
		estimationType = original.getEstimationType();
		nanStrategy = original.getNaNStrategy();
		kthSelector = original.getKthSelector();
		setData(original.getDataRef());
		if ((original.cachedPivots) != null) {
			java.lang.System.arraycopy(original.cachedPivots, 0, cachedPivots, 0, original.cachedPivots.length);
		} 
		setQuantile(original.quantile);
	}

	protected Percentile(final double quantile ,final org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType estimationType ,final org.apache.commons.math3.stat.ranking.NaNStrategy nanStrategy ,final org.apache.commons.math3.util.KthSelector kthSelector) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		setQuantile(quantile);
		cachedPivots = null;
		org.apache.commons.math3.util.MathUtils.checkNotNull(estimationType);
		org.apache.commons.math3.util.MathUtils.checkNotNull(nanStrategy);
		org.apache.commons.math3.util.MathUtils.checkNotNull(kthSelector);
		this.estimationType = estimationType;
		this.nanStrategy = nanStrategy;
		this.kthSelector = kthSelector;
	}

	@java.lang.Override
	public void setData(final double[] values) {
		if (values == null) {
			cachedPivots = null;
		} else {
			cachedPivots = new int[org.apache.commons.math3.stat.descriptive.rank.Percentile.PIVOTS_HEAP_LENGTH];
			java.util.Arrays.fill(cachedPivots, (-1));
		}
		super.setData(values);
	}

	@java.lang.Override
	public void setData(final double[] values, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (values == null) {
			cachedPivots = null;
		} else {
			cachedPivots = new int[org.apache.commons.math3.stat.descriptive.rank.Percentile.PIVOTS_HEAP_LENGTH];
			java.util.Arrays.fill(cachedPivots, (-1));
		}
		super.setData(values, begin, length);
	}

	public double evaluate(final double p) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return evaluate(getDataRef(), p);
	}

	public double evaluate(final double[] values, final double p) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		test(values, 0, 0);
		return evaluate(values, 0, values.length, p);
	}

	@java.lang.Override
	public double evaluate(final double[] values, final int start, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return evaluate(values, start, length, quantile);
	}

	public double evaluate(final double[] values, final int begin, final int length, final double p) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		test(values, begin, length);
		if ((p > 100) || (p <= 0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE , p , 0 , 100);
		} 
		if (length == 0) {
			return java.lang.Double.NaN;
		} 
		if (length == 1) {
			return values[begin];
		} 
		final double[] work = getWorkArray(values, begin, length);
		final int[] pivotsHeap = getPivots(values);
		return (work.length) == 0 ? java.lang.Double.NaN : estimationType.evaluate(work, pivotsHeap, p, kthSelector);
	}

	@java.lang.Deprecated
	int medianOf3(final double[] work, final int begin, final int end) {
		return new org.apache.commons.math3.util.MedianOf3PivotingStrategy().pivotIndex(work, begin, end);
	}

	public double getQuantile() {
		return quantile;
	}

	public void setQuantile(final double p) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if ((p <= 0) || (p > 100)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE , p , 0 , 100);
		} 
		quantile = p;
	}

	@java.lang.Override
	public org.apache.commons.math3.stat.descriptive.rank.Percentile copy() {
		return new org.apache.commons.math3.stat.descriptive.rank.Percentile(org.apache.commons.math3.stat.descriptive.rank.Percentile.this);
	}

	@java.lang.Deprecated
	public static void copy(final org.apache.commons.math3.stat.descriptive.rank.Percentile source, final org.apache.commons.math3.stat.descriptive.rank.Percentile dest) throws org.apache.commons.math3.exception.MathUnsupportedOperationException {
		throw new org.apache.commons.math3.exception.MathUnsupportedOperationException();
	}

	protected double[] getWorkArray(final double[] values, final int begin, final int length) {
		final double[] work;
		if (values == (getDataRef())) {
			work = getDataRef();
		} else {
			switch (nanStrategy) {
				case MAXIMAL :
					work = org.apache.commons.math3.stat.descriptive.rank.Percentile.replaceAndSlice(values, begin, length, java.lang.Double.NaN, java.lang.Double.POSITIVE_INFINITY);
					break;
				case MINIMAL :
					work = org.apache.commons.math3.stat.descriptive.rank.Percentile.replaceAndSlice(values, begin, length, java.lang.Double.NaN, java.lang.Double.NEGATIVE_INFINITY);
					break;
				case REMOVED :
					work = org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice(values, begin, length, java.lang.Double.NaN);
					break;
				case FAILED :
					work = org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf(values, begin, length);
					org.apache.commons.math3.util.MathArrays.checkNotNaN(work);
					break;
				default :
					work = org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf(values, begin, length);
					break;
			}
		}
		return work;
	}

	private static double[] copyOf(final double[] values, final int begin, final int length) {
		org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length);
		return org.apache.commons.math3.util.MathArrays.copyOfRange(values, begin, (begin + length));
	}

	private static double[] replaceAndSlice(final double[] values, final int begin, final int length, final double original, final double replacement) {
		final double[] temp = org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf(values, begin, length);
		for (int i = 0 ; i < length ; i++) {
			temp[i] = org.apache.commons.math3.util.Precision.equalsIncludingNaN(original, temp[i]) ? replacement : temp[i];
		}
		return temp;
	}

	private static double[] removeAndSlice(final double[] values, final int begin, final int length, final double removedValue) {
		org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length);
		final double[] temp;
		final java.util.BitSet bits = new java.util.BitSet(length);
		for (int i = begin ; i < (begin + length) ; i++) {
			if (org.apache.commons.math3.util.Precision.equalsIncludingNaN(removedValue, values[i])) {
				bits.set((i - begin));
			} 
		}
		if (bits.isEmpty()) {
			temp = org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf(values, begin, length);
		} else if ((bits.cardinality()) == length) {
			temp = new double[0];
		} else {
			temp = new double[length - (bits.cardinality())];
			int start = begin;
			int dest = 0;
			int nextOne = -1;
			int bitSetPtr = 0;
			while ((nextOne = bits.nextSetBit(bitSetPtr)) != (-1)) {
				final int lengthToCopy = nextOne - bitSetPtr;
				java.lang.System.arraycopy(values, start, temp, dest, lengthToCopy);
				dest += lengthToCopy;
				start = begin + (bitSetPtr = bits.nextClearBit(nextOne));
			}
			if (start < (begin + length)) {
				java.lang.System.arraycopy(values, start, temp, dest, ((begin + length) - start));
			} 
		}
		return temp;
	}

	private int[] getPivots(final double[] values) {
		final int[] pivotsHeap;
		if (values == (getDataRef())) {
			pivotsHeap = cachedPivots;
		} else {
			pivotsHeap = new int[org.apache.commons.math3.stat.descriptive.rank.Percentile.PIVOTS_HEAP_LENGTH];
			java.util.Arrays.fill(pivotsHeap, (-1));
		}
		return pivotsHeap;
	}

	public org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType getEstimationType() {
		return estimationType;
	}

	public org.apache.commons.math3.stat.descriptive.rank.Percentile withEstimationType(final org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType newEstimationType) {
		return new org.apache.commons.math3.stat.descriptive.rank.Percentile(quantile , newEstimationType , nanStrategy , kthSelector);
	}

	public org.apache.commons.math3.stat.ranking.NaNStrategy getNaNStrategy() {
		return nanStrategy;
	}

	public org.apache.commons.math3.stat.descriptive.rank.Percentile withNaNStrategy(final org.apache.commons.math3.stat.ranking.NaNStrategy newNaNStrategy) {
		return new org.apache.commons.math3.stat.descriptive.rank.Percentile(quantile , estimationType , newNaNStrategy , kthSelector);
	}

	public org.apache.commons.math3.util.KthSelector getKthSelector() {
		return kthSelector;
	}

	public org.apache.commons.math3.util.PivotingStrategyInterface getPivotingStrategy() {
		return kthSelector.getPivotingStrategy();
	}

	public org.apache.commons.math3.stat.descriptive.rank.Percentile withKthSelector(final org.apache.commons.math3.util.KthSelector newKthSelector) {
		return new org.apache.commons.math3.stat.descriptive.rank.Percentile(quantile , estimationType , nanStrategy , newKthSelector);
	}

	public static enum EstimationType {
LEGACY("Legacy Apache Commons Math") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 0.0;
				final double maxLimit = 1.0;
				return (java.lang.Double.compare(p, minLimit)) == 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) == 0 ? length : p * (length + 1);
			}
		}, R_1("R-1") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 0.0;
				return (java.lang.Double.compare(p, minLimit)) == 0 ? 0 : (length * p) + 0.5;
			}

			@java.lang.Override
			protected double estimate(final double[] values, final int[] pivotsHeap, final double pos, final int length, final org.apache.commons.math3.util.KthSelector kthSelector) {
				return super.estimate(values, pivotsHeap, org.apache.commons.math3.util.FastMath.ceil((pos - 0.5)), length, kthSelector);
			}
		}, R_2("R-2") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 0.0;
				final double maxLimit = 1.0;
				return (java.lang.Double.compare(p, maxLimit)) == 0 ? length : (java.lang.Double.compare(p, minLimit)) == 0 ? 0 : (length * p) + 0.5;
			}

			@java.lang.Override
			protected double estimate(final double[] values, final int[] pivotsHeap, final double pos, final int length, final org.apache.commons.math3.util.KthSelector kthSelector) {
				final double low = super.estimate(values, pivotsHeap, org.apache.commons.math3.util.FastMath.ceil((pos - 0.5)), length, kthSelector);
				final double high = super.estimate(values, pivotsHeap, org.apache.commons.math3.util.FastMath.floor((pos + 0.5)), length, kthSelector);
				return (low + high) / 2;
			}
		}, R_3("R-3") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = (1.0 / 2) / length;
				return (java.lang.Double.compare(p, minLimit)) <= 0 ? 0 : org.apache.commons.math3.util.FastMath.rint((length * p));
			}
		}, R_4("R-4") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 1.0 / length;
				final double maxLimit = 1.0;
				return (java.lang.Double.compare(p, minLimit)) < 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) == 0 ? length : length * p;
			}
		}, R_5("R-5") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = (1.0 / 2) / length;
				final double maxLimit = (length - 0.5) / length;
				return (java.lang.Double.compare(p, minLimit)) < 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) >= 0 ? length : (length * p) + 0.5;
			}
		}, R_6("R-6") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 1.0 / (length + 1);
				final double maxLimit = (1.0 * length) / (length + 1);
				return (java.lang.Double.compare(p, minLimit)) < 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) >= 0 ? length : (length + 1) * p;
			}
		}, R_7("R-7") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = 0.0;
				final double maxLimit = 1.0;
				return (java.lang.Double.compare(p, minLimit)) == 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) == 0 ? length : 1 + ((length - 1) * p);
			}
		}, R_8("R-8") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = (2 * (1.0 / 3)) / (length + (1.0 / 3));
				final double maxLimit = (length - (1.0 / 3)) / (length + (1.0 / 3));
				return (java.lang.Double.compare(p, minLimit)) < 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) >= 0 ? length : ((length + (1.0 / 3)) * p) + (1.0 / 3);
			}
		}, R_9("R-9") {
			@java.lang.Override
			protected double index(final double p, final int length) {
				final double minLimit = (5.0 / 8) / (length + 0.25);
				final double maxLimit = (length - (3.0 / 8)) / (length + 0.25);
				return (java.lang.Double.compare(p, minLimit)) < 0 ? 0 : (java.lang.Double.compare(p, maxLimit)) >= 0 ? length : ((length + 0.25) * p) + (3.0 / 8);
			}
		};
		private final java.lang.String name;
		private EstimationType(final java.lang.String type) {
			this.name = type;
		}
		protected abstract double index(final double p, final int length);

		protected double estimate(final double[] work, final int[] pivotsHeap, final double pos, final int length, final org.apache.commons.math3.util.KthSelector kthSelector) {
			final double fpos = org.apache.commons.math3.util.FastMath.floor(pos);
			final int intPos = ((int)(fpos));
			final double dif = pos - fpos;
			if (pos < 1) {
				return kthSelector.select(work, pivotsHeap, 0);
			} 
			if (pos >= length) {
				return kthSelector.select(work, pivotsHeap, (length - 1));
			} 
			final double lower = kthSelector.select(work, pivotsHeap, (intPos - 1));
			final double upper = kthSelector.select(work, pivotsHeap, intPos);
			return lower + (dif * (upper - lower));
		}

		protected double evaluate(final double[] work, final int[] pivotsHeap, final double p, final org.apache.commons.math3.util.KthSelector kthSelector) {
			org.apache.commons.math3.util.MathUtils.checkNotNull(work);
			if ((p > 100) || (p <= 0)) {
				throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE , p , 0 , 100);
			} 
			return estimate(work, pivotsHeap, index((p / 100.0), work.length), work.length, kthSelector);
		}

		public double evaluate(final double[] work, final double p, final org.apache.commons.math3.util.KthSelector kthSelector) {
			return org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.this.evaluate(work, null, p, kthSelector);
		}

		java.lang.String getName() {
			return name;
		}
	}
}

