package org.apache.commons.lang3.text;


public abstract class StrLookup<V> {
	private static final org.apache.commons.lang3.text.StrLookup<java.lang.String> NONE_LOOKUP;

	private static final org.apache.commons.lang3.text.StrLookup<java.lang.String> SYSTEM_PROPERTIES_LOOKUP;

	static {
		NONE_LOOKUP = new org.apache.commons.lang3.text.StrLookup.MapStrLookup<java.lang.String>(null);
		org.apache.commons.lang3.text.StrLookup<java.lang.String> lookup = null;
		try {
			final java.util.Map<?, ?> propMap = java.lang.System.getProperties();
			@java.lang.SuppressWarnings(value = "unchecked")
			final java.util.Map<java.lang.String, java.lang.String> properties = ((java.util.Map<java.lang.String, java.lang.String>)(propMap));
			lookup = new org.apache.commons.lang3.text.StrLookup.MapStrLookup<java.lang.String>(properties);
		} catch (java.lang.SecurityException ex) {
			lookup = org.apache.commons.lang3.text.StrLookup.NONE_LOOKUP;
		}
		SYSTEM_PROPERTIES_LOOKUP = lookup;
	}

	public static org.apache.commons.lang3.text.StrLookup<?> noneLookup() {
		return org.apache.commons.lang3.text.StrLookup.NONE_LOOKUP;
	}

	public static org.apache.commons.lang3.text.StrLookup<java.lang.String> systemPropertiesLookup() {
		return org.apache.commons.lang3.text.StrLookup.SYSTEM_PROPERTIES_LOOKUP;
	}

	public static <V>org.apache.commons.lang3.text.StrLookup<V> mapLookup(java.util.Map<java.lang.String, V> map) {
		return new org.apache.commons.lang3.text.StrLookup.MapStrLookup<V>(map);
	}

	protected StrLookup() {
		super();
	}

	public abstract java.lang.String lookup(java.lang.String key);

	static class MapStrLookup<V> extends org.apache.commons.lang3.text.StrLookup<V> {
		private final java.util.Map<java.lang.String, V> map;

		MapStrLookup(java.util.Map<java.lang.String, V> map) {
			this.map = map;
		}

		@java.lang.Override
		public java.lang.String lookup(java.lang.String key) {
			if ((map) == null) {
				return null;
			} 
			java.lang.Object obj = map.get(key);
			if (obj == null) {
				return null;
			} 
			return obj.toString();
		}
	}
}

