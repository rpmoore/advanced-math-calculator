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
import java.text.ParseException;

import junit.framework.TestCase;

import parser.EquationLexer;
import parser.ExpressionType;
import parser.ParseTree;



public class TrigFunctionTesting extends TestCase {
    
    
    public void testLexSin()
    {
    	EquationLexer lexer = new EquationLexer("sin(x)");
    	
    	assertEquals(ExpressionType.SIN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
        
    public void testLexCos()
    {
    	EquationLexer lexer = new EquationLexer("cos(x)");
    	
    	assertEquals(ExpressionType.COS, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexTan()
    {
    	EquationLexer lexer = new EquationLexer("tan(x)");
    	
    	assertEquals(ExpressionType.TAN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexSinh()
    {
    	EquationLexer lexer = new EquationLexer("sinh(x)");
    	
    	assertEquals(ExpressionType.SINH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexCosh()
    {
    	EquationLexer lexer = new EquationLexer("cosh(x)");
    	
    	assertEquals(ExpressionType.COSH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexTanh()
    {
    	EquationLexer lexer = new EquationLexer("tanh(x)");
    	
    	assertEquals(ExpressionType.TANH, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLn()
    {
    	EquationLexer lexer = new EquationLexer("ln(x)");
    	
    	assertEquals(ExpressionType.LN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLog()
    {
    	EquationLexer lexer = new EquationLexer("log(x)");
    	
    	assertEquals(ExpressionType.LOG, lexer.nextElement().getType());
    	assertEquals(ExpressionType.LEFTPAREN, lexer.nextElement().getType());
    	assertEquals(ExpressionType.VARIABLE, lexer.nextElement().getType());
    	assertEquals(ExpressionType.RIGHTPAREN, lexer.nextElement().getType());
    }
    
    public void testLexLogEval()
    {
    	try {
			ParseTree tree = ParseTree.generate("log(x)", false);
			
			assertEquals(Math.log10(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexSinEval()
    {
    	try {
			ParseTree tree = ParseTree.generate("sin(x)", false);
			
			assertEquals(Math.sin(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexCosEval()
    {
    	try {
			ParseTree tree = ParseTree.generate("cos(x)", false);
			
			assertEquals(Math.cos(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexTanEval()
    {
    	try {
			ParseTree tree = ParseTree.generate("tan(x)", false);
			
			assertEquals(Math.tan(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    public void testLexLNEval()
    {
    	try {
			ParseTree tree = ParseTree.generate("ln(x)", false);
			
			assertEquals(Math.log(5), tree.eval(5),0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
}