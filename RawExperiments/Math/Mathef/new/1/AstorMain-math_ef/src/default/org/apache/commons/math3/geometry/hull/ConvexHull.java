package org.apache.commons.math3.geometry.hull;


public interface ConvexHull<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> extends java.io.Serializable {
	P[] getVertices();

	org.apache.commons.math3.geometry.partitioning.Region<S> createRegion() throws org.apache.commons.math3.exception.InsufficientDataException;
}

