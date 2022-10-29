import java.lang.Math;
import java.util.Arrays;
/**
 * 
 * @author Gabriel O'Donnell
 * This is an array based implementation of the binary search tree data structure
 * It has been made into a generic type to allow for the most versatility.
 *
 * @param <T> any object that extends the comparable interface.
 */
public class BinarySearchTree  <T extends Comparable<T>>{
	private int size = 1;
	private int level = 0;
	private Object[] array = new Object[] {null};
	
	//Constructor
	public BinarySearchTree(int inputLevel){
		//Size and level will be updated by the extendSize function
		for(int i =0; i < inputLevel; i++) {
			extendSize();
		}
		for(int i =0; i < array.length; i++) {
			array[i]=null;
		}
	}
	
	//Copy Constructor 
	public BinarySearchTree(final BinarySearchTree<T> tree) {
		int treeLevel = tree.level();
		for(int i =0; i < treeLevel; i++) {
			extendSize();
		}
		
		for(int i =0; i < tree.size; i++) {
			if (tree.searchIndex(i) == null) {
				
			}
			else {
			T value = tree.searchIndex(i);
			this.insert(value);
			}
		}
		
	}
	
	/**
	 * inserts a data value
	 * @param data: the value to be inserted
	 */
	public void insert(T data) {

		
		//keeps track of what index we are on
		int currentIndex = 0;
		while(true) {
			//makes room for a new index
			if(currentIndex >= size) {
				extendSize();
			}
			
			//Case in which we found a position to add the item.
			if(array[currentIndex]== null) {
				array[currentIndex] = data;
				break;
			}
			
			//Check if its equal
			else if(((T) array[currentIndex]).compareTo(data) == 0) {
				System.out.println("No repeating values, Insertion cancelled");
			}
			//Check if the array at current index is larger
			else if(((T) array[currentIndex]).compareTo(data) > 0) {
				currentIndex = (2 * currentIndex + 1);
			}
			//Check if the array at current index is smaller
			else if(((T) array[currentIndex]).compareTo(data) < 0) {
				currentIndex = (2 * currentIndex + 2);
			}
		}
		
	}
	
	/**
	 * This is the front facing delete function that acts as a 
	 * driver function for my actual deletion algorithm.
	 * @param data: the value to be deleted
	 * @return true/false if the operation is successful
	 */
	public boolean delete(T data) {
		int currentIndex = 0;
		if (search(data)== -1) {
			return false;
		}
			
		return deleteSetup(currentIndex, data);
		
	}
	
	/**
	 * This algorithm gets finds the index that needs to be deleted to then
	 * pass to the actual deletion operation.
	 * @param currentIndex: the root of the tree
	 * @param data: the value to be deleted
	 * @return true/false if the operation is successful
	 */
	private boolean deleteSetup(int currentIndex, T data) {
		//Check if the array at current index is larger
		if(((T) array[currentIndex]).compareTo(data) > 0) {
			currentIndex = (2 * currentIndex + 1);
			return deleteSetup(currentIndex, data);
		}
		//Check if the array at current index is smaller
		else if(((T) array[currentIndex]).compareTo(data) < 0) {
			currentIndex = (2 * currentIndex + 2);
			return deleteSetup(currentIndex, data);
		}
		else {
			return deleteNode(currentIndex);                                      
		}
	}
	
	
	/**
	 * This is the actual deletion operation that a takes into consideration the amount
	 * of children that the node as and will work to maintain the binary search tree properties.
	 * @param currentIndex: index to be deleted
	 * @return true/false if the operation is successful
	 */
	private boolean deleteNode(int currentIndex) {
		int leftchild = (2 * currentIndex + 1);
		int rightchild = (2 * currentIndex + 2);
		//case 1: no children
		if (leftchild >= size ||(array[leftchild] == null && array[rightchild] == null)) {
			array[currentIndex] = null;
			return true;
		}
		//case 2: one child
		else if (array[leftchild] == null) {	
			array[currentIndex] = array[rightchild];
			array[rightchild] = null;
			return true;
		}
		else if (array[rightchild] == null) {	
			array[currentIndex] = array[leftchild];
			array[leftchild] = null;
			return true;
		}//case 3: two children
		else {
			T pred = predecessor(currentIndex);
			int predIndex = search(pred);
			deleteNode(predIndex);
			array[currentIndex] = pred;
			return true;
			
		}
	}
	
