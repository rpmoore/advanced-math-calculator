package org.beginprogramming.web.server.services;

import java.io.StringReader;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;
import org.advancedMathCalculator.parser.cc.TokenMgrError;
import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.IntegralService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IntegralServiceImpl extends RemoteServiceServlet implements
		IntegralService {

	private static final long serialVersionUID = 6433487991776962200L;

	@Override
	public CalculationResponse integralService(String equation, String leftBound, String rightBound) {
		final StringBuilder returnString = new StringBuilder();
		final CalculationResponse response = new CalculationResponse();
		try {
			final RPNCC rpn = new RPNCC(new EquationParserCC(new StringReader(equation)).parseEquation());
			response.setResult(SimpsonsRule.compute(rpn, Double.parseDouble(leftBound), Double.parseDouble(rightBound)));
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