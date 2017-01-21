import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private Percolation percolation;
	private double[] results;
	private int size;
	private double mean = 0.0;
	private double stddev = 0.0;

	public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
		if (n < 1 || trials < 1)
			throw new IllegalArgumentException();
		this.size = n;
		results = new double[trials];
	}

	public double mean() {                         // sample mean of percolation threshold
		return mean;
	}

	public double stddev() {                        // sample standard deviation of percolation threshold
		return stddev;
	}

	public double confidenceLo() {                // low  endpoint of 95% confidence interval
		return mean - getShift();
	}

	public double confidenceHi() {                  // high endpoint of 95% confidence interval
		return mean + getShift();
	}

	private void calculate() {
		for (int i = 0, a, b; i < results.length; i++) {
			percolation = new Percolation(size);
			do {
				a = StdRandom.uniform(size)+1;
				b = StdRandom.uniform(size)+1;
				if (!percolation.isOpen(a, b))
				{
					results[i] += 1;
					percolation.open(a, b);
				}
			}
			while (!percolation.percolates());
			results[i] /= size*size;
		}
		mean = StdStats.mean(results);
		stddev = StdStats.stddev(results);
	}

	private double getShift() {
		return 1.96 * stddev / results.length;
	}


	public static void main(String[] args) {        // test client (described below)
		PercolationStats percolationStats = new PercolationStats(2, 1000);
		percolationStats.calculate();

		System.out.println(percolationStats.mean());
		System.out.println(percolationStats.stddev());
		System.out.println(percolationStats.confidenceLo());
		System.out.println(percolationStats.confidenceHi());
	}
}