	/**
	 * Finds the data at a given index
	 * @param index: the position of the data we want
	 * @return the value of the data at that index
	 */
	public T searchIndex(int index) {
		if(index >= size) {
			System.out.print("Out of Bounds");
			return null;
		}
		else if(index < 0) {
			System.out.print("Out of Bounds");
			return null;
		}
		else {
			return (T) array[index];
		}
	}
	
	/**
	 * Finds a particular index of a data value in the tree.
	 * @param data: the search value
	 * @return the index of the value
	 */
 	public int search(T data) {
		int currentIndex = 0;
		while(true) {
			//check if we have gone past array size
			if (currentIndex >= size || array[currentIndex] == null) {
				System.out.println("Not Found");
				return -1;
				}
			
			//This will check if we found it
			//Base case
			if (  ((T) array[currentIndex]).equals(data)) {
				return currentIndex;
			}
			
			//Check if the array at current index is larger
			else if(((T) array[currentIndex]).compareTo(data) > 0) {
				currentIndex = (2 * currentIndex + 1);
			}
			//Check if the array at current index is smaller
			else if(((T) array[currentIndex]).compareTo(data) < 0) {
				currentIndex = (2 * currentIndex + 2);
			}
			
			
			
		}
	}
 	
 	/**
 	 * This is the front facing driver function of the recursive function.
 	 * The result will be the list printed with in order traversal 
 	 * In order: (Left, Node, Right).
 	 */
	public void inOrder() {
		
		System.out.println();
		int currentIndex = 0;
		//This is the driver function for this recursive function.
		inOrderRecursive(currentIndex);
		System.out.println();
	
	}
	
	/**
	 * Recursively goes through the tree to print in with an in order traversal. 
	 * In order: (Left, Node, Right).
	 * @param currentIndex: the root of the tree
	 */
	private void inOrderRecursive(int currentIndex) {
		if (currentIndex >= size ) {
			return;
		}
		
		if(array[currentIndex] != null) {
			inOrderRecursive(2 * currentIndex +1); //left
			System.out.print(" "+ array[currentIndex] + " "); //node
			inOrderRecursive(2 * currentIndex +2); //right
		}
	}
	
	/**
 	 * This is the front facing driver function of the recursive function.
 	 * The result will be the list printed with reverse in order traversal 
 	 * In order: (Right, Node, Left).
 	 */
	public void reveseInOrder() {
		
		System.out.println();
		int currentIndex = 0;
		//This is the driver function for this recursive function.
		reveseInOrderRecursive(currentIndex);
		System.out.println();
	
	}
	/**
	 * Recursively goes through the tree to print in with an reverse in order traversal. 
	 * In order: (Right, Node, Left).
	 * @param currentIndex: the root of the tree
	 */
	private void reveseInOrderRecursive(int currentIndex) {
		if (currentIndex >= size ) {
			return;
		}
		
		if(array[currentIndex] != null) {
			reveseInOrderRecursive(2 * currentIndex +2); //right
			System.out.print(" "+ array[currentIndex] + " "); //node
			reveseInOrderRecursive(2 * currentIndex +1); //left
		}
	}
 
	/**
 	 * This is the front facing driver function of the recursive function.
 	 * The result will be the list printed with preorder traversal 
 	 * In order: (Node, Left, Right).
 	 */
    public void preOrder() {
		
		System.out.println();
		int currentIndex = 0;
		//This is the driver function for this recursive function.
		preOrderRecursive(currentIndex);
		System.out.println();
	
	}
    /**
	 * Recursively goes through the tree to print in with an preorder traversal. 
	 * In order: (Node, Left, Right).
	 * @param currentIndex: the root of the tree
	 */
	private void preOrderRecursive(int currentIndex) {
		if (currentIndex >= size ) {
			return;
		}
		
		if(array[currentIndex] != null) {
			System.out.print(" "+ array[currentIndex] + " "); //node
			preOrderRecursive(2 * currentIndex +1); //left
			preOrderRecursive(2 * currentIndex +2); //right
		}
	}
	
