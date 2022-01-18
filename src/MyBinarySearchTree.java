public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> { 
	
	/**
	 * Innere Klasse welche einen Knoten mit seinem Vaterknoten speichert.
	 */
	private class TwoNodes {
		BinaryTreeNode<T> cur, parent; // Elternknoten immer zum aktuellen Knoten dazuspeichern
	}
	
	// Wurzelknoten
	private BinaryTreeNode<T> root;
	
	// Anzahl der im Baum gespeicherten Elemente
	private int size;

	public MyBinarySearchTree() {
		// Initialisiert den binären Suchbaum 
		root = null;
		size = 0;
	}
	
	///////////////////////////////////////////////////////
	// Methoden des Interface
	///////////////////////////////////////////////////////

	@Override
	public void insert(T elem) throws IllegalArgumentException {
		if (elem == null) {
			throw new IllegalArgumentException(
					"null arguments are not allowed!");
		}

		BinaryTreeNode<T> n = new BinaryTreeNode<T>(elem); // neuen Knoten erzeugen

		if (root == null) { // Baum ist leer
			root = n;
		} else { // Baum ist nicht leer -> Einfügeposition suchen
			TwoNodes pos = locate(elem, true);

			// Einfügeposition gefunden -> neuer Knoten ist linkes bzw. rechtes Kind (dafür wird innere Klasse mit Hilfsknoten benötigt!)
			if (elem.compareTo(pos.parent.getData()) < 0) {
				pos.parent.setLeft(n); 
			} else { // >= 0
				pos.parent.setRight(n);
			}
		}
		size++;
	}

	@Override
	public T find(T key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(
					"null arguments are not allowed!");
		}

		TwoNodes pos = locate(key, false);
		if (pos.cur != null) {
			return pos.cur.getData();
		}
		return null;
	}

	@Override
	public boolean remove(T key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(
					"null arguments are not allowed!");
		}

		// Leerer Baum -> nichts zu entfernen
		if (root == null) {
			return false;
		}

		// Zu entfernenden Knoten lokalisieren
		TwoNodes pos = locate(key, false);
		if (pos.cur == null) { // Knoten mit Schlüssel key nicht gefunden  -> return false 
			return false;
		}

		BinaryTreeNode<T> pnf = pos.cur; // zu entfernendes Element
		BinaryTreeNode<T> nf = pos.cur.getRight(); // Zeiger auf rechten Baum des gefundenen Knotens

		// Knoten mit Schlüssel key hat keinen rechten Kindknoten (Fall 1 und 2 falls kein rechtes Kind) -> Elternknoten muss auf anderen Kindknoten zeigen 
		if (nf == null) {
			if (pos.parent == pos.cur) {
				root = pos.cur.getLeft(); // Wurzelknoten zeigt auf linken Baum
			} else {
				if (pos.cur.getData().compareTo(pos.parent.getData()) < 0) {
					pos.parent.setLeft(pos.cur.getLeft()); // Elternknoten ist grösser -> linkes Kind des Elternknotens mit linkem Kind des zu entfernenden Knotens verbinden  
				} else {
					pos.parent.setRight(pos.cur.getLeft()); // Elternknoten ist kleiner/gleich gross
				}
			}
		}

		// Fall 3, und 2 falls kein linkes Kind
		else { 
			// 1) Linkesten Kindknoten im rechten Baum suchen (nächst grösster Schlüssel = symmetrischer Nachfolger) 
			while (nf.getLeft() != null) {
				pnf = nf;
				nf = nf.getLeft();
			}

			// 2) Nachfolger entfernen  
			if (pnf == pos.cur) { // Elternknoten des Nachfolgers ist gleich dem zu entfernenden Element 
				pos.cur.setRight(nf.getRight());
			} else {
				pnf.setLeft(nf.getRight()); // linkes Kind des Elternknoten des Nachfolgers zeigt auf rechtes Kind des Nachfolgerknotens
			}

			// 3) Element mit Nachfolgerknoten ersetzen 
			nf.setLeft(pos.cur.getLeft());
			nf.setRight(pos.cur.getRight());

			// 4) Eltern-Kind-Relation wiederherstellen
			if (pos.cur == root) { // Wurzelknoten muss entfernt werden 
				root = nf;
			} else {
				if (pos.cur.getData().compareTo(pos.parent.getData()) < 0) { // Elternknoten des Wurzelknotens muss auf eingef�gten Knoten zeigen 
					pos.parent.setLeft(nf);
				} else {
					pos.parent.setRight(nf);
				}
			}
		}
		size--;
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArrayPostOrder() {
		Object[] ret = new Object[size];
		toArrayPostOrder (ret, 0, root);
		return ret;
	}

	@Override
	public Object[] toArrayInOrder() {
		Object[] ret = new Object[size];
		toArrayInOrder(ret, 0, root);
		return ret;
	}

	@Override
	public Object[] toArrayPreOrder() {
		Object[] ret = new Object[size];
		toArrayPreOrder(ret, 0, root);
		return ret;
	}

	@Override
	public T getParent(T key) throws IllegalArgumentException {
		TwoNodes t = locate(key, false);
		return t.parent.getData();
	}

	@Override
	public boolean isRoot(T key) throws IllegalArgumentException {
		if(root.getData().equals(key)) return true;
		return false;
	}

	@Override
	public boolean isInternal(T key) throws IllegalArgumentException {
		return !isExternal(key);
	}

	@Override
	public boolean isExternal(T key) throws IllegalArgumentException {
		TwoNodes t = locate(key, false);
		if(t.cur.getLeft() == null && t.cur.getRight() == null) return true;
		return false;
	}
	
	
	///////////////////////////////////////////////////////
	// Private Methods
	///////////////////////////////////////////////////////
	/**
	 * Findet eine insert- oder find-Position für ein Element mit dem Schlüssel key
	 * im binären Suchbaum.
	 * 
	 * @param insertPos
	 *            true wenn Element eingef�gt werden soll, false wenn ein Element
	 *            gesucht wird.
	 */
	private TwoNodes locate(T key, boolean insertPos) {
		TwoNodes ret = new TwoNodes();
		ret.cur = ret.parent = root; // aktueller Knoten

		while ((ret.cur != null) && (insertPos || (key.compareTo(ret.cur.getData()) != 0))) { // Schleife abbrechen sobald
																							// Element gefunden wurde
			ret.parent = ret.cur;

			// Select left or right child depending on the value of the current node.
			if (key.compareTo(ret.cur.getData()) < 0) {
				ret.cur = ret.cur.getLeft();
			} else {
				ret.cur = ret.cur.getRight();
			}
		}
		return ret;
	}
	
	private int toArrayPreOrder (Object[] ret, int offset, BinaryTreeNode<T> n) {
		if(n != null) {
			int o = offset;
			ret[o++] = n.getData(); // data
			o = toArrayPreOrder (ret, o, n.getLeft()); //linker Teilbaum
			o = toArrayPreOrder (ret, o, n.getRight()); //rechter Teilbaum
			return o;
		} else {
			return offset;
		}
	}
	
	private int toArrayPostOrder (Object[] ret, int offset, BinaryTreeNode<T> n) {
		if (n != null) {
			int o = toArrayPostOrder (ret, offset, n.getLeft()); // linker Teilbaum
			o = toArrayPostOrder (ret, o, n.getRight()); // rechter Teilbaum
			ret[o++] = n.getData(); // node
			return o;
		} else {
			return offset;
		}
	}
	
	private int toArrayInOrder (Object[] ret, int offset, BinaryTreeNode<T> n) {
		if (n != null) {
			int o = toArrayInOrder (ret, offset, n.getLeft()); // linker Teilbaum
			ret[o++] = n.getData(); // node
			o = toArrayInOrder (ret, o, n.getRight()); // rechter Teilbaum
			return o;
		} else {
			return offset;
		}
	}
	
}
