package org.apache.commons.math3.geometry.euclidean.twod.hull;


public class ConvexHull2D implements java.io.Serializable , org.apache.commons.math3.geometry.hull.ConvexHull<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> {
	private static final long serialVersionUID = 20140129L;

	private final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] vertices;

	private final double tolerance;

	private transient org.apache.commons.math3.geometry.euclidean.twod.Segment[] lineSegments;

	public ConvexHull2D(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] vertices ,final double tolerance) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		this.tolerance = tolerance;
		if (!(isConvex(vertices))) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NOT_CONVEX);
		} 
		this.vertices = vertices.clone();
	}

	private boolean isConvex(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] hullVertices) {
		if ((hullVertices.length) < 3) {
			return true;
		} 
		int sign = 0;
		for (int i = 0 ; i < (hullVertices.length) ; i++) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 = hullVertices[(i == 0 ? (hullVertices.length) - 1 : i - 1)];
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = hullVertices[i];
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p3 = hullVertices[(i == ((hullVertices.length) - 1) ? 0 : i + 1)];
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D d1 = p2.subtract(p1);
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D d2 = p3.subtract(p2);
			final double crossProduct = org.apache.commons.math3.util.MathArrays.linearCombination(d1.getX(), d2.getY(), (-(d1.getY())), d2.getX());
			final int cmp = org.apache.commons.math3.util.Precision.compareTo(crossProduct, 0.0, tolerance);
			if (cmp != 0.0) {
				if ((sign != 0.0) && (cmp != sign)) {
					return false;
				} 
				sign = cmp;
			} 
		}
		return true;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] getVertices() {
		return vertices.clone();
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Segment[] getLineSegments() {
		return retrieveLineSegments().clone();
	}

	private org.apache.commons.math3.geometry.euclidean.twod.Segment[] retrieveLineSegments() {
		if ((lineSegments) == null) {
			final int size = vertices.length;
			if (size <= 1) {
				org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments = new org.apache.commons.math3.geometry.euclidean.twod.Segment[0];
			} else if (size == 2) {
				org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments = new org.apache.commons.math3.geometry.euclidean.twod.Segment[1];
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 = vertices[0];
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = vertices[1];
				org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments[0] = new org.apache.commons.math3.geometry.euclidean.twod.Segment(p1 , p2 , new org.apache.commons.math3.geometry.euclidean.twod.Line(p1 , p2 , tolerance));
			} else {
				org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments = new org.apache.commons.math3.geometry.euclidean.twod.Segment[size];
				org.apache.commons.math3.geometry.euclidean.twod.Vector2D firstPoint = null;
				org.apache.commons.math3.geometry.euclidean.twod.Vector2D lastPoint = null;
				int index = 0;
				for (org.apache.commons.math3.geometry.euclidean.twod.Vector2D point : vertices) {
					if (lastPoint == null) {
						firstPoint = point;
						lastPoint = point;
					} else {
						org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments[(index++)] = new org.apache.commons.math3.geometry.euclidean.twod.Segment(lastPoint , point , new org.apache.commons.math3.geometry.euclidean.twod.Line(lastPoint , point , tolerance));
						lastPoint = point;
					}
				}
				org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.this.lineSegments[index] = new org.apache.commons.math3.geometry.euclidean.twod.Segment(lastPoint , firstPoint , new org.apache.commons.math3.geometry.euclidean.twod.Line(lastPoint , firstPoint , tolerance));
			}
		} 
		return lineSegments;
	}

	public org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> createRegion() throws org.apache.commons.math3.exception.InsufficientDataException {
		if ((vertices.length) < 3) {
			throw new org.apache.commons.math3.exception.InsufficientDataException();
		} 
		final org.apache.commons.math3.geometry.partitioning.RegionFactory<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> factory = new org.apache.commons.math3.geometry.partitioning.RegionFactory<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>();
		final org.apache.commons.math3.geometry.euclidean.twod.Segment[] segments = retrieveLineSegments();
		final org.apache.commons.math3.geometry.euclidean.twod.Line[] lineArray = new org.apache.commons.math3.geometry.euclidean.twod.Line[segments.length];
		for (int i = 0 ; i < (segments.length) ; i++) {
			lineArray[i] = segments[i].getLine();
		}
		return factory.buildConvex(lineArray);
	}
}

