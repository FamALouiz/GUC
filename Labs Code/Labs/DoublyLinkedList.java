class Node
{
	public Object data;
	public Node next;
	public Node previous;
	
	public Node(Object o)   
    {
    	data = o;
    } 
	
	public String toString() {
		return data.toString();
	}
}

class DoublyNodeedList
{
	private Node first; // reference to first Node on list
	private Node last; // reference to first Node on list
	
	public DoublyNodeedList()
	{
		first = null;
		last = null;
	}
	public boolean isEmpty()
	{
		return (first == null);
	}
	
	public void insertFirst(Object d)
	{
		Node newNode = new Node(d);
		if( isEmpty() ) { 
			last = newNode;
		} else {
			first.previous = newNode;
		}
		newNode.next = first;
		first = newNode; 
	}
	
	public void insertLast(Object d)
	{
		Node newNode = new Node(d);
		if( isEmpty() ) 
			first = newNode; 
		else
		{
			last.next = newNode;
			newNode.previous = last; 
		}
		last = newNode;
	}
	
	public Object removeFirst() 
	{ 
		Object temp = first.data;
		if(first.next == null)
			last = null; 
		else
			first.next.previous = null; 
		first = first.next;
		return temp;
	}
	
	public Object removeLast()
	{ 
		Object temp = last.data;
		if(first.next == null)
			first = null;
		else
			last.previous.next = null; 
		last = last.previous; 
		return temp;
	}
	public Object getFirst()
	{
		return first.data;
	}
	public Object getLast()
	{
		return last.data;
	}
	public void displayForward()
	{
		System.out.print("[ ");
		Node current = first; 
		while(current != null) 
		{
		System.out.print(current + " ");
		current = current.next; 
		}
		System.out.println("]");
	}	
	
	public void displayBackward()
	{
		System.out.print("[ ");
		Node current = last; 
		while(current != null)
		{
			System.out.print(current + " "); 
			current = current.previous; 
		}
		System.out.println("]");
	}
	
	
	
	
}