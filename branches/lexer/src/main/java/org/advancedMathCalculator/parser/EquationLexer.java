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
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.text.ParseException;

public class EquationLexer implements Lexer<EquationToken> {

	private final PushbackReader buffer;
	private final StringBuilder lexeme;
	private EquationToken next;
	private int current;
	private int position;

	public EquationLexer(String equation) {
		buffer = new PushbackReader(new StringReader(equation));
		lexeme = new StringBuilder();
		next = getNext();
	}

	/**
	 * Instead of throwing a ParseException I will return a
	 * <ExpressionType>.<BAD_TOKEN>.
	 * 
	 * @return
	 * @throws ParseException
	 */
	private EquationToken getNext() {
		restartLexeme();// This is needed to clear the lexeme for each new call.
		EquationToken retToken = null;
		try {
			if (current != -1) {
				current = skipWhiteSpace();
			}
			switch (current) {
			case '+':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.ADD, position);
				break;
			case '-':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.SUBTRACT, position);
				break;
			case '*':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.MULTIPLY, position);
				break;
			case '/':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.DIVIDE, position);
				break;
			case '^':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.POW, position);
				break;
			case 'x':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.VARIABLE, position);
				break;
			case 'e':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.E, position);
				break;
			case 'p':
				pushBackChar();
				matchString("pi");
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.PI, position);
				break;
			case '(':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.LEFTPAREN, position);
				break;
			case ')':
				retToken = new EquationToken(lexeme.toString(),
						ExpressionType.RIGHTPAREN, position);
				break;
			case 's':// sin and sinh
				pushBackChar();
				matchString("sin");
				current = nextChar();
				if (current == 'h') {
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.SINH, position);
				} else {
					pushBackChar();
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.SIN, position);
				}
				break;
			case 'c':
				pushBackChar();
				matchString("cos");
				current = nextChar();
				if (current == 'h') {
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.COSH, position);
				} else {
					pushBackChar();
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.COS, position);
				}
				break;
			case 't':
				pushBackChar();
				matchString("tan");
				current = nextChar();
				if (current == 'h') {
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.TANH, position);
				} else {
					pushBackChar();
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.TAN, position);
				}
				break;
			case 'l':
				current = nextChar();
				if (current == 'n') {
					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.LN, position);
				} else {
					if (current == 'o') {
						if ((current = nextChar()) == 'g') {
							retToken = new EquationToken(lexeme.toString(),
									ExpressionType.LOG, position);
						} else {
							retToken = new EquationToken("Unknown token: "
									+ lexeme.toString(),
									ExpressionType.BAD_TOKEN, position);
						}
					} else {
						retToken = new EquationToken("Unknown token: "
								+ lexeme.toString(), ExpressionType.BAD_TOKEN,
								position);
					}
				}
				break;
			case -1:
				retToken = new EquationToken("EOF", ExpressionType.EOF,
						position);
				break;
			default:
				if (Character.isDigit(current) || (current == '.'))// check for
																	// numbers
																	// first.
				{
					boolean hitPeriod = false;
					if (current == '.') {
						hitPeriod = true;
					}
					// get the next character.
					current = nextChar();

					while (Character.isDigit(current) || (current == '.')) {
						if ((current == '.') && !hitPeriod) {
							hitPeriod = true;
						} else if ((current == '.') && hitPeriod)// This is the
																	// error
																	// case.
						{
							retToken = new EquationToken("Bad Token: "
									+ lexeme.toString(),
									ExpressionType.BAD_TOKEN, position);
							return retToken;
						}
						current = nextChar();
					}
					pushBackChar();// if the next character is not a period or a
									// character we need to push it back on.

					retToken = new EquationToken(lexeme.toString(),
							ExpressionType.NUMBER, position);
				} else {
					retToken = new EquationToken(
							"Unknown token starting with (" + lexeme.toString()
									+ ")", ExpressionType.BAD_TOKEN, position);
				}
				break;
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			retToken = new EquationToken("ERROR: IO", ExpressionType.BAD_TOKEN,
					position);
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			retToken = new EquationToken("Unknown token: " + lexeme.toString(),
					ExpressionType.BAD_TOKEN, position);
		}
		return retToken;
	}

	/**
	 * @return false if EOF has been hit, true otherwise.
	 */
	public boolean hasMoreElements() {
		if (next.getType() != ExpressionType.EOF) {
			return true;
		}
		return false;
	}

	private void match(int c) throws ParseException, IOException {
		final int next = nextChar();
		if (c != next) {
			throw new ParseException("Expected character '" + (char) c
					+ "' and got '" + (char) next + "'.", position);
		}
	}

	private void matchString(String string) throws IOException, ParseException {

		for (int i = 0; i < string.length(); ++i) {
			match(string.charAt(i));
		}
	}

	private int nextChar() throws IOException {
		final int retval = buffer.read();
		position++;
		lexeme.append((char) retval);
		return retval;
	}

	public EquationToken nextElement() {
		EquationToken token;
		token = next;
		next = getNext();
		return token;
	}

	public EquationToken peek() {
		// TODO Auto-generated method stub
		return next;
	}

	/**
	 * push the last char from the lexeme back onto the PushbackReader.
	 * 
	 * @throws IOException
	 */
	private void pushBackChar() throws IOException {
		final char topush = lexeme.charAt(lexeme.length() - 1);
		lexeme.deleteCharAt(lexeme.length() - 1);
		this.buffer.unread(topush);
		this.position--;
	}

	private void restartLexeme() {
		lexeme.setLength(0);
	}

	/**
	 * Returns the next character from the file buffer after any white space and
	 * maintains a lexeme that will be used when certain tokens are found. Also
	 * maintains the line and position.
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
			if ((retval == ' ') || (retval == '\t')) {
				position++;
				retval = buffer.read();

				// check for carriage return followed by linefeed
			} else if (retval == '\r') {
				position = 0; // below will be incrementing positionition for
								// retval
				// char
				// will be returning the next character instead
				retval = buffer.read();
				// carriage return followed by linefeed
				if (retval == '\n') {
					retval = buffer.read();
				}
			} else {
				position = 0; // below will be incrementing positionition for
								// retval
				// char
				// will be returning the next character instead
				retval = buffer.read();
			}
		}

		position++;
		lexeme.append((char) retval);
		return retval;
	}
}
