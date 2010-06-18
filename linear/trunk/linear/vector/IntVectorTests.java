package linear.vector;
import junit.framework.TestCase;
import linear.util.HelperMathFunctions;



public class IntVectorTests extends TestCase {
	
	public void testBasicIntAdd() throws VectorSizeIncorrect
	{
		Integer[] list1 = {1,1,1};
		Integer[] list2 = {1,2,3};
		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		
		Vector resultVec = vector1.add(vector2);
		for(int i = 0; i < resultVec.size();++i)
		{
			assertEquals(2+i, resultVec.get(i));
		}
	}
	
	public void testInvalidSize()
	{
		Integer[] list1 = {1,1,1};
		Vector<Integer> resultVector = null;
		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>();
		
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
		Integer[] list = {1,2,3};
		Vector<Integer> vector = new Vector<Integer>(list);
		Vector<Integer> resultVector = vector.mult(2);
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals(new Integer((i+1)*2), resultVector.get(i));
		}
	}
	
	public void testSingleMultDouble()
	{
		Integer[] list = {1,2,3};
		Vector<Integer> vector = new Vector<Integer>(list);
		Vector<Integer> resultVector = vector.mult(2.2);
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals(new Integer(HelperMathFunctions.floor((i+1)*2.2)), resultVector.get(i));
		}
	}

	public void testDotProduct() throws VectorSizeIncorrect
	{
		Integer[] list1 = {1,2,3};
		Integer[] list2 = {2,2,2};
		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		Vector<Integer> resultVector = vector1.mult(vector2);
		
		for(int i = 0; i < resultVector.size();++i)
		{
			assertEquals(new Integer((i+1)*2),resultVector.get(i));
		}
	}
	
	public void testNullInput() throws VectorSizeIncorrect
	{
		Vector<Integer> vect = new Vector<Integer>();
		
		assertNull(vect.add(null));
		assertNull(vect.mult(null));
	}
}
