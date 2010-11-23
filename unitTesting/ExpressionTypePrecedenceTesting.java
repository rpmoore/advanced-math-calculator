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
		assertEquals(0, ExpressionType.getPrecedence(ExpressionType.COS, ExpressionType.COS));
		assertEquals(0, ExpressionType.getPrecedence(ExpressionType.COS, ExpressionType.COSH));
		assertEquals(0, ExpressionType.getPrecedence(ExpressionType.LN, ExpressionType.COS));
	}
}
