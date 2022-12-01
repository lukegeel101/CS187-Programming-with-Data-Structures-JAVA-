package structures;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
//imports iterator queue and linkedlist

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  private int size;
  protected BSTNode<T> root;
  
	public boolean isEmpty() {
		return root == null; //checks if root is empty
	}

	public int size() {
		return size; //gets size
	}

	public boolean contains(T t) {
		if (get(t).equals(t)) {
			return true; //if t is contained, true
		}
		return false; //if t isn't contained, false
	}

	public boolean remove(T t) {
		if (t == null) { //check if t is null
			throw new NullPointerException(); //if t is null, throw exception
		}
		Iterator<T> toTraverse = inorderIterator(); //Traverses in order
		while (toTraverse.hasNext()) {
			T var = toTraverse.next();
			if (var.equals(t)) {
				size = size - 1; //takes 1 away from size
				return true; //returns true
			}
		}
		return false; //returns false
	}

	public T get(T t) throws NullPointerException {
		if (t == null) { //check if t is null
			throw new NullPointerException(); //if t is null, throw exception
		}
		Iterator<T> toTraverse = inorderIterator();
		while (toTraverse.hasNext()) {
			T var = toTraverse.next();
			if (var.equals(t)) {
				return var; //returns t
			}
		}
		return null; //returns null
	}

	private BSTNode<T> addToSubtree(T t, BSTNode<T> node) {
		if (node == null) { //checks if node is null
			return new BSTNode<T>(t, null, null);
		}
		if (t.compareTo(node.getData()) <= 0) { //checks if t is greater than the node
			node.setLeft(addToSubtree(t, node.getLeft())); //adds t to the left
    } 
    else {
			node.setRight(addToSubtree(t, node.getRight())); //adds t to the right
		}
		return node; //returns the node
  }
  
  public void add(T t) {
		root = addToSubtree(t, root); //adds t to the end
		size = size + 1; //increases size by 1
	}

	@Override
	public T getMinimum() { //gets mimimum by iterating through 
		Iterator<T> toGet = preorderIterator();
		T val1 = null; //sets val1 to null
		if (toGet.hasNext())
			val1 = toGet.next();
		return val1; //returns minimum value
	}

	@Override
	public T getMaximum() { //gets maximum
		Iterator<T> toGet = preorderIterator();
		T val2 = null;
		while (toGet.hasNext()) {
			val2 = toGet.next();
		}
		return val2; //returns maximum value
	}

	@Override
	public int height() {
		return heightHelper(root); //returns height
	}

	public int heightHelper(BSTNode<T> node) {
		if (node == null) {
			return -1;
		}
		if (node.getLeft() == null & node.getRight() == null) {
			return 0;
		}
		if (node.getRight() == null) {
			return 1 + heightHelper(node.getLeft());
		}
		if (node.getLeft() == null) {
			return 1 + heightHelper(node.getRight());
		}
		int value = node.getLeft().getData().compareTo(node.getRight().getData());
		if (value > 0) {
			return 1 + heightHelper(node.getLeft());
		} else {
			return 1 + heightHelper(node.getRight());
		}
	}

	@Override
	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	public void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	public void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			inorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}

	}

	// Need to figure this out
	@Override
	public boolean equals(BSTInterface<T> other) {
		return equaHelper(root,other.getRoot());
	}
	
	public boolean equaHelper(BSTNode<T> thisNode, BSTNode<T> otherNode){
		
		if (thisNode == otherNode) {
	        return true;
	    }
	    if (thisNode == null || otherNode == null) {
	        return false;
	    }
	    return thisNode.getData().equals(otherNode.getData()) &&
	           equaHelper(thisNode.getLeft(), otherNode.getLeft()) &&
	           equaHelper(thisNode.getRight(), otherNode.getRight());
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {

		if (equals(other)) {
			return true; //returns true if others are equal
    }
    Iterator<T> thisIt = inorderIterator();
		Iterator<T> difIt = other.inorderIterator();
		LinkedList<T> difValue = new LinkedList<T>(); //creates new LL
		LinkedList<T> thisValue = new LinkedList<T>(); //creates new LL

		while (thisIt.hasNext()) {
			thisValue.add(thisIt.next());
		}
		while (difIt.hasNext()) {
			difValue.add(difIt.next());
		}
		for (int k = 0; k < thisValue.size(); k++) {
			T valToFind = thisValue.get(k);
			if (!difValue.contains(valToFind)) {
				return false; //returns false
			}
		}
		return true; //returns true
	}

	@Override
	public boolean isBalanced() {
		if (size == 0) { //checks if size is 0
			return true; //returns true
    }
    double upperBoundary = Math.pow(2, height() + 1);
		double lowerBoundary = Math.pow(2, height());

		if (lowerBoundary <= size && size < upperBoundary) {
			return true; //returns true
		}
		return false; //returns false
	}
	
	@Override
	public void balance() {
		LinkedList<T> nodes = getAsLL();
		BinarySearchTree<T> newTree = new BinarySearchTree<T>() ;
		insertTree(0, (size() - 1), nodes, newTree) ;
		this.root = newTree.getRoot() ;
	}
	
	public void insertTree(int lower, int upper, LinkedList<T> nodes, BinarySearchTree<T> tree) {
		if (lower == upper) //checks if lower equals upper
			tree.add(nodes.get(lower)); //adds lower node
		else if (lower  == upper - 1) { //checks to see if lower + 1 = upper
			tree.add(nodes.get(lower)); //adds lower node
			tree.add(nodes.get(upper)); //adds upper node
    } 
    else { //if lower doesn't equal upper or upper - 1
			int middle = (lower + upper) / 2; //middle = average of lower and upper
			tree.add(nodes.get(middle));
			insertTree(lower, middle - 1, nodes,tree);
			insertTree(middle+1,upper,nodes,tree);
		}
	}

	public LinkedList<T> getAsLL() {
		LinkedList<T> valueT = new LinkedList<T>();
		Iterator<T> iterator = inorderIterator();
		while (iterator.hasNext()) {
			valueT.add(iterator.next());
		}
		return valueT;
	}

	@Override
	public BSTNode<T> getRoot() {
		return root;
	}
	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
				count = count + 1;
			}
			if (cursor.getRight() != null) {
				dot += cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
				count = count + 1;
			}
		}
		dot += "};";
		return dot;
	}
}