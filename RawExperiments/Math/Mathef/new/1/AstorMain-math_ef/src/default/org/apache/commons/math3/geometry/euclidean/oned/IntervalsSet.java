package org.apache.commons.math3.geometry.euclidean.oned;


public class IntervalsSet extends org.apache.commons.math3.geometry.partitioning.AbstractRegion<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> implements java.lang.Iterable<double[]> {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	public IntervalsSet(final double tolerance) {
		super(tolerance);
	}

	public IntervalsSet(final double lower ,final double upper ,final double tolerance) {
		super(org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree(lower, upper, tolerance), tolerance);
	}

	public IntervalsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> tree ,final double tolerance) {
		super(tree, tolerance);
	}

	public IntervalsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>> boundary ,final double tolerance) {
		super(boundary, tolerance);
	}

	@java.lang.Deprecated
	public IntervalsSet() {
		this(org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public IntervalsSet(final double lower ,final double upper) {
		this(lower, upper, org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public IntervalsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> tree) {
		this(tree, org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public IntervalsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>> boundary) {
		this(boundary, org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.DEFAULT_TOLERANCE);
	}

	private static org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> buildTree(final double lower, final double upper, final double tolerance) {
		if ((java.lang.Double.isInfinite(lower)) && (lower < 0)) {
			if ((java.lang.Double.isInfinite(upper)) && (upper > 0)) {
				return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.TRUE);
			} 
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> upperCut = new org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(upper) , true , tolerance).wholeHyperplane();
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(upperCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.TRUE) , null);
		} 
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> lowerCut = new org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(lower) , false , tolerance).wholeHyperplane();
		if ((java.lang.Double.isInfinite(upper)) && (upper > 0)) {
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(lowerCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.TRUE) , null);
		} 
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> upperCut = new org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(upper) , true , tolerance).wholeHyperplane();
		return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(lowerCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(upperCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(java.lang.Boolean.TRUE) , null) , null);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet buildNew(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> tree) {
		return new org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet(tree , getTolerance());
	}

	@java.lang.Override
	protected void computeGeometricalProperties() {
		if ((getTree(false).getCut()) == null) {
			setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.NaN)));
			setSize((((java.lang.Boolean)(getTree(false).getAttribute())) ? java.lang.Double.POSITIVE_INFINITY : 0));
		} else {
			double size = 0.0;
			double sum = 0.0;
			for (final org.apache.commons.math3.geometry.euclidean.oned.Interval interval : asList()) {
				size += interval.getSize();
				sum += (interval.getSize()) * (interval.getBarycenter());
			}
			setSize(size);
			if (java.lang.Double.isInfinite(size)) {
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.NaN)));
			} else if (size >= (org.apache.commons.math3.util.Precision.SAFE_MIN)) {
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D((sum / size)))));
			} else {
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(getTree(false).getCut().getHyperplane())).getLocation())));
			}
		}
	}

	public double getInf() {
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node = getTree(false);
		double inf = java.lang.Double.POSITIVE_INFINITY;
		while ((node.getCut()) != null) {
			final org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint op = ((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(node.getCut().getHyperplane()));
			inf = op.getLocation().getX();
			node = op.isDirect() ? node.getMinus() : node.getPlus();
		}
		return ((java.lang.Boolean)(node.getAttribute())) ? java.lang.Double.NEGATIVE_INFINITY : inf;
	}

	public double getSup() {
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node = getTree(false);
		double sup = java.lang.Double.NEGATIVE_INFINITY;
		while ((node.getCut()) != null) {
			final org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint op = ((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(node.getCut().getHyperplane()));
			sup = op.getLocation().getX();
			node = op.isDirect() ? node.getPlus() : node.getMinus();
		}
		return ((java.lang.Boolean)(node.getAttribute())) ? java.lang.Double.POSITIVE_INFINITY : sup;
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> projectToBoundary(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> point) {
		final double x = ((org.apache.commons.math3.geometry.euclidean.oned.Vector1D)(point)).getX();
		double previous = java.lang.Double.NEGATIVE_INFINITY;
		for (final double[] a : org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.this) {
			if (x < (a[0])) {
				final double previousOffset = x - previous;
				final double currentOffset = (a[0]) - x;
				if (previousOffset < currentOffset) {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(point , finiteOrNullPoint(previous) , previousOffset);
				} else {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(point , finiteOrNullPoint(a[0]) , currentOffset);
				}
			} else if (x <= (a[1])) {
				final double offset0 = (a[0]) - x;
				final double offset1 = x - (a[1]);
				if (offset0 < offset1) {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(point , finiteOrNullPoint(a[1]) , offset1);
				} else {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(point , finiteOrNullPoint(a[0]) , offset0);
				}
			} 
			previous = a[1];
		}
		return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>(point , finiteOrNullPoint(previous) , (x - previous));
	}

	private org.apache.commons.math3.geometry.euclidean.oned.Vector1D finiteOrNullPoint(final double x) {
		return java.lang.Double.isInfinite(x) ? null : new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(x);
	}

	public java.util.List<org.apache.commons.math3.geometry.euclidean.oned.Interval> asList() {
		final java.util.List<org.apache.commons.math3.geometry.euclidean.oned.Interval> list = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.oned.Interval>();
		for (final double[] a : org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.this) {
			list.add(new org.apache.commons.math3.geometry.euclidean.oned.Interval(a[0] , a[1]));
		}
		return list;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> getFirstLeaf(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> root) {
		if ((root.getCut()) == null) {
			return root;
		} 
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> smallest = null;
		for (org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> n = root ; n != null ; n = previousInternalNode(n)) {
			smallest = n;
		}
		return leafBefore(smallest);
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> getFirstIntervalBoundary() {
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node = getTree(false);
		if ((node.getCut()) == null) {
			return null;
		} 
		node = getFirstLeaf(node).getParent();
		while ((node != null) && (!((isIntervalStart(node)) || (isIntervalEnd(node))))) {
			node = nextInternalNode(node);
		}
		return node;
	}

	private boolean isIntervalStart(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if (((java.lang.Boolean)(leafBefore(node).getAttribute()))) {
			return false;
		} 
		if (!((java.lang.Boolean)(leafAfter(node).getAttribute()))) {
			return false;
		} 
		return true;
	}

	private boolean isIntervalEnd(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if (!((java.lang.Boolean)(leafBefore(node).getAttribute()))) {
			return false;
		} 
		if (((java.lang.Boolean)(leafAfter(node).getAttribute()))) {
			return false;
		} 
		return true;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> nextInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if ((childAfter(node).getCut()) != null) {
			return leafAfter(node).getParent();
		} 
		while (isAfterParent(node)) {
			node = node.getParent();
		}
		return node.getParent();
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> previousInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if ((childBefore(node).getCut()) != null) {
			return leafBefore(node).getParent();
		} 
		while (isBeforeParent(node)) {
			node = node.getParent();
		}
		return node.getParent();
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> leafBefore(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		node = childBefore(node);
		while ((node.getCut()) != null) {
			node = childAfter(node);
		}
		return node;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> leafAfter(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		node = childAfter(node);
		while ((node.getCut()) != null) {
			node = childBefore(node);
		}
		return node;
	}

	private boolean isBeforeParent(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> parent = node.getParent();
		if (parent == null) {
			return false;
		} else {
			return node == (childBefore(parent));
		}
	}

	private boolean isAfterParent(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> parent = node.getParent();
		if (parent == null) {
			return false;
		} else {
			return node == (childAfter(parent));
		}
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> childBefore(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if (isDirect(node)) {
			return node.getMinus();
		} else {
			return node.getPlus();
		}
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> childAfter(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		if (isDirect(node)) {
			return node.getPlus();
		} else {
			return node.getMinus();
		}
	}

	private boolean isDirect(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		return ((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(node.getCut().getHyperplane())).isDirect();
	}

	private double getAngle(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> node) {
		return ((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(node.getCut().getHyperplane())).getLocation().getX();
	}

	public java.util.Iterator<double[]> iterator() {
		return new org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.SubIntervalsIterator();
	}

	private class SubIntervalsIterator implements java.util.Iterator<double[]> {
		private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> current;

		private double[] pending;

		public SubIntervalsIterator() {
			current = getFirstIntervalBoundary();
			if ((current) == null) {
				if (((java.lang.Boolean)(getFirstLeaf(getTree(false)).getAttribute()))) {
					pending = new double[]{ java.lang.Double.NEGATIVE_INFINITY , java.lang.Double.POSITIVE_INFINITY };
				} else {
					pending = null;
				}
			} else if (isIntervalEnd(current)) {
				pending = new double[]{ java.lang.Double.NEGATIVE_INFINITY , getAngle(current) };
			} else {
				selectPending();
			}
		}

		private void selectPending() {
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> start = current;
			while ((start != null) && (!(isIntervalStart(start)))) {
				start = nextInternalNode(start);
			}
			if (start == null) {
				current = null;
				pending = null;
				return ;
			} 
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> end = start;
			while ((end != null) && (!(isIntervalEnd(end)))) {
				end = nextInternalNode(end);
			}
			if (end != null) {
				pending = new double[]{ getAngle(start) , getAngle(end) };
				current = end;
			} else {
				pending = new double[]{ getAngle(start) , java.lang.Double.POSITIVE_INFINITY };
				current = null;
			}
		}

		public boolean hasNext() {
			return (pending) != null;
		}

		public double[] next() {
			if ((pending) == null) {
				throw new java.util.NoSuchElementException();
			} 
			final double[] next = pending;
			selectPending();
			return next;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}
}

