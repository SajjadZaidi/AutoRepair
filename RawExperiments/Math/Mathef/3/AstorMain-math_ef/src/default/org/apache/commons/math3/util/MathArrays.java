package org.apache.commons.math3.util;


public class MathArrays {
	private static final int SPLIT_FACTOR = 134217729;

	private MathArrays() {
	}

	public interface Function {
		double evaluate(double[] array);

		double evaluate(double[] array, int startIndex, int numElements);
	}

	public static double[] scale(double val, final double[] arr) {
		double[] newArr = new double[arr.length];
		for (int i = 0 ; i < (arr.length) ; i++) {
			newArr[i] = (arr[i]) * val;
		}
		return newArr;
	}

	public static void scaleInPlace(double val, final double[] arr) {
		for (int i = 0 ; i < (arr.length) ; i++) {
			arr[i] *= val;
		}
	}

	public static double[] ebeAdd(double[] a, double[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] result = a.clone();
		for (int i = 0 ; i < (a.length) ; i++) {
			result[i] += b[i];
		}
		return result;
	}

	public static double[] ebeSubtract(double[] a, double[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] result = a.clone();
		for (int i = 0 ; i < (a.length) ; i++) {
			result[i] -= b[i];
		}
		return result;
	}

	public static double[] ebeMultiply(double[] a, double[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] result = a.clone();
		for (int i = 0 ; i < (a.length) ; i++) {
			result[i] *= b[i];
		}
		return result;
	}

	public static double[] ebeDivide(double[] a, double[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((a.length) != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(a.length , b.length);
		} 
		final double[] result = a.clone();
		for (int i = 0 ; i < (a.length) ; i++) {
			result[i] /= b[i];
		}
		return result;
	}

	public static double distance1(double[] p1, double[] p2) {
		double sum = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			sum += org.apache.commons.math3.util.FastMath.abs(((p1[i]) - (p2[i])));
		}
		return sum;
	}

