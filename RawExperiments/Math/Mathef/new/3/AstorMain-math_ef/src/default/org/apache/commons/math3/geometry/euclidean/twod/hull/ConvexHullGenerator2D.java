package org.apache.commons.math3.geometry.euclidean.twod.hull;


public interface ConvexHullGenerator2D extends org.apache.commons.math3.geometry.hull.ConvexHullGenerator<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> {
	org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D generate(java.util.Collection<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.NullArgumentException;
}

