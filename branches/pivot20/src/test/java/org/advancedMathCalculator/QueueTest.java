package org.advancedMathCalculator;

import java.util.Iterator;

import junit.framework.TestCase;

import org.advancedMathCalculator.dataStructures.Queue;
import org.junit.Test;

public class QueueTest extends TestCase {

	@Test
	public void testIterator() {
		int num = 1;
		final Queue<Integer> stack = new Queue<Integer>();
		stack.enqueue(1);
		stack.enqueue(2);
		stack.enqueue(3);
		stack.enqueue(4);
		final Iterator<Integer> iter = stack.iterator();

		while (iter.hasNext()) {
			assertEquals(new Integer(num), iter.next());
			num++;
		}
	}

	@Test
	public void testPush() {
		final Queue<Integer> stack = new Queue<Integer>();
		stack.enqueue(8);
		assertEquals(new Integer(8), stack.peek());
		assertEquals(new Integer(8), stack.dequeue());
	}
}
