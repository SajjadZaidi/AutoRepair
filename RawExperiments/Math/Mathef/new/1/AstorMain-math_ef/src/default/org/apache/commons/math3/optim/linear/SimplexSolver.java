package org.apache.commons.math3.optim.linear;


public class SimplexSolver extends org.apache.commons.math3.optim.linear.LinearOptimizer {
	static final int DEFAULT_ULPS = 10;

	static final double DEFAULT_CUT_OFF = 1.0E-10;

	private static final double DEFAULT_EPSILON = 1.0E-6;

	private final double epsilon;

	private final int maxUlps;

	private final double cutOff;

	private org.apache.commons.math3.optim.linear.PivotSelectionRule pivotSelection;

	private org.apache.commons.math3.optim.linear.SolutionCallback solutionCallback;

	public SimplexSolver() {
		this(org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_EPSILON, org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_ULPS, org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_CUT_OFF);
	}

	public SimplexSolver(final double epsilon) {
		this(epsilon, org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_ULPS, org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_CUT_OFF);
	}

	public SimplexSolver(final double epsilon ,final int maxUlps) {
		this(epsilon, maxUlps, org.apache.commons.math3.optim.linear.SimplexSolver.DEFAULT_CUT_OFF);
	}

	public SimplexSolver(final double epsilon ,final int maxUlps ,final double cutOff) {
		this.epsilon = epsilon;
		this.maxUlps = maxUlps;
		this.cutOff = cutOff;
		org.apache.commons.math3.optim.linear.SimplexSolver.this.pivotSelection = org.apache.commons.math3.optim.linear.PivotSelectionRule.DANTZIG;
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) throws org.apache.commons.math3.exception.TooManyIterationsException {
		return super.optimize(optData);
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		solutionCallback = null;
		for (org.apache.commons.math3.optim.OptimizationData data : optData) {
			if (data instanceof org.apache.commons.math3.optim.linear.SolutionCallback) {
				solutionCallback = ((org.apache.commons.math3.optim.linear.SolutionCallback)(data));
				continue;
			} 
			if (data instanceof org.apache.commons.math3.optim.linear.PivotSelectionRule) {
				pivotSelection = ((org.apache.commons.math3.optim.linear.PivotSelectionRule)(data));
				continue;
			} 
		}
	}

	private java.lang.Integer getPivotColumn(org.apache.commons.math3.optim.linear.SimplexTableau tableau) {
		double minValue = 0;
		java.lang.Integer minPos = null;
		for (int i = tableau.getNumObjectiveFunctions() ; i < ((tableau.getWidth()) - 1) ; i++) {
			final double entry = tableau.getEntry(0, i);
			if (entry < minValue) {
				minValue = entry;
				minPos = i;
				if (((pivotSelection) == (org.apache.commons.math3.optim.linear.PivotSelectionRule.BLAND)) && (isValidPivotColumn(tableau, i))) {
					break;
				} 
			} 
		}
		return minPos;
	}

	private boolean isValidPivotColumn(org.apache.commons.math3.optim.linear.SimplexTableau tableau, int col) {
		for (int i = tableau.getNumObjectiveFunctions() ; i < (tableau.getHeight()) ; i++) {
			final double entry = tableau.getEntry(i, col);
			if ((org.apache.commons.math3.util.Precision.compareTo(entry, 0.0, cutOff)) > 0) {
				return true;
			} 
		}
		return false;
	}

