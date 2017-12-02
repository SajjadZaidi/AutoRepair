package org.apache.commons.math3.linear;


public interface FieldVectorChangingVisitor<T extends org.apache.commons.math3.FieldElement<?>> {
	void start(int dimension, int start, int end);

	T visit(int index, T value);

	T end();
}

