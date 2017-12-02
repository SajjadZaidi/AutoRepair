package org.apache.commons.math3.geometry.spherical.oned;


public class S1Point implements org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
	public static final org.apache.commons.math3.geometry.spherical.oned.S1Point NaN = new org.apache.commons.math3.geometry.spherical.oned.S1Point(java.lang.Double.NaN , org.apache.commons.math3.geometry.euclidean.twod.Vector2D.NaN);

	private static final long serialVersionUID = 20131218L;

	private final double alpha;

	private final org.apache.commons.math3.geometry.euclidean.twod.Vector2D vector;

	public S1Point(final double alpha) {
		this(org.apache.commons.math3.util.MathUtils.normalizeAngle(alpha, org.apache.commons.math3.util.FastMath.PI), new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(org.apache.commons.math3.util.FastMath.cos(alpha) , org.apache.commons.math3.util.FastMath.sin(alpha)));
	}

	private S1Point(final double alpha ,final org.apache.commons.math3.geometry.euclidean.twod.Vector2D vector) {
		this.alpha = alpha;
		this.vector = vector;
	}

	public double getAlpha() {
		return alpha;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D getVector() {
		return vector;
	}

	public org.apache.commons.math3.geometry.Space getSpace() {
		return org.apache.commons.math3.geometry.spherical.oned.Sphere1D.getInstance();
	}

	public boolean isNaN() {
		return java.lang.Double.isNaN(alpha);
	}

	public double distance(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> point) {
		return org.apache.commons.math3.geometry.spherical.oned.S1Point.distance(org.apache.commons.math3.geometry.spherical.oned.S1Point.this, ((org.apache.commons.math3.geometry.spherical.oned.S1Point)(point)));
	}

	public static double distance(org.apache.commons.math3.geometry.spherical.oned.S1Point p1, org.apache.commons.math3.geometry.spherical.oned.S1Point p2) {
		return org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle(p1.vector, p2.vector);
	}

	@java.lang.Override
	public boolean equals(java.lang.Object other) {
		if ((org.apache.commons.math3.geometry.spherical.oned.S1Point.this) == other) {
			return true;
		} 
		if (other instanceof org.apache.commons.math3.geometry.spherical.oned.S1Point) {
			final org.apache.commons.math3.geometry.spherical.oned.S1Point rhs = ((org.apache.commons.math3.geometry.spherical.oned.S1Point)(other));
			if (rhs.isNaN()) {
				return org.apache.commons.math3.geometry.spherical.oned.S1Point.this.isNaN();
			} 
			return (alpha) == (rhs.alpha);
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		if (isNaN()) {
			return 542;
		} 
		return 1759 * (org.apache.commons.math3.util.MathUtils.hash(alpha));
	}
}

