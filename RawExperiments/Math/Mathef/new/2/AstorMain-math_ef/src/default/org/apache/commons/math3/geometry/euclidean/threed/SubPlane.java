package org.apache.commons.math3.geometry.euclidean.threed;


public class SubPlane extends org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
	public SubPlane(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane ,final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> remainingRegion) {
		super(hyperplane, remainingRegion);
	}

	@java.lang.Override
	protected org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> buildNew(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane, final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> remainingRegion) {
		return new org.apache.commons.math3.geometry.euclidean.threed.SubPlane(hyperplane , remainingRegion);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.Side side(org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane) {
		final org.apache.commons.math3.geometry.euclidean.threed.Plane otherPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(hyperplane));
		final org.apache.commons.math3.geometry.euclidean.threed.Plane thisPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(getHyperplane()));
		final org.apache.commons.math3.geometry.euclidean.threed.Line inter = otherPlane.intersection(thisPlane);
		final double tolerance = thisPlane.getTolerance();
		if (inter == null) {
			final double global = otherPlane.getOffset(thisPlane);
			return global < (-1.0E-10) ? org.apache.commons.math3.geometry.partitioning.Side.MINUS : global > 1.0E-10 ? org.apache.commons.math3.geometry.partitioning.Side.PLUS : org.apache.commons.math3.geometry.partitioning.Side.HYPER;
		} 
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p = thisPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(inter.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.ZERO))))));
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D q = thisPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(inter.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.ONE))))));
		org.apache.commons.math3.geometry.euclidean.threed.Vector3D crossP = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
		if ((crossP.dotProduct(otherPlane.getNormal())) < 0) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D tmp = p;
			p = q;
			q = tmp;
		} 
		final org.apache.commons.math3.geometry.euclidean.twod.Line line2D = new org.apache.commons.math3.geometry.euclidean.twod.Line(p , q , tolerance);
		return getRemainingRegion().side(line2D);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> split(org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> hyperplane) {
		final org.apache.commons.math3.geometry.euclidean.threed.Plane otherPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(hyperplane));
		final org.apache.commons.math3.geometry.euclidean.threed.Plane thisPlane = ((org.apache.commons.math3.geometry.euclidean.threed.Plane)(getHyperplane()));
		final org.apache.commons.math3.geometry.euclidean.threed.Line inter = otherPlane.intersection(thisPlane);
		final double tolerance = thisPlane.getTolerance();
		if (inter == null) {
			final double global = otherPlane.getOffset(thisPlane);
			return global < (-1.0E-10) ? new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>(null , org.apache.commons.math3.geometry.euclidean.threed.SubPlane.this) : new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>(org.apache.commons.math3.geometry.euclidean.threed.SubPlane.this , null);
		} 
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p = thisPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(inter.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.ZERO))))));
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D q = thisPlane.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(inter.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(org.apache.commons.math3.geometry.euclidean.oned.Vector1D.ONE))))));
		org.apache.commons.math3.geometry.euclidean.threed.Vector3D crossP = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
		if ((crossP.dotProduct(otherPlane.getNormal())) < 0) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D tmp = p;
			p = q;
			q = tmp;
		} 
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> l2DMinus = new org.apache.commons.math3.geometry.euclidean.twod.Line(p , q , tolerance).wholeHyperplane();
		final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> l2DPlus = new org.apache.commons.math3.geometry.euclidean.twod.Line(q , p , tolerance).wholeHyperplane();
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> splitTree = getRemainingRegion().getTree(false).split(l2DMinus);
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> plusTree = getRemainingRegion().isEmpty(splitTree.getPlus()) ? new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(java.lang.Boolean.FALSE) : new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(l2DPlus , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(java.lang.Boolean.FALSE) , splitTree.getPlus() , null);
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> minusTree = getRemainingRegion().isEmpty(splitTree.getMinus()) ? new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(java.lang.Boolean.FALSE) : new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(l2DMinus , new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(java.lang.Boolean.FALSE) , splitTree.getMinus() , null);
		return new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>(new org.apache.commons.math3.geometry.euclidean.threed.SubPlane(thisPlane.copySelf() , new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet(plusTree , tolerance)) , new org.apache.commons.math3.geometry.euclidean.threed.SubPlane(thisPlane.copySelf() , new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet(minusTree , tolerance)));
	}
}

