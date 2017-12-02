package org.apache.commons.math3.geometry.spherical.twod;


public class SubCircle extends org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
	public SubCircle(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> hyperplane ,final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> remainingRegion) {
		super(hyperplane, remainingRegion);
	}

	@java.lang.Override
	protected org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> buildNew(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> hyperplane, final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> remainingRegion) {
		return new org.apache.commons.math3.geometry.spherical.twod.SubCircle(hyperplane , remainingRegion);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.Side side(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> hyperplane) {
		final org.apache.commons.math3.geometry.spherical.twod.Circle thisCircle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(getHyperplane()));
		final org.apache.commons.math3.geometry.spherical.twod.Circle otherCircle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(hyperplane));
		final double angle = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(thisCircle.getPole(), otherCircle.getPole());
		if ((angle < (thisCircle.getTolerance())) || (angle > ((org.apache.commons.math3.util.FastMath.PI) - (thisCircle.getTolerance())))) {
			return org.apache.commons.math3.geometry.partitioning.Side.HYPER;
		} else {
			return ((org.apache.commons.math3.geometry.spherical.oned.ArcsSet)(getRemainingRegion())).side(thisCircle.getInsideArc(otherCircle));
		}
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> split(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D> hyperplane) {
		final org.apache.commons.math3.geometry.spherical.twod.Circle thisCircle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(getHyperplane()));
		final org.apache.commons.math3.geometry.spherical.twod.Circle otherCircle = ((org.apache.commons.math3.geometry.spherical.twod.Circle)(hyperplane));
		final double angle = org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle(thisCircle.getPole(), otherCircle.getPole());
		if (angle < (thisCircle.getTolerance())) {
			return new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(null , org.apache.commons.math3.geometry.spherical.twod.SubCircle.this);
		} else if (angle > ((org.apache.commons.math3.util.FastMath.PI) - (thisCircle.getTolerance()))) {
			return new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>(org.apache.commons.math3.geometry.spherical.twod.SubCircle.this , null);
		} else {
			final org.apache.commons.math3.geometry.spherical.oned.Arc arc = thisCircle.getInsideArc(otherCircle);
			final org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split split = ((org.apache.commons.math3.geometry.spherical.oned.ArcsSet)(getRemainingRegion())).split(arc);
			final org.apache.commons.math3.geometry.spherical.oned.ArcsSet plus = split.getPlus();
			final org.apache.commons.math3.geometry.spherical.oned.ArcsSet minus = split.getMinus();
			return new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.twod.Sphere2D>((plus == null ? null : new org.apache.commons.math3.geometry.spherical.twod.SubCircle(thisCircle.copySelf() , plus)) , (minus == null ? null : new org.apache.commons.math3.geometry.spherical.twod.SubCircle(thisCircle.copySelf() , minus)));
		}
	}
}

