package bptree;

import defIntegral.Calculate;

public class ParseTree extends BinaryTree implements Calculate {
	private String expression;

	public ParseTree(String expression) {
		this.expression = expression;
	}

	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * @param expression The mathematical expression to be parsed into a binary tree.
	 */
	
}