	private java.lang.Integer getPivotRow(org.apache.commons.math3.optim.linear.SimplexTableau tableau, final int col) {
		java.util.List<java.lang.Integer> minRatioPositions = new java.util.ArrayList<java.lang.Integer>();
		double minRatio = java.lang.Double.MAX_VALUE;
		for (int i = tableau.getNumObjectiveFunctions() ; i < (tableau.getHeight()) ; i++) {
			final double rhs = tableau.getEntry(i, ((tableau.getWidth()) - 1));
			final double entry = tableau.getEntry(i, col);
			if ((org.apache.commons.math3.util.Precision.compareTo(entry, 0.0, cutOff)) > 0) {
				final double ratio = org.apache.commons.math3.util.FastMath.abs((rhs / entry));
				final int cmp = java.lang.Double.compare(ratio, minRatio);
				if (cmp == 0) {
					minRatioPositions.add(i);
				} else if (cmp < 0) {
					minRatio = ratio;
					minRatioPositions.clear();
					minRatioPositions.add(i);
				} 
			} 
		}
		if ((minRatioPositions.size()) == 0) {
			return null;
		} else if ((minRatioPositions.size()) > 1) {
			if ((tableau.getNumArtificialVariables()) > 0) {
				for (java.lang.Integer row : minRatioPositions) {
					for (int i = 0 ; i < (tableau.getNumArtificialVariables()) ; i++) {
						int column = i + (tableau.getArtificialVariableOffset());
						final double entry = tableau.getEntry(row, column);
						if ((org.apache.commons.math3.util.Precision.equals(entry, 1.0, maxUlps)) && (row.equals(tableau.getBasicRow(column)))) {
							return row;
						} 
					}
				}
			} 
			java.lang.Integer minRow = null;
			int minIndex = tableau.getWidth();
			for (java.lang.Integer row : minRatioPositions) {
				final int basicVar = tableau.getBasicVariable(row);
				if (basicVar < minIndex) {
					minIndex = basicVar;
					minRow = row;
				} 
			}
			return minRow;
		} 
		return minRatioPositions.get(0);
	}

	protected void doIteration(final org.apache.commons.math3.optim.linear.SimplexTableau tableau) throws org.apache.commons.math3.exception.TooManyIterationsException, org.apache.commons.math3.optim.linear.UnboundedSolutionException {
		incrementIterationCount();
		java.lang.Integer pivotCol = getPivotColumn(tableau);
		java.lang.Integer pivotRow = getPivotRow(tableau, pivotCol);
		if (pivotRow == null) {
			throw new org.apache.commons.math3.optim.linear.UnboundedSolutionException();
		} 
		tableau.performRowOperations(pivotCol, pivotRow);
	}

	protected void solvePhase1(final org.apache.commons.math3.optim.linear.SimplexTableau tableau) throws org.apache.commons.math3.exception.TooManyIterationsException, org.apache.commons.math3.optim.linear.NoFeasibleSolutionException, org.apache.commons.math3.optim.linear.UnboundedSolutionException {
		if ((tableau.getNumArtificialVariables()) == 0) {
			return ;
		} 
		while (!(tableau.isOptimal())) {
			doIteration(tableau);
		}
		if (!(org.apache.commons.math3.util.Precision.equals(tableau.getEntry(0, tableau.getRhsOffset()), 0.0, epsilon))) {
			throw new org.apache.commons.math3.optim.linear.NoFeasibleSolutionException();
		} 
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointValuePair doOptimize() throws org.apache.commons.math3.exception.TooManyIterationsException, org.apache.commons.math3.optim.linear.NoFeasibleSolutionException, org.apache.commons.math3.optim.linear.UnboundedSolutionException {
		if ((solutionCallback) != null) {
			solutionCallback.setTableau(null);
		} 
		final org.apache.commons.math3.optim.linear.SimplexTableau tableau = new org.apache.commons.math3.optim.linear.SimplexTableau(getFunction() , getConstraints() , getGoalType() , isRestrictedToNonNegative() , epsilon , maxUlps);
		solvePhase1(tableau);
		tableau.dropPhase1Objective();
		if ((solutionCallback) != null) {
			solutionCallback.setTableau(tableau);
		} 
		while (!(tableau.isOptimal())) {
			doIteration(tableau);
		}
		final org.apache.commons.math3.optim.PointValuePair solution = tableau.getSolution();
		if (isRestrictedToNonNegative()) {
			final double[] coeff = solution.getPoint();
			for (int i = 0 ; i < (coeff.length) ; i++) {
				if ((org.apache.commons.math3.util.Precision.compareTo(coeff[i], 0, epsilon)) < 0) {
					throw new org.apache.commons.math3.optim.linear.NoFeasibleSolutionException();
				} 
			}
		} 
		return solution;
	}
}

