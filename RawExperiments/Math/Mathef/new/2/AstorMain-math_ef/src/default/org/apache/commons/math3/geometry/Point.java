package org.apache.commons.math3.geometry;


public interface Point<S extends org.apache.commons.math3.geometry.Space> extends java.io.Serializable {
	org.apache.commons.math3.geometry.Space getSpace();

	boolean isNaN();

	double distance(org.apache.commons.math3.geometry.Point<S> p);
}

