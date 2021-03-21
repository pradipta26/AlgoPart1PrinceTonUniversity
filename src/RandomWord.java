import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int count = 1;
        String champion = "";
        String currentString;
        while (!StdIn.isEmpty()) {
            currentString = StdIn.readString();
            if (champion.trim().length() == 0 || StdRandom.bernoulli((double) 1/++count))
                champion = currentString;
        }
        StdOut.println(champion);
    }
}
