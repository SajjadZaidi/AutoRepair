package org.apache.commons.math3.ode;


public abstract class AbstractIntegrator implements org.apache.commons.math3.ode.FirstOrderIntegrator {
	protected java.util.Collection<org.apache.commons.math3.ode.sampling.StepHandler> stepHandlers;

	protected double stepStart;

	protected double stepSize;

	protected boolean isLastStep;

	protected boolean resetOccurred;

	private java.util.Collection<org.apache.commons.math3.ode.events.EventState> eventsStates;

	private boolean statesInitialized;

	private final java.lang.String name;

	private org.apache.commons.math3.util.Incrementor evaluations;

	private transient org.apache.commons.math3.ode.ExpandableStatefulODE expandable;

	public AbstractIntegrator(final java.lang.String name) {
		this.name = name;
		stepHandlers = new java.util.ArrayList<org.apache.commons.math3.ode.sampling.StepHandler>();
		stepStart = java.lang.Double.NaN;
		stepSize = java.lang.Double.NaN;
		eventsStates = new java.util.ArrayList<org.apache.commons.math3.ode.events.EventState>();
		statesInitialized = false;
		evaluations = new org.apache.commons.math3.util.Incrementor();
		setMaxEvaluations((-1));
		evaluations.resetCount();
	}

	protected AbstractIntegrator() {
		this(null);
	}

	public java.lang.String getName() {
		return name;
	}

	public void addStepHandler(final org.apache.commons.math3.ode.sampling.StepHandler handler) {
		stepHandlers.add(handler);
	}

	public java.util.Collection<org.apache.commons.math3.ode.sampling.StepHandler> getStepHandlers() {
		return java.util.Collections.unmodifiableCollection(stepHandlers);
	}

	public void clearStepHandlers() {
		stepHandlers.clear();
	}

