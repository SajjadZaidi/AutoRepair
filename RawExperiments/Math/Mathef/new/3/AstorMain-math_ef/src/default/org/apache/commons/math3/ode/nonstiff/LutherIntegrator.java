package org.apache.commons.math3.ode.nonstiff;


public class LutherIntegrator extends org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator {
	private static final double Q = org.apache.commons.math3.util.FastMath.sqrt(21);

	private static final double[] STATIC_C = new double[]{ 1.0 , 1.0 / 2.0 , 2.0 / 3.0 , (7.0 - (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q)) / 14.0 , (7.0 + (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q)) / 14.0 , 1.0 };

	private static final double[][] STATIC_A = new double[][]{ new double[]{ 1.0 } , new double[]{ 3.0 / 8.0 , 1.0 / 8.0 } , new double[]{ 8.0 / 27.0 , 2.0 / 27.0 , 8.0 / 27.0 } , new double[]{ ((-21.0) + (9.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 392.0 , ((-56.0) + (8.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 392.0 , (336.0 - (48.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 392.0 , ((-63.0) + (3.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 392.0 } , new double[]{ ((-1155.0) - (255.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 1960.0 , ((-280.0) - (40.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 1960.0 , (0.0 - (320.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 1960.0 , (63.0 + (363.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 1960.0 , (2352.0 + (392.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 1960.0 } , new double[]{ (330.0 + (105.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 , (120.0 + (0.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 , ((-200.0) + (280.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 , (126.0 - (189.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 , ((-686.0) - (126.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 , (490.0 - (70.0 * (org.apache.commons.math3.ode.nonstiff.LutherIntegrator.Q))) / 180.0 } };

	private static final double[] STATIC_B = new double[]{ 1.0 / 20.0 , 0 , 16.0 / 45.0 , 0 , 49.0 / 180.0 , 49.0 / 180.0 , 1.0 / 20.0 };

	public LutherIntegrator(final double step) {
		super("Luther", org.apache.commons.math3.ode.nonstiff.LutherIntegrator.STATIC_C, org.apache.commons.math3.ode.nonstiff.LutherIntegrator.STATIC_A, org.apache.commons.math3.ode.nonstiff.LutherIntegrator.STATIC_B, new org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator(), step);
	}
}

