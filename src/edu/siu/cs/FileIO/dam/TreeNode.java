package edu.siu.cs.FileIO.dam;

import java.util.ArrayList;

/**
 * A simple node to be used in a tree structure. This class supports the method
 * DAMFunctions.removeSelfDependencies()
 * @author Crackers
 *
 * @param <T>
 */
public class TreeNode {
	public int id;
	public ArrayList<TreeNode> children;
	
	public TreeNode(int id) {
		this.id = id;
		this.children = new ArrayList<TreeNode>(0);
	}
	
	@Override
	public String toString() {
		return "["+id+"]";
	}
	
	/**
	 * Returns the next child this tree points too
	 */
	public TreeNode getNext() {
		if(children.isEmpty())
			return null;
		else
			return children.remove(0);
	}
}
