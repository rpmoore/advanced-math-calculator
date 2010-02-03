package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DataStructures.BinaryTree;
import DataStructures.TreeNode;
import bptree.EquationToken;
import bptree.ExpressionType;
import bptree.ParseTreeGenerationException;
import bptree.ParseTreeNewLexer;

public class ParseTreeNewLexerTesting {
	
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ParseTreeNewLexerTesting.class);
    }

	
	@Test
	public void testInsertBasic()
	{
		ParseTreeNewLexer tree= null;
		try {
			tree = ParseTreeNewLexer.makeTree("2 + 3",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		TreeNode<EquationToken> root = btree.getRoot(); 
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals("2", root.getLeft().getItem().getToken());
		assertFalse(btree.isEmpty());
	}
	
	
	@Test
	public void testInsertBasicCombined()
	{
		ParseTreeNewLexer tree = null;
		try {
			tree = ParseTreeNewLexer.makeTree("2+3/x",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		assertEquals(ExpressionType.ADD, root.getItem().getType());
		assertEquals(ExpressionType.DIVIDE,root.getRight().getItem().getType());
		assertEquals("3", root.getRight().getLeft().getItem().getToken());
		assertEquals("x", root.getRight().getRight().getItem().getToken());
	}
	
	@Test
	public void testBasicEvalOne()
	{
		ParseTreeNewLexer tree = null;
		try {
			tree = ParseTreeNewLexer.makeTree("2+3/x",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		
		assertEquals(3, tree.eval(3),0);
	}
	
	@Test
	public void testBasicEvalTwo()
	{
		ParseTreeNewLexer tree = null;
		try
		{
			tree = ParseTreeNewLexer.makeTree("3+5*5", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals(28.0, tree.eval(0), 0);
	}
	
	@Test
	public void testBasicEvalThree()
	{
		ParseTreeNewLexer tree = null;
		try
		{
			tree = ParseTreeNewLexer.makeTree("e+pi", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals((Math.E + Math.PI), tree.eval(0), 0);
	}
	
	@Test
	public void testBasicEvalFour()
	{
		ParseTreeNewLexer tree = null;
		try
		{
			tree = ParseTreeNewLexer.makeTree("(12-8)^2/2", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals(8, tree.eval(0), 0);
	}
	
	@Test
	public void testBasicEvalFive()
	{
		ParseTreeNewLexer treeOptOn = null;
		ParseTreeNewLexer treeOptOff = null;		
		try
		{
			treeOptOn = ParseTreeNewLexer.makeTree("(12-8)^2/2", true);
			treeOptOff = ParseTreeNewLexer.makeTree("(12-8)^2/2", false);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}

		assertEquals(8, treeOptOn.eval(0), 0);
		assertEquals(8, treeOptOff.eval(0),0);
	}
	
	@Test
	public void testBasicEvalSix()
	{
		ParseTreeNewLexer tree = null;
		try {
			tree = ParseTreeNewLexer.makeTree("-2+3/x",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		
		assertEquals(-1.0, tree.eval(3),0);
	}
	
	@Test
	public void testBasicEvalSeven()
	{
		ParseTreeNewLexer tree = null;
		try {
			tree = ParseTreeNewLexer.makeTree("12x + 4",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		
		assertEquals(16.0, tree.eval(1),0);
	}
	
	@Test
	public void testBasicEvalEight()
	{
		ParseTreeNewLexer tree = null;
		try {
			tree = ParseTreeNewLexer.makeTree("3PIx",false);
		} catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		
		assertEquals(3 * Math.PI * 2, tree.eval(2),0);
	}
	
	@Test
	public void testOptimizingOne()
	{
		ParseTreeNewLexer tree = null;
		try
		{
			tree = ParseTreeNewLexer.makeTree("3+5*5", true);
		}
		catch (ParseTreeGenerationException e) {
			e.printStackTrace();
		}
		BinaryTree<EquationToken> btree = tree.getTree();
		
		TreeNode<EquationToken> root = btree.getRoot();
		
		assertEquals(28.0, Double.parseDouble(root.getItem().getToken()),0);
		assertNull(root.getLeft());
		assertNull(root.getRight());
	}
	
	@Test
	public void testBadEquationInputOne()
	{
		ParseTreeNewLexer tree = null;		
		try
		{
			tree = ParseTreeNewLexer.makeTree("(12-8)^2/", false);
			assertTrue(false);
		}
		catch (ParseTreeGenerationException e) {
			assertNull(tree);
			assertTrue(true);
		}

	}
	
	@Test
	public void testBadEquationInputTwo()
	{
		ParseTreeNewLexer tree = null;		
		try
		{
			tree = ParseTreeNewLexer.makeTree("Bad", false);
			assertTrue(false);
		}
		catch (ParseTreeGenerationException e) {
			assertNull(tree);
			assertTrue(true);
		}
	}
	
	@Test
	public void testBadEquationInputThree()
	{
		ParseTreeNewLexer tree = null;		
		try
		{
			tree = ParseTreeNewLexer.makeTree("Bad + x", false);
			assertTrue(false);
		}
		catch (ParseTreeGenerationException e) {
			assertNull(tree);
			assertTrue(true);
		}
	}
}
