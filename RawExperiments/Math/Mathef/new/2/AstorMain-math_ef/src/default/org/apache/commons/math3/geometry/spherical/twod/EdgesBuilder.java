package org.apache.commons.math3.geometry.spherical.twod;


class EdgesBuilder implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> {
	private final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> root;

	private final double tolerance;

	private final java.util.Map<org.apache.commons.math3.geometry.spherical.twod.Edge, org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>> edgeToNode;

	private final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>, java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge>> nodeToEdgesList;

	public EdgesBuilder(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> root ,final double tolerance) {
		this.root = root;
		this.tolerance = tolerance;
		this.edgeToNode = new java.util.IdentityHashMap<org.apache.commons.math3.geometry.spherical.twod.Edge, org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>>();
		this.nodeToEdgesList = new java.util.IdentityHashMap<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>, java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge>>();
	}

	public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
		return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS;
	}

	public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
		nodeToEdgesList.put(node, new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Edge>());
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>)(node.getAttribute()));
		if ((attribute.getPlusOutside()) != null) {
			addContribution(((org.apache.commons.math3.geometry.spherical.twod.SubCircle)(attribute.getPlusOutside())), false, node);
		} 
		if ((attribute.getPlusInside()) != null) {
			addContribution(((org.apache.commons.math3.geometry.spherical.twod.SubCircle)(attribute.getPlusInside())), true, node);
		} 
	}

	public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
	}

	private void addContribution(final org.apache.commons.math3.geometry.spherical.twod.SubCircle sub, final boolean reversed, final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node) {
		final org.apache.commons.math3.geometry.spherical.twod.Circle circle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(sub.getHyperplane()));
		final java.util.List<org.apache.commons.math3.geometry.spherical.oned.Arc> arcs = ((org.apache.commons.math3.geometry.spherical.oned.ArcsSet)(sub.getRemainingRegion())).asList();
		for (final org.apache.commons.math3.geometry.spherical.oned.Arc a : arcs) {
			final org.apache.commons.math3.geometry.spherical.twod.Vertex start = new org.apache.commons.math3.geometry.spherical.twod.Vertex(((org.apache.commons.math3.geometry.spherical.twod.S2Point)(circle.toSpace(new org.apache.commons.math3.geometry.spherical.oned.S1Point(a.getInf())))));
			final org.apache.commons.math3.geometry.spherical.twod.Vertex end = new org.apache.commons.math3.geometry.spherical.twod.Vertex(((org.apache.commons.math3.geometry.spherical.twod.S2Point)(circle.toSpace(new org.apache.commons.math3.geometry.spherical.oned.S1Point(a.getSup())))));
			start.bindWith(circle);
			end.bindWith(circle);
			final org.apache.commons.math3.geometry.spherical.twod.Edge edge;
			if (reversed) {
				edge = new org.apache.commons.math3.geometry.spherical.twod.Edge(end , start , a.getSize() , circle.getReverse());
			} else {
				edge = new org.apache.commons.math3.geometry.spherical.twod.Edge(start , end , a.getSize() , circle);
			}
			edgeToNode.put(edge, node);
			nodeToEdgesList.get(node).add(edge);
		}
	}

	private org.apache.commons.math3.geometry.spherical.twod.Edge getFollowingEdge(final org.apache.commons.math3.geometry.spherical.twod.Edge previous) throws org.apache.commons.math3.exception.MathIllegalStateException {
		final org.apache.commons.math3.geometry.spherical.twod.S2Point point = previous.getEnd().getLocation();
		final java.util.List<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>> candidates = root.getCloseCuts(point, tolerance);
		double closest = tolerance;
		org.apache.commons.math3.geometry.spherical.twod.Edge following = null;
		for (final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> node : candidates) {
			for (final org.apache.commons.math3.geometry.spherical.twod.Edge edge : nodeToEdgesList.get(node)) {
				if ((edge != previous) && ((edge.getStart().getIncoming()) == null)) {
					final org.apache.commons.math3.geometry.euclidean.threed.Vector3D edgeStart = edge.getStart().getLocation().getVector();
					final double gap = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(point.getVector(), edgeStart);
					if (gap <= closest) {
						closest = gap;
						following = edge;
					} 
				} 
			}
		}
		if (following == null) {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D previousStart = previous.getStart().getLocation().getVector();
			if ((org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(point.getVector(), previousStart)) <= (tolerance)) {
				return previous;
			} 
			throw new org.apache.commons.math3.exception.MathIllegalStateException(org.apache.commons.math3.exception.util.LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN);
		} 
		return following;
	}

	public java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> getEdges() throws org.apache.commons.math3.exception.MathIllegalStateException {
		for (final org.apache.commons.math3.geometry.spherical.twod.Edge previous : edgeToNode.keySet()) {
			previous.setNextEdge(getFollowingEdge(previous));
		}
		return new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Edge>(edgeToNode.keySet());
	}
}

