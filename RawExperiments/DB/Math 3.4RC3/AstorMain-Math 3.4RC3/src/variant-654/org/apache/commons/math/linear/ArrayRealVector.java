package org.apache.commons.math.linear;


public class ArrayRealVector implements java.io.Serializable , org.apache.commons.math.linear.RealVector {
    private static final long serialVersionUID = -1097961340710804027L;

    private static final org.apache.commons.math.linear.RealVectorFormat DEFAULT_FORMAT = org.apache.commons.math.linear.RealVectorFormat.getInstance();

    protected double[] data;

    public ArrayRealVector() {
        data = new double[0];
    }

    public ArrayRealVector(int size) {
        data = new double[size];
    }

    public ArrayRealVector(int size ,double preset) {
        data = new double[size];
        java.util.Arrays.fill(data, preset);
    }

    public ArrayRealVector(double[] d) {
        data = d.clone();
    }

    public ArrayRealVector(double[] d ,boolean copyArray) throws java.lang.IllegalArgumentException , java.lang.NullPointerException {
        if (d == null) {
            throw new java.lang.NullPointerException();
        } 
        if ((d.length) == 0) {
            throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("vector must have at least one element");
        } 
        data = copyArray ? d.clone() : d;
    }

    public ArrayRealVector(double[] d ,int pos ,int size) {
        if ((d.length) < (pos + size)) {
            throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("position {0} and size {1} don't fit to the size of the input array {2}", pos, size, d.length);
        } 
        data = new double[size];
        java.lang.System.arraycopy(d, pos, data, 0, size);
    }

    public ArrayRealVector(java.lang.Double[] d) {
        data = new double[d.length];
        for (int i = 0 ; i < (d.length) ; i++) {
            data[i] = d[i].doubleValue();
        }
    }

    public ArrayRealVector(java.lang.Double[] d ,int pos ,int size) {
        if ((d.length) < (pos + size)) {
            throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("position {0} and size {1} don't fit to the size of the input array {2}", pos, size, d.length);
        } 
        data = new double[size];
        for (int i = pos ; i < (pos + size) ; i++) {
            data[(i - pos)] = d[i].doubleValue();
        }
    }

    public ArrayRealVector(org.apache.commons.math.linear.RealVector v) {
        data = new double[v.getDimension()];
        for (int i = 0 ; i < (data.length) ; ++i) {
            data[i] = v.getEntry(i);
        }
    }

