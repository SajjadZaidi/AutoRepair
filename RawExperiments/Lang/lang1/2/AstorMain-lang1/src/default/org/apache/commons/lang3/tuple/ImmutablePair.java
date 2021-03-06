package org.apache.commons.lang3.tuple;


public final class ImmutablePair<L, R> extends org.apache.commons.lang3.tuple.Pair<L, R> {
	private static final long serialVersionUID = 4954918890077093841L;

	public final L left;

	public final R right;

	public static <L, R>org.apache.commons.lang3.tuple.ImmutablePair<L, R> of(final L left, final R right) {
		return new org.apache.commons.lang3.tuple.ImmutablePair<L, R>(left , right);
	}

	public ImmutablePair(final L left ,final R right) {
		super();
		this.left = left;
		this.right = right;
	}

	@java.lang.Override
	public L getLeft() {
		return left;
	}

	@java.lang.Override
	public R getRight() {
		return right;
	}

	@java.lang.Override
	public R setValue(final R value) {
		throw new java.lang.UnsupportedOperationException();
	}
}

