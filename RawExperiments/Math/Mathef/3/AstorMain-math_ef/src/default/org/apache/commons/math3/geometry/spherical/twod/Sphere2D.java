package org.apache.commons.math3.geometry.spherical.twod;


public class Sphere2D implements java.io.Serializable , org.apache.commons.math3.geometry.Space {
	private static final long serialVersionUID = 20131218L;

	private Sphere2D() {
	}

	public static org.apache.commons.math3.geometry.spherical.twod.Sphere2D getInstance() {
		return org.apache.commons.math3.geometry.spherical.twod.Sphere2D.LazyHolder.INSTANCE;
	}

	public int getDimension() {
		return 2;
	}

	public org.apache.commons.math3.geometry.spherical.oned.Sphere1D getSubSpace() {
		return org.apache.commons.math3.geometry.spherical.oned.Sphere1D.getInstance();
	}

	private static class LazyHolder {
		private static final org.apache.commons.math3.geometry.spherical.twod.Sphere2D INSTANCE = new org.apache.commons.math3.geometry.spherical.twod.Sphere2D();
	}

	private java.lang.Object readResolve() {
		return org.apache.commons.math3.geometry.spherical.twod.Sphere2D.LazyHolder.INSTANCE;
	}
}

