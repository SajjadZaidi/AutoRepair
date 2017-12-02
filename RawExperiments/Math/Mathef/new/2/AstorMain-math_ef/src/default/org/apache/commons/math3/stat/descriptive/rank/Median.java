package org.apache.commons.math3.stat.descriptive.rank;


public class Median extends org.apache.commons.math3.stat.descriptive.rank.Percentile implements java.io.Serializable {
	private static final long serialVersionUID = -3961477041290915687L;

	private static final double FIXED_QUANTILE_50 = 50.0;

	public Median() {
		super(org.apache.commons.math3.stat.descriptive.rank.Median.FIXED_QUANTILE_50);
	}

	public Median(org.apache.commons.math3.stat.descriptive.rank.Median original) throws org.apache.commons.math3.exception.NullArgumentException {
		super(original);
	}

	private Median(final org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType estimationType ,final org.apache.commons.math3.stat.ranking.NaNStrategy nanStrategy ,final org.apache.commons.math3.util.KthSelector kthSelector) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		super(org.apache.commons.math3.stat.descriptive.rank.Median.FIXED_QUANTILE_50, estimationType, nanStrategy, kthSelector);
	}

	@java.lang.Override
	public org.apache.commons.math3.stat.descriptive.rank.Median withEstimationType(final org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType newEstimationType) {
		return new org.apache.commons.math3.stat.descriptive.rank.Median(newEstimationType , getNaNStrategy() , getKthSelector());
	}

	@java.lang.Override
	public org.apache.commons.math3.stat.descriptive.rank.Median withNaNStrategy(final org.apache.commons.math3.stat.ranking.NaNStrategy newNaNStrategy) {
		return new org.apache.commons.math3.stat.descriptive.rank.Median(getEstimationType() , newNaNStrategy , getKthSelector());
	}

	@java.lang.Override
	public org.apache.commons.math3.stat.descriptive.rank.Median withKthSelector(final org.apache.commons.math3.util.KthSelector newKthSelector) {
		return new org.apache.commons.math3.stat.descriptive.rank.Median(getEstimationType() , getNaNStrategy() , newKthSelector);
	}
}

