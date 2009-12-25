package bptree;

public class BinaryTree {

	private TreeNode root;

	public boolean isEmpty()
	{
		if(root != null)
		{	
			return false;
		}
		return true;
	}

	public void clear()
	{
		if(!isEmpty())
		{
			root = null;
		}
	}

	//Used when combining 2 BinaryTrees together
	public TreeNode getRoot()//TODO make this private for release
	{
		return root;
	}
	
	public void setRoot(TreeNode newRoot)
	{
		this.root = newRoot;
	}		
}
