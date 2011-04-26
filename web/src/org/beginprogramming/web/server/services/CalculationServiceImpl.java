package org.beginprogramming.web.server.services;

import java.io.StringReader;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defDerivative.CentralDifference;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;
import org.advancedMathCalculator.parser.cc.TokenMgrError;
import org.beginprogramming.web.client.services.CalculationResponse;
import org.beginprogramming.web.client.services.CalculationService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CalculationServiceImpl extends RemoteServiceServlet implements CalculationService {

	private static final long serialVersionUID = -4368176011337648722L;

	@Override
	public CalculationResponse calculateEquation(String equation) {
		final StringBuilder returnString = new StringBuilder();
		final CalculationResponse response = new CalculationResponse();
		if(equation.toLowerCase().contains("x"))
		{
			response.setErrorMessage("The equation cannot have any variables in it.");
			return response;
		}
		try {
			final RPNCC rpn = new RPNCC(new EquationParserCC(new StringReader(equation)).parseEquation());
			response.setResult(rpn.eval(0));
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
