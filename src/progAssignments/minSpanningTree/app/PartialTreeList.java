package progAssignments.minSpanningTree.app; //revert

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import progAssignments.minSpanningTree.structures.*;

/**
 * Stores partial trees in a circular linked list
 * 
 */
public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */

		PartialTreeList result = new PartialTreeList();

		for(int i = 0; i < graph.vertices.length; i++){
			Vertex curr = graph.vertices[i];
			PartialTree partialTree = new PartialTree(curr);
			for(Vertex.Neighbor ptr = curr.neighbors; ptr != null; ptr = ptr.next){
				Arc edge = new Arc(curr, ptr.vertex, ptr.weight);
				MinHeap<Arc> priorityQ = partialTree.getArcs();
				priorityQ.insert(edge);
			}
			result.append(partialTree);
		}

		return result;
	}
	
	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * for that graph
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */

		ArrayList<Arc> result = new ArrayList<>();

		while(ptlist.size() != 1){
			PartialTree currPT = ptlist.remove(); //Step 3
			MinHeap<Arc> currPQ = currPT.getArcs(); //Step 3
			Arc currArc = currPQ.deleteMin(); //step 4
			while(currArc.getv2().getRoot().equals(currPT.getRoot())){
				currArc = currPQ.deleteMin();
			}
			result.add(currArc);

			Vertex rootPTY = currArc.getv2().getRoot();
			PartialTree ptY = null;
			for(PartialTree pt2: ptlist){
				if(pt2.getRoot().equals(rootPTY)){
						ptY = pt2;
				}
			}
			currPT.merge(ptY);
			ptlist.removeTreeContaining(rootPTY);
			ptlist.append(currPT);
			ptlist.size--;
		}

		return result;
	}
	
    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    			
    	if (rear == null) {
    		throw new NoSuchElementException("list is empty");
    	}
    	PartialTree ret = rear.next.tree;
    	if (rear.next == rear) {
    		rear = null;
    	} else {
    		rear.next = rear.next.next;
    	}
    	size--;
    	return ret;
    		
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */


		Node ptr = rear;
		do{

			PartialTree partialTreeToDel = ptr.next.tree;
			MinHeap<Arc> priorityQ = new MinHeap<>(ptr.next.tree.getArcs());

			while(!priorityQ.isEmpty()){
				Arc curr = priorityQ.deleteMin();
				if(curr.getv1().equals(vertex)){
					ptr.next = ptr.next.next;

					if(rear.tree.getRoot().equals(vertex)){
						rear = rear.next;
					}

					return partialTreeToDel;
				}
			}

			ptr = ptr.next;
		} while (ptr != rear);

    		throw new NoSuchElementException();
     }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


