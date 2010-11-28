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

import junit.framework.TestCase;

import parser.ExpressionLexer;

public class ExpressionLexerTest extends TestCase{

	public void testMult()
	{
		ExpressionLexer lex = new ExpressionLexer("2*3");
		assertEquals("2", lex.nextElement().getToken());
		assertEquals("*",lex.nextElement().getToken());
		assertEquals("3",lex.nextElement().getToken());
	}
	
	public void testImplicitMult()
	{
		ExpressionLexer lex = new ExpressionLexer("3x");
		assertEquals("3", lex.nextElement().getToken());
		assertEquals("*", lex.nextElement().getToken());
		assertEquals("x", lex.nextElement().getToken());
	}
	
	public void testImplicitSub()
	{
		ExpressionLexer lex = new ExpressionLexer("3-x");
		assertEquals("3", lex.nextElement().getToken());
		assertEquals("+", lex.nextElement().getToken());
		assertEquals("-", lex.nextElement().getToken());
		assertEquals("x", lex.nextElement().getToken());
	}
}