	public static int distance1(int[] p1, int[] p2) {
		int sum = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			sum += org.apache.commons.math3.util.FastMath.abs(((p1[i]) - (p2[i])));
		}
		return sum;
	}

	public static double distance(double[] p1, double[] p2) {
		double sum = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			final double dp = (p1[i]) - (p2[i]);
			sum += dp * dp;
		}
		return org.apache.commons.math3.util.FastMath.sqrt(sum);
	}

	public static double distance(int[] p1, int[] p2) {
		double sum = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			final double dp = (p1[i]) - (p2[i]);
			sum += dp * dp;
		}
		return org.apache.commons.math3.util.FastMath.sqrt(sum);
	}

	public static double distanceInf(double[] p1, double[] p2) {
		double max = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			max = org.apache.commons.math3.util.FastMath.max(max, org.apache.commons.math3.util.FastMath.abs(((p1[i]) - (p2[i]))));
		}
		return max;
	}

	public static int distanceInf(int[] p1, int[] p2) {
		int max = 0;
		for (int i = 0 ; i < (p1.length) ; i++) {
			max = org.apache.commons.math3.util.FastMath.max(max, org.apache.commons.math3.util.FastMath.abs(((p1[i]) - (p2[i]))));
		}
		return max;
	}

	public static enum OrderDirection {
INCREASING, DECREASING;	}

	public static <T extends java.lang.Comparable<? super T>>boolean isMonotonic(T[] val, org.apache.commons.math3.util.MathArrays.OrderDirection dir, boolean strict) {
		T previous = val[0];
		final int max = val.length;
		for (int i = 1 ; i < max ; i++) {
			final int comp;
			switch (dir) {
				case INCREASING :
					comp = previous.compareTo(val[i]);
					if (strict) {
						if (comp >= 0) {
							return false;
						} 
					} else {
						if (comp > 0) {
							return false;
						} 
					}
					break;
				case DECREASING :
					comp = val[i].compareTo(previous);
					if (strict) {
						if (comp >= 0) {
							return false;
						} 
					} else {
						if (comp > 0) {
							return false;
						} 
					}
					break;
				default :
					throw new org.apache.commons.math3.exception.MathInternalError();
			}
			previous = val[i];
		}
		return true;
	}

	public static boolean isMonotonic(double[] val, org.apache.commons.math3.util.MathArrays.OrderDirection dir, boolean strict) {
		return org.apache.commons.math3.util.MathArrays.checkOrder(val, dir, strict, false);
	}

	public static boolean checkOrder(double[] val, org.apache.commons.math3.util.MathArrays.OrderDirection dir, boolean strict, boolean abort) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
		double previous = val[0];
		final int max = val.length;
		int index;
		ITEM : for (index = 1 ; index < max ; index++) {
			switch (dir) {
				case INCREASING :
					if (strict) {
						if ((val[index]) <= previous) {
							break ITEM;
						} 
					} else {
						if ((val[index]) < previous) {
							break ITEM;
						} 
					}
					break;
				case DECREASING :
					if (strict) {
						if ((val[index]) >= previous) {
							break ITEM;
						} 
					} else {
						if ((val[index]) > previous) {
							break ITEM;
						} 
					}
					break;
				default :
					throw new org.apache.commons.math3.exception.MathInternalError();
			}
			previous = val[index];
		}
		if (index == max) {
			return true;
		} 
		if (abort) {
			throw new org.apache.commons.math3.exception.NonMonotonicSequenceException(val[index] , previous , index , dir , strict);
		} else {
			return false;
		}
	}

	public static void checkOrder(double[] val, org.apache.commons.math3.util.MathArrays.OrderDirection dir, boolean strict) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
		org.apache.commons.math3.util.MathArrays.checkOrder(val, dir, strict, true);
	}

	public static void checkOrder(double[] val) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
		org.apache.commons.math3.util.MathArrays.checkOrder(val, org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING, true);
	}

	public static void checkRectangular(final long[][] in) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(in);
		for (int i = 1 ; i < (in.length) ; i++) {
			if ((in[i].length) != (in[0].length)) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(org.apache.commons.math3.exception.util.LocalizedFormats.DIFFERENT_ROWS_LENGTHS , in[i].length , in[0].length);
			} 
		}
	}

	public static void checkPositive(final double[] in) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		for (int i = 0 ; i < (in.length) ; i++) {
			if ((in[i]) <= 0) {
				throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(in[i]);
			} 
		}
	}

	public static void checkNotNaN(final double[] in) throws org.apache.commons.math3.exception.NotANumberException {
		for (int i = 0 ; i < (in.length) ; i++) {
			if (java.lang.Double.isNaN(in[i])) {
				throw new org.apache.commons.math3.exception.NotANumberException();
			} 
		}
	}

	public static void checkNonNegative(final long[] in) throws org.apache.commons.math3.exception.NotPositiveException {
		for (int i = 0 ; i < (in.length) ; i++) {
			if ((in[i]) < 0) {
				throw new org.apache.commons.math3.exception.NotPositiveException(in[i]);
			} 
		}
	}

	public static void checkNonNegative(final long[][] in) throws org.apache.commons.math3.exception.NotPositiveException {
		for (int i = 0 ; i < (in.length) ; i++) {
			for (int j = 0 ; j < (in[i].length) ; j++) {
				if ((in[i][j]) < 0) {
					throw new org.apache.commons.math3.exception.NotPositiveException(in[i][j]);
				} 
			}
		}
	}

	public static double safeNorm(double[] v) {
		double rdwarf = 3.834E-20;
		double rgiant = 1.304E19;
		double s1 = 0;
		double s2 = 0;
		double s3 = 0;
		double x1max = 0;
		double x3max = 0;
		double floatn = v.length;
		double agiant = rgiant / floatn;
		for (int i = 0 ; i < (v.length) ; i++) {
			double xabs = org.apache.commons.math3.util.FastMath.abs(v[i]);
			if ((xabs < rdwarf) || (xabs > agiant)) {
				if (xabs > rdwarf) {
					if (xabs > x1max) {
						double r = x1max / xabs;
						s1 = 1 + ((s1 * r) * r);
						x1max = xabs;
					} else {
						double r = xabs / x1max;
						s1 += r * r;
					}
				} else {
					if (xabs > x3max) {
						double r = x3max / xabs;
						s3 = 1 + ((s3 * r) * r);
						x3max = xabs;
					} else {
						if (xabs != 0) {
							double r = xabs / x3max;
							s3 += r * r;
						} 
					}
				}
			} else {
				s2 += xabs * xabs;
			}
		}
		double norm;
		if (s1 != 0) {
			norm = x1max * (java.lang.Math.sqrt((s1 + ((s2 / x1max) / x1max))));
		} else {
			if (s2 == 0) {
				norm = x3max * (java.lang.Math.sqrt(s3));
			} else {
				if (s2 >= x3max) {
					norm = java.lang.Math.sqrt((s2 * (1 + ((x3max / s2) * (x3max * s3)))));
				} else {
					norm = java.lang.Math.sqrt((x3max * ((s2 / x3max) + (x3max * s3))));
				}
			}
		}
		return norm;
	}

	public static void sortInPlace(double[] x, double[]... yList) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathArrays.sortInPlace(x, org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING, yList);
	}

	public static void sortInPlace(double[] x, final org.apache.commons.math3.util.MathArrays.OrderDirection dir, double[]... yList) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NullArgumentException {
		if (x == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		final int yListLen = yList.length;
		final int len = x.length;
		for (int j = 0 ; j < yListLen ; j++) {
			final double[] y = yList[j];
			if (y == null) {
				throw new org.apache.commons.math3.exception.NullArgumentException();
			} 
			if ((y.length) != len) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(y.length , len);
			} 
		}
		final java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>> list = new java.util.ArrayList<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>>(len);
		for (int i = 0 ; i < len ; i++) {
			list.add(new org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>(x[i] , i));
		}
		final java.util.Comparator<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>> comp = dir == (org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING) ? new java.util.Comparator<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>>() {
			public int compare(org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer> o1, org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		} : new java.util.Comparator<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer>>() {
			public int compare(org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer> o1, org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}
		};
		java.util.Collections.sort(list, comp);
		final int[] indices = new int[len];
		for (int i = 0 ; i < len ; i++) {
			final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Integer> e = list.get(i);
			x[i] = e.getKey();
			indices[i] = e.getValue();
		}
		for (int j = 0 ; j < yListLen ; j++) {
			final double[] yInPlace = yList[j];
			final double[] yOrig = yInPlace.clone();
			for (int i = 0 ; i < len ; i++) {
				yInPlace[i] = yOrig[indices[i]];
			}
		}
	}

	public static int[] copyOf(int[] source) {
		return org.apache.commons.math3.util.MathArrays.copyOf(source, source.length);
	}

	public static double[] copyOf(double[] source) {
		return org.apache.commons.math3.util.MathArrays.copyOf(source, source.length);
	}

	public static int[] copyOf(int[] source, int len) {
		final int[] output = new int[len];
		java.lang.System.arraycopy(source, 0, output, 0, org.apache.commons.math3.util.FastMath.min(len, source.length));
		return output;
	}

	public static double[] copyOf(double[] source, int len) {
		final double[] output = new double[len];
		java.lang.System.arraycopy(source, 0, output, 0, org.apache.commons.math3.util.FastMath.min(len, source.length));
		return output;
	}

	public static double[] copyOfRange(double[] source, int from, int to) {
		final int len = to - from;
		final double[] output = new double[len];
		java.lang.System.arraycopy(source, from, output, 0, org.apache.commons.math3.util.FastMath.min(len, ((source.length) - from)));
		return output;
	}

	public static double linearCombination(final double[] a, final double[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int len = a.length;
		if (len != (b.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(len , b.length);
		} 
		if (len == 1) {
			return (a[0]) * (b[0]);
		} 
		final double[] prodHigh = new double[len];
		double prodLowSum = 0;
		for (int i = 0 ; i < len ; i++) {
			final double ai = a[i];
			final double ca = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * ai;
			final double aHigh = ca - (ca - ai);
			final double aLow = ai - aHigh;
			final double bi = b[i];
			final double cb = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * bi;
			final double bHigh = cb - (cb - bi);
			final double bLow = bi - bHigh;
			prodHigh[i] = ai * bi;
			final double prodLow = (aLow * bLow) - ((((prodHigh[i]) - (aHigh * bHigh)) - (aLow * bHigh)) - (aHigh * bLow));
			prodLowSum += prodLow;
		}
		final double prodHighCur = prodHigh[0];
		double prodHighNext = prodHigh[1];
		double sHighPrev = prodHighCur + prodHighNext;
		double sPrime = sHighPrev - prodHighNext;
		double sLowSum = (prodHighNext - (sHighPrev - sPrime)) + (prodHighCur - sPrime);
		final int lenMinusOne = len - 1;
		for (int i = 1 ; i < lenMinusOne ; i++) {
			prodHighNext = prodHigh[(i + 1)];
			final double sHighCur = sHighPrev + prodHighNext;
			sPrime = sHighCur - prodHighNext;
			sLowSum += (prodHighNext - (sHighCur - sPrime)) + (sHighPrev - sPrime);
			sHighPrev = sHighCur;
		}
		double result = sHighPrev + (prodLowSum + sLowSum);
		if (java.lang.Double.isNaN(result)) {
			result = 0;
			for (int i = 0 ; i < len ; ++i) {
				result += (a[i]) * (b[i]);
			}
		} 
		return result;
	}

	public static double linearCombination(final double a1, final double b1, final double a2, final double b2) {
		final double ca1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a1;
		final double a1High = ca1 - (ca1 - a1);
		final double a1Low = a1 - a1High;
		final double cb1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b1;
		final double b1High = cb1 - (cb1 - b1);
		final double b1Low = b1 - b1High;
		final double prod1High = a1 * b1;
		final double prod1Low = (a1Low * b1Low) - (((prod1High - (a1High * b1High)) - (a1Low * b1High)) - (a1High * b1Low));
		final double ca2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a2;
		final double a2High = ca2 - (ca2 - a2);
		final double a2Low = a2 - a2High;
		final double cb2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b2;
		final double b2High = cb2 - (cb2 - b2);
		final double b2Low = b2 - b2High;
		final double prod2High = a2 * b2;
		final double prod2Low = (a2Low * b2Low) - (((prod2High - (a2High * b2High)) - (a2Low * b2High)) - (a2High * b2Low));
		final double s12High = prod1High + prod2High;
		final double s12Prime = s12High - prod2High;
		final double s12Low = (prod2High - (s12High - s12Prime)) + (prod1High - s12Prime);
		double result = s12High + ((prod1Low + prod2Low) + s12Low);
		if (java.lang.Double.isNaN(result)) {
			result = (a1 * b1) + (a2 * b2);
		} 
		return result;
	}

	public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3) {
		final double ca1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a1;
		final double a1High = ca1 - (ca1 - a1);
		final double a1Low = a1 - a1High;
		final double cb1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b1;
		final double b1High = cb1 - (cb1 - b1);
		final double b1Low = b1 - b1High;
		final double prod1High = a1 * b1;
		final double prod1Low = (a1Low * b1Low) - (((prod1High - (a1High * b1High)) - (a1Low * b1High)) - (a1High * b1Low));
		final double ca2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a2;
		final double a2High = ca2 - (ca2 - a2);
		final double a2Low = a2 - a2High;
		final double cb2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b2;
		final double b2High = cb2 - (cb2 - b2);
		final double b2Low = b2 - b2High;
		final double prod2High = a2 * b2;
		final double prod2Low = (a2Low * b2Low) - (((prod2High - (a2High * b2High)) - (a2Low * b2High)) - (a2High * b2Low));
		final double ca3 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a3;
		final double a3High = ca3 - (ca3 - a3);
		final double a3Low = a3 - a3High;
		final double cb3 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b3;
		final double b3High = cb3 - (cb3 - b3);
		final double b3Low = b3 - b3High;
		final double prod3High = a3 * b3;
		final double prod3Low = (a3Low * b3Low) - (((prod3High - (a3High * b3High)) - (a3Low * b3High)) - (a3High * b3Low));
		final double s12High = prod1High + prod2High;
		final double s12Prime = s12High - prod2High;
		final double s12Low = (prod2High - (s12High - s12Prime)) + (prod1High - s12Prime);
		final double s123High = s12High + prod3High;
		final double s123Prime = s123High - prod3High;
		final double s123Low = (prod3High - (s123High - s123Prime)) + (s12High - s123Prime);
		double result = s123High + ((((prod1Low + prod2Low) + prod3Low) + s12Low) + s123Low);
		if (java.lang.Double.isNaN(result)) {
			result = ((a1 * b1) + (a2 * b2)) + (a3 * b3);
		} 
		return result;
	}

	public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3, final double a4, final double b4) {
		final double ca1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a1;
		final double a1High = ca1 - (ca1 - a1);
		final double a1Low = a1 - a1High;
		final double cb1 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b1;
		final double b1High = cb1 - (cb1 - b1);
		final double b1Low = b1 - b1High;
		final double prod1High = a1 * b1;
		final double prod1Low = (a1Low * b1Low) - (((prod1High - (a1High * b1High)) - (a1Low * b1High)) - (a1High * b1Low));
		final double ca2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a2;
		final double a2High = ca2 - (ca2 - a2);
		final double a2Low = a2 - a2High;
		final double cb2 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b2;
		final double b2High = cb2 - (cb2 - b2);
		final double b2Low = b2 - b2High;
		final double prod2High = a2 * b2;
		final double prod2Low = (a2Low * b2Low) - (((prod2High - (a2High * b2High)) - (a2Low * b2High)) - (a2High * b2Low));
		final double ca3 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a3;
		final double a3High = ca3 - (ca3 - a3);
		final double a3Low = a3 - a3High;
		final double cb3 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b3;
		final double b3High = cb3 - (cb3 - b3);
		final double b3Low = b3 - b3High;
		final double prod3High = a3 * b3;
		final double prod3Low = (a3Low * b3Low) - (((prod3High - (a3High * b3High)) - (a3Low * b3High)) - (a3High * b3Low));
		final double ca4 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * a4;
		final double a4High = ca4 - (ca4 - a4);
		final double a4Low = a4 - a4High;
		final double cb4 = (org.apache.commons.math3.util.MathArrays.SPLIT_FACTOR) * b4;
		final double b4High = cb4 - (cb4 - b4);
		final double b4Low = b4 - b4High;
		final double prod4High = a4 * b4;
		final double prod4Low = (a4Low * b4Low) - (((prod4High - (a4High * b4High)) - (a4Low * b4High)) - (a4High * b4Low));
		final double s12High = prod1High + prod2High;
		final double s12Prime = s12High - prod2High;
		final double s12Low = (prod2High - (s12High - s12Prime)) + (prod1High - s12Prime);
		final double s123High = s12High + prod3High;
		final double s123Prime = s123High - prod3High;
		final double s123Low = (prod3High - (s123High - s123Prime)) + (s12High - s123Prime);
		final double s1234High = s123High + prod4High;
		final double s1234Prime = s1234High - prod4High;
		final double s1234Low = (prod4High - (s1234High - s1234Prime)) + (s123High - s1234Prime);
		double result = s1234High + ((((((prod1Low + prod2Low) + prod3Low) + prod4Low) + s12Low) + s123Low) + s1234Low);
		if (java.lang.Double.isNaN(result)) {
			result = (((a1 * b1) + (a2 * b2)) + (a3 * b3)) + (a4 * b4);
		} 
		return result;
	}

	public static boolean equals(float[] x, float[] y) {
		if ((x == null) || (y == null)) {
			return !((x == null) ^ (y == null));
		} 
		if ((x.length) != (y.length)) {
			return false;
		} 
		for (int i = 0 ; i < (x.length) ; ++i) {
			if (!(org.apache.commons.math3.util.Precision.equals(x[i], y[i]))) {
				return false;
			} 
		}
		return true;
	}

	public static boolean equalsIncludingNaN(float[] x, float[] y) {
		if ((x == null) || (y == null)) {
			return !((x == null) ^ (y == null));
		} 
		if ((x.length) != (y.length)) {
			return false;
		} 
		for (int i = 0 ; i < (x.length) ; ++i) {
			if (!(org.apache.commons.math3.util.Precision.equalsIncludingNaN(x[i], y[i]))) {
				return false;
			} 
		}
		return true;
	}

	public static boolean equals(double[] x, double[] y) {
		if ((x == null) || (y == null)) {
			return !((x == null) ^ (y == null));
		} 
		if ((x.length) != (y.length)) {
			return false;
		} 
		for (int i = 0 ; i < (x.length) ; ++i) {
			if (!(org.apache.commons.math3.util.Precision.equals(x[i], y[i]))) {
				return false;
			} 
		}
		return true;
	}

	public static boolean equalsIncludingNaN(double[] x, double[] y) {
		if ((x == null) || (y == null)) {
			return !((x == null) ^ (y == null));
		} 
		if ((x.length) != (y.length)) {
			return false;
		} 
		for (int i = 0 ; i < (x.length) ; ++i) {
			if (!(org.apache.commons.math3.util.Precision.equalsIncludingNaN(x[i], y[i]))) {
				return false;
			} 
		}
		return true;
	}

	public static double[] normalizeArray(double[] values, double normalizedSum) throws org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (java.lang.Double.isInfinite(normalizedSum)) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NORMALIZE_INFINITE);
		} 
		if (java.lang.Double.isNaN(normalizedSum)) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NORMALIZE_NAN);
		} 
		double sum = 0.0;
		final int len = values.length;
		double[] out = new double[len];
		for (int i = 0 ; i < len ; i++) {
			if (java.lang.Double.isInfinite(values[i])) {
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INFINITE_ARRAY_ELEMENT , values[i] , i);
			} 
			if (!(java.lang.Double.isNaN(values[i]))) {
				sum += values[i];
			} 
		}
		if (sum == 0) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.ARRAY_SUMS_TO_ZERO);
		} 
		for (int i = 0 ; i < len ; i++) {
			if (java.lang.Double.isNaN(values[i])) {
				out[i] = java.lang.Double.NaN;
			} else {
				out[i] = ((values[i]) * normalizedSum) / sum;
			}
		}
		return out;
	}

	public static <T>T[] buildArray(final org.apache.commons.math3.Field<T> field, final int length) {
		@java.lang.SuppressWarnings(value = "unchecked")
		T[] array = ((T[])(java.lang.reflect.Array.newInstance(field.getRuntimeClass(), length)));
		java.util.Arrays.fill(array, field.getZero());
		return array;
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	public static <T>T[][] buildArray(final org.apache.commons.math3.Field<T> field, final int rows, final int columns) {
		final T[][] array;
		if (columns < 0) {
			T[] dummyRow = org.apache.commons.math3.util.MathArrays.buildArray(field, 0);
			array = ((T[][])(java.lang.reflect.Array.newInstance(dummyRow.getClass(), rows)));
		} else {
			array = ((T[][])(java.lang.reflect.Array.newInstance(field.getRuntimeClass(), new int[]{ rows , columns })));
			for (int i = 0 ; i < rows ; ++i) {
				java.util.Arrays.fill(array[i], field.getZero());
			}
		}
		return array;
	}

	public static double[] convolve(double[] x, double[] h) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(x);
		org.apache.commons.math3.util.MathUtils.checkNotNull(h);
		final int xLen = x.length;
		final int hLen = h.length;
		if ((xLen == 0) || (hLen == 0)) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		final int totalLength = (xLen + hLen) - 1;
		final double[] y = new double[totalLength];
		for (int n = 0 ; n < totalLength ; n++) {
			double yn = 0;
			int k = org.apache.commons.math3.util.FastMath.max(0, ((n + 1) - xLen));
			int j = n - k;
			while ((k < hLen) && (j >= 0)) {
				yn += (x[(j--)]) * (h[(k++)]);
			}
			y[n] = yn;
		}
		return y;
	}

	public static enum Position {
HEAD, TAIL;	}

	public static void shuffle(int[] list, int start, org.apache.commons.math3.util.MathArrays.Position pos) {
		org.apache.commons.math3.util.MathArrays.shuffle(list, start, pos, new org.apache.commons.math3.random.Well19937c());
	}

	public static void shuffle(int[] list, int start, org.apache.commons.math3.util.MathArrays.Position pos, org.apache.commons.math3.random.RandomGenerator rng) {
		switch (pos) {
			case TAIL :
				{
					for (int i = (list.length) - 1 ; i >= start ; i--) {
						final int target;
						if (i == start) {
							target = start;
						} else {
							target = new org.apache.commons.math3.distribution.UniformIntegerDistribution(rng , start , i).sample();
						}
						final int temp = list[target];
						list[target] = list[i];
						list[i] = temp;
					}
				}
				break;
			case HEAD :
				{
					for (int i = 0 ; i <= start ; i++) {
						final int target;
						if (i == start) {
							target = start;
						} else {
							target = new org.apache.commons.math3.distribution.UniformIntegerDistribution(rng , i , start).sample();
						}
						final int temp = list[target];
						list[target] = list[i];
						list[i] = temp;
					}
				}
				break;
			default :
				throw new org.apache.commons.math3.exception.MathInternalError();
		}
	}

	public static void shuffle(int[] list, org.apache.commons.math3.random.RandomGenerator rng) {
		org.apache.commons.math3.util.MathArrays.shuffle(list, 0, org.apache.commons.math3.util.MathArrays.Position.TAIL, rng);
	}

	public static void shuffle(int[] list) {
		org.apache.commons.math3.util.MathArrays.shuffle(list, new org.apache.commons.math3.random.Well19937c());
	}

	public static int[] natural(int n) {
		return org.apache.commons.math3.util.MathArrays.sequence(n, 0, 1);
	}

	public static int[] sequence(int size, int start, int stride) {
		final int[] a = new int[size];
		for (int i = 0 ; i < size ; i++) {
			a[i] = start + (i * stride);
		}
		return a;
	}

	public static boolean verifyValues(final double[] values, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length, false);
	}

	public static boolean verifyValues(final double[] values, final int begin, final int length, final boolean allowEmpty) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (values == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INPUT_ARRAY);
		} 
		if (begin < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.START_POSITION , java.lang.Integer.valueOf(begin));
		} 
		if (length < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.LENGTH , java.lang.Integer.valueOf(length));
		} 
		if ((begin + length) > (values.length)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END , java.lang.Integer.valueOf((begin + length)) , java.lang.Integer.valueOf(values.length) , true);
		} 
		if ((length == 0) && (!allowEmpty)) {
			return false;
		} 
		return true;
	}

	public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, weights, begin, length, false);
	}

	public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length, final boolean allowEmpty) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		if ((weights == null) || (values == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INPUT_ARRAY);
		} 
		if ((weights.length) != (values.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(weights.length , values.length);
		} 
		boolean containsPositiveWeight = false;
		for (int i = begin ; i < (begin + length) ; i++) {
			final double weight = weights[i];
			if (java.lang.Double.isNaN(weight)) {
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NAN_ELEMENT_AT_INDEX , java.lang.Integer.valueOf(i));
			} 
			if (java.lang.Double.isInfinite(weight)) {
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INFINITE_ARRAY_ELEMENT , java.lang.Double.valueOf(weight) , java.lang.Integer.valueOf(i));
			} 
			if (weight < 0) {
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX , java.lang.Integer.valueOf(i) , java.lang.Double.valueOf(weight));
			} 
			if ((!containsPositiveWeight) && (weight > 0.0)) {
				containsPositiveWeight = true;
			} 
		}
		if (!containsPositiveWeight) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO);
		} 
		return org.apache.commons.math3.util.MathArrays.verifyValues(values, begin, length, allowEmpty);
	}
}

