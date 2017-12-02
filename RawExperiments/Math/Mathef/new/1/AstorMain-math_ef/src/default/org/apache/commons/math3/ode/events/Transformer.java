package org.apache.commons.math3.ode.events;


enum Transformer {
UNINITIALIZED {
		@java.lang.Override
		protected double transformed(final double g) {
			return 0;
		}
	}, PLUS {
		@java.lang.Override
		protected double transformed(final double g) {
			return g;
		}
	}, MINUS {
		@java.lang.Override
		protected double transformed(final double g) {
			return -g;
		}
	}, MIN {
		@java.lang.Override
		protected double transformed(final double g) {
			return org.apache.commons.math3.util.FastMath.min((-(org.apache.commons.math3.util.Precision.SAFE_MIN)), org.apache.commons.math3.util.FastMath.min((-g), (+g)));
		}
	}, MAX {
		@java.lang.Override
		protected double transformed(final double g) {
			return org.apache.commons.math3.util.FastMath.max((+(org.apache.commons.math3.util.Precision.SAFE_MIN)), org.apache.commons.math3.util.FastMath.max((-g), (+g)));
		}
	};
	protected abstract double transformed(double g);
}

