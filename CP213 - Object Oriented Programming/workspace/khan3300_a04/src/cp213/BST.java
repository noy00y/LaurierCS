package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-11-01
 */
public class BST<T extends Comparable<T>> {
    protected int comparisons = 0; // Count of comparisons performed by tree.

    // Attributes.
    protected TreeNode<T> root = null; // Root node of the tree.
    protected int size = 0; // Number of elements in the tree.

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in items and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in items and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

    	// Base Case:
    	if (source == null && target == null) {
    		return true;
    	}
    	
    	// Case 1: height and value r same
    	else if (source != null && target != null && source.getItem().compareTo(target.getItem()) == 0) {
    		this.comparisons += 1;
    		return equalsAux(source.getLeft(), target.getLeft()) && equalsAux(source.getRight(), target.getRight());	
    	}
    	
    	return false;
    }

    /**
     * Auxiliary method for {@code insert}. Inserts data into this BST.
     *
     * @param node the current node (TreeNode)
     * @param data data to be inserted into the node
     * @return the inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {
	
    	
    	// Base Case --> if end of tree or duplicates:
//    	System.out.print("Current data: " + data + " --> ");
    	
    	if (node == null) {
//    		System.out.println("End of Tree/Base Case");
    		node = new TreeNode<>(data);
    		data.incrementCount();
        	this.size += 1;
    	}
    	
    	// Insert Left node > data:
    	else if (node.getItem().compareTo(data) > 0) {
//    		System.out.println("Going left from " + node.getItem());
    		this.comparisons += 1;
    		node.setLeft(insertAux(node.getLeft(), data));
    	}
    	
    	// Insert Right node < data:
    	else if (node.getItem().compareTo(data) < 0) {
//    		System.out.println("Going right from " + node.getItem());
    		this.comparisons += 1;
    		node.setRight(insertAux(node.getRight(), data));
    	}
    	
    	node.updateHeight();
    	return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

    	// Base:
    	if (node == null) {
    		return true;
    	}
    	
    	// Case 1: left tree min >= parent (need to recurse first)
    	if (minNode != null && node.getItem().compareTo(minNode.getItem()) <= 0) {
    		this.comparisons += 1;
    		return false;
    	}
    	
    	// Case 2: right tree max <= parent (need to recurse first)
    	if (maxNode != null && node.getItem().compareTo(maxNode.getItem()) >= 0) {
    		this.comparisons += 1;
    		return false;
    	}
    	
    	// Case 3: 
    	if (node.getHeight() != Math.max(node.getLeft().getHeight(), node.getRight().getHeight())) {
    		this.comparisons += 1;
    		return false;
    	}
    	
    	// Recurse Case:
    	else {
    		return isValidAux(node.getLeft(), minNode, node) // 1. go to left tree, 2. find min val, 3. parent is max
    				&& isValidAux(node.getRight(), node, maxNode); // 1. go to right tree, 2. find max val, 3. parent is min
    	}
    }

    /**
     * Returns the height of a given TreeNode.
     *
     * @param node The TreeNode to determine the height of.
     * @return The item of the height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
		int height = 0;
	
		if (node != null) {
		    height = node.getHeight();
		}
		return height;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this BST contains key, false otherwise.
     */
    public boolean contains(final CountedItem<T> key) {

    	// your code here
    	TreeNode<T> node = this.root;
    	boolean found = false;
    	
    	// Search:
    	while (node != null && !found) {
    		if (node.getItem().compareTo(key) > 0) {
    			this.comparisons += 1;
    			node = node.getLeft();
    		}
    		
    		else if (node.getItem().compareTo(key) < 0) {
    			this.comparisons += 1;
    			node = node.getRight();
    		}
    		
    		else if (node.getItem().compareTo(key) == 0) {
    			this.comparisons += 1;
    			found = true;
    		}
    	}

	return found;
    }

    /**
     * Determines whether two BSTs are identical.
     *
     * @param target The BST to compare this BST against.
     * @return true if this BST and that BST contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
    	
    	this.comparisons += 1;
    	
		if (this.size != target.size) {
		    return false;
		}
		return this.equalsAux(this.root, target.root);
    }

    /**
     * Get number of comparisons executed by the {@code retrieve} method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this BST.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
	int height = 0;

	if (this.root != null) {
	    height = this.root.getHeight();
	}
	return height;
    }

    /**
     * Returns the number of nodes in the BST.
     *
     * @return number of node in this BST.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns an array of copies of CountedItem objects in a linked data
     * structure. The array contents are in data order from smallest to largest.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedItem<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this BST.
     *
     * @param data Data to store.
     */
    public void insert(final CountedItem<T> data) {

		// Creating a Root
//    	System.out.println("------------------");
    	if (this.root == null) {
    		TreeNode<T> node = new TreeNode<>(data); // new node
    		data.incrementCount(); // accounting for data type
    		this.root = node; 
    		this.size += 1;
    	}
    	
    	// Else --> Finding the parent node
    	else {
    		// Recurse and Insert
    		insertAux(this.root, data);
    	}
    	
    	this.root.updateHeight();
   		return;
    }

    /**
     * Determines if this BST is empty.
     *
     * @return true if this BST is empty, false otherwise.
     */
    public boolean isEmpty() {
	return this.root == null;
    }

    /**
     * Determines if this BST is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this BST is a valid BST, false otherwise.
     */
    public boolean isValid() {
    	return this.isValidAux(this.root, null, null);
    }

    /**
     * Returns an array of copies of CountedItem objects int a linked data
     * structure. The array contents are in level order starting from the root
     * (this) node. Helps determine the structure of the tree.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedItem<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have item
     * count of 0). Returning a complete CountedItem gives access to the
     * item and count.
     *
     * @param key The key to look for.
     * @return data The complete CountedItem that matches key, null otherwise.
     */
    public CountedItem<T> retrieve(final CountedItem<T> key) {

    	// Declarations
    	TreeNode<T> node = this.root;
    	CountedItem<T> value = null;
    	boolean found = false;

    	// If Key is good:
    	if (key.getCount() == 0) {
	    	while (node != null && !found) {
	    		if (node.getItem().compareTo(key) > 0) {
	    			node = node.getLeft();
	    			this.comparisons += 1;
	    		}
	    		
	    		else if (node.getItem().compareTo(key) < 0) {
	    			node = node.getRight();
	    			this.comparisons += 1;
	    		}
	    		
	    		else if (node.getItem().compareTo(key) == 0) {
	    			value = node.getItem();
	    			this.comparisons += 1;
	    			found = true;
	    		}
	    	}    		
    	}
    	
	return value;
    }
}
