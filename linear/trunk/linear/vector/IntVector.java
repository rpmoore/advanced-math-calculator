package linear.vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import linear.util.HelperMathFunctions;


public class IntVector implements Vector {
	
	

	
	public IntVector()
	{
		backingArray = new ArrayList<Integer>();
	}
	
	public IntVector(Collection<Integer> list)
	{
		backingArray = new ArrayList<Integer>(list);
	}
	
	public IntVector(int[] list)
	{
		backingArray = new ArrayList<Integer>();
		for(int item:list)
		{
			backingArray.add(item);
		}
	}
	
	public Object get(int i) throws IndexOutOfBoundsException
	{
		return backingArray.get(i);
	}
	
	public void insert(int index, int value)
	{
		set(index, value);
	}
	
	public void insertLast(int value)
	{
		insert(size(),value);
	}

	@Override
	/**
	 * 
	 * Returns a new Vector with the second vector added to this vector.
	 * @param obj is the second vector that needs to be added this.
	 * @return Return a new vector that equals this + obj, if obj is null, add returns null.
	 */
	public Vector add(Vector obj) throws VectorSizeIncorrect  {
		if(obj == null)
		{
			return null;
		}
		//check to make sure that the sizes are the same.
		if(this.size() != obj.size())
		{
			throw new VectorSizeIncorrect();
		}
		
		//create a vector and return the new vector.
		final IntVector retVector = new IntVector();
		final int backingSize = backingArray.size();
		for(int i = 0; i < backingSize;++i)
		{
			Integer item = backingArray.get(i);
			Object leftItem = obj.get(i);
			if(leftItem instanceof Integer)
			{
				Integer leftItemInt =(Integer) leftItem;
				retVector.insert(i, item + leftItemInt);
			}
			else if(leftItem instanceof Double)
			{
				Double leftItemFloat = (Double)leftItem;
				retVector.insert(i,  HelperMathFunctions.floor(item + leftItemFloat));
			}
		}
			 
		return retVector;
	}

	@Override
	public Vector div(int val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector div(double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Performs the dot product of the two vectors.
	 * 
	 */
	public Vector mult(Vector obj)  throws VectorSizeIncorrect{
		if(obj == null)
		{
			return null;
		}
		//check to see that the sizes of the 2 arrays are the same
		if(obj.size() != this.size())
		{
			throw new VectorSizeIncorrect();
		}
		
		final IntVector retVector = new IntVector();
		final int backingSize = backingArray.size();	
		for(int i = 0; i <backingSize;++i)
		{
			retVector.insertLast((Integer)this.get(i) * (Integer) obj.get(i));
		}
		
		return retVector;
	}

	@Override
	public Vector mult(double val) {
		IntVector resultVec = new IntVector();
		for(Object intItem: this)
		{
			resultVec.insertLast(HelperMathFunctions.floor(((Integer)intItem)*val));
		}
		return resultVec;
	}

	@Override
	public Vector mult(int val) {
		IntVector resultVec = new IntVector();
		for(Object intItem: this)
		{
			resultVec.insertLast(((Integer)intItem)*val);
		}
		return resultVec;
	}

	@Override
	public Vector sub(Vector obj) throws VectorSizeIncorrect {
		if(obj == null)
		{
			return null;
		}
		return null;
	}

	@Override
	public Iterator iterator() {
		return backingArray.iterator();
	}

	@Override
	public int size() {
		return backingArray.size();
	}
	
	/**
	 * Sets the value at index to value, if the index is not 
	 * within the size of the vector, then 0 will be added to the
	 * end of the vector until index is the last value in the vector.   
	 * @param index
	 * @param value
	 */
	private void set(int index, int value)
	{
		while(index >= backingArray.size())
		{
			backingArray.add(0);
		}
		backingArray.set(index, value);
	}
}