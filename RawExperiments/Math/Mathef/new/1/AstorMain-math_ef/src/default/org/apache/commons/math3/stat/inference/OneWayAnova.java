package org.apache.commons.math3.stat.inference;


public class OneWayAnova {
	public OneWayAnova() {
	}

	public double anovaFValue(final java.util.Collection<double[]> categoryData) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats a = anovaStats(categoryData);
		return a.F;
	}

	public double anovaPValue(final java.util.Collection<double[]> categoryData) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NullArgumentException {
		final org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats a = anovaStats(categoryData);
		final org.apache.commons.math3.distribution.FDistribution fdist = new org.apache.commons.math3.distribution.FDistribution(null , a.dfbg , a.dfwg);
		return 1.0 - (fdist.cumulativeProbability(a.F));
	}

	public double anovaPValue(final java.util.Collection<org.apache.commons.math3.stat.descriptive.SummaryStatistics> categoryData, final boolean allowOneElementData) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NullArgumentException {
		final org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats a = anovaStats(categoryData, allowOneElementData);
		final org.apache.commons.math3.distribution.FDistribution fdist = new org.apache.commons.math3.distribution.FDistribution(null , a.dfbg , a.dfwg);
		return 1.0 - (fdist.cumulativeProbability(a.F));
	}

	private org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats anovaStats(final java.util.Collection<double[]> categoryData) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(categoryData);
		final java.util.Collection<org.apache.commons.math3.stat.descriptive.SummaryStatistics> categoryDataSummaryStatistics = new java.util.ArrayList<org.apache.commons.math3.stat.descriptive.SummaryStatistics>(categoryData.size());
		for (final double[] data : categoryData) {
			final org.apache.commons.math3.stat.descriptive.SummaryStatistics dataSummaryStatistics = new org.apache.commons.math3.stat.descriptive.SummaryStatistics();
			categoryDataSummaryStatistics.add(dataSummaryStatistics);
			for (final double val : data) {
				dataSummaryStatistics.addValue(val);
			}
		}
		return anovaStats(categoryDataSummaryStatistics, false);
	}

	public boolean anovaTest(final java.util.Collection<double[]> categoryData, final double alpha) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.OutOfRangeException {
		if ((alpha <= 0) || (alpha > 0.5)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL , alpha , 0 , 0.5);
		} 
		return (anovaPValue(categoryData)) < alpha;
	}

	private org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats anovaStats(final java.util.Collection<org.apache.commons.math3.stat.descriptive.SummaryStatistics> categoryData, final boolean allowOneElementData) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(categoryData);
		if (!allowOneElementData) {
			if ((categoryData.size()) < 2) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(org.apache.commons.math3.exception.util.LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED , categoryData.size() , 2);
			} 
			for (final org.apache.commons.math3.stat.descriptive.SummaryStatistics array : categoryData) {
				if ((array.getN()) <= 1) {
					throw new org.apache.commons.math3.exception.DimensionMismatchException(org.apache.commons.math3.exception.util.LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED , ((int)(array.getN())) , 2);
				} 
			}
		} 
		int dfwg = 0;
		double sswg = 0;
		double totsum = 0;
		double totsumsq = 0;
		int totnum = 0;
		for (final org.apache.commons.math3.stat.descriptive.SummaryStatistics data : categoryData) {
			final double sum = data.getSum();
			final double sumsq = data.getSumsq();
			final int num = ((int)(data.getN()));
			totnum += num;
			totsum += sum;
			totsumsq += sumsq;
			dfwg += num - 1;
			final double ss = sumsq - ((sum * sum) / num);
			sswg += ss;
		}
		final double sst = totsumsq - ((totsum * totsum) / totnum);
		final double ssbg = sst - sswg;
		final int dfbg = (categoryData.size()) - 1;
		final double msbg = ssbg / dfbg;
		final double mswg = sswg / dfwg;
		final double F = msbg / mswg;
		return new org.apache.commons.math3.stat.inference.OneWayAnova.AnovaStats(dfbg , dfwg , F);
	}

	private static class AnovaStats {
		private final int dfbg;

		private final int dfwg;

		private final double F;

		private AnovaStats(int dfbg ,int dfwg ,double F) {
			this.dfbg = dfbg;
			this.dfwg = dfwg;
			this.F = F;
		}
	}
}

