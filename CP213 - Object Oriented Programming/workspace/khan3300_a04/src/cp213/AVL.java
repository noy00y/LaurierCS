package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-11-01
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if
     * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
     * is balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	// your code here
    	int balance_ = 0;
    	
    	if (node!=null && node.getLeft()!=null && node.getRight() != null) {
    		balance_ = node.getLeft().getHeight() - node.getRight().getHeight();
    	}
    	
    	return balance_;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here
    	TreeNode<T> node_one = node.getRight();
    	TreeNode<T> node_two = node_one.getLeft();
    	node_one.setLeft(node);
    	node.setRight(node_two);
    	node.updateHeight();
    	node_one.updateHeight();
    	return node_one;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here
    	TreeNode<T> node_one = node.getLeft();
    	TreeNode<T> node_two = node_one.getRight();
    	node_one.setRight(node);
    	node.setLeft(node_two);
    	node.updateHeight();
    	node_one.updateHeight();
    	return node_one;
    }

    /**
     * Auxiliary method for {@code insert}. Inserts data into this AVL.
     *
     * @param node the current node (TreeNode)
     * @param data Data to be inserted into the node
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

    	// your code here
	    if (node == null) {
	    	node = new TreeNode<T>(data);
	    	
	    	node.getItem().incrementCount();
	    	this.size += 1;
	    }
	
	    else if (data.compareTo(node.getItem()) < 0) {
	    	node.setLeft(insertAux(node.getLeft(), data));
	    }
	    
	    else if (data.compareTo(node.getItem()) > 0) {
	    	node.setRight(insertAux(node.getRight(), data));
	    }
	    
	    else if(data.compareTo(node.getItem()) == 0) {
	    	node.getItem().incrementCount();
	    }
	    
	    if (balance(node) > 1) {
	    	if (balance(node.getLeft()) > 0) {
	    		node.setLeft(rotateLeft(node.getLeft()));
	    		node = rotateRight(node);
	    	}
	    	
	    	else {
	    		node = rotateRight(node);
	    	}
	    }
	    
		return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree. An AVL must meet the BST validation conditions, and
     * additionally be balanced in all its subtrees - i.e. the difference in height
     * between any two children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
    	if (node==null) { 
    		return true;
    	}
    	if ( (( minNode!=null && (node.getItem().compareTo(minNode.getItem())<=0)) || 
    			(maxNode!=null && (node.getItem().compareTo(maxNode.getItem())>=0))) || 
    			(Math.abs(node.getLeft().getHeight() - node.getRight().getHeight())>1) ) {
    		return false;
    	}
	return isValidAux(node.getRight(),node,maxNode) && isValidAux(node.getLeft(),minNode,node);

    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
