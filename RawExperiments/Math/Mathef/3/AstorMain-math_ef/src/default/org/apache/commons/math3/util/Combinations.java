package org.apache.commons.math3.util;


public class Combinations implements java.lang.Iterable<int[]> {
	private final int n;

	private final int k;

	private final org.apache.commons.math3.util.Combinations.IterationOrder iterationOrder;

	private static enum IterationOrder {
LEXICOGRAPHIC;	}

	public Combinations(int n ,int k) {
		this(n, k, org.apache.commons.math3.util.Combinations.IterationOrder.LEXICOGRAPHIC);
	}

	private Combinations(int n ,int k ,org.apache.commons.math3.util.Combinations.IterationOrder iterationOrder) {
		org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial(n, k);
		this.n = n;
		this.k = k;
		this.iterationOrder = iterationOrder;
	}

	public int getN() {
		return n;
	}

	public int getK() {
		return k;
	}

	public java.util.Iterator<int[]> iterator() {
		if (((k) == 0) || ((k) == (n))) {
			return new org.apache.commons.math3.util.Combinations.SingletonIterator(org.apache.commons.math3.util.MathArrays.natural(k));
		} 
		switch (iterationOrder) {
			case LEXICOGRAPHIC :
				return new org.apache.commons.math3.util.Combinations.LexicographicIterator(n , k);
			default :
				throw new org.apache.commons.math3.exception.MathInternalError();
		}
	}

	public java.util.Comparator<int[]> comparator() {
		return new org.apache.commons.math3.util.Combinations.LexicographicComparator(n , k);
	}

	private static class LexicographicIterator implements java.util.Iterator<int[]> {
		private final int k;

		private final int[] c;

		private boolean more = true;

		private int j;

		public LexicographicIterator(int n ,int k) {
			this.k = k;
			c = new int[k + 3];
			if ((k == 0) || (k >= n)) {
				more = false;
				return ;
			} 
			for (int i = 1 ; i <= k ; i++) {
				c[i] = i - 1;
			}
			c[(k + 1)] = n;
			c[(k + 2)] = 0;
			j = k;
		}

		public boolean hasNext() {
			return more;
		}

		public int[] next() {
			if (!(more)) {
				throw new java.util.NoSuchElementException();
			} 
			final int[] ret = new int[k];
			java.lang.System.arraycopy(c, 1, ret, 0, k);
			int x = 0;
			if ((j) > 0) {
				x = j;
				c[j] = x;
				(j)--;
				return ret;
			} 
			if (((c[1]) + 1) < (c[2])) {
				(c[1])++;
				return ret;
			} else {
				j = 2;
			}
			boolean stepDone = false;
			while (!stepDone) {
				c[((j) - 1)] = (j) - 2;
				x = (c[j]) + 1;
				if (x == (c[((j) + 1)])) {
					(j)++;
				} else {
					stepDone = true;
				}
			}
			if ((j) > (k)) {
				more = false;
				return ret;
			} 
			c[j] = x;
			(j)--;
			return ret;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	private static class SingletonIterator implements java.util.Iterator<int[]> {
		private final int[] singleton;

		private boolean more = true;

		public SingletonIterator(final int[] singleton) {
			this.singleton = singleton;
		}

		public boolean hasNext() {
			return more;
		}

		public int[] next() {
			if (more) {
				more = false;
				return singleton;
			} else {
				throw new java.util.NoSuchElementException();
			}
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	private static class LexicographicComparator implements java.io.Serializable , java.util.Comparator<int[]> {
		private static final long serialVersionUID = 20130906L;

		private final int n;

		private final int k;

		public LexicographicComparator(int n ,int k) {
			this.n = n;
			this.k = k;
		}

		public int compare(int[] c1, int[] c2) {
			if ((c1.length) != (k)) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(c1.length , k);
			} 
			if ((c2.length) != (k)) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(c2.length , k);
			} 
			final int[] c1s = org.apache.commons.math3.util.MathArrays.copyOf(c1);
			java.util.Arrays.sort(c1s);
			final int[] c2s = org.apache.commons.math3.util.MathArrays.copyOf(c2);
			java.util.Arrays.sort(c2s);
			final long v1 = lexNorm(c1s);
			final long v2 = lexNorm(c2s);
			if (v1 < v2) {
				return -1;
			} else if (v1 > v2) {
				return 1;
			} else {
				return 0;
			}
		}

		private long lexNorm(int[] c) {
			long ret = 0;
			for (int i = 0 ; i < (c.length) ; i++) {
				final int digit = c[i];
				if ((digit < 0) || (digit >= (n))) {
					throw new org.apache.commons.math3.exception.OutOfRangeException(digit , 0 , ((n) - 1));
				} 
				ret += (c[i]) * (org.apache.commons.math3.util.ArithmeticUtils.pow(n, i));
			}
			return ret;
		}
	}
}

