public class LargestElementFind {
    private int totalElement;
    private int id[];
    public LargestElementFind (int totalElement){
        this.totalElement = totalElement;
        id = new int[totalElement];
        for (int i =0; i<totalElement; i++) {
            id[i] = i;
        }
    }
    private int findRoot(int p) {
        while(p != id[p])
            p = id[id[p]];
        return p;
    }
    public int find(int p) {
        int maxValue = findRoot(p);
        System.out.println("max value in tree: " + maxValue);
        return maxValue;
    }
    public void union (int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        System.out.print(String.format("p = %d -> q = %d : rootP = %d -> rootQ = %d", p, q, rootP, rootQ));
        if (rootP != rootQ) {
            if (rootP >= rootQ)
                id[rootQ] = rootP;
             else
                id[rootP] = rootQ;
            System.out.println(String.format(" : New rootP = %d -> rootQ = %d", id[rootP], id[rootQ]));
        }
    }
}
