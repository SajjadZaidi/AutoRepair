package org.apache.commons.math3.geometry.spherical.twod;


public class SphericalPolygonsSet extends org.apache.commons.math3.geometry.partitioning.AbstractRegion<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
	private java.util.List<org.apache.commons.math3.geometry.spherical.twod.Vertex> loops;

	public SphericalPolygonsSet(final double tolerance) {
		super(tolerance);
	}

	public SphericalPolygonsSet(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D pole ,final double tolerance) {
		super(new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(new org.apache.commons.math3.geometry.spherical.twod.Circle(pole , tolerance).wholeHyperplane() , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(java.lang.Boolean.TRUE) , null), tolerance);
	}

	public SphericalPolygonsSet(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D center ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D meridian ,final double outsideRadius ,final int n ,final double tolerance) {
		this(tolerance, org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices(center, meridian, outsideRadius, n));
	}

	public SphericalPolygonsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> tree ,final double tolerance) {
		super(tree, tolerance);
	}

	public SphericalPolygonsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>> boundary ,final double tolerance) {
		super(boundary, tolerance);
	}

	public SphericalPolygonsSet(final double hyperplaneThickness ,final org.apache.commons.math3.geometry.spherical.twod.S2Point... vertices) {
		super(org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
	}

	private static org.apache.commons.math3.geometry.spherical.twod.S2Point[] createRegularPolygonVertices(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D center, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D meridian, final double outsideRadius, final int n) {
		final org.apache.commons.math3.geometry.spherical.twod.S2Point[] array = new org.apache.commons.math3.geometry.spherical.twod.S2Point[n];
		final org.apache.commons.math3.geometry.euclidean.threed.Rotation r0 = new org.apache.commons.math3.geometry.euclidean.threed.Rotation(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.crossProduct(center, meridian) , outsideRadius);
		array[0] = new org.apache.commons.math3.geometry.spherical.twod.S2Point(r0.applyTo(center));
		final org.apache.commons.math3.geometry.euclidean.threed.Rotation r = new org.apache.commons.math3.geometry.euclidean.threed.Rotation(center , ((org.apache.commons.math3.util.MathUtils.TWO_PI) / n));
		for (int i = 1 ; i < n ; ++i) {
			array[i] = new org.apache.commons.math3.geometry.spherical.twod.S2Point(r.applyTo(array[(i - 1)].getVector()));
		}
		return array;
	}

	private static org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> verticesToTree(final double hyperplaneThickness, final org.apache.commons.math3.geometry.spherical.twod.S2Point... vertices) {
		final int n = vertices.length;
		if (n == 0) {
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(java.lang.Boolean.TRUE);
		} 
		final org.apache.commons.math3.geometry.spherical.twod.Vertex[] vArray = new org.apache.commons.math3.geometry.spherical.twod.Vertex[n];
		for (int i = 0 ; i < n ; ++i) {
			vArray[i] = new org.apache.commons.math3.geometry.spherical.twod.Vertex(vertices[i]);
		}
		java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> edges = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Edge>(n);
		org.apache.commons.math3.geometry.spherical.twod.Vertex end = vArray[(n - 1)];
		for (int i = 0 ; i < n ; ++i) {
			final org.apache.commons.math3.geometry.spherical.twod.Vertex start = end;
			end = vArray[i];
			org.apache.commons.math3.geometry.spherical.twod.Circle circle = start.sharedCircleWith(end);
			if (circle == null) {
				circle = new org.apache.commons.math3.geometry.spherical.twod.Circle(start.getLocation() , end.getLocation() , hyperplaneThickness);
			} 
			edges.add(new org.apache.commons.math3.geometry.spherical.twod.Edge(start , end , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(start.getLocation().getVector(), end.getLocation().getVector()) , circle));
			for (final org.apache.commons.math3.geometry.spherical.twod.Vertex vertex : vArray) {
				if (((vertex != start) && (vertex != end)) && ((org.apache.commons.math3.util.FastMath.abs(circle.getOffset(vertex.getLocation()))) <= hyperplaneThickness)) {
					vertex.bindWith(circle);
				} 
			}
		}
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>();
		org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges(hyperplaneThickness, tree, edges);
		return tree;
	}

	private static void insertEdges(final double hyperplaneThickness, final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node, final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> edges) {
		int index = 0;
		org.apache.commons.math3.geometry.spherical.twod.Edge inserted = null;
		while ((inserted == null) && (index < (edges.size()))) {
			inserted = edges.get((index++));
			if (!(node.insertCut(inserted.getCircle()))) {
				inserted = null;
			} 
		}
		if (inserted == null) {
			final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> parent = node.getParent();
			if ((parent == null) || (node == (parent.getMinus()))) {
				node.setAttribute(java.lang.Boolean.TRUE);
			} else {
				node.setAttribute(java.lang.Boolean.FALSE);
			}
			return ;
		} 
		final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> outsideList = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Edge>();
		final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> insideList = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Edge>();
		for (final org.apache.commons.math3.geometry.spherical.twod.Edge edge : edges) {
			if (edge != inserted) {
				edge.split(inserted.getCircle(), outsideList, insideList);
			} 
		}
		if (!(outsideList.isEmpty())) {
			org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges(hyperplaneThickness, node.getPlus(), outsideList);
		} else {
			node.getPlus().setAttribute(java.lang.Boolean.FALSE);
		}
		if (!(insideList.isEmpty())) {
			org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges(hyperplaneThickness, node.getMinus(), insideList);
		} else {
			node.getMinus().setAttribute(java.lang.Boolean.TRUE);
		}
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet buildNew(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> tree) {
		return new org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet(tree , getTolerance());
	}

	@java.lang.Override
	protected void computeGeometricalProperties() throws org.apache.commons.math3.exception.MathIllegalStateException {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> tree = getTree(true);
		if ((tree.getCut()) == null) {
			if (((tree.getCut()) == null) && ((java.lang.Boolean)(tree.getAttribute()))) {
				setSize((4 * (org.apache.commons.math3.util.FastMath.PI)));
				setBarycenter(new org.apache.commons.math3.geometry.spherical.twod.S2Point(0 , 0));
			} else {
				setSize(0);
				setBarycenter(org.apache.commons.math3.geometry.spherical.twod.S2Point.NaN);
			}
		} else {
			final org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer pc = new org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer(getTolerance());
			tree.visit(pc);
			setSize(pc.getArea());
			setBarycenter(pc.getBarycenter());
		}
	}

	public java.util.List<org.apache.commons.math3.geometry.spherical.twod.Vertex> getBoundaryLoops() throws org.apache.commons.math3.exception.MathIllegalStateException {
		if ((loops) == null) {
			if ((getTree(false).getCut()) == null) {
				loops = java.util.Collections.emptyList();
			} else {
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> root = getTree(true);
				final org.apache.commons.math3.geometry.spherical.twod.EdgesBuilder visitor = new org.apache.commons.math3.geometry.spherical.twod.EdgesBuilder(root , getTolerance());
				root.visit(visitor);
				final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> edges = visitor.getEdges();
				loops = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Vertex>();
				while (!(edges.isEmpty())) {
					org.apache.commons.math3.geometry.spherical.twod.Edge edge = edges.get(0);
					final org.apache.commons.math3.geometry.spherical.twod.Vertex startVertex = edge.getStart();
					loops.add(startVertex);
					do {
						for (final java.util.Iterator<org.apache.commons.math3.geometry.spherical.twod.Edge> iterator = edges.iterator() ; iterator.hasNext() ; ) {
							if ((iterator.next()) == edge) {
								iterator.remove();
								break;
							} 
						}
						edge = edge.getEnd().getOutgoing();
					} while ((edge.getStart()) != startVertex );
				}
			}
		} 
		return java.util.Collections.unmodifiableList(loops);
	}

	public org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point> getEnclosingCap() {
		if (isEmpty()) {
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(org.apache.commons.math3.geometry.spherical.twod.S2Point.PLUS_K , java.lang.Double.NEGATIVE_INFINITY);
		} 
		if (isFull()) {
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(org.apache.commons.math3.geometry.spherical.twod.S2Point.PLUS_K , java.lang.Double.POSITIVE_INFINITY);
		} 
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> root = getTree(false);
		if ((isEmpty(root.getMinus())) && (isFull(root.getPlus()))) {
			final org.apache.commons.math3.geometry.spherical.twod.Circle circle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(root.getCut().getHyperplane()));
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(new org.apache.commons.math3.geometry.spherical.twod.S2Point(circle.getPole()).negate() , (0.5 * (org.apache.commons.math3.util.FastMath.PI)));
		} 
		if ((isFull(root.getMinus())) && (isEmpty(root.getPlus()))) {
			final org.apache.commons.math3.geometry.spherical.twod.Circle circle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(root.getCut().getHyperplane()));
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(new org.apache.commons.math3.geometry.spherical.twod.S2Point(circle.getPole()) , (0.5 * (org.apache.commons.math3.util.FastMath.PI)));
		} 
		final java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> points = getInsidePoints();
		final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Vertex> boundary = getBoundaryLoops();
		for (final org.apache.commons.math3.geometry.spherical.twod.Vertex loopStart : boundary) {
			int count = 0;
			for (org.apache.commons.math3.geometry.spherical.twod.Vertex v = loopStart ; (count == 0) || (v != loopStart) ; v = v.getOutgoing().getEnd()) {
				++count;
				points.add(v.getLocation().getVector());
			}
		}
		final org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator generator = new org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator();
		final org.apache.commons.math3.geometry.enclosing.WelzlEncloser<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D> encloser = new org.apache.commons.math3.geometry.enclosing.WelzlEncloser<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(getTolerance() , generator);
		org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D> enclosing3D = encloser.enclose(points);
		final org.apache.commons.math3.geometry.euclidean.threed.Vector3D[] support3D = enclosing3D.getSupport();
		final double r = enclosing3D.getRadius();
		final double h = enclosing3D.getCenter().getNorm();
		if (h < (getTolerance())) {
			org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point> enclosingS2 = new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(org.apache.commons.math3.geometry.spherical.twod.S2Point.PLUS_K , java.lang.Double.POSITIVE_INFINITY);
			for (org.apache.commons.math3.geometry.euclidean.threed.Vector3D outsidePoint : getOutsidePoints()) {
				final org.apache.commons.math3.geometry.spherical.twod.S2Point outsideS2 = new org.apache.commons.math3.geometry.spherical.twod.S2Point(outsidePoint);
				final org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> projection = projectToBoundary(outsideS2);
				if (((org.apache.commons.math3.util.FastMath.PI) - (projection.getOffset())) < (enclosingS2.getRadius())) {
					enclosingS2 = new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(outsideS2.negate() , ((org.apache.commons.math3.util.FastMath.PI) - (projection.getOffset())) , ((org.apache.commons.math3.geometry.spherical.twod.S2Point)(projection.getProjected())));
				} 
			}
			return enclosingS2;
		} 
		final org.apache.commons.math3.geometry.spherical.twod.S2Point[] support = new org.apache.commons.math3.geometry.spherical.twod.S2Point[support3D.length];
		for (int i = 0 ; i < (support3D.length) ; ++i) {
			support[i] = new org.apache.commons.math3.geometry.spherical.twod.S2Point(support3D[i]);
		}
		final org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point> enclosingS2 = new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.twod.S2Point>(new org.apache.commons.math3.geometry.spherical.twod.S2Point(enclosing3D.getCenter()) , org.apache.commons.math3.util.FastMath.acos((((1 + (h * h)) - (r * r)) / (2 * h))) , support);
		return enclosingS2;
	}

	private java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> getInsidePoints() {
		final org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer pc = new org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer(getTolerance());
		getTree(true).visit(pc);
		return pc.getConvexCellsInsidePoints();
	}

	private java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> getOutsidePoints() {
		final org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet complement = ((org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet)(new org.apache.commons.math3.geometry.partitioning.RegionFactory<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>().getComplement(org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.this)));
		final org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer pc = new org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer(getTolerance());
		complement.getTree(true).visit(pc);
		return pc.getConvexCellsInsidePoints();
	}
}

