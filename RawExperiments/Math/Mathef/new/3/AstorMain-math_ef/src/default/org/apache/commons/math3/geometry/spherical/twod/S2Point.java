package org.apache.commons.math3.geometry.spherical.twod;


public class S2Point implements org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> {
	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point PLUS_I = new org.apache.commons.math3.geometry.spherical.twod.S2Point(0 , (0.5 * (org.apache.commons.math3.util.FastMath.PI)) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_I);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point PLUS_J = new org.apache.commons.math3.geometry.spherical.twod.S2Point((0.5 * (org.apache.commons.math3.util.FastMath.PI)) , (0.5 * (org.apache.commons.math3.util.FastMath.PI)) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_J);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point PLUS_K = new org.apache.commons.math3.geometry.spherical.twod.S2Point(0 , 0 , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_K);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point MINUS_I = new org.apache.commons.math3.geometry.spherical.twod.S2Point(org.apache.commons.math3.util.FastMath.PI , (0.5 * (org.apache.commons.math3.util.FastMath.PI)) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_I);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point MINUS_J = new org.apache.commons.math3.geometry.spherical.twod.S2Point((1.5 * (org.apache.commons.math3.util.FastMath.PI)) , (0.5 * (org.apache.commons.math3.util.FastMath.PI)) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_J);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point MINUS_K = new org.apache.commons.math3.geometry.spherical.twod.S2Point(0 , org.apache.commons.math3.util.FastMath.PI , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_K);

	public static final org.apache.commons.math3.geometry.spherical.twod.S2Point NaN = new org.apache.commons.math3.geometry.spherical.twod.S2Point(java.lang.Double.NaN , java.lang.Double.NaN , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.NaN);

	private static final long serialVersionUID = 20131218L;

	private final double theta;

	private final double phi;

	private final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vector;

	public S2Point(final double theta ,final double phi) throws org.apache.commons.math3.exception.OutOfRangeException {
		this(theta, phi, org.apache.commons.math3.geometry.spherical.twod.S2Point.vector(theta, phi));
	}

	public S2Point(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vector) throws org.apache.commons.math3.exception.MathArithmeticException {
		this(org.apache.commons.math3.util.FastMath.atan2(vector.getY(), vector.getX()), org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_K, vector), vector.normalize());
	}

	private S2Point(final double theta ,final double phi ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vector) {
		this.theta = theta;
		this.phi = phi;
		this.vector = vector;
	}

	private static org.apache.commons.math3.geometry.euclidean.threed.Vector3D vector(final double theta, final double phi) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((phi < 0) || (phi > (org.apache.commons.math3.util.FastMath.PI))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(phi , 0 , org.apache.commons.math3.util.FastMath.PI);
		} 
		final double cosTheta = org.apache.commons.math3.util.FastMath.cos(theta);
		final double sinTheta = org.apache.commons.math3.util.FastMath.sin(theta);
		final double cosPhi = org.apache.commons.math3.util.FastMath.cos(phi);
		final double sinPhi = org.apache.commons.math3.util.FastMath.sin(phi);
		return new org.apache.commons.math3.geometry.euclidean.threed.Vector3D((cosTheta * sinPhi) , (sinTheta * sinPhi) , cosPhi);
	}

	public double getTheta() {
		return theta;
	}

	public double getPhi() {
		return phi;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getVector() {
		return vector;
	}

	public org.apache.commons.math3.geometry.Space getSpace() {
		return org.apache.commons.math3.geometry.spherical.twod.Sphere2D.getInstance();
	}

	public boolean isNaN() {
		return (java.lang.Double.isNaN(theta)) || (java.lang.Double.isNaN(phi));
	}

	public org.apache.commons.math3.geometry.spherical.twod.S2Point negate() {
		return new org.apache.commons.math3.geometry.spherical.twod.S2Point((-(theta)) , ((org.apache.commons.math3.util.FastMath.PI) - (phi)) , vector.negate());
	}

	public double distance(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> point) {
		return org.apache.commons.math3.geometry.spherical.twod.S2Point.distance(org.apache.commons.math3.geometry.spherical.twod.S2Point.this, ((org.apache.commons.math3.geometry.spherical.twod.S2Point)(point)));
	}

	public static double distance(org.apache.commons.math3.geometry.spherical.twod.S2Point p1, org.apache.commons.math3.geometry.spherical.twod.S2Point p2) {
		return org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(p1.vector, p2.vector);
	}

	@java.lang.Override
	public boolean equals(java.lang.Object other) {
		if ((org.apache.commons.math3.geometry.spherical.twod.S2Point.this) == other) {
			return true;
		} 
		if (other instanceof org.apache.commons.math3.geometry.spherical.twod.S2Point) {
			final org.apache.commons.math3.geometry.spherical.twod.S2Point rhs = ((org.apache.commons.math3.geometry.spherical.twod.S2Point)(other));
			if (rhs.isNaN()) {
				return org.apache.commons.math3.geometry.spherical.twod.S2Point.this.isNaN();
			} 
			return ((theta) == (rhs.theta)) && ((phi) == (rhs.phi));
		} 
		return false;
	}

	@java.lang.Override
	public int hashCode() {
		if (isNaN()) {
			return 542;
		} 
		return 134 * ((37 * (org.apache.commons.math3.util.MathUtils.hash(theta))) + (org.apache.commons.math3.util.MathUtils.hash(phi)));
	}
}

