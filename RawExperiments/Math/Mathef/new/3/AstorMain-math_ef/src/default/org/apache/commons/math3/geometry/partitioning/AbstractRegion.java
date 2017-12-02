package org.apache.commons.math3.geometry.partitioning;


public abstract class AbstractRegion<S extends org.apache.commons.math3.geometry.Space, T extends org.apache.commons.math3.geometry.Space> implements org.apache.commons.math3.geometry.partitioning.Region<S> {
	private org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree;

	private final double tolerance;

	private double size;

	private org.apache.commons.math3.geometry.Point<S> barycenter;

	protected AbstractRegion(final double tolerance) {
		org.apache.commons.math3.geometry.partitioning.AbstractRegion.this.tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(java.lang.Boolean.TRUE);
		this.tolerance = tolerance;
	}

	protected AbstractRegion(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree ,final double tolerance) {
		org.apache.commons.math3.geometry.partitioning.AbstractRegion.this.tree = tree;
		this.tolerance = tolerance;
	}

	protected AbstractRegion(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> boundary ,final double tolerance) {
		this.tolerance = tolerance;
		if ((boundary.size()) == 0) {
			tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(java.lang.Boolean.TRUE);
		} else {
			final java.util.TreeSet<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> ordered = new java.util.TreeSet<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>>(new java.util.Comparator<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>>() {
				public int compare(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> o1, final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> o2) {
					final double size1 = o1.getSize();
					final double size2 = o2.getSize();
					return size2 < size1 ? -1 : o1 == o2 ? 0 : +1;
				}
			});
			ordered.addAll(boundary);
			tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>();
			insertCuts(tree, ordered);
			tree.visit(new org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<S>() {
				public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
					return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_SUB_MINUS;
				}

				public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
				}

				public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
					if (((node.getParent()) == null) || (node == (node.getParent().getMinus()))) {
						node.setAttribute(java.lang.Boolean.TRUE);
					} else {
						node.setAttribute(java.lang.Boolean.FALSE);
					}
				}
			});
		}
	}

	public AbstractRegion(final org.apache.commons.math3.geometry.partitioning.Hyperplane<S>[] hyperplanes ,final double tolerance) {
		this.tolerance = tolerance;
		if ((hyperplanes == null) || ((hyperplanes.length) == 0)) {
			tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(java.lang.Boolean.FALSE);
		} else {
			tree = hyperplanes[0].wholeSpace().getTree(false);
			org.apache.commons.math3.geometry.partitioning.BSPTree<S> node = tree;
			node.setAttribute(java.lang.Boolean.TRUE);
			for (final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane : hyperplanes) {
				if (node.insertCut(hyperplane)) {
					node.setAttribute(null);
					node.getPlus().setAttribute(java.lang.Boolean.FALSE);
					node = node.getMinus();
					node.setAttribute(java.lang.Boolean.TRUE);
				} 
			}
		}
	}

	public abstract org.apache.commons.math3.geometry.partitioning.AbstractRegion<S, T> buildNew(org.apache.commons.math3.geometry.partitioning.BSPTree<S> newTree);

	public double getTolerance() {
		return tolerance;
	}

	private void insertCuts(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> boundary) {
		final java.util.Iterator<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> iterator = boundary.iterator();
		org.apache.commons.math3.geometry.partitioning.Hyperplane<S> inserted = null;
		while ((inserted == null) && (iterator.hasNext())) {
			inserted = iterator.next().getHyperplane();
			if (!(node.insertCut(inserted.copySelf()))) {
				inserted = null;
			} 
		}
		if (!(iterator.hasNext())) {
			return ;
		} 
		final java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> plusList = new java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>>();
		final java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>> minusList = new java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.SubHyperplane<S>>();
		while (iterator.hasNext()) {
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> other = iterator.next();
			switch (other.side(inserted)) {
				case PLUS :
					plusList.add(other);
					break;
				case MINUS :
					minusList.add(other);
					break;
				case BOTH :
					final org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<S> split = other.split(inserted);
					plusList.add(split.getPlus());
					minusList.add(split.getMinus());
					break;
				default :
			}
		}
		insertCuts(node.getPlus(), plusList);
		insertCuts(node.getMinus(), minusList);
	}

	public org.apache.commons.math3.geometry.partitioning.AbstractRegion<S, T> copySelf() {
		return buildNew(tree.copySelf());
	}

	public boolean isEmpty() {
		return isEmpty(tree);
	}

	public boolean isEmpty(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		if ((node.getCut()) == null) {
			return !((java.lang.Boolean)(node.getAttribute()));
		} 
		return (isEmpty(node.getMinus())) && (isEmpty(node.getPlus()));
	}

	public boolean isFull() {
		return isFull(tree);
	}

	public boolean isFull(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		if ((node.getCut()) == null) {
			return ((java.lang.Boolean)(node.getAttribute()));
		} 
		return (isFull(node.getMinus())) && (isFull(node.getPlus()));
	}

	public boolean contains(final org.apache.commons.math3.geometry.partitioning.Region<S> region) {
		return new org.apache.commons.math3.geometry.partitioning.RegionFactory<S>().difference(region, org.apache.commons.math3.geometry.partitioning.AbstractRegion.this).isEmpty();
	}

	public org.apache.commons.math3.geometry.partitioning.BoundaryProjection<S> projectToBoundary(final org.apache.commons.math3.geometry.Point<S> point) {
		final org.apache.commons.math3.geometry.partitioning.BoundaryProjector<S, T> projector = new org.apache.commons.math3.geometry.partitioning.BoundaryProjector<S, T>(point);
		getTree(true).visit(projector);
		return projector.getProjection();
	}

	public org.apache.commons.math3.geometry.partitioning.Region.Location checkPoint(final org.apache.commons.math3.geometry.Vector<S> point) {
		return checkPoint(((org.apache.commons.math3.geometry.Point<S>)(point)));
	}

	public org.apache.commons.math3.geometry.partitioning.Region.Location checkPoint(final org.apache.commons.math3.geometry.Point<S> point) {
		return checkPoint(tree, point);
	}

	protected org.apache.commons.math3.geometry.partitioning.Region.Location checkPoint(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.Vector<S> point) {
		return checkPoint(node, ((org.apache.commons.math3.geometry.Point<S>)(point)));
	}

	protected org.apache.commons.math3.geometry.partitioning.Region.Location checkPoint(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.Point<S> point) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> cell = node.getCell(point, tolerance);
		if ((cell.getCut()) == null) {
			return ((java.lang.Boolean)(cell.getAttribute())) ? org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE : org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE;
		} 
		final org.apache.commons.math3.geometry.partitioning.Region.Location minusCode = checkPoint(cell.getMinus(), point);
		final org.apache.commons.math3.geometry.partitioning.Region.Location plusCode = checkPoint(cell.getPlus(), point);
		return minusCode == plusCode ? minusCode : org.apache.commons.math3.geometry.partitioning.Region.Location.BOUNDARY;
	}

	public org.apache.commons.math3.geometry.partitioning.BSPTree<S> getTree(final boolean includeBoundaryAttributes) {
		if ((includeBoundaryAttributes && ((tree.getCut()) != null)) && ((tree.getAttribute()) == null)) {
			tree.visit(new org.apache.commons.math3.geometry.partitioning.BoundaryBuilder<S>());
		} 
		return tree;
	}

	public double getBoundarySize() {
		final org.apache.commons.math3.geometry.partitioning.BoundarySizeVisitor<S> visitor = new org.apache.commons.math3.geometry.partitioning.BoundarySizeVisitor<S>();
		getTree(true).visit(visitor);
		return visitor.getSize();
	}

	public double getSize() {
		if ((barycenter) == null) {
			computeGeometricalProperties();
		} 
		return size;
	}

	protected void setSize(final double size) {
		org.apache.commons.math3.geometry.partitioning.AbstractRegion.this.size = size;
	}

	public org.apache.commons.math3.geometry.Point<S> getBarycenter() {
		if ((barycenter) == null) {
			computeGeometricalProperties();
		} 
		return barycenter;
	}

	protected void setBarycenter(final org.apache.commons.math3.geometry.Vector<S> barycenter) {
		setBarycenter(((org.apache.commons.math3.geometry.Point<S>)(barycenter)));
	}

	protected void setBarycenter(final org.apache.commons.math3.geometry.Point<S> barycenter) {
		org.apache.commons.math3.geometry.partitioning.AbstractRegion.this.barycenter = barycenter;
	}

	protected abstract void computeGeometricalProperties();

	public org.apache.commons.math3.geometry.partitioning.Side side(final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane) {
		final org.apache.commons.math3.geometry.partitioning.InsideFinder<S> finder = new org.apache.commons.math3.geometry.partitioning.InsideFinder<S>(org.apache.commons.math3.geometry.partitioning.AbstractRegion.this);
		finder.recurseSides(tree, hyperplane.wholeHyperplane());
		return finder.plusFound() ? finder.minusFound() ? org.apache.commons.math3.geometry.partitioning.Side.BOTH : org.apache.commons.math3.geometry.partitioning.Side.PLUS : finder.minusFound() ? org.apache.commons.math3.geometry.partitioning.Side.MINUS : org.apache.commons.math3.geometry.partitioning.Side.HYPER;
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> intersection(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub) {
		return recurseIntersection(tree, sub);
	}

	private org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> recurseIntersection(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub) {
		if ((node.getCut()) == null) {
			return ((java.lang.Boolean)(node.getAttribute())) ? sub.copySelf() : null;
		} 
		final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane = node.getCut().getHyperplane();
		switch (sub.side(hyperplane)) {
			case PLUS :
				return recurseIntersection(node.getPlus(), sub);
			case MINUS :
				return recurseIntersection(node.getMinus(), sub);
			case BOTH :
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plus = recurseIntersection(node.getPlus(), split.getPlus());
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> minus = recurseIntersection(node.getMinus(), split.getMinus());
				if (plus == null) {
					return minus;
				} else if (minus == null) {
					return plus;
				} else {
					return plus.reunite(minus);
				}
			default :
				return recurseIntersection(node.getPlus(), recurseIntersection(node.getMinus(), sub));
		}
	}

	public org.apache.commons.math3.geometry.partitioning.AbstractRegion<S, T> applyTransform(final org.apache.commons.math3.geometry.partitioning.Transform<S, T> transform) {
		final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> map = new java.util.HashMap<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>>();
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> transformedTree = recurseTransform(getTree(false), transform, map);
		for (final java.util.Map.Entry<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> entry : map.entrySet()) {
			if ((entry.getKey().getCut()) != null) {
				@java.lang.SuppressWarnings(value = "unchecked")
				org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> original = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(entry.getKey().getAttribute()));
				if (original != null) {
					@java.lang.SuppressWarnings(value = "unchecked")
					org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> transformed = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(entry.getValue().getAttribute()));
					for (final org.apache.commons.math3.geometry.partitioning.BSPTree<S> splitter : original.getSplitters()) {
						transformed.getSplitters().add(map.get(splitter));
					}
				} 
			} 
		}
		return buildNew(transformedTree);
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	private org.apache.commons.math3.geometry.partitioning.BSPTree<S> recurseTransform(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.partitioning.Transform<S, T> transform, final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> map) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> transformedNode;
		if ((node.getCut()) == null) {
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(node.getAttribute());
		} else {
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub = node.getCut();
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> tSub = ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T>)(sub)).applyTransform(transform);
			org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(node.getAttribute()));
			if (attribute != null) {
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> tPO = (attribute.getPlusOutside()) == null ? null : ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T>)(attribute.getPlusOutside())).applyTransform(transform);
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> tPI = (attribute.getPlusInside()) == null ? null : ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T>)(attribute.getPlusInside())).applyTransform(transform);
				attribute = new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>(tPO , tPI , new org.apache.commons.math3.geometry.partitioning.NodesSet<S>());
			} 
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(tSub , recurseTransform(node.getPlus(), transform, map) , recurseTransform(node.getMinus(), transform, map) , attribute);
		}
		map.put(node, transformedNode);
		return transformedNode;
	}
}

