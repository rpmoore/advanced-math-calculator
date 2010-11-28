package dataStructures;
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
import java.util.LinkedList;
import java.util.List;

/**
 * Generic Queue implementation.  Supports the standard queue operations, plus some additional peek methods.
 * 
 * @author Ryan Moore
 *
 * @param <T> Generic type.
 */
public class Queue <T> implements Iterable<T> {
	private final List<T> queue;
	public Queue()
	{
		queue = new LinkedList<T>();
	}
	
	public void enqueue(T item)
	{
		queue.add(queue.size(), item);
	}
	
	public T dequeue()
	{
		return queue.remove(0);
	}
	
	public T peek()
	{
		return queue.get(0);
	}
	
	public T peekLast()
	{
		return queue.get(queue.size()-1);
	}
	
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}
	
	public String toString()
	{
		return queue.toString();
	}

}
