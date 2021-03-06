package org.apache.commons.math3.stat;


public class Frequency implements java.io.Serializable {
	private static final long serialVersionUID = -3845586908418844111L;

	private final java.util.TreeMap<java.lang.Comparable<?>, java.lang.Long> freqTable;

	public Frequency() {
		freqTable = new java.util.TreeMap<java.lang.Comparable<?>, java.lang.Long>();
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	public Frequency(java.util.Comparator<?> comparator) {
		freqTable = new java.util.TreeMap<java.lang.Comparable<?>, java.lang.Long>(((java.util.Comparator<? super java.lang.Comparable<?>>)(comparator)));
	}

	@java.lang.Override
	public java.lang.String toString() {
		java.text.NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		java.lang.StringBuilder outBuffer = new java.lang.StringBuilder();
		outBuffer.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
		java.util.Iterator<java.lang.Comparable<?>> iter = freqTable.keySet().iterator();
		while (iter.hasNext()) {
			java.lang.Comparable<?> value = iter.next();
			outBuffer.append(value);
			outBuffer.append('\t');
			outBuffer.append(getCount(value));
			outBuffer.append('\t');
			outBuffer.append(nf.format(getPct(value)));
			outBuffer.append('\t');
			outBuffer.append(nf.format(getCumPct(value)));
			outBuffer.append('\n');
		}
		return outBuffer.toString();
	}

	public void addValue(java.lang.Comparable<?> v) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		incrementValue(v, 1);
	}

