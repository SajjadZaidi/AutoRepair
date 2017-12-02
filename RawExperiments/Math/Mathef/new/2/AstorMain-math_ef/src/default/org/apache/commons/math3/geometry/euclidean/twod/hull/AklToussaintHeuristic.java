package org.apache.commons.math3.geometry.euclidean.twod.hull;


public final class AklToussaintHeuristic {
	private AklToussaintHeuristic() {
	}

	public static java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> reducePoints(final java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> points) {
		int size = 0;
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D minX = null;
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D maxX = null;
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D minY = null;
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D maxY = null;
		for (org.apache.commons.math3.geometry.euclidean.twod.Vector2D p : points) {
			if ((minX == null) || ((p.getX()) < (minX.getX()))) {
				minX = p;
			} 
			if ((maxX == null) || ((p.getX()) > (maxX.getX()))) {
				maxX = p;
			} 
			if ((minY == null) || ((p.getY()) < (minY.getY()))) {
				minY = p;
			} 
			if ((maxY == null) || ((p.getY()) > (maxY.getY()))) {
				maxY = p;
			} 
			size++;
		}
		if (size < 4) {
			return points;
		} 
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> quadrilateral = org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.buildQuadrilateral(minY, maxX, maxY, minX);
		if ((quadrilateral.size()) < 3) {
			return points;
		} 
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> reducedPoints = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(quadrilateral);
		for (final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p : points) {
			if (!(org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral(p, quadrilateral))) {
				reducedPoints.add(p);
			} 
		}
		return reducedPoints;
	}

	private static java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> buildQuadrilateral(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D... points) {
		java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> quadrilateral = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Vector2D>();
		for (org.apache.commons.math3.geometry.euclidean.twod.Vector2D p : points) {
			if (!(quadrilateral.contains(p))) {
				quadrilateral.add(p);
			} 
		}
		return quadrilateral;
	}

	private static boolean insideQuadrilateral(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D point, final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> quadrilateralPoints) {
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 = quadrilateralPoints.get(0);
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = quadrilateralPoints.get(1);
		if ((point.equals(p1)) || (point.equals(p2))) {
			return true;
		} 
		final double last = point.crossProduct(p1, p2);
		final int size = quadrilateralPoints.size();
		for (int i = 1 ; i < size ; i++) {
			p1 = p2;
			p2 = quadrilateralPoints.get(((i + 1) == size ? 0 : i + 1));
			if ((point.equals(p1)) || (point.equals(p2))) {
				return true;
			} 
			if ((last * (point.crossProduct(p1, p2))) < 0) {
				return false;
			} 
		}
		return true;
	}
}

