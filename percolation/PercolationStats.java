import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96; // constant
    private final double[] sites; // array of sites that make threshold
    private final int trials; // amount of trials
    private double mean; // mean of array
    private double s; // standard deviation of array

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        // illegal argument
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("out of bounds");
        }


        sites = new double[trials];
        // loop that creates new percolation object
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            int amtOpen = 0;
            // if the rest still hasnt percolated, keep running.
            while (!test.percolates()) {
                int row = StdRandom.uniformInt(n);
                int col = StdRandom.uniformInt(n);
                // if site isnt opened, open it
                if (!test.isOpen(row, col)) {
                    test.open(row, col);
                    amtOpen++;
                }

            }
            sites[i] = (double) amtOpen / (n * n); // threshold
        }
        mean = StdStats.mean(sites);
        s = StdStats.stddev(sites);

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(sites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double low = mean - ((CONFIDENCE * s) / Math.sqrt(trials));
        return low;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double high = mean + ((CONFIDENCE * s) / Math.sqrt(trials));
        return high;
    }

    // test client as shown in instructions
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch timer = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean()           = " + stats.mean());
        StdOut.println("stddev()         = " + stats.stddev());
        StdOut.println("confidenceLow()  = " + stats.confidenceLow());
        StdOut.println("confidenceHigh() = " + stats.confidenceHigh());
        StdOut.println("elapsed time     = " + timer.elapsedTime());

    }
}
