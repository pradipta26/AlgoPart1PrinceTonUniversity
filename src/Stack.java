public class Stack {
    private static class Node {
        int value;
        Node nextNode = null;
        Node(int value) {
            this.value = value;
        }
    }
    Node head = null;
    int size = 0;
    public void push(int value) {
        size++;
        Node node = new Node(value);
        if (head != null)
            node.nextNode = head;
        head = node;
    }
    public int pop(){
        if(head != null) {
            int value = head.value;
            head = head.nextNode;
            size--;
            return value;
        }
        else System.out.println("Stack Overflow!!");
        return -1;
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public static Stack reverse( Stack stack) {
        Stack reverseStack = new Stack();
        while(stack.head != null) {
            reverseStack.push(stack.pop());
        }
        return reverseStack;
    }
    public Stack reverse() {
        Stack reverseStack = new Stack();
        while(this.head != null) {
            reverseStack.push(this.pop());
        }
        return reverseStack;
    }
    public void print() {
        Node node = head;
        while(node != null) {
            System.out.print(" -> " + node.value);
            node = node.nextNode;
        }
        System.out.println("");
    }
    public int popMax() {
        Node node = head.nextNode;
        int maxValue= head.value;
        while (null != node) {
            if (node.value > maxValue)
                maxValue = node.value;
            node = node.nextNode;
        }
        Stack tempStack = new Stack();
        node = head;
        while(node != null) {
            if (maxValue != node.value) {
                tempStack.push(pop());
                node = node.nextNode;
            }
            else {
                pop();
                Node tempNode = tempStack.head;
                while(null != tempNode) {
                    push(tempNode.value);
                    tempNode = tempNode.nextNode;
                }
                break;
            }
        }
        return maxValue;
    }
}
