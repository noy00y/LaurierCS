package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> value contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2021-09-24
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to values in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    
	public void assist(final SingleQueue<T> queue) {
		
		// Updating:
		SingleNode<T> node = queue.front;
		queue.length--;
		queue.front = queue.front.getNext();
		
		// Empty Q:
		if (queue.front == null) {
			queue.rear = null;
		}
		
		// Update Q:
		if (this.front == null) {
			this.front = node;
		}
		
		else {
			this.rear.setNext(node);
		}
		
		this.rear = node;
		this.rear.setNext(null);
		this.length++;
		
		return;
	}
	
	public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

		// your code here
	    while (left.front != null && right.front != null) { 
	    	this.assist(left);
	    	this.assist(right);
	    }
	    
	    while (left.front != null) {
	    	this.assist(left);
	    }
	    
	    while (right.front != null) {
	    	this.assist(right);
	    }
	    
		return;
    }

    /**
     * Adds value to the rear of the queue. Increments the queue length.
     *
     * @param data The value to added to the rear of the queue.
     */
    public void insert(final T data) {

    	// your code here
    	SingleNode<T> value = new SingleNode<>(data, null);
    	
    	if (this.front == null) {
    		this.front = value;
    	}
    	
    	else {
    		this.rear.setNext(value);
    	}
    	
    	this.rear = value;
    	this.length++;

    	return;
    }

    /**
     * Returns the front value of the queue and removes that value from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The value at the front of the queue.
     */
    public T remove() {

    	// your code here
    	assert this.front != null : "cannot remove from empty queue";
    	SingleNode<T> value = this.front;
    	this.front = this.front.getNext();
    	
    	this.length--;
    	
    	if (this.front == null) {
    		this.rear = null;
    	}

	return value.getData();
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move value or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain values.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
    	boolean l = true;
    	while (this.front != null) {
    		
    		if (l) {
    			left.assist(this);
    		}
    		
    		else {
    			right.assist(this);
    		}
    		
    		l = !l;
    	}
    	
	return;
    }
}
