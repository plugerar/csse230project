package csse230project;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<E extends Comparable<? super E>> extends ArrayList<E>   {

	private static final long serialVersionUID = 1L;
	private List<E> h = new ArrayList<>();
	private int size;
	
	/**
	 * Instantiates a new priority queue.
	 */
	public PriorityQueue()
	{
		
		this.size=0;
	}
	
	/**
	 * Adds the specified item to the list
	 * duplicate elements are allowed
	 * @param e the object to be added
	 * @return true, if successfully added
	 */
	@Override
	public boolean add(E e)
	{
		int k=h.size()-1;
		h.add(e);
		//int k=h.size()-1;
		int childLoc=h.size()-1;
		while(k>0)
		{
			k=k/2;
			E parent = this.h.get(k);
			E child = this.h.get(childLoc);
			if(this.h.get(k).compareTo(child)==1)
			{
				h.set(childLoc, parent);
				h.set(k, e);
			}
			childLoc=k;
			
		}
		this.size++;
		return true;
		
	}
	
	/**
	 * Contains.
	 *	returns true if the queue contains the element
	 * @param o the object to be compared
	 * @return true, if successful
	 */
	@Override
	public boolean contains(Object o)
	{
		if(h.contains(o))
		{
			return true;
		}
		return false;

	}
	
	/**
	 * Peek.
	 * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	 * @return the head of the queue
	 */
	public E peek()
	{
		if(this.h.isEmpty())
		{
			return null;
		}
		return this.h.get(0);
		
	}
	
	/**
	 * Poll.
	 * Retrieves and removes the head of this queue, or returns null if this queue is empty.
	 * @return the head of the queue
	 */
	public E poll()
	{
		if(this.h.isEmpty())
		{
			return null;
		}
		E element=this.h.get(0);
		this.remove(element);
		return element;
		
	}
	
	/**
	 * Removes the specified object from the queue if it exists
	 *
	 * @param o the o
	 * @return true, if the element is removed successfully, otherwise false
	 */
	@Override
	public boolean remove(Object o)
	{
		int tempNodeLoc=0;
		E tempNode=null;
		boolean nodeFound=false;
		for(int k=0;k<h.size();k++)
		{
			if(h.get(k).equals(o))
			{
				tempNodeLoc=k;
				tempNode=h.get(h.size()-1);
				h.remove(h.size()-1);
				nodeFound=true;
				this.size--;
				break;
			}
		}
		if(nodeFound && !h.isEmpty())
		{
		this.h.set(tempNodeLoc, tempNode);
		this.percolateDown(tempNodeLoc);
		}
		return nodeFound;
		
	}
	
	/**
	 * Percolate down
	 * This function is used during insertion and removal
	 * It rebalances to maintain a minheap
	 *
	 * @param position the position of the node that is to be percolated
	 */
	public void percolateDown(int position)
	{
		int k = position;
		int parentLoc=position;
		while(k*2<h.size()-1)
		{
			if(k==0)
			{
				k++;
			}
			else
			{
				k=k*2+1;
			}
			E child=h.get(k);
			E child2=h.get(k);
			if(k+1<h.size())
			{
			child2=h.get(k+1);
			}
			E parent=h.get(parentLoc);
			if(child2.compareTo(child)<0 && child2.compareTo(parent)==-1)
			{
				h.set(parentLoc, child2);
				h.set((k+1),parent);
			}
			else if(child2.compareTo(child)>=0 && child.compareTo(parent)==-1)
			{
				h.set(parentLoc, child);
				h.set(k,parent);
			}
			parentLoc=k;
		}
	}
	
	/**
	 * Size of the heap
	 *
	 * @return the number of elements in the collection
	 */
	@Override
	public int size()
	{
		return this.size;
		
	}
	
	/**
	 * To array.
	 * Returns an array containing all of the elements in this queue; 
	 * @return array of objects contained in the queue
	 */
	@Override
	public Object[] toArray()
	{
		return this.h.toArray();
		
	}
	
	/**
	 * To array.
	 * Returns an array containing all of the elements in this queue; 
	 * the runtime type of the returned array is that of the specified array.
	 * @param <E> the element type
	 * @param a the array elements are to be inserted
	 * @return priority queue as an array
	 */
	public <E> E[] toArray(E[] a)
	{
		return this.h.toArray(a);
	}
	
	/**
	 * Clear.
	 * Removes all of the elements from this priority queue.
	 */
	@Override
	public void clear()
	{
		this.h=new ArrayList<>();
	}
	
	/**
	 * Offer.
	 * Inserts the specified element into this priority queue.
	 * @param e the element to be inserted
	 * @return true, if successfully added
	 */
	public boolean offer(E e)
	{
		return this.add(e);
		
	}
	
}
