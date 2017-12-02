package org.apache.commons.math3.geometry.enclosing;


public interface Encloser<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> {
	org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> enclose(java.lang.Iterable<P> points);
}

