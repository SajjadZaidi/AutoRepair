package org.apache.commons.math.util;


public class TransformerMap implements java.io.Serializable , org.apache.commons.math.util.NumberTransformer {
	private static final long serialVersionUID = 4605318041528645258L;

	private org.apache.commons.math.util.NumberTransformer defaultTransformer = null;

	private java.util.Map<java.lang.Class<?>, org.apache.commons.math.util.NumberTransformer> map = null;

	public TransformerMap() {
		map = new java.util.HashMap<java.lang.Class<?>, org.apache.commons.math.util.NumberTransformer>();
		defaultTransformer = new org.apache.commons.math.util.DefaultTransformer();
	}

	public boolean containsClass(java.lang.Class<?> key) {
		return map.containsKey(key);
	}

	public boolean containsTransformer(org.apache.commons.math.util.NumberTransformer value) {
		return map.containsValue(value);
	}

	public org.apache.commons.math.util.NumberTransformer getTransformer(java.lang.Class<?> key) {
		return map.get(key);
	}

	public org.apache.commons.math.util.NumberTransformer putTransformer(java.lang.Class<?> key, org.apache.commons.math.util.NumberTransformer transformer) {
		return map.put(key, transformer);
	}

	public org.apache.commons.math.util.NumberTransformer removeTransformer(java.lang.Class<?> key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public java.util.Set<java.lang.Class<?>> classes() {
		return map.keySet();
	}

	public java.util.Collection<org.apache.commons.math.util.NumberTransformer> transformers() {
		return map.values();
	}

	public double transform(java.lang.Object o) throws org.apache.commons.math.MathException {
		double value = java.lang.Double.NaN;
		if ((o instanceof java.lang.Number) || (o instanceof java.lang.String)) {
			value = defaultTransformer.transform(o);
		} else {
			org.apache.commons.math.util.NumberTransformer trans = getTransformer(o.getClass());
			if (trans != null) {
				value = trans.transform(o);
			} 
		}
		return value;
	}
}

