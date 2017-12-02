package org.apache.commons.math3.optim.linear;


public class SolutionCallback implements org.apache.commons.math3.optim.OptimizationData {
	private org.apache.commons.math3.optim.linear.SimplexTableau tableau;

	void setTableau(final org.apache.commons.math3.optim.linear.SimplexTableau tableau) {
		org.apache.commons.math3.optim.linear.SolutionCallback.this.tableau = tableau;
	}

	public org.apache.commons.math3.optim.PointValuePair getSolution() {
		return (tableau) != null ? tableau.getSolution() : null;
	}

	public boolean isSolutionOptimal() {
		return (tableau) != null ? tableau.isOptimal() : false;
	}
}

