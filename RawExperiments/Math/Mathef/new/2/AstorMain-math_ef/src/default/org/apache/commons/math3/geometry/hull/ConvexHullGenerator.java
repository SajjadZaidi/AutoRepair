package org.apache.commons.math3.geometry.hull;


public interface ConvexHullGenerator<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> {
	org.apache.commons.math3.geometry.hull.ConvexHull<S, P> generate(java.util.Collection<P> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.NullArgumentException;
}

