package org.apache.commons.math3.stat.descriptive;


public abstract class AbstractUnivariateStatistic implements org.apache.commons.math3.stat.descriptive.UnivariateStatistic {
	private double[] storedData;

	public void setData(final double[] values) {
		storedData = values == null ? null : values.clone();
	}

	public double[] getData() {
		return (storedData) == null ? null : storedData.clone();
	}

	protected double[] getDataRef() {
		return storedData;
	}

	public void setData(final double[] values, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (values == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INPUT_ARRAY);
		} 
		if (begin < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.START_POSITION , begin);
		} 
		if (length < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.LENGTH , length);
		} 
		if ((begin + length) > (values.length)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END , (begin + length) , values.length , true);
		} 
		storedData = new double[length];
		java.lang.System.arraycopy(values, begin, storedData, 0, length);
	}

	public double evaluate() throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return evaluate(storedData);
	}

	public double evaluate(final double[] values) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		test(values, 0, 0);
		return evaluate(values, 0, values.length);
	}

	public abstract double evaluate(final double[] values, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException;

	public abstract org.apache.commons.math3.stat.descriptive.UnivariateStatistic copy();

	protected boolean test(final double[] values, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length, false);
	}

	protected boolean test(final double[] values, final int begin, final int length, final boolean allowEmpty) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length, allowEmpty);
	}

	protected boolean test(final double[] values, final double[] weights, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, weights, begin, length, false);
	}

	protected boolean test(final double[] values, final double[] weights, final int begin, final int length, final boolean allowEmpty) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, weights, begin, length, allowEmpty);
	}
}

