package org.apache.commons.math3.geometry.spherical.oned;


public class ArcsSet extends org.apache.commons.math3.geometry.partitioning.AbstractRegion<org.apache.commons.math3.geometry.spherical.oned.Sphere1D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> implements java.lang.Iterable<double[]> {
	public ArcsSet(final double tolerance) {
		super(tolerance);
	}

	public ArcsSet(final double lower ,final double upper ,final double tolerance) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		super(org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree(lower, upper, tolerance), tolerance);
	}

	public ArcsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> tree ,final double tolerance) throws org.apache.commons.math3.geometry.spherical.oned.ArcsSet.InconsistentStateAt2PiWrapping {
		super(tree, tolerance);
		check2PiConsistency();
	}

	public ArcsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>> boundary ,final double tolerance) throws org.apache.commons.math3.geometry.spherical.oned.ArcsSet.InconsistentStateAt2PiWrapping {
		super(boundary, tolerance);
		check2PiConsistency();
	}

	private static org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> buildTree(final double lower, final double upper, final double tolerance) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		if ((org.apache.commons.math3.util.Precision.equals(lower, upper, 0)) || ((upper - lower) >= (org.apache.commons.math3.util.MathUtils.TWO_PI))) {
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.TRUE);
		} else if (lower > upper) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL , lower , upper , true);
		} 
		final double normalizedLower = org.apache.commons.math3.util.MathUtils.normalizeAngle(lower, org.apache.commons.math3.util.FastMath.PI);
		final double normalizedUpper = normalizedLower + (upper - lower);
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> lowerCut = new org.apache.commons.math3.geometry.spherical.oned.LimitAngle(new org.apache.commons.math3.geometry.spherical.oned.S1Point(normalizedLower) , false , tolerance).wholeHyperplane();
		if (normalizedUpper <= (org.apache.commons.math3.util.MathUtils.TWO_PI)) {
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> upperCut = new org.apache.commons.math3.geometry.spherical.oned.LimitAngle(new org.apache.commons.math3.geometry.spherical.oned.S1Point(normalizedUpper) , true , tolerance).wholeHyperplane();
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(lowerCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(upperCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.TRUE) , null) , null);
		} else {
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> upperCut = new org.apache.commons.math3.geometry.spherical.oned.LimitAngle(new org.apache.commons.math3.geometry.spherical.oned.S1Point((normalizedUpper - (org.apache.commons.math3.util.MathUtils.TWO_PI))) , true , tolerance).wholeHyperplane();
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(lowerCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(upperCut , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.FALSE) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.TRUE) , null) , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.TRUE) , null);
		}
	}

	private void check2PiConsistency() throws org.apache.commons.math3.geometry.spherical.oned.ArcsSet.InconsistentStateAt2PiWrapping {
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> root = getTree(false);
		if ((root.getCut()) == null) {
			return ;
		} 
		final java.lang.Boolean stateBefore = ((java.lang.Boolean)(getFirstLeaf(root).getAttribute()));
		final java.lang.Boolean stateAfter = ((java.lang.Boolean)(getLastLeaf(root).getAttribute()));
		if (stateBefore ^ stateAfter) {
			throw new org.apache.commons.math3.geometry.spherical.oned.ArcsSet.InconsistentStateAt2PiWrapping();
		} 
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> getFirstLeaf(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> root) {
		if ((root.getCut()) == null) {
			return root;
		} 
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> smallest = null;
		for (org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> n = root ; n != null ; n = previousInternalNode(n)) {
			smallest = n;
		}
		return leafBefore(smallest);
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> getLastLeaf(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> root) {
		if ((root.getCut()) == null) {
			return root;
		} 
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> largest = null;
		for (org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> n = root ; n != null ; n = nextInternalNode(n)) {
			largest = n;
		}
		return leafAfter(largest);
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> getFirstArcStart() {
		org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node = getTree(false);
		if ((node.getCut()) == null) {
			return null;
		} 
		node = getFirstLeaf(node).getParent();
		while ((node != null) && (!(isArcStart(node)))) {
			node = nextInternalNode(node);
		}
		return node;
	}

	private boolean isArcStart(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if (((java.lang.Boolean)(leafBefore(node).getAttribute()))) {
			return false;
		} 
		if (!((java.lang.Boolean)(leafAfter(node).getAttribute()))) {
			return false;
		} 
		return true;
	}

	private boolean isArcEnd(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if (!((java.lang.Boolean)(leafBefore(node).getAttribute()))) {
			return false;
		} 
		if (((java.lang.Boolean)(leafAfter(node).getAttribute()))) {
			return false;
		} 
		return true;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> nextInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if ((childAfter(node).getCut()) != null) {
			return leafAfter(node).getParent();
		} 
		while (isAfterParent(node)) {
			node = node.getParent();
		}
		return node.getParent();
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> previousInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if ((childBefore(node).getCut()) != null) {
			return leafBefore(node).getParent();
		} 
		while (isBeforeParent(node)) {
			node = node.getParent();
		}
		return node.getParent();
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> leafBefore(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		node = childBefore(node);
		while ((node.getCut()) != null) {
			node = childAfter(node);
		}
		return node;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> leafAfter(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		node = childAfter(node);
		while ((node.getCut()) != null) {
			node = childBefore(node);
		}
		return node;
	}

	private boolean isBeforeParent(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> parent = node.getParent();
		if (parent == null) {
			return false;
		} else {
			return node == (childBefore(parent));
		}
	}

	private boolean isAfterParent(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> parent = node.getParent();
		if (parent == null) {
			return false;
		} else {
			return node == (childAfter(parent));
		}
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> childBefore(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if (isDirect(node)) {
			return node.getMinus();
		} else {
			return node.getPlus();
		}
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> childAfter(org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		if (isDirect(node)) {
			return node.getPlus();
		} else {
			return node.getMinus();
		}
	}

	private boolean isDirect(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		return ((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(node.getCut().getHyperplane())).isDirect();
	}

	private double getAngle(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node) {
		return ((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(node.getCut().getHyperplane())).getLocation().getAlpha();
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.spherical.oned.ArcsSet buildNew(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> tree) {
		return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet(tree , getTolerance());
	}

	@java.lang.Override
	protected void computeGeometricalProperties() {
		if ((getTree(false).getCut()) == null) {
			setBarycenter(org.apache.commons.math3.geometry.spherical.oned.S1Point.NaN);
			setSize((((java.lang.Boolean)(getTree(false).getAttribute())) ? org.apache.commons.math3.util.MathUtils.TWO_PI : 0));
		} else {
			double size = 0.0;
			double sum = 0.0;
			for (final double[] a : org.apache.commons.math3.geometry.spherical.oned.ArcsSet.this) {
				final double length = (a[1]) - (a[0]);
				size += length;
				sum += length * ((a[0]) + (a[1]));
			}
			setSize(size);
			if (org.apache.commons.math3.util.Precision.equals(size, org.apache.commons.math3.util.MathUtils.TWO_PI, 0)) {
				setBarycenter(org.apache.commons.math3.geometry.spherical.oned.S1Point.NaN);
			} else if (size >= (org.apache.commons.math3.util.Precision.SAFE_MIN)) {
				setBarycenter(new org.apache.commons.math3.geometry.spherical.oned.S1Point((sum / (2 * size))));
			} else {
				final org.apache.commons.math3.geometry.spherical.oned.LimitAngle limit = ((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(getTree(false).getCut().getHyperplane()));
				setBarycenter(limit.getLocation());
			}
		}
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> projectToBoundary(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> point) {
		final double alpha = ((org.apache.commons.math3.geometry.spherical.oned.S1Point)(point)).getAlpha();
		boolean wrapFirst = false;
		double first = java.lang.Double.NaN;
		double previous = java.lang.Double.NaN;
		for (final double[] a : org.apache.commons.math3.geometry.spherical.oned.ArcsSet.this) {
			if (java.lang.Double.isNaN(first)) {
				first = a[0];
			} 
			if (!wrapFirst) {
				if (alpha < (a[0])) {
					if (java.lang.Double.isNaN(previous)) {
						wrapFirst = true;
					} else {
						final double previousOffset = alpha - previous;
						final double currentOffset = (a[0]) - alpha;
						if (previousOffset < currentOffset) {
							return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(previous) , previousOffset);
						} else {
							return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(a[0]) , currentOffset);
						}
					}
				} else if (alpha <= (a[1])) {
					final double offset0 = (a[0]) - alpha;
					final double offset1 = alpha - (a[1]);
					if (offset0 < offset1) {
						return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(a[1]) , offset1);
					} else {
						return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(a[0]) , offset0);
					}
				} 
			} 
			previous = a[1];
		}
		if (java.lang.Double.isNaN(previous)) {
			return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , null , org.apache.commons.math3.util.MathUtils.TWO_PI);
		} else {
			if (wrapFirst) {
				final double previousOffset = alpha - (previous - (org.apache.commons.math3.util.MathUtils.TWO_PI));
				final double currentOffset = first - alpha;
				if (previousOffset < currentOffset) {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(previous) , previousOffset);
				} else {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(first) , currentOffset);
				}
			} else {
				final double previousOffset = alpha - previous;
				final double currentOffset = (first + (org.apache.commons.math3.util.MathUtils.TWO_PI)) - alpha;
				if (previousOffset < currentOffset) {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(previous) , previousOffset);
				} else {
					return new org.apache.commons.math3.geometry.partitioning.BoundaryProjection<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(point , new org.apache.commons.math3.geometry.spherical.oned.S1Point(first) , currentOffset);
				}
			}
		}
	}

	public java.util.List<org.apache.commons.math3.geometry.spherical.oned.Arc> asList() {
		final java.util.List<org.apache.commons.math3.geometry.spherical.oned.Arc> list = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.oned.Arc>();
		for (final double[] a : org.apache.commons.math3.geometry.spherical.oned.ArcsSet.this) {
			list.add(new org.apache.commons.math3.geometry.spherical.oned.Arc(a[0] , a[1] , getTolerance()));
		}
		return list;
	}

	public java.util.Iterator<double[]> iterator() {
		return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet.SubArcsIterator();
	}

	private class SubArcsIterator implements java.util.Iterator<double[]> {
		private final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> firstStart;

		private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> current;

		private double[] pending;

		public SubArcsIterator() {
			firstStart = getFirstArcStart();
			current = firstStart;
			if ((firstStart) == null) {
				if (((java.lang.Boolean)(getFirstLeaf(getTree(false)).getAttribute()))) {
					pending = new double[]{ 0 , org.apache.commons.math3.util.MathUtils.TWO_PI };
				} else {
					pending = null;
				}
			} else {
				selectPending();
			}
		}

		private void selectPending() {
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> start = current;
			while ((start != null) && (!(isArcStart(start)))) {
				start = nextInternalNode(start);
			}
			if (start == null) {
				current = null;
				pending = null;
				return ;
			} 
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> end = start;
			while ((end != null) && (!(isArcEnd(end)))) {
				end = nextInternalNode(end);
			}
			if (end != null) {
				pending = new double[]{ getAngle(start) , getAngle(end) };
				current = end;
			} else {
				end = firstStart;
				while ((end != null) && (!(isArcEnd(end)))) {
					end = previousInternalNode(end);
				}
				if (end == null) {
					throw new org.apache.commons.math3.exception.MathInternalError();
				} 
				pending = new double[]{ getAngle(start) , (getAngle(end)) + (org.apache.commons.math3.util.MathUtils.TWO_PI) };
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

	public org.apache.commons.math3.geometry.partitioning.Side side(final org.apache.commons.math3.geometry.spherical.oned.Arc arc) {
		final double reference = (org.apache.commons.math3.util.FastMath.PI) + (arc.getInf());
		final double arcLength = (arc.getSup()) - (arc.getInf());
		boolean inMinus = false;
		boolean inPlus = false;
		for (final double[] a : org.apache.commons.math3.geometry.spherical.oned.ArcsSet.this) {
			final double syncedStart = (org.apache.commons.math3.util.MathUtils.normalizeAngle(a[0], reference)) - (arc.getInf());
			final double arcOffset = (a[0]) - syncedStart;
			final double syncedEnd = (a[1]) - arcOffset;
			if ((syncedStart <= (arcLength - (getTolerance()))) || (syncedEnd >= ((org.apache.commons.math3.util.MathUtils.TWO_PI) + (getTolerance())))) {
				inMinus = true;
			} 
			if (syncedEnd >= (arcLength + (getTolerance()))) {
				inPlus = true;
			} 
		}
		if (inMinus) {
			if (inPlus) {
				return org.apache.commons.math3.geometry.partitioning.Side.BOTH;
			} else {
				return org.apache.commons.math3.geometry.partitioning.Side.MINUS;
			}
		} else {
			if (inPlus) {
				return org.apache.commons.math3.geometry.partitioning.Side.PLUS;
			} else {
				return org.apache.commons.math3.geometry.partitioning.Side.HYPER;
			}
		}
	}

	public org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split split(final org.apache.commons.math3.geometry.spherical.oned.Arc arc) {
		final java.util.List<java.lang.Double> minus = new java.util.ArrayList<java.lang.Double>();
		final java.util.List<java.lang.Double> plus = new java.util.ArrayList<java.lang.Double>();
		final double reference = (org.apache.commons.math3.util.FastMath.PI) + (arc.getInf());
		final double arcLength = (arc.getSup()) - (arc.getInf());
		for (final double[] a : org.apache.commons.math3.geometry.spherical.oned.ArcsSet.this) {
			final double syncedStart = (org.apache.commons.math3.util.MathUtils.normalizeAngle(a[0], reference)) - (arc.getInf());
			final double arcOffset = (a[0]) - syncedStart;
			final double syncedEnd = (a[1]) - arcOffset;
			if (syncedStart < arcLength) {
				minus.add(a[0]);
				if (syncedEnd > arcLength) {
					final double minusToPlus = arcLength + arcOffset;
					minus.add(minusToPlus);
					plus.add(minusToPlus);
					if (syncedEnd > (org.apache.commons.math3.util.MathUtils.TWO_PI)) {
						final double plusToMinus = (org.apache.commons.math3.util.MathUtils.TWO_PI) + arcOffset;
						plus.add(plusToMinus);
						minus.add(plusToMinus);
						minus.add(a[1]);
					} else {
						plus.add(a[1]);
					}
				} else {
					minus.add(a[1]);
				}
			} else {
				plus.add(a[0]);
				if (syncedEnd > (org.apache.commons.math3.util.MathUtils.TWO_PI)) {
					final double plusToMinus = (org.apache.commons.math3.util.MathUtils.TWO_PI) + arcOffset;
					plus.add(plusToMinus);
					minus.add(plusToMinus);
					if (syncedEnd > ((org.apache.commons.math3.util.MathUtils.TWO_PI) + arcLength)) {
						final double minusToPlus = ((org.apache.commons.math3.util.MathUtils.TWO_PI) + arcLength) + arcOffset;
						minus.add(minusToPlus);
						plus.add(minusToPlus);
						plus.add(a[1]);
					} else {
						minus.add(a[1]);
					}
				} else {
					plus.add(a[1]);
				}
			}
		}
		return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split(createSplitPart(plus) , createSplitPart(minus));
	}

	private void addArcLimit(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> tree, final double alpha, final boolean isStart) {
		final org.apache.commons.math3.geometry.spherical.oned.LimitAngle limit = new org.apache.commons.math3.geometry.spherical.oned.LimitAngle(new org.apache.commons.math3.geometry.spherical.oned.S1Point(alpha) , (!isStart) , getTolerance());
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> node = tree.getCell(limit.getLocation(), getTolerance());
		if ((node.getCut()) != null) {
			throw new org.apache.commons.math3.exception.MathInternalError();
		} 
		node.insertCut(limit);
		node.setAttribute(null);
		node.getPlus().setAttribute(java.lang.Boolean.FALSE);
		node.getMinus().setAttribute(java.lang.Boolean.TRUE);
	}

	private org.apache.commons.math3.geometry.spherical.oned.ArcsSet createSplitPart(final java.util.List<java.lang.Double> limits) {
		if (limits.isEmpty()) {
			return null;
		} else {
			for (int i = 0 ; i < (limits.size()) ; ++i) {
				final int j = (i + 1) % (limits.size());
				final double lA = limits.get(i);
				final double lB = org.apache.commons.math3.util.MathUtils.normalizeAngle(limits.get(j), lA);
				if ((org.apache.commons.math3.util.FastMath.abs((lB - lA))) <= (getTolerance())) {
					if (j > 0) {
						limits.remove(j);
						limits.remove(i);
						i = i - 1;
					} else {
						final double lEnd = limits.remove(((limits.size()) - 1));
						final double lStart = limits.remove(0);
						if (limits.isEmpty()) {
							if ((lEnd - lStart) > (org.apache.commons.math3.util.FastMath.PI)) {
								return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet(new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.TRUE) , getTolerance());
							} else {
								return null;
							}
						} else {
							limits.add(((limits.remove(0)) + (org.apache.commons.math3.util.MathUtils.TWO_PI)));
						}
					}
				} 
			}
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(java.lang.Boolean.FALSE);
			for (int i = 0 ; i < ((limits.size()) - 1) ; i += 2) {
				addArcLimit(tree, limits.get(i), true);
				addArcLimit(tree, limits.get((i + 1)), false);
			}
			if ((tree.getCut()) == null) {
				return null;
			} 
			return new org.apache.commons.math3.geometry.spherical.oned.ArcsSet(tree , getTolerance());
		}
	}

	public static class Split {
		private final org.apache.commons.math3.geometry.spherical.oned.ArcsSet plus;

		private final org.apache.commons.math3.geometry.spherical.oned.ArcsSet minus;

		private Split(final org.apache.commons.math3.geometry.spherical.oned.ArcsSet plus ,final org.apache.commons.math3.geometry.spherical.oned.ArcsSet minus) {
			this.plus = plus;
			this.minus = minus;
		}

		public org.apache.commons.math3.geometry.spherical.oned.ArcsSet getPlus() {
			return plus;
		}

		public org.apache.commons.math3.geometry.spherical.oned.ArcsSet getMinus() {
			return minus;
		}
	}

	public static class InconsistentStateAt2PiWrapping extends org.apache.commons.math3.exception.MathIllegalArgumentException {
		private static final long serialVersionUID = 20140107L;

		public InconsistentStateAt2PiWrapping() {
			super(org.apache.commons.math3.exception.util.LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING);
		}
	}
}

