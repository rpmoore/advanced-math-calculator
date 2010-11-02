package linear.vector;
import junit.framework.TestCase;
import linear.exceptions.VectorEmptyException;
import linear.exceptions.VectorSizeIncorrect;
import linear.util.HelperMathFunctions;



public class IntVectorTests extends TestCase {
	
	public void testVecEqualTrue()
	{
		Integer[] list1 = {1,2,3};
		Integer[] list2 = {1,2,3};

		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		
		assertTrue(vector1.equals(vector2));
	}
	
	public void testVecEqualFalse1()
	{
		Integer[] list1 = {1,1,3};
		Integer[] list2 = {1,2,3};

		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		
		assertFalse(vector1.equals(vector2));		
	}
	
	public void testVecEqualFalse2()
	{
		Integer[] list1 = {1,1,3};

		Vector<Integer> vector1 = new Vector<Integer>(list1);
		
		assertFalse(vector1.equals(null));		
	}	
	
	public void testVecEqualFalse3()
	{
		Integer[] list1 = {1,2,3,4};
		Integer[] list2 = {1,2,3};

		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		
		assertFalse(vector1.equals(vector2));		
	}
	
	public void testVecEqualFalse4()
	{
		Integer[] list1 = {1,1,3};

		Vector<Integer> vector1 = new Vector<Integer>(list1);
		
		assertFalse(vector1.equals(new Integer(1)));//Just need a random object to compare against.		
	}
	
	public void testBasicIntAdd() throws VectorSizeIncorrect, VectorEmptyException
	{
		Integer[] list1 = {1,1,1};
		Integer[] list2 = {1,2,3};
		Vector<Integer> vector1 = new Vector<Integer>(list1);
		Vector<Integer> vector2 = new Vector<Integer>(list2);
		
		Vector<Integer> resultVec = vector1.add(vector2);
		for(int i = 0; i < resultVec.size();++i)
		{
			assertEquals(new Integer(2+i), resultVec.get(i));
		}
	}
	
	public void testInvalidSize() throws VectorEmptyException
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
	
	public void testSingleMultInt() throws VectorEmptyException
	{
		Integer[] list = {1,2,3};
		Vector<Integer> vector = new Vector<Integer>(list);
		Vector<Integer> resultVector = vector.multSameConstant(new Integer(2));
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals(new Integer((i+1)*2), resultVector.get(i));
		}
	}
	
	public void testSingleMultDouble() throws VectorEmptyException
	{
		Integer[] list = {1,2,3};
		Vector<Integer> vector = new Vector<Integer>(list);
		Vector<Integer> resultVector = vector.multOtherConstant(new Double(2.2));
		
		for(int i = 0; i < vector.size();++i)
		{
			assertEquals(new Integer(HelperMathFunctions.floor((i+1)*2.2)), resultVector.get(i));
		}
	}

	public void testDotProduct() throws VectorSizeIncorrect, VectorEmptyException
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
	
	public void testNullInput() throws VectorSizeIncorrect, VectorEmptyException
	{
		Vector<Integer> vect = new Vector<Integer>();
		
		assertNull(vect.add(null));
		assertNull(vect.mult(null));
	}
}
