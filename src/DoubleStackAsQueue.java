public class DoubleStackAsQueue {
    Stack stack1 = null;
    Stack stack2 = null;
    public DoubleStackAsQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }
    public void enQueue(int value) {
        stack1.push(value);
    }
    public int deQueue() {
        if (!stack2.isEmpty())
            return stack2.pop();
        else{
            if (!stack1.isEmpty()) {
                stack2 = stack1.reverse();
                return stack2.pop();
            }
            else {
                System.out.println("Queue Overflow");
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        DoubleStackAsQueue queue = new DoubleStackAsQueue();
        queue.enQueue(5);
        queue.enQueue(3);
        queue.enQueue(7);
        queue.enQueue(1);
        queue.enQueue(10);
        queue.enQueue(5);
        queue.enQueue(9);
        queue.stack1.print();
        System.out.println(queue.stack1.popMax());
        queue.stack1.print();

    }
}
