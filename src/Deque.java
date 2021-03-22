import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
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
    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (head == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("addFirst method call with 'null' is not permitted..");
        Node node = new Node(item);
        if (head == null)
            tail = node;
        else {
            node.next = head;
            head.previous = node;
        }
        head = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("addLast method call with 'null' is not permitted..");
        Node node = new Node(item);
        if (head == null)
            head = node;
        else {
            node.previous = tail;
            tail.next = node;
        }
        tail = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null)
            throw new NoSuchElementException("removeFirst method call on empty Deque is not permitted");
        Item value = head.value;
        head = head.next;
        if (head != null)
            head.previous = null;
        else
            tail = null;
        size--;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (tail == null)
            throw new NoSuchElementException("removeLast method call on empty Deque is not permitted");
        Item value = tail.value;
        tail = tail.previous;
        if (tail != null)
            tail.next = null;
        else
            head = null;
        size--;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node currentNode = head;
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }
            @Override
            public Item next() {
                if (currentNode == null)
                    throw new NoSuchElementException("No more Item is available!!!");
                Node tempNode = currentNode;
                currentNode = currentNode.next;
                return tempNode.value;
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Remove is not supported");
            }
         };
    }
    private void printFromFront() {
        Node node = head;
        while (node != null) {
            System.out.print(" -> " + node.value);
            node = node.next;
        }
        System.out.printf("\nSize = %d, isEmpty = %b%n", size(), isEmpty());
    }
    private void printFromEnd() {
        Node node = tail;
        while (node != null) {
            System.out.print(" -> " + node.value);
            node = node.previous;
        }
        System.out.printf("\nSize = %d, isEmpty = %b%n", size(), isEmpty());
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.printf("isEmpty = %b, Size = %d%n", deque.isEmpty(), deque.size());

        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(1);
        deque.addLast(4);
        StdOut.printf("isEmpty = %b, Size = %d%n", deque.isEmpty(), deque.size());
        for (Integer value : deque) {
            System.out.print(" -> " + value);
        }
        System.out.println();

        deque.removeFirst();
        deque.removeFirst();
        for (Integer integer : deque) {
            System.out.print(" -> " + integer);
        }
        System.out.println();
        deque.removeLast();
        deque.removeLast();
        StdOut.println(deque.isEmpty());
        deque.addLast(10);
    }
}
