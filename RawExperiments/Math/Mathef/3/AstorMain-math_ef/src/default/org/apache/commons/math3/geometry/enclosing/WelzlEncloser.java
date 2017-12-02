package org.apache.commons.math3.geometry.enclosing;


public class WelzlEncloser<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> implements org.apache.commons.math3.geometry.enclosing.Encloser<S, P> {
	private final double tolerance;

	private final org.apache.commons.math3.geometry.enclosing.SupportBallGenerator<S, P> generator;

	public WelzlEncloser(final double tolerance ,final org.apache.commons.math3.geometry.enclosing.SupportBallGenerator<S, P> generator) {
		this.tolerance = tolerance;
		this.generator = generator;
	}

	public org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> enclose(final java.lang.Iterable<P> points) {
		if ((points == null) || (!(points.iterator().hasNext()))) {
			return generator.ballOnSupport(new java.util.ArrayList<P>());
		} 
		return pivotingBall(points);
	}

	private org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> pivotingBall(final java.lang.Iterable<P> points) {
		final P first = points.iterator().next();
		final java.util.List<P> extreme = new java.util.ArrayList<P>(((first.getSpace().getDimension()) + 1));
		final java.util.List<P> support = new java.util.ArrayList<P>(((first.getSpace().getDimension()) + 1));
		extreme.add(first);
		org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> ball = moveToFrontBall(extreme, extreme.size(), support);
		while (true) {
			final P farthest = selectFarthest(points, ball);
			if (ball.contains(farthest, tolerance)) {
				return ball;
			} 
			support.clear();
			support.add(farthest);
			org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> savedBall = ball;
			ball = moveToFrontBall(extreme, extreme.size(), support);
			if ((ball.getRadius()) < (savedBall.getRadius())) {
				throw new org.apache.commons.math3.exception.MathInternalError();
			} 
			extreme.add(0, farthest);
			extreme.subList(ball.getSupportSize(), extreme.size()).clear();
		}
	}

	private org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> moveToFrontBall(final java.util.List<P> extreme, final int nbExtreme, final java.util.List<P> support) {
		org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> ball = generator.ballOnSupport(support);
		if ((ball.getSupportSize()) <= (ball.getCenter().getSpace().getDimension())) {
			for (int i = 0 ; i < nbExtreme ; ++i) {
				final P pi = extreme.get(i);
				if (!(ball.contains(pi, tolerance))) {
					support.add(pi);
					ball = moveToFrontBall(extreme, i, support);
					support.remove(((support.size()) - 1));
					for (int j = i ; j > 0 ; --j) {
						extreme.set(j, extreme.get((j - 1)));
					}
					extreme.set(0, pi);
				} 
			}
		} 
		return ball;
	}

	public P selectFarthest(final java.lang.Iterable<P> points, final org.apache.commons.math3.geometry.enclosing.EnclosingBall<S, P> ball) {
		final P center = ball.getCenter();
		P farthest = null;
		double dMax = -1.0;
		for (final P point : points) {
			final double d = point.distance(center);
			if (d > dMax) {
				farthest = point;
				dMax = d;
			} 
		}
		return farthest;
	}
}

