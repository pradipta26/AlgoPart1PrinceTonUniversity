import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>  {
    private class Node {
        private final Item value;
        private Node next = null;
        private Node previous = null;
        Node(Item value) {
            this.value = value;
        }
    }
    private Node head = null;
    private Node tail = null;
    private int size = 0;
    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (head == null);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("enqueue method call with 'null' is not permitted..");
        Node node = new Node(item);
        if (head == null)
            head = node;
        else {
            node.next = head;
            head.previous = node;
        }
        head = node;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (head == null)
            throw new NoSuchElementException("dequeue method call on empty Queue is not permitted");
        Item item;
        Node node = head;

        if (tail == head) {
            item = head.value;
            head = null;
            tail = null;
        } else {
            int n = StdRandom.uniform(0, size);
            if (n == 0) {
                item = head.value;
                head = head.next;
            } else if (n == 1) {
                item = head.next.value;
                if (head.next != tail)
                    head.next = head.next.next;
                else {
                    head.next = null;
                    tail = head;
                }
            } else {
                for (int i = 1; i < n && node != null; i++)
                    node = node.next;
                item = node.next.value;
                if (tail == node.next) {
                    tail = node;
                    node.next = null;
                } else node.next = node.next.next;
             }
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (head == null)
            throw new NoSuchElementException("sample method call on empty Queue is not permitted");
        return getNode(StdRandom.uniform(0, size)).value;
    }

    private Node getNode(int n) {
        Node node = head;
        for (int i = 0; i < n; i++)
            node = node.next;
        return node;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentSize = size;
            boolean initialize = true;
            int[] used = new int[currentSize];
            @Override
            public boolean hasNext() {
                return currentSize != 0;
            }
            @Override
            public Item next() {
                if (size == 0)
                    throw new NoSuchElementException("No more Item is available!!!");
                int n = -1;
                while (currentSize > 0) {
                    n = StdRandom.uniform(0, size);
                    if (used[n] == 0) {
                        used[n] = -1;
                        break;
                    }
                }
                currentSize--;
                return getNode(n).value;
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Remove is not supported");
            }
        };
    }

    private void print() {
        Node node = head;
        while (node != null) {
            System.out.print(" -> " + node.value);
            node = node.next;
        }
        System.out.printf("\nSize = %d, isEmpty = %b%n", size, isEmpty());
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        StdOut.printf("size = %d, isEmpty = %b%n", queue.size, queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        for (Integer value : queue) {
            System.out.print(" -> " + value);
        }
        StdOut.printf("\nsize = %d, isEmpty = %b sample = %d%n", queue.size, queue.isEmpty(), queue.sample());
        queue.dequeue();
        for (Integer value : queue) {
            System.out.print(" -> " + value);
        }
        StdOut.printf("\nsize = %d, isEmpty = %b%n", queue.size, queue.isEmpty());
    }
}
