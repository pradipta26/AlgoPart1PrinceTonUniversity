import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int numberOfTrials;

    private final double[] trialFractionResult;
    private final double meanValue;
    private final double stdDeviation;
    private final static double confidenceLevel = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Negative values are not accepted.");
        numberOfTrials = trials;
        trialFractionResult = new double[numberOfTrials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            trialFractionResult[i] = percolation.numberOfOpenSites()/ Math.pow(n, 2);
        }
        meanValue = mean();
        stdDeviation = stddev();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialFractionResult);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialFractionResult);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanValue - confidenceLevel * stdDeviation / Math.sqrt(numberOfTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanValue + confidenceLevel * stdDeviation / Math.sqrt(numberOfTrials);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2 || Integer.parseInt(args[0]) < 0)
            throw new IllegalArgumentException("Insufficient number of argument.");
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percolationStats.meanValue + "\nstddev                  = "
                + percolationStats.stdDeviation + "\n95% confidence interval = ["
                + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
