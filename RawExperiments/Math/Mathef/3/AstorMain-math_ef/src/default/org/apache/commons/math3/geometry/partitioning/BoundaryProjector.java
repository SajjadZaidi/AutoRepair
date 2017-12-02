package org.apache.commons.math3.geometry.partitioning;


class BoundaryProjector<S extends org.apache.commons.math3.geometry.Space, T extends org.apache.commons.math3.geometry.Space> implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<S> {
	private final org.apache.commons.math3.geometry.Point<S> original;

	private org.apache.commons.math3.geometry.Point<S> projected;

	private org.apache.commons.math3.geometry.partitioning.BSPTree<S> leaf;

	private double offset;

	public BoundaryProjector(final org.apache.commons.math3.geometry.Point<S> original) {
		this.original = original;
		org.apache.commons.math3.geometry.partitioning.BoundaryProjector.this.projected = null;
		org.apache.commons.math3.geometry.partitioning.BoundaryProjector.this.leaf = null;
		org.apache.commons.math3.geometry.partitioning.BoundaryProjector.this.offset = java.lang.Double.POSITIVE_INFINITY;
	}

	public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		if ((node.getCut().getHyperplane().getOffset(original)) <= 0) {
			return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS;
		} else {
			return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_SUB_MINUS;
		}
	}

	public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane = node.getCut().getHyperplane();
		final double signedOffset = hyperplane.getOffset(original);
		if ((org.apache.commons.math3.util.FastMath.abs(signedOffset)) < (offset)) {
			final org.apache.commons.math3.geometry.Point<S> regular = hyperplane.project(original);
			final java.util.List<org.apache.commons.math3.geometry.partitioning.Region<T>> boundaryParts = boundaryRegions(node);
			boolean regularFound = false;
			for (final org.apache.commons.math3.geometry.partitioning.Region<T> part : boundaryParts) {
				if ((!regularFound) && (belongsToPart(regular, hyperplane, part))) {
					projected = regular;
					offset = org.apache.commons.math3.util.FastMath.abs(signedOffset);
					regularFound = true;
				} 
			}
			if (!regularFound) {
				for (final org.apache.commons.math3.geometry.partitioning.Region<T> part : boundaryParts) {
					final org.apache.commons.math3.geometry.Point<S> spI = singularProjection(regular, hyperplane, part);
					if (spI != null) {
						final double distance = original.distance(spI);
						if (distance < (offset)) {
							projected = spI;
							offset = distance;
						} 
					} 
				}
			} 
		} 
	}

	public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		if ((leaf) == null) {
			leaf = node;
		} 
	}

	public org.apache.commons.math3.geometry.partitioning.BoundaryProjection<S> getProjection() {
		offset = org.apache.commons.math3.util.FastMath.copySign(offset, (((java.lang.Boolean)(leaf.getAttribute())) ? -1 : +1));
		return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<S>(original , projected , offset);
	}

	private java.util.List<org.apache.commons.math3.geometry.partitioning.Region<T>> boundaryRegions(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		final java.util.List<org.apache.commons.math3.geometry.partitioning.Region<T>> regions = new java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.Region<T>>(2);
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> ba = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(node.getAttribute()));
		addRegion(ba.getPlusInside(), regions);
		addRegion(ba.getPlusOutside(), regions);
		return regions;
	}

	private void addRegion(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub, final java.util.List<org.apache.commons.math3.geometry.partitioning.Region<T>> list) {
		if (sub != null) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.math3.geometry.partitioning.Region<T> region = ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T>)(sub)).getRemainingRegion();
			if (region != null) {
				list.add(region);
			} 
		} 
	}

	private boolean belongsToPart(final org.apache.commons.math3.geometry.Point<S> point, final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane, final org.apache.commons.math3.geometry.partitioning.Region<T> part) {
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.Embedding<S, T> embedding = ((org.apache.commons.math3.geometry.partitioning.Embedding<S, T>)(hyperplane));
		return (part.checkPoint(embedding.toSubSpace(point))) != (org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE);
	}

	private org.apache.commons.math3.geometry.Point<S> singularProjection(final org.apache.commons.math3.geometry.Point<S> point, final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane, final org.apache.commons.math3.geometry.partitioning.Region<T> part) {
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.Embedding<S, T> embedding = ((org.apache.commons.math3.geometry.partitioning.Embedding<S, T>)(hyperplane));
		final org.apache.commons.math3.geometry.partitioning.BoundaryProjection<T> bp = part.projectToBoundary(embedding.toSubSpace(point));
		return (bp.getProjected()) == null ? null : embedding.toSpace(bp.getProjected());
	}
}

