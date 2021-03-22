import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("Argument length must be 1!!");
        int inputFound = 0;
        int numberOfChoice = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (inputFound < numberOfChoice)
                queue.enqueue(input);
            else {
                int toss = StdRandom.uniform(0, 2);
                if (toss == 1) {
                    queue.dequeue();
                    queue.enqueue(input);
                }
            }
            inputFound++;
        }
        if (numberOfChoice > inputFound)
            throw new IllegalArgumentException("Number of actual inputs are less than number of expected inputs!!!");
        for (String input : queue) {
            StdOut.println(input);
        }

    }
}
