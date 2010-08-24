package DataStructures;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Stack <T> implements Iterable<T> {

	private final ArrayList<T> stack;
	public Stack()
	{
		stack = new ArrayList<T>();
	}
	
	public void push(T obj)
	{
		stack.add(obj);
	}
	
	public T pop()
	{
		if(stack.size() == 0)
		{
			throw new EmptyStackException();
		}
		return stack.remove(stack.size()-1);
	}
	
	public T peek()
	{
		if(stack.size() == 0)
		{
			throw new EmptyStackException();
		}
		return stack.get(stack.size()-1);
	}
	
	@Override
	public Iterator<T> iterator() {
		ListIterator<T> iter = stack.listIterator(stack.size());
		List<T> newIter = new ArrayList<T>(stack.size());
		while(iter.hasPrevious())
		{
			newIter.add(iter.previous());
		}
		
		return newIter.iterator();
	}
}