	/**
 	 * This is the front facing driver function of the recursive function.
 	 * The result will be the list printed with post order traversal 
 	 * In order: (Left, Right, Node).
 	 */
    public void postOrder() {
		
		System.out.println();
		int currentIndex = 0;
		//This is the driver function for this recursive function.
		postOrderRecursive(currentIndex);
		System.out.println();
	}
    
    /**
	 * Recursively goes through the tree to print in with an post order traversal. 
	 * In order: (Left, Right, Node).
	 * @param currentIndex: the root of the tree
	 */
	private void postOrderRecursive(int currentIndex) {
		if (currentIndex >= size ) {
			return;
		}
		
		if(array[currentIndex] != null) {
			postOrderRecursive(2 * currentIndex +1); //left
			postOrderRecursive(2 * currentIndex +2); //right 
			System.out.print(" "+ array[currentIndex] + " "); //node
		}
	}
	
	/**
	 * returns the amount of nodes with data with them
	 * @return: amount of data filled indexs
	 */
	public int countNodes() {
		int count = 0;
		for(int i = 0; i < size; i++) {
			if(array[i] != null) {
				count++;
			}
		}
		return count;
	}
 
	/**
	 * Returns the largest data value in the tree.
	 * @return: the maximum of the tree
	 */
	public T maximum() {
		int currentIndex = 0;
		int nextIndex = (2*currentIndex+2);

		while(array[nextIndex] != null) {
			currentIndex = nextIndex;
			nextIndex = (2*currentIndex+2);
			if(nextIndex >= size) {
				break;
			}
		}
		return (T) array[currentIndex];
	}
	
	/**
	 * Returns the smallest data value in the tree.
	 * @return: the minimum of the tree
	 */
	public T minimum() {
		int currentIndex = 0;
		int nextIndex = (2*currentIndex+1);

		while(array[nextIndex] != null) {
			currentIndex = nextIndex;
			nextIndex = (2*currentIndex+1);
			if(nextIndex >= size) {
				break;
			}
		}
		return (T) array[currentIndex];
	}

	/**
	 * This returns the size of the array that is being used to run the BST.
	 * @return array size
	 */
	public int size() {
		return size;
	}
	/**
	 * Returns the amount of levels in the tree
	 * @return amount of levels.
	 */
	public int level() {
		return level;
	}
	/**
	 * given the index of a node, gives the predecessor to that node.
	 * this would be a node that could replace the given node while keeping
	 * the BST properties.
	 * @param index: the position of the node that will have its predecessor found.
	 * @return the data value of the indexes predecessor+
	 */
	public T predecessor(int index) {
		//maximum of left child
		int currentIndex = (2*index+1);
		int nextIndex = (2*currentIndex+2);
		
		if( nextIndex >= size) {
			return (T) array[currentIndex];
		}

		while(array[nextIndex] != null) {
			currentIndex = nextIndex;
			nextIndex = (2*currentIndex+2);
			if(nextIndex >= size) {
				break;
			}
		}
		return (T) array[currentIndex];
	}
	
	/**
	 * given the index of a node, gives the successor to that node.
	 * this would be a node that could replace the given node while keeping
	 * the BST properties.
	 * @param index: the position of the node that will have its successor found.
	 * @return the data value of the indexes successor
	 */
	public T successor(int index) {
		//minimum of right child
		int currentIndex = (2*index+2);
		int nextIndex = (2*currentIndex+1);
		
		if( nextIndex >= size) {
			return (T) array[currentIndex];
		}

		while(array[nextIndex] != null) {
			currentIndex = nextIndex;
			nextIndex = (2*currentIndex+1);
			if(nextIndex >= size) {
				break;
			}
		}
		return (T) array[currentIndex];
	}

	/**
	 * returns true of the list is empty
	 * @return True if empty
	 * 		   False if not empty
	 */
	public boolean isEmpty() {
		int count = countNodes();
		if (count == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Given that arrays are static in size we need a way to make our BST larger as the user
	 * adds values. This will create an array that is 1 level larger and copies the values over.
	 */
	public void extendSize() {
		//increment level
		level++;
		int newSize = array.length + (int) Math.pow(2, level);
		size = newSize;
		//create new array with next level
		Object[] newArray = Arrays.copyOf(array, newSize);
		array = newArray;
		
	}
}

