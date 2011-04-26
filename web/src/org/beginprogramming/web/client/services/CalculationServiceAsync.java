package org.beginprogramming.web.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalculationServiceAsync {

	void calculateEquation(String equation,
			AsyncCallback<CalculationResponse> callback);

}
