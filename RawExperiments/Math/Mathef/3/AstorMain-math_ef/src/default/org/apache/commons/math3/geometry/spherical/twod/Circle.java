package org.apache.commons.math3.geometry.spherical.twod;


public class Circle implements org.apache.commons.math3.geometry.partitioning.Embedding<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> , org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> {
	private org.apache.commons.math3.geometry.euclidean.threed.Vector3D pole;

	private org.apache.commons.math3.geometry.euclidean.threed.Vector3D x;

	private org.apache.commons.math3.geometry.euclidean.threed.Vector3D y;

	private final double tolerance;

	public Circle(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D pole ,final double tolerance) {
		reset(pole);
		this.tolerance = tolerance;
	}

	public Circle(final org.apache.commons.math3.geometry.spherical.twod.S2Point first ,final org.apache.commons.math3.geometry.spherical.twod.S2Point second ,final double tolerance) {
		reset(first.getVector().crossProduct(second.getVector()));
		this.tolerance = tolerance;
	}

	private Circle(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D pole ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D x ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D y ,final double tolerance) {
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.pole = pole;
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.x = x;
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.y = y;
		this.tolerance = tolerance;
	}

	public Circle(final org.apache.commons.math3.geometry.spherical.twod.Circle circle) {
		this(circle.pole, circle.x, circle.y, circle.tolerance);
	}

	public org.apache.commons.math3.geometry.spherical.twod.Circle copySelf() {
		return new org.apache.commons.math3.geometry.spherical.twod.Circle(org.apache.commons.math3.geometry.spherical.twod.Circle.this);
	}

	public void reset(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D newPole) {
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.pole = newPole.normalize();
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.x = newPole.orthogonal();
		org.apache.commons.math3.geometry.spherical.twod.Circle.this.y = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.crossProduct(newPole, x).normalize();
	}

	public void revertSelf() {
		y = y.negate();
		pole = pole.negate();
	}

	public org.apache.commons.math3.geometry.spherical.twod.Circle getReverse() {
		return new org.apache.commons.math3.geometry.spherical.twod.Circle(pole.negate() , x , y.negate() , tolerance);
	}

	public org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> project(org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> point) {
		return toSpace(toSubSpace(point));
	}

	public double getTolerance() {
		return tolerance;
	}

	public org.apache.commons.math3.geometry.spherical.oned.S1Point toSubSpace(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> point) {
		return new org.apache.commons.math3.geometry.spherical.oned.S1Point(getPhase(((org.apache.commons.math3.geometry.spherical.twod.S2Point)(point)).getVector()));
	}

	public double getPhase(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D direction) {
		return (org.apache.commons.math3.util.FastMath.PI) + (org.apache.commons.math3.util.FastMath.atan2((-(direction.dotProduct(y))), (-(direction.dotProduct(x)))));
	}

	public org.apache.commons.math3.geometry.spherical.twod.S2Point toSpace(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> point) {
		return new org.apache.commons.math3.geometry.spherical.twod.S2Point(getPointAt(((org.apache.commons.math3.geometry.spherical.oned.S1Point)(point)).getAlpha()));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getPointAt(final double alpha) {
		return new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(org.apache.commons.math3.util.FastMath.cos(alpha) , x , org.apache.commons.math3.util.FastMath.sin(alpha) , y);
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getXAxis() {
		return x;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getYAxis() {
		return y;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getPole() {
		return pole;
	}

	public org.apache.commons.math3.geometry.spherical.oned.Arc getInsideArc(final org.apache.commons.math3.geometry.spherical.twod.Circle other) {
		final double alpha = getPhase(other.pole);
		final double halfPi = 0.5 * (org.apache.commons.math3.util.FastMath.PI);
		return new org.apache.commons.math3.geometry.spherical.oned.Arc((alpha - halfPi) , (alpha + halfPi) , tolerance);
	}

	public org.apache.commons.math3.geometry.spherical.twod.SubCircle wholeHyperplane() {
		return new org.apache.commons.math3.geometry.spherical.twod.SubCircle(org.apache.commons.math3.geometry.spherical.twod.Circle.this , new org.apache.commons.math3.geometry.spherical.oned.ArcsSet(tolerance));
	}

	public org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet wholeSpace() {
		return new org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet(tolerance);
	}

	public double getOffset(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> point) {
		return getOffset(((org.apache.commons.math3.geometry.spherical.twod.S2Point)(point)).getVector());
	}

	public double getOffset(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D direction) {
		return (org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(pole, direction)) - (0.5 * (org.apache.commons.math3.util.FastMath.PI));
	}

	public boolean sameOrientationAs(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> other) {
		final org.apache.commons.math3.geometry.spherical.twod.Circle otherC = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(other));
		return (org.apache.commons.math3.geometry.euclidean.threed.Vector3D.dotProduct(pole, otherC.pole)) >= 0.0;
	}

	public static org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> getTransform(final org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation) {
		return new org.apache.commons.math3.geometry.spherical.twod.Circle.CircleTransform(rotation);
	}

	private static class CircleTransform implements org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
		private final org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation;

		public CircleTransform(final org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation) {
			this.rotation = rotation;
		}

		public org.apache.commons.math3.geometry.spherical.twod.S2Point apply(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> point) {
			return new org.apache.commons.math3.geometry.spherical.twod.S2Point(rotation.applyTo(((org.apache.commons.math3.geometry.spherical.twod.S2Point)(point)).getVector()));
		}

		public org.apache.commons.math3.geometry.spherical.twod.Circle apply(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> hyperplane) {
			final org.apache.commons.math3.geometry.spherical.twod.Circle circle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(hyperplane));
			return new org.apache.commons.math3.geometry.spherical.twod.Circle(rotation.applyTo(circle.pole) , rotation.applyTo(circle.x) , rotation.applyTo(circle.y) , circle.tolerance);
		}

		public org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> apply(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> sub, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> original, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> transformed) {
			return sub;
		}
	}
}

