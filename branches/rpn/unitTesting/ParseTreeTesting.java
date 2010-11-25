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
import parser.EquationToken;
import parser.ExpressionType;
import parser.ParseTree;
import dataStructures.BinaryTree;
import dataStructures.TreeNode;
import defIntegral.CalculateException;
import defIntegral.SimpsonsRule;

public class ParseTreeTesting extends TestCase {
   
	public void testcheckOPT()
	{
		assertTrue(ExpressionType.isOp(ExpressionType.ADD));
		assertTrue(ExpressionType.isOp(ExpressionType.SUBTRACT));
		assertFalse(ExpressionType.isOp(ExpressionType.NUMBER));
	}
	
	public void testgetOPT()
	{
		assertEquals(ExpressionType.ADD, ExpressionType.getType("+"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123124.3"));
		assertEquals(ExpressionType.NUMBER, ExpressionType.getType("123"));
		assertEquals(ExpressionType.OTHER, ExpressionType.getType("hello"));
	}

    public void testmakeTokensOne()
    {
    	EquationLexer tokenizer = new EquationLexer("+");
    	assertTrue(tokenizer.hasMoreElements());
    	EquationToken token = tokenizer.nextElement();
    	assertTrue(token.getType() == ExpressionType.ADD);
    	assertFalse(tokenizer.hasMoreElements());
    }

   
    public void testmakeTokensZero()
    {
    	EquationLexer tokenizer = new EquationLexer("");
    	assertFalse(tokenizer.hasMoreElements());
    	assertTrue(tokenizer.nextElement().getType() == ExpressionType.EOF);
    }

    
    public void testmakeTokensThree()
    {
    	EquationLexer tokenizer = new EquationLexer("2 + 3");
    	EquationToken token = null;
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertEquals("3",token.getToken());
    	assertFalse(tokenizer.hasMoreElements());
    }
    
    public void testmakeTokensThreeNoWhitespace()
    {
    	EquationLexer tokenizer = new EquationLexer("2+3");
    	for(int i = 0; i < 3; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    
    
    public void testmakeTokensSeven()
    {
    	EquationLexer tokenizer = new EquationLexer("(2+3)/2");
    	for(int i = 0; i < 7; i++)
    	{
    		assertTrue(tokenizer.hasMoreElements());
    		EquationToken token = tokenizer.nextElement();
    		
    		assertTrue(token.getType() != ExpressionType.OTHER);
    	}
    	assertFalse(tokenizer.hasMoreElements());
    }
    
    
    public void testPIandE()
    {
    	//This test should pass every time as written.  It is very specific
    	//and really helps illustrate how the Tokenizer should operate.
    	EquationLexer tokenizer = new EquationLexer("e+pi");
    	EquationToken token = tokenizer.nextElement();
    	assertEquals(ExpressionType.E, token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.ADD,token.getType());
    	token = tokenizer.nextElement();
    	assertEquals(ExpressionType.PI,token.getType());
    	assertFalse(tokenizer.hasMoreElements());
    }
    
	
	
	public void testInsertBasic()
	{
		ParseTree tree= null;
		try {
			tree = ParseTree.makeTree("2 + 3",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		TreeNode<EquationToken> root = btree.getRoot(); 
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals("2", root.getLeft().getItem().getToken());
		assertFalse(btree.isEmpty());
	}
	
	
	
	public void testInsertBasicCombined()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("2+3/x",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals(ExpressionType.DIVIDE,root.getRight().getItem().getType());
		assertEquals("3", root.getRight().getLeft().getItem().getToken());
		assertEquals("x", root.getRight().getRight().getItem().getToken());
	}
	
	
	public void testBasicEvalOne()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("2+3/x",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(3, tree.eval(3),0);
	}
	
	
	public void testBasicEvalTwo()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("3+5*5", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(28.0, tree.eval(0), 0);
	}
	
	
	public void testBasicEvalThree()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("e+pi", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals((Math.E + Math.PI), tree.eval(0), 0);
	}
	
	
	public void testBasicEvalFour()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("(12-8)^2/2", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(8, tree.eval(0), 0);
	}
	
	
	public void testBasicEvalFive()
	{
		ParseTree treeOptOn = null;
		ParseTree treeOptOff = null;		
		try
		{
			treeOptOn = ParseTree.makeTree("(12-8)^2/2", true);
			treeOptOff = ParseTree.makeTree("(12-8)^2/2", false);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(8, treeOptOn.eval(0), 0);
		assertEquals(8, treeOptOff.eval(0),0);
	}
	
	
	public void testBasicEvalSix()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("-2+3/x",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(-1.0, tree.eval(3),0);
	}
	
	
	public void testBasicEvalSeven()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("12x + 4",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(16.0, tree.eval(1),0);
	}
	
	
	
	public void testBasicEvalEight()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("3pix",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(3 * Math.PI * 2, tree.eval(2),0);
	}
	
	
	public void testBasicEvalNine() throws CalculateException
	{
		ParseTree tree = null;
		ParseTree tree2 = null;
		try {
			tree = ParseTree.makeTree("2x^2",false);
			tree2 = ParseTree.makeTree("2*x^2",false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(SimpsonsRule.compute(tree2, 1, 2), SimpsonsRule.compute(tree, 1, 2),0);
	}
	
	
	public void testOptimizingOne()
	{
		ParseTree tree = null;
		try
		{
			tree = ParseTree.makeTree("3+5*5", true);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		
		assertEquals(28.0, Double.parseDouble(root.getItem().getToken()),0);
		assertNull(root.getLeft());
		assertNull(root.getRight());
	}
	
	
	public void testBadEquationInputOne()
	{
		ParseTree tree = null;		
		try
		{
			tree = ParseTree.makeTree("(12-8)^2/", false);
			assertTrue(false);
		}
		catch (ParseException e) {
			assertNull(tree);
			assertTrue(true);
		}

	}
	
	
	public void testBadEquationInputTwo()
	{
		ParseTree tree = null;		
		try
		{
			tree = ParseTree.makeTree("Bad", false);
			assertTrue(false);
		}
		catch (ParseException e) {
			assertEquals(1, e.getErrorOffset());
			assertNull(tree);
			assertTrue(true);
		}
	}
	
	
	public void testBadEquationInputThree()
	{
		ParseTree tree = null;		
		try
		{
			tree = ParseTree.makeTree("Bad + x", false);
			assertTrue(false);
		}
		catch (ParseException e) {
			assertEquals(1, e.getErrorOffset());
			assertNull(tree);
			assertTrue(true);
		}
	}	
	public void testBadEquationInputFour()
	{
		ParseTree tree = null;
		try {
			tree = ParseTree.makeTree("3PIx",false); //PI should be lower case.
			tree.eval(4);//just have this to get rid of the warning.
			assertTrue(false);

		} catch (ParseException e) {
			assertEquals(2, e.getErrorOffset());//The offset has changed since the first token was made.
			assertTrue(true);//This is the correct case to hit.
		}
		
	}
}
