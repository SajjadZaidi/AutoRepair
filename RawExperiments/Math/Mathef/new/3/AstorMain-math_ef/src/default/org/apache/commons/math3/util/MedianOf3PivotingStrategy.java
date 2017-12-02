package org.apache.commons.math3.util;


public class MedianOf3PivotingStrategy implements java.io.Serializable , org.apache.commons.math3.util.PivotingStrategyInterface {
	private static final long serialVersionUID = 20140713L;

	public int pivotIndex(final double[] work, final int begin, final int end) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		org.apache.commons.math3.util.MathArrays.verifyValues(work, begin, (end - begin));
		final int inclusiveEnd = end - 1;
		final int middle = begin + ((inclusiveEnd - begin) / 2);
		final double wBegin = work[begin];
		final double wMiddle = work[middle];
		final double wEnd = work[inclusiveEnd];
		if (wBegin < wMiddle) {
			if (wMiddle < wEnd) {
				return middle;
			} else {
				return wBegin < wEnd ? inclusiveEnd : begin;
			}
		} else {
			if (wBegin < wEnd) {
				return begin;
			} else {
				return wMiddle < wEnd ? inclusiveEnd : middle;
			}
		}
	}
}

