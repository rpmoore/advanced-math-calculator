package org.advancedMathCalculator.parser;
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
public class EquationToken {
	private String token;
	private ExpressionType type;
	private int index;
	
	public EquationToken( String token, ExpressionType type, int index)
	{
		this.token = token;
		this.type = type;
		this.index = index;
	}
	public EquationToken( String token, ExpressionType type)
	{
		this(token,type,-1);
	}
	
	public String getToken() {
		return token;
	}

	public ExpressionType getType() {
		return type;
	}
	
	public int getPosition()
	{
		return index;
	}
	
	public String toString()
	{
		return "" + type + " - " + token + " - " + index;
		}
}
