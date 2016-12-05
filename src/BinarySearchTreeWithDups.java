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
				//found = true;
					result = currentEntry;
				//currentNode.setData(newEntry);
					if (currentNode.hasRightChild())
							currentNode = currentNode.getRightChild();
					else {
						found = true;
						currentNode.setRightChild(new BinaryNode<T>(newEntry));
}
			} else if (comparison < 0) { // currentEntry is greater than newEntry, in other words newEntry is smaller
				if (currentNode.hasLeftChild())
					currentNode = currentNode.getLeftChild();
				else { 
					found = true;
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				} // end if
			} else {// currentEntry is smaller than newEntry
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
		boolean found = false;
		ArrayList<T> entryList = new ArrayList<T>();
		BinaryNodeInterface<T> currentNode = getRootNode();
		while (!found && (currentNode != null)) {
			T currentEntry = currentNode.getData();

			if (searchVal.equals(currentEntry)) {
				
				entryList.add(searchVal);
				
				
				if(currentNode.hasRightChild())
				{
					currentNode = currentNode.getRightChild();
					if(searchVal.equals(currentNode.getData()))
					{
						entryList.add(searchVal);
						found = true;
					}
				}
				
			} else if (searchVal.compareTo(currentEntry) < 0)
				currentNode = currentNode.getLeftChild();
			else
				currentNode = currentNode.getRightChild();
		}

		//return entryList;

		return entryList;
	}
	public ArrayList<T> getAllEntriesRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		
		getAllEntriesHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}
	
	private void getAllEntriesHelper(T searchVal, BinaryNodeInterface<T> node, ArrayList<T> entryList) {
		if(node != null) {
			int compareNum = node.getData().compareTo(searchVal);
		
			if(compareNum == 0) {
				getAllEntriesHelper(searchVal, node.getRightChild(), entryList);
				entryList.add(node.getData());
			} else {
				getAllEntriesHelper(searchVal, node.getRightChild(), entryList);
				getAllEntriesHelper(searchVal, node.getLeftChild(), entryList);
			}
		}	
	}
	

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();
		boolean found = false;
		// Hint: consider using a stack to mimic recursion!
		// Stack<BinaryNodeInterface<T>> nodeStack = new
		// Stack<BinaryNodeInterface<T>>();
		// nodeStack.push(getRootNode());

		// while(!nodeStack.isEmpty()) {
		// }
		Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();
		BinaryNodeInterface<T> currentNode = getRootNode();
		while (!found && (currentNode != null)) {
			T currentEntry = currentNode.getData();
			
			
			
			if (searchVal.compareTo(currentEntry) == 0) {
				
				found = true;
				//currentNode = currentNode.getRightChild();
				if(currentNode.hasLeftChild())
				{
					nodeStack.push(currentNode.getLeftChild());
				}
				
			} else if (searchVal.compareTo(currentEntry) < 0) // searchVal is smaller than currentEntry and go the left side
			{
				
				currentNode = currentNode.getLeftChild();
				
					if(currentNode != null && currentNode.hasLeftChild() && searchVal.compareTo(currentNode.getData()) < 0)
					{
						nodeStack.push(currentNode.getLeftChild());
					}
				
			}	
			else // searchVal is greater than currentEntry and go the right side
			{
				
				currentNode = currentNode.getRightChild();
				if(currentNode != null)
				{
					entryList.add(currentNode.getData());
					if(currentNode.hasLeftChild() && searchVal.compareTo(currentNode.getData()) < 0)
					{
						nodeStack.push(currentNode.getLeftChild());
					}
				}
				
				
			}
				
			
		}
		
		
		
		while(!nodeStack.isEmpty()) {
			
			BinaryNodeInterface<T> myNode = nodeStack.pop();
			T myEntry = myNode.getData();
			entryList.add(myEntry);
			if(myNode.hasRightChild())
			{
				entryList.add(myNode.getRightChild().getData());
			}
			if(myNode.hasLeftChild())
			{
				entryList.add(myNode.getLeftChild().getData());
			}
			
		 }
		
		return entryList;
		
	}

	public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal) {
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		getAllLessThanHelper(searchVal, rootNode, entryList);
		
		return entryList;		
	}
	public void getAllLessThanHelper(T searchVal, BinaryNodeInterface<T> node, ArrayList<T> entryList) {
		if(node != null) {
			if(node.getData().compareTo(searchVal) < 0) {
		
			entryList.add(node.getData());
			}
			if(node.getLeftChild() != null)
			{
				getAllLessThanHelper(searchVal, node.getLeftChild(), entryList);
			}
				
			if(node.getRightChild() != null)
			{
				getAllLessThanHelper(searchVal, node.getRightChild(), entryList);
			}
		}
	}
}