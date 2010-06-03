package linear.vector;
import junit.framework.TestCase;
import linear.util.HelperMathFunctions;



public class IntVectorTests extends TestCase {
	
	public void testBasicIntAdd() throws VectorSizeIncorrect
	{
		int[] list1 = {1,1,1};
		int[] list2 = {1,2,3};
		Vector vector1 = new IntVector(list1);
		Vector vector2 = new IntVector(list2);
		
		Vector resultVec = vector1.add(vector2);
		for(int i = 0; i < resultVec.size();++i)
		{
			assertEquals(2+i, resultVec.get(i));
		}
	}
	
	public void testInvalidSize()
	{
		int[] list1 = {1,1,1};
		Vector resultVector = null;
		Vector vector1 = new IntVector(list1);
		Vector vector2 = new IntVector();
		
		try {
			resultVector = vector1.add(vector2);
			assertFalse(true);
		} catch (VectorSizeIncorrect e) {
			assertTrue(true);
		}
		assertNull(resultVector);
	}
	
	public void testSingleMultInt()
	{
		int[] list = {1,2,3};
		Vector vector = new IntVector(list);
		Vector resultVector = vector.mult(2);
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals((i+1)*2, resultVector.get(i));
		}
	}
	
	public void testSingleMultDouble()
	{
		int[] list = {1,2,3};
		Vector vector = new IntVector(list);
		Vector resultVector = vector.mult(2.2);
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals(HelperMathFunctions.floor((i+1)*2.2), resultVector.get(i));
		}
	}

	public void testDotProduct() throws VectorSizeIncorrect
	{
		int[] list1 = {1,2,3};
		int[] list2 = {2,2,2};
		Vector vector1 = new IntVector(list1);
		Vector vector2 = new IntVector(list2);
		Vector resultVector = vector1.mult(vector2);
		
		for(int i = 0; i < resultVector.size();++i)
		{
			assertEquals((i+1)*2,resultVector.get(i));
		}
	}
	
	public void testNullInput() throws VectorSizeIncorrect
	{
		Vector vect = new IntVector();
		
		assertNull(vect.add(null));
		assertNull(vect.mult(null));
	}
}
