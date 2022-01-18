import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestUE05 {
	
	// Testfolge: 5, 18, 1, 8, 14, 16, 13, 3
	// resultierender Baum nach hintereinandereinf�gen der Elemente:
	//
	//				5
	//		1				18
	//			3		8	
	//						14
	//					13		16
	//
	//Inorder:		1, 3, 5, 8, 13, 14, 16, 18
	//Preorder:		5, 1, 3, 18, 8, 14, 13, 16
	//Postorder:	3, 1, 13, 16, 14, 8, 18, 5
	
	
	MyBinarySearchTree<Integer> tree;

	@Test
	public void testInsert() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		try {
			tree.insert(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		assertEquals(8, tree.size());
	}
	
	@Test
	public void testFind() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		assertEquals(new Integer(5), tree.find(5));
		assertEquals(new Integer(18), tree.find(18));
		assertEquals(new Integer(1), tree.find(1));
		assertEquals(new Integer(8), tree.find(8));
		assertEquals(new Integer(14), tree.find(14));
		assertEquals(new Integer(16), tree.find(16));
		assertEquals(new Integer(13), tree.find(13));
		assertEquals(new Integer(3), tree.find(3));
		assertEquals(null, tree.find(new Integer(2)));
		
		try {
			tree.find(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}		
	}
	
	@Test
	public void testSize() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		tree.remove(1); assertEquals(7, tree.size());
		tree.remove(8); assertEquals(6, tree.size());
		tree.remove(8); assertEquals(6, tree.size());
	}
	
	@Test
	public void testToArrayInOrder() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		Object[] array = tree.toArrayInOrder();
		assertEquals(array[0], new Integer(1));
		assertEquals(array[1], new Integer(3));
		assertEquals(array[2], new Integer(5));
		assertEquals(array[3], new Integer(8));
		assertEquals(array[4], new Integer(13));
		assertEquals(array[5], new Integer(14));
		assertEquals(array[6], new Integer(16));
		assertEquals(array[7], new Integer(18));
		
		System.out.print("Inorder:   ");
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	@Test
	public void testToArrayPreOrder() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		Object[] array = tree.toArrayPreOrder();
		assertEquals(array[0], new Integer(5));
		assertEquals(array[1], new Integer(1));
		assertEquals(array[2], new Integer(3));
		assertEquals(array[3], new Integer(18));
		assertEquals(array[4], new Integer(8));
		assertEquals(array[5], new Integer(14));
		assertEquals(array[6], new Integer(13));
		assertEquals(array[7], new Integer(16));
		
		System.out.print("Preorder:  ");
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	@Test
	public void testToArrayPostOrder() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		Object[] array = tree.toArrayPostOrder();
		assertEquals(array[0], new Integer(3));
		assertEquals(array[1], new Integer(1));
		assertEquals(array[2], new Integer(13));
		assertEquals(array[3], new Integer(16));
		assertEquals(array[4], new Integer(14));
		assertEquals(array[5], new Integer(8));
		assertEquals(array[6], new Integer(18));
		assertEquals(array[7], new Integer(5));
		
		System.out.print("Postorder: ");
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	@Test
	public void testGetParent() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		assertEquals(new Integer(1), tree.getParent(new Integer(3)));
		assertEquals(new Integer(8), tree.getParent(new Integer(14)));
		assertEquals(new Integer(5), tree.getParent(new Integer(18)));
	}
	
	@Test
	public void testIsRoot() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		assertTrue(tree.isRoot(new Integer(5)));
		assertFalse(tree.isRoot(new Integer(18)));
	}
	
	@Test
	public void testIsInternal() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		assertTrue(tree.isInternal(new Integer(5)));
		assertTrue(tree.isInternal(new Integer(18)));
		assertTrue(tree.isInternal(new Integer(1)));
		assertTrue(tree.isInternal(new Integer(8)));
		assertTrue(tree.isInternal(new Integer(14)));
		assertFalse(tree.isInternal(new Integer(16)));
		assertFalse(tree.isInternal(new Integer(13)));
		assertFalse(tree.isInternal(new Integer(3)));
	}
	
	@Test
	public void testIsExternal() {
		tree = new MyBinarySearchTree<Integer>();
		assertEquals(0, tree.size());
		tree.insert(5); assertEquals(1, tree.size());
		tree.insert(18); assertEquals(2, tree.size());
		tree.insert(1); assertEquals(3, tree.size());
		tree.insert(8); assertEquals(4, tree.size());
		tree.insert(14); assertEquals(5, tree.size());
		tree.insert(16); assertEquals(6, tree.size());
		tree.insert(13); assertEquals(7, tree.size());
		tree.insert(3); assertEquals(8, tree.size());
		
		assertFalse(tree.isExternal(new Integer(5)));
		assertFalse(tree.isExternal(new Integer(18)));
		assertFalse(tree.isExternal(new Integer(1)));
		assertFalse(tree.isExternal(new Integer(8)));
		assertFalse(tree.isExternal(new Integer(14)));
		assertTrue(tree.isExternal(new Integer(16)));
		assertTrue(tree.isExternal(new Integer(13)));
		assertTrue(tree.isExternal(new Integer(3)));
	}
}

