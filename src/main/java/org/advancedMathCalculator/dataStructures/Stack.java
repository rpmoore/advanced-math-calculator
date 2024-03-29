package org.advancedMathCalculator.dataStructures;

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
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Generic Stack implementation with all the standard push, pop, and peek
 * methods.
 * 
 * @author Ryan Moore
 * 
 * @param <T>
 *            Generic Type.
 */
public class Stack<T> implements Iterable<T> {

	private final List<T> stack;

	public Stack() {
		stack = new ArrayList<T>();
	}

	public Stack(List<T> list) {
		stack = list;
		stack.clear();// Empty the list before using it.
	}

	public Iterator<T> iterator() {
		final ListIterator<T> iter = stack.listIterator(stack.size());
		final List<T> newIter = new ArrayList<T>(stack.size());
		while (iter.hasPrevious()) {
			newIter.add(iter.previous());
		}

		return newIter.iterator();
	}

	public T peek() {
		if (stack.size() == 0) {
			throw new EmptyStackException();
		}
		return stack.get(stack.size() - 1);
	}

	public T pop() {
		if (stack.size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	public void push(T obj) {
		stack.add(obj);
	}

	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (final T item : stack) {
			builder.append(item + ",");
		}
		return builder.toString();
	}
}
