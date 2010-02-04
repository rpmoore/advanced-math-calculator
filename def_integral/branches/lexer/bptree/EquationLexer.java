package bptree;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.text.ParseException;



public class EquationLexer implements Lexer<EquationToken> {

	PushbackReader buffer;
	StringBuilder lexeme;
	EquationToken next;
	int current;
	int position;

	public EquationLexer(String equation)
	{
		buffer = new PushbackReader(new StringReader(equation));
		lexeme = new StringBuilder();
		next = getNext();
	}

	@Override
	public EquationToken peek() {
		// TODO Auto-generated method stub
		return next;
	}

	/**
	 * @return false if EOF has been hit, true otherwise.
	 */
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
	 * @throws ParseException 
	 */
	private EquationToken getNext()
	{
		restartLexeme();//This is needed to clear the lexeme for each new call.
		EquationToken retToken = null;
		try {
			if(current != -1)
			{
				current = skipWhiteSpace();
			}
			switch(current)
			{
			case '+':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.ADD, position);
				break;
			case '-':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.SUBTRACT, position);
				break;
			case '*':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.MULTIPLY, position);
				break;
			case '/':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.DIVIDE, position);
				break;
			case '^':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.POW, position);
				break;
			case 'x':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.VARIABLE, position);
				break;
			case 'e':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.E, position);
				break;
			case 'p':
				pushBackChar();
				matchString("pi");
				retToken = new EquationToken(lexeme.toString(), ExpressionType.PI,position);
				break;
			case '(':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.LEFTPAREN, position);
				break;
			case ')':
				retToken = new EquationToken(lexeme.toString(), ExpressionType.RIGHTPAREN, position);
				break;
			case 's'://sin and sinh
				
				break;
			case -1:
				retToken = new EquationToken("EOF", ExpressionType.EOF, position);
				break;
			default:
				if(Character.isDigit(current) || current == '.')//check for numbers first.
				{
					boolean hitPeriod = false;
					if(current == '.')
					{
						hitPeriod = true;
					}
					//get the next character.
					current = nextChar();
					
					while(Character.isDigit(current) || current == '.')
					{
						if(current == '.' && !hitPeriod)
						{
							hitPeriod = true;
						}
						else if(current == '.' && hitPeriod)//This is the error case.
						{
							retToken = new EquationToken("Bad Token: " + lexeme.toString(), ExpressionType.BAD_TOKEN,position);
							return retToken;
						}
						current = nextChar();
					}
					pushBackChar();//if the next character is not a period or a character we need to push it back on.
					
					retToken = new EquationToken(lexeme.toString(), ExpressionType.NUMBER,position);
				}
				else
				{
					retToken = new EquationToken("Unknown token starting with (" + lexeme.toString() + ")" , ExpressionType.BAD_TOKEN,position);
				}
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			retToken = new EquationToken("ERROR: IO", ExpressionType.BAD_TOKEN, position);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retToken;
	}

	
	private void matchString(String string) throws IOException, ParseException {
		
		for(int i = 0; i < string.length();++i)
		{
			match(string.charAt(i));
		}
	}
	/**
	 * Returns the next character from the file buffer after any
	 * white space and maintains a lexeme
	 * that will be used when certain tokens are found. Also maintains the line
	 * and position.
	 * 
	 * @throws IOException
	 */
	private int skipWhiteSpace() throws IOException {
		int retval = buffer.read();

		// look for all end of line possibilities
		// linefeed = '\n', carriage return='\r', or carriage return followed by
		// linefeed '\r\n'
		// this takes care of Mac, Unix, and Windows
		while (Character.isWhitespace(retval)) {
			if (retval == ' ' || retval == '\t') {
				position++;
				retval = buffer.read();

				// check for carriage return followed by linefeed
			} else if (retval == '\r') {
				position = 0; // below will be incrementing positionition for retval
				// char
				// will be returning the next character instead
				retval = buffer.read();
				// carriage return followed by linefeed
				if (retval == '\n') {
					retval = buffer.read();
				}
			} else {
				position = 0; // below will be incrementing positionition for retval
				// char
				// will be returning the next character instead
				retval = buffer.read();
			}
		}

		position++;
		lexeme.append((char) retval);
		return retval;
	}
	
    private void match(int c) throws ParseException, IOException {
        int next = nextChar();
        if (c != next) {
            throw new ParseException("Expected character '" + (char) c
                    + "' and got '" + (char) next + "'.",position);
        }
    }

    private int nextChar() throws IOException {
        int retval = buffer.read();
        position++;
        lexeme.append((char) retval);
        return retval;
    }
	private void restartLexeme() {
		lexeme.setLength(0);
	}
	
    /**
     * push the last char from the lexeme back onto the PushbackReader.
     * @throws IOException
     */
    private void pushBackChar() throws IOException {
    	char topush = lexeme.charAt(lexeme.length() - 1);
    	lexeme.deleteCharAt(lexeme.length() - 1);
    	this.buffer.unread((int) topush);
    	this.position--; 	
    }
}
