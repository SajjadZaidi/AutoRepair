package org.apache.commons.math3.linear;


public interface DecompositionSolver {
	org.apache.commons.math3.linear.RealVector solve(final org.apache.commons.math3.linear.RealVector b) throws org.apache.commons.math3.linear.SingularMatrixException;

	org.apache.commons.math3.linear.RealMatrix solve(final org.apache.commons.math3.linear.RealMatrix b) throws org.apache.commons.math3.linear.SingularMatrixException;

	boolean isNonSingular();

	org.apache.commons.math3.linear.RealMatrix getInverse() throws org.apache.commons.math3.linear.SingularMatrixException;
}

