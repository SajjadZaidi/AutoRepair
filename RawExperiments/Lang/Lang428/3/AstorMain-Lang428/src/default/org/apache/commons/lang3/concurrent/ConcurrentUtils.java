package org.apache.commons.lang3.concurrent;


public class ConcurrentUtils {
	private ConcurrentUtils() {
	}

	public static org.apache.commons.lang3.concurrent.ConcurrentException extractCause(java.util.concurrent.ExecutionException ex) {
		if ((ex == null) || ((ex.getCause()) == null)) {
			return null;
		} 
		org.apache.commons.lang3.concurrent.ConcurrentUtils.throwCause(ex);
		return new org.apache.commons.lang3.concurrent.ConcurrentException(ex.getMessage() , ex.getCause());
	}

	public static org.apache.commons.lang3.concurrent.ConcurrentRuntimeException extractCauseUnchecked(java.util.concurrent.ExecutionException ex) {
		if ((ex == null) || ((ex.getCause()) == null)) {
			return null;
		} 
		org.apache.commons.lang3.concurrent.ConcurrentUtils.throwCause(ex);
		return new org.apache.commons.lang3.concurrent.ConcurrentRuntimeException(ex.getMessage() , ex.getCause());
	}

	public static void handleCause(java.util.concurrent.ExecutionException ex) throws org.apache.commons.lang3.concurrent.ConcurrentException {
		org.apache.commons.lang3.concurrent.ConcurrentException cex = org.apache.commons.lang3.concurrent.ConcurrentUtils.extractCause(ex);
		if (cex != null) {
			throw cex;
		} 
	}

	public static void handleCauseUnchecked(java.util.concurrent.ExecutionException ex) {
		org.apache.commons.lang3.concurrent.ConcurrentRuntimeException crex = org.apache.commons.lang3.concurrent.ConcurrentUtils.extractCauseUnchecked(ex);
		if (crex != null) {
			throw crex;
		} 
	}

	static java.lang.Throwable checkedException(java.lang.Throwable ex) {
		if (((ex != null) && (!(ex instanceof java.lang.RuntimeException))) && (!(ex instanceof java.lang.Error))) {
			return ex;
		} else {
			throw new java.lang.IllegalArgumentException(("Not a checked exception: " + ex));
		}
	}

	private static void throwCause(java.util.concurrent.ExecutionException ex) {
		if ((ex.getCause()) instanceof java.lang.RuntimeException) {
			throw ((java.lang.RuntimeException)(ex.getCause()));
		} 
		if ((ex.getCause()) instanceof java.lang.Error) {
			throw ((java.lang.Error)(ex.getCause()));
		} 
	}

	public static <T>T initialize(org.apache.commons.lang3.concurrent.ConcurrentInitializer<T> initializer) throws org.apache.commons.lang3.concurrent.ConcurrentException {
		return initializer != null ? initializer.get() : null;
	}

	public static <T>T initializeUnchecked(org.apache.commons.lang3.concurrent.ConcurrentInitializer<T> initializer) {
		try {
			return org.apache.commons.lang3.concurrent.ConcurrentUtils.initialize(initializer);
		} catch (org.apache.commons.lang3.concurrent.ConcurrentException cex) {
			throw new org.apache.commons.lang3.concurrent.ConcurrentRuntimeException(cex.getCause());
		}
	}

	public static <K, V>V putIfAbsent(java.util.concurrent.ConcurrentMap<K, V> map, K key, V value) {
		if (map == null) {
			return null;
		} 
		V result = map.putIfAbsent(key, value);
		return result != null ? result : value;
	}

	public static <K, V>V createIfAbsent(java.util.concurrent.ConcurrentMap<K, V> map, K key, org.apache.commons.lang3.concurrent.ConcurrentInitializer<V> init) throws org.apache.commons.lang3.concurrent.ConcurrentException {
		if ((map == null) || (init == null)) {
			return null;
		} 
		V value = map.get(key);
		if (value == null) {
			return org.apache.commons.lang3.concurrent.ConcurrentUtils.putIfAbsent(map, key, init.get());
		} 
		return value;
	}

	public static <K, V>V createIfAbsentUnchecked(java.util.concurrent.ConcurrentMap<K, V> map, K key, org.apache.commons.lang3.concurrent.ConcurrentInitializer<V> init) {
		try {
			return org.apache.commons.lang3.concurrent.ConcurrentUtils.createIfAbsent(map, key, init);
		} catch (org.apache.commons.lang3.concurrent.ConcurrentException cex) {
			throw new org.apache.commons.lang3.concurrent.ConcurrentRuntimeException(cex.getCause());
		}
	}

	public static <T>java.util.concurrent.Future<T> constantFuture(T value) {
		return new org.apache.commons.lang3.concurrent.ConcurrentUtils.ConstantFuture<T>(value);
	}

	static final class ConstantFuture<T> implements java.util.concurrent.Future<T> {
		private final T value;

		ConstantFuture(T value) {
			this.value = value;
		}

		public boolean isDone() {
			return true;
		}

		public T get() {
			return value;
		}

		public T get(long timeout, java.util.concurrent.TimeUnit unit) {
			return value;
		}

		public boolean isCancelled() {
			return false;
		}

		public boolean cancel(boolean mayInterruptIfRunning) {
			return false;
		}
	}
}

