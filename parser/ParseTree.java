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

import java.text.ParseException;
import java.util.concurrent.LinkedBlockingQueue;


import dataStructures.BinaryTree;
import dataStructures.TreeNode;
import defIntegral.Calculate;

/**
 * Creates a Binary tree from an mathematical expression.  It then allows for the computations of values
 * based off of indexes passed in.  ie  The ParseTree represents a function f and you can insert a value x
 * such that the result y = f(x).  X can be any value inside of the domain for the defined expression.  If X
 * is outside of the domain the result is undefined.
 * @author Ryan
 *
 */
public class ParseTree implements Calculate {
	private String expression;
	private BinaryTree<EquationToken> bTree;//This is the final tree
	private EquationToken lastToken;
	private LinkedBlockingQueue<EquationToken> queue;

	/**
	 * Creates a ParseTree object that has a unique expression.  Once 
	 * constructed the tree is generated and optimized.
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 * @throws ParseTreeGenerationException 
	 */
	private ParseTree(String expression) throws ParseException {
		this(expression,true);
	}

	/**
	 * Creates a ParseTree object that has a unique expression.  Once 
	 * constructed the tree is generated and or optimized.
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 * @param optimize A boolean stating weather the expression tree should be optimized or not.
	 * @throws ParseTreeGenerationException 
	 */
	private ParseTree(String expression, boolean optimize) throws ParseException
	{
		this.expression = expression;
		bTree = new BinaryTree<EquationToken>();
		this.queue = new LinkedBlockingQueue<EquationToken>();
		enqueue();
		parseToTree();
		if(optimize)
		{
			optimizeTree();
		}
	}

	/**
	 * Removes implicit multiplication from the expression and makes it explicit.
	 * Edits expression.
	 */
	private void enqueue() {
		EquationLexer lexer = new EquationLexer(this.expression);
		EquationToken current = null;
		EquationToken next = null;
		StringBuilder newExpression = new StringBuilder();
		while(lexer.hasMoreElements())
		{
			current = lexer.nextElement();
			queue.add(current);
			if(ExpressionType.isTerm(current.getType()))
			{
				next = lexer.peek();
				if(ExpressionType.isTerm(next.getType()) || ExpressionType.isTerm(next.getType()) || next.getType() == ExpressionType.LEFTPAREN)
				{
					queue.add(new EquationToken("*", ExpressionType.MULTIPLY));
				}
			}
			else if(current.getType() == ExpressionType.RIGHTPAREN)
			{
				next = lexer.peek();
				if(next.getType() == ExpressionType.LEFTPAREN)
				{
					queue.add(new EquationToken("*", ExpressionType.MULTIPLY));
				}
			}
		}
		
		this.expression = newExpression.toString();
	}

	/**
	 * Gets the next token from the lexer.
	 * @return Returns the next token.
	 * @throws ParseException If EOF throws ParseException.
	 */
	private EquationToken nextToken() throws ParseException
	{
		EquationToken next;
		next = queue.poll();
		if(next == null)
		{
			return null;
		}
		if(next.getType() == ExpressionType.BAD_TOKEN)
		{
			throw new ParseException(next.getToken(), next.getPosition());
		}
		lastToken = next;
		return next;
	}

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	private EquationToken peek() throws ParseException
	{
		EquationToken next = queue.peek();
		if(next == null)
		{
			return null;
		}
		if(next.getType() == ExpressionType.BAD_TOKEN)
		{
			throw new ParseException(next.getToken(), next.getPosition());
		}
		return next;
	}

	/**
	 * Attempts to optimize the tree to reduce the time when computing a value in the tree.
	 */
	private void optimizeTree() {

		try {
			double retVal = optimize(bTree.getRoot());
			TreeNode<EquationToken> tempNode = new TreeNode<EquationToken>(new EquationToken("" + retVal, ExpressionType.NUMBER));
			bTree.setRoot(tempNode);
		} catch (OptimizeException e) {
			//I should not need to do anything, This just means that not all of the
			//tree could be optimized away.
			return;
		}

	}

