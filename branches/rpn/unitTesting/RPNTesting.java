package unitTesting;

import java.text.ParseException;

import junit.framework.TestCase;
import parser.RPN;


public class RPNTesting extends TestCase {

	public void testConstruction()
	{
		try
		{
		
			RPN rpnExpr = new RPN("x+x");
			
			assertTrue(true);
		}
		catch(ParseException exception)
		{
			//if we got here we have a bad expression
			assertTrue(false);
		}
	
	}
}
