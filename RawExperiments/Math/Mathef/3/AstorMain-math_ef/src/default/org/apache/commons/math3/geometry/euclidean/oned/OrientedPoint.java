package org.apache.commons.math3.geometry.euclidean.oned;


public class OrientedPoint implements org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	private org.apache.commons.math3.geometry.euclidean.oned.Vector1D location;

	private boolean direct;

	private final double tolerance;

	public OrientedPoint(final org.apache.commons.math3.geometry.euclidean.oned.Vector1D location ,final boolean direct ,final double tolerance) {
		org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint.this.location = location;
		org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint.this.direct = direct;
		this.tolerance = tolerance;
	}

	@java.lang.Deprecated
	public OrientedPoint(final org.apache.commons.math3.geometry.euclidean.oned.Vector1D location ,final boolean direct) {
		this(location, direct, org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint.DEFAULT_TOLERANCE);
	}

	public org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint copySelf() {
		return org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint.this;
	}

	public double getOffset(org.apache.commons.math3.geometry.Vector<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> vector) {
		return getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(vector)));
	}

	public double getOffset(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> point) {
		final double delta = (((org.apache.commons.math3.geometry.euclidean.oned.Vector1D)(point)).getX()) - (location.getX());
		return direct ? delta : -delta;
	}

	public org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint wholeHyperplane() {
		return new org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint(org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint.this , null);
	}

	public org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet wholeSpace() {
		return new org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet(tolerance);
	}

	public boolean sameOrientationAs(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> other) {
		return !((direct) ^ (((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(other)).direct));
	}

	public org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> project(org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> point) {
		return location;
	}

	public double getTolerance() {
		return tolerance;
	}

	public org.apache.commons.math3.geometry.euclidean.oned.Vector1D getLocation() {
		return location;
	}

	public boolean isDirect() {
		return direct;
	}

	public void revertSelf() {
		direct = !(direct);
	}
}

