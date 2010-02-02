package bptree;

import java.io.PushbackReader;
import java.io.StringReader;

public class EquationLexer implements Lexer<EquationToken> {
	
	PushbackReader buffer;
	EquationToken next;
	
	public EquationLexer(String equation)
	{
		buffer = new PushbackReader(new StringReader(equation));
		next = getNext();
	}
	
	@Override
	public EquationToken peek() {
		// TODO Auto-generated method stub
		return next;
	}

	@Override
	public boolean hasMoreElements() {
		if(next.getType() != ExpressionType.EOF)
		{
			return true;
		}
		return false;
	}

	@Override
	public EquationToken nextElement() {
		EquationToken token;
		token = next;
		next = getNext();
		return token;
	}
	
	private EquationToken getNext()
	{
		
		
		
		return null;
	}

}
