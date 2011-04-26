package org.beginprogramming.web.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("integralService")
public interface IntegralService extends RemoteService {

	CalculationResponse integralService(String equation,String leftBound,String rightBound);
}
