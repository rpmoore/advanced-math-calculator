package bptree;


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
	private EquationToken lastToken;
	
	public static ParseTree makeTree(String expression,boolean optimize) throws ParseTreeGenerationException
	{
		return new ParseTree(expression,optimize);
	}
	
	/**
	 * Creates a ParseTree object that has a unique expression.  Once 
	 * constructed the tree is generated and optimized.
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 * @throws ParseTreeGenerationException 
	 */
	private ParseTree(String expression) throws ParseTreeGenerationException {
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
	private ParseTree(String expression, boolean optimize) throws ParseTreeGenerationException
	{
		this.expression = expression;
		bTree = new BinaryTree<EquationToken>();
		parseToTree();
		if(optimize)
		{
			optimizeTree();
		}
	}

	private EquationToken nextToken() throws ParseTreeGenerationException
	{
		EquationToken next = tokenizer.nextElement();
		if(next == null)
		{
			throw new ParseTreeGenerationException(lastToken.getToken());
		}
		lastToken = next;
		return next;
	}
	
	/**
	 * Attempts to optimize the tree to reduce the time when computing a value in the tree.
	 */
	private void optimizeTree() {
		// TODO Auto-generated method stub
	
		try {
			double retVal = optimize(bTree.getRoot());
			TreeNode<EquationToken> tempNode = new TreeNode<EquationToken>(new EquationToken("" + retVal, ExpressionType.NUMBER));
			bTree.setRoot(tempNode);
		} catch (OptimizeException e) {
			// TODO Auto-generated catch block
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
	private void parseToTree() throws ParseTreeGenerationException {
		tokenizer = new EquationTokenizer(this.expression);
		bTree = rootLevel();//Tree should be parsed at this point.
	}

	
	//Base level for parsing the math equation.
	private BinaryTree<EquationToken> rootLevel() throws ParseTreeGenerationException
	{
		BinaryTree<EquationToken> t;//temp binary tree that
				  				    //will be returned up.
		t = secondLevel();
		EquationToken next = tokenizer.peek();
		while(next != null && (next.getType().equals(ExpressionType.ADD) || 
			  next.getType().equals(ExpressionType.SUBTRACT)))
		{
			next = nextToken();	
			BinaryTree<EquationToken> t1 = secondLevel();
			t = mkNode(next,t,t1);
			
			next = tokenizer.peek();//get the next item in the list.
		}
		
		return t;
	}
	

	private BinaryTree<EquationToken> secondLevel() throws ParseTreeGenerationException
	{
		BinaryTree<EquationToken> t;
		
		t = thirdLevel();
		EquationToken next = tokenizer.peek();
		while(next != null && (next.getType().equals(ExpressionType.MULTIPLY) ||
			  next.getType().equals(ExpressionType.DIVIDE)))
		{
			next = nextToken();
			BinaryTree<EquationToken> t1 = thirdLevel();
			t = mkNode(next, t, t1);
			
			
			next = tokenizer.peek();//get the next item in the list.
		}
		return t;
	}
	
	private BinaryTree<EquationToken> thirdLevel() throws ParseTreeGenerationException
	{
		BinaryTree<EquationToken> t;
		
		t = fourthLevel();
		EquationToken next = tokenizer.peek();//this next
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
	
	private BinaryTree<EquationToken> fourthLevel() throws ParseTreeGenerationException
	{
		BinaryTree<EquationToken> t;
		
		EquationToken next = tokenizer.peek();
		if(ExpressionType.isTerm(next.getType()))
		{
			next = nextToken();
			t = mkNode(next);
			return t;
		}
		else if(next.getType().equals(ExpressionType.LEFTPAREN))
		{
			next = nextToken();
			t = rootLevel();
			next = nextToken();
			if(!next.getType().equals(ExpressionType.RIGHTPAREN))
			{
				throw new ParseTreeGenerationException(lastToken.getToken());
			}
			return t;
		}
		else if(next.getType().equals(ExpressionType.SUBTRACT))
		{
			next = nextToken();
			t = thirdLevel();
			//uniary minus sign... need to figure out how to code this one up.
			return t;
		}
		else
		{
			throw new ParseTreeGenerationException(lastToken.getToken());
		}
	}
	
	private BinaryTree<EquationToken> mkNode(EquationToken next) {
		return mkNode(next,null,null);
	}

	/**
	 * Creates a subtree where next is the root, t is the left subtree, and t1 is the right subtree.
	 * @param next
	 * @param t
	 * @param t1
	 * @return A new BinayTree reflecting the equation generated so far.
	 */
	private BinaryTree<EquationToken> mkNode(EquationToken next,
			BinaryTree<EquationToken> t, BinaryTree<EquationToken> t1) 
	{

		BinaryTree<EquationToken> retTree = new BinaryTree<EquationToken>();
		TreeNode<EquationToken> newNode = new TreeNode<EquationToken>(next);
		newNode.setLeft(t);
		newNode.setRight(t1);
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
}