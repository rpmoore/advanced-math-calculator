package bptree;

import java.text.ParseException;

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
	private EquationTokenizer tokenizer;
	
	public static ParseTree makeTree(String expression,boolean optimize) throws ParseException
	{
		
		
		return new ParseTree(expression,optimize);
	}
	
	/**
	 * Creates a ParseTree object that has a unique expression.  Once 
	 * constructed the tree is generated and optimized.
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 */
	private ParseTree(String expression) {
		this(expression,true);
	}
	
	/**
	 * Creates a ParseTree object that has a unique expression.  Once 
	 * constructed the tree is generated and or optimized.
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 * @param optimize A boolean stating weather the expression tree should be optimized or not.
	 */
	private ParseTree(String expression, boolean optimize)
	{
		this.expression = expression;
		bTree = new BinaryTree<EquationToken>();
		parseToTree();
		if(optimize)
		{
			optimizeTree();
		}
	}

	/**
	 * Attempts to optimize the tree to reduce the time when computing a value in the tree.
	 */
	private void optimizeTree() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creates a tree from the expression that the tree is created with.
	 */
	private void parseToTree() {
		EquationToken currentToken = null;
		tokenizer = new EquationTokenizer(this.expression);
		

	}

	
	
	
	

	/**
	 * Computes a value by replacing every variable in the tree with the passed in index.
	 * 
	 * @param index The index to compute a value on given the expression that the tree is created with.
	 */
	public double eval(double index) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public BinaryTree<EquationToken> getTree()
	{
		return bTree;
	}
}