package org.apache.commons.math3.geometry.euclidean.threed;


public class FieldVector3D<T extends org.apache.commons.math3.RealFieldElement<T>> implements java.io.Serializable {
	private static final long serialVersionUID = 20130224L;

	private final T x;

	private final T y;

	private final T z;

	public FieldVector3D(final T x ,final T y ,final T z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public FieldVector3D(final T[] v) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if ((v.length) != 3) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(v.length , 3);
		} 
		this.x = v[0];
		this.y = v[1];
		this.z = v[2];
	}

	public FieldVector3D(final T alpha ,final T delta) {
		T cosDelta = delta.cos();
		this.x = alpha.cos().multiply(cosDelta);
		this.y = alpha.sin().multiply(cosDelta);
		this.z = delta.sin();
	}

	public FieldVector3D(final T a ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u) {
		this.x = a.multiply(u.x);
		this.y = a.multiply(u.y);
		this.z = a.multiply(u.z);
	}

	public FieldVector3D(final T a ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u) {
		this.x = a.multiply(u.getX());
		this.y = a.multiply(u.getY());
		this.z = a.multiply(u.getZ());
	}

	public FieldVector3D(final double a ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u) {
		this.x = u.x.multiply(a);
		this.y = u.y.multiply(a);
		this.z = u.z.multiply(a);
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2) {
		final T prototype = a1;
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u2) {
		final T prototype = a1;
		this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2);
		this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2);
		this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
	}

	public FieldVector3D(final double a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final double a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2) {
		final T prototype = u1.getX();
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2 ,final T a3 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u3) {
		final T prototype = a1;
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u2 ,final T a3 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u3) {
		final T prototype = a1;
		this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
		this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
		this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
	}

	public FieldVector3D(final double a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final double a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2 ,final double a3 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u3) {
		final T prototype = u1.getX();
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2 ,final T a3 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u3 ,final T a4 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u4) {
		final T prototype = a1;
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
	}

	public FieldVector3D(final T a1 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u1 ,final T a2 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u2 ,final T a3 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u3 ,final T a4 ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D u4) {
		final T prototype = a1;
		this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
		this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
		this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
	}

	public FieldVector3D(final double a1 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u1 ,final double a2 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u2 ,final double a3 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u3 ,final double a4 ,final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> u4) {
		final T prototype = u1.getX();
		this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
		this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
		this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
	}

	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}

	public T getZ() {
		return z;
	}

	public T[] toArray() {
		final T[] array = org.apache.commons.math3.util.MathArrays.buildArray(x.getField(), 3);
		array[0] = x;
		array[1] = y;
		array[2] = z;
		return array;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D toVector3D() {
		return new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(x.getReal() , y.getReal() , z.getReal());
	}

	public T getNorm1() {
		return x.abs().add(y.abs()).add(z.abs());
	}

	public T getNorm() {
		return x.multiply(x).add(y.multiply(y)).add(z.multiply(z)).sqrt();
	}

	public T getNormSq() {
		return x.multiply(x).add(y.multiply(y)).add(z.multiply(z));
	}

	public T getNormInf() {
		final T xAbs = x.abs();
		final T yAbs = y.abs();
		final T zAbs = z.abs();
		if ((xAbs.getReal()) <= (yAbs.getReal())) {
			if ((yAbs.getReal()) <= (zAbs.getReal())) {
				return zAbs;
			} else {
				return yAbs;
			}
		} else {
			if ((xAbs.getReal()) <= (zAbs.getReal())) {
				return zAbs;
			} else {
				return xAbs;
			}
		}
	}

	public T getAlpha() {
		return y.atan2(x);
	}

	public T getDelta() {
		return z.divide(getNorm()).asin();
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.add(v.x) , y.add(v.y) , z.add(v.z));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.add(v.getX()) , y.add(v.getY()) , z.add(v.getZ()));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final T factor, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.getField().getOne() , org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this , factor , v);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final T factor, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.add(factor.multiply(v.getX())) , y.add(factor.multiply(v.getY())) , z.add(factor.multiply(v.getZ())));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final double factor, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(1.0 , org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this , factor , v);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> add(final double factor, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.add((factor * (v.getX()))) , y.add((factor * (v.getY()))) , z.add((factor * (v.getZ()))));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.subtract(v.x) , y.subtract(v.y) , z.subtract(v.z));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.subtract(v.getX()) , y.subtract(v.getY()) , z.subtract(v.getZ()));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final T factor, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.getField().getOne() , org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this , factor.negate() , v);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final T factor, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.subtract(factor.multiply(v.getX())) , y.subtract(factor.multiply(v.getY())) , z.subtract(factor.multiply(v.getZ())));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final double factor, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(1.0 , org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this , (-factor) , v);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> subtract(final double factor, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.subtract((factor * (v.getX()))) , y.subtract((factor * (v.getY()))) , z.subtract((factor * (v.getZ()))));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> normalize() throws org.apache.commons.math3.exception.MathArithmeticException {
		final T s = getNorm();
		if ((s.getReal()) == 0) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
		} 
		return scalarMultiply(s.reciprocal());
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> orthogonal() throws org.apache.commons.math3.exception.MathArithmeticException {
		final double threshold = 0.6 * (getNorm().getReal());
		if (threshold == 0) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.ZERO_NORM);
		} 
		if ((org.apache.commons.math3.util.FastMath.abs(x.getReal())) <= threshold) {
			final T inverse = y.multiply(y).add(z.multiply(z)).sqrt().reciprocal();
			return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(inverse.getField().getZero() , inverse.multiply(z) , inverse.multiply(y).negate());
		} else if ((org.apache.commons.math3.util.FastMath.abs(y.getReal())) <= threshold) {
			final T inverse = x.multiply(x).add(z.multiply(z)).sqrt().reciprocal();
			return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(inverse.multiply(z).negate() , inverse.getField().getZero() , inverse.multiply(x));
		} else {
			final T inverse = x.multiply(x).add(y.multiply(y)).sqrt().reciprocal();
			return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(inverse.multiply(y) , inverse.multiply(x).negate() , inverse.getField().getZero());
		}
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T angle(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) throws org.apache.commons.math3.exception.MathArithmeticException {
		final T normProduct = v1.getNorm().multiply(v2.getNorm());
		if ((normProduct.getReal()) == 0) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.ZERO_NORM);
		} 
		final T dot = org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.dotProduct(v1, v2);
		final double threshold = (normProduct.getReal()) * 0.9999;
		if (((dot.getReal()) < (-threshold)) || ((dot.getReal()) > threshold)) {
			org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v3 = org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.crossProduct(v1, v2);
			if ((dot.getReal()) >= 0) {
				return v3.getNorm().divide(normProduct).asin();
			} 
			return v3.getNorm().divide(normProduct).asin().subtract(org.apache.commons.math3.util.FastMath.PI).negate();
		} 
		return dot.divide(normProduct).acos();
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T angle(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) throws org.apache.commons.math3.exception.MathArithmeticException {
		final T normProduct = v1.getNorm().multiply(v2.getNorm());
		if ((normProduct.getReal()) == 0) {
			throw new org.apache.commons.math3.exception.MathArithmeticException(org.apache.commons.math3.exception.util.LocalizedFormats.ZERO_NORM);
		} 
		final T dot = org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.dotProduct(v1, v2);
		final double threshold = (normProduct.getReal()) * 0.9999;
		if (((dot.getReal()) < (-threshold)) || ((dot.getReal()) > threshold)) {
			org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v3 = org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.crossProduct(v1, v2);
			if ((dot.getReal()) >= 0) {
				return v3.getNorm().divide(normProduct).asin();
			} 
			return v3.getNorm().divide(normProduct).asin().subtract(org.apache.commons.math3.util.FastMath.PI).negate();
		} 
		return dot.divide(normProduct).acos();
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T angle(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) throws org.apache.commons.math3.exception.MathArithmeticException {
		return org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle(v2, v1);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> negate() {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.negate() , y.negate() , z.negate());
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> scalarMultiply(final T a) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.multiply(a) , y.multiply(a) , z.multiply(a));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> scalarMultiply(final double a) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.multiply(a) , y.multiply(a) , z.multiply(a));
	}

	public boolean isNaN() {
		return ((java.lang.Double.isNaN(x.getReal())) || (java.lang.Double.isNaN(y.getReal()))) || (java.lang.Double.isNaN(z.getReal()));
	}

	public boolean isInfinite() {
		return (!(isNaN())) && (((java.lang.Double.isInfinite(x.getReal())) || (java.lang.Double.isInfinite(y.getReal()))) || (java.lang.Double.isInfinite(z.getReal())));
	}

	@java.lang.Override
	public boolean equals(java.lang.Object other) {
		if ((org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this) == other) {
			return true;
		} 
		if (other instanceof org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> rhs = ((org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>)(other));
			if (rhs.isNaN()) {
				return org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.this.isNaN();
			} 
			return ((x.equals(rhs.x)) && (y.equals(rhs.y))) && (z.equals(rhs.z));
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		if (isNaN()) {
			return 409;
		} 
		return 311 * (((107 * (x.hashCode())) + (83 * (y.hashCode()))) + (z.hashCode()));
	}

	public T dotProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return x.linearCombination(x, v.x, y, v.y, z, v.z);
	}

	public T dotProduct(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return x.linearCombination(v.getX(), x, v.getY(), y, v.getZ(), z);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> crossProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.linearCombination(y, v.z, z.negate(), v.y) , y.linearCombination(z, v.x, x.negate(), v.z) , z.linearCombination(x, v.y, y.negate(), v.x));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> crossProduct(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(x.linearCombination(v.getZ(), y, (-(v.getY())), z) , y.linearCombination(v.getX(), z, (-(v.getZ())), x) , z.linearCombination(v.getY(), x, (-(v.getX())), y));
	}

	public T distance1(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		final T dx = v.x.subtract(x).abs();
		final T dy = v.y.subtract(y).abs();
		final T dz = v.z.subtract(z).abs();
		return dx.add(dy).add(dz);
	}

	public T distance1(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		final T dx = x.subtract(v.getX()).abs();
		final T dy = y.subtract(v.getY()).abs();
		final T dz = z.subtract(v.getZ()).abs();
		return dx.add(dy).add(dz);
	}

	public T distance(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		final T dx = v.x.subtract(x);
		final T dy = v.y.subtract(y);
		final T dz = v.z.subtract(z);
		return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt();
	}

	public T distance(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		final T dx = x.subtract(v.getX());
		final T dy = y.subtract(v.getY());
		final T dz = z.subtract(v.getZ());
		return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt();
	}

	public T distanceInf(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		final T dx = v.x.subtract(x).abs();
		final T dy = v.y.subtract(y).abs();
		final T dz = v.z.subtract(z).abs();
		if ((dx.getReal()) <= (dy.getReal())) {
			if ((dy.getReal()) <= (dz.getReal())) {
				return dz;
			} else {
				return dy;
			}
		} else {
			if ((dx.getReal()) <= (dz.getReal())) {
				return dz;
			} else {
				return dx;
			}
		}
	}

	public T distanceInf(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		final T dx = x.subtract(v.getX()).abs();
		final T dy = y.subtract(v.getY()).abs();
		final T dz = z.subtract(v.getZ()).abs();
		if ((dx.getReal()) <= (dy.getReal())) {
			if ((dy.getReal()) <= (dz.getReal())) {
				return dz;
			} else {
				return dy;
			}
		} else {
			if ((dx.getReal()) <= (dz.getReal())) {
				return dz;
			} else {
				return dx;
			}
		}
	}

	public T distanceSq(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v) {
		final T dx = v.x.subtract(x);
		final T dy = v.y.subtract(y);
		final T dz = v.z.subtract(z);
		return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
	}

	public T distanceSq(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		final T dx = x.subtract(v.getX());
		final T dy = y.subtract(v.getY());
		final T dz = z.subtract(v.getZ());
		return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T dotProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.dotProduct(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T dotProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.dotProduct(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T dotProduct(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v2.dotProduct(v1);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> crossProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.crossProduct(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> crossProduct(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.crossProduct(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> crossProduct(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return new org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T>(v2.x.linearCombination(v1.getY(), v2.z, (-(v1.getZ())), v2.y) , v2.y.linearCombination(v1.getZ(), v2.x, (-(v1.getX())), v2.z) , v2.z.linearCombination(v1.getX(), v2.y, (-(v1.getY())), v2.x));
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance1(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.distance1(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance1(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.distance1(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance1(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v2.distance1(v1);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.distance(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.distance(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distance(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v2.distance(v1);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceInf(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.distanceInf(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceInf(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.distanceInf(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceInf(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v2.distanceInf(v1);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceSq(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v1.distanceSq(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceSq(final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v1, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v2) {
		return v1.distanceSq(v2);
	}

	public static <T extends org.apache.commons.math3.RealFieldElement<T>>T distanceSq(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1, final org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D<T> v2) {
		return v2.distanceSq(v1);
	}

	@java.lang.Override
	public java.lang.String toString() {
		return org.apache.commons.math3.geometry.euclidean.threed.Vector3DFormat.getInstance().format(toVector3D());
	}

	public java.lang.String toString(final java.text.NumberFormat format) {
		return new org.apache.commons.math3.geometry.euclidean.threed.Vector3DFormat(format).format(toVector3D());
	}
}

