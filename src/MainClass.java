import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

public class MainClass {
    /*
    public static void main(String[] args) {
        int p, q;
        int totalNodes = StdIn.readInt();
        SocialNetworkConnectivity socialNetwork = new SocialNetworkConnectivity(totalNodes);
        while (!StdIn.isEmpty()) {
            p = StdIn.readInt();
            q = StdIn.readInt();
            if( p <0 || p > totalNodes - 1 ||  q <0 || q > totalNodes - 1 )
                StdOut.println("invalid pair - Out of Range!!!");
            else socialNetwork.union(p,q);
        }

    }


    public static void main(String[] args) {
        int p, q;
        int totalNodes = StdIn.readInt();
        LargestElementFind largestElementFind = new LargestElementFind(totalNodes);
        while (!StdIn.isEmpty()) {
            p = StdIn.readInt();
            q = StdIn.readInt();
            if( p <0 || p > totalNodes - 1 ||  q <0 || q > totalNodes - 1 )
                StdOut.println("invalid pair - Out of Range!!!");
            else largestElementFind.union(p,q);
        }
        largestElementFind.find(1);
        largestElementFind.find(0);


    }

     */
    public static void main(String[] args) {
        List<Integer> values = new LinkedList<>();
        SuccessorWithDelete successorWithDelete;
        int value;
        while (!StdIn.isEmpty()) {
            value = StdIn.readInt();
            values.add(value);

        }
        System.out.println("List size: " + values.size());
        if (values.size() > 0) {
            successorWithDelete = new SuccessorWithDelete(values);
            successorWithDelete.printTree();
            successorWithDelete.getSuccessor(9);
            successorWithDelete.getSuccessor(3);
            successorWithDelete.getSuccessor(7);
            successorWithDelete.getSuccessor(2);
            successorWithDelete.getSuccessor(4);
            successorWithDelete.getSuccessor(1);
            successorWithDelete.getSuccessor(8);
            successorWithDelete.getSuccessor(5);

            successorWithDelete.removeNode(8);
            successorWithDelete.getSuccessor(7);
            successorWithDelete.printTree();
            successorWithDelete.removeNode(9);
            successorWithDelete.printTree();
            successorWithDelete.removeNode(3);
            successorWithDelete.printTree();
            successorWithDelete.removeNode(1);
            successorWithDelete.printTree();
            successorWithDelete.removeNode(4);
            successorWithDelete.printTree();

        }
    }

}
