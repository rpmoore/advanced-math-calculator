package DataStructures;
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
