package org.beginprogramming.web.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("derivativeService")
public interface DerivativeService extends RemoteService {

	CalculationResponse derivativeService(String equation, String evalAt);
}
