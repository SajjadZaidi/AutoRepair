package org.apache.commons.math3.util;


public class KthSelector implements java.io.Serializable {
	private static final long serialVersionUID = 20140713L;

	private static final int MIN_SELECT_SIZE = 15;

	private final org.apache.commons.math3.util.PivotingStrategyInterface pivotingStrategy;

	public KthSelector() {
		this.pivotingStrategy = new org.apache.commons.math3.util.MedianOf3PivotingStrategy();
	}

	public KthSelector(final org.apache.commons.math3.util.PivotingStrategyInterface pivotingStrategy) throws org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(pivotingStrategy);
		this.pivotingStrategy = pivotingStrategy;
	}

	public org.apache.commons.math3.util.PivotingStrategyInterface getPivotingStrategy() {
		return pivotingStrategy;
	}

	public double select(final double[] work, final int[] pivotsHeap, final int k) {
		int begin = 0;
		int end = work.length;
		int node = 0;
		final boolean usePivotsHeap = pivotsHeap != null;
		while ((end - begin) > (org.apache.commons.math3.util.KthSelector.MIN_SELECT_SIZE)) {
			final int pivot;
			if ((usePivotsHeap && (node < (pivotsHeap.length))) && ((pivotsHeap[node]) >= 0)) {
				pivot = pivotsHeap[node];
			} else {
				pivot = partition(work, begin, end, pivotingStrategy.pivotIndex(work, begin, end));
				if (usePivotsHeap && (node < (pivotsHeap.length))) {
					pivotsHeap[node] = pivot;
				} 
			}
			if (k == pivot) {
				return work[k];
			} else if (k < pivot) {
				end = pivot;
				node = org.apache.commons.math3.util.FastMath.min(((2 * node) + 1), (usePivotsHeap ? pivotsHeap.length : end));
			} else {
				begin = pivot + 1;
				node = org.apache.commons.math3.util.FastMath.min(((2 * node) + 2), (usePivotsHeap ? pivotsHeap.length : end));
			}
		}
		java.util.Arrays.sort(work, begin, end);
		return work[k];
	}

	private int partition(final double[] work, final int begin, final int end, final int pivot) {
		final double value = work[pivot];
		work[pivot] = work[begin];
		int i = begin + 1;
		int j = end - 1;
		while (i < j) {
			while ((i < j) && ((work[j]) > value)) {
				--j;
			}
			while ((i < j) && ((work[i]) < value)) {
				++i;
			}
			if (i < j) {
				final double tmp = work[i];
				work[(i++)] = work[j];
				work[(j--)] = tmp;
			} 
		}
		if ((i >= end) || ((work[i]) > value)) {
			--i;
		} 
		work[begin] = work[i];
		work[i] = value;
		return i;
	}
}

