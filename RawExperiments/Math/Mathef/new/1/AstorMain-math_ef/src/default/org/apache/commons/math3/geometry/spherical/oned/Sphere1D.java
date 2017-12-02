package org.apache.commons.math3.geometry.spherical.oned;


public class Sphere1D implements java.io.Serializable , org.apache.commons.math3.geometry.Space {
	private static final long serialVersionUID = 20131218L;

	private Sphere1D() {
	}

	public static org.apache.commons.math3.geometry.spherical.oned.Sphere1D getInstance() {
		return org.apache.commons.math3.geometry.spherical.oned.Sphere1D.LazyHolder.INSTANCE;
	}

	public int getDimension() {
		return 1;
	}

	public org.apache.commons.math3.geometry.Space getSubSpace() throws org.apache.commons.math3.geometry.spherical.oned.Sphere1D.NoSubSpaceException {
		throw new org.apache.commons.math3.geometry.spherical.oned.Sphere1D.NoSubSpaceException();
	}

	private static class LazyHolder {
		private static final org.apache.commons.math3.geometry.spherical.oned.Sphere1D INSTANCE = new org.apache.commons.math3.geometry.spherical.oned.Sphere1D();
	}

	private java.lang.Object readResolve() {
		return org.apache.commons.math3.geometry.spherical.oned.Sphere1D.LazyHolder.INSTANCE;
	}

	public static class NoSubSpaceException extends org.apache.commons.math3.exception.MathUnsupportedOperationException {
		private static final long serialVersionUID = 20140225L;

		public NoSubSpaceException() {
			super(org.apache.commons.math3.exception.util.LocalizedFormats.NOT_SUPPORTED_IN_DIMENSION_N, 1);
		}
	}
}

