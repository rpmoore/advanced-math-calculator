package unitTesting;

import java.text.ParseException;

import org.junit.Test;
import static org.junit.Assert.*;

import bptree.EquationLexer;
import bptree.ExpressionType;
import bptree.ParseTree;

public class TrigFunctionTesting {
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TrigFunctionTesting.class);
    }
    
    @Test
    public void LexSin()
    {
    	EquationLexer lexer = new EquationLexer("sin(x)");
    	
    	assertEquals(ExpressionType.SIN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test    
    public void LexCos()
    {
    	EquationLexer lexer = new EquationLexer("cos(x)");
    	
    	assertEquals(ExpressionType.COS, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexTan()
    {
    	EquationLexer lexer = new EquationLexer("tan(x)");
    	
    	assertEquals(ExpressionType.TAN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexSinh()
    {
    	EquationLexer lexer = new EquationLexer("sinh(x)");
    	
    	assertEquals(ExpressionType.SINH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexCosh()
    {
    	EquationLexer lexer = new EquationLexer("cosh(x)");
    	
    	assertEquals(ExpressionType.COSH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexTanh()
    {
    	EquationLexer lexer = new EquationLexer("tanh(x)");
    	
    	assertEquals(ExpressionType.TANH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexLn()
    {
    	EquationLexer lexer = new EquationLexer("ln(x)");
    	
    	assertEquals(ExpressionType.LN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexLog()
    {
    	EquationLexer lexer = new EquationLexer("log(x)");
    	
    	assertEquals(ExpressionType.LOG, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    @Test
    public void LexLogEval()
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
    @Test
    public void LexSinEval()
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
    @Test
    public void LexCosEval()
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
    @Test
    public void LexTanEval()
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
    @Test
    public void LexLNEval()
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