	public void addEventHandler(final org.apache.commons.math3.ode.events.EventHandler handler, final double maxCheckInterval, final double convergence, final int maxIterationCount) {
		addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, new org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver(convergence , 5));
	}

	public void addEventHandler(final org.apache.commons.math3.ode.events.EventHandler handler, final double maxCheckInterval, final double convergence, final int maxIterationCount, final org.apache.commons.math3.analysis.solvers.UnivariateSolver solver) {
		eventsStates.add(new org.apache.commons.math3.ode.events.EventState(handler , maxCheckInterval , convergence , maxIterationCount , solver));
	}

	public java.util.Collection<org.apache.commons.math3.ode.events.EventHandler> getEventHandlers() {
		final java.util.List<org.apache.commons.math3.ode.events.EventHandler> list = new java.util.ArrayList<org.apache.commons.math3.ode.events.EventHandler>(eventsStates.size());
		for (org.apache.commons.math3.ode.events.EventState state : eventsStates) {
			list.add(state.getEventHandler());
		}
		return java.util.Collections.unmodifiableCollection(list);
	}

	public void clearEventHandlers() {
		eventsStates.clear();
	}

	public double getCurrentStepStart() {
		return stepStart;
	}

	public double getCurrentSignedStepsize() {
		return stepSize;
	}

	public void setMaxEvaluations(int maxEvaluations) {
		evaluations.setMaximalCount((maxEvaluations < 0 ? java.lang.Integer.MAX_VALUE : maxEvaluations));
	}

	public int getMaxEvaluations() {
		return evaluations.getMaximalCount();
	}

	public int getEvaluations() {
		return evaluations.getCount();
	}

	protected void initIntegration(final double t0, final double[] y0, final double t) {
		evaluations.resetCount();
		for (final org.apache.commons.math3.ode.events.EventState state : eventsStates) {
			state.setExpandable(expandable);
			state.getEventHandler().init(t0, y0, t);
		}
		for (org.apache.commons.math3.ode.sampling.StepHandler handler : stepHandlers) {
			handler.init(t0, y0, t);
		}
		setStateInitialized(false);
	}

	protected void setEquations(final org.apache.commons.math3.ode.ExpandableStatefulODE equations) {
		org.apache.commons.math3.ode.AbstractIntegrator.this.expandable = equations;
	}

	protected org.apache.commons.math3.ode.ExpandableStatefulODE getExpandable() {
		return expandable;
	}

	protected org.apache.commons.math3.util.Incrementor getEvaluationsCounter() {
		return evaluations;
	}

	public double integrate(final org.apache.commons.math3.ode.FirstOrderDifferentialEquations equations, final double t0, final double[] y0, final double t, final double[] y) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException, org.apache.commons.math3.exception.NumberIsTooSmallException {
		if ((y0.length) != (equations.getDimension())) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(y0.length , equations.getDimension());
		} 
		if ((y.length) != (equations.getDimension())) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(y.length , equations.getDimension());
		} 
		final org.apache.commons.math3.ode.ExpandableStatefulODE expandableODE = new org.apache.commons.math3.ode.ExpandableStatefulODE(equations);
		expandableODE.setTime(t0);
		expandableODE.setPrimaryState(y0);
		integrate(expandableODE, t);
		java.lang.System.arraycopy(expandableODE.getPrimaryState(), 0, y, 0, y.length);
		return expandableODE.getTime();
	}

	public abstract void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE equations, double t) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException, org.apache.commons.math3.exception.NumberIsTooSmallException;

	public void computeDerivatives(final double t, final double[] y, final double[] yDot) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException {
		evaluations.incrementCount();
		expandable.computeDerivatives(t, y, yDot);
	}

	protected void setStateInitialized(final boolean stateInitialized) {
		org.apache.commons.math3.ode.AbstractIntegrator.this.statesInitialized = stateInitialized;
	}

	protected double acceptStep(final org.apache.commons.math3.ode.sampling.AbstractStepInterpolator interpolator, final double[] y, final double[] yDot, final double tEnd) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
		double previousT = interpolator.getGlobalPreviousTime();
		final double currentT = interpolator.getGlobalCurrentTime();
		if (!(statesInitialized)) {
			for (org.apache.commons.math3.ode.events.EventState state : eventsStates) {
				state.reinitializeBegin(interpolator);
			}
			statesInitialized = true;
		} 
		final int orderingSign = interpolator.isForward() ? +1 : -1;
		java.util.SortedSet<org.apache.commons.math3.ode.events.EventState> occurringEvents = new java.util.TreeSet<org.apache.commons.math3.ode.events.EventState>(new java.util.Comparator<org.apache.commons.math3.ode.events.EventState>() {
			public int compare(org.apache.commons.math3.ode.events.EventState es0, org.apache.commons.math3.ode.events.EventState es1) {
				return orderingSign * (java.lang.Double.compare(es0.getEventTime(), es1.getEventTime()));
			}
		});
		for (final org.apache.commons.math3.ode.events.EventState state : eventsStates) {
			if (state.evaluateStep(interpolator)) {
				occurringEvents.add(state);
			} 
		}
		while (!(occurringEvents.isEmpty())) {
			final java.util.Iterator<org.apache.commons.math3.ode.events.EventState> iterator = occurringEvents.iterator();
			final org.apache.commons.math3.ode.events.EventState currentEvent = iterator.next();
			iterator.remove();
			final double eventT = currentEvent.getEventTime();
			interpolator.setSoftPreviousTime(previousT);
			interpolator.setSoftCurrentTime(eventT);
			interpolator.setInterpolatedTime(eventT);
			final double[] eventYComplete = new double[y.length];
			expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), eventYComplete);
			int index = 0;
			for (org.apache.commons.math3.ode.EquationsMapper secondary : expandable.getSecondaryMappers()) {
				secondary.insertEquationData(interpolator.getInterpolatedSecondaryState((index++)), eventYComplete);
			}
			for (final org.apache.commons.math3.ode.events.EventState state : eventsStates) {
				state.stepAccepted(eventT, eventYComplete);
				isLastStep = (isLastStep) || (state.stop());
			}
			for (final org.apache.commons.math3.ode.sampling.StepHandler handler : stepHandlers) {
				handler.handleStep(interpolator, isLastStep);
			}
			if (isLastStep) {
				java.lang.System.arraycopy(eventYComplete, 0, y, 0, y.length);
				return eventT;
			} 
			boolean needReset = false;
			for (final org.apache.commons.math3.ode.events.EventState state : eventsStates) {
				needReset = needReset || (state.reset(eventT, eventYComplete));
			}
			if (needReset) {
				interpolator.setInterpolatedTime(eventT);
				java.lang.System.arraycopy(eventYComplete, 0, y, 0, y.length);
				computeDerivatives(eventT, y, yDot);
				resetOccurred = true;
				return eventT;
			} 
			previousT = eventT;
			interpolator.setSoftPreviousTime(eventT);
			interpolator.setSoftCurrentTime(currentT);
			if (currentEvent.evaluateStep(interpolator)) {
				occurringEvents.add(currentEvent);
			} 
		}
		interpolator.setInterpolatedTime(currentT);
		final double[] currentY = new double[y.length];
		expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), currentY);
		int index = 0;
		for (org.apache.commons.math3.ode.EquationsMapper secondary : expandable.getSecondaryMappers()) {
			secondary.insertEquationData(interpolator.getInterpolatedSecondaryState((index++)), currentY);
		}
		for (final org.apache.commons.math3.ode.events.EventState state : eventsStates) {
			state.stepAccepted(currentT, currentY);
			isLastStep = (isLastStep) || (state.stop());
		}
		isLastStep = (isLastStep) || (org.apache.commons.math3.util.Precision.equals(currentT, tEnd, 1));
		for (org.apache.commons.math3.ode.sampling.StepHandler handler : stepHandlers) {
			handler.handleStep(interpolator, isLastStep);
		}
		return currentT;
	}

	protected void sanityChecks(final org.apache.commons.math3.ode.ExpandableStatefulODE equations, final double t) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NumberIsTooSmallException {
		final double threshold = 1000 * (org.apache.commons.math3.util.FastMath.ulp(org.apache.commons.math3.util.FastMath.max(org.apache.commons.math3.util.FastMath.abs(equations.getTime()), org.apache.commons.math3.util.FastMath.abs(t))));
		final double dt = org.apache.commons.math3.util.FastMath.abs(((equations.getTime()) - t));
		if (dt <= threshold) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL , dt , threshold , false);
		} 
	}
}

