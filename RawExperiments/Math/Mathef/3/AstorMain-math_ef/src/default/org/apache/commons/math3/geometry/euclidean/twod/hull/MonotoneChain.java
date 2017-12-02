package org.apache.commons.math3.geometry.euclidean.twod.hull;


public class MonotoneChain extends org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D {
	public MonotoneChain() {
		this(false);
	}

	public MonotoneChain(final boolean includeCollinearPoints) {
		super(includeCollinearPoints);
	}

	public MonotoneChain(final boolean includeCollinearPoints ,final double tolerance) {
		super(includeCollinearPoints, tolerance);
	}

	@java.lang.Override
	public java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> findHullVertices(final java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> points) {
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> pointsSortedByXAxis = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(points);
		java.util.Collections.sort(pointsSortedByXAxis, new java.util.Comparator<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>() {
			public int compare(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D o1, final org.apache.commons.math3.geometry.euclidean.twod.Vector2D o2) {
				final double tolerance = getTolerance();
				final int diff = org.apache.commons.math3.util.Precision.compareTo(o1.getX(), o2.getX(), tolerance);
				if (diff == 0) {
					return org.apache.commons.math3.util.Precision.compareTo(o1.getY(), o2.getY(), tolerance);
				} else {
					return diff;
				}
			}
		});
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> lowerHull = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>();
		for (org.apache.commons.math3.geometry.euclidean.twod.Vector2D p : pointsSortedByXAxis) {
			updateHull(p, lowerHull);
		}
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> upperHull = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>();
		for (int idx = (pointsSortedByXAxis.size()) - 1 ; idx >= 0 ; idx--) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p = pointsSortedByXAxis.get(idx);
			updateHull(p, upperHull);
		}
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> hullVertices = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>((((lowerHull.size()) + (upperHull.size())) - 2));
		for (int idx = 0 ; idx < ((lowerHull.size()) - 1) ; idx++) {
			hullVertices.add(lowerHull.get(idx));
		}
		for (int idx = 0 ; idx < ((upperHull.size()) - 1) ; idx++) {
			hullVertices.add(upperHull.get(idx));
		}
		if ((hullVertices.isEmpty()) && (!(lowerHull.isEmpty()))) {
			hullVertices.add(lowerHull.get(0));
		} 
		return hullVertices;
	}

	private void updateHull(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D point, final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> hull) {
		final double tolerance = getTolerance();
		if ((hull.size()) == 1) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 = hull.get(0);
			if ((p1.distance(point)) < tolerance) {
				return ;
			} 
		} 
		while ((hull.size()) >= 2) {
			final int size = hull.size();
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 = hull.get((size - 2));
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = hull.get((size - 1));
			final double offset = new org.apache.commons.math3.geometry.euclidean.twod.Line(p1 , p2 , tolerance).getOffset(point);
			if ((org.apache.commons.math3.util.FastMath.abs(offset)) < tolerance) {
				final double distanceToCurrent = p1.distance(point);
				if ((distanceToCurrent < tolerance) || ((p2.distance(point)) < tolerance)) {
					return ;
				} 
				final double distanceToLast = p1.distance(p2);
				if (isIncludeCollinearPoints()) {
					final int index = distanceToCurrent < distanceToLast ? size - 1 : size;
					hull.add(index, point);
				} else {
					if (distanceToCurrent > distanceToLast) {
						hull.remove((size - 1));
						hull.add(point);
					} 
				}
				return ;
			} else if (offset > 0) {
				hull.remove((size - 1));
			} else {
				break;
			}
		}
		hull.add(point);
	}
}

