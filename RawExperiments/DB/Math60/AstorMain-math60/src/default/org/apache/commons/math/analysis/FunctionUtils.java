package org.apache.commons.math.analysis;


public class FunctionUtils {
	private FunctionUtils() {
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction compose(final org.apache.commons.math.analysis.UnivariateRealFunction... f) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				double r = x;
				for (int i = (f.length) - 1 ; i >= 0 ; i--) {
					r = f[i].value(r);
				}
				return r;
			}
		};
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction add(final org.apache.commons.math.analysis.UnivariateRealFunction... f) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				double r = f[0].value(x);
				for (int i = 1 ; i < (f.length) ; i++) {
					r += f[i].value(x);
				}
				return r;
			}
		};
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction multiply(final org.apache.commons.math.analysis.UnivariateRealFunction... f) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				double r = f[0].value(x);
				for (int i = 1 ; i < (f.length) ; i++) {
					r *= f[i].value(x);
				}
				return r;
			}
		};
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction combine(final org.apache.commons.math.analysis.BivariateRealFunction combiner, final org.apache.commons.math.analysis.UnivariateRealFunction f, final org.apache.commons.math.analysis.UnivariateRealFunction g) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				return combiner.value(f.value(x), g.value(x));
			}
		};
	}

	public static org.apache.commons.math.analysis.MultivariateRealFunction collector(final org.apache.commons.math.analysis.BivariateRealFunction combiner, final org.apache.commons.math.analysis.UnivariateRealFunction f, final double initialValue) {
		return new org.apache.commons.math.analysis.MultivariateRealFunction() {
			public double value(double[] point) {
				double result = combiner.value(initialValue, f.value(point[0]));
				for (int i = 1 ; i < (point.length) ; i++) {
					result = combiner.value(result, f.value(point[i]));
				}
				return result;
			}
		};
	}

	public static org.apache.commons.math.analysis.MultivariateRealFunction collector(final org.apache.commons.math.analysis.BivariateRealFunction combiner, final double initialValue) {
		return org.apache.commons.math.analysis.FunctionUtils.collector(combiner, new org.apache.commons.math.analysis.function.Identity(), initialValue);
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction fix1stArgument(final org.apache.commons.math.analysis.BivariateRealFunction f, final double fixed) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				return f.value(fixed, x);
			}
		};
	}

	public static org.apache.commons.math.analysis.UnivariateRealFunction fix2ndArgument(final org.apache.commons.math.analysis.BivariateRealFunction f, final double fixed) {
		return new org.apache.commons.math.analysis.UnivariateRealFunction() {
			public double value(double x) {
				return f.value(x, fixed);
			}
		};
	}
}

