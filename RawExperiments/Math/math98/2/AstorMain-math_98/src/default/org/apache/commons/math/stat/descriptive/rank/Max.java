package org.apache.commons.math.stat.descriptive.rank;


public class Max extends org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic {
	private static final long serialVersionUID = -5593383832225844641L;

	private long n;

	private double value;

	public Max() {
		n = 0;
		value = java.lang.Double.NaN;
	}

	public void increment(final double d) {
		if ((d > (value)) || (java.lang.Double.isNaN(value))) {
			value = d;
		} 
		(n)++;
	}

	public void clear() {
		value = java.lang.Double.NaN;
		n = 0;
	}

	public double getResult() {
		return value;
	}

	public long getN() {
		return n;
	}

	public double evaluate(final double[] values, final int begin, final int length) {
		double max = java.lang.Double.NaN;
		if (test(values, begin, length)) {
			max = values[begin];
			for (int i = begin ; i < (begin + length) ; i++) {
				if (!(java.lang.Double.isNaN(values[i]))) {
					max = max > (values[i]) ? max : values[i];
				} 
			}
		} 
		return max;
	}
}

