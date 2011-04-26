package org.beginprogramming.web.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IntegralServiceAsync {

	void integralService(String equation, String leftBound, String rightBound,
		AsyncCallback<CalculationResponse> callback);

}
