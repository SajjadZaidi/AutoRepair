package org.apache.commons.math3.util;


public interface PivotingStrategyInterface {
	int pivotIndex(double[] work, int begin, int end) throws org.apache.commons.math3.exception.MathIllegalArgumentException;
}

