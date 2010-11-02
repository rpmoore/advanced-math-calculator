package DataStructures;

/**
 * A BinaryTree implementation.  It is very open.  Can be extended to be 
 * used in an BinarySearchTree very easily.  
 * 
 * @author Ryan
 *
 * @param <T> The Object type that the tree will hold.
 */
public class BinaryTree<T> {

	private TreeNode<T> root;

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
	public TreeNode<T> getRoot()//TODO make this private for release
	{
		return root;
	}
	
	public void setRoot(TreeNode<T> newRoot)
	{
		this.root = newRoot;
	}		
}
