package org.apache.commons.math3.util;


public class CentralPivotingStrategy implements java.io.Serializable , org.apache.commons.math3.util.PivotingStrategyInterface {
	private static final long serialVersionUID = 20140713L;

	public int pivotIndex(final double[] work, final int begin, final int end) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		org.apache.commons.math3.util.MathArrays.verifyValues(work, begin, (end - begin));
		return begin + ((end - begin) / 2);
	}
}

