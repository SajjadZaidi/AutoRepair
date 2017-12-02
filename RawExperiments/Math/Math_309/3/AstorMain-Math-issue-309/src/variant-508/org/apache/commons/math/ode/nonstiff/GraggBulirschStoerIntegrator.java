package org.apache.commons.math.ode.nonstiff;


public class GraggBulirschStoerIntegrator extends org.apache.commons.math.ode.nonstiff.AdaptiveStepsizeIntegrator {
    private static final java.lang.String METHOD_NAME = "Gragg-Bulirsch-Stoer";

    private int maxOrder;

    private int[] sequence;

    private int[] costPerStep;

    private double[] costPerTimeUnit;

    private double[] optimalStep;

    private double[][] coeff;

    private boolean performTest;

    private int maxChecks;

    private int maxIter;

    private double stabilityReduction;

    private double stepControl1;

    private double stepControl2;

    private double stepControl3;

    private double stepControl4;

    private double orderControl1;

    private double orderControl2;

    private boolean denseOutput;

    private boolean useInterpolationError;

    private int mudif;

    public GraggBulirschStoerIntegrator(final double minStep ,final double maxStep ,final double scalAbsoluteTolerance ,final double scalRelativeTolerance) {
        super(org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.METHOD_NAME, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        denseOutput = (requiresDenseOutput()) || (!(eventsHandlersManager.isEmpty()));
        setStabilityCheck(true, (-1), (-1), (-1));
        setStepsizeControl((-1), (-1), (-1), (-1));
        setOrderControl((-1), (-1), (-1));
        setInterpolationControl(true, (-1));
    }

    public GraggBulirschStoerIntegrator(final double minStep ,final double maxStep ,final double[] vecAbsoluteTolerance ,final double[] vecRelativeTolerance) {
        super(org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.METHOD_NAME, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        denseOutput = (requiresDenseOutput()) || (!(eventsHandlersManager.isEmpty()));
        setStabilityCheck(true, (-1), (-1), (-1));
        setStepsizeControl((-1), (-1), (-1), (-1));
        setOrderControl((-1), (-1), (-1));
        setInterpolationControl(true, (-1));
    }

    public void setStabilityCheck(final boolean performStabilityCheck, final int maxNumIter, final int maxNumChecks, final double stepsizeReductionFactor) {
        org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.performTest = performStabilityCheck;
        org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.maxIter = maxNumIter <= 0 ? 2 : maxNumIter;
        org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.maxChecks = maxNumChecks <= 0 ? 1 : maxNumChecks;
        if ((stepsizeReductionFactor < 1.0E-4) || (stepsizeReductionFactor > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stabilityReduction = 0.5;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stabilityReduction = stepsizeReductionFactor;
        }
    }

    public void setStepsizeControl(final double control1, final double control2, final double control3, final double control4) {
        if ((control1 < 1.0E-4) || (control1 > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl1 = 0.65;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl1 = control1;
        }
        if ((control2 < 1.0E-4) || (control2 > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl2 = 0.94;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl2 = control2;
        }
        if ((control3 < 1.0E-4) || (control3 > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl3 = 0.02;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl3 = control3;
        }
        if ((control4 < 1.0001) || (control4 > 999.9)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl4 = 4.0;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.stepControl4 = control4;
        }
    }

    public void setOrderControl(final int maximalOrder, final double control1, final double control2) {
        if ((maximalOrder <= 6) || ((maximalOrder % 2) != 0)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.maxOrder = 18;
        } 
        if ((control1 < 1.0E-4) || (control1 > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.orderControl1 = 0.8;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.orderControl1 = control1;
        }
        if ((control2 < 1.0E-4) || (control2 > 0.9999)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.orderControl2 = 0.9;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.orderControl2 = control2;
        }
        initializeArrays();
    }

    @java.lang.Override
    public void addStepHandler(final org.apache.commons.math.ode.sampling.StepHandler handler) {
        super.addStepHandler(handler);
        denseOutput = (requiresDenseOutput()) || (!(eventsHandlersManager.isEmpty()));
        initializeArrays();
    }

    @java.lang.Override
    public void addEventHandler(final org.apache.commons.math.ode.events.EventHandler function, final double maxCheckInterval, final double convergence, final int maxIterationCount) {
        super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount);
        denseOutput = (requiresDenseOutput()) || (!(eventsHandlersManager.isEmpty()));
        initializeArrays();
    }

    private void initializeArrays() {
        final int size = (maxOrder) / 2;
        if (((sequence) == null) || ((sequence.length) != size)) {
            sequence = new int[size];
            costPerStep = new int[size];
            coeff = new double[size][];
            costPerTimeUnit = new double[size];
            optimalStep = new double[size];
        } 
        if (denseOutput) {
            for (int k = 0 ; k < size ; ++k) {
                sequence[k] = (4 * k) + 2;
            }
        } else {
            for (int k = 0 ; k < size ; ++k) {
                sequence[k] = 2 * (k + 1);
            }
        }
        costPerStep[0] = (sequence[0]) + 1;
        for (int k = 1 ; k < size ; ++k) {
            costPerStep[k] = (costPerStep[(k - 1)]) + (sequence[k]);
        }
        for (int k = 0 ; k < size ; ++k) {
            coeff[k] = k > 0 ? new double[k] : null;
            for (int l = 0 ; l < k ; ++l) {
                final double ratio = ((double)(sequence[k])) / (sequence[((k - l) - 1)]);
                coeff[k][l] = 1.0 / ((ratio * ratio) - 1.0);
            }
        }
    }

    public void setInterpolationControl(final boolean useInterpolationErrorForControl, final int mudifControlParameter) {
        org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.useInterpolationError = useInterpolationErrorForControl;
        if ((mudifControlParameter <= 0) || (mudifControlParameter >= 7)) {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.mudif = 4;
        } else {
            org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator.this.mudif = mudifControlParameter;
        }
    }

    private void rescale(final double[] y1, final double[] y2, final double[] scale) {
        if ((vecAbsoluteTolerance) == null) {
            for (int i = 0 ; i < (scale.length) ; ++i) {
                final double yi = java.lang.Math.max(java.lang.Math.abs(y1[i]), java.lang.Math.abs(y2[i]));
                scale[i] = (scalAbsoluteTolerance) + ((scalRelativeTolerance) * yi);
            }
        } else {
            for (int i = 0 ; i < (scale.length) ; ++i) {
                final double yi = java.lang.Math.max(java.lang.Math.abs(y1[i]), java.lang.Math.abs(y2[i]));
                scale[i] = (vecAbsoluteTolerance[i]) + ((vecRelativeTolerance[i]) * yi);
            }
        }
    }

    private boolean tryStep(final double t0, final double[] y0, final double step, final int k, final double[] scale, final double[][] f, final double[] yMiddle, final double[] yEnd, final double[] yTmp) throws org.apache.commons.math.ode.DerivativeException {
        final int n = sequence[k];
        final double subStep = step / n;
        final double subStep2 = 2 * subStep;
        double t = t0 + subStep;
        for (int i = 0 ; i < (y0.length) ; ++i) {
            yTmp[i] = y0[i];
            yEnd[i] = (y0[i]) + (subStep * (f[0][i]));
        }
        computeDerivatives(t, yEnd, f[1]);
        for (int j = 1 ; j < n ; ++j) {
            if ((2 * j) == n) {
                java.lang.System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
            } 
            t += subStep;
            for (int i = 0 ; i < (y0.length) ; ++i) {
                final double middle = yEnd[i];
                yEnd[i] = (yTmp[i]) + (subStep2 * (f[j][i]));
                yTmp[i] = middle;
            }
            computeDerivatives(t, yEnd, f[(j + 1)]);
            if (((performTest) && (j <= (maxChecks))) && (k < (maxIter))) {
                double initialNorm = 0.0;
                for (int l = 0 ; l < (y0.length) ; ++l) {
                    final double ratio = (f[0][l]) / (scale[l]);
                    initialNorm += ratio * ratio;
                }
                double deltaNorm = 0.0;
                for (int l = 0 ; l < (y0.length) ; ++l) {
                    final double ratio = ((f[(j + 1)][l]) - (f[0][l])) / (scale[l]);
                    deltaNorm += ratio * ratio;
                }
                if (deltaNorm > (4 * (java.lang.Math.max(1.0E-15, initialNorm)))) {
                    return false;
                } 
            } 
        }
        for (int i = 0 ; i < (y0.length) ; ++i) {
            yEnd[i] = 0.5 * (((yTmp[i]) + (yEnd[i])) + (subStep * (f[n][i])));
        }
        return true;
    }

    private void extrapolate(final int offset, final int k, final double[][] diag, final double[] last) {
        for (int j = 1 ; j < k ; ++j) {
            for (int i = 0 ; i < (last.length) ; ++i) {
                diag[((k - j) - 1)][i] = (diag[(k - j)][i]) + ((coeff[(k + offset)][(j - 1)]) * ((diag[(k - j)][i]) - (diag[((k - j) - 1)][i])));
            }
        }
        for (int i = 0 ; i < (last.length) ; ++i) {
            last[i] = (diag[0][i]) + ((coeff[(k + offset)][(k - 1)]) * ((diag[0][i]) - (last[i])));
        }
    }

    @java.lang.Override
    public double integrate(final org.apache.commons.math.ode.FirstOrderDifferentialEquations equations, final double t0, final double[] y0, final double t, final double[] y) throws org.apache.commons.math.ode.DerivativeException, org.apache.commons.math.ode.IntegratorException {
        sanityChecks(equations, t0, y0, t, y);
        setEquations(equations);
        resetEvaluations();
        final boolean forward = t > t0;
        final double[] yDot0 = new double[y0.length];
        final double[] y1 = new double[y0.length];
        final double[] yTmp = new double[y0.length];
        final double[] yTmpDot = new double[y0.length];
        final double[][] diagonal = new double[(sequence.length) - 1][];
        final double[][] y1Diag = new double[(sequence.length) - 1][];
        for (int k = 0 ; k < ((sequence.length) - 1) ; ++k) {
            diagonal[k] = new double[y0.length];
            y1Diag[k] = new double[y0.length];
        }
        final double[][][] fk = new double[sequence.length][][];
        for (int k = 0 ; k < (sequence.length) ; ++k) {
            fk[k] = new double[(sequence[k]) + 1][];
            fk[k][0] = yDot0;
            for (int l = 0 ; l < (sequence[k]) ; ++l) {
                fk[k][(l + 1)] = new double[y0.length];
            }
        }
        if (y != y0) {
            java.lang.System.arraycopy(y0, 0, y, 0, y0.length);
        } 
        double[] yDot1 = null;
        double[][] yMidDots = null;
        if (denseOutput) {
            yDot1 = new double[y0.length];
            yMidDots = new double[1 + (2 * (sequence.length))][];
            for (int j = 0 ; j < (yMidDots.length) ; ++j) {
                yMidDots[j] = new double[y0.length];
            }
        } else {
            yMidDots = new double[1][];
            yMidDots[0] = new double[y0.length];
        }
        final double[] scale = new double[y0.length];
        rescale(y, y, scale);
        final double tol = (vecRelativeTolerance) == null ? scalRelativeTolerance : vecRelativeTolerance[0];
        final double log10R = (java.lang.Math.log(java.lang.Math.max(1.0E-10, tol))) / (java.lang.Math.log(10.0));
        int targetIter = java.lang.Math.max(1, java.lang.Math.min(((sequence.length) - 2), ((int)(java.lang.Math.floor((0.5 - (0.6 * log10R)))))));
        org.apache.commons.math.ode.sampling.AbstractStepInterpolator interpolator = null;
        if ((denseOutput) || (!(eventsHandlersManager.isEmpty()))) {
            interpolator = new org.apache.commons.math.ode.nonstiff.GraggBulirschStoerStepInterpolator(y , yDot0 , y1 , yDot1 , yMidDots , forward);
        } else {
            interpolator = new org.apache.commons.math.ode.sampling.DummyStepInterpolator(y , forward);
        }
        interpolator.storeTime(t0);
        stepStart = t0;
        double hNew = 0;
        double maxError = java.lang.Double.MAX_VALUE;
        boolean previousRejected = false;
        boolean firstTime = true;
        boolean newStep = true;
        boolean lastStep = false;
        boolean firstStepAlreadyComputed = false;
        for (org.apache.commons.math.ode.sampling.StepHandler handler : stepHandlers) {
            handler.reset();
        }
        costPerTimeUnit[0] = 0;
        return stepStart;
    }
}

