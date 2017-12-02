package org.apache.commons.math3.linear;


public interface FieldVectorPreservingVisitor<T extends org.apache.commons.math3.FieldElement<?>> {
	void start(int dimension, int start, int end);

	void visit(int index, T value);

	T end();
}

