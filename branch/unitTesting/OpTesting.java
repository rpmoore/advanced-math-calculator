package unitTesting;

import bptree.*;
import org.junit.*;
import static org.junit.Assert.*;



public class OpTesting {
	
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(OpTesting.class);
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
		
		assertTrue(testing.isOp(ExpressionTypes.ADD));
		assertTrue(testing.isOp(ExpressionTypes.SUBTRACT));
		assertFalse(testing.isOp(ExpressionTypes.NUMBER));
	}
	
	@Test
	public void compOPT()
	{		
		assertTrue(testing.compType(ExpressionTypes.NUMBER, ExpressionTypes.ADD) == -1);
		assertTrue(testing.compType(ExpressionTypes.ADD, ExpressionTypes.NUMBER) == 1);
		assertEquals(1, testing.compType(ExpressionTypes.ADD, ExpressionTypes.MULTIPLY));
		assertEquals(1,testing.compType(ExpressionTypes.MULTIPLY, ExpressionTypes.COS));
		assertEquals(1, testing.compType(ExpressionTypes.COS, ExpressionTypes.E));
		assertEquals(-1, testing.compType(ExpressionTypes.E, ExpressionTypes.COSH));
		assertEquals(-1, testing.compType(ExpressionTypes.MULTIPLY,ExpressionTypes.SUBTRACT));
		assertEquals(-1, testing.compType(ExpressionTypes.COS, ExpressionTypes.DIVIDE));
	}
	
	@Test
	public void getOPT()
	{
		assertEquals(ExpressionTypes.ADD, testing.returnType("+"));
		assertEquals(ExpressionTypes.NUMBER, testing.returnType("123124.3"));
		assertEquals(ExpressionTypes.NUMBER, testing.returnType("123"));
		assertEquals(-1, testing.returnType("hello"));
	}
	

}