	private double optimize(TreeNode<EquationToken> current) throws OptimizeException
	{
		boolean isException = false;
		double leftRet = 0.0;
		double rightRet = 0.0;
		OptimizeException retException = null;
		if(current.getLeft() == null && current.getRight() == null)
		{
			if(current.getItem().getType().equals(ExpressionType.VARIABLE))
			{
				throw new OptimizeException();
			}
			else
			{
				return ExpressionType.eval(current.getItem(), 0, 0, 0);//This should only be
				//numeric values that 
				//do not need any other data.
			}
		}
		else
		{
			if(current.getLeft() != null)
			{
				try
				{
					leftRet = optimize(current.getLeft());
				}
				catch(OptimizeException e)
				{
					retException = e;
					isException = true;
				}
			}
			if(current.getRight() != null)
			{
				try
				{
					rightRet = optimize(current.getRight());
					if(isException)
					{
						TreeNode<EquationToken> tempNode = new TreeNode<EquationToken>(new EquationToken("" + rightRet, ExpressionType.NUMBER));						
						current.setRight(tempNode);
						throw retException;
					}
				}
				catch(OptimizeException e)
				{
					if(!isException)
					{
						TreeNode<EquationToken> tempNode = new TreeNode<EquationToken>(new EquationToken("" + leftRet, ExpressionType.NUMBER));
						current.setLeft(tempNode);
					}
					throw e;
				} //End catch OptimizeException
			} //End if rightNode != null
			return ExpressionType.eval(current.getItem(), leftRet, rightRet, 0); // There should be no index.
		} //end else	
	}

	/**
	 * Creates a tree from the expression that the tree is created with.
	 * @throws ParseTreeGenerationException 
	 */
	private void parseToTree() throws ParseException {
		bTree = rootLevel();//Tree should be parsed at this point.
	}


	//Base level for parsing the math equation.
	private BinaryTree<EquationToken> rootLevel() throws ParseException
	{
		BinaryTree<EquationToken> t;//temp binary tree that
		//will be returned up.
		t = secondLevel();
		EquationToken next = peek();
		while(next != null && (next.getType().equals(ExpressionType.ADD) || 
				next.getType().equals(ExpressionType.SUBTRACT)))
		{
			next = nextToken();	
			BinaryTree<EquationToken> t1 = secondLevel();
			t = mkNode(next,t,t1);

			next = peek();//get the next item in the list.
		}

		return t;
	}


	private BinaryTree<EquationToken> secondLevel() throws ParseException
	{
		BinaryTree<EquationToken> t;

		t = thirdLevel();
		EquationToken next = peek();
		while(next != null && (next.getType().equals(ExpressionType.MULTIPLY) ||
				next.getType().equals(ExpressionType.DIVIDE)))
		{
			next = nextToken();
			BinaryTree<EquationToken> t1 = thirdLevel();
			t = mkNode(next, t, t1);


			next = peek();//get the next item in the list.
		}
		return t;
	}

	private BinaryTree<EquationToken> thirdLevel() throws ParseException
	{
		BinaryTree<EquationToken> t;

		t = fourthLevel();
		EquationToken next = peek();//this next
		if(next != null && next.getType().equals(ExpressionType.POW))
		{
			next = nextToken();				  //and this next should be the same.
			BinaryTree<EquationToken> t1 = fourthLevel();
			return mkNode(next, t, t1);
		}
		else
		{
			return t;
		}
	}

