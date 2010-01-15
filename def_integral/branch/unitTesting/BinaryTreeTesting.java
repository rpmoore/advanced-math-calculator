package unitTesting;

import bptree.*;

import org.junit.*;

import defIntegral.SimpsonsRule;
import static org.junit.Assert.*;



public class BinaryTreeTesting {
	
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(BinaryTreeTesting.class);
    }
    
    BinaryTree<EquationToken> testing = null;
    
    @Before
    public void startup()
    {
    	testing = new BinaryTree<EquationToken>();
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
		ParseTree tree= null;
		try {
			tree = ParseTree.makeTree("2 + 3",true);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		TreeNode<EquationToken> root = btree.getRoot(); 
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals("2", root.getLeft().getItem().getToken());
		assertFalse(btree.isEmpty());
	}
	
	
	@Test
	public void testInsertBasicCombined()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("2+3/x",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals(ExpressionType.DIVIDE,root.getRight().getItem().getType());
		assertEquals("3", root.getRight().getLeft().getItem().getToken());
		assertEquals("x", root.getRight().getRight().getItem().getToken());
	}
	
	@Test
	public void testBasicEval()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("2+3/x",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		
		assertEquals(3, tree.eval(3),0);
	}
	
	@Test
	public void testBasicEvalTwo()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("3+5*5", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals(28.0, tree.eval(0), 0);
	}
	
	@Test
	public void testBasicEvalThree()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("e+pi", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals((Math.E + Math.PI), tree.eval(0), 0);
	}
	
	@Test
	public void testOptimizingOne()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("3+5*5", true);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		
		assertEquals(28, root.getItem().getToken());
		assertNull(root.getLeft());
		assertNull(root.getRight());
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
		
		double answer = SimpsonsRule.compute(line, .5, 5.5);
		
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
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}	
		
		double answer = SimpsonsRule.compute(tree, .5, 5.5);		
		assertEquals(122.5,answer,1);
		assertEquals(122.5,answer,0);
	}
	

}