    public ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector v) {
        data = v.data.clone();
    }

    public ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector v ,boolean deep) {
        data = deep ? v.data.clone() : v.data;
    }

    public ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector v1 ,org.apache.commons.math.linear.ArrayRealVector v2) {
        data = new double[(v1.data.length) + (v2.data.length)];
        java.lang.System.arraycopy(v1.data, 0, data, 0, v1.data.length);
        java.lang.System.arraycopy(v2.data, 0, data, v1.data.length, v2.data.length);
    }

    public ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector v1 ,double[] v2) {
        data = new double[(v1.data.length) + (v2.length)];
        java.lang.System.arraycopy(v1.data, 0, data, 0, v1.data.length);
        java.lang.System.arraycopy(v2, 0, data, v1.data.length, v2.length);
    }

    public ArrayRealVector(double[] v1 ,org.apache.commons.math.linear.ArrayRealVector v2) {
        data = new double[(v1.length) + (v2.data.length)];
        java.lang.System.arraycopy(v1, 0, data, 0, v1.length);
        java.lang.System.arraycopy(v2.data, 0, data, v1.length, v2.data.length);
    }

    public ArrayRealVector(double[] v1 ,double[] v2) {
        data = new double[(v1.length) + (v2.length)];
        java.lang.System.arraycopy(v1, 0, data, 0, v1.length);
        java.lang.System.arraycopy(v2, 0, data, v1.length, v2.length);
    }

    public org.apache.commons.math.linear.RealVector copy() {
        return new org.apache.commons.math.linear.ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector.this , true);
    }

    public org.apache.commons.math.linear.RealVector add(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return add(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double[] out = new double[data.length];
            for (int i = 0 ; i < (data.length) ; i++) {
                out[i] = (data[i]) + (v.getEntry(i));
            }
            return new org.apache.commons.math.linear.ArrayRealVector(out);
        }
    }

    public org.apache.commons.math.linear.RealVector add(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) + (v[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.ArrayRealVector add(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return ((org.apache.commons.math.linear.ArrayRealVector)(add(v.data)));
    }

    public org.apache.commons.math.linear.RealVector subtract(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return subtract(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double[] out = new double[data.length];
            for (int i = 0 ; i < (data.length) ; i++) {
                out[i] = (data[i]) - (v.getEntry(i));
            }
            return new org.apache.commons.math.linear.ArrayRealVector(out);
        }
    }

    public org.apache.commons.math.linear.RealVector subtract(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) - (v[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.ArrayRealVector subtract(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return ((org.apache.commons.math.linear.ArrayRealVector)(subtract(v.data)));
    }

    public org.apache.commons.math.linear.RealVector mapAdd(double d) {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) + d;
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapAddToSelf(double d) {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = (data[i]) + d;
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapSubtract(double d) {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) - d;
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapSubtractToSelf(double d) {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = (data[i]) - d;
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapMultiply(double d) {
        setMaxEvaluations(java.lang.Integer.MAX_VALUE);
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) * d;
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapMultiplyToSelf(double d) {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = (data[i]) * d;
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapDivide(double d) {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) / d;
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapDivideToSelf(double d) {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = (data[i]) / d;
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapPow(double d) {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.pow(data[i], d);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapPowToSelf(double d) {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.pow(data[i], d);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapExp() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.exp(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapExpToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.exp(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapExpm1() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.expm1(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapExpm1ToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.expm1(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapLog() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.log(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapLogToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.log(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapLog10() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.log10(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapLog10ToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.log10(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapLog1p() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.log1p(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapLog1pToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.log1p(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapCosh() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.cosh(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapCoshToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.cosh(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapSinh() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.sinh(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapSinhToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.sinh(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapTanh() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.tanh(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapTanhToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.tanh(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapCos() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.cos(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapCosToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.cos(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapSin() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.sin(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapSinToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.sin(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapTan() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.tan(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapTanToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.tan(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapAcos() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.acos(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapAcosToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.acos(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapAsin() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.asin(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapAsinToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.asin(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapAtan() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.atan(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapAtanToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.atan(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapInv() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = 1.0 / (data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapInvToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = 1.0 / (data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapAbs() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.abs(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapAbsToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.abs(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapSqrt() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.sqrt(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapSqrtToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.sqrt(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapCbrt() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.cbrt(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapCbrtToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.cbrt(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapCeil() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.ceil(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapCeilToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.ceil(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapFloor() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.floor(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapFloorToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.floor(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapRint() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.rint(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapRintToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.rint(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapSignum() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.signum(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapSignumToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.signum(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector mapUlp() {
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = java.lang.Math.ulp(data[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector mapUlpToSelf() {
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] = java.lang.Math.ulp(data[i]);
        }
        return org.apache.commons.math.linear.ArrayRealVector.this;
    }

    public org.apache.commons.math.linear.RealVector ebeMultiply(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return ebeMultiply(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double[] out = new double[data.length];
            for (int i = 0 ; i < (data.length) ; i++) {
                out[i] = (data[i]) * (v.getEntry(i));
            }
            return new org.apache.commons.math.linear.ArrayRealVector(out);
        }
    }

    public org.apache.commons.math.linear.RealVector ebeMultiply(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) * (v[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.ArrayRealVector ebeMultiply(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return ((org.apache.commons.math.linear.ArrayRealVector)(ebeMultiply(v.data)));
    }

    public org.apache.commons.math.linear.RealVector ebeDivide(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return ebeDivide(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double[] out = new double[data.length];
            for (int i = 0 ; i < (data.length) ; i++) {
                out[i] = (data[i]) / (v.getEntry(i));
            }
            return new org.apache.commons.math.linear.ArrayRealVector(out);
        }
    }

    public org.apache.commons.math.linear.RealVector ebeDivide(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double[] out = new double[data.length];
        for (int i = 0 ; i < (data.length) ; i++) {
            out[i] = (data[i]) / (v[i]);
        }
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.ArrayRealVector ebeDivide(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return ((org.apache.commons.math.linear.ArrayRealVector)(ebeDivide(v.data)));
    }

    public double[] getData() {
        return data.clone();
    }

    public double[] getDataRef() {
        return data;
    }

    public double dotProduct(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return dotProduct(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double dot = 0;
            for (int i = 0 ; i < (data.length) ; i++) {
                dot += (data[i]) * (v.getEntry(i));
            }
            return dot;
        }
    }

    public double dotProduct(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double dot = 0;
        for (int i = 0 ; i < (data.length) ; i++) {
            dot += (data[i]) * (v[i]);
        }
        return dot;
    }

    public double dotProduct(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return dotProduct(v.data);
    }

    public double getNorm() {
        double sum = 0;
        for (double a : data) {
            sum += a * a;
        }
        return java.lang.Math.sqrt(sum);
    }

    public double getL1Norm() {
        double sum = 0;
        for (double a : data) {
            sum += java.lang.Math.abs(a);
        }
        return sum;
    }

    public double getLInfNorm() {
        double max = 0;
        for (double a : data) {
            max += java.lang.Math.max(max, java.lang.Math.abs(a));
        }
        return max;
    }

    public double getDistance(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return getDistance(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double sum = 0;
            for (int i = 0 ; i < (data.length) ; ++i) {
                final double delta = (data[i]) - (v.getEntry(i));
                sum += delta * delta;
            }
            return java.lang.Math.sqrt(sum);
        }
    }

    public double getDistance(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double sum = 0;
        for (int i = 0 ; i < (data.length) ; ++i) {
            final double delta = (data[i]) - (v[i]);
            sum += delta * delta;
        }
        return java.lang.Math.sqrt(sum);
    }

    public double getDistance(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return getDistance(v.data);
    }

    public double getL1Distance(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return getL1Distance(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double sum = 0;
            for (int i = 0 ; i < (data.length) ; ++i) {
                final double delta = (data[i]) - (v.getEntry(i));
                sum += java.lang.Math.abs(delta);
            }
            return sum;
        }
    }

    public double getL1Distance(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double sum = 0;
        for (int i = 0 ; i < (data.length) ; ++i) {
            final double delta = (data[i]) - (v[i]);
            sum += java.lang.Math.abs(delta);
        }
        return sum;
    }

    public double getL1Distance(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return getL1Distance(v.data);
    }

    public double getLInfDistance(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return getLInfDistance(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            double max = 0;
            for (int i = 0 ; i < (data.length) ; ++i) {
                final double delta = (data[i]) - (v.getEntry(i));
                max = java.lang.Math.max(max, java.lang.Math.abs(delta));
            }
            return max;
        }
    }

    public double getLInfDistance(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        double max = 0;
        for (int i = 0 ; i < (data.length) ; ++i) {
            final double delta = (data[i]) - (v[i]);
            max = java.lang.Math.max(max, java.lang.Math.abs(delta));
        }
        return max;
    }

    public double getLInfDistance(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return getLInfDistance(v.data);
    }

    public org.apache.commons.math.linear.RealVector unitVector() throws java.lang.ArithmeticException {
        final double norm = getNorm();
        if (norm == 0) {
            throw org.apache.commons.math.MathRuntimeException.createArithmeticException("zero norm");
        } 
        return mapDivide(getNorm());
    }

    public void unitize() throws java.lang.ArithmeticException {
        final double norm = getNorm();
        if (norm == 0) {
            throw org.apache.commons.math.MathRuntimeException.createArithmeticException("cannot normalize a zero norm vector");
        } 
        for (int i = 0 ; i < (data.length) ; i++) {
            data[i] /= norm;
        }
    }

    public org.apache.commons.math.linear.RealVector projection(org.apache.commons.math.linear.RealVector v) {
        return v.mapMultiply(((dotProduct(v)) / (v.dotProduct(v))));
    }

    public org.apache.commons.math.linear.RealVector projection(double[] v) {
        return projection(new org.apache.commons.math.linear.ArrayRealVector(v , false));
    }

    public org.apache.commons.math.linear.ArrayRealVector projection(org.apache.commons.math.linear.ArrayRealVector v) {
        return ((org.apache.commons.math.linear.ArrayRealVector)(v.mapMultiply(((dotProduct(v)) / (v.dotProduct(v))))));
    }

    public org.apache.commons.math.linear.RealMatrix outerProduct(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        try {
            return outerProduct(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            checkVectorDimensions(v);
            final int m = data.length;
            final org.apache.commons.math.linear.RealMatrix out = org.apache.commons.math.linear.MatrixUtils.createRealMatrix(m, m);
            for (int i = 0 ; i < (data.length) ; i++) {
                for (int j = 0 ; j < (data.length) ; j++) {
                    out.setEntry(i, j, ((data[i]) * (v.getEntry(j))));
                }
            }
            return out;
        }
    }

    public org.apache.commons.math.linear.RealMatrix outerProduct(org.apache.commons.math.linear.ArrayRealVector v) throws java.lang.IllegalArgumentException {
        return outerProduct(v.data);
    }

    public org.apache.commons.math.linear.RealMatrix outerProduct(double[] v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.length);
        final int m = data.length;
        final org.apache.commons.math.linear.RealMatrix out = org.apache.commons.math.linear.MatrixUtils.createRealMatrix(m, m);
        for (int i = 0 ; i < (data.length) ; i++) {
            for (int j = 0 ; j < (data.length) ; j++) {
                out.setEntry(i, j, ((data[i]) * (v[j])));
            }
        }
        return out;
    }

    public double getEntry(int index) throws org.apache.commons.math.linear.MatrixIndexException {
        return data[index];
    }

    public int getDimension() {
        return data.length;
    }

    public org.apache.commons.math.linear.RealVector append(org.apache.commons.math.linear.RealVector v) {
        try {
            return append(((org.apache.commons.math.linear.ArrayRealVector)(v)));
        } catch (java.lang.ClassCastException cce) {
            return new org.apache.commons.math.linear.ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector.this , new org.apache.commons.math.linear.ArrayRealVector(v));
        }
    }

    public org.apache.commons.math.linear.ArrayRealVector append(org.apache.commons.math.linear.ArrayRealVector v) {
        return new org.apache.commons.math.linear.ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector.this , v);
    }

    public org.apache.commons.math.linear.RealVector append(double in) {
        final double[] out = new double[(data.length) + 1];
        java.lang.System.arraycopy(data, 0, out, 0, data.length);
        out[data.length] = in;
        return new org.apache.commons.math.linear.ArrayRealVector(out);
    }

    public org.apache.commons.math.linear.RealVector append(double[] in) {
        return new org.apache.commons.math.linear.ArrayRealVector(org.apache.commons.math.linear.ArrayRealVector.this , in);
    }

    public org.apache.commons.math.linear.RealVector getSubVector(int index, int n) {
        org.apache.commons.math.linear.ArrayRealVector out = new org.apache.commons.math.linear.ArrayRealVector(n);
        try {
            java.lang.System.arraycopy(data, index, out.data, 0, n);
        } catch (java.lang.IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(((index + n) - 1));
        }
        return out;
    }

    public void setEntry(int index, double value) {
        try {
            data[index] = value;
        } catch (java.lang.IndexOutOfBoundsException e) {
            checkIndex(index);
        }
    }

    public void setSubVector(int index, org.apache.commons.math.linear.RealVector v) {
        try {
            try {
                set(index, ((org.apache.commons.math.linear.ArrayRealVector)(v)));
            } catch (java.lang.ClassCastException cce) {
                for (int i = index ; i < (index + (v.getDimension())) ; ++i) {
                    data[i] = v.getEntry((i - index));
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(((index + (v.getDimension())) - 1));
        }
    }

    public void setSubVector(int index, double[] v) {
        try {
            java.lang.System.arraycopy(v, 0, data, index, v.length);
        } catch (java.lang.IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(((index + (v.length)) - 1));
        }
    }

    public void set(int index, org.apache.commons.math.linear.ArrayRealVector v) throws org.apache.commons.math.linear.MatrixIndexException {
        setSubVector(index, v.data);
    }

    public void set(double value) {
        java.util.Arrays.fill(data, value);
    }

    public double[] toArray() {
        return data.clone();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return org.apache.commons.math.linear.ArrayRealVector.DEFAULT_FORMAT.format(org.apache.commons.math.linear.ArrayRealVector.this);
    }

    protected void checkVectorDimensions(org.apache.commons.math.linear.RealVector v) throws java.lang.IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
    }

    protected void checkVectorDimensions(int n) throws java.lang.IllegalArgumentException {
        if ((data.length) != n) {
            throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException("vector length mismatch: got {0} but expected {1}", data.length, n);
        } 
    }

    public boolean isNaN() {
        for (double v : data) {
            if (java.lang.Double.isNaN(v)) {
                return true;
            } 
        }
        return false;
    }

    public boolean isInfinite() {
        if (isNaN()) {
            return false;
        } 
        for (double v : data) {
            if (java.lang.Double.isInfinite(v)) {
                return true;
            } 
        }
        return false;
    }

    @java.lang.Override
    public boolean equals(java.lang.Object other) {
        if ((org.apache.commons.math.linear.ArrayRealVector.this) == other) {
            return true;
        } 
        if (other == null) {
            return false;
        } 
        try {
            org.apache.commons.math.linear.RealVector rhs = ((org.apache.commons.math.linear.RealVector)(other));
            if ((data.length) != (rhs.getDimension())) {
                return false;
            } 
            if (rhs.isNaN()) {
                return org.apache.commons.math.linear.ArrayRealVector.this.isNaN();
            } 
            for (int i = 0 ; i < (data.length) ; ++i) {
                if ((data[i]) != (rhs.getEntry(i))) {
                    return false;
                } 
            }
            return true;
        } catch (java.lang.ClassCastException ex) {
            return false;
        }
    }

    @java.lang.Override
    public int hashCode() {
        if (isNaN()) {
            return 9;
        } 
        return org.apache.commons.math.util.MathUtils.hash(data);
    }

    private void checkIndex(final int index) throws org.apache.commons.math.linear.MatrixIndexException {
        if ((index < 0) || (index >= (getDimension()))) {
            throw new org.apache.commons.math.linear.MatrixIndexException("index {0} out of allowed range [{1}, {2}]" , index , 0 , ((getDimension()) - 1));
        } 
    }
}

