import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int n;

 /**
  * construct an empty randomized queue
  */
 	public RandomizedQueue() {
 		items = (Item[]) new Object[2];
 	}

 	/**
 	 * is the queue empty?
 	 */
 	public boolean isEmpty() {
 		return n == 0;
 	}

 	/**
 	 * return the number of items on the queue
 	 */
 	public int size() {
 		return n;
 	}

 	private void resize(int size) {
 		assert size >= n;
    Item[] temp = (Item[]) new Object[size];
    for (int i = 0; i < n; i++) {
        temp[i] = items[i];
    }
    items = temp;
 	}

 	/**
 	 * add the item
 	 */
 	public void enqueue(Item item) {
 		if (item == null) throw new NullPointerException();
 		if (items.length == n) {
 			resize(n*2);
 		}
 		items[n++] = item;
 	}

 	/**
 	 * remove and return a random item
 	 */
 	public Item dequeue() {
 		if (isEmpty()) {
          throw new NoSuchElementException();
      }
      int i = StdRandom.uniform(n);
      Item item = items[i];
      items[i] = items[--n]; // replace with rightmost value
      items[n] = null;
      if (n > 0 && n == items.length / 4) {
          resize(items.length / 2);
      }
      return item;
 	}

  /**
   * return (but do not remove) a random item
   */
 	public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    return items[StdRandom.uniform(n)];
  }

  /**
   * return an independent iterator over items in random order
   */
 	public Iterator<Item> iterator() {
    return new ItemIterator(items, n);
  }

  private class ItemIterator implements Iterator<Item> {
    private int current;
    private Item[] items;

    public ItemIterator(Item[] items, int size) {
      Item[] temp = (Item[]) new Object[size];
      for (int i = 0; i < size; i++) {
          temp[i] = (Item) items[i];
      }
      StdRandom.shuffle(temp);
      this.current = size;
      this.items = temp;
    }

    public boolean hasNext() {
        return current > 0;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Item next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.items[--current];
    }
  }

  public static void main(String[] args) {
  }
}