import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private static final int virtualTop = -1;
    private  static final int virtualBottom = -2;
    private final int[][] grid;
    private final int[][] weight;
    private final boolean[][] gridStatus;
    private final boolean[][] fullSite;
    private final int n;
    private boolean isPercolate = false;
    private int openSiteCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Row must be > 0");
        }
        this.n = n;
        // virtualBottom = n * n;
        grid = new int[n][n];
        weight = new int[n][n];
        gridStatus = new boolean[n][n];
        fullSite = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    grid[i][j] = virtualTop;
                    weight[i][j] = n * n;
                }
                else if (i == n-1) {
                    grid[i][j] = virtualBottom;
                    weight[i][j] = n + n;
                }
                else {
                    grid[i][j] = i * n + j;
                    weight[i][j] = 1;
                }
                gridStatus[i][j] = false;
                fullSite[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // By convention input row and col will start from [1][1]
        --row;
        --col;
       if (row < 0 || row > n - 1 || col < 0 || col > n - 1)
            throw new IllegalArgumentException("Site number to be opened is not valid");
       if (!isOpen(row + 1, col + 1)) { // To update row column in array value based on [1][1]
           openSiteCount++;
           gridStatus[row][col] = true;
           if( n == 1) {
               fullSite[row][col] = true;
               isPercolate = true;
               return;
           }
           connectNeighbor(row, col);
           if (row == 0)
               fullSite[row][col] = true;
           else
               isFull(row + 1, col + 1);
       }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        --row;
        --col;
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1)
            throw new IllegalArgumentException("Site number is not valid");
        return gridStatus[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        --row;
        --col;
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1)
            throw new IllegalArgumentException("Site number is not valid");
        if (fullSite[row][col] )
            return  true;
        else {
            int tempRow = row;
            int tempCol = col;
            while (!(grid[tempRow][tempCol] == tempRow * n + tempCol || !gridStatus[tempRow][tempCol])) {
                if (fullSite[tempRow][tempCol] || grid[tempRow][tempCol] == -1)
                    return true;
                if (grid[tempRow][tempCol] == -2)
                    return false;
                int temp = grid[tempRow][tempCol] / n;
                tempCol = grid[tempRow][tempCol] % n;
                tempRow = temp;
            }
            return false;
         }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return isPercolate;
    }

    private void printGrid() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gridStatus[i][j])
                    StdOut.print(grid[i][j] + "\t");
                else StdOut.print("*\t");
            }
            StdOut.print("\n");
        }
    }

    private boolean union(int row1, int col1, int row2, int col2) {
        int rootP = getRoot(row1, col1);
        int rootQ = getRoot(row2, col2);
        int rpRow = rootP / n;
        int rpCol = rootP % n;
        int rqRow = rootQ / n;
        int rqCol = rootQ % n;

        if (rootP != rootQ) {
            if (grid[rpRow][rpCol] + grid[rqRow][rqCol] == virtualTop + virtualBottom)
                isPercolate = true;
            if (weight[rpRow][rpCol] >= weight[rqRow][rqCol]) {
                grid[rqRow][rqCol] = rootP;
                weight[rpRow][rpCol] += weight[rqRow][rqCol];
                if (rpRow == 0) {
                    fullSite[rqRow][rqCol] = true;
                }
            }
            else {
                grid[rpRow][rpCol] = rootQ;
                weight[rqRow][rqCol] += weight[rpRow][rpCol];
                if (rqRow == 0) {
                    fullSite[rpRow][rpCol] = true;
                }
            }
            return true;
        }
        else return false;
    }

    private int getRoot(int row, int col) {
        int startingRow = row;
        int startingCol = col;
        while (!(grid[row][col] == row * n + col || grid[row][col] == -1 || grid[row][col] == -2)) {
            int tempRow = grid[row][col] / n;
            col = grid[row][col] % n;
            row = tempRow;
        }
        if (grid[row][col] == -1) {
            int tempRow = startingRow;
            int tempCol = startingCol;
            //System.out.println(tempRow + "Temp Col" + tempCol);
            while (!(fullSite[tempRow][tempCol] || grid[row][col] == -1)) {
                fullSite[tempRow][tempCol] = true;
                tempRow = grid[tempRow][tempCol] / n;
                tempCol = grid[tempRow][tempCol] % n;
            }
        }
    return row * n + col;
    }
    private void connectNeighbor( int row, int col) {
        //System.out.println("ConnectNeighbor : row = " + row + " col = " + col);
        if (row != 0 && gridStatus[row -1][col]) {
            if (union(row, col, row - 1, col))
                connectNeighbor(row - 1, col);
        }
        if (col != 0 && gridStatus[row][col - 1]) {
            if (union(row, col, row, col - 1))
                connectNeighbor(row, col - 1);
        }
        if (col != n - 1 && gridStatus[row][col + 1]) {
            if (union(row, col, row, col + 1))
                connectNeighbor(row, col + 1);
        }
        if (row != n - 1 && gridStatus[row + 1][col]) {
            if (union(row, col, row + 1, col))
                connectNeighbor(row + 1, col);
        }
    }

    private void printFullSiteStatus() {
        StdOut.println("Full Site Status: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fullSite[i][j])
                    StdOut.print(fullSite[i][j] + "\t");
                else StdOut.print("*\t");
            }
            StdOut.println("");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        int totalRow = StdIn.readInt();
        if (totalRow <= 0) {
            throw new IllegalArgumentException("Row must be > 0");
        }
           Percolation percolation = new Percolation(totalRow);
             while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, totalRow + 1);
                int col = StdRandom.uniform(1, totalRow + 1);
                percolation.open(row, col);
                 System.out.println("Calling isFull : " + row + " , " + col);
                 boolean test = percolation.isFull(row, col);
                 System.out.println("Returned from isFull  = " + row + " , " + col + " : Is full = " + test);
                // System.out.println("Site opened = " + row + " , " + col + " Is full = " + percolation.isFull(row, col));
                // percolation.isFull(row, col);

            }
        System.out.println("Percolates = " + percolation.percolates());
        /*percolation = new Percolation(totalRow);
        percolation.printGrid();
        StdOut.println(percolation.isFull(1, 1));
        StdOut.println(percolation.isOpen(1, 1));
        //percolation.open(1, 1);
        //  5 0 1 0 4 1 0 1 1 1 3 1 4 2 0 2 3 3 0 3 1 3 3 3 4 4 1 4 4
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt() + 1;
            int col = StdIn.readInt() + 1;
            percolation.open(row, col);
            percolation.printGrid();
            System.out.println("Site opened = " + row + " , " + col + " Is full = " + percolation.isFull(row, col));
            System.out.println("Percolates = " + percolation.percolates());
        }*/
        percolation.printGrid();
        //percolation.printFullSiteStatus();
    }
}
