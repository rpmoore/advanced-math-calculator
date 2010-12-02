package org.advancedMathCalculator;

/*
 * Copyright 2010 Ryan Moore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */
import java.util.EmptyStackException;
import java.util.Iterator;

import junit.framework.TestCase;

import org.advancedMathCalculator.dataStructures.Stack;
import org.junit.Test;

public class StackTest extends TestCase {

	@Test
	public void testIterator() {
		int num = 4;
		final Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		final Iterator<Integer> iter = stack.iterator();

		while (iter.hasNext()) {
			assertEquals(new Integer(num), iter.next());
			num--;
		}
	}

	@Test
	public void testPop() {
		final Stack<Integer> stack = new Stack<Integer>();
		try {
			stack.pop();
			assertFalse(true);
		} catch (final EmptyStackException e) {
			assertTrue(true);
		}
		stack.push(8);
		assertEquals(new Integer(8), stack.pop());
		try {
			stack.peek();
			assertFalse(true);
		} catch (final EmptyStackException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testPush() {
		final Stack<Integer> stack = new Stack<Integer>();
		stack.push(8);
		assertEquals(new Integer(8), stack.peek());
	}
}
