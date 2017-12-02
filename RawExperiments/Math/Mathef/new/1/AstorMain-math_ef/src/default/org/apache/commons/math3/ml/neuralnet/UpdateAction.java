package org.apache.commons.math3.ml.neuralnet;


public interface UpdateAction {
	void update(org.apache.commons.math3.ml.neuralnet.Network net, double[] features);
}

