package linear.vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import linear.exceptions.VectorEmptyException;
import linear.exceptions.VectorSizeIncorrect;
import linear.services.vector.AddService;
import linear.services.vector.MultService;

/**  
 *  
 * @author Ryan Moore
 *
 */
public class Vector<T> implements java.lang.Iterable<T>  {
	private final ArrayList<T> backingArray;

	public Vector()
	{
		backingArray = new ArrayList<T>();
	}
	
	public Vector(T[] list)
	{
		this();
		for(T item:list)
		{
			backingArray.add(item);
		}
	}
	
	public Vector(Collection<T> list)
	{
		this();
		backingArray.addAll(list);
	}
	
	public Vector<T> add(Vector<T> obj) throws VectorSizeIncorrect, VectorEmptyException
	{
		return AddService.<T>addSerial(this, obj);
	}
	public Vector<T> mult(Vector<T> obj) throws VectorSizeIncorrect, VectorEmptyException
	{
		return MultService.multSerial(this, obj);		
	}

	public Vector<T> multSameConstant(T obj)throws VectorEmptyException
	{
		return MultService.multSerialSame(obj, this);
	}

	public Vector<T> multOtherConstant(Object obj)throws VectorEmptyException
	{
		return MultService.multSerialDifferent(obj, this);
	}
	
	public Vector<T> sub(Vector<T> obj) throws VectorSizeIncorrect
	{
		return null;	
	}
	public Vector<T> div(int val)
	{
		return null;	
	}
	public Vector<T> div(double val)
	{
		return null;
	}
	
	public int size()
	{
		return backingArray.size();
	}
	
	public void insert(int index,T item)
	{
		set(index, item);
	}
	
	public void insertLast(T item)
	{
		insert(size(),item);
	}
	
	public T get(int i) throws IndexOutOfBoundsException
	{
		return backingArray.get(i); 
	}

	public Iterator<T> iterator() {
		return backingArray.iterator();
	}
	
	/**
	 * Sets the value at index to value, if the index is not 
	 * within the size of the vector, then 0 will be added to the
	 * end of the vector until index is the last value in the vector.   
	 * @param index
	 * @param value
	 */
	private void set(int index, T value)
	{
		while(index >= backingArray.size())
		{
			backingArray.add(null);
		}
		backingArray.set(index, value);
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof Vector))
		{
			return false;
		}
		
		Vector objVec = (Vector) obj;
		
		if(objVec.size() != this.size())
		{
			return false;	
		}
		
		for(int i = 0; i < this.size();++i)
		{
			//If the items do not match at any point, return false.
			if(!(this.get(i).equals(objVec.get(i))))
			{
				return false;
			}
		}
		
		return true;
	}
}