	public void addValue(int v) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		addValue(java.lang.Long.valueOf(v));
	}

	public void addValue(long v) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		addValue(java.lang.Long.valueOf(v));
	}

	public void addValue(char v) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		addValue(java.lang.Character.valueOf(v));
	}

	public void incrementValue(java.lang.Comparable<?> v, long increment) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		java.lang.Comparable<?> obj = v;
		if (v instanceof java.lang.Integer) {
			obj = java.lang.Long.valueOf(((java.lang.Integer)(v)).longValue());
		} 
		try {
			java.lang.Long count = freqTable.get(obj);
			if (count == null) {
				freqTable.put(obj, java.lang.Long.valueOf(increment));
			} else {
				freqTable.put(obj, java.lang.Long.valueOf(((count.longValue()) + increment)));
			}
		} catch (java.lang.ClassCastException ex) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES , v.getClass().getName());
		}
	}

	public void incrementValue(int v, long increment) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		incrementValue(java.lang.Long.valueOf(v), increment);
	}

	public void incrementValue(long v, long increment) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		incrementValue(java.lang.Long.valueOf(v), increment);
	}

	public void incrementValue(char v, long increment) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		incrementValue(java.lang.Character.valueOf(v), increment);
	}

	public void clear() {
		freqTable.clear();
	}

	public java.util.Iterator<java.lang.Comparable<?>> valuesIterator() {
		return freqTable.keySet().iterator();
	}

	public java.util.Iterator<java.util.Map.Entry<java.lang.Comparable<?>, java.lang.Long>> entrySetIterator() {
		return freqTable.entrySet().iterator();
	}

	public long getSumFreq() {
		long result = 0;
		java.util.Iterator<java.lang.Long> iterator = freqTable.values().iterator();
		while (iterator.hasNext()) {
			result += iterator.next().longValue();
		}
		return result;
	}

	public long getCount(java.lang.Comparable<?> v) {
		if (v instanceof java.lang.Integer) {
			return getCount(((java.lang.Integer)(v)).longValue());
		} 
		long result = 0;
		try {
			java.lang.Long count = freqTable.get(v);
			if (count != null) {
				result = count.longValue();
			} 
		} catch (java.lang.ClassCastException ex) {
		}
		return result;
	}

	public long getCount(int v) {
		return getCount(java.lang.Long.valueOf(v));
	}

	public long getCount(long v) {
		return getCount(java.lang.Long.valueOf(v));
	}

	public long getCount(char v) {
		return getCount(java.lang.Character.valueOf(v));
	}

	public int getUniqueCount() {
		return freqTable.keySet().size();
	}

	public double getPct(java.lang.Comparable<?> v) {
		final long sumFreq = getSumFreq();
		if (sumFreq == 0) {
			return java.lang.Double.NaN;
		} 
		return ((double)(getCount(v))) / ((double)(sumFreq));
	}

	public double getPct(int v) {
		return getPct(java.lang.Long.valueOf(v));
	}

	public double getPct(long v) {
		return getPct(java.lang.Long.valueOf(v));
	}

	public double getPct(char v) {
		return getPct(java.lang.Character.valueOf(v));
	}

	@java.lang.SuppressWarnings(value = { "rawtypes" , "unchecked" })
	public long getCumFreq(java.lang.Comparable<?> v) {
		if ((getSumFreq()) == 0) {
			return 0;
		} 
		if (v instanceof java.lang.Integer) {
			return getCumFreq(((java.lang.Integer)(v)).longValue());
		} 
		java.util.Comparator<java.lang.Comparable<?>> c = ((java.util.Comparator<java.lang.Comparable<?>>)(freqTable.comparator()));
		if (c == null) {
			c = new org.apache.commons.math3.stat.Frequency.NaturalComparator();
		} 
		long result = 0;
		try {
			java.lang.Long value = freqTable.get(v);
			if (value != null) {
				result = value.longValue();
			} 
		} catch (java.lang.ClassCastException ex) {
			return result;
		}
		if ((c.compare(v, freqTable.firstKey())) < 0) {
			return 0;
		} 
		if ((c.compare(v, freqTable.lastKey())) >= 0) {
			return getSumFreq();
		} 
		java.util.Iterator<java.lang.Comparable<?>> values = valuesIterator();
		while (values.hasNext()) {
			java.lang.Comparable<?> nextValue = values.next();
			if ((c.compare(v, nextValue)) > 0) {
				result += getCount(nextValue);
			} else {
				return result;
			}
		}
		return result;
	}

	public long getCumFreq(int v) {
		return getCumFreq(java.lang.Long.valueOf(v));
	}

	public long getCumFreq(long v) {
		return getCumFreq(java.lang.Long.valueOf(v));
	}

	public long getCumFreq(char v) {
		return getCumFreq(java.lang.Character.valueOf(v));
	}

	public double getCumPct(java.lang.Comparable<?> v) {
		final long sumFreq = getSumFreq();
		if (sumFreq == 0) {
			return java.lang.Double.NaN;
		} 
		return ((double)(getCumFreq(v))) / ((double)(sumFreq));
	}

	public double getCumPct(int v) {
		return getCumPct(java.lang.Long.valueOf(v));
	}

	public double getCumPct(long v) {
		return getCumPct(java.lang.Long.valueOf(v));
	}

	public double getCumPct(char v) {
		return getCumPct(java.lang.Character.valueOf(v));
	}

	public java.util.List<java.lang.Comparable<?>> getMode() {
		long mostPopular = 0;
		for (java.lang.Long l : freqTable.values()) {
			long frequency = l.longValue();
			if (frequency > mostPopular) {
				mostPopular = frequency;
			} 
		}
		java.util.List<java.lang.Comparable<?>> modeList = new java.util.ArrayList<java.lang.Comparable<?>>();
		for (java.util.Map.Entry<java.lang.Comparable<?>, java.lang.Long> ent : freqTable.entrySet()) {
			long frequency = ent.getValue().longValue();
			if (frequency == mostPopular) {
				modeList.add(ent.getKey());
			} 
		}
		return modeList;
	}

	public void merge(final org.apache.commons.math3.stat.Frequency other) throws org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(other, org.apache.commons.math3.exception.util.LocalizedFormats.NULL_NOT_ALLOWED);
		final java.util.Iterator<java.util.Map.Entry<java.lang.Comparable<?>, java.lang.Long>> iter = other.entrySetIterator();
		while (iter.hasNext()) {
			final java.util.Map.Entry<java.lang.Comparable<?>, java.lang.Long> entry = iter.next();
			incrementValue(entry.getKey(), entry.getValue().longValue());
		}
	}

	public void merge(final java.util.Collection<org.apache.commons.math3.stat.Frequency> others) throws org.apache.commons.math3.exception.NullArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(others, org.apache.commons.math3.exception.util.LocalizedFormats.NULL_NOT_ALLOWED);
		for (final org.apache.commons.math3.stat.Frequency freq : others) {
			merge(freq);
		}
	}

	private static class NaturalComparator<T extends java.lang.Comparable<T>> implements java.io.Serializable , java.util.Comparator<java.lang.Comparable<T>> {
		private static final long serialVersionUID = -3852193713161395148L;

		@java.lang.SuppressWarnings(value = "unchecked")
		public int compare(java.lang.Comparable<T> o1, java.lang.Comparable<T> o2) {
			return o1.compareTo(((T)(o2)));
		}
	}

	@java.lang.Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((freqTable) == null ? 0 : freqTable.hashCode());
		return result;
	}

	@java.lang.Override
	public boolean equals(java.lang.Object obj) {
		if ((org.apache.commons.math3.stat.Frequency.this) == obj) {
			return true;
		} 
		if (!(obj instanceof org.apache.commons.math3.stat.Frequency)) {
			return false;
		} 
		org.apache.commons.math3.stat.Frequency other = ((org.apache.commons.math3.stat.Frequency)(obj));
		if ((freqTable) == null) {
			if ((other.freqTable) != null) {
				return false;
			} 
		} else if (!(freqTable.equals(other.freqTable))) {
			return false;
		} 
		return true;
	}
}

