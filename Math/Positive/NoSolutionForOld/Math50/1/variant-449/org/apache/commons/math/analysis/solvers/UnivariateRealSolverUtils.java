package org.apache.commons.math.analysis.solvers;


public class UnivariateRealSolverUtils {
    private UnivariateRealSolverUtils() {
    }

    public static double solve(org.apache.commons.math.analysis.UnivariateRealFunction function, double x0, double x1) {
        if (function == null) {
            throw new org.apache.commons.math.exception.NullArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.FUNCTION);
        } 
        final org.apache.commons.math.analysis.solvers.UnivariateRealSolver solver = new org.apache.commons.math.analysis.solvers.BrentSolver();
        return solver.solve(java.lang.Integer.MAX_VALUE, function, x0, x1);
    }

    public static double solve(org.apache.commons.math.analysis.UnivariateRealFunction function, double x0, double x1, double absoluteAccuracy) {
        if (function == null) {
            throw new org.apache.commons.math.exception.NullArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.FUNCTION);
        } 
        final org.apache.commons.math.analysis.solvers.UnivariateRealSolver solver = new org.apache.commons.math.analysis.solvers.BrentSolver(absoluteAccuracy);
        return solver.solve(java.lang.Integer.MAX_VALUE, function, x0, x1);
    }

    public static double forceSide(final int maxEval, final org.apache.commons.math.analysis.UnivariateRealFunction f, final org.apache.commons.math.analysis.solvers.BracketedUnivariateRealSolver<org.apache.commons.math.analysis.UnivariateRealFunction> bracketing, final double baseRoot, final double min, final double max, final org.apache.commons.math.analysis.solvers.AllowedSolution allowedSolution) {
        if (allowedSolution == (org.apache.commons.math.analysis.solvers.AllowedSolution.ANY_SIDE)) {
            return baseRoot;
        } 
        final double step = org.apache.commons.math.util.FastMath.max(bracketing.getAbsoluteAccuracy(), org.apache.commons.math.util.FastMath.abs((baseRoot * (bracketing.getRelativeAccuracy()))));
        double xLo = org.apache.commons.math.util.FastMath.max(min, (baseRoot - step));
        double fLo = f.value(xLo);
        double xHi = org.apache.commons.math.util.FastMath.min(max, (baseRoot + step));
        double fHi = f.value(xHi);
        int remainingEval = maxEval - 2;
        while (remainingEval > 0) {
            if (((fLo >= 0) && (fHi <= 0)) || ((fLo <= 0) && (fHi >= 0))) {
                return bracketing.solve(remainingEval, f, xLo, xHi, baseRoot, allowedSolution);
            } 
            boolean changeLo = false;
            boolean changeHi = false;
            if (fLo < fHi) {
                if (fLo >= 0) {
                    changeLo = true;
                } else {
                    changeHi = true;
                }
            } else {
                if (fLo > fHi) {
                    if (fLo <= 0) {
                        changeLo = true;
                    } else {
                        changeHi = true;
                    }
                } else {
                    changeLo = true;
                    changeHi = true;
                }
            }
            if (changeLo) {
                xLo = org.apache.commons.math.util.FastMath.max(min, (xLo - step));
                fLo = f.value(xLo);
                remainingEval--;
            } 
            if (changeHi) {
                xHi = org.apache.commons.math.util.FastMath.min(max, (xHi + step));
                fHi = f.value(xHi);
                remainingEval--;
            } 
        }
        throw new org.apache.commons.math.exception.NoBracketingException(org.apache.commons.math.exception.util.LocalizedFormats.FAILED_BRACKETING , xLo , xHi , fLo , fHi , (maxEval - remainingEval) , maxEval , baseRoot , min , max);
    }

    public static double[] bracket(org.apache.commons.math.analysis.UnivariateRealFunction function, double initial, double lowerBound, double upperBound) {
        return org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.bracket(function, initial, lowerBound, upperBound, java.lang.Integer.MAX_VALUE);
    }

    public static double[] bracket(org.apache.commons.math.analysis.UnivariateRealFunction function, double initial, double lowerBound, double upperBound, int maximumIterations) {
        if (function == null) {
            throw new org.apache.commons.math.exception.NullArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.FUNCTION);
        } 
        if (maximumIterations <= 0) {
            throw new org.apache.commons.math.exception.NotStrictlyPositiveException(org.apache.commons.math.exception.util.LocalizedFormats.INVALID_MAX_ITERATIONS , maximumIterations);
        } 
        org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.verifySequence(lowerBound, initial, upperBound);
        double a = initial;
        double b = initial;
        double fa;
        double fb;
        int numIterations = 0;
        do {
            a = org.apache.commons.math.util.FastMath.max((a - 1.0), lowerBound);
            b = org.apache.commons.math.util.FastMath.min((b + 1.0), upperBound);
            fa = function.value(a);
            fb = function.value(b);
            ++numIterations;
        } while ((((fa * fb) > 0.0) && (numIterations < maximumIterations)) && ((a > lowerBound) || (b < upperBound)) );
        if ((fa * fb) > 0.0) {
            throw new org.apache.commons.math.exception.NoBracketingException(org.apache.commons.math.exception.util.LocalizedFormats.FAILED_BRACKETING , a , b , fa , fb , numIterations , maximumIterations , initial , lowerBound , upperBound);
        } 
        return new double[]{ a , b };
    }

    public static double midpoint(double a, double b) {
        return (a + b) * 0.5;
    }

    public static boolean isBracketing(org.apache.commons.math.analysis.UnivariateRealFunction function, final double lower, final double upper) {
        if (function == null) {
            throw new org.apache.commons.math.exception.NullArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.FUNCTION);
        } 
        final double fLo = function.value(lower);
        final double fHi = function.value(upper);
        return ((fLo >= 0) && (fHi <= 0)) || ((fLo <= 0) && (fHi >= 0));
    }

    public static boolean isSequence(final double start, final double mid, final double end) {
        return (start < mid) && (mid < end);
    }

    public static void verifyInterval(final double lower, final double upper) {
        if (lower >= upper) {
            throw new org.apache.commons.math.exception.NumberIsTooLargeException(org.apache.commons.math.exception.util.LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL , lower , upper , false);
        } 
    }

    public static void verifySequence(final double lower, final double initial, final double upper) {
        org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.verifyInterval(lower, initial);
        org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.verifyInterval(initial, upper);
    }

    public static void verifyBracketing(org.apache.commons.math.analysis.UnivariateRealFunction function, final double lower, final double upper) {
        if (function == null) {
            throw new org.apache.commons.math.exception.NullArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.FUNCTION);
        } 
        org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.verifyInterval(lower, upper);
        if (!(org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils.isBracketing(function, lower, upper))) {
            throw new org.apache.commons.math.exception.NoBracketingException(lower , upper , function.value(lower) , function.value(upper));
        } 
    }
}

