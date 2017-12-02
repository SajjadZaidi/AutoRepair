package org.apache.commons.math3.geometry.euclidean.twod.hull;


abstract class AbstractConvexHullGenerator2D implements org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHullGenerator2D {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	private final double tolerance;

	private final boolean includeCollinearPoints;

	protected AbstractConvexHullGenerator2D(final boolean includeCollinearPoints) {
		this(includeCollinearPoints, org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D.DEFAULT_TOLERANCE);
	}

	protected AbstractConvexHullGenerator2D(final boolean includeCollinearPoints ,final double tolerance) {
		this.includeCollinearPoints = includeCollinearPoints;
		this.tolerance = tolerance;
	}

	public double getTolerance() {
		return tolerance;
	}

	public boolean isIncludeCollinearPoints() {
		return includeCollinearPoints;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D generate(final java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(points);
		java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> hullVertices = null;
		if ((points.size()) < 2) {
			hullVertices = points;
		} else {
			hullVertices = findHullVertices(points);
		}
		try {
			return new org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D(hullVertices.toArray(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[hullVertices.size()]) , tolerance);
		} catch (org.apache.commons.math3.exception.MathIllegalArgumentException e) {
			throw new org.apache.commons.math3.exception.ConvergenceException();
		}
	}

	protected abstract java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> findHullVertices(java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> points);
}

