package org.apache.commons.math3.ml.neuralnet.sofm;


public class KohonenTrainingTask implements java.lang.Runnable {
	private final org.apache.commons.math3.ml.neuralnet.Network net;

	private final java.util.Iterator<double[]> featuresIterator;

	private final org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction updateAction;

	public KohonenTrainingTask(org.apache.commons.math3.ml.neuralnet.Network net ,java.util.Iterator<double[]> featuresIterator ,org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction updateAction) {
		this.net = net;
		this.featuresIterator = featuresIterator;
		this.updateAction = updateAction;
	}

	public void run() {
		while (featuresIterator.hasNext()) {
			updateAction.update(net, featuresIterator.next());
		}
	}
}

