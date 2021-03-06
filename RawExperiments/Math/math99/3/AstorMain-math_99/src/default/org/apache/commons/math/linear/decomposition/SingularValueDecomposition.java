package org.apache.commons.math.linear.decomposition;


public interface SingularValueDecomposition extends java.io.Serializable {
	org.apache.commons.math.linear.RealMatrix getU();

	org.apache.commons.math.linear.RealMatrix getUT();

	org.apache.commons.math.linear.RealMatrix getS();

	double[] getSingularValues();

	org.apache.commons.math.linear.RealMatrix getV();

	org.apache.commons.math.linear.RealMatrix getVT();

	double getNorm();

	double getConditionNumber();

	int getRank();

	org.apache.commons.math.linear.decomposition.DecompositionSolver getSolver();
}

