package org.apache.commons.math3.geometry.spherical.oned;


public class SubLimitAngle extends org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> {
	public SubLimitAngle(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> hyperplane ,final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> remainingRegion) {
		super(hyperplane, remainingRegion);
	}

	@java.lang.Override
	public double getSize() {
		return 0;
	}

	@java.lang.Override
	public boolean isEmpty() {
		return false;
	}

	@java.lang.Override
	protected org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D, org.apache.commons.math3.geometry.spherical.oned.Sphere1D> buildNew(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> hyperplane, final org.apache.commons.math3.geometry.partitioning.Region<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> remainingRegion) {
		return new org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle(hyperplane , remainingRegion);
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.Side side(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> hyperplane) {
		final double global = hyperplane.getOffset(((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(getHyperplane())).getLocation());
		return global < (-1.0E-10) ? org.apache.commons.math3.geometry.partitioning.Side.MINUS : global > 1.0E-10 ? org.apache.commons.math3.geometry.partitioning.Side.PLUS : org.apache.commons.math3.geometry.partitioning.Side.HYPER;
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> split(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D> hyperplane) {
		final double global = hyperplane.getOffset(((org.apache.commons.math3.geometry.spherical.oned.LimitAngle)(getHyperplane())).getLocation());
		return global < (-1.0E-10) ? new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(null , org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle.this) : new org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<org.apache.commons.math3.geometry.spherical.oned.Sphere1D>(org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle.this , null);
	}
}

