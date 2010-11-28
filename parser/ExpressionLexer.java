package parser;
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
public class ExpressionLexer implements Lexer<EquationToken> {

	private final EquationLexer lexer;
	private EquationToken prev;
	private EquationToken next;
	public ExpressionLexer(String string)
	{
		this.lexer = new EquationLexer(string);
		this.prev = null;
		this.next = null;
	}
	
	@Override
	public boolean hasMoreElements() {
		if(next != null)
		{
			return true;
		}
		return lexer.hasMoreElements();
	}

	@Override
	public EquationToken nextElement() {
		EquationToken curr = lexer.peek();
		if(next != null)
		{
			EquationToken ret = next;
			next = null;
			prev = ret;
			return ret;
		}
		if(prev != null && (curr.getType().isTerm() && prev.getType().isTerm()))
		{
			EquationToken ret = new EquationToken("*", ExpressionType.MULTIPLY);
			prev = ret;
			return ret;
		}
		if(curr.getType() == ExpressionType.SUBTRACT && ( prev != null && prev.getType().isTerm()))
		{
			EquationToken ret = new EquationToken("+", ExpressionType.ADD);
			prev = ret;
			next = lexer.nextElement();
			return ret;
		}
		prev = curr;
		return lexer.nextElement();
	}

	@Override
	public EquationToken peek() {
		return lexer.peek();
	}
}
