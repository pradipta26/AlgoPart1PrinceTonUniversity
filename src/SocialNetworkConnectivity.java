public class SocialNetworkConnectivity {
    private int totalMember;
    private int weight[];
    private int id[];
    private int connections[];
    private int connectionCount = 0;
    public SocialNetworkConnectivity (int totalMember){
        this.totalMember = totalMember;
        id = new int[totalMember];
        weight = new int[totalMember];
        connections = new int[totalMember];
        for (int i =0; i<totalMember; i++) {
            id[i] = i;
            weight[i] = 1;
            connections[i] = 0;
        }
    }
    private int findRoot(int p) {
        while(p != id[p])
            p = id[id[p]];
        return p;
    }
    boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
    void union (int p, int q) {

        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if(rootP != rootQ) {
            if(weight[rootP] >= weight[rootQ]) {
                id[rootQ] = rootP;
                weight[rootP] += weight[rootQ];
            } else {
                id[rootP] = rootQ;
                weight[rootQ] += weight[rootP];
            }
            if (connections[p]<2) {
                connections[p]++;
                connectionCount++;
            }
            if (connections[q]<2) {
                connections[q]++;
                connectionCount++;
            }
            if (connectionCount == 2 * (totalMember - 1)) {
                System.out.println("All members are connected. Total unique connections are :" + connectionCount);
                System.exit(0);
            }
        }
    }
}
