package org.apache.commons.lang3.builder;


public class ReflectionToStringBuilder extends org.apache.commons.lang3.builder.ToStringBuilder {
	public static java.lang.String toString(final java.lang.Object object) {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(object, null, false, false, null);
	}

	public static java.lang.String toString(final java.lang.Object object, final org.apache.commons.lang3.builder.ToStringStyle style) {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(object, style, false, false, null);
	}

	public static java.lang.String toString(final java.lang.Object object, final org.apache.commons.lang3.builder.ToStringStyle style, final boolean outputTransients) {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(object, style, outputTransients, false, null);
	}

	public static java.lang.String toString(final java.lang.Object object, final org.apache.commons.lang3.builder.ToStringStyle style, final boolean outputTransients, final boolean outputStatics) {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(object, style, outputTransients, outputStatics, null);
	}

	public static <T>java.lang.String toString(final T object, final org.apache.commons.lang3.builder.ToStringStyle style, final boolean outputTransients, final boolean outputStatics, final java.lang.Class<? super T> reflectUpToClass) {
		return new org.apache.commons.lang3.builder.ReflectionToStringBuilder(object , style , null , reflectUpToClass , outputTransients , outputStatics).toString();
	}

	public static java.lang.String toStringExclude(final java.lang.Object object, final java.util.Collection<java.lang.String> excludeFieldNames) {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toStringExclude(object, org.apache.commons.lang3.builder.ReflectionToStringBuilder.toNoNullStringArray(excludeFieldNames));
	}

	static java.lang.String[] toNoNullStringArray(final java.util.Collection<java.lang.String> collection) {
		if (collection == null) {
			return org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY;
		} 
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toNoNullStringArray(collection.toArray());
	}

	static java.lang.String[] toNoNullStringArray(final java.lang.Object[] array) {
		final java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>(array.length);
		for (final java.lang.Object e : array) {
			if (e != null) {
				list.add(e.toString());
			} 
		}
		return list.toArray(org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY);
	}

	public static java.lang.String toStringExclude(final java.lang.Object object, final java.lang.String... excludeFieldNames) {
		return new org.apache.commons.lang3.builder.ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
	}

	private boolean appendStatics = false;

	private boolean appendTransients = false;

	protected java.lang.String[] excludeFieldNames;

	private java.lang.Class<?> upToClass = null;

	public ReflectionToStringBuilder(final java.lang.Object object) {
		super(object);
	}

	public ReflectionToStringBuilder(final java.lang.Object object ,final org.apache.commons.lang3.builder.ToStringStyle style) {
		super(object, style);
	}

	public ReflectionToStringBuilder(final java.lang.Object object ,final org.apache.commons.lang3.builder.ToStringStyle style ,final java.lang.StringBuffer buffer) {
		super(object, style, buffer);
	}

	public <T>ReflectionToStringBuilder(final T object ,final org.apache.commons.lang3.builder.ToStringStyle style ,final java.lang.StringBuffer buffer ,final java.lang.Class<? super T> reflectUpToClass ,final boolean outputTransients ,final boolean outputStatics) {
		super(object, style, buffer);
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.setUpToClass(reflectUpToClass);
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.setAppendTransients(outputTransients);
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.setAppendStatics(outputStatics);
	}

	protected boolean accept(final java.lang.reflect.Field field) {
		if ((field.getName().indexOf(org.apache.commons.lang3.ClassUtils.INNER_CLASS_SEPARATOR_CHAR)) != (-1)) {
			return false;
		} 
		if ((java.lang.reflect.Modifier.isTransient(field.getModifiers())) && (!(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.isAppendTransients()))) {
			return false;
		} 
		if ((java.lang.reflect.Modifier.isStatic(field.getModifiers())) && (!(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.isAppendStatics()))) {
			return false;
		} 
		if (((org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames) != null) && ((java.util.Arrays.binarySearch(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames, field.getName())) >= 0)) {
			return false;
		} 
		return true;
	}

	protected void appendFieldsIn(final java.lang.Class<?> clazz) {
		if (clazz.isArray()) {
			org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.reflectionAppendArray(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getObject());
			return ;
		} 
		final java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		java.lang.reflect.AccessibleObject.setAccessible(fields, true);
		for (final java.lang.reflect.Field field : fields) {
			final java.lang.String fieldName = field.getName();
			if (org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.accept(field)) {
				try {
					final java.lang.Object fieldValue = org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getValue(field);
					org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.append(fieldName, fieldValue);
				} catch (final java.lang.IllegalAccessException ex) {
					throw new java.lang.InternalError(("Unexpected IllegalAccessException: " + (ex.getMessage())));
				}
			} 
		}
	}

	public java.lang.String[] getExcludeFieldNames() {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames.clone();
	}

	public java.lang.Class<?> getUpToClass() {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.upToClass;
	}

	protected java.lang.Object getValue(final java.lang.reflect.Field field) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException {
		return field.get(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getObject());
	}

	public boolean isAppendStatics() {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendStatics;
	}

	public boolean isAppendTransients() {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendTransients;
	}

	public org.apache.commons.lang3.builder.ReflectionToStringBuilder reflectionAppendArray(final java.lang.Object array) {
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getStyle().reflectionAppendArrayDetail(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getStringBuffer(), null, array);
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this;
	}

	public void setAppendStatics(final boolean appendStatics) {
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendStatics = appendStatics;
	}

	public void setAppendTransients(final boolean appendTransients) {
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendTransients = appendTransients;
	}

	public org.apache.commons.lang3.builder.ReflectionToStringBuilder setExcludeFieldNames(final java.lang.String... excludeFieldNamesParam) {
		if (excludeFieldNamesParam == null) {
			org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames = null;
		} else {
			org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames = org.apache.commons.lang3.builder.ReflectionToStringBuilder.toNoNullStringArray(excludeFieldNamesParam);
			java.util.Arrays.sort(org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.excludeFieldNames);
		}
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this;
	}

	public void setUpToClass(final java.lang.Class<?> clazz) {
		if (clazz != null) {
			final java.lang.Object object = getObject();
			if ((object != null) && ((clazz.isInstance(object)) == false)) {
				throw new java.lang.IllegalArgumentException("Specified class is not a superclass of the object");
			} 
		} 
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.upToClass = clazz;
	}

	@java.lang.Override
	public java.lang.String toString() {
		if ((org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getObject()) == null) {
			return org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getStyle().getNullText();
		} 
		java.lang.Class<?> clazz = org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getObject().getClass();
		org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendFieldsIn(clazz);
		while (((clazz.getSuperclass()) != null) && (clazz != (org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.getUpToClass()))) {
			clazz = clazz.getSuperclass();
			org.apache.commons.lang3.builder.ReflectionToStringBuilder.this.appendFieldsIn(clazz);
		}
		return super.toString();
	}
}

