package org.beginprogramming.web.server.services;

import java.io.StringReader;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defDerivative.CentralDifference;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;
import org.advancedMathCalculator.parser.cc.TokenMgrError;
import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.DerivativeService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DerivativeServiceImpl extends RemoteServiceServlet implements DerivativeService {

	private static final long serialVersionUID = 7568205615675754231L;

	@Override
	public CalculationResponse derivativeService(String equation, String evalAt) {
		final StringBuilder returnString = new StringBuilder();
		final CalculationResponse response = new CalculationResponse();
		try {
			final RPNCC rpn = new RPNCC(new EquationParserCC(new StringReader(equation)).parseEquation());
			response.setResult(CentralDifference.DerivativeOh4Central1(rpn, Double.parseDouble(evalAt),0));
		} catch (ParseException e) {
			returnString.append(e.getMessage());	
		} catch (CalculateException e) {
			returnString.append(e.getMessage());
		} catch (NumberFormatException e) {
			returnString.append(e.getMessage());
		} catch (TokenMgrError e) {
			returnString.append(e.getMessage());
		} catch(Exception e) {
			returnString.append(e.getMessage());
		}
		
		if(response.getResultType() < 0)
		{
			response.setErrorMessage(returnString.toString());
		}

		return response;
	}
}
