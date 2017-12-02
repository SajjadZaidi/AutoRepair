package org.apache.commons.math3.geometry.enclosing;


public interface SupportBallGenerator<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> {
	org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> ballOnSupport(java.util.List<P> support);
}

