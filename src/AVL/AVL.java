package AVL;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ArrayList;

public class AVL <T> {
	
	Node root;
	
	public boolean insertB(int value) {
		//Check if the tree is empty
		if (root == null) {
			root = new Node(value);
			return true;
		}
		else root.insert(value);
	}
	
	private class Node {
		private Node leftChild;
		private Node rightChild;
		private int nodeData;
		
		private Node(int value) {
			nodeData = value;
		}
	}
	
	//Add element in AVL tree, no duplicants is allowed to be added
	public void insert(T element) {
		
		
		
		if (root == null) {
			re
		}
		
			
	}
	
	
	
	//Remove element from AVL tree if exist
	public void remove(T element) {
		
	}
	
	//Find the element and return true if it exist and false if it doesnt exist
	public boolean find(T element) {
		
	}
	
	//Traverses the AVL tree according to the principal "pre order" and adds every nodes value in the arraylist which is returned
	public ArrayList<T> preOrderWalk() {
		
	}
	
	//Traverses the AVL tree according to the principal "in order" and adds every nodes value in the arraylist which is returned
	public ArrayList<T> inOrderWalk() {
		
	}
	
	//Traverses the AVL tree according to the principal "post order" and adds every nodes value in the arraylist which is returned
	public ArrayList<T> postOrderWalk() {
		
	}
	
	//Return the height of the AVL tree, if the AVL tree is empty return -1
	public int getTreeHeight() {
		
	} 
	
	//Find and return the smallest value in the AVL tree
	public T getMin() {
		
	}
	
	//Find and return the largest value in the AVL tree
	public T getMax() {
		
	}
	
}
