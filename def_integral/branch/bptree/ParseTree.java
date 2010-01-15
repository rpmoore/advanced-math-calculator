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

	private EquationToken nextToken()
	{
		EquationToken next = tokenizer.nextElement();
		lastToken = next;
		return next;
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
		tokenizer = new EquationTokenizer(this.expression);
		bTree = rootLevel();//Tree should be parsed at this point.
	}

	
	//Base level for parsing the math equation.
	private BinaryTree<EquationToken> rootLevel()
	{
		BinaryTree<EquationToken> t;//temp binary tree that
													   //will be returned up.
		t = secondLevel();
		EquationToken next = nextToken();
		while(next.getType().equals(ExpressionType.ADD) || 
			  next.getType().equals(ExpressionType.SUBTRACT))
		{
			
			BinaryTree<EquationToken> t1 = secondLevel();
			t = mkNode(next,t,t1);
			next = nextToken();
		}
		
		return t;
	}
	

	private BinaryTree<EquationToken> secondLevel()
	{
		BinaryTree<EquationToken> t;
		
		t = thirdLevel();
		EquationToken next = nextToken();
		while(next.getType().equals(ExpressionType.MULTIPLY) ||
			  next.getType().equals(ExpressionType.DIVIDE))
		{
			BinaryTree<EquationToken> t1 = thirdLevel();
			
			
			
			next = nextToken();
		}
		return null;
	}
	
	private BinaryTree<EquationToken> thirdLevel()
	{
	
		return null;
	}
	
	private BinaryTree<EquationToken> fourthLevel()
	{
		
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	public BinaryTree<EquationToken> getTree()
	{
		return bTree;
	}
}