	private BinaryTree<EquationToken> fourthLevel() throws ParseException
	{
		BinaryTree<EquationToken> t;

		EquationToken next = peek();
		if(next != null && ExpressionType.isTerm(next.getType()))
		{
			next = nextToken();//This must happen before the next if statement.
			t = mkNode(next);

			return t;
		}
		else if(next != null && ExpressionType.isFunction(next.getType()))
		{
			next = nextToken();
			EquationToken tempNext = peek();
			if(tempNext.getType() == ExpressionType.LEFTPAREN)
			{
				tempNext = nextToken();//get rid of the LEFTPAREN
				t = rootLevel();
				tempNext = nextToken();
				if(tempNext.getType() != ExpressionType.RIGHTPAREN)
				{
					throw new ParseException("Expected " + ExpressionType.RIGHTPAREN + " but got " + tempNext.getToken(),tempNext.getPosition());
				}
			
				return mkNode(next, t);
			}
			else
			{
				throw new ParseException("Expected " + ExpressionType.LEFTPAREN + " but got " + tempNext.getToken(),tempNext.getPosition());
				
			}
			
		}
		else if(next != null &&next.getType().equals(ExpressionType.LEFTPAREN))
		{
			next = nextToken();
			t = rootLevel();
			next = nextToken();
			if(!next.getType().equals(ExpressionType.RIGHTPAREN))
			{
				throw new ParseException("Expected " + ExpressionType.RIGHTPAREN + " but got " + lastToken.getToken(),lastToken.getPosition());
			}
			return t;
		}
		else if(next != null &&next.getType().equals(ExpressionType.SUBTRACT))
		{
			next = nextToken();
			t = secondLevel();
			//uniary minus sign... need to figure out how to code this one up.

			return mkNode(next,t);
		}
		else
		{
			throw new ParseException("Unexpected token " + lastToken.getToken() ,lastToken.getPosition());
		}
	}

	/**
	 * Creates a subtree with only a root.
	 * @param next
	 * @return
	 */
	private BinaryTree<EquationToken> mkNode(EquationToken next) {
		return mkNode(next,null,null);
	}

	/**
	 * Creates a subtree where the first left child is Double.Nan for computing the unary - value, and for functions.
	 * @param next
	 * @param t
	 * @return
	 */
	private BinaryTree<EquationToken> mkNode(EquationToken next,BinaryTree<EquationToken> t)
	{
		BinaryTree<EquationToken> t1 = new BinaryTree<EquationToken>();
		t1.setRoot(new TreeNode<EquationToken>(new EquationToken("Nan", ExpressionType.NAN)));
		return mkNode(next,t1,t);
	}

	/**
	 * Creates a subtree where next is the root, t is the left subtree, and t1 is the right subtree.
	 * @param next
	 * @param left
	 * @param right
	 * @return A new BinayTree reflecting the equation generated so far.
	 */
	private BinaryTree<EquationToken> mkNode(EquationToken next,
			BinaryTree<EquationToken> left, BinaryTree<EquationToken> right) 
			{

		BinaryTree<EquationToken> retTree = new BinaryTree<EquationToken>();
		TreeNode<EquationToken> newNode = new TreeNode<EquationToken>(next);
		newNode.setLeft(left);
		newNode.setRight(right);
		retTree.setRoot(newNode);
		return retTree;
			}

	/**
	 * Computes a value by replacing every variable in the tree with the passed in index.
	 * 
	 * @param index The index to compute a value on given the expression that the tree is created with.
	 */
	public double eval(double index) {

		return eval(index,bTree.getRoot());
	}

	private double eval(double index, TreeNode<EquationToken> current)
	{
		double left = 0.0;
		double right = 0.0;
		if(current.getLeft()!= null)
		{
			left = eval(index,current.getLeft());
		}
		if(current.getRight() != null)
		{
			right = eval(index, current.getRight());
		}

		return ExpressionType.eval(current.getItem(),left, right,index);
	}

	public BinaryTree<EquationToken> getTree()
	{
		return bTree;
	}

	public static ParseTree generate(String expr) throws ParseException {		
		return new ParseTree(expr,true);
	}
	public static ParseTree generate(String expr,boolean optimize) throws ParseException {		
		return new ParseTree(expr,optimize);
	}
}