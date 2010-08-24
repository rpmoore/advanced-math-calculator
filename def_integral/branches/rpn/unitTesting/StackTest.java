package unitTesting;

import java.util.EmptyStackException;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.Test;

import DataStructures.Stack;

public class StackTest extends TestCase{

	@Test
	public void testPush() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(8);
		assertEquals(new Integer(8), stack.peek());
	}

	@Test
	public void testPop() {
		Stack<Integer> stack = new Stack<Integer>();
		try
		{
			stack.pop();
			assertFalse(true);
		}
		catch(EmptyStackException e)
		{
			assertTrue(true);
		}
		stack.push(8);
		assertEquals(new Integer(8), stack.pop());
		try
		{
			stack.peek();
			assertFalse(true);
		}
		catch(EmptyStackException e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void testIterator() {
		int num = 4;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		Iterator<Integer> iter = stack.iterator();
		
		while(iter.hasNext())
		{
			assertEquals(new Integer(num), iter.next());
			num--;
		}
	}
}
