package org.apache.commons.math3.stat.descriptive;


public class StatisticalSummaryValues implements java.io.Serializable , org.apache.commons.math3.stat.descriptive.StatisticalSummary {
	private static final long serialVersionUID = -5108854841843722536L;

	private final double mean;

	private final double variance;

	private final long n;

	private final double max;

	private final double min;

	private final double sum;

	public StatisticalSummaryValues(double mean ,double variance ,long n ,double max ,double min ,double sum) {
		super();
		this.mean = mean;
		this.variance = variance;
		this.n = n;
		this.max = max;
		this.min = min;
		this.sum = sum;
	}

	public double getMax() {
		return max;
	}

	public double getMean() {
		return mean;
	}

	public double getMin() {
		return min;
	}

	public long getN() {
		return n;
	}

	public double getSum() {
		return sum;
	}

	public double getStandardDeviation() {
		return org.apache.commons.math3.util.FastMath.sqrt(variance);
	}

	public double getVariance() {
		return variance;
	}

	@java.lang.Override
	public boolean equals(java.lang.Object object) {
		if (object == (org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.this)) {
			return true;
		} 
		if ((object instanceof org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues) == false) {
			return false;
		} 
		org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues stat = ((org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues)(object));
		return (((((org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getMax(), getMax())) && (org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && (org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && (org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getN(), getN()))) && (org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getSum(), getSum()))) && (org.apache.commons.math3.util.Precision.equalsIncludingNaN(stat.getVariance(), getVariance()));
	}

	@java.lang.Override
	public int hashCode() {
		int result = 31 + (org.apache.commons.math3.util.MathUtils.hash(getMax()));
		result = (result * 31) + (org.apache.commons.math3.util.MathUtils.hash(getMean()));
		result = (result * 31) + (org.apache.commons.math3.util.MathUtils.hash(getMin()));
		result = (result * 31) + (org.apache.commons.math3.util.MathUtils.hash(getN()));
		result = (result * 31) + (org.apache.commons.math3.util.MathUtils.hash(getSum()));
		result = (result * 31) + (org.apache.commons.math3.util.MathUtils.hash(getVariance()));
		return result;
	}

	@java.lang.Override
	public java.lang.String toString() {
		java.lang.StringBuffer outBuffer = new java.lang.StringBuffer();
		java.lang.String endl = "\n";
		outBuffer.append("StatisticalSummaryValues:").append(endl);
		outBuffer.append("n: ").append(getN()).append(endl);
		outBuffer.append("min: ").append(getMin()).append(endl);
		outBuffer.append("max: ").append(getMax()).append(endl);
		outBuffer.append("mean: ").append(getMean()).append(endl);
		outBuffer.append("std dev: ").append(getStandardDeviation()).append(endl);
		outBuffer.append("variance: ").append(getVariance()).append(endl);
		outBuffer.append("sum: ").append(getSum()).append(endl);
		return outBuffer.toString();
	}
}

