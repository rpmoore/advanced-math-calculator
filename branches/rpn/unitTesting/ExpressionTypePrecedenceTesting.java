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

import parser.ExpressionType;

import junit.framework.TestCase;


public class ExpressionTypePrecedenceTesting extends TestCase {

	public void testFunc()
	{
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.COS, ExpressionType.COS));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.COS, ExpressionType.COSH));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.LN, ExpressionType.COS));
	}
	
	public void testOP()
	{
		assertEquals(-1, ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.COS));
		assertEquals(1,ExpressionType.comparePrecedence(ExpressionType.SIN, ExpressionType.DIVIDE));
		assertEquals(0,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.ADD));
		assertEquals(0,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.SUBTRACT));
		assertEquals(0,ExpressionType.comparePrecedence(ExpressionType.DIVIDE, ExpressionType.MULTIPLY));
		assertEquals(-1,ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.MULTIPLY));
		assertEquals(1,ExpressionType.comparePrecedence(ExpressionType.DIVIDE, ExpressionType.ADD));
	}
	
	public void testParans()
	{
		assertEquals(-1, ExpressionType.comparePrecedence(ExpressionType.ADD, ExpressionType.LEFTPAREN));
		assertEquals(0, ExpressionType.comparePrecedence(ExpressionType.RIGHTPAREN, ExpressionType.LEFTPAREN));
		assertEquals(1, ExpressionType.comparePrecedence(ExpressionType.RIGHTPAREN, ExpressionType.MULTIPLY));
		assertEquals(1, ExpressionType.comparePrecedence(ExpressionType.LEFTPAREN, ExpressionType.POW));
	}
}
