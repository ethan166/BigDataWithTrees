import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperIterative(newEntry);
		return result;
	}

	// ??? IMPLEMENT THIS METHOD
	private T addEntryHelperIterative(T newEntry) {
		BinaryNodeInterface<T> currentNode = getRootNode();
		assert currentNode != null;
		T result = null;
		boolean found = false;
		boolean looking = false;
		while (!found) {
			T currentEntry = currentNode.getData();
			int comparison = newEntry.compareTo(currentEntry);

			if (comparison == 0) { // newEntry matches currentEntry;
									// return and replace currentEntry
				result = currentEntry;
				looking = true;
				if (currentNode.hasRightChild())
				{
					currentNode = currentNode.getRightChild();
					if(currentNode.hasLeftChild() && looking )
					{
						currentNode = currentNode.getLeftChild();
						while(currentNode.hasLeftChild() && found == true)
						{
							if(currentNode.hasLeftChild())
							{
								currentNode = currentNode.getLeftChild();
							} else {
								found = true;
								currentNode.setLeftChild(new BinaryNode<T>(newEntry));
							}
						}
					}
					else
					{
						found = true;
						currentNode.setLeftChild(new BinaryNode<T>(newEntry));
					}
				
				}
				else {
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
				
			} else if (comparison < 0) { // currentEntry is greater than newEntry, in other words newEntry is smaller
				if (currentNode.hasLeftChild())
					currentNode = currentNode.getLeftChild();
				else { // currentEntry is smaller than newEntry
					found = true;
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				} // end if
			} else {
				assert comparison > 0;

				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else {
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
			}
		}

		return result;
		
		// IMPORTANT: METHOD MUST BE ITERATIVE!!
		
	}
	
	
	
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> currentNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		boolean found = false;
		//int count = 0;
		//T currentEntry = currentNode.getData();

		
			while (currentNode != null && found == false) {
				T currentEntry = currentNode.getData();
				if (searchVal.equals(currentEntry)) {
					entryList.add(searchVal);
					found = true;
					
						while(currentNode.hasRightChild())
						{
							currentNode = currentNode.getRightChild();
							currentEntry = currentNode.getData();
							if(searchVal.equals(currentEntry))
							{
								entryList.add(searchVal);
								currentNode = currentNode.getRightChild();
							}
						}
					//
					} else if (searchVal.compareTo(currentEntry) < 0)
						currentNode = currentNode.getLeftChild();
					else
						currentNode = currentNode.getRightChild();
				
				} 
				return entryList;
			}

	//	return entryList;
	

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		// getAllEntriesHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}
	
	public void getAllEntriesHelper( T searchVal, BinaryNodeInterface<T> rootNode,ArrayList<T> entryList)
	{
		T currentEntry = rootNode.getData();
		if (searchVal.equals(currentEntry)) {
			entryList.add(searchVal);
		}
		
		if (rootNode.hasLeftChild())
		{
			getAllEntriesHelper(searchVal, rootNode.getLeftChild(), entryList);
		}
		else if(rootNode.hasRightChild())
		{
			getAllEntriesHelper(searchVal, rootNode.getRightChild(), entryList);
		}
		
	//	return searchVal;
	}
	/*
	 * 
	 * 
	public int getNumberOfNodes() {
		int leftNumber = 0;
		int rightNumber = 0;

		if (left != null)
			leftNumber = left.getNumberOfNodes();

		if (right != null)
			rightNumber = right.getNumberOfNodes();

		return 1 + leftNumber + rightNumber;
	} 
	 */

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();

		// Hint: consider using a stack to mimic recursion!
		// Stack<BinaryNodeInterface<T>> nodeStack = new
		// Stack<BinaryNodeInterface<T>>();
		// nodeStack.push(getRootNode());

		// while(!nodeStack.isEmpty()) {
		// }
		
		return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		// getAllEntriesLessThanHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}



}