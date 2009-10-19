package unitTesting;

import java.rmi.server.ExportException;
import java.text.ParseException;

import bptree.*;

import org.junit.*;
import org.junit.internal.runners.statements.ExpectException;

import static org.junit.Assert.*;



public class BinaryTreeTesting {
	
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(BinaryTreeTesting.class);
    }
    
    BinaryTree testing = null;
    
    @Before
    public void startup()
    {
    	testing = new BinaryTree();
    }
    
	@Test
	public void checkOPT()
	{
		
		assertTrue(ExpressionType.isOp(ExpressionType.ADD));
		assertTrue(ExpressionType.isOp(ExpressionType.SUBTRACT));
		assertFalse(ExpressionType.isOp(ExpressionType.NUMBER));
	}
	
	@Test
	public void compOPT()
	{		
		assertTrue(ExpressionType.compType(ExpressionType.NUMBER, ExpressionType.ADD) == -1);
		assertTrue(ExpressionType.compType(ExpressionType.ADD, ExpressionType.NUMBER) == 1);
		assertEquals(1, ExpressionType.compType(ExpressionType.ADD, ExpressionType.MULTIPLY));
		assertEquals(1,ExpressionType.compType(ExpressionType.MULTIPLY, ExpressionType.COS));
		assertEquals(1, ExpressionType.compType(ExpressionType.COS, ExpressionType.E));
		assertEquals(-1, ExpressionType.compType(ExpressionType.E, ExpressionType.COSH));
		assertEquals(-1, ExpressionType.compType(ExpressionType.MULTIPLY,ExpressionType.SUBTRACT));
		assertEquals(-1, ExpressionType.compType(ExpressionType.COS, ExpressionType.DIVIDE));
		assertEquals(1, ExpressionType.compType(ExpressionType.LEFTPAREN, ExpressionType.SUBTRACT));
		assertEquals(0, ExpressionType.compType(ExpressionType.LEFTPAREN, ExpressionType.RIGHTPAREN));
	}
	
	@Test
	public void getOPT()
	{
		assertEquals(ExpressionType.ADD, ExpressionType.getType("+"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123124.3"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123"));
		assertEquals(-1, ExpressionType.getType("hello"));
	}

    @Test
    public void makeTokensOne()
    {
    	EquationTokenizer tokenizer = new EquationTokenizer("+");
    	assertTrue(tokenizer.hasMoreElements());
    	EquationToken token = tokenizer.nextElement();
    	assertTrue(token.getType() == ExpressionType.ADD);
    	assertFalse(tokenizer.hasMoreElements());
    }

    @Test
    public void makeTokensZero()
    {
    	EquationTokenizer tokenizer = new EquationTokenizer("");
    	assertFalse(tokenizer.hasMoreElements());
    	assertTrue(tokenizer.nextElement() == null);
    }

    @Test
    public void makeTokensThree()
    {
    	EquationTokenizer tokenizer = new EquationTokenizer("2 + 3");
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    @Test
    public void makeTokensThreeNoWhitespace()
    {
    	EquationTokenizer tokenizer = new EquationTokenizer("2+3");
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
    	EquationTokenizer tokenizer = new EquationTokenizer("(2+3)/2");
    	for(int i = 0; i < 7; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    
	
	@Test
	public void testInsertBasic()
	{
		//TODO remove this when testing the insert methods.
		ParseTree tree = new ParseTree();
		BinaryTree btree = null;
		try {
			btree = tree.parse("2 + 3");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BinaryTree.TreeNode root = btree.getRoot(); 
		assertEquals(ExpressionType.ADD, root.getType());
		assertEquals("2", root.getLeft().getExpression());
		assertFalse(btree.isEmpty());
	}
	
	
	@Test
	public void testInsertBasicCombined()
	{
		//TODO remove this when testing the insert methods.
		ParseTree tree = new ParseTree();
		BinaryTree btree = null;
		try
		{
		btree = tree.parse("2+3/x");
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		BinaryTree.TreeNode root = btree.getRoot();
		assertEquals(ExpressionType.ADD, root.getType());
		assertEquals(ExpressionType.DIVIDE,root.getRight().getType());
		assertEquals("3", root.getRight().getLeft().getExpression());
		assertEquals("x", root.getRight().getRight().getExpression());
	}
	

}
