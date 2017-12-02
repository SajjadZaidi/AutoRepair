package org.apache.commons.math3;


public interface RealFieldElement<T> extends org.apache.commons.math3.FieldElement<T> {
	double getReal();

	T add(double a);

	T subtract(double a);

	T multiply(double a);

	T divide(double a);

	T remainder(double a);

	T remainder(T a) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T abs();

	T ceil();

	T floor();

	T rint();

	long round();

	T signum();

	T copySign(T sign);

	T copySign(double sign);

	T scalb(int n);

	T hypot(T y) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T reciprocal();

	T sqrt();

	T cbrt();

	T rootN(int n);

	T pow(double p);

	T pow(int n);

	T pow(T e) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T exp();

	T expm1();

	T log();

	T log1p();

	T cos();

	T sin();

	T tan();

	T acos();

	T asin();

	T atan();

	T atan2(T x) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T cosh();

	T sinh();

	T tanh();

	T acosh();

	T asinh();

	T atanh();

	T linearCombination(T[] a, T[] b) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T linearCombination(double[] a, T[] b) throws org.apache.commons.math3.exception.DimensionMismatchException;

	T linearCombination(T a1, T b1, T a2, T b2);

	T linearCombination(double a1, T b1, double a2, T b2);

	T linearCombination(T a1, T b1, T a2, T b2, T a3, T b3);

	T linearCombination(double a1, T b1, double a2, T b2, double a3, T b3);

	T linearCombination(T a1, T b1, T a2, T b2, T a3, T b3, T a4, T b4);

	T linearCombination(double a1, T b1, double a2, T b2, double a3, T b3, double a4, T b4);
}

