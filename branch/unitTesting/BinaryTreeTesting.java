package unitTesting;

import java.text.ParseException;

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
		
		assertTrue(BinaryTree.isOp(ExpressionTypes.ADD));
		assertTrue(BinaryTree.isOp(ExpressionTypes.SUBTRACT));
		assertFalse(BinaryTree.isOp(ExpressionTypes.NUMBER));
	}
	
	@Test
	public void compOPT()
	{		
		assertTrue(BinaryTree.compType(ExpressionTypes.NUMBER, ExpressionTypes.ADD) == -1);
		assertTrue(BinaryTree.compType(ExpressionTypes.ADD, ExpressionTypes.NUMBER) == 1);
		assertEquals(1, BinaryTree.compType(ExpressionTypes.ADD, ExpressionTypes.MULTIPLY));
		assertEquals(1,BinaryTree.compType(ExpressionTypes.MULTIPLY, ExpressionTypes.COS));
		assertEquals(1, BinaryTree.compType(ExpressionTypes.COS, ExpressionTypes.E));
		assertEquals(-1, BinaryTree.compType(ExpressionTypes.E, ExpressionTypes.COSH));
		assertEquals(-1, BinaryTree.compType(ExpressionTypes.MULTIPLY,ExpressionTypes.SUBTRACT));
		assertEquals(-1, BinaryTree.compType(ExpressionTypes.COS, ExpressionTypes.DIVIDE));
	}
	
	@Test
	public void getOPT()
	{
		assertEquals(ExpressionTypes.ADD, BinaryTree.returnType("+"));
		assertEquals(ExpressionTypes.NUMBER, BinaryTree.returnType("123124.3"));
		assertEquals(ExpressionTypes.NUMBER, BinaryTree.returnType("123"));
		assertEquals(-1, BinaryTree.returnType("hello"));
	}
	
	@Test
	public void testInsertBasic()
	{
		BinaryParseTree tree = new BinaryParseTree();
		BinaryTree btree = null;
		try {
			btree = tree.parse("2 + 3");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BinaryTree.TreeNode root = btree.getRoot(); 
		assertEquals(ExpressionTypes.ADD, root.getType());
		assertEquals("2", root.getLeft().getExpression());
		assertFalse(btree.isEmpty());
	}
	
	@Test
	public void testInsertBasicCombined()
	{
		BinaryParseTree tree = new BinaryParseTree();
		BinaryTree btree = null;
		try
		{
		btree = tree.parse("2 + 3 / x");
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		BinaryTree.TreeNode root = btree.getRoot();
		assertEquals(ExpressionTypes.ADD, root.getType());
		assertEquals(ExpressionTypes.DIVIDE,root.getRight().getType());
		assertEquals("3", root.getRight().getLeft().getExpression());
		assertEquals("x", root.getRight().getRight().getExpression());
	}
	

}
