package unitTesting;

import bptree.*;
import org.junit.*;
import static org.junit.Assert.*;



public class OpTesting {
	
	@Test
	public void checkOPT()
	{
		BinaryTree testing = new BinaryTree();
		
		assertTrue(testing.isOp(ExpressionTypes.ADD));
		assertTrue(testing.isOp(ExpressionTypes.SUBTRACT));
		assertFalse(testing.isOp(ExpressionTypes.NUMBER));
	}
	
	@Test
	public void compOPT()
	{
		BinaryTree testing = new BinaryTree();
		
		assertTrue(testing.compType(ExpressionTypes.NUMBER, ExpressionTypes.ADD) == -1);
	}
	
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(OpTesting.class);
    }
}
