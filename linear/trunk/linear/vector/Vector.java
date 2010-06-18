package linear.vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import linear.services.vector.AddService;

/**  
 *  
 * @author Ryan Moore
 *
 */
public class Vector<T> implements java.lang.Iterable<T>  {
	private final ArrayList<T> backingArray;
	private final Class<T> classType;
	
	@SuppressWarnings("unchecked")
	public Vector()
	{
		backingArray = new ArrayList<T>();
		T temp = (T) new Object(); //This is not really safe, 
		//but need to do it to get the type for the type making
		//for the ops.
		classType = (Class<T>) temp.getClass();
		
		
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
	
	public Vector<T> add(Vector<T> obj) throws VectorSizeIncorrect
	{
		return AddService.<T>addSerial(this, obj,getClassType());
	}
	public Vector<T> mult(Vector<T> obj) throws VectorSizeIncorrect
	{
		return null;		
	}
	public Vector<T> mult(double val)
	{
		return null;
	}
	public Vector<T> mult(int val)
	{
		return null;	
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
	
	private Class getClassType()
	{
		return classType;
	}
}