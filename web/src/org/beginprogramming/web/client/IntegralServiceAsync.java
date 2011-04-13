package org.beginprogramming.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IntegralServiceAsync {

	void integralService(String equation, String leftBound, String rightBound,
			AsyncCallback<String> callback);

}
