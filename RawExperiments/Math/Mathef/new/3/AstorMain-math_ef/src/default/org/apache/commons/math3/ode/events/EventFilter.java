package org.apache.commons.math3.ode.events;


public class EventFilter implements org.apache.commons.math3.ode.events.EventHandler {
	private static final int HISTORY_SIZE = 100;

	private final org.apache.commons.math3.ode.events.EventHandler rawHandler;

	private final org.apache.commons.math3.ode.events.FilterType filter;

	private final org.apache.commons.math3.ode.events.Transformer[] transformers;

	private final double[] updates;

	private boolean forward;

	private double extremeT;

	public EventFilter(final org.apache.commons.math3.ode.events.EventHandler rawHandler ,final org.apache.commons.math3.ode.events.FilterType filter) {
		this.rawHandler = rawHandler;
		this.filter = filter;
		this.transformers = new org.apache.commons.math3.ode.events.Transformer[org.apache.commons.math3.ode.events.EventFilter.HISTORY_SIZE];
		this.updates = new double[org.apache.commons.math3.ode.events.EventFilter.HISTORY_SIZE];
	}

	public void init(double t0, double[] y0, double t) {
		rawHandler.init(t0, y0, t);
		forward = t >= t0;
		extremeT = forward ? java.lang.Double.NEGATIVE_INFINITY : java.lang.Double.POSITIVE_INFINITY;
		java.util.Arrays.fill(transformers, org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED);
		java.util.Arrays.fill(updates, extremeT);
	}

	public double g(double t, double[] y) {
		final double rawG = rawHandler.g(t, y);
		if (forward) {
			final int last = (transformers.length) - 1;
			if ((extremeT) < t) {
				final org.apache.commons.math3.ode.events.Transformer previous = transformers[last];
				final org.apache.commons.math3.ode.events.Transformer next = filter.selectTransformer(previous, rawG, forward);
				if (next != previous) {
					java.lang.System.arraycopy(updates, 1, updates, 0, last);
					java.lang.System.arraycopy(transformers, 1, transformers, 0, last);
					updates[last] = extremeT;
					transformers[last] = next;
				} 
				extremeT = t;
				return next.transformed(rawG);
			} else {
				for (int i = last ; i > 0 ; --i) {
					if ((updates[i]) <= t) {
						return transformers[i].transformed(rawG);
					} 
				}
				return transformers[0].transformed(rawG);
			}
		} else {
			if (t < (extremeT)) {
				final org.apache.commons.math3.ode.events.Transformer previous = transformers[0];
				final org.apache.commons.math3.ode.events.Transformer next = filter.selectTransformer(previous, rawG, forward);
				if (next != previous) {
					java.lang.System.arraycopy(updates, 0, updates, 1, ((updates.length) - 1));
					java.lang.System.arraycopy(transformers, 0, transformers, 1, ((transformers.length) - 1));
					updates[0] = extremeT;
					transformers[0] = next;
				} 
				extremeT = t;
				return next.transformed(rawG);
			} else {
				for (int i = 0 ; i < ((updates.length) - 1) ; ++i) {
					if (t <= (updates[i])) {
						return transformers[i].transformed(rawG);
					} 
				}
				return transformers[((updates.length) - 1)].transformed(rawG);
			}
		}
	}

	public org.apache.commons.math3.ode.events.EventHandler.Action eventOccurred(double t, double[] y, boolean increasing) {
		return rawHandler.eventOccurred(t, y, filter.getTriggeredIncreasing());
	}

	public void resetState(double t, double[] y) {
		rawHandler.resetState(t, y);
	}
}

