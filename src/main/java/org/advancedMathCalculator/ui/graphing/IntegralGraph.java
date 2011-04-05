package org.advancedMathCalculator.ui.graphing;
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
import org.advancedMathCalculator.defIntegral.Calculate;


/**
 *Creates a graph of the integral passed into it.  Automaticlly sets bounds, and allows for
 *scrolling of the graph and printing out values.
 * 
 * @author Ryan
 *
 * @param <T> An equation to graph the integral of.
 */
public class IntegralGraph<T extends Calculate> extends LineGraph<T> {
	
	private double left;
	private double right;
	
	/**
	 * 
	 * @param equation
	 * @param left
	 * @param right
	 */
	public IntegralGraph(double left, double right)
	{
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Creates a graph without the area filled in for the integral.  Would be the same as
	 * creating a LineGraph.
	 * @param equation
	 */
	public IntegralGraph(T equation)
	{
		this(0,0);
	}
}
