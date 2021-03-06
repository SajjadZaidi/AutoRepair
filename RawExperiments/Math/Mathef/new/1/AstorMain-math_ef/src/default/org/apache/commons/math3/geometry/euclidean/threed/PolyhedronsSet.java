package org.apache.commons.math3.geometry.euclidean.threed;


public class PolyhedronsSet extends org.apache.commons.math3.geometry.partitioning.AbstractRegion<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	public PolyhedronsSet(final double tolerance) {
		super(tolerance);
	}

	public PolyhedronsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> tree ,final double tolerance) {
		super(tree, tolerance);
	}

	public PolyhedronsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>> boundary ,final double tolerance) {
		super(boundary, tolerance);
	}

	public PolyhedronsSet(final double xMin ,final double xMax ,final double yMin ,final double yMax ,final double zMin ,final double zMax ,final double tolerance) {
		super(org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.buildBoundary(xMin, xMax, yMin, yMax, zMin, zMax, tolerance), tolerance);
	}

	@java.lang.Deprecated
	public PolyhedronsSet() {
		this(org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolyhedronsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> tree) {
		this(tree, org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolyhedronsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>> boundary) {
		this(boundary, org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolyhedronsSet(final double xMin ,final double xMax ,final double yMin ,final double yMax ,final double zMin ,final double zMax) {
		this(xMin, xMax, yMin, yMax, zMin, zMax, org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.DEFAULT_TOLERANCE);
	}

	private static org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> buildBoundary(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax, final double tolerance) {
		if (((xMin >= (xMax - tolerance)) || (yMin >= (yMax - tolerance))) || (zMin >= (zMax - tolerance))) {
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>(java.lang.Boolean.FALSE);
		} 
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pxMin = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(xMin , 0 , 0) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_I , tolerance);
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pxMax = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(xMax , 0 , 0) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_I , tolerance);
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pyMin = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0 , yMin , 0) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_J , tolerance);
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pyMax = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0 , yMax , 0) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_J , tolerance);
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pzMin = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0 , 0 , zMin) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.MINUS_K , tolerance);
		final org.apache.commons.math3.geometry.euclidean.threed.Plane pzMax = new org.apache.commons.math3.geometry.euclidean.threed.Plane(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0 , 0 , zMax) , org.apache.commons.math3.geometry.euclidean.threed.Vector3D.PLUS_K , tolerance);
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> boundary = new org.apache.commons.math3.geometry.partitioning.RegionFactory<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>().buildConvex(pxMin, pxMax, pyMin, pyMax, pzMin, pzMax);
		return boundary.getTree(false);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet buildNew(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> tree) {
		return new org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet(tree , getTolerance());
	}

	@java.lang.Override
	protected void computeGeometricalProperties() {
		getTree(true).visit(new org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.FacetsContributionVisitor());
		if ((getSize()) < 0) {
			setSize(java.lang.Double.POSITIVE_INFINITY);
			setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.NaN)));
		} else {
			setSize(((getSize()) / 3.0));
			setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D((1.0 / (4 * (getSize()))) , ((org.apache.commons.math3.geometry.euclidean.threed.Vector3D)(getBarycenter()))))));
		}
	}

	private class FacetsContributionVisitor implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> {
		public FacetsContributionVisitor() {
			setSize(0);
			setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0 , 0 , 0))));
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> node) {
			return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS;
		}

		public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> node) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(node.getAttribute()));
			if ((attribute.getPlusOutside()) != null) {
				addContribution(attribute.getPlusOutside(), false);
			} 
			if ((attribute.getPlusInside()) != null) {
				addContribution(attribute.getPlusInside(), true);
			} 
		}

		public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> node) {
		}

		private void addContribution(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> facet, final boolean reversed) {
			final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> polygon = ((org.apache.commons.math3.geometry.euclidean.threed.SubPlane)(facet)).getRemainingRegion();
			final double area = polygon.getSize();
			if (java.lang.Double.isInfinite(area)) {
				setSize(java.lang.Double.POSITIVE_INFINITY);
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.NaN)));
			} else {
				final org.apache.commons.math3.geometry.euclidean.threed.Plane plane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(facet.getHyperplane()));
				final org.apache.commons.math3.geometry.euclidean.threed.Vector3D facetB = plane.toSpace(polygon.getBarycenter());
				double scaled = area * (facetB.dotProduct(plane.getNormal()));
				if (reversed) {
					scaled = -scaled;
				} 
				setSize(((getSize()) + scaled));
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(1.0 , ((org.apache.commons.math3.geometry.euclidean.threed.Vector3D)(getBarycenter())) , scaled , facetB))));
			}
		}
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> firstIntersection(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D point, final org.apache.commons.math3.geometry.euclidean.threed.Line line) {
		return recurseFirstIntersection(getTree(true), point, line);
	}

	private org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> recurseFirstIntersection(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> node, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D point, final org.apache.commons.math3.geometry.euclidean.threed.Line line) {
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> cut = node.getCut();
		if (cut == null) {
			return null;
		} 
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> minus = node.getMinus();
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> plus = node.getPlus();
		final org.apache.commons.math3.geometry.euclidean.threed.Plane plane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(cut.getHyperplane()));
		final double offset = plane.getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(point)));
		final boolean in = (org.apache.commons.math3.util.FastMath.abs(offset)) < 1.0E-10;
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> near;
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> far;
		if (offset < 0) {
			near = minus;
			far = plus;
		} else {
			near = plus;
			far = minus;
		}
		if (in) {
			final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> facet = boundaryFacet(point, node);
			if (facet != null) {
				return facet;
			} 
		} 
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> crossed = recurseFirstIntersection(near, point, line);
		if (crossed != null) {
			return crossed;
		} 
		if (!in) {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D hit3D = plane.intersection(line);
			if (hit3D != null) {
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> facet = boundaryFacet(hit3D, node);
				if (facet != null) {
					return facet;
				} 
			} 
		} 
		return recurseFirstIntersection(far, point, line);
	}

	private org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> boundaryFacet(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D point, final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> node) {
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D point2D = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(node.getCut().getHyperplane())).toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(point)));
		@java.lang.SuppressWarnings(value = "unchecked")
		final org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(node.getAttribute()));
		if (((attribute.getPlusOutside()) != null) && ((((org.apache.commons.math3.geometry.euclidean.threed.SubPlane)(attribute.getPlusOutside())).getRemainingRegion().checkPoint(point2D)) == (org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE))) {
			return attribute.getPlusOutside();
		} 
		if (((attribute.getPlusInside()) != null) && ((((org.apache.commons.math3.geometry.euclidean.threed.SubPlane)(attribute.getPlusInside())).getRemainingRegion().checkPoint(point2D)) == (org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE))) {
			return attribute.getPlusInside();
		} 
		return null;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet rotate(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D center, final org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation) {
		return ((org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet)(applyTransform(new org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.RotationTransform(center , rotation))));
	}

	private static class RotationTransform implements org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
		private org.apache.commons.math3.geometry.euclidean.threed.Vector3D center;

		private org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation;

		private org.apache.commons.math3.geometry.euclidean.threed.Plane cachedOriginal;

		private org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> cachedTransform;

		public RotationTransform(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D center ,final org.apache.commons.math3.geometry.euclidean.threed.Rotation rotation) {
			org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.RotationTransform.this.center = center;
			org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.RotationTransform.this.rotation = rotation;
		}

		public org.apache.commons.math3.geometry.euclidean.threed.Vector3D apply(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> point) {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D delta = ((org.apache.commons.math3.geometry.euclidean.threed.Vector3D)(point)).subtract(center);
			return new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(1.0 , center , 1.0 , rotation.applyTo(delta));
		}

		public org.apache.commons.math3.geometry.euclidean.threed.Plane apply(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane) {
			return ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(hyperplane)).rotate(center, rotation);
		}

		public org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> apply(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> sub, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> original, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> transformed) {
			if (original != (cachedOriginal)) {
				final org.apache.commons.math3.geometry.euclidean.threed.Plane oPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(original));
				final org.apache.commons.math3.geometry.euclidean.threed.Plane tPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(transformed));
				final org.apache.commons.math3.geometry.euclidean.threed.Vector3D p00 = oPlane.getOrigin();
				final org.apache.commons.math3.geometry.euclidean.threed.Vector3D p10 = oPlane.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(1.0 , 0.0))));
				final org.apache.commons.math3.geometry.euclidean.threed.Vector3D p01 = oPlane.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(0.0 , 1.0))));
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D tP00 = tPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(apply(p00))));
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D tP10 = tPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(apply(p10))));
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D tP01 = tPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(apply(p01))));
				final java.awt.geom.AffineTransform at = new java.awt.geom.AffineTransform(((tP10.getX()) - (tP00.getX())) , ((tP10.getY()) - (tP00.getY())) , ((tP01.getX()) - (tP00.getX())) , ((tP01.getY()) - (tP00.getY())) , tP00.getX() , tP00.getY());
				cachedOriginal = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(original));
				cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(at);
			} 
			return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine)(sub)).applyTransform(cachedTransform);
		}
	}

	public org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet translate(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D translation) {
		return ((org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet)(applyTransform(new org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.TranslationTransform(translation))));
	}

	private static class TranslationTransform implements org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
		private org.apache.commons.math3.geometry.euclidean.threed.Vector3D translation;

		private org.apache.commons.math3.geometry.euclidean.threed.Plane cachedOriginal;

		private org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> cachedTransform;

		public TranslationTransform(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D translation) {
			org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.TranslationTransform.this.translation = translation;
		}

		public org.apache.commons.math3.geometry.euclidean.threed.Vector3D apply(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> point) {
			return new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(1.0 , ((org.apache.commons.math3.geometry.euclidean.threed.Vector3D)(point)) , 1.0 , translation);
		}

		public org.apache.commons.math3.geometry.euclidean.threed.Plane apply(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane) {
			return ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(hyperplane)).translate(translation);
		}

		public org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> apply(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> sub, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> original, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> transformed) {
			if (original != (cachedOriginal)) {
				final org.apache.commons.math3.geometry.euclidean.threed.Plane oPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(original));
				final org.apache.commons.math3.geometry.euclidean.threed.Plane tPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(transformed));
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D shift = tPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(apply(oPlane.getOrigin()))));
				final java.awt.geom.AffineTransform at = java.awt.geom.AffineTransform.getTranslateInstance(shift.getX(), shift.getY());
				cachedOriginal = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(original));
				cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(at);
			} 
			return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine)(sub)).applyTransform(cachedTransform);
		}
	}
}

