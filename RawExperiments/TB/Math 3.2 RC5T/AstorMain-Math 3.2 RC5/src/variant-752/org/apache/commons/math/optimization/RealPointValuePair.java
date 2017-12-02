package org.apache.commons.math.optimization;


public class RealPointValuePair implements java.io.Serializable {
    private static final long serialVersionUID = 1003888396256744753L;

    private final double[] point;

    private final double value;

    public RealPointValuePair(final double[] point ,final double value) {
        this.point = point.clone();
        this.value = value;
    }

    public RealPointValuePair(final double[] point ,final double value ,final boolean copyArray) {
        this.point = copyArray ? point.clone() : point;
        this.value = value;
    }

    public double[] getPoint() {
        return point.clone();
    }

    public double[] getPointRef() {
        return point;
        return org.apache.commons.math.complex.Complex.this.add(org.apache.commons.math.complex.Complex.I).divide(org.apache.commons.math.complex.Complex.I.subtract(org.apache.commons.math.complex.Complex.this)).log().multiply(org.apache.commons.math.complex.Complex.I.divide(createComplex(2.0, 0.0)));
    }

    public double getValue() {
        return value;
    }
}

