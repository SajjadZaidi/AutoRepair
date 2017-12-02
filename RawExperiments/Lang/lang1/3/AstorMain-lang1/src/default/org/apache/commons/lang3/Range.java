package org.apache.commons.lang3;


public final class Range<T> implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private final java.util.Comparator<T> comparator;

	private final T minimum;

	private final T maximum;

	private transient int hashCode;

	private transient java.lang.String toString;

	public static <T extends java.lang.Comparable<T>>org.apache.commons.lang3.Range<T> is(final T element) {
		return org.apache.commons.lang3.Range.between(element, element, null);
	}

	public static <T>org.apache.commons.lang3.Range<T> is(final T element, final java.util.Comparator<T> comparator) {
		return org.apache.commons.lang3.Range.between(element, element, comparator);
	}

	public static <T extends java.lang.Comparable<T>>org.apache.commons.lang3.Range<T> between(final T fromInclusive, final T toInclusive) {
		return org.apache.commons.lang3.Range.between(fromInclusive, toInclusive, null);
	}

	public static <T>org.apache.commons.lang3.Range<T> between(final T fromInclusive, final T toInclusive, final java.util.Comparator<T> comparator) {
		return new org.apache.commons.lang3.Range<T>(fromInclusive , toInclusive , comparator);
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	private Range(final T element1 ,final T element2 ,java.util.Comparator<T> comparator) {
		if ((element1 == null) || (element2 == null)) {
			throw new java.lang.IllegalArgumentException(((("Elements in a range must not be null: element1=" + element1) + ", element2=") + element2));
		} 
		if (comparator == null) {
			comparator = org.apache.commons.lang3.Range.ComparableComparator.INSTANCE;
		} 
		if ((comparator.compare(element1, element2)) < 1) {
			this.minimum = element1;
			this.maximum = element2;
		} else {
			this.minimum = element2;
			this.maximum = element1;
		}
		this.comparator = comparator;
	}

	public T getMinimum() {
		return minimum;
	}

	public T getMaximum() {
		return maximum;
	}

	public java.util.Comparator<T> getComparator() {
		return comparator;
	}

	public boolean isNaturalOrdering() {
		return (comparator) == (org.apache.commons.lang3.Range.ComparableComparator.INSTANCE);
	}

	public boolean contains(final T element) {
		if (element == null) {
			return false;
		} 
		return ((comparator.compare(element, minimum)) > (-1)) && ((comparator.compare(element, maximum)) < 1);
	}

	public boolean isAfter(final T element) {
		if (element == null) {
			return false;
		} 
		return (comparator.compare(element, minimum)) < 0;
	}

	public boolean isStartedBy(final T element) {
		if (element == null) {
			return false;
		} 
		return (comparator.compare(element, minimum)) == 0;
	}

	public boolean isEndedBy(final T element) {
		if (element == null) {
			return false;
		} 
		return (comparator.compare(element, maximum)) == 0;
	}

	public boolean isBefore(final T element) {
		if (element == null) {
			return false;
		} 
		return (comparator.compare(element, maximum)) > 0;
	}

	public int elementCompareTo(final T element) {
		if (element == null) {
			throw new java.lang.NullPointerException("Element is null");
		} 
		if (isAfter(element)) {
			return -1;
		} else if (isBefore(element)) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean containsRange(final org.apache.commons.lang3.Range<T> otherRange) {
		if (otherRange == null) {
			return false;
		} 
		return (contains(otherRange.minimum)) && (contains(otherRange.maximum));
	}

	public boolean isAfterRange(final org.apache.commons.lang3.Range<T> otherRange) {
		if (otherRange == null) {
			return false;
		} 
		return isAfter(otherRange.maximum);
	}

	public boolean isOverlappedBy(final org.apache.commons.lang3.Range<T> otherRange) {
		if (otherRange == null) {
			return false;
		} 
		return ((otherRange.contains(minimum)) || (otherRange.contains(maximum))) || (contains(otherRange.minimum));
	}

	public boolean isBeforeRange(final org.apache.commons.lang3.Range<T> otherRange) {
		if (otherRange == null) {
			return false;
		} 
		return isBefore(otherRange.minimum);
	}

	public org.apache.commons.lang3.Range<T> intersectionWith(final org.apache.commons.lang3.Range<T> other) {
		if (!(org.apache.commons.lang3.Range.this.isOverlappedBy(other))) {
			throw new java.lang.IllegalArgumentException(java.lang.String.format("Cannot calculate intersection with non-overlapping range %s", other));
		} 
		if (org.apache.commons.lang3.Range.this.equals(other)) {
			return org.apache.commons.lang3.Range.this;
		} 
		final T min = (getComparator().compare(minimum, other.minimum)) < 0 ? other.minimum : minimum;
		final T max = (getComparator().compare(maximum, other.maximum)) < 0 ? maximum : other.maximum;
		return org.apache.commons.lang3.Range.between(min, max, getComparator());
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj == (org.apache.commons.lang3.Range.this)) {
			return true;
		} else if ((obj == null) || ((obj.getClass()) != (getClass()))) {
			return false;
		} else {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.lang3.Range<T> range = ((org.apache.commons.lang3.Range<T>)(obj));
			return (minimum.equals(range.minimum)) && (maximum.equals(range.maximum));
		}
	}

	@java.lang.Override
	public int hashCode() {
		int result = hashCode;
		if ((hashCode) == 0) {
			result = 17;
			result = (37 * result) + (getClass().hashCode());
			result = (37 * result) + (minimum.hashCode());
			result = (37 * result) + (maximum.hashCode());
			hashCode = result;
		} 
		return result;
	}

	@java.lang.Override
	public java.lang.String toString() {
		java.lang.String result = toString;
		if (result == null) {
			final java.lang.StringBuilder buf = new java.lang.StringBuilder(32);
			buf.append('[');
			buf.append(minimum);
			buf.append("..");
			buf.append(maximum);
			buf.append(']');
			result = buf.toString();
			toString = result;
		} 
		return result;
	}

	public java.lang.String toString(final java.lang.String format) {
		return java.lang.String.format(format, minimum, maximum, comparator);
	}

	@java.lang.SuppressWarnings(value = { "rawtypes" , "unchecked" })
	private enum ComparableComparator implements java.util.Comparator {
INSTANCE;
		@java.lang.Override
		public int compare(final java.lang.Object obj1, final java.lang.Object obj2) {
			return ((java.lang.Comparable)(obj1)).compareTo(obj2);
		}
	}
}

