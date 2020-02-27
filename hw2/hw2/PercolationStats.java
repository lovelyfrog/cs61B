package hw2;
import java.lang.*;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] threshold;
    int N;
    int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.N = N;
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        threshold = new double[T];
        for (int i=0; i<T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(0, N);
                    col = StdRandom.uniform(0, N);
                }
                p.open(row, col);
            }
            threshold[i] = (double)p.numberOfOpenSites() / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
