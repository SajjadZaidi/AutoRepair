package org.apache.commons.math3.analysis.interpolation;


public class FieldHermiteInterpolator<T extends org.apache.commons.math3.FieldElement<T>> {
	private final java.util.List<T> abscissae;

	private final java.util.List<T[]> topDiagonal;

	private final java.util.List<T[]> bottomDiagonal;

	public FieldHermiteInterpolator() {
		this.abscissae = new java.util.ArrayList<T>();
		this.topDiagonal = new java.util.ArrayList<T[]>();
		this.bottomDiagonal = new java.util.ArrayList<T[]>();
	}

	public void addSamplePoint(final T x, final T[]... value) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MathArithmeticException, org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.ZeroException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(x);
		T factorial = x.getField().getOne();
		for (int i = 0 ; i < (value.length) ; ++i) {
			final T[] y = value[i].clone();
			if (i > 1) {
				factorial = factorial.multiply(i);
				final T inv = factorial.reciprocal();
				for (int j = 0 ; j < (y.length) ; ++j) {
					y[j] = y[j].multiply(inv);
				}
			} 
			final int n = abscissae.size();
			bottomDiagonal.add((n - i), y);
			T[] bottom0 = y;
			for (int j = i ; j < n ; ++j) {
				final T[] bottom1 = bottomDiagonal.get((n - (j + 1)));
				if (x.equals(abscissae.get((n - (j + 1))))) {
					throw new org.apache.commons.math3.exception.ZeroException(org.apache.commons.math3.exception.util.LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO , x);
				} 
				final T inv = x.subtract(abscissae.get((n - (j + 1)))).reciprocal();
				for (int k = 0 ; k < (y.length) ; ++k) {
					bottom1[k] = inv.multiply(bottom0[k].subtract(bottom1[k]));
				}
				bottom0 = bottom1;
			}
			topDiagonal.add(bottom0.clone());
			abscissae.add(x);
		}
	}

	public T[] value(T x) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(x);
		if (abscissae.isEmpty()) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
		} 
		final T[] value = org.apache.commons.math3.util.MathArrays.buildArray(x.getField(), topDiagonal.get(0).length);
		T valueCoeff = x.getField().getOne();
		for (int i = 0 ; i < (topDiagonal.size()) ; ++i) {
			T[] dividedDifference = topDiagonal.get(i);
			for (int k = 0 ; k < (value.length) ; ++k) {
				value[k] = value[k].add(dividedDifference[k].multiply(valueCoeff));
			}
			final T deltaX = x.subtract(abscissae.get(i));
			valueCoeff = valueCoeff.multiply(deltaX);
		}
		return value;
	}

	public T[][] derivatives(T x, int order) throws org.apache.commons.math3.exception.NoDataException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(x);
		if (abscissae.isEmpty()) {
			throw new org.apache.commons.math3.exception.NoDataException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
		} 
		final T zero = x.getField().getZero();
		final T one = x.getField().getOne();
		final T[] tj = org.apache.commons.math3.util.MathArrays.buildArray(x.getField(), (order + 1));
		tj[0] = zero;
		for (int i = 0 ; i < order ; ++i) {
			tj[(i + 1)] = tj[i].add(one);
		}
		final T[][] derivatives = org.apache.commons.math3.util.MathArrays.buildArray(x.getField(), (order + 1), topDiagonal.get(0).length);
		final T[] valueCoeff = org.apache.commons.math3.util.MathArrays.buildArray(x.getField(), (order + 1));
		valueCoeff[0] = x.getField().getOne();
		for (int i = 0 ; i < (topDiagonal.size()) ; ++i) {
			T[] dividedDifference = topDiagonal.get(i);
			final T deltaX = x.subtract(abscissae.get(i));
			for (int j = order ; j >= 0 ; --j) {
				for (int k = 0 ; k < (derivatives[j].length) ; ++k) {
					derivatives[j][k] = derivatives[j][k].add(dividedDifference[k].multiply(valueCoeff[j]));
				}
				valueCoeff[j] = valueCoeff[j].multiply(deltaX);
				if (j > 0) {
					valueCoeff[j] = valueCoeff[j].add(tj[j].multiply(valueCoeff[(j - 1)]));
				} 
			}
		}
		return derivatives;
	}
}

