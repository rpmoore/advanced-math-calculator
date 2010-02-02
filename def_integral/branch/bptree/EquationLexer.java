package bptree;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.text.ParseException;



public class EquationLexer implements Lexer<EquationToken> {
	
	PushbackReader buffer;
	StringBuilder lexeme;
	EquationToken next;
	int lookahead;
	int position;
	
	public EquationLexer(String equation) throws IOException
	{
		buffer = new PushbackReader(new StringReader(equation));
		lookahead = buffer.read();
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
	
	/**
	 * Instead of throwing a ParseException I will return a <ExpressionType>.<BAD_TOKEN>.
	 * 
	 * @return
	 */
	private EquationToken getNext()
	{
		
		switch(lookahead)
		{
		case '+':
		
			break;
		case '-':
			
			break;
		case '*':
			
			break;
		case '/':
			
			break;
		case '^':
			
			break;
		case 'x':
			
			break;
		case 'e':
			
			break;
	
		
		
		}
		
		return null;
	}

}
