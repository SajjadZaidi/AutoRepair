package org.apache.commons.math3.exception;


public class TooManyEvaluationsException extends org.apache.commons.math3.exception.MaxCountExceededException {
	private static final long serialVersionUID = 4330003017885151975L;

	public TooManyEvaluationsException(java.lang.Number max) {
		super(max);
		getContext().addMessage(org.apache.commons.math3.exception.util.LocalizedFormats.EVALUATIONS);
	}
}

