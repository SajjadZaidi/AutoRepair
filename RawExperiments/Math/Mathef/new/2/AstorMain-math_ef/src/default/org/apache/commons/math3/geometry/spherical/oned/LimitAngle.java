package org.apache.commons.math3.geometry.spherical.oned;


public class LimitAngle implements org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
	private org.apache.commons.math3.geometry.spherical.oned.S1Point location;

	private boolean direct;

	private final double tolerance;

	public LimitAngle(final org.apache.commons.math3.geometry.spherical.oned.S1Point location ,final boolean direct ,final double tolerance) {
		org.apache.commons.math3.geometry.spherical.oned.LimitAngle.this.location = location;
		org.apache.commons.math3.geometry.spherical.oned.LimitAngle.this.direct = direct;
		this.tolerance = tolerance;
	}

	public org.apache.commons.math3.geometry.spherical.oned.LimitAngle copySelf() {
		return org.apache.commons.math3.geometry.spherical.oned.LimitAngle.this;
	}

	public double getOffset(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> point) {
		final double delta = (((org.apache.commons.math3.geometry.spherical.oned.S1Point)(point)).getAlpha()) - (location.getAlpha());
		return direct ? delta : -delta;
	}

	public boolean isDirect() {
		return direct;
	}

	public org.apache.commons.math3.geometry.spherical.oned.LimitAngle getReverse() {
		return new org.apache.commons.math3.geometry.spherical.oned.LimitAngle(location , (!(direct)) , tolerance);
	}

	public org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle wholeHyperplane() {
		return new org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle(org.apache.commons.math3.geometry.spherical.oned.LimitAngle.this , null);
	}

	public org.apache.commons.math3.geometry.spherical.oned.ArcsSet wholeSpace() {
		return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet(tolerance);
	}

	public boolean sameOrientationAs(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> other) {
		return !((direct) ^ (((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(other)).direct));
	}

	public org.apache.commons.math3.geometry.spherical.oned.S1Point getLocation() {
		return location;
	}

	public org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> project(org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> point) {
		return location;
	}

	public double getTolerance() {
		return tolerance;
	}
}

