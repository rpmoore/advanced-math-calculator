package org.beginprogramming.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("integral")
public interface IntegralService extends RemoteService {

	String integralService(String equation,String leftBound,String rightBound);
}
