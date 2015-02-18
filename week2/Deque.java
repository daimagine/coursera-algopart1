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

		public void setItem(Item item) { this.item = item; }
		public Item getItem() { return this.item; }
		public void setPrev(Node prev) { this.prev = prev; }
		public Node getPrev() { return this.prev; }
		public void setNext(Node next) { this.next = next; }
		public Node getNext() { return this.next; }
	}

	private Node front;
	private Node end;

	/**
	 * construct an empty deque
	 */
	public Deque() {
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
         Item item = front.getItem();
   		Node node = front.getNext();
   		if (node != null) {
            node.setPrev(null);
         }
   		front = node;
         return item;
   	}
   	
      /** 
       * remove and return the item from the end
       */
      public Item removeLast() {
         if (end == null) throw new java.util.NoSuchElementException();
         Item item = end.getItem();
         Node node = end.getPrev();
         if (node != null) {
            node.setNext(null);
         }
         end = node;
         return item;
      }

      /**
       * return an iterator over items in order from front to end
       */
      @Override
   	public Iterator<Item> iterator() {
         return new ItemIterator(front);
      }

      private class ItemIterator implements Iterator<Item>{
         private Node current;

         public ItemIterator(Node current) {
            this.current = current;
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