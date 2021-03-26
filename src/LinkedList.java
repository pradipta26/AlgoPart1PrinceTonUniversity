import edu.princeton.cs.algs4.StdOut;

public class LinkedList<T> {
    private class Node<T> {
        private T value;
        private Node next = null;
        Node(T value) {
            this.value = value;
        }
    }
    private Node head = null;
    private int size = 0;
    void insert (T value) {
        Node node = new Node(value);
        if (head != null)
            node.next = head;
        head = node;
        size++;
    }
    public T remove(int index) {
        if (isEmpty() || index >= size())
            throw new IllegalArgumentException("Remove from empty list is not permitted");
        int currentIndex = 1;
        Node<T> nodeBeforeIndex = head;
        while (currentIndex < index) {
            nodeBeforeIndex = nodeBeforeIndex.next;
            currentIndex++;
        }
        Node<T> currentNode = nodeBeforeIndex.next;
        nodeBeforeIndex.next = currentNode.next;
        T value = currentNode.value;
        currentNode = null;
        return value;
    }

    public Node<T> getNode(int index) {
        if (isEmpty() || index >= size())
            return null;
        int currentIndex = 0;
        Node<T> indexedNode = head;
        while (currentIndex < index) {
            indexedNode = indexedNode.next;
            currentIndex++;
        }
        return indexedNode;
    }
    /*public Node<T> getRelativeNode(int baseIndex, int targetIndex) {
        if (head == null || baseIndex >= size() || targetIndex >= size() || baseIndex > targetIndex)
            return null;
        else if (baseIndex == targetIndex)
            return getNode(baseIndex);
        int currentIndex = 0;
        Node<T> indexedNode = head;
        while (currentIndex < index) {
            indexedNode = indexedNode.next;
            currentIndex++;
        }
        return indexedNode;
    }*/

    public boolean isEmpty() {
        return (head == null);
    }
    public int size() {
        return size;
    }
    public T getNodeValue (int index) {
        return getNode(index).value;
    }
    public void print() {
        if (isEmpty())
            return;
        Node<T> currentNode = head;
        while (currentNode != null) {
            StdOut.print(currentNode.value + " <- ");
            currentNode = currentNode.next;
        }
        StdOut.println();
    }
    public void swap(int index1, int index2) {
        T node1Value = getNodeValue(index1);
        getNode(index1).value = getNodeValue(index2);
        getNode(index2).value = node1Value;
    }
    public void updateNodeValue(int nodeIndex, T value) {
        getNode(nodeIndex).value = value;
    }
    /*public T[] convertToArray(){
        int size = size();
        T array = new T[size];
    }

     */

}
