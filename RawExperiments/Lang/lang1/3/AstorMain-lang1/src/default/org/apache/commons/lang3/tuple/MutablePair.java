package org.apache.commons.lang3.tuple;


public class MutablePair<L, R> extends org.apache.commons.lang3.tuple.Pair<L, R> {
	private static final long serialVersionUID = 4954918890077093841L;

	public L left;

	public R right;

	public static <L, R>org.apache.commons.lang3.tuple.MutablePair<L, R> of(final L left, final R right) {
		return new org.apache.commons.lang3.tuple.MutablePair<L, R>(left , right);
	}

	public MutablePair() {
		super();
	}

	public MutablePair(final L left ,final R right) {
		super();
		org.apache.commons.lang3.tuple.MutablePair.this.left = left;
		org.apache.commons.lang3.tuple.MutablePair.this.right = right;
	}

	@java.lang.Override
	public L getLeft() {
		return left;
	}

	public void setLeft(final L left) {
		org.apache.commons.lang3.tuple.MutablePair.this.left = left;
	}

	@java.lang.Override
	public R getRight() {
		return right;
	}

	public void setRight(final R right) {
		org.apache.commons.lang3.tuple.MutablePair.this.right = right;
	}

	@java.lang.Override
	public R setValue(final R value) {
		final R result = getRight();
		setRight(value);
		return result;
	}
}

