package org.apache.commons.math3.analysis.differentiation;


public class SparseGradient implements java.io.Serializable , org.apache.commons.math3.RealFieldElement<org.apache.commons.math3.analysis.differentiation.SparseGradient> {
	private static final long serialVersionUID = 20131025L;

	private double value;

	private final java.util.Map<java.lang.Integer, java.lang.Double> derivatives;

	private SparseGradient(final double value ,final java.util.Map<java.lang.Integer, java.lang.Double> derivatives) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient.this.value = value;
		this.derivatives = new java.util.HashMap<java.lang.Integer, java.lang.Double>();
		if (derivatives != null) {
			org.apache.commons.math3.analysis.differentiation.SparseGradient.this.derivatives.putAll(derivatives);
		} 
	}

	private SparseGradient(final double value ,final double scale ,final java.util.Map<java.lang.Integer, java.lang.Double> derivatives) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient.this.value = value;
		this.derivatives = new java.util.HashMap<java.lang.Integer, java.lang.Double>();
		if (derivatives != null) {
			for (final java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
				org.apache.commons.math3.analysis.differentiation.SparseGradient.this.derivatives.put(entry.getKey(), (scale * (entry.getValue())));
			}
		} 
	}

	public static org.apache.commons.math3.analysis.differentiation.SparseGradient createConstant(final double value) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(value , java.util.Collections.<java.lang.Integer, java.lang.Double>emptyMap());
	}

	public static org.apache.commons.math3.analysis.differentiation.SparseGradient createVariable(final int idx, final double value) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(value , java.util.Collections.singletonMap(idx, 1.0));
	}

	public int numVars() {
		return derivatives.size();
	}

	public double getDerivative(final int index) {
		final java.lang.Double out = derivatives.get(index);
		return out == null ? 0.0 : out;
	}

	public double getValue() {
		return value;
	}

	public double getReal() {
		return value;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient add(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) + (a.value)) , derivatives);
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = out.derivatives.get(id);
			if (old == null) {
				out.derivatives.put(id, entry.getValue());
			} else {
				out.derivatives.put(id, (old + (entry.getValue())));
			}
		}
		return out;
	}

	public void addInPlace(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		value += a.value;
		for (final java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = derivatives.get(id);
			if (old == null) {
				derivatives.put(id, entry.getValue());
			} else {
				derivatives.put(id, (old + (entry.getValue())));
			}
		}
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient add(final double c) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) + c) , derivatives);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient subtract(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) - (a.value)) , derivatives);
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = out.derivatives.get(id);
			if (old == null) {
				out.derivatives.put(id, (-(entry.getValue())));
			} else {
				out.derivatives.put(id, (old - (entry.getValue())));
			}
		}
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient subtract(double c) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) - c) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient multiply(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) * (a.value)) , java.util.Collections.<java.lang.Integer, java.lang.Double>emptyMap());
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
			out.derivatives.put(entry.getKey(), ((a.value) * (entry.getValue())));
		}
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = out.derivatives.get(id);
			if (old == null) {
				out.derivatives.put(id, ((value) * (entry.getValue())));
			} else {
				out.derivatives.put(id, (old + ((value) * (entry.getValue()))));
			}
		}
		return out;
	}

	public void multiplyInPlace(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
			derivatives.put(entry.getKey(), ((a.value) * (entry.getValue())));
		}
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = derivatives.get(id);
			if (old == null) {
				derivatives.put(id, ((value) * (entry.getValue())));
			} else {
				derivatives.put(id, (old + ((value) * (entry.getValue()))));
			}
		}
		value *= a.value;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient multiply(final double c) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) * c) , c , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient multiply(final int n) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) * n) , n , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient divide(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) / (a.value)) , java.util.Collections.<java.lang.Integer, java.lang.Double>emptyMap());
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
			out.derivatives.put(entry.getKey(), ((entry.getValue()) / (a.value)));
		}
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : a.derivatives.entrySet()) {
			final int id = entry.getKey();
			final java.lang.Double old = out.derivatives.get(id);
			if (old == null) {
				out.derivatives.put(id, (((-(out.value)) / (a.value)) * (entry.getValue())));
			} else {
				out.derivatives.put(id, (old - (((out.value) / (a.value)) * (entry.getValue()))));
			}
		}
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient divide(final double c) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) / c) , (1.0 / c) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient negate() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient((-(value)) , (-1.0) , derivatives);
	}

	public org.apache.commons.math3.Field<org.apache.commons.math3.analysis.differentiation.SparseGradient> getField() {
		return new org.apache.commons.math3.Field<org.apache.commons.math3.analysis.differentiation.SparseGradient>() {
			public org.apache.commons.math3.analysis.differentiation.SparseGradient getZero() {
				return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(0);
			}

			public org.apache.commons.math3.analysis.differentiation.SparseGradient getOne() {
				return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(1);
			}

			public java.lang.Class<? extends org.apache.commons.math3.FieldElement<org.apache.commons.math3.analysis.differentiation.SparseGradient>> getRuntimeClass() {
				return org.apache.commons.math3.analysis.differentiation.SparseGradient.class;
			}
		};
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient remainder(final double a) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.IEEEremainder(value, a) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient remainder(final org.apache.commons.math3.analysis.differentiation.SparseGradient a) {
		final double rem = org.apache.commons.math3.util.FastMath.IEEEremainder(value, a.value);
		final double k = org.apache.commons.math3.util.FastMath.rint((((value) - rem) / (a.value)));
		return subtract(a.multiply(k));
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient abs() {
		if ((java.lang.Double.doubleToLongBits(value)) < 0) {
			return negate();
		} else {
			return org.apache.commons.math3.analysis.differentiation.SparseGradient.this;
		}
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient ceil() {
		return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(org.apache.commons.math3.util.FastMath.ceil(value));
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient floor() {
		return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(org.apache.commons.math3.util.FastMath.floor(value));
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient rint() {
		return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(org.apache.commons.math3.util.FastMath.rint(value));
	}

	public long round() {
		return org.apache.commons.math3.util.FastMath.round(value);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient signum() {
		return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(org.apache.commons.math3.util.FastMath.signum(value));
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient copySign(final org.apache.commons.math3.analysis.differentiation.SparseGradient sign) {
		final long m = java.lang.Double.doubleToLongBits(value);
		final long s = java.lang.Double.doubleToLongBits(sign.value);
		if (((m >= 0) && (s >= 0)) || ((m < 0) && (s < 0))) {
			return org.apache.commons.math3.analysis.differentiation.SparseGradient.this;
		} 
		return negate();
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient copySign(final double sign) {
		final long m = java.lang.Double.doubleToLongBits(value);
		final long s = java.lang.Double.doubleToLongBits(sign);
		if (((m >= 0) && (s >= 0)) || ((m < 0) && (s < 0))) {
			return org.apache.commons.math3.analysis.differentiation.SparseGradient.this;
		} 
		return negate();
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient scalb(final int n) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient out = new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.scalb(value, n) , java.util.Collections.<java.lang.Integer, java.lang.Double>emptyMap());
		for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
			out.derivatives.put(entry.getKey(), org.apache.commons.math3.util.FastMath.scalb(entry.getValue(), n));
		}
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient hypot(final org.apache.commons.math3.analysis.differentiation.SparseGradient y) {
		if ((java.lang.Double.isInfinite(value)) || (java.lang.Double.isInfinite(y.value))) {
			return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(java.lang.Double.POSITIVE_INFINITY);
		} else if ((java.lang.Double.isNaN(value)) || (java.lang.Double.isNaN(y.value))) {
			return org.apache.commons.math3.analysis.differentiation.SparseGradient.createConstant(java.lang.Double.NaN);
		} else {
			final int expX = org.apache.commons.math3.util.FastMath.getExponent(value);
			final int expY = org.apache.commons.math3.util.FastMath.getExponent(y.value);
			if (expX > (expY + 27)) {
				return abs();
			} else if (expY > (expX + 27)) {
				return y.abs();
			} else {
				final int middleExp = (expX + expY) / 2;
				final org.apache.commons.math3.analysis.differentiation.SparseGradient scaledX = scalb((-middleExp));
				final org.apache.commons.math3.analysis.differentiation.SparseGradient scaledY = y.scalb((-middleExp));
				final org.apache.commons.math3.analysis.differentiation.SparseGradient scaledH = scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).sqrt();
				return scaledH.scalb(middleExp);
			}
		}
	}

	public static org.apache.commons.math3.analysis.differentiation.SparseGradient hypot(final org.apache.commons.math3.analysis.differentiation.SparseGradient x, final org.apache.commons.math3.analysis.differentiation.SparseGradient y) {
		return x.hypot(y);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient reciprocal() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient((1.0 / (value)) , ((-1.0) / ((value) * (value))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient sqrt() {
		final double sqrt = org.apache.commons.math3.util.FastMath.sqrt(value);
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(sqrt , (0.5 / sqrt) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient cbrt() {
		final double cbrt = org.apache.commons.math3.util.FastMath.cbrt(value);
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(cbrt , (1.0 / ((3 * cbrt) * cbrt)) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient rootN(final int n) {
		if (n == 2) {
			return sqrt();
		} else if (n == 3) {
			return cbrt();
		} else {
			final double root = org.apache.commons.math3.util.FastMath.pow(value, (1.0 / n));
			return new org.apache.commons.math3.analysis.differentiation.SparseGradient(root , (1.0 / (n * (org.apache.commons.math3.util.FastMath.pow(root, (n - 1))))) , derivatives);
		}
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient pow(final double p) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.pow(value, p) , (p * (org.apache.commons.math3.util.FastMath.pow(value, (p - 1)))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient pow(final int n) {
		if (n == 0) {
			return getField().getOne();
		} else {
			final double valueNm1 = org.apache.commons.math3.util.FastMath.pow(value, (n - 1));
			return new org.apache.commons.math3.analysis.differentiation.SparseGradient(((value) * valueNm1) , (n * valueNm1) , derivatives);
		}
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient pow(final org.apache.commons.math3.analysis.differentiation.SparseGradient e) {
		return log().multiply(e).exp();
	}

	public static org.apache.commons.math3.analysis.differentiation.SparseGradient pow(final double a, final org.apache.commons.math3.analysis.differentiation.SparseGradient x) {
		if (a == 0) {
			if ((x.value) == 0) {
				return x.compose(1.0, java.lang.Double.NEGATIVE_INFINITY);
			} else if ((x.value) < 0) {
				return x.compose(java.lang.Double.NaN, java.lang.Double.NaN);
			} else {
				return x.getField().getZero();
			}
		} else {
			final double ax = org.apache.commons.math3.util.FastMath.pow(a, x.value);
			return new org.apache.commons.math3.analysis.differentiation.SparseGradient(ax , (ax * (org.apache.commons.math3.util.FastMath.log(a))) , x.derivatives);
		}
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient exp() {
		final double e = org.apache.commons.math3.util.FastMath.exp(value);
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(e , e , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient expm1() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.expm1(value) , org.apache.commons.math3.util.FastMath.exp(value) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient log() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.log(value) , (1.0 / (value)) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient log10() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.log10(value) , (1.0 / ((org.apache.commons.math3.util.FastMath.log(10.0)) * (value))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient log1p() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.log1p(value) , (1.0 / (1.0 + (value))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient cos() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.cos(value) , (-(org.apache.commons.math3.util.FastMath.sin(value))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient sin() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.sin(value) , org.apache.commons.math3.util.FastMath.cos(value) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient tan() {
		final double t = org.apache.commons.math3.util.FastMath.tan(value);
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(t , (1 + (t * t)) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient acos() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.acos(value) , ((-1.0) / (org.apache.commons.math3.util.FastMath.sqrt((1 - ((value) * (value)))))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient asin() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.asin(value) , (1.0 / (org.apache.commons.math3.util.FastMath.sqrt((1 - ((value) * (value)))))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient atan() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.atan(value) , (1.0 / (1 + ((value) * (value)))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient atan2(final org.apache.commons.math3.analysis.differentiation.SparseGradient x) {
		final org.apache.commons.math3.analysis.differentiation.SparseGradient r = multiply(org.apache.commons.math3.analysis.differentiation.SparseGradient.this).add(x.multiply(x)).sqrt();
		final org.apache.commons.math3.analysis.differentiation.SparseGradient a;
		if ((x.value) >= 0) {
			a = divide(r.add(x)).atan().multiply(2);
		} else {
			final org.apache.commons.math3.analysis.differentiation.SparseGradient tmp = divide(r.subtract(x)).atan().multiply((-2));
			a = tmp.add(((tmp.value) <= 0 ? -(org.apache.commons.math3.util.FastMath.PI) : org.apache.commons.math3.util.FastMath.PI));
		}
		a.value = org.apache.commons.math3.util.FastMath.atan2(value, x.value);
		return a;
	}

	public static org.apache.commons.math3.analysis.differentiation.SparseGradient atan2(final org.apache.commons.math3.analysis.differentiation.SparseGradient y, final org.apache.commons.math3.analysis.differentiation.SparseGradient x) {
		return y.atan2(x);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient cosh() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.cosh(value) , org.apache.commons.math3.util.FastMath.sinh(value) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient sinh() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.sinh(value) , org.apache.commons.math3.util.FastMath.cosh(value) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient tanh() {
		final double t = org.apache.commons.math3.util.FastMath.tanh(value);
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(t , (1 - (t * t)) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient acosh() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.acosh(value) , (1.0 / (org.apache.commons.math3.util.FastMath.sqrt((((value) * (value)) - 1.0)))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient asinh() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.asinh(value) , (1.0 / (org.apache.commons.math3.util.FastMath.sqrt((((value) * (value)) + 1.0)))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient atanh() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.atanh(value) , (1.0 / (1.0 - ((value) * (value)))) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient toDegrees() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.toDegrees(value) , org.apache.commons.math3.util.FastMath.toDegrees(1.0) , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient toRadians() {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(org.apache.commons.math3.util.FastMath.toRadians(value) , org.apache.commons.math3.util.FastMath.toRadians(1.0) , derivatives);
	}

	public double taylor(final double... delta) {
		double y = value;
		for (int i = 0 ; i < (delta.length) ; ++i) {
			y += (delta[i]) * (getDerivative(i));
		}
		return y;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient compose(final double f0, final double f1) {
		return new org.apache.commons.math3.analysis.differentiation.SparseGradient(f0 , f1 , derivatives);
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final org.apache.commons.math3.analysis.differentiation.SparseGradient[] a, final org.apache.commons.math3.analysis.differentiation.SparseGradient[] b) throws org.apache.commons.math3.exception.DimensionMismatchException {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = a[0].getField().getZero();
		for (int i = 0 ; i < (a.length) ; ++i) {
			out = out.add(a[i].multiply(b[i]));
		}
		final double[] aDouble = new double[a.length];
		for (int i = 0 ; i < (a.length) ; ++i) {
			aDouble[i] = a[i].getValue();
		}
		final double[] bDouble = new double[b.length];
		for (int i = 0 ; i < (b.length) ; ++i) {
			bDouble[i] = b[i].getValue();
		}
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(aDouble, bDouble);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final double[] a, final org.apache.commons.math3.analysis.differentiation.SparseGradient[] b) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = b[0].getField().getZero();
		for (int i = 0 ; i < (a.length) ; ++i) {
			out = out.add(b[i].multiply(a[i]));
		}
		final double[] bDouble = new double[b.length];
		for (int i = 0 ; i < (b.length) ; ++i) {
			bDouble[i] = b[i].getValue();
		}
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a, bDouble);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final org.apache.commons.math3.analysis.differentiation.SparseGradient a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final org.apache.commons.math3.analysis.differentiation.SparseGradient a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = a1.multiply(b1).add(a2.multiply(b2));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final double a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final double a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = b1.multiply(a1).add(b2.multiply(a2));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final org.apache.commons.math3.analysis.differentiation.SparseGradient a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final org.apache.commons.math3.analysis.differentiation.SparseGradient a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2, final org.apache.commons.math3.analysis.differentiation.SparseGradient a3, final org.apache.commons.math3.analysis.differentiation.SparseGradient b3) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final double a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final double a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2, final double a3, final org.apache.commons.math3.analysis.differentiation.SparseGradient b3) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final org.apache.commons.math3.analysis.differentiation.SparseGradient a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final org.apache.commons.math3.analysis.differentiation.SparseGradient a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2, final org.apache.commons.math3.analysis.differentiation.SparseGradient a3, final org.apache.commons.math3.analysis.differentiation.SparseGradient b3, final org.apache.commons.math3.analysis.differentiation.SparseGradient a4, final org.apache.commons.math3.analysis.differentiation.SparseGradient b4) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value);
		return out;
	}

	public org.apache.commons.math3.analysis.differentiation.SparseGradient linearCombination(final double a1, final org.apache.commons.math3.analysis.differentiation.SparseGradient b1, final double a2, final org.apache.commons.math3.analysis.differentiation.SparseGradient b2, final double a3, final org.apache.commons.math3.analysis.differentiation.SparseGradient b3, final double a4, final org.apache.commons.math3.analysis.differentiation.SparseGradient b4) {
		org.apache.commons.math3.analysis.differentiation.SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
		out.value = org.apache.commons.math3.util.MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value);
		return out;
	}

	@java.lang.Override
	public boolean equals(java.lang.Object other) {
		if ((org.apache.commons.math3.analysis.differentiation.SparseGradient.this) == other) {
			return true;
		} 
		if (other instanceof org.apache.commons.math3.analysis.differentiation.SparseGradient) {
			final org.apache.commons.math3.analysis.differentiation.SparseGradient rhs = ((org.apache.commons.math3.analysis.differentiation.SparseGradient)(other));
			if (!(org.apache.commons.math3.util.Precision.equals(value, rhs.value, 1))) {
				return false;
			} 
			if ((derivatives.size()) != (rhs.derivatives.size())) {
				return false;
			} 
			for (final java.util.Map.Entry<java.lang.Integer, java.lang.Double> entry : derivatives.entrySet()) {
				if (!(rhs.derivatives.containsKey(entry.getKey()))) {
					return false;
				} 
				if (!(org.apache.commons.math3.util.Precision.equals(entry.getValue(), rhs.derivatives.get(entry.getKey()), 1))) {
					return false;
				} 
			}
			return true;
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		return (743 + (809 * (org.apache.commons.math3.util.MathUtils.hash(value)))) + (167 * (derivatives.hashCode()));
	}
}

