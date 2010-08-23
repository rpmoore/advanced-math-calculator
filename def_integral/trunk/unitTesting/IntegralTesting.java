package unitTesting;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import bptree.ParseTree;
import defIntegral.SimpsonsRule;

public class IntegralTesting {
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(IntegralTesting.class);
    }
	
	@Test
	public void EquivalentStatements()
	{
		try {
			double result1 = SimpsonsRule.compute(ParseTree.makeTree("3*x^2", true), 2, 3);
			assertEquals(19.0,result1,0);
			result1 = SimpsonsRule.compute(ParseTree.makeTree("(3*x)^2", true), 2, 3);
			assertEquals(57.0,result1,0);
			result1 = SimpsonsRule.compute(ParseTree.makeTree("3x^2", true), 2, 3);
			assertEquals(19.0,result1,0);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testBasicSimpsionsRuleOne()
	{
		Line2 line = new Line2();
		
		double answer = SimpsonsRule.compute(line,2,4);
		
		assertEquals(4,answer,0);
	}
	
	@Test
	public void testBasicDiagnalSimpsionsRuleTwo()
	{
		DiagnalLine line = new DiagnalLine();
		
		double answer = SimpsonsRule.compute(line, -.5, 4.5);
		
		assertEquals(122.5,answer,1);
		assertEquals(122.5,answer,0);
	}
	
	@Test
	public void testIntegrationSimpsionsParseTree()
	{		
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("12*x+.5", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}	
		
		double answer = SimpsonsRule.compute(tree, .5, 5.5);		
		assertEquals(182.5,answer,1);
		assertEquals(182.5,answer,0);
	}
}
