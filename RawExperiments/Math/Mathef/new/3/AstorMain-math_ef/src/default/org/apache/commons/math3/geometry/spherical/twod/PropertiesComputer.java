package org.apache.commons.math3.geometry.spherical.twod;


class PropertiesComputer implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> {
	private final double tolerance;

	private double summedArea;

	private org.apache.commons.math3.geometry.euclidean.threed.Vector3D summedBarycenter;

	private final java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> convexCellsInsidePoints;

	public PropertiesComputer(final double tolerance) {
		this.tolerance = tolerance;
		org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.this.summedArea = 0;
		org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.this.summedBarycenter = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.ZERO;
		this.convexCellsInsidePoints = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.threed.Vector3D>();
	}

	public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
		return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS;
	}

	public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
	}

	public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
		if (((java.lang.Boolean)(node.getAttribute()))) {
			final org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet convex = new org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet(node.pruneAroundConvexCell(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE, null) , tolerance);
			final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Vertex> boundary = convex.getBoundaryLoops();
			if ((boundary.size()) != 1) {
				throw new org.apache.commons.math3.exception.MathInternalError();
			} 
			final double area = convexCellArea(boundary.get(0));
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D barycenter = convexCellBarycenter(boundary.get(0));
			convexCellsInsidePoints.add(barycenter);
			summedArea += area;
			summedBarycenter = new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(1 , summedBarycenter , area , barycenter);
		} 
	}

	private double convexCellArea(final org.apache.commons.math3.geometry.spherical.twod.Vertex start) {
		int n = 0;
		double sum = 0;
		for (org.apache.commons.math3.geometry.spherical.twod.Edge e = start.getOutgoing() ; (n == 0) || ((e.getStart()) != start) ; e = e.getEnd().getOutgoing()) {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D previousPole = e.getCircle().getPole();
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D nextPole = e.getEnd().getOutgoing().getCircle().getPole();
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D point = e.getEnd().getLocation().getVector();
			double alpha = org.apache.commons.math3.util.FastMath.atan2(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.dotProduct(nextPole, org.apache.commons.math3.geometry.euclidean.threed.Vector3D.crossProduct(point, previousPole)), (-(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.dotProduct(nextPole, previousPole))));
			if (alpha < 0) {
				alpha += org.apache.commons.math3.util.MathUtils.TWO_PI;
			} 
			sum += alpha;
			n++;
		}
		return sum - ((n - 2) * (org.apache.commons.math3.util.FastMath.PI));
	}

	private org.apache.commons.math3.geometry.euclidean.threed.Vector3D convexCellBarycenter(final org.apache.commons.math3.geometry.spherical.twod.Vertex start) {
		int n = 0;
		org.apache.commons.math3.geometry.euclidean.threed.Vector3D sumB = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.ZERO;
		for (org.apache.commons.math3.geometry.spherical.twod.Edge e = start.getOutgoing() ; (n == 0) || ((e.getStart()) != start) ; e = e.getEnd().getOutgoing()) {
			sumB = new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(1 , sumB , e.getLength() , e.getCircle().getPole());
			n++;
		}
		return sumB.normalize();
	}

	public double getArea() {
		return summedArea;
	}

	public org.apache.commons.math3.geometry.spherical.twod.S2Point getBarycenter() {
		if ((summedBarycenter.getNormSq()) == 0) {
			return org.apache.commons.math3.geometry.spherical.twod.S2Point.NaN;
		} else {
			return new org.apache.commons.math3.geometry.spherical.twod.S2Point(summedBarycenter);
		}
	}

	public java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> getConvexCellsInsidePoints() {
		return convexCellsInsidePoints;
	}
}

