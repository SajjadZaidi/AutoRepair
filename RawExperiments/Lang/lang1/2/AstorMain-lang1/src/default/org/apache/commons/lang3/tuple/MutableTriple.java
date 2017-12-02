package org.apache.commons.lang3.tuple;


public class MutableTriple<L, M, R> extends org.apache.commons.lang3.tuple.Triple<L, M, R> {
	private static final long serialVersionUID = 1L;

	public L left;

	public M middle;

	public R right;

	public static <L, M, R>org.apache.commons.lang3.tuple.MutableTriple<L, M, R> of(final L left, final M middle, final R right) {
		return new org.apache.commons.lang3.tuple.MutableTriple<L, M, R>(left , middle , right);
	}

	public MutableTriple() {
		super();
	}

	public MutableTriple(final L left ,final M middle ,final R right) {
		super();
		org.apache.commons.lang3.tuple.MutableTriple.this.left = left;
		org.apache.commons.lang3.tuple.MutableTriple.this.middle = middle;
		org.apache.commons.lang3.tuple.MutableTriple.this.right = right;
	}

	@java.lang.Override
	public L getLeft() {
		return left;
	}

	public void setLeft(final L left) {
		org.apache.commons.lang3.tuple.MutableTriple.this.left = left;
	}

	@java.lang.Override
	public M getMiddle() {
		return middle;
	}

	public void setMiddle(final M middle) {
		org.apache.commons.lang3.tuple.MutableTriple.this.middle = middle;
	}

	@java.lang.Override
	public R getRight() {
		return right;
	}

	public void setRight(final R right) {
		org.apache.commons.lang3.tuple.MutableTriple.this.right = right;
	}
}

