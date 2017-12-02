package org.apache.commons.math3.ode.events;


public enum FilterType {
TRIGGER_ONLY_DECREASING_EVENTS {
		@java.lang.Override
		protected boolean getTriggeredIncreasing() {
			return false;
		}

		@java.lang.Override
		protected org.apache.commons.math3.ode.events.Transformer selectTransformer(final org.apache.commons.math3.ode.events.Transformer previous, final double g, final boolean forward) {
			if (forward) {
				switch (previous) {
					case UNINITIALIZED :
						if (g > 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else if (g < 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else {
							return org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED;
						}
					case PLUS :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return previous;
						}
					case MINUS :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else {
							return previous;
						}
					case MIN :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else {
							return previous;
						}
					case MAX :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else {
							return previous;
						}
					default :
						throw new org.apache.commons.math3.exception.MathInternalError();
				}
			} else {
				switch (previous) {
					case UNINITIALIZED :
						if (g > 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else if (g < 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED;
						}
					case PLUS :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else {
							return previous;
						}
					case MINUS :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return previous;
						}
					case MIN :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else {
							return previous;
						}
					case MAX :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else {
							return previous;
						}
					default :
						throw new org.apache.commons.math3.exception.MathInternalError();
				}
			}
		}
	}, TRIGGER_ONLY_INCREASING_EVENTS {
		@java.lang.Override
		protected boolean getTriggeredIncreasing() {
			return true;
		}

		@java.lang.Override
		protected org.apache.commons.math3.ode.events.Transformer selectTransformer(final org.apache.commons.math3.ode.events.Transformer previous, final double g, final boolean forward) {
			if (forward) {
				switch (previous) {
					case UNINITIALIZED :
						if (g > 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else if (g < 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED;
						}
					case PLUS :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else {
							return previous;
						}
					case MINUS :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return previous;
						}
					case MIN :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else {
							return previous;
						}
					case MAX :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else {
							return previous;
						}
					default :
						throw new org.apache.commons.math3.exception.MathInternalError();
				}
			} else {
				switch (previous) {
					case UNINITIALIZED :
						if (g > 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else if (g < 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else {
							return org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED;
						}
					case PLUS :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MIN;
						} else {
							return previous;
						}
					case MINUS :
						if (g >= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MAX;
						} else {
							return previous;
						}
					case MIN :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.MINUS;
						} else {
							return previous;
						}
					case MAX :
						if (g <= 0) {
							return org.apache.commons.math3.ode.events.Transformer.PLUS;
						} else {
							return previous;
						}
					default :
						throw new org.apache.commons.math3.exception.MathInternalError();
				}
			}
		}
	};
	protected abstract boolean getTriggeredIncreasing();

	protected abstract org.apache.commons.math3.ode.events.Transformer selectTransformer(org.apache.commons.math3.ode.events.Transformer previous, double g, boolean forward);
}

