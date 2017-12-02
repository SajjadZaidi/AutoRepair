package org.apache.commons.math3.util;


public class Incrementor {
	private int maximalCount;

	private int count = 0;

	private final org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback maxCountCallback;

	public Incrementor() {
		this(0);
	}

	public Incrementor(int max) {
		this(max, new org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback() {
			public void trigger(int max) throws org.apache.commons.math3.exception.MaxCountExceededException {
				throw new org.apache.commons.math3.exception.MaxCountExceededException(max);
			}
		});
	}

	public Incrementor(int max ,org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback cb) throws org.apache.commons.math3.exception.NullArgumentException {
		if (cb == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		maximalCount = max;
		maxCountCallback = cb;
	}

	public void setMaximalCount(int max) {
		maximalCount = max;
	}

	public int getMaximalCount() {
		return maximalCount;
	}

	public int getCount() {
		return count;
	}

	public boolean canIncrement() {
		return (count) < (maximalCount);
	}

	public void incrementCount(int value) throws org.apache.commons.math3.exception.MaxCountExceededException {
		for (int i = 0 ; i < value ; i++) {
			incrementCount();
		}
	}

	public void incrementCount() throws org.apache.commons.math3.exception.MaxCountExceededException {
		if ((++(count)) > (maximalCount)) {
			maxCountCallback.trigger(maximalCount);
		} 
	}

	public void resetCount() {
		count = 0;
	}

	public interface MaxCountExceededCallback {
		void trigger(int maximalCount) throws org.apache.commons.math3.exception.MaxCountExceededException;
	}
}

