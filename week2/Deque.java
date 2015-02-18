import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private class Node {
		private Item item;
		private Node prev;
		private Node next;

		public Node(Item item) {
			this.item = item;
			this.prev = null;
			this.next = null;
		}

		public void setItem(Item i) { this.item = i; }
		public Item getItem() { return this.item; }
		public void setPrev(Node p) { this.prev = p; }
		public Node getPrev() { return this.prev; }
		public void setNext(Node n) { this.next = n; }
		public Node getNext() { return this.next; }
	}

	private Node front;
	private Node end;
   private int N;

	/**
	 * construct an empty deque
	 */
	public Deque() {
		this.front = null;
		this.end = null;
      this.N = 0;
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
   		return N;
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
         N++;
   	}

   	/**
   	 * add the item to the end
   	 */
   	public void addLast(Item item) {
   		if (item == null) throw new NullPointerException();
   		Node node = new Node(item);
   		if (end == null) {
   			front = node;
            end = node;
   		} else {
   			node.setPrev(end);
   			end.setNext(node);
   			end = node;
   		}
         N++;
   	}

   	/**
   	 * remove and return the item from the front
   	 */
   	public Item removeFirst() {
   		if (front == null) throw new java.util.NoSuchElementException();
         Item item = front.getItem();
         front = front.getNext();
   		if (front != null) {
            front.setPrev(null);
         } else {
            end = null;
         }
         N--;
         return item;
   	}
   	
      /** 
       * remove and return the item from the end
       */
      public Item removeLast() {
         if (end == null) throw new java.util.NoSuchElementException();
         Item item = end.getItem();
         end = end.getPrev();
         if (end != null) {
            end.setNext(null);
         } else {
            front = null;
         }
         N--;
         return item;
      }

      /**
       * return an iterator over items in order from front to end
       */
      @Override
   	public Iterator<Item> iterator() {
         return new ItemIterator();
      }

      private class ItemIterator implements Iterator<Item> {
         private Node current;

         public ItemIterator() {
            this.current = front;
         }

         @Override
         public boolean hasNext() {
            return current != null;
         }

         @Override
         public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.getItem();
            current = current.getNext();
            return item;
         }

         @Override
         public void remove() {
            throw new UnsupportedOperationException();
         }
      }

}