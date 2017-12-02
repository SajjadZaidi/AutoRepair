package org.apache.commons.math3.geometry;


public interface Vector<S extends org.apache.commons.math3.geometry.Space> extends org.apache.commons.math3.geometry.Point<S> {
	org.apache.commons.math3.geometry.Vector<S> getZero();

	double getNorm1();

	double getNorm();

	double getNormSq();

	double getNormInf();

	org.apache.commons.math3.geometry.Vector<S> add(org.apache.commons.math3.geometry.Vector<S> v);

	org.apache.commons.math3.geometry.Vector<S> add(double factor, org.apache.commons.math3.geometry.Vector<S> v);

	org.apache.commons.math3.geometry.Vector<S> subtract(org.apache.commons.math3.geometry.Vector<S> v);

	org.apache.commons.math3.geometry.Vector<S> subtract(double factor, org.apache.commons.math3.geometry.Vector<S> v);

	org.apache.commons.math3.geometry.Vector<S> negate();

	org.apache.commons.math3.geometry.Vector<S> normalize() throws org.apache.commons.math3.exception.MathArithmeticException;

	org.apache.commons.math3.geometry.Vector<S> scalarMultiply(double a);

	boolean isInfinite();

	double distance1(org.apache.commons.math3.geometry.Vector<S> v);

	double distance(org.apache.commons.math3.geometry.Vector<S> v);

	double distanceInf(org.apache.commons.math3.geometry.Vector<S> v);

	double distanceSq(org.apache.commons.math3.geometry.Vector<S> v);

	double dotProduct(org.apache.commons.math3.geometry.Vector<S> v);

	java.lang.String toString(final java.text.NumberFormat format);
}

