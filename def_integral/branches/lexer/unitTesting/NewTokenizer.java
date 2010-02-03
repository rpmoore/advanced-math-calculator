package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bptree.EquationLexer;
import bptree.EquationToken;
import bptree.ExpressionType;

public class NewTokenizer {

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(NewTokenizer.class);
    }
    
    @Test
    public void makeTokensOne()
    {
    	EquationLexer tokenizer = new EquationLexer("+");
    	assertTrue(tokenizer.hasMoreElements());
    	EquationToken token = tokenizer.nextElement();
    	assertTrue(token.getType() == ExpressionType.ADD);
    	assertFalse(tokenizer.hasMoreElements());
    }

    @Test
    public void makeTokensZero()
    {
    	EquationLexer tokenizer = new EquationLexer("");
    	assertFalse(tokenizer.hasMoreElements());
    	assertTrue(tokenizer.nextElement().getType() == ExpressionType.EOF);
    }

    @Test
    public void makeTokensThree()
    {
    	EquationLexer tokenizer = new EquationLexer("2 + 3");
    	EquationToken token = null;
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertEquals("3",token.getToken());
    	assertEquals(ExpressionType.EOF,tokenizer.nextElement().getType());
    	assertFalse(tokenizer.hasMoreElements());
    }
    @Test
    public void makeTokensThreeNoWhitespace()
    {
    	EquationLexer tokenizer = new EquationLexer("2+3");
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    
    @Test
    public void makeTokensSeven()
    {
    	EquationLexer tokenizer = new EquationLexer("(2+3)/2");
    	for(int i = 0; i < 7; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    
    @Test
    public void testPIandE()
    {
    	//This test should pass every time as written.  It is very specific
    	//and really helps illustrate how the Tokenizer should operate.
    	EquationLexer tokenizer = new EquationLexer("e+pi");
    	EquationToken token = tokenizer.nextElement();
    	assertEquals(ExpressionType.E, token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.ADD,token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.PI,token.getType());
    	assertFalse(tokenizer.hasMoreElements());
    }
}
