public interface BinarySearchTree<T extends Comparable<T>> {
	
	// Inserts elem into the tree. Duplicates and null is not allowed
	public void insert(T elem) throws IllegalArgumentException;

	// Returns the element with the key or null if not found
	public T find(T key) throws IllegalArgumentException;

	// Removes the element with the key. Returns true if eleme has been found
	public boolean remove(T key) throws IllegalArgumentException; // Returns the number of elements in the tree

	public int size();

	// Returns an array representation of the tree in post-order-traversal
	public Object[] toArrayPostOrder();

	// Returns an array representation of the tree in in-order-traversal
	public Object[] toArrayInOrder();

	// Returns an array representation of the tree in pre-order-traversal
	public Object[] toArrayPreOrder();

	// Retruns the parent-node of the key. Null if not found
	public T getParent(T key) throws IllegalArgumentException; // Returns true if the key is the root, false otherwise

	public boolean isRoot(T key) throws IllegalArgumentException; // True if the key is an internal node, false
																	// otherwise

	public boolean isInternal(T key) throws IllegalArgumentException;

	// True if the key is an external node, false otherwise
	public boolean isExternal(T key) throws IllegalArgumentException;
}
