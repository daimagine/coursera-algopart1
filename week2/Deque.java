import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	class Node {
		private Item item;
		private Node prev;
		private Node next;

		public Node(Item item) {
			this.item = item;
			this.prev = null;
			this.next = null;
		}

		public void setItem(Item item) { this.item = item; }
		public Item getItem() { return this.item; }
		public void setPrev(Item item) { this.prev = prev; }
		public Item getPrev() { return this.prev; }
		public void setNext(Item item) { this.next = item; }
		public Item getNext() { return this.next; }
	}

	private Node front;
	private Node end;

	/**
	 * construct an empty deque
	 */
	public Deque(Item item) {
		this.front = null;
		this.end = null;
	}

	/**
	 * is the deque empty?
	 */
   	public boolean isEmpty() {
   		return front == null;
   	}

   	/**
   	 * return the number of items on the deque
   	 */
   	public int size() {
   		if (front == null) return 0;
   		int size = 0;
   		Node item = front;
   		while (front.getNext() != null) {
   			size++;
   		}
   		return size;
   	}

   	/**
   	 * add the item to the front
   	 */
   	public void addFirst(Item item) {
   		if (item == null) throw new NullPointerException();
   		Node node = new Node(item);
   		if (front == null) {
   			front = node;
   			end = node;
   		} else {
   			node.setPrev(null);
   			node.setNext(front);
   			front.setPrev(node);
   			front = node;	
   		}
   	}

   	/**
   	 * add the item to the end
   	 */
   	public void addLast(Item item) {
   		if (item == null) throw new NullPointerException();
   		Node node = new Node(item);
   		if (end == null) {
   			this.addFirst(item);
   		} else {
   			node.setPrev(end);
   			end.setNext(node);
   			end = node;
   		}
   	}

   	/**
   	 * remove and return the item from the front
   	 */
   	public Item removeFirst() {
   		if (front == null) throw new java.util.NoSuchElementException();
   		Node node = front.getNext();
   		node.setPrev(null);
   		front = node;
   	}
   	public Item removeLast()                 // remove and return the item from the end
   	public Iterator<Item> iterator()         // return an iterator over items in order from front to end

   	public static void main(String[] args)   // unit testing
}