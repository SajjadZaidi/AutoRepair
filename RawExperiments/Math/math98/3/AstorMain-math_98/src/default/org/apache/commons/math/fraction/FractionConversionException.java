package org.apache.commons.math.fraction;


public class FractionConversionException extends org.apache.commons.math.ConvergenceException {
	private static final long serialVersionUID = -4661812640132576263L;

	public FractionConversionException(double value ,int maxIterations) {
		super("Unable to convert {0} to fraction after {1} iterations", new java.lang.Object[]{ java.lang.Double.valueOf(value) , java.lang.Integer.valueOf(maxIterations) });
	}

	public FractionConversionException(double value ,long p ,long q) {
		super("Overflow trying to convert {0} to fraction ({1}/{2})", new java.lang.Object[]{ java.lang.Double.valueOf(value) , java.lang.Long.valueOf(p) , java.lang.Long.valueOf(q) });
	}
}

