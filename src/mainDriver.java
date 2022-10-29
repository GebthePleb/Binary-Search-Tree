import java.util.Arrays;

/**
 * 
 * @author Gabriel O'Donnell
 * This is a driver function for my Binary search tree class. This is simply so I can show
 * how it works and use all of its different functions.
 *
 */
public class mainDriver {

	public static void main(String[] args) {
		//create values to add to the BST
		Point a = new Point(5,5);
		Point b = new Point(4,4);
		Point c = new Point(0,1);
		Point d = new Point(5,0);
		Point e = new Point(6,6);
		Point f = new Point(6,0);
		Point g = new Point(7,0);
		
		BinarySearchTree<Point> pointCollection = new BinarySearchTree<Point>(2);
		
		//add them to the BST
		pointCollection.insert(a);
		pointCollection.insert(b);
		pointCollection.insert(c);
		pointCollection.insert(d);
		pointCollection.insert(e);
		pointCollection.insert(f);
		pointCollection.insert(g);
		
		//Show the different traversals
		System.out.println("In Order: ");
		pointCollection.inOrder();
		System.out.println("Pre-Order: ");
		pointCollection.preOrder();
		System.out.println("Post-Order: ");
		pointCollection.postOrder();
		
		//show the copy constructors
		System.out.println();
		BinarySearchTree<Point> myPointCollection = new BinarySearchTree<Point>(pointCollection);
		System.out.println("Copy In Order: ");
		myPointCollection.inOrder();
		
		Point copyE = new Point(e);
		
		System.out.println();
		System.out.println();
		//Show the search function
		System.out.println("Searching for (6,6), index: " + pointCollection.search(copyE));
		System.out.println();
		System.out.println();
		//deletion
		pointCollection.delete(e);
		System.out.println("After Deleting (6,6): ");
		pointCollection.inOrder();
		System.out.println();
		System.out.println();
		//the other helpful functions.
		System.out.println("Root Predecessor: " + pointCollection.predecessor(0)+ " Root Successor: " + pointCollection.successor(0));
		System.out.println("Maximum: " + pointCollection.maximum());
		System.out.println("Minimum: " + pointCollection.minimum());
		System.out.println("Nodes: " + pointCollection.countNodes());
		System.out.println("Size: " + pointCollection.size());
		pointCollection.inOrder();
		pointCollection.reveseInOrder();
		
		}

}
