package org.beginprogramming.web.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DerivativeServiceAsync {

	void derivativeService(String equation, String leftBound,
		AsyncCallback<CalculationResponse> callback);

}
