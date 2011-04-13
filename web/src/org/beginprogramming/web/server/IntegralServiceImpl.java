package org.beginprogramming.web.server;

import java.io.StringReader;

import org.advancedMathCalculator.computation.CalculateException;
import org.advancedMathCalculator.computation.defIntegral.SimpsonsRule;
import org.advancedMathCalculator.parser.cc.EquationParserCC;
import org.advancedMathCalculator.parser.cc.ParseException;
import org.advancedMathCalculator.parser.cc.RPNCC;
import org.beginprogramming.web.client.IntegralService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IntegralServiceImpl extends RemoteServiceServlet implements
		IntegralService {

	private static final long serialVersionUID = 6433487991776962200L;

	@Override
	public String integralService(String equation, String leftBound, String rightBound) {
		StringBuilder returnString = new StringBuilder();
		try {
			final RPNCC rpn = new RPNCC(new EquationParserCC(new StringReader(equation)).parseEquation());
			returnString.append(SimpsonsRule.compute(rpn, Double.parseDouble(leftBound), Double.parseDouble(rightBound)));
			
		} catch (ParseException e) {
			returnString.append("ERROR: ");
			returnString.append(e.getMessage());
		} catch (CalculateException e) {
			returnString.append("ERROR: ");
			returnString.append(e.getMessage());
		} catch (NumberFormatException e) {
			returnString.append("ERROR: ");
			returnString.append(e.getMessage());
		}
		return returnString.toString();
	}
}
