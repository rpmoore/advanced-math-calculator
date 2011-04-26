package org.beginprogramming.web.client.services;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calculateService")
public interface CalculationService extends RemoteService {

		CalculationResponse calculateEquation(String equation);
}
