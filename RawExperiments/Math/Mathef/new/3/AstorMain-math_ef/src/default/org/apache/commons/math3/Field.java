package org.apache.commons.math3;


public interface Field<T> {
	T getZero();

	T getOne();

	java.lang.Class<? extends org.apache.commons.math3.FieldElement<T>> getRuntimeClass();
}

