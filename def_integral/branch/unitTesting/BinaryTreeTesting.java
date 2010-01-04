package unitTesting;


import bptree.*;

import org.junit.*;
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
	public void getOPT()
	{
		assertEquals(ExpressionType.ADD, ExpressionType.getType("+"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123124.3"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123"));
		assertEquals(ExpressionType.OTHER, ExpressionType.getType("hello"));
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
    	EquationToken token = null;
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertEquals("3",token.getToken());
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
    public void testPIandE()
    {
    	//This test should pass every time as written.  It is very specific
    	//and really helps illustrate how the Tokenizer should operate.
    	EquationTokenizer tokenizer = new EquationTokenizer("e+pi");
    	EquationToken token = tokenizer.nextElement();
    	assertEquals(ExpressionType.E, token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.ADD,token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.PI,token.getType());
    	assertFalse(tokenizer.hasMoreElements());
    }
    
	
	@Test
	public void testInsertBasic()
	{
		//TODO remove this when testing the insert methods.
		ParseTree tree = new ParseTree("2 + 3");
		BinaryTree<EquationToken> btree = tree.getTree();
		TreeNode<EquationToken> root = btree.getRoot(); 
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals("2", root.getLeft().getItem().getToken());
		assertFalse(btree.isEmpty());
	}
	
	
	@Test
	public void testInsertBasicCombined()
	{
		//TODO remove this when testing the insert methods.
		ParseTree tree = new ParseTree("2+3/x");
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals(ExpressionType.DIVIDE,root.getRight().getItem().getType());
		assertEquals("3", root.getRight().getLeft().getItem().getToken());
		assertEquals("x", root.getRight().getRight().getItem().getToken());
	}
	

}
