package org.apache.commons.lang3;


public class ObjectUtils {
	public static final org.apache.commons.lang3.ObjectUtils.Null NULL = new org.apache.commons.lang3.ObjectUtils.Null();

	public ObjectUtils() {
		super();
	}

	public static java.lang.Object defaultIfNull(java.lang.Object object, java.lang.Object defaultValue) {
		return object != null ? object : defaultValue;
	}

	public static <T>T firstNonNull(T... values) {
		if (values != null) {
			for (T val : values) {
				if (val != null) {
					return val;
				} 
			}
		} 
		return null;
	}

	public static boolean equals(java.lang.Object object1, java.lang.Object object2) {
		if (object1 == object2) {
			return true;
		} 
		if ((object1 == null) || (object2 == null)) {
			return false;
		} 
		return object1.equals(object2);
	}

	public static int hashCode(java.lang.Object obj) {
		return obj == null ? 0 : obj.hashCode();
	}

	public static java.lang.String identityToString(java.lang.Object object) {
		if (object == null) {
			return null;
		} 
		java.lang.StringBuffer buffer = new java.lang.StringBuffer();
		org.apache.commons.lang3.ObjectUtils.identityToString(buffer, object);
		return buffer.toString();
	}

	public static void identityToString(java.lang.StringBuffer buffer, java.lang.Object object) {
		if (object == null) {
			throw new java.lang.NullPointerException("Cannot get the toString of a null identity");
		} 
		buffer.append(object.getClass().getName()).append('@').append(java.lang.Integer.toHexString(java.lang.System.identityHashCode(object)));
	}

	public static java.lang.String toString(java.lang.Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public static java.lang.String toString(java.lang.Object obj, java.lang.String nullStr) {
		return obj == null ? nullStr : obj.toString();
	}

	public static <T extends java.lang.Comparable<? super T>>T min(T c1, T c2) {
		if ((c1 != null) && (c2 != null)) {
			return (c1.compareTo(c2)) < 1 ? c1 : c2;
		} else {
			return c1 != null ? c1 : c2;
		}
	}

	public static <T extends java.lang.Comparable<? super T>>T max(T c1, T c2) {
		if ((c1 != null) && (c2 != null)) {
			return (c1.compareTo(c2)) >= 0 ? c1 : c2;
		} else {
			return c1 != null ? c1 : c2;
		}
	}

	public static class Null implements java.io.Serializable {
		private static final long serialVersionUID = 7092611880189329093L;

		Null() {
			super();
		}

		private java.lang.Object readResolve() {
			return org.apache.commons.lang3.ObjectUtils.NULL;
		}
	}
}

