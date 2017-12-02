package org.apache.commons.lang3;


public class ArrayUtils {
	public static final java.lang.Object[] EMPTY_OBJECT_ARRAY = new java.lang.Object[0];

	public static final java.lang.Class<?>[] EMPTY_CLASS_ARRAY = new java.lang.Class[0];

	public static final java.lang.String[] EMPTY_STRING_ARRAY = new java.lang.String[0];

	public static final long[] EMPTY_LONG_ARRAY = new long[0];

	public static final java.lang.Long[] EMPTY_LONG_OBJECT_ARRAY = new java.lang.Long[0];

	public static final int[] EMPTY_INT_ARRAY = new int[0];

	public static final java.lang.Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new java.lang.Integer[0];

	public static final short[] EMPTY_SHORT_ARRAY = new short[0];

	public static final java.lang.Short[] EMPTY_SHORT_OBJECT_ARRAY = new java.lang.Short[0];

	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	public static final java.lang.Byte[] EMPTY_BYTE_OBJECT_ARRAY = new java.lang.Byte[0];

	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

	public static final java.lang.Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new java.lang.Double[0];

	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];

	public static final java.lang.Float[] EMPTY_FLOAT_OBJECT_ARRAY = new java.lang.Float[0];

	public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];

	public static final java.lang.Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new java.lang.Boolean[0];

	public static final char[] EMPTY_CHAR_ARRAY = new char[0];

	public static final java.lang.Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new java.lang.Character[0];

	public static final int INDEX_NOT_FOUND = -1;

	public ArrayUtils() {
		super();
	}

	public static java.lang.String toString(final java.lang.Object array) {
		return org.apache.commons.lang3.ArrayUtils.toString(array, "{}");
	}

	public static java.lang.String toString(final java.lang.Object array, final java.lang.String stringIfNull) {
		if (array == null) {
			return stringIfNull;
		} 
		return new org.apache.commons.lang3.builder.ToStringBuilder(array , org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE).append(array).toString();
	}

	public static int hashCode(final java.lang.Object array) {
		return new org.apache.commons.lang3.builder.HashCodeBuilder().append(array).toHashCode();
	}

	public static boolean isEquals(final java.lang.Object array1, final java.lang.Object array2) {
		return new org.apache.commons.lang3.builder.EqualsBuilder().append(array1, array2).isEquals();
	}

	public static java.util.Map<java.lang.Object, java.lang.Object> toMap(final java.lang.Object[] array) {
		if (array == null) {
			return null;
		} 
		final java.util.Map<java.lang.Object, java.lang.Object> map = new java.util.HashMap<java.lang.Object, java.lang.Object>(((int)((array.length) * 1.5)));
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Object object = array[i];
			if (object instanceof java.util.Map.Entry<?, ?>) {
				final java.util.Map.Entry<?, ?> entry = ((java.util.Map.Entry<?, ?>)(object));
				map.put(entry.getKey(), entry.getValue());
			} else if (object instanceof java.lang.Object[]) {
				final java.lang.Object[] entry = ((java.lang.Object[])(object));
				if ((entry.length) < 2) {
					throw new java.lang.IllegalArgumentException((((("Array element " + i) + ", '") + object) + "', has a length less than 2"));
				} 
				map.put(entry[0], entry[1]);
			} else {
				throw new java.lang.IllegalArgumentException((((("Array element " + i) + ", '") + object) + "', is neither of type Map.Entry nor an Array"));
			}
		}
		return map;
	}

	public static <T>T[] toArray(final T... items) {
		return items;
	}

	public static <T>T[] clone(final T[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static long[] clone(final long[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static int[] clone(final int[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static short[] clone(final short[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static char[] clone(final char[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static byte[] clone(final byte[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static double[] clone(final double[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static float[] clone(final float[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static boolean[] clone(final boolean[] array) {
		if (array == null) {
			return null;
		} 
		return array.clone();
	}

	public static java.lang.Object[] nullToEmpty(final java.lang.Object[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.String[] nullToEmpty(final java.lang.String[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		return array;
	}

	public static long[] nullToEmpty(final long[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_ARRAY;
		} 
		return array;
	}

	public static int[] nullToEmpty(final int[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INT_ARRAY;
		} 
		return array;
	}

	public static short[] nullToEmpty(final short[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_ARRAY;
		} 
		return array;
	}

	public static char[] nullToEmpty(final char[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHAR_ARRAY;
		} 
		return array;
	}

	public static byte[] nullToEmpty(final byte[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_ARRAY;
		} 
		return array;
	}

	public static double[] nullToEmpty(final double[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_ARRAY;
		} 
		return array;
	}

	public static float[] nullToEmpty(final float[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_ARRAY;
		} 
		return array;
	}

	public static boolean[] nullToEmpty(final boolean[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_ARRAY;
		} 
		return array;
	}

	public static java.lang.Long[] nullToEmpty(final java.lang.Long[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Integer[] nullToEmpty(final java.lang.Integer[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Short[] nullToEmpty(final java.lang.Short[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Character[] nullToEmpty(final java.lang.Character[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Byte[] nullToEmpty(final java.lang.Byte[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Double[] nullToEmpty(final java.lang.Double[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Float[] nullToEmpty(final java.lang.Float[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY;
		} 
		return array;
	}

	public static java.lang.Boolean[] nullToEmpty(final java.lang.Boolean[] array) {
		if ((array == null) || ((array.length) == 0)) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY;
		} 
		return array;
	}

	public static <T>T[] subarray(final T[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		final java.lang.Class<?> type = array.getClass().getComponentType();
		if (newSize <= 0) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final T[] emptyArray = ((T[])(java.lang.reflect.Array.newInstance(type, 0)));
			return emptyArray;
		} 
		@java.lang.SuppressWarnings(value = "unchecked")
		final T[] subarray = ((T[])(java.lang.reflect.Array.newInstance(type, newSize)));
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static long[] subarray(final long[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_ARRAY;
		} 
		final long[] subarray = new long[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static int[] subarray(final int[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INT_ARRAY;
		} 
		final int[] subarray = new int[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static short[] subarray(final short[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_ARRAY;
		} 
		final short[] subarray = new short[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static char[] subarray(final char[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHAR_ARRAY;
		} 
		final char[] subarray = new char[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static byte[] subarray(final byte[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_ARRAY;
		} 
		final byte[] subarray = new byte[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static double[] subarray(final double[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_ARRAY;
		} 
		final double[] subarray = new double[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static float[] subarray(final float[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_ARRAY;
		} 
		final float[] subarray = new float[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static boolean[] subarray(final boolean[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		} 
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		} 
		if (endIndexExclusive > (array.length)) {
			endIndexExclusive = array.length;
		} 
		final int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_ARRAY;
		} 
		final boolean[] subarray = new boolean[newSize];
		java.lang.System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static boolean isSameLength(final java.lang.Object[] array1, final java.lang.Object[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final long[] array1, final long[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final int[] array1, final int[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final short[] array1, final short[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final char[] array1, final char[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final byte[] array1, final byte[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final double[] array1, final double[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final float[] array1, final float[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static boolean isSameLength(final boolean[] array1, final boolean[] array2) {
		if (((((array1 == null) && (array2 != null)) && ((array2.length) > 0)) || (((array2 == null) && (array1 != null)) && ((array1.length) > 0))) || (((array1 != null) && (array2 != null)) && ((array1.length) != (array2.length)))) {
			return false;
		} 
		return true;
	}

	public static int getLength(final java.lang.Object array) {
		if (array == null) {
			return 0;
		} 
		return java.lang.reflect.Array.getLength(array);
	}

	public static boolean isSameType(final java.lang.Object array1, final java.lang.Object array2) {
		if ((array1 == null) || (array2 == null)) {
			throw new java.lang.IllegalArgumentException("The Array must not be null");
		} 
		return array1.getClass().getName().equals(array2.getClass().getName());
	}

	public static void reverse(final java.lang.Object[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		java.lang.Object tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final long[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		long tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final int[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		int tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final short[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		short tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final char[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		char tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final byte[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		byte tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final double[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		double tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final float[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		float tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static void reverse(final boolean[] array) {
		if (array == null) {
			return ;
		} 
		int i = 0;
		int j = (array.length) - 1;
		boolean tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public static int indexOf(final java.lang.Object[] array, final java.lang.Object objectToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, objectToFind, 0);
	}

	public static int indexOf(final java.lang.Object[] array, final java.lang.Object objectToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		if (objectToFind == null) {
			for (int i = startIndex ; i < (array.length) ; i++) {
				if ((array[i]) == null) {
					return i;
				} 
			}
		} else if (array.getClass().getComponentType().isInstance(objectToFind)) {
			for (int i = startIndex ; i < (array.length) ; i++) {
				if (objectToFind.equals(array[i])) {
					return i;
				} 
			}
		} 
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final java.lang.Object[] array, final java.lang.Object objectToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, objectToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final java.lang.Object[] array, final java.lang.Object objectToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		if (objectToFind == null) {
			for (int i = startIndex ; i >= 0 ; i--) {
				if ((array[i]) == null) {
					return i;
				} 
			}
		} else if (array.getClass().getComponentType().isInstance(objectToFind)) {
			for (int i = startIndex ; i >= 0 ; i--) {
				if (objectToFind.equals(array[i])) {
					return i;
				} 
			}
		} 
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final java.lang.Object[] array, final java.lang.Object objectToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, objectToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final long[] array, final long valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final long[] array, final long valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final long[] array, final long valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final long[] array, final long valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final long[] array, final long valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final int[] array, final int valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final int[] array, final int valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final int[] array, final int valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final int[] array, final int valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final short[] array, final short valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final short[] array, final short valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final short[] array, final short valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final short[] array, final short valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final short[] array, final short valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final char[] array, final char valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final char[] array, final char valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final char[] array, final char valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final char[] array, final char valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final char[] array, final char valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final byte[] array, final byte valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final byte[] array, final byte valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final byte[] array, final byte valueToFind, int startIndex) {
		if (array == null) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final byte[] array, final byte valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final double[] array, final double valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final double[] array, final double valueToFind, final double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0, tolerance);
	}

	public static int indexOf(final double[] array, final double valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int indexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		final double min = valueToFind - tolerance;
		final double max = valueToFind + tolerance;
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (((array[i]) >= min) && ((array[i]) <= max)) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final double[] array, final double valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final double[] array, final double valueToFind, final double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE, tolerance);
	}

	public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		final double min = valueToFind - tolerance;
		final double max = valueToFind + tolerance;
		for (int i = startIndex ; i >= 0 ; i--) {
			if (((array[i]) >= min) && ((array[i]) <= max)) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final double[] array, final double valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static boolean contains(final double[] array, final double valueToFind, final double tolerance) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0, tolerance)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final float[] array, final float valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final float[] array, final float valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final float[] array, final float valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final float[] array, final float valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final float[] array, final float valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static int indexOf(final boolean[] array, final boolean valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, 0);
	}

	public static int indexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			startIndex = 0;
		} 
		for (int i = startIndex ; i < (array.length) ; i++) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(final boolean[] array, final boolean valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, java.lang.Integer.MAX_VALUE);
	}

	public static int lastIndexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
		if (org.apache.commons.lang3.ArrayUtils.isEmpty(array)) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} 
		if (startIndex < 0) {
			return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
		} else if (startIndex >= (array.length)) {
			startIndex = (array.length) - 1;
		} 
		for (int i = startIndex ; i >= 0 ; i--) {
			if (valueToFind == (array[i])) {
				return i;
			} 
		}
		return org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND;
	}

	public static boolean contains(final boolean[] array, final boolean valueToFind) {
		return (org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind)) != (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND);
	}

	public static char[] toPrimitive(final java.lang.Character[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHAR_ARRAY;
		} 
		final char[] result = new char[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].charValue();
		}
		return result;
	}

	public static char[] toPrimitive(final java.lang.Character[] array, final char valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHAR_ARRAY;
		} 
		final char[] result = new char[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Character b = array[i];
			result[i] = b == null ? valueForNull : b.charValue();
		}
		return result;
	}

	public static java.lang.Character[] toObject(final char[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY;
		} 
		final java.lang.Character[] result = new java.lang.Character[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Character.valueOf(array[i]);
		}
		return result;
	}

	public static long[] toPrimitive(final java.lang.Long[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_ARRAY;
		} 
		final long[] result = new long[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].longValue();
		}
		return result;
	}

	public static long[] toPrimitive(final java.lang.Long[] array, final long valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_ARRAY;
		} 
		final long[] result = new long[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Long b = array[i];
			result[i] = b == null ? valueForNull : b.longValue();
		}
		return result;
	}

	public static java.lang.Long[] toObject(final long[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
		} 
		final java.lang.Long[] result = new java.lang.Long[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Long.valueOf(array[i]);
		}
		return result;
	}

	public static int[] toPrimitive(final java.lang.Integer[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INT_ARRAY;
		} 
		final int[] result = new int[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].intValue();
		}
		return result;
	}

	public static int[] toPrimitive(final java.lang.Integer[] array, final int valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INT_ARRAY;
		} 
		final int[] result = new int[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Integer b = array[i];
			result[i] = b == null ? valueForNull : b.intValue();
		}
		return result;
	}

	public static java.lang.Integer[] toObject(final int[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
		} 
		final java.lang.Integer[] result = new java.lang.Integer[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Integer.valueOf(array[i]);
		}
		return result;
	}

	public static short[] toPrimitive(final java.lang.Short[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_ARRAY;
		} 
		final short[] result = new short[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].shortValue();
		}
		return result;
	}

	public static short[] toPrimitive(final java.lang.Short[] array, final short valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_ARRAY;
		} 
		final short[] result = new short[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Short b = array[i];
			result[i] = b == null ? valueForNull : b.shortValue();
		}
		return result;
	}

	public static java.lang.Short[] toObject(final short[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY;
		} 
		final java.lang.Short[] result = new java.lang.Short[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Short.valueOf(array[i]);
		}
		return result;
	}

	public static byte[] toPrimitive(final java.lang.Byte[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_ARRAY;
		} 
		final byte[] result = new byte[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].byteValue();
		}
		return result;
	}

	public static byte[] toPrimitive(final java.lang.Byte[] array, final byte valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_ARRAY;
		} 
		final byte[] result = new byte[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Byte b = array[i];
			result[i] = b == null ? valueForNull : b.byteValue();
		}
		return result;
	}

	public static java.lang.Byte[] toObject(final byte[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY;
		} 
		final java.lang.Byte[] result = new java.lang.Byte[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Byte.valueOf(array[i]);
		}
		return result;
	}

	public static double[] toPrimitive(final java.lang.Double[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_ARRAY;
		} 
		final double[] result = new double[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].doubleValue();
		}
		return result;
	}

	public static double[] toPrimitive(final java.lang.Double[] array, final double valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_ARRAY;
		} 
		final double[] result = new double[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Double b = array[i];
			result[i] = b == null ? valueForNull : b.doubleValue();
		}
		return result;
	}

	public static java.lang.Double[] toObject(final double[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY;
		} 
		final java.lang.Double[] result = new java.lang.Double[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Double.valueOf(array[i]);
		}
		return result;
	}

	public static float[] toPrimitive(final java.lang.Float[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_ARRAY;
		} 
		final float[] result = new float[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].floatValue();
		}
		return result;
	}

	public static float[] toPrimitive(final java.lang.Float[] array, final float valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_ARRAY;
		} 
		final float[] result = new float[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Float b = array[i];
			result[i] = b == null ? valueForNull : b.floatValue();
		}
		return result;
	}

	public static java.lang.Float[] toObject(final float[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY;
		} 
		final java.lang.Float[] result = new java.lang.Float[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = java.lang.Float.valueOf(array[i]);
		}
		return result;
	}

	public static boolean[] toPrimitive(final java.lang.Boolean[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_ARRAY;
		} 
		final boolean[] result = new boolean[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i].booleanValue();
		}
		return result;
	}

	public static boolean[] toPrimitive(final java.lang.Boolean[] array, final boolean valueForNull) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_ARRAY;
		} 
		final boolean[] result = new boolean[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			final java.lang.Boolean b = array[i];
			result[i] = b == null ? valueForNull : b.booleanValue();
		}
		return result;
	}

	public static java.lang.Boolean[] toObject(final boolean[] array) {
		if (array == null) {
			return null;
		} else if ((array.length) == 0) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY;
		} 
		final java.lang.Boolean[] result = new java.lang.Boolean[array.length];
		for (int i = 0 ; i < (array.length) ; i++) {
			result[i] = array[i] ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE;
		}
		return result;
	}

	public static boolean isEmpty(final java.lang.Object[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final long[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final int[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final short[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final char[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final byte[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final double[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final float[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static boolean isEmpty(final boolean[] array) {
		return (array == null) || ((array.length) == 0);
	}

	public static <T>boolean isNotEmpty(final T[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final long[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final int[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final short[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final char[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final byte[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final double[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final float[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static boolean isNotEmpty(final boolean[] array) {
		return (array != null) && ((array.length) != 0);
	}

	public static <T>T[] addAll(final T[] array1, final T... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final java.lang.Class<?> type1 = array1.getClass().getComponentType();
		@java.lang.SuppressWarnings(value = "unchecked")
		final T[] joinedArray = ((T[])(java.lang.reflect.Array.newInstance(type1, ((array1.length) + (array2.length)))));
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		try {
			java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		} catch (final java.lang.ArrayStoreException ase) {
			final java.lang.Class<?> type2 = array2.getClass().getComponentType();
			if (!(type1.isAssignableFrom(type2))) {
				throw new java.lang.IllegalArgumentException(((("Cannot store " + (type2.getName())) + " in an array of ") + (type1.getName())) , ase);
			} 
			throw ase;
		}
		return joinedArray;
	}

	public static boolean[] addAll(final boolean[] array1, final boolean... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final boolean[] joinedArray = new boolean[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static char[] addAll(final char[] array1, final char... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final char[] joinedArray = new char[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static byte[] addAll(final byte[] array1, final byte... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final byte[] joinedArray = new byte[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static short[] addAll(final short[] array1, final short... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final short[] joinedArray = new short[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static int[] addAll(final int[] array1, final int... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final int[] joinedArray = new int[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static long[] addAll(final long[] array1, final long... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final long[] joinedArray = new long[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static float[] addAll(final float[] array1, final float... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final float[] joinedArray = new float[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static double[] addAll(final double[] array1, final double... array2) {
		if (array1 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array2);
		} else if (array2 == null) {
			return org.apache.commons.lang3.ArrayUtils.clone(array1);
		} 
		final double[] joinedArray = new double[(array1.length) + (array2.length)];
		java.lang.System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		java.lang.System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static <T>T[] add(final T[] array, final T element) {
		java.lang.Class<?> type;
		if (array != null) {
			type = array.getClass();
		} else if (element != null) {
			type = element.getClass();
		} else {
			throw new java.lang.IllegalArgumentException("Arguments cannot both be null");
		}
		@java.lang.SuppressWarnings(value = "unchecked")
		final T[] newArray = ((T[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, type)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static boolean[] add(final boolean[] array, final boolean element) {
		final boolean[] newArray = ((boolean[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Boolean.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static byte[] add(final byte[] array, final byte element) {
		final byte[] newArray = ((byte[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Byte.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static char[] add(final char[] array, final char element) {
		final char[] newArray = ((char[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Character.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static double[] add(final double[] array, final double element) {
		final double[] newArray = ((double[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Double.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static float[] add(final float[] array, final float element) {
		final float[] newArray = ((float[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Float.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static int[] add(final int[] array, final int element) {
		final int[] newArray = ((int[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Integer.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static long[] add(final long[] array, final long element) {
		final long[] newArray = ((long[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Long.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	public static short[] add(final short[] array, final short element) {
		final short[] newArray = ((short[])(org.apache.commons.lang3.ArrayUtils.copyArrayGrow1(array, java.lang.Short.TYPE)));
		newArray[((newArray.length) - 1)] = element;
		return newArray;
	}

	private static java.lang.Object copyArrayGrow1(final java.lang.Object array, final java.lang.Class<?> newArrayComponentType) {
		if (array != null) {
			final int arrayLength = java.lang.reflect.Array.getLength(array);
			final java.lang.Object newArray = java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), (arrayLength + 1));
			java.lang.System.arraycopy(array, 0, newArray, 0, arrayLength);
			return newArray;
		} 
		return java.lang.reflect.Array.newInstance(newArrayComponentType, 1);
	}

	public static <T>T[] add(final T[] array, final int index, final T element) {
		java.lang.Class<?> clss = null;
		if (array != null) {
			clss = array.getClass().getComponentType();
		} else if (element != null) {
			clss = element.getClass();
		} else {
			throw new java.lang.IllegalArgumentException("Array and element cannot both be null");
		}
		@java.lang.SuppressWarnings(value = "unchecked")
		final T[] newArray = ((T[])(org.apache.commons.lang3.ArrayUtils.add(array, index, element, clss)));
		return newArray;
	}

	public static boolean[] add(final boolean[] array, final int index, final boolean element) {
		return ((boolean[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Boolean.valueOf(element), java.lang.Boolean.TYPE)));
	}

	public static char[] add(final char[] array, final int index, final char element) {
		return ((char[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Character.valueOf(element), java.lang.Character.TYPE)));
	}

	public static byte[] add(final byte[] array, final int index, final byte element) {
		return ((byte[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Byte.valueOf(element), java.lang.Byte.TYPE)));
	}

	public static short[] add(final short[] array, final int index, final short element) {
		return ((short[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Short.valueOf(element), java.lang.Short.TYPE)));
	}

	public static int[] add(final int[] array, final int index, final int element) {
		return ((int[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Integer.valueOf(element), java.lang.Integer.TYPE)));
	}

	public static long[] add(final long[] array, final int index, final long element) {
		return ((long[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Long.valueOf(element), java.lang.Long.TYPE)));
	}

	public static float[] add(final float[] array, final int index, final float element) {
		return ((float[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Float.valueOf(element), java.lang.Float.TYPE)));
	}

	public static double[] add(final double[] array, final int index, final double element) {
		return ((double[])(org.apache.commons.lang3.ArrayUtils.add(array, index, java.lang.Double.valueOf(element), java.lang.Double.TYPE)));
	}

	private static java.lang.Object add(final java.lang.Object array, final int index, final java.lang.Object element, final java.lang.Class<?> clss) {
		if (array == null) {
			if (index != 0) {
				throw new java.lang.IndexOutOfBoundsException((("Index: " + index) + ", Length: 0"));
			} 
			final java.lang.Object joinedArray = java.lang.reflect.Array.newInstance(clss, 1);
			java.lang.reflect.Array.set(joinedArray, 0, element);
			return joinedArray;
		} 
		final int length = java.lang.reflect.Array.getLength(array);
		if ((index > length) || (index < 0)) {
			throw new java.lang.IndexOutOfBoundsException(((("Index: " + index) + ", Length: ") + length));
		} 
		final java.lang.Object result = java.lang.reflect.Array.newInstance(clss, (length + 1));
		java.lang.System.arraycopy(array, 0, result, 0, index);
		java.lang.reflect.Array.set(result, index, element);
		if (index < length) {
			java.lang.System.arraycopy(array, index, result, (index + 1), (length - index));
		} 
		return result;
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	public static <T>T[] remove(final T[] array, final int index) {
		return ((T[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static <T>T[] removeElement(final T[] array, final java.lang.Object element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static boolean[] remove(final boolean[] array, final int index) {
		return ((boolean[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static boolean[] removeElement(final boolean[] array, final boolean element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static byte[] remove(final byte[] array, final int index) {
		return ((byte[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static byte[] removeElement(final byte[] array, final byte element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static char[] remove(final char[] array, final int index) {
		return ((char[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static char[] removeElement(final char[] array, final char element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static double[] remove(final double[] array, final int index) {
		return ((double[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static double[] removeElement(final double[] array, final double element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static float[] remove(final float[] array, final int index) {
		return ((float[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static float[] removeElement(final float[] array, final float element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static int[] remove(final int[] array, final int index) {
		return ((int[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static int[] removeElement(final int[] array, final int element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static long[] remove(final long[] array, final int index) {
		return ((long[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static long[] removeElement(final long[] array, final long element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	public static short[] remove(final short[] array, final int index) {
		return ((short[])(org.apache.commons.lang3.ArrayUtils.remove(((java.lang.Object)(array)), index)));
	}

	public static short[] removeElement(final short[] array, final short element) {
		final int index = org.apache.commons.lang3.ArrayUtils.indexOf(array, element);
		if (index == (org.apache.commons.lang3.ArrayUtils.INDEX_NOT_FOUND)) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	private static java.lang.Object remove(final java.lang.Object array, final int index) {
		final int length = org.apache.commons.lang3.ArrayUtils.getLength(array);
		if ((index < 0) || (index >= length)) {
			throw new java.lang.IndexOutOfBoundsException(((("Index: " + index) + ", Length: ") + length));
		} 
		final java.lang.Object result = java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), (length - 1));
		java.lang.System.arraycopy(array, 0, result, 0, index);
		if (index < (length - 1)) {
			java.lang.System.arraycopy(array, (index + 1), result, index, ((length - index) - 1));
		} 
		return result;
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	public static <T>T[] removeAll(final T[] array, final int... indices) {
		return ((T[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static <T>T[] removeElements(final T[] array, final T... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<T, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<T, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final T v : values) {
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(v);
			if (count == null) {
				occurrences.put(v, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<T, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final T v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v, found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		@java.lang.SuppressWarnings(value = "unchecked")
		final T[] result = ((T[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
		return result;
	}

	public static byte[] removeAll(final byte[] array, final int... indices) {
		return ((byte[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static byte[] removeElements(final byte[] array, final byte... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Byte, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Byte, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final byte v : values) {
			final java.lang.Byte boxed = java.lang.Byte.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Byte, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Byte v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.byteValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((byte[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static short[] removeAll(final short[] array, final int... indices) {
		return ((short[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static short[] removeElements(final short[] array, final short... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Short, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Short, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final short v : values) {
			final java.lang.Short boxed = java.lang.Short.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Short, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Short v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.shortValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((short[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static int[] removeAll(final int[] array, final int... indices) {
		return ((int[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static int[] removeElements(final int[] array, final int... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Integer, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Integer, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final int v : values) {
			final java.lang.Integer boxed = java.lang.Integer.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Integer, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Integer v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.intValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((int[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static char[] removeAll(final char[] array, final int... indices) {
		return ((char[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static char[] removeElements(final char[] array, final char... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Character, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Character, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final char v : values) {
			final java.lang.Character boxed = java.lang.Character.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Character, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Character v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.charValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((char[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static long[] removeAll(final long[] array, final int... indices) {
		return ((long[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static long[] removeElements(final long[] array, final long... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Long, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Long, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final long v : values) {
			final java.lang.Long boxed = java.lang.Long.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Long, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Long v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.longValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((long[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static float[] removeAll(final float[] array, final int... indices) {
		return ((float[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static float[] removeElements(final float[] array, final float... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Float, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Float, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final float v : values) {
			final java.lang.Float boxed = java.lang.Float.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Float, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Float v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.floatValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((float[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static double[] removeAll(final double[] array, final int... indices) {
		return ((double[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static double[] removeElements(final double[] array, final double... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Double, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Double, org.apache.commons.lang3.mutable.MutableInt>(values.length);
		for (final double v : values) {
			final java.lang.Double boxed = java.lang.Double.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Double, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Double v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.doubleValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((double[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	public static boolean[] removeAll(final boolean[] array, final int... indices) {
		return ((boolean[])(org.apache.commons.lang3.ArrayUtils.removeAll(((java.lang.Object)(array)), org.apache.commons.lang3.ArrayUtils.clone(indices))));
	}

	public static boolean[] removeElements(final boolean[] array, final boolean... values) {
		if ((org.apache.commons.lang3.ArrayUtils.isEmpty(array)) || (org.apache.commons.lang3.ArrayUtils.isEmpty(values))) {
			return org.apache.commons.lang3.ArrayUtils.clone(array);
		} 
		final java.util.HashMap<java.lang.Boolean, org.apache.commons.lang3.mutable.MutableInt> occurrences = new java.util.HashMap<java.lang.Boolean, org.apache.commons.lang3.mutable.MutableInt>(2);
		for (final boolean v : values) {
			final java.lang.Boolean boxed = java.lang.Boolean.valueOf(v);
			final org.apache.commons.lang3.mutable.MutableInt count = occurrences.get(boxed);
			if (count == null) {
				occurrences.put(boxed, new org.apache.commons.lang3.mutable.MutableInt(1));
			} else {
				count.increment();
			}
		}
		final java.util.BitSet toRemove = new java.util.BitSet();
		for (final java.util.Map.Entry<java.lang.Boolean, org.apache.commons.lang3.mutable.MutableInt> e : occurrences.entrySet()) {
			final java.lang.Boolean v = e.getKey();
			int found = 0;
			for (int i = 0, ct = e.getValue().intValue() ; i < ct ; i++) {
				found = org.apache.commons.lang3.ArrayUtils.indexOf(array, v.booleanValue(), found);
				if (found < 0) {
					break;
				} 
				toRemove.set((found++));
			}
		}
		return ((boolean[])(org.apache.commons.lang3.ArrayUtils.removeAll(array, toRemove)));
	}

	static java.lang.Object removeAll(final java.lang.Object array, final int... indices) {
		final int length = org.apache.commons.lang3.ArrayUtils.getLength(array);
		int diff = 0;
		if (org.apache.commons.lang3.ArrayUtils.isNotEmpty(indices)) {
			java.util.Arrays.sort(indices);
			int i = indices.length;
			int prevIndex = length;
			while ((--i) >= 0) {
				final int index = indices[i];
				if ((index < 0) || (index >= length)) {
					throw new java.lang.IndexOutOfBoundsException(((("Index: " + index) + ", Length: ") + length));
				} 
				if (index >= prevIndex) {
					continue;
				} 
				diff++;
				prevIndex = index;
			}
		} 
		final java.lang.Object result = java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), (length - diff));
		if (diff < length) {
			int end = length;
			int dest = length - diff;
			for (int i = (indices.length) - 1 ; i >= 0 ; i--) {
				final int index = indices[i];
				if ((end - index) > 1) {
					final int cp = (end - index) - 1;
					dest -= cp;
					java.lang.System.arraycopy(array, (index + 1), result, dest, cp);
				} 
				end = index;
			}
			if (end > 0) {
				java.lang.System.arraycopy(array, 0, result, 0, end);
			} 
		} 
		return result;
	}

	static java.lang.Object removeAll(final java.lang.Object array, final java.util.BitSet indices) {
		final int srcLength = org.apache.commons.lang3.ArrayUtils.getLength(array);
		final int removals = indices.cardinality();
		final java.lang.Object result = java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), (srcLength - removals));
		int srcIndex = 0;
		int destIndex = 0;
		int count;
		int set;
		while ((set = indices.nextSetBit(srcIndex)) != (-1)) {
			count = set - srcIndex;
			if (count > 0) {
				java.lang.System.arraycopy(array, srcIndex, result, destIndex, count);
				destIndex += count;
			} 
			srcIndex = indices.nextClearBit(set);
		}
		count = srcLength - srcIndex;
		if (count > 0) {
			java.lang.System.arraycopy(array, srcIndex, result, destIndex, count);
		} 
		return result;
	}
}

