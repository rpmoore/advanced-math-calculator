package unitTesting;

import java.text.ParseException;

import junit.framework.TestCase;

import parser.EquationLexer;
import parser.ExpressionType;
import parser.ParseTree;



public class TrigFunctionTesting extends TestCase {
    
    
    public void testLexSin()
    {
    	EquationLexer lexer = new EquationLexer("sin(x)");
    	
    	assertEquals(ExpressionType.SIN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
        
    public void testLexCos()
    {
    	EquationLexer lexer = new EquationLexer("cos(x)");
    	
    	assertEquals(ExpressionType.COS, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexTan()
    {
    	EquationLexer lexer = new EquationLexer("tan(x)");
    	
    	assertEquals(ExpressionType.TAN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexSinh()
    {
    	EquationLexer lexer = new EquationLexer("sinh(x)");
    	
    	assertEquals(ExpressionType.SINH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexCosh()
    {
    	EquationLexer lexer = new EquationLexer("cosh(x)");
    	
    	assertEquals(ExpressionType.COSH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexTanh()
    {
    	EquationLexer lexer = new EquationLexer("tanh(x)");
    	
    	assertEquals(ExpressionType.TANH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLn()
    {
    	EquationLexer lexer = new EquationLexer("ln(x)");
    	
    	assertEquals(ExpressionType.LN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLog()
    {
    	EquationLexer lexer = new EquationLexer("log(x)");
    	
    	assertEquals(ExpressionType.LOG, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLogEval()
    {
    	try {
			ParseTree tree = ParseTree.makeTree("log(x)", false);
			
			assertEquals(Math.log10(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexSinEval()
    {
    	try {
			ParseTree tree = ParseTree.makeTree("sin(x)", false);
			
			assertEquals(Math.sin(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexCosEval()
    {
    	try {
			ParseTree tree = ParseTree.makeTree("cos(x)", false);
			
			assertEquals(Math.cos(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexTanEval()
    {
    	try {
			ParseTree tree = ParseTree.makeTree("tan(x)", false);
			
			assertEquals(Math.tan(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexLNEval()
    {
    	try {
			ParseTree tree = ParseTree.makeTree("ln(x)", false);
			
			assertEquals(Math.log(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
}