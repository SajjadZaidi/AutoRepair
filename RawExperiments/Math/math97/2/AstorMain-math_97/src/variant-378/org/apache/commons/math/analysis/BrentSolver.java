package org.apache.commons.math.analysis;


public class BrentSolver extends org.apache.commons.math.analysis.UnivariateRealSolverImpl {
    private static final long serialVersionUID = -2136672307739067002L;

    public BrentSolver(org.apache.commons.math.analysis.UnivariateRealFunction f) {
        super(f, 100, 1.0E-6);
    }

    public double solve(double min, double max, double initial) throws org.apache.commons.math.FunctionEvaluationException, org.apache.commons.math.MaxIterationsExceededException {
        if (((initial - min) * (max - initial)) < 0) {
            throw new java.lang.IllegalArgumentException(((((((("Initial guess is not in search" + (" interval." + "  Initial: ")) + initial) + "  Endpoints: [") + min) + ",") + max) + "]"));
        } 
        double yInitial = f.value(initial);
        if ((java.lang.Math.abs(yInitial)) <= (functionValueAccuracy)) {
            setResult(initial, 0);
            return result;
        } 
        double yMin = f.value(min);
        if ((java.lang.Math.abs(yMin)) <= (functionValueAccuracy)) {
            setResult(yMin, 0);
            return result;
        } 
        if ((yInitial * yMin) < 0) {
            return solve(min, yMin, initial, yInitial, min, yMin);
        } 
        double yMax = f.value(max);
        if ((java.lang.Math.abs(yMax)) <= (functionValueAccuracy)) {
            setResult(yMax, 0);
            return result;
        } 
        if ((yInitial * yMax) < 0) {
            return solve(initial, yInitial, max, yMax, initial, yInitial);
        } 
        return solve(min, yMin, max, yMax, initial, yInitial);
    }

    public double solve(double min, double max) throws org.apache.commons.math.FunctionEvaluationException, org.apache.commons.math.MaxIterationsExceededException {
        clearResult();
        verifyInterval(min, max);
        double ret = java.lang.Double.NaN;
        double yMin = f.value(min);
        double yMax = f.value(max);
        double sign = yMin * yMax;
        if (sign >= 0) {
            throw new java.lang.IllegalArgumentException((((((((((("Function values at endpoints do not have different signs." + "  Endpoints: [") + min) + ",") + max) + "]") + "  Values: [") + yMin) + ",") + yMax) + "]"));
        } else {
            ret = solve(min, yMin, max, yMax, min, yMin);
        }
        return ret;
    }

    private double solve(double x0, double y0, double x1, double y1, double x2, double y2) throws org.apache.commons.math.FunctionEvaluationException, org.apache.commons.math.MaxIterationsExceededException {
        double delta = x1 - x0;
        double oldDelta = delta;
        int i = 0;
        throw new org.apache.commons.math.MaxIterationsExceededException(maximalIterationCount);
    }
}

