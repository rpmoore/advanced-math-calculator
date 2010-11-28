package unitTesting;
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

import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.Test;

import dataStructures.Queue;


public class QueueTest extends TestCase{

	@Test
	public void testPush() {
		Queue<Integer> stack = new Queue<Integer>();
		stack.enqueue(8);
		assertEquals(new Integer(8), stack.peek());
		assertEquals(new Integer(8), stack.dequeue());
	}

	@Test
	public void testIterator() {
		int num = 1;
		Queue<Integer> stack = new Queue<Integer>();
		stack.enqueue(1);
		stack.enqueue(2);
		stack.enqueue(3);
		stack.enqueue(4);
		Iterator<Integer> iter = stack.iterator();
		
		while(iter.hasNext())
		{
			assertEquals(new Integer(num), iter.next());
			num++;
		}
	}
}
